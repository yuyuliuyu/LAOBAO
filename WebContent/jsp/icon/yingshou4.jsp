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
    
    <title>My JSP 'shengchan2.jsp' starting page</title>
    <link href="${base}template/system/css/demo.css"  rel="stylesheet"  type="text/css" />
    <script src="${base}template/common/js/jquery-1.6.2.min.js"  type="text/javascript"></script>
	<style type="text/css">

	html
	{
		border:solid 1px #999;
		border-top:0;
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
        <img src="${base}template/system/images/daohang/sf05.png" border="0" usemap="#Map" />
        <map name="Map" id="Map">
          <area shape="rect" coords="277,112,471,149"   href="javascript:openWindow('arap/qtlsfkfp','其它零散付款发票');"/><!-- href="其它零散付款发票" -->
          <area shape="rect" coords="87,160,278,197"   href="javascript:openWindow('arap/gysfpqx', '供应商发票取消');"/><!-- href="零散发票付款取消" -->
          <area shape="rect" coords="279,206,471,241"  href="javascript:openWindow('arap/qtlsfk','其它零散付款');"/><!--href="其它零散付款"  -->
          <area shape="rect" coords="277,300,471,335"  href="javascript:openWindow('arap/yfkcx','应付款查询');"/><!-- href="应收付款（账龄）查询" -->
          <area shape="rect" coords="530,112,724,146"  href="javascript:openWindow('arap/ar_qtlsskfp','其它零散收款发票');"/><!-- href="其它零散收款发票" -->
          <area shape="rect" coords="722,161,917,198"  href="javascript:openWindow('arap/khfpqx','客户发票取消');" /><!--  href="零散收款发票取消"-->
          <area shape="rect" coords="529,207,724,243"  href="javascript:openWindow('arap/ar_qtlssk','其它零散收款');"/><!-- href="其它零散收款" -->
          <area shape="rect" coords="530,300,724,337"  href="javascript:openWindow('arap/yskcx','应收款查询');"/><!--  href="应收付款（账龄）查询"-->
        </map>
    </div>
</div>
  </body>
</html>
