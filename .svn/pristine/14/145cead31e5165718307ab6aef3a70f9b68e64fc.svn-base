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
<title>电子档案列表</title>
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
	<div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:200px;">职工号：
                    <input class="mini-textbox" id="jobNumber" name="jobNumber" emptyText="请输入职工号" onenter="onKeyEnter" />
                </td>
                <td style="width:200px;">姓名：
                    <input id="name" name="name" class="mini-textbox" emptyText="请输入员工姓名" onenter="onKeyEnter"/>
                </td>
                <td style="width:200px;">档案名称：
                    <input id="reName" name="reName" class="mini-textbox" emptyText="请输入档案名称" onenter="onKeyEnter"/>
                </td>
                <td style="width:200px;">档案年份：
                    <input id="reYear" name="reYear" class="mini-textbox" emptyText="请输入档案年份" onenter="onKeyEnter"/>
                </td>
                <td style="white-space:nowrap;">
                	<a class="mini-button" iconCls="icon-search" onclick="search('jobNumber,name,reName,reYear','jobNumber,name,reName,reYear')" onenter="onKeyEnter">查询</a>
               	</td>
            </tr>
        </table>
    </div>
	<div class="mini-toolbar" style="padding:0px;border-top:0;">
	    <table style="width:100%;">
		    <tr>
			    <td style="width:100%;">
			    	<tags:hasPerm value="/empdata/record!add.action">
				    <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
				    </tags:hasPerm>
				    <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
				    <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
				    <!-- <a class="mini-button" iconCls="icon-upload" onclick="add()">上传文件</a> -->
				    <a class="mini-button" iconCls="icon-search" onclick="look()">查看文件</a>
				    <a class="mini-button" iconCls="icon-download" onclick="filedownload()">档案下载</a>
			    </td>
		    </tr>
	    </table>
	</div>
	<input type="hidden"  name="flag" id="flag" value="${flag}" />
	<div class="mini-fit">
		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
	         url="../empdata/record!getListData.action?personId=${personId}" idField="id" multiSelect="true" 
	         allowAlternating="true" pageSize="20" >
	             
			 <div property="columns">
			     <div type="checkcolumn">选择</div>
			     <div type="indexcolumn" headerAlign="center">序列</div>
			     <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
			     <div field="jobNumber" id ="jobNumber" name = "jobNumber" width="100" headerAlign="center" align="left">职工号</div>
				 <div field="pername" id ="pername" name = "pername"width="100" headerAlign="center" align="left">职工姓名</div>
				 <div field="filmName" id ="filmName" name = "filmName"width="100" headerAlign="center" align="left">单位</div>
				 <div field="departName" id ="departName" name = "departName"width="100" headerAlign="center" align="left">部门</div>
				 <div field="post" id ="post" name = "post"width="100" headerAlign="center" align="left">标准岗位</div>
				 <div field="specificPost" id ="specificPost" name = "specificPost"width="100" headerAlign="center" align="left">具体岗位</div>
			                        
			     <div type="comboboxcolumn" field="reType" width="100" headerAlign="center" align="center">档案类型
                   <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935ba3dd8a015ba3f117400003" textField="dicname" valueField="dicvalue"/>
               	 </div>
			     <div field="reName" id ="reName" name = "reName" width="100" headerAlign="center" align="center" >档案名称</div>
			     <div field="reFileNum" id ="reFileNum" name = "reFileNum" width="100" headerAlign="center" align="center" >档案文号</div>
			     <div field="reContent" id ="reContent" name = "reContent" width="200" headerAlign="center" align="center" >档案内容简介</div>
			     <div field="reYear" id ="reYear" name = "reYear" width="100" headerAlign="center" align="center" >档案年份</div>
			     <div field="reNum" id ="reNum" name = "reNum" width="100" headerAlign="center" align="center" >档案编号</div>
			     <div field="extendName" id ="extendName" name = "extendName" width="100" headerAlign="center" align="center" >扩展名</div>
			     <div field="createNum" id ="createNum" name = "createNum" width="100" headerAlign="center" align="center" >创建工号</div>
			     <div field="createName" id ="createName" name = "createName" width="100" headerAlign="center" align="center" >创建姓名</div>
			     <div field="createDate" id ="createDate" name = "createDate" width="100" headerAlign="center" align="center" >创建日期</div>
			     <div field="remark" id ="remark" name = "remark" width="100" headerAlign="center" align="center" >备注</div>
		     </div>
	    </div>
	</div>
    
    <script type="text/javascript">
    var flag=$("#flag").val();
   	mini.parse();
   	var grid=mini.get("grid");
  	if(flag=="1"){
   	 	grid.hideColumn("jobNumber");
   	 	grid.hideColumn("pername");
    	grid.hideColumn("filmName");
    	grid.hideColumn("departName");
    	grid.hideColumn("post");
    	grid.hideColumn("specificPost");
	}
    grid.hideColumn("id");
    grid.load();
    
    function add() {
    	mini.open({
    	   url : "../empdata/record!add.action?personId=${personId}",
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
  //***************编辑方法**********************//
	function edit() {
   	 var row = grid.getSelected();
   	 if (row) {
    		mini.open({
               	 url : "../empdata/record!add.action?personId=${personId}&id="+row.id,
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
	//删除
	function remove() {
		var rows = grid.getSelecteds();
		if (rows.length > 0) {
				if (confirm("确定删除选中记录？")) {
					var ids = [];
					for (var i = 0, l = rows.length; i < l; i++) {
						var r = rows[i];
						ids.push(r.id);
					}
					var sid = ids.join(',');
					$.ajax({
					url : "../empdata/record!remove.action?ids=" + sid,
					type : "post",
					success : function(text) {
						grid.reload();
						mini.alert("删除成功！");
					},
					error : function(text) {
						mini.alert("删除失败");
					}
				});
			}
		} else {
			mini.alert("请选中一条记录！");
		}
	}	
	
	function look() {
		var rows = grid.getSelected();
		if (rows) {  
			window.open(rows.realUrl);
		} else {
			mini.alert("请选中一条记录！");
		}
	} 
	
	//下载
    function filedownload() {
    	var row = grid.getSelected();
	   	 if (row) {
	   		window.location.href="../empdata/record!download.action?id="+row.id; 
	  		  } else {
	      		  mini.alert("请选中一条记录！");
	   		 }
    }
	
    //*****************************************//	
    function onKeyEnter(e) {
        search();
    }
    </script>
</body>
</html>