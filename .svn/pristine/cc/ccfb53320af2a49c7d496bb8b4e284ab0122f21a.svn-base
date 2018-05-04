<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@tag import="org.springframework.context.ApplicationContext"%>
<%@tag import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@tag import="com.lingnet.qxgl.security.perm.PermissionChecker"%>
<%@attribute name="value" type="java.lang.String" required="true"%>
<%
  String value = (String) jspContext.getAttribute("value");
  ServletContext servletContext = request.getSession().getServletContext();
  ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
  PermissionChecker permissionChecker = (PermissionChecker)ctx.getBean("PermissionChecker"); 
  boolean authorized = permissionChecker.isAuthorized(value,request.getSession());
  if (authorized) {
%>
<jsp:doBody/>
<%
  }
%>
