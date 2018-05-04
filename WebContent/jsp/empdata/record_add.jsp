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
<title>电子档案增加</title>
    <link href="${base}/template/system/css/base.css" rel="stylesheet" type="text/css" />
    <script src="${base}/template/miniui/boot.js" type="text/javascript"></script>
    <link href= "../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <link href="${base}/template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" charset="utf-8" src="${base}/template/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${base}/template/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${base}/template/ueditor/lang/zh-cn/zh-cn.js"></script>

    <link rel="stylesheet" type="text/css" href="${base}/template/plugin/webuploader/webuploader.css" />
    <link rel="stylesheet" type="text/css" href="${base}/template/plugin/webuploader/style.css" />
    <script type="text/javascript" src="${base}/template/plugin/webuploader/webuploader.js"></script>
    <script type="text/javascript" src="${base}/template/plugin/webuploader/image_upload.js"></script>
    <script type="text/javascript" src="${base}/template/plugin/webuploader/images_upload.js"></script>

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
        <input class="mini-hidden" name="imgpath" id="imgpath" value="${record.imgpath}" style="display:none;"/>
        <input class="mini-hidden" name="path" id="path" value="${path}" style="display:none;"/>
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
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>档案信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">档案类型：</td>
                    <td class="list_right_box" width="30%">
                        <input id="reType" name="reType" class="mini-combobox" style="width:90%;" value="${record.reType}"  textField="dicname" valueField="dicvalue"
		                   		required="true" url="../system/dectionary!listjson.action?id=402881935ba3dd8a015ba3f117400003" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                    <td class="list_left_box1" valign="top" width="15%">档案名称：</td>
                    <td class="list_right_box" width="30%">
                        <input name="reName" id="reName" class="mini-textbox" value="${record.reName}"
                                required="true" vtype="maxLength:50" width="90%"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">档案文号：</td>
                    <td class="list_right_box" width="30%">
                        <input name="reFileNum" id="reFileNum" class="mini-textbox" value="${record.reFileNum}"
                                 width="90%"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">档案编号：</td>
                    <td class="list_right_box" width="30%">
                        <input name="reNum" id="reNum" class="mini-textbox" value="${record.reNum}"
                                vtype="maxLength:50" width="90%"/>
                    </td> 
                </tr>
               <tr>
                    <td class="list_left_box1" valign="top" width="15%">档案年份：</td>
                    <td class="list_right_box" width="30%">
                        <input name="reYear" id="reYear" class="mini-textbox" value="${record.reYear}"
                                vtype="maxLength:50" width="85%"/>&nbsp;&nbsp;年
                    </td> 
                    
                </tr>
               
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">档案内容简介：</td>
                    <td class="list_right_box" colspan="3">
                        <input id="reContent" name="reContent" class="mini-textarea" value="${record.reContent}"  maxlength="500"style="width: 100%;height:50px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">备注：</td>
                    <td class="list_right_box" colspan="3">
                        <input id="remark" name="remark" class="mini-textarea" value="${record.remark}"  maxlength="500"style="width: 100%;height:50px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">创建工号：</td>
                    <td class="list_right_box" width="30%">
                        <input id="createNum" name="createNum" class="mini-buttonedit" onbuttonclick="onButtonEdit2" width="90%"
                                value="${record.createNum}"allowInput="false"/> 
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">创建姓名：</td>
                    <td class="list_right_box" width="30%">
                        <input name="createName" id="createName" class="mini-textbox" value="${record.createName}"
                                vtype="maxLength:50" width="90%" readonly="true"/>
                    </td> 
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top">
        证件上传：
                	</td>
                    <td class="list_right_box" colspan="3">
                   	<div id="uploader-demo" class="wu-example">
				   	    <!--用来存放item-->
					    <div id="fileList" class="uploader-list">
						     <s:if test="record.imgpath!=null&&record.imgpath!=''">
						     		<div id="upImgName"  class="file-item thumbnail;upload-state-done" style="border:1px solid #ccc;"> 
					                    <img name="upImgName"  src="${sessionScope.path}${record.imgpath}" style="width:150px;height:100px;"/> 
					                    <div class="info"></div> 
				                	</div>
						     </s:if>
						     <div id="delPic" style='width:100%;float:left;text-align:center;margin-top:1px;'>
				        	 	<a href='#' onClick="deleImgD()" style='width:50px;height:30px;line-height:38px;background:#2CA8F7;color:#FFF; text-decoration:none;padding:10px 30px;border-radius:10px;'>删除</a>
				        	 </div>
			        	</div>
				    </div>
				    <div id="filePicker" style="display: inline;">选择文件</div>
				    <div id = "highCamera" class="webuploader-pick">高拍仪拍照</div>
                   </td>
               </tr>
            </table>
        </form>
     </div>
     <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
         <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
         <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
         <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
   	 </div>
    
    <script type="text/javascript">
    mini.parse();
    var path = "/dangan";
    var personId = mini.get("personId").getValue();
    var iframe = document.getElementById("zhuanzheng");
    if(personId!=null&&personId!=""){
    	iframe.style.display="none";
	}else{
		iframe.style.display="block";
	}
	function onButtonEdit(e) {
        var btnEdit = this;
        var personId=mini.get("personId");
        var name=mini.get("pername");
        var post=mini.get("perpost");
        var specificPost=mini.get("specificPost");
        var depart=mini.get("departName");
        var filmName=mini.get("filmName");
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
        var createName=mini.get("createName");
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
                        createName.setValue(data.name);
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
            url: "../empdata/record!saveOrUpdate.action",
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
	function deleImgD(){
			$("#upImgName").children().remove();
			$("#upImgName").remove();
			mini.get("imgpath").setValue("");
		}

		$(document).ready(function() {
			$("#highCamera").on("click",function() {
				choseHighCamera();
            });
	        $("#highCamera").on("mouseover mouseout",function() {
		        $(this).toggleClass("webuploader-pick-hover");
	        });
		});

		/**
		 * 利用高拍仪拍照
		 * zhanghj
		 **/
		function choseHighCamera() {
			mini.open({
                url : "../basis/branch!camera.action?path="+path,
                title : "拍照",
                width : 680,
                height : 540,
                onload : function() {
                },
                ondestroy : function(action) {
                	var iFrame = this.getIFrameEl();
                    var data = iFrame.contentWindow.destroy();
                    if (data != "") {
                        data = data.substring(data.indexOf(path));
                        mini.get("imgpath").setValue(data);
                        var file = data.substring(data.lastIndexOf("\\") + 1);
                        var upImgName = document.getElementsByName("upImgName");
                        if (upImgName.length > 0) {
                            $("img[name='upImgName']").attr("src", '${sessionScope.path}'+data);
                            $("img[name='upImgName']").attr("width", "150px");
                            $("img[name='upImgName']").attr("height", "100px");
                            $(".info").html(file);
                        } else {
                            $("#fileList").append('<div id="upImgName"  class="file-item thumbnail;upload-state-done" style="border:1px solid #ccc;">'+
                                    '<img name="upImgName"  src="${sessionScope.path}'+data+'" style="width:150px;height:100px;"/> '+
                                    '<div class="info">'+file+'</div></div>');
                            var delPic= document.getElementById("delPic");
                            if (!delPic) {
                                $("#fileList").append('<div style="width:100%;float:left;text-align:center;margin-top:1px;">'+
                                        '<a href="#" onClick="deleImgD()" style="width:50px;height:30px;line-height:38px;background:#2CA8F7;color:#FFF; text-decoration:none;padding:10px 30px;border-radius:10px;">删除</a></div>');
                            }
                        }
                    }
                }
            });
        }
	//***************************************************//
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