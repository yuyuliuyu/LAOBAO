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
    
    <title>PQC检验流程</title>
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
        <img src="${base}template/system/images/daohang/zj-pqc.png"  class="imgh"  border="0" usemap="#Map" />
        <map name="Map"  id="Map">
          	 	  <area shape="rect" coords="125,6,319,42" href="javascript:openWindow('purchase/po_cglssh', '采购临时收货');">	<!-- 采购临时收货 -->
		          <area shape="rect" coords="319,58,511,94" href="javascript:openWindow('qcmgt/material_check', '物料检验参数设置');">	<!-- 物料检验参数设置 -->
		          <area shape="rect" coords="125,109,320,145" href="javascript:openWindow('qcmgt/sample_check', '样品检验单');">	<!-- 样品检验单 -->
		          <area shape="rect" coords="12,198,207,234" href="javascript:openWindow('qcmgt/pro_lld', '质检样品领料');">	<!-- 质检样品领料 -->
		          <area shape="rect" coords="239,199,433,234" href="javascript:openWindow('qcmgt/pro_tld', '质检样品退料');">	<!-- 质检样品领料2 -->
		          <area shape="rect" coords="125,296,318,330" href="javascript:openWindow('qcmgt/pro_jyjg', '样品检验结果输入');">	<!-- 样品检验结果输入 -->
		          <area shape="rect" coords="125,337,318,373" href="javascript:openWindow('qcmgt/pro_qc', '样品检验单状态控制');">	<!-- 样品检验单结单 -->
		          <area shape="rect" coords="125,381,318,413" href="javascript:openWindow('qcmgt/iqc_in', 'IQC正式入库');">	<!-- IQC正式入库 -->
		  </map>
       
  		</div>
	</div>
  </body>
</html>






