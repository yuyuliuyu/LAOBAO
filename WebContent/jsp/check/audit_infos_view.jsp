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
<title>查看考勤信息</title>
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
    .us{
		width:800px;height:40px;
		padding:10px;
		position:relative;top:0px;left:20px;
	}
</style>
</head>
<body style="line-height: 16px;">
    <form id="form1" method="post">
        <input class="mini-hidden" name="id" id="id" value="${ckCheckInfo.id}" style="display:none;"/>
        <input type="hidden" id="monthDays" name="monthDays" value="${monthDays }" />
        <input type="hidden" id="yearMonth" name="yearMonth" value="${yearMonth }" />
        <div style="overflow-x: auto; overflow-y: auto;width:100%;">
        	<table border="0" cellpadding="0" cellspacing="0" style="padding-bottom:10px;padding-top:10px;">
				<tbody>
					<tr>
						<td class="input_text" width="300">
						</td>
						<td class="input_text" width="300">职工号:
							<span style="border-bottom:#666666 solid 1px;" id="jobNumber">${ckCheckInfo.jobNumber }</span>
						</td>
						<td class="input_text" width="270">姓名：
							<span style="border-bottom:#666666 solid 1px;">${name }</span>
						</td>
						<td class="input_text" colspan="2" width="330">工作日期：
							<span style="border-bottom:#666666 solid 1px;">${workDate }</span>
						</td>
					</tr>
				</tbody>
			</table>
            <table cellpadding="0" cellspacing="0" border="0" align="center" class="addtable" width="1650" style="margin:auto;padding-left:5px;padding-right:5px;">
                <tr>
                	<td class="list_right_box" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;width:50px;">日期</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center">1</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">2</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">3</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">4</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">5</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">6</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">7</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">8</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">9</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">10</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">11</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">12</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">13</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">14</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">15</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">16</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">17</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">18</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">19</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">20</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">21</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">22</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">23</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">24</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">25</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">26</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">27</td>
                    <td class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">28</td>
                    <td id="tr1td29" class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">29</td>
                    <td id="tr1td30" class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">30</td>
                    <td id="tr1td31" class="list_right_box" width="2%" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;">31</td>
                 </tr>
                 <tr>
                 	<td class="list_right_box" style="BACKGROUND: #f6f6f6;align:center;">考勤</td>
                    <td class="list_right_box">
                    	<input name="day1" id="day1" width="40px" value="${ckCheckInfo.day1}" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false" 
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day2" id="day2" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day2}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day3" id="day3" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day3}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day4" id="day4" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day4}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day5" id="day5" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day5}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day6" id="day6" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day6}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day7" id="day7" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day7}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day8" id="day8" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day8}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day9" id="day9" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day9}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day10" id="day10" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day10}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day11" id="day11" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day11}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day12" id="day12" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day12}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day13" id="day13" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day13}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day14" id="day14" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day14}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day15" id="day15" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day15}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day16" id="day16" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day16}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day17" id="day17" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day17}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day18" id="day18" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day18}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day19" id="day19" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"   
							width="40px" value="${ckCheckInfo.day19}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day20" id="day20" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"   
							width="40px" value="${ckCheckInfo.day20}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day21" id="day21" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day21}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day22" id="day22" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day22}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day23" id="day23" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day23}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day24" id="day24" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day24}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day25" id="day25" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day25}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day26" id="day26" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day26}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day27" id="day27" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day27}" url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box">
                        <input name="day28" id="day28" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="true" enabled="false"  
							width="40px" value="${ckCheckInfo.day28}" url="../check/content!getContentList.action"/>
                    </td>
                    <td id="tr2td29" class="list_right_box">
                        <input name="day29" id="daye29" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="false" enabled="false"  
							width="40px" value="${ckCheckInfo.day29}" url="../check/content!getContentList.action"/>
                    </td>
                    <td id="tr2td30" class="list_right_box">
                        <input name="day30" id="day30" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="false" enabled="false"  
							width="40px" value="${ckCheckInfo.day30}" url="../check/content!getContentList.action"/>
                    </td>
                    <td id="tr2td31" class="list_right_box">
                        <input name="day31" id="day31" 
							class="mini-combobox" textField="text" enabled="false" valueField="text" emptyText="请选择..." required="false" enabled="false"  
							width="40px" value="${ckCheckInfo.day31}" url="../check/content!getContentList.action"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_right_box" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;width:50px;">加班</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime1" name="overtime1" value="${ckCheckInfo.overtime1}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime2" name="overtime2" value="${ckCheckInfo.overtime2}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime3" name="overtime3" value="${ckCheckInfo.overtime3}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime4" name="overtime4" value="${ckCheckInfo.overtime4}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime5" name="overtime5" value="${ckCheckInfo.overtime5}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime6" name="overtime6" value="${ckCheckInfo.overtime6}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime7" name="overtime7" value="${ckCheckInfo.overtime7}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime8" name="overtime8" value="${ckCheckInfo.overtime8}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime9" name="overtime9" value="${ckCheckInfo.overtime9}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime10" name="overtime10" value="${ckCheckInfo.overtime10}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime11" name="overtime11" value="${ckCheckInfo.overtime11}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime12" name="overtime12" value="${ckCheckInfo.overtime12}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime13" name="overtime13" value="${ckCheckInfo.overtime13}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime14" name="overtime14" value="${ckCheckInfo.overtime14}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime15" name="overtime15" value="${ckCheckInfo.overtime15}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime16" name="overtime16" value="${ckCheckInfo.overtime16}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime17" name="overtime17" value="${ckCheckInfo.overtime17}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime18" name="overtime18" value="${ckCheckInfo.overtime18}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime19" name="overtime19" value="${ckCheckInfo.overtime19}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime20" name="overtime20" value="${ckCheckInfo.overtime20}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime21" name="overtime21" value="${ckCheckInfo.overtime21}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime22" name="overtime22" value="${ckCheckInfo.overtime22}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime23" name="overtime23" value="${ckCheckInfo.overtime23}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime24" name="overtime24" value="${ckCheckInfo.overtime24}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime25" name="overtime25" value="${ckCheckInfo.overtime25}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime26" name="overtime26" value="${ckCheckInfo.overtime26}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime27" name="overtime27" value="${ckCheckInfo.overtime27}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime28" name="overtime28" value="${ckCheckInfo.overtime28}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td id="tr3td29" class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime29" name="overtime29" value="${ckCheckInfo.overtime29}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td id="tr3td30" class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime30" name="overtime30" value="${ckCheckInfo.overtime30}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                	<td id="tr3td31" class="list_right_box" align="center">
                		<input class="mini-checkbox" id="overtime31" name="overtime31" value="${ckCheckInfo.overtime31}" trueValue="1" falseValue="0" enabled="false" valueField="text" url="../template/isOvertime.txt"/>
                	</td>
                </tr>
                <tr>
                	<td class="list_right_box" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;width:50px;">最早时间</td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime1">${map.startTime1 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime2">${map.startTime2 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime3">${map.startTime3 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime4">${map.startTime4 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime5">${map.startTime5 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime6">${map.startTime6 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime7">${map.startTime7 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime8">${map.startTime8 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime9">${map.startTime9 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime10">${map.startTime10 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime11">${map.startTime11 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime12">${map.startTime12 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime13">${map.startTime13 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime14">${map.startTime14 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime15">${map.startTime15 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime16">${map.startTime16 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime17">${map.startTime17 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime18">${map.startTime18 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime19">${map.startTime19 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime20">${map.startTime20 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime21">${map.startTime21 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime22">${map.startTime22 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime23">${map.startTime23 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime24">${map.startTime24 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime25">${map.startTime25 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime26">${map.startTime26 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime27">${map.startTime27 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime28">${map.startTime28 }</span></td>
                	<td id="tr4td29" class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime29">${map.startTime29 }</span></td>
                	<td id="tr4td30" class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime30">${map.startTime30 }</span></td>
                	<td id="tr4td31" class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="startTime31">${map.startTime31 }</span></td>
                </tr>
                <tr>
                	<td class="contact list_right_box" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;width:50px;">最晚时间</td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime1">${map.endTime1 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime2">${map.endTime2 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime3">${map.endTime3 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime4">${map.endTime4 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime5">${map.endTime5 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime6">${map.endTime6 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime7">${map.endTime7 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime8">${map.endTime8 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime9">${map.endTime9 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime10">${map.endTime10 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime11">${map.endTime11 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime12">${map.endTime12 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime13">${map.endTime13 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime14">${map.endTime14 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime15">${map.endTime15 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime16">${map.endTime16 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime17">${map.endTime17 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime18">${map.endTime18 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime19">${map.endTime19 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime20">${map.endTime20 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime21">${map.endTime21 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime22">${map.endTime22 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime23">${map.endTime23 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime24">${map.endTime24 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime25">${map.endTime25 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime26">${map.endTime26 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime27">${map.endTime27 }</span></td>
                	<td class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime28">${map.endTime28 }</span></td>
                	<td id="tr5td29" class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime29">${map.endTime29 }</span></td>
                	<td id="tr5td30" class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime30">${map.endTime30 }</span></td>
                	<td id="tr5td31" class="contact list_right_box" style="text-decoration: none;text-align:center"><span id="endTime31">${map.endTime31 }</span></td>
                </tr>
                <tr>
                	<td class="list_right_box" style="BACKGROUND: #f6f6f6;text-decoration: none;text-align:center;width:50px;">备注</td>
                	<td id="tr6td29" class="list_right_box" style="text-decoration: none;text-align:center" colspan="31">
                		<input id="remark" name="remark" vtype="maxLength:200" class="mini-textarea" enabled="false"
							emptyText="" value="${ckCheckInfo.remark}" style="width:100%;height:100%;" />
                	</td>
                </tr>
            </table>
        </div>
        <div class="us">
			<span>打卡时间：</span>
			<span id="allTime"></span>
		</div>
    </form>
    
    <script type="text/javascript">
    	var monthDays = "${monthDays}";
    	if(monthDays == 28){
    		$("#tr1td29").hide();
    		$("#tr1td30").hide();
    		$("#tr1td31").hide();
    		$("#tr2td29").hide();
    		$("#tr2td30").hide();
    		$("#tr2td31").hide();
    		$("#tr3td29").hide();
    		$("#tr3td30").hide();
    		$("#tr3td31").hide();
    		$("#tr4td29").hide();
    		$("#tr4td30").hide();
    		$("#tr4td31").hide();
    		$("#tr5td29").hide();
    		$("#tr5td30").hide();
    		$("#tr5td31").hide();
    	} else if (monthDays == 29){
    		$("#tr1td30").hide();
    		$("#tr1td31").hide();
    		$("#tr2td30").hide();
    		$("#tr2td31").hide();
    		$("#tr3td30").hide();
    		$("#tr3td31").hide();
    		$("#tr4td30").hide();
    		$("#tr4td31").hide();
    		$("#tr5td30").hide();
    		$("#tr5td31").hide();
    	} else if (monthDays == 30){
    		$("#tr1td31").hide();
    		$("#tr2td31").hide();
    		$("#tr3td31").hide();
    		$("#tr4td31").hide();
    		$("#tr5td31").hide();
    	}
        mini.parse();
        
        $(document).ready(function(){
			$(".contact").hover(function(){  
				var dayValue = $(this).find('span').attr('id') + "";
				var day = dayValue.substring(dayValue.indexOf("Time")+4, dayValue.length);
				var yearMonth = $("#yearMonth").val();
				var jobNumber = $("#jobNumber").html();
				$.ajax({
	                url: "../check/check_infos!getAllTimesByDay.action",
	                data:{
	                	day : day,
	                	yearMonth : yearMonth,
	                	jobNumber : jobNumber
	                },
	                success: function (text) {
	                	$("#allTime").text(text);
	                }
                })
	        },function(){  
	            $("#allTime").text("");
	        }) 
		})
       	        
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