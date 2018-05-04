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
    <title>编辑参保信息</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1">
        <input class="mini-hidden" id="id" name="id" value="${id}"/>
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top">工号：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" value="${mapInfo.jobNumber}"/>
                </td>
                <td class="list_left_box1" valign="top">姓名：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" value="${mapInfo.name}"/>
                </td>
                <td class="list_left_box1" valign="top">公司：</td>
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
                <td class="list_left_box1" valign="top">平均工资：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowInput="false" style="width:90%" value="${mapInfo.averageSalary}"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;" pageSize="20"
            allowAlternating="true" multiSelect="true" url="../salary/salary_insurance!getStaffCanBaoInfo.action?id=${id}"
            allowCellEdit="true" allowCellSelect="true" idField="id" allowResize="false" showPager="false" oncellvalidation="onCellValidation">
            <div property="columns">
                <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                <div type="comboboxcolumn" displayField="name" field="ibfId" width="80" headerAlign="center" align="center" readOnly="true">福利项目
                    <input property="editor" class="mini-combobox" textField="name" url="../basis/insurance_benefits!getAllItemsListData.action"/>
                </div>
                <div type="comboboxcolumn" field="area" displayField="areaName" width="60" headerAlign="center" align="center" readOnly="true">缴费地域
                    <input property="editor" class="mini-combobox" url="../basis/pay_areas!jsonlist.action" textField="city"/>
                </div>
                <div field="jfCompany" width="120" headerAlign="center" align="center" readOnly="true">缴费单位
                    <input property="editor" class="mini-textbox" />
                </div>
                <div field="jfAccount" width="100" headerAlign="center" align="center" vtype="required;float" >缴费账号*
                    <input property="editor" class="mini-textbox" maxLength="40"/>
                </div>
                <div field="baseCompany" width="90" headerAlign="center" align="center" vtype="required;float" >公司缴费基数*
                    <input property="editor" class="mini-textbox" maxLength="20"/>
                </div>
                <div field="basePersonal" width="90" headerAlign="center" align="center" vtype="required;float" >员工缴费基数*
                    <input property="editor" class="mini-textbox" maxLength="20"/>
                </div>
                <div header="开始缴费年月*" headerAlign="center">
                    <div property="columns">
                        <div field="year" width="50" headerAlign="center" align="center"numberFormat="0">年
                            <input property="editor" class="mini-spinner" format="0" minValue="1990" maxValue="2100" style="width:100%"/>
                        </div>
                        <div field="month" width="40" headerAlign="center" align="center"numberFormat="0" >月
                          <input property="editor" class="mini-spinner" format="0" minValue="1" maxValue="12" style="width:100%"/>
                        </div>
                    </div>
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
        grid.hideColumn("id");
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
                // 判断是否存在相同的福利项目
                var hash = {};
                for(var i=0; i< data.length; i++) {
                    var row = data[i];
                    if(row.ibfId != undefined && hash[row.ibfId]) {
                        mini.alert("存在相同的福利项目：<font color='red'>"+row.name +"</font>");
                        return;
                    }
                    hash[row.ibfId] = true;
                }
                gridjson= mini.encode(data);
            }

            window.parent.loading();
            $.ajax({
                url : "../salary/salary_insurance!updateToJfJs.action",
                type : "post",
                data : {
                    id : mini.get("id").getValue(),
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

        function onEnglishAndNumberValidation(e) {
            if (e.isValid) {
                if (isEnglishAndNumber(e.value) == false) {
                    e.errorText = "必须输入英文+数字";
                    e.isValid = false;
                }
            }
        }
        /* 是否英文+数字 */
        function isEnglishAndNumber(v) {
            var re = new RegExp("^[0-9a-zA-Z\_]+$");
            if (re.test(v)) return true;
            return false;
        }
        /* 是否数字 */
        function isNumber(v) {
            if (v=="" || v==null || v== undefined) {
                return true;
            }
            var re = new RegExp("^[0-9]+$");
            if (re.test(v)) return true;
            return false;
        }

        function onCellValidation(e) {
            var field = e.field, record = e.record;

            // 公司缴费基数、个人缴费基数上限与下限
            if (field == "baseCompany" || field == "basePersonal") {
                var value = parseFloat(e.value);
                var high = parseFloat(record.high);
                var low = parseFloat(record.low);
                if (value > high || value < low) {
                    e.errorText = "必须在" + high + "-" + low + "之间";
                    e.isValid = false;
                }
            }
        }

    </script>
</body>
</html>