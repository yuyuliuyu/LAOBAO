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
<title>增加分中心部门</title>
<link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css" />
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="${base}/template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${base}template/plugin/webuploader/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${base}template/plugin/webuploader/style.css" />
<script type="text/javascript" src="${base}template/plugin/webuploader/webuploader.js"></script>
<script type="text/javascript" src="${base}template/plugin/webuploader/image_upload.js"></script>
<script type="text/javascript" src="${base}template/plugin/public/add.js"></script>
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
        
        <input class="mini-hidden" name="id" id="id" value="${department.id}" style="display:none;"/>
        <input class="mini-hidden" name="cid" id="cid" value="${cid}" style="display:none;"/>
        
        <div>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">部门名称：</td>
                    <td class="list_right_box" width="30%">
                        <s:if test="department.id==null">
                            <input name="name" id="name" class="mini-textbox" value="${department.name}" onblur="onblur('name','input_code')"
                                   required="true" requiredErrorText="不能为空" vtype="maxLength:50" width="200px"/>
                        </s:if>
                        <s:else>
                            <input name="name" id="name" class="mini-textbox" value="${department.name}"
                                   enabled="false" vtype="maxLength:50" width="200px"/>
                        </s:else>
                    </td>
                     <td class="list_left_box1" valign="top" width="15%">输入码：</td>
                    <td class="list_right_box" width="30%">
                        <input name="input_code" id="input_code" class="mini-textarea" value="${department.input_code}"
                               enabled ="false"emptyText="系统自动生成" width="200px"/>
                    </td>
                </tr>
                 <tr>
                   <td class="list_left_box1" valign="top" width="15%">部门描述：</td>
                    <td class="list_right_box" width="30%">
                        <input name="description" id="description" class="mini-textarea" value="${department.description}"
                               required="true" requiredErrorText="不能为空" vtype="maxLength:100" width="200px"/>
                    </td>
                     <td class="list_left_box1" valign="top" width="10%">父级部门：</td>
                    <td class="list_right_box" width="30%">
                         <input id="parent" name="parent"class="mini-buttonedit" onbuttonclick="onButtonEdit" width="200px"
                                value="${department.parent}" text="${pname}" allowInput="false" enable="false"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="10%">是否功能科室：</td>
                    <td class="list_right_box" width="30%">
                         <input id="isgnks" name="is_function"class="mini-combobox" width="200px"textField="text" valueField="id" 
                                value="${department.is_function==null?0:department.is_function}"  allowInput="false"enable="false" />
                    </td>
                    <td class="list_left_box1" valign="top" width="15%">序号：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${department.xh}"
                               required="true" requiredErrorText="不能为空" vtype="maxLength:3" width="200px"enable="false"/>
                    </td> 
                </tr>
                <td class="list_left_box1" valign="top" width="10%">数据报告格式：</td>
                    <td class="list_right_box" width="30%">
                         <input id="sjbggs" name="sjbggs"class="mini-combobox" width="200px"textField="text" valueField="id" 
                                value="${department.sjbggs==null?0:department.sjbggs}"  allowInput="false" enable="false"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="15%">检查地点：</td>
                    <td class="list_right_box" width="30%">
                        <input name="jcdd" id="jcdd" class="mini-textbox" value="${department.jcdd}"
                               required="true" requiredErrorText="不能为空" vtype="maxLength:100" width="200px"enable="false"/>
                    </td> 
                <tr>
                   <td class="list_left_box1" valign="top" width="10%">接口类型：</td>
                    <td class="list_right_box" width="30%">
                         <input id="jklx" name="jklx"class="mini-combobox" width="200px"textField="text" valueField="id" 
                                value="${department.jklx==null?0:department.jklx}"  allowInput="false"enable="false" />
                    </td>
                    <td class="list_left_box1" valign="top" width="15%">科室号：</td>
                    <td class="list_right_box" width="30%">
                        <input name="ksh" id="ksh" class="mini-textbox" value="${department.ksh}"
                               required="true" requiredErrorText="不能为空" vtype="maxLength:100" width="200px"enable="false"/>
                    </td> 
                </tr>
                
                
            </table>
            <table cellpadding="0" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<td class="list_left_box2">图片1</td>
					</tr>
				</tbody>
			</table>
            	<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
				<tr>
					<td class="list_left_box1" valign="top">图片2：</td>
					<td class="list_right_box">
						<div id="scdiv" >
							<div id="uploader-demo" class="wu-example">
								<!--用来存放item-->
								<div id="fileList" class="uploader-list">
								</div>
								<div id="filePicker">选择图片</div>
							</div>
						</div>
						<div id="upImgAs">
							<s:if test="department.imgpath!=null && department.imgpath!=''">
									<div  style="border:1px solid #ccc;width:100px;height:100px;"> 
										<img name="upImgName"  src="${base}${department.imgpath}" style="height:100px;"/>
										<div style='width:100%;float:left;text-align:center;margin-top:15px;'>
											<a href='#' onClick="deleImgA()" style='width:50px;height:30px;line-height:30px;background:#2CA8F7;color:#FFF; text-decoration:none;padding:10px 30px;border-radius:10px;'>删除</a>
										</div>
										<div class="info"></div> 
									</div>
							</s:if>
						</div>
					</td>
				</tr>
				
				<tr style="height:50px">
					<td colspan="2"></td>
				</tr>
			</table>
			<input class="mini-textbox" id="imgpath" name="imgpath" value="${department.imgpath}"style="display:none;" />
        </div>
		<div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        </div>
        
    </form>
    
    <script type="text/javascript">
        mini.parse();
        window.onload=winload;
        function winload(){
        	var _img = $("input[name='imgpath']").val();
        	if(_img!=null&&_img!=""&&typeof(_img)){
        		$("#scdiv").css("display","none");
        	}else{
        		$("#upImgAs").css("display","none");
        	}
        }
        var gndata=[{"id":0,"text":"否"},{"id":1,"text":"是"}];
        mini.get("isgnks").setData(gndata);
        
        var sjbggs=[{"id":0,"text":"检查描述"},{"id":1,"text":"参考范围"},{"id":2,"text":"常规检查"}];
        mini.get("sjbggs").setData(sjbggs);
        
        var jklx = [{"id":0,"text":"HIS"},{"id":1,"text":"LIS"},{"id":2,"text":"US彩超"},{"id":3,"text":"ECG"},{"id":4,"text":"心电图"}];
        mini.get("jklx").setData(jklx);
        function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "../depart/backend_dep!tree1.action",
                showMaxButton: false,
                title: "选择父级部门",
                width: 250,
                height: 330,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        console.log(data);
                        var isFunction = data.isFunction;
                        if(isFunction==1){
                        	mini.alert("父级部门不能是功能科室!");
                        	return;
                        }
                        if(mini.get("name").getValue()==data.name){
                            mini.alert("父级部门不能是自己！");
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
        
        function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            
            if (form.isValid() == false) return;
            
            var formdata = form.getData();
            var formjson = mini.encode(formdata);
            //strut令牌
            window.parent.loading();
            $.ajax({
                url: "../depart/backend_dep!saveOrUpdata.action",
                type:"POST",
                data:{
                	formData:formjson,
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
            mini.get("imgpath").setValue("${department.imgpath}");
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