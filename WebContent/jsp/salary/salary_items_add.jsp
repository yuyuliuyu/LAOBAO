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
    <title>薪资项目增加、编辑</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top"style="width:20%">公司：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" id="id" name="id" value="${salaryItems.id}"/>
                    <input class="mini-combobox asLabel" readOnly="true" id="depId" name="depId" textField="fzx" url="../salary/salary_manage!getBranches.action" value="${depId}" style="width:90%"/>
                </td>
                <td class="list_left_box1" valign="top"style="width:20%">*薪资项目名称：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" name="name" value="${salaryItems.name }" style="width:90%" required="true" maxLength="100"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*保留小数：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" vtype="range:0,8"name="numberAccuracy" value="${salaryItems.numberAccuracy == null? 0 : salaryItems.numberAccuracy}" style="width:90%" required="true" minValue="0" maxValue="8"/>
                </td>
                <td class="list_left_box1" valign="top">*增减属性：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" name="addOrLess" value="${salaryItems.addOrLess == null? 1 : salaryItems.addOrLess}" style="width:90%" required="true"
                        data="[{id:1,text:'加'},{id:2,text:'减'},{id:3,text:'其它项'}]"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*固定薪资项目：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" name="fixed" value="${salaryItems.fixed == null? 0 : salaryItems.fixed}" style="width:90%" required="true"
                        data="[{id:0,text:'否'},{id:1,text:'是'}]"/>
                </td>
                <td class="list_left_box1" valign="top">*异动项目：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" name="yd" value="${salaryItems.yd == null? 0 : salaryItems.yd}" style="width:90%" required="true"
                        data="[{id:0,text:'否'},{id:1,text:'是'}]"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*标准类项目：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" name="bzl" value="${salaryItems.bzl == null? 0 : salaryItems.bzl}" style="width:90%" required="true"
                        data="[{id:0,text:'否'},{id:1,text:'是'}]"/>
                </td>
                <td class="list_left_box1" valign="top">*年金类项目：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" name="njl" value="${salaryItems.njl == null? 0 : salaryItems.njl}" style="width:90%" required="true"
                        data="[{id:0,text:'否'},{id:1,text:'是'}]"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*统筹类项目：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" name="tcl" value="${salaryItems.tcl == null? 0 : salaryItems.tcl}" style="width:90%" required="true"
                        data="[{id:0,text:'否'},{id:1,text:'是'}]"/>
                </td>
                <td class="list_left_box1" valign="top">*项目类型：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" name="itemType" value="${salaryItems.itemType }" style="width:90%" required="true"
                        url="../system/dectionary!listjson.action?id=402881945cc8c4c8015cc926f00d0008" textField="dicname" valueField="dicvalue"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*项目属性：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" name="itemPro" value="${salaryItems.itemPro }" style="width:90%" required="true"
                        url="../system/dectionary!listjson.action?id=402881945cc8c4c8015cc92867ae000e" textField="dicname" valueField="dicvalue"/>
                </td>
                <td class="list_left_box1" valign="top">*是数值：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" name="isNumber" value="${salaryItems.isNumber == null ? 1: salaryItems.isNumber }" style="width:90%" required="true"
                        data="[{id:0,text:'否'},{id:1,text:'是'}]"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*顺序：</td>
                <td class="list_right_box" >
                    <input class="mini-spinner" name="sx" value="${salaryItems.sx }" style="width:90%" required="true" minValue="0" maxValue="99999999"/>
                </td>
                <td class="list_left_box1" valign="top">*是否编辑：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" name="bj" value="${salaryItems.bj == null? 0 : salaryItems.bj }" style="width:90%" required="true"
                        data="[{id:0,text:'否'},{id:1,text:'是'}]"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*状态：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" name="type" value="${salaryItems.type == null? 1 : salaryItems.type }" style="width:90%" required="true"
                        data="[{id:0,text:'无效'},{id:1,text:'有效'}]"/>
                </td>
                <td class="list_left_box1" valign="top">*档案是否显示：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" data="[{id:0,text:'不显示'},{id:1,text:'显示'}]"name="isDisplay"value="${salaryItems.isDisplay == null? 0: salaryItems.isDisplay}"
                        style="width: 90%"required="true"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div class="mini-toolbar" style="padding:0px;border-top:0;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" name="editStatus" onclick="addRow()">增加</a>
                        <a class="mini-button" iconCls="icon-remove" name="editStatus" onclick="removeRow()">删除</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_items!getSalaryItemAndTypeListData.action?id=${salaryItems.id}" pageSize="20"
                showpager="false" idField="id" allowResize="false" borderStyle="border-top:1px solid #C9C9C9"multiSelect="true"
                allowCellEdit="true" allowCellSelect="true" oncellvalidation="onMoneyCellValidation">
                <div property="columns">
                    <div type="checkcolumn" width="20"></div>
                    <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                    <div type="indexcolumn" headeralign="center" width="20">序列</div>
                    <div field="typeId" displayField="name" width="200" headerAlign="center" align="center"vType="required;">项目类别*
                        <input property="editor" class="mini-buttonedit" onbuttonclick="onButtonEditAlert"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onSaveOrUpdate()" style="width:60px;margin-right:20px;" iconCls="icon-save" name="editStatus">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        var form = new mini.Form("#form1");

        $(function() {
            var isEdit = '${isEdit}';
            if (isEdit == 0) {
                form.setEnabled(false);
                grid.setAllowCellEdit(false);
                var editStatus = mini.getsbyName("editStatus");
                for(var i=0; i < editStatus.length; i++){
                    var link = editStatus[i];
                    link.disable();
                }
            }
        });

        function onButtonEditAlert(e) {
            onButtonEdit(e.sender, "薪资项目类别", "../salary/salary_items!tree.action", 280, 300,'name',"id,depId","id,depId");
        }

        function onSaveOrUpdate() {
            // 表单部分
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
                var hash = {};
                for ( var i = 0, l = data.length; i < l; i++) {
                    var row = data[i];
                    // 判断存在相同的薪资类别
                    var compare = row.typeId;
                    if(hash[compare]) {
                        mini.alert("存在相同的 <font color='red'>"+row.name +"</font> 薪资类别！");
                        return;
                    }
                    hash[compare] = true;
                    if(row.id == ""||typeof(row.id)== "undefined"){
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
                url : "../salary/salary_items!saveOrUpdateItems.action",
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

        /**
         * 单元格验证
         * zhanghj
         **/
        function onMoneyCellValidation(e) {
            // 项目类别
            if (e.field == "typeId") {
                if (e.value =="") {
                    e.errorText = "不能为空";
                    e.isValid = false;
                }
            }
        }
    </script>
</body>
</html>