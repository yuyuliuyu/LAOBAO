<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
request.setAttribute("base", basePath);
//重定向路径
//response.sendRedirect(basePath+"/system/backend_page!mainshow.action");
response.sendRedirect(basePath+"/jsp/system/loginLayout.jsp");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta  http-equiv="refresh" content="0;URL='/system/backend_page!mainshow.action" />
	
  </head>
  <body>
  	欢迎使用本系统
  </body>
</html>
