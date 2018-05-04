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
    <title>每月缴费核对</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
    <script src="../template/plugin/biguploader/uploader.js" type="text/javascript"></script>
    <style type="text/css">
        .check_row {
            background:#CCFFCC;
        }
        .check_red_row {
            background:#FF0000;
        }
        .check_blue_row {
            background:#4876FF;
        }
    </style>
</head>

<body>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <input class="mini-hidden" id="companyId" value="${companyId}"/>
                    &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="onKeyEnter"/>
                    &nbsp;姓名：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                    &nbsp;缴费年月:
                    <input class="mini-textbox" id="jsYear" style="width: 60px" vtype="int"
                        onenter="onKeyEnter"/>年
                    <input class="mini-textbox" id="jsMonth" style="width: 40px" vtype="int"
                        onenter="onKeyEnter"/>月
                    &nbsp;保险项目:
                    <input class="mini-combobox" id="insuranceId" name="insuranceId" textField="name" value="${insuranceId}"
                        url="../basis/insurance_benefits!getAllItemsListData.action" onvaluechanged="onFuliValueChanged"/>
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                    <br><a class="mini-button" iconCls="icon-upload" onclick="importAccording()">导入社保缴费</a>
                    <a class="mini-button" iconCls="icon-upload" onclick="uploadDoc()">上传单据</a>
                    <a class="mini-button" iconCls="icon-download" onclick="downDoc()">下载单据</a>
                    <a class="mini-button" iconCls="icon-download" onclick="downModel()">下载缴费明细模板</a>
                </td>
            </tr>
        </table>
    </div>
    <form id="uploaders" method="post" enctype="multipart/form-data" encoding="multipart/form-data" target="appFrame" style="display: none;">
       <input class="mini-htmlfile" id="uploadFile" name="file" limitType="*" onfileselect= "onFileSelected"/>
    </form>
    <div class="mini-fit">
        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20" showPager = "false" virtualScroll="true"
            pagerSize="20" idField="id" allowResize="false" borderStyle="border-center:0px;border-right:0px;">
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");
        var pageIndex = 0;
        var pageSize = grid1.getPageSize();
        var insuranceId = mini.get("insuranceId");
        var uploadFile = mini.get("uploadFile");
        var date = new Date();
        var jsYear = mini.get("jsYear");
        var year = '${jsYear}';
        var month = '${jsMonth}';
        if (year == "" || year == "0") {
            year = date.getFullYear();
        }
        jsYear.setValue(year);
        var jsMonth = mini.get("jsMonth");
        if (month == "" || month == "0") {
            month = date.getMonth() + 1;
        }
        jsMonth.setValue(month);

        $(function() {
            searchS();
        });
        function searchS() {
            dynamicTable('companyId,jobNumber,name,jsYear,jsMonth,insuranceId','depId,jobNumber,name,jsYear,jsMonth,insuranceId');
        }

        function searchCondition(name,attr) {
            var names=name.split(",");
            var attrs=attr.split(",");
            var searchData="{";
            for(var i=0;i<names.length;i++){
                if(mini.get(names[i])){
                    var value=mini.get(names[i]).getValue();
                    if(value!=null&&value!=""&&typeof(value)!=undefined){
                        searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\",";
                    }
                }
            }
            searchData=searchData+"}";
    
            return searchData;
        }

        function dynamicTable(name,attr) {
            var ss = searchCondition(name,attr);
            $.ajax({
                url: "../salary/salary_insurance!getInsuranceStaffListData.action?pageIndex="+pageIndex+"&pageSize="+pageSize,
                type: "POST",
                data : {
                    searchData : ss
                },
                success: function(text) {
                    var jsonData = mini.decode(text);
                    var arr = [];
                    arr.push({ type: "checkcolumn", headerAlign:"center", width : 40});
                    arr.push({ type: "indexcolumn", headerAlign:"center", width: 40,header: "序列"});
                    arr.push({ field: "id", id:"id", name:"id", headerAlign:"center", width: 40});
                    arr.push({ field: "staffId", id:"staffId", name:"staffId", headerAlign:"center", width: 40, header: "员工id"});
                    arr.push({ field:"jobNumber", width:"80", headerAlign:"center", align:"center", header: "工号"});
                    arr.push({ field:"name", width:"100", headerAlign:"center", align:"center", header: "姓名"});
                    arr.push({ field:"idCard", width:"120", headerAlign:"center", align:"center", header: "身份证号"});
                    arr.push({ field:"company", width:"150", headerAlign:"center", align:"center", header: "公司"});
                    arr.push({ field:"detpName", width:"80", headerAlign:"center", align:"center", header: "部门"});
                    arr.push({ type:"comboboxcolumn", field:"isDaiJiao", readOnly:"true", width:"46", headerAlign:"center", align:"center", header: "代缴", editor: { type: "combobox", data: "[{id:0, text:'否'},{id:1, text:'是'}]"}});
                    arr.push({ field:"jfDate", width:"80", headerAlign:"center", align:"center", header: "缴费年月"});
                    arr.push({ type:"comboboxcolumn", field:"isPayBack", width:"74", headerAlign:"center", align:"center", header: "是否补缴", editor: { type: "combobox", data: "[{id:0, text:'否'},{id:1, text:'是'}]"}});
                    arr.push({ headerAlign:"center", align:"center", header: "补缴",
                                  columns : [
                                         { field:"payBackYear", width:"60", headerAlign:"center", align:"center", header: "年",
                                             editor: { type: "spinner", minValue: 1990, maxValue: 2100}},
                                         { field:"payBackMonth", width:"44", headerAlign:"center", align:"center", header: "月",
                                             editor: { type: "spinner", minValue: 1, maxValue: 12}}
                                  ]});

                    // 循环便利所有的福利项目
                    var fulis = mini.decode(jsonData.fulis);
                    for (var i=0; i< fulis.length;i++) {
                        arr.push({ headerAlign:"center", header: fulis[i].INSUR_NAME, fuiliId: fulis[i].ID,
                                  columns : [
                                         { field:"AREA"+fulis[i].ID, width:"80", headerAlign:"center", align:"center", header: "缴费地域"},
                                         { field:"COMD"+fulis[i].ID, width:"140", headerAlign:"center", align:"center", header: "缴费单位"},
                                         { field:"COMB"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "公司缴费基数"},
                                         { field:"PERB"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "员工缴费基数"},
                                         { field:"COMJ"+fulis[i].ID, name:"COMJ"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "公司缴费"},
                                         { field:"PERJ"+fulis[i].ID, name:"PERJ"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "员工缴费"}
                                  ]});
                    }
                    var dd = jsonData.data;
                    var table = [];
                    for (var i=0; i< jsonData.data.length;i++) {
                        var row = dd[i];
                        var column = {id: row.id, staffId: row.staffId, jobNumber: row.jobNumber, name: row.name, company: row.company,
                                detpName: row.deptName, isDaiJiao: row.isDaiJiao, jfDate: row.jfDate, pjgz: 0, isPayBack: row.isPayBack,
                                payBackYear: row.payBackYear, payBackMonth: row.payBackMonth, idCard: row.idCard, kind: row.kind, note: row.note};
                        var objs = eval(column);
                        if (row.flId != null) {
                            var flId = row.flId.split(",");
                            var jSon = row.jfArea == null? null : row.jfArea.split(",");
                            var jfCompany = row.jfCompany == null? null : row.jfCompany.split(",");
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
                                objs[aField] = jSon==null? "" :jSon[j];
                                objs[bField] = jfCompany==null? "": jfCompany[j];
                                objs[cField] = jsbaseCompany[j];
                                objs[dField] = jsbasePersonal[j];
                                objs[eField] = jssCompany[j];
                                objs[fField] = jssPersonal[j];
                            }
                        }
                        table.push(column);
                    }
                    grid1.setTotalCount(jsonData.total);
                    grid1.setPageIndex(pageIndex);
                    grid1.setPageSize(pageSize);
                    grid1.set({columns : arr, ondrawcell: "onCheckDrawCell"});
                    grid1.hideColumn("id");
                    grid1.hideColumn("staffId");
                    grid1.frozenColumns(0, 8);
                    grid1.setData(table);
                }
            });
        }

        grid1.on("beforeload", function (e) {
            pageIndex = e.data.pageIndex;
            pageSize = e.data.pageSize;
            $("#searchButton").click();
        });

        function onCheckDrawCell(e) {
            var record = e.record,field = e.field;
            // 核对数据
            if (record.kind == 2) {
                e.rowCls = "check_row";
            }
            // 遍历note
            var note = mini.decode(record.note);
            if (note.allLine != undefined) {
                if (note.allLine == "1") {
                    e.rowCls = "check_red_row";
                } else if (note.allLine == "2") {
                    e.rowCls = "check_blue_row";
                }
            }
            if (note[field]  != undefined) {
                e.cellStyle = "color:red;font-weight:bold;";
            }
        }

        // 导入社保缴费
        function importAccording() {
            if (jsYear.getValue() == "" || jsMonth.getValue() == "") {
                mini.alert("请筛选缴费年月");
                return;
            }
            // 获取表格列字段
            var columns = grid1.columns;
            var len = columns.length;
            var col = columns[2].field;
            for (var i = 3, l = columns.length; i < l; i++) {
                col += "," + columns[i].field;
            }
            var ids = "${companyId}";
            var condition = "depId=" + ids+"&effectiveYear="+jsYear.getValue() +
                "&effectiveMonth="+jsMonth.getValue()+"&insuranceId="+insuranceId.getValue()+"&column="+col;
            mini.open({
                url : "../salary/salary_insurance!audit.action?" + condition,
                title : "导入社保缴费",
                width : 480,
                height : 240,
                onload : function() {
                },
                ondestroy : function(action) {
                    $("#searchButton").click();
                }
            });
        }

        function onFuliValueChanged(e) {
            $("#searchButton").click();
        }

        // 上传单据
        function uploadDoc() {
            var url = "../salary/salary_insurance!uploadDoc.action?id=${id}&companyId=${companyId}&insuranceId=${insuranceId}";
            bigWebUploader.openMini("uploaders","uploadFile", url);
        }

        // 下载单据
        function downDoc() {
        	window.parent.loading();
        	$.ajax({
                url: "../salary/salary_insurance!downDoc.action",
                type:"POST",
                data: {
                    id: '${id}'
                },
                success: function(text) {
                    window.parent.unmask();
                    var data = mini.decode(text);
                    if (data.success == "success") {
                        window.location.href="../system/xiazai!download.action?name="+data.info;
                    } else {
                    	mini.alert(data.info);
                    }
                }
            });
        }

        // 缴费明细模板下载
        function downModel() {
        	window.location.href="../salary/jfmxmb.action";
        }

        function onFileSelected(e) {
            window.parent.loading();
            // 表单提交
            bigWebUploader.submit(callback);
        }

        function callback(data) {
            window.parent.unmask();
            if (data == "success") {
                mini.alert("上传成功");
            } else {
                mini.alert(data);
            }
        }
    </script>
</body>
</html>