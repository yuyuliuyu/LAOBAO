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
    <title>班制表历史查询</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link rel="stylesheet" href="../template/system/css/base.css" type="text/css"></link>
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    body {
        margin: 0;
        padding: 0;
        border: 0;
        width: 100%;
        height: 100%;
        overflow: hidden;
    }
    .myrow {
        background: #fceee2;
    }
    </style>
</head>
<body>
    <div class="mini-toolbar" style="padding:0px;border-top:0;border-left:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:270px;padding-left:20px;">年月份：
                    <input class="mini-textbox" id="czUser" name="czUser" emptyText="请输入年月份：如1703" onenter="onKeyEnter" style="width:180px;"/>
                </td>
                <td style="white-space:nowrap;">
                    <a class="mini-button" iconCls="icon-search" onclick="search()" onenter="onKeyEnter">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="border-left:0;padding:0px;">
		<table style="width:100%;">
			<tr>
				<td>
					<a class="mini-button" iconCls="icon-upload" onclick="exportEmpInfo()">导出</a> 
				</td>
			</tr>
		</table>
	</div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
             url="../check/statistics!getInstituteData.action" idField="id" multiSelect="true" 
             allowAlternating="true" pageSize="20" >
            <div property="columns">
                <div type="indexcolumn" width="50" headerAlign="center">序列</div>
                <div field="fzx" width="80" headerAlign="center" align="center" >班制名称</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >1</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >2</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >3</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >4</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >5</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >6</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >7</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >8</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >9</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >10</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >11</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >12</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >13</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >14</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >15</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >16</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >17</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >18</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >19</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >20</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >21</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >22</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >23</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >24</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >25</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >26</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >27</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >28</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >29</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >30</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >31</div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
        mini.parse();
        
        window.onload = windowOnload;
        function windowOnload() {
            
        }
        
        var grid=mini.get("grid");
        grid.load();
                
        //////////////////////////////////////////////
        function ondayRenderer(e) {
            var value = e.value;
            if(value != null){
                value = new Date(value);
                if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
            }else{
                return "";
            }
        }
        
        ///////////////////////////////////////////////
        function search() {
            var czUser= mini.get("czUser").getValue();
            var stadate= mini.get("stadate").getText();
            var enddate= mini.get("enddate").getText();
            var czType= mini.get("czType").getValue();
            var djType= mini.get("djType").getValue();
            var ip= mini.get("ip").getValue();
            var fzx=mini.get("fzx").getText();
            grid.load({
                czUser : czUser,
                stadate : stadate,
                enddate : enddate,
                djType : djType,
                ip : ip,
                fzx:fzx
            });
        }
        
        function onKeyEnter(e) {
            search();
        }
        function beforenodeselect(e) {
            //禁止选中父节点
            if (e.isLeaf == false) e.cancel = true;
        }
        
        //新增
		function add() {
			mini.open({
				url : "../check/institute!add.action",
				title : "新增班制表信息",
				width : 660,
				height : 440,
				allowResize : false,
				onload : function() {
					//var iframe = this.getIFrameEl(); 
				},
				ondestroy : function(action) {
					//刷新页面
					window.location.reload();
				}
			});
		}
		//编辑
		function edit() {
			var rows = grid.getSelecteds();
			var row = grid.getSelected();
			if (rows.length <= 0){
				mini.alert("请选择一条记录！");
			} else if (rows.length > 1){
				mini.alert("只能选择一条记录！");
			} else {
				mini.open({
					url : "../check/institute!edit.action?id=" + row.id,
					title : "修改班制表信息",
					width : 700,
					height : 400,
					/* allowResize:false, */
					onload : function() {
					},
					ondestroy : function(action) {
						//刷新页面
						window.location.reload();
					}
				});
			}
		}
		//循环排班
		function cycle() {
			mini.open({
				url : "../check/institute!cycle.action",
				title : "循环排班",
				width : 660,
				height : 500,
				allowResize : false,
				onload : function() {
					//var iframe = this.getIFrameEl(); 
				},
				ondestroy : function(action) {
					//刷新页面
					window.location.reload();
				}
			});
		}
    </script>
</body>
</html>