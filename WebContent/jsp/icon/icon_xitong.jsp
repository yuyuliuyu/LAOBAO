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
			tab.url =  urlname + "!list.action";

			tabs.addTab(tab);
		}
		tabs.activeTab(tab);
	}
</script>
  </head>
  
  <body>
     <div class="assbk" >
    <div class="asskj">
        <img src="${base}template/system/images/daohang/sysEdit.png"  class="imgh"  border="0" usemap="#Map" />
        <map name="Map"  id="Map">
          	   <area shape="rect" coords="34,9,131,122" href="javascript:openWindow('../resource/backend_res','资源管理');">		<!-- 权限设置 -->
	          <area shape="rect" coords="213,10,310,123" href="javascript:openWindow('backend_role','角色管理');">		<!-- 资源管理 -->
	          <area shape="rect" coords="384,11,483,121" href="javascript:openWindow('admin','用户管理');">		<!-- 角色管理 -->
	          <area shape="rect" coords="558,10,651,123" href="javascript:openWindow('../jcsj/log','系统日志');">		<!-- 权限设置 -->
	          <area shape="rect" coords="722,12,831,120"  href="javascript:openWindow('../depart/backend_dep','部门管理');">		<!-- 系统日志 -->
	          <area shape="rect" coords="36,195,128,309" href="javascript:openWindow('../jcsj/sysparam_manage','系统公共参数设置');">		<!-- 部门管理 -->
	          <area shape="rect" coords="208,192,312,307" href="javascript:openWindow('../dictionary/backend_dictionary','参数配置');">		<!-- 系统参数设置 --><%--
	          <area shape="rect" coords="387,197,480,310" href="javascript:openWindow('../dictionary/backend_dictionary','参数配置');">		--%><!-- 参数配置 -->
        </map>
  		</div>
	</div>
  </body>
</html>






