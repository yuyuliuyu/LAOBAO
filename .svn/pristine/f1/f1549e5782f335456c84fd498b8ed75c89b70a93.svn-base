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
    <title>增加月平均工资计算过程</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-tabs" style="width:100%;height:100%">
        <div title="平均工资计算过程">
            <div id="form1">
                <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td class="list_left_box1" valign="top" style="width: 20%">名称：</td>
                        <td class="list_right_box" >
                            <input class="mini-hidden" id="id" name="id" value="${salaryMonth.id}"/>
                            <input class="mini-textbox"style="width: 90%" value="${salaryMonth.name}" name="name" required="true" maxLength="100"/>
                        </td>
                        <td class="list_left_box1" valign="top"style="width: 20%">*组织机构：</td>
                        <td class="list_right_box">
                            <input class="mini-combobox" readOnly="true" enabled="false" id="companyId" name="companyId" textField="fzx" url="../salary/salary_manage!getBranches.action"
                                value="${companyId}" style="width:90%" required="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="list_left_box1" valign="top">*计算年份：</td>
                        <td class="list_right_box">
                            <input class="mini-spinner" style="width: 90%" value="${salaryMonth.year}" id ="year" name="year" required="true"minValue="1990" maxValue="2100" format="0" />
                        </td>
                        <td class="list_left_box1" valign="top">*生效年月：</td>
                        <td class="list_right_box">
                            <input class="mini-spinner" style="width:55%" id="effectiveYear" name="effectiveYear" value="${salaryMonth.effectiveYear}" minValue="1990" maxValue="2100" format="0" required="true"/>
                            <input class="mini-spinner" style="width:30%" id="effectiveMonth" name="effectiveMonth" value="${salaryMonth.effectiveMonth}" minValue="1" maxValue="12" format="0" required="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="list_left_box1" valign="top">*计算类别：</td>
                        <td class="list_right_box">
                            <input class="mini-combobox" style="width: 90%" value="${salaryMonth.type}" required="true" name="type"
                                data="[{id:1,text:'不足整月的按整月计算 '},{id:2,text:'不足整月的舍弃此月'}]"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-toolbar" style="padding:0px;border-top:0;">
                <table style="width:100%;">
                    <tr>
                        <td style="width:100%;">
                            <a class="mini-button" iconCls="icon-add" onclick="chooseItems()">选择薪资项目</a>
                            <a class="mini-button" iconCls="icon-remove" onclick="removeRow()">删除</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    allowAlternating="true" multiSelect="true" url="../salary/salary_month!getMonthItemForId.action?id=${salaryMonth.id}"
                    idField="id" allowResize="false" showPager = "false"
                    allowCellEdit="true" allowCellSelect="true">
                    <div property="columns">
                        <div type="checkcolumn" width="40"></div>
                        <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                        <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                        <div field="itemsId" displayField="itemName" width="120" headerAlign="center" align="center" vtype="required;">薪酬项目</div>
                        <div type="comboboxcolumn" field="isAdd" width="60" headerAlign="center" align="center" vtype="required;">加减项
                            <input property="editor" class="mini-combobox" style="width:100%;" data="[{id:1,text:'减'},{id:2,text:'加'}]" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
                <a class="mini-button" onclick="onOK()" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
                <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
            </div>
        </div>
        <div title="平均工资计算结果">
            <div class="mini-toolbar" style="padding:0px;border-top:0;">
                <table style="width:100%;">
                    <tr>
                        <td style="width:100%;">
                            &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="searchS"/>
                            &nbsp;姓名：<input class="mini-textbox" id="name" onenter="searchS"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                            <br><a class="mini-button" iconCls="icon-add" onclick="choosePerson()">增加</a>
                            <a class="mini-button" iconCls="icon-remove" onclick="removeRows()">删除</a>
                            <a class="mini-button" iconCls="icon-save" onclick="save()">保存</a>
                            <a class="mini-button" iconCls="icon-reload" onclick="reJs()">重新计算</a>
                            <a class="mini-button" iconCls="icon-tip" onclick="replaceCurAvgSalary()">替换维护月平均工资</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid2" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    allowAlternating="true" multiSelect="true" url="../salary/salary_month!getMonthSalaryListData.action?id=${id}"
                    allowCellEdit="true" allowCellSelect="true" idField="id" allowResize="false">
                    <div property="columns">
                        <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                        <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                        <div field="staffId" name="staffId" width="80" headerAlign="center" align="center">员工id</div>
                        <div field="monthStaffId" name="monthStaffId" width="80" headerAlign="center" align="center">月平均工资ID</div>
                        <div field="jobNumber" width="80" headerAlign="center" align="center">工号</div>
                        <div field="name" width="100" headerAlign="center" align="center">姓名</div>
                        <div field="companyName" width="100" headerAlign="center" align="center">公司</div>
                        <div field="deptName" width="60" headerAlign="center" align="center">部门</div>
                        <div field="averageSalary" width="60" headerAlign="center" align="center" vtype="required;float">平均工资*
                            <input property="editor" class="mini-textbox" maxLength="20"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");
        grid1.hideColumn("id");
        grid1.load();
        var grid2 = mini.get("grid2");
        grid2.hideColumn("id");
        grid2.hideColumn("staffId");
        grid2.hideColumn("monthStaffId");
        grid2.load();
        var year = mini.get("year");
        var effectiveYear = mini.get("effectiveYear");
        var effectiveMonth = mini.get("effectiveMonth");
        if ('${salaryMonth.id}' == null || '${salaryMonth.id}' == "") {
            var date = new Date();
            year.setValue(date.getFullYear());
            effectiveYear.setValue(date.getFullYear());
            effectiveMonth.setValue(date.getMonth()+1);
        } else {
            year.setEnabled(false);
            var companyId = mini.get("companyId");
            companyId.setEnabled(false);
            effectiveYear.setEnabled(false);
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
            // 表格部分
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
                // 是否存在相同的薪资项目
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
                    if(row.itemsId != undefined && hash[row.itemsId]) {
                        mini.alert("薪酬项目重复：<font color='red'>"+row.itemName +"</font>");
                        return;
                    }
                    hash[row.itemsId] = true;
                }
                var griddata = grid1.getChanges();
                gridjson= mini.encode(griddata);
            }

            window.parent.loading();
            $.ajax({
                url : "../salary/salary_month!saveOrUpdate.action",
                type : "post",
                data : {
                    formdata : formjson,
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
            var companyId = mini.get("companyId").getValue();
            mini.open({
                url : "../salary/salary_month!person.action?id="+companyId,
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
                            var r = [];
                            for(var i=0,l=data.length;i<l;i++){
                                var newRow = {staffId: data[i].id,jobNumber: data[i].jobNumber,name: data[i].name,companyName: data[i].companyName, deptName : data[i].deptName,averageSalary: 0 };
                                r.push(newRow);
                            }
                            grid2.addRows(r);
                        }
                    }
                }
            });
        }

        /** 添加页面用到的删除行方法 */
        function removeRows() {
            var rows = grid2.getSelecteds();
            if (rows.length > 0) {
                grid2.removeRows(rows, false);
            }else{
                mini.alert("请选择需要删除的记录!");
            }
        }

        function chooseItems() {
        	var data = grid1.getData();
            var items = "";
            if (data.length > 0) {
                items = data[0].itemId;
            }
            for (var i = 1, l = data.length; i < l; i++) {
                var row = data[i];
                items += "," + row.itemId;
            }
            mini.open({
                url : "../salary/salary_items!choose.action?companyId=${depId}&items="+items,
                title : "选择薪资项目",
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
                            var eLen = grid1.getData().length;
                            var r = [];
                            for(var i=0,l=data.length;i<l;i++){
                                var newRow = {itemsId: data[i].id, itemName: data[i].name, isAdd: 2};
                                r.push(newRow);
                            }
                            grid1.addRows(r);
                        }
                    }
                }
            });
        }

        function searchS() {
            search('name,jobNumber','name,jobNumber');
        }

        function search(name,attr) {
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
            grid2.load({ searchData : searchData });
        }

        function save() {
            // 判断是否保存计算过程
            var process = mini.get("id").getValue();
            if (process == "") {
                mini.alert("请先保存计算过程");
                return;
            }
            // 表格部分
            var grid=mini.get("grid2");
            var gridjson="";
            if(grid){
                // 验证
                grid.validate();
                if (grid.isValid() == false) {
                    gridError(grid);
                    return;
                }
                var data = grid.getData();
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
                        grid.updateRow(row,row);
                    }else{
                        row._state="modified";
                        grid.updateRow(row,row);
                    }
                    if(row.staffId != undefined && hash[row.staffId]) {
                        mini.alert("员工重复：<font color='red'>"+row.name +"</font>");
                        return;
                    }
                    hash[row.staffId] = true;
                }
                var griddata = grid.getChanges();
                gridjson= mini.encode(griddata);
            }
            window.parent.loading();
            $.ajax({
                url : "../salary/salary_month!saveOrUpdateToPerson.action",
                type : "post",
                data : {
                    id : process,
                    griddata : gridjson
                },
                success : function(text) {
                    window.parent.unmask();
                    if (text == "success") {
                        mini.confirm("是否需要替换现有平均工资？<br> 请慎重使用，每年调整缴费基数时使用","提醒", function(action) {
                            if (action == "ok") {
                                window.parent.loading();
                                $.ajax({
                                    url : "../salary/salary_month!replaceCurAvgSalary.action",
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
                                                message: "操作成功",
                                                callback: function (action) {
                                                    grid2.reload();
                                                }
                                            });
                                        } else {
                                            if(text.length>0){
                                                mini.alert(text);
                                            }
                                        }
                                    }
                                });
                            } else grid2.reload();
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
                url : "../salary/salary_month!updateReloadCalculation.action",
                type : "post",
                data : {
                    id : process
                },
                success : function(text) {
                    window.parent.unmask();
                    if (text == "success") {
                        mini.confirm("是否需要替换现有平均工资？","提醒", function(action) {
                            if (action == "ok") {
                                window.parent.loading();
                                $.ajax({
                                    url : "../salary/salary_month!replaceCurAvgSalary.action",
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
                                                message: "操作成功",
                                                callback: function (action) {
                                                    grid2.reload();
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
                    } else {
                        if(text.length>0){
                            mini.alert(text);
                        }
                    }
                }
            });
        }

        // 是否替换
        function replaceCurAvgSalary() {
            // 判断是否保存计算过程
            var process = mini.get("id").getValue();
            if (process == "") {
                mini.alert("请先保存计算过程");
                return;
            }
            mini.confirm("确定要替换现有平均工资？<br> 请慎重使用，每年调整缴费基数时使用","提醒", function(action) {
                if (action == "ok") {
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_month!replaceCurAvgSalary.action",
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
                                    message: "操作成功",
                                    callback: function (action) {
                                        grid2.reload();
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