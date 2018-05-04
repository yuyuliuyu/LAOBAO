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
    <title>年假查询</title>
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
                <td style="width:250px;padding-left:20px;">年度：
                    <input class="mini-textbox" id="czUser" name="czUser" emptyText="请输入年度，如：2017" style="width:150px;"/>
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
             url="../check/statistics!getAnnualLeaveData.action" idField="id" multiSelect="true" 
             allowAlternating="true" pageSize="20" >
            <div property="columns">
                <div type="indexcolumn" width="50" headerAlign="center">序列</div>
                <div field="czDate" width="80" headerAlign="center" align="center" >姓名</div>
                <div field="czDate" width="100" headerAlign="center" align="center" >单位</div>
                <div field="czDate" width="100" headerAlign="center" align="center" >班组</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >应休</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >一月</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >二月</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >三月</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >四月</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >五月</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >六月</div>
                <div field="zcUser" width="50" headerAlign="center" align="center" >七月</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >八月</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >九月</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >十月</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >十一月</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >十二月</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >已休</div>
                <div field="czDate" width="50" headerAlign="center" align="center" >剩余</div>
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
    </script>
</body>
</html>