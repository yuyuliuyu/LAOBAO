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
<title>考勤标准维护</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<link rel="stylesheet" href="../template/system/css/base.css"
	type="text/css"></link>
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
body {
	margin: 0;
	sqcdh padding: 0;
	border: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
}

.myrow {
	background: #fceee2;
}
</style>
</head>
<body>
	<div class="mini-toolbar" style="border-left:0;padding:0px;">
		<table style="width:100%;">
			<tr>
				<td >岗位： <input class="mini-textbox" id="gangwei" name="gangwei" emptyText="请输入岗位"
					style="width:150px;" />
				<a class="mini-button"iconCls="icon-search" onclick="search()" onenter="onKeyEnter">查询</a>
				<br>
				    <a class="mini-button" iconCls="icon-add" onclick="add()">新增</a>
					<a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
					<a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
					<a class="mini-button" iconCls="icon-upload"onclick="exportStandardInfo()">导出</a></td>
			</tr>
		</table>
	</div>
	<div class="mini-fit">
		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;"
			url="../laobao/gang_wei!getPersonData.action" idField="id"
			multiSelect="true" allowAlternating="true" pageSize="20">
		<div property="columns">
			<div type="checkcolumn" width="40"></div>
			<div type="indexcolumn" headerAlign="center" width="40">序列</div>
			<div field="id" id="id" name="id" class="mini-hidden">职工号</div>
			<div field="positionName" width="100" headerAlign="center" align="center">岗位名称</div>
			<div header="棉安全帽" headerAlign="center">
				<div property="columns">
					<div field="maqm" width="60" headerAlign="center" align="center">年/1项</div>
				</div>
			</div>
			<div header="单安全帽" headerAlign="center">
				<div property="columns">
					<div field="daqm" width="60" headerAlign="center" align="center">年/1项</div>
				</div>
			</div>
			<div header="长袖工装" headerAlign="center">
				<div property="columns">
					<div field="cxgz" width="60" headerAlign="center" align="center">年/1套</div>
				</div>
			</div>
			<div header="短袖工装" headerAlign="center">
				<div property="columns">
					<div field="dxgz" width="60" headerAlign="center" align="center">年/1套</div>
				</div>
			</div>
			<div header="羽绒服" headerAlign="center">
				<div property="columns">
					<div field="yrf" width="60" headerAlign="center" align="center">年/1件</div>
				</div>
			</div>
			<div header="雨衣" headerAlign="center">
				<div property="columns">
					<div field="yy" width="60" headerAlign="center" align="center">年/1套</div>
				</div>
			</div>
			<div header="雨靴" headerAlign="center">
				<div property="columns">
					<div field="yx" width="60" headerAlign="center" align="center">年/1双</div>
				</div>
			</div>
			<div header="棉安全鞋" headerAlign="center">
				<div property="columns">
					<div field="maqx" width="60" headerAlign="center" align="center">年/1双</div>
				</div>
			</div>
			<div header="绝缘鞋" headerAlign="center">
				<div property="columns">
					<div field="jyx" width="60" headerAlign="center" align="center">年/1双</div>
				</div>
			</div>
<!-- 			<div header="毛巾" headerAlign="center">
				<div property="columns">
					<div field="price" width="60" headerAlign="center" align="center">月/1条</div>
				</div>
			</div>
			<div header="线手套" headerAlign="center">
				<div property="columns">
					<div field="price" width="60" headerAlign="center" align="center">月/1副</div>
				</div>
			</div>
			<div header="香皂" headerAlign="center">
				<div property="columns">
					<div field="price" width="60" headerAlign="center" align="center">月/1块</div>
				</div>
			</div>
			<div header="肥皂" headerAlign="center">
				<div property="columns">
					<div field="price" width="60" headerAlign="center" align="center">月/1块</div>
				</div>
			</div>
			<div header="防滑耐" headerAlign="center">
				<div property="columns">
					<div field="price" width="60" headerAlign="center" align="center">月/1副</div>
				</div>
			</div> -->
			<div header="绝缘手套" headerAlign="center">
				<div property="columns">
					<div field="jyst" width="60" headerAlign="center" align="center">年/1副</div>
				</div>
			</div>
			<div header="电焊手套" headerAlign="center">
				<div property="columns">
					<div field="dhst" width="60" headerAlign="center" align="center">年/1副</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		mini.parse();
		var grid = mini.get("grid");
		grid.hideColumn("id");
		grid.load();
		//查询
		function search() {
			var gangwei = mini.get("gangwei").getValue();
			var stadate = mini.get("stadate").getText();
			var enddate = mini.get("enddate").getText();
			var czType = mini.get("czType").getValue();
			var djType = mini.get("djType").getValue();
			var ip = mini.get("ip").getValue();
			var fzx = mini.get("fzx").getText();
			grid.load({
				czUser : czUser,
				stadate : stadate,
				enddate : enddate,
				djType : djType,
				ip : ip,
				fzx : fzx
			});
		}
		//新增
		function add() {
			mini.open({
				url : "../laobao/wu_pin!ffbzadd.action",
				title : "新增劳保物品发放标准信息",
				width : 500,
				height : 400,
				allowResize : false,
				onload : function() {
					//var iframe = this.getIFrameEl(); 
				},
				ondestroy : function(action) {
					//刷新页面
					window.location.reload();
				}
			});
		}
		//编辑
		function edit() {
			var rows = grid.getSelecteds();
			var row = grid.getSelected();
			if (rows.length <= 0) {
				mini.alert("请选择一条记录！");
			} else if (rows.length > 1) {
				mini.alert("只能选择一条记录！");
			} else {
				mini.open({
					url : "../laobao/wu_pin!ffbzedit.action?id=" + row.id,
					title : "修改劳保物品发放标准信息",
					width : 500,
					height : 400,
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
		//删除
		function remove() {
			var rows = grid.getSelecteds();
			if (rows.length > 0) {
				if (confirm("确定删除选中记录？")) {
					var ids = [];
					for ( var i = 0, l = rows.length; i < l; i++) {
						var r = rows[i];
						ids.push(r.id);
					}
					var sid = ids.join(',');
					$.ajax({
						url : "../laobao/wupin!remove.action?ids=" + sid,
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
		//导出发放标准信息
		function exportStandardInfo() {
			window.location.href = "../laobao/wupin!exportStandardInfo.action";
		}
	</script>
</body>
</html>