<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>角色管理列表</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <style type="text/css">
    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }
    </style>
    <script src="${base}template/miniui/boot.js" type="text/javascript"></script>
    <link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css" />
    <!--引入皮肤样式-->
    <link href="${base}template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div class="mini-toolbar" style="padding:0px;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                    <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                    <!-- <a class="mini-button" iconCls="icon-edit" onclick="head()">动态表头展示</a> -->
                    <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
                </td>
                <td style="white-space:nowrap;">
                    <input id="key" class="mini-textbox" emptyText="请输入角色名称" style="width:150px;" onenter="onKeyEnter"/>
                    <a class="mini-button" onclick="search()">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit" >
        <div id="dept_grid" class="mini-datagrid" style="width:100%;height:100%;"
             url="backend_role!json.action" resultAsTree="false" allowResize="false"
             onshowrowdetail="onShowRowDetail" allowAlternating="true" pageSize="50" >
            <div property="columns">
                <div type="checkcolumn" width="20">选择</div>
                <div type="indexcolumn" headerAlign="center" width="20">序列</div>
                <div type="expandcolumn" width="20">#</div>
                <div field="name" name="name" width="100" headerAlign="center" align="center">角色名称</div>
                <div field="isSystem" name="isSystem" width="80" headerAlign="center" align="center">系统内置</div>
                <div field="description" name="description" width="100" headerAlign="center" align="center">角色描述</div>
                <div field="createdate" name="createdate" width="80" headerAlign="center" align="center" dateFormat="yyyy-MM-dd HH:mm:ss">创建时间</div>
            </div>
        </div>
        <div id="detailGrid_Form" style="display:none;">
            <div id="employee_grid" class="mini-datagrid" style="width:100%;height:200px;" 
                 url="backend_role!getuser.action" idField="id" pageSize="10" showPager="true">
                 
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center">序列</div>
                    <div field="username" width="100" headerAlign="center" align="left">用户名</div>
                    <div header="基本信息" headerAlign="center">
                        <div property="columns">
                            <div field="name" width="100" headerAlign="center" align="left">姓名</div>
                            <div field="email" width="100" headerAlign="center" align="left">邮箱</div>
                        </div>
                    </div>
                    <div field="createdate" width="80" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss" align="left">创建时间</div>
                </div>
                
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
        mini.parse();
        
        var dept_grid = mini.get("dept_grid");
        var employee_grid = mini.get("employee_grid");
        var detailGrid_Form = document.getElementById("detailGrid_Form");
        dept_grid.load();
        
        function onShowRowDetail(e) {
            var grid = e.sender;
            var row = e.record;
            
            var td = grid.getRowDetailCellEl(row);
            td.appendChild(detailGrid_Form);
            detailGrid_Form.style.display = "block";
            
            employee_grid.load({ id: row.id });
        }
        
        ///////////////////////////////////////////////////////   
        function add() {
            mini.open({
                url: "../system/backend_role!function.action?id=null",
                title: "新增角色",
                width: 810,
                height: 450,
                /* allowResize : false, */
                ondestroy: function (action) {
                    dept_grid.reload();
                }
            });
        }
        
        function edit() {
            var row = dept_grid.getSelected();
            if (row) {
                mini.open({
                    url: "../system/backend_role!function.action?id="+row.id,
                    title: "编辑角色",
                     width: 810,
               		 height: 450,
                    /* allowResize : false, */
                    ondestroy: function (action) {
                        dept_grid.reload();
                    }
                });
            }else{
                mini.alert("请选中一条记录！");
            }
        }
        
       /*  function show() {
            var row = dept_grid.getSelected();
            if (row) {
                mini.open({
                    url: "../system/backend_role!show.action?id="+row.id,
                    title: "提醒角色",
                     width: 810,
               		 height: 450,
                    ondestroy: function (action) {
                       if(action=="ok"){
                            dept_grid.reload();
                        }
                    }
                });
            }else{
                mini.alert("请选中一条记录！");
            }
        } */
        
        ////////////////////////////////////
        function head() {
            var row = dept_grid.getSelected();
            if (row) {
                mini.open({
                    url: "../jcsj/table_head!show.action?id="+row.id,
                    title: "动态表头展示",
                  	 width: 810,
               		 height: 450,
                    ondestroy: function (action) {
                       if(action=="ok"){
                            mini.alert("保存成功！");
                            dept_grid.reload();
                        }
                    }
                });
            }else{
                mini.alert("请选中一条记录！");
            }
        }
        
        //////////////////////////////////////
        function showTab(uid){
            var row = dept_grid.getRowByUID(uid);
            mini.open({
                url:"backend_role!resouce.action?id="+row.id,
                title:"权限内容",
                width:600,
                height:460,
                ondestroy:function(action){
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        
                        if(data.id==null||data.id==""||data.name==null||data.name==""){
                            mini.alert("权限，角色名不能为空！");
                            return;
                        }
                        $.ajax({
                            url: "backend_role!savedata.action?id="+row.id,
                            type:'post',
                            data:{
                                data:data.id,
                                name:data.name,
                                desc:data.desc
                            },
                            success: function (text) {
                                dept_grid.reload();
                            }
                        });
                    }
                }
            });
        }
        
        function remove() {
            var row = dept_grid.getSelected();
            if (row) {
                mini.confirm("您确定要删除该信息吗？","提醒",function(action){
                    if(action=="ok"){
                        $.ajax({
                            url: "backend_role!delete.action?id=" +row.id,
                            success: function (text) {
                                if(text=="success"){
                                    dept_grid.reload();
                                    mini.alert("删除成功！");
                                }else{
                                   dept_grid.reload();
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
            var searchData="{";
            if(key!=null&&key!=""&&typeof(key)!=undefined){
            	searchData=searchData+"\"name\":"+"\""+key+"\"";
            }
            searchData= searchData+"}";
            dept_grid.load({searchData:searchData});
           // dept_grid.load({ key: key });
        }
        
        function onKeyEnter(e) {
            search();
        }
        
    </script>
</body>
</html>