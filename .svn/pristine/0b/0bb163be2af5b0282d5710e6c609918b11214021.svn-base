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
    <title>个人商业保险缴费增加编辑</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit" id="form1">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top">*姓名：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" id="id" name="id" value="${salaryPerInsurance.id}"/>
                    <input class="mini-buttonedit" style="width:90%" name="staffId" value="${salaryPerInsurance.staffId}" text="${staffName}"
                        onbuttonclick ="onButtonEdit(this,'选择人员','../salary/salary_business!person.action', 880, 480,'name','','','deptId,deptName','deptId,deptName')" required="true"/>
                </td>
                <td class="list_left_box1" valign="top">部门：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" style="width:90%" id="deptId" name="deptId" value="${salaryPerInsurance.deptId}"/>
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" id="deptName" value="${deptName}"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*年份：</td>
                <td class="list_right_box">
                    <input class="mini-spinner" style="width:90%" id="year" name="year" value="${salaryPerInsurance.year}" minValue="1900" maxValue="2100"required="true"/>
                </td>
                <td class="list_left_box1" valign="top">*保险项目：</td>
                <td class="list_right_box">
                    <input id="ibfId" name="ibfId" class="mini-lookup" value="${salaryPerInsurance.ibfId}"text="${bxName}"
                         textField="name" valueField="id" popupWidth="auto" emptyText="请选择..." onbuttonclick="onButtonEditJfFuli"
                         popup="#gridPanel3" grid="#datagrid3" required="true" style="width:90%"/>
                     <div id="gridPanel3" class="mini-panel" title="header" iconCls="icon-add" style="width:450px;height:250px;" 
                       showToolbar="true" showCloseButton="true" showHeader="false" bodyStyle="padding:0"  borderStyle="border:0">
                         <div property="toolbar" style="padding:5px;padding-left:8px;text-align:center;">
                             <div style="float:left;padding-bottom:2px;">
                                <span>名称：</span>
                                <input id="insuranceName" class="mini-textbox" style="width:160px;" onenter="onButtonEditJfFuli"/>
                                <a class="mini-button" onclick="onButtonEditJfFuli">查询</a>
                             </div>
                             <div style="clear:both;"></div>
                         </div>
                         <div id="datagrid3" class="mini-datagrid" style="width:100%;height:100%;" 
                            borderStyle="border:0" pagerSize="10">
                             <div property="columns">
                                 <div type="checkcolumn" width="30"></div>
                                 <div field="id" id="id" width="20" name="id" class="mini-hidden"></div>
                                 <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                                 <div field="name" width="100" headerAlign="center" align="center">福利项目</div>
                             </div>
                         </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*保额（万元）：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="bf"vtype="float" value="${salaryPerInsurance.bf}"maxLength="20" required="true"/>
                </td>
                <td class="list_left_box1" valign="top">*费率（‰）：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="bxfl"vtype="float" value="${salaryPerInsurance.bxfl}" maxLength="1000" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*公司缴费金额（元）：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="gsjfje" vtype="float" value="${salaryPerInsurance.gsjfje}" maxLength="20" required="true"/>
                </td>
                <td class="list_left_box1" valign="top">*个人缴费金额（元）：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="grjfje" vtype="float" value="${salaryPerInsurance.grjfje}" maxLength="20" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*缴费日期：</td>
                <td class="list_right_box" >
                    <input class="mini-datepicker" style="width:90%" id="beginDate" name="beginDate" format="yyyy年MM月dd日" value="${salaryPerInsurance.beginDate}" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*被保险人：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="bbxr" value="${salaryPerInsurance.bbxr}" maxLength="25" required="true"/>
                </td>
                <td class="list_left_box1" valign="top">*与投保人关系：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="ytbrgx" value="${salaryPerInsurance.ytbrgx}" maxLength="25" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*证件号：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="idCard" value="${salaryPerInsurance.idCard}" maxLength="18"required="true"/>
                </td>
                <td class="list_left_box1" valign="top">*被保险人出生日期：</td>
                <td class="list_right_box">
                    <input class="mini-datepicker" style="width:90%" name="birthDate" format="yyyy年MM月dd日" value="${salaryPerInsurance.birthDate}" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*被保险人性别：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" style="width:90%" name="sex" value="${salaryPerInsurance.sex}"
                        data="[{id:0, text:'男'},{id:1, text:'女'}]" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">描述：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" style="width:90%;height: 78px" name="note" value="${salaryPerInsurance.note}" maxLength="1000" />
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOkClose()" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var year = mini.get("year");
        var beginDate = mini.get("beginDate");

        $(function() {
            var id = '${salaryPerInsurance.id}';
            if (id == "") {
                var date = new Date();
                year.setValue(date.getFullYear());
                beginDate.setValue(date);
            }
        });

        function onOkClose() {
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
                url : "../salary/salary_business!saveOrUpdateToPer.action",
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
                                document.location.reload();
                                window.CloseOwnerWindow();
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

        // 福利项目
        function onButtonEditJfFuli(e) {
            var datagrid3 = mini.get("datagrid3");
            datagrid3.hideColumn("id");
            datagrid3.setUrl("../basis/insurance_benefits!getAllItemsListData.action");
            datagrid3.load({insuranceName: mini.get("insuranceName").getValue()});
        }
    </script>
</body>
</html>