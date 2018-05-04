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
<title>返聘人员信息</title>
<link href="${base}template/system/css/base.css" rel="stylesheet" type="text/css" />
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<script src="../template/plugin/public/add.js" type="text/javascript"></script>
<link href= "../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
<!--引入皮肤样式-->
<link href="${base}/template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
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
    <form id="form1" method="post">
        <%-- <input class="mini-hidden" name="id" id="id" value="${id}" style="display:none;"/> --%>
        <input class="mini-hidden" name="filmName2" id="filmName2" style="isplay:none;"/>
        <input class="mini-hidden" name="depName2" id="depName2" style="isplay:none;"/>
        <input class="mini-hidden" name="spanPostName2" id="spanPostName2" style="isplay:none;"/>
        <input class="mini-hidden" name="spPostName2" id="spPostName2" style="isplay:none;"/>
        <input class="mini-hidden" name="personId" id="personId" value="${personId}" style="display:none;"/>
        <div>
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
                <tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>基本信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top" width="120px">姓名：</td>
                    <td class="list_right_box" width="30%">
                        <input name="name" id="name" class="mini-textarea" value="${information.name}"
                               redonly="redonly" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >职工号：</td>
                    <td class="list_right_box" >
                        <input name="jobNumber" id="jobNumber" class="mini-textarea" value="${information.jobNumber}"
                               vtype="maxLength:20" width="90%"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >退休前公司：</td>
                    <td class="list_right_box" >
                        <input name="preFirm" id="preFirm" class="mini-textarea" value="${information.filmName}"
                               vtype="maxLength:20" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >退休前部门：</td>
                    <td class="list_right_box"  >
                         <input name="preDep" id="preDep" class="mini-textarea" value="${information.departName}"
                                redonly="true" width="90%"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >退休前岗位：</td>
                    <td class="list_right_box" >
                         <input name="specificPost" id="specificPost" class="mini-textarea" value="${information.specificPost}"
                                redonly="true" width="90%"/>
                    </td>
                </tr>
           	    <tr> 
               		<td align="left"   colspan="4" > &nbsp;</td> 
                </tr> 
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;"><b>返聘信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >开始时间：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
								required="true"	name="beginDate" id="beginDate" required="true" requiredErrorText="不能为空" style="width: 90%" msg="日期格式不正确" allowInput="false"/>
                    </td>
                    <td class="list_left_box1" valign="top" >结束时间：</td>
                    <td class="list_right_box">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
								name="endDate" id="endDate" required="true" requiredErrorText="不能为空" style="width: 90%" msg="日期格式不正确" allowInput="false"/>
                    </td>
                </tr>
                <tr>
                	<td class="list_left_box1" valign="top" >是否原单位*：</td>
                    
                    <td class="list_right_box">
                        <input id="isRepost" name="isRepost" class="mini-combobox" emptyText="请选择..."
                         required="true" url="../template/using.txt" textField="text" width="90%" onvaluechanged="onChanged"/>
                    </td>
                    
				</tr>
			</table>
		</div>
		<div id="zhuanzheng" style="display:none">
			<table cellpadding="0" cellspacing="0" border="0" class="addtable" width="90%" align="center">
               
                <tr>
	                    <td class="list_left_box1" valign="top" width="120px">分配部门：</td>
	                    
	                    <td class="list_right_box" width="30%">
                        <input id="backDep" name="backDep" class="mini-buttonedit" onbuttonclick="onButtonEdit" width="90%"
                               allowInput="false"  /> 
                   		 </td>
	                    <td class="list_left_box1" valign="top">公司：</td>
	                    <td class="list_right_box">
                        	<input id="backFirm" name="backFirm" class="mini-buttonedit" width="90%" allowInput="false"  /> 
                   		</td>
	                </tr>
	                <tr>
	                    <td class="list_left_box1" valign="top">分配具体岗位：</td>
	                    
	                    <td class="list_right_box">
                        <input id="backPost" name="backPost" class="mini-buttonedit" onbuttonclick="onButtonEdit1" width="90%"
                               allowInput="false"/> 
                    	</td>
	                    <td class="list_left_box1" valign="top">标准岗位：</td>
	                    <td class="list_right_box">
                        <input id="backSpaPost" name="backSpaPost" class="mini-buttonedit" width="90%"
                               allowInput="false"/> 
                    	</td>
	                </tr>
            </table>
        </div>
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        </div>
    </form>
    
    <script type="text/javascript">
        mini.parse();
        
        function onChanged(){
        	var firstCombo = mini.get("isRepost");
        	var isPractice = firstCombo.getValue();
        	if(isPractice==0){
        		var iframe = document.getElementById("zhuanzheng");
                iframe.style.display="block";
        	}
        }
        
		function onButtonEdit(e) {
            var btnEdit = this;
            var filmname=mini.get("filmName2");//公司名称
            var backFirm=mini.get("backFirm");//公司id
            mini.open({
                url: "../basis/branch!tree.action",
                showMaxButton: false,
                title: "选择部門",
                width: 250,
                height: 330,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                    
                        if (data) {
                            btnEdit.setValue(data.id);
                            btnEdit.setText(data.name);
                            backFirm.setValue(data.branchId);
                            
                            $.ajax({
                                url: "../personnel/change!findFilmName.action?filmId="+data.branchId,
                                success: function (text) {
                                    if(text!=null){
                                    	filmname.setValue(text);
                                    	backFirm.setText(text);
                                    }
                                }
                            });   
                            
                        }
                    }
                }
            });
            
        }
		
		//选择岗位
 		function onButtonEdit1(e) {
 	        var btnEdit = this;
 	        var depart=mini.get("backDep").getValue();
 	       	var postId=mini.get("backSpaPost");//标准岗位
 	       	var postName=mini.get("spanPostName2");//标准岗位
 	        var sppostName=mini.get("spPostName2");//具体岗位
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
 	                        sppostName.setValue(data.positionName);//部门岗位名称
 	                    }
 	                }
 	            }
 	        });
 	        
 	    } 
		
        function onOk(){
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            var data = form.getData();
            var json = mini.encode(data); 
            //strut令牌
            $.ajax({
                url: "../personnel/departure!saveOrUpdateFanpin.action",
                data:{
                	formdata:json,
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