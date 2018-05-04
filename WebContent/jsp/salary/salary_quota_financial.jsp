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
    <title>薪资项目计算公式</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width:30px">*</td>
                <td class="list_right_box">
                    <div class="mini-radiobuttonlist" id="gs" repeatItems="1" repeatLayout="table" repeatDirection="horizontal" onvaluechanged= "onValueChanged"
                        textField="content" valueField="id" url="../salary/salary_quota!getAllGroupWageFinancialList.action?assignStaffId=${assignStaffId}&id=${id}" >
                    </div>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top"></td>
                <td class="list_right_box">
                    <textarea class="mini-textarea" style="width: 90%;height:70px" id="content"></textarea>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOKClose()" style="width:60px;margin-right:20px;" iconCls="icon-ok">计算</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var retResult = 0;
        var content = mini.get("content");
        var gs = mini.get("gs");
        gs.select(0);
        gs.doValueChanged();

        function GetData() {
            return retResult;
        }

        function onOKClose() {
            var Ol0lo = gs.Ol0lo;
            if (Ol0lo[0].type == 2) {
                if (content.getValue() == "") {
                    mini.alert("公式不能为空");
                    return;
                }
                retResult = content.getValue();
            }
            CloseWindow("ok");
        }

        function onValueChanged(e) {
            var sender = e.sender;
            var Ol0lo = sender.Ol0lo;
            if (Ol0lo[0].type == 2) {
                content.enable();
                content.setValue("result=");
            } else {
                content.disable();
                content.setValue("");
                retResult = Ol0lo[0].cntMath;
            }
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

    </script>
</body>
</html>