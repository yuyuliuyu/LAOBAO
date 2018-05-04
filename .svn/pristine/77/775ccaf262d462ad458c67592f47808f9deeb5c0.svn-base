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
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>动态表头设置</title>
<style type="text/css">
</style>
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../template/plugin/public/cent_dep.js"></script>
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
	<div class="mini-splitter" style="width:100%;height:100%;">
			<!-- 分中心树 start -->
            <div size="20%" showCollapseButton="true">
            	<input type="hidden" value="${sessionScope.depId}" id="depId" />
                <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                    <span style="padding-left:5px;">数据库表：</span>
                </div>
                <div class="mini-fit" style=" border-top:0;">
                    <ul id="deptree" class="mini-tree" url="../train/table_colum!treejson.action" style="width:100%;height:100%;"
                        showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                    </ul>
                </div>
            </div>
            <!-- 分中心树  end -->
            <div showCollapseButton="false">
	            <div class="mini-toolbar" style="padding:0px;border-top:0;">
	       			 <table style="width:100%;">
	           			 <tr>
			                <td style="width:100%;">
                        <input name="pid" id="pid" class="mini-hidden" value="" disable="disable"  width="100%" />
			                    <a class="mini-button" iconCls="icon-add" onclick="add_c()">增加字段</a>
			                    <a class="mini-button" iconCls="icon-add" onclick="add_p()">增加表</a>
			                    <a class="mini-button" iconCls="icon-edit" onclick="edit_c()">编辑字段</a>
			                    <a class="mini-button" iconCls="icon-edit" onclick="edit_p()">编辑表</a>
			                    <a class="mini-button" iconCls="icon-remove" onclick="remove_c()">删除字段</a>
			                    <a class="mini-button" iconCls="icon-remove" onclick="remove_p()">删除表</a>
			                    <a class="mini-button" iconCls="icon-remove" onclick="exportinfo()">测试导出</a>
			                    <a class="mini-button" iconCls="icon-remove" onclick="showset()">测试设置动态表头</a>
			                   
			                </td>
	            		 </tr>
	        		</table>
	    		</div> 
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" allowResize="false" url="../train/table_colum!listjson.action" idField="id" multiSelect="true">
		                <div property="columns"> 	
	                        <div type="checkcolumn">选择</div>
	                        <div type="indexcolumn" headerAlign="center">序列</div> 
			                    <s:iterator value="showlist" var="showlist">
	                        <div field="<s:property value="columname" />" id="<s:property value="columname"/>" name = "<s:property value="columname"/>"width="100" headerAlign="center" align="left"><s:property value="chinaname" /></div>
			                    </s:iterator>
	                    </div>
       		 	</div>
            </div>
	</div> 
</div> 
    <script type="text/javascript">
    mini.parse(); 
 
    var grid = mini.get("grid");
    grid.hideColumn("id");
    grid.hideColumn("pid");
    grid.hideColumn("cid");
    grid.hideColumn("did");
    grid.load();
    var tree = mini.get("deptree");
      tree.on("nodeselect", function (e) {
    var node = tree.getSelectedNode();
    if (node) {
		grid.setUrl("../train/table_colum!listjson.action");
		mini.get("pid").setValue(node.id);
        grid.load({id:node.id}); 
    }
	});
      function add_p() {
    			 mini.open({
    	                url : "../train/table_colum!add.action?pid=0",
    	                title : "添加表",
    	                width : 670,
    	                height : 340,
    	                /* allowResize : false, */
    	                onload : function() {
    	                },
    	                ondestroy : function(action) {
    	                    grid.reload();
    	                }
    	            }); 
       	 }  
        function add_c() { 
            var node = tree.getSelectedNode();
           if(typeof(node)=="undefined" ){
        	   alert("请先选择表，然后再添加字段");
           }else{
			 mini.open({
	                url : "../train/table_colum!add.action?pid="+node.id,
	                title : "添加字段",
	                width : 670,
	                height : 340,
	                /* allowResize : false, */
	                onload : function() {
	                },
	                ondestroy : function(action) {
	                    grid.reload();
	                }
	            });
			 } 
  	 	}   
        function edit_p() { 
            var node = tree.getSelectedNode();
            if(typeof(node)=="undefined" ){
         	   alert("请先选择表，然后才能编辑该表");
            } 
    			 mini.open({
    	                url : "../train/table_colum!add.action?pid=0&id="+node.id,
    	                title : "添加需求",
    	                width :670,
    	                height : 340,
    	                /* allowResize : false, */
    	                onload : function() {
    	                },
    	                ondestroy : function(action) {
    	                    grid.reload();
    	                }
    	            }); 
       	 	 
        }
        function edit_c() { 
            var node = tree.getSelectedNode();
        	var rows = grid.getSelecteds();
            if (rows.length == 1) {
    			 mini.open({
    	                url : "../train/table_colum!add.action?id="+rows[0].id+"&pid="+node.id,
    	                title : "添加字段",
    	                width : 670,
    	                height : 340,
    	                /* allowResize : false, */
    	                onload : function() {
    	                },
    	                ondestroy : function(action) {
    	                    grid.reload();
    	                }
    	            }); 
       	 	}else{
                mini.alert("请选中一条记录！");
       	 	}
  	 	}  
        function showtest(){
        	window.location.href="../train/table_colum!list_test.action";
        }
        function remove_c(){
        	var rows = grid.getSelecteds();
            var node = tree.getSelectedNode();
            if (rows.length == 1) { 
        	$.ajax({
		          url: "../train/table_colum!remove.action",
		          type:'post',
		          data:{id:rows[0].id},
		          success: function (text) {
		               window.parent.unmask();
		              if (text == "success") { 
  	                    grid.reload();
		                  mini.showMessageBox({
		                      title: "提醒",
		                      width: 250,
		                      iconCls: "mini-messagebox-warning",
		                      buttons: ["ok"],
		                      message: "保存成功！",
		                      callback: function (action) {
		                          CloseWindow("ok");
		                      }
		                  });
		              } else {
		                  if(text.length>0){ 
		  	                    grid.reload();
		                      mini.alert(text);
		                  }
		              }
		          } 
		      });
        	}else{
        		mini.alert("请选中一条数据后进行处理");
        	}
        }
        function remove_p(){
            var node = tree.getSelectedNode();
            if(typeof(node)=="undefined" ){
         	   alert("请先选择表，然后再添加字段");
            }else{
            	
        	$.ajax({
		          url: "../train/table_colum!remove.action",
		          type:'post',
		          data:{   id: node.id,pid:node.id },
		          success: function (text) {
		               window.parent.unmask();
		              if (text == "success") {
		                  mini.showMessageBox({
		                      title: "提醒",
		                      width: 250,
		                      iconCls: "mini-messagebox-warning",
		                      buttons: ["ok"],
		                      message: "保存成功！",
		                      callback: function (action) {
		                          CloseWindow("ok");
		                      }
		                  });
		                 
		              } else {
		                  if(text.length>0){
								location.reload();
		                      mini.alert(text);
		                  }
		              }
		          } 
		      });
        	}
        }
        
        function exportinfo(){
	window.location.href="../train/table_colum!exporttest.action";
        	
        }
        
        function showset(){ 
			 mini.open({
	                url : "../train/table_colum!setcolum.action?tablename=动态表头",
	                title : "设置字段展示",
	                width :770,
	                height : 600,
	                /* allowResize : false, */
	                onload : function() {
	                },
	                ondestroy : function(action) {
	                    grid.reload();
	                }
	            }); 
        }
        function onKeyEnter(e) {
            search();
        }
    </script>
</body>
</html>