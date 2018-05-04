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
    <title>各单位薪酬总额</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
        <div showCollapseButton="true" size="16%">
             <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                 <span style="padding-left:5px;">部门名称：</span>
             </div>
             <div class="mini-fit">
                 <ul id="tree1" class="mini-tree" url="../basis/branch!treeList.action" style="width:100%;"
                     showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                 </ul>
             </div>
        </div>
        <div showCollapseButton="true">
             <div class="mini-toolbar" style="padding:0px;border-top:0;">
                <table style="width:100%;">
                    <tr>
                        <td style="width:100%;">
                            &nbsp;上报日期:
                            <input class="mini-datepicker" id="startTime" style="width: 100px" format="yyyy年MM月" onenter="onKeyEnter"/>
                            <a id="searchButton" class="mini-button" iconCls="icon-search" onclick="">查询</a>
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
                        <div field="" width="80" headerAlign="center" align="center">公司</div>
                        <div field="" width="80" headerAlign="center" align="center">上报额度</div>
                        <div field="" width="80" headerAlign="center" align="center">上报日期</div>
                        <div field="" width="80" headerAlign="center" align="center">状态</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        var startTime = mini.get("startTime");
        startTime.setValue(new Date());

        function sh() {
             mini.open({
                 url : "../salary/salary_quota!audit.action",
                 title : "审核意见",
                 width : 420,
                 height : 180,
                 onload : function() {
                 },
                 ondestroy : function(action) {
                     grid.reload();
                 }
             });
        }
    </script>
</body>
</html>