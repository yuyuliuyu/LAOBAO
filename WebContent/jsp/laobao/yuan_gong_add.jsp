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
<title>基础信息增加</title>
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
        <input class="mini-hidden" name="id" id="id" value="${information.id}" style="display:none;"/>
        <input class="mini-hidden" name="departId" id="departId" value="${departId}" style="display:none;"/>
        <input class="mini-hidden" name="branchId" id="branchId" value="${branchId}" style="display:none;"/>
        
        <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
        		<tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>基本信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                
                <tr>
                    
                    <td class="list_left_box1" valign="top">职工编号：</td>
                    <td class="list_right_box">
                        <input name="jobNumber" id="jobNumber" class="mini-textbox" value="${information.jobNumber}"
                                vtype="maxLength:32;required" width="100%"/>
                    </td> 
                    <td class="list_left_box1" valign="top">姓名：</td>
                    <td class="list_right_box">
                        <input name="name" id="name" class="mini-textbox" value="${information.name}"
                                vtype="maxLength:20;required" width="100%"/>
                    </td>
				 </tr>
               	 <tr> 
                   <td class="list_left_box1" valign="top">性别：</td>
                    <td class="list_right_box">
                        
                        <input id="sex" name="sex" class="mini-radiobuttonlist" repeatItems="4"
                    		url="../template/sex.txt"
			        		textField="text" valueField="id" value="${information.sex}" />
                    </td> 
                    <td class="list_left_box1" valign="top">年龄：</td>
                    <td class="list_right_box">
                        <input id="age" name="age" class="mini-spinner" minValue="0" value="${information.age}" maxValue="200"style="width: 100%;"/>
                    </td> 
                </tr>

                <tr>
                    <td class="list_left_box1" valign="top">职工来源：</td>
                    <td class="list_right_box">
                        <input id="empSource" name="empSource" class="mini-combobox"
                        	emptyText="请选择..." url="../template/empSource.txt" textField="text" 
                          	valueField="id" width="100%" value="${information.empSource}"/>
                    </td>
                    
                    <td class="list_left_box1" valign="top">在职状态：</td>
                    <td class="list_right_box">
                        <input id="onJob" name="onJob" class="mini-combobox"
                        	emptyText="请选择..."
                          	url="../template/onJob.txt" textField="text" 
                          	 width="100%" value="${information.onJob}"/>
                    </td> 
                </tr>

                <tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>用品信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">鞋号：</td>
                    <td class="list_right_box">
                        <input name="shoe_number" id="shoe_number" class="mini-textbox" value="${information.shoe_number}"
                                vtype="maxLength:15" width="100%"/>
                    </td>
                    <td class="list_left_box1" valign="top">工装号：</td>
                    <td class="list_right_box">
                        <input name="clothes_number" id="clothes_number" class="mini-textbox" value="${information.clothes_number}"
                                vtype="maxLength:15" width="100%"/>
                    </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">岗位类别：</td>
                    <td class="list_right_box">
                        <input name="postType" id="postType" class="mini-textbox" value="${information.postType}"
                                vtype="maxLength:32" width="100%"/>
                    </td>

                </tr> 
               

       		 <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
        		<tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" width="20%"  style="font-size:18px;"><b>最近领取</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr>
                
            </table> 
       		<!--  <div id="showTime" style = "width:90%;margin-left:5%">
	            <div id="grid1" class="mini-treegrid" style="width:100%;height:94%;"
			           showTreeIcon="true" treeColumn="name" idField="did" parentField="pid"
			             resultAsTree="false" allowResize="false" expandOnLoad="true">
			                <div property="columns">
		                        <div type="checkcolumn">选择</div>
		                        <div type="indexcolumn" headerAlign="center">序列</div>
		                        
		                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">姓名</div>
		                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">性别</div>
		                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">政治面貌</div>
		                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">年龄</div>
		                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">出生日期</div>
		                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">与本人关系</div>
		                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">称谓</div>
		                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">民族</div>
		                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">工作单位</div>
		                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">岗位职务</div>
		                    </div>
	       		 </div>
       		</div> -->
            </br></br></br>
            <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
	            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
	            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
	            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        	</div>
        
    </form>
    </div>
    <script type="text/javascript">
   <script type="text/javascript">
        mini.parse();
        
        function onButtonEdit(e) {
        	
            var btnEdit = this;
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
            var branchId=mini.get("branchId");
            var data = form.getData();
            var json = mini.encode(data); 
            //strut令牌
            
            $.ajax({
                url: "../laobao/yuan_gong!saveOrUpdate.action?branchId="+branchId,
                data:{
                	formdata:json,
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