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
<title>统计列表</title>
<style type="text/css">
</style>
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../template/plugin/public/cent_dep.js"></script>
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
            <div size="240" showCollapseButton="true">
                <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                    <span style="padding-left:5px;">职务族：</span>
                </div>
                <div class="mini-fit" style=" border-top:0;">
                    <ul id="tree1" class="mini-tree" url="../post/post!treeList.action" style="width:100%;"onNodeclick="onNodeclick(this)"
                        showTreeIcon="true" textField="postName" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                    </ul>
                </div>
            </div>
            <!-- 分中心树  end -->
            <div showCollapseButton="false">
            <div id="tabs1" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;" 
					onactivechanged="onTabsActiveChanged" bodyStyle="padding:0;border:0;">
					<div title="职务">
						<iframe id="framezwu" frameborder="0" name="mainframe" style="width:100%;height:100%;" border="0"></iframe>
					</div>
					<div title="职位">
						<iframe id="framezwi" frameborder="0" name="framedzda" style="width:100%;height:100%;" border="0"></iframe>
					</div>
					<div title="人员">
						<iframe id="framery" frameborder="0" name="framejyjl" style="width:100%;height:100%;" border="0"></iframe>
					</div>
			</div>
			</div>
	</div>
</div>
    
    <script type="text/javascript">
        mini.parse();
        var tree=mini.get("tree1");
        function onTabsActiveChanged(e) {
            var tabs = e.sender;
            var tab = tabs.getActiveTab();
            if(tab.title == "职务"){
            	var iframe = document.getElementById("framezwu");
            	iframe.src = "../post/tong_ji!zwulist.action"; 
			    
            } else if (tab.title == "职位"){
            	var iframe = document.getElementById("framezwi");
            	iframe.src = "../post/tong_ji!zwilist.action"; 
			    
            } else if (tab.title == "人员"){
            	var iframe = document.getElementById("framery");
            	iframe.src = "../post/tong_ji!rylist.action"; 
			    
            }
        }
        function onNodeclick (e){
        	var tabs = mini.get("tabs1");
        	debugger;
            var tab = tabs.getActiveTab();
            var node = tree.getSelectedNode();
	  		if(node){
	  			if(tab.title == "职务"){
	            	var iframe = document.getElementById("framezwu");
	            	iframe.src = "../post/tong_ji!zwulist.action?pid="+node.id; 
            	}else if (tab.title == "职位"){
            		var iframe = document.getElementById("framezwi");
            		iframe.src = "../post/tong_ji!zwilist.action?pid="+node.id; 
	            } else if (tab.title == "人员"){
	            	var iframe = document.getElementById("framery");
	            	iframe.src = "../post/tong_ji!rylist.action?pid="+node.id; 
	            }
	  		}
        } 
    </script>
</body>
</html>