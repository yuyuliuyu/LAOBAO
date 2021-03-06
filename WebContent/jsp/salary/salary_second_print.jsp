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
    <title>打印二次分配</title>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <script src="../template/plugin/print/LodopFuncs.js" type="text/javascript"></script>
    <script src="http://localhost:8000/CLodopfuncs.js?priority=1" type="text/javascript"></script>

    <style type="text/css" id="style1">
        .base {
            border-left: 1px solid #000;
            border-top: 1px solid #000;
        }
        .base td {
            border-right: 1px solid #000;
            border-bottom: 1px solid #000;
            text-overflow: ellipsis; /* for IE */  
            -moz-text-overflow: ellipsis; /* for Firefox,mozilla */  
            overflow: hidden;  
        }
        .title {
            width:60px;
            font-size: 14px;
        }
        .title_wrap {
            white-space: pre-line;
        }
        .title_align {
            text-align: center;
        }
        .g-title {width: 100%;text-align : center}
        .g-title p {font-size:24px;font-weight:bold;text-align: center;margin:0px}
    </style>
</head>

<body>
    <div id="salaryLayout" style="display:none;"></div>
    <div id="foot" style="width:100%;margin:0px auto;font-size: 14px;display: none;">
        <div style="float:left;">[打印时间：<span id="curDate"></span>]</div>
        <div style="float:right;display: flex;font-size:14px">
            <div style="float:left;margin-right: 14px;">人数：<font id="staffCount"></font></div>
            <div>
                <span style="text-align:right">第<font tdata="PageNO" format="0">#</font>页</span>
            </div>
        </div>
    </div>
    <object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
        <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
    </object>
    <div class="mini-fit">
        <table style="width:100%;height: 100%;">
            <tr>
                <td style="width:100%;height: 100%;text-align: center;">
                    &nbsp;打印机名称：<input class="mini-combobox" id ="printer" url="../salary/salary_quota!getPrinterList.action" style="width: 200px"
                        textField="name" popupWidth="auto"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" iconCls="chengben" style="margin-right:20px;" onclick="preview()">预览</a>
        <a class="mini-button" iconCls="icon-print" style="margin-right:20px;" onclick="print()">打印</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-cancel">取消</a> 
    </div>

    <script type="text/javascript">
        mini.parse();
        document.getElementById("curDate").innerHTML=mini.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
        var printer = mini.get("printer");
        printer.setValue('${defaultPrinter}');

        function dynamicTable(preType) {
            var LODOP = "";
            try {
                LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
                if (LODOP && !LODOP.VERSION) {
                    return;
                }
            } catch(ex) {
                mini.alert(ex.message);
                return;
            }
            window.parent.loading();
            $.ajax({
                url: "../salary/salary_second!getAllSecondForTypeData.action",
                type: "POST",
                data : {
                    period: '${period}',
                    companyId: '${companyId}',
                    searchData : '${searchData}'
                },
                success: function(text) {
                    window.parent.unmask();
                    var gridData = mini.decode(text);
                    var jsonData = gridData;

                    // 设置打印参数
                    LODOP.PRINT_INITA(0,0, "280mm","380mm", "${data}_员工二次分配");
                    LODOP.SET_PRINT_PAGESIZE(2,"280mm","380mm", "");// 纸张大小

                    var strBodyStyle="<style>"+document.getElementById("style1").innerHTML+"</style>";

                    // 标题
                    var title = '<div class="g-title" id="m-top-title"><p>员工二次分配</p></div>';
                    // 标题每页都存在
                    LODOP.ADD_PRINT_HTM("4mm","0","100%","24px", strBodyStyle + title);
                    LODOP.SET_PRINT_STYLEA(0,"ItemType",1);

                    // 次级标题
                    var nextTitle = '<table style="width:100%;margin-top:4px;font-size:14px;" border="0" cellpadding="0" cellspacing="0">'
                        + '         <tr>'
                        + '             <td>部门：${deptName}</td>'
                        + '             <td style="text-align: center;">薪资组：${groupName}</td>'
                        + '             <td style="float: right;">分配权限：${fpqxName}</td>'
                        + '         </tr>'
                        + '     </table>';
                    LODOP.ADD_PRINT_TABLE("15mm","2%","96%", "30%", strBodyStyle + nextTitle);
                    LODOP.SET_PRINT_STYLEA(0,"ItemType",1);

                    // 表头
                    var html= '<table id="salaryData" class="base" style="width:100%;" border="0" cellpadding="0" cellspacing="0">'
                       + '         <thead><tr>'
                       + '             <td class="title title_align title_wrap">工号</td>'
                       + '             <td class="title title_align title_wrap">姓名</td>'
                       + '             <td class="title title_align title_wrap">具体岗位</td>'
                       + '             <td class="title title_align title_wrap">标准岗位</td>'
                       + '             <td class="title title_align title_wrap">薪酬岗位</td>'
                       + '             <td class="title title_align title_wrap">特殊标记</td>'
                       + '             <td class="title title_align title_wrap">工资形式</td>';

                    // 循环便利所有的福利项目
                    if (jsonData.length > 0) {
                        var fulisName = jsonData[0][5].split(",");
                        var actualName = jsonData[0][15].split(",");
                        for (var i=0; i< fulisName.length;i++) {
                            html += ' <td class="title title_align title_wrap">'+ fulisName[i] +'</td>';
                            html += ' <td class="title title_align title_wrap">'+ actualName[i] +'</td>';
                        }
                    }
                    html += '     </thead></tr>';

                    var isAdd = false;
                    // 遍历数据
                    var gzxs = {"0":"计时", "1":"计件", "2":"联产"};
                    for (var i=0; i< jsonData.length;i++) {
                        var row = jsonData[i];
                        html += '<tr>'
                             + '    <td class="title title_align">'+ row[2] +'</td>'
                             + '    <td class="title title_align">'+ row[3] +'</td>'
                             + '    <td class="title title_align">'+ row[11] +'</td>'
                             + '    <td class="title title_align">'+ row[10] +'</td>'
                             + '    <td class="title title_align">'+ row[12] +'</td>'
                             + '    <td class="title title_align">'+ row[13] +'</td>'
                             + '    <td class="title title_align">'+ gzxs[row[14]] +'</td>';
                        if (row[4] != null) {
                            var charge = row[7].split(",");
                            var actualAmount = row[16].split(",");
                            for (var j=0; j< charge.length;j++) {
                                html += ' <td class="title title_align">'+ charge[j] +'</td>';
                                html += ' <td class="title title_align">'+ actualAmount[j] +'</td>';
                            }
                        }
                        isAdd = true;
                        html += '  </tr>';
                    }
                    html += '     </table>';
                    $("#salaryLayout").html(html);

                    // 人数
                    $("#staffCount").html(jsonData.length);

                    var salaryLayout = '<div id="salaryLayout">'+$("#salaryLayout").html()+'</div>';
                    LODOP.ADD_PRINT_TABLE("20mm","2%","96%", "90%", strBodyStyle + salaryLayout);// 薪资信息
                    LODOP.SET_PRINT_STYLEA(0,"FontSize",12);

                    var foot = '<div id="foot" style="width:100%;margin:0px auto;font-size: 14px">'+document.getElementById("foot").innerHTML + '</div>';
                    LODOP.ADD_PRINT_HTM("96%","2%","96%","10%", foot);
                    LODOP.SET_PRINT_STYLEA(0,"ItemType",1);// 作为页脚

                    // 可以选择打印机
                    LODOP.SET_PRINT_MODE("RESELECT_PRINTER", true);
                    LODOP.SET_PRINT_MODE("RESELECT_ORIENT", true);
                    LODOP.SET_PRINT_MODE("RESELECT_PAGESIZE", true);
                    LODOP.SET_PRINT_MODE("RESELECT_COPIES", true);
                    LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD",true);//隐藏走纸板
                    LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",1);//横向时的正向显示
                    LODOP.SET_PRINTER_INDEX(printer.getValue());// 指定打印机（序号或者打印机名称）

                    if (preType == 0) {
                        // 预览
                        LODOP.PREVIEW();
                    } else if (preType == 1) {
                        // 打印
                        LODOP.PRINT();
                    }
                }
            });
        }

        // 预览
        function preview() {
            dynamicTable(0);
        }

        // 打印
        function print() {
            dynamicTable(1);
        }

        /** 关闭按钮点击事件 */
        function onCancel() {
            CloseWindow("cancel");
        }

        /** 关闭窗口 */
        function CloseWindow(action) {
            if (window.CloseOwnerWindow)
                return window.CloseOwnerWindow(action);
            else
                window.close();
        }

    </script>
</body>
</html>