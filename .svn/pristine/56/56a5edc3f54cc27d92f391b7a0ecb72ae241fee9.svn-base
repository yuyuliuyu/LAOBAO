<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
    String flag=request.getParameter("flag");
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /> 
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>培训需求增加页面</title>
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<script src="../template/plugin/public/add.js" type="text/javascript"></script>

<!--引入皮肤样式-->
<link href="../template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
<link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
<link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
<link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    html, body
    {
        font-size:12px;
        padding:0;
        margin:0;
        border:0;
        height:100%;
        overflow:hidden;
    }
    .check_box
    {
        float:left;
        margin-right:5px;
        *margin-right:15px !important;
    }
    
    input[type="checkbox"]
    {
        vertical-align:-3px;
    }
 </style>
  </head>
  
  <body> 
	<div class="mini-fit">
   	 <form id="form1" method="post">
        <input class="mini-hidden" name="id" id="id" value="${id}" style="display:none;"/>
        <input class="mini-hidden" name="pid" id="pid" value="${pid}" style="display:none;"/>
        <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
             	<tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1" height="20px" width="150px"><b>动态表头设置：</b></td> 
               		<td align="left" class="list_left_box1" colspan="3" > </td> 
                </tr> 
                <tr>
                     <td class="list_left_box1" valign="top">上级分类（报表专用）：</td>
                    <td class="list_right_box"> 
                     <s:if test="pid==0">
					</s:if><s:else>
					<input id="ptype" name="ptype" class="mini-combobox" style="width:150px;" textField="text" valueField="id" emptyText="请选择..."
    url="../train/table_colum!getpid.action?pid=${pid }" value="${tableinfo.ptype}"  allowInput="true" showNullItem="true" nullItemText="请选择..."/>
                    </s:else>
                    </td>
                    <td class="list_left_box1" valign="top"  width="100px">数据库字段名：</td>
                    <td class="list_right_box">
                        <input name="columname" id="columname" class="mini-textbox" value="${tableinfo.columname}"  width="100%" />
                    </td>
                </tr>
                <tr>
                   <td class="list_left_box1" valign="top">字段中文名：</td>
                    <td class="list_right_box">
                        <input name="chinaname" id="chinaname" class="mini-textbox" value="${tableinfo.chinaname}"  width="100%" />
                    </td>
                    <td class="list_left_box1" valign="top">字段类型：</td>
                    <td class="list_right_box">
					<input id="columtype" name="columtype" class="mini-combobox" style="width:150px;" textField="text" valueField="id" emptyText="请选择..."
   data="[{id:1,text:'字符型'},{id:2,text:'整型'},{id:3,text:'时间型'}, {id:4,text:'浮点型'}]" value="${tableinfo.columtype}"  required="true" allowInput="true" showNullItem="true" nullItemText="请选择..."/>    
                    </td>   
                </tr>
                <tr>
                   <td class="list_left_box1" valign="top" width="100px">字段长度：</td>
                    <td class="list_right_box">
                        <input name="columlength" id="columlength" class="mini-textbox" value="${tableinfo.columlength}"  width="100%" />
                    </td> 
                    <td class="list_left_box1" valign="top" width="100px">是否显示：</td>
                    <td class="list_right_box">
					<input id="columstate" name="columstate" class="mini-combobox" style="width:150px;" textField="text" valueField="id" emptyText="请选择..."
   data="[{id:1,text:'不显示'},{id:2,text:'仅列表显示'},{id:3,text:'仅导出显示'}, {id:4,text:'都显示'}]" value="${tableinfo.columstate}"  required="true" allowInput="true" showNullItem="true" nullItemText="请选择..."/> 
                        
                    </td>   
                </tr> 
                
                <tr>
                   <td class="list_left_box1" valign="top" width="100px">列表展示优先级：</td>
                    <td class="list_right_box"> 
                    
                        <input name="listsort" id="listsort" class="mini-textbox" value="${tableinfo.listsort}" <s:if test="tableinfo.ptype!=null&&tableinfo.ptype!=''">enable="false" </s:if> width="100%" />
                    </td>
                    <td class="list_left_box1" valign="top" width="100px">导出优先级：</td>
                    <td class="list_right_box">
                        <input name="exportsort" id="exportsort" class="mini-textbox" value="${tableinfo.exportsort}"  <s:if test="tableinfo.ptype!=null&&tableinfo.ptype!=''">enable="false" </s:if>  width="100%" />
                    </td>   
                </tr> 
                <tr>
                	<td class="list_left_box1" valign="top" width="100px">数据库列表：</td>
                    <td class="list_right_box">
                        <input id="sjkType" name="sjkType" class="mini-combobox"style="width:100%;" value="${tableinfo.sjkType}"  text="${tableinfo.sjkType}" textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402880ed5e16c908015e16cd2862000b" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                </tr>
            </table> 
            <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
	            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a>  
	            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        	</div>
        
    </form>
    </div>
  </body>
    <script type="text/javascript">
        mini.parse();
			var columtype=[
			               {id:1,text:'字符型'},
			               {id:2,text:'整型'},
			               {id:3,text:'时间型'},
			               {id:4,text:'浮点型'}
			               ];
			

	        var positionCombo = mini.get("positionCombo") 
            var url = "../train/table_colum!getpid.action?pid=" +mini.get("pid").getValue();
            positionCombo.setUrl(url);
	        
		  function onOk(){ 
		      var form = new mini.Form("form1");
	          var data = form.getData();
		      form.validate(); 
		      if (form.isValid() == false) return; 
	            var json = mini.encode(data); 
		      //strut令牌
		      window.parent.loading();
		      $.ajax({
		          url: "../train/table_colum!saveorupdate.action",
		          type:'post',
		          data:{   jsondata: json },
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
		              } else {
		                  if(text.length>0){132
		                      mini.alert(text);
		                  }
		              }
		          } 
		      });
		  }
		  

	        function CloseWindow(action) {
	            if (action == "close" && form.isChanged()) {
	                if (confirm("数据被修改了，是否先保存？")) {
	                    return false;
	                }
	            }
	            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
	            else window.close();
	        }

	        function onCancel(e) {
	            CloseWindow("cancel");
	        }
  </script>
</html>
