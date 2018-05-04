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
    <title>批量调整员工薪级</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit" id="form1">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top">*调整过程名称：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width: 90%"/>
                </td>
                <td class="list_left_box1" valign="top">*组织机构：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" value="" style="width: 90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*调整类型：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" value="" style="width: 90%"/>
                </td>
                <td class="list_left_box1" valign="top">*调整方式：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" value="" style="width: 90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*调整级数：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" value="" style="width: 90%"/>
                </td>
                <td class="list_left_box1" valign="top">*生效日期：</td>
                <td class="list_right_box">
                    <input class="mini-datepicker" value="" style="width: 90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*调整的薪酬标准：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" value="" style="width: 90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">描述：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" style="width: 90%;height:60px" value=""/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOKClose()" style="margin-right:20px;" iconCls="icon-save">提交审批</a>
        <a class="mini-button" onclick="onOKClose()" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
    </script>
</body>
</html>