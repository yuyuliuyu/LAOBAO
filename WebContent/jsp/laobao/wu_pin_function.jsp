<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
    String flag=request.getParameter("flag");
    String id = (String)request.getAttribute("id");
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>增加物品</title>
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
                <td class="list_left_box1" valign="top">物品名称：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" name="id" id="id" value="${wupin.id}" style="display:none;"/>
                    <input id="wpmc" name="wpmc" class="mini-textbox" style="width:150px;" value="${wupin.wpmc}" 
                       required="true" requiredErrorText="不能为空" vtype="maxLength:50"/>
                </td>
                <td class="list_left_box1" valign="top">规格型号：</td>
                <td class="list_right_box">
                    <input id="ggxh" name="ggxh" class="mini-textbox" style="width:150px;" required="true" requiredErrorText="不能为空" 
                        value="${wupin.ggxh}" vtype="maxLength:100"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">计量单位：</td>
                <td class="list_right_box">
                    <input id="jldw" name="jldw" class="mini-textbox" style="width:150px;" required="true" requiredErrorText="不能为空" 
                        value="${wupin.jldw}" vtype="maxLength:100"/>
                </td>
                <td class="list_left_box1" valign="top">供应商：</td>
                <td class="list_right_box">
                    <input id="gys" name="gys" class="mini-textbox" style="width:150px;" required="true" requiredErrorText="不能为空" 
                        value="${wupin.gys}" vtype="maxLength:100"/>
                </td>
            </tr>
<%--             <tr>
                <td class="list_left_box1" valign="top">库存数量：</td>
                <td class="list_right_box">
                    <input id="kcsl" name="kcsl" class="mini-textbox" style="width:150px;" required="true" requiredErrorText="不能为空" 
                        value="${wupin.kcsl}" vtype="maxLength:100"/>
                </td>
            
            </tr> --%>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
        <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
        <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
    </div>
    
    <script type="text/javascript">
        mini.parse();
        
        //var tree = mini.get("treegrid1");
        
        var showAllSelect = true;
        
        function ondrawcell(e) {
            var tree = e.sender,
            record = e.record,
            field = e.field,
            id = record[tree.getIdField()],
            funs = record.functions;
            
            function createCheckboxs(funs) {
                if (!funs) return "";
                var html = "";
                if (showAllSelect) {
                    var value = record.checkAll !== false ? "全选" : "取消";
                    var clickFn = 'checkAllFunc(\'' + id + '\', this)';
                    html += '<input onclick="' + clickFn + '" type="button" value="' + value + '" style="border:solid 1px #aaa;"/>';
                }
                for (var i = 0, l = funs.length; i < l; i++) {
                    var fn = funs[i];
                    var clickFn = 'checkFunc(\'' + id + '\',\'' + fn.action + '\', this.checked)';
                    var checked = fn.checked ? 'checked' : '';
                    html += '<label class="function-item"><input onclick="' + clickFn + '" ' + checked + ' type="checkbox" name="'
                            + fn.action + '" hideFocus/>' + fn.name + '</label>';
                    if(i>=6&&i%6==0)
                        html += '</br>';
                }
                return html;
            }
            if (field == 'functions') {
                e.cellHtml = createCheckboxs(funs);
            }
        }
        
        function getFuns() {
            var data = tree.getData();
            var json = mini.encode(data);
            mini.alert(json);
        }
        
        function checkFunc(id, action, checked) {
            var record = tree.getRecord(id);
            if(!record) return;
            var funs = record.functions;
            if (!funs) return;
            function getAction(action) {
                for (var i = 0, l = funs.length; i < l; i++) {
                    var o = funs[i];
                    if (o.action == action) return o;
                }
            }
            var obj = getAction(action);
            if (!obj) return;
            obj.checked = checked;
        }
        
        function checkAllFunc(id, btn) {
            var record = tree.getRecord(id);
            if (!record) return;
            var funs = record.functions;
            if (!funs) return;
            var checked = record.checkAll !== false;
            for (var i = 0, l = funs.length; i < l; i++) {
                var o = funs[i];
                o.checked = checked;
            }
            record.checkAll = !checked;
            tree.updateRow(record);
        }
        
        ///////////////////////////////////////////////////////////////
        function onOk() {
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            
           var data = form.getData();
           var json = mini.encode(data);
            window.parent.loading();
            $.ajax({
                url : "../laobao/wu_pin!saveOrUpdate.action",
                type : "post",
                data : {
                     formdata: json
                },
                success : function(text) {
                    window.parent.unmask();
                    if (text == "success") {
                        mini.showMessageBox({
                            title: "提醒",
                            width: 250,
                            iconCls: "mini-messagebox-warning",
                            buttons: ["ok"],
                            message: "保存成功！",
                            callback: function (action) {
                                CloseWindow("ok");
                            }
                        }); 
                    } else {
                        if(text.length>0){
                            mini.alert(text);
                        }
                    }
                }/* ,
                error : function(jqXHR, textStatus, errorThrown) {
                   // mini.alert(jqXHR.responseText);
                } */
            });
        }
        
        ///////////////////////////////////////////////////////////
        function CloseWindow(action) {
            if (window.CloseOwnerWindow)
                return window.CloseOwnerWindow(action);
            else
                window.close();
        }
        
        function onCancel() {
            CloseWindow("cancel");
        }
        
        function onReload() {
            document.location.reload();
        }
    </script>
</body>
</html>