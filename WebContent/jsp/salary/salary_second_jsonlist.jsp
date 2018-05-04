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
    <title>二次分配数据</title>
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
                <ul id="deptree" class="mini-tree" url="../salary/salary_approval!getCurComDepts.action?companyId=${companyId}" style="width:100%;height:100%;"
                    showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true" onnodeclick = "onNodeClick">
                </ul>
            </div>
        </div>
        <div showCollapseButton="true">
             <div class="mini-toolbar" style="padding:0px;border-top:0;">
                <table style="width:100%;">
                    <tr>
                        <td style="width:100%;">
                            &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="searchS"/>
                            &nbsp;姓名：<input class="mini-textbox" id="name" onenter="searchS"/>
                            <input class="mini-hidden" id="deptId" />
                            &nbsp;薪资组：<input class="mini-combobox" id="salaryGroup" textField="name" onvaluechanged="onValueChanged" onenter="searchS"/>
                            <input class="mini-hidden" style="width: 120px" id="fpqx" value="2"
                                data="[{id: 1, text:'公司'},{id: 2, text:'基层'}]" onenter="searchS" onvaluechanged="onValueChanged"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                            <br>
                            <a class="mini-button" iconCls="icon-save" name="status" onclick="saveAllAmount()">保存</a>
                            <a class="mini-button" iconCls="icon-ok" name="status" onclick="setReport()">上报</a>
                            <a class="mini-button" iconCls="icon-no" onclick="setCancelReport()">取消上报</a>
                            <a class="mini-button" iconCls="icon-print" onclick="print()">打印</a>
                            <a class="mini-button" iconCls="icon-download" onclick="downModel()">下载导入模板</a>
                            <a class="mini-button" iconCls="icon-upload" onclick="importExcelData()">导入</a>
                            <a class="mini-button" iconCls="icon-download" onclick="exportExcelData()">导出</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20" showPager="false" virtualScroll="true"
                    allowCellEdit="true" allowCellSelect="true" allowAlternating="true" allowHeaderWrap = "true"
                    idField="id" allowResize="false">
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
        var deptId = mini.get("deptId");
        var salaryGroup = mini.get("salaryGroup");

        var tree = mini.get("deptree");
        function deepTree(ids, tree, node) {
            var parentNode = tree.bubbleParent(node, function(e) {
                if (!tree.isLeaf(e) || tree.getLevel(e) == 0) {
                    ids = e.id;
                    var list = tree.getAllChildNodes(e);
                    var len = list.length;
                    for (var i = 0; i < len; i++) {
                        ids += "," + list[i].id;
                    }
                }
            });

            return ids;
        }

        function onValueChanged(e) {
            searchS();
        }
        function searchS() {
            var node = tree.getSelectedNode();
            var ids = "";
            ids = deepTree(ids, tree, node);
            deptId.setValue(ids);
            dynamicTable('jobNumber,name,deptId,salaryGroup,fpqx','jobNumber,name,deptId,salaryGroup,fpqx');
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
            var node = tree.getSelectedNode();
            window.parent.loading();
            $.ajax({
                url: "../salary/salary_second!getAllSecondAmountListData.action",
                type: "POST",
                data : {
                    period: '${period}',
                    companyId: node.branchId,
                    searchData : ss
                },
                success: function(text) {
                    window.parent.unmask();
                    var gridData = mini.decode(text);
                    var jsonData = gridData.data;
                    var arr = [];
                    arr.push({ type: "indexcolumn", headerAlign:"center", width: 40,header: "序列"});
                    arr.push({ field: "id", id:"id", name:"id", headerAlign:"center", width: 40});
                    arr.push({ field: "staffId", id:"staffId", name:"staffId", headerAlign:"center", header: "员工ID"});
                    arr.push({ field:"jobNumber", width:"100", headerAlign:"center", align:"center", header: "工号"});
                    arr.push({ field:"name", width:"100", headerAlign:"center", align:"center", header: "姓名"});
                    arr.push({ field:"groupName", width:"120", headerAlign:"center", align:"center", header: "薪资组"});
                    arr.push({ type: "comboboxcolumn", field:"fpqx", readOnly:true, width:"80", headerAlign:"center", align:"center", header: "分配权限",
                                  editor: { type: "combobox", data: "[{id: 1, text:'公司'},{id: 2, text:'基层'}]"}});
                    arr.push({ field:"salaryPostName", readOnly:true, width:"100", headerAlign:"center", align:"center", header: "具体岗位"});
                    arr.push({ field:"post", width:"100", headerAlign:"center", align:"center", header: "标准岗位"});
                    arr.push({ field:"salaryPost", width:"100", headerAlign:"center", align:"center", header: "薪酬岗位"});
                    arr.push({ field:"specialMark", width:"80", headerAlign:"center", align:"center", header: "特殊标记"});
                    arr.push({ field:"gzxs", width:"80", headerAlign:"center", align:"center", header: "工资形式"});

                    // 循环便利所有的薪资项目
                    if (jsonData.length > 0) {
                    	if (jsonData[0].itemsId) {
	                        var fulis = jsonData[0].itemsId.split(",");
	                        var fulisName = jsonData[0].itemsName.split(",");
	                        var actualName = jsonData[0].actualName.split(",");
	                        for (var i=0; i< fulis.length;i++) {
	                            arr.push({ field: fulis[i] + "yj", readOnly:true, width:"80", headerAlign:"center", align:"center", header: fulisName[i]});
	                            arr.push({ field: fulis[i] + "actual", width:"80", headerAlign:"center", align:"center", header: actualName[i]+"*",
	                                        salaryId: fulis[i], salaryName: fulisName[i],
	                                        vtype: "float;required:true", editor: { type: "textbox", maxLength: 20}});
	                        }
                    	}
                    } else {
                        var statusName = mini.getsbyName("status");
                        for(var ii=0; ii < statusName.length; ii++){
                            var link = statusName[ii];
                            link.show();
                        }
                    }

                    // 是否显示【保存】、【上报】按钮
                    var auth = gridData.auth;
                    var statusName = mini.getsbyName("status");
                    if (auth == 1) {
                        for(var ii=0; ii < statusName.length; ii++){
                            var link = statusName[ii];
                            link.hide();
                        }
                    } else {
                        for(var ii=0; ii < statusName.length; ii++){
                            var link = statusName[ii];
                            link.show();
                        }
                    }

                    // 遍历数据
                    var table = [];
                    for (var i=0; i< jsonData.length;i++) {
                        var row = jsonData[i];
                        var column = {id: row.id, staffId: row.staffId, jobNumber: row.jobNumber, name: row.name, groupName: row.groupName, fpqx: row.fpqx,
                                post: row.post, salaryPostName: row.salaryPostName, salaryPost: row.salaryPost, specialMark: row.specialMark, gzxs: row.gzxs};
                        var objs = eval(column);
                        if (row.itemsId != null) {
                            var itemsId = row.itemsId.split(",");
                            var staticValue = row.staticValue.split(",");
                            var actualAmount = row.actualAmount.split(",");
                            for (var j=0; j< itemsId.length;j++) {
                                var aField = itemsId[j];
                                objs[aField+"yj"] = staticValue[j];
                                objs[aField+"actual"] = actualAmount[j];
                            }
                        }
                        table.push(column);
                    }
                    grid1.setData(table);
                    grid1.set({columns : arr});
                    grid1.hideColumn("id");
                    grid1.hideColumn("staffId");
                    grid1.frozenColumns(0, 11);
                    grid1.setTotalCount(jsonData.length);
                }
            });
        }

        // 保存
        function saveAllAmount() {
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
                    if(row.id == null || row.id == ""||typeof(row.id)== "undefined"){
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
            var col = "", salaryName= "";
            if (len > 11) {
                col = columns[13].salaryId;
                salaryName = columns[13].salaryName;
                for (var i = 14, l = len; i < l; i++) {
                    if (columns[i].salaryId) {
                        col += "," + columns[i].salaryId;
                        salaryName += "," + columns[i].salaryName;
                    }
                }
            }

            window.parent.loading();
            $.ajax({
                url : "../salary/salary_second!saveOrUpdate.action",
                type : "POST",
                data : {
                    companyId: '${companyId}',
                    depId: deptId.getValue(),
                    period: '${period}',
                    fpqx: mini.get("fpqx").getValue(),
                    griddata : gridjson,
                    columns: col,
                    salaryNames: salaryName
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

        // 上报
        function setReport() {
            var data = grid1.getData();
            if(data.length<1){// 空行也算length
                mini.alert("详细信息为空，不能上报！");
                return;
            }
            mini.confirm("上报之后不能再修改数据，确定要上报总量吗？","提醒",function(action) {
                if (action == "ok") {
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_second!updateSetReportStatus.action",
                        type : "POST",
                        data : {
                            companyId: deptId.getValue(),
                            period: '${period}',
                            fpqx: mini.get("fpqx").getValue()
                        },
                        success : function(text) {
                            if (text == "success") {
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "上报成功",
                                    callback: function (action) {
                                        searchS();
                                    }
                                });
                            } else {
                                window.parent.unmask();
                                if(text.length>0){
                                    mini.alert(text);
                                }
                            }
                        }
                    });
                }
            });
        }

        // 取消上报
        function setCancelReport() {
            var data = grid1.getData();
            if(data.length<1){// 空行也算length
                mini.alert("详细信息为空，不能取消上报！");
                return;
            }
            mini.confirm("确定要取消上报总量吗？","提醒",function(action) {
                if (action == "ok") {
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_second!updateSetCancelReportStatus.action",
                        type : "POST",
                        data : {
                            companyId: deptId.getValue(),
                            period: '${period}',
                            fpqx: mini.get("fpqx").getValue()
                        },
                        success : function(text) {
                            if (text == "success") {
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "取消成功",
                                    callback: function (action) {
                                        searchS();
                                    }
                                });
                            } else {
                                window.parent.unmask();
                                if(text.length>0){
                                    mini.alert(text);
                                }
                            }
                        }
                    });
                }
            });
        }

        // 总量验证
        /* function onCellValidation(e) {
            var field = e.field, record = e.record;
            if (field) {
                // 总量比较
                var actualIndex = field.indexOf("actual");
                if (actualIndex > -1) {
                    if (e.column.salaryName.indexOf("绩效考核") >= 0) {
                    //if (e.column.salaryName.indexOf("岗位工资") >= 0) {
                        return;
                    }
                    var sub = field.substring(0, actualIndex);
                    if (e.value != null && parseFloat(e.value) > parseFloat(record[sub+"yj"])) {
                        e.errorText = "实际分配总量不能多于标准量";
                        e.isValid = false;
                    }
                }
            }
        } */

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

        function print() {
            var ss = searchCondition('jobNumber,name,deptId,salaryGroup,fpqx','jobNumber,name,deptId,salaryGroup,fpqx');
            mini.open({
                url : "../salary/salary_second!print.action?id=${id}&period=${period}&companyId=${companyId}&searchData="+ss,
                title : "打印二次分配",
                width : 460,
                height : 180,
                onload : function() {
                },
                ondestroy : function(action) {
                    searchS();
                }
            });
        }

        // 导入模板下载
        function downModel() {
            window.location.href="../salary/secondAdjust.action";
        }

        // 导入
        function importExcelData() {
            var node = tree.getSelectedNode();
            if (node) {
                if (node.flg == 0) {
                    mini.alert("请选择部门");
                    return
                }
                deptId.setValue(node.id);
                var branchId = node.branchId;
                if (branchId) {
                    var url = "../salary/salary_second!uploadSecondItems.action?companyId=" +
                            branchId+"&period=${period}&depId="+deptId.getValue()+"&fpqx=" + mini.get("fpqx").getValue();
                    bigWebUploader.openMini("uploaders","uploadFile", url);
                }
            } else {
                mini.alert("请选择部门");
                return;
            }
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

        function exportExcelData() {
            var node = tree.getSelectedNode();
            var searchData = searchCondition('jobNumber,name,deptId,salaryGroup,fpqx','jobNumber,name,deptId,salaryGroup,fpqx');
            window.location.href="../salary/salary_second!export.action?searchData="+searchData+"&period=${period}&depId="+node.branchId;
        }

        function onNodeClick(e) {
        	var node = tree.getSelectedNode();
            if (node) {
                salaryGroup.setUrl("../salary/salary_second!getAllStaffSalaryGroup.action?companyId="+node.branchId);
                salaryGroup.select(0);
                searchS();
            } else {
                grid1.setData([]);
                grid1.setTotalCount(0);
            }
        }
    </script>
</body>
</html>