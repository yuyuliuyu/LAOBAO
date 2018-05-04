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
    <title>工资审批</title>
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
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    &nbsp;分配过程:
                    <input class="mini-textbox" id="name" style="width: 100px" onenter="onKeyEnter"/>
                    &nbsp;薪酬期间:
                    <input class="mini-textbox" id="payPeriod" style="width: 100px" onenter="onKeyEnter"/>
                    <a id="searchButton" class="mini-button" iconCls="icon-search" onclick="search('name,payPeriod','name,payPeriod')">查询</a>
                    <br><a class="mini-button" iconCls="icon-ok" onclick="commitCheck()">审批</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20" multiSelect="true"
            allowAlternating="true" url="../salary/salary_quota!getSalaryAssignationForAuthListData2.action"
            idField="id" allowResize="false" ondrawcell="onDrawCell" onshowrowdetail="onShowRowDetail">
            <div property="columns">
                <div type="checkcolumn" width="40"></div>
                <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                <div type="expandcolumn" >审核记录</div>
                <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                <div field="name" width="80" headerAlign="center" align="center">分配过程名称</div>
                <div field="companyName" width="80" headerAlign="center" align="center">公司</div>
                <div field="groupName" width="80" headerAlign="center" align="center">薪资组</div>
                <div field="wageName" width="80" headerAlign="center" align="center">工资套</div>
                <div field="payPeriod" width="80" headerAlign="center" align="center">薪酬期间</div>
                <div field="aproMan" width="80" headerAlign="center" align="center">待审核</div>
                <div type="comboboxcolumn" field="isSp" width="80" headerAlign="center" align="center">审核状态
                    <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945bfbc847015bfbd8717d0008"
                        valueField="dicvalue" textField="dicname"/>
                </div>
            </div>
        </div>

        <div id="detailGrid_Form" style="display:none;">
            <div id="checkGrid" class="mini-datagrid" style="width:100%;" pageSize="10"
                allowAlternating="true" url="../salary/salary_quota!getCheckListData.action"
                idField="id" allowResize="false">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                    <div field="aproId" width="80" headerAlign="center" align="center">账号</div>
                    <div field="aproMan" width="80" headerAlign="center" align="center">审核人</div>
                    <div type="comboboxcolumn" field="isSp" width="40" headerAlign="center" align="center">审核状态
                        <input property="editor" class="mini-combobox" data="[{id:-1, text:'未审核'},{id:0, text:'未通过'},{id:1, text:'通过'}]"/>
                    </div>
                    <div field="checkDate" width="120" headerAlign="center" align="center" renderer="onLongDateRenderer">审核时间</div>
                    <div field="note" width="120" headerAlign="center" align="center">审核意见</div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.hideColumn("id");
        grid.load({searchData : "{'isSp' : 1}"});
        var checkGrid = mini.get("checkGrid");
        var detailGrid_Form = document.getElementById("detailGrid_Form");

        function listPayRecord(assignId, groupId, salaryWageId, companyId, salaryPeriod, salaryRate) {
            var tabs = window.parent.mini.get("mainTabs");
            var id = "审批薪酬核算数据";
            var tab = tabs.getTab(id);
            var searchdata = {};
            searchdata.groupId = groupId;
            searchdata.salaryWageId = salaryWageId;
            searchdata.companyId = companyId;
            searchdata.salaryPeriod = salaryPeriod;
            searchdata.salaryRate = salaryRate;
            if (!tab) {
                tab = {};
                tab.name = id;
                tab.title = id;
                tab.showCloseButton = true;
                tab.url = "../salary/salary_approval!processData.action?id=" + assignId+"&conditionData="+mini.encode(searchdata);
                tabs.addTab(tab);
            } else {
                tab.url = "../salary/salary_approval!processData.action?id=" + assignId+"&conditionData="+mini.encode(searchdata);
            }
            tabs.activeTab(tab);
            tabs.reloadTab(tab);
        }

        function onDrawCell(e) {
            var record = e.record;
            // 发放过程
            if (e.field=="name") {
                var html = '<a class="link" href="javascript:listPayRecord(\''+ record.id +'\', \''+ record.groupId +'\', \''+ 
                          record.salaryWageId +'\', \''+ record.companyId +'\', \''+ record.salaryPeriod +'\', \''+ record.salaryRate +'\');">'+ record.name +'</a>';
                e.cellHtml=html;
            }
        }

        function onShowRowDetail(e) {
            var sender = e.sender;
            var row = e.record;

            var td = sender.getRowDetailCellEl(row);
            td.appendChild(detailGrid_Form);
            detailGrid_Form.style.display = "block";

            checkGrid.load({ id: row.id, processId: row.process });
        }

        // 审批
        function commitCheck() {
            var rows = grid.getSelecteds();
            if (rows.length ==0 || rows.length >1) {
                mini.alert("请选择一条记录");
                return;
            }
            var assignIds = rows[0].id;
            window.parent.loading();
            $.ajax({
                url : "../salary/salary_quota!isCheckAUth2.action",
                type : "post",
                data : {
                    ids : assignIds
                },
                success : function(text) {
                    window.parent.unmask();
                    var data = mini.decode(text);
                    var result = data.result;
                    var info = data.info;
                    if (result == "success") {
                        checkAuth(assignIds);
                    } else if (result == "fail") {
                        mini.alert(info);
                        return;
                    } else {
                        if(result.length>0){
                            var alertInfo = "";
                            if (result == "3") {
                                alertInfo = "您不是该分配过程的当前审批人，不能操作";
                            } else if (result == "4") {
                                alertInfo = "该分配过程的审批流程不存在";
                            } else if (result == "13") {
                                alertInfo = "该分配过程已经发放";
                            }
                            if (alertInfo != "") {
                                mini.showMessageBox({
                                    title: "提醒",
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: alertInfo,
                                    callback: function (action) {
                                        grid.reload();
                                    }
                                });
                            }
                        }
                    }
                }
            });
        }
        function checkAuth(ids) {
            mini.open({
                url : "../salary/salary_quota!audit.action?ids=" + ids,
                title : "审核意见",
                width : 520,
                height : 380,
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