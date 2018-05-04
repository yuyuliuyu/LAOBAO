<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>用户管理列表</title>
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
    <div class="mini-fit"> 
            	<!-- 用户 start --> 
           <div class="mini-toolbar" style="border-top:0;border-left:0; padding:0px;">
                    <table style="width:100%;">
                        <tr>
                            <td style="width:100%;">
                                <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                                <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
                                <a class="mini-button" iconCls="icon-cut" onclick="unable()">禁用</a>
                                <a class="mini-button" iconCls="icon-ok" onclick="able()">启用</a>
                                <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a> 
                                <a class="mini-button" iconCls="icon-edit" onclick="authEdit()">数据权限</a>
                                <%-- <a class="mini-button" iconCls="icon-zhuanyibumen" onclick="change()">转移部门</a>--%>
                            </td>
                            <td style="white-space:nowrap;">用户名：
                               <input id="key" class="mini-textbox" emptyText="请输入用户名" onenter="onKeyEnter"/>
                               <a class="mini-button" onclick="searchd()" iconCls="icon-search">查询</a>
                           </td> 
                        </tr>
                    </table>
            </div>
            <div id="grid2" class="mini-datagrid" style="width:100%;height:94.6%;"
              allowAlternating="true" idField="id"  pageSize="20" showPager="true" multiSelect="true" >
                    
                    <div property="columns">
                        <div type="checkcolumn" width="50" ></div>
                        <div type="indexcolumn" width="50" headerAlign="center">序列</div>
                        <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden"></div>
						<div field="did" id="did" width="80" name="did" headerAlign="center" align="center" class="mini-hidden"></div>
						<div field="cid" id="cid" width="80" name="cid" headerAlign="center" align="center" class="mini-hidden"></div> 
                        <div headerAlign="center" header="基本信息">
                            <div property="columns">
                                <div field="name" width="100" headerAlign="center" align="center">姓名</div>
                                <div field="email" width="100" headerAlign="center" align="center">邮箱</div>
                                <div field="sdiscount" id="sdiscount" name="sdiscount" width="60" headerAlign="center" align="center">折扣</div>
                                <div field="ldiscount" id="ldiscount" name="ldiscount" width="60" headerAlign="center" align="center">领导折扣</div>
                                <div field="isleader" width="100" headerAlign="center" align="center">是否为领导</div>
                            </div>
                        </div>
                         <div field="enable" width="80" headerAlign="center" align="center">状态</div>
                        <div field="createdate" width="120" headerAlign="center" align="center" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss">创建时间</div>
                    </div>
                </div> 
             <!-- 用户end -->  
                 
        <!-- 部门用户split end --> 
    </div>
    <script type="text/javascript">
        mini.parse();
         
        var grid = mini.get("grid2"); 
		grid.hideColumn("id");
        grid.hideColumn("did");
		grid.hideColumn("cid");

        tree.on("nodeselect", function (e) {
            var node = tree.getSelectedNode();
            if (node) {
				treegrid.setUrl("../basis/branch!depData.action");
                treegrid.load({cid:node.id}); 
            } else { 
            }
        });
        
        treegrid.on("nodeselect",function(e){
        	var node = tree.getSelectedNode();
        	var tnode = e.node;
        	var pid=tnode.pid;
        	var did=tnode.did;
        	var depName=e.node.name;
        	if(node){
        		if(tnode){
        			var ids = e.node.id;
        			var list = treegrid.getChildNodes(treegrid.getSelectedNode());
                    for ( var i = 0; i < list.length; i++) {
                        ids = ids + "," + list[i].id;
                    }
        			mini.get("key").setValue(null);
        			grid.setUrl("../system/admin!userList.action");
        			grid.load({cid:node.id,depId:ids,key:null});
        		}
        	}
        });
         
        function searchd() {
            var key = mini.get("key").getValue();
            var node = tree.getSelectedNode();
            var treenode = treegrid.getSelectedNode();
            if(node){
            	if(treenode){
            		grid.setUrl("../system/admin!userList.action");
            		grid.load({ "key": key,"depId":treenode.did,"cid":node.id });
            	}else{
            		mini.alert("请选择部门!");
            		return;
            	}
                
            }else{
                mini.alert("请先选中单位!");
                return;
            }
        }
        
        function onKeyEnter(e) {
            searchd();
        }
        function onbmKeyEnter(e) {
        	bmsearch();
        }
    </script>
</body>
</html>