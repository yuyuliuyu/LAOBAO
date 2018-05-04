<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>  

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>jQuery组织结构图表插件OrgChart - 站长素材</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/zzsc-demo.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/jquery.orgchart.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>  

<div id="chart-container"></div> 
<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/html2canvas.js"></script>
<script type="text/javascript" src="js/jquery.orgchart.js"></script> 

<script type="text/javascript">
'use strict';
$.ajax({
    url: "../../train/depart!list.action", 
    success: function (text) {   
        var datasource = eval('(' + text + ')'); 
        $('#chart-container').orgchart({
       	  'data' : datasource, 
       	  'depth': 2,
       	  'nodeTitle': 'name',
       	  'nodeContent': 'title'
       	});
    } 
});  
</script>
 </body>
</html>