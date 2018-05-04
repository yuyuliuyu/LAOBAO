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
    
    <title>基本设置</title>
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
			tab.url = "${base }" + urlname + "!list.action";

			tabs.addTab(tab);
		}
		tabs.activeTab(tab);
	}
</script>
  </head>
  
  <body>
     <div class="assbk" >
    <div class="asskj">
        <img src="${base}template/system/images/daohang/cj-jb.png"  class="imgh"  border="0" usemap="#Map" />
        <map name="Map"  id="Map">
          		 <area shape="rect" coords="35,25,128,136" href="javascript:openWindow('workmgt/gzzxsz', '工作中心设置');"> <!-- 工作中心设置 -->
		          <area shape="rect" coords="214,21,308,135" href="javascript:openWindow('workmgt/gzzxyg', '工作中心员工');">	<!-- 工作中心员工 -->
		          <area shape="rect" coords="385,23,472,135" href="javascript:openWindow('workmgt/gzjj', '工装夹具');">	<!-- 工装夹具 -->
		          <area shape="rect" coords="544,24,629,135" href="javascript:openWindow('workmgt/gxsz', '工序设置');">	<!-- 工序设置 -->
		          <area shape="rect" coords="706,23,799,136" href="javascript:openWindow('workmgt/gylcwh', '工艺流程维护');">	<!-- 工艺流程维护 -->
        </map>
  		</div>
	</div>
  </body>
</html>






