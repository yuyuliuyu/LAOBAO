<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
   
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>实习生信息编辑</title>
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
   	 	<input type="hidden"  name="flag" id="flag" value="${flag}" />
        <input class="mini-hidden" name="id" id="id" value="${information.id}" style="display:none;"/>
        <input class="mini-hidden" name="departId" id="departId" value="${departId}" style="display:none;"/>
        <input class="mini-hidden" name="branchId" id="branchId" value="${branchId}" style="display:none;"/>
        <input class="mini-hidden" name="imgpath" id="imgpath" value="${imgpath}" style="display:none;"/>
        
        <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
        		<tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>基本信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                	<td class="list_left_box1" valign="top">实习编号：</td>
	                <td class="list_right_box">
	                	<input name="practiceNum" id="practiceNum" class="mini-textbox" value="${information.practiceNum}"
	                		readonly="readonly" vtype="maxLength:20;required" width="100%"/>
	                </td>
                    <td class="list_left_box1" valign="top" rowspan="5">照片：</td>
                   	<td class="list_right_box" rowspan="5">
                   		<s:if test="information.imgpath!=null">
                    		<img id="licensepicture" src="${imgpath}" height="110" width="70"/>
                    	</s:if>
                    	
                    </td> 
                </tr>
              	<tr>
                   <td class="list_left_box1" valign="top">姓名：</td>
                   <td class="list_right_box">
                       <input name="name" id="name" class="mini-textbox" value="${information.name}"
                               vtype="maxLength:20;required" width="100%"/>
                   </td>
                </tr>
               	 <tr> 
                    <td class="list_left_box1" valign="top">曾用名：</td>
                    <td class="list_right_box">
                        <input name="oldName" id="oldName" class="mini-textbox" value="${information.oldName}"
                                vtype="maxLength:20" width="100%"/>
                    </td> 
                 </tr>
                
                <tr>
                   <td class="list_left_box1" valign="top">证件类型：</td>
                    <td class="list_right_box">
                        <input id="idType" name="idType"  class="mini-combobox" style="width:100%;" value="${information.idType}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881935ba8c785015ba8d9d7c0000b" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                </tr>
               	<tr> 
                    <td class="list_left_box1" valign="top">证件号码：</td>
                    <td class="list_right_box">
                        <input name="idNumber" id="idNumber" class="mini-textbox" value="${information.idNumber}"
                                 vtype="maxLength:20" width="100%"/>
                    </td>  
                 </tr>
                
               	 <tr> 
                    
                    <td class="list_left_box1" valign="top">出生日期：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="borthDate" id="borthDate" style="width: 100%" msg="日期格式不正确" allowInput="false" onvaluechanged="onChanged(e)"
									 readonly="readonly" value="${information.borthDate}"/>
                    </td> 
                    <td class="list_left_box1" valign="top">年龄：</td>
                    <td class="list_right_box">
                        <%-- <input id="age" name="age" class="mini-spinner" minValue="0" value="${information.age}" maxValue="200"style="width: 100%;"/> --%>
                    	<input name="age" id="age" class="mini-textbox" value="${age}"
                                readonly="readonly" vtype="maxLength:20" width="100%"/>
                    </td> 
                </tr>
                
                <tr>
                	<td class="list_left_box1" valign="top">性别：</td>
                    <td class="list_right_box">
                        <input id="sex" name="sex" class="mini-radiobuttonlist" url="../template/sex.txt"
                        readonly="readonly" textField="text" valueField="id" value="${information.sex}" />
                    </td> 
                    <td class="list_left_box1" valign="top">民族：</td>
                    <td class="list_right_box">
                        <input id="nation" name="nation" class="mini-combobox" style="width:100%;" value="${information.nation}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955cce6bd4015cce9be464000b" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                </tr>
               <tr>
                	<td class="list_left_box1" valign="top">出生地：</td>
                    
                    <td class="list_right_box">
                    	<input id="borthPlaceP" name="borthPlaceP" class="mini-combobox" style="width:85%;" value="${information.borthPlaceP}"  textField="pro" valueField="proid" onvaluechanged="onDeptChanged" 
                    		url="../personnel/personnel!getProvinceData.action" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                    <td class="list_right_box">
                    	<input id="borthPlaceC" name="borthPlaceC" class="mini-combobox" style="width:95%;" value="${information.borthPlaceC}"  textField="city" valueField="cityid" onvaluechanged="onDeptChanged1" 
                    		url="../personnel/personnel!getCityData.action?adressNum=${information.borthPlaceC}" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                    <td class="list_right_box">
                    	<input id="borthPlaceA" name="borthPlaceA" class="mini-combobox" style="width:85%;" value="${information.borthPlaceA}"  textField="area" valueField="areaid" 
                    		url="../personnel/personnel!getAreaData.action?adressNum=${information.borthPlaceA}"showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                </tr>
                <%-- <tr>
                    <td class="list_left_box1" valign="top">详细地址：</td>
                    <td class="list_right_box" colspan="3">
                        <input name="borthPlace" id="borthPlace" class="mini-textbox" value="${information.borthPlace}"
                                width="100%"/>
                    </td>  
                </tr> --%>
                <tr>
                	<td class="list_left_box1" valign="top">籍贯：</td>
                    
                    <td class="list_right_box">
                    	<input id="nativePlaceP" name="nativePlaceP" class="mini-combobox" style="width:85%;" value="${information.nativePlaceP}"  textField="pro" valueField="proid" onvaluechanged="onDeptChanged2" 
                    		url="../personnel/personnel!getProvinceData.action" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                    <td class="list_right_box">
                    	<input id="nativePlaceC" name="nativePlaceC" class="mini-combobox" style="width:95%;" value="${information.nativePlaceC}"  textField="city" valueField="cityid" onvaluechanged="onDeptChanged3" 
                    		url="../personnel/personnel!getCityData.action?adressNum=${information.nativePlaceC}"showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                    <td class="list_right_box">
                    	<input id="nativePlaceA" name="nativePlaceA" class="mini-combobox" style="width:85%;" value="${information.nativePlaceA}"  textField="area" valueField="areaid" 
                    		url="../personnel/personnel!getAreaData.action?adressNum=${information.nativePlaceA}" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                </tr>
                <%-- <tr>
                    <td class="list_left_box1" valign="top">详细地址：</td>
                    <td class="list_right_box" colspan="3">
                        <input name="nativePlace" id="nativePlace" class="mini-textbox" value="${information.nativePlace}"
                                width="100%"/>
                    </td>  
                </tr> --%>
                <tr>
                	<td class="list_left_box1" valign="top">参加工作时间：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd" 
							id="setWorkDate" name="setWorkDate" style="width: 100%" msg="日期格式不正确" allowInput="false"
									 value="${information.setWorkDate}"/>
                    </td> 
                    <td class="list_left_box1" valign="top">政治面貌：</td>
                    <td class="list_right_box">
                        <input id="politicsStatus" name="politicsStatus" class="mini-combobox" style="width:100%;" value="${information.politicsStatus}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881935b840948015b84cff8c10019" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                 </tr>
                
                 <tr>
                    <td class="list_left_box1" valign="top">婚姻状况：</td>
                    <td class="list_right_box">
                        <input id="isMarried" name="isMarried" class="mini-combobox" style="width:100%;" value="${information.isMarried}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881a05b650042015b6639e3d1003b" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                    <td class="list_left_box1" valign="top">特长爱好：</td>
                    <td class="list_right_box">
                        <input name="hobby" id="hobby" class="mini-textbox" value="${information.hobby}"
                                vtype="maxLength:50" width="100%"/>
                    </td> 
                 </tr>
                 <tr>
                    <td class="list_left_box1" valign="top">国家序列最高职称：</td>
                    
                    <td class="list_right_box">
                        <input id="highestTitle" name="highestTitle" class="mini-combobox" style="width:100%;" value="${information.highestTitle}"  textField="dicname" valueField="dicvalue"
		                   		 url="../system/dectionary!listjson.action?id=402881955d176487015d17682e54000a" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                    <td class="list_left_box1" valign="top">国家职业资格等级：</td>
                    
                    <td class="list_right_box">
                        <input id="proQuaLevel" name="proQuaLevel" class="mini-combobox" style="width:100%;" value="${information.proQuaLevel}"  textField="dicname" valueField="dicvalue"
		                   		 url="../system/dectionary!listjson.action?id=402881955d176487015d17685ddb000b" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>   
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">外语语种：</td>
                    
                    <td class="list_right_box">
                        <input id="foreignType" name="foreignType" class="mini-combobox" style="width:100%;" value="${information.foreignType}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881a05b650042015b663bdfe9003d" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                    <td class="list_left_box1" valign="top">外语级别：</td>
                    <td class="list_right_box">
                        <input id="english" name="english" class="mini-combobox" style="width:100%;" value="${information.english}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955c4223c5015c426675b30016" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>   
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">计算机等级：</td>
                    <td class="list_right_box">
                        <input id="computerLevel" name="computerLevel" class="mini-combobox" style="width:100%;" value="${information.computerLevel}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955ccefa0a015ccf1b5c070002" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>  
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">职工来源：</td>
                    <td class="list_right_box" width="30%">
                        <input id="empSource" name="empSource" class="mini-combobox" style="width:100%;" value="${information.empSource}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881935baceaa1015badf989e30015" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                    <td class="list_left_box1" valign="top">原身份：</td>
                    <td class="list_right_box">
                        <input name="formerIdentity" id="formerIdentity" class="mini-textbox" value="${information.formerIdentity}"
                                vtype="maxLength:20" width="100%"/>
                    </td>    
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">身高：</td>
                    <td class="list_right_box" >
						<input id="height" name="height" class="mini-spinner" minValue="0" value="${information.height}" maxValue="999999"style="width: 85%;"/>&nbsp;&nbsp;CM
					</td>
                    <td class="list_left_box1" valign="top">体重：</td>
                    <td class="list_right_box" >
						<input id="weight" name="weight" class="mini-spinner" minValue="0" value="${information.weight}" maxValue="999999"style="width: 85%;"/>&nbsp;&nbsp;KG
					</td>  
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">血型：</td>
                    <td class="list_right_box" width="30%">
                        <input id="blood" name="blood" class="mini-combobox"  style="width:100%;" value="${information.blood}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881935b8df599015b8e11bfff0015" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                    <td class="list_left_box1" valign="top">健康状况：</td>
                    <td class="list_right_box">
                        <input name="health" id="health" class="mini-textbox" value="${information.health}"
                                vtype="maxLength:10" width="100%"/>
                    </td>    
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">备注1：</td>
                    <td class="list_right_box">
                        <input name="remark1" id="remark1" class="mini-textbox" value="${information.remark1}"
                                vtype="maxLength:10" width="100%"/>
                    </td>
                    <td class="list_left_box1" valign="top">备注2：</td>
                    <td class="list_right_box">
                        <input name="remark2" id="remark2" class="mini-textbox" value="${information.remark2}"
                                vtype="maxLength:10" width="100%"/>
                    </td>    
                </tr>
                <tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>通讯信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">办公电话：</td>
                    <td class="list_right_box">
                        <input name="officePhone" id="officePhone" class="mini-textbox" value="${information.officePhone}"
                                vtype="maxLength:15" width="100%"/>
                    </td>
                    <td class="list_left_box1" valign="top">移动电话：</td>
                    <td class="list_right_box">
                        <input name="phone" id="phone" class="mini-textbox" value="${information.phone}"
                                vtype="maxLength:15" width="100%"/>
                    </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">办公邮箱：</td>
                    <td class="list_right_box">
                        <input name="officeEmail" id="officeEmail" class="mini-textbox" value="${information.officeEmail}"
                                vtype="maxLength:32" width="100%"/>
                    </td>
                    <td class="list_left_box1" valign="top">个人邮箱：</td>
                    <td class="list_right_box">
                        <input name="email" id="email" class="mini-textbox" value="${information.email}"
                                vtype="maxLength:32" width="100%"/>
                    </td> 
                </tr> 
                <tr>
                	<td class="list_left_box1" valign="top">户籍地址：</td>
                    
                    <td class="list_right_box">
                    	<input id="residencePlaceP" name="residencePlaceP" class="mini-combobox" style="width:85%;" value="${information.residencePlaceP}"  textField="pro" valueField="proid" onvaluechanged="onDeptChanged4" 
                    		url="../personnel/personnel!getProvinceData.action" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                    <td class="list_right_box">
                    	<input id="residencePlaceC" name="residencePlaceC" class="mini-combobox" style="width:95%;" value="${information.residencePlaceC}"  textField="city" valueField="cityid" onvaluechanged="onDeptChanged5" 
                    		url="../personnel/personnel!getCityData.action?adressNum=${information.residencePlaceC}" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                    <td class="list_right_box">
                    	<input id="residencePlaceA" name="residencePlaceA" class="mini-combobox" style="width:85%;" value="${information.residencePlaceA}"  textField="area" valueField="areaid" 
                    		url="../personnel/personnel!getAreaData.action?adressNum=${information.residencePlaceA}" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">详细地址：</td>
                    <td class="list_right_box" colspan="3">
                        <input name="residencePlace" id="residencePlace" class="mini-textbox" value="${information.residencePlace}"
                                width="100%"/>
                    </td>  
                </tr> 
                
                 <tr>
                	<td class="list_left_box1" valign="top">现住址：</td>
                    
                    <td class="list_right_box">
                    	<input id="addressP" name="addressP" class="mini-combobox" style="width:85%;" value="${information.addressP}"  textField="pro" valueField="proid" onvaluechanged="onDeptChanged6" 
                    		url="../personnel/personnel!getProvinceData.action" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                    <td class="list_right_box">
                    	<input id="addressC" name="addressC" class="mini-combobox" style="width:95%;" value="${information.addressC}"  textField="city" valueField="cityid" onvaluechanged="onDeptChanged7" 
                    		url="../personnel/personnel!getCityData.action?adressNum=${information.addressC}" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                    <td class="list_right_box">
                    	<input id="addressA" name="addressA" class="mini-combobox" style="width:85%;" value="${information.addressA}"  textField="area" valueField="areaid" 
                    		url="../personnel/personnel!getAreaData.action?adressNum=${information.addressA}" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">详细地址：</td>
                    <td class="list_right_box" colspan="3">
                        <input name="address" id="address" class="mini-textbox" value="${information.address}"
                                width="100%"/>
                    </td>  
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">通讯地址：</td>
                    <td class="list_right_box" colspan="3">
                        <input id="cmJyfw" name="cmJyfw" class="mini-textarea" value="${branch.cmJyfw}"  maxlength="50"style="width: 100%;height:50px"/>
                    </td> 
                    
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">紧急联系人：</td>
                    <td class="list_right_box">
                        <input name="urgentPeople" id="urgentPeople" class="mini-textbox" value="${information.urgentPeople}"
                                vtype="maxLength:32" width="100%"/>
                    </td>
                    <td class="list_left_box1" valign="top">联系电话：</td>
                    <td class="list_right_box">
                        <input name="upPhone" id="upPhone" class="mini-textbox" value="${information.upPhone}"
                                vtype="maxLength:50" width="100%"/>
                    </td> 
                </tr> 
                <tr>
                	<td class="list_left_box1" valign="top">联系地址：</td>
                    <td class="list_right_box">
                    	<input id="upAddressP" name="upAddressP" class="mini-combobox" style="width:85%;" value="${information.upAddressP}"  textField="pro" valueField="proid" onvaluechanged="onDeptChanged8" 
                    		url="../personnel/personnel!getProvinceData.action" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                    <td class="list_right_box">
                    	<input id="upAddressC" name="upAddressC" class="mini-combobox" style="width:95%;" value="${information.upAddressC}"  textField="city" valueField="cityid" onvaluechanged="onDeptChanged9" 
                    		url="../personnel/personnel!getCityData.action?adressNum=${information.upAddressC}" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                    <td class="list_right_box">
                    	<input id="upAddressA" name="upAddressA" class="mini-combobox" style="width:85%;" value="${information.upAddressA}"  textField="area" valueField="areaid" 
                    		url="../personnel/personnel!getAreaData.action?adressNum=${information.upAddressA}" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." /> 
                    </td>
                </tr>
                
        		<tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>岗位信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">入司时间1：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="inCompanyTime" id="inCompanyTime" style="width: 100%" msg="日期格式不正确" allowInput="false"
									 value="${information.inCompanyTime}"/>
                    </td> 
                    <td class="list_left_box1" valign="top">入司时间2：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="inCompanyTime2" id="inCompanyTime2" style="width: 100%" msg="日期格式不正确" allowInput="false"
									 value="${information.inCompanyTime2}"/>
                    </td> 
               </tr>
               <tr>
                    <td class="list_left_box1" valign="top">入港时间1：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="inPortTime" id="inPortTime" style="width: 100%" msg="日期格式不正确" allowInput="false"
									 value="${information.inPortTime}"/>
                    </td>
                    <td class="list_left_box1" valign="top">入港时间2：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="inPortTime2" id="inPortTime2" style="width: 100%" msg="日期格式不正确" allowInput="false"
									 value="${information.inPortTime2}"/>
                    </td>
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">在岗状态：</td>
                    <td class="list_right_box" width="30%">
                        <input id="onPost" name="onPost" class="mini-combobox" style="width:100%;" value="${information.onPost}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955bc84595015bc87aa73e0010" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                	<td class="list_left_box1" valign="top">劳务公司：</td>
                    <td class="list_right_box" width="30%">
                        <input id="laborCompany" name="laborCompany" class="mini-combobox"  style="width:100%;" value="${information.laborCompany}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955d1ba95e015d1bbcbed5000a" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top">所属部门：</td>
                    <td class="list_right_box">
                        <input id="departName" name="departName" class="mini-textarea" value="${information.departName}"  maxlength="32"style="width: 100%"/>
                    </td>
                    <td class="list_left_box1" valign="top">所属班组：</td>
                    <td class="list_right_box">
                        <input id="classNo" name="classNo" class="mini-textarea" value="${information.classNo}"   maxlength="32"style="width: 100%"/>
                    </td>
               </tr>
               <tr>
                	<td class="list_left_box1" valign="top">成本单位：</td>
                    <td class="list_right_box">
                        <input id="costOffice" name="costOffice" class="mini-textarea" value="${information.costOffice}"  maxlength="32"style="width: 100%"/>
                    </td> 
                    <td class="list_left_box1" valign="top">成本部门：</td>
                    <td class="list_right_box">
                        <input id="costDep" name="costDep" class="mini-textarea" value="${information.costDep}"  maxlength="32"style="width: 100%"/>
                    </td> 
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top">所属模块：</td>
                    <td class="list_right_box">
                        <input id="module" name="module" class="mini-textarea" value="${information.module}"  maxlength="32"style="width: 100%"/>
                    </td> 
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top">具体岗位：</td>
                    <td class="list_right_box" width="30%">
                        <input id="specificPostId" name="specificPostId" class="mini-buttonedit" onbuttonclick="onButtonEdit1" width="100%"
                                value="${information.specificPostId}" text="${information.specificPost}" allowInput="false"/> 
                    </td>
                    <td class="list_left_box1" valign="top">标准岗位：</td>
                    <td class="list_right_box">
                        <input name="postId" id="postId" class="mini-buttonedit" value="${information.postId}" text="${information.post}" readonly="true" width="100%"/>
                    </td>
                    
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top">是否为技术工人：</td>
                    <td class="list_right_box">
                        <input id="isSkilled" name="isSkilled" class="mini-combobox"
                        	emptyText="请选择..." 
                          	url="../template/using.txt" textField="text" 
                          	 width="100%" value="${information.isSkilled}"/>
                    </td>
                    <td class="list_left_box1" valign="top">是否为专业技术人员：</td>
                    <td class="list_right_box">
                        <input id="isSpecialty" name="isSpecialty" class="mini-combobox"
                        	emptyText="请选择..." 
                          	url="../template/using.txt" textField="text" 
                          	 width="100%" value="${information.isSpecialty}"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top">岗位类别：</td>
                    <td class="list_right_box">
                        <input id="postType" name="postType" class="mini-combobox" style="width:100%;" value="${deptPosition.postType}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=8aa385705d0b61c0015d0cdf761200ea" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
               	    </td> 
                    <td class="list_left_box1" valign="top">岗位序列：</td>
                    <td class="list_right_box">
                        <input id="postOrder" name="postOrder" class="mini-combobox" style="width:100%;" value="${deptPosition.postOrder}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=8aa385705d0b61c0015d0cdfa20200eb" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
               		 </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top">机关编制：</td>
                    <td class="list_right_box">
                        <input id="jgbz" name="jgbz" class="mini-combobox"
                        	emptyText="请选择..." 
                          	url="../template/using.txt" textField="text" 
                          	width="100%" value="${information.jgbz}"/>
                    </td>
                    <td class="list_left_box1" valign="top">开始时间：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="beginTime" id="beginTime" style="width: 100%" msg="日期格式不正确" allowInput="false"
									 value="${information.beginTime}"/>
                    </td>
                </tr> 
                 <tr>
                 	<td class="list_left_box1" valign="top">职务级别：</td>
                    <td class="list_right_box">
                        <input id="jobLevel" name="jobLevel" class="mini-combobox"  style="width:100%;" value="${information.jobLevel}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881935ba8c785015ba8e043c80011" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                    <td class="list_left_box1" valign="top">任职年限：</td>
                    <td class="list_right_box">
                        <input name="career" id="career" class="mini-textbox" value="${information.career}"
                                vtype="maxLength:10" width="100%"/>
                    </td> 
                </tr>
           </table>     
           <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
        		<tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" width="20%"  style="font-size:18px;"><b>兼职信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr>
                
            </table> 
            <div id="showTime1" style = "width:90%;margin-left:5%">
	            <div id="jzgrid" class="mini-datagrid" style="width:100%;" showPager="false"
         	 		 url="../personnel/pt_job!getListData.action?personId=${personId}" idField="id" multiSelect="true" 
        			 allowAlternating="true" pageSize="10" >
			                <div property="columns">
		                        <div type="indexcolumn" headerAlign="center">序列</div>
		                        <div field="firmId" id ="firmId" name = "firmId"width="100" headerAlign="center" align="left">兼职公司</div>
		                        <div field="depart" id ="depart" name = "depart"width="100" headerAlign="center" align="left">所属部门</div>
		                        <div field="standardPost" id ="standardPost" name = "standardPost"width="100" headerAlign="center" align="left">标准岗位</div>
		                        <div field="spePost" id ="spePost" name = "spePost"width="100" headerAlign="center" align="left">具体岗位</div>
		                        <div field="beginTime" id ="beginTime" name = "beginTime"width="100" headerAlign="center" align="left">开始时间</div>
		                        <div field="endTime" id ="endTime" name = "endTime"width="100" headerAlign="center" align="left">结束时间</div>
		                    	<div type="comboboxcolumn" field="state" width="100" headerAlign="center" align="center">状态
                 					<input property="editor" class="mini-combobox" data="[{id:0, text:'兼职结束'},{id:1, text:'兼职中'}]"/>
                 				</div>
		                    </div>
	       		 </div>
       		 </div>
             <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">    
                <tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>教育经历</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">全日制学历最高学历：</td>
                    <td class="list_right_box" width="30%">
                        <input id="educationLever" name="educationLever" class="mini-combobox" style="width:100%;" value="${education.educationLever}"  textField="dicname" valueField="dicvalue"
		                   		readonly="true" url="../system/dectionary!listjson.action?id=402881a05b650042015b6617c989002f" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                    <td class="list_left_box1" valign="top">全日制学历最高学位：</td>
                    <td class="list_right_box" width="30%">
                        <input id="degree" name="degree" class="mini-combobox" style="width:90%;" value="${education.degree}"  textField="dicname" valueField="dicvalue"
		                   		readonly="true"  url="../system/dectionary!listjson.action?id=402881a05b650042015b663e8700003f" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">入学时间：</td>
                    <td class="list_right_box">
                    	<input class="mini-textbox" value="${education.entranceDate}"width="100%" readonly="true"/>
                    </td>
                    <td class="list_left_box1" valign="top">毕业时间：</td>
                    <td class="list_right_box">
                    	<input class="mini-textbox" value="${education.graduateDate}"width="100%" readonly="true"/>
                    </td> 
                </tr>  
                <tr>
                    <td class="list_left_box1" valign="top">学校名称：</td>
                    <td class="list_right_box">
                        <input class="mini-textbox" value="${education.school}"width="100%" readonly="true"/>
                    </td> 
                    <td class="list_left_box1" valign="top">所学专业：</td>
                    <td class="list_right_box">
                        <input class="mini-textbox" value="${education.profession}"width="100%" readonly="true"/>
                    </td>
                </tr> 
                
                <tr>
                    <td class="list_left_box1" valign="top">在职教育最高学历：</td>
                    <td class="list_right_box">
                        <input class="mini-textbox" value="${education2.educationLever}"width="100%" readonly="true"/>
                    </td>
                    <td class="list_left_box1" valign="top">在职教育最高学位：</td>
                    <td class="list_right_box">
                        <input class="mini-textbox" value="${education2.degree}"width="100%" readonly="true"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">入学时间：</td>
                    <td class="list_right_box">
                        <input class="mini-textbox" value="${education2.entranceDate}"width="100%" readonly="true"/>
                    </td>
                    <td class="list_left_box1" valign="top">毕业时间：</td>
                    <td class="list_right_box">
                        <input class="mini-textbox" value="${education2.graduateDate}"width="100%" readonly="true"/>
                    </td> 
                </tr>  
                <tr>
                    <td class="list_left_box1" valign="top">学校名称：</td>
                    <td class="list_right_box" >
                        <input class="mini-textbox" value="${education2.school}"width="100%" readonly="true"/>
                    </td> 
                    <td class="list_left_box1" valign="top">所学专业：</td>
                    <td class="list_right_box">
                        <input class="mini-textbox" value="${education2.profession}"width="100%" readonly="true"/>
                    </td>
                </tr> 
            </table>    
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
        		<tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" width="20%"  style="font-size:18px;"><b>职业履历</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr>
                
            </table> 
            <div id="showTime1" style = "width:90%;margin-left:5%">
	            <div id="zyllgrid" class="mini-datagrid" style="width:100%;"  showPager="false"
         	 		 url="../empdata/career!getListData.action?personId=${personId}" idField="id" multiSelect="true" 
        			 allowAlternating="true" pageSize="10" >
			         <div property="columns">
		                    <div type="indexcolumn" headerAlign="center">序列</div>
		                    <div field="begindate" id ="begindate" name = "begindate"width="100" headerAlign="center" align="left">开始时间</div>
	                        <div field="enddate" id ="enddate" name = "enddate" width="100" headerAlign="center" align="left" >结束时间</div>
	                        <div field="company" id ="company" name = "company"width="100" headerAlign="center" align="left">工作单位</div>
	                        <div field="department" id ="department" name = "department"width="100" headerAlign="center" align="left">任职部门</div>
	                        <div field="prove" id ="prove" name = "prove"width="100" headerAlign="center" align="left">任职岗位</div>
	                        <div field="prove" id ="prove" name = "prove"width="100" headerAlign="center" align="left">证明人</div>
	                        <div field="proDuty" id ="proDuty" name = "proDuty" width="100" headerAlign="center" align="left">证明人职务</div>
	                        <div field="proTel" id ="proTel" name = "proTel" width="100" headerAlign="center" align="left">联系方式</div>
	                        <div field="remark" id ="remark" name = "remark" width="100" headerAlign="center" align="left">备注</div>
		             </div>
	       		 </div>
       		 </div>
       		 <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
        		<tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" width="20%"  style="font-size:18px;"><b>社会关系</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr>
                
            </table> 
       		 <div id="showTime" style = "width:90%;margin-left:5%">
	            <div id="shgxgrid" class="mini-datagrid" style="width:100%;"  showPager="false"
         	 		 url="../empdata/social_relation!getListData.action?personId=${personId}" idField="id" multiSelect="true" 
        			 allowAlternating="true" pageSize="10" >
        			 <div property="columns">
		                 <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">亲属姓名</div>
			         	 <div type="comboboxcolumn" field="sex" width="100" headerAlign="center" align="center">性别
	                 	 <input property="editor" class="mini-combobox" data="[{id:0, text:'女'},{id:1, text:'男'}]"/>
	                     </div>
	                	 <div type="comboboxcolumn" field="politicsStatus" width="100" headerAlign="center" align="center">政治面貌
	                  	 <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935b840948015b84cff8c10019" textField="dicname" valueField="dicvalue"/>
	               		 </div>
			        	 <div field="borthDate" id ="borthDate" name = "borthDate"width="100" headerAlign="center" align="left">出生日期</div>
			       	     <div field="relation" id ="relation" name = "relation"width="100" headerAlign="center" align="left">与本人关系</div>
			       	 	 <div field="call" id ="call" name = "call"width="100" headerAlign="center" align="left">称谓</div>
			        	 <div field="nation" id ="nation" name = "nation"width="100" headerAlign="center" align="left">民族</div>
			        	 <div field="workUnit" id ="workUnit" name = "workUnit"width="100" headerAlign="center" align="left">工作单位</div>
			        	 <div field="duty" id ="duty" name = "duty"width="100" headerAlign="center" align="left">岗位职务</div>
		             </div>
	       		 </div>
       		</div>
                
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">       
                <tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b> 薪资信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">社会保险账号：</td>
                    <td class="list_right_box">
                        <input name="insuranceNum" id="insuranceNum" class="mini-textbox" value="${information.insuranceNum}"
                                readonly="readonly"vtype="maxLength:15" width="100%"/>
                    </td>
                    <td class="list_left_box1" valign="top">公积金账号：</td>
                    <td class="list_right_box">
                        <input name="cpfNum" id="cpfNum" class="mini-textbox" value="${information.cpfNum}"
                                readonly="readonly"vtype="maxLength:15" width="100%"/>
                    </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">开户行：</td>
                    <td class="list_right_box">
                        <input name="openingBank" id="openingBank" class="mini-textbox" value="${information.openingBank}"
                                readonly="readonly" vtype="maxLength:20" width="100%"/>
                    </td> 
                    <td class="list_left_box1" valign="top">银行卡号：</td>
                    <td class="list_right_box">
                        <input name="account" id="account" class="mini-textbox" value="${salary.account}" 
                                readonly="readonly" vtype="maxLength:20" width="100%"/>
                    </td> 
                     
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">免税比例：</td>
                    <td class="list_right_box">
                        <input name="dutyFreeRatio" id="dutyFreeRatio" class="mini-textbox" value="${information.dutyFreeRatio}"
                                readonly="readonly"vtype="maxLength:10" width="100%"/>
                    </td>
                    <td class="list_left_box1" valign="top">工资类型：</td>
                    <td class="list_right_box">
                        <input name="gzxs" class="mini-combobox" readonly="readonly"
                        url="../template/wageForm.txt" textField="text" 
                        valueField="id" style="width: 100%" value="${record.gzxs}"/>
                                
                    </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">年金职级类别：</td>
                    <td class="list_right_box">
                        <input name="annuityType" id="annuityType" class="mini-textbox" value="${information.annuityType}"
                                readonly="readonly"vtype="maxLength:50" width="100%"/>
                    </td> 
                    <td class="list_left_box1" valign="top">年金缴费状态：</td>
                    <td class="list_right_box">
                        <input name="annuityState" id="annuityState" class="mini-textbox" value="${information.annuityState}"
                                readonly="readonly"vtype="maxLength:50" width="100%"/>
                    </td> 
                     
                </tr>
                
                <tr>
                    <td class="list_left_box1" valign="top" >年金缴费单位：</td>
                    <td class="list_right_box" colspan="3">
                        <input id="annuityCompany" name="annuityCompany" class="mini-textarea" readonly="readonly" value="${information.annuityCompany}"  maxlength="50"style="width: 100%"/>
                    </td> 
                </tr>
            </table>
            
       		 
            </br></br></br>
            <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
	            <a class="mini-button" id="upload" onclick="upload" style="width:100px;margin-right:20px;" iconCls="icon-upload">上传照片</a>
	            <a class="mini-button" id="onOk" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
	            <a class="mini-button" onclick="onReload" id="onReload" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
	            <a class="mini-button" id="onCancel" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        	</div>
        
    </form>
    </div>
    <script type="text/javascript">
        mini.parse();
        if("1"==${flag}){
        	mini.get("upload").setVisible(false);
            mini.get("onOk").setVisible(false);
            mini.get("onReload").setVisible(false);
            mini.get("onCancel").setVisible(false);
        }
        var grid = mini.get("jzgrid");
        var grid1 = mini.get("zyllgrid");
        var grid2 = mini.get("shgxgrid");
        grid.load();
        grid1.load();
        grid2.load();
        //grid.hideColumn("id");
        
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
        
        function onButtonEdit1(e) {
 	        var btnEdit = this;
 	        var depart=mini.get("departId").getValue();
 	       	var postId=mini.get("postId");
 	       	var postName=mini.get("post");
 	       	var specificPost=mini.get("specificPost");
 	       
 	        mini.open({
 	            url: "../post/position!show.action?id="+depart,
 	            showMaxButton: false,
 	            title: "选择人员",
 	            width: 400,
 	            height: 300,
 	            ondestroy: function (action) {
 	                if (action == "ok") {
 	                    var iframe = this.getIFrameEl();
 	                    var data = iframe.contentWindow.GetData();
 	                    data = mini.clone(data);
 	                    if (data) {
 	                        btnEdit.setValue(data.id);
 	                        btnEdit.setText(data.positionName);
 	                       	postId.setValue(data.positionId);//标准岗位id
 	                       	postId.setText(data.positionName2);//标准岗位名称
 	                       	
 	                       	postName.setValue(data.positionName2);//标准岗位名称
 	                        specificPost.setValue(data.positionName);
 	                    }
 	                }
 	            }
 	        });
 	    } 
        function onChanged(e){
 			var value = e.value;
 			var borthYear=value.getFullYear();
 			var age = mini.get("age");
 			var date=new Date;
 			var newYear=date.getFullYear();
 			age.setValue(newYear-borthYear);
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
                url: "../personnel/practice!saveOrUpdate.action?branchId="+branchId,
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
      	//导入
        function upload() {
        	 mini.open({
	                url : "../personnel/personnel!pictureupload.action",
	                title : "添加信息",
	                width : 300,
	                height : 100,
	                /* allowResize : false, */
	                onload : function() {
	                },
	                ondestroy : function(action) {
	                	if (action == "ok") {
		                    var iframe = this.getIFrameEl();
	                        var data = iframe.contentWindow.GetData();
	                        data = mini.clone(data);
	                        mini.get("imgpath").setValue(data);
	                        grid.reload();
	                	}
	                }
	            });
        }
      	
      	
      //出生地
        function onDeptChanged(){
        	var borthPlaceP = mini.get("borthPlaceP");
        	var borthPlaceC = mini.get("borthPlaceC");
        	var id = borthPlaceP.getValue();
        	borthPlaceC.setValue("");
            var url = "../personnel/personnel!getCityData.action?fatherid=" + id;
            borthPlaceC.setUrl(url);
            onDeptChanged1();
        }
        function onDeptChanged1(){
        	var borthPlaceC = mini.get("borthPlaceC");
        	var borthPlaceA = mini.get("borthPlaceA");
        	var id = borthPlaceC.getValue();
        	borthPlaceA.setValue("");
            var url = "../personnel/personnel!getAreaData.action?fatherid=" + id;
            borthPlaceA.setUrl(url);
        }
        //籍贯
        function onDeptChanged2(){
        	var nativePlaceP = mini.get("nativePlaceP");
        	var nativePlaceC = mini.get("nativePlaceC");
        	var id = nativePlaceP.getValue();
        	nativePlaceC.setValue("");
            var url = "../personnel/personnel!getCityData.action?fatherid=" + id;
            nativePlaceC.setUrl(url);
            onDeptChanged3();
        }
        function onDeptChanged3(){
        	var nativePlaceC = mini.get("nativePlaceC");
        	var nativePlaceA = mini.get("nativePlaceA");
        	var id = nativePlaceC.getValue();
        	nativePlaceA.setValue("");
            var url = "../personnel/personnel!getAreaData.action?fatherid=" + id;
            nativePlaceA.setUrl(url);
        }
      //籍贯
        function onDeptChanged4(){
        	var residencePlaceP = mini.get("residencePlaceP");
        	var residencePlaceC = mini.get("residencePlaceC");
        	var id = residencePlaceP.getValue();
        	residencePlaceC.setValue("");
            var url = "../personnel/personnel!getCityData.action?fatherid=" + id;
            residencePlaceC.setUrl(url);
            onDeptChanged5();
        }
        function onDeptChanged5(){
        	var residencePlaceC = mini.get("residencePlaceC");
        	var residencePlaceA = mini.get("residencePlaceA");
        	var id = residencePlaceC.getValue();
        	residencePlaceA.setValue("");
            var url = "../personnel/personnel!getAreaData.action?fatherid=" + id;
            residencePlaceA.setUrl(url);
        }
        function onDeptChanged6(){
        	var addressP = mini.get("addressP");
        	var addressC = mini.get("addressC");
        	var id = addressP.getValue();
        	addressC.setValue("");
            var url = "../personnel/personnel!getCityData.action?fatherid=" + id;
            addressC.setUrl(url);
            onDeptChanged7();
        }
        function onDeptChanged7(){
        	var addressC = mini.get("addressC");
        	var addressA = mini.get("addressA");
        	var id = addressC.getValue();
        	addressA.setValue("");
            var url = "../personnel/personnel!getAreaData.action?fatherid=" + id;
            addressA.setUrl(url);
        }
        function onDeptChanged8(){
        	var upAddressP = mini.get("upAddressP");
        	var upAddressC = mini.get("upAddressC");
        	var id = upAddressP.getValue();
        	upAddressC.setValue("");
            var url = "../personnel/personnel!getCityData.action?fatherid=" + id;
            upAddressC.setUrl(url);
            onDeptChanged9();
        }
        function onDeptChanged9(){
        	var upAddressC = mini.get("upAddressC");
        	var upAddressA = mini.get("upAddressA");
        	var id = upAddressC.getValue();
        	upAddressA.setValue("");
            var url = "../personnel/personnel!getAreaData.action?fatherid=" + id;
            upAddressA.setUrl(url);
        }
    </script>
</body>
</html>