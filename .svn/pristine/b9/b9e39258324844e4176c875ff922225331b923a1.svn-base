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
    <title>调整员工固定薪酬结构</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
        <div size="16%" showCollapseButton="true">
            <div class="mini-toolbar" style="padding:2px;border-top:0;border-center:0;border-right:0;">
                <span style="padding-center:5px;">组织机构：</span>
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
                        <td>
                            <a class="mini-button" iconCls="icon-edit" onclick="edit()">调整</a>
                            <a class="mini-button" iconCls="icon-node" onclick="adjustHistory()">调整历史</a>
                            &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="onKeyEnter"/>
                            &nbsp;姓名：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                        </td>
                        <td style="width:30%;text-align: right;">
                            &nbsp;薪资组：<input class="mini-combobox" id="salaryGroup" textField="name" onvaluechanged= "onValueChanged"
                                url="../salary/salary_items!getSalaryGroupListData.action" onenter="onKeyEnter"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="salGrid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    allowAlternating="true" idField="id" allowResize="false" multiSelect="true">
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("salGrid");
        var pageIndex = 0;
        var pageSize = grid1.getPageSize();
        var tree = mini.get("tree1");
        tree.on("nodeselect", function (e) {
            var node = tree.getSelectedNode();
            if (node) {
                if(document.getElementById("searchButton")){ 
                    $("#searchButton").click();
                }else { 
                    var ids = "";
                    if(tree){
                        var node = tree.getSelectedNode();
                        if(node){
                            var ids = "";
                            ids = depAll(ids,tree,node);
                        }
                    }
                    grid1.load({ searchData:"{\"depId\":\""+ids+"\"}" });
                }
            } else {
                grid1.setData([]);
                grid1.setTotalCount(0);
            }
        });
        var salaryGroup = mini.get("salaryGroup");
        salaryGroup.select(0);

        $(function() {
            searchS();
        });
        function searchS() {
            dynamicTable('jobNumber,name,salaryGroup','jobNumber,name,salaryGroup');
        }

        function edit() {
            var rows = grid1.getSelecteds();
            if (rows.length == 0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i=1; i< rows.length;i++) {
                ids += "," + rows[i].id;
            }
            var tabs = window.parent.mini.get("mainTabs");
            var id = "员工工资调整";
            var tab = tabs.getTab(id);
            if (tab) {
                tabs.removeTab(tab);
            }
            tab = {};
            tab.name = id;
            tab.title = "员工工资调整";
            tab.showCloseButton = true; 
            tab.url = "../salary/salary_record!structEdit.action?ids="+ids;
            tabs.addTab(tab);
            tabs.activeTab(tab);
        }

        function searchCondition(name,attr) {
            var names=name.split(",");
            var attrs=attr.split(",");
            var searchData="{";
            for(var i=0;i<names.length;i++){
                if(mini.get(names[i])){
                    var value=mini.get(names[i]).getValue();
                    if(value!=null&&value!=""&&typeof(value)!=undefined){
                        searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\",";
                    }
                }
            }
            var cd = searchData.length;//长度
            var fy = searchData.charAt(cd-1);
            if(fy==","){
                searchData = searchData.substring(0,cd-1);
            }
            if(tree){
                var node = tree.getSelectedNode();
                if(node){
                    var ids = "";
                    ids = depAll(ids,tree,node);
                    if (searchData == "{"){
                        searchData=searchData+"\"depId\":\""+ids+"\"";
                    } else {
                        searchData=searchData+",\"depId\":\""+ids+"\"";
                    }
                }
            }
            searchData=searchData+"}";

            return searchData;
        }
        function depAll(ids,tree,node){
            if(node.flg==1){
                ids += node.id;
            }
            var list = tree.getAllChildNodes(node);
            if(list.length>0){
                if (ids == "") ids = list[0].id; 
                else ids += "," + list[0].id;
                for ( var i = 1; i < list.length; i++) {
                    if(list[i].flg==1){ 
                        ids += "," + list[i].id; 
                    }
                }
            }
            return ids;
        }

        function dynamicTable(name,attr) {
            var ss = searchCondition(name,attr);
            $.ajax({
                url: "../salary/salary_record!getAllSalaryAdjustListData.action?pageIndex="+pageIndex+"&pageSize="+pageSize,
                type: "POST",
                data : {
                    searchData : ss
                },
                success: function(text) {
                    var jsonData = mini.decode(text);
                    var arr = [];
                    arr.push({ type: "checkcolumn", headerAlign:"center", width : 40});
                    arr.push({ type: "indexcolumn", headerAlign:"center", width: 40,header: "序列"});
                    arr.push({ field: "id", id:"id", name:"id", headerAlign:"center", width: 40});
                    arr.push({ field:"jobNumber", width:"80", headerAlign:"center", align:"center", header: "工号"});
                    arr.push({ field:"name", width:"100", headerAlign:"center", align:"center", header: "姓名"});
                    arr.push({ field:"company", width:"150", headerAlign:"center", align:"center", header: "公司"});
                    arr.push({ field:"detpName", width:"80", headerAlign:"center", align:"center", header: "部门"});
                    arr.push({ field:"post", width:"80", headerAlign:"center", align:"center", header: "岗位"});
                    arr.push({ type:"comboboxcolumn", field:"onJob", width:"80", headerAlign:"center", align:"center", header: "在岗状态", editor: { type: "combobox", url: "../system/dectionary!listjson.action?id=402881955bc84595015bc87aa73e0010",textField:"dicname",valueField:"dicvalue"}});
                    if (jsonData.data.length > 0) {
                        if ((jsonData.data)[0].wmName != null) {
                            var jSon = (jsonData.data)[0].wmName.split(",");
                            for (var i=0; i< jSon.length;i++) {
                                arr.push({ field:"a"+i, width:"80", headerAlign:"center", align:"center", header: jSon[i]});
                            }
                        }
                    }
                    var dd = jsonData.data;
                    var table = [];
                    for (var i=0; i< jsonData.total;i++) {
                        var row = dd[i];
                        var column = {id: row.id, jobNumber: row.jobNumber, name: row.name, company: row.company, detpName: row.detpName,
                                jobLevel: row.jobLevel, post: row.post, onJob:row.onJob};
                        var objs = eval(column);
                        if ((dd)[i].wmName != null) {
                            var jSon = (dd)[i].staticValue.split(",");
                            for (var j=0; j< jSon.length;j++) {
                                var aa = "a"+j;
                                objs[aa] = jSon[j];
                            }
                        }
                        table.push(column);
                    }
                    grid1.setTotalCount(jsonData.total);
                    grid1.setPageIndex(pageIndex);
                    grid1.setPageSize(pageSize)
                    grid1.set({columns : arr});
                    grid1.hideColumn("id");
                    grid1.setData(table);
                }
            });
        }

        grid1.on("beforeload", function (e) {
            pageIndex = e.data.pageIndex;
            pageSize = e.data.pageSize;
            $("#searchButton").click();
        });

        function adjustHistory() {
        	var rows = grid1.getSelecteds();
            if (rows.length == 0) {
                mini.alert("请选择一条记录");
                return;
            }
            var id = rows[0].id;
            mini.open({
                url : "../salary/salary_record!structHistory.action?id=" + id,
                title : "员工调整历史",
                width : 780,
                height : 480,
                onload : function() {
                },
                ondestroy : function(action) {
                    searchS();
                }
            });
        }

        function onValueChanged(e) {
            $("#searchButton").click();
        }
    </script>
</body>
</html>