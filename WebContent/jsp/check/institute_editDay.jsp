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
<title>编辑白班排班信息</title>
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
        <input class="mini-hidden" name="id" id="id" value="${ckYearDay.id}" style="display:none;"/>
        <input class="mini-hidden" name="instituteId" id="instituteId" value="${instituteId}" style="display:none;"/>
        <input type="hidden" name="backupYear" id="backupYear" value="${ckYearDay.yearCalendar}"/>
        <input class="mini-hidden" name="totalDays" id="totalDays" value="${ckYearDay.totalDays}" style="display:none;"/>
        <input type="hidden" name="existFlag" id="existFlag" value="${existFlag }"/>
        <div>
        	<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="94%" style="margin:auto;">
        		<tr>
            		<td class="list_right_box" width="20%" style="BACKGROUND: #f6f6f6;">年份：</td>
                    <td class="list_right_box" width="30%">
                    	<input name="yearCalendar" id="yearCalendar" class="mini-textbox" enabled="false"
							width="200px" value="${ckYearDay.yearCalendar}" />
                    </td>
                    <td class="list_right_box" width="20%" style="BACKGROUND: #f6f6f6;">月份：</td>
                    <td class="list_right_box" width="30%">
						<input field="monthCalendar" name="monthCalendar" id="monthCalendar" width="200px" value="${ckYearDay.monthCalendar}" 
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..." required="true"
							url="../check/institute!getMonths.action?existFlag=${existFlag }"/>
                    </td>
            	</tr>
        	</table>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="94%" style="margin:auto;padding-top:20px;">
                <tr>
                    <td class="list_right_box" width="7%" style="BACKGROUND: #f6f6f6;">1：</td>
                    <td class="list_right_box" width="13%">
                        <input field="day1" name="day1" id="day1" width="70px" value="${ckYearDay.day1}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" width="7%" style="BACKGROUND: #f6f6f6;">2：</td>
                    <td class="list_right_box" width="13%">
                        <input field="day2" name="day2" id="day2" width="70px" value="${ckYearDay.day2}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" width="7%" style="BACKGROUND: #f6f6f6;">3：</td>
                    <td class="list_right_box" width="13%">
                        <input field="day3" name="day3" id="day3" width="70px" value="${ckYearDay.day3}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" width="7%" style="BACKGROUND: #f6f6f6;">4：</td>
                    <td class="list_right_box" width="13%">
                        <input field="day4" name="day4" id="day4" width="70px" value="${ckYearDay.day4}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" width="7%" style="BACKGROUND: #f6f6f6;">5：</td>
                    <td class="list_right_box" width="13%">
                        <input field="day5" name="day5" id="day5" width="70px" value="${ckYearDay.day5}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                </tr>
                <tr id="dayTr10">
                	<td class="list_right_box" style="BACKGROUND: #f6f6f6;">6：</td>
                    <td class="list_right_box">
                        <input field="day6" name="day6" id="day6" width="70px" value="${ckYearDay.day6}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">7：</td>
                    <td class="list_right_box">
                        <input field="day7" name="day7" id="day7" width="70px" value="${ckYearDay.day7}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">8：</td>
                    <td class="list_right_box">
                        <input field="day8" name="day8" id="day8" width="70px" value="${ckYearDay.day8}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">9：</td>
                    <td class="list_right_box">
                        <input field="day9" name="day9" id="day9" width="70px" value="${ckYearDay.day9}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">10：</td>
                    <td class="list_right_box">
                        <input field="day10" name="day10" id="day10" width="70px" value="${ckYearDay.day10}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                </tr>
                <tr id="dayTr15">
                	<td class="list_right_box" style="BACKGROUND: #f6f6f6;">11：</td>
                    <td class="list_right_box">
                        <input field="day11" name="day11" id="day11" width="70px" value="${ckYearDay.day11}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">12：</td>
                    <td class="list_right_box">
                        <input field="day12" name="day12" id="day12" width="70px" value="${ckYearDay.day12}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">13：</td>
                    <td class="list_right_box">
                        <input field="day13" name="day13" id="day13" width="70px" value="${ckYearDay.day13}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">14：</td>
                    <td class="list_right_box">
                        <input field="day14" name="day14" id="day14" width="70px" value="${ckYearDay.day14}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">15：</td>
                    <td class="list_right_box">
                        <input field="day15" name="day15" id="day15" width="70px" value="${ckYearDay.day15}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                </tr>
                <tr id="dayTr20">
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">16：</td>
                    <td class="list_right_box">
                        <input field="day16" name="day16" id="day16" width="70px" value="${ckYearDay.day16}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">17：</td>
                    <td class="list_right_box">
                        <input field="day17" name="day17" id="day17" width="70px" value="${ckYearDay.day17}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">18：</td>
                    <td class="list_right_box">
                        <input field="day18" name="day18" id="day18" width="70px" value="${ckYearDay.day18}" required="true" 
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">19：</td>
                    <td class="list_right_box">
                        <input field="day19" name="day19" id="day19" width="70px" value="${ckYearDay.day19}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">20：</td>
                    <td class="list_right_box">
                        <input field="day20" name="day20" id="day20" width="70px" value="${ckYearDay.day20}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                </tr>
                <tr id="dayTr25">
                	<td class="list_right_box" style="BACKGROUND: #f6f6f6;">21：</td>
                    <td class="list_right_box">
                        <input field="day21" name="day21" id="day21" width="70px" value="${ckYearDay.day21}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">22：</td>
                    <td class="list_right_box">
                        <input field="day22" name="day22" id="day22" width="70px" value="${ckYearDay.day22}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">23：</td>
                    <td class="list_right_box">
                        <input field="day23" name="day23" id="day23" width="70px" value="${ckYearDay.day23}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">24：</td>
                    <td class="list_right_box">
                        <input field="day24" name="day24" id="day24" width="70px" value="${ckYearDay.day24}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">25：</td>
                    <td class="list_right_box">
                        <input field="day25" name="day25" id="day25" width="70px" value="${ckYearDay.day25}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                </tr>
                <tr id="dayTr30">
                	<td class="list_right_box" style="BACKGROUND: #f6f6f6;">26：</td>
                    <td class="list_right_box">
                        <input field="day26" name="day26" id="day26" width="70px" value="${ckYearDay.day26}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">27：</td>
                    <td class="list_right_box">
                        <input field="day27" name="day27" id="day27" width="70px" value="${ckYearDay.day27}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">28：</td>
                    <td class="list_right_box">
                        <input field="day28" name="day28" id="day28" width="70px" value="${ckYearDay.day28}" required="true"
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td id="dayTd29" class="list_right_box" style="BACKGROUND: #f6f6f6;">29：</td>
                    <td id="dayDiv29" class="list_right_box">
                        <input field="day29" name="day29" id="day29" width="70px" value="${ckYearDay.day29}" 
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                    <td id="dayTd30" class="list_right_box" style="BACKGROUND: #f6f6f6;">30：</td>
                    <td id="dayDiv30" class="list_right_box">
                        <input field="day30" name="day30" id="day30" width="70px" value="${ckYearDay.day30}" 
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                </tr>
                <tr id="dayTr35">
                    <td class="list_right_box" style="BACKGROUND: #f6f6f6;">31：</td>
                    <td class="list_right_box">
                        <input field="day31" name="day31" id="day31" width="70px" value="${ckYearDay.day31}" 
							class="mini-combobox" textField="text" valueField="text" emptyText="请选择..."  
							url="../check/content!getContentList.action"/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        </div>
    </form>
    
    <script type="text/javascript">
    	//月份下拉锁定
    	var existFlag = $("#existFlag").val();
    	if (existFlag != ""){
    		$("#monthCalendar").attr("enabled", false);
    	}
    	//日期锁定
    	var totalDays = $("#totalDays").val();
    	if (totalDays == "28"){
    		$("#dayTr35").hide();//隐藏table
    		$("#dayTd29").hide();
    		$("#dayDiv29").hide();
    		$("#dayTd30").hide();
    		$("#dayDiv30").hide();
    	} else if (totalDays == "29"){
    		$("#dayTr35").hide();//隐藏table
    		$("#dayTd30").hide();
    		$("#dayDiv30").hide();
    	} else if (totalDays == "30"){
    		$("#dayTr35").hide();//隐藏table
    	}
    	
        mini.parse();
		var id = mini.get("id").getValue();
		
		//监听月份的值
		var monthCalendar = mini.get("monthCalendar");
	    monthCalendar.on("valuechanged", function (e) {
	    	var year = mini.get("yearCalendar").getValue();
	    	var monthVal = this.getValue();
	    	var date=new Date;
	    	var month=date.getMonth()+1;
 			month =(month<10 ? "0"+month:month);
	        if(this.getValue() == "1" && month != 1){
	        	mini.get("yearCalendar").setValue(parseInt(year)+1);
	        } 
	        if(this.getValue() == month){
	        	mini.get("yearCalendar").setValue($("#backupYear").val());
	        }
	        //重新去后台取值
	        $.ajax({
                url: "../check/institute!getChangeDays.action",
                data:{
                	yearVal : mini.get("yearCalendar").getValue(),
                	monthVal : monthVal
                },
                success: function (text) {
                	var info = JSON.parse(text);
                	if(info.totalDays == 28){
                		$("#dayTr35").hide();//隐藏table
			    		$("#dayTd29").hide();
			    		$("#dayDiv29").hide();
			    		$("#dayTd30").hide();
			    		$("#dayDiv30").hide();
                	} else if (info.totalDays == 29){
                		$("#dayTr35").hide();//隐藏table
			    		$("#dayTd29").show();
			    		$("#dayDiv29").show();
			    		$("#dayTd30").hide();
			    		$("#dayDiv30").hide();
                	} else if (info.totalDays == 30){
                		$("#dayTr35").hide();//隐藏table
			    		$("#dayTd29").show();
			    		$("#dayDiv29").show();
			    		$("#dayTd30").show();
			    		$("#dayDiv30").show();
                	} else if (info.totalDays == 31){
                		$("#dayTr35").show();//展示table
			    		$("#dayTd29").show();
			    		$("#dayDiv29").show();
			    		$("#dayTd30").show();
			    		$("#dayDiv30").show();
                	}
                	mini.get("id").setValue(info.id);
                	mini.get("totalDays").setValue(info.totalDays);
                	mini.get("day1").setValue(info.day1);
                	mini.get("day2").setValue(info.day2);
                	mini.get("day3").setValue(info.day3);
                	mini.get("day4").setValue(info.day4);
                	mini.get("day5").setValue(info.day5);
                	mini.get("day6").setValue(info.day6);
                	mini.get("day7").setValue(info.day7);
                	mini.get("day8").setValue(info.day8);
                	mini.get("day9").setValue(info.day9);
                	mini.get("day10").setValue(info.day10);
                	mini.get("day11").setValue(info.day11);
                	mini.get("day12").setValue(info.day12);
                	mini.get("day13").setValue(info.day13);
                	mini.get("day14").setValue(info.day14);
                	mini.get("day15").setValue(info.day15);
                	mini.get("day16").setValue(info.day16);
                	mini.get("day17").setValue(info.day17);
                	mini.get("day18").setValue(info.day18);
                	mini.get("day19").setValue(info.day19);
                	mini.get("day20").setValue(info.day20);
                	mini.get("day21").setValue(info.day21);
                	mini.get("day22").setValue(info.day22);
                	mini.get("day23").setValue(info.day23);
                	mini.get("day24").setValue(info.day24);
                	mini.get("day25").setValue(info.day25);
                	mini.get("day26").setValue(info.day26);
                	mini.get("day27").setValue(info.day27);
                	mini.get("day28").setValue(info.day28);
                	mini.get("day29").setValue(info.day29);
                	mini.get("day30").setValue(info.day30);
                	mini.get("day31").setValue(info.day31);
                },
                error:function(text){
                	mini.get("id").setValue("");
                	mini.get("totalDays").setValue("");
                	mini.get("day1").setValue("");
                	mini.get("day2").setValue("");
                	mini.get("day3").setValue("");
                	mini.get("day4").setValue("");
                	mini.get("day5").setValue("");
                	mini.get("day6").setValue("");
                	mini.get("day7").setValue("");
                	mini.get("day8").setValue("");
                	mini.get("day9").setValue("");
                	mini.get("day10").setValue("");
                	mini.get("day11").setValue("");
                	mini.get("day12").setValue("");
                	mini.get("day13").setValue("");
                	mini.get("day14").setValue("");
                	mini.get("day15").setValue("");
                	mini.get("day16").setValue("");
                	mini.get("day17").setValue("");
                	mini.get("day18").setValue("");
                	mini.get("day19").setValue("");
                	mini.get("day20").setValue("");
                	mini.get("day21").setValue("");
                	mini.get("day22").setValue("");
                	mini.get("day23").setValue("");
                	mini.get("day24").setValue("");
                	mini.get("day25").setValue("");
                	mini.get("day26").setValue("");
                	mini.get("day27").setValue("");
                	mini.get("day28").setValue("");
                	mini.get("day29").setValue("");
                	mini.get("day30").setValue("");
                	mini.get("day31").setValue("");
                }
             })
	    });
	    
        //保存
        function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            //判断白班班制名称是否已生成
            var instituteId = mini.get("instituteId").getValue();
            if (instituteId == ""){
            	mini.alert("请先创建 白班 班制名称");
            }
            //判断全年白班排班是否已生成
            var totalDays = mini.get("totalDays").getValue();
            if (totalDays == ""){
            	mini.alert("请先生成全年白班排班！");
            	return;
            }
            var data = form.getData();
            var json = mini.encode(data); 
            //strut令牌
            window.parent.loading();
            $.ajax({
                url: "../check/institute!saveOrUpdateDays.action",
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