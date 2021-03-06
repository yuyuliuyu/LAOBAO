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
<title>增加编辑页面</title>
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
	<div class="mini-fit" id="form1">
		<div>
			<input class="mini-hidden" name="id" id="id" value="${id}" style="display:none;" /> 
			<input class="mini-hidden" name="pid" id="pid" value="${pid}" style="display:none;" /> 
			<input class="mini-hidden" name="moduleType" id="moduleType" value="${jcdictionary.moduleType}" style="display:none;" />
			<table cellpadding="0" cellspacing="0" border="0" class="addtable"
				width="100%" align="center">
				<tr>
					<td align="left" colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td align="left" class="list_left_box1" height="20px" width="20%"><b>数据字典：</b></td>
					<td align="left" class="list_left_box1" colspan="3"></td>
				</tr>
				<tr>
					<td class="list_left_box1" valign="top" width="20%">字典值：</td>
					<td class="list_right_box"><input name="dicvalue"
						id="dicvalue" class="mini-textbox"
						value="${jcdictionary.dicvalue}" width="100%" required="true"/></td>
					<td class="list_left_box1" valign="top"width="20%">字典名：</td>
					<td class="list_right_box"><input name="dicname" id="dicname"
						class="mini-textbox" value="${jcdictionary.dicname}" width="100%" required="true"/>
					</td>
				</tr>
				<tr>
					<td class="list_left_box1" valign="top" width="20%">是否可编辑：</td>
					<td class="list_right_box">
					   <input id="isEdit" name="isEdit" class="mini-combobox" value="${jcdictionary.isEdit==null?1:jcdictionary.isEdit}" width="100%" 
					       data="[{id:0,text:'不可编辑'},{id:1,text:'可编辑'}]"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="mini-toolbar"
		style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;"
		borderStyle="border:0;">
		<a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" 
			style="width:60px;margin-right:20px;display:display;" iconCls="icon-save">保存</a> 
		<a class="mini-button" onclick="onCancel" iconCls="icon-close"
			style="width:60px;" iconCls="icon-close">关闭</a>
	</div>
</body>
<script type="text/javascript">
	mini.parse();
	$(document).ready(function() {
		if ('${pid}' != "0") {// 值不可以编辑
			mini.get("isEdit").setEnabled(false);
		} else if ('${pid}' == "0") {// 分类不可以编辑,则不可以编辑
			if ('${jcdictionary.isEdit==null?1:jcdictionary.isEdit}' == "0") {
	            mini.get("isEdit").setEnabled(false);
			} else {
				mini.get("isEdit").setEnabled(true);
			}
        }
		if ('${state}' == "0") {
			mini.get("dicvalue").disable();
		} else mini.get("dicvalue").enable();
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
			url : "../system/dectionary!saveorupdate.action",
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
						callback : function(action) {
							CloseWindow("ok");
						}
					});
				} else {
					if (text.length > 0) {
						mini.showMessageBox({
							title : "提醒",
							width : 250,
							iconCls : "mini-messagebox-warning",
							buttons : [ "ok" ],
							message : text,
							callback : function(action) {
								CloseWindow("ok");
							}
						}); 
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
</script>
</html>
