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
<title>增加考勤内容信息</title>
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
</head>
<body>
    <form id="form1" method="post" class="mini-fit">
        <input class="mini-hidden" name="id" id="id" value="${ckContent.id}" style="display:none;"/>
        <div>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                <tr>
                    <td class="list_left_box1" valign="top" width="13%">内容：</td>
                    <td class="list_right_box" width="12%">
                        <input name="content" id="content" class="mini-textbox" value="${ckContent.content}"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" 
                            width="100px" />
                    </td>
                    <td class="list_left_box1" valign="top" width="13%">类别：</td>
                    <td class="list_right_box" width="12%">
                        <input name="contentType" id="contentType" class="mini-textbox" value="${ckContent.contentType}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="13%">出工：</td>
                    <td class="list_right_box" width="12%">
                        <input name="outWork" id="outWork" class="mini-textbox" value="${ckContent.outWork}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="13%">岗位：</td>
                    <td class="list_right_box" width="12%">
                        <input name="post" id="post" class="mini-textbox" value="${ckContent.post}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="10%">出勤：</td>
                    <td class="list_right_box" width="15%">
                        <input name="outDuty" id="outDuty" class="mini-textbox" value="${ckContent.outDuty}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">白：</td>
                    <td class="list_right_box" width="15%">
                        <input name="daytime" id="daytime" class="mini-textbox" value="${ckContent.daytime}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">夜：</td>
                    <td class="list_right_box" width="15%">
                        <input name="night" id="night" class="mini-textbox" value="${ckContent.night}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">下：</td>
                    <td class="list_right_box" width="15%">
                        <input name="offDuty" id="offDuty" class="mini-textbox" value="${ckContent.offDuty}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">中：</td>
                    <td class="list_right_box" width="15%">
                        <input name="centre" id="centre" class="mini-textbox" value="${ckContent.centre}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">外：</td>
                    <td class="list_right_box" width="15%">
                        <input name="overseas" id="overseas" class="mini-textbox" value="${ckContent.overseas}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">休：</td>
                    <td class="list_right_box" width="15%">
                        <input name="rest" id="rest" class="mini-textbox" value="${ckContent.rest}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">全：</td>
                    <td class="list_right_box" width="15%">
                        <input name="allDay" id="allDay" class="mini-textbox" value="${ckContent.allDay}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">带：</td>
                    <td class="list_right_box" width="15%">
                        <input name="take" id="take" class="mini-textbox" value="${ckContent.take}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">弹：</td>
                    <td class="list_right_box" width="15%">
                        <input name="flip" id="flip" class="mini-textbox" value="${ckContent.flip}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">调：</td>
                    <td class="list_right_box" width="15%">
                        <input name="call" id="call" class="mini-textbox" value="${ckContent.call}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">换：</td>
                    <td class="list_right_box" width="15%">
                        <input name="changes" id="changes" class="mini-textbox" value="${ckContent.changes}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">病：</td>
                    <td class="list_right_box" width="15%">
                        <input name="disease" id="disease" class="mini-textbox" value="${ckContent.disease}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">事：</td>
                    <td class="list_right_box" width="15%">
                        <input name="thing" id="thing" class="mini-textbox" value="${ckContent.thing}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">婚：</td>
                    <td class="list_right_box" width="15%">
                        <input name="marry" id="marry" class="mini-textbox" value="${ckContent.marry}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">伤：</td>
                    <td class="list_right_box" width="15%">
                        <input name="injury" id="injury" class="mini-textbox" value="${ckContent.injury}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="10%">丧：</td>
                    <td class="list_right_box" width="15%">
                        <input name="lost" id="lost" class="mini-textbox" value="${ckContent.lost}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">产：</td>
                    <td class="list_right_box" width="15%">
                        <input name="give" id="give" class="mini-textbox" value="${ckContent.give}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">探：</td>
                    <td class="list_right_box" width="15%">
                        <input name="explore" id="explore" class="mini-textbox" value="${ckContent.explore}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">旷：</td>
                    <td class="list_right_box" width="15%">
                        <input name="free" id="free" class="mini-textbox" value="${ckContent.free}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">待：</td>
                    <td class="list_right_box" width="15%">
                        <input name="stay" id="stay" class="mini-textbox" value="${ckContent.stay}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">学：</td>
                    <td class="list_right_box" width="15%">
                        <input name="study" id="study" class="mini-textbox" value="${ckContent.study}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">非：</td>
                    <td class="list_right_box" width="15%">
                        <input name="very" id="very" class="mini-textbox" value="${ckContent.very}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">公：</td>
                    <td class="list_right_box" width="15%">
                        <input name="business" id="business" class="mini-textbox" value="${ckContent.business}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">差：</td>
                    <td class="list_right_box" width="15%">
                        <input name="evection" id="evection" class="mini-textbox" value="${ckContent.evection}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">团：</td>
                    <td class="list_right_box" width="15%">
                        <input name="team" id="team" class="mini-textbox" value="${ckContent.team}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">借：</td>
                    <td class="list_right_box" width="15%">
                        <input name="borrow" id="borrow" class="mini-textbox" value="${ckContent.borrow}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">退：</td>
                    <td class="list_right_box" width="15%">
                        <input name="retreat" id="retreat" class="mini-textbox" value="${ckContent.retreat}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="10%">辞：</td>
                    <td class="list_right_box" width="15%">
                        <input name="diction" id="diction" class="mini-textbox" value="${ckContent.diction}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">离：</td>
                    <td class="list_right_box" width="15%">
                        <input name="leave" id="leave" class="mini-textbox" value="${ckContent.leave}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">年：</td>
                    <td class="list_right_box" width="15%">
                        <input name="year" id="year" class="mini-textbox" value="${ckContent.year}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                     <td class="list_left_box1" valign="top" width="10%">其他：</td>
                    <td class="list_right_box" width="15%">
                        <input name="other" id="other" class="mini-textbox" value="${ckContent.other}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">大班：</td>
                    <td class="list_right_box" width="15%">
                        <input name="bigClass" id="bigClass" class="mini-textbox" value="${ckContent.bigClass}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">小班：</td>
                    <td class="list_right_box" width="15%">
                        <input name="smallClass" id="smallClass" class="mini-textbox" value="${ckContent.smallClass}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">大夜：</td>
                    <td class="list_right_box" width="15%">
                        <input name="bigNight" id="bigNight" class="mini-textbox" value="${ckContent.bigNight}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">小夜：</td>
                    <td class="list_right_box" width="15%">
                        <input name="smallNight" id="smallNight" class="mini-textbox" value="${ckContent.smallNight}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">上班时间段1：</td>
                    <td class="list_right_box" width="15%">
                        <input class="mini-timespinner" format="HH:mm" width="100px" required="true"
						 	id="startTimeb1" name="startTimeb1" value="${ckContent.startTimeb1}" />
                    </td>
                    <td class="list_left_box1" valign="center" width="10%" style="text-align:center"> —— </td>
                    <td class="list_right_box" width="15%">
                        <input class="mini-timespinner" format="HH:mm" width="100px" required="true" 
						 	id="startTimef1" name="startTimef1" value="${ckContent.startTimef1}" />
                    </td>
                	<td class="list_left_box1" valign="top" width="10%">下班时间段1：</td>
                    <td class="list_right_box" width="15%">
                        <input class="mini-timespinner" format="HH:mm" width="100px" required="true" 
						 	id="endTimeb1" name="endTimeb1" value="${ckContent.endTimeb1}" />
                    </td>
                    <td class="list_left_box1" valign="center" width="10%" style="text-align:center"> —— </td>
                    <td class="list_right_box" width="15%">
                        <input class="mini-timespinner" format="HH:mm" width="100px" required="true" 
						 	id="endTimef1" name="endTimef1" value="${ckContent.endTimef1}" />
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">上班时间段2：</td>
                    <td class="list_right_box" width="15%">
                        <input class="mini-timespinner" format="HH:mm" width="100px"
						 	id="startTimeb2" name="startTimeb2" value="${ckContent.startTimeb2}" />
                    </td>
                    <td class="list_left_box1" valign="center" width="10%" style="text-align:center"> —— </td>
                    <td class="list_right_box" width="15%">
                        <input class="mini-timespinner" format="HH:mm" width="100px"
						 	id="startTimef2" name="startTimef2" value="${ckContent.startTimef2}" />
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">下班时间段2：</td>
                    <td class="list_right_box" width="15%">
                        <input class="mini-timespinner" format="HH:mm" width="100px"
						 	id="endTimeb2" name="endTimeb2" value="${ckContent.endTimeb2}" />
                    </td>
                    <td class="list_left_box1" valign="center" width="10%" style="text-align:center"> —— </td>
                    <td class="list_right_box" width="15%">
                        <input class="mini-timespinner" format="HH:mm" width="100px"
						 	id="endTimef2" name="endTimef2" value="${ckContent.endTimef2}" />
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">上班时间段3：</td>
                    <td class="list_right_box" width="15%">
                        <input class="mini-timespinner" format="HH:mm" width="100px"
						 	id="startTimeb3" name="startTimeb3" value="${ckContent.startTimeb3}" />
                    </td>
                    <td class="list_left_box1" valign="center" width="10%" style="text-align:center"> —— </td>
                    <td class="list_right_box" width="15%">
                        <input class="mini-timespinner" format="HH:mm" width="100px"
						 	id="startTimef3" name="startTimef3" value="${ckContent.startTimef3}" />
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">下班时间段3：</td>
                    <td class="list_right_box" width="15%">
                        <input class="mini-timespinner" format="HH:mm" width="100px"
						 	id="endTimeb3" name="endTimeb3" value="${ckContent.endTimeb3}" />
                    </td>
                    <td class="list_left_box1" valign="center" width="10%" style="text-align:center;paddint-top:5px;"> —— </td>
                    <td class="list_right_box" width="15%">
                        <input class="mini-timespinner" format="HH:mm" width="100px"
						 	id="endTimef3" name="endTimef3" value="${ckContent.endTimef3}" />
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">计件：</td>
                    <td class="list_right_box" width="15%">
                        <input name="piece" id="piece" class="mini-textbox" value="${ckContent.piece}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                	<td class="list_left_box1" valign="top" width="10%">加班系数：</td>
                    <td class="list_right_box" width="15%">
                        <input name="overtimeModulus" id="overtimeModulus" class="mini-textbox" value="${ckContent.overtimeModulus}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">节加系数：</td>
                    <td class="list_right_box" width="15%">
                        <input name="vacationModulus" id="vacationModulus" class="mini-textbox" value="${ckContent.vacationModulus}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">出勤小时数：</td>
                    <td class="list_right_box" width="15%">
                        <input name="outHour" id="outHour" class="mini-textbox" value="${ckContent.outHour}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="10%">延时小时数：</td>
                    <td class="list_right_box" width="15%">
                        <input name="delayedHour" id="delayedHour" class="mini-textbox" value="${ckContent.delayedHour}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                </tr>
                <tr style="height:100px;">
                    <td class="list_left_box1" valign="top" width="10%">说明：</td>
                    <td class="list_right_box" width="15%" colspan="7">
                        <input name="discription" id="discription" class="mini-textarea" value="${ckContent.discription}"
                            required="false" requiredErrorText="不能为空" vtype="maxLength:200" width="100%" height="100%"/>
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;"> 
        <a class="mini-button" onclick="onOk(1)" id="cz" style="margin-right:20px;" iconCls="icon-reload">保存为模版</a>
        <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
    </div>
    
    <script type="text/javascript">
    	var id = $("#id").val();
        if (id != ""){
        	$("#content").attr("enabled", false);
        }
    	
        mini.parse();
                
        function onOk(id){
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            //验证上下班时间
//             var startTimeb1 = mini.get("startTimeb1").getValue();
//             var sb1 = new Date(startTimeb1);
//         	if ((sb1.getHours() + ":" + sb1.getMinutes()) == "0:0"){
//         		mini.alert("上班时间段1 起始时间不能为空");
//         		return;
//         	}
//         	var startTimef1 = mini.get("startTimef1").getValue();
//             var sf1 = new Date(startTimef1);
//         	if ((sf1.getHours() + ":" + sf1.getMinutes()) == "0:0"){
//         		mini.alert("上班时间段1 结束时间不能为空");
//         		return;
//         	}
//         	var endTimeb1 = mini.get("endTimeb1").getValue();
//             var eb1 = new Date(endTimeb1);
//         	if ((eb1.getHours() + ":" + eb1.getMinutes()) == "0:0"){
//         		mini.alert("下班时间段1 起始时间不能为空");
//         		return;
//         	}
//         	var endTimef1 = mini.get("endTimef1").getValue();
//             var ef1 = new Date(endTimef1);
//         	if ((ef1.getHours() + ":" + ef1.getMinutes()) == "0:0"){
//         		mini.alert("下班时间段1 结束时间不能为空");
//         		return;
//         	}
            var data = form.getData();
            var json = mini.encode(data); 
            //strut令牌
            window.parent.loading();
            $.ajax({
                url: "../check/content!saveOrUpdate.action?contentName="+id,
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