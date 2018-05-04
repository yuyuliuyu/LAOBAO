<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("base", basePath);
	String flag = request.getParameter("flag");
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>加班申请</title>
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<script src="../template/plugin/public/add.js" type="text/javascript"></script>

<!--引入皮肤样式-->
<link href="../template/miniui/themes/blue/skin.css" rel="stylesheet"
	type="text/css" />
<link href="../template/plugin/public/css/tablepub.css" rel="stylesheet"
	type="text/css" />
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet"
	type="text/css" />
<link href="../template/system/css/base.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
html, body {
	font-size: 12px;
	padding: 0;
	margin: 0;
	border: 0;
	height: 100%;
	overflow: hidden;
}

.check_box {
	float: left;
	margin-right: 5px;
	*margin-right: 15px !important;
}

input[type="checkbox"] {
	vertical-align: -3px;
}
</style>
</head>

<body>
	<form id="form1" method="post">
		<div>
			<input class="mini-hidden" name="id" id="id" value="${evection.id}" style="display:none;" />
			<input class="mini-hidden" name="empId" id="empId" value="${evection.empId}" style="display:none;" />   
			<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
				<tr>
					<td align="left" colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td align="left" class="list_left_box1" height="20px" colspan="4" style="text-align:center;"><b>加班申请</b></td>
				</tr>
				<tr>
                    <td class="list_left_box1" valign="top" width="18%">员工工号：</td>
                    <td class="list_right_box" width="32%">
                        <input name="jobNumber" id="jobNumber" readonly="readonly" class="mini-textbox" value="${evection.jobNumber}"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" width="150px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="18%">员工姓名：</td>
                    <td class="list_right_box" width="32%">
                        <input name="applyName" id="applyName" readonly="readonly" class="mini-textbox" value="${evection.applyName}"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" width="150px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1">加班开始时间：</td>
                    <td class="list_right_box">
                        <input id="startDate" name="startDate" class="mini-datepicker" allowInput="false" 
					      	style="width:150px;" format="yyyy-MM-dd HH:mm" showTime="true" showOkButton="true" required="true" 
					      	value="${evection.startDate }"/>
                    </td>
                    <td class="list_left_box1">加班结束时间：</td>
                    <td class="list_right_box">
                        <input id="endDate" name="endDate" class="mini-datepicker" allowInput="false" 
					      	style="width:150px;" format="yyyy-MM-dd H:mm" timeFormat="H:mm" showTime="true" showOkButton="true" required="true" 
					      	value="${evection.endDate }"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1">加班小时：</td>
                    <td class="list_right_box">
                        <input name="overtimeDay" id="overtimeDay" class="mini-textbox" value="${evection.overtimeDay}"
                            required="true" requiredErrorText="不能为空" vtype="float;maxLength:4" width="150px"/>
                    </td>
                    <td class="list_left_box1">加班类型：</td>
                    <td class="list_right_box">
                        <input field="evectionType" name="evectionType" id="evectionType" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText=""  
							width="150px" value="${evection.evectionType}" url="../template/overtimeType.txt"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1">流程选择：</td>
                	<td class="list_right_box" colspan="3">
                        <input field="processId" name="processId" id="processId" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText=""  
							width="450px" value="${evection.processId}" url="../system/work_flow!getworkflow.action?type=3"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1">备注：</td>
                    <td class="list_right_box" colspan="3">
                        <input name="remark" id="remark" class="mini-textarea" value="${evection.remark}"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:1000" style="width:100%;height:100px;"/>
                    </td>
                </tr>
			</table>
		</div>
		<div class="mini-toolbar"
			style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;"
			borderStyle="border:0;">
			<a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save"
				style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a> 
			<a class="mini-button" onclick="onCancel" iconCls="icon-close"
				style="width:60px;margin-right:20px;" iconCls="icon-close">关闭</a>
			<a class="mini-button" id="give" onclick="giveapp" style="width:60px;margin-right:20px;" iconCls="icon-redo">提报</a> 
		</div>
	</form>
</body>
<script type="text/javascript">
	mini.parse();
	function onOk() {
		var form = new mini.Form("form1");
		var data = form.getData();
		form.validate();
		if (form.isValid() == false)
			return;
		var json = mini.encode(data);
		//strut令牌
		if(mini.get("startDate").getValue()>mini.get("endDate").getValue()){
			alert("开始时间大于结束时间");
		}
		else{
		window.parent.loading();
		$.ajax({
			url : "../check/overtime_record!saveOrUpdate.action",
			type : 'post',
			data : {
				jsondate : json
			},
			success : function(text) {
				window.parent.unmask();
				if (text == "success") {
					mini.showMessageBox({
						title : "提醒",
						width : 250,
						iconCls : "mini-messagebox-warning",
						buttons : [ "ok" ],
						message : "保存成功！",
						callback : function(action){
							CloseWindow("ok");
						}
					});
				} else {
					if (text.length > 0) {
						mini.alert(text);
						CloseWindow("ok");
					}
				}
			}
		});
	}
	}

	function giveapp(){
		var id = mini.get("id").getValue();
		var processId = mini.get("processId").getValue();
		if(id==''||id=='null'){
			alert("请保存后，再进行提报");
		}else if(processId==''||processId=='null'){
			alert("请选择流程后，再进行提报");
		}
		mini.open({
				url : "../check/overtime_sub!giveapp.action?id="+id+"&type=3&pid="+processId,
				title : "选择审批发起节点", 
				width : 670,
				height :440,
				/* allowResize : false, */
				onload : function() {
				},
				ondestroy : function(action) {
					//刷新页面
					onCancel();
				}
			});
		
	}
	
	function CloseWindow(action) {
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        } 

	function onCancel(e) {
		CloseWindow("cancel");
	}
</script>
</html>
