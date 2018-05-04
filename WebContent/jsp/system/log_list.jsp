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
    <title>日志查询</title>
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
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
            	<td>分中心：
	            	<input id="fzx" class="mini-treeselect" url="../basis/branch!treeList.action" multiSelect="true"  valueFromSelect="true"
				        textField="name" valueField="id" parentField="pid" onbeforenodeselect="beforenodeselect" allowInput="true"
				        showRadioButton="false" showFolderCheckBox="false" expandOnLoad="true" emptyText="请选择..." 
				    />
    			</td>
                <td>操作用户：
                    <input class="mini-textbox" id="czUser" name="czUser" emptyText="请输入操作用户" onenter="onKeyEnter" />
                </td>
                <td>开始日期：
                    <input id="stadate" name="stadate" class="mini-datepicker" emptyText="请选择开始日期" dateFormat="yyyy-MM-dd" onenter="onKeyEnter"/>
                </td>
                <td>结束日期：
                    <input id="enddate" name="enddate" class="mini-datepicker" emptyText="请选择结束日期" dateFormat="yyyy-MM-dd" onenter="onKeyEnter"/>
                </td>
                <td>单据类型：
                    <input class="mini-textbox" id="djType" name="djType" emptyText="请输入单据类型" onenter="onKeyEnter" />
                </td>
                <td>操作类型：
                    <div id="czType" name="czType" class="mini-combobox" emptyText="请选择..." 
                         allowInput="false" popupWidth="140" textField="text" valueField="id" width="140px"
                         multiSelect="true" showClose="false" oncloseclick="onCloseClick" onenter="onKeyEnter"> 
                        <div property="columns">
                            <div header="操作类型" field="text"></div>
                        </div>
                    </div>
                </td>
                <td>IP地址：
                    <input class="mini-textbox" id="ip" name="ip" emptyText="请输入IP地址" onenter="onKeyEnter"/>
                </td>
                <td style="white-space:nowrap;">
                    <a class="mini-button" iconCls="icon-search" onclick="search()" onenter="onKeyEnter">查询</a>
                </td>
            </tr>
        </table>
    </div>
    
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" 
             url="../system/log!search.action" idField="id" multiSelect="true" 
             allowAlternating="true" pageSize="20" >
            <div property="columns">
                <div type="indexcolumn" headerAlign="center">序列</div>
                <div field="fzx" width="50" headerAlign="center" align="center" >分中心</div>
                <div field="zcUser" width="80" headerAlign="center" align="center" >操作用户</div>
                <div field="czDate" width="100" headerAlign="center" align="center" renderer="ondayRenderer">操作时间</div>
                <div field="djType" width="80" headerAlign="center" align="center">菜单名称</div>
                <%-- <div field="czdj" width="80" headerAlign="center" align="center">操作单据</div>--%>
                <div field="czType" width="80" headerAlign="center" align="center">操作类型</div>
                <div field="ipDz" width="80" headerAlign="center" align="center">IP地址</div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
        mini.parse();
        
        window.onload = windowOnload;
        function windowOnload() {
            mini.get("stadate").setValue("2007-01-01");
            mini.get("enddate").setValue(new Date());
        }
        
        var grid=mini.get("grid");
        grid.load();
        
        //////////////////////////////////////////////
        var czType  = mini.get("czType");
        czType.setData([{"id":"'增加'", "text":"增加"},
                        {"id":"'编辑'", "text":"编辑"},
                        {"id":"'删除'", "text":"删除"},
                        {"id":"'查看'", "text":"查看"},
                        {"id":"'查询'", "text":"查询"},
                        {"id":"'启用'", "text":"启用"},
                        {"id":"'禁用'", "text":"禁用"},
                        {"id":"'推送'", "text":"推送"},
                        {"id":"'撤销推送'", "text":"撤销推送"}
                        ]);
        
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
                czType : czType,
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
    </script>
</body>
</html>