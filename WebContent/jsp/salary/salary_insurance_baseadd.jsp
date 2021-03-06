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
    <title>增加缴费基数计算过程</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-tabs" style="width:100%;height:100%">
        <div title="缴费基数计算过程">
            <div class="mini-fit">
                <div id="form1">
                    <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td class="list_left_box1" valign="top" style="width: 20%">*名称：</td>
                            <td class="list_right_box" >
                                <input class="mini-hidden" id="id" name="id" value="${mapInfo.id}"/>
                                <input class="mini-textbox"style="width: 90%" value="${mapInfo.name}" name="name" required="true" maxLength="100"/>
                            </td>
                            <td class="list_left_box1" valign="top"style="width: 20%">*缴费单位：</td>
                            <td class="list_right_box">
                                <input id="companyId" name="companyId" class="mini-lookup" showClose="true" oncloseclick="onClearClick" value="${mapInfo.companyId}"text="${mapInfo.companyName}"
                                    textField="fzx" valueField="id" popupWidth="auto" emptyText="请选择..." onbuttonclick="onButtonEditJfComAlert"
                                    popup="#gridPanel" grid="#datagrid1" multiSelect="true" required="true" style="width:90%"/>
                                <div id="gridPanel" class="mini-panel" title="header" iconCls="icon-add" style="width:450px;height:250px;" 
                                  showToolbar="true" showCloseButton="true" showHeader="false" bodyStyle="padding:0"  borderStyle="border:0">
                                    <div property="toolbar" style="padding:5px;padding-left:8px;text-align:center;">
                                        <div style="float:left;padding-bottom:2px;">
                                           <span>名称：</span>
                                           <input id="companyName" class="mini-textbox" style="width:160px;" onenter="onButtonEditJfComAlert"/>
                                           <a class="mini-button" onclick="onButtonEditJfComAlert">查询</a>
                                        </div>
                                        <div style="clear:both;"></div>
                                    </div>
                                    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
                                       borderStyle="border:0" pagerSize="10">
                                        <div property="columns">
                                            <div type="checkcolumn" width="30"></div>
                                            <div field="id" id="id" width="20" name="id" class="mini-hidden"></div>
                                            <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                                            <div field="fzx" width="100" headerAlign="center" align="center">公司</div>
                                        </div>
                                    </div>
                               </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="list_left_box1" valign="top">*缴费地域：</td>
                            <td class="list_right_box">
                                <input id="areaId" name="areaId" class="mini-lookup" showClose="true" oncloseclick="onClearClick" value="${mapInfo.areaId}"text="${mapInfo.areaName}"
                                    textField="city" valueField="id" popupWidth="auto" emptyText="请选择..." onbuttonclick="onButtonEditJfArea"
                                    popup="#gridPanel2" grid="#datagrid2" multiSelect="true" required="true" style="width:90%"/>
                                <div id="gridPanel2" class="mini-panel" title="header" iconCls="icon-add" style="width:450px;height:250px;" 
                                  showToolbar="true" showCloseButton="true" showHeader="false" bodyStyle="padding:0"  borderStyle="border:0">
                                    <div property="toolbar" style="padding:5px;padding-left:8px;text-align:center;">
                                        <div style="float:left;padding-bottom:2px;">
                                           <span>名称：</span>
                                           <input id="areaName" class="mini-textbox" style="width:160px;" onenter="onButtonEditJfArea"/>
                                           <a class="mini-button" onclick="onButtonEditJfArea">查询</a>
                                        </div>
                                        <div style="clear:both;"></div>
                                    </div>
                                    <div id="datagrid2" class="mini-datagrid" style="width:100%;height:100%;" 
                                       borderStyle="border:0" pagerSize="10">
                                        <div property="columns">
                                            <div type="checkcolumn" width="30"></div>
                                            <div field="id" id="id" width="20" name="id" class="mini-hidden"></div>
                                            <div field="province"  name="province"  width="100" headerAlign="center" align="center">省</div>
                                            <div field="city"  name="city"  width="100" headerAlign="center" align="center">市</div>
                                            <div type="comboboxcolumn" field="state"  name="state"  width="100" headerAlign="center" align="center">状态
                                                <input property="editor" class="mini-combobox" data="[{id:0,text:'无效'},{id:1,text:'有效'}]"/>
                                            </div>
                                        </div>
                                    </div>
                               </div>
                            </td>
                            <td class="list_left_box1" valign="top">*福利项目：</td>
                            <td class="list_right_box">
                                <input id="fuliId" name="fuliId" class="mini-lookup" showClose="true" oncloseclick="onClearClick" value="${mapInfo.fuliId}"text="${mapInfo.fuliName}"
                                    textField="name" valueField="id" popupWidth="auto" emptyText="请选择..." onbuttonclick="onButtonEditJfFuli"
                                    popup="#gridPanel3" grid="#datagrid3" multiSelect="true" required="true" style="width:90%"/>
                                <div id="gridPanel3" class="mini-panel" title="header" iconCls="icon-add" style="width:450px;height:250px;" 
                                  showToolbar="true" showCloseButton="true" showHeader="false" bodyStyle="padding:0"  borderStyle="border:0">
                                    <div property="toolbar" style="padding:5px;padding-left:8px;text-align:center;">
                                        <div style="float:left;padding-bottom:2px;">
                                           <span>名称：</span>
                                           <input id="insuranceName" class="mini-textbox" style="width:160px;" onenter="onButtonEditJfFuli"/>
                                           <a class="mini-button" onclick="onButtonEditJfFuli">查询</a>
                                        </div>
                                        <div style="clear:both;"></div>
                                    </div>
                                    <div id="datagrid3" class="mini-datagrid" style="width:100%;height:100%;" 
                                       borderStyle="border:0" pagerSize="10">
                                        <div property="columns">
                                            <div type="checkcolumn" width="30"></div>
                                            <div field="id" id="id" width="20" name="id" class="mini-hidden"></div>
                                            <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                                            <div field="name" width="100" headerAlign="center" align="center">福利项目</div>
                                        </div>
                                    </div>
                               </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="list_left_box1" valign="top">*参与计算的组织：</td>
                            <td class="list_right_box">
                                <input class="mini-buttonedit" id="deptId" name="deptId" showClose="true" emptyText="请选择..." oncloseclick="onClearClick" onbuttonclick="onButtonEditJfDept"
                                    style="width: 90%"value="${mapInfo.deptId}"text="${mapInfo.deptName}" required="true"/>
                            </td>
                            <td class="list_left_box1" valign="top">*计算年月：</td>
                            <td class="list_right_box">
                                <input class="mini-spinner" style="width:55%" id="effectiveYear" name="effectiveYear" value="${mapInfo.effectiveYear}" minValue="1990" maxValue="2100" format="0" required="true"/>
                                <input class="mini-spinner" style="width:30%" id="effectiveMonth" name="effectiveMonth" value="${mapInfo.effectiveMonth}" minValue="1" maxValue="12" format="0" required="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="list_left_box1" valign="top">备注：</td>
                            <td class="list_right_box" colspan="3">
                                <input class="mini-textarea" style="width: 90%;height:60px" name="note" value="${mapInfo.note}"maxLength="1000"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
                <a class="mini-button" onclick="onOK()" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
                <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
            </div>
        </div>
        <div title="计算缴费基数临时结果">
            <div class="mini-toolbar" style="padding:0px;border-top:0;">
                <table style="width:100%;">
                    <tr>
                        <td style="width:100%;">
                            &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="searchS"/>
                            &nbsp;姓名：<input class="mini-textbox" id="name" onenter="searchS"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                            <br><a class="mini-button" iconCls="icon-add" onclick="choosePerson()">增加</a>
                            <a class="mini-button" iconCls="icon-edit" onclick="editInsurance()">编辑</a>
                            <a class="mini-button" iconCls="icon-remove" onclick="removeRows()">删除</a>
                            <a class="mini-button" iconCls="icon-save" onclick="save()">保存</a>
                            <a class="mini-button" iconCls="icon-reload" onclick="reJs()">重新计算</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    pagerSize="20" idField="id" allowResize="false" borderStyle="border-center:0px;border-right:0px;" multiSelect="true"
                    allowAlternating="true" allowCellEdit="true" allowCellSelect="true">
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");
        var pageIndex = 0;
        var pageSize = grid1.getPageSize();
        var effectiveYear = mini.get("effectiveYear");
        var effectiveMonth = mini.get("effectiveMonth");
        if ('${mapInfo.id}' == null || '${mapInfo.id}' == "") {
            var date = new Date();
            effectiveYear.setValue(date.getFullYear());
            effectiveMonth.setValue(date.getMonth()+1);
        } else {
            var areaId = mini.get("areaId");
            areaId.setEnabled(false);
            var fuliId = mini.get("fuliId");
            fuliId.setEnabled(false);
            var companyId = mini.get("companyId");
            companyId.setEnabled(false);
            var deptId = mini.get("deptId");
            deptId.setEnabled(false);
            effectiveYear.setEnabled(false);
            effectiveMonth.setEnabled(false);
        }

        /** 添加页面用到的删除行方法 */
        function removeRow() {
            var rows = grid1.getSelecteds();
            if (rows.length > 0) {
                grid1.removeRows(rows, false);
            }else{
                mini.alert("请选择需要删除的记录!");
            }
        }

        // 保存计算过程
        function onOK() {
            // 表单部分
            var form = new mini.Form("#form1");
            var formjson="";
            if(form){
                form.validate();
                if (form.isValid() == false)return;
                // 提交表单数据
                var formdata = form.getData();
                formjson = mini.encode(formdata); 
            }

            window.parent.loading();
            $.ajax({
                url : "../salary/salary_payment!saveOrUpdateToBase.action",
                type : "post",
                data : {
                    formdata : formjson
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
                                document.location.reload();
                                window.CloseOwnerWindow();
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

        /** 选择人 */
        function choosePerson() {
            // 判断是否保存计算过程
            var process = mini.get("id").getValue();
            if (process == "") {
                mini.alert("请先保存计算过程");
                return;
            }
            var deptId = mini.get("deptId").getValue();
            var effectiveYear = mini.get("effectiveYear").getValue();
            var effectiveMonth = mini.get("effectiveMonth").getValue();
            mini.open({
                url : "../salary/salary_payment!basePerson.action?id="+process+"&ids="+deptId+"&year="+effectiveYear+"&month="+effectiveMonth,
                title : "选择员工",
                width : 680,
                height : 540,
                onload : function() {
                },
                ondestroy : function(action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        if (data.length > 0) {
                            var table = [];
                            for(var i=0,l=data.length;i<l;i++){
                                var row = data[i];
                                var column = {staffId: row.id, jobNumber: row.jobNumber, name: row.name, company: row.company,
                                        detpName: row.deptName, isDaiJiao: row.isDaiJiao, jfDate: row.jfDate, pjgz: 0, isPayBack: 0};
                                var objs = eval(column);
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
                                table.push(objs);
                            }
                            grid1.addRows(table);
                        }
                    }
                }
            });
        }

        function editInsurance() {
            var rows = grid1.getSelecteds();
            if (rows.length == 0) {
                mini.alert("请选择一条记录!");
                return;
            }
            var id = rows[0].id;
            var staffId = rows[0].staffId;
            mini.open({
                url : "../salary/salary_payment!jfEdit.action?id="+id+"&staffId="+staffId,
                title : "编辑员工缴费信息",
                width : 820,
                height : 580,
                onload : function() {
                },
                ondestroy : function(action) {
                    searchS();
                }
            });
        }

        /** 添加页面用到的删除行方法 */
        function removeRows() {
            var rows = grid1.getSelecteds();
            if (rows.length > 0) {
                grid1.removeRows(rows, false);
            }else{
                mini.alert("请选择需要删除的记录!");
            }
        }

        $(function() {
            searchS();
        });
        function searchS() {
            dynamicTable('jobNumber,name','jobNumber,name');
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
            $.ajax({
                url: "../salary/salary_payment!getJsBaseSalaryListDatas.action?pageIndex="+pageIndex+"&pageSize="+pageSize,
                type: "POST",
                data : {
                    id : mini.get("id").getValue(),
                    fuli : mini.get("fuliId").getValue(),
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
                    arr.push({ field:"company", width:"150", headerAlign:"center", align:"center", header: "公司"});
                    arr.push({ field:"detpName", width:"80", headerAlign:"center", align:"center", header: "部门"});
                    arr.push({ field:"jfDate", width:"80", headerAlign:"center", align:"center", header: "生效年月"});
                    arr.push({ field:"pjgz", width:"80", headerAlign:"center", align:"center", header: "平均工资"});

                    // 循环便利所有的福利项目
                    var fulis = mini.decode(jsonData.fulis);
                    for (var i=0; i< fulis.length;i++) {
                        arr.push({ headerAlign:"center", header: fulis[i].INSUR_NAME, fuiliId: fulis[i].ID,
                                  columns : [
                                         { field:"AREA"+fulis[i].ID, width:"80", headerAlign:"center", align:"center", header: "缴费地域"},
                                         { field:"COMB"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "公司缴费基数"},
                                         { field:"PERB"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "员工缴费基数"}
                                  ]});
                    }
                    var dd = jsonData.data;
                    var table = [];
                    for (var i=0; i< jsonData.total;i++) {
                        var row = dd[i];
                        var column = {id: row.id, staffId: row.staffId, jobNumber: row.jobNumber, name: row.name, company: row.company,
                                detpName: row.deptName, jfDate: row.jfDate, pjgz: row.pjgz};
                        var objs = eval(column);
                        if (row.flId != null) {
                            var flId = row.flId.split(",");
                            var jSon = row.jfArea.split(",");
                            var jsbaseCompany = null;
                            if (row.baseCompany == null) {
                                jsbaseCompany = new Array(flId.length);
                            } else jsbaseCompany = row.baseCompany.split(",");
                            var jsbasePersonal = null;
                            if (row.basePersonal == null) {
                                jsbasePersonal = new Array(flId.length);
                            } else jsbasePersonal = row.basePersonal.split(",");
                            for (var j=0; j< flId.length;j++) {
                                var aField = "AREA"+flId[j];
                                var cField = "COMB"+flId[j];
                                var dField = "PERB"+flId[j];
                                objs[aField] = jSon[j];
                                objs[cField] = jsbaseCompany[j];
                                objs[dField] = jsbasePersonal[j];
                            }
                        }
                        table.push(column);
                    }
                    grid1.setData(table);
                    grid1.setTotalCount(jsonData.total);
                    grid1.setPageIndex(pageIndex);
                    grid1.setPageSize(pageSize);
                    grid1.set({columns : arr});
                    grid1.hideColumn("id");
                    grid1.hideColumn("staffId");
                    grid1.frozenColumns(0, 9);
                }
            });
        }

        grid1.on("beforeload", function (e) {
            pageIndex = e.data.pageIndex;
            pageSize = e.data.pageSize;
            $("#searchButton").click();
        });

        function save() {
            // 判断是否保存计算过程
            var process = mini.get("id").getValue();
            if (process == "") {
                mini.alert("请先保存计算过程");
                return;
            }
            // 表格部分
            var grid1=mini.get("grid1");
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

                // 是否存在相同的员工
                var hash = {};
                for ( var i = 0, l = data.length; i < l; i++) {
                    var row = data[i];
                    if(row.id == ""||typeof(row.id)== "undefined"){
                        row._state="added";
                        grid1.updateRow(row,row);
                    }else{
                        row._state="modified";
                        grid1.updateRow(row,row);
                    }
                    if(row.staffId != undefined && hash[row.staffId]) {
                        mini.alert("员工重复：<font color='red'>"+row.name +"</font>");
                        return;
                    }

                    // 不是补缴
                    hash[row.staffId] = true;
                }
                var griddata = grid1.getChanges();
                gridjson= mini.encode(griddata);
            }
            var columns = grid1.columns;
            var len = columns.length;
            var col = "";
            if (len > 10) {
                col = columns[11].fuiliId;
                for (var i = 12, l = columns.length; i < l; i++) {
                    col += "," + columns[i].fuiliId;
                }
            }
            window.parent.loading();
            $.ajax({
                url : "../salary/salary_payment!saveOrUpdateToBasePerson.action",
                type : "post",
                data : {
                    id : process,
                    griddata : gridjson,
                    columns: col
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

        // 重新计算
        function reJs() {
            // 判断是否保存计算过程
            var process = mini.get("id").getValue();
            if (process == "") {
                mini.alert("请先保存计算过程");
                return;
            }
            window.parent.loading();
            $.ajax({
                url : "../salary/salary_payment!updateReloadBase.action",
                type : "post",
                data : {
                    id : process
                },
                success : function(text) {
                    window.parent.unmask();
                    if (text == "success") {
                        mini.showMessageBox({
                            title: "提醒",
                            width: 250,
                            iconCls: "mini-messagebox-warning",
                            buttons: ["ok"],
                            message: "计算成功",
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

        // 缴费单位
        function onButtonEditJfComAlert(e) {
            var datagrid1 = mini.get("datagrid1");
            datagrid1.hideColumn("id");
            datagrid1.setUrl("../salary/salary_manage!getBranches.action");
            datagrid1.load({companyName: mini.get("companyName").getValue()});
        }

        // 缴费地域
        function onButtonEditJfArea(e) {
            var datagrid2 = mini.get("datagrid2");
            datagrid2.hideColumn("id");
            datagrid2.setUrl("../basis/pay_areas!jsonlist.action");
            datagrid2.load({areaName: mini.get("areaName").getValue()});
        }

        // 福利项目
        function onButtonEditJfFuli(e) {
            var datagrid3 = mini.get("datagrid3");
            datagrid3.hideColumn("id");
            datagrid3.setUrl("../basis/insurance_benefits!getAllItemsListData.action");
            datagrid3.load({insuranceName: mini.get("insuranceName").getValue()});
        }

        // 参与计算的组织
        function onButtonEditJfDept(e) {
            onButtonEdit(e.sender, "选择参与计算的组织", "../salary/salary_payment!company.action", 320, 480,"","","");
        }

        /**
         * 套餐清除
         * zhanghj
         */
        function onClearClick(e) {
            var obj = e.sender;
            obj.setText("");
            obj.setValue("");
        }

    </script>
</body>
</html>