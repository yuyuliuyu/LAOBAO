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
						<textarea id="nopassReason" name="nopassReason" class="mini-textarea" required="true"
							style="width:97%;height: 240px;" vtype="maxLength:500">${activityApply.nopassReason}</textarea>
					</td>
				</tr>
			</table>
			<table border="0" width="90%" id="table1" align="center" height="58">
				<tr class=TableLine2>
					<td align="center">
						<input type="button" onClick="pass()" value="" class="biaogebtn13" /> 
						<input type="button" onClick="noPass()" value="" class="biaogebtn14" /> 
						<input type="button" onClick="onCancel()" value="" class="biaogebtn3" />
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
<script type="text/javascript">
	mini.parse();

	//审核通过
	function pass() {
		//手动判断意见
		if (mini.get("nopassReason").value.trim().length < 1) {
			mini.alert("请填写审核意见！");
			return;
		}
		if (mini.get("nopassReason").value.length > 500) {
			mini.alert("审核意见不能超过500个字符！");
			return;
		}
		//提交数据
		$.ajax({
			url : "../check/overtime!saveApprove.action",
			type : 'post',
			data : {
				comment : mini.get("nopassReason").getValue(),
				id : mini.get("id").getValue(),
				approveStatus : "1"
			},
			success : function(text) {
				if (text != "error") {
					mini.alert(text, "提示", function() {
						onCancel();
					});
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				mini.alert("操作失败！");
			}
		});
	}
	//审核不通过
	function noPass() {
		//判断审核意见
		if (mini.get("nopassReason").value.trim().length < 1) {
			mini.alert("请填写审核意见！");
			return;
		}
		if (mini.get("nopassReason").value.length > 500) {
			mini.alert("审核意见不能超过500个字符！");
			return;
		}
		//提交数据
		var form = new mini.Form("form1");
		form.validate();
		if (form.isValid() == false) {
			return;
		}
		$.ajax({
			url : "../check/overtime!saveApprove.action",
			type : 'post',
			data : {
				comment : mini.get("nopassReason").getValue(),
				id : mini.get("id").getValue(),
				approveStatus : "2"
			},
			success : function(text) {
				if (text != "error") {
					mini.alert(text, "提示", function() {
						onCancel();
					});
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				mini.alert("操作失败！");
			}
		});
	}
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