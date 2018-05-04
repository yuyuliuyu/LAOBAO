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
    
    <title>业务流程</title>
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
        <img src="${base}template/system/images/daohang/xs-ywlc.png"  class="imgh"  border="0" usemap="#Map" />
        <map name="Map"  id="Map">
          	  <area shape="rect" coords="13,32,209,64" href="javascript:openWindow('market/xsbjd', '客户报价单');">	<!-- 客户报价单 -->
	          <area shape="rect" coords="286,33,482,64" href="javascript:openWindow('market/so_xsddwh', '销售订单维护');">	<!-- 销售订单维护 -->
	          <area shape="rect" coords="285,119,481,154" href="javascript:openWindow('market/xsddbg', '销售订单变更');">	<!-- 销售订单变更 -->
	          <area shape="rect" coords="149,215,344,246" href="javascript:openWindow('market/so_xsfh', '销售发货');">	<!-- 销售发货 -->
	          <area shape="rect" coords="413,217,609,249" href="javascript:openWindow('market/xsth', '销售退货');">	<!-- 销售退货 -->
	          <area shape="rect" coords="171,303,365,337" href="javascript:openWindow('market/xsddztxg', '销售订单状态控制');">	<!-- 销售订单状态控制 -->
	          <area shape="rect" coords="389,304,587,336" href="javascript:openWindow('market/khdzd', '客户对账单');">	<!-- 客户对账单 -->
        </map>
  		</div>
	</div>
  </body>
</html>






