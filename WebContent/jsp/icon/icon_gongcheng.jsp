<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工程</title>
<link href="${base}template/system/css/demo.css" rel="stylesheet" type="text/css" />


	<script type="text/javascript">
	
		function openWindow(urlname, name) {
	
			var tabs = window.parent.mini.get("mainTabs");
	
			var id = "tab$"+name;
			var tab = tabs.getTab(id);
			if (!tab) {
				tab = {};
				tab.name = id;
				tab.title = name;
				tab.showCloseButton = true;
				tab.url = "${base }" + urlname+".action";
	
				tabs.addTab(tab);
			}
			tabs.activeTab(tab);
		}
	</script>
</head>
  
<body style="background:white;">
<div class="assbk" style="width:100%; height:100%; overflow :auto;">
    <div class="asskj">
        <img src="${base }template/system/images/daohang/gongcheng.jpg" border="0" usemap="#Map" />
        <map name="Map" id="Map">
          	  <area shape="rect" coords="377,88,578,123" href="javascript:openWindow('bominfo/item!list','物料数据维护');"><!-- 物料数据维护 -->
	          <area shape="rect" coords="617,171,820,206" href="javascript:openWindow('bominfo/bom_replace!list','物料替代关系');"><!-- 物料替关系维护 -->
	          <area shape="rect" coords="378,270,577,305" href="javascript:openWindow('bominfo/bom!list','BOM维护');"><!-- BOM维护 -->
	          <area shape="rect" coords="646,270,847,307" href="javascript:openWindow('bominfo/bom_ecn!list','BOM变更');"><!-- BOM变更 -->
	          <area shape="rect" coords="260,346,460,383" href="javascript:openWindow('bominfo/bom!cxson','BOM查询子料');"><!-- BOM查询子料 -->
	          <area shape="rect" coords="492,346,693,383" href="javascript:openWindow('bominfo/bom!cxprd','BOM反查父料 ');"> <!-- BOM查询父料 -->
        </map>
    </div>
</div>
  </body>
</html>
