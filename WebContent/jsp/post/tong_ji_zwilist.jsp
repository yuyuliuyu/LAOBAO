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
<title>职位列表</title>
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

	    <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" 
						url="../post/position!positionList.action?id=${pid}" idField="id" multiSelect="true" 
						 allowAlternating="true" pageSize="20" >		
             
	         <div property="columns">
	                <div type="indexcolumn" headerAlign="center">序列</div>
	                <div field="positionCode" id ="positionCode" name = "positionCode"width="100" headerAlign="center" align="left">编码</div>
	                <div field="positionName" id ="positionName" name = "positionName"width="100" headerAlign="center" align="left">名称</div>
	                <div field="compName" id ="compName" name = "compName"width="100" headerAlign="center" align="left">公司</div>
	                <div field="deptName" id ="deptName" name = "deptName"width="100" headerAlign="center" align="left">部门</div>
	                <div field="pName" id ="pName" name = "pName"width="100" headerAlign="center" align="left">职务</div>
	                <div field="dutyName" id ="dutyName" name = "dutyName"width="100" headerAlign="center" align="left">职务族</div>
	                <div field="applyDate" id ="applyDate" name = "applyDate"width="100" headerAlign="center" align="left">生效日期</div>
	               
	            </div>
	 	</div>
            
    <script type="text/javascript">
        mini.parse();
        
        var grid = mini.get("grid1");
        grid.hideColumn("id");
        grid.load();
        
        function onKeyEnter(e) {
            search();
        }
    	

    </script>
</body>
</html>