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
    <title>员工固定薪酬结构调整历史</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1" class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="onKeyEnter" value="${mapInfo.jobNumber}"/>
                    &nbsp;姓名：<input class="mini-textbox" id="name" onenter="onKeyEnter" value="${mapInfo.name}"/>
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('jobNumber,name','jobNumber,name')">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_record!getAllRecordHistoryListData.action?staffId=${mapInfo.staffId}" pageSize="20"
            allowAlternating="true" idField="id" allowResize="false">
            <div property="columns">
                <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div field="name" headerAlign="center" align="center">薪资项目</div>
                <div field="adjustDate" headerAlign="center" align="center" renderer="ondayRenderer2">调整日期</div>
                <div field="adjustFront"  headerAlign="center" align="center">调整前值</div>
                <div field="adjustNext"  headerAlign="center" align="center">调整前后</div>
                <div field="note" headerAlign="center" align="center">调整原因</div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">关闭</a>
    </div>

    <script type="text/javascript">
        mini.parse();

    </script>
</body>
</html>