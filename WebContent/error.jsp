<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("base", basePath);
Exception springSecurityLastException = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'error.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
    <%-- <%=springSecurityLastException %> --%>
    登录人数已达上限
    <a href="${base}jsp/system/login.jsp">返回</a>
    
<!--    <form action="error" method="get">
    <input type="text"  name="error">
    <input type="submit">
    </form> -->
</body>
</html>
