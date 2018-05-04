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
    <title>工资条列表</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top">薪资组名称：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" id="id" name="id" value="${id}"/>
                    <input class="mini-hidden" id="companyId" name="companyId" value="${companyId}"/>
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" value="${groupName}"/>
                </td>
                <td class="list_left_box1" valign="top">工资套名称：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" value="${wageName}"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <a class="mini-button" iconCls="icon-add" onclick="addWindows()">增加</a>
                    <a class="mini-button" iconCls="icon-edit" onclick="editWindows()">编辑</a>
                    <a class="mini-button" iconCls="icon-remove" onclick="remove('','salary','salary_wages',false)">删除</a>
                    <a class="mini-button" iconCls="icon-filter" onclick="saveDefault()">设置默认</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_wages!getSalaryWageListData.action?id=${id}" pageSize="20"
            allowAlternating="true" idField="id" allowResize="false">
            <div property="columns">
                <div type="checkcolumn" width="40">选择</div>
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div type="indexcolumn" headeralign="center"  width="60">序列</div>
                <div field="name" width="200" headerAlign="center" align="center">工资条名称</div>
                <div type="comboboxcolumn" field="isDefault" width="200" headerAlign="center" align="center">默认工资条
                    <input property="editor" class="mini-combobox" style="width:100%;" data="[{id:0,text:'否'},{id:1,text:'是'}]" />
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">关闭</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");

        function addWindows() {
            mini.open({
                url : "../salary/salary_wages!add.action?groupWageAndTypeId=${id}&companyId="+mini.get("companyId").getValue(),
                title : "增加工资条",
                width : 720,
                height : 540,
                onload : function() {
                },
                ondestroy : function(action) {
                    grid.reload();
                }
            });
        }

        function editWindows() {
        	var rows = grid.getSelecteds();
            if(rows.length!=1){
                mini.alert("请选择一条记录!");
                return;
            }
            var row=rows[0];
            mini.open({
                url : "../salary/salary_wages!edit.action?id="+row.id+"&groupWageAndTypeId=${id}&companyId="+mini.get("companyId").getValue(),
                title : "编辑工资条",
                width : 720,
                height : 540,
                onload : function() {
                },
                ondestroy : function(action) {
                    grid.reload();
                }
            });
        }

        function saveDefault() {
            var rows = grid.getSelecteds();
            if(rows.length!=1){
                mini.alert("请选择一条记录!");
                return;
            }
            var row=rows[0];
            $.ajax({
                url: "../salary/salary_wages!saveDefault.action",
                data : {
                    id : row.id
                },
                type:"post",
                success : function(text) {
                    if (text == "success") {
                        mini.alert("设置成功");
                        grid.reload();
                    } else
                        mini.alert(text);
                }
            });
        }

        /** 关闭窗口 */
        function CloseWindow(action) {
            if (window.CloseOwnerWindow)
                return window.CloseOwnerWindow(action);
            else
                window.close();
        }
        /** 关闭按钮点击事件 */
        function onCancel() {
            CloseWindow("cancel");
        }
    </script>
</body>
</html>