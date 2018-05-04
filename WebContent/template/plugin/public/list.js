///////////////////////2016////////////////////////////////////////////

$(function(){ 	
	mini.parse();
	var grid = mini.get("grid");
	queryCondition="";
	if(grid){
		grid.hideColumn("id");
		grid.load();
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
	tabs.addTab(tab);
	tabs.activeTab(tab);
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
	//如果锁定,不能编辑
	if(rows[0].status && rows[0].status==1){
		mini.alert("该记录已锁定，请解锁以后再进行编辑！");
		return;
	}
	//如果存在审核状态,且审核通过，不能编辑
	if(rows[0].flag && (rows[0].flag != 0 && rows[0].flag!="草稿" && rows[0].flag!=2 && rows[0].flag!="未通过")){
		mini.alert("提交,审核或废除的记录，不能编辑！");
		return;
	}
	var url="../"+b+"/"+c+"!edit.action?id="+rows[0].id+"&show=true"
	if(isCommon)url="../basemanage/base_manage!edit.action?packageName="+b+"&className="+className+"&id="+rows[0].id+"&show='true'";
	tab.url = url;
	tabs.addTab(tab);
	tabs.activeTab(tab);
}
/**跳转查看页面
 * a:title
 * b:包名
 * c:类名
 * action方法名:look
 * */
function look(a,b,c) {
	var tabs = window.parent.mini.get("mainTabs");
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
    tab.url = "../"+b+"/"+c+"!look.action?id="+rows[0].id+"&show=false";
    tabs.addTab(tab);
    tabs.activeTab(tab);
}
/**删除
 * action方法名:delete
 * 参数:
 * a:title
 * b:包名
 * c:类名
 * isCommon:如果有值 将调用通用的BaseManageAction  默认为不是true
 * 传递到后台的参数:{ids：树列表为选中节点及所有子节点id；普通列表为所有选中行的id（用逗号连接的字符串）}
 * 删除成功返回"success"
 * */
function remove(a,b,c,isCommon){
	var grid=mini.get("grid");
	var rows=grid.getSelecteds();
	var l=rows.length
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
		var list = grid.getAllChildNodes(grid.getSelectedNode( ));
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
		var childPackage = b;
		var url="../"+b+"/"+c+"!remove.action?ids=" + ids;
		if(isCommon)url="../basemanage/base_manage!remove.action?ids="+ids+"&className="+className +"&childPackage=" + childPackage;
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
		var childPackage = a;
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
				if(isCommon)url="../basemanage/base_manage!lock.action?ids="+ids+"&className="+className +"&childPackage=" + childPackage;
				$.ajax({
					url: url,
					success: function (text) {
						window.parent.unmask();
						if(text=="success"){
							mini.alert("锁定成功！");
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
		var childPackage = a;
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
				if(isCommon)url="../basemanage/base_manage!unlock.action?ids="+ids+"&className="+className +"&childPackage=" + childPackage;
				window.parent.loading();
				$.ajax({
					url:url ,
					success: function (text) {
						window.parent.unmask();
						if(text=="success"){
							mini.alert("解锁成功！");
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
/**显示审核状态
 * status:{0草稿 1提交中  2未通过 3通过 4废除}
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
	var Suo = [{id:0,text:'未激活'},{id:1,text:'已激活'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){
			if(e.value==1){return "<font color='green'>"+g.text+"</font>";}
			if(e.value==0){return "<font color='red'>"+g.text+"</font>";}
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
			if(value!=null&&value!=""&&typeof(value)!=undefined){
				searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\",";
			}
		}
	}
	var cd = searchData.length;//长度
	var fy = searchData.charAt(cd-1);
	if(fy==","){
		searchData = searchData.substring(0,cd-1);
	}
	searchData=searchData+"}";
	queryCondition=searchData;
	grid.load({ searchData : searchData });
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
			if(value!=null&&value!=""&&typeof(value)!=undefined){
				searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\"";
				if(i!=names.length-1)searchData=searchData+",";
			}
		}
	}
	searchData=searchData+"}";
	queryCondition=searchData;
	grid.load({ searchData : searchData });
}

/**查询
 * grid:grid的id的值
 * name:所有查询条件框的name字符串
 * attr:所有查询条件的属性名
 * */
function searchByData(grid,name,attr){
	var grid=mini.get(grid);
	var names=name.split(",");
	var attrs=attr.split(",");
	var searchData="{";
	for(var i=0;i<names.length;i++){
		if(mini.get(names[i])){
			var value=mini.get(names[i]).getValue();
			if(value!=null&&value!=""&&typeof(value)!=undefined){
				searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\",";
			}
		}
	}
	var cd = searchData.length;//长度
	var fy = searchData.charAt(cd-1);
	if(fy==","){
		searchData = searchData.substring(0,cd-1);
	}
	searchData=searchData+"}";
	queryCondition=searchData;
	grid.load({ searchData : searchData });
}

/**点击回车查询
 * JSP:onkeydown="onKeyEnter"
 * 查询按钮id：searchButton
 * 火狐下不识别 event is not defined
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
function addWindow(a,b,c,width,height,isCommon){
	var w=400;
	var h=300;
	if(width)w=width;
	if(height)h=height;
	var className=c;
	c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
	var grid=mini.get("grid");
	var url="../"+b+"/"+c+"!add.action?id=" + null + "&show='true'";
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
function editWindow(a,b,c,width,height,isCommon){
	var grid=mini.get("grid");
	var rows = grid.getSelecteds();
	if(rows.length!=1){
		mini.alert("请选择一条记录!");
		return;
	}
	var row=rows[0];
	if(row.status && row.status==1){
		mini.alert( "该记录已锁定，请解锁以后再进行编辑！");
		return;
	}
	var w=400;
	var h=300;
	if(width)w=width;
	if(height)h=height;
	var className=c;
	c = c.replace(/([A-Z])/g,"_$1").toLowerCase();
	var url="../"+b+"/"+c+"!edit.action?id=" + row.id + "&show='true'";
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
 * 审核状态 flag 如果有审核状态，flag=1或flag=”通过“的数据才能激活
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
			if(r.flag && r.flag!=1 && r.flag!="通过"){
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
/**跳转审核页面(审核状态 flag 0未审核 1审核中 2审核通过 3审核不通过)
 * a:title
 * b:包名
 * c:类名
 * action方法名:check
 * action属性 isCheck="true"
 * 审核状态 flag flag=0或flag=”未审核“的数据才能审核
 */
function check(a,b,c) {
	var tabs = window.parent.mini.get("mainTabs");
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
	tab.url = "../"+b+"/"+c+"!check.action?id="+rows[0].id+"&show=false&isCheck=true";
	tabs.addTab(tab);
	tabs.activeTab(tab);
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
/**导出
 * a:包名
 * b:类名
 * action方法名:export
 * */
function exportExcel(a,b){
	var grid=mini.get("grid");
	var rows=grid.getSelecteds();
	if(rows.length==0){
		mini.alert("计费信息为空");
		return;
	}
	b = b.replace(/([A-Z])/g,"_$1").toLowerCase();
	var ids=[];
	for(var i=1;i<rows.length;i++){
		ids.push(rows[i].id);
	}
	var url="../"+a+"/"+b+"!export.action?ids="+ids;
	window.location.href=url;
}

/**时间格式化
 * 形式:yyyy-MM-dd'
 * */
function ondayRenderer(e) {
	var value = e.value;
	if(value != null){
	    value = new Date(value);
	    if (value) return mini.formatDate(value, 'yyyy-MM-dd');
	}else{
	    return "";
	}
}
/**时间格式化
 * 形式:yyyy年MM月dd日'
 * */
function ondayRenderer2(e) {
	var value = e.value;
	if(value != null){
		value = new Date(value);
		if (value) return mini.formatDate(value, 'yyyy年MM月dd日');
	}else{
		return "";
	}
}
/**
 * 长日期格式化(yyyy-MM-dd HH:mm:ss)
 * zhanghj
 **/
function onLongDateRenderer(e) {
    var value = e.value;
    if (value) return mini.formatDate(new Date(value), "yyyy-MM-dd HH:mm:ss");
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
/**导出当前页
 * a:包名
 * b:类名
 * action方法名:export
 * */
function exportExcel2(){
	var grid=mini.get("grid");
	//var rows=grid.getSelecteds();
	var rows=grid.getData();
	if(rows.length==0){
		mini.alert("计费信息为空");
		return;
	}
	var griddata=mini.encode(rows);//alert(griddata);return;
	document.getElementById("griddata").value = griddata;
	var excelForm = document.getElementById("excelForm");
	$("#searchData").val(queryCondition);
	excelForm.submit();
}

/**显示是否
 * status:{0：否，1：是}
 * */
function onIsOrNotRenderer(e) {
	var Suo = [{id:1,text:'是'},{id:0,text:'否'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){return g.text;}
	}
	return "";
}

/**显示收费方式
 * status:{0：现金，1：月结}
 * */
function onSfTypeRenderer(e) {
	var Suo = [{id:1,text:'月结'},{id:0,text:'现金'}];
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

/**显示单背 双背
 * status:{0：单背，1：双背}
 * */
function onFirstsdRenderer(e) {
	var Suo = [{id:1,text:'双背'},{id:0,text:'单背'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){return g.text;}
	}
	return "";
}


//数字靠右并且保留4位小数
function onRenderer(e){
	if(e.value==null)
		return null;
	return parseFloat(e.value).toFixed(2);
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
			
			if(r.flg && r.flg==1 || r.flg=="已分配"){
				mini.alert("已选择的数据已分配，不能作废！");
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


/**
 *性别选择
 ***/
var sexGenders = [{ id: 0, sex: '男' }, { id: 1, sex: '女'}, { id: 2, sex: '通用'}];
var sex = [{ id: 0, sex: '男' }, { id: 1, sex: '女'}];
var sex1=[{'id':'0','text':'男'},{'id':'1','text':'女'}];/**.**/

//性别
function sexRenderer(e) {
    for (var i = 0, l = sex1.length; i < l; i++) {
    var g = sex1[i];
    if (g.id == e.value){return g.text;}
    }
    return "";
}
/**
 * 检查类别
 **/
var jclb= [{id:0,text:"健康体检"},{id:1,text:"职业体检"},{id:2,text:"综合"},{id:3,text:"复查"}];
/**
 * 职业体检类别
 */
var zytjlb=[{'id':'0','text':'上岗前'},{'id':'1','text':'在岗期间'},{'id':'2','text':'离岗时'},{'id':'3','text':'离岗后'},{'id':'4','text':'应急'}];
/***
 * 餐序
 */
var xc = [{id:'0',text:'餐前'},{id:'1',text:'餐后'}];
/**
 * 是否启用
 */
var sfqy = [{id:'0',text:'否'},{id:'1',text:'是'}];

/**
 * 报告发送
 */
var SendType = [ {id : 1,text : '个检发'}, {id : 2,text : '快递发'}, 
                 {id : 3,text : '团检按个发'}, {id : 4,text : '团检发'},
                 {id : 5,text : '危急值个发'}, {id : 6,text : '危急值团发'} ];
/**
 * 报告流当前状态
 */
var reportStatus = [ {id : 0,text : '总检开始'},{id : 1,text : '总检完成'},
                     {id : 2,text : '报告已打印'},{id : 3,text : '一审通过'},
                     {id : 4,text : '一审不通过'},{id : 5,text : '二审通过'},
                     {id : 6,text : '二审不通过'},{id : 7,text : '终审通过'},
                     {id : 8,text : '终审不通过'},{id : 9,text : '报告已交接'},
                     {id : 10,text : '报告已通知'},{id : 11,text : '报告已领取'}];
function reportStatusRenderer(e) {
	for (var i = 0, l = reportStatus.length; i < l; i++) {
		var g = reportStatus[i];
		if (g.id == e.value){return g.text;}
	}
	return "";
}

/**导出
 * a:包名
 * b:类名
 * name:所有查询条件框的name字符串
 * attr:所有查询条件的属性名
 * action方法名:export
 * */
function exportExcelData(a, b, name, attr) {
    var grid=mini.get("grid");
    var rows = grid.getSelecteds();
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
    searchData=searchData+"}";
    window.location.href="../"+a+"/"+b+"!export.action?searchData="+searchData;
}

/**
 * 只能选择今天之前的日期
 */
function onDrawDate(e) {
    var date = e.date;
    var d = new Date();

    if (date.getTime() > d.getTime()) {
        e.allowSelect = false;
    }
}

/**
 * 判断浏览器内核
 * @returns {Boolean}
 */
function webBrowser() {
	var verTrident=ua.match(/Trident\D?\d+/i);
	var verIE=ua.match(/MSIE\D?\d+/i);

	if (verTrident != null || verIE != null) {
		return "IE";
	} else {
		return "!IE";
	}
}

/**
 * 公司性质
 */
var companyxz = [{id:0,text:'合资公司'},{id:1,text:'外包公司'}];
/**
 * 公司类型
 */
var companyType = [{id:0,text:'有限责任公司'},{id:1,text:'股份有限责任公司'},{id:2,text:'合资公司'}];
