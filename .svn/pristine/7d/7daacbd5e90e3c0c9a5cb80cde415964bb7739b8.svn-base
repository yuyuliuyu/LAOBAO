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
    <title>薪酬审批色值编辑</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div id="form1" class="mini-fit">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top">公司：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" id="id" name="id" value="${checkSalaryColor.id}"/>
                    <input class="mini-combobox asLabel" readOnly="true" name="companyId" textField="fzx"
                        url="../salary/salary_manage!getBranches.action" value="${depId}" style="width:90%"/>
                </td>
                <td class="list_left_box1" valign="top">薪资项目：</td>
                <td class="list_right_box">
                    <input name="ibfId" class="mini-lookup" value="${checkSalaryColor.ibfId}"text="${salaryName}"
                        textField="name" valueField="id" popupWidth="auto" emptyText="请选择..." onbuttonclick="onButtonEditJfComAlert"
                        popup="#gridPanel" grid="#datagrid1" required="true" style="width:90%"/>
                    <div id="gridPanel" class="mini-panel" title="header" iconCls="icon-add" style="width:450px;height:250px;" 
                      showToolbar="true" showCloseButton="true" showHeader="false" bodyStyle="padding:0"  borderStyle="border:0">
                        <div property="toolbar" style="padding:5px;padding-left:8px;text-align:center;">
                            <div style="float:left;padding-bottom:2px;">
                               <span>名称：</span>
                               <input id="salaryName" class="mini-textbox" style="width:160px;" onenter="onButtonEditJfComAlert"/>
                               <a class="mini-button" onclick="onButtonEditJfComAlert">查询</a>
                            </div>
                        </div>
                        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
                           borderStyle="border:0" pagerSize="10">
                            <div property="columns">
                                <div type="checkcolumn" width="30">选择</div>
                                <div field="id" id="id" width="20" name="id" class="mini-hidden"></div>
                                <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                                <div field="name" width="100" headerAlign="center" align="center">薪资项目</div>
                            </div>
                        </div>
                   </div>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">颜色：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="color" value="${checkSalaryColor.color}" maxLength="20" required="true"/>
                </td>
                <td class="list_left_box1" valign="top">差值：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="colorDiff" value="${checkSalaryColor.colorDiff}" maxLength="20" vtype="float" required="true"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onok" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();

        function onok() {
        	// 表单部分
            var form = new mini.Form("#form1");
            var formjson="";
            if(form){
                form.validate();
                if (form.isValid() == false)return;
                // 提交表单数据
                var formdata = form.getData();
                formjson = mini.encode(formdata); 
            }
            window.parent.loading();
            $.ajax({
                url : "../salary/check_salary!saveOrUpdate.action",
                type : "post",
                data : {
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
                            message: "保存成功",
                            callback: function (action) {
                            	CloseWindow("ok")
                            }
                        });
                    } else {
                        if(text.length>0){
                            mini.alert(text);
                        }
                    }
                }
            });
        }

        /** 关闭按钮点击事件 */
        function onCancel() {
            CloseWindow("cancel");
        }
        
        /** 关闭窗口 */
        function CloseWindow(action) {
            if (window.CloseOwnerWindow)
                return window.CloseOwnerWindow(action);
            else
                window.close();
        }

        function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "../salary/formula!tree.action",
                showMaxButton: false,
                title: "选择父级资源",
                width: 300,
                height: 440,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        mini.get("pid").setValue(data.id);
                        mini.get("btnEdit1").setValue(data.id);
                        mini.get("btnEdit1").setText(data.method);
                    }
                }
            });
        }

        // 薪资项目
        function onButtonEditJfComAlert(e) {
            var datagrid1 = mini.get("datagrid1");
            datagrid1.hideColumn("id");
            datagrid1.setUrl("../salary/salary_items!getSalaryItemListData.action");
            var sd = {};
            sd.name = mini.get("salaryName").getValue();
            datagrid1.load({searchData: mini.encode(sd)});
        }
    </script>
</body>
</html>