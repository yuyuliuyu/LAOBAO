<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path;
    pageContext.setAttribute("base", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>保险福利项目</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>
<body>
				<div class="mini-toolbar" id="form1" style="padding:0px;border-top:0;">
					<table style="width:100%;">
						<tr>
							<td style="width:100%;"> 
								<a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
								<a class="mini-button" iconCls="icon-edit" onclick="edit()">修改</a>
								<a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a> 
						</tr>
					</table>
				</div> 
	<div class="mini-fit"> 
		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;"
			contextMenu="#gridMenu" url="../basis/insurance_benefits!jsonlist.action" allowAlternating="true"
			idField="id" pageSize="20" >
						<div property="columns">
               			<div type="checkcolumn" width="50"></div>
				        <div field="id" name="id" id="id" width="100" headerAlign="center" align="center">id</div> 
				        <div field="name"  name="name"  width="100" headerAlign="center" align="center">名称</div>
				        <div field="type"  name="type"  width="100" headerAlign="center" align="center">类型</div>
				        <div field="insuranceModel"  name="type"  width="110" headerAlign="center" align="center">社保缴费模板</div>
				        <div field="ispay"  name="ispay"  width="100" headerAlign="center" align="center">是否个人缴纳</div>
				        <div field="isvalid"  name="isvalid"  width="100" headerAlign="center" align="center">是否有效</div> 
					</div>
				</div> 
		</div> 
	<script type="text/javascript">
		mini.parse(); 
		var grid = mini.get("grid");
		grid.hideColumn("id");
		grid.hideColumn("indexcolumn");
		grid.load();
		//添加分类
		function add() {
			mini.open({
						url : "../basis/insurance_benefits!add.action",
						title : "增加保险福利项目",
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
					url : "../basis/insurance_benefits!add.action?id=" + rows[0].id ,
					title : "编辑保险福利项目",
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
						url : "../basis/insurance_benefits!remove.action?id="+rows[0].id,
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
		function onKeyEnter(e) {
			search();
		}
	</script>
</body>
</html>