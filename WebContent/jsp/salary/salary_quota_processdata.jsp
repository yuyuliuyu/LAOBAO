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
    <title>维护薪酬核算数据</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/biguploader/uploader.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
        <div size="16%" showCollapseButton="true">
            <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                <span style="padding-left:5px;">部门：</span>
            </div>
            <div class="mini-fit" style=" border-top:0;">
                <ul id="deptree" class="mini-tree" url="../salary/salary_approval!getCurComDepts.action?companyId=${salaryAssignation.companyId}" style="width:100%;height:100%;"
                    showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true" onnodeclick = "onNodeClick">
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
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                            <br><a class="mini-button" iconCls="icon-add" name="status" onclick="addNewStaff()">追加员工</a>
                            <a class="mini-button" iconCls="icon-edit" name="status" onclick="editRows()">调整</a>
                            <a class="mini-button" iconCls="icon-remove" name="status" onclick="removeRows()">删除</a>
                            <a class="mini-button" iconCls="icon-ok" name="status" onclick="commitCheck()">提交审批</a>
                            <a class="mini-button" iconCls="icon-reload" name="status" onclick="reInit()">重新初始化</a>
                            <!-- <a class="mini-hidden" iconCls="icon-goto" name="status" onclick="giveAllot()">分配二次分配数据</a> -->
                            <a class="mini-button" iconCls="icon-goto" name="status" onclick="giveSencondAllot()">获取二次分配数据</a>
                            <a class="mini-button" iconCls="icon-ok" name="status" onclick="allowIbfEdit()">允许保险修改</a>
                            <a class="mini-button" iconCls="icon-download" onclick="downModel()">下载导入模板</a>
                            <a class="mini-button" iconCls="icon-upload" name="status"  onclick="importExcelData()">导入</a>
                            <a class="mini-button" iconCls="icon-download" onclick="exportExcelData('salary', 'salary_quota','jobNumber,name,deptId','jobNumber,name,deptId')">导出</a>
                            <a class="mini-button" iconCls="icon-print" onclick="print()">打印工资表</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" pageSize="50"
                    allowAlternating="true" multiSelect="true" allowHeaderWrap="true"  showPager="false"
                    idField="id" allowResize="false" virtualScroll="true" virtualColumns="true" showSummaryRow="true" >
                </div>
            </div>
        </div>
    </div>
    <form id="uploaders" method="post" enctype="multipart/form-data" encoding="multipart/form-data" target="appFrame" style="display: none;">
       <input class="mini-htmlfile" id="uploadFile" name="uploadFile" limitType="*" onfileselect= "onFileSelected"/>
    </form>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");
        var pageIndex = 0;
        var pageSize = grid1.getPageSize();

        // 汇总数据
        var gSummaryData = "";

        // 追加员工
        function addNewStaff() {
            // 核算过程状态检查
            var status = buttonAuth();
            if (status == "fail") {
                mini.alert("该核算过程已经提交或者已通过");
                return;
            }
            mini.open({
                url : "../salary/salary_quota!staff.action?id=${id}",
                title : "增加员工",
                width : 720,
                height : 540,
                onload : function() {
                },
                ondestroy : function(action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        window.parent.loading();
                        $.ajax({
                            url : "../salary/salary_quota!saveSelectStaffData.action",
                            type : "post",
                            data : {
                                id : '${id}',
                                selectData : mini.encode(data)
                            },
                            success : function(text) {
                                window.parent.unmask();
                                if (text == "success") {
                                    $.ajax({
                                        url : "../salary/salary_quota!updateTotalAmount.action",
                                        type : "get",
                                        data : {
                                            id : '${id}'
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
                                                    searchS();
                                                }
                                            }
                                        }
                                    });
                                } else {
                                    if(text.length>0){
                                        mini.alert(text);
                                        searchS();
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }

        function commitCheck() {
            // 核算过程状态检查
            var status = buttonAuth();
            if (status == "fail") {
                mini.alert("该核算过程已经提交或者已通过");
                return;
            }
            mini.open({
                url : "../salary/salary_quota!authpro.action?ids=${id}",
                title : "审批岗位选择",
                width : 560,
                height : 380,
                onload : function() {
                },
                ondestroy : function(action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        window.parent.loading();
                        $.ajax({
                            url : "../salary/salary_quota!batchCommitCheck.action",
                            type : "post",
                            data : {
                                ids : '${id}',
                                isIgnore: 1,
                                formdata: data
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
                                            document.location.reload();
                                        }
                                    });
                                } else {
                                    if (text == "-1") {
                                        mini.alert("存在未上报的二次分配项目");
                                    } else if(text.length>0){
                                        mini.alert(text);
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }

        $(function() {
            searchS();

            // 根据提交状态是否显示某些按钮
            var checkStatus = '${salaryAssignation.isSp}';
            if (checkStatus == 1 || checkStatus == 3) {// 已提交、已通过的按钮不显示
                var statusName = mini.getsbyName("status");
                for(var i=0; i < statusName.length; i++){
                    var link = statusName[i];
                    link.hide();
                }
            }
        });
        function searchS() {
            dynamicTable('jobNumber,name,deptId','jobNumber,name,deptId');
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
                url: "../salary/salary_quota!getAssignationForStaffData.action?pageIndex="+pageIndex+"&pageSize="+pageSize,
                type: "POST",
                data : {
                    id: '${id}',
                    ids: "",
                    searchData : ss
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
                    arr.push({ field:"deptName", width:"120", headerAlign:"center", align:"center", header: "部门"});
                    arr.push({ field:"gwName", width:"120", headerAlign:"center", align:"center", header: "具体岗位"});
                    arr.push({ field:"bzgwName", width:"120", headerAlign:"center", align:"center", header: "标准岗位"});
                    arr.push({ field:"xcgwName", width:"120", headerAlign:"center", align:"center", header: "薪酬岗位"});
                    arr.push({ type:"comboboxcolumn", field:"isAllMonth", width:"74", headerAlign:"center", align:"center", header: "参加平均工资计算",
                           editor: { type: "combobox", data: "[{id:0, text:'否'},{id:1, text:'是'}]"}});

                    // 循环便利所有的福利项目
                    if (jsonData.length > 0) {
                        if (jsonData[0].itemsId != null) {
                            var fulis = jsonData[0].itemsId.split(",;");
                            var fulisName = jsonData[0].itemsName.split(",;");
                            for (var i=0; i< fulis.length;i++) {
                                arr.push({ field: fulis[i], width:"80", headerAlign:"center", align:"center", header: fulisName[i]});
                            }
                        }
                    }

                    // 遍历数据
                    var table = [];
                    for (var i=0; i< jsonData.length;i++) {
                        var row = jsonData[i];
                        var column = {id: row.id, staffId: row.staffId, jobNumber: row.jobNumber, name: row.name, departId: row.departId,
                                isAllMonth: row.isAllMonth, deptName: row.deptName, gwName: row.gwName, bzgwName: row.bzgwName, xcgwName: row.xcgwName};
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
                    gSummaryData = gridData.summary;
                    grid1.setData(table);
                    grid1.setPageIndex(pageIndex);
                    grid1.setPageSize(pageSize);
                    grid1.set({columns : arr, ondrawsummarycell: "onDrawSummaryCell"});
                    grid1.hideColumn("id");
                    grid1.hideColumn("staffId");
                    grid1.frozenColumns(0, 10);
                    grid1.setTotalCount(gridData.total);
                }
            });
        }
        grid1.on("beforeload", function (e) {
            pageIndex = e.data.pageIndex;
            pageSize = e.data.pageSize;
            $("#searchButton").click();
        });

        // 重新初始化
        function reInit() {
            // 核算过程状态检查
            var status = buttonAuth();
            if (status == "fail") {
                mini.alert("该核算过程已经提交或者已通过");
                return;
            }
            mini.confirm("此操作将会重新初始化核算过程，确定要初始化？","提醒",function(action) {
                if (action == "ok") {
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_quota!updateAssignationForStaffData.action",
                        type : "post",
                        data : {
                            id : '${id}'
                        },
                        success : function(text) {
                            if (text == "success") {
                                $.ajax({
                                    url : "../salary/salary_quota!updateTotalAmount.action",
                                    type : "post",
                                    data : {
                                        id : '${id}'
                                    },
                                    success : function(text) {
                                        if (text == "success") {
                                            $.ajax({
                                                url : "../salary/salary_quota!saveItemsFormula.action",
                                                type : "post",
                                                data : {
                                                    id : '${id}',
                                                    assignStaffId: ""
                                                },
                                                success : function(text) {
                                                    window.parent.unmask();
                                                    if (text == "success") {
                                                        mini.showMessageBox({
                                                            title: "提醒",
                                                            width: 250,
                                                            iconCls: "mini-messagebox-warning",
                                                            buttons: ["ok"],
                                                            message: "重新初始化成功",
                                                            callback: function (action) {
                                                                searchS();
                                                            }
                                                        });
                                                    } else {
                                                        if(text.length>0){
                                                            if(text.length>0){
                                                                mini.alert(text, "提醒", function(action) {
                                                                    searchS();
                                                                });
                                                            }
                                                        }
                                                    }
                                                }
                                            });
                                        } else {
                                            window.parent.unmask();
                                            if(text.length>0){
                                                mini.alert(text, "提醒", function(action) {
	                                                searchS();
                                                });
                                            }
                                        }
                                    }
                                });
                            } else {
                                window.parent.unmask();
                                if(text.length>0){
                                    mini.alert(text);
                                    searchS();
                                }
                            }
                        }
                    });
                }
            });
        }

        // 调整
        function editRows() {
            // 核算过程状态检查
            var status = buttonAuth();
            if (status == "fail") {
                mini.alert("该核算过程已经提交或者已通过");
                return;
            }
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
                url : "../salary/salary_quota!adjust.action?id=${id}&ids="+ids,
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
        // 删除
        function removeRows() {
            // 核算过程状态检查
            var status = buttonAuth();
            if (status == "fail") {
                mini.alert("该核算过程已经提交或者已通过");
                return;
            }
            var rows = grid1.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i=1, l = rows.length; i < l; i++) {
                ids += "," + rows[i].id;
            }
            mini.confirm("您确定要删除选中信息吗？","提醒",function(action){
                if(action=="ok"){
                    window.parent.loading();
                    $.ajax({
                        url: "../salary/salary_quota!removeForAssign.action",
                        type:"post",
                        data: {
                            ids: ids
                        },
                        success: function (text) {
                            if(text=="success"){
                                window.parent.unmask();
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "删除成功",
                                    callback: function (action) {
                                        searchS();
                                    }
                                });
                            }else{
                                mini.alert(text);
                                searchS();
                            }
                        }
                    });
                }
            });
        }

        // 按钮操作之前的状态检查
        function buttonAuth() {
            var checkStatus = '${salaryAssignation.isSp}';
            if (checkStatus == 1 || checkStatus == 3) {
                return "fail";
            } else {
                return "success";
            }
        }

        /**导出
         * a:包名
         * b:类名
         * name:所有查询条件框的name字符串
         * attr:所有查询条件的属性名
         * action方法名:export
         * */
        function exportExcelData(a, b, name, attr) {
            var names=name.split(",");
            var attrs=attr.split(",");
            var searchData="{";
                for(var i=0;i<names.length;i++){
                    var name=names[i];
                    if(mini.get(name)){
                        var value=(name=="startTime"||name=="endTime")?mini.get(name).getFormValue():mini.get(name).getValue();
                        searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\"";
                        if(i!=names.length-1)searchData=searchData+",";
                    }
                }
            searchData=searchData+"}";
            window.location.href="../"+a+"/"+b+"!export.action?searchData="+searchData+"&id=${id}";
        }

        /**
         * 单元格绘制
         * zhanghj
         **/
        function onDrawSummaryCell(e) {
            var field = e.field;
            var sender = e.sender;
            // 循环便利所有的福利项目
            var summary = mini.decode(gSummaryData);
            for (var i=0; i< summary.length;i++) {
                if (field != null && field == summary[i].IBFID) {
                    e.cellStyle = "text-align:center";
                    e.cellHtml = parseFloat(summary[i].CHARGE).toFixed(summary[i].XS);
                }
            }
        }

        // 分配二次数据
        function giveAllot() {
            // 核算过程状态检查
            var status = buttonAuth();
            if (status == "fail") {
                mini.alert("该核算过程已经提交或者已通过");
                return;
            }

            mini.confirm("确定要分配二次数据总量吗？","提醒", function(action) {
                if (action=="ok") {
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_quota!updateAllot.action",
                        type : "post",
                        data : {
                            id : '${id}'
                        },
                        success : function(text) {
                            window.parent.unmask();
                            if (text == "success") {
                                mini.alert("操作成功");
                            } else {
                                if(text.length>0){
                                    mini.alert(text);
                                    searchS();
                                }
                            }
                        }
                    });
                }
            });
        }

        // 获取二次分配数据
        function giveSencondAllot() {
            // 核算过程状态检查
            var status = buttonAuth();
            if (status == "fail") {
                mini.alert("该核算过程已经提交或者已通过");
                return;
            }
            var rows = grid1.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i=1, l = rows.length; i < l; i++) {
                ids += "," + rows[i].id;
            }

            mini.confirm("确定要获取二次分配数据吗？","提醒", function(action) {
                if (action=="ok") {
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_quota!updateCheckSecondData.action",
                        type : "post",
                        data : {
                            id : '${id}',
                            ids: ids
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
                                        $("#searchButton").click();
                                    }
                                });
                            } else {
                                if(text.length>0){
                                    mini.alert(text);
                                    $("#searchButton").click();
                                }
                            }
                        }
                    });
                }
            });
        }

        // 打印工资表
        function print() {
            mini.open({
                url : "../salary/salary_quota!printSalary.action?id=${id}",
                title : "打印工资表",
                width : 460,
                height : 180,
                onload : function() {
                },
                ondestroy : function(action) {
                    searchS();
                }
            });
        }

        // 允许保险修改
        function allowIbfEdit() {
            // 核算过程状态检查
            var status = buttonAuth();
            if (status == "fail") {
                mini.alert("该核算过程已经提交或者已通过");
                return;
            }
            mini.confirm("确定要允许保险缴费修改吗？","提醒", function(action) {
                if (action=="ok") {
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_quota!allowIbfEdit.action",
                        type : "post",
                        data : {
                            id : '${id}'
                        },
                        success : function(text) {
                            window.parent.unmask();
                            if (text == "success") {
                                mini.alert("操作成功");
                            } else {
                                if(text.length>0){
                                    mini.alert(text);
                                    searchS();
                                }
                            }
                        }
                    });
                }
            });
        }

        // 导入模板下载
        function downModel() {
            window.location.href="../salary/checkModel.action";
        }

        // 导入
        function importExcelData() {
            var url = "../salary/salary_quota!uploadCheckData.action?id=${id}";
            bigWebUploader.openMini("uploaders","uploadFile", url);
        }

        function onFileSelected(e) {
            window.parent.loading();
            // 表单提交
            bigWebUploader.submit(callback);
        }

        function callback(data) {
            window.parent.unmask();
            if (data == "success") {
                mini.alert("导入成功");
                $("#searchButton").click();
            } else {
                mini.alert(data);
            }
        }

        function onNodeClick(e) {
            mini.get("deptId").setValue(e.node.id);
            $("#searchButton").click();
        }
    </script>
</body>
</html>