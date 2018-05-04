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
    <title>薪酬审批色值列表</title>
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
                <ul id="tree1" class="mini-tree" url="../basis/branch!getTreeListByUser.action" style="width:100%;height:100%;"
                    showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                </ul>
            </div>
        </div>
        <div showCollapseButton="true">
             <div class="mini-toolbar" style="padding:0px;border-top:0;">
                <table style="width:100%;">
                    <tr>
                        <td style="width:100%;">
                            &nbsp;薪酬名称：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('name','name')">查询</a>
                            <br><a class="mini-button" iconCls="icon-add" onclick="addNews()">增加</a>
                            <a class="mini-button" iconCls="icon-edit" onclick="editWindow('薪酬审批色值','salary','check_salary',620,380,false)">编辑</a>
                            <a class="mini-button" iconCls="icon-remove" onclick="remove('','salary','check_salary', false)">删除</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="50"
                    allowAlternating="true" multiSelect="true" idField="id" allowResize="false">
                    <div property="columns">
                        <div type="checkcolumn" width="40"></div>
                        <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                        <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                        <div field="salaryName" width="140" headerAlign="center" align="center">薪资项目名称</div>
                        <div field="color" width="80" headerAlign="center" align="center">颜色</div>
                        <div field="colorDiff" width="80" headerAlign="center" align="center">差值</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        var tree = mini.get("tree1");
        tree.on("nodeselect", function (e) {
            var node = tree.getSelectedNode();
            if (node) {
                var dId = "";
                if (node.pid != "-1") {
                    dId = node.branchId;
                }
                grid.setUrl("../salary/check_salary!getListData.action?depId="+dId);
                $("#searchButton").click();
            } 
        });

        function addNews() {
            var node = tree.getSelectedNode();
            if (node) {
                var branchId = node.branchId;
                if (branchId) {
                    mini.open({
                        url : "../salary/check_salary!add.action?depId="+branchId,
                        title : "增加薪酬审批色值",
                        width : 620,
                        height : 380,
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
    </script>
</body>
</html>