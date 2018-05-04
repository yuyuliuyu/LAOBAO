<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>项目</title>
    <link href="${base}template/system/css/demo.css" rel="stylesheet"  type="text/css" />
    <script src="${base}template/common/js/jquery-1.6.2.min.js" type="text/javascript"></script>
	<style type="text/css">

		html
		{
			border:solid 1px #999;
			border-top:0;
		}
		body
		{
			padding:0;	
		}

	.mini-tabs-body {
		padding:0;
	}
	</style>



<script type="text/javascript">

	function openWindow(urlname, name) {

		var tabs = window.parent.parent.mini.get("mainTabs");

		var id = "tab$"+name;
		var tab = tabs.getTab(id);
		if (!tab) {
			tab = {};
			tab.name = id;
			tab.title = name;
			tab.showCloseButton = true;
			tab.url =urlname + "!list.action";

			tabs.addTab(tab);
		}
		tabs.activeTab(tab);
	}
</script>
  </head>
  
  <body>
     <div class="assbk" >
    <div class="asskj">
        <img src="${base}template/system/images/daohang/xiangmu.png"  class="imgh"  border="0" usemap="#Map" />
         <map name="Map"  id="Map">
        
          <area shape="rect" coords="105,30,297,65" href="javascript:openWindow('../project/new_project','项目设置');">		<!-- 项目设置 -->
          <area shape="rect" coords="105,110,297,145" href="javascript:openWindow('../project/project_item','物料清单');">		<!-- 项目设置 -->
          <area shape="rect" coords="102,186,296,222" href="javascript:openWindow('../project/project_in','施工领料');">		<!-- 施工领料 -->
          <area shape="rect" coords="375,187,563,221" href="javascript:openWindow('../project/project_out','施工退料');">		<!-- 施工退料 -->
          <area shape="rect" coords="103,269,299,303" href="javascript:openWindow('../project/project_loss','报损单');">		<!-- 报损单 -->
          
      	</map>
  		</div>
	</div>
  </body>
</html>






