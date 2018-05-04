<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("base", basePath);
    List map = (List)request.getAttribute("mapD");
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>打印页面设计</title>
    <style type="text/css">
    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }
    </style>
    <script src="${base}/template/miniui/boot.js" type="text/javascript"></script>
    <!--引入皮肤样式-->
    <link href="${base}/template/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div style="LINE-HEIGHT: 25px" align=center>
        <strong>
            <font color="#0000FF" size="5px">转仓单</font>
        </strong>
    </div>
    
    <div>
        ${mainHtml}
    </div>
    
    <div>
        <table border=1 cellSpacing=0 cellPadding=6 width="100%" style="border-collapse:collapse" bordercolor="#333333">
            <thead>
                <tr>
                    ${html}
                </tr>
            </thead>
            <tbody>
            <%
            if(map.size()>0){
                for(int i=0;i<map.size();i++){
            %>
                <tr>
                    <%
                    Map map1 = (Map)map.get(i);
                    Iterator iter = map1.keySet().iterator(); 
                    while (iter.hasNext()) {
                        Object key = iter.next(); 
                        if(map1.get(key)!=null){
                    %>
                            <td align="center">
                            <%=map1.get(key)%>
                            </td>
                        <%}else{ %>
                            <td>
                            </td>
                        <%}
                     }%>
                </tr>
              <%}
            }%>
            </tbody>
            
<%--             <tfoot>
                ${footHtml}
            </tfoot> --%>
            
        </table>
   </div>
   
    <div>
        ${footHtml}
    </div>
    
</body>
</html>