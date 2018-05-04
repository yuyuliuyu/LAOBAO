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
	
	<div  style="width:60%;float:left" >
    <form id="form1" method="post">
        
        <input class="mini-hidden" name="id" id="id" value="${branch.id}" style="display:none;"/>
        
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
            	<tr>
                    <td class="list_left_box1" valign="top" width="30%">上级单位名称（一级）：</td>
                    <td class="list_right_box" width="70%">
                    	青岛港（集团）有限公司
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">简称：</td>
                    <td class="list_right_box" width="70%">
                    	青港集团
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">上级单位名称（二级）：</td>
                    <td class="list_right_box" width="70%">
                    	青岛港国际股份有限公司
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">简称：</td>
                    <td class="list_right_box" width="70%">
                    	青港国际
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">分中心名称：</td>
                    <td class="list_right_box" width="70%">
                    	${branch.fzx}
                        
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">简称：</td>
                    <td class="list_right_box" width="70%">
                    	${branch.fzx}
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">下属单位：</td>
                    <td class="list_right_box" width="70%">
                           ${branch.fzx}
                    </td>
                </tr>
                
                
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">公司部门编制数：</td>
                    <td class="list_right_box" width="70%">
                    
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">组织结构图：</td>
                    <td class="list_right_box" width="70%">
                    	公司，部门--数据库显示
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">公司简介：</td>
                    <td class="list_right_box" width="70%">
                    	
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">工商注册信息：</td>
                    <td class="list_right_box" width="70%">
                    	
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">公司资质：</td>
                    <td class="list_right_box" width="70%">
                    	道路运输许可证、危险品证（点击显示图片）
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">网址：</td>
                    <td class="list_right_box" width="70%">
                    	www.baidu.com
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">荣誉：</td>
                    <td class="list_right_box" width="70%">
                    	2016年青岛港先进单位
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">历史沿革：</td>
                    <td class="list_right_box" width="70%">
                    	（时间轴，历史变化记录）
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">备注：</td>
                    <td class="list_right_box" width="70%">
                    	
                    </td>
                </tr>
            </table>
    </form>
    
    
    
    </div>
    <div  style="width:40%;float:right" >
    	<img src="../template/system/images/timg.jpg" onload="if (this.width>420 || this.height>500) if (this.width/this.height>430/500) this.width=430; else this.height=500;"/>
    </div>
    <script type="text/javascript">
        mini.parse();
          
    </script>
</body>
</html>