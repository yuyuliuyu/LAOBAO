<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>资产</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	<link href="${base}template/system/css/demo.css" rel="stylesheet"
	type="text/css" />

<script type="text/javascript">
function openWindow(urlname,name) {
 
 var tabs = window.parent.mini.get("mainTabs");

 var id = "tab$"+name;
        var tab = tabs.getTab(id);
        if (!tab) {
            tab = {};
            tab.name = id;
            tab.title = name;
            tab.showCloseButton = true;
            tab.url = "${base }"+urlname+"!list.action";

            tabs.addTab(tab);
        }
        tabs.activeTab(tab);
    } 
</script>
</head>

<body style="background:white;">
	<div class="assbk" >
		<div class="asskj">
		<img src="${base}template/system/images/daohang/zichan.png" border="0" align="center" usemap="#Map" />
			 <map name="Map">
              <area shape="rect" coords="36,58,238,95" href="javascript:openWindow('asset/asset_class','资产种类及编码设置');">
              <area shape="rect" coords="319,58,521,95" href="javascript:openWindow('asset/asset_mold_group','模具类资产的组别');">
              <area shape="rect" coords="196,157,396,197" href="javascript:openWindow('asset/asset','资产维护');">
              <area shape="rect" coords="464,241,668,277" href="javascript:openWindow('asset/asset','资产维护');">
              <area shape="rect" coords="198,316,398,350" href="javascript:openWindow('asset/asset_depre','资产折旧');">
              <area shape="rect" coords="199,364,397,396" href="javascript:openWindow('asset/asset_adj','资产调整');">
              <area shape="rect" coords="198,446,396,483" href="javascript:openWindow('asset/asset_his','add资产净值变化流水账查询');">
            </map>
		</div>
	</div>
</body>
</html>
