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
    <title>绩效考核</title>
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
                    &nbsp;姓名：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('name,','name')">查询</a>
                    <a class="mini-button" iconCls="icon-download" onclick="exportExcelData('branch', 'performance_appraisal','name,','name')">导出</a>
                    <br><a class="mini-button" iconCls="icon-add" onclick="addWindow('绩效考核','branch', 'performance_appraisal', 670, 360, false)">增加</a>
                    <a class="mini-button" iconCls="icon-edit" onclick="editWindow('绩效考核','branch', 'performance_appraisal', 670, 360, false)">编辑</a>
                    <a class="mini-button" iconCls="icon-remove" onclick="remove('','branch','performance_appraisal', false)">删除</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit"> 
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="50"
            url="../branch/performance_appraisal!jsonlist.action" 
            allowAlternating="true" multiSelect="true" idField="id" allowResize="false">
            <div property="columns">
                <div type="checkcolumn" width="50"></div>
                <div type="indexcolumn" headeralign="center"  width="30">序列</div>
                <div field="id" name="id" id="id" width="100" headerAlign="center" align="center">id</div> 
                <div field="deptName" width="100" headerAlign="center" align="center">部门</div>
                <div field="jobNumber" width="100" headerAlign="center" align="center">工号</div>
                <div field="userName" width="100" headerAlign="center" align="center">责任人</div>
                <div field="perforDeptName" width="100" headerAlign="center" align="center">考核部门</div>
                <div type="comboboxcolumn" field="perforStyle" width="100" headerAlign="center" align="center">考核分类
                    <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945d359d52015d35e45d3e0009"
                        textField="dicname" valueField="dicvalue"/>
                </div>
                <div field="perforThing" width="120" headerAlign="center" align="center">考核事件</div>
                <div header="考核奖励" headerAlign="center">
                    <div property="columns">
                        <div field="perforRewardScroe" width="80" headerAlign="center" align="center">考核分数</div>
                        <div field="perforRewardMoney" width="80" headerAlign="center" align="center">考核金额</div>
                    </div>
                </div>
                <div header="考核扣罚" headerAlign="center">
                    <div property="columns">
                        <div field="perforPunishScroe" width="80" headerAlign="center" align="center">考核分数</div>
                        <div field="perforPunishMoney" width="80" headerAlign="center" align="center">考核金额</div>
                    </div>
                </div>
                <div field="perforDate" width="100" headerAlign="center" align="center" renderer="ondayRenderer2">考核日期</div>
                <div type="comboboxcolumn" field="perforAppr" width="100" headerAlign="center" align="center">考核确认
                    <input property="editor" class="mini-combobox" data="[{id:1,text:'确认'},{id:0,text:'不确认'}]"/>
                </div> 
                <div type="comboboxcolumn" field="perforState" width="100" headerAlign="center" align="center">考核兑现
                    <input property="editor" class="mini-combobox" data="[{id:1,text:'兑现'},{id:0,text:'不兑现'}]"/>
                </div>  
            </div>
        </div>
    </div>
    <script type="text/javascript">
        mini.parse(); 

    </script>
</body>
</html>