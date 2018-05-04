<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
String id = (String)request.getAttribute("id");
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>角色提醒页面</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <!--引入皮肤样式-->
    <link href="${base}/template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    <style>
        body{
            margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
        }
        .function-item{
            margin-left:5px;
            margin-right:5px;
        }
        .function-item input{
            vertical-align:bottom;
        }
    </style>
</head>

<body>
    <%--<input type="button" value="获取权限" onclick="getFuns()" /> --%>
    
    <form id="form" method="post">
        <div class="mini-toolbar" style="text-align:center;line-height:30px;" 
             borderStyle="border-left:0;border-top:0;border-right:0;">
              <label>角色名称:</label>
              <%if(id.equals("null")){ %>
                  <input id="rname" class="mini-textbox" style="width:150px;" value="${backendRole.name}" required="true" requiredErrorText="不能为空" />
              <%}else{ %>
                  <input id="rname" class="mini-textbox" style="width:150px;" value="${backendRole.name}" enabled="false"/>
              <%} %>
        </div>
    </form>
    
    <div class="mini-fit" >
        <div id="treegrid1" class="mini-treegrid" style="width:100%;height:100%;"
            url="../resource/backend_res!getaudetailMh.action?id=<%=id%>"
            treeColumn="name" idField="id" parentField="pid" resultAsTree="false"  
            allowResize="false" expandOnLoad="false" showTreeIcon="true"
            allowSelect="false" allowCellSelect="false" enableHotTrack="false"
            ondrawcell="ondrawcell" >
            
           <div property="columns">
                <div type="indexcolumn" headerAlign="center" align="center" width="50">序列</div>
                <div name="name" field="name" width="250" >模块名称</div>
                <div field="functions" width="100%">权限</div>
           </div>
           
       </div>
    </div>
    
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
        <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
        <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
    </div>
    
    <script type="text/javascript">
        mini.parse();
        
        var tree = mini.get("treegrid1");
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
        
        ///////////////////////////////////////////////////
        function onOk() {
            var form = new mini.Form("form");
            form.validate();
            if (form.isValid() == false) return;
            
            var data = tree.getData();
            var json = mini.encode(data);
            
            //strut令牌
             window.parent.loading();
            $.ajax({
                url : "../system/backend_role!modifyMh.action",
                type : "post",
                data : {
                    data : json, id:"<%=id%>"
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
                }
            });
        }
        
        ///////////////////////////////////////////////
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