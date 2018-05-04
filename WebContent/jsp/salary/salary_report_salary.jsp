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
    <title>工资报表</title>
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
                            &nbsp;工号:
                            <input class="mini-textbox" id="jobNumber" onenter="onKeyEnter"/>
                            &nbsp;姓名:
                            <input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                            &nbsp;性别：
                            <input class="mini-combobox" style="width:50px" id="sex" showNullItem="true" data="[{id:0,text:'女'},{id:1,text:'男'}]"/>
                            &nbsp;薪酬期间:
                            <input class="mini-textbox" id="payPeriod" style="width: 100px" onenter="onKeyEnter"/>
                            &nbsp;薪资项目:
                            <input id="salaryItem" class="mini-lookup" showClose="true" oncloseclick="onClearClick"
                                 textField="name" valueField="id" popupWidth="auto" popup="#gridPanel" grid="#salaryItemTree" multiSelect="true" />
                            <div id="gridPanel" class="mini-panel" title="header" iconCls="icon-add" style="width:300px;height:250px;" 
                               showToolbar="true" showCloseButton="true" showHeader="false" bodyStyle="padding:0"  borderStyle="border:0">
                                 <div property="toolbar" style="padding:5px;padding-left:8px;text-align:center;">
                                     <div style="float:left;padding-bottom:2px;">
                                        <input id="itemName" class="mini-textbox" onenter="findNodes"/>
                                        <a class="mini-button" onclick="findNodes">查询</a>
                                     </div>
                                     <div style="clear:both;"></div>
                                 </div>
                                 <div id="salaryItemTree" class="mini-treegrid" style="width:100%;height:100%;" showTreeIcon="false"
                                    checkRecursive="true" parentField="pid" idField= "id" onlyCheckSelection="true"showCheckBox="true"
                                    borderStyle="border:0" pagerSize="10" showPager="false" treeColumn="name" resultAsTree="false" onnodecheck="onNodeCheck">
                                     <div property="columns">
                                         <div field="id" id="id" width="20" name="id" class="mini-hidden"></div>
                                         <div field="name" name="name" width="100" headerAlign="center" align="left">薪资项目</div>
                                     </div>
                                 </div>
                            </div>
                            <br>
                            &nbsp;员工类型：
                            <input class="mini-combobox" style="width:80px" id="empType" showNullItem="true" url="../system/dectionary!listjson.action?id=402881935baceaa1015bad355ba8000a"
                                textField="dicname" valueField="dicvalue" onenter="onKeyEnter"/>
                            &nbsp;职务级别：
                            <input class="mini-combobox" id="zwjb" style="width:100px" showNullItem="true" url="../system/dectionary!listjson.action?id=402881935ba8c785015ba8e043c80011"
                                textField="dicname" valueField="dicvalue" onenter="onKeyEnter"/>
                            &nbsp;成本单位：<div class="mini-combobox" id="cbdw" style="width: 180px"popupWidth="20%" multiSelect="true" url="../salary/salary_manage!getBranches.action" 
                                            valueField="id" textField="fzx"valueFromSelect="true" allowInput="true" pinyinField="jm">
                                            <div property="columns">
                                                <div field="jm" width="80"headerAlign="center"align="center">公司编码</div>
                                                <div field="fzx" width="180"headerAlign="center"align="center">公司</div>
                                            </div>
                                        </div>
                            &nbsp;是否发薪：
                            <input class="mini-combobox" style="width:50px" id="sffx" showNullItem="true" data="[{id:0,text:'否'},{id:1,text:'是'}]"/>
                            <a id="searchButton" class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                            <a class="mini-button" iconCls="icon-download"
                                onclick="exportExcelData('salary', 'salary_report','jobNumber,name,payPeriod,salaryItem,sex,empType,zwjb,cbdw,sffx','jobNumber,name,payPeriod,salaryItem,sex,empType,zwjb,cbdw,sffx')">导出</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    allowAlternating="true" idField="id" allowResize="false" allowHeaderWrap="true">
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");
        var salaryItem = mini.get("salaryItem");
        var salaryItemTree = mini.get("salaryItemTree");
        salaryItemTree.hideColumn("id");
        var payPeriod = mini.get("payPeriod");
        payPeriod.setValue(mini.formatDate(new Date(), "yyyy年M月"));
        var tree = mini.get("deptree");
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
                // 获取节点的所有父节点
                var allParentNode = tree.getAncestors(node);
                var need = [];
                for (var i=0; i < allParentNode.length; i++) {
                    need.push(allParentNode[i].branchId);
                }
                if (node.flg == 0)
                    need.push(node.branchId);
                var ids = need.join(",");
                salaryItemTree.setUrl("../salary/salary_items!getSalaryItemToItemTypeData.action?depId="+ids);
            } else {
                grid1.setData([]);
                grid1.setTotalCount(0);
            }
        });

        $(function() {
            searchS();
        });
        function searchS() {
            dynamicTable('jobNumber,name,payPeriod,salaryItem,sex,empType,zwjb,cbdw,sffx','jobNumber,name,payPeriod,salaryItem,sex,empType,zwjb,cbdw,sffx');
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

        /**
         * 动态列
         * zhanghj
         */
        function dynamicTable(name,attr) {
            var ss = searchCondition(name,attr);
            window.parent.loading();
            $.ajax({
                url: "../salary/salary_report!getSlaryReportListData.action",
                type: "POST",
                data : {
                    searchData : ss
                },
                success: function(text) {
                    window.parent.unmask();
                    var gridData = mini.decode(text);
                    var jsonData = gridData.data;
                    var arr = [];
                    arr.push({ type: "indexcolumn", headerAlign:"center", width: 40,header: "序列"});
                    arr.push({ field: "id", id:"id", name:"id", headerAlign:"center", width: 40});
                    arr.push({ field: "staffId", id:"staffId", name:"staffId", headerAlign:"center", header: "员工ID"});
                    arr.push({ field:"jobNumber", width:"80", headerAlign:"center", align:"center", header: "工号"});
                    arr.push({ field:"name", width:"100", headerAlign:"center", align:"center", header: "姓名"});
                    arr.push({ type:"comboboxcolumn", field:"sex", width:"60", headerAlign:"center", align:"center", header: "性别",
                             editor: { type: "combobox", data: "[{id:0, text:'女'},{id:1, text:'男'}]"}});
                    arr.push({ type:"comboboxcolumn", field:"empType", width:"80", headerAlign:"center", align:"center", header: "员工类型",
                             editor: { type: "combobox", url: "../system/dectionary!listjson.action?id=402881935baceaa1015bad355ba8000a",
                                      textField: "dicname", valueField: "dicvalue"}});
                    arr.push({ type:"comboboxcolumn", field:"jobLevel", width:"80", headerAlign:"center", align:"center", header: "职务级别",
                             editor: { type: "combobox", url: "../system/dectionary!listjson.action?id=402881935ba8c785015ba8e043c80011",
                                      textField: "dicname", valueField: "dicvalue"}});
                    arr.push({ field:"cbdw", width:"100", headerAlign:"center", align:"center", header: "成本单位"});
                    arr.push({ type:"comboboxcolumn", field:"sffx", width:"60", headerAlign:"center", align:"center", header: "是否发薪",
                             editor: { type: "combobox", data: "[{id:0, text:'否'},{id:1, text:'是'}]"}});
                    arr.push({ field:"companyName", width:"100", headerAlign:"center", align:"center", header: "公司"});
                    arr.push({ field:"deptName", width:"100", headerAlign:"center", align:"center", header: "部门"});
                    arr.push({ field:"payPeriod", width:"80", headerAlign:"center", align:"center", header: "薪酬期间"});

                    // 遍历选中的薪资项目
                    var choseItems = [];
                    var itemsValue = salaryItem.getValue().split(",;");
                    var itemsText = salaryItem.getText().split(",;");
                    for (var i =0; i< itemsValue.length;i++) {
                        choseItems[itemsValue[i]] = "";
                        arr.push({ field: itemsValue[i], width:"80", headerAlign:"center", align:"center", header: itemsText[i]});
                    }

                    // 遍历数据
                    var table = [];
                    for (var i=0; i< jsonData.length;i++) {
                        var row = jsonData[i];
                        var column = {id: row.id, staffId: row.staffId, jobNumber: row.jobNumber, name: row.name, companyName: row.companyName,
                                deptName: row.deptName, payPeriod: row.payPeriod, sex: row.sex, empType: row.empType, jobLevel: row.jobLevel,
                                cbdw:row.cb,sffx:row.sffx};
                        var objs = eval(column);
                        var newObjs = $.extend({}, objs, choseItems);
                        if (row.itemsId != null) {
                            var itemsId = row.itemsId.split(",;");
                            var charge = row.charge.split(",;");
                            for (var j=0; j< itemsId.length;j++) {
                                var aField = itemsId[j];
                                newObjs[aField] = charge[j];
                            }
                        }
                        table.push(newObjs);
                    }
                    grid1.setData(table);
                    grid1.set({columns : arr});
                    grid1.hideColumn("id");
                    grid1.hideColumn("staffId");
                    grid1.frozenColumns(0, 12);
                    grid1.setTotalCount(jsonData.length);
                }
            });
        }

        /**导出
         * a:包名
         * b:类名
         * name:所有查询条件框的name字符串
         * attr:所有查询条件的属性名
         * action方法名:export
         * */
        function exportExcelData(a, b, name, attr) {
            var search = mini.decode(searchCondition(name,attr));
            search.itemName = salaryItem.getText();
            var searchData = encodeURI(mini.encode(search));
            window.location.href="../"+a+"/"+b+"!export.action?searchData="+searchData;
        }

        /**
         * 清除
         * zhanghj
         */
        function onClearClick(e) {
            var obj = e.sender;
            obj.setText("");
            obj.setValue("");
            salaryItemTree.uncheckAllNodes();
        }

        /* 查找节点 */
        function findNodes() {
            var key = mini.get("itemName").getValue();
            if (key == "") {
                salaryItemTree.clearFilter();
            } else {
                salaryItemTree.filter(function (node) {
                    var text = node.name ? node.name : "";
                    if (text.indexOf(key) != -1) {
                        return true;
                    }
                });
            }
        }

        // 薪资项目节点选中
        function onNodeCheck(e) {
            var value = salaryItemTree.getValue(false);
            var vs = value.split(",");
            var text = "";
            if (vs.length > 0) {
                var node = salaryItemTree.getNode(vs[0]);
                text = node.name;
                for (var i = 1; i< vs.length; i++) {
                    var node = salaryItemTree.getNode(vs[i]);
                    text += "," + node.name;
                }
            }
            salaryItem.setValue(value);
            salaryItem.setText(text);
        }
    </script>
</body>
</html>