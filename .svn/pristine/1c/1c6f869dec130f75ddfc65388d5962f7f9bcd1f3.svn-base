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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>保险福利项目</title>
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<script src="../template/plugin/public/add.js" type="text/javascript"></script>

<!--引入皮肤样式-->
<link href="../template/miniui/themes/blue/skin.css" rel="stylesheet"
	type="text/css" />
<link href="../template/plugin/public/css/tablepub.css" rel="stylesheet"
	type="text/css" />
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet"
	type="text/css" />
<link href="../template/system/css/base.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
html, body {
	font-size: 12px;
	padding: 0;
	margin: 0;
	border: 0;
	height: 100%;
	overflow: hidden;
}

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
	<form id="form1" method="post">
		<div>
			<input class="mini-hidden" name="id" id="id" value="${payareas.id}" style="display:none;" />   
			<input class="mini-hidden" name="state" id="state" value="${payareas.state}" style="display:none;" />    
			<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%" align="center">
			 
				<tr>
					<td align="left" class="list_left_box1" height="20px" width="150px"><b>缴费地区参数：</b></td>
					<td align="left" class="list_left_box1" colspan="3"></td>
				</tr>
				<tr>
					<td class="list_left_box1" valign="top">省：</td>
					<td class="list_right_box">
					 <input  name="province" id="province" required="true"  class="mini-combobox" textField="text" 
					 valueField="id" emptyText=""  value="${payareas.province}" onvaluechanged="onDeptChanged"
					  url="../basis/pay_areas!pjsonlist.action?pid=0"/>
					</td>
					<td class="list_left_box1" valign="top" width="100px">市：</td>
					<td class="list_right_box"> 
					 <input  name="city" id="city" required="true"  class="mini-combobox" textField="text" 
					 valueField="id" emptyText=""  value="${payareas.city}"
					  url="../basis/pay_areas!pjsonlist.action?pid=${payareas.province}"/>
					 </td>
				</tr> 
				<tr>
					<td class="list_left_box1" valign="top">状态：</td>
					<td class="list_right_box">
					 <input class="mini-combobox" name="state" required="true"data="[{id:0,text:'无效'},{id:1,text:'有效'}]" value="${payareas.state==null? 1:payareas.state}"/>
					</td>
				</tr> 
			</table>
		</div>
		<div class="mini-toolbar"
			style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;"
			borderStyle="border:0;">
			<a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save"
				style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a> 
			<a class="mini-button" onclick="onCancel" iconCls="icon-close"
				style="width:60px;" iconCls="icon-close">关闭</a>
		</div>
	</form> 
<div class="container">    
									<div class="mini-toolbar" style="padding:0px;border-top:0;">
										<table style="width:100%;">
											<tr>
												<td style="width:100%;"> 
													<a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
											</tr>
										</table>
									</div> 
	 <div class="mini-clearfix " style="clear:both;">  
		 <div class="mini-col-6"  style="float:left;width: 100%;"> 
		      <div class="mini-panel" title="社平工资" width="auto" expanded="false"
		           showCollapseButton="true" showCloseButton="false" > 
							<div class="mini-fit" style="height: 100px">  
									<div id="grid1" class="mini-datagrid" style="width:100%;height: 100%;" showPager="false"
										contextMenu="#gridMenu" url="../basis/pay_areas!jsonlist_wage.action?state=1" allowAlternating="true"
										idField="id" pageSize="10" onshowrowdetail="onShowRowDetail">
											<div property="columns">
					               			<div type="checkcolumn" width="50"></div>
									        <div field="id" name="id" id="id" width="100" headerAlign="center" align="center">id</div> 
									        <div field="pid" name="pid" id="pid" width="100" headerAlign="center" align="center">pid</div> 
									        <div field="type" name="type" id="type" width="100" headerAlign="center" align="center">type</div> 
									        <div field="year"  name="year"  width="100" headerAlign="center" align="center">年份</div>
									        <div field="wagemoney"  name="wagemoney"  width="100" headerAlign="center" align="center">社平工资</div>
									        <div field="startdate"  name="startdate"  width="100" headerAlign="center" align="center">开始日期</div> 
									        <div field="enddate"  name="enddate"  width="100" headerAlign="center" align="center">结束日期</div> 
											<div field="caozuo" align="center" width="120" headerAlign="center" renderer="onActionRendere" >操作</div>
										</div>
									</div> 
							</div> 
		      </div>
		 </div> 
	  </div> 
	   <div class="mini-clearfix " style="clear:both;">  
		 <div class="mini-col-6"  style="float:left;width: 100%; "> 
		      <div class="mini-panel" title="职工平均工资" width="auto"  expanded="false" buttonclick="show()"
		           showCollapseButton="true" showCloseButton="false" >
		           
						<div class="mini-fit" style="height: 100px">  
									<div id="grid2" class="mini-datagrid" style="width:100%;height:100%;" showPager="false"
										contextMenu="#gridMenu" url="../basis/pay_areas!jsonlist_wage.action?state=2" allowAlternating="true"
										idField="id" pageSize="10" onshowrowdetail="onShowRowDetail">
											<div property="columns">
						               			<div type="checkcolumn" width="50"></div>
										        <div field="id" name="id" id="id" width="100" headerAlign="center" align="center">id</div> 
										        <div field="pid" name="pid" id="pid" width="100" headerAlign="center" align="center">pid</div> 
										        <div field="type" name="type" id="type" width="100" headerAlign="center" align="center">type</div> 
										        <div field="year"  name="year"  width="100" headerAlign="center" align="center">年份</div>
										        <div field="wagemoney"  name="wagemoney"  width="100" headerAlign="center" align="center">职工平均工资</div>
										        <div field="startdate"  name="startdate"  width="100" headerAlign="center" align="center">开始日期</div> 
										        <div field="enddate"  name="enddate"  width="100" headerAlign="center" align="center">结束日期</div> 
												<div field="caozuo" align="center" width="120" headerAlign="center" renderer="onActionRender" >操作</div>
											</div>
									</div> 
						</div> 
		      </div>
		 </div> 
	  </div> 
	  <div class="mini-clearfix " style="clear:both;">  
		 <div class="mini-col-6"  style="float:left;width: 100%;height: 25%;"> 
		      <div class="mini-panel" title="最低工资" width="auto"  expanded="false" showToolbars="true"
		           showCollapseButton="true" showCloseButton="false" > 
						<div class="mini-fit" style="height: 100px">  
									<div id="grid3" class="mini-datagrid" style="width:100%;height:100%;" showPager="false"
										contextMenu="#gridMenu" url="../basis/pay_areas!jsonlist_wage.action?state=3" allowAlternating="true"
										idField="id"   onshowrowdetail="onShowRowDetail">
											<div property="columns">
						               			<div type="checkcolumn" width="50"></div>
										        <div field="id" name="id" id="id" width="100" headerAlign="center" align="center">id</div> 
										        <div field="pid" name="pid" id="pid" width="100" headerAlign="center" align="center">pid</div> 
										        <div field="type" name="type" id="type" width="100" headerAlign="center" align="center">type</div> 
										        <div field="year"  name="year"  width="100" headerAlign="center" align="center">年份</div>
										        <div field="wagemoney"  name="wagemoney"  width="100" headerAlign="center" align="center">最低工资</div>
										        <div field="startdate"  name="startdate"  width="100" headerAlign="center" align="center">开始日期</div> 
										        <div field="enddate"  name="enddate"  width="100" headerAlign="center" align="center">结束日期</div> 
												<div field="caozuo" align="center" width="120" headerAlign="center" renderer="onActionRende" >操作</div>
											</div>
									</div> 
							</div> 
		      </div>
		 </div> 
	  </div>   
 </div>  
</body>
<script type="text/javascript"> 
	mini.parse(); 
	
	var grid1 = mini.get("grid1");
	grid1.hideColumn("id"); 
	grid1.hideColumn("pid"); 
	grid1.hideColumn("type"); 
	grid1.load();
	
	var grid2 = mini.get("grid2");
	grid2.hideColumn("id");
	grid2.hideColumn("pid"); 
	grid2.hideColumn("type"); 
	grid2.load();

	var grid3 = mini.get("grid3");
	grid3.hideColumn("id");
	grid3.hideColumn("pid"); 
	grid3.hideColumn("type"); 
	grid3.load();


    var sheng = mini.get("province");
    var shi = mini.get("city");
    function onDeptChanged(e) {
        var id = sheng.getValue(); 
        shi.setValue(""); 
        var url = "../basis/pay_areas!pjsonlist.action?pid=" + id
        shi.setUrl(url); 
        shi.select(0);
    }
	function onOk() {
		var form = new mini.Form("form1");
		var data = form.getData();
		form.validate();
		if (form.isValid() == false)
			return;
		var json = mini.encode(data);
		//strut令牌
		window.parent.loading();
		$.ajax({
			url : "../basis/pay_areas!saveOrUpdate.action",
			type : 'post',
			data : {
				jsondata : json
			},
			success : function(text) {
				window.parent.unmask(); 
					mini.showMessageBox({
						title : "提醒",
						width : 250,
						iconCls : "mini-messagebox-warning",
						buttons : [ "ok" ],
						message : "保存成功！",
						callback : function(action) {
							CloseWindow("ok");
						}
					});
				 
			}
		});
	}

	function onActionRendere(e) {
		var grid = e.sender;
        var record = e.record; 
		var s = '<div><a class="New_Button" href="javascript:editSub(\'1\')">编辑</a>&nbsp;|&nbsp;<a class="New_Button" href="javascript:del(\'1\')">删除</a></div>';
   		return s;
    } 
	function onActionRender(e) {
		var grid = e.sender;
        var record = e.record; 
		var s = '<div><a class="New_Button" href="javascript:editSub(\''+record.id+'\')">编辑</a>&nbsp;|&nbsp;<a class="New_Button" href="javascript:del(\''+record.id+'\')">删除</a></div>';
   		return s;
    } 
	function onActionRende(e) {
		var grid = e.sender;
        var record = e.record; 
		var s = '<div><a class="New_Button" href="javascript:editSub(\''+record.id+'\')">编辑</a>&nbsp;|&nbsp;<a class="New_Button" href="javascript:del(\''+record.id+'\')">删除</a></div>';
   		return s;
    } 
	function show(){
		alert("wertyuio");
	}
	
	function CloseWindow(action) {
		if (action == "close" && form.isChanged()) {
			if (confirm("数据被修改了，是否先保存？")) {
				return false;
			}
		}
		if (window.CloseOwnerWindow)
			return window.CloseOwnerWindow(action);
		else
			window.close();
	}

	function onCancel(e) {
		CloseWindow("cancel");
	}
	function add() {
		var pid=mini.get("id").getValue();
		if(pid==null||pid==''){
			mini.alert("请保存地区信息后再进行如下操作");
		}
		mini.open({
					url : "../basis/pay_areas!addwage.action?pid="+mini.get("id").getValue(),
					title : "增加保险福利项目",
					width : 970,
					height : 640,
					/* allowResize : false, */
					onload : function() {
					},
					ondestroy : function(action) {
						//刷新页面
						window.location.reload();
					}
				});
	} 
	function editSub(stat) { 
		var rows='';
		var type='';
		if(stat==1){
			var rows = grid1.getSelecteds(); 
		}else if(stat==2){
			var rows = grid2.getSelecteds();
		}else if(stat==3){
			var rows = grid3.getSelecteds();
		}
		if (rows.length == 1) {
			mini.open({
				url : "../basis/pay_areas!addwage.action?id=" + rows[0].id,
				title : "编辑保险福利项目",
				width : 970,
				height : 640,
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
	function del(){
		var rows = grid.getSelecteds();
		if (rows.length == 1) {
			if (confirm("确定删除选中的记录？")) {
				$.ajax({
					url : "../basis/pay_areas!remove_wage.action?id="+rows[0].id,
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
</script>
</html>
