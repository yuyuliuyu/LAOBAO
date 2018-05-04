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
<title>党组织列表</title>
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
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
</head>

<body>
     <div class="mini-splitter" style="width:100%;height:100%;" handlerSize="9">
	 
   	 <div size="260" showCollapseButton="true">
        <div  class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;" name="form1"> 
            <span style="padding-left:5px;">党组织管理：</span>
        </div>
        <div class="mini-fit">
            <ul id="tree1" class="mini-tree" url="../organiz/party_organization!treeList.action" style="width:100%;" expandOnNodeClick="true" expandOnLoad="true"
                showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" onNodeclick="onNodeclick">        
            </ul>
        </div>
    </div>
    <div showCollapseButton="flase">
    	 <div class="mini-toolbar" style="padding:0px;border-top:0;">
	       			 <table style="width:100%;">
	           			 <tr>
			                <td style="width:100%;">
			                    <a class="mini-button" iconCls="icon-add" onclick="adddep()">增加党组织</a>
			                    <a class="mini-button" iconCls="icon-edit" onclick="editdep()">编辑党组织</a> 
			                </td>
	            		 </tr>
	        		</table>
   		 </div>
         <iframe id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
	</div>
	</div>
    
    <script type="text/javascript">
        mini.parse();
        var grid=mini.get("tree1");
	 //////////////////////////////////////////////
	 
        $().ready(function() {
        	var iframe = document.getElementById("mainframe");
       		iframe.src = "../organiz/party_organization!departadd.action";	 
        });
     //////////////////////////////////////////////
      function onNodeclick(e) {
        var node = e.node; 
    	var iframe = document.getElementById("mainframe");
        /* showTab(node); 
    	 if(node.name.indexOf("公司")!=-1){
         	iframe.src = "../organiz/party_organization!departadd.action";
         }else{
         	iframe.src = "../organiz/party_organization!departadd2.action";
         } */
  		grid.reload();
     }

      function addcom(){
      	mini.open({
      		url:"../organiz/party_organization!departadd1.action",
      		title:"信息编辑",
      		width:800,height:600,
      		
      	});
      }
      function editcom(){
        	mini.open({
        		url:"../organiz/party_organization!departadd1.action",
        		title:"信息编辑",
        		width:800,height:600,
        		
        	});
        }
      function adddep(){
        	mini.open({
        		url:"../organiz/organiz_info!add.action",
        		title:"信息编辑",
        		width:800,height:600,
        		
        	});
        }
      function editdep(){
      	mini.open({
    		url:"../organiz/organiz_info!add.action",
      		title:"信息编辑",
      		width:800,height:600,
      	});
      }
    </script>
</body>
</html>