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
<title>新增劳保物品发放标准信息</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
</head>
<body>
    <form id="form1" method="post">
        <input class="mini-hidden" name="id" id="id" value="${fafangbiaozhun.id}" style="display:none;"/>
        <div>
        
            <table cellpadding="0" cellspacing="0" border="0" class="addtable" width="100%">
<%--                 <tr>
                    <td class="list_left_box1" valign="top" width="30%">职工号：</td>
                    <td class="list_right_box" width="60%">
                        <input name="jobNumber" id="jobNumber" class="mini-textbox" value="${mapDate.JOB_NUMBER}"
                            required="true" requiredErrorText="不能为空" emptyText="请输入职工号：如666666" vtype="maxLength:8" width="200px"/>
                    </td>
                </tr> --%>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">岗位：</td>
                    <td class="list_right_box" width="60%">
                        <input name="name" class="mini-buttonedit" style="width:200px" required="true" requiredErrorText="不能为空" 
                            value="${fafangbiaozhun.name}" text="${data}" onbuttonclick="onButtonEdit(this,'岗位选择','../laobao/wu_pin!post.action',920,560,'positionName')"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">棉安全帽：</td>
                    <td class="list_right_box" width="60%">
                        <input name="maqm" id="maqm" class="mini-textbox" value="${fafangbiaozhun.maqm}"
                               required="true" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">单安全帽：</td>
                    <td class="list_right_box" width="60%">
                        <input name="daqm" id="daqm" class="mini-textbox" value="${fafangbiaozhun.daqm}"
                               required="true"  vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">长袖工装：</td>
                    <td class="list_right_box" width="60%">
                        <input name="cxgz" id="cxgz" class="mini-textbox" value="${fafangbiaozhun.cxgz}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>
                <tr>
                    <td class="list_left_box1" valign="top" width="30%">短袖工装：</td>
                    <td class="list_right_box" width="60%">
                        <input name="dxgz" id="dxgz" class="mini-textbox" value="${fafangbiaozhun.dxgz}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>              
                  <tr>
                    <td class="list_left_box1" valign="top" width="30%">羽绒服：</td>
                    <td class="list_right_box" width="60%">
                        <input name="yrf" id="yrf" class="mini-textbox" value="${fafangbiaozhun.yrf}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>              
                  <tr>
                    <td class="list_left_box1" valign="top" width="30%">雨衣：</td>
                    <td class="list_right_box" width="60%">
                        <input name="yy" id="yy" class="mini-textbox" value="${fafangbiaozhun.yy}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>              
                  <tr>
                    <td class="list_left_box1" valign="top" width="30%">雨靴：</td>
                    <td class="list_right_box" width="60%">
                        <input name="yx" id="yx" class="mini-textbox" value="${fafangbiaozhun.yx}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>              
                  <tr>
                    <td class="list_left_box1" valign="top" width="30%">棉安全鞋：</td>
                    <td class="list_right_box" width="60%">
                        <input name="maqx" id="maqx" class="mini-textbox" value="${fafangbiaozhun.maqx}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>               
                 <tr>
                    <td class="list_left_box1" valign="top" width="30%">绝缘鞋：</td>
                    <td class="list_right_box" width="60%">
                        <input name="jyx" id="jyx" class="mini-textbox" value="${fafangbiaozhun.jyx}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>             
<%--                    <tr>
                    <td class="list_left_box1" valign="top" width="30%">毛巾：</td>
                    <td class="list_right_box" width="60%">
                        <input name="maojin" id="maojin" class="mini-textbox" value="${ckStandard.maojin}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>           
                     <tr>
                    <td class="list_left_box1" valign="top" width="30%">线手套：</td>
                    <td class="list_right_box" width="60%">
                        <input name="xianshoutao" id="xianshoutao" class="mini-textbox" value="${ckStandard.xianshoutao}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>             
                   <tr>
                    <td class="list_left_box1" valign="top" width="30%">香皂：</td>
                    <td class="list_right_box" width="60%">
                        <input name="xiangzao" id="xiangzao" class="mini-textbox" value="${ckStandard.xiangzao}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>            
                    <tr>
                    <td class="list_left_box1" valign="top" width="30%">肥皂：</td>
                    <td class="list_right_box" width="60%">
                        <input name="feizao" id="feizao" class="mini-textbox" value="${ckStandard.feizao}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                    
                </tr>              
                  <tr>
                    <td class="list_left_box1" valign="top" width="30%">防滑耐：</td>
                    <td class="list_right_box" width="60%">
                        <input name="fanghuanai" id="fanghuanai" class="mini-textbox" value="${ckStandard.fanghuanai}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>    --%>           
                  <tr>
                    <td class="list_left_box1" valign="top" width="30%">绝缘手套：</td>
                    <td class="list_right_box" width="60%">
                        <input name="jyst" id="jyst" class="mini-textbox" value="${fafangbiaozhun.jyst}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>              
                  <tr>
                    <td class="list_left_box1" valign="top" width="30%">电焊手套：</td>
                    <td class="list_right_box" width="60%">
                        <input name="dhst" id="dhst" class="mini-textbox" value="${fafangbiaozhun.dhst}"
                               required="false" vtype="maxLength:2" width="200px"/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position: fixed;bottom: 0;width: 100%;" borderStyle="border:0;">
            <a class="mini-button" id="ok" onclick="onOk1" iconCls="icon-save" style="width:60px;margin-right:20px;"iconCls="icon-save">保存</a> 
            <a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
            <a class="mini-button" onclick="onCancel" iconCls="icon-close" style="width:60px;" iconCls="icon-close">关闭</a> 
        </div>
    </form>
    <script type="text/javascript">
        mini.parse();
        
        function onOk1() {
            var form = new mini.Form("form1");
            form.validate();
            if (form.isValid() == false) return;
            //strut令牌
            var data = form.getData();
            var json = mini.encode(data);
            window.parent.loading();
            $.ajax({
                url : "../laobao/wu_pin!saveOrUpdateBz.action",
                type : "post",
                data : {
                	formdata:json
                },
                success : function(text) {
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
                }/* ,
                error : function(jqXHR, textStatus, errorThrown) {
                   // mini.alert(jqXHR.responseText);
                } */
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