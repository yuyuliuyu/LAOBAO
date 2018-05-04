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
    <title>团体商业保险缴费增加编辑</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit" id="form1">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top">*投保单位：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" id="id" name="id" value="${salaryOrgInsurance.id}"/>
                    <input id="insuranceId" name="insuranceId" class="mini-lookup" value="${salaryOrgInsurance.insuranceId}"text="${tbdw}"
                        textField="fzx" valueField="id" popupWidth="auto" emptyText="请选择..." onbuttonclick="onButtonEditJfComAlert"
                        popup="#gridPanel" grid="#datagrid1" required="true" style="width:90%"/>
                    <div id="gridPanel" class="mini-panel" title="header" iconCls="icon-add" style="width:450px;height:250px;" 
                      showToolbar="true" showCloseButton="true" showHeader="false" bodyStyle="padding:0"  borderStyle="border:0">
                        <div property="toolbar" style="padding:5px;padding-left:8px;text-align:center;">
                            <div style="float:left;padding-bottom:2px;">
                               <span>名称：</span>
                               <input id="companyName" class="mini-textbox" style="width:160px;" onenter="onButtonEditJfComAlert"/>
                               <a class="mini-button" onclick="onButtonEditJfComAlert">查询</a>
                            </div>
                            <div style="clear:both;"></div>
                        </div>
                        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
                           borderStyle="border:0" pagerSize="10">
                            <div property="columns">
                                <div type="checkcolumn" width="30"></div>
                                <div field="id" id="id" width="20" name="id" class="mini-hidden"></div>
                                <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                                <div field="fzx" width="100" headerAlign="center" align="center">公司</div>
                            </div>
                        </div>
                   </div>
                </td>
                <td class="list_left_box1" valign="top">*承保公司：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="cbgs" value="${salaryOrgInsurance.cbgs}" maxLength="50" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*保险项目：</td>
                <td class="list_right_box">
                    <input id="ibfId" name="ibfId" class="mini-lookup" value="${salaryOrgInsurance.ibfId}"text="${bxName}"
                         textField="name" valueField="id" popupWidth="auto" emptyText="请选择..." onbuttonclick="onButtonEditJfFuli"
                         popup="#gridPanel3" grid="#datagrid3" required="true" style="width:90%"/>
                     <div id="gridPanel3" class="mini-panel" title="header" iconCls="icon-add" style="width:450px;height:250px;" 
                       showToolbar="true" showCloseButton="true" showHeader="false" bodyStyle="padding:0"  borderStyle="border:0">
                         <div property="toolbar" style="padding:5px;padding-left:8px;text-align:center;">
                             <div style="float:left;padding-bottom:2px;">
                                <span>名称：</span>
                                <input id="insuranceName" class="mini-textbox" style="width:160px;" onenter="onButtonEditJfFuli"/>
                                <a class="mini-button" onclick="onButtonEditJfFuli">查询</a>
                             </div>
                             <div style="clear:both;"></div>
                         </div>
                         <div id="datagrid3" class="mini-datagrid" style="width:100%;height:100%;" 
                            borderStyle="border:0" pagerSize="10">
                             <div property="columns">
                                 <div type="checkcolumn" width="30"></div>
                                 <div field="id" id="id" width="20" name="id" class="mini-hidden"></div>
                                 <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                                 <div field="name" width="100" headerAlign="center" align="center">福利项目</div>
                             </div>
                         </div>
                    </div>
                </td>
                <td class="list_left_box1" valign="top">*保费：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="bf" vtype="float" value="${salaryOrgInsurance.bf}" maxLength="20" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">保险费率(%)：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="bxfl"vtype="float"  value="${salaryOrgInsurance.bxfl}"maxLength="20" />
                </td>
                <td class="list_left_box1" valign="top">适用员工：</td>
                <td class="list_right_box">
                    <input class="mini-textbox" style="width:90%" name="syyg" value="${salaryOrgInsurance.syyg}"maxLength="1000" />
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*投保开始时间：</td>
                <td class="list_right_box">
                    <input class="mini-datepicker" style="width:90%" name="beginDate" format="yyyy年MM月dd日" value="${salaryOrgInsurance.beginDate}" required="true"/>
                </td>
                <td class="list_left_box1" valign="top">*投保结束时间：</td>
                <td class="list_right_box">
                    <input class="mini-datepicker" style="width:90%" name="endDate" format="yyyy年MM月dd日" value="${salaryOrgInsurance.endDate}" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">*保险金额（描述）：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" style="width:90%;height: 78px" name="bxjems"value="${salaryOrgInsurance.bxjems}" maxLength="1000" required="true"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top">备注：</td>
                <td class="list_right_box" colspan="3">
                    <input class="mini-textarea" style="width:90%;height: 78px" name="note" value="${salaryOrgInsurance.note}" maxLength="1000" />
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOk('salary','salary_business','',false)" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();

        // 投保单位
        function onButtonEditJfComAlert(e) {
            var datagrid1 = mini.get("datagrid1");
            datagrid1.hideColumn("id");
            datagrid1.setUrl("../salary/salary_manage!getBranches.action");
            datagrid1.load({companyName: mini.get("companyName").getValue()});
        }

        // 福利项目
        function onButtonEditJfFuli(e) {
            var datagrid3 = mini.get("datagrid3");
            datagrid3.hideColumn("id");
            datagrid3.setUrl("../basis/insurance_benefits!getAllItemsListData.action");
            datagrid3.load({insuranceName: mini.get("insuranceName").getValue()});
        }
    </script>
</body>
</html>