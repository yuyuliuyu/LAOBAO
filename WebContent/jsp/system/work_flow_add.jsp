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
<title>增加工作流</title>
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
    <form id="form1" method="post">
        <input class="mini-hidden" name="id" id="id" value="${workflowp.id}" style="display:none;"/>
        <div>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
               <tr>
                    <td class="list_left_box1" valign="top" width="30%">流程名称：</td>
                    <td class="list_right_box" width="60%">
                        <input name="name" id="name" class="mini-textarea" value="${workflowp.name}"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" width="200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">流程类型：</td>
                    <td class="list_right_box" width="60%">
					    <input class="mini-combobox" value="${workflowp.type}" name="type" id="type"emptyText="请选择..."style="width: 200px"textField="dicname" valueField="dicvalue"
                            required="true" url="../system/dectionary!listjson.action?id=4028818d5b7046b2015b707328fa0031"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="30%">是否启用：</td>
					<td>
						<input name="state" id="state" required="true" repeatItems="3" 
							class="mini-radiobuttonlist" textField="text" valueField="id" repeatDirection="vertical"  
							width="300px" value="${workflowp.state}" url="../template/using.txt"/>
					</td>
				</tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">说明：</td>
                    <td class="list_right_box">
                        <input name="memo" id="memo" class="mini-textarea" value="${workflowp.memo}"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" width="100%" height="72px"/>
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
    
    <script type="text/javaScript">
        mini.parse();
        window.onload=onLoad();
        function onLoad(){
        	var id = mini.get("id").getValue();
        	if(id!=null&&id!=""){
        		//mini.get("jm").disable();
        	}
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
                url: "../system/work_flow!saveorupdate_p.action",
                data:{
                	jsondate:json
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
                }
                /* ,
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

 		function onButtonEdit(e) {
            var btnEdit = this;
//            var departName=mini.get("departName");
//            var filmId=mini.get("filmId");//公司ID
//            var filmName=mini.get("filmName");//公司
            
            mini.open({
                url: "../basis/branch!comptree.action",
                showMaxButton: false,
                title: "选择部門",
                width: 250,
                height: 330,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                    
                        if (data) {
                            btnEdit.setValue(data.id);
                            btnEdit.setText(data.name);
                            departName.setValue(data.name);
                            filmId.setValue(data.branchId);
                            $.ajax({
                                url: "../personnel/change!findFilmName.action?filmId="+data.branchId,
                                success: function (text) {
                                    if(text!=null){
                                    	filmName.setValue(text);
                                    }
                                }
                            }); 
                        }
                    }
                }
            });
            
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