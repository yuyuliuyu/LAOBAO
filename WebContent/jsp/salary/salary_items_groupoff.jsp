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
    <title>注销个人薪资组</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;" pageSize="20" url="../salary/salary_items!getPersonalGroupListData.action?ids=${ids}&recordId=${recordId}"
            allowAlternating="true" idField="id" allowResize="false"showPager="false"borderStyle="border-top : 1px solid #C9C9C9;"
            allowCellEdit="true" allowCellSelect="true" multiSelect="true" oncellbeginedit="OnCellBeginEdit" oncellvalidation="onCellValidation">
            <div property="columns">
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div type="checkcolumn" width="40"></div>
                <div type="indexcolumn" headeralign="center"  width="40">序列</div>
                <div field="groupName" readOnly="true" width="120" headerAlign="center" align="center">薪资组</div>
                <div type="comboboxcolumn" readOnly="true" field="isOff" width="60" headerAlign="center" align="center">是否注销
                    <input property="editor" class="mini-combobox" data="[{id:0, text:'未注销'},{id:1, text:'已注销'}]" />
                </div>
                <div field="offDate" width="110" headerAlign="center" align="center"vType="required;" renderer="ondayRenderer2">注销日期*
                    <input property="editor" class="mini-datepicker" format="yyyy年MM月dd日"/>
                </div>
            </div>
        </div>
        <div id="grid2" class="mini-datagrid" style="width:100%;margin-top: 12px" url="../salary/salary_items!getPersonalToAllocationListData.action?ids=${ids}&recordId=${recordId}" pageSize="20"
            allowAlternating="true" idField="id" allowResize="false"showPager="false"borderStyle="border-top : 1px solid #C9C9C9;">
            <div property="columns">
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                <div field="jobNumber" width="80" headerAlign="center" align="center">工号</div>
                <div field="name" width="100" headerAlign="center" align="center">姓名</div>
                <div field="companyName" width="100" headerAlign="center" align="center">公司</div>
                <div field="deptName" width="60" headerAlign="center" align="center">部门</div>
                <div field="post" width="80" headerAlign="center" align="center">岗位</div>
                <div field="salaryGroup" width="120" headerAlign="center" align="center">当前薪资组</div>
                <div type="comboboxcolumn" field="onJob" width="60" headerAlign="center" align="center">员工状态
                    <input property="editor" class="mini-combobox" data="[{id:0, text:'实习期'},{id:1, text:'试岗期'},{id:2, text:'正式工'},{id:3, text:'离职'},{id:4, text:'退休'},{id:5, text:'返聘'}]"/>
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
        grid.hideColumn("id");
        grid.load();
        var grid2 = mini.get("grid2");
        grid2.hideColumn("id");
        grid2.load();

        function onOKClose() {
            // 表格部分
            var gridjson="";
            if(grid){
                // 验证
                grid.validate();
                if (grid.isValid() == false) {
                    gridError(grid);
                    return;
                }
                var data = grid.getSelecteds();
                if(data.length<1){// 空行也算length
                    mini.alert("请选择一条数据！");
                    return;
                }
                gridjson= mini.encode(data);
            }

            window.parent.loading();
            $.ajax({
                url : "../salary/salary_items!savePersonalGroupListData.action",
                type : "post",
                data : {
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
                            message: "注销成功",
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

        function OnCellBeginEdit(e) {
            var record = e.record, field = e.field;
            // 注销日期
            if (field == "offDate") {
                // 已经注销的不能编辑
                if (record.isOff == 1) {
                    e.cancel = true;
                }
            }
        }

        function onCellValidation(e) {
            var record = e.record, field = e.field;
            // 注销日期
            if (field == "offDate") {
                if (grid.isSelected(e.row)) {
                    var v = $.trim(e.value);
                    if (record.isOff == 0 && (v == "" || v==null)) {
                        e.errorText = "不能为空";
                        e.isValid = false;
                    }
                } else e.isValid = true;
            }
        }
    </script>
</body>
</html>