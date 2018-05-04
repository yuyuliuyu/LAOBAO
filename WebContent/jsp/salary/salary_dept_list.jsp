<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path;
    pageContext.setAttribute("base", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>薪酬岗位字典</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/public/list.js" type="text/javascript"></script>
</head>

<body>
    <div class="mini-toolbar" style="padding:0px;border-top:0;">
        <table style="width:100%;">
            <tr>
                <td style="width:100%;">
                    <a class="mini-button" iconCls="icon-add" onclick="addWindow('薪酬岗位字典','salary','salary_dept', 620,320, false)">增加</a>
                    <a class="mini-button" iconCls="icon-edit" onclick="editWindow('薪酬岗位字典','salary','salary_dept',620,320,false)">编辑</a>
                    <a class="mini-button" iconCls="icon-remove" onclick="remove('','salary','salary_dept',false)">删除</a>
                    &nbsp;岗位名称：<input class="mini-textbox" id="name" onenter="onKeyEnter"/>
                    <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="search('name','name')">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-fit">
        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" pageSize="20"
            url="../salary/salary_dept!getDataList.action" showPager="false" allowCellWrap="true"
            multiSelect="true" idField="id" allowResize="false">
            <div property="columns">
                <div type="checkcolumn" width="40"></div>
                <div type="indexcolumn" headerAlign="center" width="40">序列</div>
                <div field="id" id="id" width="80" name="id" class="mini-hidden"></div>
                <div field="zwbm" width="60" headerAlign="center" align="center">岗位编码</div>
                <div field="name" width="140" headerAlign="center" align="center">薪酬岗位</div>
                <div type="comboboxcolumn" field="type" width="80" headerAlign="center" align="center">类别
                    <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945c0f0f8b015c0f148bf7003c"
                        textField="dicname" valueField="dicvalue"/>
                </div>
                <div field="gjdx" width="80" headerAlign="center" align="center">工资档序</div>
                <div type="comboboxcolumn" field="zjlb" width="80" headerAlign="center" align="center">职级类别
                    <input property="editor" class="mini-combobox" url="../system/dectionary!listjson.action?id=402881945c0f0f8b015c0f173cfe003f"
                        textField="dicname" valueField="dicvalue"/>
                </div>
                <div field="note" width="120" headerAlign="center" align="center">备注</div>
                <div field="positionName" width="120" headerAlign="center" align="center">标准岗位</div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        mini.parse();

        var grid = mini.get("grid");

        function removes() {
            var rows=grid.getSelecteds();
            var l=rows.length
            if (l == 0) {
                mini.alert("请选择一条记录");
                return;
            }
            var ids = rows[0].id;
            for(var i=1;i<l;i++){
                ids=ids+","+rows[i].id;
            }
            mini.confirm("您确定要删除该信息吗？","提醒",function(action){
                if(action=="ok"){
                    window.parent.loading();
                    $.ajax({
                        url : "../salary/salary_dept!remove.action?ids="+ids,
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
    </script>
</body>
</html>