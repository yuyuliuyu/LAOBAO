<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path ;
            pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>权限详细信息</title>
<script src="${base}/template/miniui/boot.js" type="text/javascript"></script>
<style type="text/css">
body {
    margin: 0;
    padding: 0;
    border: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
}
</style>
</head>

<body>
    <fieldset style="width:98%;border:solid 1px #aaa;position:relative;">
        <legend>权限详细信息</legend>
        <div id="editForm1" style="padding:5px;">
            <div style="padding-left:11px;padding-bottom:5px;">
                
                <input id="id" class="mini-hidden" name="id" value="${resource.id}" style="display:none;"/>
                
                <table style="width:100%;">
                    <tr>
                        <td>权限名称：</td>
                        <td>
                            <input name="resourcename" id="resourcename" class="mini-textbox" value="${resource.resourcename}"
                            	   onvalidation="onNamesakeValidation"
                                   required="true" requiredErrorText="不能为空" vtype="maxLength:20" width="180px" />
                        </td>
                        <td>权限路径：</td>
                        <td>
                            <input name="resourceurl" id="resourceurl" class="mini-textbox" value="${resource.resourceurl}"
                                   required="true" requiredErrorText="不能为空" width="180px" vtype="maxLength:70"/>
                        </td>
                    </tr>
                    <tr>
                        <td>排列次序：</td>
                        <td>
                            <input name="sortorder" id="sortorder" class="mini-textbox" value="${resource.sortorder}"
                                   required="true" requiredErrorText="不能为空" vtype="float;maxLength:5" width="180px"/>
                        </td>
                        <td>权限描述：</td>
                        <td>
                            <input name="description" id="description" class="mini-textarea" value="${resource.description}"
                                   vtype="maxLength:100" width="180px"/>
                        </td>
                    </tr>
                    <tr>
                        <td>显示与否：</td>
                        <td>
                            <input id="state"  name="state" class="mini-combobox" data="source" value="${resource.state}"
                                   required="true" requiredErrorText="不能为空" width="180px"/>
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
    
    <input id="presource" name="presource" id="presource" class="mini-hidden" value="${parent}" style="display:none;"/>
    
    <div class="mini-fit" style="margin-top:0px">
        <div id="datagrid1" class="mini-treegrid" style="width:100%;height:100%;"
             url="backend_res!getauth.action" idField="id" onselectionchanged="onSelectionChanged">
             
            <div property="columns">
                <div type="checkcolumn" width="35">选择</div>
                <div type="indexcolumn" headerAlign="center" width="35">序列</div>
                <div field="id" id="id" width="80" name="idColumn" headerAlign="center" allowSort="true" class="mini-hidden"></div>
                <div name="resourcename" field="resourcename" headerAlign="center" align="center" width="80">权限名称</div>
                <div field="resourceurl" headerAlign="center" align="center" width="170">权限路径</div>
                <div field="sortorder" headerAlign="center" align="center" width="80">排列次序</div>
                <div field="description" headerAlign="center" align="center" width="80">权限描述</div>
                <div field="state" headerAlign="center" align="center" width="80" renderer="onGenderRenderer">显示与否</div>
                <div field="cz" headerAlign="center" align="center" renderer="onActionRenderer" width="80">操作</div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
        var source = [ {id : 0,text : '否'},{id : 1,text : '是'}];
        
        mini.parse();
        
        var form = new mini.Form("editForm1");
        
        var grid = mini.get("datagrid1");
        
        grid.load({parent:"${parent}"});
        grid.hideColumn("idColumn");
        
        /////////////////////////////////////////////////////////////////
        var Genders = [{ id: 0, text: '不显示' }, { id: 1, text: '显示'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        
        /* 删除按钮移动 */
        function onActionRenderer(e) {
            var record = e.record;
            var uid = record._uid;
            var s = '<a class="Delete_Button" href="javascript:delRow(\'' + uid + '\')">删除</a>';
            
            return s;
        }
        
        ///////////////////////////////////////////////////////
        function onSelectionChanged(e) {
            var grid = e.sender;
            var record = grid.getSelected();
            if (record) {
                editRow(record._uid);
            } else {
                form.reset();
            }
        }
        
        function newRow() {
            var row = {};
            grid.addRow(row, 0);
            editRow(row._uid);
        }
        
        function editRow(row_uid) {
            var row = grid.getRowByUID(row_uid);
            if (row) {
                //表单加载字典信息
                var form = new mini.Form("editForm1");
                
                if (grid.isNewRow(row)) {
                    form.reset();
                } else {
                    form.loading();
                    $.ajax({
                        url : "../resource/backend_res!view.action?id="+ row.id,
                        success : function(text) {
                            var o = mini.decode(text);
                            form.setData(o);
                            form.unmask();
                        }
                    });
                }
                grid.doLayout();
            }
        }
        
        function cancelRow() {
            window.location.reload(true);
            grid.reload();
        }
        
        function delRow(row_uid) {
            var row = grid.getRowByUID(row_uid);
            if (row) {
                mini.confirm("您确定要删除该信息吗？", "提醒", function (action) {
                    if (action == "ok") {
                        $.ajax({
                            url : "../resource/backend_res!remove.action?id=" + row.id,
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
        
        /*修改*/
        function updateRow() {
            var form = new mini.Form("editForm1");
            form.validate();
            if (form.isValid() == false)
                return;
            
            var o = form.getData();
            var json = mini.encode(o);
            
            //strut令牌
             window.parent.loading();
            $.ajax({
                url : "../resource/backend_res!authadd.action",
                data : {data : json,
                        parent:mini.get("presource").getValue()
                       },
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
                }
            });
        }
        
        function onNamesakeValidation(e){
       	 if (e.isValid) {
       		 var presource=mini.get("presource").getValue();
       		 var resourceName=mini.get("resourcename").getValue();
       		 if(presource==resourceName){
       			 e.errorText = "权限名称不能与父级资源同名！";
                    e.isValid = false
       		 }
       	 }
       }
    </script>
</body>
</html>
