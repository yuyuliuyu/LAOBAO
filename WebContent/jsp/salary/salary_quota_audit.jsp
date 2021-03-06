<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path;
    pageContext.setAttribute("base", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>审核意见</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit" id="form1">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width:30%">审核结果：</td>
                <td class="list_right_box">
                    <input class="mini-radiobuttonlist" style="vertical-align: middle;display: inline-block;" onValueChanged="onValueChanged"
                            repeatItems="1" repeatDirection="vertical" id="isSp" name="isSp" value="1" data="[{id:0,text:'不通过'},{id:1,text:'通过'}]"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" id="txt_note">审核意见：</td>
                <td class="list_right_box">
                    <input class="mini-textarea" id="note" name="note" style="width: 90%;height:120px;" maxLength="1000" onvalidation="onNoteValidation"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width:30%">审批岗位：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" id="nodeId" name = "nodeId"/>
                    <input class="mini-hidden" id="spgwName" name = "spgwName"/>
                    <input class="mini-hidden" id="staffName" name = "staffName"/>
                    <input class="mini-combobox" id="spgw" name="spgw" onValueChanged="onGwValueChanged"
                        url="../system/work_flow!getNextCheckDept.action?pid=${pid}&nodeid=${nodeId}" style="width: 90%;"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">审批人：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" id="spr" name="spr" style="width: 90%;" onValueChanged="onStaffValueChanged"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="checkSub()" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var isSp = mini.get("isSp");
        var note = mini.get("note");
        var txt_note = document.getElementById("txt_note");
        var spgw = mini.get("spgw");
        var spr = mini.get("spr");
        var nodeId = mini.get("nodeId");

        function onGwValueChanged(e) {
            var selected = e.selected;
            var url = "../system/work_flow!getUserByPostposit.action?pid="+e.value+"&companyId="+selected.companyId;
            spr.setUrl(url); 
            spr.select(0);
        	var selected = e.selected;
            nodeId.setValue(selected.curNode);
            mini.get("spgwName").setValue(e.selected.text);
        }

        function onValueChanged(e) {
            var value= e.value;
            if (value == 0) {
                txt_note.innerHTML = "*审核意见：";
            } else txt_note.innerHTML = "审核意见：";
        }

        function onNoteValidation(e) {
            if (isSp.getValue() == 0) {
                if (e.value == "") {
                    if (e.isValid) {
                        e.errorText = "不能为空";
                        e.isValid = false;
                    }
                } else e.isValid = true;
            } else {
                e.isValid = true;
            }
        }

        function checkSub() {
            var form = new mini.Form("#form1");
            var formjson="";
            if(form){
                form.validate();
                if (form.isValid() == false)return;
                // 提交表单数据
                var formdata = form.getData();
                formjson = mini.encode(formdata);
            }
            if (spgw.getValue() != "" && spr.getValue() == "") {
                spr.setIsValid(false);
                mini.alert("审批人不能为空");
                return;
            }
            var v = isSp.getValue();
            var title = "";
            if (isSp.getValue() == 0) {
                title = "确定要审核不通过吗？";
            } else {
                title = "确定要审核通过吗？";
            }
            var assignIds = '${ids}';
            mini.confirm(title, "提醒", function(action) {
                if (action == "ok") {
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_quota!isCheckAUth2.action",
                        type : "post",
                        data : {
                            ids : assignIds,
                            formdata: formjson
                        },
                        success : function(text) {
                            window.parent.unmask();
                            var data = mini.decode(text);
                            var result = data.result;
                            var info = data.info;
                            if (result == "success") {
                                check(assignIds, formjson);
                            } else {
                                if(result.length>0){
                                    var alertInfo = "";
                                    if (result == "3" || result == "4") {
                                        alertInfo = "选中的第"+info+"条分配过程的下一位审核者已经审核，不能操作";
                                    } else if (result == "1") {
                                        // 审核不通过可以不可以再审核
                                    	alertInfo = "选中的第"+info+"条分配过程审核不通过，需要重新提交审批";
                                    } else if (result == "2") {
                                        // 审核通过可以继续审核
                                        check(assignIds, formjson);
                                    } else if (result == "11") {
                                        alertInfo = "选中的第"+info+"条分配过程，请等待上一位审核者审核";
                                    } else if (result == "12") {
                                        alertInfo = "选中的第"+info+"条分配过程的上一位审核者审核不通过，不能操作";
                                    }
                                    if (alertInfo != "") {
                                        mini.showMessageBox({
                                            title: "提醒",
                                            iconCls: "mini-messagebox-warning",
                                            buttons: ["ok"],
                                            message: alertInfo,
                                            callback: function (action) {
                                                grid.reload();
                                            }
                                        });
                                    }
                                }
                            }
                        }
                    });
                }
            });
        }

        function check(idss, formjson) {
            window.parent.loading();
            $.ajax({
                url : "../salary/salary_quota!updateCheckPassed2.action",
                type : "post",
                data : {
                    ids : idss,
                    formdata : formjson
                },
                success : function(text) {
                    window.parent.unmask();
                    if (text == "success") {
                        mini.showMessageBox({
                            title: "提醒",
                            width: 250,
                            iconCls: "mini-messagebox-warning",
                            buttons: ["ok"],
                            message: "操作成功",
                            callback: function (action) {
                                onCancel();
                            }
                        });
                    } else {
                        mini.alert(text);
                    }
                }
            });
        }

        function onStaffValueChanged(e) {
            mini.get("staffName").setValue(e.selected.text);
        }

    </script>
</body>
</html>