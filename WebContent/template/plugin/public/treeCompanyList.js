/**
  * 获取左侧树所有的公司
  *
  */

$(function() {
	var tree=mini.get("deptree");
	tree.on("nodeselect", function (e) {
        var node = tree.getSelectedNode();
        if (node) {
            var dId = "";
            dId = deepTree(dId, tree, node);

            // 获取节点的所有父节点
            var allParentNode = tree.getAncestors(node);
            var need = [];
            for (var i=0; i < allParentNode.length; i++) {
                need.push(allParentNode[i].branchId);
            }
            if (node.flg == 0)
                need.push(node.branchId);
            var fatherIds = need.join(",");
            if (callback != undefined && callback != null) callback(dId, node, fatherIds);
        } 
    });
});

function search(name,attr){
	var grid=mini.get("grid");
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
			ids = deepTree(ids,tree,node);
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

function deepTree(ids, tree, node) {
    var selectNode = tree.getSelectedNode(node);
    // 是公司
    if (node.flg == 0) {
        // 当前公司ID
        if (ids == "") ids = node.branchId;
        else ids += "," + node.branchId;
    } else {
        if (ids == "") ids = node.pid;
        else ids += "," + node.pid;
        node = tree.getParentNode(node);
    }

    var list = tree.getAllChildNodes(node);
    for (var i = 0, l = list.length; i < l; i++) {
        if(list[i].flg==0){ 
            ids += "," + list[i].id; 
        }
    }

    return ids;
}