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
    <title>分配总量</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>

    <style type="text/css">
        .link {text-decoration:none}
    </style>
</head>

<body>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    &nbsp;部门:
                    <input class="mini-textbox" id="deptName" style="width: 120px" onenter="onKeyEnter"/>
                    &nbsp;分配权限:
                    <input class="mini-combobox" style="width: 120px" id="fpqx" onvaluechanged="onValueChanged"
                        data="[{id: 1, text:'公司'},{id: 2, text:'基层'}]" onenter="onKeyEnter"/>
                    <a id="searchButton" class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                    <br>
                    <a class="mini-button" iconCls="icon-save" onclick="onOK()">保存</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <!-- <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20" showPager = "false"
            pagerSize="20" idField="id" allowResize="false" borderStyle="border-center:0px;border-right:0px;" multiSelect="true"
            allowCellEdit="true" allowCellSelect="true"allowHeaderWrap ="true"> -->
        <div id="grid1" class="mini-treegrid" style="width:100%;height:100%;" borderStyle="border-center:0px;border-right:0px;"
            showTreeIcon="false" treeColumn="taskname" idField="UID" 
            parentField="ParentTaskUID" resultAsTree="false" expandOnLoad="true"
            checkRecursive="false" showCheckBox="false" autoCheckParent="false"
            allowResize="false" allowCellEdit="true" allowCellSelect="true"allowHeaderWrap ="true">
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");
        var fpqx = mini.get("fpqx");
        fpqx.select(1);

        function onValueChanged(e) {
            searchS();
        }

        // 原合计
        var byjxldkhField = [];
        // 分配合计
        var actualField = [];
        // 其他合计
        var otherField = [];

        function searchS() {
            dynamicTable('deptName,fpqx','deptName,fpqx');
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
            var cd = searchData.length;//长度
            var fy = searchData.charAt(cd-1);
            if(fy==","){
                searchData = searchData.substring(0,cd-1);
            }
            searchData=searchData+"}";

            return searchData;
        }

        function dynamicTable(name,attr) {
            var ss = searchCondition(name,attr);
            window.parent.loading();
            $.ajax({
                url: "../salary/salary_amount!getAmountForPeriodListData.action",
                type: "POST",
                data : {
                    period : '${period}',
                    companyId : '${companyId}',
                    searchData : ss
                },
                success: function(text) {
                    window.parent.unmask();
                    var jsonData = mini.decode(text);
                    byjxldkhField.splice(0, byjxldkhField.length);
                    actualField.splice(0, actualField.length);
                    otherField.splice(0, otherField.length);
                    var arr = [];
                    arr.push({ type: "indexcolumn", headerAlign:"center", width: 40,header: "序列"});
                    arr.push({ field: "id", id:"id", name:"id", headerAlign:"center", width: 40});
                    arr.push({ field: "companyId", id:"companyId", name:"companyId", headerAlign:"center", width: 40, header: "单位ID"});
                    arr.push({ field:"company", name: "company",width:"150", headerAlign:"center", align:"center", header: "单位"});
                    arr.push({ field: "deptId", id:"deptId", name:"deptId", headerAlign:"center", width: 40, header: "部门ID"});
                    arr.push({ field:"deptName", name: "taskname", width:"150", headerAlign:"center", align:"left", header: "部门"});
                    arr.push({ field:"peopleCount", width:"60", headerAlign:"center", align:"center", header: "人数"});
                    arr.push({ field:"lastMonthAmount", width:"80", headerAlign:"center", align:"center", header: "上月结余"});

                    var second = [];
                    var actualSecond = [];
                    var secondAmount = mini.decode(jsonData.secondAmount);
                    // 数值数组
                    var secondValue = new Array(secondAmount.length);
                    for (var i=0; i< secondAmount.length;i++) {
                        var scolumn = { field:"second"+secondAmount[i].IBF_ID, width:"80", headerAlign:"center", align:"center", header: secondAmount[i].IBF_NAME};
                        second.push(scolumn);
                        var acolumn = { field:"actual"+secondAmount[i].IBF_ID, width:"80", headerAlign:"center", align:"center", header: secondAmount[i].IBF_NAME};
                        actualSecond.push(acolumn);
                        secondValue["second"+secondAmount[i].IBF_ID] = 0;
                        secondValue["actual"+secondAmount[i].IBF_ID] = 0;
                        secondValue["other"+secondAmount[i].IBF_ID] = 0;
                        byjxldkhField.push({item: "second"+secondAmount[i].IBF_ID});
                        actualField.push({item: "actual"+secondAmount[i].IBF_ID});
                    }
                    second.push({field:"secondTotal", width:"80", headerAlign:"center", align:"center", header: "合计"});
                    arr.push({ headerAlign:"center", align:"center", header: "二次分配总量", columns : second});
                    arr.push({ field:"byjxldkh", width:"80", headerAlign:"center", align:"center", header: "本月绩效联动考核*",vtype: "float;required:true",
                                   editor: { type: "textbox", maxLength: 20}});
                    arr.push({ headerAlign:"center", align:"center", header: "其他奖励",
                        columns : [
                               { field:"specialReward", width:"80", headerAlign:"center", align:"center", header: "特殊激励*",vtype: "float;required:true",
                                   editor: { type: "textbox", maxLength: 20}},
                               { field:"otherReward", width:"80", headerAlign:"center", align:"center", header: "其他1*",vtype: "float;required:true",
                                   editor: { type: "textbox", maxLength: 20}},
                               { field:"otherReward2", width:"80", headerAlign:"center", align:"center", header: "其他2*",vtype: "float;required:true",
                                   editor: { type: "textbox", maxLength: 20}},
                               { field:"otherReward3", width:"80", headerAlign:"center", align:"center", header: "其他3*",vtype: "float;required:true",
                                   editor: { type: "textbox", maxLength: 20}},
                               { field:"rewardTotal", width:"80", headerAlign:"center", align:"center", header: "合计"}
                        ]});
                    arr.push({ field:"bykfpzl", width:"80", headerAlign:"center", align:"center", header: "本月可分配总量"});
                    actualSecond.push({field:"actualSecondTotal", width:"80", headerAlign:"center", align:"center", header: "合计"});
                    arr.push({ headerAlign:"center", align:"center", header: "实际工资分配结果", columns : actualSecond});
                    arr.push({ field:"curMonthAmount", width:"80", headerAlign:"center", align:"center", header: "本月结余"});
                    var otherSecond = [];
                    var otherSecondAmount = mini.decode(jsonData.otherSecondAmount);
                    for (var i=0; i< otherSecondAmount.length;i++) {
                        var column = { field:"other"+otherSecondAmount[i].IBF_ID, width:"80", headerAlign:"center", align:"center", header: otherSecondAmount[i].IBF_NAME+"*",
                                         vtype: "float;required:true", editor: { type: "textbox", maxLength: 20}};
                        otherSecond.push(column);
                        otherField.push({item: "other"+otherSecondAmount[i].IBF_ID});
                    }
                    otherSecond.push({field:"otherSecondTotal", width:"80", headerAlign:"center", align:"center", header: "合计"});
                    arr.push({ headerAlign:"center", align:"center", header: "其他分配总量", columns : otherSecond});
                    arr.push({ field:"note", width:"150", headerAlign:"center", align:"center", header: "备注",
                                  editor: { type: "textbox", maxLength: 1000}});

                    var dd = jsonData.data;
                    var table = [];
                    for (var i=0; i< jsonData.total;i++) {
                        var row = dd[i];
                        var column = {id: row.id, companyId: row.companyId, company: row.company, deptId: row.deptId, deptName: row.deptName,
                                peopleCount: row.peopleCount, lastMonthAmount: row.lastMonthAmount,
                                byjxldkh: row.byjxldkh, specialReward: row.specialReward,
                                otherReward: row.otherReward, otherReward2: row.otherReward2, otherReward3: row.otherReward3, rewardTotal: row.rewardTotal,
                                bykfpzl: row.bykfpzl, curMonthAmount: row.curMonthAmount, note: row.note, otherId: row.otherId, UID: row.UID,
                                ParentTaskUID: row.ParentTaskUID};
                        var objs = eval(column);
                        var newObjs = $.extend({}, objs, secondValue);
                        if (row.itemId != null) {
                            var itemId = row.itemId.split(",");
                            var staticValue = row.staticValue.split(",");
                            var actualItemid = row.actualItemid.split(",");
                            var needAmount = row.needAmount.split(",");
                            var otherNeedAmount = row.otherNeedAmount.split(",");
                            for (var j=0; j< itemId.length;j++) {
                                newObjs["second"+itemId[j]] = staticValue[j];
                                newObjs["actual"+actualItemid[j]] = needAmount[j];
                                newObjs["other"+actualItemid[j]] = otherNeedAmount[j];
                            }
                        }
                        table.push(newObjs);
                    }
                    grid1.loadList(table);
                    //grid1.setTotalCount(jsonData.total);
                    grid1.set({columns : arr, ondrawcell: "onDrawCell"});
                    grid1.hideColumn("id");
                    grid1.hideColumn("companyId");
                    grid1.hideColumn("company");
                    grid1.hideColumn("deptId");
                    grid1.frozenColumns(0, 7);
                }
            });
        }

        /**
         * 单元格绘制
         * zhanghj
         **/
        function onDrawCell(e) {
            var field = e.field;
            var record = e.record;
            // 求出二次分配总量合计
            if (field == "secondTotal") {
                var formula = 0;
                for (var i=0; i< byjxldkhField.length; i++) {
                    var s = byjxldkhField[i].item;
                    var d = record[s];
                    if (d != null) {
                        formula = parseFloat(formula) + parseFloat(d);
                    }
                }
                record.secondTotal = formula;
                e.cellHtml = formula;
            }

            // 其他奖励合计
            if (field == "rewardTotal") {
                var specialReward = record.specialReward, otherReward = record.otherReward, otherReward2 = record.otherReward2, otherReward3 = record.otherReward3;
                var rewardTotal = parseFloat(specialReward) + parseFloat(otherReward) + parseFloat(otherReward2) + parseFloat(otherReward3);
                record.rewardTotal = rewardTotal;
                e.cellHtml = rewardTotal;
            }

            // 本月可分配总量
            if (field == "bykfpzl") {
                var lastMonthAmount = record.lastMonthAmount, secondTotal = record.secondTotal, byjxldkh = record.byjxldkh, rewardTotal = record.rewardTotal;
                var bykfpzl = parseFloat(lastMonthAmount) + parseFloat(secondTotal) + parseFloat(byjxldkh) + parseFloat(rewardTotal);
                record.bykfpzl = bykfpzl;
                e.cellHtml = bykfpzl;
            }

            // 实际二次分配总量
            if (field == "actualSecondTotal") {
                var formula = 0;
                for (var i=0; i< actualField.length; i++) {
                    var s = actualField[i].item;
                    var d = record[s];
                    if (d != null) {
                        formula = parseFloat(formula) + parseFloat(d);
                    }
                }
                record.actualSecondTotal = formula;
                e.cellHtml = formula;
            }

            // 本月结余
            if (field == "curMonthAmount") {
                var bykfpzl = record.bykfpzl, actualSecondTotal = record.actualSecondTotal;
                var curMonthAmount = parseFloat(bykfpzl) - parseFloat(actualSecondTotal);
                record.curMonthAmount = curMonthAmount;
                e.cellHtml = curMonthAmount;
            }

            // 其他分配总量
            if (field == "otherSecondTotal") {
                var formula = 0;
                for (var i=0; i< otherField.length; i++) {
                    var s = otherField[i].item;
                    var d = record[s];
                    if (d != null) {
                        formula = parseFloat(formula) + parseFloat(d);
                    }
                }
                record.otherSecondTotal = formula;
                e.cellHtml = formula;
            }
        }

        function onOK() {
            var gridjson="";
            if(grid1){
                // 验证
                grid1.validate();
                if (grid1.isValid() == false) {
                    gridError(grid1);
                    return;
                }
                var data = grid1.getData();
                if(data.length<1){// 空行也算length
                    mini.alert("详细信息为空，保存失败！");
                    return;
                }
                gridjson= mini.encode(data);
            }

            window.parent.loading();
            $.ajax({
                url : "../salary/salary_amount!saveOrUpdate.action",
                type : "POST",
                data : {
                    period: '${period}',
                    fpqx: fpqx.getValue(),
                    griddata : gridjson
                },
                success : function(text) {
                    window.parent.unmask();
                    if (text == "success") {
                        mini.showMessageBox({
                            title: "提醒",
                            width: 250,
                            iconCls: "mini-messagebox-warning",
                            buttons: ["ok"],
                            message: "保存成功",
                            callback: function (action) {
                                searchS();
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

        /*******************************************************************************
         * grid 错误提示
         ******************************************************************************/
        function gridError(grid){
            var error = grid.getCellErrors()[0];
            var head = error.column.header.replace(/[\r\n]/g,"").replace(/[ ]/g,"");
            var errorText = error.errorText;
            mini.showMessageBox({
                 title: "提醒",
                 iconCls: "mini-messagebox-warning",
                 buttons: ["ok"],
                 message: head+":"+errorText+",请修改后重新操作!",
                 callback: function (action) {
                      grid.setSelected(error.record);
                      grid.beginEditCell(error.record, error.column);
                      return;
                   }
                 });
        }

        /**点击回车查询
         * JSP:onkeydown="onKeyEnter"
         * 查询按钮id：searchButton
         * 火狐下不识别 event is not defined
         * */
        function onKeyEnter(){
            if((event.keyCode || event.which) == 13){
                $("#searchButton").click();
            }
        }

        // 不通过(作废)
        function unCheck() {
            mini.confirm("确定要不通过？", "提醒", function(action) {
                if (action == "ok") {
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_amount!updateUnCheck.action",
                        type : "POST",
                        data : {
                            period: '${period}',
                            companyId : '${companyId}'
                        },
                        success : function(text) {
                            window.parent.unmask();
                            if (text == "success") {
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "操作成功",
                                    callback: function (action) {
                                        searchS();
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
            });
        }

    </script>
</body>
</html>