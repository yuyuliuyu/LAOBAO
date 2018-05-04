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
        <img src="${base}template/system/images/daohang/cg_caigou.png"  class="imgh"  border="0" usemap="#Map" />
        <map name="Map"  id="Map">
           	 <area shape="rect" coords="399,6,587,38"      href="javascript:openWindow('purchase/po_cgsqdwh', '采购申请单维护');"/><!-- 采购申请单维护 -->
	          <area shape="rect" coords="601,57,791,88"     href="javascript:openWindow('purchase/po_cgsqdbg', '采购申请单变更');"/><!-- 采购申请单变更 -->
	          <area shape="rect" coords="131,111,319,142"  href="javascript:openWindow('purchase/po_supplier_price', '供应商报价单');" /><!-- 供应商报价单 -->
              <area shape="rect" coords="400,112,590,143" href="javascript:openWindow('purchase/po_cgddwh', '采购订单维护');"> <!-- 采购订单维护 -->
	          <area shape="rect" coords="5,276,200,313"  href="javascript:openWindow('purchase/po_cglssh', '采购临时收货');" /><!-- 采购临时收货 -->
	          <area shape="rect" coords="398,188,588,220"  href="javascript:openWindow('purchase/po_cgddbg', '采购订单变更');">  <!-- 采购订单变更 -->
	          <area shape="rect" coords="270,278,463,311" href="javascript:openWindow('qcmgt/iqc_in', 'IQC正式入库');">	<!-- IQC正式入库 -->
	          <area shape="rect" coords="295,358,481,389" href="javascript:openWindow('purchase/po_order_sc', '采购订单状态控制 ');">	<!-- 采购订单状态控制 -->
	          <area shape="rect" coords="513,357,706,389" href="javascript:openWindow('purchase/vendor_statements', '供应商对账单');">	<!-- 供应商对账单 -->  	
	          <area shape="rect" coords="532,279,725,310" href="javascript:openWindow('purchase/po_cgth', '采购退货');">  <!-- 采购退货 -->
        </map>
  		</div>
	</div>
  </body>
</html>






