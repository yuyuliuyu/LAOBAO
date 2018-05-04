<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>管理员列表</title>

    <style type="text/css">
    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }    
    </style>  
    
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    
    <!--引入皮肤样式-->
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />

</head>
<body>
   
    
<div class="mini-splitter" style="width:100%;height:100%;">
    <div size="240" showCollapseButton="true">
        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">                
            <span style="padding-left:5px;">名称：</span>
            <input class="mini-textbox" />
            <a class="mini-button" iconCls="icon-search" plain="true">查找</a>                  
        </div>
        <div class="mini-fit">
            <ul id="tree1" class="mini-tree" url="../depart/backend_dep!treelist.action" style="width:100%;"
                showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false"
                
            >        
            </ul>
        </div>
    </div>
     <div showCollapseButton="false">
    
        <div class="mini-toolbar" style="/* border-bottom:0; */border-top:0;border-left:0; padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="white-space:nowrap;">
                        <input id="key" class="mini-textbox" emptyText="请输入姓名" style="width:150px;" onenter="onKeyEnter"/>   
                        <a class="mini-button" onclick="search()">查询</a>
                    </td>
                </tr>
            </table>           
        </div>
   
    <div class="mini-fit" >
    <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" 
                borderStyle="border:0;"
                url="admin!json.action"
                showFilterRow="true" idField="id"  multiSelect="true" onrowdblclick="onRowDblClick"             
            >
        <div property="columns">
            <div type="indexcolumn"></div>       
            <div type="checkcolumn" ></div>   
             <div field="id" id="id" width="120" name="idColumn" headerAlign="center" allowSort="true"  class="mini-hidden" >id</div>  
            <div field="username" width="120" headerAlign="center" allowSort="true">员工帐号</div>    
            <div field="name" width="120" headerAlign="center" allowSort="true">姓名</div>    
            <div field="createdate" width="100" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">创建日期</div>    
            <div header="基本信息">
                <div property="columns">
                    <div field="email" width="100">邮箱</div>
                </div>
            </div>                 
        </div>
    </div>
    </div>
   </div>         
</div>
    
    <script type="text/javascript">
        mini.parse();

        var tree = mini.get("tree1");
        
        var grid = mini.get("grid1");
        
        grid.hideColumn("idColumn");
        

        tree.on("nodeselect", function (e) {
 
 		var node = tree.getSelectedNode();
            if (node) {
                grid.load({ depId: e.node.Id });
            } else {
                grid.setData([]);
                grid.setTotalCount(0);
            }
        });

        function search() {
            var key = mini.get("key").getValue();
            var node = tree.getSelectedNode();
			
            if(node){
            	grid.load({ key: key,searchBy:"name",depId:node.Id });
            }else{
            	alert("请先选择部门");
            }
        }
        function onKeyEnter(e) {
            search();
        }

       /*获取选中行*/
		function GetData() {
        var row = grid.getSelected();
        return row;
	    }
	    
	    /*双击直接确定选中行*/
	    function onRowDblClick(e) {
	        onOk();
	    }
	    
		function onOk() {
	        CloseWindow("ok");
	    }
	    
	    function onCancel() {
	        CloseWindow("cancel");
	    }
	    
	    function CloseWindow(action) {
	        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
	        else window.close();
	    }
	    
		/*刷新*/
		function refush() {
            grid.load();
        }        
        
    </script>

</body>
</html>