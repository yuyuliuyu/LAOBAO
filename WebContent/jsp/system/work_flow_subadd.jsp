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
<title>增加工作流节点</title>
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
        <input class="mini-hidden" name="id" id="id" value="${workflowc.id}" style="display:none;"/>
        <input class="mini-hidden" name="pid" id="pid" value="${workflowc.pid}" style="display:none;"/>
        <div>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
               <tr>
                    <td class="list_left_box1" valign="top" width="30%">节点序列：</td>
                    <td class="list_right_box" width="60%">
                        <input name="sort" id="sort" class="mini-textarea" readonly="readonly" value="${workflowc.sort}"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" width="200px"/>
                    </td>
                </tr>
                <tr>
					<td class="list_left_box1" valign="top" width="30%">审批岗位：</td>
					<td class="list_right_box" width="60%">
					
                    <input name="appid" id="appid" class="mini-buttonedit" value="${workflowc.appid}"  
                    text="${workflowc.appman}" vtype="maxLength:50;required" width="200px" 
                    emptyText="请选择..."  onbuttonclick="onButtonEdit" />
                
                            <%-- 
                         <input id="appid" name="appid" class="mini-buttonedit" onbuttonclick="onButtonEdit" width="200px"
                                value="${workflowc.appid}" text="${workflowc.appman}" allowInput="false"/>  --%>
					</td>
				</tr>
                <tr>
					<td class="list_left_box1" valign="top" width="30%">审核后编辑：</td>
					<td class="list_right_box" width="60%">
                         <input id="isCheckEdit" name="isCheckEdit" class="mini-combobox" width="200px" data="[{id:0,text:'否'},{id:1,text:'是'}]" required="true"
                                value="${workflowc.isCheckEdit}" text="${workflowc.isCheckEdit}"/> 
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
        window.onload=onLoad();
        function onLoad(){
        	var id = mini.get("id").getValue();
        	if(id!=null&&id!=""){ 
        	}
        }


    	function onButtonEdit(e) {
            var btnEdit = this; 
            mini.open({
                url: "../post/post!show.action", 
                showMaxButton: false,
                title: "选择标准岗位",
                width: 700,
                height: 500,
                ondestroy: function (action) {
 
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        if (data) {
                             btnEdit.setValue(data.id);
                             btnEdit.setText(data.positionName); 
                        }
                    }
                }
            });
            
        } 
        
        function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            
            if (form.isValid() == false) return;
              
            var id = mini.get("id").getValue();
            var pid = mini.get("pid").getValue();
            var data = form.getData();
            var json = mini.encode(data);
            //strut令牌
            window.parent.loading();
            $.ajax({
                url: "../system/work_flow!saveorupdate_c.action",
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