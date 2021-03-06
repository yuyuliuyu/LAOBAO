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
<title>增加人员档案信息</title>
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
    <form id="form1" method="post">
        <input class="mini-hidden" name="id" id="id" value="${retire.id}" style="display:none;"/>
        <input class="mini-hidden" name="personId" id="personId" value="${personId}" style="display:none;"/>
        <div>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                <tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>基本信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top" >姓名：</td>
                    <td class="list_right_box" >
                        <input name="name" id="name" class="mini-textarea" value="${information.name}"
                               readonly="true" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >职工号：</td>
                    <td class="list_right_box" >
                        <input name="jobNumber" id="jobNumber" class="mini-textarea" value="${information.jobNumber}"
                               readonly="true" requiredErrorText="不能为空" vtype="maxLength:20" width="90%"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >退休前公司：</td>
                    <td class="list_right_box" >
                        <input name="preFirm" id="preFirm" class="mini-textarea" value="${information.filmName}"
                               readonly="true" requiredErrorText="不能为空" vtype="maxLength:20" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >退休前部门：</td>
                    <td class="list_right_box"  >
                         <input name="preDep" id="preDep" class="mini-textarea" value="${information.departName}"
                                readonly="true" width="90%"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >退休前岗位：</td>
                    <td class="list_right_box" >
                         <input name="post" id="post" class="mini-textarea" value="${information.post}"
                                readonly="true" width="90%"/>
                    </td>
                </tr>
           	    <tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>退休信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top" >退休类别*：</td>
                    <td class="list_right_box">
                        <input id="leaveType" name="leaveType" required="true" requiredErrorText="不能为空" class="mini-combobox" style="width:90%;" value="${retire.leaveType}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955bfabed6015bfb538a000009" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                    <td class="list_left_box1" valign="top" >退休日期*：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="leaveDate" id="leaveDate" required="true" requiredErrorText="不能为空" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${retire.leaveDate}"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" >退休后管理部门*：</td>
                    
                    <td class="list_right_box">
                        <input id="laterDep" name="laterDep" class="mini-buttonedit" onbuttonclick="onButtonEdit" width="90%"
                                value="${retire.laterDep}" allowInput="false"  />  
                    </td>
                    <td class="list_left_box1" valign="top" >退休后管理单位*：</td>
                    
                    <td class="list_right_box">
                        <input id="laterFirm" name="laterFirm" class="mini-buttonedit" width="90%"
                                value="${retire.laterFirm}" allowInput="false"  />  
                    </td>
                    
               </tr>
               <tr>
               		<td class="list_left_box1" valign="top" >审批单位：</td>
                    
                    <td class="list_right_box">
                        <input id="approveFirm" name="approveFirm" class="mini-buttonedit" onbuttonclick="onButtonEdit1" width="90%"
                                value="${retire.approveFirm}" allowInput="false"  />  
                    </td>
                    <td class="list_left_box1" valign="top" >批准文号：</td>
                    <td class="list_right_box" >
                         <input name="fileNum" id="fileNum" class="mini-textarea" value="${retire.fileNum}"
                                width="90%"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >其他说明：</td>
                    <td class="list_right_box" colspan="3">
                        <input id="remark" name="remark" class="mini-textarea" value="${retire.remark}"  maxlength="500"style="width: 96%;height:50px"/>
                    </td> 
                    
                </tr>
            </table>
        </div>
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        </div>
    </form>
    
    <script type="text/javascript">
        mini.parse();
		function onButtonEdit(e) {
            var btnEdit = this;
            var laterFirm=mini.get("laterFirm");
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
                            laterFirm.setValue(data.branchId);
                            $.ajax({
                                url: "../personnel/change!findFilmName.action?filmId="+data.branchId,
                                success: function (text) {
                                    if(text!=null){
                                    	laterFirm.setText(text);
                                    }
                                }
                            }); 
                            
                        }
                    }
                }
            });
            
        }
		
		function onButtonEdit1(e) {
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
        function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            var data = form.getData();
            var json = mini.encode(data); 
            //strut令牌
            $.ajax({
                url: "../personnel/departure!tuixiuUpdate.action",
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