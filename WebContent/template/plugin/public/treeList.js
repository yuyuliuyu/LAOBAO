//////////////带仓库树的js//////////////////////
/**仓库树宽度160*/
/**选择总仓库   不显示数据*/
$(function(){ 
	mini.parse();	
	var grid = mini.get("grid");
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
					grid.load({ searchData:"{\"depId\":\""+ids+"\"}" });
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
		// ids +=  "," +list[0].id;
		//alert(ids +"&&&&&"+i);
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
