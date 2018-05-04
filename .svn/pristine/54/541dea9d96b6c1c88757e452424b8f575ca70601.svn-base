$(function() {
	mini.parse();
	var grid = mini.get("grid");
	// 如果有表格，隐藏id列
	if(grid){
		grid.hideColumn("id");
		// grid.on("cellcommitedit", onCellCommitEdit);
	}
	// 判断如果ID不为空
	var form = new mini.Form("form1");
	// 判断是否查看
	if(mini.get("flag") && mini.get("flag").getValue()=="false"){
		var fields = form.getFields();                
		for (var i = 0, l = fields.length; i < l; i++) {
			var c = fields[i];
			d=c.getId();
			if (c.setReadOnly) c.setReadOnly(true);     // 只读
			if (c.setIsValid) c.setIsValid(true);      // 去除错误提示
			if(d=="xgyfd"){
				c.setReadOnly(false); 
			}
			// if (c.addCls) c.addCls("asLabel"); //增加asLabel外观
		}
		mini.get("ok").setVisible(false);
		mini.get("cz").setVisible(false);
		// mini.get("grid").setUrl = "";
	   /*
		 * mini.get("add").setVisible(false);
		 * mini.get("remove").setVisible(false);
		 */
		$("#bar").hide();
		if(grid)grid.setAllowCellEdit(false);
	}
	
	var winW = $(window).height();

	win1 = $(".win1").height();
	win2 = $(".win2").height();
	win3 = $(".win3").height();
	win4 = $(".bottomInfo").height()==null?0:$(".bottomInfo").height();
	win5 = $("#bar").height();
/*
 * win6 = $(".win21").height()==null ? 0 : $(".win21").height(); win7 =
 * $(".win22").height()==null ? 0 : $(".win22").height(); win8 =
 * $(".win23").height()==null ? 0 : $(".win23").height(); win9 =
 * $("#fd2").height()==null ? 0 : $("#fd2").height(); win10 =
 * $("#fd3").height()==null ? 0 : $("#fd3").height();
 */
	if($("#bar").is(":hidden")){
		win5 = 0;
	}

	win = win1 + win2 + win3 + win4 + win5 /* + win6 + win7 + win8 +win9 +win10 */ + 57;
	cheight = winW - win - 8;
	cheight = cheight <= 200 ? 200 : cheight;

	$("#zsgd").css("height", cheight + "px");
	$(window).resize(function() {
		var winW2 = $(window).height();
		var cheight2 = winW2 - win;

		cheight2 = cheight2 <= 200 ? 200 : cheight2;
		$("#zsgd").css("height", cheight2 + "px");
	});

	
	// 子项自动生成5条
	function addload(){
		for ( var i = 0; i < 5; i++) {
				addRow();
		}
	}
	
	
	// 数字靠右并且保留4位小数
	function onRenderer(e){
		if(e.value==null)
			return null;
		return parseFloat(e.value).toFixed(4);
	}
});
/** 验证英文数字 */
function onEnglishAndNumberValidation(e) {
	if (e.isValid && e.value) {
		if (isEnglishAndNumber(e.value) == false) {
			e.errorText = "必须输入英文+数字";
			e.isValid = false;
		}
	}
}
/* 是否英文或数字 */
function isEnglishAndNumber(v) {
	var re = new RegExp("^[0-9a-zA-Z]+$");
	if (re.test(v)) return true;
	return false;
}
/**
 * 弹窗 btnEdit:this title:标题 url:弹窗路径 width:窗口宽度 默认400 height:窗口高度 默认300
 * property:点击确定后，要显示的属性的名称，默认为"text"
 * 
 * 传递到后台的参数 names:input的name columns:对应的Action属性名
 * 
 * 修改其他Input值 inputs 要修改的所有Input id attrs 对应的属性
 * 
 * 重置 gridId重置的gridid inputId重置的inputId
 */
function onButtonEdit(btnEdit,title,url,width,height,property,names,columns,inputs,attrs,gridId,inputId){
	var w=400;
	var h=300;
	if(width)w=width;
	if(height)h=height;
	if(names && columns){
		var nameArray=names.split(",");
		var columnArray=columns.split(",");
		for(var i=0;i<nameArray.length;i++){
			if(i==0){
				url=url+"?"+columnArray[0]+"="+mini.get(nameArray[0]).getValue();
			}else{
				url=url+"&"+columnArray[i]+"="+mini.get(nameArray[i]).getValue();
			}
		}
	}
	mini.open({
		url: url,
		showMaxButton: false,
		title: title,
		width: w,
		height: h,
		ondestroy: function (action) {
			if (action == "ok") {
				var iframe = this.getIFrameEl();
				var data = iframe.contentWindow.GetData();
				data = mini.clone(data);
				if (data) {
					if(property){
						btnEdit.setText(data[property]);
					}else{
						btnEdit.setText(data.text);
					}
					btnEdit.setValue(data.id);
					
					if(inputs && attrs){
						var inputsArray=inputs.split(",");
						var attrsArray=attrs.split(",");
						for(var i=0;i<inputsArray.length;i++){
							var button=mini.get(inputsArray[i]);
							button.setValue(data[attrsArray[i]]);
						}
					}
					if(gridId)mini.get(gridId).load();
					if(inputId){
						mini.get(inputId).setValue("");
						if(mini.get(inputId).setText)mini.get(inputId).setText("");
					}
				}
			}else{
				btnEdit.setValue("");
				btnEdit.setText("");
				
				if(inputs && attrs){
					var inputsArray=inputs.split(",");
					for(var i=0;i<inputsArray.length;i++){
						var button=mini.get(inputsArray[i]);
						button.setValue("");
					}
				}
				if(gridId)mini.get(gridId).load();
				if(inputId){
					mini.get(inputId).setValue("");
					if(mini.get(inputId).setText)mini.get(inputId).setText("");
				}
			}
		}
	});
}
/**
 * 提交(无列表) a:包名 b:类名 c:方法名 默认为saveOrUpdate isCommon:如果有值将调用BaseManageAction
 */
function onOk(a,b,c,isCommon){
	/*
	 * if(mini.get("indate")!=""&&mini.get("indate")!=null&&typeof(mini.get("indate"))!="undefined"){
	 * mini.get("indate").setMaxDate(new Date("9999-12-12")); }
	 */
	
	// 表单部分
	var form = new mini.Form("#form1");
	var formjson="";
	if(form){
		form.validate();
		if (form.isValid() == false)return;
		// 提交表单数据
		var formdata = form.getData();
		formjson = mini.encode(formdata); 
	}
	// 表格部分
	var grid=mini.get("grid");
	var gridjson="";
	var childPackage = a;
	if(grid){
		// 验证
		grid.validate();
		if (grid.isValid() == false) {
			gridError(grid);
			return;
		}
		var data = grid.getData();
		if(data.length<1){// 空行也算length
			mini.alert("详细信息为空，保存失败！");
			return;
		}
		for ( var i = 0, l = data.length; i < l; i++) {
			var row = data[i];
			if(row.id == ""||typeof(row.id)== "undefined"){
				row._state="added";
				grid.updateRow(row,row);
			}else{
				row._state="modified";
				grid.updateRow(row,row);
			}
		}
		var griddata = grid.getChanges();
		gridjson= mini.encode(griddata);
	}
	var method="saveOrUpdate";
	if(c)method=c;
	var className=b;
	b = b.replace(/([A-Z])/g,"_$1").toLowerCase();
	var url="../"+a+"/"+b+"!"+method+".action";
	if(isCommon)url="../basemanage/base_manage!saveOrUpdate.action?className="+className +"&childPackage=" + childPackage;
	
	window.parent.loading();
	$.ajax({
		url : url,
		type : "post",
		data : {
			formdata : formjson,
			griddata : gridjson
		},
		success : function(text) {
			window.parent.unmask();
			if (text == "success") {
				mini.showMessageBox({
					title: "提醒",
					width: 250,
					iconCls: "mini-messagebox-warning",
					buttons: ["ok"],
					message: "保存成功",
					callback: function (action) {
						document.location.reload();
						window.CloseOwnerWindow();
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

/** 关闭窗口 */
function CloseWindow(action) {
	if (window.CloseOwnerWindow)
		return window.CloseOwnerWindow(action);
	else
		window.close();
}
/**
 * 显示是否 status:{0：否，1：是}
 */
function onIsOrNotRenderer(e) {
	var Suo = [{id:1,text:'是'},{id:0,text:'否'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){return g.text;}
	}
	return "";
}
/** 重置按钮点击事件 */
function onReload() {
	document.location.reload();
}

/** 关闭按钮点击事件 */
function onCancel() {
	CloseWindow("cancel");
}

/** 增加行 */
function addRow() {
	var grid=mini.get("grid");
	var newRow = {};
	grid.selectAll(false);
	var leng = grid.getData().length;
	grid.addRow(newRow, leng);
	var row=grid.getRow(leng);
	grid.updateRow(row,row);
	grid.deselectAll (false);
}

/** 添加页面用到的删除行方法 */
function removeRow() {
	var grid=mini.get("grid");
	var rows = grid.getSelecteds();
	if (rows.length > 0) {
		grid.removeRows(rows, false);
	}else{
		mini.alert("请选择需要删除的记录!");
	}
}
/**
 * 审核通过 a：包名 b：类名 action方法名:approve 通过按钮Id pass 不通过按钮id vote
 */
function approved(a,b){
	mini.confirm("确定审核通过吗？","提醒",function(action){
		if(action=="ok"){
			b = b.replace(/([A-Z])/g,"_$1").toLowerCase();
			window.parent.loading();
			$.ajax({
				url : "../"+a+"/"+b+"!approve.action?id="+mini.get("id").getValue(),
				type:'post',
				success: function (text) {
					window.parent.unmask();
					if(text=="success"){
						mini.get("pass").setVisible(false);
						mini.get("vote").setVisible(false);
						mini.alert("审核成功！");
						// document.location.reload();
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
/**
 * 审核不通过 a：包名 b：类名 action方法名:unApprove 传到后台的值：{opinion:审核意见 , id}
 */
function unApproved(a,b){
	var id = mini.get("id").getValue();
	b = b.replace(/([A-Z])/g,"_$1").toLowerCase();
	// 审核意见弹窗
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
							data:{id:mini.get("id").getValue(),opinion:data},
							success: function (text) {
								window.parent.unmask();
								if(text=="success"){
									mini.get("pass").setVisible(false);
									mini.get("vote").setVisible(false);
									mini.alert("驳回成功！");
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
}
/**
 * 提交(单表、一次增加多条、表单数据全部塞到了griddata里) a:包名 b:类名 c:方法名 默认为saveOrUpdate
 */
function onOkGx(a,b,c){
	/*
	 * if(mini.get("indate")!=""&&mini.get("indate")!=null&&typeof(mini.get("indate"))!="undefined"){
	 * mini.get("indate").setMaxDate(new Date("9999-12-12")); }
	 */
	
	// 表单部分
	var form = new mini.Form("#form1");
	var formdata;
	if(form){
		form.validate();
		if (form.isValid() == false)return;
		// 提交表单数据
		formdata = form.getData();
	}
	
	// 表格部分
	var grid=mini.get("grid");
	var gridjson="";
	if(grid){
		// 验证
		grid.validate();
		if (grid.isValid() == false) {
			var error = grid.getCellErrors()[0];
			grid.beginEditCell(error.record, error.column);
			return;
		}
		var data = grid.getData();
		if(data.length<1){// 空行也算length
			mini.alert("详细信息为空，保存失败！");
			return;
		}
		for ( var i = 0, l = data.length; i < l; i++) {
			var row = data[i];
			if(row.id == ""||typeof(row.id)== "undefined"){
				row._state="added";
				grid.updateRow(row,row);
			}else{
				row._state="modified";
				grid.updateRow(row,row);
			}
		}
		var griddata = grid.getChanges();
		if(formdata){
			for(var i=0;i<griddata.length;i++){
				for(var attr in formdata){
					if(attr!="pagesize" && attr!="show" && attr!="id")griddata[i][attr]=formdata[attr];
				}
			}
		}
		gridjson= mini.encode(griddata);
	}
	
	var method="saveOrUpdate";
	if(c)method=c;
	var className=b;
	b = b.replace(/([A-Z])/g,"_$1").toLowerCase();
	var url="../"+a+"/"+b+"!"+method+".action";
	window.parent.loading();
	$.ajax({
		url : url,
		type : "post",
		data : {
			griddata : gridjson
		},
		success : function(text) {
			window.parent.unmask();
			if (text == "success") {
				mini.showMessageBox({
					title: "提醒",
					width: 250,
					iconCls: "mini-messagebox-warning",
					buttons: ["ok"],
					message: "保存成功",
					callback: function (action) {
						document.location.reload();
						window.CloseOwnerWindow();
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
/**
 * 提交单元格编辑数据前激发 grid 添加 oncellcommitedit="onCellCommitEdit" 在input 添加验证条件 在Input
 * 和div 上都需要加验证条件
 */
function onCellCommitEdit(e) {
	var editor = e.editor;
	var sender = e.sender;
	editor.validate();
	if (editor.isValid() == false) {
		e.cancel = true;
		// 提示错误信息
		mini.alert(editor.getErrorText(),"",function(){
			// 获取焦点
			sender.setCurrentCell([e.rowIndex,e.column._index+1]);
			sender.beginEditCell();
			return;
		});
	}
}
// 将页面输入的字母转换成大写
function onWoValueChanged(e){
	mini.get(e.source.id).setValue(e.value.toUpperCase());
}
/**
 * grid弹窗 btnEdit:this title:标题 url:弹窗路径 width:窗口宽度 默认400 height:窗口高度 默认300
 * 
 * colNames需要设置其他位置值的列名 colValues对应colNames的弹窗返回数据的属性名
 * property:点击确定后，设置在当前位置的属性名，默认为"text"
 * 
 * names:input的name columns:对应的Action属性名
 */
function onGridButtonEdit(btnEdit,title,url,width,height,colNames,colValues,property,names,columns){
	var grid=mini.get("grid");
	var row;
	// if(grid)row=grid.getSelected();
	if(grid){
		var cell=grid.getCurrentCell();
		var _id=cell["0"]["_id"];
		row=grid.findRow(function(row){
				if(row._id==_id)return true;
			});
	}
	
	var w=400;
	var h=300;
	if(width)w=width;
	if(height)h=height;
	if(names && columns){
		var nameArray=names.split(",");
		var columnArray=columns.split(",");
		for(var i=0;i<nameArray.length;i++){
			if(i==0){
				url=url+"?"+columnArray[0]+"="+mini.get(nameArray[0]).getValue();
			}else{
				url=url+"&"+columnArray[i]+"="+mini.get(nameArray[i]).getValue();
			}
		}
	}
	mini.open({
		url: url,
		showMaxButton: false,
		title: title,
		width: w,
		height: h,
		ondestroy: function (action) {
			if (action == "ok") {
				var iframe = this.getIFrameEl();
				var data = iframe.contentWindow.GetData2();
				data = mini.clone(data);
				if (data) {
					 for(var i = 0 ; i<data.length;i++){
						 if(i==0){
							 if(property){
									btnEdit.setText(data[i][property]);
									btnEdit.setValue(data[i][property]);
								}else{
									btnEdit.setText(data[i].text);
									btnEdit.setValue(data[i].text);
								}
								
								if(colNames && colValues){
									var names=colNames.split(",");
									var values=colValues.split(",");
									for(var r=0;r<names.length;r++){
										row[names[r]]=data[i][values[r]];
									}
								}
						 }else{
							 if(colNames && colValues){
		    						var names=colNames.split(",");
		    						var values=colValues.split(",");
		    						var row2={};
		    						for(var r=0;r<names.length;r++){
		    							var name = names[r]
		    							row2[name] = data[i][values[r]];
		    						}
		    						grid.addRow(row2,row2)
		    					}
						 }
    					
                     }
				}
			}else{
				btnEdit.setValue("");
				btnEdit.setText("");
				if(colNames && colValues){
					var names=colNames.split(",");
					var values=colValues.split(",");
					for(var i=0;i<names.length;i++){
						row[names[i]]="";
					}
				}
			}
			grid.updateRow(row, row);
		}
	});
}
/** autocomplete */
function onAutocompleteValueChanged(e){
	var item = e.selected;
	var id=e.sender.id;
	if(!item){
		mini.get(id).setValue("");
		mini.get(id).setText("");
	}
}

/**
 * 显示校验标志 status:{0：未校验，1：已校验}
 */
function onCheckMarkRenderer(e) {
	var Suo = [{id:1,text:'已校验'},{id:0,text:'未校验'}];
	for (var i = 0, l = Suo.length; i < l; i++) {
		var g = Suo[i];
		if (g.id == e.value){return g.text;}
	}
	return "";
}
/** 电话验证 */
function onIsTellValidation(e){
	if (e.isValid && e.value) {
		var length = e.value.length;   
		var mobile = /^(((13[0-9]{1}))+\d{8})$/;   
		var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
		if (!(tel.test(e.value)) || (length==11 && mobile.test(e.value))) {
			e.errorText = "请正确填写您的联系方式";
			e.isValid = false;
		}
	}
}
 
/*******************************************************************************
 * 数字靠右并且保留4位小数
 ******************************************************************************/
function onRenderer(e){
	if(e.value==null)
		return null;
	return parseFloat(e.value).toFixed(4);
}
/*******************************************************************************
 * 失去焦点后去后台获取输入码 a:页面需要转换的输入框name b:返回到输入框的name
 ******************************************************************************/
function onblur(a,b) {
	
	var inputName = mini.get(a).getValue();
	if (inputName == "") {
				return;
	}
	$.ajax({
		url : "../basemanage/base_manage!getBlurData.action",
		type : "post",
		data : { inputName : inputName },
		success : function(text) {
			var inputCode = mini.get(b);
			inputCode.setValue(text)
		}
	});
}
/*******************************************************************************
 * grid 错误提示
 ******************************************************************************/
   function gridError(grid){
	   var error = grid.getCellErrors()[0];
       var head = error.column.header.replace(/[\r\n]/g,"").replace(/[ ]/g,"");
       var errorText = error.errorText;
	   mini.showMessageBox({
            title: "提醒",
            iconCls: "mini-messagebox-warning",
            buttons: ["ok"],
            message: head+":"+errorText+",请修改后重新操作!",
            callback: function (action) {
                 grid.setSelected(error.record);
                 grid.beginEditCell(error.record, error.column);
                 return;
              }
            });
   }

/*******************************************************************************
 * 性别选择
 ******************************************************************************/
var sexGenders = [{ id: 0, sex: '男' }, { id: 1, sex: '女'}, { id: 2, sex: '通用'}];
var sex = [{ id: 0, sex: '男' }, { id: 1, sex: '女'}];
/**
 * 检查类别
 */
var jclb= [{id:0,text:"健康体检"},{id:1,text:"职业体检"},{id:2,text:"综合"},{id:3,text:"复查"}];
/**
 * 职业体检类别
 */
var zytjlb=[{'id':'0','text':'上岗前'},{'id':'1','text':'在岗期间'},{'id':'2','text':'离岗时'},{'id':'3','text':'离岗后'},{'id':'4','text':'应急'}];
/**
 * 文化程度
 */
var cultural = [{id:0, text:"小学"},{id:1, text:"初中"},{id:2, text:"技校"},{id:3, text:"职高"},{id:4, text:"高中"},{id:5, text:"中专"},{id:6, text:"大专"},{id:7, text:"大学"},{id:8, text:"研究生以上"}];

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
