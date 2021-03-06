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
    <title>停保</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <a class="mini-button" id="front" onclick="frontItems()" enabled="false">上一个</a>
                    <a class="mini-button" id="next" onclick="nextItems()">下一个</a>
                    <input id="staff" class="mini-combobox" url="../salary/salary_insurance!getChoseStaff.action?ids=${ids}" textField="name"
                        onvaluechanged="onValueChanged"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
            allowAlternating="true" multiSelect="true"
            allowCellEdit="true" allowCellSelect="true" idField="id" allowResize="false" showPager = "false">
            <div property="columns">
                <div type="checkcolumn" width="40"></div>
                <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                <div field="name" width="100" headerAlign="center" align="center">保险项目</div>
                <div header="结束缴费年月*" headerAlign="center">
                    <div property="columns">
                        <div field="endYear" width="40" headerAlign="center" align="center"numberFormat="0"  vtype="required">年
                            <input property="editor" class="mini-spinner" format="0" minValue="1990" maxValue="2100" style="width:100%"/>
                        </div>
                        <div field="endMonth" width="40" headerAlign="center" align="center"numberFormat="0" vtype="required">月
                          <input property="editor" class="mini-spinner" format="0" minValue="1" maxValue="12" style="width:100%"/>
                        </div>
                    </div>
                </div>
                <div field="endNote" width="120" headerAlign="center" align="center">停缴原因
                    <input property="editor" class="mini-textbox" style="width:100%" maxLength="1000"/>
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
        var front = mini.get("front");
        var next = mini.get("next");
        var staff = mini.get("staff");
        staff.select(0);
        staff.doValueChanged();
        var curPos = 0;

        function frontItems() {
            staff.select(--curPos);
            staff.doValueChanged();
        }
        function nextItems() {
            staff.select(++curPos);
            staff.doValueChanged();
        }

        function onValueChanged(e) {
            var sender = e.sender;
            var data = sender.data;
            for (var i=0,l=data.length;i<l;i++) {
                if (data[i].id==e.value) {
                    curPos = i;
                    break;
                }
            }
            if (data.length == 1) {
                front.setEnabled(false);
                next.setEnabled(false);
            } else if (curPos == 0) {
                front.setEnabled(false);
                next.setEnabled(true);
            } else if ((curPos+1) == data.length) {
                front.setEnabled(true);
                next.setEnabled(false);
            }
            grid.setUrl("../salary/salary_insurance!getChoseStaffInsurances.action?id="+e.value);
            grid.load();
        }

        function onOKClose() {
            // 验证
            grid.validate();
            if (grid.isValid() == false) {
                gridError(grid);
                return;
            }
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条福利项目");
                return;
            }
            gridjson= mini.encode(rows);
            var id = staff.getValue();

            mini.confirm("确定要停保？","提醒", function(action) {
                if (action=="ok") {
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_insurance!updateStopInsurance.action",
                        type : "post",
                        data : {
                            id : id,
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
                                    message: "停保成功",
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

    </script>
</body>
</html>