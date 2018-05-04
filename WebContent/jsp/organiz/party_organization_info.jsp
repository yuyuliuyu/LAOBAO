<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
    String flag=request.getParameter("flag");
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>总页面</title>
<link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css" />
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<script src="../template/plugin/public/add.js" type="text/javascript"></script>
<link href= "../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
<!--引入皮肤样式-->
<link href="${base}/template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    html, body
    {
        font-size:12px;
        padding:0;
        margin:0;
        border:0;
        height:100%;
        overflow:hidden;
    }
    .check_box
    {
        float:left;
        margin-right:5px;
        *margin-right:15px !important;
    }
    
    input[type="checkbox"]
    {
        vertical-align:-3px;
    }
 </style>
</head>
<body>
    <div id="tabs1" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;" 
		onactivechanged="onTabsActiveChanged" bodyStyle="padding:0;border:0;" > 
		<div title="组织概要">
			<iframe id="framezzgy" frameborder="0" name="framezzgy" style="width:100%;height:100%;" border="0"></iframe>
		</div>
		<div title="历届班子" >
			<iframe id="frameljbz" frameborder="0" name="frameljbz" style="width:100%;height:100%;" border="0"></iframe>
		</div>
		<div title="工作组" >
			<iframe id="framegzz" frameborder="0" name="framegzz" style="width:100%;height:100%;" border="0"></iframe>
		</div>
		<div title="历史沿革" >
			<iframe id="framelsyg" frameborder="0" name="framelsyg" style="width:100%;height:100%;" border="0"></iframe>
		</div> 
		<div title="党员管理" id="bbb" name="bbb" >
			<iframe id="framedygl" frameborder="0" name="framedygl" style="width:100%;height:100%;" border="0"></iframe>
		</div> 
		<div title="日常管理" id="ccc" name="ccc" >
			<iframe id="framercgl" frameborder="0" name="framercgl" style="width:100%;height:100%;" border="0"></iframe>
		</div> 
	</div>
	
	
	
	<!-- 管理党员  --> 
	
    <div id="tabs2"  class="mini-tabs" activeIndex="1" style="width:100%;height:100%;" 
		onactivechanged="onTabsActiveChanged" bodyStyle="padding:0;border:0;" > 
		<div title="正式党员" >
			<iframe id="framezsdy" frameborder="0" name="framezsdy" style="width:100%;height:100%;" border="0"></iframe>
		</div>
		<div title="预备党员">
			<iframe id="frameybdy" frameborder="0" name="frameybdy" style="width:100%;height:100%;" border="0"></iframe>
		</div>
		<div title="计划发展对象" >
			<iframe id="framejhfz" frameborder="0" name="framejhfz" style="width:100%;height:100%;" border="0"></iframe>
		</div>
		<div title="入党积极分子" >
			<iframe id="framejjfz" frameborder="0" name="framejjfz" style="width:100%;height:100%;" border="0"></iframe>
		</div>
		<div title="群众" >
			<iframe id="frameqz" frameborder="0" name="frameqz" style="width:100%;height:100%;" border="0"></iframe>
		</div>
		<div title="党员异动" >
			<iframe id="framedyyd" frameborder="0" name="framedyyd" style="width:100%;height:100%;" border="0"></iframe>
		</div> 
		<div title="组织管理"  id="aaa" name="aaa" >
			<iframe id="framezzgl" frameborder="0" name="framezzgl" style="width:100%;height:100%;" border="0"></iframe>
		</div>
		<div title="日常管理" id="ccc" name="ccc" >
			<iframe id="framercgl" frameborder="0" name="framercgl" style="width:100%;height:100%;" border="0"></iframe>
		</div> 
	</div>
    
    <div id="tabs3" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;" 
		onactivechanged="onTabsActiveChanged" bodyStyle="padding:0;border:0;" > 
		<div title="党费信息">
			<iframe id="framedfxx" frameborder="0" name="framedfxx" style="width:100%;height:100%;" border="0"></iframe>
		</div>
		<div title="作风建设" >
			<iframe id="framezfjs" frameborder="0" name="framezfjs" style="width:100%;height:100%;" border="0"></iframe>
		</div>
		<div title="统战工作" >
			<iframe id="frametzgz" frameborder="0" name="frametzgz" style="width:100%;height:100%;" border="0"></iframe>
		</div>
		<div title="双拥工作" >
			<iframe id="framesygz" frameborder="0" name="frametzgz" style="width:100%;height:100%;" border="0"></iframe>
		</div> 
		<div title="日常工作" >
			<iframe id="framercgz" frameborder="0" name="framercgz" style="width:100%;height:100%;" border="0"></iframe>
		</div> 
		<div title="精神文明建设" >
			<iframe id="framewmjs" frameborder="0" name="framewmjs" style="width:100%;height:100%;" border="0"></iframe>
		</div> 
		<div title="扶贫工作" >
			<iframe id="framefpgz" frameborder="0" name="framefpgz" style="width:100%;height:100%;" border="0"></iframe>
		</div> 
		<div title="党员管理" id="bbb" name="bbb" >
			<iframe id="framedygl" frameborder="0" name="framedygl" style="width:100%;height:100%;" border="0"></iframe>
		</div> 
		<div title="组织管理"  id="aaa" name="aaa" >
			<iframe id="framezzgl" frameborder="0" name="framezzgl" style="width:100%;height:100%;" border="0"></iframe>
		</div>
	</div>
    
    <script type="text/javascript"> 
        mini.parse();
        zzgl();
        //懒加载tab数据
        function dygl(){ 
        	var iframe2 = document.getElementById("tabs1");
        	var iframe1 = document.getElementById("tabs2"); 
        	var iframe3 = document.getElementById("tabs3"); 
        	iframe1.style.display="block";  
        	iframe2.style.display="none"; 
        	iframe3.style.display="none"; 
        	}
        function zzgl(){ 
        	var iframe2 = document.getElementById("tabs1");
        	var iframe1 = document.getElementById("tabs2"); 
        	var iframe3 = document.getElementById("tabs3"); 
        	iframe2.style.display="block";  
        	iframe1.style.display="none";  
        	iframe3.style.display="none";  
        	}
        function rcgl(){ 
        	var iframe2 = document.getElementById("tabs1");
        	var iframe1 = document.getElementById("tabs2"); 
        	var iframe3 = document.getElementById("tabs3"); 
        	iframe2.style.display="none";  
        	iframe1.style.display="none";  
        	iframe3.style.display="block";   
        	}
        function onTabsActiveChanged(e) {
            var tabs = e.sender;
            var tab = tabs.getActiveTab();
            if(tab.title == "组织概要"){
            	var iframe = document.getElementById("framezzgy");
            	iframe.src = "../organiz/organiz_info!list.action"; 
            	grid.hideColumn("id");
			    grid.load();
            } else if (tab.title == "历届班子"){
            	var iframe = document.getElementById("frameljbz");
            	iframe.src = "../organiz/organiz_group!list.action"; 
			    grid2.hideColumn("id");
			    grid2.load();
            } else if (tab.title == "工作组"){
            	var iframe = document.getElementById("framegzz");
            	iframe.src = "../organiz/working_group!list.action"; 
			    grid3.hideColumn("id");
			    grid3.load();
            } else if (tab.title == "历史沿革"){
            	var iframe = document.getElementById("framelsyg");
            	iframe.src = "../organiz/organiz_histry!list.action"; 
			    grid4.hideColumn("id");
			    grid4.load();
            }else if (tab.title == "正式党员"){
            	var iframe = document.getElementById("framezsdy");
            	iframe.src = "../organiz/party_member!list.action"; 
			    grid5.hideColumn("id");
			    grid5.load();
            }else if (tab.title == "预备党员"){
            	var iframe = document.getElementById("frameybdy");
            	iframe.src = "../organiz/party_member!yblist.action"; 
			    grid6.hideColumn("id");
			    grid6.load();
            }else if (tab.title == "计划发展对象"){
            	var iframe = document.getElementById("framejhfz");
            	iframe.src = "../organiz/party_member!jhlist.action"; 
			    grid7.hideColumn("id");
			    grid7.load();
            }else if (tab.title == "入党积极分子"){
            	var iframe = document.getElementById("framejjfz");
            	iframe.src = "../organiz/party_member!jjlist.action"; 
			    grid9.hideColumn("id");
			    grid9.load();
            }else if (tab.title == "群众"){
            	var iframe = document.getElementById("frameqz");
            	iframe.src = "../organiz/party_member!qzlist.action"; 
			    grid8.hideColumn("id");
			    grid8.load();
            }else if (tab.title == "党员异动"){
            	var iframe = document.getElementById("framedyyd");
            	iframe.src = "../organiz/party_relation!ydlist.action"; 
			    grid8.hideColumn("id");
			    grid8.load();
            }
            else if (tab.title == "党费信息"){
            	var iframe = document.getElementById("framedfxx");
            	iframe.src = "../organiz/party_manager!dflist.action"; 
			    grid10.hideColumn("id");
			    grid10.load();
            }else if (tab.title == "作风建设"){
            	var iframe = document.getElementById("framezfjs");
            	iframe.src = "../organiz/party_manager!jslist.action"; 
			    grid11.hideColumn("id");
			    grid11.load();
            }else if (tab.title == "统战工作"){
            	var iframe = document.getElementById("frametzgz");
            	iframe.src = "../organiz/party_manager!tzlist.action"; 
			    grid12.hideColumn("id");
			    grid12.load();
            }else if (tab.title == "双拥工作"){
            	var iframe = document.getElementById("framesygz");
            	iframe.src = "../organiz/party_manager!sylist.action"; 
			    grid13.hideColumn("id");
			    grid13.load();
            }else if (tab.title == "日常工作"){
            	var iframe = document.getElementById("framercgz");
            	iframe.src = "../organiz/party_manager!rclist.action"; 
			    grid14.hideColumn("id");
			    grid14.load();
            }else if (tab.title == "精神文明建设"){
            	var iframe = document.getElementById("framewmjs");
            	iframe.src = "../organiz/party_manager!jslist.action"; 
			    grid15.hideColumn("id");
			    grid15.load();
            }else if (tab.title == "扶贫工作"){
            	var iframe = document.getElementById("framefpgz");
            	iframe.src = "../organiz/party_manager!fplist.action"; 
			    grid16.hideColumn("id");
			    grid16.load();
            } 
            else if (tab.title == "党员管理"){dygl();
            }else if (tab.title == "组织管理"){ zzgl(); 
            }else if (tab.title == "日常管理"){ rcgl();
            }

        }
    </script>
</body>
</html>