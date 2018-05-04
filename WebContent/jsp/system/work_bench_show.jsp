<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
HttpSession sessions=request.getSession();
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>管理中心</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="${base}template/miniui/boot.js" type="text/javascript"></script>
    <link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css" />
    <script src="${base}template/common/js/toastr.min.js" type="text/javascript"></script>
    <link href="${base}template/common/css/toastr.min.css" rel="stylesheet" type="text/css" /> 
    <link href="${base}template/common/css/demo.css" rel="stylesheet" type="text/css" /> 
     <link rel="shortcut icon" href="${base}template/system/images/favicon.ico" type="image/x-icon" />
	<!--引入皮肤样式--> 
    <link href="${base}template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    
  
    <style>
        
    .container
    {
        padding:30px;
    }
    
    .container .mini-panel
    {
        margin-right:20px;
        margin-bottom:20px;
    }
    
    </style>    
</head>
<body> 
<div class="container">
 <s:iterator var="benchlist" value="benchlist" status="index"> 
	 <s:if test="(#index.count-1)>0&&((#index.count-1)%2)==0">
	     </div>
	 </s:if>
	 <s:if test="(#index.count-1)==0||((#index.count-1)%2)==0"> 
	        <div class="mini-clearfix " style="clear:both;"> 
	 </s:if>
	 <div class="mini-col-6"  style="float:left;width: 45%"> 
	      <div class="mini-panel" title="<s:property value='title'/>" width="auto"  id=" <s:property value='id'/> "
	           showCollapseButton="true" showCloseButton="false" >
	        <iframe src="<s:property value='pageURL'/>" style="width: 100%"></iframe> 
	      </div>
	 </div>
 </s:iterator> 
 </div>  	 
</body>
</html>
