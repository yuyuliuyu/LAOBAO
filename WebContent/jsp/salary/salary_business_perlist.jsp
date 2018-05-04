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
    <title>个人商业保险缴费列表</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/treeCompanyList.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0">
        <div size="16%" showCollapseButton="true">
            <div class="mini-toolbar" style="padding:2px;border-top:0;border-center:0;border-right:0;">
                <span style="padding-center:5px;">组织机构：</span>
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
                            &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="onKeyEnter"/>
                            &nbsp;姓名：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('jobNumber,name','jobNumber,name')">查询</a>
                            <br><a class="mini-button" name="readTye" iconCls="icon-add" onclick="add()">增加</a>
                            <a class="mini-button" name="readTye" iconCls="icon-edit" onclick="edit()">编辑</a>
                            <a class="mini-button" name="readTye" iconCls="icon-remove" onclick="removes()">删除</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
                    allowAlternating="true" idField="id" allowResize="false" multiSelect="true">
                    <div property="columns">
                        <div type="checkcolumn" width="40"></div>
                        <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                        <div field="jobNumber" width="80" headerAlign="center" align="center">工号</div>
                        <div field="name" width="100" headerAlign="center" align="center">姓名</div>
                        <div field="deptName" width="60" headerAlign="center" align="center">部门</div>
                        <div field="year" width="40" headerAlign="center" align="center">年份</div>
                        <div field="insuranceName" width="40" headerAlign="center" align="center">保险项目</div>
                        <div field="bf" width="60" headerAlign="center" align="center">保额（万元）</div>
                        <div field="bxfl" width="60" headerAlign="center" align="center">费率（‰）</div>
                        <div field="bbxr" width="60" headerAlign="center" align="center">被保险人</div>
                        <div field="ytbrgx" width="60" headerAlign="center" align="center">与投保人关系</div>
                        <div field="gsjfje" width="80" headerAlign="center" align="center">个人缴费金额（元）</div>
                        <div field="grjfje" width="80" headerAlign="center" align="center">公司缴费金额（元）</div>
                        <div field="beginDate" width="80" headerAlign="center" align="center" renderer="ondayRenderer2">缴费日期</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.hideColumn("id");
        var tree = mini.get("deptree");

        function callback(ids, node) {
            grid.setUrl("../salary/salary_business!getPerListData.action");
            $("#searchButton").click();
        }

        function add() {
            mini.open({
                url : "../salary/salary_business!perAdd.action",
                title : "新增个人商业保险缴费",
                width : 720,
                height : 480,
                onload : function() {
                },
                ondestroy : function(action) {
                    grid.reload();
                }
            });
        }

        function edit() {
            var grid=mini.get("grid");
            var rows = grid.getSelecteds();
            if(rows.length!=1){
                mini.alert("请选择一条记录!");
                return;
            }
            mini.open({
                url : "../salary/salary_business!perEdit.action?id="+rows[0].id,
                title : "编辑个人商业保险缴费",
                width : 720,
                height : 480,
                onload : function() {
                },
                ondestroy : function(action) {
                    grid.reload();
                }
            });
        }

        function removes() {
            var grid=mini.get("grid");
            var rows = grid.getSelecteds();
            if (rows.length == 0) {
                mini.alert("请选择一条记录!");
                return;
            }
            var ids = rows[0].id
            for (var i = 1,l = rows.length;i<l;i++) {
                ids += "," + rows[i].id
            }

            mini.confirm("您确定要删除选中信息吗？","提醒",function(action){
                if(action=="ok"){
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_business!removeToPer.action",
                        data: {
                            ids: ids
                        },
                        type:'post',
                        success: function (text) {
                            window.parent.unmask();
                            if(text=="success"){
                                grid.reload();
                                mini.alert("删除成功！");
                            }else{
                                mini.alert(text);
                            }
                        }
                    });
                }
            });
        }

        /**点击回车查询
         * JSP:onkeydown="onKeyEnter"
         * 查询按钮id：searchButton
         * 火狐下不识别 event is not defined
         * */
        function onKeyEnter(){
            if((event.keyCode || event.which) == 13){
                $("#searchButton").click();
            }
        }
        /**时间格式化
         * 形式:yyyy年MM月dd日'
         * */
        function ondayRenderer2(e) {
            var value = e.value;
            if(value != null){
                value = new Date(value);
                if (value) return mini.formatDate(value, 'yyyy年MM月dd日');
            }else{
                return "";
            }
        }
    </script>
</body>
</html>