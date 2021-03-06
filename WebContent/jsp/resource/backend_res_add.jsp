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
<title>增加资源</title>
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
    /* 文本框只读 */
    .asLabel .mini-textbox-border,
    .asLabel .mini-textbox-input,
    .asLabel .mini-buttonedit-border,
    .asLabel .mini-buttonedit-input,
    .asLabel .mini-textboxlist-border
    {
        border:0;background:none;cursor:default;
    }
    .asLabel .mini-buttonedit-button,
    .asLabel .mini-textboxlist-close
    {
        display:none;
    }
    .asLabel .mini-textboxlist-item
    {
        padding-right:8px;
    }
 </style>
</head>

<body>
    <form id="form1" method="post">
        
        <input class="mini-hidden" name="id" id="id" value="${resource.id}" style="display:none;"/>
        <input class="mini-hidden" name="size" id="size" value="${resource.moduleid}" style="display:none;"/>
        <input class="mini-hidden" name="cid" id="cid" value="${child}" style="display:none;"/>
        
        <div>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">资源名称：</td>
                    <td class="list_right_box" width="60%">
                        <s:if test="resource.id!=null">
                            <input name="name" id="name" class="mini-textbox" value="${resource.resourcename}"
                                   enabled="false" vtype="maxLength:50" width="200px"/>
                        </s:if>
                        <s:else>
                            <input name="name" id="name" class="mini-textbox" value="${resource.resourcename}" 
                                   required="true" requiredErrorText="不能为空" vtype="maxLength:50" width="200px"/> 
                            </s:else>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">资源描述：</td>
                    <td class="list_right_box" width="60%">
                        <input name="description" id="description" class="mini-textarea" value="${resource.description}"
                               required="true" requiredErrorText="不能为空" vtype="maxLength:100" width="200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">资源路径：</td>
                    <td class="list_right_box" width="60%">
                         <input name="url" id="url" class="mini-textbox" value="${resource.resourceurl}" 
                                required="true" requiredErrorText="不能为空" vtype="maxLength:100" width="200px"/> 
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">资源图标：</td>
                    <td class="list_right_box" width="60%">
                        <input name="imgvalue" id="imgvalue" class="mini-textbox" value="${resource.imgvalue}"
                               vtype="maxLength:50" width="200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">父级资源：</td>
                    <td class="list_right_box" width="30%">
                         <input id="btnEdit1" class="mini-buttonedit" onbuttonclick="onButtonEdit" width="200px"
                                value="${resource.presource}" text="${resource.presource}" allowInput="false"/>
                    </td>
                </tr>
                <%-- <s:if test="resource.id!=null">
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">创建时间：</td>
                    <td class="list_right_box" width="60%">
                        <input name="createdate" id="createdate" class="mini-datepicker" width="200px"
                               format="yyyy-MM-dd HH:mm:ss" value="${resource.createDate}" enabled="false"/>
                    </td>
                </tr>
                </s:if> --%>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">菜单显示：</td>
                    <td class="list_right_box" width="60%">
                      <select id="onshow" name="onshow" >
                           <s:if test="resource.state==1">
                                <option value="1" selected="selected">是</option>
                           </s:if>
                           <s:else>
                                <option value="1">是</option>
                           </s:else>
                           <s:if test="resource.state==0">
                                <option value="0" selected="selected">否</option> 
                           </s:if>
                           <s:else>
                                <option value="0">否</option> 
                           </s:else> 
                        </select>
                    </td>
                </tr>
                <!-- <tr>
                    <td class="list_left_box1" valign="top" width="30%">添加权限：</td>
                    <td class="list_right_box" width="60%">
                        <select id="onauth" name="onauth" >
                            <option value="0" selected="selected">否</option> 
                            <option value="1" >是</option>
                        </select>
                    </td>
                </tr> -->
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">排列次序：</td>
                    <td class="list_right_box" width="60%">
                        <input name="sortorder" id="sortorder" class="mini-textbox" value="${resource.sortorder}" 
                               vtype="float;maxLength:5" required="true" requiredErrorText="不能为空" width="200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">模块名称：</td>
                    <td class="list_right_box" width="60%">
                        <s:if test="resource==null || resource.presource == 'MENU_ROOT'">
                            <input class="mini-combobox" id="modulename" name="modulename" data="[{id:1,text:'系统管理'},{id:2,text:'人事管理'},{id:3,text:'考勤管理'},{id:4,text:'薪酬管理'},{id:5,text:'劳保管理'}]"
                                value="${resource.modulename}" required="true" width="200px" />
                        </s:if>
                        <s:else>
                            <input class="mini-combobox asLabel" id="modulename" name="modulename" data="[{id:1,text:'系统管理'},{id:2,text:'人事管理'},{id:3,text:'考勤管理'},{id:4,text:'薪酬管理'},{id:5,text:'劳保管理'}]"
                                value="${resource.modulename}" required="true" width="200px" readOnly="true"/>
                        </s:else>
                    </td>
                </tr>
            </table>
        </div>
        
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
              <a class="mini-button" onclick="onOpen" iconCls="icon-edit" style="width:60px;margin-right:20px;" iconCls="icon-close">图标</a>
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
           
        </div>
    </form>
    
    <script type="text/javascript">
        mini.parse();
        
        function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "../resource/backend_res!tree.action",
                showMaxButton: false,
                title: "选择父级资源",
                width: 300,
                height: 440,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                       
                        var size = iframe.contentWindow.GetSize();
                        size = mini.clone(size);
                        
                        if(mini.get("name").getValue()==data.id){
                            mini.alert("父级资源不能是自己！");
                            return;
                        }
                        var childs =mini.get("cid").getValue();
                        if( childs.length>0){
                            var  allid = childs.split(",");
                            for(var  i=0;i<allid.length;i++){
                               if(allid[i]==data.Id){
                                 mini.alert("父级菜单的父级资源不能是该菜单的子菜单！");
                                 return;
                               }
                            }
                        }
                        if (data) {
                            btnEdit.setValue(data.id);
                            btnEdit.setText(data.id);
                            
                            mini.get("size").setValue(size+1);
                            // 设置模块名称
                            mini.get("modulename").setValue(data.modelArea);
                        }
                    }
                }
            });
        } 
        
        function onOk(){
            var checkText=$("#onshow").find("option:selected").val();  
            if(checkText==""){
                mini.alert("显示选项不能为空");
                return;
            }
            var form = new mini.Form("form1");
            form.validate();
            
            if (form.isValid() == false) return;
            if(mini.get("size").getValue()==""||mini.get("btnEdit1").getValue()==""){
                mini.get("size").setValue("0");
            }
            
            //strut令牌
            window.parent.loading();
            $.ajax({
                url: "../resource/backend_res!updata.action",
                type:'post',
                data:{id:mini.get("id").getValue(),
                      name:mini.get("name").getValue(),
                      description:(mini.get("description").getValue()),
                      parent:mini.get("btnEdit1").getValue(),
                      show:$("#onshow").find("option:selected").val(),
                      onauth:"0",
                      menu:mini.get("size").getValue(),
                      imgvalue:mini.get("imgvalue").getValue(),
                      url:mini.get("url").getValue(),
                      sortorder:mini.get("sortorder").getValue(),
                      child:"${child}",
                      modulename: mini.get("modulename").getValue()
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
        
        function onOpen(){
        	window.open("${base}jsp/resource/domShow.html");
        }
    </script>
</body>
</html>
