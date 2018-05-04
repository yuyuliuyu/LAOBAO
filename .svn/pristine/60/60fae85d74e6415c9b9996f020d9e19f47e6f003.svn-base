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

<title>My JSP 'icon_caigou.jsp' starting page</title>
<link href="${base}template/system/css/demo.css" rel="stylesheet"
	type="text/css" />
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>

<link href="${base}template/system/css/base.css" rel="stylesheet"
	type="text/css" />



<script type="text/javascript">
	function openWindow(urlname, name) {

		var tabs = window.parent.mini.get("mainTabs");

		var id = "tab$" + urlname;
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
	<div id="tabs1" class="mini-tabs" activeIndex="0"
		style="width:100%;height:100%;" plain="false">
		<div title="生产流程" url="${base}jsp/icon/shengchan.jsp"></div>
		<%-- <div title="MRP流程" url="${base}jsp/icon/shengchan2.jsp"></div> --%>
	</div>
</body>
</html>
