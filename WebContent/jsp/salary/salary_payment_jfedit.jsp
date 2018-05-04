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
    <title>编辑个人缴费信息</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1">
        <input class="mini-hidden" id="id" name="id" value="${mapInfo.id}"/>
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width:10%">工号：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" value="${mapInfo.jobNumber}"/>
                </td>
                <td class="list_left_box1" valign="top" style="width:10%">姓名：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" value="${mapInfo.name}"/>
                </td>
                <td class="list_left_box1" valign="top" style="width:10%">公司：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" style="width:90%" value="${mapInfo.company}"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">部门：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" value="${mapInfo.detpName}"/>
                </td>
                <td class="list_left_box1" valign="top">岗位：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" value="${mapInfo.post}"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%" pageSize="20" borderStyle="border-top : 1px solid #C9C9C9;"
            allowAlternating="true" url="../salary/salary_payment!getStaffPaymentData.action?id=${id}&staffId=${staffId}"
            allowCellEdit="true" allowCellSelect="true" idField="id" allowResize="false" showPager="false" oncellbeginedit="onCellBeginEdit">
            <div property="columns">
                <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                <div type="comboboxcolumn" displayField="name" field="ibfId" width="80" headerAlign="center" align="center" >福利项目
                    <input property="editor" class="mini-combobox" textField="name" url="../basis/insurance_benefits!getAllItemsListData.action"/>
                </div>
                <div type="comboboxcolumn" readOnly="true" field="area" displayField="areaName" width="70" headerAlign="center" align="center">缴费地域
                    <input property="editor" class="mini-combobox" url="../basis/pay_areas!jsonlist.action" textField="city"/>
                </div>
                <div field="jfCompany" width="120" headerAlign="center" align="center">缴费单位</div>
                <div field="jfAccount" width="100" headerAlign="center" align="center" >缴费账号</div>
                <div type="comboboxcolumn" field="isPayBack" width="70" headerAlign="center" align="center" >是否补缴*
                    <input property="editor" class="mini-combobox" data="[{id: 0, text: '否'}, {id: 1, text: '是'}]" />
                </div>
                <div header="补缴年月" headerAlign="center">
                    <div property="columns">
                        <div field="payBackYear" width="50" headerAlign="center" align="center"numberFormat="0"  vtype="required">年*
                            <input property="editor" class="mini-spinner" format="0" minValue="1990" maxValue="2100" style="width:100%"/>
                        </div>
                        <div field="payBackMonth" width="40" headerAlign="center" align="center"numberFormat="0" vtype="required">月*
                          <input property="editor" class="mini-spinner" format="0" minValue="1" maxValue="12" style="width:100%"/>
                        </div>
                    </div>
                </div>
                <div field="baseCompany" width="90" headerAlign="center" align="center">公司缴费基数</div>
                <div field="basePersonal" width="90" headerAlign="center" align="center">员工缴费基数</div>
                <div field="paymentCompany" width="90" headerAlign="center" align="center" vtype="required;float">公司缴费*
                    <input property="editor" class="mini-textbox" maxLength="20"/>
                </div>
                <div field="paymentPersonal" width="90" headerAlign="center" align="center" vtype="required">员工缴费*
                    <input property="editor" class="mini-textbox" maxLength="20"/>
                </div>
            </div>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOK()" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.frozenColumns(0, 4);
        grid.load();

        function onOK() {
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
                gridjson= mini.encode(data);
            }

            window.parent.loading();
            $.ajax({
                url : "../salary/salary_payment!saveOrUpdateToPayment.action",
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

        // 单元格编辑钱监听
        function onCellBeginEdit(e) {
            var field = e.field;
            var record = e.record;
            if (field == "payBackYear" || field == "payBackMonth") {
                if (record.isPayBack == 0) {
                    e.cancel = true;
                }
            }
        }

    </script>
</body>
</html>