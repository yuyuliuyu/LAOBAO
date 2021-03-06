<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
    pageContext.setAttribute("base", basePath);

%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>新增单位信息</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /> 
	<link href="${base}/template/system/css/base.css" rel="stylesheet" type="text/css" />
	<script src="${base}/template/miniui/boot.js" type="text/javascript"></script>
	<script src="../template/plugin/public/add.js" type="text/javascript"></script>
	<link href= "../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" charset="utf-8" src="${base}/template/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${base}/template/ueditor/ueditor.all.js"> </script>
	<script type="text/javascript" charset="utf-8" src="${base}/template/ueditor/lang/zh-cn/zh-cn.js"></script>
	<!--引入皮肤样式-->
	<link href="${base}/template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />

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
	        /*overflow:hidden;*/
	        overflow: inherit;
	        overflow-x:hidden;
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
    	<form id="form1" method="post" style="height: 100%">
        <input class="mini-hidden" name="id" id="id" value="${laborOutsourceApp.id}" style="display:none;"/>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%" margin-bottom="10px">
            <tr>
                <td class="list_left_box1" valign="top" width="15%">项目编号：</td>
                <td class="list_right_box" width="35%">
                	<input name="projectCode" id="projectCode" class="mini-textbox" value="${laborOutsourceApp.projectCode}"
                         vtype="maxLength:50" allowInput="false" emptyText="系统自动生成" width="200px" />
                </td>
                <td class="list_left_box1" valign="top" width="15%">申请单位：</td>
                <td class="list_right_box" width="35%">
                	<input name="applyCom" id="applyCom" class="mini-buttonedit" value="${laborOutsourceApp.applyCom}"
                         text="${applyComName}"   vtype="maxLength:50;required" width="200px" emptyText="请选择..."  onbuttonclick="onApplyCompEdit" />
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">项目名称：</td>
                <td class="list_right_box" width="35%">
                	<input name="projectName" id="projectName" class="mini-textbox" value="${laborOutsourceApp.projectName}"
                         vtype="maxLength:50;required" width="200px" />
                </td>
                <td class="list_left_box1" valign="top" width="15%">付款单位：</td>
                <td class="list_right_box" width="35%">
                	<input name="payCom" id="payCom" class="mini-buttonedit" value="${laborOutsourceApp.payCom}"
                         text="${payComName}"   vtype="maxLength:50;required" width="200px" emptyText="请选择..."  onbuttonclick="onApplyCompEdit" />
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">前期采购价格：</td>
                <td class="list_right_box" width="35%">
                	<input name="prePurPrice" id="prePurPrice" class="mini-textbox" value="${laborOutsourceApp.prePurPrice}"
                         vtype="maxLength:50;float" width="200px" />
                </td>
                <td class="list_left_box1" valign="top" width="15%">预计合同值：</td>
                <td class="list_right_box" width="35%">
                	<input name="estimateConValue" id="estimateConValue" class="mini-textbox" value="${laborOutsourceApp.estimateConValue}"
                         vtype="maxLength:50;float" width="200px" />
                </td>
                
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">前期承包方：</td>
                <td class="list_right_box" width="35%">
                	<input name="preContractor" id="preContractor" class="mini-textbox" value="${laborOutsourceApp.preContractor}"
                         vtype="maxLength:50;required" width="200px" />
                </td>
                <td class="list_left_box1" valign="top" width="15%">时间要求：</td>
                <td class="list_right_box" width="35%">
                	<input name="timeRequest" id="timeRequest" class="mini-textbox" value="${laborOutsourceApp.timeRequest}"
                         vtype="maxLength:50;required" width="200px" />
                </td>
            </tr>
            <tr>
            	<td class="list_left_box1" valign="top" width="15%">技术要求：</td>
                <td class="list_right_box" width="35%" colspan="3">
                	<input name="technicalReq" id="technicalReq" class="mini-textarea" value="${laborOutsourceApp.technicalReq}" 
                               required="true" requiredErrorText="不能为空" vtype="maxLength:50" width="90%" height="100px"/>
                </td>
            </tr>
            <tr>
            	<td class="list_left_box1" valign="top" width="15%">审批意见：</td>
                <td class="list_right_box" width="35%" colspan="3"> 
                	${formdata }
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
        window.onload=onLoad();
        function onLoad(){
        	var id = mini.get("id").getValue();
        	
        }
        
        function isEnglishAndNum(e){
        	var rex = new RegExp("^[A-Za-z]+$");
        	if(rex.test(e)){
        		return true;
        	}else{
        		return false;
        	}
        	
        }
        function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            
            if (form.isValid() == false) return;
            
            
            var data = form.getData();
            var json = mini.encode(data); 

            
            window.parent.loading();
            $.ajax({
                url: "../contract/labor_outsource_app!saveOrUpdate.action",
                data:{
                	formdata:json
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
        
		function deleImgD(){
			$("#upImgName").children().remove()
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

		function onApplyCompEdit(e){
			var btnEdit = this;
            mini.open({
                url: "../basis/branch!tree.action",
                showMaxButton: false,
                title: "选择组织机构",
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
                        }
                    }
                }
            });
		}
    </script>
    </body>
</html>