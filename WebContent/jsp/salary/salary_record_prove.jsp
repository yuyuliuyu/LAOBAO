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
    <title>员工收入证明</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td>
                    &nbsp;姓名：<input class="mini-textbox" onenter="onKeyEnter"/>
                    &nbsp;薪酬日期从：<input class="mini-datepicker" id="startTime"  onenter="onKeyEnter"/>
                    &nbsp;到：<input class="mini-datepicker" id="endTime"  onenter="onKeyEnter"/>
                    <a class="mini-button" iconCls="icon-search" onclick="sr()">查询</a>
                    <a class="mini-button" iconCls="icon-download" onclick="">导出</a>
                </td>
                <td style="float: right;">
                    &nbsp;薪资组：
                    <input class="mini-combobox" id="salaryGroupId" showNullItem="true"
                        url="../salary/salary_items!getSalaryGroupListData.action" textField="name"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_record!getHistorySalaryRecord.action?id=" pageSize="20"
            pagerSize="20" idField="id" allowResize="false" borderStyle="border-center:0px;border-right:0px;">
            <div property="columns">
                <div field="id" id="id" width="20" name="id" class="mini-hidden"></div>
                <div type="indexcolumn" headeralign="center"  width="60">序列</div>
                <div field= width="100" headerAlign="center" align="center">工号</div>
                <div field="" width="100" headerAlign="center" align="center">姓名</div>
                <div field="" width="100" headerAlign="center" align="center">公司</div>
                <div field="" width="100" headerAlign="center" align="center">部门</div>
                <div field="" width="100" headerAlign="center" align="center">基本工资</div>
                <div field="" width="100" headerAlign="center" align="center">绩效奖金</div>
                <div field="" width="100" headerAlign="center" align="center">岗位津贴</div>
                <div field="" width="100" headerAlign="center" align="center">其他补贴</div>
                <div field="" width="100" headerAlign="center" align="center">应发合计</div>
                <div field="" width="100" headerAlign="center" align="center">个人所得税</div>
                <div field="" width="100" headerAlign="center" align="center">实际应发</div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
    </script>
</body>
</html>