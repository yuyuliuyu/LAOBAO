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
<title>组织列表</title>
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
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div  class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;" name="form1"> 
        <span style="padding-left:5px;">组织机构：</span>
    </div>
    <div class="mini-fit">
        <ul id="tree" class="mini-tree" url="../basis/branch!getTreeshowByUser.action" style="width:100%;" expandOnNodeClick="true" expandOnLoad="false"
            showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" onnodeclick="onNodeclick"  >        
        </ul>
    </div>
    
    <script type="text/javascript">
        mini.parse();
        var tree=mini.get("tree");
        tree.collapseAll();
        
		
	 //////////////////////////////////////////////
        // 节点点击事件
        function onNodeclick(e) {
            var node = e.node;
            var staffName = "";
            var positionName = "";
            var depId = "";
            if (node.flg == "3") {// 岗位
                positionName = node.name;
                depId = node.pid;
            } else if (node.flg =="4") {// 员工
                staffName = node.name;
                var pNode = tree.getParentNode(node);
                positionName = pNode.name;
                depId = pNode.pid;
            } else return;
            // 权限查找
            $.ajax({
                url: "${base}system/backend_menu!getAuthMenu.action",
                type:"POST",
                success: function(text) {
                    if (text !="" && text.length > 0) {
                        var s = text.split(",");
                        var authMenu = $("#authMenu");
                        if (s.indexOf("2") == -1) {
                            mini.alert("没有权限查看人员信息");
                        } else {
                            var tabs = window.parent.parent.mini.get("mainTabs");
                            var id = "tab$员工管理";
                            var tab = tabs.getTab(id);
                            if (!tab) {
                                tab = {};
                                tab.name = id;
                                tab.title = "员工管理";
                                tab.showCloseButton = true;
                                tab.url = "../personnel/personnel!list.action?depId="+depId+"&positionName="+positionName+"&staffName=" + staffName;
                                tabs.addTab(tab);
                            } else tab.url = "../personnel/personnel!list.action?depId="+depId+"&positionName="+positionName+"&staffName=" + staffName;
                            tabs.activeTab(tab);
                            tabs.reloadTab(tab);
                        }
                    }
                }
            });
        }
     ////////////////////////////////////////////// 
    </script>
</body>
</html>