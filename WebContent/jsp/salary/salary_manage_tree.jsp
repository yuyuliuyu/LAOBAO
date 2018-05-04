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
    <title>人员薪资列表</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    </style>
</head>

<body>
    <div class="mini-fit">
       <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
           <div showCollapseButton="true" size="16%">
                <ul id="tree1" class="mini-tree" url="../basis/branch!treeList.action" style="width:100%;"
                        showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                    </ul>
           </div>
           <div showCollapseButton="true">
                <iframe id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
           </div>
       </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var pageTree = [{id: 1, text: "基础设置",
                            children: [
                                       {id: 10, text: "税率维护", url: "../salary/salary_rate!list.action"}
                                      ]
                         }, {id: 2, text: "薪资管理",
                            children: [
                                       {id: 10, text: "薪资项目", url: "../salary/salary_manage!rate.action"},
                                       {id: 11, text: "薪资项目类别", url: "../salary/salary_manage!rate.action"},
                                       {id: 12, text: "工资套", url: "../salary/salary_manage!rate.action"},
                                       {id: 13, text: "薪资组", url: "../salary/salary_manage!rate.action"},
                                       {id: 14, text: "工资条", url: "../salary/salary_manage!rate.action"},
                                       {id: 15, text: "工资绩效档案", url: "../salary/salary_record!list.action"},
                                       {id: 16, text: "异动人员工资调整", url: "../salary/salary_manage!rate.action"},
                                      ]
                         }];
        var tree = mini.get("tree");
        tree.loadData(pageTree);
        var mainframe = document.getElementById("mainframe");

        function onNodeclick(e) {
        	var node = e.node;
        	mainframe.src = node.url;
        }
    </script>
</body>
</html>