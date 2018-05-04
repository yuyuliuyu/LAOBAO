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
    <title>薪资项目选择</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <input class="mini-hidden" id="companyId" value="${companyId}"/>
                    &nbsp;薪资项目名称：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                    &nbsp;项目类型：<input class="mini-combobox" id="itemType" showNullItem="true"
                        data="[{id:1,text:'普通薪资项目'},{id:2,text:'应纳税所得额'},{id:3,text:'应缴税金'},{id:4,text:'实发工资'},{id:5,text:'应发合计'}]" onenter="onKeyEnter"/>
                    &nbsp;公司名称：<input class="mini-textbox" id="companyName" onenter="onKeyEnter"/>
                    <input class="mini-hidden" id="type" value="1"/>
                    <input class="mini-hidden" id="items" value="${items}"/>
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('name,itemType,type,companyName,items','name,itemType,type,companyName,items')">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
            url="../salary/salary_items!getSalaryItemGKListData.action?depId=${companyId}"
            allowAlternating="true" multiSelect="true" idField="id" allowResize="false">
            <div property="columns">
                <div type="checkcolumn" width="40"></div>
                <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                <div field="name" width="140" headerAlign="center" align="center">薪资项目名称</div>
                <div type="comboboxcolumn" field="depId" width="140" headerAlign="center" align="center">公司
                    <input property="editor" class="mini-combobox" textField="fzx" url="../salary/salary_manage!getBranches.action"/>
                </div>
                <div field="numberAccuracy" width="80" headerAlign="center" align="center">保留小数</div>
                <div type="comboboxcolumn" field="addOrLess" width="80" headerAlign="center" align="center">增减属性
                    <input property="editor" class="mini-combobox" style="width:100%;" data="addOrLess" />
                </div>
                <div type="comboboxcolumn" field="fixed" width="100" headerAlign="center" align="center">固定薪资项目
                    <input property="editor" class="mini-combobox" style="width:100%;" data="fixed" />
                </div>
                <div type="comboboxcolumn" field="yd" width="80" headerAlign="center" align="center">异动项目
                    <input property="editor" class="mini-combobox" style="width:100%;" data="fixed" />
                </div>
                <div type="comboboxcolumn" field="bzl" width="80" headerAlign="center" align="center">标准类项目
                    <input property="editor" class="mini-combobox" style="width:100%;" data="fixed" />
                </div>
                <div type="comboboxcolumn" field="njl" width="80" headerAlign="center" align="center">年金类项目
                    <input property="editor" class="mini-combobox" style="width:100%;" data="fixed" />
                </div>
                <div type="comboboxcolumn" field="tcl" width="80" headerAlign="center" align="center">统筹类项目
                    <input property="editor" class="mini-combobox" style="width:100%;" data="fixed" />
                </div>
                <div type="comboboxcolumn" field="itemType" width="100" headerAlign="center" align="center">项目类型
                    <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945cc8c4c8015cc926f00d0008"
                                textField="dicname" valueField="dicvalue"/>
                </div>
                <div type="comboboxcolumn" field="itemPro" width="100" headerAlign="center" align="center">项目属性
                    <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945cc8c4c8015cc92867ae000e"
                                textField="dicname" valueField="dicvalue"/>
                </div>
                <div type="comboboxcolumn" field="type" width="80" headerAlign="center" align="center">状态
                    <input property="editor" class="mini-combobox" style="width:100%;" data="type" />
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOKClose()" style="width:60px;margin-right:20px;" iconCls="icon-save">选择</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var addOrLess = [{id:1,text:'加'},{id:2,text:'减'}];
        var fixed = [{id:0,text:'否'},{id:1,text:'是'}];
        var type = [{id:0,text:'无效'},{id:1,text:'有效'}];
        var grid = mini.get("grid");
        grid.frozenColumns(0, 3);
        grid.hideColumn("id");
        var choseIds = "";
        var choseNames = "";
        var typeIds = "";
        $(function() {
        	$("#searchButton").click();
        });

        function GetData() {
            var rows = grid.getSelecteds();

            return rows;
        }

        function onOKClose() {
        	var rows = grid.getSelecteds();
            if (rows.length == 0) {
                mini.alert("请选择一条数据");
                return;
            }
            CloseWindow("ok");
        }

        /** 关闭按钮点击事件 */
        function onCancel() {
            CloseWindow("cancel");
        }
        /** 关闭窗口 */
        function CloseWindow(action) {
            if (window.CloseOwnerWindow)
                return window.CloseOwnerWindow(action);
            else
                window.close();
        }

    </script>
</body>
</html>