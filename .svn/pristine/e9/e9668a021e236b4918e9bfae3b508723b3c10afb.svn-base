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
        <img src="${base}template/system/images/daohang/zj-jiben.png"  class="imgh"  border="0" usemap="#Map" />
        <map name="Map"  id="Map">
          	  <area shape="rect" coords="28,17,119,127" href="javascript:openWindow('qcmgt/check_project', '检验项目设置');"> <!--qcmgt/check_project 检验项目设置 -->
          	 <area shape="rect" coords="225,15,335,131" href="javascript:openWindow('qcmgt/check_group_m','检验项目组设置');"> <!-- 检验项目组设置 -->
         	 <area shape="rect" coords="435,18,530,130" href="javascript:openWindow('qcmgt/disquality_code', '不良代码设置');">	<!-- 不良代码设置 -->
         	 <area shape="rect" coords="635,17,756,129" href="javascript:openWindow('qcmgt/material_check','物料检验参数设置');"> 	<!-- 物料检验参数设置 -->
        </map>
  		</div>
	</div>
  </body>
</html>






