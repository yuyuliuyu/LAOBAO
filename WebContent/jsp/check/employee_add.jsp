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
<title>编辑人员档案信息</title>
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
</style>
<script src="../template/miniui/js/TreeSelectWindow.js" type="text/javascript"></script>
</head>
<body>
    <form id="form1" method="post">
        <input class="mini-hidden" name="id" id="id" value="${map.id}" style="display:none;"/>
        <input class="mini-hidden" name="checkUnitId" id="checkUnitId" value="${map.checkUnitId}" style="display:none;"/>
        <div>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">职工号：</td>
                    <td class="list_right_box" width="60%">
                        <input name="jobNumber" id="jobNumber" class="mini-textbox" value="${map.jobNumber}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" width="200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">姓名：</td>
                    <td class="list_right_box" width="60%">
                        <input name="name" id="name" class="mini-textbox" value="${map.name}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:20" width="200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">标准岗位：</td>
                    <td class="list_right_box" width="60%">
                        <input name="standardPost" id="standardPost" class="mini-textbox" value="${map.standardPost}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:20" width="200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">具体岗位：</td>
                    <td class="list_right_box" width="60%">
                        <input name="spePost" id="spePost" class="mini-textbox" value="${map.spePost}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:20" width="200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">薪酬岗位：</td>
                    <td class="list_right_box" width="60%">
                        <input name="selaryPost" id="selaryPost" class="mini-textbox" value="${map.selaryPost}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:20" width="200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">考勤单位：</td>
                    <td class="list_right_box">
			        	<input id="checkUnit" name="checkUnit" value="${map.checkUnit}" class="mini-buttonedit" 
			        		required="true" allowInput="false" onbuttonclick="onButtonEdit" style="width:200px;"/>
					</td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">班组名称：</td>
                    <td class="list_right_box" width="60%" >
                        <input id="classNo" name="classNo" class="mini-combobox"
                        	emptyText="请选择..." required="true" requiredErrorText="不能为空"
                          	url="../check/class_group!getClassInfos.action" textField="className" 
                          	valueField="classNo" width="200px" value="${map.classNo}"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">是否班长：</td>
                    <td class="list_right_box" width="60%">
                    	<input id="isMonitor" name="isMonitor" class="mini-radiobuttonlist" repeatItems="4"
                    		required="true" requiredErrorText="不能为空" url="../template/using.txt"
			        		textField="text" valueField="id" value="${map.isMonitor}" />
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">是否特殊工时：</td>
                    <td class="list_right_box" width="60%">
                    	<input id="isSpecialHour" name="isSpecialHour" class="mini-radiobuttonlist" repeatItems="4"
                    		required="true" requiredErrorText="不能为空" url="../template/using.txt"
			        		textField="text" valueField="id" value="${map.isSpecialHour}" />
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">工资形式：</td>
                    <td class="list_right_box" width="60%" >
                        <input id="wageForm" name="wageForm" class="mini-combobox"
                        	emptyText="请选择..." required="true" requiredErrorText="不能为空"
                          	url="../template/wageForm.txt" textField="text" 
                          	valueField="id" width="200px" value="${map.wageForm}"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">考勤编号：</td>
                    <td class="list_right_box" width="60%">
                         <input name="checkNo" id="checkNo" class="mini-textbox" value="${map.checkNo}"
                               required="true" requiredErrorText="不能为空" vtype="maxLength:20" width="200px"/>
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
        
        var checkUnit = mini.get("checkUnit");
        checkUnit.setValue("${map.checkUnit}");
        checkUnit.setText("${map.checkUnit}");
        
        function onButtonEdit(e) {  
			var buttonEdit = e.sender;
            var win = new TreeSelectWindow();
            win.set({
              //multiSelect: true,
              //showFolderCheckBox: true,
              //checkRecursive: true,                
                url: "../basis/branch!getTreeListByUser.action",    
                title: "选择考勤单位",
                width: 350,
                height: 300
            });
            
            win.show();
            //初始化数据
            win.setData(null, function (action) {
                if (action == "ok") {
                   //获取数据
                    var data = win.getData();
                    if (data) {
                        buttonEdit.setValue(data.id);
                        buttonEdit.setText(data.text);
                        mini.get("checkUnitId").setValue(data.id);
                    }
                }
            });
        }
        
        //保存
        function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            //验证
            var classNo = mini.get("classNo").getValue();
            if (classNo == "null"){
            	mini.alert("请选择班组");
            	return;
            }
            var isMonitor = mini.get("isMonitor").getValue();
            if (isMonitor == "null"){
            	mini.alert("请选择是否班长");
            	return;
            }
            var isSpecialHour = mini.get("isSpecialHour").getValue();
            if (isSpecialHour == "null"){
            	mini.alert("请选择是否特殊工时");
            	return;
            }
            var wageForm = mini.get("wageForm").getValue();
            if (wageForm == "null"){
            	mini.alert("请选择工资形式");
            	return;
            }
            var data = form.getData();
            var json = mini.encode(data); 
           	//strut令牌
            window.parent.loading();
            $.ajax({
                url: "../check/employee!saveOrUpdate.action",
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
                //mini.alert(jqXHR.responseText);
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
    </script>
</body>
</html>