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
    <title>员工选择</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/treeList.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit">
        <div class="mini-splitter" style="width:100%;height:100%;">
            <div size="28%" showCollapseButton="true">
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
                                <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchs()">查询</a>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="mini-fit">
                    <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                        url="../salary/salary_business!getAllPersonalListData.action"
                        allowAlternating="true" idField="id" allowResize="false">
                        <div property="columns">
                            <div type="checkcolumn" width="40">选择</div>
                            <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                            <div type="indexcolumn" headeralign="center"  width="38">序列</div>
                            <div field="jobNumber" width="80" headerAlign="center" align="center">工号</div>
                            <div field="name" width="100" headerAlign="center" align="center">姓名</div>
                            <div field="company" width="100" headerAlign="center" align="center">公司</div>
                            <div field="deptName" width="60" headerAlign="center" align="center">部门</div>
                            <div field="specificPost" width="80" headerAlign="center" align="center">岗位</div>
                            <div type="comboboxcolumn" field="onJob" width="60" headerAlign="center" align="center">员工状态
                                <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935baceaa1015bae044344001a"
                                    textField="dicname" valueField="dicvalue"/>
                            </div>
                            <div type="comboboxcolumn" field="onPost" width="60" headerAlign="center" align="center">在岗状态
                                <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955bc84595015bc87aa73e0010"
                                    textField="dicname" valueField="dicvalue"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOKClose()" style="width:60px;margin-right:20px;" iconCls="icon-save">选择</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.hideColumn("id");
        var deptree = mini.get("deptree");
        var choseNames = "";

        $(function() {
            searchs();
        });

        function searchs() {
            search('jobNumber,name','jobNumber,name');
        }

        function GetData() {
            return choseNames;
        }

        function onOKClose() {
            var row = grid.getSelected();
            if (!row) {
                mini.alert("请选择一条数据");
                return;
            }
            choseNames = row;
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