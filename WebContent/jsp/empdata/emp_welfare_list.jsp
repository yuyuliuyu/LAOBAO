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
<title>员工福利列表</title>
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
                <td style="width:200px;">福利名称：
                    <input id="welName" name="welName" class="mini-textbox" emptyText="请输入福利名称" onenter="onKeyEnter"/>
                </td>
                <td style="white-space:nowrap;">
                	<a class="mini-button" iconCls="icon-search" onclick="search('jobNumber,name,welName','jobNumber,name,welName')" onenter="onKeyEnter">查询</a>
               	</td>
            </tr>
        </table>
    </div>
	<div class="mini-toolbar" style="padding:0px;border-top:0;">
		<table style="width:100%;">
	    	<tr>
				<td style="width:100%;">
					<tags:hasPerm value="../empdata/emp_welfare!add.action">
			    	<a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
			    	</tags:hasPerm>
			    	<tags:hasPerm value="../empdata/emp_welfare!edit.action">
			        <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
			        </tags:hasPerm>
			        <tags:hasPerm value="../empdata/emp_welfare!remove.action">
			        <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
			        </tags:hasPerm>
			    </td>
	        </tr>
		</table>
	</div>
	<input type="hidden"  name="flag" id="flag" value="${flag}" />
	<div class="mini-fit">
		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
        	url="../empdata/emp_welfare!getListData.action?personId=${personId}" idField="id" multiSelect="true" 
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
                
                <div type="comboboxcolumn" field="welType" width="100" headerAlign="center" align="center">福利类别
                   <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935b840948015b85525d550059" textField="dicname" valueField="dicvalue"/>
                </div> 
            	<div field="welName" id ="welName" name = "welName" width="100" headerAlign="center" align="center" >福利名称</div>
	            <div field="welContent" id ="welContent" name = "welContent" width="100" headerAlign="center" align="center">福利内容</div>
	            <div field="begindate" id ="begindate" name = "begindate" width="100" headerAlign="center" align="center" >开始日期</div>
	            <div field="enddate" id ="enddate" name = "enddate" width="100" headerAlign="center" align="center">结束日期</div>
	            <div type="comboboxcolumn" field="isend" width="100" headerAlign="center" align="center">是否终止
			    	<input property="editor" class="mini-combobox" data="[{id:0, text:'否'},{id:1, text:'是'}]"/>
			    </div>
	            <div field="cost" id ="cost" name = "cost" width="100" headerAlign="center" align="center">费用</div>
	            <div field="unitApproved" id ="unitApproved" name = "unitApproved" width="100" headerAlign="center" align="center">批准单位</div>
	            <div field="approver" id ="approver" name = "approver" width="100" headerAlign="center" align="center">批准人</div>
	         	<div field="remark" id ="remark" name = "remark" width="100" headerAlign="center" align="center">备注</div>
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
    	   url : "../empdata/emp_welfare!add.action?personId=${personId}",
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
               	 url : "../empdata/emp_welfare!add.action?personId=${personId}&id="+row.id,
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
					url : "../empdata/emp_welfare!remove.action?ids=" + sid,
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
    	
	function onKeyEnter(e) {
        search();
    }

    </script>
</body>
</html>