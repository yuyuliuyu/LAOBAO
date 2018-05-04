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
<title>职务</title>
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
<div class="mini-fit">
    <form id="form1" method="post">
        <input class="mini-hidden" name="flg" id="flg" value="${flg}" style="display:none;"/>
        <input class="mini-hidden" name="id" id="id" value="${postPosition.id}" style="display:none;"/>
        <input class="mini-hidden" name="cid" id="cid" value="${cid}" style="display:none;"/>
        <input class="mini-hidden" name="dutyId" id="dutyId" value="${postPosition.dutyId}" style="display:none;"/>
        
        <table cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                    <tr>
                        <td class="list_left_box2">
                                                                   职务信息
                        </td>
                    </tr>
                </tbody>
        </table>
        <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
            <tr>
                <td class="list_left_box1" valign="top" width="15%">编码：</td>
                <td class="list_right_box" width="30%">
                    <input name="positionCode" id="positionCode" class="mini-textbox" value="${postPosition.positionCode}"
                            vtype="maxLength:20;required" width="200px"/>
                </td> 
                <td class="list_left_box1" valign="top" width="15%">名称：</td>
                <td class="list_right_box" width="30%">
                    <input name="positionName" id="positionName" class="mini-textbox" value="${postPosition.positionName}"
                            vtype="maxLength:50;required" width="200px"/>
                </td> 
            </tr>
             <tr>
                <td class="list_left_box1" valign="top" width="15%">岗位性质：</td>
                <td class="list_right_box" width="30%">
                    <input name="positionnature" id="positionnature" class="mini-textbox" value="${postPosition.positionnature}"
                            vtype="maxLength:20;required" width="200px"/>
                </td> 
                <td class="list_left_box1" valign="top" width="15%">岗位类别：</td>
                <td class="list_right_box" width="30%"> 
                
                    <input  name="positioncategory" id="positioncategory" class="mini-combobox" style="width:200px;" textField="text" valueField="id" emptyText="请选择..." 
                        url="../system/dectionary!getpid.action?pid=402881825c152f73015c1573bffd000b"  showNullItem="true" nullItemText="请选择..." value="${postPosition.positioncategory}"/>    
                </td> 
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">职等：</td>
                <td class="list_right_box" width="30%">
                    <input name="positionGrad" id="positionGrad" class="mini-buttonedit" value="${postPosition.positionGrad}"
                         text="${positionGrade.name}"   vtype="maxLength:20;required" width="200px" emptyText="请选择..."  onbuttonclick="onGradeEdit" />
                    
                </td> 
                <td class="list_left_box1" valign="top" width="15%">生效日期：</td>
                <td class="list_right_box" width="30%">
                    <input name="applyDate" id="applyDate" class="mini-datepicker" value="${postPosition.applyDate}"
                            vtype="maxLength:50;required" width="200px"/>
                </td> 
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" >工作内容：</td>
                <td class="list_right_box" colspan="3">
                    <input name="jobContent" id="jobContent" class="mini-textbox" value="${postPosition.jobContent}"
                            vtype="maxLength:20;required" width="100%"/>
                </td> 
            </tr>
        </table>
        <table cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                    <tr>
                        <td class="list_left_box2">
                                                                   任职要求
                        </td>
                    </tr>
                </tbody>
        </table>
        <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
            <tr>
                <td class="list_left_box1" valign="top" width="15%">学历：</td>
                <td class="list_right_box" width="30%">
                    <input  name="education" id="education" class="mini-combobox" style="width:200px;" textField="text" valueField="id" emptyText="请选择..." 
                        url="../system/dectionary!getpid.action?pid=402881a05b650042015b6617c989002f"  showNullItem="true" nullItemText="请选择..." value="${postPosition.education}"/>    
                    
                </td> 
                <td class="list_left_box1" valign="top" width="15%">专业：</td>
                <td class="list_right_box" width="30%">
                    <input  name="profession" id="profession" class="mini-combobox" style="width:200px;" textField="text" valueField="id" emptyText="请选择..." 
                        url="../system/dectionary!getpid.action?pid=402881a05b650042015b6635803c0039"  showNullItem="true" nullItemText="请选择..." value="${postPosition.profession}"/>
                </td> 
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">婚姻：</td>
                <td class="list_right_box" width="30%">
                    <input  name="marriage" id="marriage" class="mini-combobox" style="width:200px;" textField="text" valueField="id" emptyText="请选择..." 
                        url="../system/dectionary!getpid.action?pid=402881a05b650042015b6639e3d1003b"  showNullItem="true" nullItemText="请选择..." value="${postPosition.marriage}"/>
                </td> 
                <td class="list_left_box1" valign="top" width="15%">外语：</td>
                <td class="list_right_box" width="30%">
                    <input  name="foreign" id="foreign" class="mini-combobox" style="width:200px;" textField="text" valueField="id" emptyText="请选择..." 
                        url="../system/dectionary!getpid.action?pid=402881a05b650042015b663bdfe9003d"  showNullItem="true" nullItemText="请选择..." value="${postPosition.foreign}"/>
                </td> 
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">户籍：</td>
                <td class="list_right_box" width="30%">
                    <input name="residence" id="residence" class="mini-textbox" value="${postPosition.residence}"
                            vtype="maxLength:20" width="200px"/>
                </td> 
                <td class="list_left_box1" valign="top" width="15%">性别：</td>
                <td class="list_right_box" width="30%">
                    <input id="sex" name="sex" class="mini-radiobuttonlist" repeatItems="4"
                    		url="../template/sex.txt" textField="text" valueField="id" value="${postPosition.sex}" />
                </td> 
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">专业知识：</td>
                <td class="list_right_box" width="30%">
                    <input name="proKnowledge" id="proKnowledge" class="mini-textbox" value="${postPosition.proKnowledge}"
                            vtype="maxLength:20" width="200px"/>
                </td> 
                <td class="list_left_box1" valign="top" width="15%">技能资格：</td>
                <td class="list_right_box" width="30%">
                    <input name="wsq" id="wsq" class="mini-textbox" value="${postPosition.wsq}"
                            vtype="maxLength:50" width="200px"/>
                </td> 
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">形象气质：</td>
                <td class="list_right_box" width="30%">
                    <input name="elegance" id="elegance" class="mini-textbox" value="${postPosition.elegance}"
                            vtype="maxLength:20" width="200px"/>
                </td> 
                <td class="list_left_box1" valign="top" width="15%">文字表达：</td>
                <td class="list_right_box" width="30%">
                    <input name="wordExpression" id="wordExpression" class="mini-textbox" value="${postPosition.wordExpression}"
                            vtype="maxLength:50" width="200px"/>
                </td> 
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">最小年龄：</td>
                <td class="list_right_box" width="30%">
                    <input name="minAge" id="minAge" class="mini-textbox" value="${postPosition.minAge}"
                            vtype="float;range:0,1000" width="200px"/>
                </td> 
                <td class="list_left_box1" valign="top" width="15%">最大年龄：</td>
                <td class="list_right_box" width="30%">
                    <input name="maxAge" id="maxAge" class="mini-textbox" value="${postPosition.maxAge}"
                            vtype="float;range:0,1000" width="200px"/>
                </td> 
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">最小身高(cm)：</td>
                <td class="list_right_box" width="30%">
                    <input name="minHeight" id="minHeight" class="mini-textbox" value="${postPosition.minHeight}"
                            vtype="float;range:0,1000" width="200px"/>
                </td> 
                <td class="list_left_box1" valign="top" width="15%">最大身高(cm)：</td>
                <td class="list_right_box" width="30%">
                    <input name="maxHeight" id="maxHeight" class="mini-textbox" value="${postPosition.maxHeight}"
                            vtype="float;range:0,1000" width="200px"/>
                </td> 
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">最小工作年限：</td>
                <td class="list_right_box" width="30%">
                    <input name="minWorkSeniority" id="minWorkSeniority" class="mini-textbox" value="${postPosition.minWorkSeniority}"
                            vtype="range:0,1000" width="200px"/>
                </td> 
                <td class="list_left_box1" valign="top" width="15%">最大工作年限：</td>
                <td class="list_right_box" width="30%">
                    <input name="maxWorkSeniority" id="maxWorkSeniority" class="mini-textbox" value="${postPosition.maxWorkSeniority}"
                            vtype=";range:0,1000" width="200px"/>
                </td> 
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">学位：</td>
                <td class="list_right_box" width="30%">
                    <input  name="degree" id="degree" class="mini-combobox" style="width:200px;" textField="text" valueField="id" emptyText="请选择..." 
                        url="../system/dectionary!getpid.action?pid=402881a05b650042015b663e8700003f"  showNullItem="true" nullItemText="请选择..." value="${postPosition.degree}"/>
                </td> 
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" >其他：</td>
                <td class="list_right_box" colspan="3">
                    <input name="remark" id="remark" class="mini-textarea" value="${postPosition.remark}"
                            vtype="maxLength:20" width="100%"/>
                </td> 
            </tr>
        </table>
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
            <a class="mini-button"  onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        </div>
        
    </form>
 </div>  
    <script type="text/javascript">
        mini.parse();
        window.onload=onLoad();
        var form = new mini.Form("form1");
        //判断是否查看
        if(mini.get("flg").getValue()=="false"){
        	
            var fields = form.getFields();
            for (var i = 0, l = fields.length; i < l; i++) {
                var c = fields[i];
                if (c.setReadOnly) c.setReadOnly(true);     //只读
                if (c.setIsValid) c.setIsValid(true);      //去除错误提示
            }
            mini.get("ok").setVisible(false);
            mini.get("cz").setVisible(false);
        }
        function onLoad(){
        	var id = mini.get("id").getValue();
        	if(id!=null&&id!=""){
        		
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
            
            var jm = mini.get("positionCode").getValue();
            /* if(!isEnglishAndNum(jm)){
            	mini.alert("简码必须是英文!");
            	return;
            } */
            var data = form.getData();
            var json = mini.encode(data); 
            //strut令牌
            window.parent.loading();
            $.ajax({
                url: "../post/post!postionSave.action",
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
        function onGradeEdit(e){
        	var btnEdit = this;
        	
        	mini.open({
            url: "../post/position_grade!show.action",
            showMaxButton: false,
            title: "选择职等信息",
            width: 600,
            height: 500,
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