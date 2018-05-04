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
<script type="text/javascript" src="../template/plugin/public/cent_dep.js"></script>
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
            <div size="260" showCollapseButton="true">
                <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                    <span style="padding-left:5px;">公司名称：</span>
                </div>
                <div class="mini-fit">
                    <ul id="tree1" class="mini-tree" url="../basis/branch!compList.action" style="width:100%;" onNodeclick="onNodeclick"
                        showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                    </ul>
                </div>
            </div>

            <!-- 分中心树  end -->
            <div showCollapseButton="false">
            	<div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
		      		<table style="width:100%;">
		         		<tr>
		           			<td style="width:200px;">名称：
		                  		<input id="name" name="name" class="mini-textbox" emptyText="请输入名称" onenter="onKeyEnter"/>
		             		</td>
		              		<td style="white-space:nowrap;">
		                		 <a class="mini-button" iconCls="icon-search" onclick="search('name','name')" onenter="onKeyEnter">查询</a>
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
			                    <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
			                </td>
	            		 </tr>
	        		</table>
	    		</div>
	    		<div class="mini-fit">
					<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
						url="../post/position_grade!listdata.action" idField="id" multiSelect="true" 
						 allowAlternating="true" pageSize="20" >
						<div property="columns">
	                        <div type="checkcolumn">选择</div>
	                        <div type="indexcolumn" headerAlign="center">序列</div>
	                        <div field="id" id="id" name="id" width="80" headerAlign="center" align="center" class="mini-hidden">id</div>
	                        <div field="companyId" id="companyId" width="80" name="companyId" headerAlign="center" align="center" class="mini-hidden">公司id</div>
	                        <div field="fzx" id ="fzx" name = "fzx"width="100" headerAlign="center" align="left">公司名称</div>
	                        <div field="sortNo" id ="sortNo" name = "sortNo"width="100" headerAlign="center" align="left">序号</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">名称</div>
	                        <div field="positionLayer" id ="positionLayer" name = "positionLayer"width="100" headerAlign="center" align="left">职层</div>
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
        grid.hideColumn("companyId");
        grid.load();
        var tree = mini.get("tree1");
        
        
        function add() {
        	var node = tree.getSelectedNode();
        	
        	if(node){
        		var cid = node.id;
    			 mini.open({
    	                url : "../post/position_grade!add.action?cid="+cid+"&flg=true",
    	                title : "添加岗位信息",
    	                width : 770,
    	                height : 400,
    	                /* allowResize : false, */
    	                onload : function() {
    	                },
    	                ondestroy : function(action) {
    	                    grid.reload();
    	                }
    	            });
        	}else{
        		mini.alert("请先选择公司!");
        		return;
        	}
           
        }
        //////////////////////////////////////////////
        function edit() {
            
            var row = grid.getSelected();
            var rows = grid.getSelecteds();
            if(rows.length==1){
                mini.open({
                    url : "../post/position_grade!edit.action?id="+row.id+"&flg=true",
                    title : "编辑岗位信息",
                    width : 770,
    	            height : 400,
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
        function remove() {
            var row = grid.getSelected();
            var rows = grid.getSelecteds();
            if(rows.length==1){
            
        	mini.confirm("您确定要删除该信息吗？","提醒",function(action){
                if(action=="ok"){
                    $.ajax({
                        url : "../post/position_grade!delete.action?id=" + row.id,
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
            search('name','name');
        }
        
    	function onNodeclick(e) {
    		
	    	var node = tree.getSelectedNode();
		  	var nodeId = node.id;
    		grid.setUrl("../post/position_grade!listdata.action?id="+node.id);
            grid.load();
     }

    </script>
</body>
</html>