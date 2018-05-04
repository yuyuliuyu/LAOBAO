<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
String requestURI=request.getRequestURI();

%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <link href="${base}template/system/css/404css.css" rel="stylesheet" type="text/css" />
 <script src="${base}template/common/js/jquery-1.6.2.min.js" 

type="text/javascript"></script>
<script type="text/javascript">
//alert(top.location.href+"===="+self.location.href)
//if(top.location.href!=self.location.href){ location.href="${base}sessionTimeOut.jsp"; } 
$(document).ready(function() {
    countDown(5);
    $("#bt a").attr("href",$("#container a").attr("href"));
});
function countDown(secs) {
    tiao.innerText = secs;
    if (--secs > 0) {
        setTimeout("countDown(" + secs + ")", 1000);
    } else {
   // alert($("#container a").attr("href"));
     window.location.href=$("#container a").attr("href");
    }
} 
</script>
</head>
<body>
    <div id="container">
    <div class="quanxDiv"> 
    
	
	 <center><a href="system/backend_login!main.action"></a></center>

    </div>
    <div class="quanX">
        <span class="quanXspan">
        <span id="tiao" >会话超时!<b>5</b> </span>
           <span>秒后自动跳转到登录页。 如果您的浏览器没有自动跳转，请点击返回</span>
        </span>
    </div>
    <div id="bt" class="butnew">
         <a href=""><img src="${base}template/system/images/button.png" class="png"></a>
        <!-- <input type="button" value=""  class="dlbtnq" title="确定"/> -->
    </div>
    
    
    
</div>
<div id="cloud" class="png" style="position:absolute;bottom:0;width:100%;overflow:hidden;">
	<img src="${base}template/system/images/error_cloud.png" style="position:absolute; bottom:0;">
</div>
<pre style="display: none;">                            
</pre>
</body>
</html>