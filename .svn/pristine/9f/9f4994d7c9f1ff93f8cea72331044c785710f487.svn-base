<%-- <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
String selectedNum=request.getParameter("selectednum");
if(selectedNum==null)
	selectedNum="0";
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>管理中心</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
    <link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css"  />
    <link href="${base}template/system/css/admin.css" rel="stylesheet" type="text/css"  />
    <link href="${base}template/system/css/tiao.css" rel="stylesheet" type="text/css"  />
    <script src="${base}template/common/js/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script src="${base}template/system/js/base.js" type="text/javascript"></script>
    <script src="${base}template/system/js/admin.js" type="text/javascript"></script>
    <script type="text/javascript">
    var selectednum=<%=selectedNum%>
    $().ready(function() {

	var $menuItem = $("#menu .menuItem");
	var $previousMenuItem;
	
	$menuItem.click( function() {
		var $this = $(this);
		if ($previousMenuItem != null) {
			$previousMenuItem.removeClass("current");
		}
		$previousMenuItem = $this;
		$this.addClass("current");
	});

});

 $(function(){
 			if(selectednum==0){
 				 $(".def").text("(默认风格1)");
 			}else{
 				$(".def").text("(默认风格2)");
 			}
 			
 			$(".sel option").eq(selectednum).attr("selected","true");
 			
        });
			
function chengon(targ,selObj,restore){  
	
    var num = $(".sel option:selected").index();
         
	top.location.href = "<%=basePath%>"+"system/"+selObj.options[selObj.selectedIndex].value+"?selectednum="+num;
	
} 


</script>

   
<style>	
	body{background:#008DE6;}
</style>
  </head>
  <body>	


		<div class="headbg">
        <div class="zhongstyle">
        	<div class="logostyle"></div>

            <div class="rightstyle">
        		 <div class="weltip">
        		 	欢迎您,<sec:authentication property="name"/>
        		 	
        		 </div>
           		 
           		 <div class="tip_logo tip1">
           		 	<a href="javascript:;"> <img src="${base}/template/system/images/skinw8/tip11.png" ></a>
           		 </div>
				
				 <div class="tip_logo tip2">
           		 	<a href="javascript:;"> <img src="${base}/template/system/images/skinw8/tip22.png" ></a>
           		 </div>
           		 
           		 <div class="tip_logo tip3" onclick="top.location='<%=basePath%>logout?token=backend'">
           		 	<a href="<%=basePath%>logout?token=backend" target="_top"></a>
           		 </div>
            </div>
            
        </div>
    </div>
    

</body>
</html>
 --%>
 <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head></head>
  <body>

  </body>
<script type="text/javascript">
   function reload(){
	   document.location.reload();
   }
</script>
</html>