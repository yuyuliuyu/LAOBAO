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
    <title>税率列表</title>
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
                    <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_rate!getAllRateListData.action" 
            allowAlternating="true" idField="id" allowResize="false" showPager="false">
            <div property="columns">
                <div type="checkcolumn" width="6">选择</div>
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div field="name" width="200" headerAlign="center" align="center">类型</div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");

        function edit() {
            var row = grid.getSelected();
            if(row){
                 var id = row.id;
                 mini.open({
                        url : "../salary/salary_rate!edit.action?id="+id,
                        title : "编辑税率",
                        width : 680,
                        height : 540,
                        onload : function() {
                        },
                        ondestroy : function(action) {
                            grid.reload();
                        }
                    });
            }else{
                mini.alert("请选择一条记录!");
                return;
            }
        }
    </script>
</body>
</html>