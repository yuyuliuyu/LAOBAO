<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>岗位设置</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit">
        <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
            <div size="32%" showCollapseButton="true">
                <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
                    <div size="56%" showCollapseButton="true">
                        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                            <span style="padding-left:5px;">组织机构：</span>
                        </div>
                        <div class="mini-fit">
                            <ul id="tree1" class="mini-tree" url="../basis/branch!treeList.action" style="width:100%;height:100%;" onNodeclick="onNodeclick"
                                showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                            </ul>
                        </div>
                    </div>
                    <div showCollapseButton="false">
                        <!-- ckId -->
                        <input type="hidden" value="${sessionScope.id}" id="id" />
                        <!-- 分中心树 start -->
                        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                            <span style="padding-left:5px;">岗位：</span>
                        </div>
                        <div class="mini-fit" style=" border-top:0;">
                            <ul id="tree2" class="mini-tree" style="width:100%;height:100%;" onNodeclick="onNodeGwclick"
                                showTreeIcon="true" textField="positionName" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true" >
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 分中心树  end -->
            <div showCollapseButton="false">
                <div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
                     <table style="width:100%;">
                          <tr>
                            <td style="width:200px;">物品名称：
                                 <input id="wpmc" name="wpmc" class="mini-textbox" emptyText="请输入物品名称" onenter="onKeyEnter"/>
                           </td>
                            <td style="width:200px;">规格型号：
                                 <input id="ggxh" name="ggxh" class="mini-textbox" emptyText="请输入规格型号" onenter="onKeyEnter"/>
                           </td>
<!--                            <td style="width:200px;">岗位：
                               <input id="post" name="post" class="mini-textbox" emptyText="请输入职位" onenter="onKeyEnter"/>
                           </td> -->
                           
                           <td style="white-space:nowrap;">
                               <a id="searchButton" class="mini-button" iconCls="icon-search" onclick="search1('wpmc,ggxh','wpmc,ggxh')" onenter="onKeyEnter">查询</a>
                           </td> 
                         </tr>
                     </table>
                </div>
                <div class="mini-toolbar" style="padding:0px;border-top:0;">
                     <table style="width:100%;">
                         <tr>
                            <td style="width:100%;">
                                <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                                <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                                <a class="mini-button" iconCls="icon-remove" onclick="removes()">删除</a>
                            </td>
                         </tr>
                    </table>
                </div>
                <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
                     idField="id" multiSelect="true" url="../laobao/wupin_gangwei!getGwAndWpData.action"
                     allowAlternating="true" pageSize="20" >
                        <div property="columns">
                            <div type="checkcolumn">选择</div>
                            <div type="indexcolumn" headerAlign="center">序列</div>
                            <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
                            <div field="wpmc" id ="wpmc" name = "wpmc"width="100" headerAlign="center" align="center">物品名称</div>
                            <div field="ggxh" id ="ggxh" name = "ggxh"width="100" headerAlign="center" align="center">规格型号</div>
                            <div field="jldw" id ="jldw" name = "jldw"width="100" headerAlign="center" align="center">计量单位</div>
                            <div field="gys" id ="gys" name = "gys"width="100" headerAlign="center" align="center">供应商</div>
                            <div field="lqjg" width="100" headerAlign="center" align="center">领取间隔</div>
                            <div type="comboboxcolumn" field="lqdw" width="100" headerAlign="center" align="center"vType="required;">领取单位
                                <input property="editor" class="mini-combobox" style="width:100%;" data="[{id:1, text:'年'}, {id:2, text:'月'}]" />
                            </div>
                            <div field="createDate" id ="createDate" name = "createDate"width="100" headerAlign="center" align="center" renderer="onLongDateRenderer">创建时间</div>
                        </div>
                </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var tree1 = mini.get("tree1");
        var tree2 = mini.get("tree2");
        var grid = mini.get("grid");
        grid.hideColumn("id");

        //人员管理基础数据添加
        function add() {
            var node=tree2.getSelectedNode();
            if(node){
                var nodeId=node.id;
                var branchId=node.branchId;
                mini.open({
                    url : "../laobao/wupin_gangwei!add.action?gwId="+nodeId+"&branchId="+branchId,
                    title : "新添加岗位发放物品！",
                    width : 900,
                    height : 600,
                    onload : function() {
                    },
                    ondestroy : function(action) {
                        grid.reload();
                    }
                });
            }else{
                mini.alert("请选择岗位！");
            }
        }
   
        //////////////////////////////////////////////
        //人员管理基础数据修改
        function edit() {
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url : "../laobao/wupin_gangwei!add.action?id="+row.id+"&job_number="+row.jobNumber,
                    title : "编辑人员信息",
                    width : 900,
                    height : 600,
                    
                    onload : function() {
                    },
                    ondestroy : function(action) {
                        grid.reload();
                    }
                });
            } else {
                mini.alert("请选中一条记录！");
            }
        }
         
        ////////////////////////////////////////////////
        function removes() {
            var rows=grid.getSelecteds();
            var l=rows.length
            if (l == 0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for(var i=1;i<l;i++){
                ids=ids+","+rows[i].id;
            }
            mini.confirm("您确定要删除该信息吗？","提醒",function(action){
                if(action=="ok"){
                    window.parent.loading();
                    $.ajax({
                        url : "../laobao/wupin_gangwei!delete.action?ids="+ids,
                        type:'post',
                        success: function (text) {
                            window.parent.unmask();
                            if(text=="success"){
                                grid.reload();
                                mini.alert("删除成功！");
                            }else{
                                mini.alert(text);
                            }
                        }
                    });
                }
            });
        }


        /**
         * 长日期格式化(yyyy-MM-dd HH:mm:ss)
         * zhanghj
         **/
        function onLongDateRenderer(e) {
            var value = e.value;
            if (value) return mini.formatDate(new Date(value), "yyyy-MM-dd ");
            return "";
        }

        // 部门选择
        function onNodeclick(e) {
            var node = tree1.getSelectedNode();
            var nodeId = node.id;
            tree2.setUrl("../post/position!listData.action?id="+node.id);
            tree2.load();
        }

        // 岗位选择
        function onNodeGwclick(e) {
            $("#searchButton").click();
        }

        function search1(name,attr){
            var searchData="{";
            if(name&&attr){
                var names=name.split(",");
                var attrs=attr.split(",");
                for(var i=0;i<names.length;i++){
                    if(mini.get(names[i])){
                        var value=mini.get(names[i]).getValue();
                        searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\"";
                        if(i!=names.length-1)searchData=searchData+",";
                    }
                }
            }
            //如果有树
            if(tree2){
                var node = tree2.getSelectedNode();
                if(node){
                    var ids = node.id;
                    if (searchData == "{"){
                        searchData=searchData+"\"depId\":\""+ids+"\"";
                    } else {
                        searchData=searchData+",\"depId\":\""+ids+"\"";
                    }
                    
                }
            }
            searchData=searchData+"}";
            grid.load({ searchData: searchData });
        }
    </script>
</body>
</html>