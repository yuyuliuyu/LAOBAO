<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
    String flag=request.getParameter("flag");
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>员工奖励信息</title>
<link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css" />
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<script src="../template/plugin/public/add.js" type="text/javascript"></script>
<link href= "../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
<!--引入皮肤样式-->
<link href="${base}/template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    html, body
    {
        font-size:12px;
        padding:0;
        margin:0;
        border:0;
        height:100%;
        overflow:hidden;
    }
    .check_box
    {
        float:left;
        margin-right:5px;
        *margin-right:15px !important;
    }
    
    input[type="checkbox"]
    {
        vertical-align:-3px;
    }
 </style>
</head>

<body>
	<div class="mini-fit" > 
        <form id="uploaders" method="post" action="../contract/contract_manager!uploadimage.action?id=${id}"
       			 enctype="multipart/form-data" encoding="multipart/form-data" target="_self">
       		 <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%"> 
                     
				<tr>
					<td class="list_left_box1" valign="top">文件名称：</td>
					<td class="list_right_box"><input name="imagename" id="imagename"
						class="mini-textbox" value="${contract.imagename}" width="100%" />
					</td>
					<td class="list_left_box1" valign="top" >备注：</td>
					<td class="list_right_box"><input name="remark"
						id="remark" class="mini-textbox"
						value="${contract.remark}" width="100%" /></td>
				</tr>
                    <tr>
                    <td class="list_left_box1" valign="top" width="112px";>
			<input class="mini-hidden" name="id" id="id" value="${id}" style="display:none;" />  
			<input class="mini-hidden" name="formid" id="formid" value="${formid}" style="display:none;" />  
			<input class="mini-hidden" name="formcode" id="formcode" value="${formcode}" style="display:none;" />
         请选择上传文件：
                    </td>
                    <td class="list_right_box" colspan="3">
				            <input class="mini-htmlfile" id="uploadFile" name="uploadFile" limitType="*.xls;*.xlsx;*.txt;*.jpg;*.jpeg;*.gif;*.png;*.doc" text="${award.url}" />
                    	<a class="mini-button" onclick="upload" style="width:100px;margin-right:20px;" iconCls="icon-upload">上传</a>
                    </td>
                </tr>
                <tr>
					<td align="left"  colspan="6"><font color="red" size="5" >${msg }</font> </td> 
                </tr>	
            </table>
           </form>
        </div> 
		<div class="mini-toolbar"
			style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;"
			borderStyle="border:0;"> 
			<a class="mini-button" onclick="onCancel" iconCls="icon-close"
				style="width:60px;" iconCls="icon-close">关闭</a>
		</div>
    </div>
    <script type="text/javascript">
    mini.parse(); 
	//*************************************//
 
	//***************************************************//
     function upload() {
    	var uploadFile = mini.get("uploadFile");
    	if (uploadFile.getValue()=="") {
    		mini.alert("请选择上传文件");
    		return;
    	}  
        uploaders.submit(); 
        
    }
    //关闭当前页面
    function onOkClose() {
    	CloseWindow("ok");
    }
	function CloseWindow(action) {
		if (action == "close" && form.isChanged()) {
			if (confirm("数据被修改了，是否先保存？")) {
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
</body>
</html>