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
    <title>薪酬岗位字典增加、编辑</title>
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
                <td class="list_left_box1" valign="top"style="width:20%">*员工账号：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" id="id" name="id" value="${mapData.id}"/>
                    <input class="mini-textbox" name="userName" value="${mapData.userName}" style="width:90%" required="true"
                        onblur = "onBlur"/>
                </td>
                <td class="list_left_box1" valign="top">*可查看类型：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" value="${mapData.watchType}" id="watchType" name="watchType" style="width: 90%" onvaluechanged="onValueChanged"
                        required="true" data="[{id:1, text:'连续薪酬期间个数'},{id:2, text:'可查看年份'},{id:3, text:'可查看哪几个薪酬期间'}]"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*类型值：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textbox" id="typeValue" name="typeValue" value="${mapData.typeValue}" style="width:90%" maxLength="2000"
                        onvalidation="onValidation" />
                    <input class="mini-hidden" id="companyId" value="${depId}"/>
                    <input class="mini-hidden" id="display" />
                    <input class="mini-buttonedit" id="hidenTypeValue" name="hidenTypeValue" value="${mapData.typeValue}" text="${mapData.typeValueName}" style="width:90%" allowInput="false"
                        showClose="true" oncloseclick= "onCloseClick" onbuttonclick="onRollButtonClick"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOKClose()" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var watchType = mini.get("watchType");
        var typeValue = mini.get("typeValue");
        var hidenTypeValue = mini.get("hidenTypeValue");
        var display = mini.get("display");
        var isHand = false;

        $(document).ready(function() {
            isHand = true;
            watchType.doValueChanged();
        });

        function onValueChanged(e) {
            var value = e.value;
            if (value == 1) {
                typeValue.show();
                hidenTypeValue.hide();
            } else {
                if (value==2) {
                    display.setValue(1);
                } else if (value==3) {
                    display.setValue(0);
                }
                typeValue.hide();
                hidenTypeValue.show();
            }
            if (!isHand) {
                typeValue.setValue(null);
                hidenTypeValue.setValue(null);
                hidenTypeValue.setText(null);
            }
            isHand = false;
        }

        function onBlur(e) {
            var v = e.sender.value;
            if (v != "" || v != null) {
                window.parent.loading();
                $.ajax({
                    url:"../salary/salary_pager!getCurAccountCompany.action",
                    type: "get",
                    data : {
                        userName : v
                    },
                    success: function(text) {
                        window.parent.unmask();
                        var data = mini.decode(text);
                        if (data.success == "true") {
                            mini.get("companyId").setValue(data.info);
                        } else {
                            mini.alert(data.info);
                            return;
                        }
                    }
                });
            }
        }

        function onValidation(e) {
            var v = watchType.getValue();
            var value = e.value;
            if (v == 1) {
                if (!isNumber(value)) {
                    e.errorText = "只能输入正整数";
                    e.isValid = false;
                }
            }
        }

        function isNumber(v) {
            var re = new RegExp("^[0-9]+$");
            if (re.test(v)) return true;
            return false;
        }

        function onCloseClick(e) {
            var obj = e.sender;
            obj.setText("");
            obj.setValue("");
        }

        function onRollButtonClick(e) {
            onButtonEdit(this,'选择薪酬期间','../salary/salary_pager!payPeriodSelector.action',980,580,'','companyId,display','id,display')
        }

        function onOKClose() {
            if (watchType.getValue() == 1) {
                if (typeValue.getValue() == null || typeValue.getValue() == "") {
                    typeValue.setIsValid(false);
                    typeValue.setErrorText("不能为空");
                    return;
                }
            } else {
                if (hidenTypeValue.getValue() == null || hidenTypeValue.getValue() == "") {
                    hidenTypeValue.setIsValid(false);
                    hidenTypeValue.setErrorText("不能为空");
                    return;
                }
            }
            onOk('salary','salary_pager', '', false);
        }
    </script>
</body>
</html>