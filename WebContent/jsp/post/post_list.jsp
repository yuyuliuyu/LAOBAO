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
<title>基础信息列表</title>
<style type="text/css">
</style>
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
<script src="${base}/template/plugin/public/treeList.js" type="text/javascript"></script>
<style type="text/css">
body {
    margin: 0;
    padding: 0;
    border: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
}
</style>
</head>

<body>
	<div class="mini-fit">
		<div class="mini-splitter" style="width:100%;height:100%;" handlerSize="9">
			<!-- 分中心树 start -->
            
            <div size="240" showCollapseButton="true">
            	<div class="mini-toolbar" style="padding:0px;border-top:0;">
	       			 <table style="width:100%;">
	           			 <tr>
			                <td style="width:100%;">
			                    <a class="mini-button" iconCls="icon-add" onclick="addpost()">增加</a>
			                    <a class="mini-button" iconCls="icon-edit" onclick="editpost()">编辑</a>
			                    <a class="mini-button" iconCls="icon-remove" onclick="deletepost()">删除</a>
			                </td>
	            		 </tr>
	        		</table>
	    		</div>
                <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                    <span style="padding-left:5px;">职务族：</span>
                </div>
                <div class="mini-fit" style=" border-top:0;">
                    <ul id="tree1" class="mini-tree" url="../post/post!treeList.action" style="width:100%;"onNodeclick="onNodeclick"
                        showTreeIcon="true" textField="postName" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                    </ul>
                </div>
            </div>
            <!-- 分中心树  end -->
            <div showCollapseButton="false">
            	<div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
		      		<table style="width:100%;">
		         		<tr>
		         			<td style="width:200px;">编码：
		                  		<input id="positionCode" name="positionCode" class="mini-textbox" emptyText="请输入岗位编码" onenter="onKeyEnter"/>
		             		</td>
		           			<td style="width:200px;">名称：
		                  		<input id="positionName" name="positionName" class="mini-textbox" emptyText="请输入岗位名称" onenter="onKeyEnter"/>
		             		</td>
		              		<td style="white-space:nowrap;">
		                		 <a class="mini-button" iconCls="icon-search" onclick="search('positionCode,positionName','positionCode,positionName')" onenter="onKeyEnter">查询</a>
		              		</td>
		          		 </tr>
		      		</table>
		  		</div>
	            <div class="mini-toolbar" style="padding:0px;border-top:0;">
	       			<table style="width:100%;">
	           			 <tr>
			                <td style="width:100%;">
			                    <a class="mini-button" iconCls="icon-add" onclick="addposition()">设立</a>
			                    <a class="mini-button" iconCls="icon-edit" onclick="editposition()">编辑</a>
			                    <a class="mini-button" iconCls="icon-remove" onclick="removeposition()">删除</a>
			                    <a class="mini-button" iconCls="icon-search" onclick="lookposition()">查看</a>
			                </td>
	            		 </tr>
	        		</table>
	    		</div>
	    		<div class="mini-fit">
					<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
						url="../post/post!positionList.action" idField="id" multiSelect="true" 
						 allowAlternating="true" pageSize="20" >
						 
							<div property="columns">
								<div type="checkcolumn">选择</div>
								<div type="indexcolumn" headerAlign="center">序列</div>
								<div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
								<div field="dutyId" id="dutyId" width="80" name="dutyId" headerAlign="center" align="center" class="mini-hidden">职务族id</div>
								<div field="positionCode" id ="positionCode" name = "positionCode"width="100" headerAlign="center" align="left">编码</div>
								<div field="positionName" id ="positionName" name = "positionName"width="100" headerAlign="center" align="left">名称</div>
								<div field="dutyName" id ="dutyName" name = "dutyName"width="100" headerAlign="center" align="left">职务族</div>
								<div field="positionGrad" id ="positionGrad" name = "positionGrad"width="100" headerAlign="center" align="left">职等</div>
								<div field="applyDate" id ="applyDate" name = "applyDate"width="100" headerAlign="center" align="left">生效日期</div>
								<div type="comboboxcolumn" field="state" width="100" headerAlign="center" align="center">状态
									<input property="editor" class="mini-combobox" data="[{id:1, text:'有效'},{id:0, text:'无效'}]"/>
								</div>
								
							</div>
					</div>
       		 	</div>
            </div>
        </div>
	</div>

    <script type="text/javascript">
        mini.parse();
        
        var grid = mini.get("grid");
        grid.hideColumn("id");
        grid.hideColumn("dutyId");
        grid.load();
        var tree = mini.get("tree1");
        
        //////////////////////////////////////////////
        function addpost() {
 			var node = tree.getSelectedNode();
 			if(node){	
	  			var nodeId = node.id;}
 			else{
	  			var nodeId = "";
 			}
 			 mini.open({
 	                url : "../post/post!dutylistadd.action?id="+nodeId,
 	                title : "添加信息",
 	                width : 600,
 	                height : 500,
 	                /* allowResize : false, */
 	                onload : function() {
 	                },
 	                ondestroy : function(action) {
 	                	treeLoad();
 	                }
 	            }); 
        }
        function editpost() {
 			var node = tree.getSelectedNode();
	  		if(node){					
	  			var nodeId = node.id;
	  			mini.open({
	        		url:"../post/post!dutylistedit.action?id="+nodeId,
	        		title:"信息编辑",
	        		width : 600,
 	                height : 350,
	        		ondestroy : function(action) {
	        			treeLoad();
 	                }
	        	});
	  			return;
	  		}
        }

        // 树重新加载
        function treeLoad() {
        	$.ajax({
                url: "../post/post!treeList.action",
                type: "post",
                success: function (text) {
                    var data = mini.decode(text);
                    tree.loadList(data);
                    tree.expandAll();
                }
            });
        }
        function deletepost() {
 			var node = tree.getSelectedNode();
	    	if(node){
			    var list = tree.getAllChildNodes( tree.getSelectedNode( ));
			    
			    var ids=node.id;
	      		//判断部门是否有下级部门，如果有给出提示，并且删除下级所有的部门
	      		if (tree.hasChildren(node)) {
                	mini.showMessageBox({
                		title:"确定吗？",
                		message:"该职务族下带有下级职务，您确定继续执行吗？",
                		buttons: ["ok", "no"],
                		callback: function(action){
                			if(action =="ok"){
                				 for(var i=0;i<list.length;i++){
                					ids = ids +","+list[i].id;
                                 }
                				 zhixing(ids);
                			}else{
                				return;
                			}
                		}
                	});
                    
                }else{
                	zhixing(ids);
                }
		      	
		    }else{
		    	mini.alert("请先选中节点");
		        return;
		    }
        }
        
        function zhixing(ids){
        	mini.confirm("您确定要删除该信息吗？","提醒",function(action){
                if(action=="ok"){
                    $.ajax({
                        url : "post!deleteDuty.action?id=" + ids,
                        type:'post',
                        success: function (text) {
                            if(text=="success"){
                               
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "删除成功",
                                    callback: function (action) {
                                        document.location.reload();
                                    }
                                });
                            }else{
                                mini.alert(text);
                            }
                        }
                    });
                }
            });
        }
        function addposition() {
        	var node = tree.getSelectedNode();
        	
        	if(node){
        		var cid = node.id;
    			 mini.open({
    	                url : "../post/post!add.action?cid="+cid+"&flg=true",
    	                title : "添加岗位信息",
    	                width : 770,
    	                height : 600,
    	                /* allowResize : false, */
    	                onload : function() {
    	                },
    	                ondestroy : function(action) {
    	                    grid.reload();
    	                }
    	            });
        	}else{
        		mini.alert("请先选择职务族!");
        		return;
        	}
           
        }
        //////////////////////////////////////////////
        function editposition() {
            
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url : "../post/post!editposition.action?id="+row.id+"&flg=true",
                    title : "编辑岗位信息",
                    width : 770,
    	            height : 600,
                    /* allowResize : false, */
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
        //////////////////////////////////////////////
          /* function look() {
           
            
            var tab = { title: "tab", showCloseButton: true };
           
            tabs.addTab(tab);

            tabs.activeTab(tab);

        } */
        function lookposition() {
             var row = grid.getSelected();
            if (row) {
                mini.open({
                    url : "../post/post!lookposition.action?id="+row.id+"&flg=false",
                    title : "查看岗位信息",
                    width : 770,
    	            height : 600,
                    /* allowResize : false, */
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
        function removeposition() {
            var row = grid.getSelected();
            var rows = grid.getSelecteds();
            
            if (rows.length==1) {
        	mini.confirm("您确定要删除该信息吗？","提醒",function(action){
                if(action=="ok"){
                    $.ajax({
                        url : "post!postionDelete.action?id=" + row.id,
                        type:'post',
                        success: function (text) {
                            if(text=="success"){
                               
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "删除成功",
                                    callback: function (action) {
                                        document.location.reload();
                                    }
                                });
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
        
        
       
        
        function onKeyEnter(e) {
            search('positionCode,positionName','positionCode,positionName');
        }
        
    	function onNodeclick(e) {
    		
	    	var node = tree.getSelectedNode();
		  	var nodeId = node.id;
    		grid.setUrl("../post/post!positionList.action?id="+node.id);
            grid.load();
     }

    </script>
</body>
</html>