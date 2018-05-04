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
    
    <title>IQC检验流程</title>
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
        <img src="${base}template/system/images/daohang/zj-iqc.png"  class="imgh"  border="0" usemap="#Map" />
        <map name="Map"  id="Map">
          	 <area shape="rect" coords="126,12,314,45" > <!-- 产品临时存仓 -->
          <area shape="rect" coords="320,56,507,88" href="javascript:openWindow('qcmgt/material_check', '物料检验参数设置');"> <!-- 产品检验参数设置 -->
          <area shape="rect" coords="126,99,312,130" href="javascript:openWindow('qcmgt/sample_check', '样品检验单');"> <!-- 样品检验单 -->
          <area shape="rect" coords="10,178,199,213" href="javascript:openWindow('qcmgt/pro_lld', '质检样品领料');"> <!-- 质检样品领料 -->
          <area shape="rect" coords="239,180,426,213" href="javascript:openWindow('qcmgt/pro_tld', '质检样品退料');"> <!-- 质检样品领料2 -->
          <area shape="rect" coords="125,260,310,291" href="javascript:openWindow('qcmgt/pro_jyjg', '样品检验结果输入');"> <!-- 样品检验结果输入 -->
          <area shape="rect" coords="125,302,312,335" href="javascript:openWindow('qcmgt/pro_qc', '样品检验单状态控制');"> <!-- 样品检验单结单 -->
          <area shape="rect" coords="126,346,312,379" href="javascript:openWindow('qcmgt/fqc_in', 'FQC正式入库');"> <!-- FQC正式入库 -->
        </map>
  		</div>
	</div>
  </body>
</html>






