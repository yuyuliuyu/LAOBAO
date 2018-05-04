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
    <title>基础工资字典</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
            url="../salary/salary_base!getDataList.action" showPager="false"
            multiSelect="true" idField="id" allowResize="false" allowCellWrap ="true">
            <div property="columns">
                <div type="checkcolumn" width="40"></div>
                <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                <div field="jcgz" name="jcgz" width="100" headerAlign="center" align="center">基础工资</div>
                <div field="zjlb" width="100" headerAlign="center" align="center">职级类别</div>
                <div field="xs" width="80" headerAlign="center" align="center">系数</div>
                <div field="content" name="content" width="120" headerAlign="center" align="center">内容</div>
                <div field="display" width="100" headerAlign="center" align="center">显示</div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();

        var grid = mini.get("grid");

        grid.on("load", function () {
            grid.mergeColumns(["jcgz", "content"]);
        });
    </script>
</body>
</html>