<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("base", basePath);
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>CheckBoxTree</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="${base}template/miniui/boot.js" type="text/javascript"></script>
     <script src="${base}template/common/js/jquery-1.6.2.min.js" type="text/javascript"></script>
    <link href="${base}template/miniui/css/portal.css" rel="stylesheet" type="text/css" />
	<script src="${base}template/miniui/js/Portal.js" type="text/javascript"></script>	
    

</head>
<body>
    <h1>开始桌面</h1>
    <input id="Button1" type="button" value="button1" />
    <input id="Button2" type="button" value="button2" />
    
</body>

<script type="text/javascript">

    var portal = new mini.ux.Portal();
    portal.set({
        style: "width: 100%;height:400px",
        columns: [250, "100%", 250]
    });
    portal.render(document.body);

    //panel
    portal.setPanels([
        { column: 0, id: "p1", title: "panel1", showCloseButton: true, body: "#Button1" },
        { column: 0, id: "p2", title: "panel2", showCollapseButton: true, height: 150 },

        { column: 1, id: "p3", title: "panel3", showCloseButton: true },
        { column: 1, id: "p4", title: "panel4", showCollapseButton: true },

        { column: 2, id: "p5", title: "panel5", showCloseButton: true },
        { column: 2, id: "p6", title: "panel6", showCollapseButton: true }
    ]);
    
    var bodyEl = portal.getPanelBodyEl("p2");
    bodyEl.appendChild(document.getElementById("Button2"));

    //获取配置的panels信息
    var panels = portal.getPanels();
    //alert(panels.length);


</script>
</html>