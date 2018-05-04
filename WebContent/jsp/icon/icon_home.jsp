
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
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>管理员列表</title>

    <style type="text/css">
    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }    
    </style>  
    <link href="${base}template/system/css/master.css" rel="stylesheet" type="text/css" />
      <link href="${base}template/system/css/master1.css" rel="stylesheet" type="text/css" />
    <script src="${base}template/miniui/boot.js" type="text/javascript"></script>
    
    <!--引入皮肤样式-->
    <link href="${base}template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
</head>
<body>
   
    
<div class="mini-splitter" style="width:100%;height:100%;">
    <div size="750" showCollapseButton="false" >
        <div class="mini-fit">
            <!-- 图标显示区域 -->
				<div class="show">
					<ul class="list-icon">
<!-- 						<li onclick="openWindow('caigou','采购')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/caigou.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="采购"> -->
<!-- 								采购 -->
<!-- 							</a> -->
<!-- 						</li> -->

<!-- 						<li onclick="openWindow('cangchu','仓储')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/cangchu.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="仓储"> -->
<!-- 								仓储 -->
<!-- 							</a> -->
<!-- 						</li> -->

<!-- 						 <li onclick="openWindow('haiguan','海关')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/haiguan.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="海关"> -->
<!-- 								海关 -->
<!-- 							</a> -->
<!-- 						</li>  -->

<!-- 						<li onclick="openWindow('zhijian','质检')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/zhijian.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="质检"> -->
<!-- 								质检 -->
<!-- 							</a> -->
<!-- 						</li> -->

<!-- 						 <li onclick="openWindow('chejian','车间')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/chejian.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="车间"> -->
<!-- 								车间 -->
<!-- 							</a> -->
<!-- 						</li>  -->

<!-- 						 <li onclick="openWindow('caiwu','财务')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/caiwu.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="财务"> -->
<!-- 								财务 -->
<!-- 							</a> -->
<!-- 						</li>  -->

<!-- 						 <li onclick="openWindow('xitong','系统')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/xitong.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="系统"> -->
<!-- 								系统 -->
<!-- 							</a> -->
<!-- 						</li>  -->

<!-- 						<li onclick="openWindow('yingshou','应收应付')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/yingshouyingfu.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="应收应付"> -->
<!-- 								应收应付 -->
<!-- 							</a> -->
<!-- 						</li> -->
						
<!-- 						<li onclick="openWindow('xitong','系统管理')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/xitongguanli.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="系统管理"> -->
<!-- 								系统管理 -->
<!-- 							</a> -->
<!-- 						</li> -->
						
<!-- 						  <li onclick="openWindow('zichan','资产')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/zichan.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="资产"> -->
<!-- 								资产 -->
<!-- 							</a> -->
<!-- 						</li>   -->
						
<!-- 						 <li onclick="openWindow('chengben','成本')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/chengben.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="成本"> -->
<!-- 								成本 -->
<!-- 							</a> -->
<!-- 						</li>  -->
						
<!-- 						<li onclick="openWindow('jichu','基础')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/jichu.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="基本"> -->
<!-- 								基础 -->
<!-- 							</a> -->
<!-- 						</li> -->
						
<!-- 						<li onclick="openWindow('gongcheng','工程')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/gongcheng.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="工程"> -->
<!-- 								工程 -->
<!-- 							</a> -->
<!-- 						</li> -->
						
<!-- 						<li onclick="openWindow('xiaoshou','销售')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/xiaoshou.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="销售"> -->
<!-- 								销售 -->
<!-- 							</a> -->
<!-- 						</li> -->
						
<!-- 						<li onclick="openWindow('shengchan','生产')"> -->
<!-- 							<a class="list-img" href="#"> -->
<!-- 								<img src="${base}template/system/images/skinw8/shengchan.png" alt="" /> -->
<!-- 							</a> -->
<!-- 							<a class="left" href="#" title="生产"> -->
<!-- 								生产 -->
<!-- 							</a> -->
<!-- 						</li> -->
						
					</ul>
				 
					<div class="clear"></div>
						<div class="bqlogo"><a href="http://www.i-lingnet.com"  target="_blank"><img src="${base}template/system/images/skinw8/contbg.png" alt="" /></a></div>
				</div>
				<!-- 图标显示区域结束 -->
        </div>
    </div>
     <div showCollapseButton="true">
   <div class="rig_lm03">
      <div class="title"><img src="${base}template/system/images/listicon.jpg" class="icon" />
        <h2>待办事项</h2>
      </div>
      <div class="detail">
        <div class="inner03">
          <div id="tabCot_product" class="zhutitab">
          <div class="tabContainer">
              <ul class="tabHead" id="tabCot_product-li-currentBtn-">
                <li class="currentBtn"><a href="javascript:void(0)" title="待办事项" rel="1">待办事项</a></li>
              <li ><a href="javascript:void(0)" title="库存预警" rel="2">库存预警</a></li>
              </ul>
              <div class="clear"></div>
            </div>
          
            <div id="tabCot_product_1"  class="tabCot" style="width:100%;" >	
            <div class="mini-toolbar" style="width:100%;height:100%;border-bottom:0;padding:0px;">
            <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" 
				url="../../system/backend_role!treeshow.action" showPager="false">
	
				<div property="columns">
				<div field="mokuai" width="50" headerAlign="center" align="center">模块名</div>
				<div field="type" width="30" headerAlign="center" align="center">操作类型</div>
					<div field="tixing" width="80" headerAlign="center" align="center">提醒</div>
					
				</div>
	            </div>
	            </div>
            </div>
            
   
  <div id="tabCot_product_2" class="tabCot" style="width:100%;">	
  <div class="mini-toolbar"  style="width:100%;height:100%;border-bottom:0;padding:0px;">
            <div id="grid2" class="mini-datagrid" style="width:100%;height:100%;"
				url="" pageSize="20" >
				
			<div property="columns">
			 <div type="indexcolumn" width="40"  headerAlign="center" align="center"> 序列</div>
			<div field="itemCode" width="80" headerAlign="center" align="center">物料</div>
			<div field="lot" width="80" headerAlign="center" align="center">批次</div>
			<div field="ckdh" width="80" headerAlign="center" align="center">仓库</div>
			<div field="qty" width="60" headerAlign="center" align="center">仓库数量</div>
			<div field="minQty" width="80" headerAlign="center" align="center">仓库最小数量</div>
			<div field="maxQty" width="80" headerAlign="center" align="center">仓库最大数量</div>
			<div field="state" width="60" headerAlign="center" align="center">状态</div>
			</div>
            </div>
         </div>   
            </div>
            
            <script language="JavaScript" type="text/javascript" src="${base}template/system/js/tab.js"></script> 
          </div>
        </div>
      </div>
   </div>   
</div>
</div>    
    <script type="text/javascript">
        mini.parse();

        var tree = mini.get("tree1");
        var winW = $(window).height();
        var trueh=winW-80;
        $("#tabCot_product_1").css("height", trueh + "px");
        $("#tabCot_product_2").css("height", trueh + "px");
        var grid1 = mini.get("grid1");
        var grid2 = mini.get("grid2");
        grid1.hideColumn("idColumn");
        grid1.style.display = "block";
       grid1.load();
        grid2.load();
    
       $(window).resize(function() {
				var winW2 = $(window).height();
        var trueh1=winW2-80;
        $("#tabCot_product_1").css("height", trueh1 + "px");
        $("#tabCot_product_2").css("height", trueh1 + "px");
			});
	function fpdh(){
		var  url=grid1.getSelected().url;
		var ids=grid1.getSelected().ids;
		var title=grid1.getSelected().title;
		
		var tabs = window.parent.mini.get("mainTabs");
		 		var id = ids;
		        var tab = tabs.getTab(id);
		       if (!tab) {
	            tab = {};
	            tab.name = id;
	            tab.title = title;
	            tab.showCloseButton = true;
	           
	            tab.url = url;
	            tabs.addTab(tab);
	           
	            
	        }else{
	        	tabs.removeTab(tab);
	            tab = {};
	            tab.name = id;
	            tab.title = title;
	            tab.showCloseButton = true;
	           
	            tab.url = url;
	            tabs.addTab(tab);
	        }
        tabs.activeTab(tab);
		
		}
        //////////////////////////////////////////////
 function openWindow(urlname,name) {
 
 var tabs = window.parent.mini.get("mainTabs");

 var id = "add$"+urlname;
        var tab = tabs.getTab(id);
        if (!tab) {
            tab = {};
            tab.name = id;
            tab.title = name;
            tab.showCloseButton = true;
            tab.url = "${base}/jsp/icon/icon_"+urlname+".jsp";

            tabs.addTab(tab);
        }
        tabs.activeTab(tab);
    } 
       
    </script>

</body>
</html>--%>