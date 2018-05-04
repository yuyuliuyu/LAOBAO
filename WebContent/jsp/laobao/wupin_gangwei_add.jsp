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
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <a class="mini-button" iconCls="icon-add" onclick="addRow()">增加</a>
                    <a class="mini-button" iconCls="icon-remove" onclick="removeRow()">删除</a>
                </td>
            </tr>
        </table>
    </div>
	<div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
            allowAlternating="true" idField="id" allowResize="false" showPager="false"multiSelect="true"
            allowCellEdit="true" allowCellSelect="true">
            <div property="columns">
                <div type="checkcolumn" width="20"></div>
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                <div field="wpId" displayField="wpmc" width="100" headerAlign="center" align="center"vType="required;">物品名称*
                    <input property="editor" class="mini-buttonedit" textField="wpmc" onbuttonclick="onButtonEditAlert"/>
                </div>
                <div field="ggxh" width="100" headerAlign="center" align="center">规格型号</div>
                <div field="jldw" width="100" headerAlign="center" align="center">计量单位</div>
                <div field="lqjg" width="100" headerAlign="center" align="center" vType="required;">领取间隔*
                    <input property="editor" class="mini-spinner" style="width:100%;" minValue="0" maxValue="999999"/>
                </div>
                <div type="comboboxcolumn" field="lqdw" width="100" headerAlign="center" align="center"vType="required;">领取单位*
                    <input property="editor" class="mini-combobox" style="width:100%;" data="[{id:1, text:'年'}, {id:2, text:'月'}]" />
                    <!-- ,{id:3, text:'天'} -->
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>
	<script type="text/javaScript">
        mini.parse();
        var grid = mini.get("grid");

        /** 增加行 */
        function addRow() {
            var grid=mini.get("grid");
            var newRow = {};
            grid.selectAll(false);
            var leng = grid.getData().length;
            grid.addRow(newRow, leng);
            var row=grid.getRow(leng);
            grid.updateRow(row,row);
            grid.deselectAll (false);
        }

        /** 添加页面用到的删除行方法 */
        function removeRow() {
            var grid=mini.get("grid");
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                grid.removeRows(rows, false);
            }else{
                mini.alert("请选择需要删除的记录!");
            }
        }

        function onButtonEditAlert(e) {
        	var btnEdit = this;
        	mini.open({
                url: "../laobao/wupin_gangwei!thing.action",
                title: "物品选择",
                width: 780,
                height: 560,
                ondestroy: function (action) {
                	if (action == "ok") {
                		var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        btnEdit.setValue(data.id);
                        btnEdit.setText(data.wpmc);
                        var editor = grid.getEditorOwnerRow(e.sender);
                        grid.updateRow(editor, {ggxh: data.ggxh, jldw: data.jldw});
                	}
                }
        	});
        }

        function onOk() {
        	var gridjson="";
            if(grid){
                // 验证
                grid.validate();
                if (grid.isValid() == false) {
                    gridError(grid);
                    return;
                }
                var data = grid.getData();
                if(data.length<1){// 空行也算length
                    mini.alert("详细信息为空，保存失败！");
                    return;
                }

                // 判断grid是否存在相同的物品
                var hash = {};
                for ( var i = 0, l = data.length; i < l; i++) {
                    var row = data[i];
                    if(row.id == ""||typeof(row.id)== "undefined"){
                        row._state="added";
                        grid.updateRow(row,row);
                    }else{
                        row._state="modified";
                        grid.updateRow(row,row);
                    }
                    if(row.wpId != undefined && hash[row.wpId]) {
                        mini.alert("存在相同的物品：<font color='red'>"+row.wpmc +"</font>");
                        return;
                    }
                    hash[row.wpId] = true;
                }
                var griddata = grid.getChanges();
                gridjson= mini.encode(griddata);
            }

            window.parent.loading();
            $.ajax({
                url : "../laobao/wupin_gangwei!saveOrUpdate.action",
                type : "post",
                data : {
                	gwId: '${gwId}',
                    griddata : gridjson
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
                                document.location.reload();
                                window.CloseOwnerWindow();
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