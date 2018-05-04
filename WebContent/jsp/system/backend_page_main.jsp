<%-- <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    <title>管理中心</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <frameset id="parentFrameset" rows="60,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame id="headerFrame" name="headerFrame" src="system/backend_page!header.action" frameborder="no" scrolling="no" noresize="noresize" />
	<frameset id="mainFrameset" name="mainFrameset" cols="130,6,*" frameborder="no" border="0" framespacing="0">
		<frame id="menuFrame" name="menuFrame" src="system/backend_menu!admin.action" frameborder="no" scrolling="no" noresize="noresize" />
		<frame id="middleFrame" name="middleFrame" src="system/backend_page!middle.action" frameborder="no" scrolling="no" noresize="noresize" />
		<frame id="mainFrame" name="mainFrame" src="system/backend_page!index.action" frameborder="no" noresize="noresize" />
	</frameset>
</frameset>
<noframes>
	<body>
		noframes
	</body>
</noframes>
</html> --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>管理中心</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    
    <script src="${base}template/miniui/boot.js" type="text/javascript"></script>
    <link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css" />
    
	
    
    <style type="text/css">
    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }    
    .header
    {
    	height:90px;
        background:url(../header.gif) repeat-x 0 -1px;
    }
    </style>  
    

    
</head>
<body >   

<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
    <div class="header" region="north" height="90" showSplit="false" showHeader="false">
        <iframe id="topframe" src = "backend_page!header.action" frameborder="0" name="main" style="width:100%;height:100%;" border="0" scrolling="no"></iframe>  
    </div>
   <!--  <div title="south" region="south" showSplit="false" showHeader="false" height="30" >
        <div style="line-height:28px;text-align:center;cursor:default">Copyright © 青岛一凌网集成有限公司版权所有 </div>
    </div> -->
    <!-- <div showHeader="false" region="west" width="180" maxWidth="250" minWidth="100" >
        OutlookMenu
        <div id="leftTree" class="mini-outlookmenu" url="backend_menu!list.action" onitemselect="onItemSelect"
            idField="id" parentField="pid" textField="text" borderStyle="border:0" 
        >
        </div>

    </div>
    <div title="center" region="center" bodyStyle="overflow:hidden;">
        <iframe id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
    </div> -->
    
    
    
    <div title="center" region="center" style="border:0;" bodyStyle="overflow:hidden;">
        <!--Splitter-->
        <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0;">
            <div size="180" maxSize="250" minSize="100" showCollapseButton="true" style="border:0;">
                <!--OutlookTree-->
                <div id="leftTree" class="mini-outlookmenu" url="backend_menu!list.action" onitemselect="onItemClick"
            		idField="id" parentField="pid" textField="text" borderStyle="border:0" 
      			  >
                </div>
                
            </div>
            <div showCollapseButton="false" style="border:0;">
                <!--Tabs-->
                <div id="mainTabs" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;"      
                     plain="false">
                    <div title="首页" url="backend_tree!list.action" >        
                    </div>
                    
                </div>
            </div>        
        </div>
    </div>
</div>
    
    <script type="text/javascript">
        
        mini.parse();

        function showTab(node) {
        var tabs = mini.get("mainTabs");

        var id = "tab$" + node.id;
        var tab = tabs.getTab(id);
        if (!tab) {
            tab = {};
            tab.name = id;
            tab.title = node.text;
            tab.showCloseButton = true;
            
            tab.url = node.url;

            tabs.addTab(tab);
        }
        tabs.activeTab(tab);
    }
    function onItemClick(e) {        
        var item = e.item;
       
       
            showTab(item);
                   
    }

    </script>

</body>
</html>
