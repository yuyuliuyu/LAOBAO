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
<title>考勤历史查询</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<link rel="stylesheet" href="../template/system/css/base.css"
	type="text/css"></link>
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
body {
	margin: 0;
	sqcdh padding: 0;
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
	<div class="mini-toolbar"
		style="padding:0px;border-top:0;border-left:0;">
		<table style="width:100%;">
			<tr>
				<td style="width:270px;padding-left:20px;">起始年月份： <input
					class="mini-textbox" id="czUser" name="czUser"
					emptyText="请输入月份：如1703" onenter="onKeyEnter" style="width:180px;" />
				</td>
				<td style="width:270px;padding-left:20px;">截止年月份： <input
					class="mini-textbox" id="czUser" name="czUser"
					emptyText="请输入月份：如1704" onenter="onKeyEnter" style="width:180px;" />
				</td>
				<td style="width:250px;padding-left:20px;">职工号： <input
					class="mini-textbox" id="czUser" name="czUser" emptyText="请输入职工号"
					style="width:150px;" /></td>
				<td style="white-space:nowrap;"><a class="mini-button"
					iconCls="icon-search" onclick="search()" onenter="onKeyEnter">查询</a>
				</td>
			</tr>
		</table>
	</div>
	<div class="mini-toolbar" style="border-left:0;padding:0px;">
		<table style="width:100%;">
			<tr>
				<td><a class="mini-button" iconCls="icon-upload" onclick="exportClassInfo()">导出</a> </td>
			</tr>
		</table>
	</div>
	<div class="mini-fit">
		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;"
			url="../laobao/wupin_history!getPersonData.action" idField="id"
			multiSelect="true" allowAlternating="true" pageSize="20">

			<div property="columns">

				<div type="checkcolumn">选择</div>
				<div type="indexcolumn" headerAlign="center">序列</div>
				<div field="jobNumber" id="jobNumber" width="80" name="jobNumber" headerAlign="center"
					align="center" class="mini-hidden">职工号</div>
				<div field="wpmc" id="wpmc" name="wpmc" width="100"
					headerAlign="center" align="left">物品名称</div>
				<div field="ggxh" id="ggxh" name="ggxh" width="100"
					headerAlign="center" align="left">规格型号</div>
				<div field="jldw" id="jldw" name="jldw" width="100"
					headerAlign="center" align="left">计量单位</div>
				<div field="gys" id="gys" name="gys" width="100"
					headerAlign="center" align="left">供应商</div>
				<div field="createdate" id="createdate" name="createdate"
					width="100" headerAlign="center" align="left">创建时间</div>
				<div fiels="nextdate" id="nextdate" name="nextdate"
					width="100" healthAlign="center" align="left">下次领取时间</div>

			</div>
		</div>
	</div>

	<script type="text/javascript">
		mini.parse();

		window.onload = windowOnload;
		function windowOnload() {

		}

		var grid = mini.get("grid");
		grid.load();

		//////////////////////////////////////////////
		function ondayRenderer(e) {
			var value = e.value;
			if (value != null) {
				value = new Date(value);
				if (value)
					return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
			} else {
				return "";
			}
		}

		///////////////////////////////////////////////
		function search() {
			var czUser = mini.get("czUser").getValue();
			var stadate = mini.get("stadate").getText();
			var enddate = mini.get("enddate").getText();
			var czType = mini.get("czType").getValue();
			var djType = mini.get("djType").getValue();
			var ip = mini.get("ip").getValue();
			var fzx = mini.get("fzx").getText();
			grid.load({
				czUser : czUser,
				stadate : stadate,
				enddate : enddate,
				djType : djType,
				ip : ip,
				fzx : fzx
			});
		}

		function onKeyEnter(e) {
			search();
		}
		function beforenodeselect(e) {
			//禁止选中父节点
			if (e.isLeaf == false)
				e.cancel = true;
		}
		function exportClassInfo(){
			window.location.href="../laobao/wupin_history!exportStandardInfo.action";
		}

/* 		//新增
		function add() {
			mini.open({
				url : "../check/institute!add.action",
				title : "新增班制表信息",
				width : 660,
				height : 440,
				allowResize : false,
				onload : function() {
					//var iframe = this.getIFrameEl(); 
				},
				ondestroy : function(action) {
					//刷新页面
					window.location.reload();
				}
			});
		}
		//编辑
		function edit() {
			var rows = grid.getSelecteds();
			var row = grid.getSelected();
			if (rows.length <= 0) {
				mini.alert("请选择一条记录！");
			} else if (rows.length > 1) {
				mini.alert("只能选择一条记录！");
			} else {
				mini.open({
					url : "../check/institute!edit.action?id=" + row.id,
					title : "修改班制表信息",
					width : 700,
					height : 400,
					/* allowResize:false, */
/* 					onload : function() {
					},
					ondestroy : function(action) {
						//刷新页面
						window.location.reload();
					}
				});
			}
		}
		//循环排班
		function cycle() {
			mini.open({
				url : "../check/institute!cycle.action",
				title : "循环排班",
				width : 660,
				height : 500,
				allowResize : false,
				onload : function() {
					//var iframe = this.getIFrameEl(); 
				},
				ondestroy : function(action) {
					//刷新页面
					window.location.reload();
				}
			});
		} */ 
	</script>
</body>
</html>