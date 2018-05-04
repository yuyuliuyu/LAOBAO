<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>选择分中心</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="${base}template/miniui/boot.js" type="text/javascript"></script>
    <link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    html, body
    {
        font-size:12px;
        padding:0;
        margin:0;
        border:0;
        height:100%;
        overflow:hidden;
    }
    </style>
</head>
<body>
    <div class="mini-fit">
        <ul id="tree1" class="mini-tree" style="width:100%;height:100%;" 
            showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false"
            expandOnLoad="true" onnodedblclick="onNodeDblClick" expandOnDblClick="false">
        </ul>
    </div>
    
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" 
         borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="mini-button" style="width:60px;" onclick="onOk()" iconCls="icon-save">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" style="width:60px;" onclick="onCancel()" iconCls="icon-close">取消</a>
    </div>
    
    <script type="text/javascript">
        mini.parse();
        
        var tree = mini.get("tree1");
        tree.load("../basis/branch!getTreeListByUser.action");
        
        
        function GetData() {
            var node = tree.getSelectedNode();
            return node;
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
        
        /////////////////////////////////////////////////
        function CloseWindow(action) {
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }
        
        function onOk() {
            /* var node = tree.getSelectedNode();
            if (node && tree.isLeaf(node) == false) {
                alert("不能选中父节点");
                return;
            } */
            CloseWindow("ok");
        }
        
        function onCancel() {
            CloseWindow("cancel");
        }
    </script>
    </body>
</html>