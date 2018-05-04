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
	<title>列表页</title>
	<style type="text/css">
	body{
	    margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
	}
	</style>
	<script src="${base}/template/miniui/boot.js" type="text/javascript"></script>
	<link href="${base}/template/system/css/base.css" rel="stylesheet" type="text/css" />
	<script src="${base}/template/plugin/public/treeList.js" type="text/javascript"></script>
	<!--引入皮肤样式-->
	<link href="${base}/template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="mini-fit" >
<div class="mini-splitter" style="width:100%;height:100%;">
			<div size="20%" showCollapseButton="true">
			<!-- ckId -->
			<input type="hidden" value="${sessionScope.depId}" id="depId" />
			<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
				<span style="padding-left:5px;">组织机构：</span>
			</div>
			<div class="mini-fit" style=" border-top:0;">
				<ul id="deptree" class="mini-tree" url="../basis/branch!getTreeListByUser.action" style="width:100%;height:100%;"
					showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true" >
				</ul>
			</div>
		</div>
		<div showCollapseButton="false">	
			<div style="width:100%;">
				<div class="mini-toolbar" style=" /* border-bottom:0; */padding:0px;border-top:0;">
					<table style="width:100%;">
						<tr>
							<td >
								船名：
								<input id="shipName" name="shipName" class="mini-textbox" emptyText="请输入船名" onenter="onKeyEnter"/>
							</td>
							<td >
								航次：
								<input id="voyage" name="voyage" class="mini-textbox" emptyText="请输入航次" onenter="onKeyEnter"/>
							</td>
							<td >
								主提单号：
								<input id="billingNum" name="billingNum" class="mini-textbox" emptyText="请输入主提单号" 
								onenter="onKeyEnter"/>
							</td>
							<td >
								箱号：
								<input id="containerNum" name="containerNum" class="mini-textbox" emptyText="请输入箱号" onenter="onKeyEnter"/>	
							</td>
						</tr>
						<tr>
						
						<td >开始时间：
                    			<input id="start" name="start" class="mini-datepicker" emptyText="请选择开始时间" dateFormat="yyyy-MM-dd"  
                           			onenter="onKeyEnter" allowInput="false" />
                        </td>
                		<td >结束时间：
	                    		<input id="end" name="end" class="mini-datepicker" emptyText="请选择结束时间" dateFormat="yyyy-MM-dd" 
	                           		onenter="onKeyEnter"allowInput="false" />
	                	</td>
	                	<td style="white-space:nowrap;">

								<a class="mini-button" iconCls="icon-search" id="searchButton" onclick="search('shipName,voyage,billingNum,containerNum','shipName,voyage,billingNum,containerNum')">查询</a>

						</td>
	                	</tr>
					</table>
				</div>
			</div>
			<div class="mini-toolbar" style="spadding:0px;border-top:0;">
					<table style="width:100%;">
						<tr>
							<td >
							<tags:hasPerm value="/statquery/box_query!yundi.action">
							<a class="mini-button" iconCls="icon-downgrade" onclick="yundi()">运抵</a> 
							</tags:hasPerm>
							<tags:hasPerm value="/statquery/box_query!deyundi.action">
							<a class="mini-button" iconCls="icon-remove" onclick="deyundi()">删除运抵</a> 
							</tags:hasPerm>
							<tags:hasPerm value="/statquery/box_query!huizhi.action">
							<a class="mini-button" iconCls="icon-upgrade" onclick="huizhi()">回执</a> 
							</tags:hasPerm>
							<tags:hasPerm value="/statquery/box_query!yanfeng.action">
							<a class="mini-button" iconCls="icon-goto" onclick="yanfeng()">验封</a>
							</tags:hasPerm>
							</td>
						</tr>
					</table>
				</div>
			<div class="mini-fit" >
				<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
					url="../system/backend_role!json.action"  idField="id"
					allowAlternating="true"  pageSize="20" multiSelect="true" sortMode="client">
				
				<div property="columns">
                	<div type="checkcolumn" width="40"></div>
                	<div type="indexcolumn" headerAlign="center" width="40">序列</div>
                	<div field="id" id="id" width="80" name="id" headerAlign="center" allowSort="true" class="mini-hidden" >id</div>
					<div field="shipName" name="shipName" width="100" headerAlign="center" align="center" allowSort="true">中文船名</div>							
					<div field="voyage" name="voyage" width="100" headerAlign="center" align="center" >航次</div>
                	<div field="billingNum" name="billingNum" width="100" headerAlign="center" align="center">主提单号</div>
					<div field="containerNum" name="containerNum" width="100" headerAlign="center" align="center">箱号</div>
					<div field="containerSize" width="60" headerAlign="center" align="center">尺寸</div>
					<div field="containerType" width="60" headerAlign="center" align="center">箱型</div>  
					<div field="containerCompany" width="100" headerAlign="center" align="center">箱属</div>
					<div field="emptyWeight" width="60" headerAlign="center" align="center" >空重 </div>
					<div field="reportCode" width="80" headerAlign="center" align="center" >回执代号 </div>
					<div field="reportStatus" width="60" headerAlign="center" align="center" >运抵状态 </div>
					<div field="trade" width="60" headerAlign="center" align="center" >内外贸 </div>
					<div field="yfbz" width="70" headerAlign="center" align="center" renderer="onIsOrNotRenderer">验封标志 </div>
            	</div>
				
				</div>
			</div>
		</div>

	</div>
	</div>	
</div>
<script type="text/javascript">
	mini.parse();
	var grid = mini.get("grid");

/**查询
 * name:所有查询条件框的name字符串
 * attr:所有查询条件的属性名
 * */
function search(name,attr){
	var grid=mini.get("grid");
	var names=name.split(",");
	var attrs=attr.split(",");
	var searchData="{";
	for(var i=0;i<names.length;i++){
		if(mini.get(names[i])){
			var value=mini.get(names[i]).getValue();
			searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\"";
			if(i!=names.length-1)searchData=searchData+",";
		}
	}
	searchData=searchData+",\"start\""+":\""+mini.get("start").getFormValue()
				+"\","+"\"end\""+":\""+mini.get("end").getFormValue()+"\"";
	//如果有树
	var tree=mini.get("deptree");
	if(tree){
		var node = tree.getSelectedNode();
		if(node){
			var ids = "";
			ids = depAll(ids,tree,node);
		    searchData=searchData+",\"depId\":\""+ids+"\"";
		}
	}
	searchData=searchData+"}";
	grid.load({ searchData: searchData });
}

function depAll(ids,tree,node){
	debugger;
	if(node.flg==1){
		ids += node.id+",";
		return ids;
	}else{
		var list = tree.getChildNodes(node);
		if(list.length>0){
			 for ( var i = 0; i < list.length; i++) {
			    	if(list[i].flg==1){
			    		ids = ids + "," + list[i].id;
			    	}else{
			    		node = list[i];
			    		ids = depAll(ids,tree,node);
			    	}
			 }
		}
		return ids;
	}
}
</script>
</body>
</html>