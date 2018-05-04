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
<title>工伤列表</title>
<style type="text/css">
</style>
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
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
	
		<div class="mini-toolbar" style="padding:0px;border-top:0;">
			<table style="width:100%;">
				<tr>
					<td style="width:100%;">
						<tags:hasPerm value="../empdata/injury!add.action">
						<a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
						</tags:hasPerm>
						<tags:hasPerm value="../empdata/injury!edit.action">
	                    <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
	                    </tags:hasPerm>
	                    <tags:hasPerm value="../empdata/injury!remove.action">
	                    <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
	                    </tags:hasPerm>
	                    <!-- <a class="mini-button" iconCls="icon-upload" onclick="upload()">上传证件</a> -->
	                </td>
           		 </tr>
       		</table>
		</div>
		<div class="mini-fit">
		<input type="hidden"  name="flag" id="flag" value="${flag}" />
		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
			url="../empdata/injury!getListData.action?personId=${personId}" idField="id" multiSelect="true" 
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
	                        
                <!-- <div field="onPost" id ="onPost" name = "onPost" width="100" headerAlign="center" align="center" >是否在岗</div> -->
                <div type="comboboxcolumn" field="onPost" width="100" headerAlign="center" align="center">是否在岗
                	<input property="editor" class="mini-combobox" data="[{id:0, text:'否'},{id:1, text:'是'}]"/>
                </div>
                <div field="injuryUnit" id ="injuryUnit" name = "injuryUnit" width="100" headerAlign="center" align="center" >发生工伤时所属单位</div>
               	<div field="injuryDepartment" id ="injuryDepartment" name = "injuryDepartment" width="100" headerAlign="center" align="center">所属部门</div>
              	<div field="injuryPost" id ="injuryPost" name = "injuryPost" width="100" headerAlign="center" align="center" >所属岗位</div>
                <div field="injuryDate" id ="injuryDate" name = "injuryDate" width="100" headerAlign="center" align="center" >工伤日期</div>
                <div field="injuryProcess" id ="injuryProcess" name = "injuryProcess" width="100" headerAlign="center" align="center">工伤事故经过</div>
                <div field="injuryPart" id ="injuryPart" name = "injuryPart" width="100" headerAlign="center" align="center">工伤部位</div>
                <div field="injuryCause" id ="injuryCause" name = "injuryCause" width="100" headerAlign="center" align="center">工伤原因</div>
                <div type="comboboxcolumn" field="injuryType" width="100" headerAlign="center" align="center">工伤种类
                	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955c42841a015c438e0457000e"
                    	textField="dicname" valueField="dicvalue"/>
                </div>
                <div type="comboboxcolumn" field="manageStatus" width="100" headerAlign="center" align="center">处理状态
                	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955c42841a015c438891ed000a"
                    	textField="dicname" valueField="dicvalue"/>
                </div>
                <div field="healBegindate" id ="healBegindate" name = "healBegindate" width="100" headerAlign="center" align="center">医疗开始日期</div>
                <div field="healEnddate" id ="healEnddate" name = "healEnddate" width="100" headerAlign="center" align="center">医疗结束日期</div>
                <div field="healCosts" id ="healCosts" name = "healCosts" width="100" headerAlign="center" align="center">医疗费用</div>
                <div field="injurySubsidy" id ="injurySubsidy" name = "injurySubsidy" width="100" headerAlign="center" align="center">伤残补助</div>
                <div field="authDate" id ="authDate" name = "authDate" width="100" headerAlign="center" align="center">工伤鉴定日期</div>
                <div field="healSubsidy" id ="healSubsidy" name = "healSubsidy" width="100" headerAlign="center" align="center">医疗补助金</div>
                <div field="healSubsidySocial" id ="healSubsidySocial" name = "healSubsidySocial" width="100" headerAlign="center" align="center">医疗补助金社保部分</div>
                <div field="healSubsidyFirm" id ="healSubsidyFirm" name = "healSubsidyFirm" width="100" headerAlign="center" align="center">医疗补助金公司部分</div>
                <div field="employSubsidy" id ="employSubsidy" name = "employSubsidy" width="100" headerAlign="center" align="center">就业补助金</div>
                <div field="employSubsidySocial" id ="employSubsidySocial" name = "employSubsidySocial" width="100" headerAlign="center" align="center">就业补助金社保部分</div>
                <div field="employSubsidyFilm" id ="employSubsidyFilm" name = "employSubsidyFilm" width="100" headerAlign="center" align="center">就业补助金公司部分</div>
                <div field="remark" id ="remark" name = "remark" width="100" headerAlign="center" align="center">备注</div>
				<div field="cuteProcess" id ="cuteProcess" name = "cuteProcess" width="100" headerAlign="center" align="center">处理过程</div>
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
    	   url : "../empdata/injury!add.action?personId=${personId}",
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
               	 url : "../empdata/injury!add.action?personId=${personId}&id="+row.id,
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
					url : "../empdata/injury!remove.action?ids=" + sid,
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
    	

    </script>
</body>
</html>