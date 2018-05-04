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
    <title>福利类型</title>
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
                            <a class="mini-button" iconCls="icon-edit" onclick="eidt()">编辑</a>
                            <a class="mini-button" iconCls="icon-edit" onclick="remove()">删除</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_record!getSalaryStandard.action" pageSize="20"
                    pagerSize="20" idField="id" allowResize="false" borderStyle="border-left:0px;border-right:0px;">
                    <div property="columns">
                        <div type="checkcolumn" width="30">选择</div>
                        <div field="id" id="id" width="20" name="id" class="mini-hidden"></div>
                        <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                        <div field="" width="100" headerAlign="left" align="left">福利名称</div>
                        <div field="" width="100" headerAlign="left" align="left">费用</div>
                        <div field="" width="100" headerAlign="left" align="left">薪资项目</div>
                        <div field="" width="100" headerAlign="left" align="left">备注</div>
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
                url : "../salary/salary_welfare!typeAdd.action?id=",
                title : "福利增加",
                width : 540,
                height : 320,
                onload : function() {
                },
                ondestroy : function(action) {
                }
            });
        }

    </script>
</body>
</html>