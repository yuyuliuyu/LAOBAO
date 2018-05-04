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
<title>兼职履历增加</title>
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
        <input class="mini-hidden" name="firm" id="firm" value="${ptjob.firm}" style="display:none;"/>
        <input class="mini-hidden" name="dep" id="dep" value="${ptjob.dep}" style="display:none;"/>
        <input class="mini-hidden" name="standardPost" id="standardPost" value="${ptjob.standardPost}" style="display:none;"/>
        <input class="mini-hidden" name="spePost" id="spePost" value="${ptjob.spePost}" style="display:none;"/>
        
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
                               required="true" value="${info.jobNumber}" text="${info.jobNumber}" allowInput="false"/> 
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
            </table>
        </div>
			<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>兼职信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">兼职部门：</td>
                    
                    <td class="list_right_box" width="30%">
                       <input id="depId" name="depId" value="${ptjob.depId}" text="${ptjob.dep}" class="mini-buttonedit" onbuttonclick="onButtonEdit2" width="90%"
                              required="true" allowInput="false"  /> 
                  	</td>
                    <td class="list_left_box1" valign="top" width="15%">兼职公司：</td>
                    <td class="list_right_box" width="30%">
                       	<input id="firmId" name="firmId" required="true" value="${ptjob.firmId}" text="${ptjob.firm}" class="mini-buttonedit" width="90%" allowInput="false"  /> 
                  	</td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">兼职具体岗位：</td>
                    
                    <td class="list_right_box" width="30%">
                       <input id="spePostId" name="spePostId" required="true" value="${ptjob.spePostId}" text="${ptjob.spePost}" class="mini-buttonedit" onbuttonclick="onButtonEdit1" width="90%"
                              allowInput="false"/> 
                   	</td>
                    <td class="list_left_box1" valign="top" width="15%">兼职标准岗位：</td>
                    <td class="list_right_box" width="30%">
                       <input id="standardPostId" name="standardPostId" required="true" value="${ptjob.standardPostId}" text="${ptjob.standardPost}" class="mini-buttonedit" width="90%"
                              allowInput="false"/> 
                   	</td>
                </tr>
                
                <tr>
                	<td class="list_left_box1" valign="top" width="15%">职务级别：</td>
                    <td class="list_right_box" width="30%">
                        <input id="jobLevel" name="jobLevel" class="mini-combobox" style="width:90%;" value="${ptjob.jobLevel}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881935ba8c785015ba8e043c80011" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">开始时间：</td>
                    <td class="list_right_box" width="30%">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="beginTime" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${ptjob.beginTime}"/>
                    </td> 
                </tr>
              
                <tr>
                    
                    <td class="list_left_box1" valign="top" >备注：</td>
                    <td class="list_right_box" colspan="3">
                        <input id="bz" name="bz" class="mini-textarea" value="${ptjob.bz}"  maxlength="500"style="width: 96%;height:50px"/>
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
        
        function onButtonEdit2(e) {
            var btnEdit = this;
            var firmId=mini.get("firmId");//公司id
            var firm=mini.get("firm");//公司
            var dep=mini.get("dep");//部门
            mini.open({
                url: "../basis/branch!tree.action",
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
                            firmId.setValue(data.branchId);
                            dep.setValue(data.name);
                            $.ajax({
                                url: "../personnel/change!findFilmName.action?filmId="+data.branchId,
                                success: function (text) {
                                    if(text!=null){
                                    	firmId.setText(text);
                                    	firm.setValue(text);
                                    }
                                }
                            });   
                            
                        }
                    }
                }
            });
            
        }
		
		//选择岗位
 		function onButtonEdit1(e) {
 	        var btnEdit = this;
 	        var depart=mini.get("depId").getValue();
 	       	var postId=mini.get("standardPostId");//标准岗位id
 	        var post=mini.get("standardPost");//标准岗位
 	       	var spePost=mini.get("spePost");//具体岗位
 	        mini.open({
 	            url: "../post/position!show.action?id="+depart,
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
 	                        btnEdit.setValue(data.id);
 	                        btnEdit.setText(data.positionName);
 	                        spePost.setValue(data.positionName);
 	                       	postId.setValue(data.positionId);//标准岗位id
 	                        postId.setText(data.positionName2);//标准岗位名称
 	                        post.setValue(data.positionName2);//标准岗位
 	                    }
 	                }
 	            }
 	        });
 	        
 	    } 
        
        function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            var data = form.getData();
            var json = mini.encode(data); 
            //strut令牌
            $.ajax({
                url: "../personnel/pt_job!saveOrUpdate.action",
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