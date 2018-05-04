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
    <title>调整员工薪级列表</title>
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
                            <a class="mini-button" iconCls="icon-add" onclick="edit()">增加</a>
                            <a class="mini-button" iconCls="icon-edit" onclick="edit()">删除</a>
                            <a class="mini-button" iconCls="icon-edit" onclick="">提交审批</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_rate!getAllRateListData.action" pageSize="20"
                    allowAlternating="true" idField="id" allowResize="false" onrowdblclick="onRowDblClick">
                    <div property="columns">
                        <div type="checkcolumn" width="40">选择</div>
                        <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                        <div type="indexcolumn" headeralign="center"  width="60">序列</div>
                        <div field="" width="100" headerAlign="left" align="left">调整过程名称</div>
                        <div field="" width="100" headerAlign="left" align="left">组织机构</div>
                        <div field="" width="80" headerAlign="left" align="left">调整类型</div>
                        <div field="" width="80" headerAlign="left" align="left">调整级数</div>
                        <div field="" width="100" headerAlign="left" align="left">调整方式</div>
                        <div field="" width="80" headerAlign="left" align="left">生效日期</div>
                        <div field="" width="80" headerAlign="left" align="left">状态</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");

        function edit() {
            mini.open({
                url : "../salary/salary_record!levelAdd.action?id=",
                title : "批量调整员工薪级",
                width : 640,
                height : 320,
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