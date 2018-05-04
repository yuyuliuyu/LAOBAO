<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>按日考勤数据树信息</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<link rel="stylesheet" href="../template/system/css/base.css"
	type="text/css"></link>
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	border: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
}
</style>
</head>
<body>
	<div class="mini-splitter" style="width:100%;height:100%;" handlerSize="9">
		<div size="300" showCollapseButton="true">
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
		<div showCollapseButton="flase">
			<div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
		        <table style="width:100%;">
		            <tr>
		                <td style="width:300px;padding-left:20px;">日期：
		                 	<input name="day" id="day" class="mini-spinner" value="1" minValue="1" maxValue="31" style="margin-left: 9px;"/>
		                </td>
		                <td style="white-space:nowrap;">
		                    <a class="mini-button" iconCls="icon-search" onclick="search('day','day')" onenter="onKeyEnter">查询</a>
		                </td>
		            </tr>
		        </table>
		    </div>
			<div class="mini-toolbar" style="border-left:0;padding:0px;">
				<table style="width:100%;">
					<tr>
						<td>
							<a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a> 
						</td>
						<td style="width:200px;" align="right">年月份:</td>
						<td id="yearMonth" style="width:100px;font-weight:bold;">${yearMonth }</td>
						<td style="width:50px;" align="right">状态：</td>
		                <td id="auditStatus" style="width:200px;font-weight:bold;">${auditStatus }</td>
					</tr>
				</table>
			</div>
		    <div class="mini-fit">
		        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
		             url="../check/check_infos!getDayInfoData.action?day=1" idField="id" multiSelect="true" 
		             allowAlternating="true" pageSize="20" >
		            <div property="columns">
		            	<div type="checkcolumn"></div>
		                <div type="indexcolumn" width="80" headerAlign="center">序列</div>
		                <div field="id" id="id" name="id" width="120" headerAlign="center" allowSort="true">id</div>
		                <div field="className" id="className" name="className" width="80" headerAlign="center" align="center">班组</div>
		                <div field="jobNumber" id="jobNumber" name="jobNumber" width="80" headerAlign="center" align="center">职工号</div>
		                <div field="name" id="name" name="name" width="80" headerAlign="center" align="center" >姓名</div>
		                <div field="post" id="post" name="post" width="80" headerAlign="center" align="center" >岗位</div>
		                <div field="content" id="content" name="content" width="80" headerAlign="center" align="center" >考勤</div>
		                <div field="overtime" id="overtime" name="overtime" width="80" headerAlign="center" align="center" >加班</div>
		                <div field="startTime" id="startTime" name="startTime" width="120" headerAlign="center" align="center" >最早时间</div>
		                <div field="endTime" id="endTime" name="endTime" width="120" headerAlign="center" align="center" >最晚时间</div>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
	<script type="text/javascript">
		var day = "${day}";
		var monthDays = "${monthDays}";
		var auditStatus = "${auditStatus}";
		if (auditStatus == "待审核"){
			$("#auditStatus").css("color", "blue");
		} else if (auditStatus == "审核通过"){
			$("#auditStatus").css("color", "green");
		} else if (auditStatus == "审核不通过"){
			$("#auditStatus").css("color", "red");
		} else if (auditStatus == "未上报") {
			$("#auditStatus").css("color", "gray");
		}
		
		mini.parse();	
		var grid = mini.get("grid");
		grid.hideColumn("id");
        
        $(function(){ 
        	grid.load();
        	var day = mini.get("day").getValue();
			//如果有树
			var tree=mini.get("deptree");
			if(tree && grid){
				//默认选中
				var depId = $("#depId").val();
				if(depId){
					var originalNode=tree.getNode();
					tree.selectNode(originalNode);
				}
				
				tree.on("nodeclick", function (e) {
					var tree=mini.get("deptree");
					var node = tree.getSelectedNode();
					//可见即可选
					/*if(!tree.isAncestor ( originalNode, node )){
						tree.selectNode ( originalNode );//选中原节点
						//tree.unSelectNode( node );//取消选中当前节点
						return;
					}*/
					if (node) {
						if(document.getElementById("searchButton")){ 
							$("#searchButton").click();
						}else { 
							var ids = "";
							var tree=mini.get("deptree");
							if(tree){
								var node = tree.getSelectedNode();
								if(node){
									var ids = "";
									ids = depAll(ids,tree,node);
								}
							}
							grid.load({ searchData:"{\"depId\":\""+ids+"\", \"day\":\""+day+"\"}" });
						}
					} else {
						grid.setData([]);
						grid.setTotalCount(0);
					}
				});
			}
		});
		
		/**查询
		 * name:所有查询条件框的name字符串
		 * attr:所有查询条件的属性名
		 * */
		function search(name,attr){
			var grid=mini.get("grid");
			var searchData="{";
			if(name&&attr){
				var names=name.split(",");
				var attrs=attr.split(",");
				for(var i=0;i<names.length;i++){
					if(mini.get(names[i])){
						var value=mini.get(names[i]).getValue();
						searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\"";
						if(i!=names.length-1)searchData=searchData+",";
					}
				}
			}
			//searchData=searchData+",\"start\""+":\""+mini.get("start").getFormValue()
			//			+"\","+"\"end\""+":\""+mini.get("end").getFormValue()+"\"";
			//如果有树
			var tree=mini.get("deptree");
			if(tree){
				var node = tree.getSelectedNode();
				if(node){
					var ids = "";
					ids = depAll(ids,tree,node);
					if (searchData == "{"){
						searchData=searchData+"\"depId\":\""+ids+"\"";
					} else {
						searchData=searchData+",\"depId\":\""+ids+"\"";
					}
				    
				}
			}
			searchData=searchData+"}";
			grid.load({ searchData: searchData });
		}
		
		function depAll(ids,tree,node){
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
		
		//编辑
		function edit() {
			var rows = grid.getSelecteds();
			var row = grid.getSelected();
			if (rows.length <= 0){
				mini.alert("请选择一条记录！");
			} else if (rows.length > 1){
				mini.alert("只能选择一条记录！");
			} else {
				var day = mini.get("day").getValue();
				mini.open({
					url : "../check/check_infos!editEmp.action?id=" + row.id+"&day="+day,
					title : "修改日考勤信息",
					width : 500,
					height : 320,
					/* allowResize:false, */
					onload : function() {
					},
					ondestroy : function(action) {
						//刷新页面
						window.location.reload();
					}
				});
			}
		}
	</script>
</body>
</html>