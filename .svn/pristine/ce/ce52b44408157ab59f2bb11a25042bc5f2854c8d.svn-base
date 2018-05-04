<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shengchan2.jsp' starting page</title>
    <link href="${base}template/system/css/demo.css" rel="stylesheet"  type="text/css" />
    <script src="${base}template/common/js/jquery-1.6.2.min.js" type="text/javascript"></script>
	<style type="text/css">

		html
		{
			border:solid 1px #999;
			border-top:0;
		}
		body
		{
			padding:0;	
		}

	.mini-tabs-body {
		padding:0;
	}
	</style>



<script type="text/javascript">
	/* $(function(){
		
		var imgh = $("body").height();
		
		var toph = parseInt( (imgh-375)/2 );
		
		$(".assbk").css("marginTop",toph);
	}); */

	function openWindow(urlname, name) {

		var tabs = window.parent.parent.mini.get("mainTabs");

		var id = "tab$"+name;
		var tab = tabs.getTab(id);
		if (!tab) {
			tab = {};
			tab.name = id;
			tab.title = name;
			tab.showCloseButton = true;
			tab.url = "${base }" + urlname + "!list.action";

			tabs.addTab(tab);
		}
		tabs.activeTab(tab);
	}
</script>
  </head>
  
  <body>
     <div class="assbk" >
    <div class="asskj">
        <img src="${base}template/system/images/daohang/sf03.png"  class="imgh"  border="0" usemap="#Map" />
        <map name="Map"  id="Map">
           <area shape="rect" coords="18,10,210,46"      href="javascript:openWindow('jcsj/jcsj_vendor','供应商资料维护');"/><!-- href="物流公司维护" -->
          <area shape="rect" coords="263,10,457,47"     href="javascript:openWindow('jcsj/transport_way','运输方式设置');"/><!-- href="运输方式设置" -->
          <area shape="rect" coords="141,116,333,151"  href="javascript:openWindow('arap/yfd','运费单');" />
          <area shape="rect" coords="139,179,334,218"  href="javascript:openWindow('arap/yffp','运费发票');" />
          <!--  <area shape="rect" coords="654,257,849,296"  href="javascript:openWindow('arap/gysfpqx','供应商发票取消');"/>--><!-- href="运费发票取消" -->
          <area shape="rect" coords="139,243,333,283"  href="javascript:openWindow('arap/zfgyshk','支付供应商货款');"/><!-- href="支付运费款" -->
          <area shape="rect" coords="390,245,586,283"  href="javascript:openWindow('arap/yfkggys','预付款给供应商');"/><!-- href="预付款给物流公司" -->
          <area shape="rect" coords="390,181,584,216"  href="javascript:openWindow('arap/gysfpqx','供应商发票取消');"><!-- 运费发票取消 -->
          <!--  <area shape="rect" coords="140,367,336,405"  href="javascript:openWindow('arap/zfgyshk','支付供应商货款'"/>-->
          <area shape="rect" coords="142,311,334,344"  href="javascript:openWindow('arap/yfkcx','应付款查询');"><!-- 应付货款查询 -->
        </map>
  		</div>
	</div>
  </body>
</html>






