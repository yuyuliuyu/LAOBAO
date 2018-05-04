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
    <title>出勤率报表</title>
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
    </style>
</head>
<body>
	<div class="mini-splitter" style="width:100%;height:100%;">
		<div size="300" showCollapseButton="true">
			<!-- ckId -->
			<input type="hidden" value="${sessionScope.depId}" id="depId" />
			<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
				<span style="padding-left:5px;">组织机构：</span>
			</div>
			<div class="mini-fit" style=" border-top:0;">
				<ul id="deptree" class="mini-tree" url="../basis/branch!getTreeListByUser.action" style="width:100%;height:100%;"
					showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true" >
				</ul>
			</div>
		</div>
		<div showCollapseButton="flase">
			<div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
		        <table style="width:100%;">
		            <tr>
		                <td style="width:270px;padding-left:20px;">年月份：
		                    <input class="mini-textbox" id="yearMonth" name="yearMonth" emptyText="请输入年月份：如1703" 
		                    	vtype="float;rangeLength:4" onvalidation="onYearMonthValidation" 
		                    	onenter="onKeyEnter" style="width:180px;"/>
		                </td>
		                <td style="white-space:nowrap;">
		                    <a class="mini-button" iconCls="icon-search" onclick="search('yearMonth', 'yearMonth')" onenter="onKeyEnter">查询</a>
		                </td>
		            </tr>
		        </table>
		    </div>
		    <div class="mini-toolbar" style="border-left:0;padding:0px;">
				<table style="width:100%;">
					<tr>
						<!-- <td>
							<a class="mini-button" iconCls="icon-upload" onclick="exportRateCheckInfo()">导出</a> 
							<a class="mini-button" iconCls="icon-print" onclick="print()">打印</a> 
						</td> -->
					</tr>
				</table>
			</div>
		    <div class="mini-fit">
		        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
		             url="../check/report!getRateData.action" idField="id" multiSelect="true" 
		             allowAlternating="true" pageSize="20" >
		            <div property="columns">
		            	<div type="indexcolumn" width="50" headerAlign="center">序列</div>
		                <div field="depName" width="120" headerAlign="center" align="center" >部门名称</div>
		                <div field="depNum" width="60" headerAlign="center" align="center" >人数</div>
		                <div field="restDays" width="60" headerAlign="center" align="center" >公休日</div>
		                <div field="holidayDays" width="60" headerAlign="center" align="center" >节假日</div>
		                <div field="shouldOutDays" width="80" headerAlign="center" align="center" >应出勤工日</div><!-- 工作日*人数 -->
		                <div field="actualOutDays" width="80" headerAlign="center" align="center" >实出勤工日</div><!-- 人员实际出勤总数 -->
		                <div field="total" width="60" headerAlign="center" align="center" >合计</div>
		                <div header="缺勤工日" headerAlign="center">
		                	<div property="columns">
		                		<div field="disease" width="60" headerAlign="center" align="center" >病</div>
		                		<div field="injury" width="60" headerAlign="center" align="center" >伤</div>
		                		<div field="thing" width="60" headerAlign="center" align="center" >事</div>
		                		<div field="free" width="60" headerAlign="center" align="center" >旷</div>
		                		<div field="marry" width="60" headerAlign="center" align="center" >婚</div>
		                		<div field="lost" width="60" headerAlign="center" align="center" >丧</div>
		                		<div field="explore" width="60" headerAlign="center" align="center" >探</div>
		                		<div field="give" width="60" headerAlign="center" align="center" >产</div>
		                	</div>
		                </div>
		                <div field="publicDays" width="60" headerAlign="center" align="center" >公假</div><!-- 员工实际休公假天数? -->
		                <div field="attendanceRate" width="60" headerAlign="center" align="center" >出勤率</div><!-- 实出勤工日/应出勤工日 -->
		                <div field="workRate" width="60" headerAlign="center" align="center" >出工率</div><!-- 员工实际出工总数/应出勤工日 -->
		            </div>
		        </div>
		    </div>
		</div>
    </div>
    <script type="text/javascript">
        mini.parse();
		var grid = mini.get("grid");
		grid.hideColumn("id");
		
		//年月份格式验证
		function onYearMonthValidation(e) {
            if (e.isValid) {
                var pattern = /\d{2}[0]\d{1}|\d{2}[1][0-2]/;
                if (e.value.length != 0){
                	if (e.value.length != 4 || pattern.test(e.value) == false) {
	                    e.errorText = "必须输入4位年月份数字";
	                    e.isValid = false;
	                }
                }
            }
        }
		
		$(function(){ 
        	grid.load();
			//如果有树
			var tree=mini.get("deptree");
			if(tree && grid){
				//默认选中
				var depId = $("#depId").val();
				if(depId){
					var originalNode=tree.getNode();
					tree.selectNode(originalNode);
				}
				tree.on("nodeclick", function (e) {
					var tree=mini.get("deptree");
					var node = tree.getSelectedNode();
					var yearMonth = mini.get("yearMonth").getValue();
					//可见即可选
					/*if(!tree.isAncestor ( originalNode, node )){
						tree.selectNode ( originalNode );//选中原节点
						//tree.unSelectNode( node );//取消选中当前节点
						return;
					}*/
					if (node) {
						if(document.getElementById("searchButton")){ 
							$("#searchButton").click();
						}else { 
							var ids = "";
							var tree=mini.get("deptree");
							if(tree){
								var node = tree.getSelectedNode();
								if(node){
									var ids = "";
									ids = depAll(ids,tree,node);
								}
							}
							if (yearMonth == ""){
								mini.alert("请输入年月份");
								return;
							}
							grid.load({ searchData:"{\"depId\":\""+ids+"\", \"yearMonth\":\""+yearMonth+"\"}" });
						}
					} else {
						grid.setData([]);
						grid.setTotalCount(0);
					}
				});
			}
		});
		
		/**查询
		 * name:所有查询条件框的name字符串
		 * attr:所有查询条件的属性名
		 * */
		function search(name,attr){
			var grid=mini.get("grid");
			var yearMonth = mini.get("yearMonth").getValue();
			if (yearMonth == ""){
				mini.alert("请输入年月份");
				return;
			}
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
			//searchData=searchData+",\"start\""+":\""+mini.get("start").getFormValue()
			//			+"\","+"\"end\""+":\""+mini.get("end").getFormValue()+"\"";
			//如果有树
			var tree=mini.get("deptree");
			if(tree){
				var node = tree.getSelectedNode();
				if(node){
					var ids = "";
					ids = depAll(ids,tree,node);
					if (searchData == "{"){
						searchData=searchData+"\"depId\":\""+ids+"\"";
					} else {
						searchData=searchData+",\"depId\":\""+ids+"\"";
					}
				}
			}
			searchData=searchData+"}";
			grid.load({ searchData: searchData });
		}
		
		function depAll(ids,tree,node){
		    if(node.flg==1){
		        ids += node.id;
		    }
		    var list = tree.getAllChildNodes(node);
		    if(list.length>0){
		        if (ids == "") ids = list[0].id; 
		        else ids += "," + list[0].id;
		        for ( var i = 1; i < list.length; i++) {
		            if(list[i].flg==1){ 
		                ids += "," + list[i].id; 
		            }
		        }
		    } else {
		        if (ids == "") ids = node.id; 
		        else ids += "," + node.id;
		    }
		    return ids;
		}
		//导出月出勤率报表信息集合
		function exportRateCheckInfo(){
			var yearMonth = mini.get("yearMonth").getValue();
			var depIds = "";
			//如果有树
			var tree=mini.get("deptree");
			if(tree){
				var node = tree.getSelectedNode();
				if(node){
					var ids = "";
					ids = depAll(ids,tree,node);
					depIds = ids.substring(0,ids.length);
				}
			}
			window.location.href="../check/report!exportRateCheckInfo.action?yearMonth="+yearMonth
				+"&depIds="+depIds;
		}
    </script>
</body>
</html>