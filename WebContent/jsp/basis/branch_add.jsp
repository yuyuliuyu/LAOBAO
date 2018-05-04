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
        
        <input class="mini-hidden" name="id" id="id" value="${branch.id}" style="display:none;"/>
        <input class="mini-hidden" name="imgpath" id="imgpath" value="${branch.imgpath}" style="display:none;"/>
         <input class="mini-hidden" name="path" id="path" value="${path}" style="display:none;"/>
       
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%" margin-bottom="10px">
                
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">单位名称：</td>
                    <td class="list_right_box" width="35%" colspan="3">
                    	<s:if test="branch.id!=null&&branch.id!=''">
                            <input name="fzx" id="fzx" class="mini-textbox" value="${branch.fzx}"
                                   enabled="false" vtype="maxLength:25" width="500px" />
                        </s:if>
                        <s:else> 
                                   <input name="fzx" id="fzx" class="mini-textbox" value="${branch.fzx}" 
                                   required="true" requiredErrorText="不能为空" vtype="maxLength:50" width="700px"  />
                        </s:else> 
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">公司编码：</td>
                    <td class="list_right_box" >
                    	<input name="jm" id="jm" class="mini-textarea" value="${branch.jm}"
                               required="true" requiredErrorText="不能为空" vtype="maxLength:25" width="200px"/>
                    </td> 
                    <td class="list_left_box1" valign="top">集团编码：</td>
                    <td class="list_right_box" >
                    	<input name="field1" id="field1" class="mini-textarea" value="${branch.field1}"
                             vtype="maxLength:25" width="200px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">简称：</td>
                    <td class="list_right_box" width="35%">
                        <input name="srm" id="srm" class="mini-textarea" value="${branch.srm}"
                               enabled="true" vtype="maxLength:100" width="200px" />
                        
                    </td>
                    <td class="list_left_box1" valign="top">网址：</td>
                    <td class="list_right_box" >
                    	
                                <input name="webUrl" id="webUrl" class="mini-textbox" value="${branch.webUrl}" 
                                    vtype="maxLength:100" width="200px"/>                     
                    </td>
                </tr>               
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">父级单位：</td>
                    <td class="list_right_box" width="35%">
                    	<input id="btnEdit1" name="pid" class="mini-buttonedit" onbuttonclick="onButtonEdit" width="200px"
                                value="${branch.pid}" text="${pname}" allowInput="false"  />   
                        
                    </td>                    
                    <td class="list_left_box1" valign="top" width="15%">公司性质${branch.type}</td>
                    <td class="list_right_box" width="35%"> 
                        <input id="type" name="type" class="mini-combobox"  style="width:100%;" value="${branch.type}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=8aa385705cbf4c50015cbf9fab540042" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">公司荣誉：</td>
                    <td class="list_right_box" colspan="3">
                    	<input name="honor" id="honor" class="mini-textarea" value="${branch.honor}"
                              vtype="maxLength:400" width="700px" height=“200px”/>
                    </td>
                </tr>
				 <tr>
                    <td class="list_left_box1" valign="top">公司简介：</td>
                    <td class="list_right_box" colspan="3">
                    	<input name="shortRemark" id="shortRemark" class="mini-textarea" value="${branch.shortRemark}"
                              vtype="maxLength:400" width="700px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">备注：</td>
                    <td class="list_right_box" colspan="3">
					
						<input name="remark" id="remark" class="mini-textarea" value="${branch.remark}"
                               vtype="maxLength:400" width="700px"/>
                    	
                    </td>
                </tr>
                <tr>
            	<td class="list_left_box1" valign="top">公司资质：</td>
                <td class="list_right_box" colspan="3">
                    <a class="mini-button" id="uploadBut" onclick="upImage();">上传图片</a>	
                    <%--<span class="warnInfo"><span class="icon">&nbsp;</span>图片宽高比例64:37,建议尺寸640（宽度）*370（高度）！</span>--%>
                </td>
            </tr>
                <tr>
                    <td class="list_right_box" colspan="4">
					
							<fieldset style="border:solid 1px #aaa;margin-top:8px;margin-bottom:10px;position:relative;">
						        <legend>工商注册信息</legend>
						        <div id="editForm1" style="padding:5px;">
						            
						            <table  class="addtable" style="width:100%;" cellpadding="0" cellspacing="0" border="0">
						                <tr>
						                    <td class="list_left_box1" valign="top" width="15%">名称：</td>
						                    <td class="list_right_box" >
						                      <input id="cmName" name="cmName" class="mini-textbox" value="${branch.cmName}" maxlength="100"style="width: 90%;"/>
						                    </td>
						                    <td class="list_left_box1" valign="top" width="15%">类型：</td>
						                    <td class="list_right_box" >
						                      <input id="cmType" name="cmType" class="mini-combobox" value="${branch.cmType}" data="companyType"style="width: 90%;"/>
						                    </td>
						                    <td class="list_left_box1" valign="top" width="15%">法定代表人：</td>
						                    <td class="list_right_box" >
						                      <input id="cmLegal" name="cmLegal" class="mini-textbox" value="${branch.cmLegal}" maxlength="50"style="width: 90%;"/>
						                    </td>
						                </tr>
						                <tr>
						                    <td class="list_left_box1" valign="top" width="15%">住所：</td>
						                    <td class="list_right_box" >
						                    <input id="cmAddr" name="cmAddr" class="mini-textbox" value="${branch.cmAddr}" maxlength="1000"style="width: 90%;"/>
						                    </td>
						                    <td class="list_left_box1" valign="top" width="15%">注册资本：</td>
						                    <td class="list_right_box" >
						                      <input id="cmZb" name="cmZb" class="mini-spinner" minValue="0" value="${branch.cmZb}" maxValue="999999"style="width: 90%;"/>
						                    </td>
						                    <td class="list_left_box1" valign="top" width="15%">成立日期：</td>
						                    <td class="list_right_box" >
                                                <input id="cmEstablishDate" name="cmEstablishDate" class="mini-datepicker" value="${branch.cmEstablishDate}" style="width: 90%;"/>
                                            </td>
						                </tr>
						                <tr>
						                    <td class="list_left_box1" valign="top" width="15%">营业期限(上限)：</td>
						                    <td class="list_right_box" >
						                      <input id="cmYyqxHigh" name="cmYyqxHigh" class="mini-datepicker" value="${branch.cmYyqxHigh}" forstyle="width: 90%;"mat="yyyy年MM月dd日"/>
						                    </td>
						                    <td class="list_left_box1" valign="top" width="15%">营业期限(下限)：</td>
						                    <td class="list_right_box" >
						                      <input id="cmYyqxLow" name="cmYyqxLow" class="mini-datepicker" value="${branch.cmYyqxLow}" style="width: 90%;"/>
						                    </td>
						                </tr>
						                <tr>
						                    <td  class="list_left_box1" valign="top" width="15%">统一社会信用代码：</td>
                                            <td class="list_right_box" >
                                              <input name="cmTyshdm" class="mini-textbox" value="${branch.cmTyshdm}"  maxlength="50"style="width: 90%;"/>
                                            </td>
						                    <td class="list_left_box1" valign="top" width="15%">开户行：</td>
						                    <td class="list_right_box" >
						                      <input id="cmBank" name="cmBank" class="mini-textbox" value="${branch.cmBank}"  maxlength="50"style="width: 90%;"/>
						                    </td>
						                    <td  class="list_left_box1" valign="top" width="15%">账号：</td>
						                    <td class="list_right_box" >
						                      <input id="cmZh" name="cmZh" class="mini-textbox" value="${branch.cmZh}" vtype="int" maxlength="50"style="width: 90%;"/>
						                    </td>
						                </tr>
						                <tr>
						                    <td class="list_left_box1" valign="top" width="15%">联系部门：</td>
						                    <td class="list_right_box" >
						                    <input id="cmLxbm" name="cmLxbm" class="mini-textbox" value="${branch.cmLxbm}"  maxlength="50"style="width: 90%;"/>
						                    </td>
						                    <td  class="list_left_box1" valign="top" width="15%">联系人：</td>
						                    <td class="list_right_box" >
						                    <input id="cmLxr" name="cmLxr" class="mini-textbox" value="${branch.cmLxr}" maxlength="50" style="width: 90%;"/>
						                    </td>
						                    <td  class="list_left_box1" valign="top" width="15%">职务：</td>
						                    <td class="list_right_box" >
						                    <input name="cmZw" class="mini-textbox" value="${branch.cmZw}"  maxlength="50"style="width: 90%;"/>
						                    </td>
						                </tr>
						                <tr>
						                    <td class="list_left_box1" valign="top" width="15%">联系电话：</td>
						                    <td class="list_right_box" >
						                      <input id="cmPhone" name="cmPhone" class="mini-textbox" value="${branch.cmPhone}"  maxlength="50"style="width: 90%;"/>
						                    </td>
						                    <td  class="list_left_box1" valign="top" width="15%">传真：</td>
						                    <td class="list_right_box" >
						                      <input id="cmFax" name="cmFax" class="mini-textbox" value="${branch.cmFax}"  maxlength="50"style="width: 90%;"/>
						                    </td>
						                </tr>
						                <tr>
						                    <td  class="list_left_box1" valign="top" width="15%">经营范围：</td>
                                            <td class="list_right_box" width="10%" colspan="5">
                                              <input id="cmJyfw" name="cmJyfw" class="mini-textarea" value="${branch.cmJyfw}"  maxlength="1000"style="width: 90%;height:50px"/>
                                            </td>
						                </tr>
						                <tr>
						                    <td  class="list_left_box1" valign="top" width="15%">通信地址：</td>
						                    <td class="list_right_box" width="10%" colspan="5">
                                              <input name="cmTxdz" class="mini-textarea" value="${branch.cmTxdz}"  maxlength="1000" style="width: 90%;height:50px"/>
                                            </td>
						                </tr>
						                <tr>
                                            <td  class="list_left_box1" valign="top" width="15%">网页：</td>
						                    <td class="list_right_box" width="10%"colspan="5">
                                              <input name="cmWeb" class="mini-textarea" value="${branch.cmWeb}"  maxlength="1000"style="width: 90%;height:50px"/>
                                            </td>
						                </tr>
						            </table>
						        </div>
						    </fieldset>
                    	
                    </td>
                </tr>
				<tr >
	                <td class="list_left_box1" valign="top">营业执照：</td>
	                <td class="list_right_box" colspan="3">
	               		<div id="uploader-demo" class="wu-example">
						    <!--用来存放item-->
						    <div id="fileList" class="uploader-list">
						     <s:if test="branch.imgpath!=null&&branch.imgpath!=''">
						     		<div id="upImgName"  class="file-item thumbnail;upload-state-done" style="border:1px solid #ccc;"> 
					                    <img name="upImgName"  src="${sessionScope.path}${branch.imgpath}" style="width:150px;height:100px;"/> 
					                    <div class="info"></div> 
				                	</div>
						     </s:if>
						     <div id="delPic" style='width:100%;float:left;text-align:center;margin-top:1px;'>
				        		<a href='#' onClick="deleImgD()" style='width:50px;height:30px;line-height:38px;background:#2CA8F7;color:#FFF; text-decoration:none;padding:10px 30px;border-radius:10px;'>删除</a>
				        	 </div>
				        	</div>
						    </div>
						    <div id="filePicker" style="display: inline;">选择图片</div>
						    <div id = "highCamera" class="webuploader-pick">高拍仪拍照</div>
	                </td>
           	    </tr>
        </table>
            
    <div style="display:none;"/>
        	<script type="text/plain" id="myEditor" ></script>
			<script type="text/plain" id="upload_ue"></script>             
    </div>
    <div id="imgmu" style="margin-top: 10px;">
        	<s:iterator value="imgList" id="list" status="st">
	        	<s:if test="#list!=null" >
	        		<div id="upImgA<s:property value='#st.index'/>"  style='width:30%;margin-left:2%;text-align:center;float:left;height:auto;padding-bottom:70px;padding-top:70px;'>
		        	<img src="${sessionScope.path}<s:property value='#list.path'/>"  name='upImg' style='width:300px;height:278px;float:left;border:1px solid #ccc;'/>
		        	<div style='width:100%;float:left;text-align:center;margin-top:15px;'>
		        		<a href='#' onClick="deleImgA(<s:property value='#st.index'/>)" style='width:50px;height:30px;line-height:30px;background:#2CA8F7;color:#FFF; text-decoration:none;padding:10px 30px;border-radius:10px;'>删除</a></div>
		        	</div>
	        	</s:if>
        	</s:iterator>
    </div>
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
        var path = "/licence";
        function onLoad(){
        	var id = mini.get("id").getValue();
        	if(id!=null&&id!=""){
        		mini.get("jm").disable();
        	}
        }
        function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "../basis/branch!tree.action",
                showMaxButton: false,
                title: "选择父级分中心",
                width: 250,
                height: 330,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        
                        if(mini.get("id").getValue()==data.id){
                            mini.alert("父级分中心不能是自己！");
                            return;
                        }
                        if (data) {
                            btnEdit.setValue(data.id);
                            btnEdit.setText(data.name);
                        }
                    }
                }
            });
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
            
            var name = mini.get("fzx").getValue();
            var srm = mini.get("srm").getValue();
            var id = mini.get("id").getValue();
            var pid = mini.get("btnEdit1").getValue();
            var data = form.getData();
            var json = mini.encode(data); 

            var arr=[];
            var a = $("img[name='upImg']");
            for(var i=0;i<a.length;i++){
            	arr.push(a[i].src);
            	
            }
            window.parent.loading();
            $.ajax({
                url: "../basis/branch!saveOrUpdate.action",
                data:{
                	formdata:json,
                	img : arr.join(","),
                    child:"${child}"
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
    </script>
    </body>
</html>