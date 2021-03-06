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
    <title>证照管理</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link rel="stylesheet" href="../template/system/css/base.css" type="text/css"></link>
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
    .myrow {
        background: #fceee2;
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
                
                
                <td style="white-space:nowrap;">
                	<a class="mini-button" iconCls="icon-search" onclick="search('jobNumber,name','jobNumber,name')" onenter="onKeyEnter">查询</a>
               	</td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="border-left:0;padding:0px;">
		<table style="width:100%;">
			<tr>
				<td>
					<tags:hasPerm value="../empdata/license!add.action">
					<a class="mini-button" iconCls="icon-add" onclick="add()">添加</a>
					</tags:hasPerm>
					<tags:hasPerm value="../empdata/license!edit.action">
					<a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
					</tags:hasPerm>
					<tags:hasPerm value="../empdata/license!remove.action">
			        <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a> 
			        </tags:hasPerm>
			        <tags:hasPerm value="../empdata/license!look.action">
			        <a class="mini-button" iconCls="icon-search" onclick="look()">查看文件</a> 
			        </tags:hasPerm>
			        <tags:hasPerm value="../empdata/license!filedownload.action">
			        <a class="mini-button" iconCls="icon-download" onclick="filedownload()">证照下载</a>
			        </tags:hasPerm>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden"  name="flag" id="flag" value="${flag}" />
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
             url="../empdata/license!getListData.action?personId=${personId}" idField="id" multiSelect="true" 
             allowAlternating="true" pageSize="20" >
            <div property="columns">
            	<div type="checkcolumn"></div>
                <div type="indexcolumn" headerAlign="center">序列</div>
                
                <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
                <div field="url" id="url" width="80" name="url" headerAlign="center" align="center" class="mini-hidden">url</div>
                <div field="jobNumber" id ="jobNumber" name = "jobNumber" width="100" headerAlign="center" align="left">职工号</div>
                <div field="pername" id ="pername" name = "pername"width="100" headerAlign="center" align="left">职工姓名</div>
		        <div field="filmName" id ="filmName" name = "filmName"width="100" headerAlign="center" align="left">单位</div>
		        <div field="departName" id ="departName" name = "departName"width="100" headerAlign="center" align="left">部门</div>
		        <div field="post" id ="post" name = "post"width="100" headerAlign="center" align="left">标准岗位</div>
		        <div field="specificPost" id ="specificPost" name = "specificPost"width="100" headerAlign="center" align="left">具体岗位</div>
                
                <div type="comboboxcolumn" field="licenseType" width="100" headerAlign="center" align="center">证照类型
			   		<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935b88e560015b895aa2f40016" textField="dicname" valueField="dicvalue"/>
			   	</div>
                <div type="comboboxcolumn" field="theClass" width="100" headerAlign="center" align="center">分类
                	<input property="editor" class="mini-combobox" data="[{id:0, text:'技工信息'},{id:1, text:'职称聘任'},{id:2, text:'员工技能'},{id:3, text:'军官证'},{id:4, text:'工伤'}]"/>
                </div>
                <div field="licenseNum" width="100" headerAlign="center" align="left">证照编号</div>
                <div type="comboboxcolumn" field="licenseStatus" width="100" headerAlign="center" align="center">证件状态
			   		<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935b88e560015b895b010c0017" textField="dicname" valueField="dicvalue"/>
			   	</div>
                <div field="begindate"  width="100" headerAlign="center" align="center" >生效起始日期</div>
                <div field="enddate"  width="100" headerAlign="center" align="center" >生效结束日期</div>
                <div field="validity"  width="100" headerAlign="center" align="left" >有效期</div>
                <div field="loseDate"  width="100" headerAlign="center" align="left" >距离失效天数</div>
                <div field="office"  width="100" headerAlign="center" align="left" >发证机关</div>
                <div type="comboboxcolumn" field="isRecheck" width="100" headerAlign="center" align="center">是否复检
			    	<input property="editor" class="mini-combobox" data="[{id:0, text:'否'},{id:1, text:'是'}]"/>
			    </div>
                
                <div field="lastRecheckday"  width="100" headerAlign="center" align="left" >上次复检日</div>
                <div field="nextRecheckday"  width="100" headerAlign="center" align="left" >下次复检日</div>
                <div field="recheckDays"  width="100" headerAlign="center" align="left" >距离复检天数</div>
                <div type="comboboxcolumn" field="isBackpay" width="100" headerAlign="center" align="center">是否回缴
			    	<input property="editor" class="mini-combobox" data="[{id:0, text:'否'},{id:1, text:'是'}]"/>
			    </div>
                <div field="backpayDate"  width="100" headerAlign="center" align="left" >回缴日期</div>
                <div field="tackOffice"  width="100" headerAlign="center" align="left" >保管单位</div>
                <div field="tackName"  width="100" headerAlign="center" align="left" >保管人</div>
                <div field="tackDate"  width="100" headerAlign="center" align="left" >保管日期</div>
                <div field="deliverName"  width="100" headerAlign="center" align="left" >转交人</div>
                <div field="deliverDate"  width="100" headerAlign="center" align="left" >转交日期</div>
                <div field="remark"  width="100" headerAlign="center" align="left" >备注</div>
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
    grid.hideColumn("url");
    grid.load();
    function add() {
    	mini.open({
    	   url : "../empdata/license!add.action?personId=${personId}",
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
               	 url : "../empdata/license!add.action?personId=${personId}&id="+row.id,
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
					url : "../empdata/license!remove.action?ids=" + sid,
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
		var row = grid.getSelected();
	   	 if (row) {
	    		mini.open({
	               	 url : "../empdata/license!look.action?id="+row.id,
	                 title : "查看证照",
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
	//下载
    function filedownload() {
    	var row = grid.getSelected();
	   	 if (row) {
	   		window.location.href="../empdata/license!download.action?id="+row.id; 
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