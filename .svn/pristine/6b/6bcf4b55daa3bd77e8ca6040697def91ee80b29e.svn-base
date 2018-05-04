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
    <title>薪资项目一级类别增加、编辑</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1">
        <div class="mini-toolbar" style="padding:0px;border-top:0;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-save" onclick="onOk()">保存</a>
                        <a class="mini-button" iconCls="icon-add" id="addNext" onclick="add()">增加下一级</a>
                    </td>
                </tr>
            </table>
        </div>
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width: 15%">公司：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" name="depId" value="${depId}"/>
                    <input class="mini-hidden" name="id" value="${id}"/>
                    <input class="mini-hidden" name="pid" value="-1"/>
                    <input class="mini-textbox asLabel" readOnly = "true" required="true" value="${depName}" style="width: 90%"/>
                </td>
                <td class="list_left_box1" valign="top"style="width: 15%">*名称：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" name="name"style="width: 90%" required="true" value="${name}" maxLength="100"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top"style="width: 15%">描述：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" name="note" style="width:100%;height: 60px" value="${note}" maxLength="1000"/>
                </td>
            </tr>
        </table>
    </div>

    <script type="text/javascript">
        mini.parse();

        function add() {
        	var itemFrame = window.parent.document.getElementById("itemFrame");
            itemFrame.src = "../salary/salary_items!typelevel.action?depId=${depId}&pid=${id}";
        }

        function onOk(){
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
                for ( var i = 0, l = data.length; i < l; i++) {
                    var row = data[i];
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
            window.parent.parent.loading();
            $.ajax({
                url : "../salary/salary_items!saveOrUpdate.action",
                type : "post",
                data : {
                    formdata : formjson,
                    griddata : gridjson
                },
                success : function(text) {
                	window.parent.parent.unmask();
                    if (text == "success") {
                    	window.parent.parent.mini.showMessageBox({
                            title: "提醒",
                            width: 250,
                            iconCls: "mini-messagebox-warning",
                            buttons: ["ok"],
                            message: "保存成功",
                            callback: function (action) {
                                document.location.reload();
                                var node = window.parent.tree.getSelectedNode();
                                if (node) {
                                    var dId = "";
                                    if (node.pid != "-1") {
                                        dId = node.branchId;
                                    }
                                    window.parent.treeLevel.load({depId: dId});
                                }
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