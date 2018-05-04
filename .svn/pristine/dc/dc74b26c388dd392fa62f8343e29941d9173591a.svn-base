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
	<title>工作流设置</title>
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
	<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
	<link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css" />
	<!--引入皮肤样式-->
	<link href="${base}template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="mini-toolbar" style="padding:0px;border-top:0;">
		<table style="width:100%;">
			<tr>
				<td style="width:100%;">
					<a class="mini-button" iconCls="icon-add" onclick="add()">新增</a> 
					<a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a> 
					<a class="mini-button" iconCls="icon-add" onclick="addSub()">添加节点</a> 
				</td>
			</tr>
		</table>
	</div>
	<div class="mini-fit">
		<div id="treegrid1" class="mini-datagrid" style="width:100%;height:100%;"
			contextMenu="#gridMenu" url="../check/process!getData.action" allowAlternating="true"
			idField="id" pageSize="10" onshowrowdetail="onShowRowDetail">
            <div property="columns">
			    <div type="checkcolumn" >选择</div>
	        	<div type="indexcolumn" headerAlign="center">序列</div>
	        	<div type="expandcolumn">#</div>   
        	    <div field="id" id="id" width="80" name="id" headerAlign="center" allowSort="true"  class="mini-hidden" >id</div>
		        <div field="name" name="name"  width="120" headerAlign="center" align="center">流程名称</div>
		        <div field="type"  name="type"  width="100" headerAlign="center" align="center">流程类型</div>
		        <div field="createDate" name="createDate" width="150" headerAlign="center" align="center" 
		        	allowSort="true" dataformat="YYYY-MM">创建时间</div>
		        <div field="usingState" name="usingState" width="80" headerAlign="center" align="center">是否启用</div>
		        <div field="processDes" name="processDes" width="300" headerAlign="center" align="center">流程描述</div>
		    </div>
		</div>
		<ul id="gridMenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen">              
	        <li name="add" iconCls="icon-add" onclick="addSub">新增节点</li>       
	    </ul>
		<div id="detailGrid_Form" style="display:none;">
			<div id="grid" class="mini-datagrid" style="width:100%;height:150px;"
				url="../check/process!getDataByParentId.action" pageSize="10">
				<div property="columns">
					<div field="subId" id="subId" width="80" name="subId" headerAlign="center" allowSort="true" class="mini-hidden" >subId</div>
					<div field="sequence" width="100" headerAlign="center" align="center" >节点序列</div>
				    <div field="department" width="150" headerAlign="center" align="center" >部门</div>
				    <div field="post" width="150" headerAlign="center" align="center" >职位</div>
				    <div field="headNode" width="150" headerAlign="center" align="center" >节点负责人</div>
					<div field="caozuo" align="center" width="120" headerAlign="center" 
						renderer="onActionRenderer" >操作</div>
				</div>
			</div>
		</div>
		<div id="htmlContent"></div>
	</div>
	
	<script type="text/javascript">
        mini.parse();
        
        var grid = mini.get("treegrid1");
        var detail_grid = mini.get("grid");
        var detailGrid_Form = document.getElementById("detailGrid_Form");
        
        var type  = mini.get("type");          
        grid.hideColumn("id");
        detail_grid.hideColumn("subId");
        grid.load();
        
        function onBeforeOpen(e) {
		    var grid = mini.get("treegrid1");
		    var menu = e.sender;
		            
		    var row = grid.getSelected();
		    var rowIndex = grid.indexOf(row);            
		    if (!row || rowIndex== 0) {
		        e.cancel = true;
		        //阻止浏览器默认右键菜单
		        e.htmlEvent.preventDefault();
		        return;
		    }
		}
        
        function onShowRowDetail(e) {
            var grid = e.sender;
            var row = e.record;
            
            var td = grid.getRowDetailCellEl(row);
            td.appendChild(detailGrid_Form);
            detailGrid_Form.style.display = "block";
            
            detail_grid.load({
                id : row.id
            });
        }
        
        function onRenderer(e){
             if(e.value==null)
                 return null;
             return parseFloat(e.value).toFixed(4);
        }
        
        //新增
       	function add() {
			mini.open({
				url : "../check/process!add.action",
				title : "新增工作流",
				width : 550,
				height : 350,
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
					url : "../check/process!edit.action?id=" + row.id,
					title : "修改工作流",
					width : 550,
					height : 350,
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
		
		//新增
       	function addSub() {
       		var rows = grid.getSelecteds();
			var row = grid.getSelected();
			if (rows.length <= 0){
				mini.alert("请选择一条流程！");
			} else if (rows.length > 1){
				mini.alert("只能选择一条流程！");
			} else {
				mini.open({
					url : "../check/process!addSub.action?processId="+row.id,
					title : "新增工作流节点",
					width : 550,
					height : 450,
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
		}
		
		function onActionRenderer(e) {
			var grid = e.sender;
            var record = e.record; 
    		var s = '<div><a class="New_Button" href="javascript:editSub(\''+record.subId+'\')">编辑节点</a>&nbsp;|&nbsp;<a class="New_Button" href="javascript:del(\''+record.subId+'\')">删除节点</a></div>';
       		return s;
        }
        
        //编辑节点
       	function editSub(subId) {
       		mini.open({
					url : "../check/process!editSub.action?subId="+subId,
					title : "编辑工作流节点",
					width : 550,
					height : 450,
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
    </script>
</body>
</html>