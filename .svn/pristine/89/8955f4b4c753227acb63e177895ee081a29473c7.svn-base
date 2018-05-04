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
    <title>员工工资调整</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
</head>

<body>
    <input class="mini-hidden" name="id" id="id" value="${id}" style="display:none;"/>
    <div class="mini-fit">
        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;"pageSize="20"
            allowCellEdit="true" allowCellSelect="true" allowAlternating="true" idField="id" allowResize="false"
            showPager="false" borderStyle="border-top : 1px solid #C9C9C9;"
            oncellendedit="onCellEndEdit">
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOKClose()" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");

        $(function() {
            dynamicTable();
        });

        function dynamicTable() {
            $.ajax({
                url: "../salary/salary_record!getStaffForAdjustListData.action",
                type: "POST",
                data : {
                    ids : '${ids}'
                },
                success: function(text) {
                    var jsonData = mini.decode(text);
                    var arr = [];
                    arr.push({ type: "indexcolumn", headerAlign:"center", width: 40,header: "序列"});
                    arr.push({ field: "id", id:"id", name:"id", headerAlign:"center", width: 40});
                    arr.push({ field:"jobNumber", width:"80", headerAlign:"center", align:"center", header: "工号"});
                    arr.push({ field:"name", width:"100", headerAlign:"center", align:"center", header: "姓名"});
                    arr.push({ field:"xs", id:"xs", name:"xs", width:"100", headerAlign:"center", align:"center", header: "精确度"});
                    arr.push({ field:"wmId", id:"wmId", name:"wmId", width:"100", headerAlign:"center", align:"center", header: "项目ID"});
                    if (jsonData.length > 0) {
                        if (jsonData[0].wmName != null) {
                            var jSon = jsonData[0].wmName.split(",");
                            for (var i=0; i< jSon.length;i++) {
                                arr.push({ field:"a"+i, width:"80", headerAlign:"center", align:"center", header: jSon[i], vtype:"float", editor: { type: "textbox"}});
                            }
                        }
                        grid1.set({columns : arr});
                        grid1.hideColumn("id");
                        grid1.hideColumn("wmId");
                        grid1.hideColumn("xs");
                        var dd = jsonData;
                        var table = [];
                        for (var i=0; i< jsonData.length;i++) {
                            var row = dd[i];
                            var column = {id: row.id, jobNumber: row.jobNumber, name: row.name, xs: row.xs, wmId: row.wmId, svId: row.svId};
                            var objs = eval(column);
                            if ((dd)[i].wmName != null) {
                                var jSon = (dd)[i].staticValue.split(",");
                                for (var j=0; j< jSon.length;j++) {
                                    var aa = "a"+j;
                                    objs[aa] = jSon[j];
                                }
                            }
                            table.push(column);
                        }
                        grid1.clearRows();
                        grid1.addRows(table);
                        grid1.accept();
                    }
                }
            });
        }

        function onCellEndEdit(e) {
            var field = e.field, record = e.record;
            // 将值转换为目标格式
            var data = record.xs;

            if (data) {
                var staticV = data.split(",");
                var chai = field.substring(1);
                for (var i=0; i<staticV.length; i++) {
                    if (i==chai) {
                        var aa = "a"+i;
                        var v = parseFloat(record[aa]).toFixed(staticV[i]);
                        var column = {};
                        var objs = eval(column);
                        objs[aa] = v;
                        grid1.updateRow(record, objs);
                        return;
                    }
                }
            }
        }

        function onOKClose() {
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
                // 判断grid是否多个是否发薪
                for ( var i = 0, l = data.length; i < l; i++) {
                    var row = data[i];
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

            window.parent.loading();
            $.ajax({
                url : "../salary/salary_record!saveOrUpdateToStaffSalary.action",
                type : "post",
                data : {
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
                                dynamicTable();
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