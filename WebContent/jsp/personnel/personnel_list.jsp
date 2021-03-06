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
          				  	<td style="width:200px;">职工&nbsp;号：
                   				 <input id="jobNumber" name="jobNumber" class="mini-textbox" emptyText="请输入员工职工号" onenter="onKeyEnter"/>
              			   </td>
            			    <td style="width:200px;">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：
                   				 <input id="name" name="name" class="mini-textbox" emptyText="请输入员工姓名" onenter="onKeyEnter"/>
              			   </td>
            			    <td style="width:200px;">部门岗位：
                   				 <input id="deptName" class="mini-textbox" emptyText="请输入部门岗位" onenter="onKeyEnter"/>
              			   </td>
               			   <td style="white-space:nowrap;">
                 			   <a id="searchButton" class="mini-button" iconCls="icon-search" onclick="search('jobNumber,name,positionId,jobLevel,agebegin,ageend,empType,onJob,onPost,sex,isMarried,deptName','jobNumber,name,positionId,jobLevel,agebegin,ageend,empType,onJob,onPost,sex,isMarried,deptName')" onenter="onKeyEnter">查询</a>
               			   </td>
               			   <td style="white-space:nowrap;">
               			   <a class="mini-button" iconCls="icon-sort" onclick="clickAction()" onenter="onKeyEnter">更多 </a>
           				   </td>
           				 </tr>
       				 </table>
       				 <div id="zhuanzheng" style="display:none">
	       				 <table style="width:100%;">
	              			   <td style="width:200px;">标准岗位：
	              			  		<input name="positionId" id="positionId" class="mini-buttonedit" value="${postPosition.id}"
	                          				vtype="maxLength:50;required" emptyText="请选择..."  onbuttonclick="onPostCodeEdit" />
	              			   </td>
	              			   <td style="width:200px;">职务级别：
	                   				 <input id="jobLevel" name="jobLevel" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935ba8c785015ba8e043c80011" textField="dicname" valueField="dicvalue"/>
	              			   </td>
	              			   <td style="width:200px;">年龄从：
	                   				 <input id="agebegin" name="agebegin" class="mini-textbox" />
	              			   </td>
	              			   <td style="width:200px;">到：
	              			  		<input id="ageend" name="ageend" class="mini-textbox" />
	              			   </td>
	            			</tr>
	            			<tr>
	              			   <td style="width:200px;">员工类型：
	              			   		 <input id="empType" name="empType" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935baceaa1015bad355ba8000a" textField="dicname" valueField="dicvalue"/>
	              			   </td>
	              			   <td style="width:200px;">员工状态：
	                   				 <input id="onJob" name="onJob" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935baceaa1015bae044344001a" textField="dicname" valueField="dicvalue"/>
	              			   </td>
	              			   <td style="width:200px;">在岗状态：
	                   				 <input id="onPost" name="onPost" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955bc84595015bc87aa73e0010" textField="dicname" valueField="dicvalue"/>
	              			   </td>
	              			   <td style="width:200px;">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：
	              			   		<input id="sex" name="sex" class="mini-combobox" url="../template/sex.txt" textField="text" valueField="id"/>
	              			   </td>
	              			   <td style="width:200px;">婚姻状况：
	                   				 <input id="isMarried" name="isMarried" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881a05b650042015b6639e3d1003b" textField="dicname" valueField="dicvalue"/>
	              			   </td>
	           				 </tr>
	       				 </table>
       				 </div>
   			    </div>
   			    
	            <div class="mini-toolbar" style="padding:0px;border-top:0;">
	       			 <table style="width:100%;">
	           			 <tr>
			                <td style="width:100%;">
			                	<tags:hasPerm value="../personnel/personnel!addmini.action">
			                    <a class="mini-button" iconCls="icon-add" onclick="addperson()">增加</a>
			                    </tags:hasPerm>
			                    <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
			                    <a class="mini-button" iconCls="icon-search" onclick="look()">查看员工详细信息</a>
			                    <a class="mini-button" iconCls="icon-upload" onclick="upload()">导入数据</a>
			                    <a class="mini-button" iconCls="icon-upload" onclick="exportJl()">导出人员简历</a>
			                    <a class="mini-button" iconCls="icon-upload" onclick="exporttest('jobNumber,name,positionId,jobLevel,agebegin,ageend,empType,onJob,onPost,sex,isMarried,deptName','jobNumber,name,positionId,jobLevel,agebegin,ageend,empType,onJob,onPost,sex,isMarried,deptName')">自定义导出</a>
			                </td>
	            		 </tr>
	        		</table>
	    		</div>
	    		<div class="mini-fit">
	    		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
             		url="../personnel/personnel!getPersonData.action" idField="id" multiSelect="true" 
            		 allowAlternating="true" pageSize="100" >
            		 
		                <div property="columns">
	                    
	                        <div type="checkcolumn">选择</div>
	                        <div type="indexcolumn" headerAlign="center">序列</div>
	                        <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
	                        <div field="jobNumber" id ="jobNumber" name = "jobNumber"width="100" headerAlign="center" align="left">职工编号</div>
	                        <div field="name" width="100" headerAlign="center" align="left">姓名</div>
	                        <div field="age" width="100" headerAlign="center" align="left">年龄</div>
	                        <div type="comboboxcolumn" field="sex" width="100" headerAlign="center" align="center">性别
                            	<input property="editor" class="mini-combobox" data="[{id:0, text:'女'},{id:1, text:'男'}]"/>
                        	</div>
                        	<div type="comboboxcolumn" field="isMarried" width="100" headerAlign="center" align="center">婚姻状态
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881a05b650042015b6639e3d1003b"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
                        	<div field="jobNumberJt" id ="jobNumberJt" name = "jobNumberJt"width="100" headerAlign="center" align="left">集团工号</div>
	                        <div field="post" width="100" headerAlign="center" align="center" >标准岗位</div>

	                        <div field="specificPost" width="100" headerAlign="center" align="center" >部门岗位</div>
	                        <div type="comboboxcolumn" field="jobLevel" width="100" headerAlign="center" align="center">职务级别
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935ba8c785015ba8e043c80011"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
	                        <div type="comboboxcolumn" field="empType" width="100" headerAlign="center" align="center">员工类型
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935baceaa1015bad355ba8000a"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
                        	<div type="comboboxcolumn" field="onJob" width="100" headerAlign="center" align="center">员工状态
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935baceaa1015bae044344001a"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
                        	
                        	<div type="comboboxcolumn" field="onPost" width="100" headerAlign="center" align="center">在岗状态
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955bc84595015bc87aa73e0010"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
                        	<div type="comboboxcolumn" field="idType" width="100" headerAlign="center" align="center">证件类别
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881935ba8c785015ba8d9d7c0000b"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
	                        <div field="idNumber" width="100" headerAlign="center" align="center" >证件号码</div>
	                        <div type="comboboxcolumn" field="laborCompany" width="200" headerAlign="center" align="center">劳务公司
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955d1ba95e015d1bbcbed5000a"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
                        	<div type="comboboxcolumn" field="edu1" width="100" headerAlign="center" align="center">全日制最高学历
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881a05b650042015b6617c989002f"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
                        	<div type="comboboxcolumn" field="edu2" width="100" headerAlign="center" align="center">在职最高学历
                            	<input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881a05b650042015b6617c989002f"
                            	textField="dicname" valueField="dicvalue"/>
                        	</div>
	                        
	                    </div>
       		 	</div>
       		 	</div>
            </div>
        </div>
	</div>

    <script type="text/javascript">
        mini.parse();
        var tree = mini.get("deptree");
        var grid = mini.get("grid");
        grid.hideColumn("id");
        
        grid.load();
        $(function() {
            tree.selectNode('${depId}');
            mini.get("deptName").setValue('${positionName}');
            mini.get("name").setValue('${staffName}');
            $("#searchButton").click();
        });
        function onKeyEnter(e) {
            $("#searchButton").click();
        }
        //////////////////////////////////////////////
        
        //人员管理基础数据添加
        function addperson() {
        	var node=tree.getSelectedNode();
        	if(node){
        		var nodeId=node.id;
        		var nodeName=node.name;
        		var branchId=node.branchId;
        		$.ajax({
                    url: "../personnel/change!findDep.action?departId="+node.id,
                    type: "post",
                    success: function (text) {
                        if(text=="error"){
                        	mini.alert("选中节点不是部门，请重新操作！");
                            return;
                        }else{
                        	
                        	mini.open({
                                url : "../personnel/personnel!addmini.action?departId="+nodeId+"&branchId="+branchId+"&nodeName="+nodeName,
                                title : "新添加人员信息",
                                width : 900,
                                height : 600,
                                onload : function() {
                                },
                                ondestroy : function(action) {
                                    grid.reload();
                                }
                            });
                        	
                        }
                    }
                }); 
        		
        	}else{
        		mini.alert("请选择部门！");
        	}
        }
        //////////////////////////////////////////////
        //人员管理基础数据修改
        function edit() {
            var row = grid.getSelected();
            if (row) {
            	mini.open({
                    url : "../personnel/personnel!add.action?flag=0&personId="+row.id,
                    title : "编辑人员信息",
                    width : 900,
                    height : 600,
                    
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
        
         function upload() {
             mini.open({
                  url: "../personnel/personnel_upload!importInfos.action",
                  showMaxButton: false,
                  title: "批量上传人员信息",
                  width: 350,
                  height: 200,
  				onload : function() {
  				},
  				ondestroy : function(action) {
  					//刷新页面
  					window.location.reload();
  				}
              });
          }
         function exportJl(){
        	 var row = grid.getSelected();
         	if(row){
         		
         		/* $.ajax({
                    url: "../personnel/personnel_upload!createDoc.action?personId="+row.id,
                    type: "post",
                    success: function (text) {
                        if(text=="error"){
                        	mini.alert("导出失败！");
                            return;
                        }else{
                        	mini.alert("导出成功！");
                        }
                    }
                });  */
         		
         		window.location.href="../personnel/personnel_upload!createDoc.action?personId="+row.id;
         	}else{
         		mini.alert("请选中一条记录！");
         	}
         }
         function onPostCodeEdit(e){
         	var btnEdit = this;
         	mini.open({
             url: "../post/post!show.action",
             showMaxButton: false,
             title: "选择标准岗位",
             width: 600,
             height: 500,
             ondestroy: function (action) {
                 if (action == "ok") {
                     var iframe = this.getIFrameEl();
                     var data = iframe.contentWindow.GetData();
                     data = mini.clone(data);
                     if (data) {
                         btnEdit.setValue(data.id);
                         btnEdit.setText(data.positionCode);
                     }
                 }
             }
         });
         }
         function clickAction(){
        	 var iframe = document.getElementById("zhuanzheng");
        	 if(iframe.style.display=="none"){
        		 iframe.style.display="block";
         	}else{
         		iframe.style.display="none";
         	}
         }
         
         function exporttest(name,attr){
        	 
        	var grid=mini.get("grid");
       		var searchData="{";
       		if(name&&attr){
       			var names=name.split(",");
       			var attrs=attr.split(",");
       			for(var i=0;i<names.length;i++){
       				if(mini.get(names[i])){
       					var value=mini.get(names[i]).getValue();
       					searchData=searchData+"\""+attrs[i]+"\""+":\""+value+"\"";
       					if(i!=names.length-1)searchData=searchData+",";
       				}
       			}
       		}
       		//如果有树
       		var tree=mini.get("deptree");
       		if(tree){
       			var node = tree.getSelectedNode();
       			if(node){
       				var ids = "";
       				ids = depAll(ids,tree,node);
       				if (searchData == "{"){
       					searchData=searchData+"\"depId\":\""+ids+"\"";
       				} else {
       					searchData=searchData+",\"depId\":\""+ids+"\"";
       				}
       			    
       			}
       		}
       		searchData=searchData+"}";
        	 
        	 
         	window.location.href="../personnel/personnel_upload!exporttest.action?searchData="+searchData;
         }
    </script>
</body>
</html>