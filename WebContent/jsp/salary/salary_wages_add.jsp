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
    <title>工资条增加</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width: 18%">*工资条名称：</td>
                <td class="list_right_box" >
                    <input class="mini-hidden" id="id" name="id" value="${salaryPart.id}"/>
                    <input class="mini-hidden" name="groupWageAndTypeId" value="${groupWageAndTypeId}"/>
                    <input class="mini-hidden" id="companyId" name="companyId" value="${companyId}"/>
                    <input class="mini-textbox"style="width: 90%" name="name" value="${salaryPart.name}" maxLength="100" required="true"/>
                </td>
                <td class="list_left_box1" valign="top"style="width: 22%">值为0时不发送工资条：</td>
                <td class="list_right_box">
                    <input class="mini-combobox"style="width: 90%" name="noSend" textField="name" showNullItem="true"
                        url="../salary/salary_items!getValueIsZeroNoSendRecords.action?id=${groupWageAndTypeId}" value="${salaryPart.noSend}"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">描述：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" style="width: 90%;height:60px" name="note" value="${salaryPart.note}"maxLength="1000"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <a class="mini-button" iconCls="icon-add" onclick="chooseItems()">选择薪资项目</a>
                    <a class="mini-button" iconCls="icon-upgrade" onclick="moveup(1)">上移</a>
                    <a class="mini-button" iconCls="icon-downgrade" onclick="movedown(1)">下移</a>
                    <a class="mini-button" onclick="moveup(2)">置顶</a>
                    <a class="mini-button" onclick="movedown(2)">置底</a>
                    <a class="mini-button" iconCls="icon-remove" onclick="removeRow()">删除</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_wages!getSalaryWageItemsListData.action?id=${salaryPart.id}" pageSize="20"
            allowAlternating="true" idField="id" allowResize="false" showPager="false" multiSelect="true"
            allowCellEdit="true" allowCellSelect="true">
            <div property="columns">
                <div type="checkcolumn" width="40"></div>
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div type="indexcolumn" headeralign="center"  width="60">序列</div>
                <div field="itemId" name="itemId" width="200" headerAlign="center" align="center">薪资项目ID</div>
                <div field="name" width="120" headerAlign="center" align="center">薪资项目</div>
                <div type="comboboxcolumn" field="isZreo" width="130" headerAlign="center" align="center" vtype="required;">值为0时显示项目
                    <input property="editor" class="mini-combobox" style="width:100%;" data="[{id:0,text:'不显示'},{id:1,text:'显示'}]" />
                </div>
                <div field="sx" width="60" headerAlign="center" align="center" vtype="required;">显示顺序
                    <input property="editor" class="mini-spinner" style="width:100%;"minValue="0" maxValue="999999"/>
                </div>
                <div field="note" width="150" headerAlign="center" align="center" vtype="maxLength:1000">描述
                    <input property="editor" class="mini-textbox" style="width:100%;"/>
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOk()" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.hideColumn("itemId");
        grid.hideColumn("id");
        grid.load();

        function chooseItems() {
        	var data = grid.getData();
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
                width : 840,
                height : 540,
                onload : function() {
                },
                ondestroy : function(action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        if (data.length > 0) {
                            var eLen = grid.getData().length;
                            var r = [];
                            for(var i=0,l=data.length;i<l;i++){
                                var newRow = {itemId: data[i].id,name: data[i].name,isZreo:1, sx: i+1+eLen};
                                r.push(newRow);
                            }
                            grid.addRows(r);
                        }
                    }
                }
            });
        }

        function onOk() {
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
            var grid=mini.get("grid");
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
                // 判断grid是否存在相同的薪资项目
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
                    if(row.itemId != undefined && hash[row.itemId]) {
                        mini.alert("存在相同的薪资项目：<font color='red'>"+row.name +"</font>");
                        return;
                    }
                    if (row.changeItem != 1) {
                        hash[row.itemId] = true;
                    }
                }
                var griddata = grid.getChanges();
                gridjson= mini.encode(griddata);
            }
            window.parent.loading();
            $.ajax({
                url : "../salary/salary_wages!saveOrUpdate.action",
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
                                CloseWindow("ok");
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
        function removeRow() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                grid.removeRows(rows, false);
            }else{
                mini.alert("请选择需要删除的记录!");
            }
        }

        function moveup(t) {
            var row = grid.getSelected();
            if (row) {
                var index = grid.indexOf(row);
                var local = 0;
                if (t==1) {
                    local = index - 1;
                } else {
                    local = 0;
                }

                // 组合顺序
                var zu = [];
                for (var i=local;i<index+1;i++) {
                    var r = grid.getRow(i);
                    zu[i] = r.sx;
                }
                grid.moveRow(row, local);
                for (var i=local;i<index+1;i++) {
                    var r = grid.getRow(i);
                    grid.updateRow(r, {sx: zu[i]});
                }
            }
        }

        function movedown(t) {
            var row = grid.getSelected();
            if (row) {
                var index = grid.indexOf(row);
                var local = 0;
                if (t==1) {
                    local = index + 2;
                }
                else {
                    local = grid.getData().length;
                }

                // 组合顺序
                var zu = [];
                for (var i=index;i<local;i++) {
                    var r = grid.getRow(i);
                    zu[i] = r.sx;
                }
                grid.moveRow(row, local);
                for (var i=index;i<local;i++) {
                    var r = grid.getRow(i);
                    grid.updateRow(r, {sx: zu[i]});
                }
            }
        }

    </script>
</body>
</html>