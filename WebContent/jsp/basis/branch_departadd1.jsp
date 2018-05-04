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
<title>分中心部门</title>
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
	
	<div  style="width:100%;float:left" >
    <form id="form1" method="post">
        
        <input class="mini-hidden" name="id" id="id" value="${branch.id}" style="display:none;"/>
        
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
                
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">单位名称：</td>
                    <td class="list_right_box" width="35%">
                    	<s:if test="branch.id!=null&&branch.id!=''">
                            <input name="fzx" id="fzx" class="mini-textbox" value="${branch.fzx}"
                                   enabled="false" vtype="maxLength:25" width="200px"onblur="onblur('fzx','srm')"/>
                        </s:if>
                        <s:else>
                            
                                   <input name="fzx" id="fzx" class="mini-textbox" value="${branch.fzx}" 
                                   required="true" requiredErrorText="不能为空" vtype="maxLength:25" width="200px"onblur="onblur('fzx','srm')"/>
                        </s:else>
                        
                    </td>
                    
                    <td class="list_left_box1" valign="top" width="15%">简称：</td>
                    <td class="list_right_box" width="35%">
                    	<input name="fzx" id="fzx" class="mini-textbox" value="${branch.fzx}"
                         	 enabled="false" vtype="maxLength:25" width="100%"onblur="onblur('fzx','srm')"/>
                        
                    </td>
                    
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">父级单位：</td>
                    <td class="list_right_box" >
                         <input id="btnEdit1"name="pid" class="mini-buttonedit" onbuttonclick="onButtonEdit" width="200px"
                                value="${branch.pid}" text="${pname}" allowInput="false"/>
                    </td>
                    <td class="list_left_box1" valign="top">公司简介：</td>
                    <td class="list_right_box" >
                    	<input id="nbyj" name="nbyj" value="${branch.nbyj}" class="mini-textarea" style="width:100%;" />
                        
                    </td>
                </tr>
                
                <tr>
                    <td class="list_left_box1" valign="top">公司资质：</td>
                    <td class="list_right_box" colspan="3">
                    	<a class="mini-button" iconCls="icon-upload" onclick="upload()">上传</a>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="15%">网址：</td>
                    <td class="list_right_box" width="35%">
                    	<input name="fzx" id="fzx" class="mini-textbox" value="${branch.fzx}"
                         	 enabled="false" vtype="maxLength:25" width="100%"onblur="onblur('fzx','srm')"/>
                        
                    </td>
                    
                    <td class="list_left_box1" valign="top" width="15%">名誉：</td>
                    <td class="list_right_box" width="35%">
                    	<input name="fzx" id="fzx" class="mini-textbox" value="${branch.fzx}"
                         	 enabled="false" vtype="maxLength:25" width="100%"onblur="onblur('fzx','srm')"/>
                        
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">历史沿革：</td>
                    <td class="list_right_box" colspan="3">
                    	<input value="" class="mini-textarea" width="100%" />
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top">备注：</td>
                    <td class="list_right_box" colspan="3">
                    	<input value="" class="mini-textarea" width="100%" />
                    </td>
                </tr>
              
            </table>
            
            
        <fieldset style="width:700px;border:solid 1px #aaa;margin-top:8px;position:relative;">
        <legend>工商注册信息</legend>
        <div id="editForm1" style="padding:5px;">
            
            <table style="width:100%;">
                <tr>
                    <td style="width:200px;">名称：</td>
                    <td style="width:200px;">
                    <input id="loginname" name="loginname" class="mini-textbox" />
                    </td>
                    <td style="width:200px;">类型：</td>
                    <td style="width:200px;">
                    <input id="name" name="name" class="mini-textbox" />
                    </td>
                    <td style="width:200px;">法定代表人：</td>
                    <td style="width:200px;">
                    <input id="gender" name="gender" class="mini-combobox" data="Genders"/>
                    </td>
                </tr>
                <tr>
                    <td>住所：</td>
                    <td>
                    <input id="name" name="name" class="mini-textbox" />
                    </td>
                    <td>注册资本：</td>
                    <td>
                    <input id="age" name="age" class="mini-spinner" minValue="0" maxValue="200" value="25"  />
                    </td>
                    <td>成立日期：</td>
                    <td><input id="birthday" name="birthday" class="mini-datepicker" /></td>
                </tr>
                <tr>
                    <td>营业期限：</td>
                    <td>
                    <input id="loginname" name="loginname" class="mini-textbox" />
                    </td>
                    <td >经营范围：</td>
                    <td >
                    <input id="name" name="name" class="mini-textbox" />
                    </td>
                    <td >统一社会信用代码：</td>
                    <td >
                    <input name="salary" class="mini-textbox" />
                    </td>
                </tr>
                <tr>
                    <td>开户行：</td>
                    <td>
                    <input id="loginname" name="loginname" class="mini-textbox" />
                    </td>
                    <td >账号：</td>
                    <td >
                    <input id="name" name="name" class="mini-textbox" />
                    </td>
                    <td >通信地址：</td>
                    <td >
                    <input name="salary" class="mini-textbox" />
                    </td>
                </tr>
                <tr>
                    <td>联系部门：</td>
                    <td>
                    <input id="loginname" name="loginname" class="mini-textbox" />
                    </td>
                    <td >联系人：</td>
                    <td >
                    <input id="name" name="name" class="mini-textbox" />
                    </td>
                    <td >职务：</td>
                    <td >
                    <input name="salary" class="mini-textbox" />
                    </td>
                </tr>
                <tr>
                    <td>联系电话：</td>
                    <td>
                    <input id="loginname" name="loginname" class="mini-textbox" />
                    </td>
                    <td >传真：</td>
                    <td >
                    <input id="name" name="name" class="mini-textbox" />
                    </td>
                    <td >网页：</td>
                    <td >
                    <input name="salary" class="mini-textbox" />
                    </td>
                </tr>
            </table>
        </div>
    </fieldset>
        
    </form>
    
    </div>
    <div class="mini-toolbar" id="submitDiv"
		style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;"
		borderStyle="border:0;">
		<a class="mini-button" id="ok" onclick="onOk" iconCls="icon-save"
			style="width:60px;margin-right:20px;" iconCls="icon-save">提交</a>
	</div>
    <script type="text/javascript">
        mini.parse();
        function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "../basis/branch!tree.action",
                showMaxButton: false,
                title: "选择父级分中心",
                width: 250,
                height: 330,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        
                        if(mini.get("id").getValue()==data.id){
                            mini.alert("父级分中心不能是自己！");
                            return;
                        }
                        if (data) {
                            btnEdit.setValue(data.id);
                            btnEdit.setText(data.name);
                        }
                    }
                }
            });
        }
    </script>
</body>
</html>