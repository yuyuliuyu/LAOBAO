<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
    String flag=request.getParameter("flag");
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>岗位物品信息增加</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
            <span style="padding-left:5px;">物品列表</span>
        </div>
    <div class="mini-fit" id="form1">
        <div id="dept_grid" class="mini-datagrid" style="width:100%;height:100%" pageSize="10" multiSelect="true"
             allowAlternating="true" idField="id" allowResize="false" allowCellEdit="true" allowCellSelect="true">
            <div property="columns">
                <div type="checkcolumn"  width="40"></div>
                <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                <div field="wpmc" id ="wpmc" name = "wpmc"width="100" headerAlign="center" align="center">物品名称</div>
                <div field="count" width="80" headerAlign="center" align="center" vType="required;">领取数量*
                    <input property="editor" class="mini-spinner" style="width:100%;" minValue="1" maxValue="99999999"/>
                </div>
                
                <div field="ggxh" id ="ggxh" name = "ggxh"width="100" headerAlign="center" align="center">规格型号</div>
                <div field="jldw" id ="jldw" name = "jldw"width="100" headerAlign="center" align="center">计量单位</div>
                <div field="gys" id ="gys" name = "gys"width="100" headerAlign="center" align="center">供应商</div>
                <div field="lqjg" width="100" headerAlign="center" align="center">领取间隔</div>
                <div type="comboboxcolumn" readOnly="true" field="lqdw" width="100" headerAlign="center" align="center"vType="required;">领取单位
                    <input property="editor" class="mini-combobox" style="width:100%;" data="[{id:1, text:'年'}, {id:2, text:'月'}]" />
                </div>
                <div field="createDate" width="140" headerAlign="center" align="center" renderer="onLongDateRenderer">创建时间</div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
        <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a>
    </div>
    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("dept_grid");
        grid.hideColumn("id");
        grid.setUrl("../laobao/wupin_gangwei!getCurStaffWupinData.action?staffId=" + '${id}');
        var searchData = "{'postId': '"+'${data}'+"'}";
        grid.load({searchData : searchData});
        
        function onButtonEdit(e) {
            
            var btnEdit = this;
            mini.open({
                url: "../basis/branch!tree.action",
                showMaxButton: false,
                title: "选择部門",
                width: 250,
                height: 330,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                    
                        if (data) {
                            btnEdit.setValue(data.id);
                            btnEdit.setText(data.name);
                        }
                    }
                }
            });
            
        }
        function isEnglishAndNum(e){
            var rex = new RegExp("^[A-Za-z]+$");
            if(rex.test(e)){
                return true;
            }else{
                return false;
            }
            
        }
        function onOk(){
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                var data = rows;
                for ( var i = 0, l = data.length; i < l; i++) {
                    var row = data[i];
                    if (!row.count) {
                    	mini.alert("数量不能为空");
                        return;
                    }
                }
                window.parent.loading();
                $.ajax({
                     url: "../laobao/wupin_gangwei!saveLingquWp.action",
                         type : "post",
                     data : {
                         staffId : '${id}',
                         griddata: mini.encode(rows)
                     },
                     success: function (text) {
                         window.parent.unmask();
                         if (text == "success") {
                                 mini.showMessageBox({
                                 title: "提醒",
                                 width: 250,
                                 iconCls: "mini-messagebox-warning",
                                 buttons: ["ok"],
                                 message: "申请成功！",
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
            } else {
                mini.alert("请选择一条数据");
            }
        }
        
        function onCancel(e) {
             CloseWindow("cancel");
        }
        
        function onReload() {
            document.location.reload();
        }
        
        function CloseWindow(action) {
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }    

        /**
         * 长日期格式化(yyyy-MM-dd HH:mm:ss)
         * zhanghj
         **/
        function onLongDateRenderer(e) {
            var value = e.value;
            if (value) return mini.formatDate(new Date(value), "yyyy-MM-dd HH:mm:ss");
            return "";
        }
    </script>
</body>
</html>