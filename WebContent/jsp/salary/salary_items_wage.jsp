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
    <title>工资套</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
    <script src="../template/plugin/public/treeCompanyList.js" type="text/javascript"></script>
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
                            &nbsp;工资套名称：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                            &nbsp;工资套类型：<input class="mini-combobox" id="sign" showNullItem="true"
                                data="[{id:1, text:'薪酬期间工资'},{id:2, text:'绩效奖励'},{id:3, text:'提成工资'},{id:4, text:'离职结算'}]" onenter="onKeyEnter"/>
                            &nbsp;生效日期:
                            <input class="mini-datepicker" id="startTime" style="width: 130px" format="yyyy年MM月dd日" onenter="onKeyEnter"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchByTime('name,sign,startTime','name,sign,startTime')">查询</a>
                            <br><a class="mini-button" iconCls="icon-add" onclick="addWage()">增加</a>
                            <a class="mini-button" iconCls="icon-edit" onclick="editWage()">编辑</a>
                            <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
                            <a class="mini-hidden" iconCls="icon-goto" onclick="fp()">薪资组分配</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    allowAlternating="true" idField="id" allowResize="false"multiSelect="true">
                    <div property="columns">
                        <div type="checkcolumn" width="40"></div>
                        <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                        <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                        <div type="comboboxcolumn" field="companyId" width="140" headerAlign="center" align="center">公司
                            <input property="editor" class="mini-combobox" textField="fzx" url="../salary/salary_manage!getBranches.action"/>
                        </div>
                        <div field="name" width="200" headerAlign="center" align="center">工资套名称</div>
                        <div type="comboboxcolumn" field="sign" width="200" headerAlign="center" align="center">工资套类型
                            <input property="editor" class="mini-combobox" data="[{id:1, text:'薪酬期间工资'},{id:2, text:'绩效奖励'},{id:3, text:'提成工资'},{id:4, text:'离职结算'}]"/>
                        </div>
                        <div type="comboboxcolumn" field="type" width="200" headerAlign="center" align="center">状态
                            <input property="editor" class="mini-combobox" data="[{id:0,text:'无效'},{id:1,text:'有效'}]"/>
                        </div>
                        <div field="effectDate" width="200" headerAlign="center" align="center" renderer="ondayRenderer2">生效日期</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        var tree = mini.get("deptree");

        function callback(ids, node) {
            grid.setUrl("../salary/salary_items!getSalaryWageListData.action?companyId="+ids);
            $("#searchButton").click();
        }
    
        function addWage() {
            if (tree) {
                var node = tree.getSelectedNode();
                if (node) {
                    var branchId = node.branchId;
                    mini.open({
                        url : "../salary/salary_items!wageadd.action?companyId="+branchId+"&depId="+getSelectedNode(node),
                        title : "新增工资套",
                        width : 950,
                        height : 580,
                        onload : function() {
                        },
                        ondestroy : function(action) {
                            grid.reload();
                        }
                    });
                } else {
                    mini.alert("请选择公司");
                    return;
                }
            }
        }
    
        function editWage() {
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url : "../salary/salary_items!wageEdit.action?id=" + row.id,
                    title : "编辑工资套",
                    width : 950,
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

        // 获取选中节点
        function getSelectedNode(node) {
            // 获取节点的所有父节点
            var allParentNode = tree.getAncestors(node);
            var need = [];
            for (var i=0; i < allParentNode.length; i++) {
                need.push(allParentNode[i].branchId);
            }
            if (node.flg == 0)
                need.push(node.branchId);
            var ids = need.join(",");

            return ids;
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
                            url: "../salary/salary_items!removeToWage.action",
                            type: "POST",
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

        function fp() {
             var tabs = window.parent.mini.get("mainTabs");
             var id = "薪资组工资套分配";
             var tab = tabs.getTab(id);
             if (tab) {
                 tabs.removeTab(tab);
             }
             tab = {};
             tab.name = id;
             tab.title = id;
             tab.showCloseButton = true; 
             tab.url = "../salary/salary_items!groupList.action";
             tabs.addTab(tab);
             tabs.activeTab(tab);
        }
    </script>
</body>
</html>