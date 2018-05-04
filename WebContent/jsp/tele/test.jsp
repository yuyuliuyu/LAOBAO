<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="../includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<title>电话费导入</title>
	<style type="text/css">
		body{
			margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
		}
	</style>
	<script src="${base}/template/miniui/boot.js" type="text/javascript"></script>
	<script src="${base}/template/system/js/cklist.js" type="text/javascript"></script>
	<link href="${base}/template/system/css/base.css" rel="stylesheet" type="text/css" />
	<link href="${base}/template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="mini-fit" >
		<div class="mini-splitter" style="width:100%;height:100%;">
		
<%-- 			<div size="160" showCollapseButton="true">
				<!-- ckId -->
				<input type="hidden" value="${ckId}" id="ckId" />
				<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
					<span style="padding-left:5px;">仓库名称：</span>
				</div>
				<div class="mini-fit" style=" border-top:0;">
					<ul id="cktree" class="mini-tree" url="../depart/warehouse!treedata.action" style="width:100%;"
						showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" 
						expandOnLoad="true" >
					</ul>
				</div>
			</div>
 --%>
			<div showCollapseButton="false">
				<div style="width:100%;">
					<div class="mini-toolbar" style="padding:0px;border-top:0;">
						<table style="width:100%;">
							<tr>
							<td >
								 部门名称：            
								<input id="department" name="department" class="mini-textbox" emptyText="" onkeydown="onKeyEnter"/>
							</td>
							<td >
								电话号码：            
								<input id="phonenumber" name="phonenumber" class="mini-textbox" emptyText="" onkeydown="onKeyEnter"/>
							</td>
<!-- 							<td >
								客户： 
								<input id="cargoOwner" name="cargoOwner" class="mini-textbox" emptyText="" onkeydown="onKeyEnter"/>
							</td> -->
							<td style="white-space:nowrap;">
								<a class="mini-button" iconCls="icon-search" id="searchButton"
								onclick="search('department,phonenumber','department,phonenumber')">查询</a>
							</td>
						</tr>
					</table>
					</div>
				</div>
			
				<div class="mini-toolbar" style="spadding:0px;border-top:0;">
					<table style="width:100%;">
						<tr>
							<td style="width:100%;">
						 			<a class="mini-button" iconCls="icon-upload" onclick="upload('','futures','futuresCd')">导入</a>


								 <tags:hasPerm value="/futures/futures_cd!delete.action">
									<a class="mini-button" iconCls="icon-remove" onclick="remove('舱单','futures','futuresCd')">删除</a>
								</tags:hasPerm>
								

								
							</td>
						</tr>
					</table>
				</div>
			
				<div class="mini-fit" >
					<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
						url="../futures/futures_cd!getListData.action?ckId=${ckId }" idField="id"
						allowAlternating="true" pageSize="20" multiSelect="true" >
						 
						<div property="columns">
							<div type="checkcolumn" width="40"></div>
							<div type="indexcolumn" headerAlign="center" width="40">序列</div>
							<div field="id" id="id" width="80" name="id" headerAlign="center" allowSort="true" class="mini-hidden" >id</div>
							<div field="slzt" name="slzt" width="100" headerAlign="center" align="center">受理状态</div>
							<div field="tradtype" name="tradtype" width="100" headerAlign="center" align="center" renderer="onTradtypeRenderer">贸易类型</div>
							<div field="cargoOwner" name="cargoOwner" width="100" headerAlign="center" align="center">客户名称</div>
							<div field="billingNum" name="billingNum" width="100" headerAlign="center" align="center">主提单号</div>
							<div field="cargoName" name="cargoName" width="100" headerAlign="center" align="center">品名</div>
							<div field="brand" name="brand" width="100" headerAlign="center" align="center">品牌</div>
							<div field="production" name="production" width="100" headerAlign="center" align="center">生产加工厂</div>
							<div field="piece" name="piece" width="100" headerAlign="center" align="center">件数</div>
							<div field="cargoWeight" name="cargoWeight" width="100" headerAlign="center" align="center">吨数</div>
							<div field="cargoWeight" name="cargoWeight" width="100" headerAlign="center" align="center">毛重</div>
							<div field="containerNum" name="containerNum" width="100" headerAlign="center" align="center">箱号</div>
							<div field="containerPile" name="containerPile" width="100" headerAlign="center" align="center">箱型</div>
							<div field="sealNum1" name="sealNum1" width="100" headerAlign="center" align="center">铅封号</div>
							<div field="creater" name="creater"	width="60"	headerAlign="center" align="center">创建人</div>
							<div field="createdate" name="createdate" width="80" headerAlign="center" align="center" format="yyyy-MM-dd">创建时间</div> 
							

							
						</div>
			
					</div>
				</div>
			</div>
		
		</div>	
	</div>
<script>
	mini.parse();
	
	var grid = mini.get("grid");
	
	if(${ckId}==2){
		grid.hideColumn("allocateNum");
		grid.hideColumn("houseBillNum");
		grid.hideColumn("billingSerialNum");
		grid.hideColumn("cargoOwner");
		grid.hideColumn("itemName");
		grid.hideColumn("shippingMark");
		grid.hideColumn("dischargedFlag");
		grid.hideColumn("dangerousFlag");
		grid.hideColumn("freezerFlag");
		grid.hideColumn("clearedType");
		grid.hideColumn("goType");
		grid.hideColumn("checkMark"); 
	}



	/**弹窗 接口导入 */
	function jkimport(){

		var grid=mini.get("grid");
		
		mini.open({
			url : "../futures/futures_cd!jkimport.action?ckId="+mini.get("cktree").getSelectedNode().id,
			title : "接口导入",
			width : 350,
			height : 150,
			onload : function() {
			},
			ondestroy : function(action) {
				grid.reload();
			}
		});
	}
	
	/**入库受理*/
	function shouli(){
		var grid=mini.get("grid");
		var rows=grid.getSelecteds();
		if(rows.length==0){
			mini.alert("请选择");
			return;
		}
		var ids=new Array();
		var tdh=rows[0].tdh;
		for(var i=0;i<rows.length;i++){
			if(tdh!=rows[i].tdh){
				mini.alert("请选择相同提单号数据");
				return;
			}
			if(rows[i].slzt=='Y'){
				//mini.alert("请选择未受理的数据");
				//return;
			}
			ids.push(rows[i].id);
		}
		mini.confirm("确定要受理吗？","提醒",function (action){
			if(action=="ok"){
				window.parent.loading();
				$.ajax({
					type:'post',
					url:'../futures/futures_cd!shouli.action?ids='+ids,
					success:function(text){
						window.parent.unmask();
						if(text=="success"){
							mini.alert("受理成功");
							grid.reload();
						}else{
							mini.alert(text);
						}
					}
				});
			}
		});
	}
	
	 function onTradtypeRenderer(e) {
		 	var Suo = [{id:'0',text:'国产'},{id:'1',text:'进口'}];
		 	for (var i = 0, l = Suo.length; i < l; i++) {
		 		var g = Suo[i];
		 		if (g.id == e.value){return g.text;}
		 	}
		 	return "";
		 }
</script>
</body>
</html>