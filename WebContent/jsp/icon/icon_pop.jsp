<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
String selectedNum=request.getParameter("selectednum");
if(selectedNum==null)
	selectedNum="0";
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Window 弹出面板</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 

    <script src="${base}template/miniui/boot.js" type="text/javascript"></script>
    
    <!--引入皮肤样式-->
    <link href="${base}template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <h1>Window 弹出面板</h1>      
    

        x: <select id="x">
                    <option value="left">Left</option>
                    <option value="center" >Center</option>
                    <option value="right" selected>Right</option>
                </select>
        y: <select id="y">
                    <option value="top" >Top</option>
                    <option value="middle" >Middle</option>
                    <option value="bottom" selected>Bottom</option>
                </select>
        <input type="button" value="showAtPos" onclick="showAtPos()"/>
        <input type="button" value="mini.open" onclick="openAtPos()"/>
<br />
        xAlign: <select id="xAlign">
                    <option value="outleft">OutLeft</option>
                    <option value="left" >Left</option>               
                    <option value="center" >Center</option>
                    <option value="right">Right</option>
                    <option value="outright" selected>OutRight</option>
                </select>
        yAlign: <select id="yAlign">
                    <option value="above">Above</option>
                    <option value="top" selected>Top</option>
                    <option value="middle" >Middle</option>
                    <option value="bottom">Bottom</option>
                    <option value="below">Below</option>
                </select>
    <input id="d" type="button" value="showAtEl" onclick="showAtEl()" />        


<div id="win1" class="mini-window" title="Window" style="width:400px;height:250px;" 
    showMaxButton="true" showCollapseButton="true" showShadow="true"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
    <div property="toolbar" style="padding:5px;">
        <input type='button' value='Hide' onclick="hideWindow()" style='vertical-align:middle;'/>
    </div>
    <div property="footer" style="text-align:right;padding:5px;padding-right:15px;">
        <input type='button' value='Hide' onclick="hideWindow()" style='vertical-align:middle;'/>
    </div>

    1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />1<br />
</div>
<script type="text/javascript">
    mini.parse();
    function showAtPos() {
        var win = mini.get("win1");

        var x = document.getElementById("x").value;
        var y = document.getElementById("y").value;

        win.showAtPos(x, y);

    }
    function showAtEl() {
        var win = mini.get("win1");

        var atEl = document.getElementById("d");                

        var xAlign = document.getElementById("xAlign").value;
        var yAlign = document.getElementById("yAlign").value;

        win.showAtEl(atEl, {
            xAlign: xAlign,
            yAlign: yAlign
        });
    }
    function openAtPos() {
        var win = mini.open({
            title: 'mini.open弹出方式',
            url: bootPATH +'../demo/overview.html',
            showModal: false,
            width: 400,
            height: 250
        });

        var x = document.getElementById("x").value;
        var y = document.getElementById("y").value;
        win.showAtPos(x, y);
    }

    function hideWindow() {
        var win = mini.get("win1");
        win.hide();
    }
    showAtPos();

</script>

    <div class="description">
        <h3>Description</h3>
        <p>
        </p>
    </div>
</body>
</html>


