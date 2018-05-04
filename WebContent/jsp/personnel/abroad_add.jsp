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
<title>出国增加</title>
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
        
        <input class="mini-hidden" name="id" id="id" value="${id}" style="display:none;"/>
        <input class="mini-hidden" name="personId" id="personId" value="${personId}" style="display:none;"/>
        <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                 <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>基本信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                 </tr> 
                 <tr>
                    <td class="list_left_box1" valign="top" width="15%">职工号：</td>
                    <td class="list_right_box" width="30%">
                        <input id="jobNumber" name="jobNumber" class="mini-buttonedit" onbuttonclick="onButtonEdit" width="90%"
                                value="${info.jobNumber}" text="${info.jobNumber}" allowInput="false"/> 
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">职工姓名：</td>
                    <td class="list_right_box" width="30%">
                        <input name="pername" id="pername" class="mini-textbox" value="${info.name}"
                                readonly="true" width="90%"/>
                    </td> 
                </tr>
                <tr>
                    
                    <td class="list_left_box1" valign="top" width="15%">单位：</td>
                    <td class="list_right_box" width="30%">
                        <input name="filmName" id="filmName" class="mini-textbox" value="${info.filmName}"
                                readonly="true" width="90%"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">部门：</td>
                    <td class="list_right_box" width="30%">
                        <input name="departName" id="departName" class="mini-textbox" value="${info.departName}"
                                readonly="true" width="90%"/>
                    </td> 
                </tr>
                
                <tr>
                    
                    <td class="list_left_box1" valign="top" width="15%">标准岗位：</td>
                    <td class="list_right_box" width="30%">
                        <input name="post" id="post" class="mini-textbox" value="${info.post}"
                                readonly="true" width="90%"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">具体岗位：</td>
                    <td class="list_right_box" width="30%">
                        <input name="specificPost" id="specificPost" class="mini-textbox" value="${info.specificPost}"
                                readonly="true" width="90%"/>
                    </td> 
                </tr>
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>出国信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                
                <tr>
                    
                    <td class="list_left_box1" valign="top" width="15%">出国类型：</td>
                    <td class="list_right_box" width="30%">
                        <input id="abroadCase" name="abroadCase" class="mini-combobox" style="width:90%;" value="${abroad.abroadCase}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955c47d5b4015c48c012dc0012" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">出国目的：</td>
                    <td class="list_right_box" width="30%">
                        <input id="abroadGoal" name="abroadGoal" class="mini-combobox" style="width:90%;" value="${abroad.abroadGoal}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955c47d5b4015c48bfdeef0011" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                </tr>
                 <tr>
                    <td class="list_left_box1" valign="top" width="15%">出行国家：</td>
                    <td class="list_right_box" width="30%">
                        <input name="goalPiace" id="goalPiace" class="mini-textbox" value="${abroad.goalPiace}"
                               style="width:90%;"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">开始时间：</td>
                    <td class="list_right_box" width="30%">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
							id="begindate" name="begindate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${abroad.begindate}"/>
                    </td>  
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">预计结束时间：</td>
                    <td class="list_right_box" width="30%">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
							id="planenddate" name="planenddate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${abroad.planenddate}"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">证件类型：</td>
                    <td class="list_right_box" width="30%">
                        <input id="idType" name="idType" class="mini-combobox" style="width:90%;" value="${abroad.idType}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881935ba8c785015ba8d9d7c0000b" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                    <td class="list_left_box1" valign="top" width="15%">证件号码：</td>
                    <td class="list_right_box" width="30%">
                        <input name="idNum" id="idNum" class="mini-textbox" value="${abroad.idNum}"
                               style="width:90%;"/>
                    </td> 
                </tr>
                <tr>
                    
                    <td class="list_left_box1" valign="top" >备注：</td>
                    <td class="list_right_box" colspan="3">
                         <input id="remark" name="remark" class="mini-textarea" value="${abroad.remark}"  maxlength="500"style="width: 96%;height:50px"/>
                    </td> 
                </tr>
            </table>
        
        
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        </div>
        
    </form>
    
    <script type="text/javascript">
        mini.parse();
		function onButtonEdit(e) {
            var btnEdit = this;
            var name=mini.get("pername");
            var post=mini.get("post");
            var specificPost=mini.get("specificPost");
            var depart=mini.get("departName");
            var filmName=mini.get("filmName");
            var personId=mini.get("personId");
            mini.open({
                url: "../personnel/personnel!show.action",
                showMaxButton: false,
                title: "选择人员",
                width: 400,
                height: 300,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        if (data) {
                            btnEdit.setValue(data.jobNumber);
                            btnEdit.setText(data.jobNumber);
                            name.setValue(data.name);
                            post.setValue(data.post);
                            specificPost.setValue(data.specificPost);
                            depart.setValue(data.departName);
                            filmName.setValue(data.filmName);
                            personId.setValue(data.id);
                        }
                    }
                }
            });
            
        } 
		//*************************************//
		function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            var data = form.getData();
            var json = mini.encode(data); 
            //strut令牌
            $.ajax({
                url: "../personnel/abroad!saveOrUpdate.action",
                data:{
                	formdata:json,
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
            });
        }
		//*************************************//
    </script>
</body>
</html>