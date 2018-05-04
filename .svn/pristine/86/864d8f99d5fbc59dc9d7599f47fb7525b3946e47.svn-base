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
<title>工伤增加</title>
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
        
        <input class="mini-hidden" name="id" id="id" value="${id}" style="display:none;"/>
        <input class="mini-hidden" name="personId" id="personId" value="${personId}" style="display:none;"/>
        <input class="mini-hidden" name="url" id="url" value="${url}" style="display:none;"/>
        <div id="zhuanzheng" style="display:none">	
        	<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                 <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>基本信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    
                    <td class="list_left_box1" valign="top" width="20%">职工号：</td>
                    <td class="list_right_box" width="30%">
                        <input id="jobNumber" name="jobNumber" class="mini-buttonedit" onbuttonclick="onButtonEdit" width="90%"
                                required="true" value="${info.jobNumber}" text="${info.jobNumber}"allowInput="false"/> 
                    </td> 
                    <td class="list_left_box1" valign="top" width="20%">职工姓名：</td>
                    <td class="list_right_box" width="30%">
                        <input name="pername" id="pername" class="mini-textbox" value="${info.name}"
                                readonly="true" width="90%"/>
                    </td> 
                </tr>
                <tr>
                    
                    <td class="list_left_box1" valign="top" >单位：</td>
                    <td class="list_right_box" >
                        <input name="filmName" id="filmName" class="mini-textbox" value="${info.filmName}"
                                readonly="true" width="90%"/>
                    </td> 
                    <td class="list_left_box1" valign="top" >部门：</td>
                    <td class="list_right_box" >
                        <input name="departName" id="departName" class="mini-textbox" value="${info.departName}"
                                readonly="true" width="90%"/>
                    </td> 
                </tr>
                
                <tr>
                    
                    <td class="list_left_box1" valign="top" >标准岗位：</td>
                    <td class="list_right_box" >
                        <input name="perpost" id="perpost" class="mini-textbox" value="${info.post}"
                                readonly="true" width="90%"/>
                    </td> 
                    <td class="list_left_box1" valign="top" >具体岗位：</td>
                    <td class="list_right_box" >
                        <input name="specificPost" id="specificPost" class="mini-textbox" value="${info.specificPost}"
                                readonly="true" width="90%"/>
                    </td> 
                </tr>
            </table>
        </div>
			<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>工伤信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top" width="20%">是否在岗：</td>
                    
                    <td class="list_right_box" width="30%">
                        <input id="onPost" name="onPost" class="mini-combobox"
                        	required="true" emptyText="请选择..." style="width:90%;"
                          	url="../template/using.txt" textField="text" 
                          	valueField="id"  value="${injury.onPost}"/>
                    </td> 
                    <td class="list_left_box1" valign="top" >发生工伤时所属部门：</td>
                    
                    <td class="list_right_box">
                        <input id="injuryDepartment" name="injuryDepartment" class="mini-buttonedit" onbuttonclick="onButtonEdit1" width="90%"
                                required="true" value="${injury.injuryDepartment}" text="${qxdep.name}"allowInput="false"/> 
                    </td>
                    
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >发生工伤时所属单位：</td>
                    <td class="list_right_box" >
                        <input id="injuryUnit" name="injuryUnit" class="mini-buttonedit" width="90%"
                                required="true" value="${injury.injuryUnit}" text="${branch.fzx}"allowInput="false"/> 
                    </td>
                    <td class="list_left_box1" valign="top" >发生工伤时所属岗位：</td>
                    
                    <td class="list_right_box">
                        <input id="injuryPost" name="injuryPost" class="mini-buttonedit" onbuttonclick="onButtonEdit2" width="90%"
                                required="true" value="${injury.injuryPost}" text="${deppost.positionName}"allowInput="false"/> 
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >工伤日期：</td>
                    <td class="list_right_box" >
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									required="true" name="injuryDate" id="injuryDate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${injury.injuryDate}"/>
                    </td> 
                    <td class="list_left_box1" valign="top" >处理状态：</td>
                    <td class="list_right_box">
                        <input id="manageStatus" name="manageStatus" class="mini-combobox" style="width:90%;" value="${injury.manageStatus}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955c42841a015c438891ed000a" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                </tr>
                <tr>
                    
                    <td class="list_left_box1" valign="top" >工伤事故经过：</td>
                    <td class="list_right_box" colspan="3">
                        <input id="injuryProcess" name="injuryProcess" class="mini-textarea" value="${injury.injuryProcess}"  maxlength="500"style="width: 96%;height:50px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >工伤部位：</td>
                    <td class="list_right_box" >
                         <input name="injuryPart" id="injuryPart" class="mini-textarea" value="${injury.injuryPart}"
                                vtype="maxLength:20" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >工伤种类：</td>
                    <td class="list_right_box">
                        <input id="injuryType" name="injuryType" class="mini-combobox" style="width:90%;" value="${injury.injuryType}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955c42841a015c438e0457000e" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >工伤原因：</td>
                    <td class="list_right_box" colspan="3">
                         <input id="injuryCause" name="injuryCause" class="mini-textarea" value="${injury.injuryCause}"  maxlength="500"style="width: 96%;height:50px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >停工留薪开始日期：</td>
                    
                    <td class="list_right_box" >
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="stopjobBegindate" id="stopjobBegindate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${injury.stopjobBegindate}"/>
                    </td> 
                    <td class="list_left_box1" valign="top" >停工留薪结束日期：</td>
                    
                    <td class="list_right_box" >
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="stopjobEnddate" id="stopjobEnddate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${injury.stopjobEnddate}"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >停工留薪延长至日期：</td>
                    
                    <td class="list_right_box" >
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="stopjobExtenddate" id="stopjobExtenddate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${injury.stopjobExtenddate}"/>
                    </td> 
                    <td class="list_left_box1" valign="top" >伤残等级：</td>
                    <td class="list_right_box" >
                        <input id="injuryLever" name="injuryLever" class="mini-combobox" style="width:90%;" value="${injury.injuryLever}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955c42841a015c43ea0445001b" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >停工伤认定日期：</td>
                    
                    <td class="list_right_box" >
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="injuryAffirmdate" id="injuryAffirmdate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${injury.injuryAffirmdate}"/>
                    </td> 
                    
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >复发记录：</td>
                    <td class="list_right_box" colspan="3">
                         <input id="injuryAgainRecord" name="injuryAgainRecord" class="mini-textarea" value="${injury.injuryAgainRecord}"  maxlength="500"style="width: 96%;height:50px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >因果关系鉴定：</td>
                    <td class="list_right_box" colspan="3">
                         <input id="causEffect" name="causEffect" class="mini-textarea" value="${injury.causEffect}"  maxlength="500"style="width: 96%;height:50px"/>
                    </td>
                </tr>
                 <tr>
                    <td class="list_left_box1" valign="top" >医疗开始日期：</td>
                    
                    <td class="list_right_box" >
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="healBegindate" id="healBegindate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${injury.healBegindate}"/>
                    </td> 
                    <td class="list_left_box1" valign="top" >医疗结束日期：</td>
                    
                    <td class="list_right_box" >
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="healEnddate" id="healEnddate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${injury.healEnddate}"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >医疗费用：</td>
                    <td class="list_right_box" >
                         <input name="healCosts" id="healCosts" class="mini-textarea" value="${injury.healCosts}"
                                vtype="maxLength:20" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >伤残补助金：</td>
                    <td class="list_right_box" >
                         <input name="injurySubsidy" id="injurySubsidy" class="mini-textarea" value="${injury.injurySubsidy}"
                                vtype="maxLength:20" width="90%"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >工伤鉴定日期：</td>
                    
                    <td class="list_right_box" >
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="authDate" id="authDate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${injury.authDate}"/>
                    </td> 
                    
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >就业补助金：</td>
                    <td class="list_right_box" >
                         <input name="employSubsidy" id="employSubsidy" class="mini-textarea" value="${injury.employSubsidy}"
                                vtype="maxLength:20" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >医疗补助金：</td>
                    <td class="list_right_box" >
                         <input name="healSubsidy" id="healSubsidy" class="mini-textarea" value="${injury.healSubsidy}"
                                vtype="maxLength:20" width="90%"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >社保承担：</td>
                    <td class="list_right_box" >
                         <input name="employSubsidySocial" id="employSubsidySocial" class="mini-textarea" value="${injury.employSubsidySocial}"
                                vtype="maxLength:20" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >社保承担：</td>
                    <td class="list_right_box" >
                         <input name="healSubsidySocial" id="healSubsidySocial" class="mini-textarea" value="${injury.healSubsidySocial}"
                                vtype="maxLength:20" width="90%"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >公司承担：</td>
                    <td class="list_right_box" >
                         <input name="employSubsidyFilm" id="employSubsidyFilm" class="mini-textarea" value="${injury.employSubsidyFilm}"
                                vtype="maxLength:20" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >公司承担：</td>
                    <td class="list_right_box" >
                         <input name="healSubsidyFirm" id="healSubsidyFirm" class="mini-textarea" value="${injury.healSubsidyFirm}"
                                vtype="maxLength:20" width="90%"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >处理过程：</td>
                    <td class="list_right_box" colspan="3">
                         <input id="cuteProcess" name="cuteProcess" class="mini-textarea" value="${injury.cuteProcess}"  maxlength="500"style="width: 96%;height:50px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >备注：</td>
                    <td class="list_right_box" colspan="3">
                         <input id="remark" name="remark" class="mini-textarea" value="${injury.remark}"  maxlength="500"style="width: 96%;height:50px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" >文件编号：</td>
                    <td class="list_right_box" >
                         <input name="fileNum" id="fileNum" class="mini-textarea" value="${license.fileNum}"
                                vtype="maxLength:20" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >文件名称：</td>
                    <td class="list_right_box" >
                         <input name="fileName" id="fileName" class="mini-textarea" value="${license.fileName}"
                                vtype="maxLength:20" width="90%"/>
                    </td>
                </tr>
            </table>
        </form>
        <form id="uploaders" method="post" action="../empdata/license!uploadFile.action?id=${id}"
       			 enctype="multipart/form-data" encoding="multipart/form-data" target="appFrame">
       		 <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                
                <tr>
                    <tr>
                    <td class="list_left_box1" valign="top" width="20%">
         请选择上传文件：
                    </td>
                    <td class="list_right_box" colspan="3">
				        <input class="mini-htmlfile" id="uploadFile" name="uploadFile" limitType="*.jpg;*.jpeg;"  text="${license.url}" />
                    	<a class="mini-button" onclick="upload" style="width:100px;margin-right:20px;" iconCls="icon-upload">上传</a>
                    </td>
                </tr>
                </tr>
            </table>
           </form>
        </br></br></br>
        <span id="x" style="display:none"></span>
    	<iframe id="frame" name="appFrame" src="" style="display:none;"></iframe>
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        </div>
    
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
	//选择部门
 		function onButtonEdit1(e) {
            var btnEdit = this;
            var injuryUnit=mini.get("injuryUnit");//公司
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
                            injuryUnit.setValue(data.branchId);
                            $.ajax({
                                url: "../personnel/change!findFilmName.action?filmId="+data.branchId,
                                success: function (text) {
                                    if(text!=null){
                                    	injuryUnit.setText(text);
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
 		function onButtonEdit2(e) {
 	        var btnEdit = this;
 	        var depart=mini.get("injuryDepartment").getValue();
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
 	                    }
 	                }
 	            }
 	        });
 	        
 	    } 
	//////////////////////////////////////////////////////////////
	function onOk(){
        var form = new mini.Form("form1");
        form.validate();
        if (form.isValid() == false) return;
        var data = form.getData();
        var json = mini.encode(data); 
        //strut令牌
        $.ajax({
            url: "../empdata/injury!saveOrUpdate.action",
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
	
	//***************************************************//
	// 得到iframe
    var appFrame = document.getElementById('frame');
    // iframe监听事件
    var listener = function() {
        window.parent.unmask();
        // 获取上传之后的iframe返回值
        var doc = appFrame.contentWindow.document;
        var pre = doc.getElementsByTagName('pre');
        // 是否存在(默认会自动生成pre标签)
        if (pre && pre.length > 0) {
            var allmsg = JSON.parse(pre[0].innerHTML);//返回值
            var response=allmsg.msg;//返回msg的值
            if(response=="success"){
            	mini.get("url").setValue(allmsg.fileUrl);//给URL赋值
                mini.showMessageBox({
                    title: "提醒",
                    width: 250,
                    iconCls: "mini-messagebox-warning",
                    buttons: ["ok"],
                    message: "上传成功",
                    callback: function (action) {
                    	
                    }
                });
            } else {
               response = $("#x").html(response).text();
               if (response.indexOf("@") == 0) {
                   message = response.substring(1, response.length);
                   onCancel();
               } else {
            	   mini.alert(response);
            	   message = "";
               }
            }
        }
    }

    // 判断iframe的监听类型（IE只支持attachEvent，火狐支持addEventListener，W3C标准）
    if (appFrame.addEventListener) {
        appFrame.addEventListener('load', function(evt) {
            listener();
        }, false);
    } else if(appFrame.attachEvent) {
        appFrame.attachEvent('onload', function() {
            listener();
        });
    }

    //导入
    function upload() {
    	var uploadFile = mini.get("uploadFile");
    	if (uploadFile.getValue()=="") {
    		mini.alert("请选择上传文件");
    		return;
    	}
        window.parent.loading();
        // 表单提交
        uploaders.submit();
    }
    //关闭当前页面
    function onOkClose() {
    	CloseWindow("ok");
    }
    </script>
</body>
</html>