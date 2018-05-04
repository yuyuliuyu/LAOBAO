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
    
    <title>成本</title>
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
	function openWindow1(urlname, name) {

		var tabs = window.parent.parent.mini.get("mainTabs");

		var id = "tab$"+name;
		var tab = tabs.getTab(id);
		if (!tab) {
			tab = {};
			tab.name = id;
			tab.title = name;
			tab.showCloseButton = true;
			tab.url = "${base }" + urlname + "!show.action";

			tabs.addTab(tab);
		}
		tabs.activeTab(tab);
	}
</script>
  </head>
  
  <body>
     <div class="assbk" >
    <div class="asskj">
        <%--<img src="${base}template/system/images/daohang/chengben.png"  class="imgh"  border="0" usemap="#Map" />
        <map name="Map"  id="Map">
          	  <area shape="rect" coords="182,15,374,50" href="javascript:openWindow('costmgt/ddbzcbtz','地点标准成本调整');">		<!-- 地点标准成本调整 -->
	          <area shape="rect" coords="181,56,375,92" href="javascript:openWindow('costmgt/ddbzcbcx','地点标准成本查询');">		<!-- 地点标准成本查询 -->
	          <area shape="rect" coords="457,19,645,53" >		<!-- 地点实际成本调整 -->
	          <area shape="rect" coords="458,60,646,91" >		<!-- 地点实际成本查询 -->
	          <area shape="rect" coords="18,134,255,170" >		<!-- 员工月薪输入 -->
	          <area shape="rect" coords="19,175,253,208">		<!-- 资产折旧 -->
	          <area shape="rect" coords="18,214,251,249" >		<!-- 工作中心领料单 -->
	          <area shape="rect" coords="22,254,249,287" >		<!-- 生产日报输入 -->
	          <area shape="rect" coords="318,191,509,223" >		<!-- 月结 -->
              <area shape="rect" coords="324,293,505,326" >		<!-- 月结运算进度查询 -->
              <area shape="rect" coords="579,137,821,168" >		<!-- (工作中心-生产订单-工序)成本 -->
              <area shape="rect" coords="581,175,818,207" >		<!-- (生产订单-工序)成本 -->
              <area shape="rect" coords="578,215,822,246" >		<!-- 失效生产单及平衡存仓负数成本 -->
              <area shape="rect" coords="581,255,820,286" >		<!-- (生产订单-产品编号)成本 -->
              <area shape="rect" coords="581,294,822,326" >		<!-- 地点成本变化流水账查询 -->
        </map>
  		--%>
  		<img src="${base}template/system/images/daohang/Newchengben.png"  class="imgh"  border="0" usemap="#Map" />
       <map name="Map">
              <area shape="rect" coords="39,38,241,75" href="javascript:openWindow('costmgt/ddbzcbtz','地点标准成本调整');">
              <area shape="rect" coords="358,38,560,75" href="javascript:openWindow('costmgt/ddsjcbtz','地点实际成本调整');">
              <area shape="rect" coords="37,98,237,138" href="javascript:openWindow('costmgt/ddbzcbcx','地点标准成本查询');">
              <area shape="rect" coords="361,98,565,134" href="javascript:openWindow('locmgt/loc_stk_loc','库存现状或历史查询');">
              <area shape="rect" coords="198,235,398,269" href="javascript:openWindow1('workmgt/ygyx','员工月薪输入');">
              <area shape="rect" coords="199,287,397,319" href="javascript:openWindow('asset/asset_depre','资产折旧');">
              <area shape="rect" coords="198,336,396,373" href="javascript:openWindow('workmgt/gzzxlld','工作中心领料单(计入杂费)');">
              <area shape="rect" coords="196,389,399,421" href="javascript:openWindow('workmgt/scrbsr','生产日报输入');">
            </map>
  		</div>
	</div>
  </body>
</html>






