/**
 * show弹窗
 */
$(function(){
	mini.parse();
	var grid = mini.get("grid");
	if(grid){
		grid.hideColumn("id");
		grid.load();
	}
});

function GetData() {
	var grid = mini.get("grid");
	var row = grid.getSelected();
	return row;
}
function GetData2() {
	var grid = mini.get("grid");
	var rows = grid.getSelecteds();
	return rows;
}
function onRowDblClick(e) {
	onOk();
}
function CloseWindow(action) {
	if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
	else window.close();
}
function onOk() {
	CloseWindow("ok");
}
function onCancel() {
	CloseWindow("cancel");
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
	searchData=searchData+"}";
	grid.load({ searchData : searchData });
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
/**
 * 获取所有选中行
 */
function getDatas(){
	var grid = mini.get("grid");
	var rows = grid.getSelecteds();
	return rows;
}

/**时间格式化
 * 形式:yyyy-MM-dd HH:mm'
 * */
function onMinuteRenderer(e) {
	var value = e.value;
	if(value != null){
	    value = new Date(value);
	    if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm');
	}else{
	    return "";
	}
}

/**
 * 短日期格式化(yyyy-MM-dd)
 * zhanghj
 **/
function onShortDateRenderer(e) {
    var value = e.value;
    if (value) return mini.formatDate(new Date(value), "yyyy-MM-dd");
    return "";
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