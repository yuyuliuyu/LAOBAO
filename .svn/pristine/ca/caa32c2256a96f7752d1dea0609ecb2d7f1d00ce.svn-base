<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>基础</title>
<link href="${base}template/system/css/demo.css" rel="stylesheet" type="text/css" />


	<script type="text/javascript">
	
		function openWindow(urlname, name) {
	
			var tabs = window.parent.mini.get("mainTabs");
	
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
  
<body style="background:white;">
<div class="assbk" style="width:100%; height:100%; overflow :auto;">
    <div class="asskj">
        <img src="${base }template/system/images/daohang/jichu.png" border="0" usemap="#Map" />
        <map name="Map" id="Map">
          	  <area shape="rect" coords="17,9,110,122"     href="javascript:openWindow('jcsj/sysparam_manage','系统公共参数');"> <!-- 系统公共参数 -->
	          <area shape="rect" coords="199,17,292,122"  href="javascript:openWindow('jcsj/jcsj_lthb','流通货币定义');"> <!-- 流通货币定义 -->
	          <area shape="rect" coords="377,16,467,123"  href="javascript:openWindow('jcsj/dw','单位设置 ');"> <!-- 单位设置 -->
	          <area shape="rect" coords="544,13,635,122"  href="javascript:openWindow('jcsj/jcsj_fkfs','付款方式设置');"> <!-- 付款方式设置 -->
	          <area shape="rect" coords="724,13,797,123"  href="javascript:openWindow('jcsj/jcsj_taxtype','税别设置');"> <!--税别设置 -->
	          <area shape="rect" coords="20,201,112,311"  href="javascript:openWindow('jcsj/country_set','国家地区资料');"> <!-- 国家地区资料 -->
	          <area shape="rect" coords="203,200,292,310" href="javascript:openWindow('jcsj/dd','地点设置');"> <!-- 地点设置 -->
	          <area shape="rect" coords="373,195,465,313" href="javascript:openWindow('jcsj/ck','仓库设置');"> <!-- 仓库设置 -->
	         <!--  <area shape="rect" coords="541,197,639,312" > 50个备用字段
	          <area shape="rect" coords="697,193,828,311" href="javascript:openWindow('jcsj/goodcat_manage','物料种类及编码设置 ');"> 划分物料种类及编码 -->
	     <area shape="rect" coords="541,197,639,312" href="javascript:openWindow('jcsj/goodcat_manage','物料种类及编码设置 ');"> <!-- 划分物料种类及编码 -->
	     
	     </map>
    </div>
</div>
  </body>
</html>
