<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
String selectedNum=request.getParameter("selectednum");
if(selectedNum==null)
	selectedNum="0";
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>系统界面</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<script src="${base}template/common/js/toastr.min.js" type="text/javascript"></script>
     <link href="${base}template/common/css/toastr.min.css" rel="stylesheet" type="text/css" />
<link href="${base}template/system/css/base.css" rel="stylesheet"
	type="text/css" />

	<!--引入皮肤样式-->
    <link href="${base}template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
 
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	border: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
	background: #f6f6f6;
}
</style>
</head>

<body id="body">
<!-- 滑动通知窗口End --> 
	<input class="mini-hidden" value="1" name="id" id="id" />
	<input class="mini-hidden" value="${url }" name="url" id="url" />
	<input class="mini-hidden" value="${name}" name="name" id="name" />
	<!--Layout-->
	<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
		<div class="header" region="north" height="90" showSplit="false" showHeader="false">
			<iframe scrolling="no" id="topframe" src="backend_page!header.action" frameborder="0" name="main" style="width:100%;height:100%;" border="0" ></iframe>
		</div>
		   <div title="south" region="south" showSplit="false" showHeader="false" height="30" >
        <div  class="footys"> © 青岛一凌网集成有限公司版权所有 </div>
    </div> 
		<div title="center" region="center" bodyStyle="overflow:hidden;" style="border:0;">
			<!--Splitter-->
			<div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0;" handlerSize="9">
				<div size="220" maxSize="250" minSize="100" showCollapseButton="true" style="border-width:0px;" >
					<!--Tree-->
					 <div class="menutitle">    系统菜单  </div>
					<ul id="leftTree" class="mini-outlooktree"
						url="backend_menu!list.action" style="width:100%;height:94.4%;"
						showTreeIcon="true" textField="text" idField="id" resultAsTree="false" onNodeclick="onNodeclick">
					</ul>
				</div>
				<div showCollapseButton="false" style="border:0px;" onactivechanged="en">
					<iframe id="mainTabs" frameborder="0" name="main"
						style="width:100%;height:100%;" border="0">
					</iframe>
				</div>
				<%--<div id="mainTabs"  class="mini-tabs"  onactivechanged="en" activeIndex="0" style="width:100%;height:100%;" contextMenu="#tabsMenu"> 
		             <div name="first" id="" title="首页" url="${base}jsp/icon/icon_home3.jsp" >
		               
		             </div>
		        </div>  
			--%>
			</div>
		</div>
	</div>

	<script type="text/javascript">
        mini.parse();
        document.getElementById("id");
        var tree = mini.get("leftTree");
 		var iframe = document.getElementById("mainTabs");
        iframe.src = "${base}jsp/system/backend_page_index.jsp";
       //////////////////////////////////////
        function showTab(node) {  
            var iframe = document.getElementById("mainTabs");
        	iframe.src = node.url; 
        } 
        function onNodeclick(e) { 
            var node = e.node;  
            showTab(node); 
        }
        //////////////////////////////
        function en(e){
    		var tabs = mini.get("mainTabs");
    		if(tabs.tabs.length>8){
    			   tabs.removeTab(tabs.tabs[1]);
    		}
    		var tab = tabs.getActiveTab();
    	   if((tab.name).indexOf("add")==-1){
    		   	var framein = tabs.getTabIFrameEl(tab);
    		   	if(framein!=null){
    		   		var grid =  framein.contentWindow.mini.get("grid1");
    		   		
    		   		if(grid!=null){
    		   			grid.reload();
    		   		}
    		   	}
    		} 
    	}
        ///////////////////////////////////
         function onBeforeOpen(e) {
                currentTab = tabs.getTabByEvent(e.htmlEvent);
                if (!currentTab) {
                    e.cancel = true;                
                }
            }
        function closeTab() {
                var but = [currentTab];            
                but.push(tabs.getTab("first"));
                tabs.removeTab(but);
            }
            function closeAllBut() {
                var but = [currentTab];            
                but.push(tabs.getTab("first"));
                tabs.removeAll(but);
            }
            function closeAll() {
                tabs.removeAll(tabs.getTab("first"));
            }
            function closeAllButFirst() {
                var but = [currentTab];            
                but.push(tabs.getTab("first"));
                tabs.removeAll(but);
            }
        function reloadmenu() {	
     		tabs.reloadTab(currentTab);
     	}
        
        function loading() {
            mini.mask({
                el: document.body,
                cls: 'mini-mask-loading',
                html: '努力执行中,请稍后...'
            });
            
        }
        
        function unmask() {
             mini.unmask(document.body);
        }
        
        toastr.options = {
     					    "closeButton": true,
     						  "debug": true,
     						  "progressBar": true,
     						  "positionClass": "toast-top-center",
     						  "showDuration": "1000",
     						  "hideDuration": "1000",
     						  "timeOut": "2000",
     						  "extendedTimeOut": "2000",
     						  "showEasing": "swing",
     						  "hideEasing": "linear",
     						  "showMethod": "fadeIn",
     						  "hideMethod": "fadeOut",
     						 "preventDuplicates": false
             };
        
        function noAuth(){
                 toastr.error("请联系管理员获取操作权限!","权限错误");
        }
        //////////////////////////////////////////
        //账号管理
            function onclickd(){
                mini.open({
                    url: "../system/admin!edit2.action",
                    title: "编辑用户",
                    width:450,
                    height: 340,
                    allowDrag:false,
                    allowResize: false, 
                    onload: function () {
                    },
                    ondestroy: function (action) {
                    }
                });
            }
          
    </script>

</body>

</html>