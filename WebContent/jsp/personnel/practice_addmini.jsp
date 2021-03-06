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
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>实习生信息增加</title>
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
        <input class="mini-hidden" name="departId" id="departId" value="${departId}" style="display:none;"/>
        <input class="mini-hidden" name="post" id="post" value="${information.post}" style="isplay:none;"/>
        <input class="mini-hidden" name="specificPost" id="specificPost" value="${information.specificPost}" style="isplay:none;"/>
        
        <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
        		<tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr>
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>基本信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                
                <tr>
                	<td class="list_left_box1" valign="top">实习编号：</td>
	                <td class="list_right_box">
	                	<input name="practiceNum" id="practiceNum" class="mini-textbox" value="${information.practiceNum}"
	                		vtype="maxLength:20;required" width="100%"/>
	                </td>
                    <td class="list_left_box1" valign="top">姓名：</td>
                    <td class="list_right_box">
                        <input name="name" id="name" class="mini-textbox" value="${information.name}"
                                vtype="maxLength:20;required" width="100%"/>
                    </td>
                </tr>
                
                <tr>
                   <td class="list_left_box1" valign="top">证件类型：</td>
                    <td class="list_right_box">
                        <input id="idType" name="idType" class="mini-combobox" style="width:100%;" value="${information.idType}"  textField="dicname" valueField="dicvalue"
		                   		 url="../system/dectionary!listjson.action?id=402881935ba8c785015ba8d9d7c0000b" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                    <td class="list_left_box1" valign="top">证件号码：</td>
                    <td class="list_right_box">
                        <input name="idNumber" id="idNumber" class="mini-textbox" value="${information.idNumber}"
                                onvaluechanged="onChanged1" vtype="maxLength:20" width="100%"/>
                    </td>
                 </tr>
                 
               	 <tr> 
                    <td class="list_left_box1" valign="top">出生日期：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="borthDate" id="borthDate" style="width: 100%" msg="日期格式不正确" allowInput="false" onvaluechanged="onChanged(e)"
									 value="${information.borthDate}"/>
                    </td> 
                    <td class="list_left_box1" valign="top">年龄：</td>
                    <td class="list_right_box">
                        <input id="age" name="age" class="mini-textbox" value="${age}" style="width: 85%;"/>&nbsp;&nbsp;&nbsp;岁
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">职工来源：</td>
                    <td class="list_right_box" width="30%">
                        <input id="empSource" name="empSource" class="mini-combobox" style="width:100%;" value="0" text="学生"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881935baceaa1015badf989e30015" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                    <td class="list_left_box1" valign="top">原身份：</td>
                    <td class="list_right_box">
                        <input name="formerIdentity" id="formerIdentity" class="mini-textbox" value="${information.formerIdentity}"
                                vtype="maxLength:20" width="100%"/>
                    </td>    
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top">参加工作时间：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd" required="true"
							id="setWorkDate" name="setWorkDate" style="width: 100%" msg="日期格式不正确" allowInput="false"
									 value="${information.setWorkDate}"/>
                    </td> 
                    <td class="list_left_box1" valign="top">政治面貌：</td>
                    <td class="list_right_box">
                        <input id="politicsStatus" name="politicsStatus" class="mini-combobox" style="width:100%;" required="true" value="${information.politicsStatus}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881935b840948015b84cff8c10019" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                 </tr>
                <tr>
                	<td class="list_left_box1" valign="top">性别：</td>
                    <td class="list_right_box">
                        <input id="sex" name="sex" class="mini-radiobuttonlist" url="../template/sex.txt"
                        textField="text" valueField="id" value="${information.sex}" />
                    </td> 
                    <td class="list_left_box1" valign="top">是否退伍军人：</td>
                    <td class="list_right_box">
                        <input id="isSoldier" name="isSoldier" class="mini-combobox"
                        	emptyText="请选择..." required="true"
                          	url="../template/using.txt" textField="text" 
                          	 width="100%" value="${information.isSoldier}"/>
                    </td>    
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">国家序列最高职称：</td>
                    
                    <td class="list_right_box">
                        <input id="highestTitle" name="highestTitle" class="mini-combobox" style="width:100%;" required="true" value="${information.highestTitle}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955d176487015d17682e54000a" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                    <td class="list_left_box1" valign="top">国家职业资格等级：</td>
                    
                    <td class="list_right_box">
                        <input id="proQuaLevel" name="proQuaLevel" class="mini-combobox" style="width:100%;" required="true" value="${information.proQuaLevel}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955d176487015d17685ddb000b" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>   
                </tr>
        		<tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>岗位信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">入司时间1：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="inCompanyTime" id="inCompanyTime" style="width: 100%" msg="日期格式不正确" allowInput="false"
									 value="${information.inCompanyTime}"/>
                    </td> 
                    <td class="list_left_box1" valign="top">入司时间2：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="inCompanyTime2" id="inCompanyTime2" style="width: 100%" msg="日期格式不正确" allowInput="false"
									 value="${information.inCompanyTime2}"/>
                    </td> 
               </tr>
               <tr>
                    <td class="list_left_box1" valign="top">入港时间1：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="inPortTime" id="inPortTime" style="width: 100%" msg="日期格式不正确" allowInput="false"
									 value="${information.inPortTime}"/>
                    </td>
                    <td class="list_left_box1" valign="top">入港时间2：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="inPortTime2" id="inPortTime2" style="width: 100%" msg="日期格式不正确" allowInput="false"
									 value="${information.inPortTime2}"/>
                    </td>
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top">在岗状态：</td>
                    <td class="list_right_box" width="30%">
                        <input id="onPost" name="onPost" class="mini-combobox"  style="width:100%;" value="${information.onPost}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955bc84595015bc87aa73e0010" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                	<td class="list_left_box1" valign="top">劳务公司：</td>
                    <td class="list_right_box" width="30%">
                        <input id="laborCompany" name="laborCompany" class="mini-combobox"  style="width:100%;" value="${information.laborCompany}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881955d1ba95e015d1bbcbed5000a" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>  
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top">具体岗位：</td>
                    <td class="list_right_box" width="30%">
                        <input id="specificPostId" name="specificPostId"  class="mini-buttonedit" onbuttonclick="onButtonEdit1" width="100%"
                                value="${information.specificPostId}"allowInput="false"/> 
                    </td>
                    <td class="list_left_box1" valign="top">标准岗位：</td>
                    <td class="list_right_box">
                        <input name="postId" id="postId" class="mini-buttonedit" value="${information.postId}" readonly="true" width="100%"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top">是否为技术工人：</td>
                    <td class="list_right_box">
                        <input id="isSkilled" name="isSkilled" class="mini-combobox"
                        	emptyText="请选择..." required="true"
                          	url="../template/using.txt" textField="text" 
                          	 width="100%" value="${information.isSkilled}"/>
                    </td>
                    <td class="list_left_box1" valign="top">是否为专业技术人员：</td>
                    <td class="list_right_box">
                        <input id="isSpecialty" name="isSpecialty" class="mini-combobox"
                        	emptyText="请选择..." required="true"
                          	url="../template/using.txt" textField="text" 
                          	 width="100%" value="${information.isSpecialty}"/>
                    </td>
                </tr>
               <tr>
                	<td class="list_left_box1" valign="top">机关编制：</td>
                    <td class="list_right_box">
                        <input id="jgbz" name="jgbz" class="mini-combobox"
                        	emptyText="请选择..." 
                          	url="../template/using.txt" textField="text" 
                          	width="100%" value="${information.jgbz}"/>
                    </td>
                    <td class="list_left_box1" valign="top">开始时间：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd" required="true"
									name="beginTime" id="beginTime" style="width: 100%" msg="日期格式不正确" allowInput="false"
									 value="${information.beginTime}"/>
                    </td>
                </tr> 
                 <tr>
                 	<td class="list_left_box1" valign="top">职务级别：</td>
                    <td class="list_right_box">
                        <input id="jobLevel" name="jobLevel" class="mini-combobox" style="width:100%;" value="${information.jobLevel}"  textField="dicname" valueField="dicvalue"
		                   		url="../system/dectionary!listjson.action?id=402881935ba8c785015ba8e043c80011" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                    <td class="list_left_box1" valign="top">任职年限：</td>
                    <td class="list_right_box">
                        <input name="career" id="career" class="mini-textbox" value="${information.career}"
                                vtype="maxLength:10" width="100%"/>
                    </td> 
                </tr>
           </table>     
       		 
            </br></br></br>
            <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
	            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
	            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
	            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        	</div>
        
    </form>
    </div>
    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("jzgrid");
        var grid1 = mini.get("zyllgrid");
        var grid2 = mini.get("shgxgrid");
        grid.load();
        grid1.load();
        grid2.load();
        //grid.hideColumn("id");
        //选择岗位
 		function onButtonEdit1(e) {
 	        var btnEdit = this;
 	        var depart=mini.get("departId").getValue();
 	       	var postId=mini.get("postId");
 	       	var postName=mini.get("post");
 	       	var specificPost=mini.get("specificPost");
 	       
 	        mini.open({
 	            url: "../post/position!show.action?id="+depart,
 	            showMaxButton: false,
 	            title: "选择人员",
 	            width: 400,
 	            height: 300,
 	            ondestroy: function (action) {
 	                if (action == "ok") {
 	                    var iframe = this.getIFrameEl();
 	                    var data = iframe.contentWindow.GetData();
 	                    data = mini.clone(data);
 	                    if (data) {
 	                        btnEdit.setValue(data.id);
 	                        btnEdit.setText(data.positionName);
 	                       	postId.setValue(data.positionId);//标准岗位id
 	                       	postId.setText(data.positionName2);//标准岗位名称
 	                       	postName.setValue(data.positionName2);//标准岗位名称
 	                        specificPost.setValue(data.positionName);
 	                    }
 	                }
 	            }
 	        });
 	    } 
        function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            var branchId=mini.get("branchId");
            var data = form.getData();
            var json = mini.encode(data); 
            //strut令牌
            
            $.ajax({
                url: "../personnel/practice!saveOrUpdate.action?branchId="+branchId,
                data:{
                	formdata:json,
                    child:"${child}"
                },
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
                        if(text.length>0){
                            mini.alert(text);
                        }
                    }
                }
            });
        }
        function onChanged(e){
 			var value = e.value;
 			var borthYear=value.getFullYear();
 			var age = mini.get("age");
 			var date=new Date;
 			var newYear=date.getFullYear();
 			age.setValue(newYear-borthYear);
 		}
        function onChanged1(e){
 			var value = e.value;
 			var idType=mini.get("idType").getValue();
 			var borthDate=mini.get("borthDate");
 			var theage=mini.get("age");
 			var sex=mini.get("sex");
 			if(idType==1&&value!=null){
 				//获取性别 
 				var gender = value.slice(14, 17) % 2 ? "1" : "0"; // 1代表男性，0代表女性  
 				sex.setValue(gender);
 				//获取出生日期  
 			    var birth = value.substring(6, 10) + "-" + value.substring(10, 12) + "-" + value.substring(12, 14);  
 			    borthDate.setValue(birth);
 			    //获取年龄  
 			    var myDate = new Date();  
 			    var month = myDate.getMonth() + 1;  
 			    var day = myDate.getDate();  
 			    var age = myDate.getFullYear() - value.substring(6, 10) - 1;  
 			    if (value.substring(10, 12) < month || value.substring(10, 12) == month && value.substring(12, 14) <= day) {  
 			        age++;  
 			    } 
 			   theage.setValue(age);
 			}
 		}
        function onCancel(e) {
             CloseWindow("cancel");
        }
        
        function onReload() {
            document.location.reload();
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
    </script>
</body>
</html>