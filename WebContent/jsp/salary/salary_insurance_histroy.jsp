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
    <title>员工缴费历史</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1">
        <input class="mini-hidden" id="id" name="id" value="${mapInfo.id}"/>
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width:10%">工号：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" value="${mapInfo.jobNumber}"/>
                </td>
                <td class="list_left_box1" valign="top" style="width:10%">姓名：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" value="${mapInfo.name}"/>
                </td>
                <td class="list_left_box1" valign="top" style="width:10%">公司：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" style="width:90%" value="${mapInfo.company}"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">部门：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" value="${mapInfo.detpName}"/>
                </td>
                <td class="list_left_box1" valign="top">岗位：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" value="${mapInfo.post}"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="padding:0px;">
        <table style="width:100%;">
            <tr>
                </td>
                <td style="width:30%;text-align: right;">
                    &nbsp;年份：<input class="mini-spinner" id="year" maxValue="2100" onvaluechanged= "onValueChanged"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
            allowAlternating="true" idField="id" allowResize="false">
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">关闭</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");
        var year = mini.get("year");
        year.setValue(new Date().getFullYear());

        $(function() {
        	dynamicTable();
        });

        function dynamicTable() {
            $.ajax({
                url: "../salary/salary_payment!getStaffPaymentHistory.action",
                type: "POST",
                data : {
                    staffId : mini.get("id").getValue(),
                    year : year.getValue()
                },
                success: function(text) {
                    var jsonData = mini.decode(text);
                    grid1.clearRows();
                    var arr = [];
                    arr.push({ type: "indexcolumn", headerAlign:"center", width: 40,header: "序列"});
                    arr.push({ field: "id", id:"id", name:"id", headerAlign:"center", width: 40});
                    arr.push({ field: "staffId", id:"staffId", name:"staffId", headerAlign:"center", width: 40, header: "员工id"});
                    arr.push({ type:"comboboxcolumn", field:"isDaiJiao", width:"80", headerAlign:"center", align:"center", header: "代缴", editor: { type: "combobox", data: "[{id:0, text:'否'},{id:1, text:'是'}]"}});
                    arr.push({ field:"jfDate", width:"80", headerAlign:"center", align:"center", header: "缴费年月"});
                    arr.push({ type:"comboboxcolumn", field:"isPayBack", width:"74", headerAlign:"center", align:"center", header: "是否补缴", editor: { type: "combobox", data: "[{id:0, text:'否'},{id:1, text:'是'}]"}});
                    arr.push({ headerAlign:"center", align:"center", header: "补缴",
                                  columns : [
                                         { field:"payBackYear", width:"60", headerAlign:"center", align:"center", header: "年",
                                             editor: { type: "spinner", minValue: 1990, maxValue: 2100}},
                                         { field:"payBackMonth", width:"44", headerAlign:"center", align:"center", header: "月",
                                             editor: { type: "spinner", minValue: 1, maxValue: 12}}
                                  ]});
                    arr.push({ field:"pjgz", width:"80", headerAlign:"center", align:"center", header: "平均工资"});

                    // 循环便利所有的福利项目
                    var fulis = mini.decode(jsonData.fulis);
                    for (var i=0; i< fulis.length;i++) {
                        arr.push({ headerAlign:"center", header: fulis[i].INSUR_NAME, fuiliId: fulis[i].ID,
                                  columns : [
                                         { field:"AREA"+fulis[i].ID, width:"80", headerAlign:"center", align:"center", header: "缴费地域"},
                                         { field:"COMD"+fulis[i].ID, width:"140", headerAlign:"center", align:"center", header: "缴费单位"},
                                         { field:"COMB"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "公司缴费基数"},
                                         { field:"PERB"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "员工缴费基数"},
                                         { field:"COMJ"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "公司缴费"},
                                         { field:"PERJ"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "员工缴费"}
                                  ]});
                    }
                    grid1.set({columns : arr});
                    grid1.hideColumn("id");
                    grid1.hideColumn("staffId");
                    grid1.frozenColumns(0, 8);
                    grid1.setTotalCount(jsonData.total);
                    var dd = jsonData.data;
                    var table = [];
                    for (var i=0; i< jsonData.total;i++) {
                        var row = dd[i];
                        var column = {id: row.id, staffId: row.staffId, jobNumber: row.jobNumber, name: row.name, company: row.company,
                                detpName: row.deptName, isDaiJiao: row.isDaiJiao, jfDate: row.jfDate, pjgz: 0, isPayBack: row.isPayBack,
                                payBackYear: row.payBackYear, payBackMonth: row.payBackMonth};
                        var objs = eval(column);
                        if (row.flId != null) {
                            var flId = row.flId.split(",");
                            var jSon = row.jfArea.split(",");
                            var jfCompany = row.jfCompany.split(",");
                            var jsbaseCompany = null;
                            if (row.baseCompany == null) {
                                jsbaseCompany = new Array(flId.length);
                            } else jsbaseCompany = row.baseCompany.split(",");
                            var jsbasePersonal = null;
                            if (row.basePersonal == null) {
                                jsbasePersonal = new Array(flId.length);
                            } else jsbasePersonal = row.basePersonal.split(",");
                            var jssCompany = null;
                            if (row.sCompany == null) {
                                jssCompany = new Array(flId.length);
                            } else jssCompany = row.sCompany.split(",");
                            var jssPersonal = null;
                            if (row.sPersonal == null) {
                                jssPersonal = new Array(flId.length);
                            } else jssPersonal = row.sPersonal.split(",");
                            for (var j=0; j< flId.length;j++) {
                                var aField = "AREA"+flId[j];
                                var bField = "COMD"+flId[j];
                                var cField = "COMB"+flId[j];
                                var dField = "PERB"+flId[j];
                                var eField = "COMJ"+flId[j];
                                var fField = "PERJ"+flId[j];
                                objs[aField] = jSon[j];
                                objs[bField] = jfCompany[j];
                                objs[cField] = jsbaseCompany[j];
                                objs[dField] = jsbasePersonal[j];
                                objs[eField] = jssCompany[j];
                                objs[fField] = jssPersonal[j];
                            }
                        }
                        table.push(column);
                    }
                    grid1.addRows(table);
                    grid1.accept();
                }
            });
        }

        function onValueChanged(e) {
            dynamicTable();
        }

    </script>
</body>
</html>