<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link href="${base}template/system/css/table.css" rel="stylesheet" type="text/css" />
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
</head>
<body id="body">
	<form id="form1">
		<div class="biao" style="width:100%;height:100%; font-family:宋体;">
			<input class="mini-hidden" name="id" id="id" value="${activityApply.id}" /> 
			<table class="zhifabg" border="1px" width="100%" height="100%" border="1" align="center">
				<tr>
					<td align="right" id="biaotite" width="15%">意见：</td>
					<td colspan="3">
						<textarea id="nopassReason" name="nopassReason" class="mini-textarea" readonly="true"
							style="width:97%;height: 240px;" vtype="maxLength:500">${activityApply.nopassReason}很好，通过了</textarea>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
<script type="text/javascript">
	mini.parse();

	//取消
	function onCancel(e) {
		CloseWindow("cancel");
	}
	function CloseWindow(action) {         
        if (window.CloseOwnerWindow){
        	return window.CloseOwnerWindow(action);
        } else {
        	window.close();
        }
    } 
</script>
</html>