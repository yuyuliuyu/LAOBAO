<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path;
    pageContext.setAttribute("base", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>薪资区间增加</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1">
        <input class="mini-hidden" id="id" name="id" value="${period.id}"/>
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width:18%">公司：</td>
                <td class="list_right_box">
                     <input class="mini-combobox asLabel" name="depId" readOnly="false" url ="../salary/salary_manage!getBranches.action"
                        textField="fzx" value="${depId}" style="width:90%"/>
                </td>
                <td class="list_left_box1" valign="top" style="width:18%">*年度：</td>
                <td class="list_right_box">
                    <input class="mini-spinner" id="year" name="year" required="true"style="width:90%" value="${period.year}" minValue="0" maxValue="9999" format="#"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*发薪频率：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" value="${period.payPerid}" name="payPerid" style="width:90%" required="true"
                        data="[{id:0, text:'周'},{id:1, text:'双周'},{id:2, text:'半月'},{id:3, text:'月'}]" onvaluechanged="onValueChanged"/>
                </td>
                <td class="list_left_box1" valign="top">*标准日工作小时：</td>
                <td class="list_right_box">
                    <input class="mini-spinner" id="dayHour" name="dayHour" style="width:90%" maxValue="24" format="#" required="true"
                        value="${period.dayHour}"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*标准计薪天数：</td>
                <td class="list_right_box">
                    <input class="mini-spinner" id="day" name="day" style="width:90%" value="${period.day}" format="n2" minValue="0" maxValue="99.99" required="true"/>
                </td>
                <td class="list_left_box1" valign="top"></td>
                <td class="list_right_box">
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">描述：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" id="note" name="note" style="width:90%;height: 60px" value="${period.note}"
                        maxLength="1000"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <a class="mini-button" iconCls="icon-add" onclick="addRow()">增加</a>
                    <a class="mini-button" iconCls="icon-remove" onclick="removeRow()">删除</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"multiSelect="true"
            url = "../salary/salary_manage!getPeriodListData.action?id=${period.id}"
            allowCellEdit="true"  allowCellSelect="true" idField="id" allowResize="false" showPager="false"
            oncellvalidation="onCellValidation">
            <div property="columns">
                <div type="checkcolumn" width="30"></div>
                <div type="indexcolumn" headerAlign="center" width="30">序列</div>
                <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                <div field="name" width="100" headerAlign="center" align="center"vType="required;">薪酬期间名称
                    <input property="editor" class="mini-textbox" style="width:100%"/>
                </div>
                <div field="startDate" width="60" headerAlign="center" align="center" vType="required;" renderer="ondayRenderer">开始日期
                    <input property="editor" class="mini-datepicker" style="width:100%"format="yyyy年MM月dd日" />
                </div>
                <div field="endDate" width="60" headerAlign="center" align="center" vType="required;" renderer="ondayRenderer">结束日期
                    <input property="editor" class="mini-datepicker" style="width:100%" format="yyyy年MM月dd日"/>
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="getBigData()" style="margin-right:20px;" iconCls="icon-save">生成薪酬期间</a>
        <a class="mini-button" onclick="onOk('salary','salary_manage','',false)" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.load();
        var year = '${period.year}';
        if (year == "") {
            var year = mini.get("year");
            var d = new Date();
            year.setValue(d.getFullYear());
        }

        function edit() {
            var row = grid.getSelected();
            if(row){
                 var id = row.id;
                 mini.open({
                        url : "../salary/salary_rate!edit.action?id="+id,
                        title : "编辑税率",
                        width : 680,
                        height : 540,
                        onload : function() {
                        },
                        ondestroy : function(action) {
                            grid.reload();
                        }
                    });
            }else{
                mini.alert("请选择一条记录!");
                return;
            }
        }

        function onCellValidation(e) {
            var record = e.record, field = e.field;
            if (field == "startDate" || field == "endDate") {
                var startDate = record.startDate;
                var endDate = record.endDate;
                if (startDate != null && endDate != null) {
                    var dStart = new Date(startDate);
                    var dEnd = new Date(endDate);
                    if (dStart > dEnd) {
                        e.errorText = "开始日期应该早于结束日期";
                        e.isValid = false;
                    }
                }
            }
        }

        function getBigData() {
            onValueChanged(null);
            var year = mini.get("year");
            var vyear = year.getValue();
            var payPerid = mini.getbyName("payPerid");
            var vpayPerid = payPerid.getValue();
            if (year.getValue() == null) {
                mini.alert("请输入年度");
                return;
            }
            if (vpayPerid == "") {
                mini.alert("请选择发薪频率");
                return;
            }
            var p = 28;
            if ((vyear % 4 == 0 && vyear % 100 == 0) || vyear % 400 == 0) {
                p = 29;
            }
            var da = new Date(vyear,0,1);
            var n = 7-(da.getDay()+6)%7;
            da.setDate(da.getDate()+n);
            // 每年第一个周一的日期
            var firesMondy = da.getDate();
            var form = 0;
            var tips = "";
            var tips2 = "";
            var r = [];
            if (vpayPerid == 0) {
                form = 52;
                tips = "个星期";
                var first = new Date(vyear, 0, firesMondy);
                for(var i=0,l=form;i<l;i++) {
                    var next = new Date(first.getTime());
                    next.setDate(first.getDate() + 6);
                    var newRow = {name: vyear+"年第"+(i+1)+tips,
                        startDate : first.getFullYear() +"-" + (first.getMonth()+1) +"-"+ first.getDate(),
                        endDate: next.getFullYear() +"-" + (next.getMonth()+1) +"-"+ next.getDate()};
                    r.push(newRow);
                    first = next;
                    first.setDate(next.getDate() + 1);
                }
            } else if (vpayPerid == 1) {
                form = 26;
                tips = "个双周";
                var first = new Date(vyear, 0, firesMondy);
                for(var i=0,l=form;i<l;i++) {
                    var next = new Date(first.getTime());
                    next.setDate(first.getDate() + 13);
                    var newRow = {name: vyear+"年第"+(i+1)+tips,
                        startDate : first.getFullYear() +"-" + (first.getMonth()+1) +"-"+ first.getDate(),
                        endDate: next.getFullYear() +"-" + (next.getMonth()+1) +"-"+ next.getDate()};
                    r.push(newRow);
                    first = next;
                    first.setDate(next.getDate() + 1);
                }
            } else if (vpayPerid == 2) {
                form = 12;
                tips = "上半月";
                tips2 = "下半月";
                for(var i=0,l=form;i<l;i++) {
                    // 获取上一个月的天数
                    var first = new Date(vyear, i+1, 0);
                    var next = new Date(vyear, i, first.getDate());
                    var newRow = {name: vyear+"年"+(i+1)+"月"+tips,
                        startDate : next.getFullYear() +"-" + (next.getMonth()+1) +"-"+ 1,
                        endDate: next.getFullYear() +"-" + (next.getMonth()+1) +"-"+ 15};
                    r.push(newRow);
                    newRow = {name: vyear+"年"+(i+1)+"月"+tips2,
                            startDate : next.getFullYear() +"-" + (next.getMonth()+1) +"-"+ 16,
                            endDate: next.getFullYear() +"-" + (next.getMonth()+1) +"-"+ next.getDate()};
                    r.push(newRow);
                }
            } else if (vpayPerid == 3) {
                form = 12;
                tips = "月";
                for(var i=0,l=form;i<l;i++) {
                    // 获取上一个月的天数
                    var first = new Date(vyear, i+1, 0);
                    var next = new Date(vyear, i, first.getDate());
                    var newRow = {name: vyear+"年"+(i+1)+tips,
                        startDate : next.getFullYear() +"-" + (next.getMonth()+1) +"-"+ 1,
                        endDate: next.getFullYear() +"-" + (next.getMonth()+1) +"-"+ next.getDate()};
                    r.push(newRow);
                }
            }
            grid.addRows(r);
        }

        function onValueChanged(e) {
            var gData = grid.getData();
            for (var i=0; i <gData.length; i++) {
                grid.removeRow(gData[i], false);
            }
        }

        /**时间格式化
         * 形式:yyyy-MM-dd'
         * */
        function ondayRenderer(e) {
            var value = e.value;
            if(value != null){
                value = new Date(value);
                if (value) return mini.formatDate(value, 'yyyy年MM月dd日');
            }else{
                return "";
            }
        }

    </script>
</body>
</html>