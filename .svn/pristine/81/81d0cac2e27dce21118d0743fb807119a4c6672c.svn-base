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
    <title>审核意见列表</title>
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
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
             url="../check/rest!getOptionData.action" idField="id" multiSelect="true" 
             allowAlternating="true" pageSize="20" >
            <div property="columns">
                <div type="indexcolumn" width="50" headerAlign="center">序列</div>
                <div field="deptName" width="100" headerAlign="center" align="center" >审核部门</div>
                <div field="name" width="100" headerAlign="center" align="center" >审核人</div>
                <div field="auditStatus" width="80" headerAlign="center" align="center" >审核状态</div>
                <div field="auditTime" width="160" headerAlign="center" align="center" >审核时间</div>
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
                
        //////////////////////////////////////////////
        function ondayRenderer(e) {
            var value = e.value;
            if(value != null){
                value = new Date(value);
                if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
            }else{
                return "";
            }
        }
        
        ///////////////////////////////////////////////
        function search() {
            var czUser= mini.get("czUser").getValue();
            var stadate= mini.get("stadate").getText();
            var enddate= mini.get("enddate").getText();
            var czType= mini.get("czType").getValue();
            var djType= mini.get("djType").getValue();
            var ip= mini.get("ip").getValue();
            var fzx=mini.get("fzx").getText();
            grid.load({
                czUser : czUser,
                stadate : stadate,
                enddate : enddate,
                djType : djType,
                ip : ip,
                fzx:fzx
            });
        }
        
        function onKeyEnter(e) {
            search();
        }
        function beforenodeselect(e) {
            //禁止选中父节点
            if (e.isLeaf == false) e.cancel = true;
        }
        
		//操作
	    function onActionRenderer(e) {
            var record = e.record;
            var uid = record.id;
            var s = '';
            s = s + '&nbsp; &nbsp' + '<a class="Edit_Button" href="javascript:view(\'' + uid + '\')">查看审核意见</a>'; 
            return s;
        }
        //查看
        function view(id) {
        	mini.open({
					url : "../check/rest!viewOption.action?id=" + id,
					title : "查看审核意见",
					width : 600,
					height : 320,
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