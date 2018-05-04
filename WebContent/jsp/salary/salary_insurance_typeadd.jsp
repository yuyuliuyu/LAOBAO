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
    <title>保险类别编辑</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div id="form1" class="mini-fit">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width: 21%">*福利项目：</td>
                <td class="list_right_box" style="width: 30%">
                    <input class="mini-hidden" id="id" name = "id"value="${salaryInsurance.id}"/>
                    <input class="mini-hidden" name = "companyId"value="${depId}"/>
                    <input class="mini-combobox"style="width: 90%" value="${salaryInsurance.name}"id="name" name="name" textField="name"
                        url="../basis/insurance_benefits!getAllItemsListData.action" required="true" onvaluechanged="onFuliValueChanged"/>
                </td>
                <td class="list_left_box1" valign="top"style="width: 21%">*缴费地域：</td>
                <td class="list_right_box"style="width: 30%">
                    <input class="mini-buttonedit" id="area" name="area" onbuttonclick="onButtonEditAlert" required-="true"
                        style="width: 90%"value="${salaryInsurance.area}" text="${area}"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*员工类型：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" value="${salaryInsurance.staffType}" name="staffType"style="width: 90%"textField="dicname" valueField="dicvalue"
                        required="true" url="../system/dectionary!listjson.action?id=402881945bc7485d015bc7babe37000e"/>
                </td>
                <td class="list_left_box1" valign="top">*生效年月：</td>
                <td class="list_right_box">
                    <input class="mini-spinner" style="width:55%" id="year" name="year" value="${salaryInsurance.year}" minValue="1990" maxValue="2100" format="0" required="true"/>
                    <input class="mini-spinner" style="width:30%" id="month" name="month" value="${salaryInsurance.month}" minValue="1" maxValue="12" format="0" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*公司缴纳比例(%)：</td>
                <td class="list_right_box">
                    <input class="mini-spinner" value="${salaryInsurance.biCompany}"name="biCompany"style="width: 90%"
                        required="true" minValue="0" maxValue="100"/>
                </td>
                <td class="list_left_box1" valign="top">*员工缴纳比例(%)：</td>
                <td class="list_right_box">
                    <input class="mini-spinner" value="${salaryInsurance.biPersonal}"name="biPersonal"style="width: 90%"
                        required="true" minValue="0" maxValue="100"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*缴纳基数上限：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" value="${salaryInsurance.high}"name="high"style="width: 90%"required="true" maxLength="20" vtype="float"/>
                </td>
                <td class="list_left_box1" valign="top">*缴纳基数下限：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" value="${salaryInsurance.low}"name="low"style="width: 90%"required="true" maxLength="20" vtype="float"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*保留小数：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" value="${salaryInsurance.numberAccuracy}"name="numberAccuracy"style="width: 90%"required="true" maxLength="6"vtype="int" />
                </td>
                <td class="list_left_box1" valign="top">*缴费额进位规则：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" value="${salaryInsurance.payCarryRule}" name="payCarryRule" style="width: 90%"popupWidth="auto" textField="dicname" valueField="dicvalue"
                        required="true" url="../system/dectionary!listjson.action?id=402881945bea6f07015beab8758e000d"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*计算单位缴费：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" data="[{id:0,text:'否'},{id:1,text:'是'}]"name="isJs"value="${salaryInsurance.isJs == null? 1: salaryInsurance.isJs}"
                        style="width: 90%"required="true"/>
                </td>
                <td class="list_left_box1" valign="top">*状态：</td>
                <td class="list_right_box" >
                    <input class="mini-combobox" data="[{id:0,text:'有效'},{id:1,text:'无效'}]"name="type"value="${salaryInsurance.type == null? 0: salaryInsurance.type}"
                        style="width: 90%"required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">公司缴费基数：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" value="${salaryInsurance.baseCompany}" id="baseCompany" name="baseCompany"
                        style="width: 90%;height: 60px;margin-bottom: 2px;"/>
                    <input class="mini-hidden" id="baseCompanyMath" name = "baseCompanyMath" value="${salaryInsurance.baseCompanyMath}"/> <!-- 公司缴纳基数解析公式 -->
                    <a class="mini-button" onclick="addFoumula('baseCompany', 'baseCompanyMath', 0)">公式</a>
                    <a class="mini-button" onclick="clearInput('baseCompany')">清空</a>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top"><span id="ygjfjs">*</span>员工缴费基数：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" value="${salaryInsurance.basePersonal}" id="basePersonal" name="basePersonal"style="width: 90%;height: 60px;margin-bottom: 2px;"/>
                    <input class="mini-hidden" id="basePersonalMath" name = "basePersonalMath" value="${salaryInsurance.basePersonalMath}"/> <!-- 员工缴纳基数解析公式 -->
                    <a class="mini-button" id="basePersonalF" onclick="addFoumula('basePersonal', 'basePersonalMath', 1)">公式</a>
                    <a class="mini-button" id="basePersonalC" onclick="clearInput('basePersonal')">清空</a>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*公司缴费公式：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" value="${salaryInsurance.companyFormula}" id="companyFormula" name="companyFormula"
                        style="width: 90%;height: 60px;margin-bottom: 2px;" required="true"/>
                    <input class="mini-hidden" id="companyFormulaMath" name = "companyFormulaMath" value="${salaryInsurance.companyFormulaMath}"/> <!-- 公司缴纳公式解析公式 -->
                    <a class="mini-button" onclick="addFoumula('companyFormula', 'companyFormulaMath', 2)">公式</a>
                    <a class="mini-button" onclick="clearInput('companyFormula')">清空</a>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top"><span id="ygjfgs">*</span>员工缴费公式：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" value="${salaryInsurance.personalFormula}" id="personalFormula" name="personalFormula"style="width: 90%;height: 60px;margin-bottom: 2px;"/>
                    <input class="mini-hidden" id="personalFormulaMath" name = "personalFormulaMath" value="${salaryInsurance.personalFormulaMath}"/> <!-- 员工缴纳公式解析公式 -->
                    <a class="mini-button" id="personalFormulaF" onclick="addFoumula('personalFormula', 'personalFormulaMath', 3)">公式</a>
                    <a class="mini-button" id="personalFormulaC" onclick="clearInput('personalFormula')">清空</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOk('salary','salary_insurance','saveOrUpdate', false)" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        var year = mini.get("year");
        var month = mini.get("month");
        var basePersonal = mini.get("basePersonal");
        var basePersonalF = mini.get("basePersonalF");
        var basePersonalC = mini.get("basePersonalC");
        var personalFormula = mini.get("personalFormula");
        var personalFormulaF = mini.get("personalFormulaF");
        var personalFormulaC = mini.get("personalFormulaC");
        var fuliName = mini.get("name");
        if ('${salaryInsurance.id}' == null || '${salaryInsurance.id}' == "") {
            var date = new Date();
            year.setValue(date.getFullYear());
            month.setValue(date.getMonth()+1);
        }
        $(function() {
            var id = mini.get("id");
            if (id.getValue() =="") {
                fuliName.select(0);
            }
            fuliName.doValueChanged();
        });

        function onButtonEditAlert(e) {
            onButtonEdit(e.sender, "选择缴费地域", "../salary/salary_insurance!area.action", 480, 360,"city","","");
        }

        function onFuliValueChanged(e) {
            var selected = e.selected;
            var jiaona = selected.ispay;
            if (jiaona == 0) {
                $("#ygjfjs").css("display", "none");
                $("#ygjfgs").css("display", "none");
                basePersonal.setValue("");
                personalFormula.setValue("");
                basePersonal.setEnabled(false);
                personalFormula.setEnabled(false);
                basePersonal.setRequired(false);
                personalFormula.setRequired(false);
                basePersonal.addCls("asLabel");
                personalFormula.addCls("asLabel");

                basePersonalF.setEnabled(false);
                basePersonalC.setEnabled(false);
                personalFormulaF.setEnabled(false);
                personalFormulaC.setEnabled(false);
            } else {
                $("#ygjfjs").css("display", "inline");
                $("#ygjfgs").css("display", "inline");
                basePersonal.setEnabled(true);
                personalFormula.setEnabled(true);
                basePersonal.setRequired(true);
                personalFormula.setRequired(true);
                basePersonal.removeCls("asLabel");
                personalFormula.removeCls("asLabel");

                basePersonalF.setEnabled(true);
                basePersonalC.setEnabled(true);
                personalFormulaF.setEnabled(true);
                personalFormulaC.setEnabled(true);
            }
        }

        // 公式
        function addFoumula(id, cntMathId, type) {
            mini.open({
                url : "../salary/salary_insurance!formula.action?content="+mini.get(id).getValue()+"&type="+type+"&depId=${companyId}",
                title : "编辑公式",
                width : 780,
                height : 540,
                onload : function() {
                },
                ondestroy : function(action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.decode(mini.clone(data));
                        mini.get(id).setValue(data.content);
                        mini.get(cntMathId).setValue(data.cntMath);
                    }
                }
            });
        }

        // 清空
        function clearInput(id) {
            var clearId = mini.get(id);
            if (clearId) {
                clearId.setValue("");
            }
        }

    </script>
</body>
</html>