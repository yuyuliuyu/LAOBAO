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
    <title>休假审核列表</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link rel="stylesheet" href="../template/system/css/base.css" type="text/css"></link>
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
    .myrow {
        background: #fceee2;
    }
    </style>
</head>
<body>
    <div class="mini-toolbar" style="border-left:0;padding:0px;">
		<table style="width:100%;">
			<tr>
				<td>
					<a class="mini-button" iconCls="icon-edit" onclick="audit()">审核</a>
				</td>
			</tr>
		</table>
	</div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
             url="../check/rest!getAuditData.action" idField="id" multiSelect="true" 
             allowAlternating="true" pageSize="20" >
            <div property="columns">
            	<div type="checkcolumn"></div>
                <div type="indexcolumn" width="80" headerAlign="center">序列</div>
                <div field="deptName" width="120" headerAlign="center" align="center" >部门</div>
                <div field="jobNumber" width="120" headerAlign="center" align="center" >职工号</div>
                <div field="name" width="100" headerAlign="center" align="center" >姓名</div>
                <div field="evectionType" width="120" headerAlign="center" align="center" >休假类型</div>
                <div field="beginTime" width="160" headerAlign="center" align="center" >休假开始时间</div>
                <div field="endTime" width="160" headerAlign="center" align="center" >休假结束时间</div>
                <div field="evectionDays" width="80" headerAlign="center" align="center" >休假天数</div>
                <div field="status" width="100" headerAlign="center" align="center" >状态</div>
                <div name="action" width="100" headerAlign="center" align="center" renderer="onActionRenderer" 
                	cellStyle="padding:0;">操作</div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
        mini.parse();
        
        window.onload = windowOnload;
        function windowOnload() {
            
        }
        
        var grid=mini.get("grid");
        grid.load();
        
		//操作
	    function onActionRenderer(e) {
            var record = e.record;
            var uid = record.id;
            var s = '';
            s = s + '&nbsp; &nbsp' + '<a class="Edit_Button" href="javascript:view(\'' + uid + '\')">查看</a>'; 
            return s;
        }
        //查看
        function view(id) {
        	mini.open({
					url : "../check/rest!view.action?id=" + id,
					title : "查看申请信息",
					width : 600,
					height : 350,
					/* allowResize:false, */
					onload : function() {
					},
					ondestroy : function(action) {
						//刷新页面
						window.location.reload();
					}
				});
        }
        //审核
	    function audit(){
	    	var rows = grid.getSelecteds();
			var row = grid.getSelected();
			if (rows.length <= 0){
				mini.alert("请选择一条记录！");
			} else if (rows.length > 1){
				mini.alert("只能选择一条记录！");
			} else {
				mini.open({
						url : "../check/rest!showApprove.action?id=",
						title : "填写审核意见",
						width : 750,
						height : 410,
						allowResize : false,
						onload : function() {
						},
						ondestroy : function(action) {
							//刷新页面
							window.location.reload();
						}
					});
			}
	    }
    </script>
</body>
</html>