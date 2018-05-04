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
    <title>办理社保</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width: 20%">工号：</td>
                <td class="list_right_box" >
                    <input class="mini-textbox"style="width: 90%"/>
                </td>
                <td class="list_left_box1" valign="top"style="width: 20%">姓名：</td>
                <td class="list_right_box">
                    <input class="mini-textbox"style="width: 90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">公司：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" value=""style="width: 90%"/>
                </td>
                <td class="list_left_box1" valign="top">部门：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" value=""style="width: 90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">职位：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" value=""style="width: 90%"/>
                </td>
                <td class="list_left_box1" valign="top">缴存状态：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" data="[{id:0,text:'正常'},{id:1,text:'补缴'}]"value="0" style="width: 90%"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
            allowAlternating="true" multiSelect="true" url="../salary/salary_rate!getAllRateListData.action"
            idField="id" allowResize="false" showPager="false" >
            <div property="columns">
                <div type="checkcolumn" width="40"></div>
                <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                <div field="" width="80" headerAlign="center" align="center">保险项目</div>
                <div field="" width="80" headerAlign="center" align="center">缴费地域</div>
                <div field="" width="80" headerAlign="center" align="center">缴费单位</div>
                <div field="" width="80" headerAlign="center" align="center">开始缴费年月</div>
                <div field="" width="80" headerAlign="center" align="center">员工类型</div>
                <div field="" width="80" headerAlign="center" align="center">是否代缴</div>
                <div field="" width="80" headerAlign="center" align="center">备注</div>
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