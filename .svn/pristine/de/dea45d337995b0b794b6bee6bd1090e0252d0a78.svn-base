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
<title>基础信息列表</title>
<style type="text/css">
</style>
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
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
		<div class="mini-splitter" style="width:100%;height:100%;" handlerSize="9">
			
            <!-- 分中心树 start -->
            <div size="260" showCollapseButton="true">
                <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                    <span style="padding-left:5px;">组织机构：</span>
                </div>
                <div class="mini-fit">
                    <ul id="tree1" class="mini-tree" url="../basis/branch!treeList.action" style="width:100%;" onNodeclick="onNodeclick"
                        showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                    </ul>
                </div>
            </div>

            <!-- 分中心树  end -->
            <div showCollapseButton="false">
            	
	            <div class="mini-toolbar" style="padding:0px;border-top:0;">
	       			 <table style="width:100%;">
	           			 <tr>
			                <td style="width:100%;">
			                    
							    <a class="mini-button" iconCls="icon-search" onclick="look()">查看</a>
			                    <a class="mini-button" iconCls="icon-upload" onclick="changeAudit()">审核</a>
			                </td>
	            		 </tr>
	        		</table>
	    		</div>
	    		<div class="mini-fit">
					<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
						url="../branch/com_change!auditData.action" idField="id" multiSelect="false" 
						 allowAlternating="true" pageSize="20" >
						 
						<div property="columns">
	                    
	                         <div type="checkcolumn">选择</div>
	                        <div type="indexcolumn" headerAlign="center">序列</div>
	                        <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
	                        <div field="changeId" id ="changeId" name = "changeId" width="100" headerAlign="center" align="center" >变更机构</div>
	                        <div field="changeName" id ="changeName" name = "changeName" width="100" headerAlign="center" align="center" >变更机构</div>
	                        <div type="comboboxcolumn" field="changeType" width="100" headerAlign="center" align="center">变更类型
								<input property="editor" class="mini-combobox" url="${base}/template/changeType.txt"/>
							</div>
	                        <div field="applyComp" id ="applyComp" name = "applyComp" width="100" headerAlign="center" align="center" >申请单位</div>
	                        <div field="applyCompName" id ="applyCompName" name = "applyCompName" width="100" headerAlign="center" align="center" >申请单位</div>
	                        <div field="effectiveDate" id ="effectiveDate" name = "effectiveDate" width="100" headerAlign="center" align="center" dateFormat="yyyy-MM-dd HH:mm:ss">生效日期</div>
	                        <div type="comboboxcolumn" field="flg" width="100" headerAlign="center" align="center">状态
									<input property="editor" class="mini-combobox" data="[{id:'1', text:'审批中'},{id:0, text:'未提交'},{id:2, text:'已审批'},{id:3, text:'已驳回'}]"/>
								</div>
	                    </div>
					</div>
       		 	</div>
            </div>
        </div>
	</div>

    <script type="text/javascript">
        mini.parse();
        
        var grid = mini.get("grid");
        grid.hideColumn("id");
        grid.hideColumn("changeId");
        grid.hideColumn("applyComp");
        grid.load();
        var tree = mini.get("tree1");
        
        //////////////////////////////////////////////
        
        ////////////////////////////////////////////////
        function look() {
            var row = grid.getSelected();
            
            if (row) {
	            
            	var url = "../branch/com_change!look.action?id="+row.id+"&changeType="+row.changeType+"&flg=false";
            	if(row.type==1||row.type=="1"){
            		url = "../branch/dept_change!look.action?id="+row.id+"&changeType="+row.changeType+"&flg=false";
            	}
                mini.open({
                    url : url,
                    title : "查看组织变更",
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
        //////////////////////////////////////////////
        
        
    	function onNodeclick(e) {
	    	var node = tree.getSelectedNode();
		  	var nodeId = node.id;
    		grid.setUrl("../branch/com_change!auditData.action?id="+node.id);
            grid.load();
     }
     
     	function changeAudit(){
     		var row = grid.getSelected();
            if (row) {
	            
            	var url = "../branch/com_change!audit.action?id="+row.id;
            	
                mini.open({
                    url : url,
                    title : "审核组织变更",
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