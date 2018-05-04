<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path;
    pageContext.setAttribute("base", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>薪资类别树</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit" id="form1">
        <ul id="tree1" class="mini-tree" style="width:100%;height:100%;" 
            showTreeIcon="false" textField="name" idField="id" parentField="pid" resultAsTree="false"
            expandOnLoad="true" onnodedblclick="onNodeDblClick" expandOnDblClick="false">
        </ul>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;" iconCls="icon-save">确定</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var tree = mini.get("tree1");
        tree.load("../salary/salary_items!getItemsTypeListsData.action?depId=${depId}");
        
        function GetData() {
            var node = tree.getSelectedNode();
            return node;
        }
        
        function GetSize(){
            var node = tree.getSelectedNode();
            return tree.getAncestors ( node ).length;
        }
        
        function search() {
            var key = mini.get("key").getValue();
            if(key == ""){
                tree.clearFilter();
            }else{
                key = key.toLowerCase();
                tree.filter(function (node) {
                    var text = node.text ? node.text.toLowerCase() : "";
                    if (text.indexOf(key) != -1) {
                        return true;
                    }
                });
            }
        }
        
        function onKeyEnter(e) {
            search();
        }
        
        function onNodeDblClick(e) {
            onOk();
        }
        
        //////////////////////////////////
        function CloseWindow(action) {
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }
        
        function onOk() {
            var node = tree.getSelectedNode();
            if (!node) {
                mini.alert("请选择薪资类别");
                return;
            }
            if (node.pid == "-1") {
                mini.alert("请选择子节点");
                return;
            }
            CloseWindow("ok");
        }
        function onCancel() {
            CloseWindow("cancel");
        }
    </script>
</body>
</html>