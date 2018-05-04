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
    <title>缴费地区</title>
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
                    &nbsp;省：
                    <input class="mini-combobox" id="province" showNullItem="true" url="../basis/pay_areas!pjsonlist.action?pid=0"
                        onvaluechanged="onValueChanged" onenter="onKeyEnter" />
                    &nbsp;市：
                    <input class="mini-combobox" id="city" showNullItem="true" onenter="onKeyEnter" />
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('province,city','province,city')">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../basis/pay_areas!jsonlist.action"
            pagerSize="10" idField="id" allowResize="false" onrowdblclick="onRowDblclick">
            <div property="columns">
                <div type="checkcolumn" width="50"></div>
                <div field="id" name="id" id="id" width="100" headerAlign="center" align="center">id</div> 
                <div field="province"  name="province"  width="100" headerAlign="center" align="center">省</div>
                <div field="city"  name="city"  width="100" headerAlign="center" align="center">市</div>
                <div type="comboboxcolumn" field="state"  name="state"  width="100" headerAlign="center" align="center">状态
                    <input property="editor" class="mini-combobox" data="[{id:0,text:'无效'},{id:1,text:'有效'}]"/>
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOkSelected" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");

        function onRowDblclick(e) {
            onOkSelected();
        }

        function GetData() {
            var row = grid.getSelected();
            return row;
        }

        function onOkSelected() {
            var row = grid.getSelected();
            if (row) {
                CloseWindow("ok");
            } else {
                mini.alert("请选择一条记录");
                return;
            }
        }

        //////////////////////////////////
        function CloseWindow(action) {
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }
        function onCancel() {
            CloseWindow("cancel");
        }

        function onValueChanged(e) {
            var city = mini.get("city");
            city.setUrl("../basis/pay_areas!pjsonlist.action?pid="+e.value);
        }

    </script>
</body>
</html>