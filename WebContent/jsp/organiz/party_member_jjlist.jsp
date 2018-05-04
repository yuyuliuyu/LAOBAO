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
<title>历史沿革</title>
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
			                    <a class="mini-button" iconCls="icon-remove" onclick="remove()">取消资格</a> 
			                    <a class="mini-button" iconCls="icon-user" onclick="personinfo()">选为发展对象</a>
			                    <a class="mini-button" iconCls="icon-upgrade" onclick="nbzj()">内部转接</a>
			                    <a class="mini-button" iconCls="icon-upgrade" onclick="wbdl()">外部调离</a> 
			                </td>
	            		 </tr>
	        		</table>
	    		</div>
	    		<div id="grid1" class="mini-datagrid" style="width:100%;height:94%;"showTreeIcon="true"
		             treeColumn="name" idField="did" parentField="pid" resultAsTree="false" allowResize="false" expandOnLoad="true">
		                <div property="columns">
	                        <div type="checkcolumn">选择</div>
	                        <div type="indexcolumn" headerAlign="center">序列</div> 
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">工号</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">姓名</div>
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">所在公司</div> 
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">职位</div> 
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">申请入党日期</div> 
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">列为积极分子日期</div> 
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">介绍人</div>  
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">培养人</div> 
	                        <div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">操作</div> 
	                    </div>
       		 	</div>
	</div>
    
    <script type="text/javascript">
    mini.parse(); 
    var grid = mini.get("datagrid1");
    grid.load();
        function add() {
        		
    			 mini.open({
    	                url : "../organiz/party_member!jjadd.action",
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
      

        
    	function personinfo(){
    		window.location.href="../organiz/party_member!list.action";
    	} 

        function nbzj(){
 			 mini.open({
 	                url : "../organiz/party_relation!zjadd.action",
 	                title : "内部转接",
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
        function wbdl(){
 			 mini.open({
 	                url : "../organiz/party_relation!dladd.action",
 	                title : "外部调离",
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
    </script>
</body>
</html>