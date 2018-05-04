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
    <link href="${base}template/system/css/demo.css" rel="stylesheet" type="text/css" />
	<script src="${base}template/common/js/jquery-1.6.2.min.js" type="text/javascript"></script>
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
    <div class="assbk">
    <div class="asskj">
        <img src="${base}template/system/images/daohang/sf02.png" border="0" usemap="#Map" />
        <map name="Map" id="Map">
          <area shape="rect" coords="282,88,474,124"    href="javascript:openWindow('market/so_xsfh','销售发货');"/><!--href="销售发货"  -->
          <area shape="rect" coords="526,88,719,124"     href="javascript:openWindow('market/xsth','销售退货');"/><!--  href="销售退货" -->
          <area shape="rect" coords="35,177,230,211"     href="javascript:openWindow('arap/khfpqx','客户发票取消');"/><!--  href="销售出货发票取消"-->
          <area shape="rect" coords="281,178,472,211"   href="javascript:openWindow('arap/xsfhfp','销售发货发票');" />
          <area shape="rect" coords="526,178,719,214"   href="javascript:openWindow('arap/xsthfp','销售退货发票');" />
          <area shape="rect" coords="770,178,964,214"  href="javascript:openWindow('arap/khfpqx','客户发票取消');"/><!-- href="销售退货发票取消"  --><!--  -->
          <area shape="rect" coords="147,304,341,341"  href="javascript:openWindow('arap/yskhk','预收客户款');" />
          <area shape="rect" coords="402,304,596,341"  href="javascript:openWindow('arap/sqkhhk','收取客户货款');" />
          <area shape="rect" coords="402,385,595,421"  href="javascript:openWindow('arap/yskcx','应收款查询');"> <!-- href="应收货款（账龄）查询" -->
        </map>
    </div>
</div>
  </body>
</html>


