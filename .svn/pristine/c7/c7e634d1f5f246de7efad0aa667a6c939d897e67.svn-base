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
    <title>审批薪酬核算数据</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
        <div size="16%" showCollapseButton="true">
            <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                <span style="padding-left:5px;">部门：</span>
            </div>
            <div class="mini-fit" style=" border-top:0;">
                <ul id="deptree" class="mini-tree" url="../salary/salary_approval!getCurComDepts.action?id=${id}" style="width:100%;height:100%;"
                    showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                </ul>
            </div>
        </div>
        <div showCollapseButton="true">
             <div class="mini-toolbar" style="padding:0px;border-top:0;">
                <table style="width:100%;">
                    <tr>
                        <td style="width:100%;">
                            <input class="mini-hidden" id="id" name = "id" value="${id}"/>
                            <input class="mini-hidden" id="deptId" name = "deptId"/>
                            &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="searchS"/>
                            &nbsp;姓名：<input class="mini-textbox" id="name" onenter="searchS"/>
                            <input class="mini-hidden" id="index"/>
                            &nbsp;薪资项目：<input class="mini-combobox" id="salaryItem" showNullItem="true" onenter="searchS" onvaluechanged="onSalaryItemChanged"/>
                            <input class="mini-combobox" id="itemX" style="width:60px" data="[{id:0, text:'大于'},{id:1, text:'小于'},{id:2, text:'等于'},{id:3, text:'大于等于'},{id:4, text:'小于等于'}]" onenter="searchS"/>
                            <input class="mini-textbox" id="itemValue" style="width:80px" onenter="searchS"/>
                            &nbsp;薪酬期间：<input class="mini-buttonedit" id="periods" name="periods" allowInput="false" onbuttonclick="onPeriodButtonEdit" />
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                            <br><a class="mini-button"  iconCls="icon-edit" onclick="editRows()">调整</a>
                            <a class="mini-button"  iconCls="icon-ok" onclick="commitCheck()">审批</a>
                            <a class="mini-button" iconCls="icon-download"
                                onclick="exportExcelData('salary', 'salary_approval','jobNumber,name,deptId,periods,index,salaryItem,itemX,itemValue','jobNumber,name,deptId,periods,index,salaryItem,itemX,itemValue')">导出</a>
                            <a class="mini-button"  iconCls="icon-find" onclick="heDui()">核对</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    allowAlternating="true" multiSelect="true" showPager = "false" allowHeaderWrap="true"
                    idField="id" allowResize="false" virtualScroll="true" >
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");
        var deptId = mini.get("deptId");
        var tree = mini.get("deptree");
        var salaryItem = mini.get("salaryItem");
        var isFirst = false;
        tree.on("nodeselect", function (e) {
            var node = tree.getSelectedNode();
            if (node) {
                var ids = "";
                ids = depAll(ids, tree, node);
                deptId.setValue(ids);
                $("#searchButton").click();
            } else {
                grid1.setData([]);
                grid1.setTotalCount(0);
            }
        });
        function depAll(ids,tree,node){
            if(node.flg==1){
                ids += node.id;
            }
            var list = tree.getAllChildNodes(node);
            if(list.length>0){
                if (ids == "") ids = list[0].id; 
                else ids += "," + list[0].id;
                for ( var i = 1; i < list.length; i++) {
                    if(list[i].flg==1){ 
                        ids += "," + list[i].id; 
                    }
                }
            } else {
                if (ids == "") ids = node.id; 
                else ids += "," + node.id;
            }
            return ids;
        }

        $(function() {
            searchS();
        });
        function searchS() {
            dynamicTable('jobNumber,name,deptId,periods,index,salaryItem,itemX,itemValue','jobNumber,name,deptId,periods,index,salaryItem,itemX,itemValue');
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
                url: "../salary/salary_approval!getCompareAssignationForStaffData.action",
                type: "POST",
                data : {
                    id: '${id}',
                    ids: "",
                    searchData : ss,
                    conditionData : '${conditionData}'
                },
                success: function(text) {
                    window.parent.unmask();
                    var gridData = mini.decode(text);
                    var jsonData = gridData.data;
                    var arr = [];
                    arr.push({ type: "checkcolumn", headerAlign:"center", width : 40});
                    arr.push({ type: "indexcolumn", headerAlign:"center", width: 40,header: "序列"});
                    arr.push({ field: "id", id:"id", name:"id", headerAlign:"center", width: 40});
                    arr.push({ field: "staffId", id:"staffId", name:"staffId", headerAlign:"center", header: "员工ID"});
                    arr.push({ field:"jobNumber", width:"80", headerAlign:"center", align:"center", header: "工号"});
                    arr.push({ field:"name", width:"100", headerAlign:"center", align:"center", header: "姓名"});
                    arr.push({ field:"companyName", width:"120", headerAlign:"center", align:"center", header: "公司"});
                    arr.push({ field:"deptName", width:"100", headerAlign:"center", align:"center", header: "部门"});
                    arr.push({ field:"gwName", width:"100", headerAlign:"center", align:"center", header: "岗位"});
                    arr.push({ field:"periodName", width:"100", headerAlign:"center", align:"center", header: "薪酬区间"});
                    arr.push({ type:"comboboxcolumn", field:"isAllMonth", width:"74", headerAlign:"center", align:"center", header: "参加平均工资计算",
                        editor: { type: "combobox", data: "[{id:0, text:'否'},{id:1, text:'是'}]"}});
    
                    // 循环便利所有的福利项目
                    if (jsonData.length > 0) {
                        var ge = [];
                        if (jsonData[0].itemsId != null) {
                            var fulis = jsonData[0].itemsId.split(",;");
                            var fulisName = jsonData[0].itemsName.split(",;");
                            var isNumber = jsonData[0].isNumber.split(",;");
                            for (var i=0; i< fulis.length;i++) {
                                arr.push({ field: fulis[i], width:"80", headerAlign:"center", align:"center", header: fulisName[i]});
                                ge.push({id: fulis[i], text: fulisName[i], number: isNumber[i], index: i+1});
                            }
                            if (!isFirst) {
                                salaryItem.setData(ge);
                                isFirst = true;
                            }
                        }
                    }

                    // 遍历数据
                    var table = [];
                    for (var i=0; i< jsonData.length;i++) {
                        var row = jsonData[i];
                        var column = {id: row.id, staffId: row.staffId, jobNumber: row.jobNumber, name: row.name, periodName: row.periodName, note: row.note,
                                companyName: row.companyName, deptName: row.deptName, gwName: row.gwName,isAllMonth: row.isAllMonth};
                        var objs = eval(column);
                        if (row.itemsId != null) {
                            var itemsId = row.itemsId.split(",;");
                            var charge = row.charge.split(",;");
                            for (var j=0; j< itemsId.length;j++) {
                                var aField = itemsId[j];
                                objs[aField] = charge[j];
                            }
                        }
                        table.push(column);
                    }
                    grid1.setData(table);
                    grid1.set({columns : arr, ondrawcell: "onCheckDrawCell"});
                    grid1.hideColumn("id");
                    grid1.hideColumn("staffId");
                    grid1.frozenColumns(0, 10);
                    grid1.setTotalCount(jsonData.length);
                }
            });
        }

        function onCheckDrawCell(e) {
            var record = e.record,field = e.field;
            // 遍历note
            var note = mini.decode(record.note);
            if (note[field]  != undefined) {
                e.cellStyle = note[field];
            }
        }

        // 调整
        function editRows() {
            var assignIds = '${id}';
            window.parent.loading();
            $.ajax({
                url : "../salary/salary_quota!isCheckAUth2.action",
                type : "post",
                data : {
                    ids : assignIds
                },
                success : function(text) {
                    window.parent.unmask();
                    var data = mini.decode(text);
                    var result = data.result;
                    var info = data.info;
                    var alertInfo = "";
                    if (result == "success") {
                    } else if (result == "fail") {
                        alertInfo = info;
                    } else {
                        if(result.length>0){
                        	var alertInfo = "";
                            if (result == "3") {
                                alertInfo = "您不是该分配过程的当前审批人，不能操作";
                            } else if (result == "4") {
                                alertInfo = "该分配过程的审批流程不存在";
                            } else if (result == "13") {
                                alertInfo = "该分配过程已经发放";
                            }
                        }
                    }
                    if (alertInfo != "") {
                        mini.showMessageBox({
                            title: "提醒",
                            iconCls: "mini-messagebox-warning",
                            buttons: ["ok"],
                            message: alertInfo,
                            callback: function (action) {
                                searchS();
                            }
                        });
                    } else {
                        var rows = grid1.getSelecteds();
                        if (rows.length ==0) {
                            mini.alert("请选择一条记录");
                            return;
                        }
                        var ids = rows[0].id;
                        for (var i=1, l = rows.length; i < l; i++) {
                            ids += "," + rows[i].id;
                        }
                        var clientWidth = document.body.clientWidth - 100;
                        mini.open({
                            url : "../salary/salary_approval!adjust.action?id=${id}&ids="+ids,
                            title : "编辑薪酬核算数据",
                            width : clientWidth,
                            height : 540,
                            onload : function() {
                            },
                            ondestroy : function(action) {
                                searchS();
                            }
                        });
                    }
                }
            });
        }

        // 审批
        function commitCheck() {
            var assignIds = '${id}';
            window.parent.loading();
            $.ajax({
                url : "../salary/salary_quota!isCheckAUth2.action",
                type : "post",
                data : {
                    ids : assignIds
                },
                success : function(text) {
                    window.parent.unmask();
                    var data = mini.decode(text);
                    var result = data.result;
                    var info = data.info;
                    if (result == "success") {
                        checkAuth(assignIds);
                    } else if (result == "fail") {
                        mini.alert(info);
                        return;
                    } else {
                        if(result.length>0){
                            var alertInfo = "";
                            if (result == "3") {
                                alertInfo = "您不是该分配过程的当前审批人，不能操作";
                            } else if (result == "4") {
                                alertInfo = "该分配过程的审批流程不存在";
                            } else if (result == "13") {
                                alertInfo = "该分配过程已经发放";
                            }
                            if (alertInfo != "") {
                                mini.showMessageBox({
                                    title: "提醒",
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: alertInfo,
                                    callback: function (action) {
                                        searchS();
                                    }
                                });
                            }
                        }
                    }
                }
            });
        }
        function checkAuth(ids) {
            mini.open({
                url : "../salary/salary_quota!audit.action?ids=" + ids,
                title : "审核意见",
                width : 520,
                height : 380,
                onload : function() {
                },
                ondestroy : function(action) {
                    searchS();
                }
            });
        }

        // 与上一个薪酬区间核对
        function heDui() {
        	var assignIds = '${id}';
            window.parent.loading();
            $.ajax({
                url : "../salary/salary_quota!isCheckAUth2.action",
                type : "post",
                data : {
                    ids : assignIds
                },
                success : function(text) {
                    window.parent.unmask();
                    var data = mini.decode(text);
                    var result = data.result;
                    var info = data.info;
                    var alertInfo = "";
                    if (result == "success") {
                    } else if (result == "fail") {
                        alertInfo = info;
                    } else {
                        if(result.length>0){
                        	var alertInfo = "";
                            if (result == "3") {
                                alertInfo = "您不是该分配过程的当前审批人，不能操作";
                            } else if (result == "4") {
                                alertInfo = "该分配过程的审批流程不存在";
                            } else if (result == "13") {
                                alertInfo = "该分配过程已经发放";
                            }
                        }
                    }
                    if (alertInfo != "") {
                        mini.showMessageBox({
                            title: "提醒",
                            iconCls: "mini-messagebox-warning",
                            buttons: ["ok"],
                            message: alertInfo,
                            callback: function (action) {
                                searchS();
                            }
                        });
                    } else {
                        mini.open({
                            url: "../salary/salary_approval!checkwin.action?companyId=${companyId}",
                            title: "选择需要对比的薪酬期间",
                            width: 780,
                            height: 540,
                            ondestroy: function (action) {
                                if (action == "ok") {
                                    var iframe = this.getIFrameEl();
                                    var data = iframe.contentWindow.GetData();
                                    data = mini.clone(data);
                                    var ids = "";
                                    if (data.length > 0) {
                                        ids = data[0].peridId;
                                    }
                                    for (var i = 1; i< data.length; i++) {
                                        ids += "," + data[i].peridId;
                                    }
                                    mini.confirm("确定要核对吗？", "提醒", function(action) {
                                    if (action == "ok") {
                                        window.parent.loading();
                                        $.ajax({
                                            url: "../salary/salary_approval!checkPeriodData.action",
                                            type: "POST",
                                            data : {
                                                id: '${id}',
                                                conditionData : '${conditionData}',
                                                periods : ids
                                            },
                                            success: function(text) {
                                                window.parent.unmask();
                                                if (text == "success") {
                                                    mini.showMessageBox({
                                                        title: "提醒",
                                                        width: 250,
                                                        iconCls: "mini-messagebox-warning",
                                                        buttons: ["ok"],
                                                        message: "核对成功",
                                                        callback: function (action) {
                                                            searchS();
                                                        }
                                                    });
                                                } else {
                                                    mini.alert(text);
                                                }
                                            }
                                        });
                                    }
                                });
                                }
                            }
                        });
                    }
                }
            });
        }

        // 导出
        function exportExcelData(a, b, name, attr) {
            var searchData = searchCondition(name,attr);
            var conditionData = '${conditionData}';
            window.location.href="../"+a+"/"+b+"!exportCheckData.action?id=${id}&searchData="+searchData+"&conditionData="+conditionData;
        }

        // 薪酬期间选择
        function onPeriodButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "../salary/salary_approval!checkwin.action?companyId=${companyId}",
                title: "选择需要对比的薪酬期间",
                width: 780,
                height: 540,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        var ids = "";
                        var names = "";
                        if (data.length > 0) {
                            ids = data[0].peridId;
                            names = data[0].name;
                        }
                        for (var i = 1; i< data.length; i++) {
                            ids += "," + data[i].peridId;
                            names += "," + data[i].name;
                        }
                        btnEdit.setValue(ids);
                        btnEdit.setText(names);
                    }
                }
            });
        }

        // 薪资项目选择事件
        function onSalaryItemChanged(e) {
            var sel = e.selected;
            var index = mini.get("index");
            index.setValue(sel.index);
        }
    </script>
</body>
</html>