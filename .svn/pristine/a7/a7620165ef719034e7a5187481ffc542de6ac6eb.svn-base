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
<title>外包合同计划申请列表</title>
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
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
	   	<table style="width:100%;">
		    <tr>
			    <td style="width:100%;">
				    <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
				    <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
				    <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
			    </td>
		    </tr>
	    </table>
 	</div>
   	<div class="mini-fit">
   		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
         	 url="../contract/outcontract!listdata.action" idField="id" multiSelect="true" 
        	 allowAlternating="true" pageSize="20" >
	         <div property="columns">
		         <div type="checkcolumn">选择</div>
		         <div type="indexcolumn" headerAlign="center">序列</div>
		                       
		         <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
		         <div field="contractCode" id ="contractCode" name = "contractCode" width="100" headerAlign="center" align="left">合同编号</div>
		         <div field="contractName" id ="contractName" name = "contractName"width="100" headerAlign="center" align="left">合同名称</div>
		         <div field="contractorJia" id ="contractorJia" name = "contractorJia"width="100" headerAlign="center" align="left">合同甲方</div>
		         <div field="contractorNameJia" id ="contractorNameJia" name = "contractorNameJia"width="100" headerAlign="center" align="left">合同甲方</div>
		         <div field="contractorNameYi" id ="contractorNameYi" name = "contractorNameYi"width="100" headerAlign="center" align="left">合同乙方</div>
		         <div field="contractorYi" id ="contractorYi" name = "contractorYi"width="100" headerAlign="center" align="left">合同乙方</div>
		         <div field="type" id ="type" name = "type"width="100" headerAlign="center" align="right">合同类别</div>
		         <div type="comboboxcolumn" field="payDirection" width="100" headerAlign="center" align="center">付款方向
                   <input property="editor" class="mini-combobox" data="[{id:'1', text:'收款'},{id:0, text:'付款'}]"/>
               	 </div>
		         <div field="applyId" id ="applyId" name = "applyId"width="100" headerAlign="center" align="right">申请单id</div>
		         <div field="applyCode" id ="applyCode" name = "applyCode"width="100" headerAlign="center" align="left">申请单编号</div>
		         
		         <div field="applyComp" id ="applyComp" name = "applyComp"width="100" headerAlign="center" align="left">申请单公司</div>
		         <div field="startDate" id ="startDate" name = "startDate"width="100" headerAlign="center" align="left">合同开始日</div>
		         <div field="endDate" id ="endDate" name = "endDate"width="100" headerAlign="center" align="left">合同截止日</div>
		         
	         </div>
  		</div>
	</div>
    <script type="text/javascript">
        mini.parse();
        var grid=mini.get("grid");
        
        grid.hideColumn("id");
        grid.hideColumn("contractorJia");
        grid.hideColumn("contractorYi");
        
        grid.hideColumn("applyId");
        grid.load();
      //***************添加方法**********************//
        function add() {
        		
    			 mini.open({
    	                url : "../contract/outcontract!add.action",
    	                title : "添加合同信息",
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
  	                url : "../contract/outcontract!edit.action?id="+row.id,
  	                title : "编辑合同信息",
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
  		mini.confirm("您确定要删除该信息吗？","提醒",function(action){
                if(action=="ok"){
                	var ids = [];
  					for (var i = 0, l = rows.length; i < l; i++) {
  						var r = rows[i];
  						ids.push(r.id);
  					}
  					var sid = ids.join(',');
                    $.ajax({
                        url : "../contract/outcontract!delete.action?id=" + sid,
                        type:'post',
                        success: function (text) {
                            if(text=="success"){
                                grid.reload();
                                mini.alert("删除成功！");
                            }else{
                                mini.alert(text);
                            }
                        }
                    });
                }
            });
  			
  		} else {
  			mini.alert("请选中一条记录！");
  		}
  	}	
      
    </script>
</body>
</html>