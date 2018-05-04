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
    <title>工资单控制列表</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
    <script src="${base}/template/plugin/public/treeList.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
        <div size="16%" showCollapseButton="true">
            <div class="mini-toolbar" style="padding:2px;border-top:0;border-center:0;border-right:0;">
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
                            &nbsp;账号：<input class="mini-textbox" id="userName" onenter="onKeyEnter"/>
                            &nbsp;姓名：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('userName,name','userName,name')">查询</a>
                            <br><a class="mini-button" iconCls="icon-add" onclick="addWage()">增加</a>
                            <a class="mini-button" iconCls="icon-edit" onclick="editWage()">编辑</a>
                            <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    url="../salary/salary_pager!getPayRollsData.action" allowCellWrap="true"
                    allowAlternating="true" idField="id" allowResize="false" multiSelect="true">
                    <div property="columns">
                        <div type="checkcolumn" width="40"></div>
                        <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                        <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                        <div field="userName" width="80" headerAlign="center" align="center">账号</div>
                        <div field="staffName" width="80" headerAlign="center" align="center">姓名</div>
                        <div field="companyName" width="140" headerAlign="center" align="center">公司</div>
                        <div field="deptName" width="120" headerAlign="center" align="center">部门</div>
                        <div type="comboboxcolumn" field="watchType" width="80" headerAlign="center" align="center">可查看类型
                            <input property="editor" class="mini-combobox" data="[{id:1, text:'连续薪酬期间个数'},{id:2, text:'可查看年份'},{id:3, text:'可查看哪几个薪酬期间'}]"/>
                        </div>
                        <div field="typeValueName" width="200" headerAlign="center" align="center">类型值</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.hideColumn("id");
        var tree = mini.get("deptree");
        $(function() {
            tree.selectNode(tree.getRootNode().children[0]);
            $("#searchButton").click();
        });

        function addWage() {
            mini.open({
                url : "../salary/salary_pager!rollAdd.action",
                title : "新增工资单月份控制",
                width : 680,
                height : 260,
                onload : function() {
                },
                ondestroy : function(action) {
                    grid.reload();
                }
            });
        }

        function editWage() {
            var row = grid.getSelected();;
            if (!row) {
                mini.alert("请选择一条数据");
                return;
            }
            mini.open({
                url : "../salary/salary_pager!rollEdit.action?id=" + row.id,
                title : "编辑工资单月份控制",
                width : 680,
                height : 260,
                onload : function() {
                },
                ondestroy : function(action) {
                    grid.reload();
                }
            });
        }
    </script>
</body>
</html>