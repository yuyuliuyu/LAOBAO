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
    <title>薪资区间选择</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20" url="../salary/salary_manage!getCurComAllPeriod.action?id=${companyId}"
            allowAlternating="true" idField="id" allowResize="false" showPager="false" multiSelect="true"
            ondrawgroup="onDrawGroup" collapseGroupOnLoad="false">
            <div property="columns">
                <div type="checkcolumn" width="30"></div>
                <div type="indexcolumn" headerAlign="center" width="30">序列</div>
                <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                <div field="companyName" width="120" headerAlign="center" align="center">公司</div>
                <div field="name" width="100" headerAlign="center" align="center">薪酬期间名称</div>
                <div field="startDate" width="60" headerAlign="center" align="center" renderer="ondayRenderer">开始日期</div>
                <div field="endDate" width="60" headerAlign="center" align="center" renderer="ondayRenderer">结束日期</div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOkSelected()" style="width:60px;margin-right:20px;" iconCls="icon-save">选择</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.hideColumn("id");
        grid.groupBy("year","desc");
        grid.load();

        function GetData() {
            var rows = grid.getSelecteds();
            return rows;
        }

        function onOkSelected() {
            var rows = grid.getSelecteds();
            if (rows.length>0) {
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

        /**时间格式化
         * 形式:yyyy-MM-dd'
         * */
        function ondayRenderer(e) {
            var value = e.value;
            if(value != null){
                value = new Date(value);
                if (value) return mini.formatDate(value, 'yyyy年MM月dd日');
            }else{
                return "";
            }
        }

        function onDrawGroup(e) {
            e.cellHtml = "年度：" + e.value;
        }

    </script>
</body>
</html>