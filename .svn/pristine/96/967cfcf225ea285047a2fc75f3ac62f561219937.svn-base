<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			pageContext.setAttribute("base", basePath);
			String flag = request.getParameter("flag");
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>数据权限</title>
<link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
<link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<style type="text/css">

.check_box {
	float: left;
	margin-right: 5px;
	*margin-right: 15px !important;
}

input[type="checkbox"] {
	vertical-align: -3px;
}
</style>
</head>

<body>
	<div class="mini-fit">
		<!-- autoCheckParent:选中子节点，将父节点也自动选中。选中父节点，自动全选子节点。 -->
		<!-- checkRecursive:全选子节点， 将父节点也自动选中。选中父节点，自动全选子节点。-->
		<div id="deptgrid" class="mini-treegrid" style="width:100%;height:100%;" textField="name"
			url="../basis/branch!treeList.action" showTreeIcon="true" treeColumn="name" idField="id" 
			parentField="pid" resultAsTree="false" expandOnLoad="true" multiSelect="true" 
			checkRecursive="false" showCheckBox="true" autoCheckParent="false">
			<div property="columns">
				<div field="name" id="name" name="name" width="100" headerAlign="center" align="left">选择</div>
			</div>
		</div>
	</div>
	<div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
		<a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
		<a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
		<a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a>
	</div>
	<script type="text/javascript">
		mini.parse();
		var dept_grid=mini.get("deptgrid");
		dept_grid.load(null,function(){ 
			if("${depId}"){
				var depIds="${depId}".split(",");
				var nodes=dept_grid.findNodes(function(node){
					if($.inArray(node.id,depIds)!=-1){
						return true;
					}
				});
				for(var i=0,l=nodes.length;i<l;i++){
					nodes[i].checked=true;
				}
			}
		});
		////////////////////////////////////////
		function SaveData() {
			var depts=dept_grid.getValue();
			var dept_array=depts.split(",");
			var depIds="";
			var flg_array=[];
			for(var i=0;i<dept_array.length;i++){
				var depId=dept_array[i];
				var node=dept_grid.findNodes(function(node){if(node.id==depId)return true;})[0];
				if(node){
					depIds=depIds+depId+",";
					flg_array.push(node.flg);
				}
			}
			
		    window.parent.loading();
		    $.ajax({
		        url: "admin!saveDatauth.action",
		        type: 'post',
		        data: { id:"${id}",flag:flg_array.toString(),depId:depIds},
		        success: function (text) {
		             window.parent.unmask();
		            if (text == "success") {
		                mini.showMessageBox({
		                    title: "提醒",
		                    width: 250,
		                    iconCls: "mini-messagebox-warning",
		                    buttons: ["ok"],
		                    message: "保存成功！",
		                    callback: function (action) {
		                        CloseWindow("ok");
		                    }
		                }); 
		            } else {
		                if(text.length>0){
		                    mini.alert(text);
		                }
		            }
		        }
		    });
		}
		
		////////////////////////////////////////
		function CloseWindow(action) {
		    if (action == "close" && form.isChanged()) {
		        if (confirm("数据被修改了，是否先保存？")) {
		            return false;
		        }
		    }
		    if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
		    else window.close();
		}
		
		function onOk(e) {
		    SaveData();
		}
		
		function onCancel(e) {
		    CloseWindow("cancel");
		}
		
		function onReload() {
		    document.location.reload();
		}
    </script>
</body>
</html>