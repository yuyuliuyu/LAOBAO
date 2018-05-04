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
<title>工作台编辑页面</title>
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
			<input class="mini-hidden" name="id" id="id" value="${changeapp.id}" style="display:none;" />  
			<input class="mini-hidden" name="state" id="state" value="${changeapp.state}" style="display:none;" />  
			<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
				<tr>
					<td align="left" colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td align="left" class="list_left_box1" height="20px" width="150px"><b>申请修改考勤：</b></td>
					<td align="left" class="list_left_box1" colspan="3"></td>
				</tr>
				<tr>
					<td class="list_left_box1" valign="top">日期：</td>
					<td class="list_right_box" colspan="3">
					   <input class="mini-datepicker" id="allMonth" format="yy年MM月dd日" onvaluechanged="onAllMonthChanged"/>
					   <input name="month" id="month" class="mini-hidden" value="${changeapp.month}" width="100%" />
					   <input name="day" id="day" class="mini-hidden" value="${changeapp.day}" width="100%" />
					</td>
				</tr>
				<tr>
					<td class="list_left_box1" valign="top">申请原因：</td>
					<td class="list_right_box" colspan="3">
					 <textarea name="reson" id="reson" class="mini-textarea" width="100%" height="50px" emptyText="请输入公告内容">${changeapp.reson}</textarea>  
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
				style="width:60px;" iconCls="icon-close">关闭</a>
		</div>
	</form>
</body>
<script type="text/javascript">
	mini.parse();
	var allMonth = mini.get("allMonth");
	$(function() {
		if ('${changeapp.id}' != "") {
			var date = '${changeapp.day}';
			if (date.length ==1) {
				date = '0${changeapp.day}';
			}
			allMonth.setValue('20${changeapp.month}'+date);
		}
	});
	function onOk() {
		var form = new mini.Form("form1");
		var data = form.getData();
		form.validate();
		if (form.isValid() == false)
			return;
		var json = mini.encode(data);
		//strut令牌
		window.parent.loading();
		$.ajax({
			url : "../check/change_apply!saveOrUpdate.action",
			type : 'post',
			data : {
				jsondata : json
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

	function onAllMonthChanged(e) {
		var date = new Date(e.value);
		var a = mini.formatDate(date, "yyMMdd");
		mini.get("month").setValue(a.substring(0, 4));
		mini.get("day").setValue(a.substring(4, 6));
	}
</script>
</html>
