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
	<title>出差申请</title>
	<script src="../template/miniui/boot.js" type="text/javascript"></script>
	<!--引入皮肤样式-->
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
	</style>
</head>
<body>
	<div class="mini-fit"> 
		<div class="mini-toolbar" style="padding:0px;border-top:0;">
			<table style="width:100%;">
				<tr>
					<td style="width:100%;"> 
						<input class="mini-hidden" name="state" id="state" value="${state}" style="display:none;" />   
						<s:if test="state==0"> 
							<a class="mini-button" iconCls="icon-ok" onclick="appral()">审批</a>   
						</s:if>
						<s:else>
		 					<a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
							<a class="mini-button" iconCls="icon-edit" onclick="edit()">修改</a>
							<a class="mini-button" iconCls="icon-redo" onclick="giveapp()">提报</a>
<!-- 							<a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a> -->
						</s:else>
					</td>
				</tr>
			</table>
		</div>
		<div class="mini-fit">
			<div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
	            url="../check/evection_record!jsonlist.action?state=${state}" allowAlternating="true" pageSize="20"
	            idField="id" multiSelect="true">
	            <div property="columns">
	                <div type="checkcolumn" width="50"></div>
	                <div field="id" name="id" id="id" width="100" headerAlign="center" align="center">id</div>
	                <div field="processId" name="processId" id="processId" width="120" headerAlign="center" align="center" >流程id</div>
	                <div field="jobNumber" name="jobNumber" id="jobNumber" width="120" headerAlign="center" align="center" >职工号</div>
	                <div field="applyName" name="applyName" id="applyName" width="100" headerAlign="center" align="center" >姓名</div>
	                <div field="evectionType" name="evectionType" id="evectionType" width="120" headerAlign="center" align="center" >出差类型</div>
	                <div field="startDate" name="startDate" id="startDate" width="160" headerAlign="center" align="center" >出差开始时间</div>
	                <div field="endDate" name="endDate" id="endDate" width="160" headerAlign="center" align="center" >出差结束时间</div>
	                <div field="evectionDay" name="evectionDay" id="evectionDay" width="80" headerAlign="center" align="center" >出差天数</div> 
	                <div field="nodeName" name="nodeName" id="nodeName" width="120" headerAlign="center" align="center" >流程节点</div> 
	                <div field="nodeid" name="nodeid" id="nodeid" width="120" headerAlign="center" align="center" >岗位id</div> 
	                <div name="action" width="120" headerAlign="center" align="center" renderer="onActionRenderer" 
	                    cellStyle="padding:0;">操作</div>
	            </div>
	        </div> 
		</div>
	</div> 
	<script type="text/javascript">
		mini.parse();
		var grid = mini.get("datagrid1");
		grid.hideColumn("id");
		grid.hideColumn("processId");
		grid.hideColumn("nodeid");
		grid.load();
		
		//增加
		function add() {
			mini.open({
					url : "../check/evection_record!add.action",
					title : "增加出差申请",
					width : 670,
					height :440,
					/* allowResize : false, */
					onload : function() {
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
			if (rows.length == 1) {
				$.ajax({
						url : "../check/evection_record!getAuditStatus.action?id="+rows[0].id,
						type : "post",
						success : function(text) {
							if(text == "0" || text == "3"){
								mini.open({
									url : "../check/evection_record!add.action?id=" + rows[0].id ,
									title : "编辑出差申请",
									width : 670,
									height : 440,
									/* allowResize : false, */
									onload : function() {
									},
									ondestroy : function(action) {
										//刷新页面
										grid.reload();
									}
								});
							} else if (text == "1"){
								mini.alert("申请审核中，不能进行修改！");
							} else if (text == "2"){
								mini.alert("申请已完成，不能进行修改！");
							}
						},
						error : function(text) {
							mini.alert("操作失败");
						}
					});
			} else {
				mini.alert("请选中一条记录！");
			}
		}

		//操作
	    function onActionRenderer(e) {
            var record = e.record;
            var uid = record.id;
            var s = '';
            s = s + '&nbsp; &nbsp' + '<a class="Edit_Button" href="javascript:view(\'' + uid + '\')">查看</a> | <a class="Edit_Button" href="javascript:viewApprove(\'' + uid + '\')">审批信息</a>'; 
            return s;
        }
        //查看
        function view(id){
        	mini.open({
					url : "../check/evection_record!view.action?id="+id,
					title : "查看出差申请",
					width : 670,
					height :400,
					/* allowResize : false, */
					onload : function() {
					},
					ondestroy : function(action) {
						//刷新页面
						window.location.reload();
					}
				});
        }
        //查看审批信息
        function viewApprove(id) {
        	mini.open({
					url : "../check/overtime_sub!list.action?pid="+ id,
					title : "查看审核信息",
					width : 800,
					height : 450,
					/* allowResize:false, */
					onload : function() {
					},
					ondestroy : function(action) {
						//刷新页面
						window.location.reload();
					}
				});
        }
        
		//删除
		function remove(){
			var rows = grid.getSelecteds();
			if (rows.length == 1) {
				if (confirm("确定删除选中的记录？")) {
					$.ajax({
						url : "../check/evection_record!remove.action?id="+rows[0].id,
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
		
		//审核
		function appral(){ 
			var rows = grid.getSelecteds();
			if (rows.length == 1) {
        	mini.open({
					url : "../check/overtime_sub!add.action?type=1&pid=" +rows[0].id+"&processId="+rows[0].processId+"&advid="+rows[0].nodeid,
					title : "审批页面",
					width : 800,
					height : 450,
					/* allowResize:false,*/
					onload : function() {
					},
					ondestroy : function(action) {
						//刷新页面
						window.location.reload();
					}
				});

			} else {
				mini.alert("请选中一条记录！");
			}
		}

		// 提报
		function giveapp(){
			var row = grid.getSelected();
			if (!row) {
				mini.alert("请选择一条记录");
				return;
			}
	        var id = row.id;
	        var processId = row.processId;
	        if(id==''||id=='null'){
	            mini.alert("请保存后，再进行提报");
	            return;
	        }else if(processId==''||processId=='null'){
	            mini.alert("请选择流程后，再进行提报");
	            return;
	        }
	        mini.open({
	                url : "../check/overtime_sub!giveapp.action?id="+id+"&type=1&pid="+processId,
	                title : "选择审批发起节点",
	                width : 670,
	                height :440,
	                /* allowResize : false, */
	                onload : function() {
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