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
        
        <input class="mini-hidden" name="id" id="id" value="${orgHisMiddle.id}" style="display:none;"/>
        <input class="mini-hidden" name="attachment" id="attachment" value="${orgHisMiddle.attachment}" style="display:none;"/>
       
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%" margin-bottom="10px">
                <tr>
	                <td class="list_left_box1" valign="top" width="15%">申请单位：</td>
	                <td class="list_right_box" width="35%">
	                	<input name="applyComp" id="applyComp" class="mini-buttonedit" value="${orgHisMiddle.applyComp}"
	                         text="${appCompName}"   vtype="maxLength:50;required" width="200px" emptyText="请选择..."  onbuttonclick="onApplyCompEdit" />
	                </td>
	                <td class="list_left_box1" valign="top" width="15%">变更类型：</td>
	                <td class="list_right_box" width="35%">
	                	<input  name="changeType" id="changeType" class="mini-combobox" style="width:200px;" textField="text" valueField="id" emptyText="请选择..." 
	                        url="${base}/template/changeType.txt" required="true" readonly="readonly" showNullItem="true" nullItemText="请选择..." value="${orgHisMiddle.changeType}"/>
	                </td>
	                
	            </tr>
	            <tr>
	            	<td class="list_left_box1" valign="top" width="15%">变更原因：</td>
	                <td class="list_right_box" width="35%" colspan="3">
	                	<input name="changeReason" id="changeReason" class="mini-textarea" value="${orgHisMiddle.changeReason}" 
	                               required="true" requiredErrorText="不能为空" vtype="maxLength:50" width="90%" height="100px"/>
	                    
	                </td>
	            </tr>
                <tr>
            	<td class="list_left_box1" valign="top">附件：</td>
                <td class="list_right_box" colspan="3">
                    <a class="mini-button" id="uploadBut" onclick="upImage();">上传图片</a>	
                    <%--<span class="warnInfo"><span class="icon">&nbsp;</span>图片宽高比例64:37,建议尺寸640（宽度）*370（高度）！</span>--%>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">上级机构：</td>
                <td class="list_right_box" width="35%">
                	<input name="effectiveId" id="effectiveId" class="mini-buttonedit" value="${orgHisMiddle.effectiveId}"
                         text="${effectiveName}"   vtype="maxLength:50;required" width="200px" emptyText="请选择..."  onbuttonclick="onApplyCompEdit" />
                    
                </td>
                <td class="list_left_box1" valign="top" width="15%">变更机构：</td>
                <td class="list_right_box" width="35%">
                	<input name="changeName" id="changeName" class="mini-textbox" value="${orgHisMiddle.changeName}" 
                               required="true" enabled="false" requiredErrorText="不能为空" vtype="maxLength:50" width="200px" />
                    
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
            
            
            var data = form.getData();
            var json = mini.encode(data); 

            var arr=[];
            var a = $("img[name='upImg']");
            for(var i=0;i<a.length;i++){
            	arr.push(a[i].src);
            	
            }
            window.parent.loading();
            $.ajax({
                url: "../branch/dept_change!saveOrUpdate.action",
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