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
    <title>工资套引用</title>
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
                    &nbsp;<input class="mini-hidden" id="companyId" value="${companyId}"/>
                    &nbsp;公司名称：<input class="mini-textbox" id="companyName" onenter="onKeyEnter"/>
                    &nbsp;工资套名称：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                    &nbsp;工资套类型：<input class="mini-combobox" id="sign" showNullItem="true"
                        data="[{id:1, text:'薪酬期间工资'},{id:2, text:'绩效奖励'},{id:3, text:'提成工资'},{id:4, text:'离职结算'}]" onenter="onKeyEnter"/>
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('companyId,companyName,name,sign','companyId,companyName,name,sign')">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_items!getSalaryWageEffectListData.action" pageSize="20"
            allowAlternating="true" idField="id" allowResize="false" onrowdblclick="onRowDblclick">
            <div property="columns">
                <div type="checkcolumn" width="40">选择</div>
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div type="indexcolumn" headeralign="center"  width="40">序列</div>
                <div field="companyName" width="140" headerAlign="center" align="center">公司</div>
                <div field="name" width="200" headerAlign="center" align="center">工资套名称</div>
                <div type="comboboxcolumn" field="sign" width="200" headerAlign="center" align="center">工资套类型
                    <input property="editor" class="mini-combobox" data="[{id:1, text:'薪酬期间工资'},{id:2, text:'绩效奖励'},{id:3, text:'提成工资'},{id:4, text:'离职结算'}]"/>
                </div>
                <div type="comboboxcolumn" field="type" width="80" headerAlign="center" align="center">状态
                    <input property="editor" class="mini-combobox" data="[{id:0,text:'无效'},{id:1,text:'有效'}]"/>
                </div>
                <div field="effectDate" width="140" headerAlign="center" align="center" renderer="ondayRenderer2">生效日期</div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOkSelected" style="width:60px;margin-right:20px;" iconCls="icon-save">确定</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");

        $(function() {
            searchS();
        });

        function searchS() {
            search('companyId,companyName,name,sign','companyId,companyName,name,sign');
        }

        function onRowDblclick(e) {
        	onOkSelected();
        }

        function GetData() {
        	var row = grid.getSelected();
            return row;
        }

        function onOkSelected() {
        	var row = grid.getSelected();
            if (row) {
                CloseWindow("ok");
            } else {
                mini.alert("请选择一条记录");
                return;
            }
        }

        //////////////////////////////////
        function CloseWindow(action) {
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }
        function onCancel() {
            CloseWindow("cancel");
        }

    </script>
</body>
</html>