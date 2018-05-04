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
<title>健康档案增加</title>
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
	<div class="mini-fit" >
    <form id="form1" method="post">
        <input class="mini-hidden" name="id" id="id" value="${id}" style="display:none;"/>
        <input class="mini-hidden" name="personId" id="personId" value="${personId}" style="display:none;"/>
        <div id="zhuanzheng" style="display:none">	
        	<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                 <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>基本信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    
                    <td class="list_left_box1" valign="top" width="15%">职工号：</td>
                    <td class="list_right_box" width="30%">
                        <input id="jobNumber" name="jobNumber" class="mini-buttonedit" onbuttonclick="onButtonEdit" width="90%"
                                required="true" value="${info.jobNumber}" text="${info.jobNumber}"allowInput="false"/> 
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
                        <input name="perpost" id="perpost" class="mini-textbox" value="${info.post}"
                                readonly="true" width="90%"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">具体岗位：</td>
                    <td class="list_right_box" width="30%">
                        <input name="specificPost" id="specificPost" class="mini-textbox" value="${info.specificPost}"
                                readonly="true" width="90%"/>
                    </td> 
                </tr>
            </table>
		</div>
			<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>健康信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    
                    <td class="list_left_box1" valign="top" width="15%">身高：</td>
                    <td class="list_right_box" width="30%">
                        <input name="height" id="height" class="mini-textbox" value="${health.height}"
                                vtype="maxLength:20" width="90%"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">血型：</td>
                    <td class="list_right_box" width="30%">
                        <input id="bloodType" name="bloodType" class="mini-combobox" style="width:90%;" value="${health.bloodType}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881935b8df599015b8e11bfff0015" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">体重：</td>
                    <td class="list_right_box" width="30%">
                        <input name="weight" id="weight" class="mini-textbox" value="${health.weight}"
                                vtype="maxLength:50" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="15%">健康状况：</td>
                    
                    <td class="list_right_box" width="30%">
                        <input id="health" name="health" class="mini-combobox" style="width:90%;" value="${health.health}"  textField="dicname" valueField="dicvalue"
		                   		required="true" url="../system/dectionary!listjson.action?id=402881935b8df599015b8e1443ac001c" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                </tr>
                <tr>
                    
                    <td class="list_left_box1" valign="top" width="15%">检查种类：</td>
                    <td class="list_right_box" width="30%">
                        <input id="checkType" name="checkType" class="mini-combobox" style="width:90%;" value="${health.checkType}"  textField="dicname" valueField="dicvalue"
		                   		required="true" url="../system/dectionary!listjson.action?id=402881935b8df599015b8e15b55f0020" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                    <td class="list_left_box1" valign="top" width="15%">检查日期：</td>
                    <td class="list_right_box" width="30%">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="checkDate" id="checkDate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									required="true" value="${health.checkDate}"/>
                    </td>  
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">检查结果：</td>
                    <td class="list_right_box" width="30%">
                        <input name="checkResult" id="checkResult" class="mini-textbox" value="${health.checkResult}"
                                vtype="maxLength:20" width="90%"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">是否职业禁忌：</td>
                    <td class="list_right_box" width="30%">
                        <input id="professionTaboo" name="professionTaboo" class="mini-combobox"
                        	emptyText="请选择..." style="width:90%;"
                          	url="../template/using.txt" textField="text" 
                          	valueField="id"  value="${health.professionTaboo}"/>
                    </td> 
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="15%">是否大病：</td>
                    <td class="list_right_box" width="30%">
                        <input id="isBigIllness" name="isBigIllness" class="mini-combobox"
                        	emptyText="请选择..." style="width:90%;"
                          	url="../template/using.txt" textField="text" 
                          	valueField="id"  value="${health.isBigIllness}"/>
                    </td> 
                    
                    <td class="list_left_box1" valign="top" width="15%">是否绝症：</td>
                    <td class="list_right_box" width="30%">
                        <input id="isFatalIllness" name="isFatalIllness" class="mini-combobox"
                        	emptyText="请选择..." style="width:90%;"
                          	url="../template/using.txt" textField="text" 
                          	valueField="id"  value="${health.isFatalIllness}"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">危害因素：</td>
                    <td class="list_right_box" colspan="3">
                        <input id="theFactor" name="theFactor" class="mini-textarea" value="${health.theFactor}"  maxlength="500"style="width: 100%;height:50px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">处置措施：</td>
                    <td class="list_right_box" colspan="3">
                        <input id="tabooContent" name="tabooContent" class="mini-textarea" value="${health.tabooContent}"  maxlength="500"style="width: 100%;height:50px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">职业禁忌内容：</td>
                    <td class="list_right_box" colspan="3">
                        <input id="theStep" name="theStep" class="mini-textarea" value="${health.theStep}"  maxlength="500"style="width: 100%;height:50px"/>
                    </td> 
                </tr>
                <tr>
                    
                    <td class="list_left_box1" valign="top" >备注：</td>
                    <td class="list_right_box" colspan="3">
                        <input id="remark" name="remark" class="mini-textarea" value="${health.remark}"  maxlength="500"style="width: 100%;height:50px"/>
                    </td> 
                </tr>
            </table>
        </br></br>
        
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        </div>
        
    </form>
    </div>
    <script type="text/javascript">
    mini.parse();
    var personId = mini.get("personId").getValue();
    var iframe = document.getElementById("zhuanzheng");
    if(personId!=null&&personId!=""){
    	iframe.style.display="none";
	}else{
		iframe.style.display="block";
	}
	function onButtonEdit(e) {
        var btnEdit = this;
        var name=mini.get("pername");
        var post=mini.get("perpost");
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
            url: "../empdata/health!saveOrUpdate.action",
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
    </script>
</body>
</html>