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
<title>考核信息列表</title>
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
		                	<input class="mini-hidden" name="state" id="state" value="${state}" style="display:none;" />   
							<s:if test="state==0"> 
								<a class="mini-button" iconCls="icon-ok" onclick="appral()">审批</a>   
							</s:if>
							<s:elseif test="state==1">
	 							<a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
								<a class="mini-button" iconCls="icon-edit" onclick="edit()">修改</a>
								<!-- <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a> -->
							</s:elseif>
							<s:else>
							</s:else>
		                
		                </td>
            		 </tr>
        		</table>
    		</div>
    		<div class="mini-fit">
	    		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
             		url="../personnel/practice!getKhData.action?state=${state}&personId=${personId}" idField="id" multiSelect="true" 
            		 allowAlternating="true" pageSize="100" >
		                <div property="columns">
	                        <div type="checkcolumn">选择</div>
	                        <div type="indexcolumn" headerAlign="center">序列</div>
	                        <div field="id" id="id" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
	                        <div field="checkName" id ="checkName" name = "checkName"width="100" headerAlign="center" align="left">考核名称</div>
	                        <div field="checkContent" id ="checkContent" name = "checkContent"width="100" headerAlign="center" align="left">考核内容</div>
	                        <div field="score" id ="score" name = "score"width="100" headerAlign="center" align="left" >评分</div>
	                        <div field="begindate" id ="begindate" name = "begindate"width="100" headerAlign="center" align="left" renderer="onMinuteRenderer">考核开始时间</div>
	                        <div field="enddate" id ="enddate" name = "enddate"width="100" headerAlign="center" align="left" renderer="onMinuteRenderer">考核结束时间</div>
	                    	<div field="nodeName" name="nodeName" id="nodeName" width="120" headerAlign="center" align="center" >流程节点</div> 
	                    	<div field="nodeId" name="nodeId" id="nodeId" width="120" headerAlign="center" align="center" >岗位id</div> 
	                    	<div field="processId" name="processId" id="processId" width="120" headerAlign="center" align="center" >流程id</div>
	                    	<div name="action" width="120" headerAlign="center" align="center" renderer="onActionRenderer" 
	                    	cellStyle="padding:0;">操作</div>
	                    </div>
       		 	</div>
	</div>
    
    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.hideColumn("id");
        grid.hideColumn("processId");
		grid.hideColumn("nodeId");
        grid.load();
        function onMinuteRenderer(e) {
			var value = e.value;
			if(value != null){
			    value = new Date(value);
			    if (value) return mini.formatDate(value, 'yyyy-MM-dd');
			}else{
			    return "";
			}
		}
        
      //操作
	    function onActionRenderer(e) {
            var record = e.record;
            var uid = record.id;
            var s = '';
            s = s + '&nbsp; &nbsp' + '<a class="Edit_Button" href="javascript:view(\'' + uid + '\')">查看</a> | <a class="Edit_Button" href="javascript:viewApprove(\'' + uid + '\')">审批信息</a>'; 
            return s;
        }
        function add() {
    			 mini.open({
    	                url : "../personnel/practice!checkadd.action?personId=${personId}",
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
        function edit() {
          	 var row = grid.getSelected();
          	 if (row) {
           		mini.open({
                      	 url : "../personnel/practice!checkadd.action?id="+row.id,
                        title : "编辑信息",
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
       					url : "../personnel/practice!remove.action?ids=" + sid,
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
      	//审核
		function appral(){ 
			var rows = grid.getSelecteds();
			if (rows.length == 1) {
        	mini.open({
					url : "../check/overtime_sub!add.action?type=10&pid=" +rows[0].id+"&processId="+rows[0].processId,
					title : "审批页面",
					width : 800,
					height : 450,
					/* allowResize:false, */
					onload : function() {
					},
					ondestroy : function(action) {
						//刷新页面
						window.location.reload();
					}
				});

			} else {
				mini.alert("请选中一条记录！");
			}
		}
		//操作
	    function onActionRenderer(e) {
            var record = e.record;
            var uid = record.id;
            var s = '';
            s = s + '&nbsp; &nbsp' + '<a class="Edit_Button" href="javascript:view(\'' + uid + '\')">查看</a> | <a class="Edit_Button" href="javascript:viewApprove(\'' + uid + '\')">审批信息</a>'; 
            return s;
        }
        //查看
        function view(id){
        	mini.open({
					url : "../personnel/practice!checkview.action?id="+id,
					title : "查看出差申请",
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
     	 //查看审批信息
        function viewApprove(id) {
        	mini.open({
					url : "../check/overtime_sub!list.action?pid="+ id,
					title : "查看审核信息",
					width : 800,
					height : 450,
					/* allowResize:false, */
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