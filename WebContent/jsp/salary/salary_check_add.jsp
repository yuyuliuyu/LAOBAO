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
    <title>审批流设置</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width: 15%">名称：</td>
                <td class="list_right_box" >
                    <input class="mini-textbox"style="width: 90%"/>
                </td>
                <td class="list_left_box1" valign="top"style="width: 15%">薪资组：</td>
                <td class="list_right_box">
                    <input class="mini-combobox"style="width: 90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">状态：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" value=""style="width: 90%"/>
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
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <a class="mini-button" iconCls="icon-add" onclick="">增加</a>
                    <a class="mini-button" iconCls="icon-remove" onclick="">删除</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
            allowAlternating="true" multiSelect="true" url="../salary/salary_rate!getAllRateListData.action"
            idField="id" allowResize="false" showPager = "false">
            <div property="columns">
                <div type="checkcolumn" width="40"></div>
                <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                <div field="" width="40" headerAlign="center" align="center">级别</div>
                <div field="" width="80" headerAlign="center" align="center">类型</div>
                <div field="" width="80" headerAlign="center" align="center">人员/角色</div>
            </div>
        </div>
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