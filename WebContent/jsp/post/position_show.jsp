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
<title>岗位选择列表</title>
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
	<div class="mini-fit">
   		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
				url="../post/position!listData.action?id=${id}" idField="id" 
				 allowAlternating="true" pageSize="10" >
				 
				<div property="columns">
                       <div type="checkcolumn">选择</div>
                       <div type="indexcolumn" headerAlign="center">序列</div>
                       <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
                       <div field="dutyId" id="dutyId" width="80" name="dutyId" headerAlign="center" align="center" class="mini-hidden">职务族id</div>
                       <div field="positionCode" id ="positionCode" name = "positionCode"width="100" headerAlign="center" align="left">编码</div>
                       <div field="positionName" id ="positionName" name = "positionName"width="100" headerAlign="center" align="left">名称</div>
                       <div field="dutyName" id ="dutyName" name = "dutyName"width="100" headerAlign="center" align="left">职务族</div>
                       <div field="positionGrad" id ="positionGrad" name = "positionGrad"width="100" headerAlign="center" align="left">职等</div>
                       <div field="applyDate" id ="applyDate" name = "applyDate"width="100" headerAlign="center" align="left">生效日期</div>
                       <div type="comboboxcolumn" field="state" width="100" headerAlign="center" align="center">状态
						<input property="editor" class="mini-combobox" data="[{id:1, text:'有效'},{id:0, text:'无效'}]"/>
					</div>
                   </div>
		</div>
	</div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" 
         borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="mini-button" style="width:60px;" onclick="onOk()" iconCls="icon-save">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" style="width:60px;" onclick="onCancel()" iconCls="icon-close">取消</a>
    </div>
	

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.hideColumn("id");
        grid.hideColumn("dutyId");
        grid.load();
        
        function onKeyEnter(e) {
            search();
        }
        function onOk() {
            
            CloseWindow("ok");
        }
        function GetData() {
            var row = grid.getSelected();
            return row;
        }
        function onCancel() {
            CloseWindow("cancel");
        }
        function CloseWindow(action) {
            if (window.CloseOwnerWindow) 
            return window.CloseOwnerWindow(action);
            else window.close();
        }
    </script>
</body>
</html>