<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path;
            pageContext.setAttribute("base", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>导入信息</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit" id="form1" style="height: 10%;border-bottom:0;padding:0px;">
        <form id="uploaders" method="post" action="../laobao/wupin_input!importSave.action"
            enctype="multipart/form-data" encoding="multipart/form-data" target="appFrame">
            <table style="width:100%;height:100%">

                <tr>
                    <td class="list_left_box1" valign="top" width="32%">请选择上传文件：</td>
                    <td class="list_right_box">
                            <input class="mini-htmlfile" id="uploadFile" name="uploadFile" limitType="*.xls;*.xlsx" style="width:100%"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <span id="x" style="display:none"></span>
    <iframe id="frame" name="appFrame" src="" style="display:none;"></iframe>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" id="ok" onclick="upload()" style="width:60px;margin-right:20px;" iconCls="icon-save">导入</a>
        <a class="mini-button" onclick="onOkClose()" style="width:60px;" iconCls="icon-close">关闭</a>
    </div>

    <script type="text/javascript">
        var message = "";
        // 得到iframe
        var appFrame = document.getElementById('frame');
        // iframe监听事件
        var listener = function() {
            window.parent.unmask();
            // 获取上传之后的iframe返回值
            var doc = appFrame.contentWindow.document;
            var pre = doc.getElementsByTagName('pre');
            // 是否存在(默认会自动生成pre标签)
            if (pre && pre.length > 0) {
                var response = pre[0].innerHTML;
                if(response=="success"){
                    mini.showMessageBox({
                        title: "提醒",
                        width: 250,
                        iconCls: "mini-messagebox-warning",
                        buttons: ["ok"],
                        message: "导入信息成功",
                        callback: function (action) {
                        	onOkClose();
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

        /**
         * 匹配消息内容
         * zhanghj
         **/
        function GetData() {
        	return message;
        }

        /**
         * 导入
         * zhanghj
         **/
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


        /**
         * 关闭
         * zhanghj
         **/
        function onOkClose() {
        	CloseWindow("ok");
        }
    </script>
</body>
</html>