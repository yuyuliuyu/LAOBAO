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
    
    <title>My JSP 'cangchu2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="${base}template/system/css/demo.css" rel="stylesheet"
	type="text/css" />
	
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
   <div class="assbk" style="width:100%; height:100%; overflow :auto;">
				<div class="asskj">
					<img src="${base}template/system/images/daohang/cangkut.png"
						border="0" usemap="#Map" />
					<map name="Map" id="Map">
						<area shape="rect" coords="132,10,334,49"
							href="javascript:openWindow('locmgt/loc_pr_reserved','预留或释放采购申请');" />
						<area shape="rect" coords="399,86,600,121"
							href="javascript:openWindow('locmgt/loc_wo_reserved','预留和释放工单产出');" />
						<area shape="rect" coords="130,87,331,122"
							href="javascript:openWindow('locmgt/loc_po_reserved','预留或释放采购订单');" />
						<area shape="rect" coords="274,216,475,251"
							href="javascript:openWindow('locmgt/loc_stk_reserved','预留或释放库存物料');" />
						<area shape="circle" coords="52,240,42" href="javascript:openWindow('locmgt/loc_recent_rsv','预留现状综合查询');" />
					</map>
				</div>
			</div>
  </body>
</html>
