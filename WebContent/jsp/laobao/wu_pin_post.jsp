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
<title>基础信息列表</title>
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
        <div class="mini-splitter" style="width:100%;height:100%;" handlerSize="9">
            
            <!-- 分中心树 start -->
            <div size="260" showCollapseButton="true">
                <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
                    <span style="padding-left:5px;">部门名称：</span>
                </div>
                <div class="mini-fit">
                    <ul id="tree1" class="mini-tree" url="../basis/branch!treeList.action" style="width:100%;" onNodeclick="onNodeclick"
                        showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true">
                    </ul>
                </div>
            </div>

            <!-- 分中心树  end -->
            <div showCollapseButton="false">
                <div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
                    <table style="width:100%;">
                        <tr>
                            <td style="width:200px;">编码：
                                <input id="positionCode" name="positionCode" class="mini-textbox" emptyText="请输入岗位编码" onenter="onKeyEnter"/>
                            </td>
                            <td style="width:200px;">名称：
                                <input id="positionName" name="positionName" class="mini-textbox" emptyText="请输入岗位名称" onenter="onKeyEnter"/>
                            </td>
                            <td style="white-space:nowrap;">
                                 <a class="mini-button" iconCls="icon-search" onclick="search('positionCode,positionName','positionCode,positionName')" onenter="onKeyEnter">查询</a>
                            </td>
                         </tr>
                    </table>
                </div>
                <div class="mini-fit">
                    <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
                        url="../post/position!listData.action" idField="id" multiSelect="true" 
                         allowAlternating="true" pageSize="20" >
                         
                        <div property="columns">
                            <div type="checkcolumn"></div>
                            <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                            <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
                            <div field="dutyId" id="dutyId" width="80" name="dutyId" headerAlign="center" align="center" class="mini-hidden">职务族id</div>
                            <div field="positionCode" id ="positionCode" name = "positionCode"width="100" headerAlign="center" align="left">编码</div>
                            <div field="positionName" id ="positionName" name = "positionName"width="100" headerAlign="center" align="left">名称</div>
                            <div field="dutyName" id ="dutyName" name = "dutyName"width="100" headerAlign="center" align="left">职务族</div>
                            <div field="positionGrad" id ="positionGrad" name = "positionGrad"width="100" headerAlign="center" align="left">职等</div>
                            <div field="applyDate" id ="applyDate" name = "applyDate"width="100" headerAlign="center" align="left">生效日期</div>
                            <div field="state" id ="state" name = "state" width="100" headerAlign="center" align="center" >状态</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">选择</a> 
        <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
    </div>

    <script type="text/javascript">
        mini.parse();
        
        var grid = mini.get("grid");
        grid.hideColumn("id");
        grid.hideColumn("dutyId");
        
        var tree = mini.get("tree1");
        //////////////////////////////////////////////
          /* function look() {
           
            
            var tab = { title: "tab", showCloseButton: true };
           
            tabs.addTab(tab);

            tabs.activeTab(tab);

        } */
        function GetData() {
        	return grid.getSelected();
        }
        function onOk() {
        	var row = grid.getSelected();
        	if (!row) {
        		mini.alert("请选择一条数据");
        		return;
        	}
        	CloseWindow("ok");
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
        function onKeyEnter(e) {
            search('positionCode,positionName','positionCode,positionName');
        }
        
    	function onNodeclick(e) {
    		
	    	var node = tree.getSelectedNode();
		  	var nodeId = node.id;
    		grid.setUrl("../post/position!listData.action?id="+node.id);
            grid.load();
     }

    </script>
</body>
</html>