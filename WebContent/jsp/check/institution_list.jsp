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
    <title>班制名称维护</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link rel="stylesheet" href="../template/system/css/base.css" type="text/css"></link>
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    body {
        margin: 0;
        padding: 0;
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
				<td>
					<a class="mini-button" iconCls="icon-add" onclick="add()">新增</a> 
<!-- 					<a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>  -->
					<a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
					<a class="mini-button" iconCls="icon-upload" onclick="exportInstitutionInfo()">导出</a> 
				</td>
			</tr>
		</table>
	</div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
             url="../check/institution!getData.action" idField="id" multiSelect="true" 
             allowAlternating="true" pageSize="20" >
            <div property="columns">
            	<div type="checkcolumn"></div>
                <div type="indexcolumn" headerAlign="center" width="50">序列</div>
                <div field="id" id="id" name="id" width="120" headerAlign="center" allowSort="true">id</div>
                <div field="instituteName" id="instituteName" name="instituteName" width="800" headerAlign="center" align="center" >班制名称</div>
<!--                 <div field="overtimeModulus" id="overtimeModulus" name="overtimeModulus" width="200" headerAlign="center" align="center" >加班系数</div> -->
<!--                 <div field="vacationModulus" id="vacationModulus" name="vacationModulus" width="200" headerAlign="center" align="center" >休假系数</div> -->
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
        mini.parse();
        
        var grid=mini.get("grid");
        grid.hideColumn("id");
        grid.load();
                
        //新增
		function add() {
			mini.open({
				url : "../check/institution!add.action",
				title : "新增班制名称信息",
				width : 500,
				height : 260,
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
			if (rows.length <= 0){
				mini.alert("请选择一条记录！");
			} else if (rows.length > 1){
				mini.alert("只能选择一条记录！");
			} else {
				mini.open({
					url : "../check/institution!edit.action?id=" + row.id,
					title : "修改班制名称信息",
					width : 500,
					height : 260,
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
					for (var i = 0, l = rows.length; i < l; i++) {
						var r = rows[i];
						ids.push(r.id);
					}
					var sid = ids.join(',');
					$.ajax({
						url : "../check/institution!remove.action?ids=" + sid,
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
		//导出班制名称信息
		function exportInstitutionInfo(){
			window.location.href="../check/institution!exportInstitutionInfo.action";
		}
    </script>
</body>
</html>