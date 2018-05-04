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
    <title>生效日期内的保险项目</title>
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
                    &nbsp;福利项目：<input id="fuliName" class="mini-textbox" style="width:160px;" onenter="onSearchClick"/>
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('fuliName','fuliName')">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
            allowAlternating="true" idField="id" allowResize="false" >
            <div property="columns">
                <div type="checkcolumn" width="40">选择</div>
                <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                <div field="name" width="80" headerAlign="center" align="center">福利项目</div>
                <div type="comboboxcolumn" field="area" width="80" headerAlign="center" align="center">缴费地域
                    <input property="editor" class="mini-combobox" url="../basis/pay_areas!jsonlist.action"textField="city"/>
                </div>
                <div type="comboboxcolumn" field="staffType" width="80" headerAlign="center" align="center">员工类型
                    <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945bc7485d015bc7babe37000e"
                        textField="dicname" valueField="dicvalue"/>
                </div>
                <div field="high" width="100" headerAlign="center" align="center">缴纳基数上限</div>
                <div field="low" width="100" headerAlign="center" align="center">缴纳基数下限</div>
                <div field="biCompany" width="100" headerAlign="center" align="center">公司缴纳比例</div>
                <div field="biPersonal" width="100" headerAlign="center" align="center">个人缴纳比例</div>
                <div field="effectDate" width="80" headerAlign="center" align="center">生效年月</div>
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
        grid.setUrl("../salary/salary_insurance!getNeedJoinInsuranceItemsData.action?ids=${ids}&recordMainId=${recordMainId}");
        $(document).ready(function() {
            $("#searchButton").click();
        });

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

        function onSearchClick() {
            $("#searchButton").click();
        }

        grid.on("drawCell", function(e) {
            // 生效年月
            if (e.field=="effectDate") {
                var row = e.row;
                e.cellHtml=row.year+"年"+row.month+"月";
            }
        });

    </script>
</body>
</html>