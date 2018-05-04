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
    <link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css" />
    <script src="${base}template/miniui/boot.js" type="text/javascript"></script>
    <!--引入皮肤样式-->
    <link href="${base}/template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    
    <link href="${base}template/system/css/404css.css" rel="stylesheet" type="text/css" />
    <script src="${base}template/common/js/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            width = document.body.offsetWidth;
            height = document.body.offsetHeight;
            
            if(width<600){
                $("#xiaok").show();
                $("#kuang").hide();
            }else{
                $("#xiaok").hide();
                $("#kuang").show();
            }
        });
        
        function onCancel(e) {
             CloseWindow("cancel");
        }
        
        function CloseWindow(action) {
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }
    </script>
</head>

<body>
    <div id="kuang">
        <div id="container">
            <div class="quanxDiv">
                <center><a href="system/backend_login!main.action"></a></center>
            </div>
            
            <div class="quanX">
                <span class="quanXspan" style="line-height:40px;">
                    <span id="tiao">权限错误！</span>
                    <span>您没有权限访问此页面！</br>请联系管理员，配置此页面的权限！</span>
                </span>
            </div>
        </div>
        
        <div id="cloud" class="png" style="position:absolute;bottom:0;width:100%;overflow:hidden;">
            <img src="${base}template/system/images/error_cloud.png" style="position:absolute; bottom:0;">
        </div>
        <pre style="display: none;"></pre>
    </div>
    
    <div id="xiaok" style="display:none;color:#fff;text-align:center;font-weight: bold;line-height:30px;margin-top:1%;">
        <div><img src="${base}template/system/images/tanhao.png"/></div>
        <div>您没有权限访问此页面，请联系管理员，配置此页面的权限！</div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
        <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a>
    </div>
</body>
</html>