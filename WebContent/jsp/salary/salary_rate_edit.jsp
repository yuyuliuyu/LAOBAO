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
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <form id="form1">
        <input class="mini-hidden" name="id" id="id" value="${rate.id}" style="display:none;"/>
        <input class="mini-hidden" id="type" name="type" value="${rate.type}" style="display:none;"/>
        <table  class="addtable" style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" width="15%">类型：</td>
                <td class="list_right_box" width="30%">
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" name="name" value="${rate.name }"/>
                </td>
                <td class="list_left_box1" valign="top" width="15%">*生效日期：</td>
                <td class="list_right_box" width="30%">
                    <input class="mini-datepicker" format="yyyy年MM月dd日" style="width:90%" name="beginDate"value="${rate.beginDate }"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" width="15%">
                    <s:if test="#request.rate.type == 1">本国起征点：</s:if>
                    <s:else>*4000以下费用扣除额：</s:else>
                </td>
                <td class="list_right_box" width="30%">
                    <input class="mini-spinner"style="width:90%"name="nativeMoney"value="${rate.nativeMoney }" format="n2" minValue="0" maxValue="99999.99"/>
                </td>
                <td class="list_left_box1" valign="top" width="15%">
                    <s:if test="#request.rate.type == 1">外籍起征点：</s:if>
                    <s:else>*4000以上费用扣除%：</s:else>
                </td>
                <td class="list_right_box" width="30%">
                    <input class="mini-spinner"style="width:90%"name="foreignMoney"value="${rate.foreignMoney }" format="n2" minValue="0" maxValue="99999.99"/>
                </td>
            </tr>
        </table>
    </form>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <a class="mini-button" iconCls="icon-add" onclick="addRow()">增加</a>
                    <a class="mini-button" iconCls="icon-edit" onclick="removeRow()">删除</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_rate!getRateStaticListData.action?id=${rate.id}" pageSize="20"
            allowCellEdit="true"  allowCellSelect="true" showpager="false" idField="id" allowResize="false"multiSelect="true"
             showPager="false" borderStyle="border-left:0px;border-right:0px;"oncellendedit="onCellEndEdit">
            <div property="columns">
                <div type="checkcolumn" width="30"></div>
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div field="low" width="100" headerAlign="center" align="center"numberFormat="#.00"vType="required;">应纳税所得额下限*
                    <input property="editor" class="mini-spinner" style="width:100%" format="n2" minValue="0" maxValue="99999.99"/>
                </div>
                <div field="high" width="100" headerAlign="center" align="center"numberFormat="#.00"vType="required;">应纳税所得额上限*</div>
                <div field="rate" width="100" headerAlign="center" align="center"numberFormat="#.00"vType="required;">税率*
                    <input property="editor" class="mini-spinner" style="width:100%" format="n2" minValue="0" maxValue="100"/>
                </div>
                <div field="kcs" width="100" headerAlign="center" align="center"numberFormat="#.00"vType="required;">速算扣除数*
                    <input property="editor" class="mini-spinner" style="width:100%" format="n2" minValue="0" maxValue="99999.99"/>
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOk('salary','salary_rate','',false)" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");

        function onCellEndEdit(e) {
        	var record = e.record, field = e.field;
        	if (field == "low") {
        		if (e.rowIndex > 0) {
        			var row = grid.getRow(e.rowIndex-1);
        			grid.updateRow(row, {high: record.low});
        		}
        		var row = grid.getRow(grid.getData().length-1);
                grid.updateRow(row, {high: "正无穷"});
        	}
        }
    </script>
</body>
</html>