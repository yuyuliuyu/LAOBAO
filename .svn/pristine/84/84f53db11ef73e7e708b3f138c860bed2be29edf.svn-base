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
<title>职称聘任列表</title>
<style type="text/css">
</style>
<!--引入皮肤样式-->
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css"/>
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
	<div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:200px;">职工号：
                    <input class="mini-textbox" id="jobNumber" name="jobNumber" emptyText="请输入职工号" onenter="onKeyEnter" />
                </td>
                <td style="width:200px;">姓名：
                    <input id="name" name="name" class="mini-textbox" emptyText="请输入员工姓名" onenter="onKeyEnter"/>
                </td>
                <td style="width:200px;">聘任职称：
                    <input id="engageTitle" name="engageTitle" class="mini-textbox" emptyText="请输入聘任职称" onenter="onKeyEnter"/>
                </td>
                <td style="width:200px;">资格类别：
                    <input id="statusClass" name="statusClass" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955c4223c5015c4263bd2c000b" textField="dicname" valueField="dicvalue"/>
                </td>
                <td style="white-space:nowrap;">
                	<a class="mini-button" iconCls="icon-search" onclick="search('jobNumber,name,engageTitle,statusClass','jobNumber,name,engageTitle,statusClass')" onenter="onKeyEnter">查询</a>
               	</td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
	   	<table style="width:100%;">
		    <tr>
			    <td style="width:100%;">
			    	<tags:hasPerm value="../empdata/job_title!add.action">
				    <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
				    </tags:hasPerm>
				    <tags:hasPerm value="../empdata/job_title!edit.action">
				    <a class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a>
				    </tags:hasPerm>
				    <tags:hasPerm value="../empdata/job_title!remove.action">
				    <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>
				    </tags:hasPerm>
			    </td>
		    </tr>
	    </table>
 	</div>
	<input type="hidden"  name="flag" id="flag" value="${flag}" />
   	<div class="mini-fit">
   		<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
         	 url="../empdata/job_title!getListData.action?personId=${personId}" idField="id" multiSelect="true" 
        	 allowAlternating="true" pageSize="20">
	         <div property="columns">
		         <div type="checkcolumn">选择</div>
		         <div type="indexcolumn" width="60" headerAlign="center">序列</div>
		                       
		         <div field="id" id="id" width="0" name="id" headerAlign="center" align="center" class="mini-hidden">id</div>
		         <div field="jobNumber" id ="jobNumber" name = "jobNumber" width="100" headerAlign="center" align="left">职工号</div>
		         <div field="pername" id ="pername" name = "pername"width="100" headerAlign="center" align="left">职工姓名</div>
		         <div field="filmName" id ="filmName" name = "filmName"width="200" headerAlign="center" align="left">单位</div>
		         <div field="departName" id ="departName" name = "departName"width="100" headerAlign="center" align="left">部门</div>
		         <div field="post" id ="post" name = "post"width="100" headerAlign="center" align="left">标准岗位</div>
		         <div field="specificPost" id ="specificPost" name = "specificPost"width="100" headerAlign="center" align="left">具体岗位</div>
		         
		         <div field="engageTitle" width="100" headerAlign="center" align="center">聘任职称</div>
                 <div field="beginDate" width="100" headerAlign="center" align="center">聘期开始</div>
                 <div field="endDate" width="100" headerAlign="center" align="center">聘期结束</div>
		         <div type="comboboxcolumn" field="classType" width="100" headerAlign="center" align="center">系别
                   <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955c4223c5015c42638aef000a" textField="dicname" valueField="dicvalue"/>
                 </div>    
                 <div type="comboboxcolumn" field="statusClass" width="100" headerAlign="center" align="center">资格类别
                   <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955c4223c5015c4263bd2c000b" textField="dicname" valueField="dicvalue"/>
                 </div>        
                 <div field="profession" width="100" headerAlign="center" align="center">专业</div>
                 <div field="jugdeOffice" width="100" headerAlign="center" align="center" >评审机关</div>
                 <div field="certificateNum" width="100" headerAlign="center" align="center">证书编号</div>
                 <div field="sendCertificate" width="100" headerAlign="center" align="center">发证机关</div>
                 <div field="awardDate" width="100" headerAlign="center" align="center">授予日期</div>
                 <div field="certificateOffice" width="100" headerAlign="center" align="center">证书工作单位</div>
                 <div type="comboboxcolumn" field="isHighest" width="100" headerAlign="center" align="center">是否最高等级
                     <input property="editor" class="mini-combobox" data="[{id:0, text:'否'},{id:1, text:'是'}]"/>
                 </div>
                 <div type="comboboxcolumn" field="foreignType" width="100" headerAlign="center" align="center">外语语种
                   <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881a05b650042015b663bdfe9003d" textField="dicname" valueField="dicvalue"/>
                 </div>
                 <div type="comboboxcolumn" field="foreignLevel" width="100" headerAlign="center" align="center">外语级别
                   <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955c4223c5015c426675b30016" textField="dicname" valueField="dicvalue"/>
                 </div>
                 <div type="comboboxcolumn" field="foreignClass" width="100" headerAlign="center" align="center">外语专业类
                   <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955c4223c5015c4266a7c30017" textField="dicname" valueField="dicvalue"/>
                 </div>
                 
                 <div field="foreignDate" width="100" headerAlign="center" align="center">外语通过日期</div>
                 <div field="foreignRemark" width="100" headerAlign="center" align="center">外语备注</div>
                 <div field="computerDate" width="100" headerAlign="center" align="center">计算机通过日期</div>
                 <div field="computerRemark" width="100" headerAlign="center" align="center">计算机备注</div>
                 <div field="professionDate" width="100" headerAlign="center" align="center">专业技术通过日期</div>
                 <div field="professionRemark" width="100" headerAlign="center" align="center">专业技术备注</div>
                 <div type="comboboxcolumn" field="declareLevel1" width="100" headerAlign="center" align="center">可晋升级别1
                   <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955c4223c5015c4263bd2c000b" textField="dicname" valueField="dicvalue"/>
                 </div> 
                 
                 <div field="declareDate1" width="100" headerAlign="center" align="center">可申报时间1</div>
                 <div type="comboboxcolumn" field="declareLevel2" width="100" headerAlign="center" align="center">可晋升级别2
                   <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881955c4223c5015c4263bd2c000b" textField="dicname" valueField="dicvalue"/>
                 </div>
                 
                 <div field="declareDate2" width="100" headerAlign="center" align="center">可申报时间2</div>
                
		         
	         </div>
  		</div>
	</div>
    <script type="text/javascript">
  	    var flag=$("#flag").val();
        mini.parse();
        var grid=mini.get("grid");
        if(flag=="1"){
        	grid.hideColumn("jobNumber");
        	grid.hideColumn("pername");
        	grid.hideColumn("filmName");
        	grid.hideColumn("departName");
        	grid.hideColumn("post");
        	grid.hideColumn("specificPost");
    	}
        grid.hideColumn("id");
        grid.load();
      //***************添加方法**********************//
        function add() {
        		
    			 mini.open({
    	                url : "../empdata/job_title!add.action?personId=${personId}",
    	                title : "添加信息",
    	                width : 770,
    	                height : 540,
    	                /* allowResize : false, */
    	                onload : function() {
    	                },
    	                ondestroy : function(action) {
    	                    grid.reload();
    	                }
    	            });
           
       			 }
     	 //***************编辑方法**********************//
    	  function edit() {
         	 var row = grid.getSelected();
         	 if (row) {
          		mini.open({
  	                url : "../empdata/job_title!add.action?personId=${personId}&id="+row.id,
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
    	//删除
  	function remove() {
  		var rows = grid.getSelecteds();
  		if (rows.length > 0) {
  				if (confirm("确定删除选中记录？")) {
  					var ids = [];
  					for (var i = 0, l = rows.length; i < l; i++) {
  						var r = rows[i];
  						ids.push(r.id);
  					}
  					var sid = ids.join(',');
  					$.ajax({
  					url : "../empdata/job_title!remove.action?ids=" + sid,
  					type : "post",
  					success : function(text) {
  						grid.reload();
  						mini.alert("删除成功！");
  					},
  					error : function(text) {
  						mini.alert("删除失败");
  					}
  				});
  			}
  		} else {
  			mini.alert("请选中一条记录！");
  		}
  	}
    	
  	function onKeyEnter(e) {
        search();
    }
      
    </script>
</body>
</html>