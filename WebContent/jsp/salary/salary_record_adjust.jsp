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
    <title>调整员工薪酬</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
    <script src="${base}/template/plugin/public/treeList.js" type="text/javascript"></script>
    <script src="../template/plugin/biguploader/uploader.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
        <div size="16%" showCollapseButton="true">
            <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                <span style="padding-left:5px;">组织机构：</span>
            </div>
            <div class="mini-fit" style=" border-top:0;">
                <ul id="deptree" class="mini-tree" url="../basis/branch!getTreeListByUser.action" style="width:100%;height:100%;"
                    showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                </ul>
            </div>
        </div>
        <div showCollapseButton="true">
            <div class="mini-toolbar" style="padding:0px;border-top:0;">
                <table style="width:100%;">
                    <tr>
                        <td style="width:100%;">
                            <a class="mini-hidden" iconCls="icon-remove" onclick="remove()">删除</a>
                            &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="onKeyEnter"/>
                            &nbsp;姓名：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('jobNumber,name','jobNumber,name')">查询</a>
                            <br><a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                            <a class="mini-button" iconCls="icon-upload" onclick="importExcelData()">导入固定薪资项目值</a>
                            <a class="mini-button" iconCls="icon-download" onclick="exportExcelData('salary', 'salary_record','jobNumber,name','jobNumber,name')">导出固定薪资项目值</a>
                            <a class="mini-button" iconCls="icon-download" onclick="downModel()">下载模板</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_record!getPracticeEmployeeListData.action" pageSize="20"
                    allowAlternating="true" idField="id" allowResize="false">
                    <div property="columns">
                        <div type="checkcolumn" width="40">选择</div>
                        <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                        <div type="indexcolumn" headeralign="center"  width="40">序列</div>
                        <div field="jobNumber" width="80" headerAlign="center" align="center">工号</div>
                        <div field="name" width="100" headerAlign="center" align="center">姓名</div>
                        <div field="originalCompany" width="150" headerAlign="center" align="center">原公司</div>
                        <div field="originalDept" width="80" headerAlign="center" align="center">原部门</div>
                        <div field="originalJtgw" width="80" headerAlign="center" align="center">原岗位</div>
                        <div field="companyId" width="150" headerAlign="center" align="center">现公司</div>
                        <div field="detpName" width="80" headerAlign="center" align="center">现部门</div>
                        <div field="post" width="80" headerAlign="center" align="center">现具体岗位</div>
                        <div field="nowBzgw" width="80" headerAlign="center" align="center">现标准岗位</div>
                        <div field="salaryName" width="80" headerAlign="center" align="center">薪酬岗位</div>
                        <div field="startDate" width="100" headerAlign="center" align="center" renderer="ondayRenderer2">开始日期</div>
                        <div field="beginTime" width="100" headerAlign="center" align="center" renderer="ondayRenderer2">入司时间</div>
                        <div type="comboboxcolumn" field="changeType" width="100" headerAlign="center" align="center">异动类型
                            <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955bdb82a5015bdc08b32b0013"
                                textField="dicname" valueField="dicvalue"/>
                        </div>
                        <div field="beginDate" width="100" headerAlign="center" align="center" renderer="ondayRenderer2">异动生效日期</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <form id="uploaders" method="post" enctype="multipart/form-data" encoding="multipart/form-data" target="appFrame" style="display: none;">
       <input class="mini-htmlfile" id="uploadFile" name="uploadFile" limitType="*" onfileselect= "onFileSelected"/>
    </form>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        var tree = mini.get("deptree");

        $(function() {
            tree.selectNode(tree.getRootNode().children[0]);
            $("#searchButton").click();
        });

        function edit() {
            var rows=grid.getSelecteds();
            var l=rows.length
            if (l == 0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            mini.open({
                url : "../salary/salary_record!adjustedit.action?id="+ids+"&empChangeId="+rows[0].empChangeId,
                title : "编辑员工薪酬",
                width : 960,
                height : 520,
                onload : function() {
                },
                ondestroy : function(action) {
                    grid.reload();
                }
            });
        }

        // 导入
        function importExcelData() {
            var url = "../salary/salary_record!uploadFixedItemsValues.action";
            bigWebUploader.openMini("uploaders","uploadFile", url);
        }

        function onFileSelected(e) {
            window.parent.loading();
            // 表单提交
            bigWebUploader.submit(callback);
        }

        function callback(data) {
            window.parent.unmask();
            if (data == "success") {
                mini.alert("导入成功");
                grid.reload();
            } else {
                mini.alert(data);
            }
        }

        // 薪资项目模板下载
        function downModel() {
            window.location.href="../salary/adjustFixed.action";
        }
    </script>
</body>
</html>