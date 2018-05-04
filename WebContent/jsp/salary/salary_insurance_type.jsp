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
    <title>保险类别</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
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
                            &nbsp;福利项目：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                            &nbsp;缴费地域：<input class="mini-textbox" id="area" onenter="onKeyEnter"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('name,area','name,area')">查询</a>
                            <br><a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                            <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                            <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    allowAlternating="true" multiSelect="true" idField="id" allowResize="false" >
                    <div property="columns">
                        <div type="checkcolumn" width="40"></div>
                        <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                        <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                        <div field="companyName" width="140" headerAlign="center" align="center">公司</div>
                        <div field="name" width="80" headerAlign="center" align="center">福利项目</div>
                        <div field="area" width="80" headerAlign="center" align="center">缴费地域</div>
                        <div type="comboboxcolumn" field="staffType" width="80" headerAlign="center" align="center">员工类型
                            <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945bc7485d015bc7babe37000e"
                                textField="dicname" valueField="dicvalue"/>
                        </div>
                        <div field="high" width="80" headerAlign="center" align="center">缴纳基数上限</div>
                        <div field="low" width="80" headerAlign="center" align="center">缴纳基数下限</div>
                        <div field="biCompany" width="80" headerAlign="center" align="center">公司缴纳比例</div>
                        <div field="biPersonal" width="80" headerAlign="center" align="center">个人缴纳比例</div>
                        <div field="effectDate" width="80" headerAlign="center" align="center">生效年月</div>
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

        function callback(ids, node) {
            grid.setUrl("../salary/salary_insurance!getPaymentInsuranceListData.action?depId="+ids);
            $("#searchButton").click();
        }

        function add() {
             var node = tree.getSelectedNode();
             if (node) {
                 var branchId = node.branchId;
                 if (branchId) {
                     mini.open({
                         url : "../salary/salary_insurance!typeAdd.action?depId="+branchId,
                         title : "增加保险类别",
                         width : 640,
                         height : 560,
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

        function edit() {
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var id = rows[0].id;
            mini.open({
                url : "../salary/salary_insurance!typeEdit.action?id="+id,
                title : "编辑保险类别",
                width : 640,
                height : 560,
                onload : function() {
                },
                ondestroy : function(action) {
                    grid.reload();
                }
            });
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
                        url: "../salary/salary_insurance!remove.action",
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

        grid.on("drawCell", function(e) {
            // 生效年月
            if (e.field=="effectDate") {
                var row = e.row;
                e.cellHtml=row.year+"年"+row.month+"月";
            }
        });
    </script>
</body>
</html>