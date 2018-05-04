<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			pageContext.setAttribute("base", basePath);
			String Error_Message = (String) request.getSession().getAttribute(
					"Error_Message");
			request.getSession().removeAttribute("Error_Message");
			String type = (String) session.getAttribute("type");
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
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link rel="shortcut icon" href="${base}template/system/images/favicon.ico" type="image/x-icon" />
<link type="text/css" rel="stylesheet" href="${base}template/common/css/index.css" />
<script src="${base}template/common/js/jquery-1.9.1.min.js"></script>
<script src="${base}template/common/js/jquery.nav.js" type="text/javascript"></script>
<script src="${base}template/common/js/jquery.backstretch.min.js"></script>
<script src="${base}template/common/js/layer/layer.js"></script>
<script type="text/javascript" src="${base}template/common/js/Validform_v5.3.2_min.js"></script>
<script src="${base}template/miniui/js/miniui.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="${base}template/miniui/themes/logincs.css" />
<!--[if IE 6]>
    <script type="text/javascript" src="${base}template/system/js/DD_belatedPNG.js"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('.logtop, .logmid, .logbtm, .yunbg, .hello');
    </script> 
    <![endif]-->
</head>


<div class="topbox">
	<div class="top">
		<img class="logo" src="${base}template/common/images/logo.png">
		<div class="top-right">
			<a href="http://p.qiao.baidu.com//im/index?siteid=7595358&ucid=6674776" target="_blank">联系客服 </a>
			&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="http://www.china-saas.com/a/ERPqijianban/" target="_blank">关于ERP公司简介 </a>
			&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="http://www.china-saas.com">返回平台首页 </a>
			&nbsp;&nbsp;|&nbsp;&nbsp;
			<a title="加入收藏夹" href="javascript:;" onclick="javascript:extAddFavorite('一凌网进销存系统', location.href);">加入收藏</a>
			<%--<a title="设为首页" onclick="SetHome(window.location)" href="javascript:void(0)">设为首页</a>
--%>
		</div>
	</div>
</div>
<div class="contentbox">
	<div class="content">
		<div class="con-left">
			<div class="lhtp" id="inner">
				<div class="event-item pic01" style="display: block;">
					<a href="javascript:;" class="banner">
						<img hidefocus="true" src="${base}template/common/images/lhtp1.png" class="photo" />
					</a>
				</div>
				<div class="event-item pic02" style="display: none;">
					<a href="javascript:;" class="banner">
						<img hidefocus="true" src="${base}template/common/images/lhtp2.png" class="photo" />
					</a>
				</div>
				<div class="event-item pic03" style="display: none;">
					<a href="javascript:;" class="banner">
						<img hidefocus="true" src="${base}template/common/images/lhtp3.png" class="photo" />
					</a>
				</div>

			</div>
			<SCRIPT type="text/javascript">
        $('#inner').nav({ t: 5000, a: 3000 });
    </SCRIPT>
		</div>
		<div class="con-right">
			<form id="loginWindow" class="registerform" action="login_check?token=backend" method="post">
				<table width="200" border="0">
					<tr>
						<td colspan="2"><input name="company" type="text" value="" class="gsm" datatype="s2-18" nullmsg="请输入企业名！" errormsg="企业名2到16个字符！" placeholder="请输入企业名" style="line-height:50px;" />
						</td>
						<td>
							<div class="info">
								<span class="Validform_checktip">请输入企业名！</span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" value="" name="username" class="yhm" datatype="s2-18" nullmsg="请输入用户名！" errormsg="用户名为2到16个字符！" required="" placeholder="请输入用户名" /></td>
						<td width="110">
							<div class="info">
								<span class="Validform_checktip">请输入用户名！</span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="mmk"><input value="" class="mima" type="password" name="password" plugin="passwordStrength" datatype="*2-18" nullmsg="请输入密码!" errormsg="密码用户名为3到16个字符！" required="" placeholder="请输入密码" /></td>
						<td>
							<div class="info">
								<span class="Validform_checktip">请输入密码!</span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div></td>
					</tr>
					<%
					    if (type != null) {
									if (type.equals("T")) {
					%>
					<tr>
						<td colspan="2" class="yzmk"><input type="text" class="yanzhm" placeholder="验证码" value="" id="captcha" name="captcha" /> <img class="yzmtp" src="captcha.jpeg" id="captchaImage" alt="换一张" /> <a href="javascript:;" id="miss" class="yzmkbq">看不清换一个</a></td>
					</tr>
					<%
					    }
								}
					%>
					<tr>
						<td colspan="2">
							<%--<div class="jzmm">
      <input type="checkbox" style="vertical-align: middle;" name="" value=""/>
      两周内免登陆
      </div>    
       --%>
							<div class="sqsy">
								<a href="javascript:;" onclick="register()">申请试用</a>
							</div></td>
					</tr>
					<tr>
						<td colspan="2"><div class="dlcz">
								<input type="submit" value="提 交" class="djdl" />
								<input type="reset" class="chongzhi" value="重 置" />
							</div>
						</td>
					</tr>
				</table>
			</form>
			<div class="sjdbox">
				<a onclick=lay1(); href="javascript:;">
					<div class="ewmk">
						<p>手机扫描</p>
						<p>二维码登录</p>
					</div>
				</a>
				<a onclick=lay2(); href="javascript:;">
					<div class="iphk">
						<p>下载APP</p>
						<p>iPhone版</p>
					</div>
				</a>
				<a onclick=lay3(); href="javascript:;">
					<div class="andk">
						<p>下载APP</p>
						<p>Android版</p>
					</div>
				</a>
			</div>
		</div>
	</div>
</div>
<div class="footerbox">
	<div class="footer">一凌网 版权所有 ©2015 客服热线：400-600-1172</div>
</div>


<script type="text/javascript">
     $(function() {

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
         
         <%if (Error_Message != null) {%>
           alert("<%=Error_Message%>
	");
<%}%>
	});
	$(function() {

		$(".registerform").Validform({
			tiptype : function(msg, o, cssctl) {
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;

				if (!o.obj.is("form")) {//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip = o.obj.parents("td").next().find(".Validform_checktip");
					cssctl(objtip, o.type);
					objtip.text(msg);

					var infoObj = o.obj.parents("td").next().find(".info");
					if (o.type == 2) {
						infoObj.fadeOut(200);
					} else {
						if (infoObj.is(":visible")) {
							return;
						}
						var left = o.obj.offset().left, top = o.obj.offset().top;

						infoObj.css({
							left : left + 170,
							top : top - 45
						}).show().animate({
							top : top - 35
						}, 200);
					}

				}
			},
			usePlugin : {
				passwordstrength : {
					minLen : 6,
					maxLen : 18,
					trigger : function(obj, error) {
						if (error) {
							obj.parents("td").next().find(".passwordStrength").hide();
						} else {
							obj.parents("td").next().find(".info").hide();
							obj.parents("td").next().find(".passwordStrength").show();
						}
					}
				}
			}
		});
	});
	mini.parse();

	var loginWindow = $("#loginWindow");
	loginWindow.show();

	function onLoginClick() {
		var form = new mini.Form("#loginWindow");

		form.validate();
		if (form.isValid() == false)
			return;

		loginWindow.hide();
		mini.loading("登录验证中，请稍后...", "登录验证");

		setTimeout(function() {
			$("[name='form']").submit();
		}, 1000);
	}
	$(function() {
		$.backstretch("${base}template/common/images/bg.jpg");
		gaodu = document.documentElement.clientHeight;
		if (gaodu < 708) {
			$(".end").css("color", "#000");
		}
	});

	function lay1() {
		layer.open({
			type : 1,
			title : false,
			closeBtn : false,
			area : '280px',
			shadeClose : true,
			content : $('#tong')
		});
	};
	function lay2() {
		layer.open({
			type : 1,
			title : false,
			closeBtn : false,
			area : '280px 280px',
			shadeClose : true,
			content : $('#ios')
		});
	};
	function lay3() {
		layer.open({
			type : 1,
			title : false,
			closeBtn : false,
			area : '280px 280px',
			shadeClose : true,
			content : $('#and')
		});
	};
	//强制跳转到新窗口
	if (self.location != top.location) {
		top.location.href = location.href;
	}
	function register() {
		mini.open({
			url : "http://www.china-saas.com/plus/diy.php?action=post&diyid=3",
			showMaxButton : false,
			title : "申请试用",
			width : 800,
			height : 500,
			ondestroy : function(action) {
				if (action == "ok") {
				} else {
				}
			}
		});
	};
	//设为首页

	function SetHome(url) {
		if (document.all) {
			document.body.style.behavior = 'url(#default#homepage)';
			document.body.setHomePage(url);
		} else {
			alert("您好,您的浏览器不支持自动设置页面为首页功能,请您手动在浏览器里设置该页面为首页!");
		}
	}

	//加入收藏
	function extAddFavorite(title, url) {
		try {
			window.external.addFavorite(url, title);
		} catch (e) {
			try {
				window.sidebar.addPanel(title, url, "");
			} catch (e) {
				alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加");
			}
		}
	}
</script>
<div id="tong" class="hide" style="display:none;">
	<img src="${base}template/common/images/ewm.png" />
</div>
<div id="ios" class="hide" style="display:none;">
	<img src="${base}template/common/images/ios.png" />
</div>
<div id="and" class="hide" style="display:none;">
	<img src="${base}template/common/images/and.png" />
</div>
</body>
</html>
