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
    <title>薪酬核算数据修改</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <input class="mini-hidden" id="id" name = "id" value="${id}"/>
                    &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="searchS"/>
                    &nbsp;姓名：<input class="mini-textbox" id="name" onenter="searchS"/>
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                    <br><a class="mini-button" iconCls="icon-save" onclick="updateRecord()">保存</a>
                    <a class="mini-button" iconCls="icon-reload" onclick="reJs(1)">重算所有修改员工</a>
                    <a class="mini-button" iconCls="icon-reload" onclick="reJs(2)">重算选中修改员工</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"allowHeaderWrap="true"
            allowCellEdit="true" allowCellSelect="true" allowAlternating="true" multiSelect="true" showPager = "false"
            idField="id" allowResize="false" virtualScroll="true">
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">关闭</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");

        function updateRecord() {
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
                for ( var i = 0, l = data.length; i < l; i++) {
                    var row = data[i];
                    if(row.id == ""||typeof(row.id)== "undefined"){
                        row._state="added";
                        grid1.updateRow(row,row);
                    }else{
                        row._state="modified";
                        grid1.updateRow(row,row);
                    }
                }
                var griddata = grid1.getChanges();
                gridjson= mini.encode(griddata);
            }
            var columns = grid1.columns;
            var len = columns.length;
            var col = "";
            if (len > 9) {
                col = columns[10].salaryId;
                for (var i = 11, l = len; i < l; i++) {
                    col += "," + columns[i].salaryId;
                }
            }

            window.parent.loading();
            $.ajax({
                url : "../salary/salary_quota!updateSelectStaffSalaryData.action",
                type : "post",
                data : {
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

        function reJs(type) {
            var rows = "";
            if (type == 1) {// 重算所有修改员工
                rows = grid1.getData();
            } else if (type == 2) {// 重算选中修改员工
                rows = grid1.getSelecteds();
                if (rows.length ==0) {
                    mini.alert("请选择一条记录");
                    return;
                }
            }
            var ids = rows[0].id;
            for (var i=1, l = rows.length; i < l; i++) {
                ids += "," + rows[i].id;
            }
            window.parent.loading();
            $.ajax({
                url : "../salary/salary_quota!updateReCalculateStaffsItem.action",
                type : "POST",
                data : {
                    assignStaffId : "",
                    assignId: ids,
                    id: "",
                    formula: ""
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
                url: "../salary/salary_quota!getAssignationForStaffData.action",
                type: "POST",
                data : {
                    id: '${id}',
                    ids: '${ids}',
                    searchData : ss
                },
                success: function(text) {
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
                    arr.push({ type:"comboboxcolumn", field:"isAllMonth", width:"74", headerAlign:"center", align:"center", header: "参加平均工资计算",
                        editor: { type: "combobox", data: "[{id:0, text:'否'},{id:1, text:'是'}]"}});
    
                    // 循环便利所有的福利项目
                    if (jsonData.length > 0) {
                        var fulis = jsonData[0].itemsId.split(",;");
                        var fulisName = jsonData[0].itemsName.split(",;");
                        var isNumber = jsonData[0].isNumber.split(",;");
                        for (var i=0; i< fulis.length;i++) {
                            var param = { field: fulis[i], width:"100", headerAlign:"center", align:"center", salaryId: fulis[i], editor: { type: "textbox", maxLength: 20}};
                            if (isNumber[i] == 0) {// 不是数值，不参与计算
                                param.header = fulisName[i];
                                param.editor.maxLength = 2000;// 长度限制
                            } else if (isNumber[i] == 1) {// 不是数值，不参与计算
                                param.header = fulisName[i] +
                                '<br> <a class="mini-button" onclick="columnReCalculate(\''+fulis[i]+'\')" style="width:78px;">重新计算</a>';
                                param.vtype = "float;required:true";
                                param.editor.maxLength = 20;
                            }
                            arr.push(param);
                        }
                    }
                    grid1.set({columns : arr});
                    grid1.hideColumn("id");
                    grid1.hideColumn("staffId");
                    grid1.frozenColumns(0, 9);
                    grid1.setTotalCount(jsonData.length);

                    // 遍历数据
                    var table = [];
                    for (var i=0; i< jsonData.length;i++) {
                        var row = jsonData[i];
                        var column = {id: row.id, staffId: row.staffId, jobNumber: row.jobNumber, name: row.name,
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
                }
            });
        }

        // 重新计算
        function columnReCalculate(salaryId) {
            // 获取该项目的公式
            mini.open({
                url : "../salary/salary_quota!financial.action?assignStaffId=${id}&id=" + salaryId,
                title : "选择公式",
                width : 540,
                height : 320,
                onload : function() {
                },
                ondestroy : function(action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        var rows = grid1.getData();
                        var ids = rows[0].id;
                        for (var i=1, l = rows.length; i < l; i++) {
                            ids += "," + rows[i].id;
                        }
                        window.parent.loading();
                        $.ajax({
                            url: "../salary/salary_quota!updateSelectReCalculateStaffsItem.action",
                            type:"POST",
                            data: {
                                ids: ids,
                                itemID : salaryId,
                                formula: data
                            },
                            success: function(text) {
                                window.parent.unmask();
                                if(text=="success"){
                                    window.parent.unmask();
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
                                }else{
                                    mini.alert(text);
                                }
                            }
                        });
                    }
                }
            });
        }

        /** 关闭按钮点击事件 */
        function onCancel() {
            CloseWindow("cancel");
        }

        /** 关闭窗口 */
        function CloseWindow(action) {
            if (window.CloseOwnerWindow)
                return window.CloseOwnerWindow(action);
            else
                window.close();
        }
    </script>
</body>
</html>