<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>提示信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="${base}template/system/css/base.css" rel="stylesheet"  type="text/css" />
<link href="${base}template/system/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}template/common/js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="${base}template/system/js/admin.js"></script>
<script type="text/javascript">
	$(function() {
		var actionUrl = "${redirectUrl}";
		var actionMessage = "${actionMessage}";
		function redirectUrl() {
			if (actionUrl != null && actionUrl != "") {
				window.location.href = actionUrl;
			} else {
				window.history.back();
			}
		}
		if (actionMessage == null || actionMessage == "") {
			actionMessage = "您的操作已成功!";
		}
		$.dialog({
			type : "success",
			title : "操作提示",
			content : actionMessage,
			ok : "确定",
			okCallback : redirectUrl,
			cancelCallback : redirectUrl,
			width : 380,
			modal : true
		});

	});
</script>
</head>
<body class="success">
</body>
</html>
