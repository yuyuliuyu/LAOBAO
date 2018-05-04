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
			if(id=="tab$add生产日报查询"){
				id ="生产日报查询";
			}else{
				id =name;
			}
			tab.title = id;
			tab.showCloseButton = true;
			tab.url = "${base}" + urlname + "!list.action";

			tabs.addTab(tab);
		}
		tabs.activeTab(tab);
	}
</script>
  </head>
  
  <body>
     <div class="assbk" >
    <div class="asskj">
        <img src="${base}template/system/images/daohang/cj-ywlc.png"  class="imgh"  border="0" usemap="#Map" />
        <map name="Map"  id="Map">
          	  <area shape="rect" coords="3,14,200,45" href="javascript:openWindow('workmgt/gzzxrili', '工作中心日历编排');">		<!-- 工作中心日历编排 -->
          <area shape="rect" coords="229,15,420,45" href="javascript:openWindow('workmgt/gzzxcnsjsr', '工作中心产能数据输入');">	<!-- 工作中心产能数据输入 -->
          <area shape="rect" coords="455,15,645,45" href="javascript:openWindow('workmgt/scdgywh', '生产单的工艺维护');">	<!-- 生产单的工艺维护 -->
          <area shape="rect" coords="6,53,195,83" href="javascript:openWindow('workmgt/gzzxrilicx', '工作中心日历查询');">		<!-- 工作中心日历查询 -->
          <area shape="rect" coords="232,54,417,83" href="javascript:openWindow('workmgt/gzzxcnsjcx', '工作中心产能数据查询');">	<!-- 工作中心产能数据查询 -->
          <area shape="rect" coords="455,52,642,84" href="javascript:openWindow('workmgt/scdgybg', '生产单的工艺变更');">	<!-- 生产单的工艺变更 -->
          <!--<area shape="rect" coords="230,166,420,198" href="javascript:openWindow('workmgt/gzzxpc', '工作中心排产');">	 工作中心排产 -->
          <area shape="rect" coords="121,206,309,237" >	<!-- 工作中心排产查询 -->
          <area shape="rect" coords="346,205,532,236" >	<!-- 工作中心负荷查询 -->
          <area shape="rect" coords="470,243,656,274" href="javascript:openWindow('workmgt/gzzxlld', '工作中心领料单');">	<!-- 工作中心领料单 -->
          <area shape="rect" coords="237,281,425,312" href="javascript:openWindow('workmgt/scrbsr', '生产日报输入');">	<!-- 生产日报输入 -->
          <area shape="rect" coords="239,319,426,350" href="javascript:openWindow('workmgt/pqcjyjgsr', 'PQC检验结果输入');">	<!-- PQC检验结果输入 -->
          <area shape="rect" coords="236,356,428,386" href="javascript:openWindow('workmgt/scrbsr', 'add生产日报查询');">	<!-- 生产日报查询 -->
        </map>
  		</div>
	</div>
  </body>
</html>






