//////////////带仓库树的js//////////////////////
/**仓库树宽度160*/
/**选择总仓库   不显示数据*/
var currentTab;
$(function(){ 
	mini.parse();	
	var grid = mini.get("grid");
	var ckid=$("#ckId").val();
	var searchdata="{\"storageId\":\""+$("#ckId").val()+"\",\"ckId\":\""+$("#ckId").val()+"\"}";
	queryCondition=searchdata;//记录查询条件，用于导出
	if(grid){
		grid.hideColumn("id");
		if(grid.url && grid.url.indexOf("?ckId=")==-1){
			grid.load({ searchData:searchdata }); 
		}else{
			grid.load(); 
		}
	}
	
	//如果有树(仓库)
	var tree=mini.get("cktree");
	if(tree && grid){
		//默认选中
		var originalNode=tree.getNode($("#ckId").val());
		tree.selectNode(originalNode);
		tree.on("nodeclick", function (e) {
			var node = tree.getSelectedNode();
			//如果不是当前登录用户所属仓库节点 的子节点 或其本身   不能选择
			if(!tree.isAncestor ( originalNode, node )){
				tree.selectNode ( originalNode );//选中原节点
				//tree.unSelectNode( node );//取消选中当前节点
				return;
			}
			if (node) {
				if(document.getElementById("searchButton")){ 
					$("#searchButton").click();
				}else { 
					grid.load({ searchData:"{\"storageId\":\""+node.id+"\",\"ckId\":\""+node.id+"\"}" });
				}
			} else {
				grid.setData([]);
				grid.setTotalCount(0);
			}
		});
	}
}); 
/**跳转增加页面
 * a:title
 * b:包名
 * c:类名
 * isCommon:如果有值 调用BASEMANAGEACTION
 * action方法名:add
 * */
function add(a,b,c,isCommon) {
	var tabs = window.parent.mini.get("mainTabs");
	currentTab = tabs.getActiveTab(); 
	var id = "add$新增"+a;
	var tab = tabs.getTab(id);
	if (tab) {
	    tabs.removeTab(tab);
	}
	tab = {};
	tab.name = id;
	tab.title = "新增"+a;
	tab.showCloseButton = true; 
	var className=c;
	c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
	var url= "../"+b+"/"+c+"!add.action?id="+null+"&show=true";
	if(isCommon)url="../basemanage/base_manage!add.action?packageName="+b+"&className="+className+"&id="+null+"&show='true'";
	tab.url = url;
	//如果有仓库树 1代表总仓库
	if(mini.get("cktree")){
		var tree = mini.get("cktree");
		var node = tree.getSelectedNode();
		var nodeId = node.id;
		if(nodeId == "1"){
			mini.alert("不能选择总仓库！");
			return;
		}
		var originalNode=tree.getNode($("#ckId").val());
		if(!tree.isAncestor ( originalNode, node )){
			mini.alert("您没有此仓库的权限!");
			return;
		}
		tab.url = "../"+b+"/"+c+"!add.action?id="+null+"&show=true&ckId="+nodeId;
		tabs.addTab(tab);
		tab.parent=currentTab;
		tabs.activeTab(tab);
	}
		
}
/**跳转增加页面
 * a:title
 * b:包名
 * c:类名
 * isCommon:如果有值 调用BASEMANAGEACTION
 * action方法名:add1
 * */
function add1(a,b,c,isCommon) {
	var tabs = window.parent.mini.get("mainTabs");
	currentTab = tabs.getActiveTab(); 
	var id = "add$新增"+a;
	var tab = tabs.getTab(id);
	if (tab) {
		tabs.removeTab(tab);
	}
	tab = {};
	tab.name = id;
	tab.title = "新增"+a;
	tab.showCloseButton = true; 
	var className=c;
	c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
	var url= "../"+b+"/"+c+"!add1.action?id="+null+"&show=true";
	if(isCommon)url="../basemanage/base_manage!add1.action?packageName="+b+"&className="+className+"&id="+null+"&show='true'";
	tab.url = url;
	//如果有仓库树 1代表总仓库
	if(mini.get("cktree")){
		var tree = mini.get("cktree");
		var node = tree.getSelectedNode();
		var nodeId = node.id;
		if(nodeId == "1"){
			mini.alert("不能选择总仓库！");
			return;
		}
		var originalNode=tree.getNode($("#ckId").val());
		if(!tree.isAncestor ( originalNode, node )){
			mini.alert("您没有此仓库的权限!");
			return;
		}
		tab.url = "../"+b+"/"+c+"!add1.action?id="+null+"&show=true&ckId="+nodeId;
		tabs.addTab(tab);
		tab.parent=currentTab;
		tabs.activeTab(tab);
	}
	
}
/**跳转编辑页面
 * a:title
 * b:包名
 * c:类名
 * isCommon:如果有值，将调用BaseManageAction
 * action方法名:edit
 * */
function edit(a,b,c,isCommon) {
	var tabs = window.parent.mini.get("mainTabs");
	currentTab = tabs.getActiveTab(); 
	var id = "edit$编辑"+a;
	var tab = tabs.getTab(id);
	if (tab) {
	    tabs.removeTab(tab);
	}
	tab = {};
	tab.name = id;
	tab.title = "编辑"+a;
	tab.showCloseButton = true; 
	var className=c;
	c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
	//xhp update 2016/4/6
	var grid=mini.get("grid");
	var rows=grid.getSelecteds();
	if(rows.length!=1){
		mini.alert("请选择一条记录!");
		return;
	}
	
	//如果存在审核状态,且审核通过，不能编辑
	if(rows[0].flag && (rows[0].flag != 0 && rows[0].flag!="草稿" && rows[0].flag!=2 && rows[0].flag!="未通过")){
		mini.alert("提交,审核或废除的记录，不能编辑！");
		return;
	}
	var url="../"+b+"/"+c+"!edit.action?id="+rows[0].id+"&show=true"; 
	if(isCommon)url="../basemanage/base_manage!edit.action?packageName="+b+"&className="+className+"&id="+rows[0].id+"&show='true'";
	tab.url = url;
	//如果有仓库树 1代表总仓库
	if(mini.get("cktree")){
		var nodeId = mini.get("cktree").getSelectedNode().id;
		if(nodeId == "1"){
			mini.alert("不能选择总仓库！");
			return;
		}
		tab.url = "../"+b+"/"+c+"!edit.action?id="+rows[0].id+"&show=true&ckId="+nodeId;
		tabs.addTab(tab);
		tab.parent=currentTab;
		tabs.activeTab(tab);
	}
}
/**跳转查看页面
 * a:title
 * b:包名
 * c:类名
 * action方法名:look
 * */
function look(a,b,c) {
	var tabs = window.parent.mini.get("mainTabs");
	currentTab = tabs.getActiveTab(); 
	var id = "look$查看"+a;
	var tab = tabs.getTab(id);
	if (tab) {
	    tabs.removeTab(tab);
	}
	tab = {};
	tab.name = id;
	tab.title = "查看"+a;
	tab.showCloseButton = true; 
	c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
	//xhp update 2016/4/6
	var grid=mini.get("grid");
	var rows=grid.getSelecteds();
	if(rows.length!=1){
		mini.alert("请选择一条记录!");
		return;
	}
	if(mini.get("cktree")){
		var nodeId = mini.get("cktree").getSelectedNode().id;
		if(nodeId == "1"){
			mini.alert("不能选择总仓库！");
			return;
		}
	    tab.url = "../"+b+"/"+c+"!look.action?id="+rows[0].id+"&show=false&ckId="+nodeId;
	    tabs.addTab(tab);
	    tab.parent=currentTab;
	    tabs.activeTab(tab);
	}
}
/**删除
 * a:title
 * b:包名
 * c:类名
 * action方法名:delete
 * isCommon:如果有值 将调用通用的BaseManageAction  默认为不是true
 * 传递到后台的参数:{ids：树列表为选中节点及所有子节点id；普通列表为所有选中行的id（用逗号连接的字符串）}
 * 删除成功返回"success"
 * */
function remove(a,b,c,isCommon){
	var grid=mini.get("grid");
	var rows=grid.getSelecteds();
	var l=rows.length;
	var row;
	var ids;
	var className=c;
	c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
	//如果是树
	if(grid.getAllChildNodes){
		//只能选中一条
		if(l!=1){
			mini.alert("请选择一条记录!");
			return;
		}
		row=rows[0];
		ids=row.id;
		
		//如果存在审核状态,且审核通过，不能删除
		if(rows[0].flag && (rows[0].flag != 0 && rows[0].flag!="草稿" && rows[0].flag!=2 && rows[0].flag!="未通过")){
			mini.alert("提交,审核或废除的记录不能删除，删除失败！");
			return;
		}
		var list = grid.getAllChildNodes( grid.getSelectedNode( ));
		//如果有子节点
		if (grid.hasChildren(grid.getSelectedNode())) {
			mini.confirm("存在下级"+a+"，您确定继续执行吗？","提醒",function(action){
				if(action=="ok"){
					for(var i=0;i<list.length;i++){
						ids = ids +","+list[i].id;
					}
					deleteok(b,c,ids,grid,isCommon,className);
				}else{
					return;
				}
			});
		}else{//否则没有子节点
			deleteok(b,c,ids,grid,isCommon,className);
		}
	}else{//否则不是树
		if(l==0){
			mini.alert("请选择一条记录!");
			return;
		}
		ids=rows[0].id;
		//如果锁定,不能删除
		if(rows[0].status && rows[0].status==1){
			mini.alert("该记录已锁定，请解锁以后再进行删除！");
			return;
		}
		//如果存在审核状态,且审核通过，不能删除
		if(rows[0].flag && (rows[0].flag != 0 && rows[0].flag!="草稿" && rows[0].flag!=2 && rows[0].flag!="未通过")){
			mini.alert("提交,审核或废除的记录不能删除，删除失败！");
			return;
		}
		for(var i=1;i<l;i++){
			//如果锁定,不能删除
			if(rows[i].status && rows[i].status==1){
				mini.alert("记录已锁定，请解锁以后再删除！");
				return;
			}
			ids=ids+","+rows[i].id;
		}
		deleteok(b,c,ids,grid,isCommon,className);
	}
}
/**执行删除*/
function deleteok(b,c,ids,grid,isCommon,className){
	mini.confirm("您确定要删除该信息吗？","提醒",function(action){
		var url="../"+b+"/"+c+"!delete.action?ids=" + ids;
		if(isCommon)url="../basemanage/base_manage!delete.action?ids="+ids+"&className="+className;
		if(action=="ok"){
			window.parent.loading();
			$.ajax({
				url : url,
				type:'post',
				success: function (text) {
					window.parent.unmask();
					if(text=="success"){
						grid.reload();
						mini.alert("删除成功！");
					}else{
						mini.alert(text);
					}
				}
			});
		}
	});
}
/**锁定记录
 * a:包名
 * b:类名
 * action方法名：lock
 * 传递到后台的参数：{ids:所有选中id（逗号连接的字符串）}
 * 锁定成功返回"success"
 * */
function lock(a,b,isCommon) {
	var grid=mini.get("grid");
	var rows = grid.getSelecteds();
	if (rows.length > 0) {
		var className=b;
		b = b.replace(/([A-Z])/g,"_$1").toLowerCase();
		var ids = [];
		//如果是树
		if(grid.getAllChildNodes){
			//只能选中一条
			if(rows.length!=1){
				mini.alert("请选择一条记录!");
				return;
			}
			var list = grid.getAllChildNodes( grid.getSelectedNode( ));
			//选中节点Id
			ids.push(rows[0].id);
			//如果有子节点
			if (grid.hasChildren(grid.getSelectedNode())) {
				//子节点id
				for(var i=0;i<list.length;i++){
					ids.push(list[i].id);
				}
			}
		}else{//否则不是树
			for (var i = 0, l = rows.length; i < l; i++) {
				var r = rows[i];
				if(r.id==null||r.id==""){ 
					mini.alert("已选择数据中包含未保存项，请保存后再编辑锁定状态!");
					return;
				}else{
					ids.push(r.id);
				}
			}
		}
		mini.confirm("确定锁定选中记录？", "确定?", function (action) {
			if (action == "ok") {
				window.parent.loading();
				var url="../"+a+"/"+b+"!lock.action?ids=" +ids;
				if(isCommon)url="../basemanage/base_manage!lock.action?ids="+ids+"&className="+className;
				$.ajax({
					url: url,
					success: function (text) {
						window.parent.unmask();
						if(text=="success"){
							mini.alert("锁定成功！");
//							grid.deselectAll(true);
							grid.reload();
						}else{
							mini.alert(text);
						}
					} 
				});
			}
		});
	} else {
		mini.alert("请选中一条记录！");
	}
}
/**解锁记录
 * a:包名
 * b:类名
 * action方法名：unlock
 * 传递到后台的参数：{ids:所有选中id（逗号连接的字符串）}
 * 锁定成功返回"success"
 * */
function unLock(a,b,isCommon) {
	var grid=mini.get("grid");
	var rows = grid.getSelecteds();
	if (rows.length > 0) {
		var className=b;
		b = b.replace(/([A-Z])/g,"_$1").toLowerCase();
		var ids = [];
		//如果是树
		if(grid.getAllChildNodes){
			//只能选中一条
			if(rows.length!=1){
				mini.alert("请选择一条记录!");
				return;
			}
			var list = grid.getAllChildNodes( grid.getSelectedNode( ));
			//选中节点Id
			ids.push(rows[0].id);
			//如果有子节点
			if (grid.hasChildren(grid.getSelectedNode())) {
				//子节点id
				for(var i=0;i<list.length;i++){
					ids.push(list[i].id);
				}
			}
		}else{//否则不是树
			for (var i = 0, l = rows.length; i < l; i++) {
				var r = rows[i];
				if(r.id==null||r.id==""){ 
					mini.alert("已选择数据中包含未保存项，请保存后再编辑锁定状态!");
					return;
				}else{
					ids.push(r.id);
				}
			}
		}
		mini.confirm("确定解锁选中记录？", "确定?", function (action) {
			if (action == "ok") {
				var url="../"+a+"/"+b+"!unlock.action?ids=" +ids;
				if(isCommon)url="../basemanage/base_manage!unlock.action?ids="+ids+"&className="+className;
				window.parent.loading();
				$.ajax({
					url:url ,
					success: function (text) {
						window.parent.unmask();
						if(text=="success"){
							mini.alert("解锁成功！");
//							grid.deselectAll(true);
							grid.reload();
						}else{
							mini.alert(text);
						}
					} 
				});
			}
		});
	} else {
		mini.alert("请选中一条记录！");
	}
}
/**显示锁定状态
 * status:{0：未锁定，1：锁定}
 * */
function onSuoRenderer(e) {
	var Suo = [{id:1,text:'锁定'},{id:0,text:'未锁定'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){if(e.value==0){return "<font color='red'>"+g.text+"</font>";}else{return g.text;}}
	}
	return "";
}
/**显示是否
 * status:{0：否，1：是}
 * */
function onIsOrNotRenderer(e) {
	var Suo = [{id:1,text:'是'},{id:0,text:'否'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){
			if(g.id==1){
				return "<font color='red'>"+g.text+"</font>";
			}else{
				return g.text;
			}
		}
	}
	return "";
}
/**查询
 * name:所有查询条件框的name字符串
 * attr:所有查询条件的属性名
 * */
function search(name,attr){
	var grid=mini.get("grid");
	var names=name.split(",");
	var attrs=attr.split(",");
	var searchData="{";
	for(var i=0;i<names.length;i++){
		if(mini.get(names[i])){
			var value=mini.get(names[i]).getValue();
			searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\"";
			if(i!=names.length-1)searchData=searchData+",";
		}
	}
	//如果有仓库树
	if(mini.get("cktree")){
		var tree=mini.get("cktree");
		var node = tree.getSelectedNode();
		if(node){
			searchData=searchData+",\"storageId\":\""+node.id+"\""+",\"ckId\":\""+node.id+"\"";
		}
	}
	searchData=searchData+"}";
	queryCondition=searchData;
	grid.load({ searchData: searchData });
}
/**点击回车查询
 * JSP:onkeydown="onKeyEnter"
 * 查询按钮id：searchButton
 * */
function onKeyEnter(){
	if((event.keyCode || event.which) == 13){
		$("#searchButton").click();
	}
}
/**弹窗新增页面
 * a:title
 * b:包名
 * c:类名
 * width:弹窗宽度 默认400
 * height:弹窗高度 默认300
 * isCommon:如果有值 将调用通用的BaseManageAction
 * action方法名:add*/
function addWindow(a,b,c,width,height,isCommon,method){
	var w=400;
	var h=300;
	if(width)w=width;
	if(height)h=height;
	var className=c;
	c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
	var grid=mini.get("grid");
	var url = "../"+b+"/"+c+"!add.action?id=" + null + "&show='true'";
	if(method)url="../"+b+"/"+c+"!"+method+".action?id=" + null + "&show='true'";
	
	if(mini.get("cktree")){
		if(mini.get("cktree").getSelectedNode().id == "1"){
			mini.alert("不能选择总仓库！");
			return;
		}
		url = "../"+b+"/"+c+"!add.action?id=" + null + "&show='true'&ckId="+mini.get("cktree").getSelectedNode().id;
		if(method)url = "../"+b+"/"+c+"!addcd.action?id=" + null + "&show='true'&ckId="+mini.get("cktree").getSelectedNode().id;
	}
	if(isCommon)url="../basemanage/base_manage!add.action?packageName="+b+"&className="+className+"&id="+null+"&show='true'";
	mini.open({
		url : url,
		title : "新增"+a,
		width : w,
		height : h,
		onload : function() {
		},
		ondestroy : function(action) {
			grid.reload();
		}
	});
}
/**弹窗编辑页面
 * a:title
 * b:包名
 * c:类名
 * width:弹窗宽度 默认400
 * height:弹窗高度 默认300
 * isCommon:如果有值 将调用通用的BaseManageAction
 * action方法名:edit*/
function editWindow(a,b,c,width,height,isCommon,method){
	var grid=mini.get("grid");
	var rows = grid.getSelecteds();
	if(rows.length!=1){
		mini.alert("请选择一条记录!");
		return;
	}
	var row=rows[0];
//	if(row.status && row.status==1){
//		mini.alert( "该记录已锁定，请解锁以后再进行编辑！");
//		return;
//	}
	//如果存在审核状态,且审核通过，不能编辑
	if(rows[0].flag && (rows[0].flag != 0 && rows[0].flag!="草稿" && rows[0].flag!=2 && rows[0].flag!="未通过")){
		mini.alert("该记录已提交或审核，不能编辑！");
		return;
	}
	var w=400;
	var h=300;
	if(width)w=width;
	if(height)h=height;
	var className=c;
	c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
	var url="../"+b+"/"+c+"!edit.action?id=" + row.id + "&show='true'";
	if(method) url="../"+b+"/"+c+"!"+method+".action?id=" + row.id + "&show='true'";
	if(isCommon)url="../basemanage/base_manage!edit.action?packageName="+b+"&className="+className+"&id="+row.id+"&show='true'";
	mini.open({
		url : url,
		title : "编辑"+a,
		width : w,
		height : h,
		onload : function() {
		},
		ondestroy : function(action) {
			grid.reload();
		}
	});
}
/**弹窗查看页面
 * a:title
 * b:包名
 * c:类名
 * width:弹窗宽度 默认400
 * height:弹窗高度 默认300
 * isCommon:如果有值 将调用通用的BaseManageAction
 * action方法名:look*/
function lookWindow(a,b,c,width,height,isCommon){
	var grid=mini.get("grid");
	var rows = grid.getSelecteds();
	if(rows.length!=1){
		mini.alert("请选择一条记录!!");
		return;
	}
	var row=rows[0];

	var w=400;
	var h=300;
	if(width)w=width;
	if(height)h=height;
	var className=c;
	c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
	var url="../"+b+"/"+c+"!look.action?id=" + row.id + "&show=false";
	if(isCommon)url="../basemanage/base_manage!look.action?packageName="+b+"&className="+className+"&id="+row.id+"&show=false";
	mini.open({
		url : url,
		title : "查看"+a,
		width : w,
		height : h,
		onload : function() {
		},
		ondestroy : function(action) {
			grid.reload();
		}
	});
}
/**激活
 * a:包名
 * b:类名 首字母小写
 * 审核状态 flag 如果有审核状态，flag=3或flag=”通过“的数据才能激活
 * action 方法名activate
 * */
function activate(a,b){
	var grid=mini.get("grid");
	var rows = grid.getSelecteds();
	if (rows.length > 0) {
		b = b.replace(/([A-Z])/g,"_$1").toLowerCase();
		var ids = [];
		for (var i = 0, l = rows.length; i < l; i++) {
			var r = rows[i];
			if( r.flag!=3 && r.flag!="通过"){
				mini.alert("已选择数据中包含未审核通过的项，请审核通过后再编辑激活状态！");
				return;
			}
			if(r.id==null||r.id==""){ 
				mini.alert("已选择数据中包含未保存项，请保存后再编辑激活状态!");
				return;
			}else{
				ids.push(r.id);
			}
		}
		mini.confirm("确定激活选中记录？", "确定?", function (action) {
			if (action == "ok") {
				window.parent.loading();
				var url="../"+a+"/"+b+"!activate.action?ids=" +ids;
				$.ajax({
					url: url,
					success: function (text) {
						window.parent.unmask();
						if(text=="success"){
							mini.alert("激活成功！");
//							grid.deselectAll(true);
							grid.reload();
						}else{
							mini.alert(text);
						}
					} 
				});
			}
		});
	} else {
		mini.alert("请选择记录！");
	}
}
/**提交
 * a:包名
 * b:类名 首字母小写
 * 单据状态 flag(0草稿 1提交中  2未通过 3通过 4废除 ) flag=0或flag=”草稿“的数据才能提交
 * action 方法名commit
 * */
function commit(a,b){
	var grid=mini.get("grid");
	var rows = grid.getSelecteds();
	if (rows.length > 0) {
		b = b.replace(/([A-Z])/g,"_$1").toLowerCase();
		var ids = [];
		for (var i = 0, l = rows.length; i < l; i++) {
			var r = rows[i];
			if(r.flag && r.flag!=0 && r.flag!="草稿"){
				mini.alert("已选择的数据已被提交，请勿重复操作！");
				return;
			}
			if(r.id==null||r.id==""){ 
				mini.alert("已选择数据中包含未保存项，请保存后再提交!");
				return;
			}else{
				ids.push(r.id);
			}
		}
		mini.confirm("确定提交选中记录？", "确定?", function (action) {
			if (action == "ok") {
				window.parent.loading();
				var url="../"+a+"/"+b+"!commit.action?ids=" +ids;
				$.ajax({
					url: url,
					success: function (text) {
						window.parent.unmask();
						if(text=="success"){
							mini.alert("提交成功！");
							//grid.deselectAll(true);
							grid.reload();
						}else{
							mini.alert(text);
						}
					} 
				});
			}
		});
	} else {
		mini.alert("请选择记录！");
	}
}
/**作废
 * a:包名
 * b:类名 首字母小写
 * 单据状态 flag(0草稿 1提交中  2未通过 3通过 4废除 ) flag=3或flag=”通过“的数据才能提交
 * action 方法名revoke
 * */
function revoke(a,b){
	var grid=mini.get("grid");
	var rows = grid.getSelecteds();
	if (rows.length > 0) {
		b = b.replace(/([A-Z])/g,"_$1").toLowerCase();
		var ids = [];
		for (var i = 0, l = rows.length; i < l; i++) {
			var r = rows[i];
			if(r.flag && r.flag==4 || r.flag=="废除"){
				mini.alert("已选择的数据已废除，请勿重新操作！");
				return;
			}
			if(r.flag!=3 && r.flag!="通过"){
				mini.alert("已选择的数据未审核，请先审核！");
				return;
			}
			
			if(r.id==null||r.id==""){ 
				mini.alert("已选择数据中包含未保存项，请保存后再提交!");
				return;
			}else{
				ids.push(r.id);
			}
		}
		mini.confirm("确定作废选中记录？", "确定?", function (action) {
			if (action == "ok") {
				window.parent.loading();
				var url="../"+a+"/"+b+"!revoke.action?ids=" +ids;
				$.ajax({
					url: url,
					success: function (text) {
						window.parent.unmask();
						if(text=="success"){
							mini.alert("作废成功！");
//							grid.deselectAll(true);
							grid.reload();
						}else{
							mini.alert(text);
						}
					} 
				});
			}
		});
	} else {
		mini.alert("请选择记录！");
	}
}
/**跳转审核页面(单据状态 flag 0草稿 1提交中  2未通过 3通过 4废除)
 * a:title
 * b:包名
 * c:类名
 * action方法名:check
 * action属性 isCheck="true"
 * 审核状态 flag flag=1或flag=”提交中“的数据才能审核
 */
function check(a,b,c) {
	var tabs = window.parent.mini.get("mainTabs");
	currentTab = tabs.getActiveTab(); 
	var id = "check$审核"+a;
	var tab = tabs.getTab(id);
	if (tab) {
	    tabs.removeTab(tab);
	}
	tab = {};
	tab.name = id;
	tab.title = "审核"+a;
	tab.showCloseButton = true; 
	c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
	var grid=mini.get("grid");
	var rows=grid.getSelecteds();
	if(rows.length!=1){
		mini.alert("请选择一条记录!");
		return;
	}
	if(rows[0].flag=="草稿" || rows[0].flag==0){
		mini.alert("该记录未提交审核");
		return;
	}
	if(rows[0].flag!="提交中" && rows[0].flag!=1){
		mini.alert("该记录已审核");
		return;
	}
	if(mini.get("cktree")){
		var nodeId = mini.get("cktree").getSelectedNode().id;
		tab.url = "../"+b+"/"+c+"!check.action?id="+rows[0].id+"&show=false&isCheck=true&ckId="+nodeId;
		tabs.addTab(tab);
		tab.parent=currentTab;
		tabs.activeTab(tab);
	}
}

/*多条审核(单据状态 flag 0草稿 1提交中  2未通过 3通过 4废除)
 * a:title
 * b:包名
 * c:类名
 * action方法名:checkMore
 * action属性 isCheck="true"
 * 审核状态 flag flag=1或flag=”提交中“的数据才能审核
 */
function checkMore(a,b,c) {
	var grid=mini.get("grid");
	var rows = grid.getSelecteds();
	if (rows.length > 0) {
		c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
		var ids = [];
		for (var i = 0, l = rows.length; i < l; i++) {
			var r = rows[i];
			if(r.flag && r.flag==4 || r.flag=="废除"){
				mini.alert("已选择的数据已废除，无法审核！");
				return;
			}
			if(r.flag=="草稿" || r.flag==0){
				mini.alert("存在记录未提交审核");
				return;
			}
			if(r.flag!="提交中" && r.flag!=1){
				mini.alert("存在已审核记录，请不要重复操作!");
				return;
			}
			
			if(r.id==null||r.id==""){ 
				mini.alert("已选择数据中包含未保存项，请保存后再提交!");
				return;
			}else{
				ids.push(r.id);
			}
		}
		mini.confirm("确定审核选中记录？", "确定?", function (action) {
			if (action == "ok") {
				window.parent.loading();
				var url="../"+b+"/"+c+"!checkMore.action?ids=" +ids;
				$.ajax({
					url: url,
					success: function (text) {
						window.parent.unmask();
						if(text=="success"){
							mini.alert("审核成功！");
//							grid.deselectAll(true);
							grid.reload();
						}else{
							mini.alert(text);
						}
					} 
				});
			}
		});
	} else {
		mini.alert("请选择记录！");
	}
}
/**导出全部
 * a:包名
 * b:类名
 * action方法名:export
 * */
function exportExcel(a,b){
	b = b.replace(/([A-Z])/g,"_$1").toLowerCase();
	var url="../"+a+"/"+b+"!export.action?searchData="+queryCondition;
	window.location.href=url;
}
/**跳转导入页面*/
function upload(a,b,c){
	//如果有仓库树 1代表总仓库
	if(mini.get("cktree")){
		var tree = mini.get("cktree");
		var node = tree.getSelectedNode();
		var nodeId = node.id;
		if(nodeId == "1"){
			mini.alert("不能选择总仓库！");
			return;
		}
		var originalNode=tree.getNode($("#ckId").val());
		if(!tree.isAncestor ( originalNode, node )){
			mini.alert("您没有此仓库的权限!");
			return;
		}
	}
	var tabs = window.parent.mini.get("mainTabs");
	currentTab = tabs.getActiveTab();
	var id = "add$导入"+a;
	var tab = tabs.getTab(id);
	if (tab) {
		tabs.removeTab(tab);
	}
	tab = {};
	tab.name = id;
	tab.title = "导入"+a;
	tab.showCloseButton = true; 
	c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
	var url= "../"+b+"/"+c+"!upload.action?ckId="+mini.get("cktree").getSelectedNode().id;
	tab.url = url;
	tabs.addTab(tab);
	tab.parent = currentTab;
	tabs.activeTab(tab);
}
/**显示审核状态
 * flag:{0草稿 1提交中  2未通过 3通过 4废除}
 * */
function onCheckRenderer(e) {
	var Suo = [{id:0,text:'草稿'},{id:1,text:'提交中'},{id:2,text:'未通过'},{id:3,text:'通过'},{id:4,text:'废除'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){
			if(e.value==2 || e.value == 4){return "<font color='red'>"+g.text+"</font>";}
			if(e.value==1 || e.value == 3){return "<font color='green'>"+g.text+"</font>";}
			if(e.value==0){return g.text;}
		}
	}
	return "";
}
/**显示激活状态
 * status:{0：未激活，1：已激活}
 * */
function onActiveRenderer(e) {
	var Suo = [{id:0,text:'未激活'},{id:1,text:'已激活'},{id:2,text:'已确认'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){
			if(e.value==2){return "<font color='green'>"+g.text+"</font>";}
			if(e.value==1){return "<font color='green'>"+g.text+"</font>";}
			if(e.value==0){return "<font color='red'>"+g.text+"</font>";}
		}
	}
	return "";
}
/**显示分配状态
 * flg:{0：未分配，1：已分配}
 * */
function onAllotRenderer(e) {
	var Suo = [{id:0,text:'未分配'},{id:1,text:'已分配'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){
			if(e.value==1){return "<font color='green'>"+g.text+"</font>";}
			if(e.value==0){return "<font color='red'>"+g.text+"</font>";}
		}
	}
	return "";
}
/**显示指令状态
 * orderStatus:{0：未完成，1：已完成}
 * */
function onOrderRenderer(e) {
	var Suo = [{id:0,text:'未完成'},{id:1,text:'已完成'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){
			if(e.value==1){return "<font color='green'>"+g.text+"</font>";}
			if(e.value==0){return "<font color='red'>"+g.text+"</font>";}
		}
	}
	return "";
}
/**上下架状态
 * orderStatus:{0：下架，1：上架}
 * */
function onshelfStatusRenderer(e) {
	var Suo = [{id:0,text:'上架'},{id:1,text:'下架'},{id:2,text:'下架中'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){
			if(e.value==0){return "<font color='green'>"+g.text+"</font>";}
			if(e.value==1){return "<font color='red'>"+g.text+"</font>";}
			if(e.value==2){return "<font color='red'>"+g.text+"</font>";}
		}
	}
	return "";
}
/**上下架确认状态
 * orderStatus:{0：下架，1：上架}
 * */
function onSureRenderer(e) {
	var Suo = [{id:0,text:'未确认'},{id:1,text:'已确认'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){
			if(e.value==1){return "<font color='green'>"+g.text+"</font>";}
			if(e.value==0){return "<font color='red'>"+g.text+"</font>";}
		}
	}
	return "";
}
/**生成计划
 * a:包名
 * b:类名
 * action方法名:generatePlan
 * */
function generatePlan(a,b,c){
	var grid=mini.get("grid");
	var rows = grid.getSelecteds();
	if (rows.length > 0) {
		b = b.replace(/([A-Z])/g,"_$1").toLowerCase();
		var ids = [];
		for (var i = 0, l = rows.length; i < l; i++) {
			var r = rows[i];
			if(r.flag && r.flag!=3 && r.flag!="通过"){
				mini.alert("已选择的数据未通过审核，请先审核！");
				return;
			}
			if(r.id==null||r.id==""){ 
				mini.alert("已选择数据中包含未保存项，请保存后再提交!");
				return;
			}else{
				ids.push(r.id);
			}
		}
		mini.confirm("确定生成计划？", "确定?", function (action) {
			if (action == "ok") {
				window.parent.loading();
				var url="../"+a+"/"+b+"!generatePlan.action?ids=" +ids;
				$.ajax({
					url: url,
					success: function (text) {
						window.parent.unmask();
						if(text=="success"){
							mini.alert("成功！");
							grid.deselectAll(true);
							grid.reload();
						}else{
							mini.alert(text);
						}
					} 
				});
			}
		});
	} else {
		mini.alert("请选择记录！");
	}
}
/**显示移库状态
 * status:{0：未移库，1：移库中，2：移库完成}
 * */
function onMoveStatusRenderer(e) {
	var Suo = [{id:1,text:'移库中'},{id:0,text:'未移库'},{id:2,text:'移库完成'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){return g.text;}
	}
	return "";
}
/**显示证件类型
 * status:{3：军官证，1：身份证，2：护照}
 * */
function onCertTypeRenderer(e) {
	var Suo = [{id:1,text:'身份证'},{id:3,text:'军官证'},{id:2,text:'护照'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){return g.text;}
	}
	return "";
}
/**
 * 分配
 */
function allot(url){
	var grid=mini.get("grid");
	var rows = grid.getSelecteds();
	if(rows==null || rows==""){
		mini.alert("请选中一条记录！");
		return;
	}
	var ids=[];
	for(var i=0;i<rows.length;i++){
		var row = rows[i];
		if(row.flag && row.flag !=3 && row.flag !="通过"){
			mini.alert("所选的数据中有未审核或废除的项，请重新选择！");
			return;
		}else if(row.flg && row.flg != 0 && row.flg !="未分配" ){
			mini.alert("所选的数据中有已分配的数据，请重新选择！");
			return;
		}else{
			ids.push(row.id);
		}	
	}
	var storageId = mini.get("cktree").getValue();
	mini.open({
		url: "../system/admin!select.action?ckId="+storageId,
		title: "选择用户",
		width: 950,
		height: 380,
		ondestroy: function (action) {                    
			if (action == "ok") {
				var iframe = this.getIFrameEl();
				var data = iframe.contentWindow.GetData();
				if (data) {
				window.parent.loading();
				$.ajax({
					url:url+"?ids="+ids,
					type:"post",
					data:{userId:data.id},
					success:function(text){
							window.parent.unmask();
							if(text == "success"){
								mini.alert("分配成功！");
								grid.deselectAll(true);
								grid.reload();
							}else{
								mini.alert(text);
							}
						}
					});  
				}
			}
		}
	});
}
/**审核不通过
 * a：包名
 * b：类名
 * action方法名:unApprove
 * 传到后台的值：{opinion:审核意见 , id}
 * */
function unApproved(a,b){
	var grid=mini.get("grid");
	var rows = grid.getSelecteds();
	if (rows.length==1) {
		var row=rows[0];
		var id = row.id;
		if(row.flag && row.flag==4 || row.flag=="废除"){
			mini.alert("该记录已废除，无法驳回！");
			return;
		}
		if(row.flag=="草稿" || row.flag==0){
			mini.alert("该记录未提交审核,无需驳回!");
			return;
		}
		if(row.flag=="审核" || row.flag==3){
			mini.alert("该记录已经审核,驳回失败!");
			return;
		}
		b = b.replace(/([A-Z])/g,"_$1").toLowerCase();
		//审核意见弹窗
		mini.open({
			url : "../truckplan/dispatch_plan!opinion.action?id="+id,
			showMaxButton: false,
			title: "审核意见",
			width:320,
			height:225,
			ondestroy: function (action) {
				if (action == "ok") {
					var iframe = this.getIFrameEl();
					var data = iframe.contentWindow.document.getElementById("opinion").value;
					
					mini.confirm("确定驳回吗？","提醒",function(action){
						if(action=="ok"){
					
							if(data.length>100){
								mini.alert("审核意见不能超过100字。");
								return;
							}
							
							window.parent.loading();
							$.ajax({
								url : "../"+a+"/"+b+"!unApprove.action",
								type:'post',
								data:{id:id,opinion:data},
								success: function (text) {
									window.parent.unmask();
									if(text=="success"){
										mini.alert("驳回成功！");
										grid.reload();
									}else{
										mini.alert(text);
									}
								}
							});
					
						}else{
							return;
						}
					});
				}
			}
		});
	}else {
		mini.alert("请选中一条记录！");
	}
}
//////////////////////////////
//提示理货员从轻货架取货(必须要相同单号)
function pickup(){
	var grid=mini.get("grid");
	var rows=grid.getSelecteds();
	if(rows.length==0){
		mini.alert("请选择记录");
		return;
	}
	var orderId=rows[0].orderId;
	var ids = [];
	for(var i=0;i<rows.length;i++){
		var row=rows[i];
		if(row.orderId!=orderId){//alert((row.orderId=='ASDF34')+"|"+(orderId=='ASDF34')+"|")
			mini.alert("请选择订单号相同的记录");
			return;
		}
		if(row.waybillId){
			mini.alert("所选记录中存在已取货的记录，不能取货");
			return;
		}
		ids.push(row.id);
	}
	
	$.ajax({
			url:'../storagemanage/out_cargo_acceptance!promptTest.action',
			type:"post",
			data:{ids:ids.toString()},
			success:function(text){
					if(text == "success"){
						var tabs = window.parent.mini.get("mainTabs");
						var id = "add$轻货架取货";
						var tab = tabs.getTab(id);
						if (tab) {
							tabs.removeTab(tab);
						}
						tab = {};
						tab.name = id;
						tab.title = "轻货架取货";
						tab.showCloseButton = true; 
						var url= "../storagemanage/out_cargo_acceptance!pickup.action?ids="+ids.toString()+"&ckId="+mini.get("cktree").getSelectedNode().id;
						tab.url = url;
						tabs.addTab(tab);
						tabs.activeTab(tab);
					}else{
						mini.alert(text);
					}
				}
			}); 
}
//将页面输入的字母转换成大写
function onWoValueChanged(e){
	var re = new RegExp("^[a-zA-Z]+$");
	if (!re.test(v))
	mini.get(e.source.id).setValue(e.value.toUpperCase());
}
/**验证英文数字*/
function onEnglishAndNumberValidation(e) {
	if (e.isValid && e.value) {
		if (isEnglishAndNumber(e.value) == false) {
			e.errorText = "必须输入英文+数字";
			e.isValid = false;
		}
	}
}
/*验证数字*/
function onNumberValidation(e) {
	if (isNumber(e) == false) {
		mini.alert("必须输入数字");
		return;
	}
}
/* 是否英文或数字 */
function isEnglishAndNumber(v) {
	var re = new RegExp("^[0-9a-zA-Z]+$");
	if (re.test(v)) return true;
	return false;
}
/* 是否数字 */
function isNumber(v) {
	var re = new RegExp("^[0-9]+$");
	if (re.test(v)) return true;
	return false;
}

/**显示盘库状态
 * status:{0：未盘库，1：盘库中，2：盘亏，3：盘盈，4：正常}
 * */
function onPkStatusRenderer(e) {
	var Suo = [{id:1,text:'盘库中'},{id:0,text:'未盘库'},{id:2,text:'盘亏'},{id:3,text:'盘盈'},{id:4,text:'正常'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){return g.text;}
	}
	return "";
}
/**显示空箱重箱
 * status:{0：空箱，1：重箱}
 * */
function onEmptyHeavyRenderer(e) {
	var Suo = [{id:1,text:'重箱'},{id:0,text:'空箱'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){return g.text;}
	}
	return "";
}
/**空重renderer*/
function onEmptyWeightRenderer(e){
	var Suo = [{id:'E',text:'空'},{id:'F',text:'重'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){return g.text;}
	}
	return "";
}
/**查询（时间段:startTime-endTime）
 * name:所有查询条件框的name字符串
 * attr:所有查询条件的属性名
 * */
function searchByTime(name,attr){
	var grid=mini.get("grid");
	var names=name.split(",");
	var attrs=attr.split(",");
	var searchData="{";
	for(var i=0;i<names.length;i++){
		var name=names[i];
		if(mini.get(name)){
			var value=(name=="startTime"||name=="endTime")?mini.get(name).getFormValue():mini.get(name).getValue();
			searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\"";
			if(i!=names.length-1)searchData=searchData+",";
		}
	}
	//如果有仓库树
	if(mini.get("cktree")){
		var tree=mini.get("cktree");
		var node = tree.getSelectedNode();
		if(node){
			searchData=searchData+",\"storageId\":\""+node.id+"\""+",\"ckId\":\""+node.id+"\"";
		}
	}
	searchData=searchData+"}";
	queryCondition=searchData;
	grid.load({ searchData : searchData });
}
/**显示应收应付
 * status:{S：应收，F：应付}
 * */
function onYsyfRenderer(e) {
	var Suo = [{id:'S',text:'应收'},{id:'F',text:'应付'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){return g.text;}
	}
	return "";
}
/**autocomplete*/
function onAutocompleteValueChanged(e){
	var item = e.selected;
	var id=e.sender.id;
	if(!item){
		mini.get(id).setValue("");
		mini.get(id).setText("");
	}
}
/**复制新增 一条 如果选择了就复制 没选就新增*/
function copy(a,b,c,isCommon) {
	var tabs = window.parent.mini.get("mainTabs");
	currentTab = tabs.getActiveTab();
	var id = "add$新增"+a;
	var tab = tabs.getTab(id);
	if (tab) {
	    tabs.removeTab(tab);
	}
	tab = {};
	tab.name = id;
	tab.title = "新增"+a;
	tab.showCloseButton = true; 
	var className=c;
	c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
	var url= "../"+b+"/"+c+"!add.action?id="+null+"&show=true";
	tab.url = url;
	//如果有仓库树 1代表总仓库
	if(mini.get("cktree")){
		var tree = mini.get("cktree");
		var node = tree.getSelectedNode();
		var nodeId = node.id;
		if(nodeId == "1"){
			mini.alert("不能选择总仓库！");
			return;
		}
		var originalNode=tree.getNode($("#ckId").val());
		if(!tree.isAncestor ( originalNode, node )){
			mini.alert("您没有此仓库的权限!");
			return;
		}
		if(nodeId=="2"){
			var grid=mini.get("grid");
			var rows=grid.getSelecteds();
			if(rows.length>1){
				mini.alert("最多选择一条记录");
				return;
			}
			if(rows.length==0){
				tab.url = "../"+b+"/"+c+"!add.action?id="+null+"&show=true&ckId="+nodeId;
			}else{
				tab.url = "../"+b+"/"+c+"!copy.action?id="+rows[0].id+"&show=true&ckId="+nodeId;
			}
		}else{
			tab.url = "../"+b+"/"+c+"!add.action?id="+null+"&show=true&ckId="+nodeId;
		}		
		tabs.addTab(tab);
		tab.parent = currentTab;
		tabs.activeTab(tab);
	}
		
}

/*清除选择框*/
function onCloseClick(e) {
    var obj = e.sender;
    obj.setText("");
    obj.setValue("");
}