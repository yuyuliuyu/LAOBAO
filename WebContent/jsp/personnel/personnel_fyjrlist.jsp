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
<title>复员军人列表</title>
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
            <div size="300" showCollapseButton="true">
				<!-- ckId -->
				<input type="hidden" value="${sessionScope.depId}" id="depId" />
				<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">
					<span style="padding-left:5px;">组织机构：</span>
				</div>
				<div class="mini-fit" style=" border-top:0;">
					<ul id="deptree" class="mini-tree" url="../basis/branch!getTreeListByUser.action" style="width:100%;height:100%;"
						showTreeIcon="true" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="true" >
					</ul>
				</div>
			</div>
            <!-- 分中心树  end -->
            <div showCollapseButton="false">
            	<div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
       				 <table style="width:100%;">
          				  <tr>
          				  	<td style="width:200px;">职工号：
                   				 <input id="jobNumber" name="jobNumber" class="mini-textbox" emptyText="请输入员工职工号" onenter="onKeyEnter"/>
              			   </td>
            			    <td style="width:200px;">姓名：
                   				 <input id="name" name="name" class="mini-textbox" emptyText="请输入员工姓名" onenter="onKeyEnter"/>
              			   </td>
               			   <td style="white-space:nowrap;">
                 			   <a class="mini-button" iconCls="icon-search" onclick="search('jobNumber,name,post','jobNumber,name,post')" onenter="onKeyEnter">查询</a>
               			   </td>
           				 </tr>
       				 </table>
   			    </div>
	            <div class="mini-toolbar" style="padding:0px;border-top:0;">
					<table style="width:100%;">
						<tr>
							<td style="width:100%;">
								<a class="mini-button" iconCls="icon-search" onclick="look()">查看详细信息</a>
							</td>
						</tr>
					</table>
				</div>
	    		<div class="mini-fit">
	    		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
             		url="../personnel/personnel!getFyjrPersonData.action" idField="id" multiSelect="true" 
            		 allowAlternating="true" pageSize="100" >
		                <div property="columns">
							<div type="checkcolumn">选择</div>
							<div type="indexcolumn" headerAlign="center">序列</div>
							<div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
							<div field="jobNumber" id ="jobNumber" name = "jobNumber"width="100" headerAlign="center" align="left">职工编号</div>
							<div field="name" id ="name" name = "name"width="100" headerAlign="center" align="left">姓名</div>
							<div field="age" id ="age" name = "age"width="100" headerAlign="center" align="left">年龄</div>
							<div type="comboboxcolumn" field="sex" width="100" headerAlign="center" align="center">性别
								<input property="editor" class="mini-combobox" data="[{id:0, text:'女'},{id:1, text:'男'}]"/>
							</div>
			                        	
							<div type="comboboxcolumn" field="isMarried" width="100" headerAlign="center" align="center">婚姻状态
								<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881a05b650042015b6639e3d1003b" textField="dicname" valueField="dicvalue"/>
							</div>
							<div field="jobNumberJt" id ="jobNumberJt" name = "jobNumberJt"width="100" headerAlign="center" align="left">集团工号</div>
							<div field="filmName" width="100" headerAlign="center" align="left">公司</div>
	                        <div field="departName" width="100" headerAlign="center" align="left">部门</div>
							<div field="post" id ="post" name = "post" width="100" headerAlign="center" align="center" >岗位</div>
							<div field="jobLevel" id ="jobLevel" name = "jobLevel" width="100" headerAlign="center" align="center" >职务</div>
							<div type="comboboxcolumn" field="empType" width="100" headerAlign="center" align="center">员工类型
								<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935baceaa1015bad355ba8000a" textField="dicname" valueField="dicvalue"/>
							</div>
							<div type="comboboxcolumn" field="onJob" width="100" headerAlign="center" align="center">在职状态
								<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935baceaa1015bae044344001a" textField="dicname" valueField="dicvalue"/>
							</div>
		                 	<div type="comboboxcolumn" field="onPost" width="100" headerAlign="center" align="center">在岗状态
		  						<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955bc84595015bc87aa73e0010" textField="dicname" valueField="dicvalue"/>
							</div>
						</div>
       		 		</div>
       		 	</div>
            </div>
        </div>
	</div>


    
    <script type="text/javascript">
        mini.parse();
        
        var grid = mini.get("grid");
        grid.hideColumn("id");

        grid.load();
        function onKeyEnter(e) {
        	search('jobNumber,name,post','jobNumber,name,post');
        }
        //////////////////////////////////////////////
        //人员管理基础数据添加
        function addperson() {
        	var tabs = window.parent.mini.get("mainTabs");
        	var id = "add";
        	var tab = tabs.getTab(id);
        	if (tab) {
        	    tabs.removeTab(tab);
        	}
        	tab = {};
        	tab.name = id;
        	tab.title = "员工管理添加";
        	tab.showCloseButton = true; 
        	tab.url = "../personnel/personnel!add.action?flag=0";
        	tabs.addTab(tab);
        	tabs.activeTab(tab);
        }
        
        //////////////////////////////////////////////
        //员工查看页面
         function look() {
        	
        	var row = grid.getSelected();
         	if(row){
         		var tabs = window.parent.mini.get("mainTabs");
                 var tab = { title: "员工管理查看", showCloseButton: true };
                 tab.url = "../personnel/personnel!all.action?personId="+row.id;
                 tabs.addTab(tab);
                 tabs.activeTab(tab);
         	}else{
         		mini.alert("请选中一条记录！");
         	}

        } 
        
        /* function look() {
        	var tabs = window.parent.mini.get("mainTabs");
        	var id = "look";
        	var tab = tabs.getTab(id);
        	if (tab) {
        	    tabs.removeTab(tab);
        	}
        	tab = {};
        	tab.name = id;
        	tab.title = "员工管理查看";
        	tab.showCloseButton = true; 
        	tab.url = "../personnel/personnel!all.action";
        	tabs.addTab(tab);
        	tabs.activeTab(tab);
             
        } */ 
         
        ////////////////////////////////////////////////
        function remove() {
            var row = grid.getSelected();
            
            if (row) {
                var list = grid.getAllChildNodes( grid.getSelectedNode( ));
                var ids = row.id;
                var cid = row.cid;
                if (grid.hasChildren(grid.getSelectedNode())) {
                	mini.showMessageBox({
                		title:"确定吗？",
                		message:"该部门下带有下级部门，您确定继续执行吗？",
                		buttons: ["ok", "no"],
                		callback: function(action){
                			if(action =="ok"){
                				 for(var i=0;i<list.length;i++){
                                     ids = ids +","+list[i].id;
                                 }
                				 zhixing(ids,cid);
                			}else{
                				return;
                			}
                		}
                	});
                    
                }else{
                	zhixing(ids,cid);
                }
            } else {
                 mini.alert("请选中一条记录！");
            }
        }
        function lizhi() {
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url : "../personnel/personnel!lizhi.action?cid="+row.cid+"&id=" + row.id + "&did=" + row.did,
                    title : "编辑部门",
                    width : 770,
                    height : 540,
                    /* allowResize : false, */
                    onload : function() {
                    },
                    ondestroy : function(action) {
                        grid.reload();
                    }
                });
            } else {
                mini.alert("请选中一条记录！");
            }
        }
       
        function tuixiu() {
            var row = grid.getSelected();
            if (row) {
                mini.open({
                    url : "../personnel/personnel!tuixiu.action?cid="+row.cid+"&id=" + row.id + "&did=" + row.did,
                    title : "编辑部门",
                    width : 770,
                    height : 540,
                    /* allowResize : false, */
                    onload : function() {
                    },
                    ondestroy : function(action) {
                        grid.reload();
                    }
                });
            } else {
                mini.alert("请选中一条记录！");
            }
        }
       /*         
        function search() {
            var key = mini.get("key").getValue();
            grid.load({
                key : key,searchBy:"name"
            });
        } */
                

    	

    </script>
</body>
</html>