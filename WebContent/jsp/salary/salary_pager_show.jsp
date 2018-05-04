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
    <title>个人工资单</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="onKeyEnter"/>
                    &nbsp;姓名：<input class="mini-textbox" id="staffName" onenter="onKeyEnter"/>
                    &nbsp;公司：<input class="mini-textbox" id="companyName" onenter="onKeyEnter"/>
                    &nbsp;部门：<input class="mini-textbox" id="deptName" onenter="onKeyEnter"/>
                    &nbsp;员工类型：
                    <input class="mini-combobox" id="empType" showNullItem="true" url="../system/dectionary!listjson.action?id=402881935baceaa1015bad355ba8000a"
                        textField="dicname" valueField="dicvalue" onenter="onKeyEnter"/>
                    &nbsp;劳务公司：<input class="mini-textbox" id="laowu" onenter="onKeyEnter"/>
                    &nbsp;薪酬期间：
                    <div id="salaryPeriod" class="mini-combobox" popupWidth="200" textField="name" valueField="id" onenter="onKeyEnter"
                        url="../salary/salary_pager!getSpeialMonthPeriod.action" multiSelect="true" showClose="true" oncloseclick="onCloseClick" >
                        <div property="columns">
                            <div header="薪酬期间" field="name"></div>
                        </div>
                    </div>
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20" allowHeaderWrap="true"
            allowAlternating="true" idField="id" allowResize="false" borderStyle="border-center:0px;border-right:0px;">
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");
        var pageIndex = 0;
        var pageSize = grid1.getPageSize();

        $(function() {
            searchS();
        });
        function searchS() {
            dynamicTable('jobNumber,staffName,companyName,deptName,empType,laowu,salaryPeriod','jobNumber,staffName,companyName,deptName,empType,laowu,salaryPeriod');
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
                url: "../salary/salary_pager!getListData.action?pageIndex="+pageIndex+"&pageSize="+pageSize,
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
                    arr.push({ field:"staffName", width:"100", headerAlign:"center", align:"center", header: "姓名"});
                    arr.push({ field:"companyName", width:"150", headerAlign:"center", align:"center", header: "公司"});
                    arr.push({ field:"detpName", width:"80", headerAlign:"center", align:"center", header: "部门"});
                    arr.push({ field:"laowu", width:"150", headerAlign:"center", align:"center", header: "劳务公司"});
                    arr.push({ type:"comboboxcolumn", field:"empType", width:"80", headerAlign:"center", align:"center", header: "员工类型",
                        editor: { type: "combobox", url: "../system/dectionary!listjson.action?id=402881935baceaa1015bad355ba8000a", textField:"dicname",valueField:"dicvalue"}});
                    arr.push({ field:"periodName", width:"80", headerAlign:"center", align:"center", header: "薪酬期间"});

                    // 循环便利所有的薪资项目
                    var fulis = mini.decode(jsonData.itmeTotal);
                    var dd = jsonData.data;
                    var totalItem = Array(fulis.length);
                    for (var i=0; i< fulis.length;i++) {
                        arr.push({ field: fulis[i].IBF_ID, width:"80", headerAlign:"center", align:"center", header: fulis[i].ITEMS_NAME});
                        totalItem[fulis[i].IBF_ID] = 0;
                    }
                    var table = [];
                    for (var i=0; i< jsonData.total;i++) {
                        var row = dd[i];
                        var column = {id: row.id, jobNumber: row.jobNumber, staffName: row.staffName, companyName: row.companyName, detpName: row.deptName,
                                empType: row.empType, laowu: row.laowu, periodName: row.periodName};
                        var objs = eval(column);
                        var newObjs = $.extend({}, objs, totalItem);
                        if (dd[i].itemId != null) {
                            var flId = dd[i].itemId.split(",");
                            var salaryValue = dd[i].salaryValue.split(",");
                            for (var j=0; j< flId.length;j++) {
                                newObjs[flId[j]] = salaryValue[j];
                            }
                        }
                        table.push(newObjs);
                    }
                    grid1.setData(table);
                    grid1.setPageIndex(pageIndex);
                    grid1.setPageSize(pageSize);
                    grid1.set({columns : arr});
                    grid1.hideColumn("id");
                    grid1.frozenColumns(0, 9);
                    grid1.setTotalCount(jsonData.total);
                }
            });
        }

        grid1.on("beforeload", function (e) {
            pageIndex = e.data.pageIndex;
            pageSize = e.data.pageSize;
            $("#searchButton").click();
        });

        function onCloseClick(e) {
            var obj = e.sender;
            obj.setText("");
            obj.setValue("");
        }
    </script>
</body>
</html>