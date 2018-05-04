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
<title>招聘计划列表</title>
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
	<div class="mini-fit">
	            <div class="mini-toolbar" style="padding:0px;border-top:0;">
	       			 <table style="width:100%;">
	           			 <tr>
			                <td style="width:100%;">
			                    <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
			                    <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
			                    <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a> 
			                    <a class="mini-button" iconCls="icon-user" onclick="personinfo()">审批</a>
			                    <a class="mini-button" iconCls="icon-user" onclick="sign()">查看面试简历</a>
			                </td>
	            		 </tr>
	        		</table>
	    		</div>
	    		<div id="grid1" class="mini-datagrid" style="width:100%;height:94%;"showTreeIcon="true"
		             treeColumn="name" idField="did" parentField="pid" resultAsTree="false" allowResize="false" expandOnLoad="true">
		                <div property="columns">
	                        <div type="checkcolumn">选择</div>
	                        <div type="indexcolumn" field="indexcolumn" id="indexcolumn" name="indexcolumn" headerAlign="center">序列</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">计划编号</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">计划名称</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">招聘渠道</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">预算经费</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">开课时间</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">结束时间</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">招聘人数</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">招聘说明</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">招聘备注</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">审批人</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">操作</div> 
	                    </div>
       		 	</div>
	</div>
    
    <script type="text/javascript">
    mini.parse(); 
    var grid = mini.get("grid1");
    grid.hideColumn("indexcolumn");
    grid.load();
        function add() { 
    			 mini.open({
    	                url : "../recruit/recruit_plan!add.action",
    	                title : "添加计划",
    	                width : 670,
    	                height : 540,
    	                /* allowResize : false, */
    	                onload : function() {
    	                },
    	                ondestroy : function(action) {
    	                    grid.reload();
    	                }
    	            }); 
       			 }
    	function personinfo(){
			 mini.open({
	                url : "../recruit/recruit_plan!advance.action",
	                title : "审批意见",
	                width : 570,
	                height : 340,
	                /* allowResize : false, */
	                onload : function() {
	                },
	                ondestroy : function(action) {
	                    grid.reload();
	                }
	            }); 
    	}
    	function sign(){
    		window.location.href="../recruit/recruit_resume!list.action";
    	    //window.location.href="../organiz/working_person!list.action";
    	}
    </script>
</body>
</html>