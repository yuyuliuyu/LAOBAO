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
    <title>员工参保信息</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
    <script src="${base}/template/plugin/public/treeList.js" type="text/javascript"></script>
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
                            &nbsp;工号：<input class="mini-textbox" id="jobNumber" onenter="onKeyEnter"/>
                            &nbsp;姓名：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                            <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="searchS()">查询</a>
                            <br><a class="mini-button" iconCls="icon-goto" onclick="addCanBao()">办理参保缴费</a>
                            <a class="mini-hidden" iconCls="icon-find" onclick="searchJoins()">查询参保人员</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_insurance!getNoInsuranceOfStaffListData.action" pageSize="20"
                    pagerSize="20" idField="id" allowResize="false" borderStyle="border-center:0px;border-right:0px;" multiSelect="true">
                    <div property="columns">
                        <div type="checkcolumn" width="30"></div>
                        <div field="id" id="id" width="20" name="id" class="mini-hidden"></div>
                        <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                        <div field="jobNumber" width="100" headerAlign="center" align="center">工号</div>
                        <div field="name" width="100" headerAlign="center" align="center">姓名</div>
                        <div field="companyId" width="100" headerAlign="center" align="center">公司</div>
                        <div field="detpName" width="100" headerAlign="center" align="center">部门</div>
                        <div field="post" width="100" headerAlign="center" align="center">岗位</div>
                        <div type="comboboxcolumn" field="onJob" width="100" headerAlign="center" align="center">在职状态
                            <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935baceaa1015bae044344001a"
                                textField="dicname" valueField="dicvalue"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");

        $(function() {
            searchS();
        });
        function searchS() {
            search('jobNumber,name','jobNumber,name');
        }

        function addCanBao() {
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            var recordIds = rows[0].recordMainId;
            for (var i=1,l=rows.length;i<l;i++) {
            	recordIds += "," + rows[i].recordMainId;
            }
            mini.open({
                url : "../salary/salary_insurance!canbaoAdd.action?ids="+ids+"&recordMainId=" + recordIds,
                title : "办理参保",
                width : 890,
                height : 640,
                onload : function() {
                },
                ondestroy : function(action) {
                    grid.reload();
                }
            });
        }

        function searchJoins() {
            var tabs = window.parent.mini.get("mainTabs");
            var id = "add$";
            var tab = tabs.getTab(id);
            if (tab) {
                tabs.removeTab(tab);
            }
            tab = {};
            tab.name = id;
            tab.title = "查询参保人员";
            tab.showCloseButton = true; 
            tab.url = "../salary/salary_insurance!joinList.action";
            tabs.addTab(tab);
            tabs.activeTab(tab);
        }
    </script>
</body>
</html>