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
<title>正式工列表</title>
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
            <div size="300" showCollapseButton="true">
				<!-- ckId -->
				<input type="hidden" value="${sessionScope.depId}" id="depId" />
				<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
					<span style="padding-left:5px;">组织机构：</span>
				</div>
				<div class="mini-fit" style=" border-top:0;">
					<ul id="deptree" class="mini-tree" url="../basis/branch!getTreeListByUser.action" style="width:100%;height:100%;"
						showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true" >
					</ul>
				</div>
			</div>
            <!-- 分中心树  end -->
            <div showCollapseButton="false">
            	<div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
       				 <table style="width:100%;">
          				  <tr>
          				  	<td style="width:200px;">职工号：
                   				 <input id="jobNumber" name="jobNumber" class="mini-textbox" emptyText="请输入员工职工号" onenter="onKeyEnter"/>
              			   </td>
            			    <td style="width:200px;">姓名：
                   				 <input id="name" name="name" class="mini-textbox" emptyText="请输入员工姓名" onenter="onKeyEnter"/>
              			   </td>
               			   
               			   <td style="white-space:nowrap;">
                 			   <a class="mini-button" iconCls="icon-search" onclick="search('jobNumber,name,post','jobNumber,name,post')" onenter="onKeyEnter">查询</a>
               			   </td>
           				 </tr>
       				 </table>
   			    </div>
	            <div class="mini-toolbar" style="padding:0px;border-top:0;">
	       			 <table style="width:100%;">
	           			 <tr>
			                <td style="width:100%;">
			                    <a class="mini-button" iconCls="icon-add" onclick="addperson()">增加</a>
			                    <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
			                    <a class="mini-button" iconCls="icon-search" onclick="look()">查看员工详细信息</a>
			                    <a class="mini-button" iconCls="icon-tip" onclick="nbdd()">内部调动</a>
			                    <a class="mini-button" iconCls="icon-tip" onclick="ligang()">办理离岗</a>
			                    <a class="mini-button" iconCls="icon-tip" onclick="lizhi()">办理离职</a>
			                    <a class="mini-button" iconCls="icon-tip" onclick="tuixiu()">办理退休</a>
			                </td>
	            		 </tr>
	        		</table>
	    		</div>
	    		<div class="mini-fit">
	    		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
             		url="../personnel/personnel!getZsgPersonData.action" idField="id" multiSelect="true" 
            		 allowAlternating="true" pageSize="100" >
            		 
		                <div property="columns">
	                    
	                        <div type="checkcolumn">选择</div>
	                        <div type="indexcolumn" headerAlign="center">序列</div>
	                        <div field="id" id="id" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
	                        <div field="jobNumber" id ="jobNumber" name = "jobNumber"width="100" headerAlign="center" align="left">职工编号</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">姓名</div>
	                        <div field="age" id ="age" name = "age"width="100" headerAlign="center" align="left">年龄</div>
	                        <div type="comboboxcolumn" field="sex" width="100" headerAlign="center" align="center">性别
                            	<input property="editor" class="mini-combobox" data="[{id:0, text:'女'},{id:1, text:'男'}]"/>
                        	</div>
                        	<div type="comboboxcolumn" field="isMarried" width="100" headerAlign="center" align="center">婚姻状态
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881a05b650042015b6639e3d1003b"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
                        	<div field="jobNumberJt" id ="jobNumberJt" name = "jobNumberJt"width="100" headerAlign="center" align="left">集团工号</div>
	                        <div field="filmName" width="100" headerAlign="center" align="left">公司</div>
	                        <div field="departName" width="100" headerAlign="center" align="left">部门</div>
	                        <div field="post" id ="post" name = "post" width="100" headerAlign="center" align="center" >岗位</div>
	                        <div type="comboboxcolumn" field="jobLevel" width="100" headerAlign="center" align="center">职务级别
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935ba8c785015ba8e043c80011"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
	                        <div type="comboboxcolumn" field="empType" width="100" headerAlign="center" align="center">员工类型
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935baceaa1015bad355ba8000a"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
                        	<div type="comboboxcolumn" field="onJob" width="100" headerAlign="center" align="center">员工状态
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935baceaa1015bae044344001a"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
                        	<div type="comboboxcolumn" field="onPost" width="100" headerAlign="center" align="center">在岗状态
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955bc84595015bc87aa73e0010"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
	                        
	                    </div>
       		 	</div>
       		 	</div>
            </div>
        </div>
	</div>

    <script type="text/javascript">
        mini.parse();
        var tree = mini.get("deptree");
        var grid = mini.get("grid");
        grid.hideColumn("id");
        grid.load();
        function onKeyEnter(e) {
        	search('jobNumber,name','jobNumber,name');
        }
        //////////////////////////////////////////////
        //人员管理基础数据添加
        function addperson() {
        	
        	var node=tree.getSelectedNode();
        	if(node){
        		var nodeId=node.id;
        		var branchId=node.branchId;
        		$.ajax({
                    url: "../personnel/change!findDep.action?departId="+node.id,
                    type: "post",
                    success: function (text) {
                        if(text=="error"){
                        	mini.alert("选中节点不是部门，请重新操作！");
                            return;
                        }else{
			        		mini.open({
			                    url : "../personnel/personnel!addmini.action?departId="+nodeId+"&branchId="+branchId,
			                    title : "新添加人员信息",
			                    width : 900,
			                    height : 600,
			                    onload : function() {
			                    },
			                    ondestroy : function(action) {
			                        grid.reload();
			                    }
			                });
                        }
                    }
        		});
        	}else{
        		mini.alert("请选择部门！");
        	}
        	
        }
        
        //////////////////////////////////////////////
        //人员管理基础数据修改
        function edit() {
            var row = grid.getSelected();
            if (row) {
            	mini.open({
                    url : "../personnel/personnel!edit.action?flag=0&personId="+row.id+"&jobNumber="+row.jobNumber,
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
        //////////////////////////////////////////////
        //员工查看页面
         function look() {
        	
        	var row = grid.getSelected();
         	if(row){
         		var tabs = window.parent.mini.get("mainTabs");
                 var tab = { title: "员工管理查看", showCloseButton: true };
                 tab.url = "../personnel/personnel!all.action?personId="+row.id;
                 tabs.addTab(tab);
                 tabs.activeTab(tab);
         	}else{
         		mini.alert("请选中一条记录！");
         	}

        } 
         
        ////////////////////////////////////////////////
        function lizhi() {
        	var row = grid.getSelected();
            if (row) {
                mini.open({
                    url : "../personnel/departure!addLz.action?personId="+row.id,
                    title : "离职页面",
                    width : 770,
                    height : 540,
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
       ////////////////////////////////////////////////////////
        function tuixiu() {
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url : "../personnel/departure!addTx.action?personId="+row.id,
                    title : "退休",
                    width : 770,
                    height : 540,
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
		////////////////////////////////////////////////////////
        function nbdd() {
        	var row = grid.getSelected();
            if (row) {
                mini.open({
                    url : "../personnel/change!add.action?personId="+row.id,
                    title : "内部调动",
                    width : 770,
                    height : 540,
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
        function ligang() {
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url : "../personnel/change!ligang.action?personId="+row.id,
                    title : "离岗",
                    width : 770,
                    height : 540,
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
    </script>
</body>
</html>