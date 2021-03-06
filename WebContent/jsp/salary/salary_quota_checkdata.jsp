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
    <title>薪酬审批</title>
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
                    <a class="mini-button" iconCls="icon-add" onclick="tjsh()">审核</a>
                    <a class="mini-button" iconCls="icon-add" onclick="tjsh()">反审核</a>
                    <a class="mini-button" iconCls="icon-download" onclick="">导出</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
            allowAlternating="true" multiSelect="true" url="../salary/salary_rate!getAllRateListData.action"
            idField="id" allowResize="false" >
            <div property="columns">
                <div type="checkcolumn" width="40"></div>
                <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">工号</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">姓名</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">发薪单位</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">年功工资</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">技能津贴</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">职称津贴</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">职务津贴</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">机关津贴</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">综合补贴</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">班组长补贴</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">午餐补贴</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">岗位工资</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">计件工资</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">工伤工资</div>
                <div field="doctorapply" width="80" headerAlign="center" align="center">全勤奖</div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");

        function tjsh() {
             mini.alert("提交审核成功");
        }

        function js() {
             mini.alert("计算完成");
        }
    </script>
</body>
</html>