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
    <div class="assbk">
    <div class="asskj">
        <img src="${base}template/system/images/daohang/sf01.png"  border="0" usemap="#Map" />
        <map name="Map" id="Map">
          <area shape="rect" coords="281,0,476,38"      href="javascript:openWindow('purchase/po_cglssh', '采购临时收货');"/><!--href="采购临时收货"  -->
          <area shape="rect" coords="282,70,474,110"  href="javascript:openWindow('qcmgt/iqc_in', 'ICQ正式入库');"/><!-- href="ICQ正式入库" -->
          <area shape="rect" coords="525,72,721,109"  href="javascript:openWindow('purchase/po_cgth', '采购退货');"/><!-- href="采购退货" -->
          <area shape="rect" coords="38,140,231,179"  href="javascript:openWindow('arap/gysfpqx', '供应商发票取消');"/><!--  href="采购收货发票取消"-->
          <area shape="rect" coords="281,141,476,180" href="javascript:openWindow('arap/cgshfp','采购收货发票');" />
          <area shape="rect" coords="525,141,721,180" href="javascript:openWindow('arap/cgthfp','采购退货发票'); " />
          <area shape="rect" coords="772,142,967,178"  href="javascript:openWindow('arap/gysfpqx', '供应商发票取消');"/><!--  href="采购退货发票取消"-->
          <area shape="rect" coords="148,260,342,295" href="javascript:openWindow('arap/yfkggys','预付款给供应商');" />
          <area shape="rect" coords="403,258,597,296" href="javascript:openWindow('arap/zfgyshk','支付供应商货款');" />
          <area shape="rect" coords="402,327,596,425" href="javascript:openWindow('arap/yfkcx','应付款查询');" />
        </map>
    </div>
</div>
  </body>
</html>
