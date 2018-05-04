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
<title>驻外列表</title>
<style type="text/css">
</style>
<!--引入皮肤样式-->
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css"/>
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
                <td style="width:200px;">出行国家：
                    <input id="goalPiace" name="goalPiace" class="mini-textbox" emptyText="请输入员国家" onenter="onKeyEnter"/>
                </td>
                
                <td style="white-space:nowrap;">
                	<a class="mini-button" iconCls="icon-search" onclick="search('jobNumber,name,goalPiace','jobNumber,name,goalPiace')" onenter="onKeyEnter">查询</a>
               	</td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
	   	<table style="width:100%;">
		    <tr>
			    <td style="width:100%;">
			    	<tags:hasPerm value="../empdata/stay_out!add.action">
				    <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
				    </tags:hasPerm>
				    <tags:hasPerm value="../empdata/stay_out!edit.action">
				    <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
				    </tags:hasPerm>
				    <tags:hasPerm value="../empdata/stay_out!remove.action">
				    <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
				    </tags:hasPerm>
				    <tags:hasPerm value="../empdata/stay_out!overAbroad.action">
				    <a class="mini-button" iconCls="icon-remove" onclick="overAbroad()">结束信息</a>
				    </tags:hasPerm>
			    </td>
		    </tr>
	    </table>
 	</div>
	<input type="hidden"  name="flag" id="flag" value="${flag}" />
   	<div class="mini-fit">
   		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
         	 url="../personnel/stay_out!getListData.action?personId=${personId}" idField="id" multiSelect="true" 
        	 allowAlternating="true" pageSize="100" >
	         <div property="columns">
		         <div type="checkcolumn">选择</div>
		         <div type="indexcolumn" width="60" headerAlign="center">序列</div>
		                       
		         <div field="id" id="id" width="0" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
		         <div field="jobNumber" id ="jobNumber" name = "jobNumber" width="100" headerAlign="center" align="left">职工号</div>
		         <div field="pername" id ="pername" name = "pername"width="100" headerAlign="center" align="left">职工姓名</div>
		         <div field="filmName" id ="filmName" name = "filmName"width="200" headerAlign="center" align="left">单位</div>
		         <div field="departName" id ="departName" name = "departName"width="100" headerAlign="center" align="left">部门</div>
		         <div field="post" id ="post" name = "post"width="100" headerAlign="center" align="left">标准岗位</div>
		         <div field="specificPost" id ="specificPost" name = "specificPost"width="100" headerAlign="center" align="left">具体岗位</div>
		                    
		         <div field="theFirm"width="100" headerAlign="center" align="left">外派公司</div>
		         <div field="theDep"width="100" headerAlign="center" align="left">部门</div>
		         <div field="thePost"width="100" headerAlign="center" align="left">岗位</div>
		         <div field="theDuty"width="100" headerAlign="center" align="left">职务</div>
		         <div field="workContent"width="100" headerAlign="center" align="left">工作内容简介</div>
		         <div field="begindate"width="100" headerAlign="center" align="left">开始时间</div>
		         <div field="planenddate"width="100" headerAlign="center" align="left">计划结束时间</div>
		         <div field="enddate"width="100" headerAlign="center" align="left">实际结束时间</div>
		         <div type="comboboxcolumn" field="status" width="100" headerAlign="center" align="center">状态
                 	<input property="editor" class="mini-combobox" data="[{id:1, text:'驻外中'},{id:0, text:'驻外结束'}]"/>
                 </div>
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
      //***************添加方法**********************//
        function add() {
        		
    			 mini.open({
    	                url : "../personnel/stay_out!add.action?personId=${personId}",
    	                title : "添加信息",
    	                width : 770,
    	                height : 540,
    	                /* allowResize : false, */
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
  	                url : "../personnel/stay_out!add.action?personId=${personId}&id="+row.id,
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
  					url : "../personnel/stay_out!remove.action?ids=" + sid,
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
  	 function overAbroad() {
 		
		 mini.open({
                url : "../personnel/stay_out!finish.action",
                title : "添加信息",
                width : 770,
                height : 540,
                /* allowResize : false, */
                onload : function() {
                },
                ondestroy : function(action) {
                    grid.reload();
                }
            });
   
			 }	
  	function onKeyEnter(e) {
        search('jobNumber,name','jobNumber,name');
    }
      
    </script>
</body>
</html>