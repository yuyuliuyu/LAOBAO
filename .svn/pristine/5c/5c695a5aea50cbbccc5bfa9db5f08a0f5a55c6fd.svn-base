<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>基础信息列表</title>
<style type="text/css">
</style>
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
<script src="${base}/template/plugin/public/treeList.js" type="text/javascript"></script>
<style type="text/css">
body {
    margin: 0;
    padding: 0;
    border: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
}
</style>
</head>

<body>
	<div class="mini-fit">
		<div class="mini-splitter" style="width:100%;height:100%;" handlerSize="9">
			<!-- 分中心树 start -->
            <div size="300" showCollapseButton="true">
				<!-- ckId -->
				<input type="hidden" value="${sessionScope.depId}" id="depId" />
				<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
					<span style="padding-left:5px;">组织机构：</span>
				</div>
				<div class="mini-fit" style=" border-top:0;">
					<ul id="deptree" class="mini-tree" url="../basis/branch!getTreeListByUser.action" style="width:100%;height:100%;"
						showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true" >
					</ul>
				</div>
			</div> 
        </div>
	</div>

    <script type="text/javascript">
        mini.parse();
        var tree = mini.get("deptree");
        var grid = mini.get("grid");
        grid.hideColumn("id");
        
        grid.load();
        function onKeyEnter(e) {
            search('jobNumber,name','jobNumber,name');
        }
        ////////////////////////////////////////////// 
    </script>
</body>
</html>