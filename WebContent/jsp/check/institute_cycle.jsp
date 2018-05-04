<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("base", basePath);
%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>循环排班</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${base}template/system/css/base.css" rel="stylesheet"
	type="text/css" />
<link href="${base}template/miniui/themes/other/skin.css"
	rel="stylesheet" type="text/css" />
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<link href="${base}template/system/css/table.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div class="dangshitop" style="color:black;">添加循环排班</div>
	<div class="contentControl">
		<form name="form1" id="form1">
			<table width="90%" cellspacing="0" cellpadding="0" class="zhifabg"
				style="border:1px solid gray" align="center">
				<tr>
					<td>
						循环类型:
						<input name="cycleType" id="cycleType" required="true" repeatItems="3" 
							class="mini-radiobuttonlist" textField="text" valueField="id" repeatDirection="vertical"  
							width="300px" value="${activityApply.cycleType}" url="../template/cycleType.txt"/>
					</td>
				</tr>
				<tr id="instituteTr2">
					<td>
						<input field="a1" name="a1" id="a1" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText="请选择..."  
							width="100px" value="${activityApply.cycleType}" url="../template/content.txt"/>
						&nbsp;&nbsp;>&nbsp;&nbsp;
						<input field="a2" name="a2" id="a2" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText="请选择..."  
							width="100px" value="${activityApply.cycleType}" url="../template/content.txt"/>
					</td>
				</tr>
				<tr id="instituteTr3">
					<td>
						<input field="b1" name="b1" id="b1" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText="请选择..."  
							width="100px" value="${activityApply.cycleType}" url="../template/content.txt"/>
						&nbsp;&nbsp;>&nbsp;&nbsp;
						<input field="b2" name="b2" id="b2" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText="请选择..."  
							width="100px" value="${activityApply.cycleType}" url="../template/content.txt"/>
						&nbsp;&nbsp;>&nbsp;&nbsp;
						<input field="b3" name="b3" id="b3" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText="请选择..."  
							width="100px" value="${activityApply.cycleType}" url="../template/content.txt"/>
					</td>
				</tr>
				<tr id="instituteTr4">
					<td>
						<input field="c1" name="c1" id="c1" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText="请选择..."  
							width="100px" value="${activityApply.cycleType}" url="../template/content.txt"/>
						&nbsp;&nbsp;>&nbsp;&nbsp;
						<input field="c2" name="c2" id="c2" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText="请选择..."  
							width="100px" value="${activityApply.cycleType}" url="../template/content.txt"/>
						&nbsp;&nbsp;>&nbsp;&nbsp;
						<input field="c3" name="c3" id="c3" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText="请选择..."  
							width="100px" value="${activityApply.cycleType}" url="../template/content.txt"/>
						&nbsp;&nbsp;>&nbsp;&nbsp;
						<input field="c4" name="c4" id="c4" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText="请选择..."  
							width="100px" value="${activityApply.cycleType}" url="../template/content.txt"/>
					</td>
				</tr>
				<tr>
					<td>
						<a class="mini-button" onclick="saveCycleInfo" style="width:60px;float:right;" iconCls="icon-save">保存</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<table border="0" width="90%" align="center">
		<tr style="display:block;margin-top:40px;margin-bottom:20px;height:0px;width:100%;border:#0000FF solid 1px;"></tr>
	</table>
	<div id="showTime" style="width:90%;margin-left:5%">
		<div>
			<a class="mini-button" onclick="delTime" style="width:60px;"
				iconCls="icon-remove">删除</a>
		</div>
		<div id="timeGrid" class="mini-datagrid" borderStyle="border:1px;"
			pageSize="10" url="${base}system/time_controller!listData.action"
			idField="id" multiSelect="true" allowAlternating="true">
			<div property="columns">
				<div type="checkcolumn" width="30"></div>
				<div type="indexcolumn" width="50" headerAlign="center" width="20">序号</div>
				<div field="id" name="id" headerAlign="center"
					align="center" class="mini-hidden">id</div>
				<div field="roleName" width="80" headerAlign="center"
					align="center">循环类型</div>
				<div field="startdate" width="250" headerAlign="center"
					align="center" renderer="onDayRenderer">循环流程</div>
				<div field="enddate" width="150" headerAlign="center" align="center"
					renderer="onDayRenderer">创建时间</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$("#instituteTr2").hide();
		$("#instituteTr3").hide();
		$("#instituteTr4").hide();
		
		mini.parse();
		
		//监听循环类型的值
		var cycleType = mini.get("cycleType");
	    cycleType.on("valuechanged", function (e) {
	        if(this.getValue() == "2"){
	        	$("#instituteTr3").hide();
	        	mini.get("b1").setText("");
	        	mini.get("b2").setText("");
	        	mini.get("b3").setText("");
				$("#instituteTr4").hide();
				mini.get("c1").setText("");
				mini.get("c2").setText("");
				mini.get("c3").setText("");
				mini.get("c4").setText("");
	        	$("#instituteTr2").show();
	        } else if (this.getValue() == "3"){
	        	$("#instituteTr2").hide();
	        	mini.get("a1").setText("");
	        	mini.get("a2").setText("");
				$("#instituteTr4").hide();
				mini.get("c1").setText("");
				mini.get("c2").setText("");
				mini.get("c3").setText("");
				mini.get("c4").setText("");
	        	$("#instituteTr3").show();
	        } else if (this.getValue() == "4"){
	        	$("#instituteTr2").hide();
	        	mini.get("a1").setText("");
	        	mini.get("a2").setText("");
				$("#instituteTr3").hide();
				mini.get("b1").setText("");
	        	mini.get("b2").setText("");
	        	mini.get("b3").setText("");
	        	$("#instituteTr4").show();
	        }
	    });
		
		var timeGrid = mini.get("timeGrid");
		timeGrid.hideColumn("id");
		timeGrid.load();
		

		var form = new mini.Form("form1");
		function onOk() {
			var valid = form.validate();
			if (valid) {

				var data = form.getData();
				var json = mini.encode(data);
				$.ajax({
					url : "${base}system/time_controller!saveOrUpdate.action",
					type : "post",
					data : {
						formdata : json
					},
					success : function(text) {
						if (text == "success") {
							form1.reset();
							timeGrid.reload();
						} else {
							mini.alert(text);
						}
					}
				});
			}
		}

		function delTime() {
			var row = timeGrid.getSelecteds();
			if (row.length == 0) {
				mini.alert("请至少选择一条信息！");
				return;
			}
			var arr = [];
			for (var i = 0; i < row.length; i++) {
				arr.push(row[i].id);
			}
			$.ajax({
				url : "${base}system/time_controller!delete.action?id=" + arr,
				type : "post",
				success : function(text) {
					if (text == "success") {
						timeGrid.reload();
					} else {
						mini.alert("删除失败！");
					}
				}
			});
		}
		
		function saveCycleInfo(){
			var cycleTypeVal = mini.get("cycleType").getValue();
			if (cycleTypeVal == ""){
				mini.alert("请选择循环类型！");
				return;
			}
		}

		function onDayRenderer(e) {
			var value = e.value;
			if (value != null) {
				value = new Date(value);
				if (value)
					return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
			} else {
				return "";
			}
		}
	</script>
</body>
</html>