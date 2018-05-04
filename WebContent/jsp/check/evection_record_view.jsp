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
<title>查看出差申请</title>
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
</style>
</head>
<body>
	<form id="form1" method="post">
		<div>
			<input class="mini-hidden" name="id" id="id" value="${evection.id}" style="display:none;" />   
			<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
				<tr>
					<td align="left" colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td align="left" class="list_left_box1" height="20px" colspan="4" style="text-align:center;"><b>出差申请</b></td>
				</tr>
				 <tr>
                    <td class="list_left_box1" valign="top" width="18%">员工工号：</td>
                    <td class="list_right_box" width="32%">
                        <input class="mini-textbox" value="${evection.jobNumber}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" width="150px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="18%">员工姓名：</td>
                    <td class="list_right_box" width="32%">
                        <input class="mini-textbox" value="${evection.applyName}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" width="150px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1">出差开始时间：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" allowInput="false" enabled="false"
					      	style="width:150px;" format="yyyy-MM-dd" required="true" 
					      	value="${evection.startDate }"/>
                    </td>
                    <td class="list_left_box1">出差结束时间：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" allowInput="false" 
					      	style="width:150px;" format="yyyy-MM-dd" required="true" enabled="false"
					      	value="${evection.endDate }"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1">出差天数：</td>
                    <td class="list_right_box">
                        <input class="mini-textbox" value="${evection.evectionDay}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" width="150px"/>
                    </td>
                    <td class="list_left_box1">出差类型：</td>
                    <td class="list_right_box">
                        <input field="evectionType" required="true" enabled="false"
							class="mini-combobox" textField="text" valueField="id" emptyText=""  
							width="150px" value="${evection.evectionType}" url="../template/evectionType.txt"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1">流程选择：</td>
                	<td class="list_right_box" colspan="3">
                        <input field="processId" required="true" enabled="false"
							class="mini-combobox" textField="text" valueField="id" emptyText=""  
							width="450px" value="${evection.processId}" url="../system/work_flow!getworkflow.action?type=1"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1">备注：</td>
                    <td class="list_right_box" colspan="3">
                        <input class="mini-textarea" value="${evection.remark}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" 
                            style="width:100%;height:100px;"/>
                    </td>
                </tr>
			</table>
		</div>
	</form>
</body>
<script type="text/javascript">
	mini.parse();
		
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
