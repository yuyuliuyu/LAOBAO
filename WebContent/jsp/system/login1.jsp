<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
String Error_Message = (String)request.getSession().getAttribute("Error_Message");
request.getSession().removeAttribute("Error_Message");
String type=(String)session.getAttribute("type");
%>
<%
    response.setHeader("progma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>系统登录</title>
    <base href="<%=basePath%>" />
    
    <title>管理员登录</title>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
	<link rel="shortcut icon" href="${base}template/system/images/favicon.ico" type="image/x-icon" />
    <style type="text/css">
    body
    {
        width:100%;
        height:100%;
        margin:0;
        overflow:hidden;
    }
    </style>
     <script src="${base}template/miniui/bootxx.js" type="text/javascript"></script>
      
     <script src="${base}template/common/js/jquery-1.9.1.min.js" type="text/javascript"></script>
     <script src="${base}template/common/js/jquery.nav.js" type="text/javascript"></script>
     
	<link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css" />
	<link href="${base}template/system/css/main.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${base}template/system/css/nav.css" />

    <!--[if IE 6]>
    <script type="text/javascript" src="${base}template/system/js/DD_belatedPNG.js"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('.logtop, .logmid, .logbtm, .yunbg, .hello');
    </script> 
    <![endif]--> 
</head>

<body id="ha">   
    
    <div id="loginWindow" class="centestyl " showModal="true" showCloseButton="false">
      <div class="wrap">
        <!-- 顶部logo部分 -->
        <div class="header">
            <div class="logo">
            <img class="hello" src="${base}/template/system/images/head.png" alt="" />

            </div>
        </div>
        <!-- 顶部logo部分End -->
    
        <!-- 中心显示区域 -->
        <div class="main">
            <div class="mainleft">
                
                 <div id="inner">
                    <div class="hot-event">
                       
                        <div class="event-item" style="display: block;">
                            <a  href="javascript:;" class="banner">
                                <img hidefocus="true" border="0" src="${base}/template/system/images/show.png" class="photo"/>
                            </a>
                        </div>
                        <div class="event-item" style="display: none;">
                            <a  href="javascript:;" class="banner">
                                <img hidefocus="true" border="0" src="${base}/template/system/images/show02.png" class="photo" />
                            </a>
                        </div>
                        <div class="event-item" style="display: none;">
                            <a  href="javascript:;" class="banner">
                                <img hidefocus="true" border="0" src="${base}/template/system/images/show03.png" class="photo" />
                            </a>
                        </div>
                     
                     
                    </div>
                </div>
            </div>
            
            <!-- 登录框区域 -->
            <form id="form" name="form" action="login_check?token=backend" method="post">
            <div class="mainlogin">
                <div class="logtop">
                    
                </div>

                <div class="logmid">
                    <div class="logtit">
                        请输入账号密码
                    </div>
					<div class="user">
                        <span class="userspan">公司名:</span>
                        <span >
                            <input class="mini-textbox" id="company" value="" name="company" errorMode="none" onvalidation="onUserNameValidation" vtype="minLength:3" required="true" requiredErrorText="不能为空" minLengthErrorText="不能少于3字符"/>
                 
                        </span>
                        <div id="company_error" class="errorText"></div>
                    </div>
                    
                    <div class="user">
                        <span class="userspan">用户名:</span>
                        <span >
                            <input class="mini-textbox" id="username" value="" name="username" errorMode="none" onvalidation="onUserNameValidation" vtype="minLength:2" required="true" requiredErrorText="不能为空" minLengthErrorText="不能少于3字符"/>
                        </span>
                        <div id="username_error" class="errorText"></div>
                    </div>
                     
                    <div class="paw">
                        <span class="pawspan">密&nbsp;&nbsp;码:</span>
                        <span >
                            <input class="mini-password" type="password"  name="password" value="" id="password"  errorMode="none" onvalidation="onPwdValidation" required="true" vtype="minLength:3" minLengthErrorText="不能少于3字符" />
                        </span>
                        <div id="password_error" class="errorText"></div>
                    </div>
					<%
		                if(type!=null){
			                if(type.equals("T")){ %>
			                	
		
		                    <div class="check">
		                        <span>
		                            <input type="text"  style="padding-left:10px; width:80px;" size="16" value="验证码" onfocus="if (value =='验证码'){value =''}" onblur="if (value ==''){value='验证码'}" id="captcha" name="captcha" />
		                        </span>
		                        <span class="checkcode">
		                            <%-- <img height="32" width="88" src="${base}/template/system/images/check.png" alt="" /> --%>
		                            <img height="32" width="88" src="captcha.jpeg" id="captchaImage" alt="换一张" />
		                        </span>
		                        <span class="checktip"><a href="javascript:;" id="miss" >看不清换一个</a></span>
		                    </div>
			                <%}
		                }
	                %>

                    <div class="logbut">
                        <span> <input class="log_butt" type="button" value="登录" onclick="onLoginClick()" /> </span>
                        <span> <input class="log_butt"  type="button" onclick="onResetClick()" value="重置" /> </span>
                        <span > <input class="log_butt"  type="button" onclick="register()" value="申请试用" /> </span>
                        <%--<span class="reme"> 
                            <input class="jizhu" id="rememberMe" name="rememberMe" type="checkbox" />
                            <label class="jz" for="jzma">记住密码</label>
                        </span>
                    --%></div>
                </div>

                <div class="logbtm">
                    
                </div>

            </div>
            </form>
            <!-- 登录框结束 -->
            <div class="clear"></div>
            

        </div>  
        <!-- 中心显示区域End -->

        <div class="clear"></div>

        <div class="yunbg">
            <div class="switch-tab">
                <a href="#" onclick="return false;" class="current"></a>
                <a href="#" onclick="return false;"></a>
                <a href="#" onclick="return false;"></a>
            </div>
                <!-- <ul class="focus-content clearfix">
                    <li class="focus-content-item"><a class="focus-anchor" hidefocus="true" href="javascript:;" idx="0"></a></li>
                    <li class="focus-content-item"><a class="focus-anchor" hidefocus="true" href="javascript:;" idx="1"></a></li>
                    <li class="focus-content-item"><a class="focus-anchor current" hidefocus="true" href="javascript:;" idx="2"></a></li>
                </ul> -->
            </div>
    </div>
      
</div>
    

    
    <script type="text/javascript">
    $(function() {

        $(document).keydown(function(e){
            if(e.keyCode==13){
                onLoginClick();
            }
        });
        
        $("#clearall").click(function(){
            $("input:lt(3)").val("");
        });
    
        var $captchaImage = $("#captchaImage");
    
        // 刷新验证码
        $("#miss").click(function(){
            $captchaImage.click();
        });
        
        $captchaImage.click(function() {
        var $captchaImage = $("#captchaImage");
        
            var timestamp = (new Date()).valueOf();
            var imageSrc = $captchaImage.attr("src");
            
            if (imageSrc.indexOf("?") >= 0) {
                imageSrc = imageSrc.substring(0, imageSrc.indexOf("?"));
            }
            imageSrc = imageSrc + "?timestamp=" + timestamp;
            $captchaImage.attr("src", imageSrc);
        }); 
        
        <%if(Error_Message != null) { %>
          alert("<%=Error_Message%>");
          <%-- $.dialog({type: "error", content:"<%=Error_Message%>", modal: true, autoCloseTime: 5000});   --%>
        <% }%> 
    });
    
        mini.parse();

        /* var loginWindow = mini.get("loginWindow"); */
        var loginWindow = $("#loginWindow");
        loginWindow.show();

        function onLoginClick() {
            var form = new mini.Form("#loginWindow");

            form.validate();
            if (form.isValid() == false) return;

            loginWindow.hide();
            mini.loading("登录验证中，请稍后...", "登录验证");
 
            setTimeout(function () {
               $("[name='form']").submit();
            }, 1000);
        }
        function onResetClick() {
            
            var form = new mini.Form("#loginWindow");
            form.clear();
        }
        
        function isEmail(s) {
            if (s.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
                return true;
            else
                return false;
        }
        function updateError(e) {
            var id = e.sender.name + "_error";
            var el = document.getElementById(id);
            if (el) {
                el.innerHTML = e.errorText;
            };
        }
        function onUserNameValidation(e) {                  
            updateError(e);
        }
        function onPwdValidation(e) {        
            updateError(e);
        }
        
        //强制跳转到新窗口
    if (self.location != top.location) {
        top.location.href = location.href;
    }
    function register(){
       //window.location.href="http://www.china-saas.com/plus/diy.php?action=post&diyid=3";
    	 mini.open({
             url : "http://www.china-saas.com/plus/diy.php?action=post&diyid=3",
             showMaxButton : false,
             title : "申请试用",
             width : 800,
             height : 500,
             ondestroy : function(action) {
                 if (action == "ok") {
                 }else{
                 }
             }
         });
    };
    
    
    </script>
    <script src="${base}template/system/js/jquery.nav.js" type="text/javascript"></script>
    <script type="text/javascript">
        $('#ha').nav({ t: 2000, a: 1000 });
    </script>
    
</body>
</html>