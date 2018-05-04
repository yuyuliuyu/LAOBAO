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
    <script src="${base}template/system/js/jquery.min.js" type="text/javascript"></script>
    <script src="${base}template/system/js/jscarousel.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${base}template/system/css/zzsc.css" type="text/css" media="screen">
    
    <!--引入皮肤样式-->
    <link href="${base}template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="mini-toolbar" style="width:100%;height:100%; ">
  <div class="mini-splitter" style="width:86%;height:95%;float:right;margin-top:13px;margin-right:13px;margin-bottom:13px;" vertical="true" handlerSize="13">
    <div size="40%" showCollapseButton="false">
    <div class="overlay"><img src="${base}template/system/images/skinw8/caigou.png" alt="" /></div>
    <div id="wrapper">
<div id="jsCarousel">
	<div>
		<img src="${base}template/system/images/skinw8/caigou.png" alt="" /><br />
		<span class="thumbnail-text">采购</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/cangchu.png" alt="" /><br />
		<span class="thumbnail-text">仓储</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/haiguan.png" alt="" /><br />
		<span class="thumbnail-text">海关</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/zhijian.png" alt="" /><br />
		<span class="thumbnail-text">质检</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/chejian.png" alt="" /><br />
		<span class="thumbnail-text">车间</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/caiwu.png" alt="" /><br />
		<span class="thumbnail-text">财务</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/xitong.png" alt="" /><br />
		<span class="thumbnail-text">系统</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/yingshouyingfu.png" alt="" /><br />
		<span class="thumbnail-text">应收应付</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/xitongguanli.png" alt="" /><br />
		<span class="thumbnail-text">系统管理</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/zichan.png" alt="" /><br />
		<span class="thumbnail-text">资产</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/chengben.png" alt="" /><br />
		<span class="thumbnail-text">成本</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/jichu.png" alt="" /><br />
		<span class="thumbnail-text">基础</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/gongcheng.png" alt="" /><br />
		<span class="thumbnail-text">工程</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/xiaoshou.png" alt="" /><br />
		<span class="thumbnail-text">销售</span></div>
	<div>
		<img src="${base}template/system/images/skinw8/shengchan.png" alt="" /><br />
		<span class="thumbnail-text">生产</span></div>
	<div>
		<img src="images/img_16.jpg" /><br />
		<span class="thumbnail-text">Image Text</span></div>
</div>
<div id="demo-side-bar">
</div>
</div>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
</div>



    
    
 <!--  <ul class="list-icon">
						<li onclick="openWindow('caigou','采购')" id="li1">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/caigou.png" alt="" />
							</a>
							<a class="left" href="#" title="采购">
								采购
							</a>
						</li>

						<li onclick="openWindow('cangchu','仓储')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/cangchu.png" alt="" />
							</a>
							<a class="left" href="#" title="仓储">
								仓储
							</a>
						</li>

						 <li onclick="openWindow('haiguan','海关')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/haiguan.png" alt="" />
							</a>
							<a class="left" href="#" title="海关">
								海关
							</a>
						</li> 

						<li onclick="openWindow('zhijian','质检')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/zhijian.png" alt="" />
							</a>
							<a class="left" href="#" title="质检">
								质检
							</a>
						</li>

						 <li onclick="openWindow('chejian','车间')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/chejian.png" alt="" />
							</a>
							<a class="left" href="#" title="车间">
								车间
							</a>
						</li> 

						 <li onclick="openWindow('caiwu','财务')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/caiwu.png" alt="" />
							</a>
							<a class="left" href="#" title="财务">
								财务
							</a>
						</li> 

						 <li onclick="openWindow('xitong','系统')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/xitong.png" alt="" />
							</a>
							<a class="left" href="#" title="系统">
								系统
							</a>
						</li> 

						<li onclick="openWindow('yingshou','应收应付')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/yingshouyingfu.png" alt="" />
							</a>
							<a class="left" href="#" title="应收应付">
								应收应付
							</a>
						</li>
						
						<li onclick="openWindow('xitong','系统管理')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/xitongguanli.png" alt="" />
							</a>
							<a class="left" href="#" title="系统管理">
								系统管理
							</a>
						</li>
						
						  <li onclick="openWindow('zichan','资产')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/zichan.png" alt="" />
							</a>
							<a class="left" href="#" title="资产">
								资产
							</a>
						</li>  
						
						 <li onclick="openWindow('chengben','成本')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/chengben.png" alt="" />
							</a>
							<a class="left" href="#" title="成本">
								成本
							</a>
						</li> 
						
						<li onclick="openWindow('jichu','基础')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/jichu.png" alt="" />
							</a>
							<a class="left" href="#" title="基本">
								基础
							</a>
						</li>
						
						<li onclick="openWindow('gongcheng','工程')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/gongcheng.png" alt="" />
							</a>
							<a class="left" href="#" title="工程">
								工程
							</a>
						</li>
						
						<li onclick="openWindow('xiaoshou','销售')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/xiaoshou.png" alt="" />
							</a>
							<a class="left" href="#" title="销售">
								销售
							</a>
						</li>
						
						<li onclick="openWindow('shengchan','生产')">
							<a class="list-img" href="#">
								<img src="${base}template/system/images/skinw8/shengchan.png" alt="" />
							</a>
							<a class="left" href="#" title="生产">
								生产
							</a>
						</li>
						
					</ul>
    
      --> 
    
    
    
    
    </div>
    <div showCollapseButton="false">
    
    <div style="width:49%;height:100%;border-bottom:1px ;padding:2px;background:white;float:left">
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
    </div>
    
    <div style="width:49%;height:100%;border-bottom:1px ;padding:2px;background:white;float:right">
      <div id="tabCot_product_2" class="tabCot" style="width:100%;">	
  <div class="mini-toolbar"  style="width:100%;height:100%;border-bottom:0;padding:0px;">
            <div id="grid2" class="mini-datagrid" style="width:100%;height:100%;"
				url="../../locmgt/loc_stk_loc!search.action" pageSize="20" >
				
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
    </div>
    
    
        
    </div>        
</div>
   <div  class="mini-toolbar" style="width:11%;height:100%;border-bottom:2;padding:2px;">
    <ul>

    
        <li><div align="center"><img src="${base}template/system/images/skinw8/sm1.png"  /></div></li>
        <li ><div align="center">sdfsafd</div></li>
        <li ><div align="center"><a href="chuangyerencai/qr-zl.html"><img src="${base}template/system/images/skinw8/sm2.png"  /></a><span ><a href="chuangyerencai/qr-zl.html">　　风险管理</a></span></div></li>
        <li><div align="center"><a href="chuangyerencai/qr-zdj.html"><img src="${base}template/system/images/skinw8/sm3.png" /></a><span><a href="chuangyerencai/qr-zdj.html">　　质量管理</a></span></div></li>
        <li><div align="center"><a href="chuangyerencai/qr-he.html"><img src="${base}template/system/images/skinw8/sm4.png"  /></a><span><a href="chuangyerencai/qr-he.html ">　　人力资源</a></span></div></li>       
      </ul>
   </div>
   
 

    </div>
 
    <script type="text/javascript">
    $(document).ready(function() {
    	$('#jsCarousel').jsCarousel({ onthumbnailclick: function(src) { 
    	// 可在这里加入点击图片之后触发的效果
    	$("#overlay_pic").attr('src', src);
    	$(".overlay").show();
    	}, autoscroll: true });
    	
    	$(".overlay").click(function(){
    		$(this).hide();
    	});
    });
    
    
    
    
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
</html>











