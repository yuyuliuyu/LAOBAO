<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>组织机构变更列表</title>
<style type="text/css">
</style>
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
<script src="${base}/template/plugin/public/treeList.js" type="text/javascript"></script>
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
<div class="mini-fit">
	<div class="mini-splitter" style="width:100%;height:100%;">
			<!-- 分中心树 start -->
            <div size="20%" showCollapseButton="true">
            	<input type="hidden" value="${sessionScope.depId}" id="depId" />
                <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                    <span style="padding-left:5px;">组织机构：</span>
                </div>
                <div class="mini-fit" style=" border-top:0;">
                    <ul id="deptree" class="mini-tree" url="../basis/branch!treeList.action" style="width:100%;height:100%;"
                        showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                    </ul>
                </div>
            </div>
            <!-- 分中心树  end -->
            <div showCollapseButton="false">
	            <div class="mini-toolbar" style="padding:0px;border-top:0;">
	       			 <table style="width:100%;">
	           			 <tr>
			                <td style="width:100%;">
			                    <a class="mini-menubutton" iconCls="icon-edit" menu="#compMenu">公司变更</a>
			                    <ul id="compMenu" class="mini-menu" style="display:none;">
								    <li iconCls="icon-addnew" onclick="comChange(0)">申请设立</li>
								    <li iconCls="icon-edit" onclick="comChange(1)">申请调整</li>
								    <li iconCls="icon-reload" onclick="comChange(2)">申请转移</li>
								    <li iconCls="icon-cancel" onclick="comChange(3)">申请撤销</li>
							    </ul>
			                    <a class="mini-menubutton" iconCls="icon-edit" menu="#deptMenu">部门变更</a>
			                    <ul id="deptMenu" class="mini-menu" style="display:none;">
								    <li iconCls="icon-addnew" onclick="deptChange(0)">申请设立</li>
								    <li iconCls="icon-edit" onclick="deptChange(1)">申请调整</li>
								    <li iconCls="icon-reload" onclick="deptChange(2)">申请转移</li>
								    <li iconCls="icon-cancel" onclick="deptChange(3)">申请撤销</li>
								    <li iconCls="icon-goto" onclick="deptChange(4)">申请合并</li>
							    </ul>
			                    <a class="mini-button" iconCls="icon-upload" onclick="changeSubmit()">提交</a>
			                </td>
	            		 </tr>
	        		</table>
	    		</div>
	    		<div id="grid" class="mini-treegrid" style="width:100%;height:94%;"
		              showTreeIcon="true" url="../basis/branch!depData.action"
		             treeColumn="name" idField="did" parentField="pid"
		             resultAsTree="false" allowResize="false" expandOnLoad="true">
             
		                <div property="columns">
	                    
	                         <div type="checkcolumn">选择</div>
	                        <div type="indexcolumn" headerAlign="center">序列</div>
	                        <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">分中心部门id</div>
	                        <div field="cid" id="cid" width="80" name="cid" headerAlign="center" align="center" class="mini-hidden">分中心id</div>
	                        <div field="pid" id="pid" width="80" name="pid" headerAlign="center" align="center" class="mini-hidden"></div>
	                        <div field="did" id="did" width="80" name="did" headerAlign="center" align="center" class="mini-hidden"></div>
	                        <div field="srm" id ="srm" name = "srm" width="100" headerAlign="center" align="center" >变更机构</div>
	                        <div field="descript" id ="descript" name = "descript"width="100" headerAlign="center" align="center">变更类型</div>
	                        <div field="isgnks" id ="isgnks" name = "isgnks" width="100" headerAlign="center" align="center" renderer ="onIsgnks">申请状态</div>
	                        <div field="xh" id ="xh" name = "xh" width="100" headerAlign="center" align="center" dateFormat="yyyy-MM-dd HH:mm:ss">生效日期</div>
	                        <div field="sjbggs" id ="sjbggs" name = "sjbggs" width="100" headerAlign="center" align="center" >申请单位</div>
	                        
	                    </div>
       		 	</div>
            </div>
	</div>

</div>
    
    <script type="text/javascript">
        mini.parse();
        
        var grid = mini.get("grid");
        grid.hideColumn("id");
        grid.hideColumn("pid");
        grid.hideColumn("cid");
        grid.hideColumn("did");
        
        
        var tree = mini.get("deptree");
        /* tree.on("nodeselect", function (e) {
            var node = tree.getSelectedNode();
            if (node) {
				grid.setUrl("../basis/branch!depData.action");
                grid.load({cid:node.id});
            	
            } 
        }); */
        //////////////////////////////////////////////
        function add() {
        	var node = tree.getSelectedNode();
        	
        	if(node){
        		var cid = node.id;
    			 mini.open({
    	                url : "../basis/branch!depAdd.action?cid="+cid,
    	                title : "新增部门",
    	                width : 770,
    	                height : 540,
    	                /* allowResize : false, */
    	                onload : function() {
    	                },
    	                ondestroy : function(action) {
    	                    grid.reload();
    	                }
    	            });
        	}else{
        		mini.alert("请先选择分中心!");
        		return;
        	}
           
        }
        
        //////////////////////////////////////////////
        function edit() {
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url : "../basis/branch!depEdit.action?cid="+row.cid+"&id=" + row.id + "&did=" + row.did,
                    title : "编辑部门",
                    width : 770,
                    height : 540,
                    /* allowResize : false, */
                    onload : function() {
                    },
                    ondestroy : function(action) {
                        grid.reload();
                    }
                });
            } else {
                mini.alert("请选中一条记录！");
            }
        }
        //////////////////////////////////////////////
        function remove() {
            var row = grid.getSelected();
            
            if (row) {
                var list = grid.getAllChildNodes( grid.getSelectedNode( ));
                var ids = row.id;
                var cid = row.cid;
                if (grid.hasChildren(grid.getSelectedNode())) {
                	mini.showMessageBox({
                		title:"确定吗？",
                		message:"该部门下带有下级部门，您确定继续执行吗？",
                		buttons: ["ok", "no"],
                		callback: function(action){
                			if(action =="ok"){
                				 for(var i=0;i<list.length;i++){
                                     ids = ids +","+list[i].id;
                                 }
                				 zhixing(ids,cid);
                			}else{
                				return;
                			}
                		}
                	});
                    
                }else{
                	zhixing(ids,cid);
                }
            } else {
                 mini.alert("请选中一条记录！");
            }
        }
        
        function zhixing(ids,cid){
        	mini.confirm("您确定要删除该信息吗？","提醒",function(action){
                if(action=="ok"){
                    $.ajax({
                        url : "../basis/branch!depRemove.action?id=" + ids+"&cid="+cid,
                        type:'post',
                        success: function (text) {
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
        
        /* function search() {
            var key = mini.get("key").getValue();
            grid.load({
                key : key,searchBy:"name"
            });
        } */
        
        function onKeyEnter(e) {
            search();
        }
    	/**部门变更*/
    	function deptChange(e){
    	
    		alert(e);
    	}
    	/**公司变更*/
    	function comChange(e){
    		var node = tree.getSelectedNode();
			var nodeId = "";
			if(node){					
				nodeId = node.id;
				var flg=node.flg;
				if(flg!="0"&&flg!=0){
					mini.alert("请选择公司节点");
					return;
				}
			}
			
			mini.open({
					url:"../branch/com_change!add.action?id="+nodeId,
					title:"公司变更",
					width:1000,height:750,
					resizable:true,
					onload : function() {
	                },
	                ondestroy : function(action) {
	                	 document.location.reload();
	                }
				});
    	}

    </script>
</body>
</html>