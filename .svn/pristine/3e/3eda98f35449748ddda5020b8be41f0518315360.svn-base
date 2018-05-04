<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>工作台设置</title>
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="../template/plugin/public/cent_dep.js"></script>
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
				<div class="mini-toolbar" style="padding:0px;border-top:0;">
					<table style="width:100%;">
						<tr> 
						</tr>
					</table>
				</div>
	    <input type="hidden"  name="flag" id="flag" value="${flag}" />
		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;"
			contextMenu="#gridMenu" url="../contract/employ_agreement!lookjson.action?id=${id}" allowAlternating="true"
			idField="id" pageSize="10" onshowrowdetail="onShowRowDetail">
						<div property="columns">
               			<div type="checkcolumn" width="50"></div>
				        <div field="id" name="id" id="id" width="100" headerAlign="center" align="center">id</div> 
				        <div field="chengememo"  name="chengememo"  width="100" headerAlign="center" align="center">合同编号</div>
				        <div field="changeman"  name="changeman"  width="100" headerAlign="center" align="center">变更人</div>
				        <div field="createDate"  name="createDate"  width="100" headerAlign="center" align="center">变更时间</div>    
					</div>
				</div> 
		</div>
	<script type="text/javascript">
		var flag=$("#flag").val();
		mini.parse(); 
		var grid = mini.get("grid"); 
		grid.hideColumn("id");
		grid.hideColumn("indexcolumn");
		grid.load(); 
	</script>
</body>
</html>