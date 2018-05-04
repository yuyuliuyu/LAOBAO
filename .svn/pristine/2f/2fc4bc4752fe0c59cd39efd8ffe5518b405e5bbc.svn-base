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
        <img src="${base}template/system/images/daohang/jiben.png"  class="imgh"  border="0" usemap="#Map" />
        <map name="Map"  id="Map">
          	 <area shape="rect" coords="45,25,119,136" href="javascript:openWindow('jcsj/jcsj_buyer','采购人员维护' );"> <!-- 采购员资料 -->
          	<area shape="rect" coords="225,25,301,135" href="javascript:openWindow('jcsj/jcsj_vendor', '供应商资料维护');"> <!-- 供应商资料 -->
         	 <area shape="rect" coords="404,24,482,135" href="javascript:openWindow('purchase/po_supplier_item', '供应商料号维护');"> <!-- 供应商料号 -->
        </map>
  		</div>
	</div>
  </body>
</html>






