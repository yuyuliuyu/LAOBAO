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
        <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
            <div size="32%" showCollapseButton="true">
                <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
                    <div size="56%" showCollapseButton="true">
                        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                            <span style="padding-left:5px;">组织机构：</span>
                        </div>
                        <div class="mini-fit">
                            <ul id="tree1" class="mini-tree" url="../basis/branch!treeList.action" style="width:100%;height:100%;" onNodeclick="onNodeclick"
                                showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                            </ul>
                        </div>
                    </div>
                    <div showCollapseButton="false">
                        <!-- ckId -->
                        <input type="hidden" value="${sessionScope.id}" id="id" />
                        <input type="hidden" value="" id="id1" />
                        <!-- 分中心树 start -->
                        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                            <span style="padding-left:5px;">岗位：</span>
                        </div>
                        <div class="mini-fit" style=" border-top:0;">
                            <ul id="tree2" class="mini-tree" style="width:100%;height:100%;" onNodeclick="onNodeGwclick"
                                showTreeIcon="true" textField="positionName" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true" >
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
		<!-- 分中心树  end -->
			<div showCollapseButton="false">
				<div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
					<table style="width:100%;">
                        <tr>
			                <td style="width:100%;">
			                <!--     &nbsp;职工号： <input id="jobNumber" name="jobNumber" class="mini-textbox" emptyText="请输入员工职工号" onenter="onKeyEnter" /> -->
			                    &nbsp;姓名： <input id="name2" name="name" class="mini-textbox" emptyText="请输入员工姓名" onenter="onKeyEnter" />
			                <!--     &nbsp;岗位： <input id="post" name="post" class="mini-textbox" emptyText="请输入职位" onenter="onKeyEnter" /> -->
			    <!--                 &nbsp;状态：<input id= "type" class="mini-combobox" showNullItem="true" data="[{id:1,text:'通过'},{id:0,text:'不通过'}]" />  -->
			                 
                 			   <a id="searchButton" class="mini-button" iconCls="icon-search" onclick="search('name,type','name,type')" onenter="onKeyEnter">查询</a>
               			   </td>
			                </td>
			            </tr>
                </table>
				</div>
				<div class="mini-toolbar" style="padding:0px;border-top:0;">
					<table style="width:100%;">
						<tr>
							<td style="width:100%;">
							    <a class="mini-button" iconCls="icon-ok" onclick="agree()">同意</a>
								<a class="mini-button" iconCls="icon-no" onclick="refuse()">拒绝</a>
	</td>
						</tr>
					</table>
				</div>
				<div class="mini-fit">
					<div id="grid" class="mini-datagrid"
						style="width:100%;height:100%;"
						url="../laobao/wu_pin!getNeedCheckWp.action" idField="id"
						multiSelect="true" allowAlternating="true" pageSize="20">
						<div property="columns">
							<div type="checkcolumn"></div>
							<div type="indexcolumn" headerAlign="center">序列</div>
						
							<div field="name" width="100" headerAlign="center" align="center">姓名</div>
							<div field="wpmc" id="wpmc" name="wpmc" width="100" headerAlign="center" align="center">物品名称</div>
							<div field="count" id="count" name="count" width="100" headerAlign="center" align="center">领取数量</div>
							<div field="jldw" id="jldw" name="jldw" width="100" headerAlign="center" align="center">计量单位</div>
							<div type="comboboxcolumn"  field="isSp" width="100" headerAlign="center" align="center" >状态
							     <input property="editor" class="mini-combobox" style="width:100%;" data="[{id:0, text:'不通过'}, {id:1, text:'通过'}]" />
							</div>
							<div field="createDate" width="100" headerAlign="center" align="center" dateFormat="yyyy-MM-dd " renderer="onLongDateRenderer">创建时间</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		mini.parse();
		var zhuId;
		var tree1 = mini.get("tree1");
        var tree2 = mini.get("tree2");
		var grid = mini.get("grid");
		grid.hideColumn("id");

		grid.load();
        function onKeyEnter(e) {
             $("#searchButton").click();
        }

		function search() {
			onNodeGwclick();
		}

		////////////////////////////////////////////////
		///同意
         function agree() {
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i = 1, l = rows.length; i< l; i++) {
                ids += "," + rows[i].id;
            }

            mini.confirm("确定要同意？","提醒",function(action){
                if(action=="ok"){
                    window.parent.loading();
                    $.ajax({
                   
                        url : "../laobao/wu_pin!updateToAgree.action",
                        type : "post",
                        data : {
                            ids : ids
                        },
                        success : function(text) {
                            window.parent.unmask();
                            if (text == "success") {
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "已同意",
                                    callback: function (action) {
                                        grid.reload();
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
            });
        }

        // 不同意
        function refuse() {
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i = 1, l = rows.length; i< l; i++) {
                ids += "," + rows[i].id;
            }

            mini.confirm("确定不同意？","提醒",function(action){
                if(action=="ok"){
                    window.parent.loading();
                    $.ajax({
                        url : "../laobao/wu_pin!updateToDisAgree.action",
                        type : "post",
                        data : {
                            ids : ids
                        },
                        success : function(text) {
                            window.parent.unmask();
                            if (text == "success") {
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "成功拒绝",
                                    callback: function (action) {
                                        grid.reload();
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
            });
        }

        /**
         * 长日期格式化(yyyy-MM-dd HH:mm:ss)
         * zhanghj
         **/
        function onLongDateRenderer(e) {
            var value = e.value;
            if (value) return mini.formatDate(new Date(value), "yyyy-MM-dd ");
            return "";
        }



		// 部门选择
        function onNodeclick(e) {
            var node = tree1.getSelectedNode();
            var nodeId = node.id;
            //mini.get("id1").setValue(nodeId);
            zhuId=nodeId;
            tree2.setUrl("../post/position!listData.action?id="+node.id);
            tree2.load();
        }

		// 岗位选择
        function onNodeGwclick(e) {
            //$("#searchButton").click();
//             var node1 = tree1.getSelectedNode();
            var node2 = tree2.getSelectedNode();
            //var nodeId = node.id;
            //var zhuid=mini.get("id1").getValue();
            var name = mini.get('name2').getValue();
            var grid=mini.get("grid");
            grid.setUrl("../laobao/wu_pin!getNeedCheckWp.action?name=" + name +"&id=" + node2.id);
            grid.load();
        }

/*         function search1(name,attr){
            var searchData="{";
            if(name&&attr){
                var names=name.split(",");
                var attrs=attr.split(",");
                for(var i=0;i<names.length;i++){
                    if(mini.get(names[i])){
                        var value=mini.get(names[i]).getValue();
                        searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\"";
                        if(i!=names.length-1)searchData=searchData+",";
                    }
                }
            }
            //如果有树
            if(tree2){
                var node = tree2.getSelectedNode();
                if(node){
                    var ids = node.id;
                    if (searchData == "{"){
                        searchData=searchData+"\"depId\":\""+ids+"\"";
                    } else {
                        searchData=searchData+",\"depId\":\""+ids+"\"";
                    }
                    
                }
            }
            searchData=searchData+"}";
            grid.load({ searchData: searchData });
        } */
    </script>
</body>
</html>