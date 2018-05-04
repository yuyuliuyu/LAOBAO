<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base",basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="${base}template/system/css/demo.css" rel="stylesheet">
	
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
	
	<style>
		html
		{
			border:solid 1px #999;
			border-top:0;
		}
	</style>
  </head>
  
  <body>
   <div class="assbk"  >
				<div class="asskj">
					<img src="${base}template/system/images/daohang/cangku.png"
						border="0" usemap="#Map" />
					<map name="Map" id="Map">
						<area shape="rect" coords="14,15,217,52"
							href="javascript:openWindow('locmgt/loc_move','转仓单');" />
						<area shape="rect" coords="14,144,218,185"
							href="javascript:openWindow('locmgt/loc_adj','库存数量调整');" />
						<area shape="rect" coords="12,77,217,117"
							href="javascript:openWindow('locmgt/loc_item_fix','物料组装拆卸');" />
						<area shape="circle" coords="310,105,41" href="javascript:openWindow('locmgt/loc_stk_loc','库存现状或历史查询');" />
						<area shape="rect" coords="394,47,597,83"
							href="javascript:openWindow('locmgt/loc_stk_loc','库存现状或历史查询');" />
						<area shape="rect" coords="213,246,416,282" /><!-- href="其它进销存业务单据"  -->
						<area shape="rect" coords="392,113,598,149"
							href="javascript:openWindow('locmgt/loc_out_in','库存变化流水账查询');" />
					</map>
				</div>
			</div>
  </body>
</html>
