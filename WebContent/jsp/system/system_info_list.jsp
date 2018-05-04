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
<script type="text/javascript"
	src="../template/plugin/public/cent_dep.js"></script>
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
	<div class="mini-fit"> 
				<div class="mini-toolbar" style="padding:0px;border-top:0;">
					<table style="width:100%;">
						<tr>
							<td style="width:100%;"> 
								<a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
								<a class="mini-button" iconCls="icon-edit" onclick="edit()">修改</a>
								<a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
								<a class="mini-button" iconCls="icon-edit" onclick="stateupdate()">状态修改</a> 
								<a class="mini-button" iconCls="icon-find" onclick="showty(0)">显示停用公告</a> 
								<a class="mini-button" iconCls="icon-find" onclick="showty(1)">显示启用公告</a>    
								<input class="mini-hidden" name="state" id="state" value="${state}" style="display:none;" />  
						</tr>
					</table>
				</div>
				<div id="grid" class="mini-treegrid" style="width:100%;height:94%;"
					showTreeIcon="true" url="../system/system_info!jsonlist.action" treeColumn="name" idField="did"
					parentField="pid" resultAsTree="false" allowResize="false"
					expandOnLoad="true">
					<div property="columns">
						<div name="checkcolumn" type="checkcolumn" >选择</div>
						<div name="indexcolumn" type="indexcolumn" headerAlign="center">序列</div>
				        <div field="id"  name="id"  width="100" headerAlign="center" align="center">标题</div>
				        <div field="title"  name="title"  width="100" headerAlign="center" align="center">标题</div>
				        <div field="content"  name="content"  width="100" headerAlign="center" align="center">内容</div>
				        <div field="fabutime"  name="fabutime"  width="100" headerAlign="center" align="center">发布时间</div> 
					</div>
				</div> 
		</div> 
	<script type="text/javascript">
		mini.parse(); 
		var grid = mini.get("grid");
		grid.hideColumn("id");
		grid.hideColumn("indexcolumn");
		function showty(id){ 
			grid.load({state:id});
		}
		//添加分类
		function add() {
			mini.open({
						url : "../system/system_info!add.action",
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
				mini.open({
					url : "../system/system_info!add.action?id=" + rows[0].id ,
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
		//删除值
		function remove(){
			var rows = grid.getSelecteds();
			if (rows.length == 1) {
				if (confirm("确定删除选中的记录？")) {
					$.ajax({
						url : "../system/system_info!remove.action?id="+rows[0].id,
						type : "post",
						success : function(text) {
							grid.reload({state:mini.get("state").getValue()});
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
		//删除分类
		function stateupdate(){  
			var rows = grid.getSelecteds();
			if (rows.length == 1) {
				if (confirm("确定修改当前状态么？")){
					$.ajax({
						url : "../system/system_info!chengestate.action?id="+rows[0].id,
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
	</script>
</body>
</html>