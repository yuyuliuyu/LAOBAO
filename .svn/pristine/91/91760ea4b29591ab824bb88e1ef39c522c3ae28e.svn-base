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
<script type="text/javascript">
/* $(document).ready(function() {
    countDown(5);
});
function countDown(secs) {
    tiao.innerText = secs;
    if (--secs > 0) {
        setTimeout("countDown(" + secs + ")", 1000);
    } else {
      window.location.href="/erp"
    }
} */
</script>
</head>
<body>
    <div id="container">
    <div class="quanxDiv"> 
        服务器繁忙,您可尝试重新操作,若还有问题,请联系一下软件维护人员!
   <!-- <img src="${base}template/system/images/500.png" class="png" />
    <div class="quanX">
        <span class="quanXspan">
        <span id="tiao" ><b>5</b> </span>
                                            <span>秒后自动跳转到首页。 如果您的浏览器没有自动跳转，请点击确定
                                            </span>
        </span>-->
    </div> 
    <div class="butnew">
        <input type="button" value=""  class="dlbtnq" title="确定"/>
    </div>
</div>
<div id="cloud" class="png"></div>
<pre style="display: none;">                            
</pre>
</body>
</html>