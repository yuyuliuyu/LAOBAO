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
    
    <title>My JSP 'shengchan.jsp' starting page</title>
    
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
  <div class="assbk" style="width:100%; height:100%; overflow :auto;">
				<div class="asskj">
					<img src="${base}template/system/images/daohang/shengchan.png"
						border="0" usemap="#Map" />
					<map name="Map" id="Map">
						<area shape="rect" coords="179,6,348,39"
							href="javascript:openWindow('wipmgt/wip','生产单维护');" />
						<area shape="rect" coords="347,47,515,80"
							href="javascript:openWindow('wipmgt/wip_change','生产单变更');" />
						<area shape="rect" coords="349,164,513,195"
							href="javascript:openWindow('wipmgt/wip_pro_back','产品返退回生产仓');" />
						<area shape="rect" coords="16,128,182,158"
							href="javascript:openWindow('wipmgt/wip_gret','生产单退好料');" />
						<area shape="rect" coords="17,95,181,125"
							href="javascript:openWindow('wipmgt/wip_iss','生产单发料');" />
						<area shape="rect" coords="350,95,513,126"
							href="javascript:openWindow('wipmgt/wip_loss','生产单损耗物料');" />
						<area shape="rect" coords="348,129,514,161"
							href="javascript:openWindow('wipmgt/wip_qty_change','原料的在线数量调整');" />
						<area shape="rect" coords="16,164,182,193"
							href="javascript:openWindow('wipmgt/wip_bret','生产单退坏料');" />
						<area shape="circle" coords="263,141,32"
						href="javascript:openWindow('locmgt/loc_stk_loc','库存现状或历史查询');" />
						<area shape="rect" coords="182,234,344,262"
							href="javascript:openWindow('wipmgt/wip_prd','产品入库');" />
						<area shape="rect" coords="181,302,347,332"
							href="javascript:openWindow('qcmgt/fqc_in','FQC正式入库');" />
						<area shape="rect" coords="183,357,344,383" href="javascript:openWindow('wipmgt/wip_status_control','生产单状态控制');">
					</map>
				</div>
			</div>
  </body>
</html>
