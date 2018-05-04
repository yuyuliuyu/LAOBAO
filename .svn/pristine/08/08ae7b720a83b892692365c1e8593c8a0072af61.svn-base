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
<title>教育经历增加</title>
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

        <input class="mini-hidden" name="id" id="id" value="${id}" style="display:none;"/>
        <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">记录编号：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:20;required" width="200px"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">职工姓名：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:50" width="200px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">部门：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:20;required" width="200px"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">公司：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:50" width="200px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">合同类型：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:20;required" width="200px"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">签订日期：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:50" width="200px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">合同开始：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:20;required" width="200px"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">合同结束：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:50" width="200px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">合同状态：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:20;required" width="200px"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">合同年限：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:50" width="200px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">终止日期：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:20;required" width="200px"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">合同编号：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:50" width="200px"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">终止原因：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:20;required" width="100%"/>
                    </td> 
                    
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">备注：</td>
                    <td class="list_right_box" width="30%">
                        <input name="xh" id="xh" class="mini-textbox" value="${cd.xh}"
                                vtype="maxLength:20;required" width="100%"/>
                    </td> 
                    
                </tr>
                <tr>
                    
                    <tr>
                    <td class="list_left_box1" valign="top">
        上传合同照片：
                    </td>
                    <td class="list_right_box" colspan="3">
                        <input class="mini-htmlfile" id="uploadFile" name="uploadFile" limitType="*.xls;*.xlsx;"  required="true"/>
                    	<a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;" iconCls="icon-upload">上传</a>
                    </td>
                </tr>
                </tr>
            </table>
        
        	<table cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                    <tr>
                        <td class="list_left_box2">
                                                                   集体合同变动历史
                        </td>
                    </tr>
                </tbody>
            </table>
        
       <div class="mini-fit">
 		<div id="grid1" class="mini-datagrid" style="width:100%;height:94%;"
            showTreeIcon="true"
           treeColumn="name" idField="did" parentField="pid"
           resultAsTree="false" allowResize="false" expandOnLoad="true">
              <div property="columns">
                     <div type="checkcolumn">选择</div>
                     <div type="indexcolumn" headerAlign="center">序列</div>
                     <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">变更名称</div>
                     <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">变更类型</div>
                     <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">合同类型</div>
                     <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">变更时间</div>
                 </div>
   		 	</div>
        </div>

        

    
    <script type="text/javascript">
    mini.parse();
    </script>
</body>
</html>