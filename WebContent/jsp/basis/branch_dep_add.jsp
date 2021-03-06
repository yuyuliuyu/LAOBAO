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
<title>分中心部门</title>
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
	
	<div  style="width:100%;float:left" >
    <form id="form1" method="post">
        
        <input class="mini-hidden" name="id" id="id" value="${department.id}" style="display:none;"/>
        <input class="mini-hidden" name="barchId" id="barchId" value="${barchId}" style="display:none;"/>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">部门名称：</td>
                    <td class="list_right_box" width="60%" colspan="3">
                    	<input name="name" id="name" class="mini-textbox" value="${department.name}"
                         	  vtype="maxLength:100" width="100%" /> 
                    </td>
                </tr>
                <tr>
                    
                    <td class="list_left_box1" valign="top" width="15%">部门编码：</td>
                    <td class="list_right_box" width="35%">
                    	<input name="description" id="description" class="mini-textbox" value="${department.description}"
                               vtype="maxLength:100"  width="100%"/> 
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">集团编码：</td>
                    <td class="list_right_box" width="35%">
                    	<input name="reportPathHealth" id="reportPathHealth" class="mini-textbox" value="${department.reportPathHealth}"
                               vtype="maxLength:100"  width="100%"/> 
                    </td>
                    
                </tr>
                <tr>
                 <td class="list_left_box1" valign="top" width="15%">简称：</td>
                    <td class="list_right_box" width="35%">
                    	<input name="input_code" id="input_code" class="mini-textbox" value="${department.input_code}"
                                width="100%" />
                    </td>
                    <td class="list_left_box1" valign="top">父级单位：</td>
                    <td class="list_right_box" >
                         <input id="parent" name="parent" class="mini-textbox"  width="100%"
                                value="${department.parent}"  allowInput="false" style="display: none"/>
                         <input id="barchId" name="barchId" class="mini-textbox"  width="100%"
                                value="${department.barchId}"  allowInput="false" style="display: none"/>
                         <input id="branchName" name="branchName" class="mini-textbox"  width="100%"
                                value="${branch.fzx}"  allowInput="false" enabled ="false"/>
                    </td>
                    
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">职责：</td>
                    <td class="list_right_box" width="35%">
                    	<input name="duty" id="duty" class="mini-textbox" value="${department.duty}"
                         	  vtype="maxLength:25" width="100%"/>
                        
                    </td>
                    
                    <td class="list_left_box1" valign="top" width="15%">部门负责人：</td>
                    <td class="list_right_box" width="35%">
                    	<input name="leader" id="leader" class="mini-textbox" value="${department.leader}"
                         	  vtype="maxLength:25" width="100%"/>
                        
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">办公电话：</td>
                    <td class="list_right_box" width="35%">
                    	<input name="phone" id="phone" class="mini-textbox" value="${department.phone}"
                         	  vtype="maxLength:25" width="100%"/>
                        
                    </td>
                    <td class="list_left_box1" valign="top">成立时间：</td>
                    <td class="list_right_box" >
                    	<input id="setupDate" name="setupDate" class="mini-textbox"  width="100%"
                                value="${department.setupDate}"  allowInput="false" enabled ="false"/>
                    </td>
                   
                </tr>
                
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">邮箱：</td>
                    <td class="list_right_box" width="35%">
                    	<input name="email" id="email" class="mini-textbox" value="${department.email}"
                         	  vtype="maxLength:25" width="100%"/>
                        
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">名誉：</td>
                    <td class="list_right_box" width="35%">
                    	<input name="honor" id="honor" class="mini-textbox" value="${department.honor}"
                         	  vtype="maxLength:25" width="100%"/>
                        
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">备注：</td>
                    <td class="list_right_box" colspan="3">
                    	<input name="remark" id="remark" value="" vtype="maxLength:2000" class="mini-textbox" width="100%" />
                    </td>
                </tr>
              
            </table>
            
    </form>
    
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
      </div>
      <script type="text/javascript">
        mini.parse();
        window.onload=winload;
        function winload(){
        	var _img = $("input[name='imgpath']").val();
        	if(_img!=null&&_img!=""&&typeof(_img)){
        		$("#scdiv").css("display","none");
        		$("#upImgAs").css("display","none");
        		$("#addcendepId").attr("src","${base}"+_img);
            	$("#addcendep").css("display","");
            	
        	}else{
        		$("#upImgAs").css("display","none");
        	}
        	var id = mini.get("id").getValue();
            if(id!=null&&id!=""){
                mini.get("description").disable();
            }
        }
       /*  var gndata=[{"id":0,"text":"否"},{"id":1,"text":"是"}];
        mini.get("isgnks").setData(gndata); */
        
        //var sjbggs=[{"id":0,"text":"检查描述"},{"id":1,"text":"参考范围"},{"id":2,"text":"常规检查"}];
        /* mini.get("sjbggs").setData(sjbggs); */
        
        //var jklx = [{"id":0,"text":"HIS"},{"id":1,"text":"LIS"},{"id":2,"text":"US彩超"},{"id":3,"text":"ECG"},{"id":4,"text":"心电图"}];
        /* mini.get("jklx").setData(jklx); */
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
                        
                        if (data) {
                            btnEdit.setValue(data.id);
                            btnEdit.setText(data.name);
                            mini.get("input_code").setValue(data.input_code);
                            mini.get("description").setValue(data.description);
                            mini.get("parent").setValue(data.pname);
                            mini.get("isgnks").setValue(data.isgnks);
                            mini.get("xh").setValue(data.xh);
                            mini.get("sjbggs").setValue(data.sjbggs);
                            mini.get("jklx").setValue(data.jklx);
                            mini.get("ksh").setValue(data.ksh);
                            var img = data.imgpath;
                            if(img!=null&&img!=""){
                            	mini.get("imgpath").setValue(img);
                            	$("#addcendepId").attr("src","${base}"+img);
                            	$("#addcendep").css("display","");
                            	$("#scdiv").css("display","none");
                            }else{
                            	mini.get("imgpath").setValue("");
                            	$("#addcendepId").attr("src","");
                            	$("#addcendep").css("display","none");
                            	$("#scdiv").css("display","");
                            }
                        }
                    }else{
							  onReload()
                    }
                }
            });
        }
        /**数字判断  lsp*/
        function isNum(e){
        	
        	var patt=new RegExp("^[0-9]+$");
        	if(patt.test(e)){
        		return true;
        	}else{
        		return false;
        	}
        }
        function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            
            if (form.isValid() == false) return;
            var did = mini.get("name").getValue();
            if(did==""||did==null||typeof(did)==undefined){
            	mini.alert("部门名称必填!");
            	return;
            }
           
            var formdata = form.getData();
            var formjson = mini.encode(formdata);
            //strut令牌
            window.parent.loading();
            $.ajax({
                url: "../basis/branch!cenDepSaveOrUpdate.action",
                type:"POST",
                data:{
                	formdata:formjson
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
            mini.get("imgpath").setValue("${imgpath}");
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
        
      //删除预览
        function delcendep() {
        	mini.get("imgpath").setValue("");
        	$("#scdiv").css("display","");
        	$("#addcendep").css("display","none");
        	
        }
    </script>
</body>
</html>