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
    <title>员工福利增加</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1" class="mini-fit">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width: 20%">员工：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox"style="width: 90%"/>
                </td>
                <td class="list_left_box1" valign="top"style="width: 20%">公司：</td>
                <td class="list_right_box">
                    <input class="mini-combobox"style="width: 90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">部门：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" value=""style="width: 90%"/>
                </td>
                <td class="list_left_box1" valign="top">福利终止：</td>
                <td class="list_right_box">
                    <input class="mini-checkbox" value=""style="width: 90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">福利名称：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" value=""style="width: 90%"/>
                </td>
                <td class="list_left_box1" valign="top">发放金额：</td>
                <td class="list_right_box">
                    <input class="mini-spinner" value=""style="width: 90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">开始日期：</td>
                <td class="list_right_box">
                    <input class="mini-datepicker" value=""style="width: 90%;"/>
                </td>
                <td class="list_left_box1" valign="top">结束日期：</td>
                <td class="list_right_box">
                    <input class="mini-datepicker" value=""style="width: 90%;"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">备注：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" value=""style="width: 90%;height:60px;"/>
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
        var grid = mini.get("grid");

    </script>
</body>
</html>