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
    <title>选择薪资组</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-toolbar" id="form1" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="white-space:nowrap;">
                    &nbsp;薪资组名称：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('name','name')">查询</a>
                </td>
            </tr>
        </table>
    </div>

    <div class="mini-fit">
        <div class="mini-fit">
            <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_items!getSalaryGroupWageListData.action?companyId=${companyId}" pageSize="20"
                allowAlternating="true" idField="id" allowResize="false" onrowdblclick="onRowDblClick">
                <div property="columns">
                    <div type="checkcolumn" width="40">选择</div>
                    <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                    <div field="sgName" width="120" headerAlign="center" align="center">薪资组名称</div>
                    <div field="name" width="120" headerAlign="center" align="center">薪资组工资套</div>
                    <div type="comboboxcolumn" field="companyId" displayField="fzx" width="150" headerAlign="center" align="center">公司
                        <input property="editor" class="mini-combobox" textField="fzx" url="../salary/salary_manage!getBranches.action"/>
                    </div>
                    <div type="comboboxcolumn" field="type" width="100" headerAlign="center" align="center">状态
                        <input property="editor" class="mini-combobox" data="[{id:0,text:'无效'},{id:1,text:'有效'}]"/>
                    </div>
                    <div field="effectDate" width="140" headerAlign="center" align="center" renderer="ondayRenderer2">生效日期</div>
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOKClose()" style="width:60px;margin-right:20px;" iconCls="icon-save">确定</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">关闭</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.load();
        grid.hideColumn("id");

        /**
         * 获取一行
         * zhanghj
         **/
        function GetData() {
            var row = grid.getSelected();
            row.groupName= row.name;
            return row;
        }

        /**
         * 双击获取数据
         * zhanghj
         **/
        function onRowDblClick(e) {
            onOKClose();
        }

        /**
         * 获取数据
         * zhanghj
         **/
        function onOKClose() {
            var row = grid.getSelected();
            if (!row) {
                mini.alert("请选中一条记录！");
                return;
            }
            CloseWindow("ok");
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