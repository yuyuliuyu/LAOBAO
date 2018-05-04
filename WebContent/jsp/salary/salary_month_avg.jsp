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
    <title>维护员工月平均工资</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
    <script src="${base}/template/plugin/public/treeList.js" type="text/javascript"></script>
    <script src="../template/plugin/biguploader/uploader.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
        <div size="16%" showCollapseButton="true">
            <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                <span style="padding-left:5px;">组织机构：</span>
            </div>
            <div class="mini-fit" style=" border-top:0;">
                <ul id="deptree" class="mini-tree" url="../basis/branch!getTreeListByUser.action" style="width:100%;height:100%;"
                    showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                </ul>
            </div>
        </div>
        <div showCollapseButton="true">
             <div class="mini-toolbar" style="padding:0px;border-top:0;">
                <table style="width:100%;">
                    <tr>
                        <td style="width:100%;">
                            &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="onKeyEnter"/>
                            &nbsp;姓名：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('jobNumber,name,effectiveYear','jobNumber,name,effectiveYear')">查询</a>
                            <br><a class="mini-button" iconCls="icon-save" onclick="save()">保存</a>
                            <a class="mini-button" iconCls="icon-upload" onclick="importAccording()">导入</a>
                            <a class="mini-button" iconCls="icon-download" onclick="downModel()">下载模板</a>
                        </td>
                        <td style="width:100%;">
                            <input class="mini-spinner" id="effectiveYear" onenter="onKeyEnter" minValue="0" maxValue="2100"
                                onvaluechanged="onValueChanged"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    allowCellEdit="true" allowCellSelect="true" allowAlternating="true" multiSelect="true" url="../salary/salary_month!getMonthAvgSalary.action"
                    idField="id" allowResize="false" >
                    <div property="columns">
                        <div type="checkcolumn" width="40"></div>
                        <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                        <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                        <div field="jobNumber" width="80" headerAlign="center" align="center">工号</div>
                        <div field="name" width="80" headerAlign="center" align="center">姓名</div>
                        <div field="companyName" width="120" headerAlign="center" align="center">公司</div>
                        <div field="deptName" width="80" headerAlign="center" align="center">部门</div>
                        <div field="averageSalary" width="80" headerAlign="center" align="center" vtype="required;float">平均工资
                            <input property="editor" class="mini-textbox" maxLength="20"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <form id="uploaders" method="post" enctype="multipart/form-data" encoding="multipart/form-data" target="appFrame" style="display: none;">
       <input class="mini-htmlfile" id="uploadFile" name="uploadFile" limitType="*.xls;*.xlsx" onfileselect= "onFileSelected"/>
    </form>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        var deptree = mini.get("deptree");
        var effectiveYear = mini.get("effectiveYear");
        var myDate = new Date();
        effectiveYear.setValue(myDate.getFullYear());
        $(function() {
        	$("#searchButton").click();
        });

        function save() {
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
                for ( var i = 0, l = data.length; i < l; i++) {
                    var row = data[i];
                    if(row.id == null || row.id == ""||typeof(row.id)== "undefined"){
                        row._state="added";
                        grid.updateRow(row,row);
                    }else{
                        row._state="modified";
                        grid.updateRow(row,row);
                    }
                }
                var griddata = grid.getChanges();
                gridjson= mini.encode(griddata);
            }

            window.parent.loading();
            $.ajax({
                url : "../salary/salary_month!saveOrUpdateMonthAvgSalary.action",
                type : "post",
                data : {
                    year : effectiveYear.getValue(),
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
                                grid.reload();
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

        grid.on("drawCell", function(e) {
            // 工号
            if (e.field=="jobNumber") {
                //var row = e.row;
                //e.cellHtml=row.year+"年"+row.month+"月";
            }
        });

        function onValueChanged(e) {
            $("#searchButton").click();
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

        // 导入
        function importAccording() {
            var url = "../salary/salary_month!beachCheck.action";
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
                grid.reload();
            } else {
                mini.alert(data);
            }
        }

        // 平均工资模板下载
        function downModel() {
            window.location.href="../salary/avgSalary.action";
        }
    </script>
</body>
</html>