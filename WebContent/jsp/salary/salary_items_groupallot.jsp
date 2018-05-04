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
    <title>薪资组工资套分配</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <style type="text/css">
        .link {text-decoration: none;}
    </style>
</head>

<body>
    <div id="form1">
        <input class="mini-hidden" name="id" id="id" value="${salaryGroupWage.id}" style="display:none;"/>
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top">公司：</td>
                <td class="list_right_box">
                    <input class="mini-combobox asLabel" readOnly="true" id="companyId" name="companyId" textField="fzx" url="../salary/salary_manage!getBranches.action"
                        value="${companyId}" style="width:90%"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 15%">*薪资组：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" style="width: 90%" name="salaryGroupId" value="${salaryGroupWage.salaryGroupId}"
                        url="../salary/salary_items!getSalaryGroupEffectListData.action?companyId=${companyId}" textField="name"required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 15%">*生效日期：</td>
                <td class="list_right_box">
                    <input class="mini-datepicker" id="effectDate" name="effectDate" value="${salaryGroupWage.effectDate}" style="width: 90%"required="true"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 15%">引入工资套：</td>
                <td class="list_right_box">
                    <input class="mini-buttonedit" id="salaryWageId" name="salaryWageId" showClose="true" onbuttonclick="onButtonEditAlert"
                        oncloseclick="onCloseClick" style="width: 90%"value="${salaryGroupWage.salaryWageId}" text="${yinWage}"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 15%">*工资套名称：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" name="name" value="${salaryGroupWage.name}" style="width: 90%" maxLength="100" required="true"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 15%">*工资套类型：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" style="width: 90%" id="sign"name="sign" value="${salaryGroupWage.sign}" 
                        data="[{id:1, text:'薪酬期间工资'},{id:2, text:'绩效奖励'},{id:3, text:'提成工资'},{id:4, text:'离职结算'}]" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 15%">*状态：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" data="[{id:0,text:'无效'},{id:1,text:'有效'}]" name="type"
                        value="${salaryGroupWage.type == null?1:salaryGroupWage.type}" style="width: 90%"required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 15%">描述：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" style="width: 90%;height:60px" name="note" value="${salaryGroupWage.note}"maxLength="1000"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div class="mini-splitter" style="width:100%;height:100%;" handlerSize="-1" borderStyle="border:0">
            <div size="50%">
                <div class="mini-fit">
                    <fieldset style="height: 94%">
                        <legend style="font-size: 14px;font-weight: bold;">引用项目</legend>
                        <div class="mini-fit">
                            <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20" url="../salary/salary_items!getGroupWageTypeListData.action?id=${salaryGroupWage.id}&sign=1"
                                allowAlternating="true" idField="id" allowResize="false"showPager="false"borderStyle="border-top : 1px solid #C9C9C9;">
                                <div property="columns">
                                    <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                                    <div field="salaryWageId" id="salaryWageId" width="10" name="salaryWageId" class="mini-hidden"></div>
                                    <div type="indexcolumn" headeralign="center"  width="50">序列</div>
                                    <div field="gs" width="40" headerAlign="center" align="center">公式</div>
                                    <div field="itemId" id = "itemId" name="itemId" width="100" headerAlign="center" align="center">薪资项目ID</div>
                                    <div field="name" width="100" headerAlign="center" align="center">项目名称</div>
                                    <div type="comboboxcolumn" field="itemType" readOnly="true" width="100" headerAlign="center" align="center">项目类型
                                        <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945cc8c4c8015cc926f00d0008"
                                            textField="dicname" valueField="dicvalue"/>
                                    </div>
                                    <div field="sx" width="70" headerAlign="center" align="center" vtype="required;">显示顺序</div>
                                    <div type="comboboxcolumn" field="isDisplay" width="60" headerAlign="center" align="center" vtype="required;">默认显示
                                        <input property="editor" class="mini-combobox" style="width:100%;" data="[{id:0,text:'不显示'},{id:1,text:'显示'}]" />
                                    </div>
                                    <div type="comboboxcolumn" field="type" width="40" headerAlign="center" align="center" vtype="required;">状态
                                        <input property="editor" class="mini-combobox" style="width:100%;" data="[{id:0,text:'无效'},{id:1,text:'有效'}]" />
                                    </div>
                                    <div field="high" width="60" headerAlign="center" align="center">上限</div>
                                    <div field="low" width="60" headerAlign="center" align="center">下限</div>
                                    <div type="comboboxcolumn" field="isEr" width="120" headerAlign="center" align="center" vtype="required;">是否二次分配
                                        <input property="editor" class="mini-combobox" style="width:100%;" data="[{id:0,text:'否'},{id:1,text:'是'}]" />
                                    </div>
                                    <div field="zljs" width="90" headerAlign="center" align="center" vtype="required;">总量计算比较
                                        <input property="editor" class="mini-spinner" style="width:100%;" minValue="0" maxValue="999"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
            <div>
                <div class="mini-fit">
                    <fieldset style="height: 94%">
                        <legend style="font-size: 14px;font-weight: bold;">包含项目</legend>
                        <div class="mini-toolbar" style="padding:0px;">
                            <table style="width:100%;">
                                <tr>
                                    <td style="width:100%;">
                                        <a class="mini-button" name="flgStatus" iconCls="icon-add" onclick="chooseItems()">选择薪资项目</a>
                                        <a class="mini-button" name="flgStatus" iconCls="icon-upgrade" onclick="moveup(1)">上移</a>
                                        <a class="mini-button" name="flgStatus" iconCls="icon-downgrade" onclick="movedown(1)">下移</a>
                                        <a class="mini-button" name="flgStatus" onclick="moveup(2)">置顶</a>
                                        <a class="mini-button" name="flgStatus" onclick="movedown(2)">置底</a>
                                        <a class="mini-button" name="flgStatus" iconCls="icon-remove" onclick="removeRow()">删除</a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="mini-fit">
                            <div id="grid2" class="mini-datagrid" style="width:100%;height:100%;" url="../salary/salary_items!getGroupWageTypeListData.action?id=${salaryGroupWage.id}&sign=2" pageSize="20"
                                allowAlternating="true" idField="id" allowResize="false" showPager="false"multiSelect="true"
                                allowCellEdit="true" allowCellSelect="true" oncellvalidation="onItemsCellValidation">
                                <div property="columns">
                                    <div type="checkcolumn" width="30"></div>
                                    <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                                    <div type="indexcolumn" headeralign="center"  width="50">序列</div>
                                    <div field="gs" width="36" headerAlign="center" align="center">公式</div>
                                    <div field="itemId" id = "itemId" name="itemId" width="100" headerAlign="center" align="center">薪资项目ID</div>
                                    <div field="name" width="100" headerAlign="center" align="center">项目名称</div>
                                    <div type="comboboxcolumn" field="itemType" readOnly="true" width="100" headerAlign="center" align="center">项目类型
                                        <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945cc8c4c8015cc926f00d0008"
                                            textField="dicname" valueField="dicvalue"/>
                                    </div>
                                    <div field="sx" width="70" headerAlign="center" align="center" vtype="required;">显示顺序
                                        <input property="editor" class="mini-spinner" style="width:100%;" minValue="0" maxValue="999999"/>
                                    </div>
                                    <div type="comboboxcolumn" field="isDisplay" width="60" headerAlign="center" align="center" vtype="required;">默认显示
                                        <input property="editor" class="mini-combobox" style="width:100%;" data="[{id:0,text:'不显示'},{id:1,text:'显示'}]" />
                                    </div>
                                    <div type="comboboxcolumn" field="type" width="40" headerAlign="center" align="center" vtype="required;">状态
                                        <input property="editor" class="mini-combobox" style="width:100%;" data="[{id:0,text:'无效'},{id:1,text:'有效'}]" />
                                    </div>
                                    <div field="high" width="60" headerAlign="center" align="center">上限
                                        <input property="editor" class="mini-textbox" vtype="float" style="width:100%;"  minValue="-9999.99" maxValue="999999.9"/>
                                    </div>
                                    <div field="low" width="60" headerAlign="center" align="center">下限
                                        <input property="editor" class="mini-textbox" vtype="float" style="width:100%;"  minValue="-9999.99" maxValue="999999.9"/>
                                    </div>
                                    <div type="comboboxcolumn" field="isEr" width="120" headerAlign="center" align="center" vtype="required;">是否二次分配
                                        <input property="editor" class="mini-combobox" style="width:100%;" data="[{id:0,text:'否'},{id:1,text:'是'}]" />
                                    </div>
                                    <div field="zljs" width="90" headerAlign="center" align="center" vtype="required;">总量计算比较
                                        <input property="editor" class="mini-spinner" style="width:100%;" minValue="0" maxValue="999"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="deploy()" style="margin-right:20px;" iconCls="icon-reload">分析公式</a>
        <a class="mini-button" id="save" onclick="onOKClose()" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.hideColumn("id");
        grid.hideColumn("itemId");
        grid.hideColumn("salaryWageId");
        grid.load();
        var grid2 = mini.get("grid2");
        grid2.hideColumn("id");
        grid2.hideColumn("itemId");
        grid2.load();
        var sign = mini.get("sign");

        $(function() {
            var id = '${salaryGroupWage.id}';
            if (id == "") {
                var effectDate = mini.get("effectDate");
                effectDate.setValue(new Date());
            } else {
                sign.disable();
            }
            if ('${flg}' !="" && '${flg}' != "success") {
                mini.alert('${flg}');
                mini.get("save").disable();
                var flgStatus = mini.getsbyName("flgStatus");
                for(var i=0; i < flgStatus.length; i++){
                    var link = flgStatus[i];
                    link.disable();
                }
            }
        });

        function edit() {
            var row = grid.getSelected();
            if(row){
                 var id = row.id;
                 mini.open({
                        url : "../salary/salary_items!groupadd.action?id="+id,
                        title : "增加薪资组",
                        width : 680,
                        height : 320,
                        onload : function() {
                        },
                        ondestroy : function(action) {
                            grid.reload();
                        }
                    });
            }else{
                mini.alert("请选择一条记录!");
                return;
            }
        }

        function onButtonEdit1(btnEdit,title,url,width,height,property,names,columns,inputs,attrs,gridId,inputId){
            var w=400;
            var h=300;
            if(width)w=width;
            if(height)h=height;
            if(names && columns){
                var nameArray=names.split(",");
                var columnArray=columns.split(",");
                for(var i=0;i<nameArray.length;i++){
                    if(i==0){
                        url=url+"?"+columnArray[0]+"="+mini.get(nameArray[0]).getValue();
                    }else{
                        url=url+"&"+columnArray[i]+"="+mini.get(nameArray[i]).getValue();
                    }
                }
            }
            mini.open({
                url: url,
                showMaxButton: false,
                title: title,
                width: w,
                height: h,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        if (data) {
                            if(property){
                                btnEdit.setText(data[property]);
                            }else{
                                btnEdit.setText(data.text);
                            }
                            btnEdit.setValue(data.id);
                            
                            if(inputs && attrs){
                                var inputsArray=inputs.split(",");
                                var attrsArray=attrs.split(",");
                                for(var i=0;i<inputsArray.length;i++){
                                    var button=mini.get(inputsArray[i]);
                                    button.setValue(data[attrsArray[i]]);
                                }
                            }
                            if(gridId)mini.get(gridId).load();
                            if(inputId){
                                mini.get(inputId).setValue("");
                                if(mini.get(inputId).setText)mini.get(inputId).setText("");
                            }
                        }
                        sign.setValue(data.sign);
                        sign.disable();
                        grid.setUrl("../salary/salary_items!getWageSalaryListData.action?id="+data.id);
                        grid.load();
                    }else{
                        btnEdit.setValue("");
                        btnEdit.setText("");
                        
                        if(inputs && attrs){
                            var inputsArray=inputs.split(",");
                            for(var i=0;i<inputsArray.length;i++){
                                var button=mini.get(inputsArray[i]);
                                button.setValue("");
                            }
                        }
                        if(gridId)mini.get(gridId).load();
                        if(inputId){
                            mini.get(inputId).setValue("");
                            if(mini.get(inputId).setText)mini.get(inputId).setText("");
                        }
                    }
                }
            });
        }

        function onButtonEditAlert(e) {
            onButtonEdit1(e.sender, "工资套", "../salary/salary_items!wageyin.action", 860, 540,'name',"id,companyId","id,companyId");
        }

        function onOKClose() {
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
            var gridData = grid.getData();
            var gridJson= mini.encode(gridData);
            // 表格部分
            var gridjson="";
            var data1 = grid.getData();
            var data = grid2.getData();
            if(data1.length<1 && data.length<1){
                mini.alert("请选择薪资项目");
                return;
            }
            if(grid2){
                // 验证
                grid2.validate();
                if (grid2.isValid() == false) {
                    gridError(grid2);
                    return;
                }
                var data = grid2.getData();
                if(data1.length<1 && data.length<1){// 空行也算length
                    mini.alert("请选择薪资项目");
                    return;
                }
                // 判断grid是否存在相同的薪资项目
                var hash = {};
                // 两边相同的项目
                var sameItem = "";
                for ( var i = 0, l = data.length; i < l; i++) {
                    var row = data[i];
                    if(row.id == ""||typeof(row.id)== "undefined"){
                        row._state="added";
                        grid2.updateRow(row,row);
                    }else{
                        row._state="modified";
                        grid2.updateRow(row,row);
                    }
                    if(row.itemId != undefined && hash[row.itemId]) {
                        mini.alert("存在相同的薪资项目：<font color='red'>"+row.name +"</font>");
                        return;
                    }
                    if (row.changeItem != 1) {
                        hash[row.itemId] = true;
                    }
                    for (var ii = 0, ll = gridData.length; ii < ll; ii++) {
                        var row1 = gridData[ii];
                        if (row.itemId == row1.itemId) {
                            sameItem += row.name + "、";
                        }
                    }
                }
                if (sameItem != "") {
                    sameItem = sameItem.substring(0, sameItem.length-1);
                    mini.alert(sameItem+" 存在相同的薪资项目");
                    return;
                }
                var griddata = grid2.getChanges();
                gridjson= mini.encode(griddata);
            }
            window.parent.loading();
            $.ajax({
                url : "../salary/salary_items!saveOrUpdateSalaryGroupWage.action",
                type : "post",
                data : {
                    formdata : formjson,
                    griddata : gridJson,
                    gridData : gridjson
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
        /*******************************************************************************
         * grid 错误提示
         ******************************************************************************/
        function gridError(grid){
            var error = grid.getCellErrors()[0];
            var head = error.column.header.replace(/[\r\n]/g,"").replace(/[ ]/g,"");
            var errorText = error.errorText;
            mini.showMessageBox({
                 title: "提醒",
                 iconCls: "mini-messagebox-warning",
                 buttons: ["ok"],
                 message: head+":"+errorText+",请修改后重新操作!",
                 callback: function (action) {
                      grid.setSelected(error.record);
                      grid.beginEditCell(error.record, error.column);
                      return;
                   }
                 });
        }

        function onCloseClick(e) {
            var obj = e.sender;
            obj.setText("");
            obj.setValue("");
            sign.enable();
            grid.clearRows();
        }

        function tips() {
            mini.alert("不是数值，不能添加公式");
        }

        function gs(id, itemId, tpe, readOnly) {
            mini.open({
                url : "../salary/salary_formula!list.action?id="+id+"&wageId=${salaryGroupWage.id}&itemId=" + itemId+"&type="+tpe+"&readOnly="+readOnly+"&depId=${depId}",
                title : "公式列表",
                width : 680,
                height : 540,
                onload : function() {
                },
                ondestroy : function(action) {
                    if (tpe == 1) grid.reload();
                    if (tpe == 2) grid2.reload();
                }
            });
        }

        function onItemsCellValidation(e) {
            var field = e.field, record = e.record;
            // 上限
            if (field == "high") {
                if (record < 0 || record > 99999.99) {
                    e.errorText = "输入的值不合法";
                    e.isValid = false;
                }
            }
        }

        function chooseItems() {
            var data = grid2.getData();
            var items = "";
            if (data.length > 0) {
                items = data[0].itemId;
            }
            for (var i = 1, l = data.length; i < l; i++) {
                var row = data[i];
                items += "," + row.itemId;
            }
            mini.open({
                url : "../salary/salary_items!choose.action?companyId=${depId}&items="+items,
                title : "选择薪资项目",
                width : 840,
                height : 540,
                onload : function() {
                },
                ondestroy : function(action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        if (data.length > 0) {
                            var eLen = grid.getData().length;
                            var r = [];
                            for(var i=0,l=data.length;i<l;i++){
                                var newRow = {itemId: data[i].id,name: data[i].name,itemType: data[i].itemType, sx: i+1+eLen,isDisplay : 1,type: 1,isEr: 0, zljs:0 };
                                r.push(newRow);
                            }
                            grid2.addRows(r);
                        }
                    }
                }
            });
        }

        grid.on("drawcell", function(e) {
            // 公式
            if (e.field=="gs") {
                var row = e.row;
                var rowId = e.row.id;
                var rowSalaryWageId = e.row.salaryWageId;
                var html = "公式";
                if (rowId != "" && rowId != null) {
                    if (row.isNumber == 0) {
                        html = '<a href="javascript:void(0);" class="link" onclick="tips()">公式</a>';
                    } else {
                        var cell = "";
                        if (row.formulaCount > 0) {
                            cell = "color:red";
                        }
                        html = '<a href="javascript:;" class="link" style="'+cell+'" onclick="gs(\''+rowSalaryWageId+'\',\''+row.itemId+'\', 1, 1)">公式</a>';
                    }
                }
                e.cellHtml=html;
            }
        });

        grid2.on("drawcell", function(e) {
            // 公式
            if (e.field=="gs") {
                var row = e.row;
                var rowId = e.row.id;
                var html = "公式";
                if (rowId != "" && rowId != null) {
                    if (row.isNumber == 0) {
                        html = '<a href="javascript:void(0);" class="link" onclick="tips()">公式</a>';
                    } else {
                        var cell = "";
                        if (row.formulaCount > 0) {
                            cell = "color:red";
                        }
                        html = '<a href="javascript:;" class="link" style="'+cell+'" onclick="gs(\''+rowId+'\',\''+row.itemId+'\', 2, 0)">公式</a>';
                    }
                }
                e.cellHtml=html;
            }
        });

        function removeRow() {
            var rows = grid2.getSelecteds();
            if (rows.length > 0) {
                grid2.removeRows(rows, false);
            }else{
                mini.alert("请选择需要删除的记录!");
            }
        }

        /** 关闭窗口 */
        function CloseWindow(action) {
            if (window.CloseOwnerWindow)
                return window.CloseOwnerWindow(action);
            else
                window.close();
        }
        /** 关闭按钮点击事件 */
        function onCancel() {
            CloseWindow("cancel");
        }

        function moveup(t) {
            var row = grid2.getSelected();
            if (row) {
                var index = grid2.indexOf(row);
                var local = 0;
                if (t==1) {
                    local = index - 1;
                } else {
                    local = 0;
                }

                // 组合顺序
                var zu = [];
                for (var i=local;i<index+1;i++) {
                    var r = grid2.getRow(i);
                    zu[i] = r.sx;
                }
                grid2.moveRow(row, local);
                for (var i=local;i<index+1;i++) {
                    var r = grid2.getRow(i);
                    grid2.updateRow(r, {sx: zu[i]});
                }
            }
        }

        function movedown(t) {
            var row = grid2.getSelected();
            if (row) {
                var index = grid2.indexOf(row);
                var local = 0;
                if (t==1) {
                    local = index + 2;
                }
                else {
                    local = grid2.getData().length;
                }

                // 组合顺序
                var zu = [];
                for (var i=index;i<local;i++) {
                    var r = grid2.getRow(i);
                    zu[i] = r.sx;
                }
                grid2.moveRow(row, local);
                for (var i=index;i<local;i++) {
                    var r = grid2.getRow(i);
                    grid2.updateRow(r, {sx: zu[i]});
                }
            }
        }

        // 分析公式
        function deploy() {
            var allotId = mini.get("id").getValue();
            if (allotId == null || allotId == "") {
                mini.alert("请先保存数据");
                return;
            }
            window.parent.loading();
            $.ajax({
                url : "../salary/salary_items!saveDeploy.action",
                type : "post",
                data : {
                    id : allotId
                },
                success : function(text) {
                    window.parent.unmask();
                    if (text == "success") {
                        mini.alert("分析成功");
                    } else {
                        if(text.length>0){
                            mini.alert(text);
                        }
                    }
                }
            });
        }
    </script>
</body>
</html>