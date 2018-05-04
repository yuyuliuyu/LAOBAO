/**科室js*/
var width='1298px';
var height='640px';
/** 增加行 */
function addRow(gridId) {
	var grid=mini.get(gridId);
	var newRow = {};
	grid.selectAll(false);
	var leng = grid.getData().length;
	grid.addRow(newRow, leng);
	var row=grid.getRow(leng);
	grid.updateRow(row,row);
	grid.deselectAll (false);
}
/**删除行*/
function removeRow(gridId) {
	var grid=mini.get(gridId);
	var rows = grid.getSelecteds();
	if (rows.length > 0) {
		grid.removeRows(rows, false);
	}else{
		mini.alert("请选择需要删除的记录!");
	}
}
/**校验*/
function onCellCommitEdit(e) {
	var editor = e.editor;
	var sender = e.sender;
	if(editor){
		editor.validate();
		if (editor.isValid() == false) {
			e.cancel = true;
			mini.alert(editor.getErrorText(),"",function(){
				sender.setCurrentCell([e.rowIndex,e.column._index+1]);
				sender.beginEditCell();
				return;
			});
		}
	}
}
/**查看科室 var url = "../abteilung/division!department.action";**/
function opendepartment() {
	if(!patientcode){
		mini.alert("请先读卡,再执行后续操作!");
		return;
	}
	var url = "../totalinspecti/health_total!common.action?patientno="
			+ patientcode;
	mini.open({
		url : url,
		title : "查看科室",
		width : width,
		height : height,
		onload : function() {
		},
		ondestroy : function(action) {
		}
	});
}
/**查看档案**/
function openarchive() {
	if(!patientcode){
		mini.alert("请先读卡,再执行后续操作!");
		return;
	}
	var url = "../abteilung/division!archive.action?inputCode="+patientcode;
	mini.open({
		url : url,
		title : "查看档案",
		width : width,
		height : height,
		onload : function() {
		},
		ondestroy : function(action) {
			grid.reload();
		}
	});
}
/**回车查询*/
function onKeyEnter(){
	if((event.keyCode || event.which) == 13){
		$("#searchButtonRank").click();
	}
}
/**体检者列表查询*/
function searchByTime(name,attr,gridId){
	var grid_in=mini.get(gridId);
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
	grid_in.load({ searchData : searchData });
}
/**一般检查置换下一步时用回车键不用制表*/
function onFormEnter(e,formId){
	var form=new mini.Form(formId);
	var fields=form.getFields();
	var id=e.id;
	for(var i=0,l=fields.length;i<l-1;i++){
		if(fields[i].id==id){
			for(var j=i+1;j<l;j++){
				if(fields[j].enabled==true){
					fields[j].focus();
					break;
				}
			}
			break;
		}
	}
}
/**********************validation*****************************/
/**检查人验证*/
function onRummagerValidation(e){
	if (e.isValid) {
		if (!e.sender.getText()) {
			e.errorText = "必填!";
			e.isValid = false;
		}
	}
}
/**********************renderer******************************/
var Gender = [ {
	id : 0,
	text : '男'
}, {
	id : 1,
	text : '女'
} ];

function onGenderRenderer(e) {
	for (i = 0, l = Gender.length; i < l; i++) {
		var g = Gender[i];
		if (g.id == e.value)
			return g.text;
	}
	return "";
}

var Tjzt = [ {
	id : 1,
	text : '未审核'
}, {
	id : 0,
	text : '审核'
}, {
	id : 2,
	text : '反审核'
} ];

function GenderRenderer(e) {
	for (i = 0, l = Tjzt.length; i < l; i++) {
		var f = Tjzt[i];
		if (f.id == e.value)
			return f.text;
	}
	return "";
}
/**体检者类型*/
var examtype = [ {
	id : 0,
	text : '健康体检'
}, {
	id : 1,
	text : '职业体检'
}, {
	id : 2,
	text : '综合'
} ];

function onExamtypeRenderer(e) {
	for (i = 0, l = examtype.length; i < l; i++) {
		var g = examtype[i];
		if (g.id == e.value)
			return g.text;
	}
	return "";
}