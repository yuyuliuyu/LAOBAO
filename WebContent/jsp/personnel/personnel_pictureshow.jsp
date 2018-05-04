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
<title>上传照片</title>
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
    <div  style="width:100%;float:left" >
    	<input class="mini-hidden" name="imgpath" id="imgpath" value="${imgpath}" style="display:none;"/>
    	<form id="uploaders" method="post" action="../personnel/personnel!uploadFile.action"
       			 enctype="multipart/form-data" encoding="multipart/form-data" target="appFrame">
       		 <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                
                <tr>
                    <tr>
                    <td class="list_left_box1" valign="top" width="112px";>
         请选择上传文件：
                    </td>
                    <td class="list_right_box" colspan="3">
				        <input class="mini-htmlfile" id="uploadFile" name="uploadFile" limitType="*.jpg;*.jpeg;"  text="${license.url}" />
                    	<a class="mini-button" onclick="upload" style="width:100px;margin-right:20px;" iconCls="icon-upload">上传</a>
                    </td>
                </tr>
                </tr>
            </table>
           </form>
    </div>
    <iframe id="frame" name="appFrame" src="" style="display:none;"></iframe>
    <script type="text/javascript">
    mini.parse();
  //导入
    function upload() {
    	var uploadFile = mini.get("uploadFile");
    	if (uploadFile.getValue()=="") {
    		mini.alert("请选择上传文件");
    		return;
    	}
        window.parent.loading();
        // 表单提交
        uploaders.submit();
    } 
  
    function GetData() {
		return mini.get("imgpath").getValue();    	
    } 
  
    //***************************************************//
	// 得到iframe
    var appFrame = document.getElementById('frame');
    // iframe监听事件
    var listener = function() {
        
        // 获取上传之后的iframe返回值
        var doc = appFrame.contentWindow.document;
        var pre = doc.getElementsByTagName('pre');
        // 是否存在(默认会自动生成pre标签)
        if (pre && pre.length > 0) {
        	window.parent.unmask();
            var allmsg = JSON.parse(pre[0].innerHTML);//返回值
            var response=allmsg.msg;//返回msg的值
            if(response=="success"){
            	mini.get("imgpath").setValue(allmsg.fileUrl);//给URL赋值
                mini.showMessageBox({
                    title: "提醒",
                    width: 250,
                    iconCls: "mini-messagebox-warning",
                    buttons: ["ok"],
                    message: "上传成功",
                    callback: function (action) {
                    	CloseWindow("ok");
                    }
                });
            } else {
               response = $("#x").html(response).text();
               if (response.indexOf("@") == 0) {
                   message = response.substring(1, response.length);
                   onCancel();
               } else {
            	   mini.alert(response);
            	   message = "";
               }
            }
        }
    }

    // 判断iframe的监听类型（IE只支持attachEvent，火狐支持addEventListener，W3C标准）
    if (appFrame.addEventListener) {
        appFrame.addEventListener('load', function(evt) {
            listener();
        }, false);
    } else if(appFrame.attachEvent) {
        appFrame.attachEvent('onload', function() {
            listener();
        });
    }
    </script>
</body>
</html>