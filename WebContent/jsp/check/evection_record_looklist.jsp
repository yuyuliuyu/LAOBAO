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
	<title>出差页面</title>
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
	<div class="mini-fit"> 
		<div class="mini-toolbar" style="padding:0px;border-top:0;">
			<table style="width:100%;">
				<tr>
					<td style="width:100%;"> 
						<a class="mini-button" iconCls="icon-edit" onclick="view()">查看</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="mini-fit">
			<div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
	            url="../check/evection_record!getListData.action" allowAlternating="true" pageSize="20"
	            idField="id" multiSelect="true">
	            <div property="columns">
	                <div type="checkcolumn" width="50"></div>
	                <div field="id" name="id" id="id" width="100" headerAlign="center" align="center">id</div>
	                
		        	<div field="jobNumber" id ="jobNumber" name = "jobNumber" width="100" headerAlign="center" align="left">职工号</div>
		         	<div field="pername" id ="pername" name = "pername"width="100" headerAlign="center" align="left">职工姓名</div>
		       	    <div field="filmName" id ="filmName" name = "filmName"width="200" headerAlign="center" align="left">单位</div>
		      	    <div field="departName" id ="departName" name = "departName"width="100" headerAlign="center" align="left">部门</div>
		      	    <div field="post" id ="post" name = "post"width="100" headerAlign="center" align="left">标准岗位</div>
		      	    <div field="specificPost" id ="specificPost" name = "specificPost"width="100" headerAlign="center" align="left">具体岗位</div>
		         
	                <div field="evectionType" name="evectionType" id="evectionType" width="120" headerAlign="center" align="center" >出差类型</div>
	                <div field="startDate" name="startDate" id="startDate" width="160" headerAlign="center" align="center" >出差开始时间</div>
	                <div field="endDate" name="endDate" id="endDate" width="160" headerAlign="center" align="center" >出差结束时间</div>
	                <div field="evectionDay" name="evectionDay" id="evectionDay" width="80" headerAlign="center" align="center" >出差天数</div> 
	                <div field="address" name="address" id="address" width="80" headerAlign="center" align="center" >出差地址</div> 
	                <div field="vehicle" name="vehicle" id="vehicle" width="80" headerAlign="center" align="center" >出差工具</div> 
	            </div>
	        </div> 
		</div>
	</div> 
	<script type="text/javascript">
		mini.parse();
		var grid = mini.get("datagrid1");
		grid.hideColumn("id");
		grid.hideColumn("processId");
		grid.load();
		
        //查看
        function view(id){
        	mini.open({
					url : "../check/evection_record!view.action?id="+id,
					title : "查看出差信息",
					width : 670,
					height :400,
					/* allowResize : false, */
					onload : function() {
					},
					ondestroy : function(action) {
						//刷新页面
						window.location.reload();
					}
				});
        }
       
	</script>
</body>
</html>