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
    <title>自定义sql查询</title>
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
                <td style="width:100%;" ondblclick="onClickQtxzAlert(this)">
                    &nbsp;查询SQL语句：
                    <textarea class="mini-textarea" style="width:80%;height:64px;" id="qtxz"></textarea>
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchSql()">查询</a>
                    <a class="mini-button" iconCls="icon-download"
                        onclick="exportExcel()">导出</a>
                </td>
            </tr>
        </table>
    </div>
    <div id="qtxzWindow" class="mini-window" title="自定义SQL" style="width:700px;height:380px;overflow:hidden" 
        showModal="true" allowResize="false" allowDrag="true">
        <div class="mini-fit">
            <textarea class="mini-textarea" id="self_sql" style="width:100%;height:100%"> </textarea>
        </div>
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
            <a class="mini-button" onclick="onok" style="width:60px;margin-right:20px;" iconCls="icon-save">查询</a>
            <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">关闭</a>
        </div>
    </div>
    <div class="mini-fit">
        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
            allowAlternating="true" multiSelect="true" showPager = "false" allowHeaderWrap="true"
            idField="id" allowResize="false" virtualScroll="true">
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid1 = mini.get("grid1");
        var pageIndex = 0;
        var pageSize = grid1.getPageSize();
        var qtxzWindow = mini.get("qtxzWindow");
        var qtxz = mini.get("qtxz");
        var self_sql = mini.get("self_sql");

        function searchSql() {
            $.ajax({
                url: "../salary/customize!getCustomizeListData.action?pageIndex="+pageIndex+"&pageSize="+pageSize,
                type: "POST",
                data : {
                	sql : qtxz.getValue()
                },
                success: function(text) {
                    var jsonData = mini.decode(text);
                    var arr = [];
                    arr.push({ type: "indexcolumn", headerAlign:"center", width: 40,header: "序列"});
    
                    // 循环便利所有
                    var dd = jsonData.data;
                    if (dd.length > 0) {
                    	for (var key in dd[0]) {
	                        arr.push({ field: key, width:"80", headerAlign:"center", align:"center", header: key});
                    	}
                    }
                    var table = [];
                    for (var i=0; i< dd.length;i++) {
                        var column = {};
                    	for (var key in dd[i]) {
                    		column[key] = dd[i][key];
                    	}
                        table.push(column);
                    }
                    grid1.setData(table);
                    grid1.setPageIndex(pageIndex);
                    grid1.setPageSize(pageSize);
                    grid1.set({columns : arr});
                    grid1.hideColumn("id");
                    grid1.frozenColumns(0, 7);
                    grid1.setTotalCount(jsonData.total);
                }
            });
        }

        /**
         * SQL最大化
         * zhanghj
         */
        function onClickQtxzAlert(e) {
            self_sql.setValue(qtxz.getValue());
            qtxzWindow.showAtPos("center","middle");
            qtxzWindow.show();
        }

        function onok() {
            var s = self_sql.getValue();
            if (s == "") {
                mini.alert("sql语句不能为空");
                return;
            }
            qtxz.setValue(s);
            onCancel();
        }

        /**
         * SQL隐藏
         * zhanghj
         */
        function onCancel() {
            qtxzWindow.hide();
        }

        // 导出Excel
        function exportExcel() {
        	/* $.ajax({
                url: "../salary/customize!export.action",
                type: "POST",
                data : {
                    sql : qtxz.getValue()
                },
                success: function(text) {
                	
                }
        	}); */
        	var data = encodeURI(qtxz.getValue());
            window.location.href="../salary/customize!export.action?sql="+data;
        }

    </script>
</body>
</html>