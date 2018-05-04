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
<title>实习结束</title>
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<script src="../template/plugin/public/add.js" type="text/javascript"></script>

<!--引入皮肤样式-->
<link href="../template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
<link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
<link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
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
        <input class="mini-hidden" name="id" id="id" value="${personId}" style="display:none;"/>
        <input class="mini-hidden" name="filmId" id="filmId" value="${information.filmId}" style="isplay:none;"/>
        <input class="mini-hidden" name="filmName" id="filmName" value="${information.filmName}" style="isplay:none;"/>
        <input class="mini-hidden" name="departName" id="departName" value="${information.departName}" style="display:none;"/>
        <input class="mini-hidden" name="postId" id="postId" value="${information.postId}" style="isplay:none;"/>
        <input class="mini-hidden" name="post" id="post" value="${information.post}" style="isplay:none;"/>
        <input class="mini-hidden" name="specificPost" id="specificPost" value="${information.specificPost}" style="isplay:none;"/>
        <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
        		<tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="center" colspan="4" height="30px"><b>结束实习</b></td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">结束类型：</td>
                    <td class="list_right_box">
                        <input name="isPractice" id="isPractice"  class="mini-combobox" width="90%"  onvaluechanged="onChanged"
                               data="[{id:0, text:'实习结束离开'},{id:2, text:'实习结束转正'}]" value="${information.isPractice}"/>
                    </td> 
                    <td class="list_left_box1" valign="top">结束时间：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="overPracticeDate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${information.overPracticeDate}"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">结束原因：</td>
                    <td class="list_right_box" colspan="3">
                         <input id="remark2" name="remark2" vtype="maxLength:50" class="mini-textarea" value="${information.remark2}"  maxlength="100"style="width: 96%;height:50px"/>
                    </td> 
                </tr>
            </table>
            <div id="zhuanzheng" style="display:none">
            	<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
	                <tr>
	               		<td align="center" colspan="4" height="30px"><b>转正进入试岗期</b></td> 
                	</tr>
	                <tr>
	                    <td class="list_left_box1" valign="top">分配职工号：</td>
	                    <td class="list_right_box">
	                        <input name="jobNumber" id="jobNumber" class="mini-textbox" value="${information.jobNumber}"
		                		vtype="maxLength:32;required" width="90%"/>
	                    </td>
	                    <td class="list_left_box1" valign="top">分配集团号：</td>
	                    <td class="list_right_box">
	                        <input name="jobNumberJt" id="jobNumberJt" class="mini-textbox" value="${information.jobNumberJt}"
		                		vtype="maxLength:32;required" width="90%"/>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="list_left_box1" valign="top">转正成试岗期时间：</td>
	                    <td class="list_right_box">
	                        <input class="mini-datepicker" format="yyyy-MM-dd"name="zzDate" style="width: 90%"
	                         msg="日期格式不正确" allowInput="false"value="${information.zzDate}"/>
	                    </td>
	                    <td class="list_left_box1" valign="top">试岗期结束时间：</td>
	                    <td class="list_right_box">
	                        <input class="mini-datepicker" format="yyyy-MM-dd"name="JssyDate" style="width: 90%"
	                         msg="日期格式不正确" allowInput="false"value="${information.jssyDate}"/>
	                    </td>
	                </tr>
	                <tr>
	                	<td class="list_left_box1" valign="top">人员类别：</td>
                    	<td class="list_right_box" width="30%">
                        	<input id="empType" name="empType" class="mini-combobox" required="true" style="width:90%;" value="${information.empType}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881935baceaa1015bad355ba8000a" showNullItem="true" emptyText="请选择..." nullItemText="请选择..."/>
                   	    </td>
	                    <td class="list_left_box1" valign="top">职务级别：</td>
	                    <td class="list_right_box">
	                        <input id="jobLevel" name="jobLevel" class="mini-combobox" style="width:90%;" value="${information.jobLevel}"  textField="dicname" valueField="dicvalue"
			                   		url="../system/dectionary!listjson.action?id=402881935ba8c785015ba8e043c80011" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
	                    </td>  
               	    </tr>
	                <tr>
	                    <td class="list_left_box1" valign="top">分配部门：</td>
	                    <td class="list_right_box" width="35%">
                        <input id="departId" name="departId" class="mini-buttonedit" onbuttonclick="onButtonEdit" width="90%"
                                value="${information.departId}" text="${information.departName}" allowInput="false"  /> 
                   		 </td>
	                    <td class="list_left_box1" valign="top">分配具体岗位：</td>
	                    
	                    <td class="list_right_box" width="30%">
                        <input id="specificPostId" name="specificPostId" class="mini-buttonedit" onbuttonclick="onButtonEdit1" width="90%"
                                value="${information.specificPostId}"allowInput="false"/> 
                    	</td>
	                </tr>
	                <tr>
	                    <td class="list_left_box1" valign="top">开始时间：</td>
	                    <td class="list_right_box">
	                        <input class="mini-datepicker" format="yyyy-MM-dd" name="beginTime" style="width: 90%"
	                         msg="日期格式不正确" allowInput="false"value="${information.beginTime}"/>
	                    </td>
	                    <td class="list_left_box1" valign="top">任职年限：</td>
                   	    <td class="list_right_box">
                        	<input name="career" id="career" class="mini-textbox" value="${information.career}"
                                vtype="maxLength:10" width="90%"/>
                    	</td> 
	                </tr>
	                <tr>
	                	<td class="list_left_box1" valign="top">是否为技术工人：</td>
	                    <td class="list_right_box">
	                        <input id="isSkilled" name="isSkilled" class="mini-combobox"
	                        	emptyText="请选择..." required="true"
	                          	url="../template/using.txt" textField="text" 
	                          	 width="90%" value="${information.isSkilled}"/>
	                    </td>
	                    <td class="list_left_box1" valign="top">是否为专业技术人员：</td>
	                    <td class="list_right_box">
	                        <input id="isSpecialty" name="isSpecialty" class="mini-combobox"
	                        	emptyText="请选择..." required="true"
	                          	url="../template/using.txt" textField="text" 
	                          	 width="90%" value="${information.isSpecialty}"/>
	                    </td>
               	    </tr>
               	    
                </table>
             </div>
            </br></br></br>
            <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
	            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">提交</a> 
	            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        	</div>
        
    </form>
    </div>
    <script type="text/javascript">
        mini.parse();
        //////////////////////////////////////////////////////
        function onChanged(){
        	var firstCombo = mini.get("isPractice");
        	var isPractice = firstCombo.getValue();
        	if(isPractice==2){
        		var iframe = document.getElementById("zhuanzheng");
                iframe.style.display="block";
        	}
        	if(isPractice==0){
        		var iframe = document.getElementById("zhuanzheng");
                iframe.style.display="none";
        	}
        }
		//////////////////////////////////////////////////////
        function onOk(){
			debugger
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            var data = form.getData();
            var json = mini.encode(data); 
            //strut令牌
            $.ajax({
                url: "../personnel/practice!finishInfo.action",
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
		//////////////////////////////////////////////////////
		//选择部门
 		function onButtonEdit(e) {
            var btnEdit = this;
            var departName=mini.get("departName");
            var filmId=mini.get("filmId");//公司ID
            var filmName=mini.get("filmName");//公司
            
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
		//////////////////////////////////////////////////////
		//选择岗位
 		function onButtonEdit1(e) {
 	        var btnEdit = this;
 	        var depart=mini.get("departId").getValue();
 	       	var postId=mini.get("postId");
 	       	var postName=mini.get("post");
 	       	var specificPost=mini.get("specificPost");
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
 	                       	postId.setValue(data.positionId);//标准岗位id
 	                       	postName.setValue(data.positionName2);//标准岗位名称
 	                        specificPost.setValue(data.positionName);
 	                    }
 	                }
 	            }
 	        });
 	        
 	    } 
 		//关闭当前页面
 	    function onOkClose() {
 	    	CloseWindow("ok");
 	    }
    </script>
</body>
</html>