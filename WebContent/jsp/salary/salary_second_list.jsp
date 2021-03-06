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
    <title>二次分配</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>

    <style type="text/css">
        .link {text-decoration:none}
    </style>
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
                            &nbsp;薪酬期间:
                            <input class="mini-textbox" id="payPeriod" style="width: 100px" onenter="onKeyEnter"/>
                            &nbsp;上报状态:
                            <input class="mini-combobox" id="reportStatus" showNullItem="true"
                                data="[{id: 0, text:'未上报'},{id: 1, text:'已上报'}]" onenter="onKeyEnter"/>
                            <a id="searchButton" class="mini-button" iconCls="icon-search" onclick="search('payPeriod,reportStatus','payPeriod,reportStatus')">查询</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    allowAlternating="true" idField="id" allowResize="false" ondrawcell="onDrawCell">
                    <div property="columns">
                        <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                        <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                        <div field="payPeriod" width="80" headerAlign="center" align="center">薪酬期间</div>
                        <div type="comboboxcolumn" field="reportStatus" width="80" headerAlign="center" align="center">上报状态
                            <input property="editor" class="mini-combobox" data="[{id: 0, text:'未上报'},{id: 1, text:'已上报'}]"/>
                        </div>
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
                if(document.getElementById("searchButton")){
                    grid.setUrl("../salary/salary_second!getPeriodAmountListData.action?companyId="+node.branchId);
                    $("#searchButton").click();
                }
            } else {
                grid1.setData([]);
                grid1.setTotalCount(0);
            }
        });

        function listPayRecord(assignId) {
            var tabs = window.parent.mini.get("mainTabs");
            var id = "员工二次分配";
            var tab = tabs.getTab(id);
            var node = tree.getSelectedNode();
            if (!tab) {
                tab = {};
                tab.name = id;
                tab.title = id;
                tab.showCloseButton = true;
                tab.url = "../salary/salary_second!jsonList.action?period=" + assignId+"&companyId="+node.id;
                tabs.addTab(tab);
            } else {
                tab.url = "../salary/salary_second!jsonList.action?period=" + assignId+"&companyId="+node.id;
            }
            tabs.activeTab(tab);
            tabs.reloadTab(tab);
        }

        function onDrawCell(e) {
            var record = e.record;
            // 薪酬期间
            if (e.field=="payPeriod") {
                var html = '<a class="link" href="javascript:listPayRecord(\''+ record.id +'\');">'+ e.value +'</a>';
                e.cellHtml=html;
            }
        }

    </script>
</body>
</html>