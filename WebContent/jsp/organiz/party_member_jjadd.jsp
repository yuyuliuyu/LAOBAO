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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /> 
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>入党积极分子增加页面</title>
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
        <input class="mini-hidden" name="cid" id="cid" value="${cid}" style="display:none;"/>
        <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
             	<tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" ><b>党组织信息：</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr> 
                   <td class="list_left_box1" valign="top">名称：</td>
                    <td class="list_right_box">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}" readonly="readonly" width="100%" />
                    </td> 
                    <td class="list_left_box1" valign="top">创建日期：</td>
                    <td class="list_right_box">
                       <input class="mini-datepicker" format="yyyy-MM-dd" readonly="readonly" 
									name="date" style="width: 96%" msg="日期格式不正确" allowInput="false"
									 value="${laweFormArchfiling.date}"/>
                    </td>   
                </tr>  
                	<tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" ><b>入党积极分子信息：</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr>
                
                <tr>
                    <td class="list_left_box1" valign="top">工号：</td>
                    <td class="list_right_box">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"  width="100%" />
                    </td>   
                   <td class="list_left_box1" valign="top">姓名：</td>
                    <td class="list_right_box">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"  width="100%" />
                    </td> 
                </tr>  
                <tr>
                    <td class="list_left_box1" valign="top">所在公司：</td>
                    <td class="list_right_box">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"  width="100%" />
                    </td>   
                   <td class="list_left_box1" valign="top">职位：</td>
                    <td class="list_right_box">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"  width="100%" />
                    </td> 
                </tr>  
                <tr>
                    <td class="list_left_box1" valign="top">联系方式：</td>
                    <td class="list_right_box">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"  width="100%" />
                    </td>   
                   <td class="list_left_box1" valign="top">家庭住址：</td>
                    <td class="list_right_box">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"  width="100%" />
                    </td> 
                </tr>  
                <tr>
                    <td class="list_left_box1" valign="top">申请入党日期：</td>
                    <td class="list_right_box">
                       <input class="mini-datepicker" format="yyyy-MM-dd" 
									name="date" style="width: 96%" msg="日期格式不正确" allowInput="false"
									 value="${laweFormArchfiling.date}"/>
                    </td>   
                   <td class="list_left_box1" valign="top">培养人：</td>
                    <td class="list_right_box">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"  width="100%" />
                    </td> 
                </tr>  
                <tr>
                    <td class="list_left_box1" valign="top">培养人联系方式：</td>
                    <td class="list_right_box">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"  width="100%" />
                    </td>   
                   <td class="list_left_box1" valign="top">列为积极分子日期：</td>
                    <td class="list_right_box">
                       <input class="mini-datepicker" format="yyyy-MM-dd" 
									name="date" style="width: 96%" msg="日期格式不正确" allowInput="false"
									 value="${laweFormArchfiling.date}"/>
                    </td> 
                </tr>  
                <tr>
                   <td class="list_left_box1" valign="top">一线情况：</td>
                    <td class="list_right_box" colspan="3">
  					<textarea name="xh" id="xh" class="mini-textarea" value="${cd.xh}" emptyText="请输入备注" style="width: 100%;height: 100px"></textarea>
                    </td>  
                </tr>
            </table> 
            <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
	            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
	            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
	            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        	</div>
        
    </form>
    </div>
  </body>
</html>
