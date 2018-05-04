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
<title>数据字典</title>
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet"
	type="text/css" />
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
		<div class="mini-splitter" style="width:100%;height:100%;">
			<!-- 分中心树 start -->
			<div size="20%" showCollapseButton="true">
				<input type="hidden" value="${sessionScope.depId}" id="depId" />
				<div class="mini-toolbar"
					style="padding:2px;border-top:0;border-left:0;border-right:0;">
					<span style="padding-left:5px;">字典分类：</span>
					<input class="mini-textbox" id="key" onenter="findNodes()"/>
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="findNodes()">查询</a>
				</div>
				<div class="mini-fit" style=" border-top:0;">
					<ul id="deptree" class="mini-tree"
						url="../system/dectionary!treejson.action?moduleType=${moduleType }"
						style="width:100%;height:100%;" showTreeIcon="true"
						textField="name" idField="id" parentField="pid"
						resultAsTree="false" expandOnLoad="true">
					</ul>
				</div>
			</div>
			<!-- 分中心树  end -->
			<div showCollapseButton="false">
				<div class="mini-toolbar" style="padding:0px;border-top:0;">
					<table style="width:100%;">
						<tr>
							<td style="width:100%;"><input name="pid" id="pid"
								class="mini-hidden" value="" disable="disable" width="100%" />
								<a class="mini-button" iconCls="icon-add" onclick="add_p()">增加分类</a>
								<a class="mini-button" iconCls="icon-edit" onclick="edit_p()">编辑分类</a>
								<a class="mini-button" iconCls="icon-remove" onclick="remove_p()">删除分类</a>
								<a class="mini-button" iconCls="icon-add" onclick="add_c()">增加值</a>
								<a class="mini-button" iconCls="icon-edit" onclick="edit_c()">编辑值</a>
								<a class="mini-button" iconCls="icon-remove" onclick="remove_c()">删除值</a>
							</td>
						</tr>
					</table>
				</div>
				<div id="grid" class="mini-treegrid" style="width:100%;height:94%;"
					showTreeIcon="true" url="" treeColumn="name" idField="did"
					parentField="pid" resultAsTree="false" allowResize="false"
					expandOnLoad="true">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div type="indexcolumn" headerAlign="center">序列</div>
						<s:iterator value="showlist" var="showlist">
							<div field="<s:property value="columname" />"
								id="<s:property value="columname"/>"
								name="<s:property value="columname"/>" width="100"
								headerAlign="center" align="left">
								<s:property value="chinaname" />
							</div>
						</s:iterator>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		mini.parse();
		
		var grid = mini.get("grid");
		grid.hideColumn("id");
		grid.hideColumn("pid");
		grid.hideColumn("cid");
		grid.hideColumn("did");
		
		var tree = mini.get("deptree");
		tree.on("nodeselect", function(e) {
			var node = tree.getSelectedNode();
			if (node) {
				grid.setUrl("../system/dectionary!listjson.action");
				mini.get("pid").setValue(node.id);
				grid.load({
					id : node.id
				});
				if (node.isEdit == "0") {
	                mini.alert("不可以编辑");
	            }
			}
		});
		//添加分类
		function add_p() {
			mini.open({
						url : "../system/dectionary!add.action?pid=0&moduleType=${moduleType}",
						title : "添加分类",
						width : 670,
						height : 340,
						/* allowResize : false, */
						onload : function() {
						},
						ondestroy : function(action) {
							//刷新页面
							window.location.reload();
						}
					});
		}
		//增加值
		function add_c() {
			var node = tree.getSelectedNode();
			if (typeof (node) == "undefined") {
				alert("请先选择分类，然后再添加值");
			}
			mini.open({
				url : "../system/dectionary!add.action?pid=" + node.id
						+ "&moduleType=${moduleType}",
				title : "添加值",
				width : 670,
				height : 340,
				/* allowResize : false, */
				onload : function() {
				},
				ondestroy : function(action) {
					//刷新页面
					grid.reload();
				}
			});
		}
		//编辑分类
		function edit_p() {
			var node = tree.getSelectedNode();
			if (typeof (node) == "undefined") {
				alert("请先选择分类，然后才能编辑该分类");
			}
			mini.open({
				url : "../system/dectionary!add.action?pid=0&id=" + node.id,
				title : "编辑分类",
				width : 670,
				height : 340,
				/* allowResize : false, */
				onload : function() {
				},
				ondestroy : function(action) {
					//刷新页面
					window.location.reload();
				}
			});
		}
		//编辑值
		function edit_c() {
			var node = tree.getSelectedNode();
			var rows = grid.getSelecteds();
			if (rows.length == 1) {
				mini.open({
					url : "../system/dectionary!add.action?id=" + rows[0].id
							+ "&pid=" + node.id,
					title : "编辑值",
					width : 670,
					height : 340,
					/* allowResize : false, */
					onload : function() {
					},
					ondestroy : function(action) {
						//刷新页面
						grid.reload();
					}
				});
			} else {
				mini.alert("请选中一条记录！");
			}
		}
		//删除值
		function remove_c() {
			var rows = grid.getSelecteds();
			if (rows.length == 1) {
				if (confirm("确定删除选中的记录？")) {
					$.ajax({
						url : "../system/dectionary!remove.action?id="+rows[0].id,
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
		//删除分类
		function remove_p() {
			var node = tree.getSelectedNode();
			if (typeof (node) != "undefined") {
				if (confirm("确定删除选中的分类？")) {
					$.ajax({
						url : "../system/dectionary!remove.action?id="+node.id,
						type : "post",
						success : function(text) {
							//刷新页面
							window.location.reload();
							mini.alert("删除成功！");
						},
						error : function(text) {
							mini.alert("删除失败");
						}
					});
				}
			} else {
				mini.alert("请选中一个分类！");
			}
		}
		function onKeyEnter(e) {
			search();
		}

		/* 查找节点 */
        function findNodes() {
            var key = mini.get("key").getValue();
            if (key == "") {
            	tree.clearFilter();
            } else {
            	tree.filter(function (node) {
                    var text = node.name ? node.name : "";
                    if (text.indexOf(key) != -1) {
                        return true;
                    }
                });
            }
        }

        tree.on("drawnode", function(e) {
			var node = e.node;
			if (node.isEdit == "0") {
				e.nodeStyle = "color:red;";
			}
		});
	</script>
</body>
</html>