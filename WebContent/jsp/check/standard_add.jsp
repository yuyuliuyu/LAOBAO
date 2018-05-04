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
<title>增加考勤标准信息</title>
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
</style>
</head>
<body>
    <form id="form1" method="post">
        <input class="mini-hidden" name="id" id="id" value="${ckStandard.id}" style="display:none;"/>
        <div>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">年月份：</td>
                    <td class="list_right_box" width="60%">
                        <input name="yearCalendar" id="yearCalendar" class="mini-textbox" value="${ckStandard.yearCalendar}"
                            required="true" requiredErrorText="不能为空" emptyText="请输入年月份：如 1704" vtype="maxLength:4" width="200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">月历天数：</td>
                    <td class="list_right_box" width="60%">
                        <input name="monthDays" id="monthDays" class="mini-textbox" value="${ckStandard.monthDays}"
                               required="true" requiredErrorText="不能为空" emptyText="请输入月历天数：如 31" vtype="maxLength:2" width="200px"/>天
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">工作日天数：</td>
                    <td class="list_right_box" width="60%">
                        <input name="workDays" id="workDays" class="mini-textbox" value="${ckStandard.workDays}"
                               required="true" requiredErrorText="不能为空" emptyText="请输入工作日天数：如 20" vtype="maxLength:2" width="200px"/>天
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">公休日天数：</td>
                    <td class="list_right_box" width="60%">
                        <input name="restDays" id="restDays" class="mini-textbox" value="${ckStandard.restDays}"
                               required="true" requiredErrorText="不能为空" emptyText="请输入公休日天数：如 10" vtype="maxLength:2" width="200px"/>天
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">节假日天数：</td>
                    <td class="list_right_box" width="60%">
                        <input name="holidayDays" id="holidayDays" class="mini-textbox" value="${ckStandard.holidayDays}"
                               required="false" vtype="maxLength:2" width="200px"emptyText="请输入节假日天数：如 3"/>天
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">节一日期：</td>
                    <td class="list_right_box" width="60%">
                        <input name="festival1" id="festival1" class="mini-textbox" value="${ckStandard.festival1}"
                               required="false" vtype="maxLength:2" width="200px"emptyText="请输入日：如 01" />
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">节二日期：</td>
                    <td class="list_right_box" width="60%">
                        <input name="festival2" id="festival2" class="mini-textbox" value="${ckStandard.festival2}"
                               required="false" vtype="maxLength:2" width="200px"emptyText="请输入日：如 02" />
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">节三日期：</td>
                    <td class="list_right_box" width="60%">
                        <input name="festival3" id="festival3" class="mini-textbox" value="${ckStandard.festival3}"
                               required="false" vtype="maxLength:2" width="200px"emptyText="请输入日：如 03" />
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">节四日期：</td>
                    <td class="list_right_box" width="60%">
                        <input name="festival4" id="festival4" class="mini-textbox" value="${ckStandard.festival4}"
                               required="false" vtype="maxLength:2" width="200px"emptyText="请输入日：如 04" />
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        </div>
    </form>
    <script type="text/javascript">
        mini.parse();
        
        function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            
            var data = form.getData();
            var json = mini.encode(data); 
            //strut令牌
            window.parent.loading();
            $.ajax({
                url: "../check/standard!saveOrUpdate.action",
                data:{
                	formdata:json
                },
                success: function (text) {
                    window.parent.unmask();
                    if (text == "success") {
                        mini.showMessageBox({
                            title: "提醒",
                            width: 250,
                            iconCls: "mini-messagebox-warning",
                            buttons: ["ok"],
                            message: "保存成功！",
                            callback: function (action) {
                                CloseWindow("ok");
                            }
                        }); 
                    } else {
                        if(text.length>0){
                            mini.alert(text);
                        }
                    }
                }/* ,
                error : function(jqXHR, textStatus, errorThrown) {
                   // mini.alert(jqXHR.responseText);
                } */
            });
        }
        
        function onCancel(e) {
             CloseWindow("cancel");
        }
        
        function onReload() {
            document.location.reload();
        }
        
        function CloseWindow(action) {
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }    
    </script>
</body>
</html>