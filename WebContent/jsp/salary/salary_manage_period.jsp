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
    <title>设置薪酬区间</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-splitter" id="form1" style="width:100%;height:100%;">
        <div size="16%" showCollapseButton="true">
            <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                <span style="padding-left:5px;">组织机构：</span>
            </div>
            <div class="mini-fit" style=" border-top:0;">
                <ul id="tree1" class="mini-tree" url="../basis/branch!getTreeListByUser.action" style="width:100%;height:100%;"
                    showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                </ul>
            </div>
        </div>
        <div showCollapseButton="true">
             <div class="mini-toolbar" style="padding:0px;border-top:0;">
                 <table style="width:100%;">
                     <tr>
                         <td style="width:100%;">
                             <a class="mini-button" iconCls="icon-add" onclick="addNew()">增加</a>
                             <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                             <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
                             &nbsp;年度：
                             <input class="mini-textbox" vtype="int" id="year" style="width:80px" onenter="onKeyEnter"/>
                             &nbsp;发薪频率：
                             <input class="mini-combobox" id="payPerid" showNullItem= "true" data="[{id:0, text:'周'},{id:1, text:'双周'},{id:2, text:'半月'},{id:3, text:'月'}]"
                                style="width:80px" onenter="onKeyEnter"/>
                             <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('year,payPerid','year,payPerid')">查询</a>
                         </td>
                     </tr>
                 </table>
             </div>
             <div class="mini-fit">
                 <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_manage!getSalaryPeriodListData.action" pageSize="20"
                     allowAlternating="true" idField="id" allowResize="false"multiSelect="true">
                     <div property="columns">
                         <div type="checkcolumn" width="40"></div>
                         <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                         <div type="comboboxcolumn" field="depId" displayField="fzx" width="150" headerAlign="center" align="center">公司
                            <input property="editor" class="mini-combobox" textField="fzx" url="../salary/salary_manage!getBranches.action"/>
                         </div>
                         <div field="year" width="80" headerAlign="center" align="center">年度</div>
                         <div type="comboboxcolumn" field="payPerid" width="100" headerAlign="center" align="center">发薪频率
                            <input property="editor" class="mini-combobox" data="[{id:0, text:'周'},{id:1, text:'双周'},{id:2, text:'半月'},{id:3, text:'月'}]"/>
                         </div>
                         <div field="dayHour" width="80" headerAlign="center" align="center">标准日工作小时</div>
                         <div field="day" width="60" headerAlign="center" align="center">标准计薪天数</div>
                     </div>
                 </div>
             </div>
             </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.hideColumn("id");

        var tree = mini.get("tree1");
        tree.on("nodeselect", function (e) {
            var node = tree.getSelectedNode();
            if (node) {
                var dId = "";
                if (node.pid != "-1") {
                    dId = node.branchId;
                }
                grid.load({depId: dId});
            } 
        });

        function addNew() {
            if (tree) {
                var node = tree.getSelectedNode();
                if (node) {
                    var branchId = node.branchId;
                    if (branchId) {
                        mini.open({
                            url : "../salary/salary_manage!periodadd.action?depId=" + branchId,
                            title : "增加薪资区间",
                            width : 680,
                            height : 580,
                            onload : function() {
                            },
                            ondestroy : function(action) {
                                grid.reload();
                            }
                        });
                    }
                } else {
                    mini.alert("请选择公司");
                    return;
                }
            }
        }

        function edit() {
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url : "../salary/salary_manage!periodedit.action?id=" + row.id,
                    title : "编辑薪资区间",
                    width : 680,
                    height : 580,
                    onload : function() {
                    },
                    ondestroy : function(action) {
                        grid.reload();
                    }
                });
            } else {
                mini.alert("请选中一条记录！");
            }
        }

        function remove() {
            var rows = grid.getSelecteds();
            if (rows.length == 0) {
                mini.alert("请选中一条记录！");
                return;
            } else {
                var ids = rows[0].id;
                for (var i=1, l=rows.length;i < l; i++) {
                    ids += "," + rows[i].id;
                }
                mini.confirm("确定要删除吗？", "提醒", function(action) {
                    if (action == "ok") {
                        window.parent.loading();
                        $.ajax({
                            url: "../salary/salary_manage!remove.action",
                            type:"POST",
                            data: {
                                ids : ids
                            },
                            success: function(text) {
                                window.parent.unmask();
                                if ("success" == text) {
                                    mini.alert("删除成功");
                                    grid.reload();
                                } else {
                                    mini.alert(text);
                                }
                            }
                        });
                    }
                });
            }
        }

        function onKeyEnter(){
            if((event.keyCode || event.which) == 13){
                $("#searchButton").click();
            }
        }
    </script>
</body>
</html>