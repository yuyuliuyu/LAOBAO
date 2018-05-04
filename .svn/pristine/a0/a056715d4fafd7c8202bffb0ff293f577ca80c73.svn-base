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
				    <a class="mini-button" iconCls="icon-upload" onclick="changeSubmit()">提交</a>
				    <a class="mini-button" iconCls="icon-lock" onclick="changeAudit()">审核</a>
			    </td>
		    </tr>
	    </table>
 	</div>
   	<div class="mini-fit">
   		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
         	 url="../contract/labor_outsource_app!listdata.action" idField="id" multiSelect="true" 
        	 allowAlternating="true" pageSize="20" >
	         <div property="columns">
		         <div type="checkcolumn">选择</div>
		         <div type="indexcolumn" headerAlign="center">序列</div>
		                       
		         <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
		         <div field="projectCode" id ="projectCode" name = "projectCode" width="100" headerAlign="center" align="left">项目编号</div>
		         <div field="applyComName" id ="applyComName" name = "applyComName"width="100" headerAlign="center" align="left">申请单位</div>
		         <div field="applyCom" id ="applyCom" name = "applyCom"width="100" headerAlign="center" align="left">申请单位</div>
		         <div field="projectName" id ="projectName" name = "projectName"width="100" headerAlign="center" align="left">项目名称</div>
		         <div field="payComName" id ="payComName" name = "payComName"width="100" headerAlign="center" align="left">付款单位</div>
		         <div field="payCom" id ="payCom" name = "payCom"width="100" headerAlign="center" align="left">付款单位</div>
		         <div field="prePurPrice" id ="prePurPrice" name = "prePurPrice"width="100" headerAlign="center" align="right">前期采购价格</div>
		         <div field="estimateConValue" id ="estimateConValue" name = "estimateConValue"width="100" headerAlign="center" align="right">预计合同值</div>
		         <div field="preContractor" id ="preContractor" name = "preContractor"width="100" headerAlign="center" align="left">前期承包方</div>
		         
		         <div field="timeRequest" id ="timeRequest" name = "timeRequest"width="100" headerAlign="center" align="left">时间要求</div>
		         <div field="technicalReq" id ="technicalReq" name = "technicalReq"width="100" headerAlign="center" align="left">技术要求</div>
		         <div type="comboboxcolumn" field="state" width="100" headerAlign="center" align="center">状态
                   <input property="editor" class="mini-combobox" data="[{id:'1', text:'审批中'},{id:0, text:'未提交'},{id:2, text:'已审批'},{id:3, text:'已驳回'}]"/>
               	 </div>
	         </div>
  		</div>
	</div>
    <script type="text/javascript">
        mini.parse();
        var grid=mini.get("grid");
        
        grid.hideColumn("id");
        grid.hideColumn("applyCom");
        grid.hideColumn("payCom");
        grid.load();
      //***************添加方法**********************//
        function add() {
        		
    			 mini.open({
    	                url : "../contract/labor_outsource_app!add.action",
    	                title : "添加计划信息",
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
  	                url : "../contract/labor_outsource_app!edit.action?id="+row.id,
  	                title : "编辑计划信息",
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
                        url : "../contract/labor_outsource_app!delete.action?id=" + sid,
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
      function changeSubmit(){
     		var row = grid.getSelected();
            if (row) {
                mini.confirm("您确定要提交该信息吗？","提醒",function(action){
                if(action=="ok"){
                    $.ajax({
                        url : "../contract/labor_outsource_app!changeSubmit.action?id=" + row.id,
                        type:'post',
                        success: function (text) {
                            if(text=="success"){
                                grid.reload();
                                mini.alert("提交成功！");
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
     	function changeAudit(){
     		var row = grid.getSelected();
            if (row) {
	            
            	var url = "../contract/labor_outsource_app!audit.action?id="+row.id;
            	
                mini.open({
                    url : url,
                    title : "审核外包合同计划申请",
                    width : 1000,
    	            height : 600,
                    /* allowResize : false, */
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
    </script>
</body>
</html>