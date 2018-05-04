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
<title>老动争议</title>
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
	<div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
        <table style="width:100%;">
            <tr>
                
                <td style="width:200px;">名称：
                    <input id="stadate" name="stadate" class="mini-textbox" emptyText="请输入员工名称" onenter="onKeyEnter"/>
                </td>
                <td style="width:200px;">争议类型：
                    <input id="enddate" name="enddate" class="mini-textbox" emptyText="请输入争议类型" onenter="onKeyEnter"/>
                </td>
                <td style="width:200px;">人员：
                    <input id="enddate" name="enddate" class="mini-textbox" emptyText="请输人员" onenter="onKeyEnter"/>
                </td>
                <td style="white-space:nowrap;">
                    <a class="mini-button" iconCls="icon-search" onclick="search()" onenter="onKeyEnter">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
       <div class="mini-toolbar" style="padding:0px;border-top:0;">
      	 	<table style="width:100%;">
          		<tr>
	                <td style="width:100%;">
	                    <a class="mini-button" iconCls="icon-add" onclick="add()">添加争议信息</a>
	                    <a class="mini-button" iconCls="icon-edit" onclick="edit()">添加处理信息</a>
	                </td>
           		 </tr>
       		</table>
   		</div>
 		<div id="grid1" class="mini-treegrid" style="width:100%;height:94%;"
            showTreeIcon="true"
           treeColumn="name" idField="did" parentField="pid"
           resultAsTree="false" allowResize="false" expandOnLoad="true">
              <div property="columns">
                     <div type="checkcolumn">选择</div>
                     <div type="indexcolumn" headerAlign="center">序列</div>
                     <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
                     <div field="pid" id="pid" width="80" name="pid" headerAlign="center" align="center" class="mini-hidden">部门id</div>
                     <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">争议类型</div>
                     <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">争议编号</div>
                     <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">争议实体</div>
                     <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">争议原因</div>
                     <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">争议发起时间</div>
                     <div field="srm" id ="srm" name = "srm" width="100" headerAlign="center" align="center" >争议结束时间</div>
                 </div>
   		 	</div>
        </div>
	
    
    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid1");
        grid.hideColumn("id");
        grid.hideColumn("pid");
        //////////////////////////////////////////////
        function add() {
        	
        	mini.open({
                url : "../pact/pact!disputeadd.action",
                title : "添加信息",
                width : 770,
                height : 540,
                onload : function() {
                },
                ondestroy : function(action) {
                    grid.reload();
                }
            });
        }
        
        //////////////////////////////////////////////
        function edit() {
            /* var row = grid.getSelected();
            if (row) { */
                mini.open({
                    url : "../pact/pact!disposeadd.action",
                    title : "编辑部门",
                    width : 770,
                    height : 540,
                    /* allowResize : false, */
                    onload : function() {
                    },
                    ondestroy : function(action) {
                        grid.reload();
                    }
                });
            /* } else {
                mini.alert("请选中一条记录！");
            } */
        }

        ////////////////////////////////////////////////
        function remove() {
        	if(true){
        		/* var row = grid.getSelected(); */
    			 mini.open({
    	                /* url : "../personnel/practice!finish.action?id="+row.id, */
    	                url : "../personnel/practice!finish.action",
    	                title : "结束实习",
    	                width : 770,
    	                height : 540,
    	                onload : function() {
    	                },
    	                ondestroy : function(action) {
    	                    grid.reload();
    	                }
    	            });
        	}else{
        		mini.alert("*****！");
        	}
        }
        
        function search() {
            var key = mini.get("key").getValue();
            grid.load({
                key : key,searchBy:"name"
            });
        }
        
        function onKeyEnter(e) {
            search();
        }

    </script>
</body>
</html>