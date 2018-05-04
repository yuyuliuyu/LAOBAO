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
<title>电话表execl导入</title>
    <style type="text/css">
    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }
    </style>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
    <script src="../template/plugin/public/cklist.js" type="text/javascript"></script>
    
</head>
<body>
<!-- 	<div class="mini-splitter" style="width:100%;height:100%;"
		handlerSize="9"> -->
	 <div class="mini-toolbar" style="padding:0px;">
        <table style="width:100%;">

		<div showCollapseButton="flase">
		      <div class="mini-toolbar"
		        style="padding:0px;border-top:0;border-left:0;">
		        <table style="width:100%;">
		            <tr>
 		                  <td style="width:200px;">部门名称：
                   				 <input id="department" name="department" class="mini-textbox" emptyText="" onenter="onKeyEnter"/>
              			   </td> 
              			   <td style="width:200px;">岗位名称：
              			       <input id="postname" name="postname" class="mini-textbox" emptyText="" onenter="onKeyEnter"/>
              			    </td> 
              			    <td style="width:200px;">使用人：
              			       <input id="username" name="username" class="mini-textbox" emptyText="" onenter="onKeyEnter"/>
              			    </td>
            			    <td style="width:200px;">电话号码：
                   				 <input id="phonenumber" name="phonenumber" class="mini-textbox"  onenter="onKeyEnter"/>
              			   </td>
              			    <td align="right">类别：</td>
							<td align="left" >
								<input id="type" name="type" class="mini-combobox" emptyText="请输入类别" 
								data="[{'id':'','text':''},{id : '0',text : '青港物流1'},{id : '1',text : '青港物流2'},{id : '2',text : '青港物流3'},{id : '3',text : '青港物流4'},{id : '4',text : '青港物流5'},{id : '5',text : '青港物流6'},{id : '6',text : '青港物流7'}]" 
								emptyText="全部" nullItemText="全部" allowInput="false" showNullItem="true" onenter="onKeyEnter"/>							
							</td>


               			   <td style="white-space:nowrap;">
                 			   <a id="searchButton" class="mini-button" iconCls="icon-search" 
                 			   onclick="search('department,postname,username,phonenumber,type','department,postname,username,phonenumber,type')" onenter="onKeyEnter">查询</a>
               			   </td>
		            </tr>
		        </table>
		    </div>
		    <div class="mini-toolbar" style="border-left:0;padding:0px;">
		        <table style="width:100%;">
		            <tr>
		                <td>

							<tags:hasPerm value="/laobao/wupin_input!upload.action">
							<a class="mini-button" iconCls="icon-find" onclick="upload">导入</a>
							</tags:hasPerm>
                     <!--    <a class="mini-button" iconCls="icon-upload" onclick="upload()">导入数据</a> -->
		                <a class="mini-button" iconCls="icon-upload" onclick="exportClassInfo()">导出</a> 
		                <a class="mini-button" iconCls="icon-remove" onclick="removes()">删除</a></td> 
		            </tr>
		        </table>
		    </div> 

		    <div class="mini-fit">
		        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;"
		            url="../laobao/wu_pin_history!getPersonData.action" idField="id"
		            multiSelect="true" allowAlternating="true" pageSize="20">
		
		            <div property="columns">
		
		                <div type="checkcolumn"></div>
		                <div type="indexcolumn" headerAlign="center">序列</div>
		                <div field="id" id="id" width="80" name="id" headerAlign="center" align="center" class="mini-hidden">职工号</div>
		             <div field="department" id ="department" name = "department"width="100" headerAlign="center" align="left">部门名称</div>
		                <div field="postname" width="100" headerAlign="center" align="left">岗位名称</div>
		                <div field="username" id="username" name="username" width="100" headerAlign="center" align="left">使用人</div>
		                 <div field="phonenumber" id="phonenumber" name="phonenumber" width="100" headerAlign="center" align="left">电话号码</div>
		                <div field="type" id="ggxh" name="ggxh" width="100" headerAlign="center" align="left">类别</div>
<!-- 		                <div field="jldw" id="jldw" name="jldw" width="100" headerAlign="center" align="left">计量单位</div>
		                <div field="gys" id="gys" name="gys" width="100" headerAlign="center" align="left">供应商</div>
		                <div field="createdate" width="100" headerAlign="center" align="left"dateFormat="yyyy-MM-dd HH:mm:ss"  renderer="ondayRenderer2">创建时间</div>
		                <div field="nextdate" width="100" healthAlign="center" align="left" dateFormat="yyyy-MM-dd HH:mm:ss" renderer="ondayRenderer2">下次领取时间</div> -->
		            </div>
		        </div>
		    </div>
		</div>
	</div>
	<script type="text/javascript">
		mini.parse();
		var leftTree = mini.get("tree1");
		var grid = mini.get("grid");
        grid.load();
        function onKeyEnter(e) {
             $("#searchButton").click();
        }
/*         /**展示附件弹窗*/
/* function showAttatch(){
	var grid=mini.get("grid");
	var rows=grid.getSelecteds();
 	if(rows.length!=1){
		mini.alert("请选择一条");
		return;
	} 
	var id=rows[0].id;
	mini.open({
		url: '../laobao/wupin_input!upload.action?id='+id,
		showMaxButton: false,
		title: '附件',
		width: 870,
		height: 600
	});  */
		 function upload() {
	    	var grid=mini.get("grid");
	        mini.open({
	             url: "../laobao/wupin_input!upload.action",
	             showMaxButton: false,
	             title: "交割导入",
	             width: 400,
	             height: 250,
	             onload : function() {
	             },
	             ondestroy : function(action) {
	            	 grid.load();
	            }
	         });
	     }

/*        function upload() {
             mini.open({
                  url: "../laobao/wupin_input!upload.action",
                  showMaxButton: false,
                  title: "批量上传",
                  width: 350,
                  height: 200,
     
  				onload : function() {
  				},
  				ondestroy : function(action) {
  					//刷新页面
  					window.location.reload();
  				}
              });
          }   */
/*         function upload(){
        window.location.href="../laobao/wupin_input!upload.action";
        
        } */
        //////////////////////////////////////////////
        function ondayRenderer(e) {
            var value = e.value;
            if (value != null) {
                value = new Date(value);
                if (value)
                    return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
            } else {
                return "";
            }
        }

   function removes() {
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i = 1, l = rows.length; i< l; i++) {
                ids += "," + rows[i].id;
            }
            mini.confirm("您确定要删除该信息吗？","提醒",function(action){
                if(action=="ok"){
                    window.parent.loading();
                    $.ajax({
                        url : "../laobao/wu_pin_history!delete.action?ids="+ids,
                        type:'post',
                        success: function (text) {
                            window.parent.unmask();
                            if(text=="success"){
                                grid.reload();
                                mini.alert("删除成功！");
                            }else{
                                mini.alert(text);
                            }
                        }
                    });
                }
            });
        }


        function onKeyEnter(e) {
            search();
        }
        function beforenodeselect(e) {
            //禁止选中父节点
            if (e.isLeaf == false)
                e.cancel = true;
        }
           function exportClassInfo(){
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i = 1, l = rows.length; i< l; i++) {
                ids += "," + rows[i].id;
            }

             mini.confirm("确定要导出？","提醒",function(action){
                if(action=="ok"){
                window.location.href="../laobao/wu_pin_history!exportClassInfo.action?personId="+ids;

                }
            });  
            } 
  /*      function exportClassInfo(){
            var rows = grid.getSelecteds();
            if (rows.length ==0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for (var i = 1, l = rows.length; i< l; i++) {
                ids += "," + rows[i].id;
            }

             mini.confirm("确定要导出？","提醒",function(action){
                if(action=="ok"){
                    window.parent.loading();
               
                    $.ajax({
                        url : "../laobao/wu_pin_history!exportClassInfo.action",
                        type : "post",
                        data : {
                            ids : ids
                        }, 
                        success : function(text) {
                            window.parent.unmask();
                            if (text == "success") {
                                mini.showMessageBox({
                                    title: "提醒",
                                    width: 250,
                                    iconCls: "mini-messagebox-warning",
                                    buttons: ["ok"],
                                    message: "已同意",
                                    callback: function (action) {
                                        grid.reload();
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
            });  

           
        } */
         
        


	</script>
</body>
</html>