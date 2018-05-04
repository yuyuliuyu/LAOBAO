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

<title>采购</title>
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>

<link href="${base}template/system/css/base.css" rel="stylesheet"
	type="text/css" />



<link href="${base}template/system/css/demo.css" rel="stylesheet"
	type="text/css" />


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
<body>
	<div id="tabs1" class="mini-tabs" activeIndex="0"  style="width:100%;height:100%;  " plain="false">
		<div title="基本设置" url="${base}jsp/icon/caigou.jsp">
			
		</div>
		<div title="业务流程" url="${base}jsp/icon/caigou2.jsp">
			
		</div>
</body>
</html>
