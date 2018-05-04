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
<title>修改员工月度考勤统计信息</title>
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
    <form id="form1" method="post">
        <input class="mini-hidden" name="id" id="id" value="${statistics.id}" style="display:none;"/>
        <div>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                <tr>
                    <td class="list_left_box1" valign="top" width="13%">职工号：</td>
                    <td class="list_right_box" width="12%">
                        <input name="jobNumber" id="jobNumber" class="mini-textbox" value="${statistics.jobNumber}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" width="100px" />
                    </td>
                    <td class="list_left_box1" valign="top" width="13%">年月份：</td>
                    <td class="list_right_box" width="12%">
                        <input name="yearMonth" id="yearMonth" class="mini-textbox" value="${statistics.yearMonth}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:10" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="13%">姓名：</td>
                    <td class="list_right_box" width="12%">
                        <input name="name" id="name" class="mini-textbox" value="${statistics.name}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:20" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="13%">具体岗位：</td>
                    <td class="list_right_box" width="12%">
                        <input name="name" id="name" class="mini-textbox" value="${statistics.postName}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:20" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="13%">人员类别：</td>
                    <td class="list_right_box" width="12%">
                        <input name="empType" id="empType" class="mini-textbox" value="${statistics.empType}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:20" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="13%">班制：</td>
                    <td class="list_right_box" width="12%">
                        <input name="instituteName" id="instituteName" class="mini-textbox" value="${statistics.instituteName}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:20" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="13%">工资形式：</td>
                    <td class="list_right_box" width="12%">
                        <input name="wageForm" id="wageForm" class="mini-textbox" value="${statistics.wageForm}" enabled="false"
                            required="true" requiredErrorText="不能为空" vtype="maxLength:20" width="100px"/>
                    </td>
                	<td class="list_left_box1" valign="top" width="10%">白：</td>
                    <td class="list_right_box" width="15%">
                        <input name="daytime" id="daytime" class="mini-textbox" value="${statistics.daytime}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">夜：</td>
                    <td class="list_right_box" width="15%">
                        <input name="night" id="night" class="mini-textbox" value="${statistics.night}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">下：</td>
                    <td class="list_right_box" width="15%">
                        <input name="offDuty" id="offDuty" class="mini-textbox" value="${statistics.offDuty}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">中：</td>
                    <td class="list_right_box" width="15%">
                        <input name="centre" id="centre" class="mini-textbox" value="${statistics.centre}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">外：</td>
                    <td class="list_right_box" width="15%">
                        <input name="overseas" id="overseas" class="mini-textbox" value="${statistics.overseas}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">休：</td>
                    <td class="list_right_box" width="15%">
                        <input name="rest" id="rest" class="mini-textbox" value="${statistics.rest}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">全：</td>
                    <td class="list_right_box" width="15%">
                        <input name="alls" id="alls" class="mini-textbox" value="${statistics.alls}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">带：</td>
                    <td class="list_right_box" width="15%">
                        <input name="take" id="take" class="mini-textbox" value="${statistics.take}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">弹：</td>
                    <td class="list_right_box" width="15%">
                        <input name="flip" id="flip" class="mini-textbox" value="${statistics.flip}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">调：</td>
                    <td class="list_right_box" width="15%">
                        <input name="takeWork" id="takeWork" class="mini-textbox" value="${statistics.takeWork}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">换：</td>
                    <td class="list_right_box" width="15%">
                        <input name="changes" id="changes" class="mini-textbox" value="${statistics.changes}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">病：</td>
                    <td class="list_right_box" width="15%">
                        <input name="disease" id="disease" class="mini-textbox" value="${statistics.disease}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">事：</td>
                    <td class="list_right_box" width="15%">
                        <input name="thing" id="thing" class="mini-textbox" value="${statistics.thing}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">婚：</td>
                    <td class="list_right_box" width="15%">
                        <input name="marry" id="marry" class="mini-textbox" value="${statistics.marry}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                	<td class="list_left_box1" valign="top" width="10%">伤：</td>
                    <td class="list_right_box" width="15%">
                        <input name="injury" id="injury" class="mini-textbox" value="${statistics.injury}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">丧：</td>
                    <td class="list_right_box" width="15%">
                        <input name="lost" id="lost" class="mini-textbox" value="${statistics.lost}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">产：</td>
                    <td class="list_right_box" width="15%">
                        <input name="give" id="give" class="mini-textbox" value="${statistics.give}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">探：</td>
                    <td class="list_right_box" width="15%">
                        <input name="explore" id="explore" class="mini-textbox" value="${statistics.explore}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">旷：</td>
                    <td class="list_right_box" width="15%">
                        <input name="free" id="free" class="mini-textbox" value="${statistics.free}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">待：</td>
                    <td class="list_right_box" width="15%">
                        <input name="stay" id="stay" class="mini-textbox" value="${statistics.stay}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">学：</td>
                    <td class="list_right_box" width="15%">
                        <input name="study" id="study" class="mini-textbox" value="${statistics.study}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">非：</td>
                    <td class="list_right_box" width="15%">
                        <input name="very" id="very" class="mini-textbox" value="${statistics.very}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">公：</td>
                    <td class="list_right_box" width="15%">
                        <input name="business" id="business" class="mini-textbox" value="${statistics.business}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">差：</td>
                    <td class="list_right_box" width="15%">
                        <input name="evection" id="evection" class="mini-textbox" value="${statistics.evection}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">团：</td>
                    <td class="list_right_box" width="15%">
                        <input name="team" id="team" class="mini-textbox" value="${statistics.team}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">借：</td>
                    <td class="list_right_box" width="15%">
                        <input name="borrow" id="borrow" class="mini-textbox" value="${statistics.borrow}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">退：</td>
                    <td class="list_right_box" width="15%">
                        <input name="retreat" id="retreat" class="mini-textbox" value="${statistics.retreat}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">辞：</td>
                    <td class="list_right_box" width="15%">
                        <input name="diction" id="diction" class="mini-textbox" value="${statistics.diction}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">离：</td>
                    <td class="list_right_box" width="15%">
                        <input name="leave" id="leave" class="mini-textbox" value="${statistics.leave}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">计件：</td>
                    <td class="list_right_box" width="15%">
                        <input name="piece" id="piece" class="mini-textbox" value="${statistics.piece}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                	<td class="list_left_box1" valign="top" width="10%">年休：</td>
                    <td class="list_right_box" width="15%">
                        <input name="years" id="years" class="mini-textbox" value="${statistics.years}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">其他：</td>
                    <td class="list_right_box" width="15%">
                        <input name="other" id="other" class="mini-textbox" value="${statistics.other}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">出工：</td>
                    <td class="list_right_box" width="15%">
                        <input name="outWork" id="outWork" class="mini-textbox" value="${statistics.outWork}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">大班：</td>
                    <td class="list_right_box" width="15%">
                        <input name="bigClass" id="bigClass" class="mini-textbox" value="${statistics.bigClass}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">小班：</td>
                    <td class="list_right_box" width="15%">
                        <input name="smallClass" id="smallClass" class="mini-textbox" value="${statistics.smallClass}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">大夜：</td>
                    <td class="list_right_box" width="15%">
                        <input name="bigNight" id="bigNight" class="mini-textbox" value="${statistics.bigNight}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">小夜：</td>
                    <td class="list_right_box" width="15%">
                        <input name="smallNight" id="smallNight" class="mini-textbox" value="${statistics.smallNight}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" width="10%">合计：</td>
                    <td class="list_right_box" width="15%">
                        <input name="totals" id="totals" class="mini-textbox" value="${statistics.totals}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                	<td class="list_left_box1" valign="top" width="10%">出勤天数：</td>
                    <td class="list_right_box" width="15%">
                        <input name="attendanceDays" id="attendanceDays" class="mini-textbox" value="${statistics.attendanceDays}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                	<td class="list_left_box1" valign="top" width="10%">夜班：</td>
                    <td class="list_right_box" width="15%">
                        <input name="nightDays" id="nightDays" class="mini-textbox" value="${statistics.nightDays}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">节加：</td>
                    <td class="list_right_box" width="15%">
                        <input name="vacationModulus" id="vacationModulus" class="mini-textbox" value="${statistics.vacationModulus}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="10%">休加：</td>
                    <td class="list_right_box" width="15%">
                        <input name="overtimeModulus" id="overtimeModulus" class="mini-textbox" value="${statistics.overtimeModulus}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                	<td class="list_left_box1" valign="top" width="10%">延时小时数：</td>
                    <td class="list_right_box" width="15%">
                        <input name="delayedModulus" id="delayedModulus" class="mini-textbox" value="${statistics.delayedModulus}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="10%">缓休：</td>
                    <td class="list_right_box" width="15%">
                        <input name="latency" id="latency" class="mini-textbox" value="${statistics.latency}"
                            required="true" vtype="float;rangeChar:1,4" requiredErrorText="不能为空" width="100px"/>
                    </td>
                </tr>
                <tr style="height:100px;">
                    <td class="list_left_box1" valign="top" width="10%">备注：</td>
                    <td class="list_right_box" width="15%" colspan="7">
                        <input name="remark" id="remark" class="mini-textarea" value="${statistics.remark}"
                            required="false" requiredErrorText="不能为空" vtype="maxLength:200" width="100%" height="100%"/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        </div>
    </form>
    
    <script type="text/javascript">    	
        mini.parse();
                
        function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            var data = form.getData();
            var json = mini.encode(data); 
            //strut令牌
            window.parent.loading();
            $.ajax({
                url: "../check/statistics!updateStatisInfos.action",
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