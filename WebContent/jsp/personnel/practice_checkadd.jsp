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
<title>增加</title>
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
        <input class="mini-hidden" name="id" id="id" value="${id}" style="display:none;"/>
        <input class="mini-hidden" name="userId" id="userId" value="${check.userId}" style="display:none;"/>
        
        <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
        		<tr> 
               		<td align="left"   colspan="6" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" ><b>考核信息：</b></td> 
               		<td align="left" class="list_left_box1"  colspan="7" > </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="18%">员工编号：</td>
                    <td class="list_right_box" width="32%">
                        <input name="praticeNum" id="praticeNum" class="mini-textbox" value="${check.praticeNum}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="18%">员工姓名：</td>
                    <td class="list_right_box" width="32%">
                        <input name="applyName" id="applyName" class="mini-textbox" value="${check.applyName}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" width="90%"/>
                    </td>
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">考核名称：</td>
                    <td class="list_right_box">
                        <input name="checkName" id="checkName" class="mini-textbox" value="${check.checkName}"
                               required="true" vtype="maxLength:50" width="90%"/>
                    </td> 
                    <td class="list_left_box1" valign="top">总评分：</td>
                    <td class="list_right_box">
                        <input name="score" id="score" required="true" class="mini-textbox" value="${check.score}"  width="90%"/>
                    </td> 
                    
                </tr>
                
                <tr>
                    <td class="list_left_box1" valign="top">开始日期：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									id="begindate" name="begindate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${check.begindate}"/>
                    </td>  
                    <td class="list_left_box1" valign="top">结束日期：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									id="enddate" name="enddate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${check.enddate}"/>
                    </td>   
                    
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">考核内容：</td>
                     
                    <td class="list_right_box" colspan="3">
                         <input id="checkContent" name="checkContent" class="mini-textarea" value="${check.checkContent}"  maxlength="100"style="width: 96%;height:50px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">目标达成状况：</td>
                    <td class="list_right_box" colspan="3">
                         <input id="goalReach" name="goalReach" class="mini-textarea" value="${check.goalReach}"  maxlength="100"style="width: 96%;height:50px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >自我评价：</td>
                    <td class="list_right_box" colspan="3">
                         <input id="selfEvaluate" name="selfEvaluate" class="mini-textarea" value="${check.selfEvaluate}"  maxlength="100"style="width: 96%;height:50px"/>
                    </td> 
                </tr>
                 <tr>
                	<td class="list_left_box1">流程选择：</td>
                	<td class="list_right_box" colspan="3">
                        <input field="processId" name="processId" id="processId" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText=""  
							width="96%" value="${check.processId}" url="../system/work_flow!getworkflow.action?type=6"/>
                    </td>
                </tr>
            </table>
            </br></br></br>
            <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
	            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
	            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
        		<a class="mini-button" id="give" onclick="giveapp" style="width:60px;margin-right:20px;" iconCls="icon-redo">提报</a> 
        	</div>
        
    </form>
    </div>
    <script type="text/javascript">
        mini.parse();
        function onOk(){
        	debugger
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            var data = form.getData();
            var json = mini.encode(data); 
            if(mini.get("begindate").getValue()>mini.get("enddate").getValue()){
    			alert("开始时间大于结束时间");
    		}else{
    			//strut令牌
                $.ajax({
                    url: "../personnel/practice!saveOrUpdateKhxx.action",
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
        }  
        
        
        function giveapp(){
    		var id = mini.get("id").getValue();
    		var processId = mini.get("processId").getValue();
    		if(id==''||id=='null'){
    			alert("请保存后，再进行提报");
    			return;
    		}else if(processId==''||processId=='null'){
    			alert("请选择流程后，再进行提报");
    			return;
    		}
    		mini.open({
    				url : "../check/overtime_sub!giveapp.action?id="+id+"&type=10&pid="+processId,
    				title : "选择审批发起节点",
    				width : 670,
    				height :440,
    				/* allowResize : false, */
    				onload : function() {
    				},
    				ondestroy : function(action) {
    					//刷新页面
    					window.location.reload();
    				}
    			});
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