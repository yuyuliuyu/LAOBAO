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
    <title>个人薪资组分配</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1" class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <input class="mini-hidden" name="ids" value="${ids}"/>
                    <a class="mini-button" iconCls="icon-add" onclick="addRow()">增加</a>
                    <a class="mini-button" iconCls="icon-edit" onclick="removeRow()">删除</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;" pageSize="20"
            allowAlternating="true" idField="id" allowResize="false"showPager="false"borderStyle="border-top : 1px solid #C9C9C9;"
            allowCellEdit="true" allowCellSelect="true" multiSelect="true" oncellcommitedit="onCellCommitEdit">
            <div property="columns">
                <div type="checkcolumn" width="30"></div>
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div type="indexcolumn" headeralign="center"  width="40">序列</div>
                <div field="salaryGroupId" displayField="groupName" width="120" headerAlign="center" align="center" vType="required;">薪资组*
                    <input property="editor" class="mini-buttonedit" onbuttonclick="onButtonEditAlert" allowInput="false"/>
                </div>
                <div field="startDate" width="110" headerAlign="center" align="center"vType="required;" renderer="ondayRenderer2">开始日期*
                    <input property="editor" class="mini-datepicker"format="yyyy年MM月dd日"/>
                </div>
                <div type="comboboxcolumn" field="salaryComId" width="120" headerAlign="center" align="center"vType="required;">发薪单位*
                    <div property="editor" class="mini-combobox" popupWidth="300" url="../salary/salary_manage!getBranches.action" 
                        valueField="id" textField="fzx"valueFromSelect="true" allowInput="true"pinyinField="jm">
                            <div property="columns">
                                <div field="jm" width="80"headerAlign="center"align="center">公司编码</div>
                                <div field="fzx" width="180"headerAlign="center"align="center">公司</div>
                            </div>
                    </div>
                </div>
                <div type="comboboxcolumn" field="isSalary" width="80" headerAlign="center" align="center" vType="required;">是否发薪*
                    <input property="editor" class="mini-combobox" data="[{id:0, text:'否'},{id:1, text:'是'}]" style="width:100%"/>
                </div>
                <div type="comboboxcolumn" field="costComId" width="120" headerAlign="center" align="center" >成本单位
                    <div property="editor" class="mini-combobox" popupWidth="300" url="../salary/salary_manage!getBranches.action" 
                        valueField="id" textField="fzx"valueFromSelect="true" allowInput="true"pinyinField="jm">
                            <div property="columns">
                                <div field="jm" width="80"headerAlign="center"align="center">公司编码</div>
                                <div field="fzx" width="180"headerAlign="center"align="center">公司</div>
                            </div>
                    </div>
                </div>
                <div field="costDeptId" displayField="name" width="130" headerAlign="center" align="center">成本部门
                    <div property="editor" class="mini-combobox" popupWidth="250" id="cbDept" 
                        valueField="id" textField="name"valueFromSelect="true"
                        enterQuery="true">
                            <div property="columns">
                                <div field="name" width="180"></div>
                            </div>
                    </div>
                </div>
                <div field="costScale" width="60" headerAlign="center" align="center" vType="maxLength:8">成本比例
                    <input property="editor" class="mini-textbox" style="width:100%"/>
                </div>
            </div>
        </div>
        <div id="grid2" class="mini-datagrid" style="width:100%;margin-top: 12px" url="../salary/salary_items!getPersonalToAllocationListData.action?ids=${ids}&depId=${depId}&recordId=${recordId}" pageSize="20"
            allowAlternating="true" idField="id" allowResize="false"showPager="false"borderStyle="border-top : 1px solid #C9C9C9;">
            <div property="columns">
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                <div field="jobNumber" width="80" headerAlign="center" align="center">工号</div>
                <div field="name" width="100" headerAlign="center" align="center">姓名</div>
                <div field="companyName" width="100" headerAlign="center" align="center">公司</div>
                <div field="deptName" width="100" headerAlign="center" align="center">部门</div>
                <div field="post" width="80" headerAlign="center" align="center">岗位</div>
                <div field="salaryGroup" width="120" headerAlign="center" align="center">当前薪资组</div>
                <div type="comboboxcolumn" field="onJob" width="60" headerAlign="center" align="center">员工状态
                    <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935baceaa1015bae044344001a"
                        textField="dicname" valueField="dicvalue"/>
                </div>
                <div type="comboboxcolumn" field="onPost" width="60" headerAlign="center" align="center">在岗状态
                    <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955bc84595015bc87aa73e0010"
                        textField="dicname" valueField="dicvalue"/>
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOKClose()" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        var grid2 = mini.get("grid2");
        grid2.hideColumn("id");
        grid2.load();

        function onButtonEditAlert(e) {
            var data = grid2.getData();
            var companyIds = "";
            if (data.length > 0) {
                if (data[0].companyId && data[0].companyId != null && data[0].companyId != "") {
                    companyIds = data[0].companyId;
                }
            } else {
                mini.alert("没有分配的人员");
                return;
            }
            for (var i = 1, l = data.length; i < l; i++) {
                if (data[i].companyId && data[i].companyId != null && data[i].companyId != "") {
                    companyIds += "," + data[i].companyId;
                }
            }
            mini.open({
                url: "../salary/salary_items!salary.action?companyId="+companyIds,
                showMaxButton: false,
                title: "选择薪资组",
                width: 720,
                height: 480,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        var btnEdit = e.sender;
                        btnEdit.setText(data.sgName);
                        btnEdit.setValue(data.salaryGroupId);
                    }
                }
            });
        }

        function onOKClose() {
            // 分配的人员
            var formdata = grid2.getData();
            if (formdata.length ==0) {
                min.alert("分配的人员不能为空");
                return;
            }
            var formjson = mini.encode(formdata); 
            // 表格部分
            var grid=mini.get("grid");
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
                // 判断grid是否多个是否发薪
                var hash = {};
                var hashGroup = {};
                for ( var i = 0, l = data.length; i < l; i++) {
                    var row = data[i];
                    if (hash[row.salaryGroupId]) {
                        mini.alert("只能有一个发新单位需要发薪");
                        return;
                    }
                    if (row.isSalary == 1) {
                        hash[row.salaryGroupId] = true;
                    }
                    if (hashGroup[row.salaryGroupId]) {
                        mini.alert("存在相同的薪资组");
                        return;
                    }
                       hashGroup[row.salaryGroupId] = true;
                }
                var griddata = grid.getData();
                gridjson= mini.encode(griddata);
            }

            window.parent.loading();
            $.ajax({
                url : "../salary/salary_items!saveOrUpdateToPersonal.action",
                type : "post",
                data : {
                    formdata : formjson,
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
                            message: "分配成功",
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

        /**时间格式化
         * 形式:yyyy年MM月dd日'
         * */
        function ondayRenderer2(e) {
            var value = e.value;
            if(value != null){
                value = new Date(value);
                if (value) return mini.formatDate(value, 'yyyy年MM月dd日');
            }else{
                return "";
            }
        }

        // 成本单位改变
        function onCellCommitEdit(e) {
            var record = e.record;
            var field = e.field;

            // 成本单位
            if (field == "costComId") {
                var editor = grid.getCellEditor(8, e.row);
                editor.setUrl("../basis/branch!depData.action?cid=" + e.value);
            }
        }
    </script>
</body>
</html>