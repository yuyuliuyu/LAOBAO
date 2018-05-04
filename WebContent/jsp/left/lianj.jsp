<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>PMS</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="${base}template/fusioncharts/css/ling.css" />
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<link href="${base}template/fusioncharts/css/liebiao.css" rel="stylesheet" type="text/css"  />
</head>

<body  onresize="gundong()">  
    <div class="laogun" id="laogun">
    <div class="jiantou nei" id="jiantou" >
    </div>
      <div class="liebiao" id="liebiao" style="display:none">
        
        <ul id="authMenu">
          <li class="xis"><a class="asdasd xuanz" href="javascript:;" onclick="callParent(-1)">首页</a></li>
        </ul>
      </div>
    </div>
    
    
<script type="text/javascript">
    //页面选项卡效果
    $(document).on('click', 'a.asdasd', function() {
        $(".liebiao li a").removeClass("xuanz");
        $(this).addClass("xuanz");
    });
    //页面选项效果结束
    function callParent(index) {
        mini.parse();
	    /*var iframe = parent.window.document.getElementsByName("main")[0]
		var tabs = window.parent.mini.get("mainTabs");
		var name = "index";
        var tab = tabs.getTab(name);
		tab.name = name;
        var title="";
   		var src="";
		if(index){
			switch(index){
				case -1:src="../jsp/icon/icon_home3.jsp";title="首页";break;
				case 1:src="../sell/create_combo!list.action";title="系统设置";break;
				case 2:src="../sell/create_combo!list.action";title="人事管理";break;
				case 3:src="../sell/healthy_statistics!list.action";title="考勤管理";break;
				case 4:src="../salary/salary_manage!tree.action";title="薪酬管理";break;
			}
		}
		tab.title=title;
		tabs.updateTab(tab);
        tabs.activeTab(tab);
        iframe.src = src;*/
        if (index) {
            if (index > 0) {
                parent.window.setMenuDisplay(index);
            } else {
                var iframe = parent.window.document.getElementsByName("main")[0];
                var tabs = window.parent.mini.get("mainTabs");
                var name = "index";
                var tab = tabs.getTab(name);
                tab.name = name;
                tab.title="首页";
                tabs.updateTab(tab);
                tabs.activeTab(tab);
                iframe.src = "../system/work_bench!show.action";
            }
        }
    }
	
	//页面鼠标滚轮滑动事件
    function gundong(){
		var gao=document.body.clientHeight;
		var lieb=document.getElementById("liebiao");
        var topsa=0;
		var daoding=gao-437;//每一个列表项高度65PX
		
		if(gao-437<0){ 
		var scrollFunc=function(e){
			var lieb=document.getElementById("liebiao");
			e=e || window.event; 
			if(e.wheelDelta){
                if(e.wheelDelta<0){
					if(topsa<daoding+10){
						lieb.style.top=daoding+"px";
						topsa=daoding;
						}
					else{
						lieb.style.top=topsa-10+"px";
					    topsa=topsa-10;
						}
					}
				else{
					if(topsa>-10){

						lieb.style.top=0+"px";
						topsa=0;
						}
					else{
						lieb.style.top=topsa+10+"px";
					    topsa=topsa+10;
						}
					}
            }else if(e.detail){//Firefox 
               if(e.detail<0){
					if(topsa<daoding+10){
						lieb.style.top=daoding+"px";
						topsa=daoding;
						}
					else{
						lieb.style.top=topsa-10+"px";
					    topsa=topsa-10;
						}
					}
				else{
					if(topsa>-10){
						lieb.style.top=0+"px";
						topsa=0;
						}
					else{
						lieb.style.top=topsa+10+"px";
					    topsa=topsa+10;
						}
					}
			}
            }   
 			}
			else
			{
				var scrollFunc=function(){}
				lieb.style.top=0+"px";
				topsa=0;
			}
	    //事件结束
		/*滚轮事件注册事件*/ 
if(document.addEventListener){ 
    document.addEventListener('DOMMouseScroll',scrollFunc,false); 
}
window.onmousewheel=document.onmousewheel=scrollFunc;
}
//列表页样式切换
        function shiyan(){
		var zlj2 = window.parent.document.getElementById("asdasd");
		var nyys2 = document.getElementById("lbys");
		nyys2.innerHTML=zlj2.innerHTML;
		var jianshi2=window.parent.document.getElementById("jianshi");
		//jianshi2.onclick=function(){nyys2.innerHTML=zlj2.innerHTML;}
		}
		 
		
//列表页样式切换结束
$(function(){
	getAuthMenu();
		   gundong();
		   //shiyan();
		   });

    function getAuthMenu() {
    	$.ajax({
            url: "${base}system/backend_menu!getAuthMenu.action",
            type:"POST",
            success: function(text) {
                if (text !="" && text.length > 0) {
                	var s = text.split(",");
                    var authMenu = $("#authMenu");
                	if (s.indexOf("1") > -1) {
                		authMenu.append('<li class="xs"><a class="asdasd" href="javascript:;" onclick="callParent(1)">系统管理</a></li>');
                	}
                	if (s.indexOf("2") > -1) {
                		authMenu.append('<li class="xs"><a class="asdasd" href="javascript:;" onclick="callParent(2)">人事管理</a></li>');
                	}
                	if (s.indexOf("3") > -1) {
                		authMenu.append('<li class="kc"><a class="asdasd" href="javascript:;" onclick="callParent(3)">考勤管理</a></li>');
                	}
                	if (s.indexOf("4") > -1) {
                		authMenu.append('<li class="fx"><a class="asdasd" href="javascript:;" onclick="callParent(4)">薪酬管理</a></li>');
                	}
                	if (s.indexOf("5") > -1) {
                		authMenu.append('<li class="fx"><a class="asdasd" href="javascript:;" onclick="callParent(5)">劳保管理</a></li>');
                	}
                }
            }
        });
    }
</script> 

</body>
</html>
