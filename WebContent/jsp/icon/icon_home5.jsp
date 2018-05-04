<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>首页</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link href="yslj.css" rel="stylesheet" type="text/css" />
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}template/fusioncharts/js/fusioncharts.js"></script>
<script type="text/javascript" src="${base}template/fusioncharts/js/themes/fusioncharts.theme.fint.js"></script>
<script type="text/javascript" src="${base}template/fusioncharts/js/fusioncharts.charts.js"></script>
   <%--  <script src="${base}template/miniui/boot.js" type="text/javascript"></script> --%>
        <script src="../../template/plugin/print/LodopFuncs.js" type="text/javascript"></script>
        <script src="../../template/plugin/webuploader/webupload/jquery.js"></script>
        
        <style>
        .richeng,.tixing{ height:334px;}
        </style>
</head>
<body>
	<div class="yskj">
		<div class="richeng" id="richeng">
			<table class="images2" width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<th class="rctp" colspan="7" ><div class="richengtp">快捷操作
						</div>
						<div class="more">
							<!--<a href="javascript:openWindow('salesmgt/sales_act', 'saleaction','销售行动');">+MORE>></a>-->
						</div>
					</th>
				</tr>
				<tr height="40px">
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF" width="44px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('base/customer_personal_info!customerPersonalInfoList','个人客户')"><img src="image/khlr.png"/></a></td>
					<td bgcolor="#FFFFFF" width="38px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="javascript:openminiwebm();"><img src="image/yrzs.png"/></a></td>
					<td bgcolor="#FFFFFF" width="38px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('sales/so_xsdd!add','销售订单')"><img src="image/ddsc.png"/></a></td>
					<td bgcolor="#FFFFFF" width="44px" ></td>
				</tr>
				<tr height="40px">
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF" width="44px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('base/account_info!accountInfoList','账户信息')"><img src="image/zhye.png"/></a></td>
					<td bgcolor="#FFFFFF" width="38px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('stock/kc_crk!chk','出库管理')"><img src="image/ckgl.png"/></a></td>
					<td bgcolor="#FFFFFF" width="38px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('aftsales/deliv_query!list','物流管理')"><img src="image/wlgl.png"/></a></td>
					<td bgcolor="#FFFFFF" width="44px" ></td>
				</tr>
				<tr height="40px">
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
					<td bgcolor="#FFFFFF"></td>
				</tr>
			</table>
			<!-- <div class="option">
				<ul>
				<li><a href=""><img src="image/khlr.png"/></a></li>
				<li><a href=""><img src="image/yrzs.png"/></a></li>
				<li><a href=""><img src="image/ddsc.png"/></a></li>
				<li><a href=""><img src="image/zhye.png"/></a></li>
				<li><a href=""><img src="image/ckgl.png"/></a></li>
				<li><a href=""><img src="image/wlgl.png"/></a></li>
				</ul>
			</div>
			 -->
		</div>
		<div class="rili">
			<div class="tixing">
			<%

 //   List list = (List)request.getAttribute("list");
   
%>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="rctp top" colspan="7"><div class="tix2">新增订单列表(降序)</div>
							<div class="more"><a href="javascript:openWindows('sales/so_xsdd!list','销售订单');">+MORE>></a></div>
						</td>
					</tr>
					<tr>
						<td class="huei" align="center">序列</td>
						<td class="huei zhongj" align="center" width="23%">订单号</td>
						<td class="huei zhongj" align="center" width="13%">订单类型</td>
						<td class="huei zhongj" align="center" width="19%">客户名称</td>
						<td class="huei zhongj" align="center" width="15%">金额</td>
						<td class="huei zhongj" align="center" width="12%">付款状态</td>
						<td class="huei zhongj" align="center" width="12%">物流状态</td>
					</tr>
					<tr class="bai">
						<td align="center" >1</td>
						<td id="ddh1" class="zhongj" align="center" ></td>
						<td id="ddlx1" class="zhongj" align="center" ></td>
						<td id="khmc1" class="zhongj" align="center" ></td>
						<td id="je1" class="zhongj" align="center" ></td>
						<td id="fkzt1" class="zhongj" align="center" ></td>
						<td id="wlzt1" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >2</td>
						<td id="ddh2" class="zhongj" align="center" ></td>
						<td id="ddlx2" class="zhongj" align="center" ></td>
						<td id="khmc2" class="zhongj" align="center" ></td>
						<td id="je2" class="zhongj" align="center" ></td>
						<td id="fkzt2" class="zhongj" align="center" ></td>
						<td id="wlzt2" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >3</td>
						<td id="ddh3" class="zhongj" align="center" ></td>
						<td id="ddlx3" class="zhongj" align="center" ></td>
						<td id="khmc3" class="zhongj" align="center" ></td>
						<td id="je3" class="zhongj" align="center" ></td>
						<td id="fkzt3" class="zhongj" align="center" ></td>
						<td id="wlzt3" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >4</td>
						<td id="ddh4" class="zhongj" align="center" ></td>
						<td id="ddlx4" class="zhongj" align="center" ></td>
						<td id="khmc4" class="zhongj" align="center" ></td>
						<td id="je4" class="zhongj" align="center" ></td>
						<td id="fkzt4" class="zhongj" align="center" ></td>
						<td id="wlzt4" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >5</td>
						<td id="ddh5" class="zhongj" align="center" ></td>
						<td id="ddlx5" class="zhongj" align="center" ></td>
						<td id="khmc5" class="zhongj" align="center" ></td>
						<td id="je5" class="zhongj" align="center" ></td>
						<td id="fkzt5" class="zhongj" align="center" ></td>
						<td id="wlzt5" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td style="border-bottom:0;" align="center" >6</td>
						<td style="border-bottom:0;" id="ddh6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="ddlx6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="khmc6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="je6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="fkzt6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="wlzt6" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >7</td>
						<td id="ddh1" class="zhongj" align="center" ></td>
						<td id="ddlx1" class="zhongj" align="center" ></td>
						<td id="khmc1" class="zhongj" align="center" ></td>
						<td id="je1" class="zhongj" align="center" ></td>
						<td id="fkzt1" class="zhongj" align="center" ></td>
						<td id="wlzt1" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >8</td>
						<td id="ddh2" class="zhongj" align="center" ></td>
						<td id="ddlx2" class="zhongj" align="center" ></td>
						<td id="khmc2" class="zhongj" align="center" ></td>
						<td id="je2" class="zhongj" align="center" ></td>
						<td id="fkzt2" class="zhongj" align="center" ></td>
						<td id="wlzt2" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >9</td>
						<td id="ddh1" class="zhongj" align="center" ></td>
						<td id="ddlx1" class="zhongj" align="center" ></td>
						<td id="khmc1" class="zhongj" align="center" ></td>
						<td id="je1" class="zhongj" align="center" ></td>
						<td id="fkzt1" class="zhongj" align="center" ></td>
						<td id="wlzt1" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >10</td>
						<td id="ddh2" class="zhongj" align="center" ></td>
						<td id="ddlx2" class="zhongj" align="center" ></td>
						<td id="khmc2" class="zhongj" align="center" ></td>
						<td id="je2" class="zhongj" align="center" ></td>
						<td id="fkzt2" class="zhongj" align="center" ></td>
						<td id="wlzt2" class="zhongj" align="center" ></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="clear"></div>
		<div class="zuij">
			<div class="richeng" id="richeng">
				<table class="xstj" width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="rctp" colspan="4"><div class="zuijinbt">销售统计(最近一个月)</div>
							<div class="more">
								<a href="javascript:openWindows('sales/so_xstj!lamblist','销售统计');">+MORE>></a>
							</div>
						</td>                                    
					</tr>
					<tr>
						<td class="huei" align="center" width="10%">序列</td>
						<td class="huei zhongj" align="center" width="30%">羊肉品种</td>
						<td class="huei zhongj" align="center" width="30%">销售数量</td>
						<td class="huei zhongj" align="center" width="30%">销售金额</td>
					</tr>
					<tr class="bai">
						<td align="center" >1</td>
						<td id="pzName1" class="zhongj" align="center" ></td>
						<td id="qtym1" class="zhongj" align="center" ></td>
						<td id="totalm1" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >2</td>
						<td id="pzName2" class="zhongj" align="center" ></td>
						<td id="qtym2" class="zhongj" align="center" ></td>
						<td id="totalm2" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >3</td>
						<td id="pzName3" class="zhongj" align="center" ></td>
						<td id="qtym3" class="zhongj" align="center" ></td>
						<td id="totalm3" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >4</td>
						<td id="pzName4" class="zhongj" align="center" ></td>
						<td id="qtym4" class="zhongj" align="center" ></td>
						<td id="totalm4" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >5</td>
						<td id="pzName5" class="zhongj" align="center" ></td>
						<td id="qtym5" class="zhongj" align="center" ></td>
						<td id="totalm5" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td style="border-bottom:0;" align="center" >6</td>
						<td style="border-bottom:0;" id="pzName6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="qtym6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="totalm6" class="zhongj" align="center" ></td>
						
					</tr>
					<tr class="bai">
						<td align="center" >7</td>
						<td id="pzName3" class="zhongj" align="center" ></td>
						<td id="qtym3" class="zhongj" align="center" ></td>
						<td id="totalm3" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >8</td>
						<td id="pzName4" class="zhongj" align="center" ></td>
						<td id="qtym4" class="zhongj" align="center" ></td>
						<td id="totalm4" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >9</td>
						<td id="pzName5" class="zhongj" align="center" ></td>
						<td id="qtym5" class="zhongj" align="center" ></td>
						<td id="totalm5" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td style="border-bottom:0;" align="center" >10</td>
						<td style="border-bottom:0;" id="pzName6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="qtym6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="totalm6" class="zhongj" align="center" ></td>
						
					</tr>
					
				</table>
			</div>
			<div class="rili">
				<div class="tixing">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="rctp top" colspan="6"><div class="tix">库存状态&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         </div>
								<div class="more">
								<a href="javascript:openWindows('stock/loc_stk!list','库存查询');">+MORE>></a>
							</div>
							</td>
						</tr>
						<tr>
						<td class="huei" align="center" width="6%">序列</td>
						<td class="huei zhongj" align="center" width="20%">羊肉品种</td>
						<td class="huei zhongj" align="center" width="20%">羊肉规格</td>
						<td class="huei zhongj" align="center" width="20%">仓库名称</td>
						<td class="huei zhongj" align="center" width="18%">实际库存</td>
						<td class="huei zhongj" align="center" width="18%">可用库存</td>
					</tr>
					<tr class="bai">
						<td align="center" >1</td>
						<td id="pzhao1" class="zhongj" align="center" ></td>
						<td id="ggName1" class="zhongj" align="center" ></td>
						<td id="ckName1" class="zhongj" align="center" ></td>
						<td id="leftQty1" class="zhongj" align="center" ></td>
						<td id="suoQty1" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >2</td>
						<td id="pzhao2" class="zhongj" align="center" ></td>
						<td id="ggName2" class="zhongj" align="center" ></td>
						<td id="ckName2" class="zhongj" align="center" ></td>
						<td id="leftQty2" class="zhongj" align="center" ></td>
						<td id="suoQty2" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >3</td>
						<td id="pzhao3" class="zhongj" align="center" ></td>
						<td id="ggName3" class="zhongj" align="center" ></td>
						<td id="ckName3" class="zhongj" align="center" ></td>
						<td id="leftQty3" class="zhongj" align="center" ></td>
						<td id="suoQty3" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >4</td>
						<td id="pzhao4" class="zhongj" align="center" ></td>
						<td id="ggName4" class="zhongj" align="center" ></td>
						<td id="ckName4" class="zhongj" align="center" ></td>
						<td id="leftQty4" class="zhongj" align="center" ></td>
						<td id="suoQty4" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >5</td>
						<td id="pzhao5" class="zhongj" align="center" ></td>
						<td id="ggName5" class="zhongj" align="center" ></td>
						<td id="ckName5" class="zhongj" align="center" ></td>
						<td id="leftQty5" class="zhongj" align="center" ></td>
						<td id="suoQty5" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td style="border-bottom:0;" align="center" >6</td>
						<td style="border-bottom:0;" id="pzhao6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="ggName6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="ckName6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="leftQty6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="suoQty6" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >7</td>
						<td id="pzhao3" class="zhongj" align="center" ></td>
						<td id="ggName3" class="zhongj" align="center" ></td>
						<td id="ckName3" class="zhongj" align="center" ></td>
						<td id="leftQty3" class="zhongj" align="center" ></td>
						<td id="suoQty3" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >8</td>
						<td id="pzhao4" class="zhongj" align="center" ></td>
						<td id="ggName4" class="zhongj" align="center" ></td>
						<td id="ckName4" class="zhongj" align="center" ></td>
						<td id="leftQty4" class="zhongj" align="center" ></td>
						<td id="suoQty4" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >9</td>
						<td id="pzhao5" class="zhongj" align="center" ></td>
						<td id="ggName5" class="zhongj" align="center" ></td>
						<td id="ckName5" class="zhongj" align="center" ></td>
						<td id="leftQty5" class="zhongj" align="center" ></td>
						<td id="suoQty5" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td style="border-bottom:0;" align="center" >10 </td>
						<td style="border-bottom:0;" id="pzhao6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="ggName6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="ckName6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="leftQty6" class="zhongj" align="center" ></td>
						<td style="border-bottom:0;" id="suoQty6" class="zhongj" align="center" ></td>
					</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="clear"></div>
		<div class="clear KK" ></div>
	</div>
</body>
<script type="text/javascript">

$.ajax({
	dataType: "json",
    url : "../../sales/so_xsdd!treeshow.action",
		type:"GET",  
  
    success : function(respons) {
     

        	
        	//alert(respons.data.length);
        	var ss=7;
        	if(ss>respons.data.length+1){
        	ss=respons.data.length+1;
        	}
        	for(var i=1;i<ss;i++){
        	document.getElementById("ddh"+i).innerHTML = respons.data[i-1].code;
        	document.getElementById("ddlx"+i).innerHTML = respons.data[i-1].ddlx;
        	if(respons.data[i-1].custName.length>10){
        	var namem=respons.data[i-1].custName;
        	var named= namem.substr(0, 10);
        	document.getElementById("khmc"+i).innerHTML = named+"...";
        	}else{
        	document.getElementById("khmc"+i).innerHTML = respons.data[i-1].custName;
        	}
        	document.getElementById("je"+i).innerHTML = respons.data[i-1].totalMoney;
        	document.getElementById("fkzt"+i).innerHTML = respons.data[i-1].shoukuan;
        	document.getElementById("wlzt"+i).innerHTML = respons.data[i-1].wuliu;
        	}
    
    },
	error:
			function(XMLHttpRequest, textStatus, errorThrown){
		
		}
});


$.ajax({
	dataType: "json",
    url : "../../sales/so_xstj!lambShow.action",
		type:"GET",  
  
    success : function(respons) {
     

        	
        	
        	var ss=7;
        	if(ss>respons.data.length+1){
        	ss=respons.data.length+1;
        	}
        	
        	for(var i=1;i<ss;i++){
        	//document.getElementById("xh2"+i).innerHTML = i;
        	
        	document.getElementById("pzName"+i).innerHTML = respons.data[i-1].pzName;
        	document.getElementById("qtym"+i).innerHTML = respons.data[i-1].qty;
        	document.getElementById("totalm"+i).innerHTML = respons.data[i-1].money;
        	
        	}
    
    },
	error:
			function(XMLHttpRequest, textStatus, errorThrown){
		
		}
});





$.ajax({
	dataType: "json",
    url : "../../stock/loc_stk!listAll.action",
		type:"GET",  
  
    success : function(respons) {
     

        	
        	//alert(respons.data.length);
        	var ss=7;
        	if(ss>respons.data.length+1){
        	ss=respons.data.length+1;
        	}
        	for(var i=1;i<ss;i++){
        	document.getElementById("pzhao"+i).innerHTML = respons.data[i-1].pzName;
        	
        	if(respons.data[i-1].ggName==null){
        	
        	}else{
        	document.getElementById("ggName"+i).innerHTML = respons.data[i-1].ggName;
        }
        	document.getElementById("ckName"+i).innerHTML = respons.data[i-1].ckName;
        	document.getElementById("leftQty"+i).innerHTML = respons.data[i-1].qty;
        	document.getElementById("suoQty"+i).innerHTML = respons.data[i-1].leftQty;
        	
        	}
    
    },
	error:
			function(XMLHttpRequest, textStatus, errorThrown){
		
		}
});

	$().ready(function() {
		/* // 加载客户增长曲线
		$.ajax({
	    	url : "../../baseinfo/home!findCustomerChart.action",
			success : function(json) {
				if(json!=null){
					var cdata = json.split(",");
					var customerdata=[ {"label" : "一月", "value" : cdata[0]
								}, {"label" : "二月", "value" : cdata[1]
								}, {"label" : "三月", "value" : cdata[2]
								}, {"label" : "四月", "value" : cdata[3]
								}, {"label" : "五月", "value" : cdata[4]
								}, {"label" : "六月", "value" : cdata[5]
								}, {"label" : "七月", "value" : cdata[6]
								}, {"label" : "八月", "value" : cdata[7]
								}, {"label" : "九月", "value" : cdata[8]
								}, {"label" : "十月", "value" : cdata[9]
								}, {"label" : "十一月", "value" : cdata[10]
								}, {"label" : "十二月", "value" : cdata[11]	}];
					chart({divid:'customerchart',yname:'客户增长人数',data:customerdata});
				}
			}
		});
		// 加载订单增长曲线
		$.ajax({
	    	url : "../../baseinfo/home!findOrderChart.action",
			success : function(json) {
				if(json!=null){
					var cdata = json.split(",");
					var orderdata=[ {"label" : "一月", "value" : cdata[0]
								}, {"label" : "二月", "value" : cdata[1]
								}, {"label" : "三月", "value" : cdata[2]
								}, {"label" : "四月", "value" : cdata[3]
								}, {"label" : "五月", "value" : cdata[4]
								}, {"label" : "六月", "value" : cdata[5]
								}, {"label" : "七月", "value" : cdata[6]
								}, {"label" : "八月", "value" : cdata[7]
								}, {"label" : "九月", "value" : cdata[8]
								}, {"label" : "十月", "value" : cdata[9]
								}, {"label" : "十一月", "value" : cdata[10]
								}, {"label" : "十二月", "value" : cdata[11]	}];
					chart({divid:'orderchart',yname:'订单增长个数',data:orderdata});
				}
			}
		});	
	    // 加载最近六条销售行动记录
	    $.ajax({
	    	url : "../../baseinfo/home!findZuijinxd.action",
			success : function(resultxd) {
				if(resultxd!=null){
					var results = resultxd.split("$%$");
					var zuijinxdData = "";
					var i = 0;
					var $vs = $("#zuijinxd");
					while (i < results.length - 1) {
						zuijinxdData = results[i++].split("%@%");
						$vs.children().eq(0).text(zuijinxdData[0]);
						$vs.children().eq(1).html("<a class=\"findzuijinxd\" xdid="+zuijinxdData[2] +
								" xdtype="+zuijinxdData[3]+" href='#'>"+ zuijinxdData[1] + "</a>");
						$vs = $vs.next();
					}
				}
			}
		});
	    // 加载最近六条订单记录
	    $.ajax({
	    	url : "../../baseinfo/home!findZuijindt.action",
			success : function(result) {
				if(result!=null){
					var results = result.split("$%$");
					var zuijindtData = "";
					var i = 0;
					var $vs = $("#zuijinbtid");
					while (i < results.length - 1) {
						zuijindtData = results[i++].split("%@%");
						$vs.children().eq(0).text(zuijindtData[0]);
						$vs.children().eq(1).html("<a class=\"findzuijindt\" dtid="+zuijindtData[2]+" href='#'>"
								+ zuijindtData[1] + "</a>");
						$vs = $vs.next();
					}
				}
			}
		});
	    // 加载最近六条客户记录
	    $.ajax({
	    	url : "../../baseinfo/home!findZuijincust.action",
			success : function(result) {
				if(result!=null){
					var results = result.split("$%$");
					var zuijincustData = "";
					var i = 0;
					var $vs = $("#zuijincustid");
					while (i < results.length - 1) {
						zuijincustData = results[i++].split("%@%");
						$vs.children().eq(0).text(zuijincustData[0]);
						$vs.children().eq(1).html("<a class=\"findzuijincust\" custid="+zuijincustData[2] +
								" custtp="+zuijincustData[3]+" href='#'>"+ zuijincustData[1] + "</a>");
						$vs = $vs.next();
					}
				}
			}
		});
	    // 加载最近六条下单客户记录
	    $.ajax({
	    	url : "../../baseinfo/home!findZuijinget.action",
			success : function(resultget) {
				if(resultget!=null){
					var resultsget = resultget.split("$%$");
					var zuijingetData = "";
					var i = 0;
					var $vs = $("#zuijinget");
					while (i < resultsget.length - 1) {
						zuijingetData = resultsget[i++].split("%@%");
						$vs.children().eq(0).html("<a class=\"findzuijinget\" custid="+zuijingetData[0]+
								" custtp="+zuijingetData[2]+" href='#'>" + zuijingetData[3] + "</a>");
						$vs.children().eq(1).text(zuijingetData[2]);
						$vs.children().eq(2).text(zuijingetData[1]);
						$vs = $vs.next();
					}
				}
			}
		});
	    // 加载最近六条转入公共客户池的客户数据
	    $.ajax({
	    	url : "../../baseinfo/home!findZuijinpub.action",
			success : function(resultpub) {
				if(resultpub!=null){
					var results = resultpub.split("$%$");
					var zuijinpubData = "";
					var i = 0;
					var $vs = $("#zuijinpub");
					while (i < results.length - 1) {
						zuijinpubData = results[i++].split("%@%");
						$vs.children().eq(0).html("<a class=\"findzuijinpub\" custid="+zuijinpubData[0]+
								" custtp="+zuijinpubData[2]+" href='#'>" + zuijinpubData[3] + "</a>");
						$vs.children().eq(1).text(zuijinpubData[2]);
						$vs.children().eq(2).text(zuijinpubData[1]);
						$vs = $vs.next();
					}
				}
			}
		});*/
	}); 

	 function openWindows(urlname,name) {
		 
		 var tabs = window.parent.mini.get("mainTabs");

		 var id = "add$"+urlname;
		        var tab = tabs.getTab(id);
		        if (!tab) {
		            tab = {};
		            tab.name = id;
		            tab.title = name;
		            tab.showCloseButton = true;
		            tab.url = "${base}/../"+urlname+".action";
	

		            tabs.addTab(tab);
		        }
		        tabs.activeTab(tab);
		    } 
	 function openminiwebm(){

			
			mini.open({
		        url: "${base}/../rspect/lyzs!list.action",
		        showMaxButton: false,
		        title: "来源追溯",
		        width: 600,
		        height: 200,
		        ondestroy: function (action) {

		        }
		    	});

		}
	function chart(param) {
		var chartWidth = '100%',
	    chartHeight = '180',
	    dataType = 'json';
		var dailyCSatChart = new FusionCharts(
			{
				type : 'line',
				renderAt : param.divid,
				width : chartWidth,
				height : chartHeight,
				dataFormat : dataType,
				dataSource : {
					"chart" : {
						"xaxisname" : "月份",
						"yaxisname" : param.yname,
						"yaxismaxvalue" : "5",
						"showvalues" : "0",
						"theme" : "fint"
					},
					"data" : param.data
				}
			}).render();
	}

	window.onload = function(){
	}
	/*
	// 增加跳至销售行动详细页的响应事件
	$(".findzuijinxd").live("click",function showActXd(e){
		var xdid = $(this).attr("xdid");
		var actvtType =  $(this).attr("xdtype");
		var tabs = window.parent.mini.get("mainTabs");
		var id = "add$行动详细画面";
		var tab = tabs.getTab(id);
		if (tab) {
			tabs.removeTab(tab);
		}
		tab = {};
		tab.name = id;
		tab.title = "查看待办事项";
		tab.showCloseButton = true;
		tab.url = "../salesmgt/sales_act!view.action?id="+xdid+"&typeFlag="+actvtType;
		tabs.addTab(tab);
		tabs.activeTab(tab);
	});
	
	// 增加跳至订单详细页的响应事件
	$(".findzuijindt").live("click",function showActDt(e){
		var v_id = $(this).attr("dtid");
		var tabs = window.parent.mini.get("mainTabs");
 		var id = "add订单详细";
        var tab = tabs.getTab(id);
        if (tab) {
         tabs.removeTab(tab);
        }
        tab = {};
            tab.name = id;
            tab.title = "查看订单详细";
            tab.showCloseButton = true; 
            tab.url = "../order/order!record.action?id="+v_id;
            tabs.addTab(tab);
        tabs.activeTab(tab);	
	});
	
	// 增加跳至客户详细页的响应事件
	$(".findzuijinget").live("click",function showActCust(e){
		var custid = $(this).attr("custid");
		var custtypeid = $(this).attr("custtp");
        var checkName = "";
        var checkTitle = "";
		if (custtypeid == "企业客户") {
			checkName = "companyCheck";
			checkTitle = "查看企业客户信息";
		}
		if (custtypeid == "政府及事业单位客户") {
			checkName = "officeCheck";
			checkTitle = "查看政府及事业单位信息";
		}
		if (custtypeid == "个人客户") {
			checkName = "personCheck";
			checkTitle = "查看个人客户信息";
		}
		var tabs = window.parent.mini.get("mainTabs");
		var tabid = "add$查看" + custid;
		var tab = tabs.getTab(tabid);
		if (tab) {
			tabs.removeTab(tab);
		}
		tab = {};
		tab.name = tabid;
		tab.title = checkTitle;
		tab.showCloseButton = true;
		tab.url = "../customermgt/customer_info!" + checkName + ".action?id=" + custid + "&show=true", tabs.addTab(tab);
		tabs.activeTab(tab);
	});
	
	// 增加跳至客户详细页的响应事件
	$(".findzuijincust").live("click",function showActCust(e){
		var custid = $(this).attr("custid");
		var custtypeid = $(this).attr("custtp");
        var checkName = "";
        var checkTitle = "";
		if (custtypeid == "企业客户") {
			checkName = "companyCheck";
			checkTitle = "查看企业客户信息";
		}
		if (custtypeid == "政府及事业单位客户") {
			checkName = "officeCheck";
			checkTitle = "查看政府及事业单位信息";
		}
		if (custtypeid == "个人客户") {
			checkName = "personCheck";
			checkTitle = "查看个人客户信息";
		}
		var tabs = window.parent.mini.get("mainTabs");
		var tabid = "add$查看" + custid;
		var tab = tabs.getTab(tabid);
		if (tab) {
			tabs.removeTab(tab);
		}
		tab = {};
		tab.name = tabid;
		tab.title = checkTitle;
		tab.showCloseButton = true;
		tab.url = "../customermgt/customer_info!" + checkName + ".action?id=" + custid + "&show=true", tabs.addTab(tab);
		tabs.activeTab(tab);
	});
	
	// 增加跳至客户池客户详细页的响应事件
	$(".findzuijinpub").live("click",function showActPub(e){
		var custid = $(this).attr("custid");
		var custtypeid = $(this).attr("custtp");
        var checkName = "";
        var checkTitle = "";
		if (custtypeid == "企业客户") {
			checkName = "companyCheck";
			checkTitle = "查看企业客户信息";
		}
		if (custtypeid == "政府及事业单位客户") {
			checkName = "officeCheck";
			checkTitle = "查看政府及事业单位信息";
		}
		if (custtypeid == "个人客户") {
			checkName = "personCheck";
			checkTitle = "查看个人客户信息";
		}
		var tabs = window.parent.mini.get("mainTabs");
		var tabid = "add$查看" + custid;
		var tab = tabs.getTab(tabid);
		if (tab) {
			tabs.removeTab(tab);
		}
		tab = {};
		tab.name = tabid;
		tab.title = checkTitle;
		tab.showCloseButton = true;
		tab.url = "../customermgt/cust_pool!" + checkName + ".action?id=" + custid + "&show=true", tabs.addTab(tab);
		tabs.activeTab(tab);
	});*/
	
	// 更多信息响应
	function openWindow(urlname, resourcename ,name) {
		var tabs = window.parent.parent.mini.get("mainTabs");
		// id为"tab$"加上qx_resource内对应resourcename的值
		var id = "tab$" + resourcename;
		var tab = tabs.getTab(id);
		if (tab) {
			tabs.removeTab(tab);
		}
		tab = {};
		tab.name = id;
		tab.title = name;
		tab.showCloseButton = true;
		tab.url = "${base}" + urlname + "!list.action";
		tabs.addTab(tab);
		tabs.activeTab(tab);
	}
</script>
</html>
