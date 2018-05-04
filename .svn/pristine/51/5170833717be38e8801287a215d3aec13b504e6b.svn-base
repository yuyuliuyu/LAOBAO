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
    <title>薪资组增加、编辑</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1" class="mini-fit">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top">*薪资组名称：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" id="id" name="id" value="${salaryGroup.id}"/>
                    <input class="mini-textbox" name="name" value="${salaryGroup.name}" style="width:90%" maxLength="100" required="true"/>
                </td>
                <td class="list_left_box1" valign="top">*生效日期：</td>
                <td class="list_right_box">
                    <input class="mini-datepicker" id="effectDate" name="effectDate" value="${salaryGroup.effectDate}" style="width:90%" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*公司：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" id="companyId" name="companyId" textField="fzx" required="true" enabled="false"
                        url="../salary/salary_manage!getBranches.action" value="${companyId}" style="width:90%" onvaluechanged="onComChanged"/>
                </td>
                <td class="list_left_box1" valign="top">部门：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" id="depId" name="depId" textField="name" value="${salaryGroup.depId}" style="width:90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*发薪频率：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" name="salaryPeriod" data="[{id:0, text:'周'},{id:1, text:'双周'},{id:2, text:'半月'},{id:3, text:'月'}]"
                        value="${salaryGroup.salaryPeriod}" style="width:90%" required="true"/>
                </td>
                <td class="list_left_box1" valign="top">*约定发薪日：</td>
                <td class="list_right_box">
                    <input class="mini-spinner" name="salaryDate" value="${salaryGroup.salaryDate}" style="width:90%" minValue="1" maxValue="31"
                        required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*状态：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" name="type" value="${salaryGroup.type == null ? 1 : salaryGroup.type}"
                        data="[{id:0,text:'无效'},{id:1,text:'有效'}]" style="width:90%"/>
                </td>
                <td class="list_left_box1" valign="top"></td>
                <td class="list_right_box"></td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" id="save" onclick="onOk('salary','salary_items','saveOrUpdateToGroup',false)" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();

        $(function() {
            var id = '${salaryGroup.id}';
            if (id == "") {
                var effectDate = mini.get("effectDate");
                effectDate.setValue(new Date());
            }
            onComChanged(null);
            if ('${flg}' != "" && '${flg}' != "success") {
                showTips();
                mini.get("save").hide();
            }
        });

        /**
         * 获取部门
         * zhanghj
         */
        function onComChanged(e) {
            var companyId = mini.get("companyId");
            var depId = mini.get("depId");
            var row = companyId.getSelected();
            if (row) {
                depId.setUrl("../basis/branch!depData.action?cid=" + row.id);
            }
        }

        function showTips() {
            mini.alert('${flg}');
        }
    </script>
</body>
</html>