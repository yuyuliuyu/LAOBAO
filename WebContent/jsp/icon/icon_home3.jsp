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
	.richeng,.tixing{overflow:hidden;}
	.images2{overflow:hidden;}
	 td
        {
            white-space: nowrap !important;
        }
</style>
<%--#remind_table tr:not(.tr1) td:nth-of-type(4){
		color:red
	} --%>
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
				
				<tr height="20px">
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
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('sell/sell_customer!list','我的客户')"><img src="image/wdkh.png"/></a></td>
					<td bgcolor="#FFFFFF" width="38px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('sell/client_common!list','客户公共池')"><img src="image/khggc.png"/></a></td>
					<td bgcolor="#FFFFFF" width="38px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('sell/team_remind!list','客户预检跟踪')"><img src="image/khyjgz.png"/></a></td>
					<td bgcolor="#FFFFFF" width="44px" ></td>
				</tr>
				<tr height="15px">
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
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('sell/create_combo!list','创建套餐')"><img src="image/cjtc.png"/></a></td>
					<td bgcolor="#FFFFFF" width="38px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('sell/create_order!list','订单制定')"><img src="image/ddzd.png"/></a></td>
					<td bgcolor="#FFFFFF" width="38px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('sell/sell_pact!list','销售合同维护')"><img src="image/xshtwh.png"/></a></td>
					<td bgcolor="#FFFFFF" width="44px" ></td>
				</tr>
				<tr height="15px">
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
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('sell/sell_date!list','销售同期')"><img src="image/xstq.png"/></a></td>
					<td bgcolor="#FFFFFF" width="38px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('sell/sell_target!list','销售目标')"><img src="image/xsmb.png"/></a></td>
					<td bgcolor="#FFFFFF" width="38px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('sell/finance_input!list','财务录入')"><img src="image/cwlr.png"/></a></td>
					<td bgcolor="#FFFFFF" width="44px" ></td>
				</tr>
				<tr height="15px">
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
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('sell/risk_client!list','高危人员管理')"><img src="image/gwrygl.png"/></a></td>
					<td bgcolor="#FFFFFF" width="38px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('sell/report_remind!list','体检报告待领取提醒')"><img src="image/tjbg.png"/></a></td>
					<td bgcolor="#FFFFFF" width="38px" ></td>
					<td bgcolor="#FFFFFF" class="imagesk" width="120px"><a href="#" onclick="openWindows('sell/healthy_statistics!list','销售团检统计')"><img src="image/xstjtj.png"/></a></td>
					<td bgcolor="#FFFFFF" width="44px" ></td>
				</tr>
				<tr height="15px">
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
				<table width="100%" border="0" cellpadding="0" cellspacing="0" id="sell_table" >
					<tr>
						<td class="rctp top" colspan="9"><div class="tix2">销售订单</div>
							<div class="more"><a href="javascript:openWindows('sell/create_order!list','订单制定');">+MORE>></a></div>
						</td>
					</tr>
					<tr>
						<td class="huei" align="center">序列</td>
						<td class="huei zhongj" align="center" width="8%">订单号</td>
						<td class="huei zhongj" align="center" width="20%">客户单位名称</td>
						<td class="huei zhongj" align="center" width="10%">状态</td>
						<td class="huei zhongj" align="center" width="10%">预算</td>
						<td class="huei zhongj" align="center" width="10%">优惠价</td>
						<td class="huei zhongj" align="center" width="10%">销售经理</td>
						<td class="huei zhongj" align="center" width="13%">分中心</td>
						<td class="huei zhongj" align="center" width="12%">体检计划日期</td>
					</tr>
					<tr class="bai">
						<td align="center" >1</td>
						<td id="ddh1" class="zhongj" align="center" ></td>
						<td id="ddlx1" class="zhongj" align="center" ></td>
						<td id="khmc1" class="zhongj" align="center" ></td>
						<td id="sqzt1" class="zhongj" align="center" ></td>
						<td id="je1" class="zhongj" align="center" ></td>
						<td id="fkzt1" class="zhongj" align="center" ></td>
						<td class="zhongj" align="center"></td>
						<td id="wlzt1" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >2</td>
						<td id="ddh2" class="zhongj" align="center" ></td>
						<td id="ddlx2" class="zhongj" align="center" ></td>
						<td id="khmc2" class="zhongj" align="center"></td>
						<td id="sqzt2" class="zhongj" align="center" ></td>
						<td id="je2" class="zhongj" align="center" ></td>
						<td id="fkzt2" class="zhongj" align="center" ></td>
						<td id="wlzt2" class="zhongj" align="center" ></td>
						<td class="zhongj" align="center"></td>
					</tr>
					<tr class="bai">
						<td align="center" >3</td>
						<td id="ddh3" class="zhongj" align="center" ></td>
						<td id="ddlx3" class="zhongj" align="center" ></td>
						<td id="khmc3" class="zhongj" align="center" ></td>
						<td id="sqzt3" class="zhongj" align="center" ></td>
						<td id="je3" class="zhongj" align="center" ></td>
						<td id="fkzt3" class="zhongj" align="center" ></td>
						<td id="wlzt3" class="zhongj" align="center" ></td>
						<td class="zhongj" align="center"></td>
					</tr>
					<tr class="lan">
						<td align="center" >4</td>
						<td id="ddh4" class="zhongj" align="center" ></td>
						<td id="ddlx4" class="zhongj" align="center" ></td>
						<td id="khmc4" class="zhongj" align="center" ></td>
						<td id="sqzt4" class="zhongj" align="center" ></td>
						<td id="je4" class="zhongj" align="center" ></td>
						<td id="fkzt4" class="zhongj" align="center" ></td>
						<td id="wlzt4" class="zhongj" align="center" ></td>
						<td class="zhongj" align="center"></td>
					</tr>
					<tr class="bai">
						<td align="center" >5</td>
						<td id="ddh5" class="zhongj" align="center" ></td>
						<td id="ddlx5" class="zhongj" align="center" ></td>
						<td id="khmc5" class="zhongj" align="center" ></td>
						<td id="sqzt5" class="zhongj" align="center" ></td>
						<td id="je5" class="zhongj" align="center" ></td>
						<td id="fkzt5" class="zhongj" align="center" ></td>
						<td id="wlzt5" class="zhongj" align="center" ></td>
						<td class="zhongj" align="center"></td>
					</tr>
					<tr class="lan">
						<td align="center" >6</td>
						<td id="ddh6" class="zhongj" align="center" ></td>
						<td id="ddlx6" class="zhongj" align="center" ></td>
						<td id="khmc6" class="zhongj" align="center" ></td>
						<td id="sqzt6" class="zhongj" align="center" ></td>
						<td id="je6" class="zhongj" align="center" ></td>
						<td id="fkzt6" class="zhongj" align="center" ></td>
						<td id="wlzt6" class="zhongj" align="center" ></td>
						<td class="zhongj" align="center"></td>
					</tr>
					<tr class="bai">
						<td align="center" >7</td>
						<td id="ddh1" class="zhongj" align="center" ></td>
						<td id="ddlx1" class="zhongj" align="center" ></td>
						<td id="khmc1" class="zhongj" align="center" ></td>
						<td id="sqzt7" class="zhongj" align="center" ></td>
						<td id="je1" class="zhongj" align="center" ></td>
						<td id="fkzt1" class="zhongj" align="center" ></td>
						<td id="wlzt1" class="zhongj" align="center" ></td>
						<td class="zhongj" align="center"></td>
					</tr>
					<tr class="lan">
						<td align="center" >8</td>
						<td id="ddh2" class="zhongj" align="center" ></td>
						<td id="ddlx2" class="zhongj" align="center" ></td>
						<td id="khmc2" class="zhongj" align="center" ></td>
						<td id="sqzt8" class="zhongj" align="center" ></td>
						<td id="je2" class="zhongj" align="center" ></td>
						<td id="fkzt2" class="zhongj" align="center" ></td>
						<td id="wlzt2" class="zhongj" align="center" ></td>
						<td class="zhongj" align="center"></td>
					</tr>
					<tr class="bai">
						<td align="center" >9</td>
						<td id="ddh1" class="zhongj" align="center" ></td>
						<td id="ddlx1" class="zhongj" align="center" ></td>
						<td id="khmc1" class="zhongj" align="center" ></td>
						<td id="sqzt" class="zhongj" align="center" ></td>
						<td id="je1" class="zhongj" align="center" ></td>
						<td id="fkzt1" class="zhongj" align="center" ></td>
						<td id="wlzt1" class="zhongj" align="center" ></td>
						<td class="zhongj" align="center"></td>
					</tr>
					<tr class="lan">
						<td align="center" >10</td>
						<td id="ddh2" class="zhongj" align="center" ></td>
						<td id="ddlx2" class="zhongj" align="center" ></td>
						<td id="khmc2" class="zhongj" align="center" ></td>
						<td id="sqzt" class="zhongj" align="center" ></td>
						<td id="je2" class="zhongj" align="center" ></td>
						<td id="fkzt2" class="zhongj" align="center" ></td>
						<td id="wlzt2" class="zhongj" align="center" ></td>
						<td class="zhongj" align="center"></td>
					</tr>
				</table>
				
			</div>
		</div>
		<div class="rili">
			<div class="tixing">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" id="remind_table" >
					<tr>
						<td class="rctp top" colspan="9"><div class="tix2">预检跟踪</div>
							<div class="more"><a href="javascript:openWindows('sell/team_remind!list','客户预检跟踪');">+MORE>></a></div>
						</td>
					</tr>
					<tr class="tr1">
						<td class="huei" align="center" width="10%">序列</td>
						<td class="huei zhongj" align="center" width="40%">客户单位名称</td>
						<td class="huei zhongj" align="center" width="30%">上次体检日期</td>
						<td class="huei zhongj" align="center" width="20%" >状态</td>
					</tr>
					<tr class="bai">
						<td align="center" >1</td>
						<td id="ddh1" class="zhongj" align="center" ></td>
						<td id="ddlx1" class="zhongj" align="center" ></td>
						<td id="khmc1" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >2</td>
						<td id="ddh2" class="zhongj" align="center" ></td>
						<td id="ddlx2" class="zhongj" align="center" ></td>
						<td id="khmc2" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >3</td>
						<td id="ddh1" class="zhongj" align="center" ></td>
						<td id="ddlx1" class="zhongj" align="center" ></td>
						<td id="khmc1" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >4</td>
						<td id="ddh2" class="zhongj" align="center" ></td>
						<td id="ddlx2" class="zhongj" align="center" ></td>
						<td id="khmc2" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >5</td>
						<td id="ddh1" class="zhongj" align="center" ></td>
						<td id="ddlx1" class="zhongj" align="center" ></td>
						<td id="khmc1" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >6</td>
						<td id="ddh2" class="zhongj" align="center" ></td>
						<td id="ddlx2" class="zhongj" align="center" ></td>
						<td id="khmc2" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >7</td>
						<td id="ddh1" class="zhongj" align="center" ></td>
						<td id="ddlx1" class="zhongj" align="center" ></td>
						<td id="khmc1" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >8</td>
						<td id="ddh2" class="zhongj" align="center" ></td>
						<td id="ddlx2" class="zhongj" align="center" ></td>
						<td id="khmc2" class="zhongj" align="center" ></td>
					</tr>
					<tr class="bai">
						<td align="center" >9</td>
						<td id="ddh1" class="zhongj" align="center" ></td>
						<td id="ddlx1" class="zhongj" align="center" ></td>
						<td id="khmc1" class="zhongj" align="center" ></td>
					</tr>
					<tr class="lan">
						<td align="center" >10</td>
						<td id="ddh2" class="zhongj" align="center" ></td>
						<td id="ddlx2" class="zhongj" align="center" ></td>
						<td id="khmc2" class="zhongj" align="center" ></td>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="clear"></div>
		</div>
		<div class="clear"></div>
		<div class="clear KK" ></div>
	</div>
</body>
<script type="text/javascript">
	$().ready(function() {
		$.ajax({
			type:'post',
			url:'../../sell/create_order!getHomeData.action',
			success:function(text){
				if(text){
					var data=JSON.parse(text);
					for(var r=0;r<data.length;r++){
						var row=data[r];
						$("#sell_table tr:eq("+(r+2)+") td:eq("+1+")").html(row.ddh);
						$("#sell_table tr:eq("+(r+2)+") td:eq("+2+")").html(row.khdwmc);
						$("#sell_table tr:eq("+(r+2)+") td:eq("+3+")").html(row.spzt);
						$("#sell_table tr:eq("+(r+2)+") td:eq("+4+")").html(row.ddjg);
						$("#sell_table tr:eq("+(r+2)+") td:eq("+5+")").html(row.ddyhj);
						$("#sell_table tr:eq("+(r+2)+") td:eq("+6+")").html(row.sell);
						$("#sell_table tr:eq("+(r+2)+") td:eq("+7+")").html(row.fzxs);
						$("#sell_table tr:eq("+(r+2)+") td:eq("+8+")").html(row.date);
					}
				}
			}
		});
		$.ajax({
			type:'post',
			url:'../../sell/team_remind!getHomeData.action',
			success:function(text){
				if(text){
					var data=JSON.parse(text);
					for(var r=0;r<data.length;r++){
						var row=data[r];
						$("#remind_table tr:eq("+(r+2)+") td:eq("+1+")").html(row.khdwmc);
						$("#remind_table tr:eq("+(r+2)+") td:eq("+2+")").html(row.scjcrq);
						$("#remind_table tr:eq("+(r+2)+") td:eq("+3+")").html(row.zt);
					}
				}
			}
		});
	}); 

	 function openWindows(urlname,name) {
		 var tabs = window.parent.mini.get("mainTabs");
		 var id = "tab$"+name;
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
<script>
var x=$(window).height();
x=x-20
$(".images2").css("height",x+"px");
$(".tixing").css("height",(x-10)/2+"px");
$(".richeng").css("height",x+"px");
</script>
</html>
