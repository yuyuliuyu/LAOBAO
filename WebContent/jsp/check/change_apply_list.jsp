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
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>工作台设置</title>
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
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
            	<div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
       				 <table style="width:100%;">
          				  <tr>
          				  	<td style="width:200px;">申请人：
                   				 <input id="username" name="username" class="mini-textbox" emptyText="" onenter="onKeyEnter"/>
              			   </td>
   
               			   
               			   <td style="white-space:nowrap;">
                 			   <a id="searchButton" class="mini-button" iconCls="icon-search" onclick="search('username','username')" onenter="onKeyEnter">查询</a>
               			   </td>
           				 </tr>
       				 </table>
   			    </div>
	<div class="mini-toolbar" style="padding:0px;border-top:0;">
		<table style="width:100%;">
			<tr>
				<td style="width:100%;"> 
					<a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
					<a class="mini-button" iconCls="icon-edit" onclick="edit()">修改</a>
					<a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
					<a class="mini-button" iconCls="icon-edit" onclick="stateupdate(1)">审核</a>  
					<a class="mini-button" iconCls="icon-edit" onclick="unapply(2)">驳回</a>      
			</tr>
		</table>
	</div>
	<div class="mini-fit"> 
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
             url="../check/change_apply!jsonlist.action" idField="id" multiSelect="true" 
             allowAlternating="true" pageSize="20" > 					<div property="columns">
						<div name="checkcolumn" type="checkcolumn" >选择</div>
						<div name="indexcolumn" type="indexcolumn" headerAlign="center">序列</div>
				        <div field="id"  id="id" name="id"  width="100" headerAlign="center" align="center">标题</div>
				        <div field="month" id="month"  name="month"  width="100" headerAlign="center" align="center">考勤月度</div> 
				        <div field="day" id="day"  name="day"  width="100" headerAlign="center" align="center">考勤日期</div> 
				        <div field="reson" id="reson"  name="reson"  width="100" headerAlign="center" align="center">申请原因</div> 
				        <div field="username" id="username"  name="username"  width="100" headerAlign="center" align="center">申请人</div>
				        <div field="udate" id="udate"  name="udate" dateFormat="yyyy-MM-dd" width="100" headerAlign="center" align="center">申请时间</div>
				        <div field="applyname" id="applyname"  name="applyname"  width="100" headerAlign="center" align="center">审核人</div> 
				        <div field="state" id="state"  name="state"  width="100" headerAlign="center" align="center">审核状态</div>
				        <div field="appdate" id="appdate" name="appdate" dateFormat="yyyy-MM-dd" width="100" headerAlign="center" align="center">审核时间</div>
					</div>
				</div>
		</div> 
	<script type="text/javascript">
		mini.parse(); 
		var grid = mini.get("grid");
		grid.hideColumn("id"); 
		grid.load();
		//添加分类
		function add() {
			mini.open({
						url : "../check/change_apply!add.action",
						title : "增加系统公告",
						width : 670,
						height : 340,
						/* allowResize : false, */
						onload : function() {
						},
						ondestroy : function(action) {
							//刷新页面
							window.location.reload();
						}
					});
		} 
		function edit() { 
			var rows = grid.getSelecteds();
			if (rows.length == 1) {
				if (rows[0].state == "审核通过" || rows[0].state == "审核不通过") {
					mini.alert("该申请已经审核，不可以修改");
					return;
				}
				mini.open({
					url : "../check/change_apply!add.action?id=" + rows[0].id ,
					title : "编辑系统公告",
					width : 670,
					height : 340,
					/* allowResize : false, */
					onload : function() {
					},
					ondestroy : function(action) {
						//刷新页面
						grid.reload();
					}
				});
			} else {
				mini.alert("请选中一条记录！");
			}
		}
		function onKeyEnter(e) {
             $("#searchButton").click();
        }
		//删除值
		function remove(){
			var rows = grid.getSelecteds();
			if (rows.length == 1) {
				if (confirm("确定删除选中的记录？")) {
					$.ajax({
						url : "../check/change_apply!remove.action?id="+rows[0].id,
						type : "post",
						success : function(text) {
							grid.reload();
							mini.alert("删除成功！");
						},
						error : function(text) {
							mini.alert("删除失败");
						}
					});
				}
			} else {
				mini.alert("请选中一条记录！");
			}
		}
		//审核
		function stateupdate(state){  
			var rows = grid.getSelecteds();
			if (rows.length == 1) {
				if (confirm("确定修改当前状态么？")){
					$.ajax({
						url : "../check/change_apply!apply.action?id="+rows[0].id+"&state="+state,
						type : "post",
						success : function(text){
							grid.reload();
							mini.alert(text);
						},
						error : function(text) {
							mini.alert(text);
						}
					});
				}
			} else {
				mini.alert("请选中1一条记录！");
			} 
		}
		//驳回
		function unapply(state){  
			var rows = grid.getSelecteds();
			if (rows.length == 1) {
				if (confirm("确定修改当前状态么？")){
					$.ajax({
						url : "../check/change_apply!unapply.action?id="+rows[0].id+"&state="+state,
						type : "post",
						success : function(text){
							grid.reload();
							mini.alert(text);
						},
						error : function(text) {
							mini.alert(text);
						}
					});
				}
			} else {
				mini.alert("请选中1一条记录！");
			} 
		}
		function onKeyEnter(e) {
			search();
		}

		grid.on("drawcell", function(e) {
			var field = e.field;
			var record = e.record;
            // 审批状态
            if (field == "state") {
            	if (record.state == "审核通过") {
            		e.rowStyle = "background:#CCFFCC";
            	}
            }
		});
	</script>
</body>
</html>