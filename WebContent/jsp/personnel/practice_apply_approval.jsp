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
<title>申请审批</title>
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<script src="../template/plugin/public/add.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/blue/skin.css" rel="stylesheet"
	type="text/css" />
<link href="../template/plugin/public/css/tablepub.css" rel="stylesheet"
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
</style>
</head>
<body>
	<form id="form1" method="post">
		<div>
			<input class="mini-hidden" name="auditId" id="auditId" value="${evection.id}" style="display:none;" /> 
			<input class="mini-hidden" name="pid" id="pid" value="${evection.pid}" style="display:none;" />   
			<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
				<tr>
					<td align="left" colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td align="left" class="list_left_box1" height="20px" width="150px"><b>领导审批：</b></td>
					<td align="left" class="list_left_box1" colspan="3"></td>
				</tr>
				<tr>
					<td class="list_left_box1" valign="top">审批意见：</td>
					<td class="list_right_box" colspan="3">
					 <textarea name="opinion" id="opinion" class="mini-textarea" width="100%" height="100px" emptyText="请输入审批意见">${evection.opinion}</textarea>  
					</td>
				</tr>
				<tr>
					<td class="list_left_box1" valign="top">审批人：</td>
					<td class="list_right_box">
					<input name="auditName" id="auditName" class="mini-textbox" readonly="readonly" value="${evection.auditName}" width="100%" />
					<input name="auditId" id="auditId" class="mini-hidden" value="${evection.auditId}" width="100%" />
					<input name="evectType" id="evectType" class="mini-hidden" value="${evection.evectType}" width="100%" />
					</td>
					<td class="list_left_box1" valign="top" width="100px">审批时间：</td>
					<td class="list_right_box">
					<input name="auditDate" id="auditDate" class="mini-datepicker"  value="${evection.auditDate}" width="100%" /></td>
				</tr>
				<tr>
					<td class="list_left_box1" valign="top">审批结果：</td>
					<td class="list_right_box">
                        <input field="auditStatus" name="auditStatus" id="auditStatus" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText=""  
							width="150px" value="${evection.auditStatus}" url="../template/appral.txt"/>
                    </td>
                    <td class="list_left_box1">驳回进度：</td>
					<td align="left" class="list_left_box1" > 
                        <input field="nodeId" name="nodeId" id="nodeId"  
							class="mini-combobox" textField="text" valueField="id" emptyText=""  
							width="90%" value="" url="../system/work_flow!getDataByPId.action?pid=${processId}"/>
                    </td>
				</tr>
			</table>
		</div>
		<div class="mini-toolbar"
			style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;"
			borderStyle="border:0;">
			<a class="mini-button" id="ok" onclick="onOk(1)" iconCls="icon-save" tyle="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>  
			<a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a>
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
		//window.parent.loading(); 
		var a=mini.get("auditStatus").getValue();
		var b=mini.get("nodeId").getValue()
		if(a==1&&b==''||a==0&&b!=''){
		$.ajax({
			url : "../check/overtime_sub!saveOrUpdate.action",
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
					}
				}
			}
		}); 	 
		}else{
			alert("您的填写有误，请查证");
		}
	}

	function CloseWindow(action) {
		if (action == "close" && form.isChanged()) {
			if (confirm("数据被修改了，是否先保存？")) {
				return false;
			}
		}
		if (window.CloseOwnerWindow)
			return window.CloseOwnerWindow(action);
		else
			window.close();
	}

	function onCancel(e) {
		CloseWindow("cancel");
	}
</script>
</html>
