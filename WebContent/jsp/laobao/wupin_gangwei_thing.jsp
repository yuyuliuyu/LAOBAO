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
    <title>物品列表</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    &nbsp;物品名称：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('wpmc','wpmc')">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../laobao/wu_pin!json.action"
            pagerSize="20" showPager="true" idField="id" allowResize="false" borderStyle="border-center:0px;border-right:0px;">
            <div property="columns">
                <div type="checkcolumn" width="30">选择</div>
                <div field="id" id="id" width="20" name="id" class="mini-hidden"></div>
                <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                <div field="wpmc" name="wpmc" width="100" headerAlign="center" align="center">物品名称</div>
                <div field="ggxh" name="ggxh" width="100" headerAlign="center" align="center">规格型号</div>
                <div field="jldw" name="jldw" width="100" headerAlign="center" align="center">计量单位</div>
                <div field="gys" name="gys" width="100" headerAlign="center" align="center">供应商</div>
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;" iconCls="icon-save">确定</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        
        function GetData() {
            var node = grid.getSelected();
            return node;
        }

        function onKeyEnter(e) {
        	$("#searchButton").click();
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
            var node = grid.getSelected();
            if (!node) {
                mini.alert("请选择公司");
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