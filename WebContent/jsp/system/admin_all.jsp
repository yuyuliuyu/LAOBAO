<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>用户管理列表</title>
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
    <div class="mini-fit">
        <div class="mini-splitter" style="width:100%;height:100%;">
        	<!-- 分中心树 start -->
            <div size="200" showCollapseButton="true">
                <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                    <span style="padding-left:5px;">单位名称：</span>
                </div>
                <div class="mini-fit" style=" border-top:0;">
                    <ul id="tree1" class="mini-tree" url="../basis/branch!getBranchList.action" style="width:100%;height:100%;"
                        showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                    </ul>
                </div>
            </div>
            <!-- 分中心树  end -->
            
            <!-- 部门  在部门和用户之间使用split start -->
            <div showCollapseButton="false">
            <div class="mini-splitter" style="width:100%;height:100%;">
            	<!-- 部门 start  -->
            	<div showCollapseButton="true" size="280">
            	
            	  <div class="mini-toolbar" style="border-top:0;border-left:0; padding:0px;">
                    <table style="width:100%;">
                        <tr>
                            <td style="white-space:nowrap;float:left;">部门名称：
<%--                                <input id="bmmc"name="bmmc" class="mini-textbox" emptyText="请输入部门名称" onenter="onbmKeyEnter"/>--%>
<%--                                <a class="mini-button" onclick="bmsearch()" iconCls="icon-search">查询</a>--%>
                            </td>
                        </tr>
                    </table>
                   </div>
                    <div class="mini-fit">
	                <div id="grid1" class="mini-treegrid" style="width:100%;height:100%;" showTreeIcon="true" 
	            		 expandOnLoad="true"
	            		 treeColumn="name" idField="did" parentField="pid" resultAsTree="false">
	                    <div property="columns">
	                        <div type="checkcolumn" width="20">选择</div>
	                        <div type="indexcolumn" width="20" headerAlign="center">序列</div>
	                        <div field="name" id ="name" name ="name"width="80" headerAlign="center" align="left">部门名称</div>
	                        <div field="pid" id="pid" width="80" name="pid" headerAlign="center" align="center" class="mini-hidden"></div>
	                        <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden"></div>
	                        <div field="did" id="did" width="80" name="did" headerAlign="center" align="center" class="mini-hidden"></div>
							<div field="cid" id="cid" width="80" name="cid" headerAlign="center" align="center" class="mini-hidden"></div>
	                    </div>
                    </div>
                </div>
            </div>
            	<!-- 部门end -->
            	<!-- 用户 start -->
           <div showCollapseButton="false">
           <div class="mini-toolbar" style="border-top:0;border-left:0; padding:0px;">
                    <table style="width:100%;">
                        <tr>
                            <td style="width:100%;">
                                <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                                <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                                <a class="mini-button" iconCls="icon-cut" onclick="unable()">禁用</a>
                                <a class="mini-button" iconCls="icon-ok" onclick="able()">启用</a>
                                <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a> 
                                <a class="mini-button" iconCls="icon-edit" onclick="authEdit()">展示权限</a>
                                <a class="mini-button" iconCls="icon-edit" onclick="showEdit()">数据权限</a>
                                <a class="mini-button" iconCls="icon-edit" onclick="superauth()">生成超级权限</a>
                                <%-- <a class="mini-button" iconCls="icon-zhuanyibumen" onclick="change()">转移部门</a>--%>
                            </td>
                            <td style="white-space:nowrap;">用户名：
                               <input id="key" class="mini-textbox" emptyText="请输入用户名或姓名" onenter="onKeyEnter"/>
                               <a class="mini-button" onclick="searchd()" iconCls="icon-search">查询</a>
                           </td> 
                        </tr>
                    </table>
            </div>
            <div class="mini-fit">
            <div id="grid2" class="mini-datagrid" style="width:100%;height:100%;"
              allowAlternating="true" idField="id"  pageSize="20" showPager="true" multiSelect="true" >
                    
                    <div property="columns">
                        <div type="checkcolumn" width="50" ></div>
                        <div type="indexcolumn" width="50" headerAlign="center">序列</div>
                        <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden"></div>
						<div field="did" id="did" width="80" name="did" headerAlign="center" align="center" class="mini-hidden"></div>
						<div field="cid" id="cid" width="80" name="cid" headerAlign="center" align="center" class="mini-hidden"></div>
                        <div field="username" width="100" headerAlign="center" align="center">用户名</div>
                        <!-- <div field="inputCode" width="100" headerAlign="center" align="center">输入码</div> -->
                        <div headerAlign="center" header="基本信息">
                            <div property="columns">
                                <div field="name" width="100" headerAlign="center" align="center">姓名</div>
                                <div field="email" width="100" headerAlign="center" align="center">邮箱</div><!-- 
                                <div field="sdiscount" id="sdiscount" name="sdiscount" width="60" headerAlign="center" align="center">折扣</div>
                                <div field="ldiscount" id="ldiscount" name="ldiscount" width="60" headerAlign="center" align="center">领导折扣</div>
                                <div field="isleader" width="100" headerAlign="center" align="center">是否为领导</div> -->
                            </div>
                        </div>
                         <div field="enable" width="80" headerAlign="center" align="center">状态</div>
                        <div field="createdate" width="120" headerAlign="center" align="center" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss">创建时间</div>
                    </div>
                </div>
                </div>
             </div>
             <!-- 用户end -->  
                
            </div>
        </div>
        <!-- 部门用户split end -->
    </div>
    </div>
    <script type="text/javascript">
        mini.parse();
        
        var tree = mini.get("tree1");
        var treegrid = mini.get("grid1");
        var grid = mini.get("grid2");
        
        treegrid.hideColumn("id");
        treegrid.hideColumn("pid");
		treegrid.hideColumn("cid");
		treegrid.hideColumn("did");
		grid.hideColumn("id");
        grid.hideColumn("did");
		grid.hideColumn("cid");

        tree.on("nodeselect", function (e) {
            var node = tree.getSelectedNode();
            if (node) {
				treegrid.setUrl("../basis/branch!depData.action");
                treegrid.load({cid:node.id});
            	
            } else {
                //grid.setData([]);
               // grid.setTotalCount(0);
            }
        });
        
        treegrid.on("nodeselect",function(e){
        	var node = tree.getSelectedNode();
        	var tnode = e.node;
        	var pid=tnode.pid;
        	var did=tnode.did;
        	var depName=e.node.name;
        	if(node){
        		if(tnode){
        			var ids = e.node.id;
        			var list = treegrid.getChildNodes(treegrid.getSelectedNode());
                    for ( var i = 0; i < list.length; i++) {
                        ids = ids + "," + list[i].id;
                    }
        			grid.setUrl("../system/admin!getUserList.action");
        			grid.load({cid:node.id,depId:ids,key: mini.get("key").getValue()});
        		}
        	}
            
        });
        
        //////////////////////////////////////////////
        function add() {
           var node = tree.getSelectedNode();
		   var row1 = treegrid.getSelectedNode();
            if(node){
				/*if(!tree.isLeaf(node)){
					mini.alert("选中的分中心不能有子节点!");
					return;
				}*/
				if(row1){
					/*if(!treegrid.isLeaf(row1)){
						mini.alert("选中的部门不能有子节点!");
						return false;
					}*/
					//判断是否为销售部门
					var pid=row1.pid;
					var did=row1.did;
					var m="无";
            		mini.open({
						url: "admin!add.action?cid="+node.id+"&depId="+did+"&depName="+m,
						title: "增加用户",
						width: 840,
						height: 370,
						//allowResize:false,
						onload: function () {
						},
						ondestroy: function (action) {
							grid.reload();
						}
					});
				}else{
					mini.alert("请先选择部门！");
				}
                
            }else{
                mini.alert("请先选择分中心！");
            }
        }
        function unable(){
        	var node = tree.getSelectedNode();
        	var rows = grid.getSelecteds();
            if (rows.length == 1) {
                mini.confirm("您确定要禁用该用户吗？","提醒",function(action){ 
                    if(action=="ok"){  
                        $.ajax({
                            url: "admin!unable.action?ids=" +rows[0].id,
                            type:'post',
                            success: function (text) {
                                if(text=="success"){
                                    mini.alert("禁用成功！");
                                    grid.reload();
                                }else{
                                    mini.alert(text);
                                    grid.reload();
                                }
                            }
                        });
                    }
                });
            } else {
                mini.alert("请选中一条记录！");
            }
        }
        function able(){
        	var node = tree.getSelectedNode();
        	var rows = grid.getSelecteds();
            if (rows.length == 1) {
                mini.confirm("您确定解禁该用户吗？","提醒",function(action){
                    if(action=="ok"){
                        $.ajax({
                            url: "admin!able.action?ids=" +rows[0].id,
                            type:'post',
                            success: function (text) {
                                if(text=="success"){
                                    mini.alert("启用成功！");
                                    grid.reload();
                                }else{
                                    mini.alert(text);
                                    grid.reload();
                                }
                            }
                        });
                    }
                });
            } else {
                mini.alert("请选中一条记录！");
            }
        }
        function edit() {
            var node = tree.getSelectedNode();
            var deptNode = treegrid.getSelectedNode();
        	var rows = grid.getSelecteds();
            if (rows.length == 1) {
                mini.open({
                    url: "admin!edit.action?id="+rows[0].id+"&cid="+node.id+"&depId="+deptNode.id,
                    title: "编辑用户",
                    width: 840,
                    height: 370,
                    /* allowResize:false, */
                    onload: function () {
                    },
                    ondestroy: function (action) {
                        grid.reload();
                    }
                });
                
            } else {
                mini.alert("请选中一条记录！");
            }
        }
        function showEdit(){
        	var node = tree.getSelectedNode();
        	var rows = grid.getSelecteds();
            if (rows.length == 1) {
                mini.open({
                    url: "admin!datauthshow.action?id="+rows[0].id,
                    title: "编辑数据权限",
                    width: 360,
                    height: 470,
                    /* allowResize:false, */
                    onload: function () {
                    },
                    ondestroy: function (action) {
                        grid.reload();
                    }
                });
                
            } else {
                mini.alert("请选中一条记录！");
            }
        }
        function authEdit(){
        	var node = tree.getSelectedNode();
        	var rows = grid.getSelecteds();
            if (rows.length == 1) {
                mini.open({
                    url: "admin!datauthAdd.action?id="+rows[0].id,
                    title: "编辑展示权限",
                    width: 360,
                    height: 470,
                    /* allowResize:false, */
                    onload: function () {
                    },
                    ondestroy: function (action) {
                        grid.reload();
                    }
                });
                
            } else {
                mini.alert("请选中一条记录！");
            }
        }
        
        
        function change() {
            var rows = grid.getSelecteds();
            var node = treegrid.getSelectedNode();
            
            if(node){
                if (rows.length ==1) {
                    var ids = [];
                    for (var i = 0, l = rows.length; i < l; i++) {
                        var r = rows[i];
                        ids.push(r.id);
                    }
                    mini.open({
                        url: "admin!change.action?id="+ids+"&depId="+node.did,
                        title: "改变用户所属部门",
                        width: 395,
                        height: 220,
                        /* allowResize:false, */
                        onload: function () {
                        },
                        ondestroy: function (action) {
                            grid.reload();
                        }
                    });
                    
                } else {
                    mini.alert("请选中一条记录！");
                }
            }else{
                mini.alert("请先选中一个部门！");
            }
        }
        /*生成超级用户权限*/
        function superauth() { 
            $.ajax({
                url: "admin!superauth.action",
                type:'post',
                success: function (text) {
                    if(text=="success"){
                        mini.alert("超级权限生成成功！"); 
                    }else{
                        mini.alert(text); 
                    }
                }
            });
        }
        function remove() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                mini.confirm("您确定要删除该信息吗？","提醒",function(action){
                    if(action=="ok"){
                        var ids = [];
                        for (var i = 0, l = rows.length; i < l; i++) {
                            var r = rows[i];
                            ids.push(r.id);
                        }
                        $.ajax({
                            url: "admin!remove.action?ids=" +ids,
                            type:'post',
                            success: function (text) {
                                if(text=="success"){
                                    mini.alert("删除成功！");
                                    grid.reload();
                                }else{
                                    mini.alert(text);
                                    grid.reload();
                                }
                            }
                        });
                    }
                });
            } else {
                mini.alert("请选中一条或多条记录！");
            }
        }
        function  bmsearchd(){
        	var name = mini.get("bmmc").getValue();
            if(name==""||typeof(name)==undefined) {
               key="null";
            }
            var node = tree.getSelectedNode();
            
            if(node){
                treegrid.load({ key: name,searchBy:"name",cid:node.Id });
            }else{
                mini.alert("请先选中一个分中心！");
                return;
            }
        }
        function searchd() {
            var key = mini.get("key").getValue();
            var node = tree.getSelectedNode();
            var treenode = treegrid.getSelectedNode();
            var cid = "";
            var depId = "";
            grid.setUrl("../system/admin!getUserList.action");
            if(node){
                cid = node.id;
            	if(treenode){
            		depId = treenode.did;
            	}
            }
            grid.load({ "key": key,"depId":depId,"cid":cid });
        }

        grid.on("rowclick", function(e) {
            var row = e.row;
            var node = tree.getSelectedNode();
            if (node) {
                if (node.id != row.cid) {
                    searchNode(tree, row.cid, "id");
                }
            } else {
                searchNode(tree, row.cid, "id");
            }
        });

        treegrid.on("load", function(e) {
        	var gridRow = grid.getSelected();
            var treenode = treegrid.getSelectedNode();
            if (treenode) {
            	if (gridRow) {
	                if (treenode.id != gridRow.bid) {
	                    searchNode(treegrid, gridRow.bid, "id");
	                }
            	}
            } else {
            	if (gridRow) {
	                searchNode(treegrid, gridRow.bid, "id");
            	}
            }
        });

        function searchNode(n, id, c) {
            //查找到节点
            var nodes = n.findNodes(function (node) {
                var text = node[c] ? node[c] : "";
                if (text == id) {
                    return true;
                }
            });

            //展开所有找到的节点
            for (var i = 0, l = nodes.length; i < l; i++) {
                var node = nodes[i];
                n.expandPath(node);
            }

            //第一个节点选中并滚动到视图
            var firstNode = nodes[0];
            if (firstNode) {
                n.selectNode(firstNode);
                n.scrollIntoView(firstNode);
            }
        }
        
        function onKeyEnter(e) {
            searchd();
        }
        function onbmKeyEnter(e) {
        	bmsearch();
        }
    </script>
</body>
</html>