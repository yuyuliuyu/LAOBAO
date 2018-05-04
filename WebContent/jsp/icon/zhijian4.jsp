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
    
    <title>FQC检验流程</title>
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
        <img src="${base}template/system/images/daohang/zj-fqc.png"  class="imgh"  border="0" usemap="#Map" />
        <map name="Map"  id="Map">
          	  <area shape="rect" coords="304,19,497,53" href="javascript:openWindow('workmgt/scrbsr', '生产日报输入');"> <!-- 生产日报输入 -->
          <area shape="rect" coords="495,69,691,105" href="javascript:openWindow('qcmgt/material_check', '物料检验参数设置');"> <!-- 产品工序检验参数设置 -->
          <area shape="rect" coords="301,117,498,153" href="javascript:openWindow('qcmgt/sample_check', '样品检验单');"> <!-- 样品检验单 -->
          <area shape="rect" coords="303,210,498,245" href="javascript:openWindow('qcmgt/pro_jyjg', '样品检验结果输入');"> <!-- 样品检验结果输入 -->
          <area shape="rect" coords="303,253,498,287" href="javascript:openWindow('qcmgt/pro_qc', '样品检验单状态控制');"> <!-- 样品检验结果 -->
          <area shape="rect" coords="303,295,498,330" href="javascript:openWindow('workmgt/pqcjyjgsr', 'PQC检验结果输入');"> <!-- PQC最终数量修正 -->
        </map>
  		</div>
	</div>
  </body>
</html>






