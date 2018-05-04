<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'icon_caigou.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="${base}template/system/css/demo.css" rel="stylesheet"
	type="text/css" />

<script type="text/javascript">
function openWindow(urlname,name) {
 
 var tabs = window.parent.mini.get("mainTabs");

 var id = "tab$"+name;
        var tab = tabs.getTab(id);
        if (!tab) {
            tab = {};
            tab.name = id;
            tab.title = name;
            tab.showCloseButton = true;
            tab.url = "${base }"+urlname+"!list.action";

            tabs.addTab(tab);
        }
        tabs.activeTab(tab);
    } 
</script>
</head>

<body style="background:white;">
	<div class="assbk" >
		<div class="asskj">
			<%--<img src="${base}template/system/images/daohang/Newcaiwu.png" border="0"
				align="center" usemap="#Map" />
			<map name="Map" id="Map">
				<area shape="rect" coords="178,6,378,43"
					href="javascript:openWindow('/finmgt/wlcwflsz','物料的财务分类');" />
				<area shape="rect" coords="461,4,662,42"
					href="javascript:openWindow('finmgt/fi_kjkmlx','会计科目类型');" />
				<area shape="rect" coords="178,79,377,113" href="javascript:openWindow('bominfo/item','物料数据维护');" />
				<area shape="rect" coords="459,79,661,117"
					href="javascript:openWindow('finmgt/fi_kjkm','会计科目调整');" />
				<area shape="rect" coords="20,138,219,172"
					href="javascript:openWindow('finmgt/pzlxsz','凭证类型设置');" />
				<area shape="rect" coords="21,189,222,226"
					href="javascript:openWindow('finmgt/cypzzysz','常用凭证摘要设置');" />
				<area shape="rect" coords="16,234,219,276"
					href="javascript:openWindow('finmgt/xmsz','项目设置');" />
				<area shape="rect" coords="317,189,518,226"
					href="javascript:openWindow('finmgt/jzpz','记账凭证');" />
				<area shape="rect" coords="612,187,813,225"
					href="javascript:openWindow('finmgt/kjqjkgzkz','会计期间开关帐');" />
				<area shape="rect" coords="75,297,276,332"  /><!-- href="总账" -->
				<area shape="rect" coords="73,346,274,381"  /><!--  href="明细账"-->
				<area shape="rect" coords="73,392,274,430"  /><!-- href="凭证汇总表" -->
				<area shape="rect" coords="319,298,522,334"  /><!-- href="会计科目余额查询" -->
				<area shape="rect" coords="316,346,519,384"  /><!--  href="会计科目余额变化流水账"-->
				<area shape="rect" coords="572,297,775,332"  /><!--  href="资产负债表"-->
				<area shape="rect" coords="572,343,775,382"  /><!-- href="损益表" -->
			</map>
		--%>
		<img src="${base}template/system/images/daohang/Newcaiwu.png" border="0"
				align="center" usemap="#Map" />
			<map name="Map">
              <area shape="rect" coords="160,58,362,95" href="javascript:openWindow('/finmgt/wlcwflsz','物料的财务分类');">
              <area shape="rect" coords="442,58,644,95" href="javascript:openWindow('finmgt/fi_kjkmlx','会计科目类型');">
              <area shape="rect" coords="160,137,360,177" href="javascript:openWindow('bominfo/item','物料数据维护');">
              <area shape="rect" coords="442,137,646,173" href="javascript:openWindow('finmgt/fi_kjkm','会计科目设置');">
              <area shape="rect" coords="44,217,244,251" href="javascript:openWindow('finmgt/pzlxsz','凭证类型设置');">
              <area shape="rect" coords="44,268,242,300" href="javascript:openWindow('finmgt/cypzzysz','常用凭证摘要设置');">
              <area shape="rect" coords="45,310,243,347" href="javascript:openWindow('finmgt/xmsz','项目设置');">
              <area shape="rect" coords="321,266,520,300" href="javascript:openWindow('finmgt/jzpz','记账凭证');">
              <area shape="rect" coords="589,265,789,299" href="javascript:openWindow('finmgt/kjqjkgzkz','会计期间开关帐');">
              <area shape="rect" coords="321,371,522,406" href="javascript:openWindow('finmgt/fi_item_bal','会计科目余额查询');">
              <area shape="rect" coords="322,424,521,459" href="javascript:openWindow('finmgt/fi_item_bh','会计科目余额变化流水账');">
            </map>
		</div>
	</div>
</body>
</html>
