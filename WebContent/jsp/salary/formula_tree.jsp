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
    <title>选择公式</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="${base}template/miniui/boot.js" type="text/javascript"></script>
    <link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css" />
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
    <!--引入皮肤样式-->
    <link href="${base}/template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div class="mini-fit">
        <ul id="tree1" class="mini-tree" style="width:100%;height:100%;" 
            showTreeIcon="true" textField="method" idField="id" parentField="pid" resultAsTree="false"
            expandOnLoad="false" onnodedblclick="onNodeDblClick" expandOnDblClick="false">
        </ul>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" id="ok" onclick="onOk" style="width:60px;margin-right:20px;" iconCls="icon-save">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>
</body>
</html>

<script type="text/javascript">
    mini.parse();
    var tree = mini.get("tree1");
    tree.load("../salary/formula!getFullFormulaListData.action");
    
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