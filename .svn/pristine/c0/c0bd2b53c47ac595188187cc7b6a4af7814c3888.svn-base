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
    <link href="${base}template/system/css/demo.css" rel="stylesheet"
	type="text/css" />
	
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
   <div class="assbk">
				<div class="asskj">
					<img src="${base}template/system/images/daohang/shengchant.png"
						border="0" usemap="#Map" />
					<map name="Map" id="Map">
						<area shape="rect" coords="13,12,216,49" href="javascript:openWindow('bominfo/item!list','物料数据维护');" /><!-- href="物料的MRP参数设置" -->
						<area shape="rect" coords="275,13,477,49"
							href="javascript:openWindow('wipmgt/mrp_cysrl','MRP粗算日历单');" />
						<area shape="rect" coords="143,102,345,137"
							href="javascript:openWindow('wipmgt/mrp_ys','MRP运算');" />
						<area shape="rect" coords="353,159,555,194"  /><!-- href="MRP运算进度查询" -->
						<area shape="rect" coords="142,221,345,259"  /><!-- href="MRP运算过程数据查询" -->
						<area shape="rect" coords="142,267,345,305"  /><!--href="MPR供需关联查询"  -->
					</map>
				</div>
			</div>
  </body>
</html>
