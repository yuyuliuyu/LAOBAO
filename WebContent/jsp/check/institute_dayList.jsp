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
    <title>白班班制信息</title>
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
    <div class="mini-toolbar" style="border-left:0;padding:0px;">
		<table style="width:100%;">
			<tr>
				<td>
					<a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑白班排班</a> 
<!-- 					<a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a> -->
<!-- 					<a class="mini-button" iconCls="icon-upload" onclick="exportInstituteInfo()">导出</a>  -->
					<a class="mini-button" iconCls="icon-reload" onclick="generateYear()">生成全年白班排班</a>
					<a class="mini-button" iconCls="icon-edit" onclick="editDay()">白班预排班</a> 
				</td>
			</tr>
		</table>
	</div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
             url="../check/institute!getData.action?typeFlag=2" idField="id" multiSelect="true" 
             allowAlternating="true" pageSize="20" >
            <div property="columns">
            	<div type="checkcolumn"></div>
                <div type="indexcolumn" width="50" headerAlign="center">序列</div>
                <div field="id" id="id" name="id" width="120" headerAlign="center" allowSort="true">id</div>
                <div field="instituteName" width="80" headerAlign="center" align="center" >班制名称</div>
                <div field="startDate" width="100" headerAlign="center" align="center" >开始日期</div>
                <div field="endDate" width="100" headerAlign="center" align="center" >结束日期</div>
                <div field="day1" width="50" headerAlign="center" align="center" >1</div>
                <div field="day2" width="50" headerAlign="center" align="center" >2</div>
                <div field="day3" width="50" headerAlign="center" align="center" >3</div>
                <div field="day4" width="50" headerAlign="center" align="center" >4</div>
                <div field="day5" width="50" headerAlign="center" align="center" >5</div>
                <div field="day6" width="50" headerAlign="center" align="center" >6</div>
                <div field="day7" width="50" headerAlign="center" align="center" >7</div>
                <div field="day8" width="50" headerAlign="center" align="center" >8</div>
                <div field="day9" width="50" headerAlign="center" align="center" >9</div>
                <div field="day10" width="50" headerAlign="center" align="center" >10</div>
                <div field="day11" width="50" headerAlign="center" align="center" >11</div>
                <div field="day12" width="50" headerAlign="center" align="center" >12</div>
                <div field="day13" width="50" headerAlign="center" align="center" >13</div>
                <div field="day14" width="50" headerAlign="center" align="center" >14</div>
                <div field="day15" width="50" headerAlign="center" align="center" >15</div>
                <div field="day16" width="50" headerAlign="center" align="center" >16</div>
                <div field="day17" width="50" headerAlign="center" align="center" >17</div>
                <div field="day18" width="50" headerAlign="center" align="center" >18</div>
                <div field="day19" width="50" headerAlign="center" align="center" >19</div>
                <div field="day20" width="50" headerAlign="center" align="center" >20</div>
                <div field="day21" width="50" headerAlign="center" align="center" >21</div>
                <div field="day22" width="50" headerAlign="center" align="center" >22</div>
                <div field="day23" width="50" headerAlign="center" align="center" >23</div>
                <div field="day24" width="50" headerAlign="center" align="center" >24</div>
                <div field="day25" width="50" headerAlign="center" align="center" >25</div>
                <div field="day26" width="50" headerAlign="center" align="center" >26</div>
                <div field="day27" width="50" headerAlign="center" align="center" >27</div>
                <div field="day28" width="50" headerAlign="center" align="center" >28</div>
                <div field="day29" width="50" headerAlign="center" align="center" >29</div>
                <div field="day30" width="50" headerAlign="center" align="center" >30</div>
                <div field="day31" width="50" headerAlign="center" align="center" >31</div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
        mini.parse();
        
        var grid=mini.get("grid");
        grid.hideColumn("id");
        grid.load();
        
		//编辑
		function edit() {
			var rows = grid.getSelecteds();
			var row = grid.getSelected();
			if (rows.length <= 0){
				mini.alert("请选择一条记录！");
			} else if (rows.length > 1){
				mini.alert("只能选择一条记录！");
			} else {
				mini.open({
					url : "../check/institute!editDayClass.action?id=" + row.id,
					title : "修改白班班次信息",
					width : 850,
					height : 400,
					/* allowResize:false, */
					onload : function() {
					},
					ondestroy : function(action) {
						//刷新页面
						window.location.reload();
					}
				});
			}
		}
		//删除
		function remove() {
			var rows = grid.getSelecteds();
			if (rows.length > 0) {
				if (confirm("确定删除选中记录？")) {
					var ids = [];
					for (var i = 0, l = rows.length; i < l; i++) {
						var r = rows[i];
						ids.push(r.id);
					}
					var sid = ids.join(',');
					$.ajax({
						url : "../check/institute!remove.action?ids=" + sid,
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
		//导出考勤班次信息
		function exportInstituteInfo(){
			window.location.href="../check/institute!exportInstituteInfo.action";
		}
		//生成全年白班
		function generateYear() {
			$.ajax({
				url : "../check/institute!generateYear.action",
				type : "post",
				success : function(text) {
					if (text == "success") {
						grid.reload();
						mini.alert("生成成功！");
					} else {
						mini.alert(text);
					}
				},
				error : function(text) {
					mini.alert("生成失败");
				}
			});
		}
		//编辑白班排班信息
		function editDay() {
			mini.open({
				url : "../check/institute!editDay.action",
				title : "白班预排班",
				width : 850,
				height : 400,
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
    </script>
</body>
</html>