
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /> 
    <link href="${base}template/system/css/indexhome.css" rel="stylesheet" type="text/css" />
    <script src="${base}template/miniui/boot.js" type="text/javascript"></script>
    <title>首页</title>
    <style>
        /* 全局样式 */
        *{font-size:14px; color:#333333; font-family: "微软雅黑";}
        .contentk{float:left;width:100%;margin-top:12px;}
        .contentbox{margin:0 auto;}
        .newszw-bt{border-bottom:1px solid #dcdcdc;min-height: 57px;height: 100%;}
        .newszw-btt{font-size:16px;text-align:center;font-weight:bold;letter-spacing:1px;}
        .newszw-btsj{text-align:center;line-height:35px;}
        .newszw-con{overflow-x: hidden !important;width:100%;margin:0 auto;margin-top:9px;margin-bottom:90px;overflow: scroll;height: 180px}
        /* .newszw-con table{border-collapse:collapse;margin:20px;}
        .newszw-con table tr td{border:1px solid #ccc;} */
        .newszw-con img{width:100% !important;height:100% !important;}
        .newszw-con p{padding: 0 22px;color:#707070;line-height:30px;font-size:14px;font-family:"宋体";}
    </style>
</head>

<body>
    <div class="grxxleft">
        <div class="touxiang">
            <img src="${base}template/system/images/skinw8/tx.png"/><br />
            <sec:authentication property="name"/>
        </div>
        <!--touxiang end-->
        
        <div class="sxtop"> </div>
        <div class="sxbottom"></div>
        <div class="kjdhlm">
            <ul>
                <li><span><img src="${base}template/system/images/skinw8/grtp1.png"/></span><a onclick="onclickd();">账号管理</a></li>
                <li><span><img src="${base}template/system/images/skinw8/xs.png"/></span><a onclick="openWindow('../onlinework/form_check!list.action','在线预审')">在线预审</a></li>
                <li><span><img src="${base}template/system/images/skinw8/loc.png"/></span><a onclick="openWindow('../integral/integral_reckon!list.action','计入文明积分')">积分管理</a></li>
                <li><span><img src="${base}template/system/images/skinw8/zwzx.png"/></span><a onclick="openWindow('../govinfo/gov_manage!list.action','资讯管理')">政务资讯</a></li>
            </ul>
        </div>
    </div>
    <!--grxxleft end-->
    
    <div class="grxxright">
        <div>
            <div class="kcleft">
                <div class="kjdhtit3">
                    <span class="btwz" id="type1"></span>
                </div>
                <div class="contentk">
                    <div class="contentbox">
                        <div class="newszw-bt">
                            <div class="newszw-btt" id="title1"></div>
                            <div class="newszw-btsj">
                                <span id="createDate1"></span>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <span id="createMan1"></span>
                            </div>
                        </div>
                        <div class="newszw-con" id="txt1"></div>
                    </div>
                </div>
            </div>
            
            <div class="kcright">
                <div class="kjdhtit3">
                    <span class="btwz" id="type2"></span>
                </div>
                <div class="contentk">
                    <div class="contentbox">
                        <div class="newszw-bt">
                            <div class="newszw-btt" id="title2"></div>
                            <div class="newszw-btsj">
                                <span id="createDate2"></span>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <span id="createMan2"></span>
                            </div>
                        </div>
                        <div class="newszw-con" id="txt2"></div>
                    </div>
                </div>
            </div>
        </div>
        <!--kjdhtop end-->
        
        <div class="kjdhbottom">
            <div class="kcleft">
                <div class="kjdhtit3">
                    <span class="btwz" id="type3"></span>
                </div>
                <div class="contentk">
                    <div class="contentbox">
                        <div class="newszw-bt">
                            <div class="newszw-btt" id="title3"></div>
                            <div class="newszw-btsj">
                                <span id="createDate3"></span>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <span id="createMan3"></span>
                            </div>
                        </div>
                        <div class="newszw-con" id="txt3"></div>
                    </div>
                </div>
            </div>
            
            <div class="kcright">
                <div class="kjdhtit3">
                    <span class="btwz" id="type4"></span>
                </div>
                <div class="contentk">
                    <div class="contentbox">
                        <div class="newszw-bt">
                            <div class="newszw-btt" id="title4"></div>
                            <div class="newszw-btsj">
                                <span id="createDate4"></span>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <span id="createMan4"></span>
                            </div>
                        </div>
                        <div class="newszw-con" id="txt4"></div>
                    </div>
                </div>
            </div>
        </div>
        <!--kjdhbottom end--> 
        
    </div>
    <!--grxxright end-->
    
    <script type="text/javascript">
        mini.parse();
        
        //加载四条最新的政务管理数据
        $.ajax({
            dataType: "json",
            url : "../../govinfo/gov_info!getFourData.action",
            type:"GET",  
            success : function(respons) {
                for(var i=1;i<respons.data.length+1;i++){
                    document.getElementById("type"+i).innerHTML = respons.data[i-1].type;//分类
                    document.getElementById("title"+i).innerHTML = respons.data[i-1].title;//标题
                    document.getElementById("createDate"+i).innerHTML = "发布时间："+respons.data[i-1].createDate;//发布时间
                    document.getElementById("createMan"+i).innerHTML = "发布人："+respons.data[i-1].createMan;//发布人
                    document.getElementById("txt"+i).innerHTML = respons.data[i-1].txt;//内容
                }
            }
        });
        
        //账号管理
        function onclickd(){
            mini.open({
                url: "../system/admin!edit2.action",
                title: "编辑用户",
                width:450,
                height: 340,
                allowDrag:false,
                allowResize: false, 
                onload: function () {
                },
                ondestroy: function (action) {
                }
            });
        }
        
        //在线预审、积分管理、政务资讯
        function openWindow(urlname,name) {
            var id = "add$" + name;
            var tab = tabs.getTab(id);
            if (!tab) {
                tab = {};
                tab.name = id;
                tab.title = name;
                tab.showCloseButton = true;
                tab.url = urlname;
                tabs.addTab(tab);
            }
            tabs.activeTab(tab);
        }
        
        var tabs = window.parent.mini.get("mainTabs");
        function fpdh(url,title,id){
            if(title!=null){
                var tab = tabs.getTab(id);
                if (!tab) {
                    tab = {};
                    tab.name = id;
                    tab.title = title;
                    tab.showCloseButton = true;
                    tab.url = url;
                    tabs.addTab(tab);
                }
                tabs.activeTab(tab);
            }
        }
    </script>
</body>
</html>
