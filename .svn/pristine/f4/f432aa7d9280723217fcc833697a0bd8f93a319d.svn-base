<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
    <title>高拍仪拍照</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/idcard/JieYuCamera.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit" style="padding:16px">
        <object classid="clsid:454C18E2-8B7D-43C6-8C17-B1825B49D7DE"
            id="captrue" width="100%" height="100%"></object>
    </div>
    <input type="text" id = "path" value="${path}" style="display: none;"/>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onCamera()" style="width:60px;margin-right:20px;" iconCls="icon-save">拍照</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var jyCamera;
        var init = true;
        var pic="";
        $(function() {
            jyCamera = new JyCamera("captrue");
            try {
            	var open = jyCamera.openMainCamera(0);
                if (!open) {
                    mini.alert("摄像头启动失败");
                    init = false;
                    return;
                }
            } catch (error) {
                mini.alert("检测不到高拍仪设备，请刷新页面或者 <a href='../system/xiazai!fileDownload.action?name=highCamera'>驱动下载</a>");
                init = false;
                return;
            }
        });

        /**
         * 利用高拍仪拍照
         * zhanghj
         **/
        function onCamera() {
            if (!init) {
                mini.alert("检测不到高拍仪设备，请刷新页面或者 <a href='../system/xiazai!fileDownload.action?name=highCamera'>驱动下载</a>");
                return;
            }
            window.parent.loading();
            var date = new Date();
            var file = "HC_" + date.getTime();
            var base64 = jyCamera.getBase64ForType(4);
            $.ajax({
                url: "../upload!uploadBase64.action?path="+$("#path").val(),
                type : "POST",
                data:{
                    formdata: base64
                },
                success: function (text) {
                    window.parent.unmask();
                    // 解析
                    var data = mini.decode(text);
                    if (!data.success) {
                        mini.alert(data.msg);
                        return;
                    } else {
                        pic = data.filepath;
                        onCancel();
                        return;
                    }
                }
            });
        }

        function destroy() {
            if (init) {
            	jyCamera.stopCamera();
            }
            return pic;
        }

        /** 关闭窗口 */
        function CloseWindow(action) {
            if (window.CloseOwnerWindow)
                return window.CloseOwnerWindow(action);
            else
                window.close();
        }

        /** 关闭按钮点击事件 */
        function onCancel() {
            destroy();
            CloseWindow("cancel");
        }

    </script>
</body>
</html>