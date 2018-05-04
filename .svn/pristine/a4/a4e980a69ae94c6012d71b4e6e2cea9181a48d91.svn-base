<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>资源管理列表</title>
    <style type="text/css">
    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }
    </style>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <!--引入皮肤样式-->
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                    <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                    <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
                </td>
               
            </tr>
        </table>
    </div>
   
    <div class="mini-fit">
        <div id="treegrid1" class="mini-treegrid" style="width:100%;height:100%;"
             url="backend_res!getdata.action" showTreeIcon="true" treeColumn="taskname" 
             idField="UID" parentField="ParentTaskUID" resultAsTree="false"  
             allowResize="false" expandOnLoad="false">
             
            <div property="columns">
                <div type="checkcolumn" >选择</div>
                <div type="indexcolumn" headerAlign="center">序列</div>
                <div field="Id" id="Id" width="80" name="idColumn" headerAlign="center" class="mini-hidden"></div>
                <div field="Name" name="taskname" headerAlign="center" width="100" >资源名称</div>
                <div field="PercentComplete" headerAlign="center" align="center" width="100">资源描述</div>
                <div field="Url" headerAlign="center" align="center"  width="100">资源路径</div>
                <div field="ModuleId" headerAlign="center" align="center"  width="60">菜单层级</div>
                <div field="Show" headerAlign="center" align="center"  width="60">菜单显示</div>
                <div field="Sortorder" headerAlign="center" align="center"  width="60">排列次序</div>
                <div type="comboboxcolumn" field="modelArea" width="60" headeralign="center" align="center" >模块名称
                    <input property="editor" valueField="id" textField="text" class="mini-combobox" style="width:100%;"
                        data="[{id:1,text:'系统管理'},{id:2,text:'人事管理'},{id:3,text:'考勤管理'},{id:4,text:'薪酬管理'}]"/>
                </div>
                <div field="Createdate" width="80" headerAlign="center" align="center" dateFormat="yyyy-MM-dd HH:mm">创建时间</div>
                <div field="cz" width="60" headerAlign="center" align="center" renderer="onActionRenderer">操作</div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
        mini.parse();
        
        var grid = mini.get("treegrid1");
        grid.hideColumn("idColumn");
        
        function onActionRenderer(e) {
            var s = '<a class="New_Button" href="javascript:newAu()">权限操作</a>';
            return s;
        }
        
        function newAu(){
            var tree = mini.get("treegrid1");
            var row = grid.getSelected();
            var name=encodeURI(row.Name);
            if (!tree.hasChildren(tree.getSelectedNode())) {
                mini.open({
                    url: "../resource/backend_res!auth.action?parent="+name,
                    type : "get",
                    title: "权限操作",
                    width: 760,
                    height: 450,
                    onload: function () {
                    },
                    ondestroy: function (action) {
                    }
                });
            }else{
                mini.alert("父级菜单无操作权限！");
            }
        }
        
        //////////////////////////////////////////////
        function add() {
            mini.open({
                url: "../resource/backend_res!add.action",
                title: "新增资源",
                width: 410,
                height: 335,
                /* allowResize:false, */
                onload: function () {
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        grid.reload();
                    }
                }
            });
        }
        
        //////////////////////////////////////////////
        function edit() {
            var ids="";
            var tree = mini.get("treegrid1");
            var node = tree.getSelectedNode();
            if(node){
                var list = tree.getChildNodes (node);
                for(var i=0;i<list.length;i++){
                    ids = ids +","+list[i].Id;
                }
            }
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url: "../resource/backend_res!edit.action?id="+row.Id+"&child="+ids,
                    title: "编辑资源",
                    width: 410,
                    height: 335,
                    /* allowResize:false, */
                    onload: function () {
                    },
                    ondestroy: function (action) {
                        if(action=="ok"){
                            grid.reload();
                        }
                    }
                });
            } else {
                mini.alert("请选中一条记录！");
            }
        }
        
        function remove() {
            var row = grid.getSelected();
            if (row) {
                var tree = mini.get("treegrid1");
                var list = tree.getAllChildNodes( tree.getSelectedNode( ));
                var ids = row.Id;
                if (tree.hasChildren(tree.getSelectedNode())) {
                     if (confirm("该资源权限下带有子权限资源，您确定继续执行吗？")) {
                         for(var i=0;i<list.length;i++){
                             ids = ids +","+list[i].Id;
                         }
                     }else{
                         return;
                     }
                 }
                 mini.confirm("您确定要删除该信息吗？","提醒",function(action){
                    if(action=="ok"){
                        $.ajax({
                            url: "backend_res!delete.action?id=" +ids,
                            type:'post',
                            success: function (text) {
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
            } else {
                 mini.alert("请选中一条记录！");
            }
        }
        
        function search() {
            var key = mini.get("key").getValue();
            grid.load({ key: key });
        }
        
        function onKeyEnter(e) {
            search();
        }
    </script>
</body>
</html>