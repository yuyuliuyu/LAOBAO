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
    <title>员工保险缴纳</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
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
                            &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="onKeyEnter"/>
                            &nbsp;姓名：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                            <br><a class="mini-button" iconCls="icon-add" onclick="addCanBao()">增加参保人员</a>
                            <a class="mini-button" iconCls="icon-save" onclick="saveBase()">保存</a>
                            <a class="mini-button" iconCls="xitong" onclick="addBasejf()">计算缴费基数</a>
                            <a class="mini-button" iconCls="xitong" onclick="setJfBase()">设置缴费基数</a>
                            <a class="mini-button" iconCls="icon-filter" onclick="stopInsurance()">停保</a>
                            <a class="mini-button" iconCls="shengchan" onclick="insurancePayment()">社保缴费</a>
                            <a class="mini-button" iconCls="caiwu" onclick="jfls()">缴费历史</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    allowCellEdit="true" allowCellSelect="true" allowAlternating="true" multiSelect="true" idField="id" allowResize="false" >
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");
        var pageIndex = 0;
        var pageSize = grid1.getPageSize();
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
            } else {
                grid1.setData([]);
                grid1.setTotalCount(0);
            }
        });
    
        $(function() {
            searchS();
        });
        function searchS() {
            dynamicTable('jobNumber,name,isDaiJiao','jobNumber,name,isDaiJiao');
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
                url: "../salary/salary_insurance!getInsuranceItemsListData.action?pageIndex="+pageIndex+"&pageSize="+pageSize,
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
                    arr.push({ type:"comboboxcolumn", field:"isDaiJiao", width:"80", headerAlign:"center", align:"center", header: "代缴", editor: { type: "combobox", data: "[{id:0, text:'否'},{id:1, text:'是'}]"}});
    
                    // 循环便利所有的福利项目
                    var fulis = mini.decode(jsonData.fulis);
                    var dd = jsonData.data;
                    var insuranceId = "0";
                    if (dd.length > 0) insuranceId = dd[0].insuranceId.split(",");
                    for (var i=0; i< fulis.length;i++) {
                        arr.push({ headerAlign:"center", header: fulis[i].INSUR_NAME, fulli: fulis[i].ID,
                                  columns : [
                                         { field:"AREA"+fulis[i].ID, width:"80", headerAlign:"center", align:"center", header: "缴费地域"},
                                         { field:"COMD"+fulis[i].ID, width:"140", headerAlign:"center", align:"center", header: "缴费单位"},
                                         { field:"COMB"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "公司缴费基数"},
                                         { field:"PERB"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "员工缴费基数"},
                                         { field:"COMJ"+fulis[i].ID, name:"COMJ"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "公司缴费*", fulli: fulis[i].ID,
                                             editor: { type: "textbox", maxLength: 20, vtype:"float;required:true"}},
                                         { field:"PERJ"+fulis[i].ID, name:"PERJ"+fulis[i].ID, width:"100", headerAlign:"center", align:"center", header: "员工缴费*", fulli: fulis[i].ID,
                                             editor: { type: "textbox", maxLength: 20, vtype:"float;required:true"}}
                                  ]});
                    }
                    var table = [];
                    for (var i=0; i< jsonData.total;i++) {
                        var row = dd[i];
                        var column = {id: row.id, jobNumber: row.jobNumber, name: row.name, company: row.company, detpName: row.detpName,
                                isDaiJiao: row.isDaiJiao};
                        var objs = eval(column);
                        if (dd[i].flId != null) {
                            var flId = dd[i].flId.split(",");
                            var jSon = dd[i].jfArea.split(",");
                            var jfCompany = dd[i].jfCompany.split(",");
                            var jsbaseCompany = null;
                            if (dd[i].baseCompany == null) {
                                jsbaseCompany = new Array(flId.length);
                            } else jsbaseCompany = dd[i].baseCompany.split(",");
                            var jsbasePersonal = null;
                            if (dd[i].basePersonal == null) {
                                jsbasePersonal = new Array(flId.length);
                            } else jsbasePersonal = dd[i].basePersonal.split(",");
                            var jssCompany = null;
                            if (row.sCompany == null) {
                                jssCompany = new Array(flId.length);
                            } else jssCompany = row.sCompany.split(",");
                            var jssPersonal = null;
                            if (row.sPersonal == null) {
                                jssPersonal = new Array(flId.length);
                            } else jssPersonal = row.sPersonal.split(",");
                            for (var j=0; j< flId.length;j++) {
                                var aField = "AREA"+flId[j];
                                var bField = "COMD"+flId[j];
                                var cField = "COMB"+flId[j];
                                var dField = "PERB"+flId[j];
                                var eField = "COMJ"+flId[j];
                                var fField = "PERJ"+flId[j];
                                objs[aField] = jSon[j];
                                objs[bField] = jfCompany[j];
                                objs[cField] = jsbaseCompany[j];
                                objs[dField] = jsbasePersonal[j];
                                objs[eField] = jssCompany[j];
                                objs[fField] = jssPersonal[j];
                            }
                        }
                        table.push(column);
                    }
                    grid1.setData(table);
                    grid1.setPageIndex(pageIndex);
                    grid1.setPageSize(pageSize);
                    grid1.set({columns : arr, oncellbeginedit: "onCellBeginEdit"});
                    grid1.hideColumn("id");
                    grid1.frozenColumns(0, 7);
                    grid1.setTotalCount(jsonData.total);
                }
            });
        }

        grid1.on("beforeload", function (e) {
            pageIndex = e.data.pageIndex;
            pageSize = e.data.pageSize;
            $("#searchButton").click();
        });

        function edit() {
            var rows = grid1.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            mini.open({
                url : "../salary/salary_insurance!canbaoEdit.action?id="+ids,
                title : "设置缴费基数",
                width : 890,
                height : 640,
                onload : function() {
                },
                ondestroy : function(action) {
                    searchS();
                }
            });
        }
    
        function stopInsurance() {
            var rows = grid1.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i=1,l=rows.length;i<l;i++) {
                ids += "," + rows[i].id;
            }
            mini.open({
                url : "../salary/salary_insurance!stop.action?ids=" + ids,
                title : "停保处理",
                width : 680,
                height : 480,
                onload : function() {
                },
                ondestroy : function(action) {
                    searchS();
                }
            });
        }

        function addCanBao() {
            var tabs = window.parent.mini.get("mainTabs");
            var id = "参保人员";
            var tab = tabs.getTab(id);
            if (!tab) {
                tab = {};
                tab.name = id;
                tab.title = id;
                tab.showCloseButton = true;
                tab.url = "../salary/salary_insurance!canbaoList.action";
                tabs.addTab(tab);
            }
            tabs.activeTab(tab);
        }

        function blsb() {
            mini.open({
                url : "../salary/salary_insurance!socialsecurity.action?id=",
                title : "办理社保",
                width : 680,
                height : 540,
                onload : function() {
                },
                ondestroy : function(action) {
                }
            });
        }

        function stopInsurance() {
            var rows = grid1.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i=1,l=rows.length;i<l;i++) {
                ids += "," + rows[i].id;
            }
            mini.open({
                url : "../salary/salary_insurance!stop.action?ids=" + ids,
                title : "停保处理",
                width : 680,
                height : 480,
                onload : function() {
                },
                ondestroy : function(action) {
                    searchS();
                }
            });
        }

        function insurancePayment() {
            var tabs = window.parent.mini.get("mainTabs");
            var id = "计算社保缴费";
            var tab = tabs.getTab(id);
            if (!tab) {
                tab = {};
                tab.name = id;
                tab.title = id;
                tab.showCloseButton = true;
                tab.url = "../salary/salary_payment!list.action";
                tabs.addTab(tab);
            }
            tabs.activeTab(tab);
        }

        function jfls() {
            var rows = grid1.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var id = rows[0].id;
            mini.open({
                url : "../salary/salary_insurance!histroy.action?id="+id,
                title : "缴费历史",
                width : 960,
                height : 540,
                onload : function() {
                },
                ondestroy : function(action) {
                }
            });
        }

        // 设置缴费基数
        function setJfBase() {
            var rows = grid1.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            mini.open({
                url : "../salary/salary_insurance!canbaoEdit.action?id="+ids,
                title : "设置缴费基数",
                width : 890,
                height : 640,
                onload : function() {
                },
                ondestroy : function(action) {
                    searchS();
                }
            });
        }

        function addBasejf() {
        	var tabs = window.parent.mini.get("mainTabs");
            var id = "计算缴费基数";
            var tab = tabs.getTab(id);
            if (!tab) {
                tab = {};
                tab.name = id;
                tab.title = id;
                tab.showCloseButton = true;
                tab.url = "../salary/salary_insurance!baseList.action";
                tabs.addTab(tab);
            }
            tabs.activeTab(tab);
        }

        // 保存列表
        function saveBase() {
        	var gridjson="";
            if(grid1){
                // 验证
                grid1.validate();
                if (grid1.isValid() == false) {
                    gridError(grid1);
                    return;
                }
                var data = grid1.getData();
                if(data.length<1){// 空行也算length
                    mini.alert("详细信息为空，保存失败！");
                    return;
                }
                gridjson= mini.encode(data);
            }
            var columns = grid1.columns;
            var len = columns.length;
            var col = "";
            if (len > 7) {
                col = columns[8].fulli;
                for (var i = 9, l = len; i < l; i++) {
                    col += "," + columns[i].fulli;
                }
            }

            window.parent.loading();
            $.ajax({
                url : "../salary/salary_insurance!updateStaffBaseInsurance.action",
                type : "post",
                data : {
                    griddata : gridjson,
                    column: col
                },
                success : function(text) {
                    window.parent.unmask();
                    if (text == "success") {
                        mini.showMessageBox({
                            title: "提醒",
                            width: 250,
                            iconCls: "mini-messagebox-warning",
                            buttons: ["ok"],
                            message: "保存成功",
                            callback: function (action) {
                                searchS();
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

        // 单元格编辑钱监听
        function onCellBeginEdit(e) {
            var field = e.field;
            var record = e.record;
            if (field.indexOf("COMJ") >= 0 || field.indexOf("PERJ") >= 0) {
                if (record[field] == undefined || record[field] == null || record[field] == "") {
                    mini.alert("没有该保险项目，不可编辑");
                    e.cancel = true;
                }
            }
        }
    </script>
</body>
</html>