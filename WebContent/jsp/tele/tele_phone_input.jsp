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
<title>考勤历史树信息</title>
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
    
</head>
<body>
<!-- 	<div class="mini-splitter" style="width:100%;height:100%;"
		handlerSize="9"> -->
	 <div class="mini-toolbar" style="padding:0px;">
        <table style="width:100%;">
	<%-- 	<div size="260" showCollapseButton="true">
			<input type="hidden" id="userType" value="${userType }" />
			<div class="mini-toolbar"
				style="padding:2px;border-top:0;border-left:0;border-right:0;"
				name="form1"></div>
			<div class="mini-fit">
				<ul id="tree1" class="mini-tree"
					url="../basis/branch!treeList.action"
					style="width:100%;height:100%;" showTreeIcon="true"
					textField="name" idField="id" parentField="pid"
					resultAsTree="false" expandOnLoad="true">
				</ul>
			</div>
		</div>  --%>
		<div showCollapseButton="flase">
		      <div class="mini-toolbar"
		        style="padding:0px;border-top:0;border-left:0;">
		        <table style="width:100%;">
		            <tr>
 		                  <td style="width:200px;">部门名称：
                   				 <input id="department" name="department" class="mini-textbox" emptyText="请输入" onenter="onKeyEnter"/>
              			   </td>  
            			    <td style="width:200px;">电话号码：
                   				 <input id="phonenumber" name="phonenumber" class="mini-textbox"  onenter="onKeyEnter"/>
              			   </td>


               			   <td style="white-space:nowrap;">
                 			   <a id="searchButton" class="mini-button" iconCls="icon-search" onclick="search('department,phonenumber','department,phonenumber')" onenter="onKeyEnter">查询</a>
               			   </td>
		            </tr>
		        </table>
		    </div>
		    <div class="mini-toolbar" style="border-left:0;padding:0px;">
		        <table style="width:100%;">
		            <tr>
		                <td>
		                <a class="mini-button" iconCls="icon-upload" onclick="upload()">导入数据</a>
		                <tags:hasPerm value="/tele/tele_cd!jkimport.action">
							<a class="mini-button" iconCls="icon-exchange" onclick="jkimport">接口导入</a>
						</tags:hasPerm>
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
		             <div field="jobNumber" id ="jobNumber" name = "jobNumber"width="100" headerAlign="center" align="left">职工编号</div>
		                <div field="name" width="100" headerAlign="center" align="left">姓名</div>
		                <div field="wpmc" id="wpmc" name="wpmc" width="100" headerAlign="center" align="left">物品名称</div>
		                 <div field="count" id="count" name="count" width="100" headerAlign="center" align="left">领取数量</div>
		                <div field="ggxh" id="ggxh" name="ggxh" width="100" headerAlign="center" align="left">规格型号</div>
		                <div field="jldw" id="jldw" name="jldw" width="100" headerAlign="center" align="left">计量单位</div>
		               <!--  <div field="gys" id="gys" name="gys" width="100" headerAlign="center" align="left">供应商</div> -->
		                <div field="createdate" width="100" headerAlign="center" align="left"dateFormat="yyyy-MM-dd HH:mm:ss"  renderer="ondayRenderer2">创建时间</div>
		                <div field="nextdate" width="100" healthAlign="center" align="left" dateFormat="yyyy-MM-dd HH:mm:ss" renderer="ondayRenderer2">下次领取时间</div>
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

	</script>
</body>
</html>