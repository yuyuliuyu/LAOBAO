<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>字典详细信息</title>
    <style type="text/css">
        body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }
    </style> 
    <script src="${base}/template/miniui/boot.js" type="text/javascript"></script>
</head>

<body>
    <fieldset style="width:98%;border:solid 1px #aaa;position:relative;">
        <legend>字典详细信息</legend>
        <div id="editForm1" style="padding:5px;">
            <div style="padding-left:11px;padding-bottom:5px;">
                <input id="thesisid" class="mini-hidden" name="id" value="${dictionary.id}" style="display:none;"/>
                <table style="width:100%;">
                    <tr>
                        <td>字典名称：</td>
                        <td>
                            <input class="mini-textbox" name="name" value="${dictionary.name }" width="200px"
                                   vtype="maxLength:15" required="true" requiredErrorText="不能为空"/>
                        </td>
                        <td>字典类型：</td>
                        <td>
                            <input class="mini-textbox" id="type" name="type" value="${dictionary.type}" width="200px"
                                   vtype="maxLength:15" required="true" requiredErrorText="不能为空"/>
                        </td>
                    </tr>
                    <tr>
                        <td>标志：</td>
                        <td>
                            <input id="flg" name="flg" class="mini-combobox" data="flg" value="${dictionary.flg}"
                                   required="true" requiredErrorText="不能为空" width="200px"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:content;padding-top:5px;padding-left:500px;" colspan="6">
                            <a class="mini-button" href="javascript:updateRow();" iconCls="icon-save">保存</a>
                            <a class="mini-button" href="javascript:cancelRow();" iconCls="icon-close">取消</a>
                        </td> 
                    </tr>
                </table>
            </div>
        </div>
    </fieldset>
    
    <div class="mini-fit" style="margin-top: 0px">
        <div id="datagrid1" class="mini-datagrid" style="width:100%;" showPager="false"
             url="../dictionary/backend_dictionary!getdata.action" idField="id" 
             onselectionchanged="onSelectionChanged">
             
            <div property="columns">
                <div type="checkcolumn" width="20">选择</div>
                <div type="indexcolumn" headerAlign="center" width="20">序列</div>
                <div field="id" width="80" id="id" name="idColumn" headerAlign="center" allowSort="true" class="mini-hidden"></div>
                <div field="name" width="80" headerAlign="center" align="center">字典名称</div>
                <div field="type" width="80" headerAlign="center" align="center">字典类型</div>
                <div field="flg" width="60" headerAlign="center" align="center" renderer="onGenderRenderer">标志</div>
                <div field="time" width="80" headerAlign="center" align="center" dateFormat="yyyy-MM-dd HH:mm:ss">创建时间</div>
                <div name="action" width="60" headerAlign="center" align="center" renderer="onActionRenderer" cellStyle="padding:0;">操作</div>
            </div>
        </div>
    </div>
    
   <script type="text/javascript">
        var flg = [ {id : "T",text : '开'},{id : "F",text : '关'}];
        
        mini.parse();
        
        var editForm = document.getElementById("editForm1");
        var form = new mini.Form("editForm1");
        var grid = mini.get("datagrid1");
        
        grid.load();
        grid.hideColumn("idColumn");
        
        ////////////////////////////////////////////////////
        var Genders = [{ id: 'F', text: '关' }, { id: 'T', text: '开'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if(g.id=='0'){return "<font color='red'>"+g.text+"</font>";}else{if (g.id == e.value) return g.text;}
            }
            return "";
        }
        
        ////////////////////////////////////////////////////
        /* 删除按钮移动 */
        function onActionRenderer(e) {
            var record = e.record;
            var uid = record._uid;
            var s =  '<a class="Delete_Button" href="javascript:delRow(\'' + uid + '\')">删除</a>';
            
            return s;
        }
        
        ////////////////////////////////////////////////////
        function onSelectionChanged(e) {
            var grid = e.sender;
            var record = grid.getSelected();
            if (record) {
                editRow(record._uid);
            } else {
                form.reset();
            }
        }
        
        ////////////////////////////////////////////////////
        function newRow() {
            var row = {};
            grid.addRow(row, 0);
            editRow(row._uid);
        }
        
        ////////////////////////////////////////////////////
        function editRow(row_uid) {
            var row = grid.getRowByUID(row_uid);
            if (row) {
                //表单加载字典信息
                var form = new mini.Form("editForm1");
                
                if (grid.isNewRow(row)) {
                    form.reset();
                } else {
                    $.ajax({
                        url: "../dictionary/backend_dictionary!view.action?id="+row.id,
                        success: function (text) {
                            var o = mini.decode(text);
                            form.setData(o);
                            form.unmask();
                        }
                    });
                }
                grid.doLayout();
            }
        }
        
        ////////////////////////////////////////////////////
        function cancelRow() {
            window.location.reload(true);
            grid.reload();
        }
        
        ////////////////////////////////////////////////////
        function delRow(row_uid) {
            var row = grid.getRowByUID(row_uid);
            if (row) {
                mini.confirm("您确定要删除该信息吗？", "提醒", function (action) {
                    if (action == "ok") {
                        $.ajax({
                            url: "../dictionary/backend_dictionary!remove.action?id="+row.id,
                            type:'post',
                            success: function (text) {
                                if(text=="success"){
                                    mini.alert("删除成功！");
                                    window.location.reload(true);
                                }else{
                                    mini.alert(text);
                                }
                            }
                        });
                    }
                });
            } else {
                mini.alert("请选中一条记录！");
            }
        }
        
        ////////////////////////////////////////////////////
        /*编辑*/
        function updateRow() {
            var form = new mini.Form("editForm1");
            form.validate();
            if (form.isValid() == false) return;
            
            var o = form.getData();
            var json = mini.encode(o);
            
            var flg = mini.get("flg").getValue();
            
            //strut令牌
             window.parent.loading();
            
            $.ajax({
                url: "../dictionary/backend_dictionary!save.action",
                data: { data: json,flg:flg },
                success: function (text) {
                    window.parent.unmask();
                    if (text == "success") {
                        mini.showMessageBox({
                            title: "提醒",
                            width: 250,
                            iconCls: "mini-messagebox-warning",
                            buttons: ["ok"],
                            message: "保存成功！",
                            callback: function (action) {
                                //CloseWindow("ok");
                                window.location.reload(true);
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
    </script>
</body>
</html>
