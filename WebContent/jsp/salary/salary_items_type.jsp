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
    <title>薪资项目类别</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
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
             <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
                <div showCollapseButton="true" size="30%">
                    <div class="mini-toolbar" style="padding:0px;border-top:0;">
                        <table style="width:100%;">
                            <tr>
                                <td style="width:100%;">
                                    <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                                    <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
                                    <a class="mini-button" iconCls="icon-lock" onclick="setControl()">设置管控</a>
                                    <a class="mini-button" iconCls="icon-unlock" onclick="setNoControl()">取消管控</a>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="mini-fit">
                        <ul id="treeLevel" class="mini-tree" url="../salary/salary_items!getItemsTypeListsData.action" style="width:100%;"
                            showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                        </ul>
                    </div>
                </div>
                <div showCollapseButton="true">
                    <iframe id="itemFrame" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
                </div>
             </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var tree = mini.get("tree1");
        tree.on("nodeselect", function (e) {
            var node = tree.getSelectedNode();
            if (node) {
                var dId = "";
                if (node.pid != "-1") {
                    dId = node.branchId;
                }
                treeLevel.load({depId: dId});
            }
        });

        var treeLevel = mini.get("treeLevel");
        treeLevel.on("nodeselect", function (e) {
            var node = treeLevel.getSelectedNode();
            if (node) {
                var itemFrame = document.getElementById("itemFrame");
                if (node.pid == "-1") {
                    itemFrame.src = "../salary/salary_items!typeedit.action?id="+node.id;
                } else {
                    itemFrame.src = "../salary/salary_items!typeleveledit.action?id="+node.id;
                }
            } 
        });

        function add() {
            if (tree) {
                var node = tree.getSelectedNode();
                if (node) {
                    var branchId = node.branchId;
                    if (branchId) {
                        var itemFrame = document.getElementById("itemFrame");
                        itemFrame.src = "../salary/salary_items!typeadd.action?depId=" + branchId;
                    }
                } else {
                    mini.alert("请选择公司");
                    return;
                }
            }
        }

        function remove() {
            var node = treeLevel.getSelectedNode();
            if (node) {
                mini.confirm("确定要删除？", "提醒", function(action) {
                    if (action == "ok") {
                        window.parent.loading();
                        $.ajax({
                            url: "../salary/salary_items!remove.action",
                            type:"POST",
                            data: {
                                id: node.id
                            },
                            success: function(text) {
                                window.parent.unmask();
                                if (text == "success") {
                                    mini.alert("删除成功");
                                    var node = tree.getSelectedNode();
                                    if (node) {
                                        var dId = "";
                                        if (node.pid != "-1") {
                                            dId = node.branchId;
                                        }
                                        treeLevel.load({depId: dId});
                                    }
                                } else {
                                    mini.alert(text);
                                }
                            }
                        });
                    }
                });
            } else {
                mini.alert("请选择一条记录");
                return;
            }
        }

        // 设置统一管控
        function setControl() {
            var node = treeLevel.getSelectedNode();
            if (node) {
                if (node.pid == "-1") {
                    mini.alert("请选择二级级别");
                    return;
                }
                window.parent.loading();
                $.ajax({
                    url: "../salary/salary_items!setControl.action",
                    type:"POST",
                    data: {
                        id: node.id
                    },
                    success: function(text) {
                        window.parent.unmask();
                        if (text == "success") {
                            mini.alert("操作成功");
                            var node = tree.getSelectedNode();
                            if (node) {
                                var dId = "";
                                if (node.pid != "-1") {
                                    dId = node.branchId;
                                }
                                treeLevel.load({depId: dId});
                            }
                        } else {
                            mini.alert(text);
                        }
                    }
                });
            } else {
                mini.alert("请选择二级级别");
                return;
            }
        }

        // 取消统一管控
        function setNoControl() {
            var node = treeLevel.getSelectedNode();
            if (node) {
                if (node.pid == "-1") {
                    mini.alert("请选择二级级别");
                    return;
                }
                window.parent.loading();
                $.ajax({
                    url: "../salary/salary_items!setNoControl.action",
                    type:"POST",
                    data: {
                        id: node.id
                    },
                    success: function(text) {
                        window.parent.unmask();
                        if (text == "success") {
                            mini.alert("操作成功");
                            var node = tree.getSelectedNode();
                            if (node) {
                                var dId = "";
                                if (node.pid != "-1") {
                                    dId = node.branchId;
                                }
                                treeLevel.load({depId: dId});
                            }
                        } else {
                            mini.alert(text);
                        }
                    }
                });
            } else {
                mini.alert("请选择二级级别");
                return;
            }
        }
    </script>
</body>
</html>