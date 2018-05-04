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
    <title>审批流设置展示</title>
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
                            <a class="mini-button" iconCls="icon-edit" onclick="add()">增加</a>
                            <a class="mini-button" iconCls="icon-edit" onclick="edit()">修改</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_rate!getAllRateListData.action" pageSize="20"
                    allowAlternating="true" idField="id" allowResize="false" onrowdblclick="onRowDblClick">
                    <div property="columns">
                        <div type="checkcolumn" width="6">选择</div>
                        <div field="id" id="id" width="100" name="id" class="mini-hidden"></div>
                        <div field="" width="80" headerAlign="left" align="left">名称</div>
                        <div field="" width="100" headerAlign="left" align="left">薪资组</div>
                        <div field="" width="60" headerAlign="left" align="left">状态</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");

        function add() {
            mini.open({
                url : "../salary/salary_check!add.action",
                title : "审批流编辑",
                width : 560,
                height : 480,
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