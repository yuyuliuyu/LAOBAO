<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("base", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>一般程序列表页面</title>
<link href="../demo.css" rel="stylesheet" type="text/css" />
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
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="mini-toolbar" style="padding:0px;">
		<table style="width:100%;">
			<tr>
				<td></td>
			</tr>
		</table>
	</div>
	<div class="mini-fit">
		<div id="datagrid1" class="mini-datagrid"
			style="width:100%;height:100%;" allowResize="false"
			url="../check/overtime_sub!jsonlist.action?pid=${pid}" idField="id"
			multiSelect="true">
			<div property="columns">
				<div type="indexcolumn" width="50" headerAlign="center">序列</div>
				<div field="id" name="id" id="id" width="100" headerAlign="center"
					align="center">id</div>
				<div field="auditName" id="auditName" name="auditName" width="80"
					headerAlign="center" align="center">审批人</div>
				<div field="auditDate" id="auditDate" name="auditDate" width="120"
					headerAlign="center" align="center">审批时间</div>
				<div field="opinion" id="opinion" name="opinion" width="300"
					headerAlign="center" align="center">审批内容</div>
				<div field="auditStatus" id="auditStatus" name="auditStatus"
					width="100" headerAlign="center" align="center">审批结果</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		mini.parse();
		var grid = mini.get("datagrid1");
		grid.hideColumn("idColumn");
		grid.hideColumn("id");
		grid.hideColumn("isShow");
		grid.load();
	</script>
</body>
</html>