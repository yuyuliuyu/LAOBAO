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
    <title>薪酬岗位字典增加、编辑</title>
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
                <td class="list_left_box1" valign="top"style="width:20%">*基础工资：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" id="id" name="id" value="${salaryBaseDictionary.id}"/>
                    <input class="mini-textbox" name="name" value="${salaryBaseDictionary.jcgz}" style="width:90%" required="true" maxLength="25"/>
                </td>
                <td class="list_left_box1" valign="top">*职级类别：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" value="${salaryBaseDictionary.zjlb}" name="zjlb" style="width: 90%"popupWidth="auto" textField="dicname" valueField="dicvalue"
                        required="true" url="../system/dectionary!listjson.action?id=402881945bea6f07015beab8758e000d"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*系数：</td>
                <td class="list_right_box">
                    <input class="mini-spinner" name="xs" value="${salaryBaseDictionary.xs}" style="width:90%" required="true" minValue="0" maxValue="999999"/>
                </td>
                <td class="list_left_box1" valign="top">*内容：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" name="content" value="${salaryBaseDictionary.content}" style="width:90%" required="true" maxLength="10"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">显示：</td>
                <td class="list_right_box" >
                    <input class="mini-textbox" name="display" value="${salaryBaseDictionary.display}" style="width:90%" required="true" maxLength="25"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOk('salary','salary_dept', '', false)" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();

        function onSaveOrUpdate() {
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
    </script>
</body>
</html>