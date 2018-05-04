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
    <title>缺勤全月统计表</title>
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
		                <td style="width:240px;padding-left:20px;">年月份：
		                    <input class="mini-textbox" id="yearMonth" name="yearMonth" emptyText="请输入年月份：如1703" 
		                    	vtype="float;rangeLength:4" onvalidation="onYearMonthValidation" 
		                    	onenter="onKeyEnter" style="width:150px;"/>
		                </td>
		                <td style="width:240px;padding-left:20px;">是否缺勤全月：
		                	<input id="isAbsence" name="isAbsence" class="mini-combobox"
	                        	emptyText="请选择..." required="true" requiredErrorText="不能为空"
	                          	url="../template/using.txt" textField="text" 
	                          	valueField="id" width="120px" value=""/>
		                </td>
		                <td style="white-space:nowrap;">
		                    <a class="mini-button" iconCls="icon-search" onclick="search('yearMonth,isAbsence', 'yearMonth,isAbsence')" onenter="onKeyEnter">查询</a>
		                </td>
		            </tr>
		        </table>
		    </div>
		    <div class="mini-toolbar" style="border-left:0;padding:0px;">
				<table style="width:100%;">
					<tr>
						<td>
							<a class="mini-button" iconCls="icon-upload" onclick="exportAbsenceCheckInfo()">导出</a> 
							<a class="mini-button" iconCls="icon-print" onclick="print()">打印</a> 
						</td>
					</tr>
				</table>
			</div>
		    <div class="mini-fit">
		        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
		             url="../check/report!getAbsenceData.action" idField="id" multiSelect="true" 
		             allowAlternating="true" pageSize="20" >
		            <div property="columns">
		                <div field="jobNumber" width="100" headerAlign="center" align="center" >职工号</div>
		                <div field="yearMonth" width="100" headerAlign="center" align="center" >年月份</div>
		                <div field="name" width="100" headerAlign="center" align="center" >姓名</div>
		                <div field="deptName" width="150" headerAlign="center" align="center" >部门</div>
		                <div field="instituteName" width="150" headerAlign="center" align="center" >班制</div>
		                <div field="standardPostName" width="150" headerAlign="center" align="center" >标准岗位</div>
		                <div field="postName" width="150" headerAlign="center" align="center" >具体岗位</div>
		                <div field="salaryPostName" width="150" headerAlign="center" align="center" >薪酬岗位</div>
		                <div field="disease" width="100" headerAlign="center" align="center" >病假</div>
		                <div field="thing" width="100" headerAlign="center" align="center" >事假</div>
		                <div field="injury" width="100" headerAlign="center" align="center" >工伤</div>
		                <div field="explore" width="100" headerAlign="center" align="center" >探假</div>
		                <div field="give" width="100" headerAlign="center" align="center" >产假</div>
		                <div field="free" width="100" headerAlign="center" align="center" >旷工</div>
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
					var isAbsence = mini.get("isAbsence").getValue();
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
							if (isAbsence == ""){
								mini.alert("请选择是否缺勤全月");
								return;
							}
							grid.load({ searchData:"{\"depId\":\""+ids+"\", \"yearMonth\":\""+yearMonth+"\", \"isAbsence\":\""+isAbsence+"\"}" });
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
			var isAbsence = mini.get("isAbsence").getValue();
			if (isAbsence == ""){
				mini.alert("请选择是否缺勤全月");
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
				ids += node.id+",";
				return ids;
			}else{
				var list = tree.getChildNodes(node);
				if(list.length>0){
					 for ( var i = 0; i < list.length; i++) {
					    	if(list[i].flg==1){
					    		ids = ids + "," + list[i].id;
					    	}else{
					    		node = list[i];
					    		ids = depAll(ids,tree,node);
					    	}
					 }
				}
				return ids;
			}
		}
		//导出缺勤全月统计报表信息集合
		function exportAbsenceCheckInfo(){
			var yearMonth = mini.get("yearMonth").getValue();
			var isAbsence = mini.get("isAbsence").getValue();
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
			window.location.href="../check/report!exportAbsenceCheckInfo.action?yearMonth="+yearMonth
				+"&isAbsence="+isAbsence+"&depIds="+depIds;
		}
    </script>
</body>
</html>