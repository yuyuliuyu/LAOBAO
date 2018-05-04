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
    <title>审核流程选择</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit" id="form1">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width:30%">审批岗位：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" id="nodeId" name = "nodeId"/>
                    <input class="mini-hidden" id="spgwName" name = "spgwName"/>
                    <input class="mini-hidden" id="staffName" name = "staffName"/>
                    <input class="mini-combobox" name="spgw" onValueChanged="onValueChanged" url="../system/work_flow!getDataByPId.action?pid=${pid}"
                        style="width: 90%;" required="true" popupWidth="auto"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">审批人：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" id="spr" name="spr" style="width: 90%;" required="true" onValueChanged="onStaffValueChanged"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="checkSub()" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var retResult = 0;
        var spr = mini.get("spr");
        var nodeId = mini.get("nodeId");

        function onValueChanged(e) {
        	var selected = e.selected;
            var url = "../system/work_flow!getUserByPostposit.action?pid="+e.value+"&companyId="+selected.companyId;
            spr.setUrl(url); 
            spr.select(0);
            var selected = e.selected;
            nodeId.setValue(selected.curNode);
            mini.get("spgwName").setValue(e.selected.text);
        }

        function GetData() {
            return retResult;
        }

        function checkSub() {
            var form = new mini.Form("#form1");
            if(form){
                form.validate();
                if (form.isValid() == false)return;
                // 提交表单数据
                var formdata = form.getData();
                retResult = mini.encode(formdata); 
            }
            mini.confirm("提交后将不能再调整发放数据，您确定要提交审核吗？","提醒", function(action) {
                if (action=="ok") {
                    CloseWindow("ok");
                }
            });
        }

        function onStaffValueChanged(e) {
        	mini.get("staffName").setValue(e.selected.text);
        }

    </script>
</body>
</html>