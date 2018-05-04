<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>打印页面</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="../template/print/LodopFuncs.js" type="text/javascript"></script>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <!--引入皮肤样式-->
    <link href="${base}/template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <script type="text/javascript">
        
        var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
        
        function print(){
            LODOP.SET_LICENSES("青岛一凌网集成有限公司","818FEC6D453B7FDCEA47C8A096DA3745","","");
            //LODOP.PRINT_INIT("转仓单调整");
            LODOP.SET_PRINT_PAGESIZE(0,"220mm","120mm","");
            LODOP.ADD_PRINT_HTM("5mm","3mm","RightMargin:3mm","BottomMargin:9mm",document.getElementById("filing_app").innerHTML);
            //LODOP.SET_PRINT_STYLEA(0,"HOrient",3);
            //LODOP.SET_PRINT_STYLEA(0,"VOrient",3);
            LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
            LODOP.PRINT_DESIGN();
            //LODOP.PRINT_SETUP();
            //LODOP.PREVIEW();
        }
        
    </script>
    
    <div id="filing_app" >
    
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;position:fixed;z-index:999;bottom:0; width: 900px; " borderStyle="border:0;">
        <a class="mini-button" style="width:60px;" onclick="print()" iconcls="icon-print">打印</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" style="width:60px;" onclick="onCancel()" iconCls="icon-close">取消</a>
    </div>
    
    <script type="text/javascript">
        
        //标准方法接口定义
        function SetData(data) {
            //跨页面传递的数据对象，克隆后才可以安全使用
            data = mini.clone(data);
            $.ajax({
                url: "../system/print!desgine.action",
                title: "打印页面",
                type : "post",
                data : {
                    dataName:data.dataName,
                    data:data.datas,
                    footName:data.footName,
                    data1:data.footDatas,
                    title:data.title,
                    tableName:data.tableName,
                    tableMethod:data.tableMethod
                },
                success : function(text) {
                    document.getElementById("filing_app").innerHTML=text;
                },
                error : function(jqXHR, textStatus, errorThrown) {
                    mini.alert(jqXHR.responseText);
                }
            });
        }
        
        function onCancel(e) {
            CloseWindow("cancel");
        }
        function CloseWindow(action) {
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被编辑了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }
    </script>
</body>
</html>