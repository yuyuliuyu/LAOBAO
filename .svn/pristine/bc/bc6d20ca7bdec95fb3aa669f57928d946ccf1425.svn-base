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
<title>查看加班申请</title>
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
        <input class="mini-hidden" name="id" id="id" value="${branch.id}" style="display:none;"/>
        <div>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                <tr>
                    <td class="list_left_box1" valign="top" width="18%">员工工号：</td>
                    <td class="list_right_box" width="32%">
                        <input name="jm" id="jm" class="mini-textbox" value="${branch.jm}"
                            readonly="true" requiredErrorText="不能为空" vtype="maxLength:10" width="150px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="18%">员工姓名：</td>
                    <td class="list_right_box" width="32%">
                        <input name="jm" id="jm" class="mini-textbox" value="${branch.jm}"
                            readonly="true" requiredErrorText="不能为空" vtype="maxLength:10" width="150px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1">部门：</td>
                    <td class="list_right_box">
                        <input name="jm" id="jm" class="mini-textbox" value="${branch.jm}"
                            readonly="true" requiredErrorText="不能为空" vtype="maxLength:10" width="150px"/>
                    </td>
                    <td class="list_left_box1">岗位：</td>
                    <td class="list_right_box">
                        <input name="jm" id="jm" class="mini-textbox" value="${branch.jm}"
                            readonly="true" requiredErrorText="不能为空" vtype="maxLength:10" width="150px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1">加班开始时间：</td>
                    <td class="list_right_box">
                        <input id="securityDate" name="securityDate" class="mini-datepicker" allowInput="false" 
					      	style="width:150px;" format="yyyy-MM-dd" readonly="true" 
					      	value="${jcKjjlFmxm.securityDate }"/>
                    </td>
                    <td class="list_left_box1">加班结束时间：</td>
                    <td class="list_right_box">
                        <input id="securityDate" name="securityDate" class="mini-datepicker" allowInput="false" 
					      	style="width:150px;" format="yyyy-MM-dd" readonly="true" 
					      	value="${jcKjjlFmxm.securityDate }"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1">加班小时：</td>
                    <td class="list_right_box">
                        <input name="jm" id="jm" class="mini-textbox" value="${branch.jm}"
                            readonly="true" requiredErrorText="不能为空" vtype="maxLength:10" width="150px"/>
                    </td>
                    <td class="list_left_box1">加班类型：</td>
                    <td class="list_right_box">
                        <input field="activityName" name="activityName" id="activityName" readonly="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText=""  
							width="150px" value="${activityApply.activityName}" url="../template/overtimeType.txt"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1">备注：</td>
                    <td class="list_right_box" colspan="3">
                        <input name="jm" id="jm" class="mini-textarea" value="${branch.jm}"
                            readonly="true" requiredErrorText="不能为空" vtype="maxLength:10" style="width:100%;height:100px;"/>
                    </td>
                </tr>
            </table>
        </div>
        
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        </div>
        
    </form>
    
    <script type="text/javascript">
        mini.parse();
        window.onload=onLoad();
        function onLoad(){
        	var id = mini.get("id").getValue();
        	if(id!=null&&id!=""){
        		//mini.get("jm").disable();
        	}
        }
        
       
        function onCancel(e) {
             CloseWindow("cancel");
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