<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>转移部门页面</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <!--引入皮肤样式-->
    <link href="../template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    <link href="${base}template/system/css/tiao.css" rel="stylesheet" type="text/css" />
</head>
<body>
    
    
    <div class="selbu" align="center" >
	    <div class="bumen" align="center">
	        <b>请选择"${name}"要归属的部门： </b>
	    </div>
        <input id="btnEdit1" name="btnEdit1"class="mini-buttonedit" onbuttonclick="onButtonEdit" text="${department.name}"value="${department.id }" width="200px"/>
        <div class="bumen" align="center"> <b>是否同时存在两个部门中:</b>
        </div>
        <input id="sftscz" name="sftscz"class="mini-combobox"  textField="text" valueField="id" value="0" width="200px"/>
         
        <input class="mini-hidden" name="id" id="id" value="${id}" style="display:none;"/>
        <input class="mini-hidden" name="bid" id="bid" value="${depId}" style="display:none;"/>
        <input class="mini-hidden" name="cid" id="cid" value="${admin.cid}" style="display:none;"/>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
         <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
         <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
         <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
    </div>
    
    <script type="text/javascript">
        mini.parse();
        var sftscz = [{"id":0,"text":"否"},{"id":1,"text":"是"}];
        mini.get("sftscz").setData(sftscz);
        var oldDid = mini.get("bid").getValue();
        function onButtonEdit(e) {
            var btnEdit = this;
            var cid = mini.get("cid").getValue();
            mini.open({
                url: "../depart/backend_dep!tree1.action?cid="+cid,
                showMaxButton: false,
                title: "选择部门",
                width: 250,
                height: 300,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        
                        if (data) {
                        	if(oldDid==data.id){
                        		mini.alert("要转移的部门和原来所在部门时同一个!");
                        		return;
                        	}else{
                        		btnEdit.setValue(data.id);
                                btnEdit.setText(data.name);
                        	}
                            
                        }
                    }
                }
            });
        } 
        
        function onCancel(e) {
            CloseWindow("cancel");
        }
        
        function onReload() {
            document.location.reload();
        }
        
        function onOk(){
        	var bid = mini.get("btnEdit1").getValue();
        	var id = mini.get("id").getValue();
            if(bid==""||bid=="null"){
                mini.alert("请选择部门！");
                return;
            }
            var flag = mini.get("sftscz").getValue();
            mini.confirm("您确定要转移该信息吗？", "提醒", function(action) {
                if (action == "ok") {
                	window.parent.loading();
                    $.ajax({
                        url: "admin!changedep.action",
                        type:'post',
                        data:{id:id,
                              data:bid,flag:flag,depId:oldDid
                              },
                        success : function(text) {
                        	 window.parent.unmask();
                            if (text == "success") {
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "转移成功！",
                                    callback: function (action) {
                                        document.location.reload();
                                        window.CloseOwnerWindow();
                                    }
                                }); 
                            } else {
                                mini.alert(text);
                            }
                        }/* ,
                        error : function(jqXHR, textStatus, errorThrown) {
                            mini.alert(jqXHR.responseText);
                        } */
                    });
                }
            });
        }
        
        function CloseWindow(action) {
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }
    </script>
</body>
</html>