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
    <title>全局公式编辑</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div id="form1" class="mini-fit">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top">父级：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" id="id" name="id" value="${formula.id}"/>
                    <input class="mini-hidden" id="pid" name="pid" value="${pid}"/>
                    <input id="btnEdit1" class="mini-buttonedit" onbuttonclick="onButtonEdit" width="200px"
                        value="${pid}" text="${parentName}" allowInput="false"/>
                </td>
                <td class="list_left_box1" valign="top">*名称：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="method" value="${formula.method}" maxLength="500" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*公式(中文)方法名：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="methodCn" value="${formula.methodCn}" maxLength="500" required="true"/>
                </td>
                <td class="list_left_box1" valign="top">*公式(英文)方法名：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="methodEn" value="${formula.methodEn}" maxLength="500" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*执行顺序：</td>
                <td class="list_right_box">
                    <input class="mini-spinner" style="width:90%" name="executeIndex" value="${formula.executeIndex}" minValue="1" maxValue="9999" required="true"/>
                </td>
                <td class="list_left_box1" valign="top">*排列顺序：</td>
                <td class="list_right_box">
                    <input class="mini-spinner" style="width:90%" name="lineIndex" value="${formula.lineIndex}" minValue="1" maxValue="999999" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*类：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textbox" style="width:90%" name="pubClass" value="${formula.pubClass}" maxLength="100" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">说明：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" value="${formula.description}" name="description" style="width: 90%;height:80px" maxLength="1000"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onok" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();

        function onok() {
        	// 表单部分
            var form = new mini.Form("#form1");
            var formjson="";
            if(form){
                form.validate();
                if (form.isValid() == false)return;
                // 提交表单数据
                var formdata = form.getData();
                formjson = mini.encode(formdata); 
            }
            window.parent.loading();
            $.ajax({
                url : "../salary/formula!saveOrUpdate.action",
                type : "post",
                data : {
                    formdata : formjson
                },
                success : function(text) {
                    window.parent.unmask();
                    if (text == "success") {
                        mini.showMessageBox({
                            title: "提醒",
                            width: 250,
                            iconCls: "mini-messagebox-warning",
                            buttons: ["ok"],
                            message: "保存成功",
                            callback: function (action) {
                            	CloseWindow("ok")
                            }
                        });
                    } else {
                        if(text.length>0){
                            mini.alert(text);
                        }
                    }
                }
            });
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

        function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "../salary/formula!tree.action",
                showMaxButton: false,
                title: "选择父级资源",
                width: 300,
                height: 440,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        mini.get("pid").setValue(data.id);
                        mini.get("btnEdit1").setValue(data.id);
                        mini.get("btnEdit1").setText(data.method);
                    }
                }
            });
        } 
    </script>
</body>
</html>