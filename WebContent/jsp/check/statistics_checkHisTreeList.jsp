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
<title>考勤历史树信息</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<link rel="stylesheet" href="../template/system/css/base.css"
	type="text/css"></link>
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	border: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
}
.list_right_box {
    border-bottom: 1px solid #ddd;
    padding-top: 5px;
}
.asLabel .mini-textbox-border,
    .asLabel .mini-textbox-input,
    .asLabel .mini-buttonedit-border,
    .asLabel .mini-buttonedit-input,
    .asLabel .mini-textboxlist-border
    {
        border:0;background:none;cursor:default;
    }
</style>
</head>
<body>
	<div class="mini-splitter" style="width:100%;height:100%;" handlerSize="9">
		<div size="300" showCollapseButton="true">
			<!-- ckId -->
			<input type="hidden" value="${sessionScope.depId}" id="depId" />
			<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
				<span style="padding-left:5px;">组织机构：</span>
			</div>
			<div class="mini-fit" style=" border-top:0;">
				<ul id="deptree" class="mini-tree" url="../basis/branch!getTreeListByUser.action" style="width:100%;height:100%;"
					showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true" >
				</ul>
			</div>
		</div>
		<div showCollapseButton="flase">
			<form id="form1">
				<div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
			        <table style="width:100%;">
			            <tr>
			                <td style="width:270px;padding-left:20px;">起始年月份：
			                    <input class="mini-textbox" id="startTime" name="startTime" emptyText="请输入月份：如1703" 
			                    	onenter="onKeyEnter" style="width:180px;" vtype="float;rangeLength:4" required="true"
			                    	onvalidation="onYearMonthValidation"/>
			                </td>
			            	<td style="width:270px;padding-left:20px;">截止年月份：
			                    <input class="mini-textbox" id="endTime" name="endTime" emptyText="请输入月份：如1704" 
			                    onenter="onKeyEnter" style="width:180px;" vtype="float;rangeLength:4" required="true"
			                    onvalidation="onYearMonthValidation"/>
			                </td>
			                <td style="width:250px;padding-left:20px;">职工号：
			                    <input class="mini-textbox" id="jobNumber" name="jobNumber" emptyText="请输入职工号" 
			                    	style="width:150px;"/>
			                </td>
			                <td style="white-space:nowrap;">
			                    <a class="mini-button" iconCls="icon-search" onenter="onKeyEnter"
			                    	onclick="search('startTime,endTime,jobNumber','startTime,endTime,jobNumber')">查询</a>
			                </td>
			            </tr>
			        </table>
			    </div>
			    <div class="mini-toolbar" style="border-left:0;padding:0px;">
					<table style="width:100%;">
						<tr>
							<td>
								<a class="mini-button" iconCls="icon-upload" onclick="exportCheckHisInfo()">导出班制信息</a> 
<!-- 								<a class="mini-button" iconCls="icon-upload" onclick="exportCheckHisInfo()">导出考勤时间信息</a>  -->
							</td>
						</tr>
					</table>
				</div>
			</form>
		    <div class="mini-fit">
		    	<div id="datagrid1" class="mini-datagrid" style="width:1930px;height:60%;" multiSelect="true"
        			url="../check/statistics!getCheckHisData.action" idField="id" allowResize="true" 
        			allowAlternating="true" pageSize="15" onselectionchanged="onSelectionChanged">
		            <div property="columns">
		                <div type="indexcolumn" width="50" headerAlign="center">序列</div>
		                <div field="jobNumber" width="80" headerAlign="center" align="center" >职工号</div>
		                <div field="monthCalendar" width="80" headerAlign="center" align="center" >年月份</div>
		                <div field="name" width="80" headerAlign="center" align="center" >姓名</div>
		                <div field="className" width="80" headerAlign="center" align="center" >班组</div>
		                <div field="day1" width="50" headerAlign="center" align="center" >1日</div>
		                <div field="day2" width="50" headerAlign="center" align="center" >2日</div>
		                <div field="day3" width="50" headerAlign="center" align="center" >3日</div>
		                <div field="day4" width="50" headerAlign="center" align="center" >4日</div>
		                <div field="day5" width="50" headerAlign="center" align="center" >5日</div>
		                <div field="day6" width="50" headerAlign="center" align="center" >6日</div>
		                <div field="day7" width="50" headerAlign="center" align="center" >7日</div>
		                <div field="day8" width="50" headerAlign="center" align="center" >8日</div>
		                <div field="day9" width="50" headerAlign="center" align="center" >9日</div>
		                <div field="day10" width="50" headerAlign="center" align="center" >10日</div>
		                <div field="day11" width="50" headerAlign="center" align="center" >11日</div>
		                <div field="day12" width="50" headerAlign="center" align="center" >12日</div>
		                <div field="day13" width="50" headerAlign="center" align="center" >13日</div>
		                <div field="day14" width="50" headerAlign="center" align="center" >14日</div>
		                <div field="day15" width="50" headerAlign="center" align="center" >15日</div>
		                <div field="day16" width="50" headerAlign="center" align="center" >16日</div>
		                <div field="day17" width="50" headerAlign="center" align="center" >17日</div>
		                <div field="day18" width="50" headerAlign="center" align="center" >18日</div>
		                <div field="day19" width="50" headerAlign="center" align="center" >19日</div>
		                <div field="day20" width="50" headerAlign="center" align="center" >20日</div>
		                <div field="day21" width="50" headerAlign="center" align="center" >21日</div>
		                <div field="day22" width="50" headerAlign="center" align="center" >22日</div>
		                <div field="day23" width="50" headerAlign="center" align="center" >23日</div>
		                <div field="day24" width="50" headerAlign="center" align="center" >24日</div>
		                <div field="day25" width="50" headerAlign="center" align="center" >25日</div>
		                <div field="day26" width="50" headerAlign="center" align="center" >26日</div>
		                <div field="day27" width="50" headerAlign="center" align="center" >27日</div>
		                <div field="day28" width="50" headerAlign="center" align="center" >28日</div>
		                <div field="day29" width="50" headerAlign="center" align="center" >29日</div>
		                <div field="day30" width="50" headerAlign="center" align="center" >30日</div>
		                <div field="day31" width="50" headerAlign="center" align="center" >31日</div>
		            </div>
		        </div>
		        <fieldset style="width:100%;border:solid 1px #aaa;margin-top:8px;position:relative;">
			        <legend>考勤详细信息</legend>
			        <div id="editForm1" style="padding:5px;">
			            <input class="mini-hidden" name="id"/>
			            <table cellpadding="0" cellspacing="0" border="0" align="center" class="addtable" width="1905" style="margin:auto;padding-left:5px;padding-right:5px;">
			                <tr>
			                	<td class="list_right_box" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;width:50px;">日期</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center">1</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">2</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">3</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">4</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">5</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">6</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">7</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">8</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">9</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">10</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">11</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">12</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">13</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">14</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">15</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">16</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">17</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">18</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">19</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">20</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">21</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">22</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">23</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">24</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">25</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">26</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">27</td>
			                    <td class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">28</td>
			                    <td id="tr1td29" class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">29</td>
			                    <td id="tr1td30" class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">30</td>
			                    <td id="tr1td31" class="list_right_box" width="3%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">31</td>
			                 </tr>
			                <tr>
			                	<td class="list_right_box" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;width:50px;">加班</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime1" name="overtime1" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime2" name="overtime2" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime3" name="overtime3" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime4" name="overtime4" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime5" name="overtime5" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime6" name="overtime6" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime7" name="overtime7" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime8" name="overtime8" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime9" name="overtime9" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime10" name="overtime10" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime11" name="overtime11" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime12" name="overtime12" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime13" name="overtime13" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime14" name="overtime14" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime15" name="overtime15" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime16" name="overtime16" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime17" name="overtime17" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime18" name="overtime18" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime19" name="overtime19" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime20" name="overtime20" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime21" name="overtime21" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime22" name="overtime22" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime23" name="overtime23" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime24" name="overtime24" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime25" name="overtime25" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime26" name="overtime26" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime27" name="overtime27" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime28" name="overtime28" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td id="tr3td29" class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime29" name="overtime29" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td id="tr3td30" class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime30" name="overtime30" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                	<td id="tr3td31" class="list_right_box" align="center">
			                		<input class="mini-checkbox" id="overtime31" name="overtime31" value="" trueValue="1" falseValue="0" valueField="text" url="../template/isOvertime.txt" readonly="true"/>
			                	</td>
			                </tr>
			                <tr>
			                	<td class="list_right_box" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;width:50px;">最早时间</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime1" name="startTime1" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime2" name="startTime2" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime3" name="startTime3" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime4" name="startTime4" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime5" name="startTime5" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime6" name="startTime6" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime7" name="startTime7" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime8" name="startTime8" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime9" name="startTime9" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime10" name="startTime10" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime11" name="startTime11" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime12" name="startTime12" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime13" name="startTime13" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime14" name="startTime14" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime15" name="startTime15" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime16" name="startTime16" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime17" name="startTime17" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime18" name="startTime18" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime19" name="startTime19" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime20" name="startTime20" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime21" name="startTime21" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime22" name="startTime22" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime23" name="startTime23" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime24" name="startTime24" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime25" name="startTime25" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime26" name="startTime26" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime27" name="startTime27" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime28" name="startTime28" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td id="tr4td29" class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime29" name="startTime29" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td id="tr4td30" class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime30" name="startTime30" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td id="tr4td31" class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="startTime31" name="startTime31" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                </tr>
			                <tr>
			                	<td class="contact list_right_box" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;width:50px;">最晚时间</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime1" name="endTime1" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime2" name="endTime2" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime3" name="endTime3" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime4" name="endTime4" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime5" name="endTime5" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime6" name="endTime6" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime7" name="endTime7" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime8" name="endTime8" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime9" name="endTime9" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime10" name="endTime10" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime11" name="endTime11" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime12" name="endTime12" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime13" name="endTime13" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime14" name="endTime14" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime15" name="endTime15" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime16" name="endTime16" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime17" name="endTime17" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime18" name="endTime18" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime19" name="endTime19" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime20" name="endTime20" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime21" name="endTime21" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime22" name="endTime22" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime23" name="endTime23" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime24" name="endTime24" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime25" name="endTime25" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime26" name="endTime26" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime27" name="endTime27" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime28" name="endTime28" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td id="tr5td29" class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime29" name="endTime29" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td id="tr5td30" class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime30" name="endTime30" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                	<td id="tr5td31" class="contact list_right_box" style="text-decoration: none;text-align:center">
			                		<input class="mini-textarea" id="endTime31" name="endTime31" style="width:45px;text-align:center" readonly="true" value=""/>
			                	</td>
			                </tr>
			                <tr>
			                	<td class="list_right_box" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;width:50px;">备注</td>
			                	<td id="tr6td29" class="list_right_box" style="text-decoration: none;text-align:center" colspan="31">
			                		<input id="remark" name="remark" vtype="maxLength:200" class="mini-textarea" readonly="true"
										emptyText="" value="" style="width:100%;height:100%;" />
			                	</td>
			                </tr>
			            </table>
			        </div>
			    </fieldset>
		    </div>
		</div>
	</div>
	<script type="text/javascript">
		mini.parse();
		var grid = mini.get("datagrid1");
		var form = new mini.Form("editForm1");
		labelModel();
		
		//年月份格式验证
		function onYearMonthValidation(e) {
            if (e.isValid) {
                var pattern = /\d{2}[0]\d{1}|\d{2}[1][0-2]/;
                if (e.value.length != 4 || pattern.test(e.value) == false) {
                    e.errorText = "必须输入4位年月份数字";
                    e.isValid = false;
                }
            }
        }
		
		$(function(){ 
        	grid.load();
			//如果有树
			var tree=mini.get("deptree");
			if(tree && grid){
				//默认选中
				var depId = $("#depId").val();
				if(depId){
					var originalNode=tree.getNode();
					tree.selectNode(originalNode);
				}
				
				tree.on("nodeclick", function (e) {
					var startTime = mini.get("startTime").getValue();
		        	var endTime = mini.get("endTime").getValue();
		        	var jobNumber = mini.get("jobNumber").getValue();
					var tree=mini.get("deptree");
					var node = tree.getSelectedNode();
					//可见即可选
					/*if(!tree.isAncestor ( originalNode, node )){
						tree.selectNode ( originalNode );//选中原节点
						//tree.unSelectNode( node );//取消选中当前节点
						return;
					}*/
					if (node) {
						if(document.getElementById("searchButton")){ 
							$("#searchButton").click();
						}else { 
							var ids = "";
							var tree=mini.get("deptree");
							if(tree){
								var node = tree.getSelectedNode();
								if(node){
									var ids = "";
									ids = depAll(ids,tree,node);
								}
							}
							if (startTime == ""){
								mini.alert("请输入起始年月份");
								return;
							}
							if (endTime == ""){
								mini.alert("请输入截止年月份");
								return;
							}
							if (parseInt(startTime) > parseInt(endTime)){//起始时间大于截止时间
								mini.alert("起始年月份不能大于截止年月份！");
								return;
							}
							var myDate = new Date();
							var currYear = myDate.getFullYear(); //获取完整的年份
							var currMonth = myDate.getMonth(); //获取当前月份(0-11,0代表1月)
							currYear = (currYear+"").substring(2,4);
							currMonth = (currMonth+1+"").length==1?("0"+(currMonth+1)):(currMonth+1);
							if (parseInt(endTime) > parseInt(currYear+""+currMonth)){
								mini.alert("截止年月份不能大于当前年月份！");
								return;
							}
							var form = new mini.Form("form1");
				            form.validate();
				            if (form.isValid() == false) return;
							grid.load({ searchData:"{\"depId\":\""+ids+"\", \"startTime\":\""+startTime+"\", \"endTime\":\""+endTime+"\", \"jobNumber\":\""+jobNumber+"\"}" });
						}
					} else {
						grid.setData([]);
						grid.setTotalCount(0);
					}
				});
			}
		});
		
		/**查询
		 * name:所有查询条件框的name字符串
		 * attr:所有查询条件的属性名
		 * */
		function search(name,attr){
			var grid = mini.get("datagrid1");
			var startTime = mini.get("startTime").getValue();
        	var endTime = mini.get("endTime").getValue();
			if (startTime == ""){
				mini.alert("请输入起始年月份");
				return;
			}
			if (endTime == ""){
				mini.alert("请输入截止年月份");
				return;
			}
			if (parseInt(startTime) > parseInt(endTime)){//起始时间大于截止时间
				mini.alert("起始年月份不能大于截止年月份！");
				return;
			}
			var myDate = new Date();
			var currYear = myDate.getFullYear(); //获取完整的年份
			var currMonth = myDate.getMonth(); //获取当前月份(0-11,0代表1月)
			currYear = (currYear+"").substring(2,4);
			currMonth = (currMonth+1+"").length==1?("0"+(currMonth+1)):(currMonth+1);
			if (parseInt(endTime) > parseInt(currYear+""+currMonth)){
				mini.alert("截止年月份不能大于当前年月份！");
				return;
			}
			var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
			var searchData="{";
			if(name&&attr){
				var names=name.split(",");
				var attrs=attr.split(",");
				for(var i=0;i<names.length;i++){
					if(mini.get(names[i])){
						var value=mini.get(names[i]).getValue();
						searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\"";
						if(i!=names.length-1)searchData=searchData+",";
					}
				}
			}
			//searchData=searchData+",\"start\""+":\""+mini.get("start").getFormValue()
			//			+"\","+"\"end\""+":\""+mini.get("end").getFormValue()+"\"";
			//如果有树
			var tree=mini.get("deptree");
			if(tree){
				var node = tree.getSelectedNode();
				if(node){
					var ids = "";
					ids = depAll(ids,tree,node);
					if (searchData == "{"){
						searchData=searchData+"\"depId\":\""+ids+"\"";
					} else {
						searchData=searchData+",\"depId\":\""+ids+"\"";
					}
				    
				}
			}
			searchData=searchData+"}";
			grid.load({ searchData: searchData });
		}
		
		function depAll(ids,tree,node){
			if(node.flg==1){
				ids += node.id+",";
				return ids;
			}else{
				var list = tree.getChildNodes(node);
				if(list.length>0){
					 for ( var i = 0; i < list.length; i++) {
					    	if(list[i].flg==1){
					    		ids = ids + "," + list[i].id;
					    	}else{
					    		node = list[i];
					    		ids = depAll(ids,tree,node);
					    	}
					 }
				}
				return ids;
			}
		}
		
		function labelModel() {
			var form = new mini.Form("editForm1");
            var fields = form.getFields();                
            for (var i = 0, l = fields.length; i < l; i++) {
                var c = fields[i];
                if (c.setReadOnly) c.setReadOnly(true);     //只读
                if (c.addCls) c.addCls("asLabel");          //增加asLabel外观
            }
        }
		
		//选择数据事件
		function onSelectionChanged(e) {
            var grid = e.sender;
            var record = grid.getSelected();
            if (record) {
                editRow(record._uid);
            } else {                
                form.reset();
            }
        }
        
        //详情信息加载
        function editRow(row_uid) {
            var row = grid.getRowByUID(row_uid);
            if (row) {
                //表单加载员工信息
                var form = new mini.Form("editForm1");
                if (grid.isNewRow(row)) {
                    form.reset();
                } else {
                    form.loading();
                    $.ajax({
                        url: "../check/statistics!getDetailInfoById.action?id=" + row.id,
                        success: function (text) {
                            var o = mini.decode(text);
                            form.setData(o);
                            form.unmask();
                        }
                    });
                }
                grid.doLayout();
            }
        }
		
		//导出年假信息集合
		function exportCheckHisInfo(){
			var startTime = mini.get("startTime").getValue();
        	var endTime = mini.get("endTime").getValue();
        	var jobNumber = mini.get("jobNumber").getValue();
        	var depIds = "";
			//如果有树
			var tree=mini.get("deptree");
			if(tree){
				var node = tree.getSelectedNode();
				if(node){
					var ids = "";
					ids = depAll(ids,tree,node);
					depIds = ids.substring(0,ids.length);
				}
			}
			window.location.href="../check/statistics!exportCheckHisInfo.action?startTime="
				+startTime+"&endTime="+endTime+"&jobNumber="+jobNumber+"&depIds="+depIds;
		}
	</script>
</body>
</html>