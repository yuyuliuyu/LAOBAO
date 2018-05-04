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
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="../template/plugin/public/cent_dep.js"></script>
<script src="${base}/template/plugin/public/treeList.js"
	type="text/javascript"></script>
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
		<div class="mini-splitter" style="width:100%;height:100%;"
			handlerSize="9">
			<!-- 分中心树 start -->
			<div size="300" showCollapseButton="true">
				<!-- ckId -->
				<input type="hidden" value="${sessionScope.depId}" id="depId" />
				<div class="mini-toolbar"
					style="padding:2px;border-top:0;border-left:0;border-right:0;">
					<span style="padding-left:5px;">组织机构：</span>
				</div>
				<div class="mini-fit" style=" border-top:0;">
					<ul id="deptree" class="mini-tree"
						url="../basis/branch!getTreeListByUser.action"
						style="width:100%;height:100%;" showTreeIcon="true"
						textField="name" idField="id" parentField="pid"
						resultAsTree="false" expandOnLoad="true">
					</ul>
				</div>
			</div>
			<!-- 分中心树  end -->
			<div showCollapseButton="false">
				<div class="mini-toolbar"
					style="padding:0px;border-top:0;border-left:0;">
					<table style="width:100%;">
						<tr>
							<td style="width:200px;">职工号： <input id="jobNumber"
								name="jobNumber" class="mini-textbox" emptyText="请输入员工职工号"
								onenter="onKeyEnter" /></td>
							<td style="width:200px;">姓名： <input id="name" name="name"
								class="mini-textbox" emptyText="请输入员工姓名" onenter="onKeyEnter" />
							</td>
							<!--                			   <td style="width:200px;">岗位：
                  			   <input id="specificPost" name="specificPost" class="mini-textbox" emptyText="请输入职位" onenter="onKeyEnter"/>
               			   </td> -->

							<td style="white-space:nowrap;"><a id="searchButton"
								class="mini-button" iconCls="icon-search"
								onclick="search('jobNumber,name','jobNumber,name')"
								onenter="onKeyEnter">查询</a></td>
						</tr>
					</table>
				</div>
				<div class="mini-toolbar" style="padding:0px;border-top:0;">
					<table style="width:100%;">
						<tr>
							<td style="width:100%;">
								<!-- 			                    <a class="mini-button" iconCls="icon-add" onclick="addperson()">增加</a>
			                    <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a> -->
								<!-- 			                    <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a> 
			                    &nbsp;&nbsp;&nbsp;--> <a class="mini-button"
								iconCls="icon-addnew" onclick="reply()">申请劳保物品</a> <!-- 			                    <a class="mini-button" iconCls="icon-search" onclick="look()">查看员工详细信息</a>
			                    <a class="mini-button" iconCls="icon-tip" onclick="lizhi()">进入试岗期</a>
			                    <a class="mini-button" iconCls="icon-tip" onclick="lizhi()">转正</a>
			                    <a class="mini-button" iconCls="icon-tip" onclick="lizhi()">岗位调动</a>
			                    <a class="mini-button" iconCls="icon-tip" onclick="lizhi()">办理离职</a>
			                    <a class="mini-button" iconCls="icon-tip" onclick="tuixiu()">办理退休</a> -->
							</td>
						</tr>
					</table>
				</div>
				<div class="mini-fit">
					<div id="grid" class="mini-datagrid"
						style="width:100%;height:100%;"
						url="../laobao/yuan_gong!getPersonData.action" idField="id"
						multiSelect="true" allowAlternating="true" pageSize="20">

						<div property="columns">

							<div type="checkcolumn">选择</div>
							<div type="indexcolumn" headerAlign="center">序列</div>
							<div field="id" id="id" width="80" name="id" headerAlign="center"
								align="center" class="mini-hidden">id</div>
							<div field="jobNumber" id="jobNumber" name="jobNumber"
								width="100" headerAlign="center" align="left">职工编号</div>
							<div field="name" id="name" name="name" width="100"
								headerAlign="center" align="left">姓名</div>

							<div field="specificPost" id="specificPost" name="specificPost"
								width="100" headerAlign="center" align="center">具体岗位</div>

						
							<div type="comboboxcolumn" field="empType" width="100"
								headerAlign="center" align="center">
								员工类型 <input property="editor" class="mini-combobox"
									data="[{id:0, text:'普通员工'},{id:1, text:'业务外包人员'},{id:2, text:'劳务外包人员'},{id:3, text:'复原军人'}]" />
							</div>
							<div type="comboboxcolumn" field="onJob" width="100"
								headerAlign="center" align="center">
								员工状态 <input property="editor" class="mini-combobox"
									data="[{id:0, text:'实习期'},{id:1, text:'试岗期'},{id:2, text:'正式工'},{id:3, text:'离职'},{id:4, text:'退休'},{id:5, text:'返聘'}]" />
							</div>

							<div field="onPost" id="onPost" name="onPost" width="100"
								headerAlign="center" align="center">在岗状态</div>


						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javaScript">
		mini.parse();
		var tree = mini.get("deptree");
		var grid = mini.get("grid");
		grid.hideColumn("id");

		grid.load();
		function onKeyEnter(e) {
			$("#searchButton").click();
		}

		//////////////////////////////////////////////
		//人员管理基础数据添加
		function addperson() {

			var node = tree.getSelectedNode();
			if (node) {
				var nodeId = node.id;
				var branchId = node.branchId;
				mini.open({
					url : "../laobao/yuan_gong!add.action?departId=" + nodeId
							+ "&branchId=" + branchId,
					title : "新添加人员信息",
					width : 900,
					height : 600,
					onload : function() {
					},
					ondestroy : function(action) {
						grid.reload();
					}
				});
			} else {
				mini.alert("请选择部门！");
			}

		}

		//////////////////////////////////////////////
		//人员管理基础数据修改
		function edit() {
			var row = grid.getSelected();
			if (row) {
				mini.open({
					url : "../laobao/yuan_gong!add.action?id=" + row.id,
					title : "编辑人员信息",
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


		////////////////////////////////////////////////
		function remove() {
			var row = grid.getSelected();

			if (row) {
				var list = grid.getAllChildNodes(grid.getSelectedNode());
				var ids = row.id;
				var cid = row.cid;
				if (grid.hasChildren(grid.getSelectedNode())) {
					mini.sho8wMessageBox({
						title : "确定吗？",
						message : "该部门下带有下级部门，您确定继续执行吗？",
						buttons : [ "ok", "no" ],
						callback : function(action) {
							if (action == "ok") {
								for ( var i = 0; i < list.length; i++) {
									ids = ids + "," + list[i].id;
								}
								zhixing(ids, cid);
							} else {
								return;
							}
						}
					});

				} else {
					zhixing(ids, cid);
				}
			} else {
				mini.alert("请选中一条记录！");
			}
		}
		//////劳保物品申请

		function reply() {
			var row = grid.getSelected();
			if (row) {
				mini.open({
					url : "../laobao/wu_pin!reply.action?id=" + row.id,
					title : "新增劳保物品申请信息",
					width : 680,
					height : 350,
					allowResize : false,
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