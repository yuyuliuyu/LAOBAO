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
    
    <title>销售</title>
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
        <img src="${base}template/system/images/daohang/xs-xs.png"  class="imgh"  border="0" usemap="#Map" />
        <map name="Map"  id="Map">
          	    <area shape="rect" coords="47,21,125,135" href="javascript:openWindow('market/so_salesman', '销售人员维护');"> <!-- 销售员资料 -->
	          <area shape="rect" coords="226,26,304,137" href="javascript:openWindow('jcsj/customer', '客户资料维护');"> <!-- 客户资料 -->
	          <area shape="rect" coords="409,23,483,137" href="javascript:openWindow('market/so_cust_pro', '客户料号维护');"> <!-- 客户料号 -->
        </map>
  		</div>
	</div>
  </body>
</html>






