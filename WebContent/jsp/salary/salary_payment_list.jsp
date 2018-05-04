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
    <title>计算社保缴费</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
        <div size="16%" showCollapseButton="true">
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
                            &nbsp;名称：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                            <br><a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                            <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                            <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    allowAlternating="true" multiSelect="true" url="../salary/salary_payment!getPayMentListData.action"
                    idField="id" allowResize="false" >
                    <div property="columns">
                        <div type="checkcolumn" width="40"></div>
                        <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                        <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                        <div field="name" width="100" headerAlign="center" align="center">名称</div>
                        <div field="companyName" width="100" headerAlign="center" align="center">缴费单位</div>
                        <div field="areaName" width="60" headerAlign="center" align="center">缴费地域</div>
                        <div field="fuliName" width="120" headerAlign="center" align="center">福利项目</div>
                        <div field="deptName" width="80" headerAlign="center" align="center">参与计算的组织</div>
                        <div field="sumCom" width="80" headerAlign="center" align="center">公司缴费</div>
                        <div field="sumPer" width="80" headerAlign="center" align="center">员工缴费</div>
                        <div field="effectDate" width="80" headerAlign="center" align="center">计算年月</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        var tree = mini.get("deptree");
        tree.on("nodeselect", function (e) {
            var node = tree.getSelectedNode();
            if (node) {
                var dId = "";
                if (node.pid != "-1") {
                    dId = node.branchId;
                }
                grid.load({companyId: dId});
            }
        });

        $(function() {
            searchS();
        });

        function searchS() {
            search('name','name');
        }

        function add() {
            var node = tree.getSelectedNode();
            if (node) {
                var branchId = node.branchId;
                if (branchId) {
                    var tabs = window.parent.mini.get("mainTabs");
                    var id = "add$增加社保缴费";
                    var tab = tabs.getTab(id);
                    if (tab) {
                        tabs.removeTab(tab);
                    }
                    tab = {};
                    tab.name = id;
                    tab.title = "增加社保缴费";
                    tab.showCloseButton = true; 
                    tab.url = "../salary/salary_payment!add.action?companyId=" + branchId;
                    tabs.addTab(tab);
                    tabs.activeTab(tab);
                }
            } else {
                mini.alert("请选择公司");
                return;
            }
       }

       function edit() {
           var rows = grid.getSelecteds();
           if (rows.length ==0) {
               mini.alert("请选择一条记录");
               return;
           }
           var idRow = rows[0].id;
           var tabs = window.parent.mini.get("mainTabs");
           var id = "编辑社保缴费";
           var tab = tabs.getTab(id);
           if (tab) {
               tabs.removeTab(tab);
           }
           tab = {};
           tab.name = id;
           tab.title = "编辑社保缴费";
           tab.showCloseButton = true; 
           tab.url = "../salary/salary_payment!edit.action?id="+idRow+"&companyId=" + rows[0].companyId;
           tabs.addTab(tab);
           tabs.activeTab(tab);
       }

       function remove() {
           var rows = grid.getSelecteds();
           if (rows.length == 0) {
               mini.alert("请选择一条记录");
               return;
           }
           var ids = rows[0].id;
           for (var i=1,l= rows.length;i<l;i++) {
               ids += "," + rows[i].id;
           }

           mini.confirm("确定要删除？","提醒",function(action) {
               if (action == "ok") {
                   $.ajax({
                       url: "../salary/salary_payment!remove.action",
                       type: "POST",
                       data : {
                           ids : ids
                       },
                       success: function(text) {
                           if (text == "success") {
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

    </script>
</body>
</html>