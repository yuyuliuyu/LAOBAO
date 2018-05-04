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
<title>首页修改个人信息页面</title>
<link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css" />
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
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

<body style="height:100%">
    <center style="height:100%">
        <div style="height:100%">
            <form id="form1" method="post">
                
                <input class="mini-hidden" id="id" name="id" value="${id}" style="display:none;"/>
                <input class="mini-hidden" id="depId" name="depId" value="${admin.depId}" style="display:none;"/>
                
                <div style="height:100%">
                    <table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%" class="addtable">
                        <tr>
                            <td class="list_left_box1" valign="top" width="20%">
                                <label class="requireField" style="color:red">*</label>用户名：
                            </td>
                            <td class="list_right_box" width="40%" align="left">
                                <s:if test="isAddAction">
                                    <input class="mini-textbox" id="username" name="username" value="${admin.username}"
                                           required="true" requiredErrorText="用户名不能为空" width="200px"/>
                                </s:if>
                                <s:else>
                                    <input class="mini-textbox" id="username" name="username" value="${admin.username}"
                                           required="true" requiredErrorText="用户名不能为空" enabled="false" width="200px"/>
                                </s:else>
                            </td>
                        </tr>
                        <tr>
                            <td class="list_left_box1" valign="top" width="20%">登录密码：</td>
                            <td class="list_right_box" width="40%" align="left">
                                <input class="mini-password" id="password" name="password" width="200px"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="list_left_box1" valign="top" width="20%">重复登录密码：</td>
                            <td class="list_right_box" width="40%" align="left">
                                <input class="mini-password" id="rePassword" name="rePassword" width="200px"/>
                            </td>
                        </tr>
                        <s:if test="#request.show==1">
                           <tr>
                                <td class="list_left_box1" valign="top" width="20%">
                                        交接密码：
                                </td>
                                <td class="list_right_box" width="40%" align="left">
	                                <input class="mini-password" id="reciveCode" name="reciveCode" width="200px"/>
	                            </td>
                          </tr>
                        </s:if>
                        <tr>
                                <td colspan="2">&nbsp;
                                <span class="warnInfo"><span class="icon">&nbsp;</span>如果要修改密码，请填写密码；若留空，密码将保持不变！</span>
                                </td>
                        </tr>
                        <tr>
                           <td colspan="2">
                            <fieldset style="border:solid 1px #aaa;padding:3px;">
                                <legend>基本信息</legend>
                                <div style="padding:5px;">
                                    <table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%" class="addtable">
                                        <tr>
                                            <td class="list_left_box1" valign="top" width="20%">
                                                <label class="requireField" style="color:red">*</label>姓名：
                                            </td>
                                            <td class="list_right_box" width="40%" align="left">
                                                <input class="mini-textbox" id="name" name="name" value="${admin.name}"
                                                       required="true" requiredErrorText="姓名不能为空" vtype="maxLength:60" width="200px"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="list_left_box1" valign="top" width="20%">
                                                <!-- <label class="requireField" style="color:red">* --></label>邮箱：
                                            </td>
                                            <td class="list_right_box" width="40%" align="left">
                                                <input class="mini-textbox" id="email" name="email" value="${admin.email}"
                                                       vtype="email;maxLength:100" width="200px"/>
                                                       <!-- required="true" requiredErrorText="邮箱不能为空"  -->
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </fieldset>
                          </td>
                        </tr>
                        <tr>
                            <td colspan="2" valign="bottom">
                                <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
                                     <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
                                     <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
                                     <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </center>
    
    <script type="text/javascript">
        mini.parse();
        
        var form = new mini.Form("form1");
        
        //是否英文/数字/下划线6-16位以内（和登录验证保持一致）
        /* function isLoginFormat(v) {
            var re = new RegExp("^[0-9a-zA-Z\_]{6,16}$");
            if (re.test(v))
                return true;
            return false;
        } */
        
        ////////////////////////////////////////
        function SaveData() {
            var data = form.getData();
            form.validate();
            
            if (form.isValid() == false)
                return;
            
            var username=mini.get("username").getValue();//用户名
            var password=mini.get("password").getValue();//密码
            var rePassword=mini.get("rePassword").getValue();//重复密码
            var name=mini.get("name").getValue();//姓名
            
            var reg=/^(\w|[\u4E00-\u9FA5])*$/;//是否中文、字母、数字或下划线
            
            if(username!=null && username!=""){
                if(username.length<2 || username.length>16){
                    mini.alert("用户名必须满足长度在2-16之间，只能包含中文、字母、数字或下划线！");
                    return;
                }else{
                    if (!reg.test(username)) {
                        mini.alert("用户名必须满足长度在2-16之间，只能包含中文、字母、数字或下划线！");
                        return;
                    }
                }
            }
            
            if(password!=null && password!=""){
                if(password.length<3 || password.length>16){
                    mini.alert("密码必须满足长度在3-16之间，只能包含中文、字母、数字或下划线！");
                    return;
                }else{
                    if (!reg.test(password)) {
                        mini.alert("密码必须满足长度在3-16之间，只能包含中文、字母、数字或下划线！");
                        return;
                    }
                }
            }
            
            if(rePassword!=null && rePassword!=""){
                if(rePassword.length<3 || rePassword.length>16){
                    mini.alert("重复密码必须满足长度在3-16之间，只能包含中文、字母、数字或下划线！");
                    return;
                }else{
                    if (!reg.test(rePassword)) {
                        mini.alert("重复密码必须满足长度在3-16之间，只能包含中文、字母、数字或下划线！");
                        return;
                    }
                }
            }
            
            var reg1 = /^[^_]+$/;//是否包含下划线
            var reg2 = /^(\w|[\u4E00-\u9FA5])*$/;//是否中文、字母、数字或下划线
            
            if(name!=null && name!=""){
                if (!reg1.test(name)) {
                    mini.alert("姓名必须由中文、字母或数字组成！");
                    return;
                }else{
                    if (!reg2.test(name)) {
                        mini.alert("姓名必须由中文、字母或数字组成！");
                        return;
                    }
                }
            }
            
            var json = mini.encode(data);
            
            //strut令牌
            window.parent.loading();
            $.ajax({
                url: "admin!savetwo.action",
                type: 'post',
                data: { data: json},
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
        
        ////////////////////////////////////////
        //标准方法接口定义
        function SetData(data) {
            if (data.action == "edit") {
                //跨页面传递的数据对象，克隆后才可以安全使用
                data = mini.clone(data);
                
                $.ajax({
                    url: "../data/AjaxService.jsp?method=GetEmployee&id=" + data.id,
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);
                        form.setChanged(false);
                        onDeptChanged();
                        mini.getbyName("position").setValue(o.position);
                    }
                });
            }
        }
        
        ////////////////////////////////////////
        function GetData() {
            var o = form.getData();
            return o;
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
        
        function onOk(e) {
            SaveData();
        }
        
        function onCancel(e) {
            CloseWindow("cancel");
        }
        
        function onReload() {
            document.location.reload();
        }
    </script>
</body>
</html>