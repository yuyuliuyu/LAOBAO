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

 		<div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" 
		 	url="../post/post!personList.action?id=${pid}" idField="id" multiSelect="true" 
          	 allowAlternating="true" pageSize="20" >
              <div property="columns">
                     <div type="checkcolumn">选择</div>
                     <div type="indexcolumn" headerAlign="center">序列</div>
                     <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
                     <div field="jobNumber" id ="jobNumber" name = "jobNumber"width="100" headerAlign="center" align="left">职工编号</div>
	                 <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">姓名</div>
                     <div field="filmName" id ="filmName" name = "filmName" width="100" headerAlign="center" align="left">公司</div>
                     <div field="departName" id ="departName" name = "departName" width="100" headerAlign="center" align="left">部门</div>
	                 <div field="jobLevel" id ="jobLevel" name = "jobLevel" width="100" headerAlign="center" align="center" >职务</div>
                     <div field="inPortTime" id ="inPortTime" name = "inPortTime" width="100" headerAlign="center" align="left">入职日期</div>
                     <div field="dutyName" id ="dutyName" name = "dutyName" width="100" headerAlign="center" align="left">职务族</div>
                     <div type="comboboxcolumn" field="onJob" width="100" headerAlign="center" align="center">员工状态
                            <input property="editor" class="mini-combobox" data="[{id:0, text:'实习期'},{id:1, text:'试岗期'},{id:2, text:'正式工'},{id:3, text:'离职'},{id:4, text:'退休'},{id:5, text:'返聘'}]"/>
                     </div>
         	 </div>
  		</div>
            
    <script type="text/javascript">
        mini.parse();
        
        var grid = mini.get("grid1");
        grid.hideColumn("id");
        grid.load();
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