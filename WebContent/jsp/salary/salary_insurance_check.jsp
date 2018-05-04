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
    <title>每月缴费核对列表</title>
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
                            &nbsp;缴费年月:
                            <input class="mini-textbox" id="jsYear" style="width: 60px" vtype="int"
                                onenter="onKeyEnter"/>年
                            <input class="mini-textbox" id="jsMonth" style="width: 40px" vtype="int"
                                onenter="onKeyEnter"/>月
                            &nbsp;保险项目:
                            <input class="mini-combobox" id="insurance" name="insurance" textField="name" showNullItem ="true"
                                url="../basis/insurance_benefits!getAllItemsListData.action" onvaluechanged="onFuliValueChanged"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('jsYear,jsMonth,insurance','jsYear,jsMonth,insurance')">查询</a>
                            <br><a class="mini-button" iconCls="icon-add" onclick="addInsurance()">增加</a>
                            <a class="mini-button" iconCls="icon-edit" onclick="editInsurance()">编辑</a>
                            <a class="mini-button" iconCls="icon-download" onclick="downDoc()">下载单据</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    allowAlternating="true" idField="id" allowResize="false">
                    <div property="columns">
                        <div type="checkcolumn" width="40">选择</div>
                        <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                        <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                        <div field="company" width="120" headerAlign="center" align="center">公司</div>
                        <div field="insuranceName" width="60" headerAlign="center" align="center">福利项目</div>
                        <div field="docLocation" width="140" headerAlign="center" align="center">单据</div>
                        <div field="jfDate" width="60" headerAlign="center" align="center">缴费年月</div>
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
            grid.setUrl("../salary/salary_insurance!getInsuranceCheckListData.action?companyId="+ids);
            $("#searchButton").click();
        }

        function addInsurance() {
            var node = tree.getSelectedNode();
            if (node) {
                var branchId = node.branchId;
                var tabs = window.parent.mini.get("mainTabs");
                var id = "新增薪资组工资套分配";
                var tab = tabs.getTab(id);
                if (tab) {
                    tabs.removeTab(tab);
                }
                tab = {};
                tab.name = id;
                tab.title = id;
                tab.showCloseButton = true; 
                tab.url = "../salary/salary_insurance!checkList.action?companyId="+branchId;
                tabs.addTab(tab);
                tabs.activeTab(tab);
            } else {
                mini.alert("请选择公司或者部门进行核对");
                return;
            }
        }

        function editInsurance() {
            var row = grid.getSelected();
            if (row) {
                var tabs = window.parent.mini.get("mainTabs");
                var id = "编辑保险每月缴费核对";
                var tab = tabs.getTab(id);
                if (tab) {
                    tabs.removeTab(tab);
                }
                tab = {};
                tab.name = id;
                tab.title = id;
                tab.showCloseButton = true; 
                tab.url = "../salary/salary_insurance!checkList.action?id=" + row.id+"&insuranceId=" + row.insuranceId;
                tabs.addTab(tab);
                tabs.activeTab(tab);
            } else {
                mini.alert("请选中一条记录！");
            }
        }

        function onFuliValueChanged(e) {
            $("#searchButton").click();
        }

        // 下载单据
        function downDoc() {
            var row = grid.getSelected();
            if (!row) {
                mini.alert("请选中一条记录！");
                return;
            }
            window.parent.loading();
            $.ajax({
                url: "../salary/salary_insurance!downDoc.action",
                type:"POST",
                data: {
                    id: row.id
                },
                success: function(text) {
                    window.parent.unmask();
                    var data = mini.decode(text);
                    if (data.success == "success") {
                        window.location.href="../system/xiazai!download.action?name="+data.info;
                    } else {
                        mini.alert(data.info);
                    }
                }
            });
        }

    </script>
</body>
</html>