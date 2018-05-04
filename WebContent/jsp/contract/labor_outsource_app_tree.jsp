<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>人员选择列表</title>
<style type="text/css">
</style>
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../template/plugin/public/cent_dep.js"></script>
<script src="${base}/template/plugin/public/treeList.js" type="text/javascript"></script>
<style type="text/css">
body {
    margin: 0;
    padding: 0;
    border: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
}
</style>
</head>

<body>
	<div class="mini-fit">
        <div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
      		<table style="width:100%;">
         		<tr>
         			<td style="width:200px;">项目编号：
                  		<input id="projectCode" name="projectCode" class="mini-textbox" emptyText="请输入项目编号" onenter="onKeyEnter"/>
             		 </td>
           			 <td style="width:200px;">名称：
                  		<input id="projectName" name="projectName" class="mini-textbox" emptyText="请输入项目名称" onenter="onKeyEnter"/>
             		 </td>
              		 <td style="white-space:nowrap;">
                		 <a class="mini-button" iconCls="icon-search" onclick="search('projectCode,projectName','projectCode,projectName')" onenter="onKeyEnter">查询</a>
              		 </td>
          		 </tr>
      		</table>
  		</div>
	            
	    <div class="mini-fit">
	    		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
             		url="../contract/labor_outsource_app!listdata.action" idField="id"
            		 allowAlternating="true" pageSize="20" >
		                <div property="columns">
	                        <div type="checkcolumn">选择</div>
	                        <div type="indexcolumn" headerAlign="center">序列</div>
	                        <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
					         <div field="projectCode" id ="projectCode" name = "projectCode" width="100" headerAlign="center" align="left">项目编号</div>
					         <div field="applyComName" id ="applyComName" name = "applyComName"width="100" headerAlign="center" align="left">申请单位</div>
					         <div field="applyCom" id ="applyCom" name = "applyCom"width="100" headerAlign="center" align="left">申请单位</div>
					         <div field="projectName" id ="projectName" name = "projectName"width="100" headerAlign="center" align="left">项目名称</div>
					         <div field="payComName" id ="payComName" name = "payComName"width="100" headerAlign="center" align="left">付款单位</div>
					         <div field="payCom" id ="payCom" name = "payCom"width="100" headerAlign="center" align="left">付款单位</div>
					         <div field="prePurPrice" id ="prePurPrice" name = "prePurPrice"width="100" headerAlign="center" align="right">前期采购价格</div>
					         <div field="estimateConValue" id ="estimateConValue" name = "estimateConValue"width="100" headerAlign="center" align="right">预计合同值</div>
					         <div field="preContractor" id ="preContractor" name = "preContractor"width="100" headerAlign="center" align="left">前期承包方</div>
					         
					         <div field="timeRequest" id ="timeRequest" name = "timeRequest"width="100" headerAlign="center" align="left">时间要求</div>
					         <div field="technicalReq" id ="technicalReq" name = "technicalReq"width="100" headerAlign="center" align="left">技术要求</div>
					         <div type="comboboxcolumn" field="state" width="100" headerAlign="center" align="center">状态
			                   <input property="editor" class="mini-combobox" data="[{id:'1', text:'审批中'},{id:0, text:'未提交'},{id:2, text:'已审批'},{id:3, text:'已驳回'}]"/>
			               	 </div>
	                    </div>
       		 	</div>
       	</div>
       	<div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" 
         borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="mini-button" style="width:60px;" onclick="onOk()" iconCls="icon-save">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" style="width:60px;" onclick="onCancel()" iconCls="icon-close">取消</a>
    </div>
	</div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        grid.hideColumn("id");
        grid.hideColumn("applyCom");
        grid.hideColumn("payCom");
        grid.load();
        
        function onKeyEnter(e) {
            search();
        }
        function onOk() {
            
            CloseWindow("ok");
        }
        function GetData() {
            var row = grid.getSelected();
            return row;
        }
        function onCancel() {
            CloseWindow("cancel");
        }
        function CloseWindow(action) {
            if (window.CloseOwnerWindow) 
            return window.CloseOwnerWindow(action);
            else window.close();
        }
    </script>
</body>
</html>