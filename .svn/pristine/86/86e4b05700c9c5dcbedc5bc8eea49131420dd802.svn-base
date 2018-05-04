<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>培训需求列表</title>
<style type="text/css">
</style>
<script src="../template/miniui/boot.js" type="text/javascript"></script>
<!--引入皮肤样式-->
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../template/plugin/public/cent_dep.js"></script>
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
			            <div class="mini-toolbar" style="padding:0px;border-top:0;">
			       			 <table style="width:100%;">
			           			 <tr>
					                <td style="width:100%;"> 
					                    <a class="mini-button" iconCls="icon-user" onclick="set(1)">不显示</a>  
					                    <a class="mini-button" iconCls="icon-user" onclick="set(2)">仅列表显示</a> 
					                    <a class="mini-button" iconCls="icon-user" onclick="set(3)">仅导出显示</a> 
					                    <a class="mini-button" iconCls="icon-remove" onclick="set(4)">设为显示</a> 
		                        <input name="tablename" id="tablename" class="mini-hidden" value="${tablename}"  width="100%" />
					                </td>
			            		 </tr>
			        		</table>
			    		</div> 
	    		<div id="datagrid1" class="mini-treegrid" style="width:100%;height:94%;"
		              showTreeIcon="true" url="../train/table_colum!testjson.action"
		             treeColumn="name" idField="did" parentField="pid"
		             resultAsTree="false" allowResize="false" expandOnLoad="true"> 
		                <div property="columns">
	                        <div type="checkcolumn">选择</div>
	                        <div type="indexcolumn" headerAlign="center">序列</div>
			                    <s:iterator value="showlist" var="showlist"> 
	                        <div field="<s:property value="columname" />" id="<s:property value="columname"/>" name = "<s:property value="columname"/>"width="100" headerAlign="center" align="left"><s:property value="chinaname" /></div>
			                    </s:iterator>
	                    </div>
       		 	</div>
	</div>
    
    <script type="text/javascript">
    mini.parse(); 
    var grid = mini.get("datagrid1");
    grid.load({tablename:mini.get("tablename").getValue()}); 
    function set(type){ 
    	var rows = grid.getSelecteds();
    	var ids="";
        if (rows.length>0) {
        	for(var i=0;i<rows.length;i++){
        		ids+=","+rows[i].id;
        	}
        	$.ajax({
		          url: "../train/table_colum!setcolums.action",
		          type:'post',
		          data:{ids: ids,state:type },
		          success: function (text) {
		               window.parent.unmask();
		              if (text == "success") {
		                  mini.showMessageBox({
		                      title: "提醒",
		                      width: 250,
		                      iconCls: "mini-messagebox-warning",
		                      buttons: ["ok"],
		                      message: "保存成功！",
		                      callback: function (action) {
		                          CloseWindow("ok");
		                      }
		                  });
		                  grid.load({tablename:mini.get("tablename").getValue()}); 
		              } else {
		                  if(text.length>0){132
		                      mini.alert(text);
		                  }
		                  grid.load({tablename:mini.get("tablename").getValue()}); 
		              }
		          } 
		      });
        }else{
        	alert("您至少需选中一项进行操作");
        }
    }
    </script>
</body>
</html>