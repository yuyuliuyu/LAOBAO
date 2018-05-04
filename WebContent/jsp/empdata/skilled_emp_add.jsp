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
<title>技术工人增加</title>
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
        <input class="mini-hidden" name="url" id="url" value="${url}" style="display:none;"/>
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
                        <input name="pername" id="pername" class="mini-textbox" value="${info.name}" width="90%"/>
                    </td>
                </tr>
                <tr>
                    
                    <td class="list_left_box1" valign="top" width="15%">单位：</td>
                    <td class="list_right_box" width="30%">
                        <input name="filmName" id="filmName" class="mini-textbox" value="${info.filmName}"width="90%"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">部门：</td>
                    <td class="list_right_box" width="30%">
                        <input name="departName" id="departName" class="mini-textbox" value="${info.departName}"
                                 width="90%"/>
                    </td> 
                </tr>
                
                <tr>
                    
                    <td class="list_left_box1" valign="top" width="15%">标准岗位：</td>
                    <td class="list_right_box" width="30%">
                        <input name="perpost" id="perpost" class="mini-textbox" value="${info.post}"
                                 width="90%"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">具体岗位：</td>
                    <td class="list_right_box" width="30%">
                        <input name="specificPost" id="specificPost" class="mini-textbox" value="${info.specificPost}"
                                 width="90%"/>
                    </td> 
                </tr>
            </table>
        </div>
			<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>技工信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top" >技术工种：</td>
                    <td class="list_right_box" >
                         <input id="skillType" name="skillType" class="mini-combobox" style="width:90%;" value="${skill.skillType}"  textField="dicname" valueField="dicvalue"
		                   		required="true" url="../system/dectionary!listjson.action?id=402881935b855fc1015b8589fdfa0003" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                    <td class="list_left_box1" valign="top" >技术等级：</td>
                    <td class="list_right_box" >
                         <input id="skillGrade" name="skillGrade" class="mini-combobox" style="width:90%;" value="${skill.skillGrade}"  textField="dicname" valueField="dicvalue"
		                   		required="true" url="../system/dectionary!listjson.action?id=402881935b855fc1015b858a561c0004" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >证书编号：</td>
                    <td class="list_right_box" >
                         <input name="papersNum" id="papersNum" class="mini-textarea" value="${skill.papersNum}"
                                required="true" vtype="maxLength:20" style="width: 90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >评审机关：</td>
                    <td class="list_right_box" >
                         <input name="judgeOffice" id="judgeOffice" class="mini-textarea" value="${skill.judgeOffice}"
                                required="true" vtype="maxLength:20" style="width: 90%"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >发证机关：</td>
                    <td class="list_right_box" >
                         <input name="office" id="office" class="mini-textarea" value="${skill.office}"
                                vtype="maxLength:20" style="width: 90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >授予日期：</td>
                    <td class="list_right_box" >
                    	<input class="mini-datepicker" format="yyyy-MM-dd"
							id="awarddate" name="awarddate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									required="true" value="${skill.awarddate}"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >证书获得单位：</td>
                    <td class="list_right_box" >
                         <input name="papersOffice" id="papersOffice" class="mini-textarea" value="${skill.papersOffice}"
                                required="true" vtype="maxLength:20" style="width: 90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >获得形式：</td>
                    <td class="list_right_box" >
                         <input id="getForm" name="getForm" class="mini-combobox" style="width:90%;" value="${skill.getForm}"  textField="dicname" valueField="dicvalue"
		                   		required="true" url="../system/dectionary!listjson.action?id=402881935b855fc1015b858abc6c0005" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                </tr>
                 <tr>
                    <td class="list_left_box1" valign="top" >是否最高级：</td>
                    <td class="list_right_box" >
                         <input id="highestLevel" name="highestLevel" class="mini-combobox"
                        	emptyText="请选择..." style="width:90%;"
                          	url="../template/using.txt" textField="text" 
                          	required="true" valueField="id"  value="${skill.highestLevel}"/>
                    </td>
                    <td class="list_left_box1" valign="top" >聘任职称：</td>
                    <td class="list_right_box" >
                         <input name="appellation" id="appellation" class="mini-textarea" value="${skill.appellation}"
                                required="true" vtype="maxLength:20" style="width: 90%"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >聘期开始：</td>
                    <td class="list_right_box" >
                    	<input class="mini-datepicker" format="yyyy-MM-dd"
							id="begindate" name="begindate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									required="true" value="${skill.begindate}"/>
                    </td>
                    <td class="list_left_box1" valign="top" >聘期结束：</td>
                    <td class="list_right_box" >
                    	<input class="mini-datepicker" format="yyyy-MM-dd"
							id="enddate" name="enddate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									required="true" value="${skill.enddate}"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >等级年限：</td>
                    <td class="list_right_box" >
                         <input name="grade" id="grade" class="mini-textarea" value="${skill.grade}"
                                vtype="maxLength:20" style="width: 90%"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >备注信息：</td>
                    <td class="list_right_box" colspan="3">
                         <input id="remark" name="remark" class="mini-textarea" value="${skill.remark}"  maxlength="500"style="width: 96%;height:50px"/>
                    </td>
                </tr>
                <tr>
               		<td align="left" class="list_left_box1"  height="30px"  style="font-size:18px;width:110px;"><b>文件上传</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
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
                    <td class="list_left_box1" valign="top" width="112px";>
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
           </br></br>
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
	function onOk(){
		var entranceDate = mini.get("begindate").getValue();
       	var graduateDate = mini.get("enddate").getValue();
       	if(entranceDate > graduateDate){
       		mini.alert("起始日期不能晚于结束日期！");
       		return;
       		}
		
        var form = new mini.Form("form1");
        form.validate();
        if (form.isValid() == false) return;
        var data = form.getData();
        var json = mini.encode(data); 
        //strut令牌
        $.ajax({
            url: "../empdata/skilled_emp!saveOrUpdate.action",
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