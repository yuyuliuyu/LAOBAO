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
<title>实习生审核查看页面</title>
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
                               required="true" vtype="maxLength:50" width="90%" enabled="false"/>
                    </td> 
                    <td class="list_left_box1" valign="top">总评分：</td>
                    <td class="list_right_box">
                        <input name="score" id="score" required="true" class="mini-textbox" value="${check.score}"  width="90%" enabled="false"/>
                    </td> 
                    
                </tr>
                
                <tr>
                    <td class="list_left_box1" valign="top">开始日期：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									id="begindate" name="begindate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${check.begindate}" enabled="false"/>
                    </td>  
                    <td class="list_left_box1" valign="top">结束日期：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									id="enddate" name="enddate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${check.enddate}" enabled="false"/>
                    </td>   
                    
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">考核内容：</td>
                     
                    <td class="list_right_box" colspan="3">
                         <input id="checkContent" name="checkContent" class="mini-textarea" enabled="false" value="${check.checkContent}"  maxlength="100"style="width: 96%;height:50px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">目标达成状况：</td>
                    <td class="list_right_box" colspan="3">
                         <input id="goalReach" name="goalReach" enabled="false" class="mini-textarea" value="${check.goalReach}"  maxlength="100"style="width: 96%;height:50px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >自我评价：</td>
                    <td class="list_right_box" colspan="3">
                         <input id="selfEvaluate" name="selfEvaluate" enabled="false" class="mini-textarea" value="${check.selfEvaluate}"  maxlength="100"style="width: 96%;height:50px"/>
                    </td> 
                </tr>
                 <tr>
                	<td class="list_left_box1">流程选择：</td>
                	<td class="list_right_box" colspan="3">
                        <input field="processId" name="processId" id="processId" required="true" 
							class="mini-combobox" textField="text" valueField="id" emptyText=""  
							width="96%" value="${check.processId}" enabled="false" url="../system/work_flow!getworkflow.action?type=6"/>
                    </td>
                </tr>
            </table>
            </br></br></br>
            
        
    </form>
    </div>
    <script type="text/javascript">
        mini.parse();
        
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