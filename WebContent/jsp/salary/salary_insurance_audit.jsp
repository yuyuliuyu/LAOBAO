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
    <title>导入社保缴费信息</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/biguploader/uploader.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit" id="form1">
        <input class="mini-hidden" name="depId" value="${depId}"/>
        <input class="mini-hidden" name="effectiveYear" value="${effectiveYear}"/>
        <input class="mini-hidden" name="effectiveMonth" value="${effectiveMonth}"/>
        <input class="mini-hidden" name="column" value="${column}"/>
        <form id="uploaders" method="post" enctype="multipart/form-data" encoding="multipart/form-data" target="appFrame">
            <table style="width:100%;height:100%">
                <tr>
                    <td class="list_left_box1" valign="top">保险项目：</td>
                    <td class="list_right_box">
                        <input class="mini-radiobuttonlist" repeatItems="1" repeatDirection="vertical" id="insuranceId" name="insuranceId" value="${insuranceId}"
                            url="../salary/salary_insurance!getCurCompanyInsurance.action?depId=${depId}&effectiveYear=${effectiveYear}&effectiveMonth=${effectiveMonth}" />
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="28%">请选择上传文件：</td>
                    <td class="list_right_box">
                            <input class="mini-htmlfile" id="uploadFile" name="uploadFile" limitType="*.xls;*.xlsx" style="width:100%"
                                onfileselect= "onFileSelected"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="upload()" style="width:60px;margin-right:20px;" iconCls="icon-ok">导入</a>
        <a class="mini-button" onclick="onOkClose()" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();

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
            bigWebUploader.submit(callback);
        }

        function onFileSelected(e) {
            var form = new mini.Form("#form1");
            var formjson="";
            if(form){
                // 提交表单数据
                var formdata = form.getData();
                formjson = mini.encode(formdata); 
            }
            bigWebUploader.setUrl("uploaders", "../salary/salary_insurance!beachCheck.action?formdata=" + formjson);
        }

        function callback(data) {
            window.parent.unmask();
            if (data == "success") {
                mini.showMessageBox({
                    title: "提醒",
                    width: 250,
                    iconCls: "mini-messagebox-warning",
                    buttons: ["ok"],
                    message: "导入人员信息成功",
                    callback: function (action) {
                        onOkClose();
                    }
                });
            } else {
                mini.alert(data);
            }
        }

        /**
         * 关闭
         * zhanghj
         **/
        function onOkClose() {
            CloseWindow("ok");
        }

        /** 关闭窗口 */
        function CloseWindow(action) {
            if (window.CloseOwnerWindow)
                return window.CloseOwnerWindow(action);
            else
                window.close();
        }
    </script>
</body>
</html>