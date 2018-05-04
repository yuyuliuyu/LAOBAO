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
    <title>每月工资分配过程</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
     <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    &nbsp;薪酬期间:
                    <input class="mini-textbox" id="payPeriod" style="width: 100px" onenter="onKeyEnter"/>
                    <a id="searchButton" class="mini-button" iconCls="icon-search" onclick="search('payPeriod','payPeriod')">查询</a>
                    <br><a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                    <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                    <a class="mini-button" iconCls="icon-remove" onclick="remove('','salary','salary_quota', false)">删除</a>
                    <a class="mini-button" iconCls="icon-ok" onclick="commitCheck()">提交审批</a>
                    <a class="mini-button" iconCls="chengben" onclick="listPayRecord()">维护薪酬核算数据</a>
                    <a class="mini-button" iconCls="icon-undo" onclick="release()">发放</a>
                    <a class="mini-button" iconCls="icon-redo" onclick="noRelease()">取消发放</a>
                    <a class="mini-button" iconCls="zichan" onclick="putOut()">发布</a>
                    <a class="mini-button" iconCls="icon-zhuanyibumen" onclick="noPutOut()">取消发布</a>
                    <a class="mini-hidden" iconCls="shengchan" onclick="createBankFile()">生成报盘</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
            allowAlternating="true" multiSelect="true" url="../salary/salary_quota!getSalaryAssignationListData.action?companyId=${companyId}"
            idField="id" allowResize="false" >
            <div property="columns">
                <div type="checkcolumn" width="40"></div>
                <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                <div field="name" width="80" headerAlign="center" align="center">分配过程名称</div>
                <div field="companyName" width="80" headerAlign="center" align="center">公司</div>
                <div field="groupName" width="80" headerAlign="center" align="center">薪资组</div>
                <div field="wageName" width="80" headerAlign="center" align="center">工资套</div>
                <div field="payPeriod" width="40" headerAlign="center" align="center">薪酬期间</div>
                <div type="comboboxcolumn" field="isSp" width="40" headerAlign="center" align="center">审核状态
                    <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945bfbc847015bfbd8717d0008"
                        valueField="dicvalue" textField="dicname"/>
                </div>
                <div type="comboboxcolumn" field="isFafang" width="40" headerAlign="center" align="center">发放状态
                    <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945bfbc847015bfbd96bbf000d"
                        valueField="dicvalue" textField="dicname"/>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");

        function add() {
            var branchId = '${companyId}';
            if (branchId) {
                mini.open({
                    url : "../salary/salary_quota!processadd.action?companyId=" + branchId,
                    title : "增加薪酬分配过程",
                    width : 680,
                    height : 360,
                    onload : function() {
                    },
                    ondestroy : function(action) {
                        grid.reload();
                    }
                });
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
                url : "../salary/salary_quota!processEdit.action?id="+id,
                title : "编辑薪酬分配过程",
                width : 680,
                height : 360,
                onload : function() {
                },
                ondestroy : function(action) {
                    grid.reload();
                }
            });
        }

        // 提交审批
        function commitCheck() {
            var rows = grid.getSelecteds();
            if (rows.length ==0 || rows.length > 1) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i = 1; i < rows.length; i++) {
                ids += "," + rows[i].id;
            }
            mini.open({
                url : "../salary/salary_quota!authpro.action?ids="+ids,
                title : "审批岗位选择",
                width : 560,
                height : 380,
                onload : function() {
                },
                ondestroy : function(action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        window.parent.loading();
                        $.ajax({
                            url : "../salary/salary_quota!batchCommitCheck.action",
                            type : "post",
                            data : {
                                ids : ids,
                                isIgnore: 1,
                                formdata: data
                            },
                            success : function(text) {
                                window.parent.unmask();
                                if (text == "success") {
                                    mini.showMessageBox({
                                        title: "提醒",
                                        width: 250,
                                        iconCls: "mini-messagebox-warning",
                                        buttons: ["ok"],
                                        message: "操作成功",
                                        callback: function (action) {
                                            grid.reload();
                                        }
                                    });
                                } else {
                                    if (text == "-1") {
                                        mini.alert("存在未上报的二次分配项目");
                                    } else if(text.length>0){
                                        mini.alert(text);
                                    }
                                    grid.reload();
                                }
                            }
                        });
                    }
                }
            });
        }

        function listPayRecord() {
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var assignId = rows[0].id;
             var tabs = window.parent.mini.get("mainTabs");
             var id = "维护薪酬核算数据";
             var tab = tabs.getTab(id);
             if (!tab) {
                 tab = {};
                 tab.name = id;
                 tab.title = id;
                 tab.showCloseButton = true;
                 tab.url = "../salary/salary_quota!processData.action?id=" + assignId;
                 tabs.addTab(tab);
             } else {
                 tab.url = "../salary/salary_quota!processData.action?id=" + assignId;
             }
             tabs.activeTab(tab);
             tabs.reloadTab(tab);
        }

        // 发放
        function release() {
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i = 1, l = rows.length; i< l; i++) {
                ids += "," + rows[i].id;
            }
            mini.confirm("您确定要发放？","提醒",function(action){
                if(action=="ok"){
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_quota!updateReleaseData.action",
                        type : "post",
                        data : {
                            ids : ids
                        },
                        success : function(text) {
                            window.parent.unmask();
                            if (text == "success") {
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "发放成功",
                                    callback: function (action) {
                                        grid.reload();
                                    }
                                });
                            } else {
                                if(text.length>0){
                                    mini.alert(text);
                                }
                            }
                        }
                    });
                }
            });
        }

        // 取消发放
        function noRelease() {
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i = 1, l = rows.length; i< l; i++) {
                ids += "," + rows[i].id;
            }
            mini.confirm("您确定要取消发放？","提醒",function(action){
                if(action=="ok"){
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_quota!updateNoReleaseData.action",
                        type : "post",
                        data : {
                            ids : ids
                        },
                        success : function(text) {
                            window.parent.unmask();
                            if (text == "success") {
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "取消发放成功",
                                    callback: function (action) {
                                        grid.reload();
                                    }
                                });
                            } else {
                                if(text.length>0){
                                    mini.alert(text);
                                }
                            }
                        }
                    });
                }
            });
        }

        // 发布
        function putOut() {
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i = 1, l = rows.length; i< l; i++) {
                ids += "," + rows[i].id;
            }

            mini.confirm("您确定要发布？","提醒",function(action){
                if(action=="ok"){
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_quota!updateSendSalaryRecord.action",
                        type : "post",
                        data : {
                            ids : ids
                        },
                        success : function(text) {
                            window.parent.unmask();
                            if (text == "success") {
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "发布成功",
                                    callback: function (action) {
                                        grid.reload();
                                    }
                                });
                            } else {
                                if(text.length>0){
                                    mini.alert(text);
                                }
                            }
                        }
                    });
                }
            });
        }

        // 取消发布
        function noPutOut() {
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i = 1, l = rows.length; i< l; i++) {
                ids += "," + rows[i].id;
            }

            mini.confirm("您确定要取消发布？","提醒",function(action){
                if(action=="ok"){
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_quota!updateNoSendSalaryRecord.action",
                        type : "post",
                        data : {
                            ids : ids
                        },
                        success : function(text) {
                            window.parent.unmask();
                            if (text == "success") {
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "取消发布成功",
                                    callback: function (action) {
                                        grid.reload();
                                    }
                                });
                            } else {
                                if(text.length>0){
                                    mini.alert(text);
                                }
                            }
                        }
                    });
                }
            });
        }

        // 报盘文件
        function createBankFile() {
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i = 1, l = rows.length; i< l; i++) {
                ids += "," + rows[i].id;
            }
            window.parent.loading();
            $.ajax({
                url : "../salary/salary_quota!createBankFile.action",
                type : "post",
                data : {
                    ids : ids
                },
                success : function(text) {
                    window.parent.unmask();
                    if (text == "success") {
                        mini.showMessageBox({
                            title: "提醒",
                            width: 250,
                            iconCls: "mini-messagebox-warning",
                            buttons: ["ok"],
                            message: "取消发放成功",
                            callback: function (action) {
                                grid.reload();
                            }
                        });
                    } else {
                        if(text.length>0){
                            mini.alert(text);
                        }
                    }
                }
            });
        }
    </script>
</body>
</html>