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
<title>证照管理</title>
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
	<div class="mini-fit" >
    <form id="form1" method="post">
        <input class="mini-hidden" name="id" id="id" value="${id}" style="display:none;"/>
        <input class="mini-hidden" name="personId" id="personId" value="${personId}" style="display:none;"/>
        <input class="mini-hidden" name="url" id="url" value="${url}" style="display:none;"/>
        <div id="zhuanzheng" style="display:none">
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;width:110px;"><b>基本信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">职工号：</td>
                    <td class="list_right_box" width="30%">
                        <input id="jobNumber" name="jobNumber" class="mini-buttonedit" onbuttonclick="onButtonEdit" width="90%"
                                required="true" value="${info.jobNumber}" text="${info.jobNumber}" allowInput="false"/> 
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">职工姓名：</td>
                    <td class="list_right_box" width="30%">
                        <input name="pername" id="pername" class="mini-textbox" value="${info.name}"
                                readonly="true" width="90%"/>
                    </td> 
                </tr>
                <tr>
                    
                    <td class="list_left_box1" valign="top" width="15%">单位：</td>
                    <td class="list_right_box" width="30%">
                        <input name="filmName" id="filmName" class="mini-textbox" value="${info.filmName}"
                                readonly="true" width="90%"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">部门：</td>
                    <td class="list_right_box" width="30%">
                        <input name="departName" id="departName" class="mini-textbox" value="${info.departName}"
                                readonly="true" width="90%"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">标准岗位：</td>
                    <td class="list_right_box" width="30%">
                        <input name="perpost" id="perpost" class="mini-textbox" value="${info.post}"
                                readonly="true" width="90%"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">具体岗位：</td>
                    <td class="list_right_box" width="30%">
                        <input name="specificPost" id="specificPost" class="mini-textbox" value="${info.specificPost}"
                                readonly="true" width="90%"/>
                    </td>
                </tr>
		   </table>
		</div>
		<div>
           <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%"> 	
                <tr>
               		<td align="left" class="list_left_box1"  height="30px" style="font-size:18px;width:110px;"><b>证照信息</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                    <td class="list_left_box1" valign="top" >证照类型：</td>
                    <td class="list_right_box" width="30%">
                        <input id="licenseType" name="licenseType" class="mini-combobox" style="width:90%;" value="${license.licenseType}"  textField="dicname" valueField="dicvalue"
		                   		required="true" url="../system/dectionary!listjson.action?id=402881935b88e560015b895aa2f40016" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td>
                    <td class="list_left_box1" valign="top" >证照编号：</td>
                    <td class="list_right_box" >
                         <input name="licenseNum" id="licenseNum" class="mini-textarea" value="${license.licenseNum}"
                                required="true" vtype="maxLength:20" width="90%"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">生效起始日期：</td>
                    <td class="list_right_box" width="30%">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									required="true" name="begindate" id="begindate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 onvaluechanged="onChanged()" value="${license.begindate}"/>
                    </td>  
                    <td class="list_left_box1" valign="top" width="15%">生效结束日期：</td>
                    <td class="list_right_box" width="30%">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									required="true" name="enddate" id="enddate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 onvaluechanged="onChanged()" value="${license.enddate}"/>
                    </td>  
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">证件状态：</td>
                    <td class="list_right_box" width="30%">
                        <input id="licenseStatus" name="licenseStatus" class="mini-combobox" style="width:90%;" value="${licenseStatus}"  textField="dicname" valueField="dicvalue"
		                   		readonly="true" url="../system/dectionary!listjson.action?id=402881935b88e560015b895b010c0017" showNullItem="true" emptyText="请选择..." nullItemText="请选择..." />
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">有效期：</td>
                    <td class="list_right_box" >
                         <input name="validity" id="validity" class="mini-textarea" value="${license.validity}"
                                 readonly="true" vtype="maxLength:20" width="80%"/>&nbsp;天
                    </td> 
                </tr>
                
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">距离失效天数：</td>
                    <td class="list_right_box" >
                         <input name="loseDate" id="loseDate" class="mini-textarea" value="${loseDate}"
                                readonly="true" vtype="maxLength:20" width="80%"/>&nbsp;天
                    </td>  
                    <td class="list_left_box1" valign="top" width="15%">发证机关：</td>
                    <td class="list_right_box" >
                         <input name="office" id="office" class="mini-textarea" value="${license.office}"
                                vtype="maxLength:50" width="90%"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">是否复检：</td>
                    <td class="list_right_box" width="30%">
                        <input id="isRecheck" name="isRecheck" class="mini-combobox"
                        	emptyText="请选择..." style="width:90%;"
                          	url="../template/using.txt" textField="text" 
                          	valueField="id"  value="${license.isRecheck}"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">上次复检日：</td>
                    <td class="list_right_box" width="30%">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="lastRecheckday" id="lastRecheckday" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${license.lastRecheckday}"/>
                    </td> 
                    <td class="list_left_box1" valign="top" width="15%">下次复检日：</td>
                    <td class="list_right_box" width="30%">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="nextRecheckday" id="nextRecheckday" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${license.nextRecheckday}"/>
                    </td>  
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">距离复检天数：</td>
                    <td class="list_right_box" >
                         <input name="recheckDays" id="recheckDays" class="mini-textarea" value="${license.recheckDays}"
                                vtype="maxLength:50" width="90%"/>
                    </td>  
                    <td class="list_left_box1" valign="top" width="15%">是否回缴：</td>
                    <td class="list_right_box" width="30%">
                        <input id="isBackpay" name="isBackpay" class="mini-combobox"
                        	emptyText="请选择..." style="width:90%;"
                          	url="../template/using.txt" textField="text" 
                          	valueField="id"  value="${license.isBackpay}"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">回缴日期：</td>
                    <td class="list_right_box" width="30%">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="backpayDate" id="backpayDate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${license.backpayDate}"/>
                    </td>  
                    <td class="list_left_box1" valign="top" width="15%">保管单位：</td>
                    <td class="list_right_box" >
                         <input name="tackOffice" id="tackOffice" class="mini-textarea" value="${license.tackOffice}"
                                vtype="maxLength:50" width="90%"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">保管人：</td>
                    <td class="list_right_box" >
                         <input name="tackName" id="tackName" class="mini-textarea" value="${license.tackName}"
                                vtype="maxLength:50" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="15%">保管日期：</td>
                    <td class="list_right_box" width="30%">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="tackDate" id="tackDate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${license.tackDate}"/>
                    </td> 
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">转交人：</td>
                    <td class="list_right_box" >
                         <input name="deliverName" id="deliverName" class="mini-textarea" value="${license.deliverName}"
                                vtype="maxLength:50" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" width="15%">转交日期：</td>
                    <td class="list_right_box" width="30%">
                        <input class="mini-datepicker" format="yyyy-MM-dd"
									name="deliverDate" id="deliverDate" style="width: 90%" msg="日期格式不正确" allowInput="false"
									 value="${license.deliverDate}"/>
                    </td>  
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" >备注：</td>
                    <td class="list_right_box" colspan="3">
                         <input id="remark" name="remark" class="mini-textarea" value="${license.remark}"  maxlength="500"style="width: 96%;height:50px"/>
                    </td>
                    
                </tr>
                <tr>
               		<td align="left" class="list_left_box1"  height="30px"  style="font-size:18px;width:110px;"><b>文件上传</b></td> 
               		<td align="left" class="list_left_box1"  colspan="3" > </td> 
                </tr> 
                <tr>
                	<td class="list_left_box1" valign="top" >文件编号：</td>
                    <td class="list_right_box" >
                         <input name="fileNum" id="fileNum" class="mini-textarea" value="${license.fileNum}"
                                vtype="maxLength:20" width="90%"/>
                    </td>
                    <td class="list_left_box1" valign="top" >文件名称：</td>
                    <td class="list_right_box" >
                         <input name="fileName" id="fileName" class="mini-textarea" value="${license.fileName}"
                                vtype="maxLength:20" width="90%"/>
                    </td>
                </tr>
 			</table>
 		</div>
        </form>
        <form id="uploaders" method="post" action="../empdata/license!uploadFile.action?id=${id}"
       			 enctype="multipart/form-data" encoding="multipart/form-data" target="appFrame">
       		 <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                
                <tr>
                    <tr>
                    <td class="list_left_box1" valign="top" width="112px";>
         请选择上传文件：
                    </td>
                    <td class="list_right_box" colspan="3">
				        <input class="mini-htmlfile" id="uploadFile" name="uploadFile" limitType="*.jpg;*.jpeg;"  text="${license.url}" />
                    	<a class="mini-button" onclick="upload" style="width:100px;margin-right:20px;" iconCls="icon-upload">上传</a>
                    </td>
                </tr>
                </tr>
            </table>
           </form>
        <span id="x" style="display:none"></span>
    	<iframe id="frame" name="appFrame" src="" style="display:none;"></iframe>
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
    	</div>
        
    </div>
    <script type="text/javascript">
    mini.parse();
    var personId = mini.get("personId").getValue();
    var iframe = document.getElementById("zhuanzheng");
    if(personId!=null&&personId!=""){
    	iframe.style.display="none";
	}else{
		iframe.style.display="block";
	}
    
    function onChanged(){
    		var begindate = mini.get("begindate").getFormValue();//生效开始日期
    		var enddate = mini.get("enddate").getFormValue();//生效开始日期
			var validity = mini.get("validity");//有效时间
			var licenseStatus= mini.get("licenseStatus");//证件状态
			var loseDate= mini.get("loseDate");//距离失效天数
			if(enddate==undefined||enddate==null||enddate==""){
				 return;
			}else{
				var date=new Date;
				var month=date.getMonth()+1;
				var newDate = date.getFullYear()+"-"+month+"-"+date.getDate();
				
				validity.setValue(DateDiff(enddate,  begindate));
				
				loseDate.setValue(DateDiff(enddate,  newDate));
				var enddate1 = mini.get("enddate").getValue();
				var begindate1 = mini.get("begindate").getValue();
				if(enddate1<date||begindate1>date){
					licenseStatus.setValue(2);
				}else{
					licenseStatus.setValue(1);
				}
			}
			//var newYear=date.getFullYear();
		} 
    function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
        var  aDate,  oDate1,  oDate2,  iDays  
        aDate  =  sDate1.split("-");  
        oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);    //转换为12-18-2006格式  
        aDate  =  sDate2.split("-"); 
        oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  ;
        iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24);    //把相差的毫秒数转换为天数  
        return  iDays  
    } 
    
	function onButtonEdit(e) {
        var btnEdit = this;
        var name=mini.get("pername");
        var post=mini.get("perpost");
        var specificPost=mini.get("specificPost");
        var depart=mini.get("departName");
        var filmName=mini.get("filmName");
        var personId=mini.get("personId");
        mini.open({
            url: "../personnel/personnel!show.action",
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
                        btnEdit.setValue(data.jobNumber);
                        btnEdit.setText(data.jobNumber);
                        name.setValue(data.name);
                        post.setValue(data.post);
                        specificPost.setValue(data.specificPost);
                        depart.setValue(data.departName);
                        filmName.setValue(data.filmName);
                        personId.setValue(data.id);
                    }
                }
            }
        });
        
    } 
	//*************************************//
	function onOk(){
		
		var entranceDate = mini.get("begindate").getValue();
       	var graduateDate = mini.get("enddate").getValue();
       	if(entranceDate > graduateDate){
       		mini.alert("起始日期不能晚于结束日期！");
       		return;
       		}
       	
        var form = new mini.Form("form1");
        form.validate();
        if (form.isValid() == false) return;
        var data = form.getData();
        var json = mini.encode(data); 
        //strut令牌
        window.parent.loading();
        $.ajax({
            url: "../empdata/license!saveOrUpdate.action",
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
	//***************************************************//
	// 得到iframe
    var appFrame = document.getElementById('frame');
    // iframe监听事件
    var listener = function() {
        window.parent.unmask();
        // 获取上传之后的iframe返回值
        var doc = appFrame.contentWindow.document;
        var pre = doc.getElementsByTagName('pre');
        // 是否存在(默认会自动生成pre标签)
        if (pre && pre.length > 0) {
            var allmsg = JSON.parse(pre[0].innerHTML);//返回值
            var response=allmsg.msg;//返回msg的值
            if(response=="success"){
            	mini.get("url").setValue(allmsg.fileUrl);//给URL赋值
                mini.showMessageBox({
                    title: "提醒",
                    width: 250,
                    iconCls: "mini-messagebox-warning",
                    buttons: ["ok"],
                    message: "上传成功",
                    callback: function (action) {
                    	
                    }
                });
            } else {
               response = $("#x").html(response).text();
               if (response.indexOf("@") == 0) {
                   message = response.substring(1, response.length);
                   onCancel();
               } else {
            	   mini.alert(response);
            	   message = "";
               }
            }
        }
    }

    // 判断iframe的监听类型（IE只支持attachEvent，火狐支持addEventListener，W3C标准）
    if (appFrame.addEventListener) {
        appFrame.addEventListener('load', function(evt) {
            listener();
        }, false);
    } else if(appFrame.attachEvent) {
        appFrame.attachEvent('onload', function() {
            listener();
        });
    }

    //导入
    function upload() {
    	var uploadFile = mini.get("uploadFile");
    	if (uploadFile.getValue()=="") {
    		mini.alert("请选择上传文件");
    		return;
    	}
        window.parent.loading();
        // 表单提交
        uploaders.submit();
    }
    //关闭当前页面
    function onOkClose() {
    	CloseWindow("ok");
    }
    </script>
</body>
</html>