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
    <title>员工工资档案</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
    <script src="../template/miniui/js/TreeSelectWindow.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-fit" id="form1">
        <input class="mini-hidden" name="id" id="id" value="${mapData.ID}" style="display:none;"/>
        <input class="mini-hidden" name="recordId" value="${recordId}"/>
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width: 12%">工号：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowinput="false" style="width: 90%" value="${mapData.JOB_NUMBER}"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">姓名：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowinput="false" style="width: 90%" value="${mapData.NAME}"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">性别：</td>
                <td class="list_right_box">
                    <input class="mini-combobox asLabel" readOnly="true" style="width: 90%" data="[{id:0,text:'女'},{id:1,text:'男'}]" value="${mapData.SEX}"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 12%">年龄：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowinput="false" style="width: 90%" value="${mapData.AGE}"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">身份证号：</td>
                <td class="list_right_box">
                    <s:if test="#mapData.ID_TYPE == 0">
                        <input class="mini-textbox asLabel" allowinput="false" value="${mapData.ID_NUMBER}" style="width: 90%"/>
                    </s:if>
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">人员类别：</td>
                <td class="list_right_box">
                    <input class="mini-combobox asLabel" readOnly="true" url="../system/dectionary!listjson.action?id=402881935baceaa1015bad355ba8000a"
                        textField="dicname" valueField="dicvalue" value="${mapData.EMP_TYPE}" style="width: 90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 12%">参军时间：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowinput="false" value="${mapData.INSOL_DATE}"  style="width: 90%"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">下乡时间：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowinput="false" value="${mapData.COUNTRYSID_DATE}"  style="width: 90%"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">参加工作时间：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" allowinput="false" value="${mapData.SET_WORK_DATE}"  style="width: 90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 12%">入司时间1：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" style="width: 90%" allowinput="false" value="${mapData.IN_COMPANY_TIME}" />
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">入司时间2：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" style="width: 90%" allowinput="false" value="${mapData.IN_COMPANY_TIME2}" />
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">合同时间：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" style="width: 90%" allowinput="false" value="${mapData.CONTR_START}" />
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 12%">入港时间1：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" style="width: 90%" allowinput="false" value="${mapData.IN_PORT_TIME}" />
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">入港时间2：</td>
                <td class="list_right_box">
                    <input class="mini-datepicker asLabel" style="width: 90%"readOnly="true" value="${mapData.IN_PORT_TIME2}" />
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">扣减工龄：</td>
                <td class="list_right_box">
                    <input class="mini-spinner" name="kjgl" style="width: 90%" value="${mapData.KJGL}" minValue="0" maxValue="999"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 12%">军龄：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel" style="width: 90%"allowinput="false" value="${mapData.SOLDIER_AGE}" />
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">*单位：</td>
                <td class="list_right_box">
                    <input class="mini-buttonedit" id="filmName" name="filmName" showClose="false" emptyText="请选择..." onbuttonclick="onButtonEditFilmDept(e,'deptname')"
                        style="width: 90%" value="${mapData.FILM_ID}"text="${mapData.FILM_NAME}" required="true"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">*部门：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" id="deptname" name="deptname" textField="name" required="true"
                        value="${mapData.DEPT_ID}" text="${mapData.DEPTNAME}" style="width:90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 12%">*在港工龄起始</td>
                <td class="list_right_box">
                    <input class="mini-datepicker" id="gdDate" name="gdDate" format="yyyy年MM月dd日" style="width: 90%" value="${mapData.GD_DATE}"  required="true"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">*考勤单位：</td>
                <td class="list_right_box">
                    <input class="mini-buttonedit" id="kqname" name="kqname" showClose="false" emptyText="请选择..." onbuttonclick="onButtonEditFilmDept(e,'deptname')"
                        style="width: 90%" value="${mapData.KQCOMID}"text="${mapData.KQCOM}" required="true"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">*考勤部门：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" id="kqdept" name="kqdept" textField="name" required="true"
                        value="${mapData.KQID}" text="${mapData.KQDEPTS}" style="width:90%"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 12%">考勤班组：</td>
                <td class="list_right_box">
                    <input id="classNo" name="classNo" class="mini-combobox"
                            valueFromSelect="true" url="../check/class_group!getClassInfos.action" textField="className" 
                            valueField="classNo" style="width: 90%" value="${mapData.CLASS_NO}"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">*具体岗位：</td>
                <td class="list_right_box">
                    <input id="specificPost" name="specificPost" class="mini-buttonedit" onbuttonclick="onButtonEdit1" width="90%"
                         value="${mapData.SPECIFIC_POST}" text="${mapData.SPECIFIC_POST_NAME}" allowInput="false" /> 
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">标准岗位：</td>
                <td class="list_right_box">
                    <input class="mini-buttonedit asLabel" id="post" name="post" allowInput="false" style="width: 90%" value="${mapData.POST}" text="${mapData.POSTNAME}"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 12%">*薪酬岗位：</td>
                <td class="list_right_box">
                    <input id="salaryPost" name="salaryPost" class="mini-lookup" showClose="false" value="${mapData.SALARY_POST}"text="${mapData.SALARY_POST_NAME}"
                         textField="name" valueField="id" popupWidth="auto" onbuttonclick="onButtonEditSalaryDeptAlert" valueFromSelect="true"
                         popup="#salaryDeptGridPanel" grid="#salaryDeptGrid" required="true" style="width:90%"/>
                     <div id="salaryDeptGridPanel" class="mini-panel" title="header" iconCls="icon-add" style="width:450px;height:250px;" 
                       showToolbar="true" showCloseButton="true" showHeader="false" bodyStyle="padding:0"  borderStyle="border:0">
                         <div property="toolbar" style="padding:5px;padding-left:8px;text-align:center;">
                             <div style="float:left;padding-bottom:2px;">
                                <span>薪酬岗位名称：</span>
                                <input id="name" class="mini-textbox" style="width:160px;" onenter="onButtonEditSalaryDeptAlert"/>
                                <a class="mini-button" onclick="onButtonEditSalaryDeptAlert">查询</a>
                             </div>
                         </div>
                         <div id="salaryDeptGrid" class="mini-datagrid" style="width:100%;height:100%;" 
                            borderStyle="border:0" pagerSize="10" showPager="false">
                             <div property="columns">
                                 <div type="checkcolumn" width="30"></div>
                                 <div field="id" id="id" width="20" name="id" class="mini-hidden"></div>
                                 <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                                 <div field="name" width="100" headerAlign="center" align="center">薪酬岗位</div>
                                 <div type="comboboxcolumn" field="type" width="80" headerAlign="center" align="center">类别
                                     <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945c0f0f8b015c0f148bf7003c"
                                         textField="dicname" valueField="dicvalue"/>
                                 </div>
                                 <div field="gjdx" width="80" headerAlign="center" align="center">工资档序</div>
                                 <div type="comboboxcolumn" field="type" width="80" headerAlign="center" align="center">职级类别
                                     <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945c0f0f8b015c0f173cfe003f"
                                         textField="dicname" valueField="dicvalue"/>
                                 </div>
                             </div>
                         </div>
                    </div>
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">*是否兼职：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" style="width: 90%" value="${mapData.SFJZ}" name="isJz"required="true"
                        data="[{id: 0, text:'否'},{id: 1, text:'是'}]"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">*是否标准工时：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" style="width: 90%"allowinput="false" name="isBzgs" value="${mapData.IS_BZGS==null?1:mapData.IS_BZGS}" required="true"
                        data="[{id:0,text:'否'},{id:1,text:'是'}]" />
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 12%">*是否特殊工时：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" name="isSpecialHour" style="width: 90%"allowinput="false" value="${mapData.IS_SPECIAL_HOUR==null?1:mapData.IS_SPECIAL_HOUR}"
                        data="[{id:0,text:'否'},{id:1,text:'是'}]" required="true"/>
                </td>
            </tr>
        </table>
        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
            <span style="padding-left:5px;">发薪信息：</span>
        </div>
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top" style="width: 12%">*分配权限：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" style="width: 90%" value="${mapData.FPQX==null?2:mapData.FPQX}" name="fpqx" required="true"
                        data="[{id: 1, text:'公司'},{id: 2, text:'基层'}]"/>
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">*支付形式：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" style="width: 90%"allowinput="false" name="zfxs" value="${mapData.ZFXS==null?1:mapData.ZFXS}" required="true"
                        data="[{id:1,text:'自发薪'},{id:2,text:'股东劳务费'}]" />
                </td>
                <td class="list_left_box1" valign="top" style="width: 12%">*工资形式：</td>
                <td class="list_right_box">
                    <input name="gzxs" class="mini-combobox"
                        required="true" requiredErrorText="不能为空"
                        url="../template/wageForm.txt" textField="text" 
                        valueField="id" style="width: 90%" value="${mapData.GZXS==null?0:mapData.GZXS}"/>
                </td>
            </tr>
            <tr>
                <td class="list_left_box1" valign="top" style="width: 12%">特殊标记：</td>
                <td class="list_right_box">
                    <input class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945be55610015be55f9d750010" showNullItem="true"
                        textField="dicname" valueField="dicvalue" value="${mapData.SPECIAL_MARK}" name="specialMark" style="width: 90%"/>
                </td>
            </tr>
        </table>
        <div id="bxJnGrid" class="mini-datagrid" style="width:100%;" pageSize="20" url="../salary/salary_record!getChoseStaffInsurances.action?id=${id}"
            idField="id" allowResize="false"showPager="false" borderStyle="border-top:1px solid #C9C9C9">
            <div property="columns">
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div type="indexcolumn" headeralign="center"  width="40">序列</div>
                <div field="name" width="120" headerAlign="center" align="center">福利项目</div>
                <div field="baseCompany" width="120" headerAlign="center" align="center">缴费基数</div>
                <div field="biCompany" width="80" headerAlign="center" align="center">单位缴费比例</div>
                <div field="biPersonal" width="80" headerAlign="center" align="center">员工缴费比例</div>
                <div field="jnCompany" width="80" headerAlign="center" align="center">单位缴纳</div>
                <div field="jnPersonal" width="80" headerAlign="center" align="center">员工缴纳</div>
            </div>
        </div>
        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
            <span style="padding-left:5px;">薪资组信息：</span>
        </div>
        <div id="salaryGroupGrid" class="mini-datagrid" style="width:100%;" pageSize="20" url="../salary/salary_items!getPersonalGroupListData.action?ids=${id}&recordId=${recordId}"
            allowAlternating="true" idField="id" allowResize="false"showPager="false">
            <div property="columns">
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div type="indexcolumn" headeralign="center"  width="40">序列</div>
                <div field="groupName" width="120" headerAlign="center" align="center">薪资组</div>
                <div field="startDate" width="110" headerAlign="center" align="center" renderer="ondayRenderer2">开始日期
                    <input property="editor" class="mini-datepicker"format="yyyy年MM月dd日"/>
                </div>
                <div type="comboboxcolumn" field="salaryComId" width="120" headerAlign="center" align="center">发薪单位
                    <input property="editor" class="mini-combobox" popupWidth="250" textField="fzx" url="../salary/salary_manage!getBranches.action"/>
                </div>
                <div type="comboboxcolumn" field="isSalary" width="80" headerAlign="center" align="center" >是否发薪
                    <input property="editor" class="mini-combobox" data="[{id:0, text:'否'},{id:1, text:'是'}]" style="width:100%"/>
                </div>
                <div type="comboboxcolumn" field="costComId" width="120" headerAlign="center" align="center" >成本单位
                    <input property="editor" class="mini-combobox"popupWidth="250" textField="fzx" url="../salary/salary_manage!getBranches.action" style="width:100%"/>
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
                <div field="costScale" width="70" headerAlign="center" align="center" vType="maxLength:8">成本比例
                    <input property="editor" class="mini-textbox" style="width:100%"/>
                </div>
                <div field="offDate" width="110" headerAlign="center" align="center" renderer="ondayRenderer2">注销日期
                    <input property="editor" class="mini-datepicker" format="yyyy年MM月dd日"/>
                </div>
            </div>
        </div>
        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
            <span style="padding-left:5px;">账号信息：</span>
        </div>
        <div id="grid1" class="mini-datagrid" style="width:100%;" url="../salary/salary_record!getSalaryAccountListData.action?id=${mapData.ID}&recordId=${recordId}" pageSize="20"
            allowAlternating="true" idField="id" allowResize="false" showPager="false" multiSelect="true"
            allowCellEdit="true" allowCellSelect="true" oncellvalidation="onCellValidation" >
            <div property="columns">
                <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                <div type="comboboxcolumn" field="accountType" width="80" headerAlign="center" align="center" vtype="required;">账号类型*
                    <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881855ba41a89015ba41d28f30008"
                        valueField="dicvalue" textField="dicname"/>
                </div>
                <div type="comboboxcolumn" field="accountBank" width="80" headerAlign="center" align="center" vtype="required;">金融政府机构*
                    <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945bd6397b015bd66c787e0009"
                        valueField="dicvalue" textField="dicname"/>
                </div>
                <div field="account" width="80" headerAlign="center" align="center" vtype="required;maxLength:50">账号*
                    <input property="editor" class="mini-textbox" style="width:100%"/>
                </div>
                <div field="userName" width="60" headerAlign="center" align="center" vtype="maxLength:50">用户名
                    <input property="editor" class="mini-textbox" style="width:100%"/>
                </div>
                <div field="note" name="target" width="120" headerAlign="center" align="center" vtype="maxLength:1000">描述
                    <input property="editor" class="mini-textbox" style="width:100%"/>
                </div>
                <div field="ctr" width="60" headerAlign="center" align="center"></div>
            </div>
        </div>
        <s:iterator value="#request.itemlTypeList" var="items">
            <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                <span style="padding-left:5px;">${items[0]}：</span>
            </div>
            <div name="grid" class="mini-datagrid" style="width:100%;" url="${items[1]}" pageSize="20"
                allowCellEdit="true" allowCellSelect="true" showpager="false" idField="id" allowResize="false"
                borderStyle="border-top:1px solid #C9C9C9" oncellendedit="onCellEndEdit">
                <div property="columns">
                    <div field="id" id="id" width="10" name="id" class="mini-hidden"></div>
                    <div type="indexcolumn" headeralign="center" width="20">序列</div>
                    <div field="staticId" name= "staticId" width="200" headerAlign="center" align="center">薪资值主键ID</div>
                    <div field="slaryGeoupName" width="180" headerAlign="center" align="center">薪资组</div>
                    <div field="salaryName" width="140" headerAlign="center" align="center">薪资项目</div>
                    <div field="staticValue" width="60" headerAlign="center" align="center" vtype="float;required;maxLength:50">值*
                        <input property="editor" class="mini-textbox" style="width:100%" minValue="-99999.9" maxValue="999999.9"/>
                    </div>
                </div>
            </div>
        </s:iterator>
        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
            <span style="padding-left:5px;">变动历史：</span>
        </div>
        <div id="grid2" class="mini-datagrid" style="width:100%;" pageSize="10"
            idField="id" allowResize="false" borderStyle="border-top:1px solid #C9C9C9">
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOKClose()" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var bxJnGrid = mini.get("bxJnGrid");
        bxJnGrid.hideColumn("id");
        bxJnGrid.load();
        var salaryGroupGrid = mini.get("salaryGroupGrid");
        salaryGroupGrid.hideColumn("id");
        salaryGroupGrid.load();
        var grid = mini.getsbyName("grid");
        for (var i=0; i< grid.length;i++) {
            grid[i].hideColumn("id");
            grid[i].hideColumn("staticId");
            grid[i].load();
        }
        var grid1 = mini.get("grid1");
        grid1.hideColumn("id");
        grid1.load();
        var grid2 = mini.get("grid2");
        var filmName = mini.get("filmName");
        choseDept("deptname");
        choseDept("kqdept");

        var oldFormData;

        $(function() {
            var form = new mini.Form("#form1");
            oldFormData = form.getData();
            dynamicTable();
            if ('${mapData.GD_DATE}' == null || '${mapData.GD_DATE}' == "") {
            	var dd = new Date(); 
            	dd.setDate(dd.getDate()-1);
            	mini.get("gdDate").setValue(dd.getFullYear()+"-"+(dd.getMonth()+1)+"-"+dd.getDate())
            }
        });

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

            // 比较是否存在异动信息
            // 公司、部门、具体岗位、标准岗位、薪酬岗位、工资标准
            var change = 0
            if (oldFormData.filmName != formjson.filmName) {
                change = 1
            } else if (oldFormData.deptname != formjson.deptname) {
                change = 1
            } else if (oldFormData.specificPost != formjson.specificPost) {
                change = 1
            } else if (oldFormData.salaryPost != formjson.salaryPost) {
                change = 1
            }

            var gridjson="";
            if(grid1){
                // 验证
                grid1.validate();
                if (grid1.isValid() == false) {
                    gridError(grid1);
                    return;
                }
                var data = grid1.getData();
                if(data.length<1){// 空行也算length
                    mini.alert("账号详细信息为空，保存失败！");
                    return;
                }
                // 判断grid是否多个是否发薪
                for ( var i = 0, l = data.length; i < l; i++) {
                    var row = data[i];
                    var row = data[i];
                    if(row.id == ""||typeof(row.id)== "undefined"){
                        row._state="added";
                        grid1.updateRow(row,row);
                    }else{
                        row._state="modified";
                        grid1.updateRow(row,row);
                    }
                }
                var griddata = grid1.getChanges();
                gridjson= mini.encode(griddata);
            }

            // 标准类项目
            var grid = mini.getsbyName("grid");
            var gridStandardData="";
            var standard = {};
            var gridChange = 0;
            for (var i=0; i< grid.length;i++) {
                if (grid[i]) {
                    grid[i].validate();
                    if (grid[i].isValid() == false) {
                        gridError(grid[i]);
                        return;
                    }
                    var griddata = grid[i].getData();
                    for (var j=0; j < griddata.length; j++) {
                        var prop = {};
                        prop.id = griddata[j].id;
                        prop.staticId = griddata[j].staticId;
                        prop.staticValue = griddata[j].staticValue;
                        prop.salaryName = griddata[j].salaryName;
                        prop.groupId = griddata[j].groupId;
                        prop.itemId = griddata[j].itemId;
                        standard[prop.staticId] = prop;
                    }
                    var standGrid = grid[i].getChanges("modified", true);
                    if (standGrid.length > 0) gridChange = 1;
                }
            }
            gridStandardData = mini.encode(standard);

            window.parent.loading();
            $.ajax({
                url : "../salary/salary_record!saveOrUpdate.action",
                type : "post",
                data : {
                    formdata : formjson,
                    griddata : gridjson,
                    standardData: gridStandardData,
                    isChange: change,
                    isGridChange: gridChange
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

        // 单位选择
        function onButtonEditFilmDept(e, n) {
            mini.open({
                url: "../salary/salary_record!company.action",
                showMaxButton: false,
                title: "选择单位",
                width: 320,
                height: 480,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        if (data) {
                            e.sender.setValue(data.id);
                            e.sender.setText(data["name"]);
                            choseDept(n);
                        }
                    }
                }
            });
        }

        // 选择部门
        function choseDept(deptname) {
            var depId = mini.get(deptname);
            depId.setUrl("../basis/branch!depData.action?cid=" + filmName.getValue());
        }

        // 考勤单位选择
        function onButtonEditKQDept(e) {
            var buttonEdit = e.sender;
            var win = new TreeSelectWindow();
            win.set({
              //multiSelect: true,
              //showFolderCheckBox: true,
              //checkRecursive: true,                
                url: "../basis/branch!getAllTreeList.action",    
                title: "选择考勤单位",
                width: 350,
                height: 300
            });
            
            win.show();
            //初始化数据
            win.setData(null, function (action) {
                if (action == "ok") {
                   //获取数据
                    var data = win.getData();
                    if (data) {
                        buttonEdit.setValue(data.id);
                        buttonEdit.setText(data.text);
                    }
                }
            });
        }

        // 账号信息验证
        function onCellValidation(e) {
            var field = e.field, record = e.record;

            // 账号
            if (field == "account") {
                onEnglishAndNumberValidation(e);
            }
        }

        function onEnglishAndNumberValidation(e) {
            if (e.isValid) {
                if (isEnglishAndNumber(e.value) == false) {
                    e.errorText = "必须输入英文+数字";
                    e.isValid = false;
                }
            }
        }

        grid1.on("drawcell", function(e) {
            // 操作
            if (e.field=="ctr") {
                var html = '<a href="javascript:void(0);" onclick="addBxRows('+e.rowIndex+')">增加</a>'+
                    '&nbsp;&nbsp;<a href="javascript:void(0);" onclick="removeBxRows('+e.rowIndex+')">删除</a>';
                e.cellHtml=html;
            }
        });

        grid1.on("load", function(e) {
            if (e.total == 0) {
                addBxRows(0);
            }
        });

        /** 增加行 */
        function addBxRows(rowIndex) {
            var newRow = {};
            grid1.selectAll(false);
            grid1.addRow(newRow, rowIndex +1);
            var row=grid1.getRow(rowIndex+1);
            grid1.updateRow(row,row);
            grid1.deselectAll (false);
        }

        /** 添加页面用到的删除行方法 */
        function removeBxRows(rowIndex) {
            var rows = grid1.getRow(rowIndex);
            grid1.removeRow(rows, false);
        }

        // 薪酬岗位选择
        function onButtonEditSalaryDeptAlert(e) {
            var salaryDeptGrid = mini.get("salaryDeptGrid");
            salaryDeptGrid.hideColumn("id");
            salaryDeptGrid.setUrl("../salary/salary_dept!getDataList.action");
            salaryDeptGrid.load({searchData: "{'name': '"+ mini.get("name").getValue() +"'}"});
        }

        //选择岗位
        function onButtonEdit1(e) {
            var btnEdit = this;
            var depart=mini.get("deptname").getValue();
            var postId=mini.get("post");
            var specificPost=mini.get("specificPost");
            if (depart == "") {
                mini.alert("请选择部门");
                return;
            }
            mini.open({
                url: "../post/position!show.action?id="+depart,
                showMaxButton: false,
                title: "选择岗位",
                width: 400,
                height: 300,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        if (data) {
                            btnEdit.setValue(data.id);
                            btnEdit.setText(data.positionName);
                            postId.setValue(data.positionId);//标准岗位id
                            postId.setText(data.positionName2);//标准岗位名称
                        }
                    }
                }
            });
        }

        function onCellEndEdit(e) {
            var field = e.field, record = e.record, editor = e.editor;;
            // 值
            if (field == "staticValue") {
                // 将值转换为目标格式
                var v = parseFloat(record.staticValue).toFixed(record.xs);
                this.updateRow(e.record, {staticValue: $.trim(v)});
            }
        }

        var pageIndex = 0;
        var pageSize = grid2.getPageSize();
        function dynamicTable() {
            $.ajax({
                url: "../salary/salary_record!getChangeHistoryForStaff.action?pageIndex="+pageIndex+"&pageSize="+pageSize,
                type: "POST",
                data : {
                    staffId : '${mapData.ID}'
                },
                success: function(text) {
                    var jsonData = mini.decode(text);
                    var arr = [];
                    arr.push({ type: "checkcolumn", headerAlign:"center", width : 40});
                    arr.push({ type: "indexcolumn", headerAlign:"center", width: 40,header: "序列"});
                    arr.push({ field: "id", id:"id", name:"id", headerAlign:"center", width: 40});
                    arr.push({ type:"comboboxcolumn", field:"changeType", width:"80", headerAlign:"center", align:"center", header: "异动类型",
                                  editor: { type: "combobox", url: "../system/dectionary!listjson.action?id=402881955bdb82a5015bdc08b32b0013", textField:"dicname",valueField:"dicvalue"}});

                    // 循环便利所有的福利项目
                    var choseItems = [];
                    var originalColumns = [];
                    originalColumns.push({ field:"originalCompany", width:"100", headerAlign:"center", align:"center", header: "公司"});
                    originalColumns.push({ field:"originalDept", width:"60", headerAlign:"center", align:"center", header: "部门"});
                    originalColumns.push({ field:"originalJtgw", width:"100", headerAlign:"center", align:"center", header: "具体岗位"});
                    originalColumns.push({ field:"salaryPostName", width:"100", headerAlign:"center", align:"center", header: "薪酬岗位"});

                    var nowColumns = [];
                    nowColumns.push({ field:"nowCompany", width:"100", headerAlign:"center", align:"center", header: "公司"});
                    nowColumns.push({ field:"nowDept", width:"60", headerAlign:"center", align:"center", header: "部门"});
                    nowColumns.push({ field:"nowJtgw", width:"100", headerAlign:"center", align:"center", header: "具体岗位"});
                    nowColumns.push({ field:"nowBzgw", width:"100", headerAlign:"center", align:"center", header: "标准岗位"});
                    nowColumns.push({ field:"salaryPostName2", width:"100", headerAlign:"center", align:"center", header: "薪酬岗位"});

                    var fulis = mini.decode(jsonData.ydItems);
                    var xm = [];
                    xm["岗位绩效工资标准"] = "岗位绩效工资标准";
                    xm["绩效考核标准"] = "绩效考核标准";
                    xm["基础工资标准"] = "基础工资标准";
                    xm["绩效工资保留"] = "绩效工资保留";
                    for (var i=0; i< fulis.length;i++) {
                        if (xm[fulis[i].ITEMS_NAME] == fulis[i].ITEMS_NAME) {
	                        choseItems[fulis[i].IBF_ID] = 0;
	                        originalColumns.push({ field: "original" + fulis[i].IBF_ID, width:"110", headerAlign:"center", align:"center", header: fulis[i].ITEMS_NAME});
	                        nowColumns.push({ field: "now" + fulis[i].IBF_ID, width:"110", headerAlign:"center", align:"center", header: fulis[i].ITEMS_NAME});
                    	}
                    }

                    arr.push({ headerAlign:"center", header: "原岗位", columns : originalColumns});
                    arr.push({ headerAlign:"center", header: "现岗位", columns : nowColumns});
                    arr.push({ field:"changeDate", width:"110", headerAlign:"center", align:"center", header: "调动日期", renderer: ondayRenderer2});

                    var dd = jsonData.data;
                    var table = [];
                    for (var i=0; i< dd.length;i++) {
                        var row = dd[i];
                        var column = {id: row.id, changeType: row.changeType, originalCompany: row.originalCompany, originalDept: row.originalDept, originalJtgw: row.originalJtgw,
                                salaryPostName: row.salaryPostName, nowCompany: row.nowCompany, nowDept: row.nowDept, nowJtgw: row.nowJtgw, nowBzgw: row.nowBzgw,
                                salaryPostName2: row.salaryPostName2, changeDate: row.changeDate};
                        var objs = eval(column);
                        var newObjs = $.extend({}, objs, choseItems);
                        if (dd[i].ibfId != null) {
                            var flId = dd[i].ibfId.split(",");
                            var adjustFronts = dd[i].adjustFront.split(",");
                            var adjustNexts = dd[i].adjustNext.split(",");
                            for (var j=0; j< flId.length;j++) {
                                newObjs["original"+flId[j]] = adjustFronts[j];
                                newObjs["now"+flId[j]] = adjustNexts[j];
                            }
                        }
                        table.push(newObjs);
                    }
                    grid2.setTotalCount(jsonData.total);
                    grid2.setPageIndex(pageIndex);
                    grid2.setPageSize(pageSize);
                    grid2.set({columns : arr});
                    grid2.hideColumn("id");
                    grid2.frozenColumns(0, 3);
                    grid2.setData(table);
                }
            });
        }

        grid2.on("beforeload", function (e) {
            pageIndex = e.data.pageIndex;
            pageSize = e.data.pageSize;
            dynamicTable();
        });

    </script>
</body>
</html>