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
<title>职务z</title>
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
<div class="mini-fit">
    <form id="form1" method="post">
        
        <input class="mini-hidden" name="id" id="id" value="${positionGrade.id}" style="display:none;"/>
        <input class="mini-hidden" name="companyId" id="companyId" value="${branch.id}" style="display:none;"/>
        
        <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
            <tr>
                
                <td class="list_left_box1" valign="top" width="15%">公司名称：</td>
                <td class="list_right_box" width="30%">
                    <input name="companyName" id="companyName" class="mini-textbox" value="${branch.fzx}"
                            readonly="readonly" width="200px"/>
                </td>
                <td class="list_left_box1" valign="top" width="15%">职层：</td>
                <td class="list_right_box" width="30%">
                    <input name="positionLayer" id="positionLayer" class="mini-textbox" value="${positionGrade.positionLayer}"
                            vtype="maxLength:20;required" width="200px"/>
                </td>  
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">序号：</td>
                <td class="list_right_box" width="30%">
                    <input name="sortNo" id="sortNo" class="mini-spinner" value="${positionGrade.sortNo}"
                            vtype="required"  width="200px"/>
                </td> 
                <td class="list_left_box1" valign="top" width="15%">名称：</td>
                
                <td class="list_right_box" width="30%">
                    <input name="name" id="name" class="mini-textbox" value="${positionGrade.name}"
                            vtype="maxLength:50;required" width="200px"/>
                </td> 
            </tr>
            
        </table>
        
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        </div>
        
    </form>
 </div>  
    <script type="text/javascript">
        mini.parse();
        window.onload=onLoad();
        function onLoad(){
        	var id = mini.get("id").getValue();
        	if(id!=null&&id!=""){
        		mini.get("jm").disable();
        	}
        }
        function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "../basis/branch!tree.action",
                showMaxButton: false,
                title: "选择父级分中心",
                width: 250,
                height: 330,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        
                        if(mini.get("id").getValue()==data.id){
                            mini.alert("父级分中心不能是自己！");
                            return;
                        }
                        if (data) {
                            btnEdit.setValue(data.id);
                            btnEdit.setText(data.name);
                        }
                    }
                }
            });
        }
        function isEnglishAndNum(e){
        	var rex = new RegExp("^[A-Za-z]+$");
        	if(rex.test(e)){
        		return true;
        	}else{
        		return false;
        	}
        	
        }
        function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            
            if (form.isValid() == false) return;
            
            
            var data = form.getData();
            var json = mini.encode(data); 
            //strut令牌
            window.parent.loading();
            $.ajax({
                url: "../post/position_grade!save.action",
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
        function onPostCodeEdit(e){
        	var btnEdit = this;
        	var postPositionName = mini.get("postPositionName");
        	var dutyId  = mini.get("dutyId");
        	mini.open({
            url: "../post/post!show.action",
            showMaxButton: false,
            title: "选择标准岗位",
            width: 600,
            height: 500,
            ondestroy: function (action) {
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = mini.clone(data);
                    if (data) {
                        btnEdit.setValue(data.id);
                        btnEdit.setText(data.positionCode);
                        postPositionName.setValue(data.positionName);
                        dutyId.setValue(data.dutyId);
                    }
                }
            }
        });
        }  
    </script>
</body>
</html>