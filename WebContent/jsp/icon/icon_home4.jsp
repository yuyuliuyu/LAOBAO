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
<link href="${base}template/system/css/indexhome.css" rel="stylesheet" type="text/css" />
<script src="${base}template/miniui/boot.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}template/system/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}template/system/js/ImgSlide.js"></script>
    <script type="text/javascript" src="${base}template/system/js/imageBox.js"></script>
    <style type="text/css">
        ul {
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .myul li {
            width: 150px;
            height: 150px;
        }

    </style>
    <script type="text/javascript">
        $(function () {
            $('.myul').ImgSlide({
                showImages: 6
            });
            $('.img_b').myImageBox();
        });
        </script>
<title>管理中心</title>
</head>

<body>
<div class="grxxleft">
  <div class="touxiang"> <img src="${base}template/system/images/skinw8/tx.png"/><br />
    <sec:authentication property="name"/> </div>
  <!--touxiang end-->
  <div class="sxtop"> </div>
  <div class="sxbottom"></div>
  <div class="kjdhlm">
    <ul>
      <li><span><img src="${base}template/system/images/skinw8/grtp1.png"/></span><a onclick="onclickd();">账号管理</a></li>
      <li><span><img src="${base}template/system/images/skinw8/xs.png"/></span><a onclick="openWindow('xiaoshou','销售')">销售管理</a></li>
      <li><span><img src="${base}template/system/images/skinw8/loc.png"/></span><a onclick="openWindow('cangchu','仓储')">仓储管理</a></li>
      <li><span><img src="${base}template/system/images/skinw8/cg.png"/></span><a onclick="openWindow('caigou','采购')">采购管理</a></li>
    </ul>
  </div>
</div>
<!--grxxleft end-->

<div class="grxxright">
  <div class="kjdhtop">
    <div class="kjdhtit">快捷导航</div>
    
    	 <div style="  float:left;margin-right: 5px; height: 150px; line-height: 150px;background-color: #EEE">
            <span style="cursor: pointer;width: 35px;height: 150px;line-height: 150px; display: inline-block"
                  class="leftBtn">《《</span></div>
        <ul class="myul">
            <li>
                <span style="position: absolute;bottom: 5px;width: 100%;text-align: center;">111111</span>
                <a  title="生产" href="#">
								<img src="${base}template/system/images/skinw8/shengchan.png" alt="生产" />
				</a>
            </li>
            <li>
                <span style="position: absolute;bottom: 5px;width: 100%;text-align: center;">22222</span>
                <a  title="生产" href="#">
								<img src="${base}template/system/images/skinw8/shengchan.png" alt="生产" />
				</a>
            </li>
            <li>
                <span style="position: absolute;bottom: 5px;width: 100%;text-align: center;">33333</span>
                <a  title="生产" href="#">
								<img src="${base}template/system/images/skinw8/shengchan.png" alt="生产" />
				</a>
            </li>
            <li>
                <span style="position: absolute;bottom: 5px;width: 100%;text-align: center;">44444</span>
                <a  title="生产" href="#">
								<img src="${base}template/system/images/skinw8/shengchan.png" alt="生产" />
				</a>
            </li>
            <li>
                <span style="position: absolute;bottom: 5px;width: 100%;text-align: center;">55555</span>
                <a  title="生产" href="#">
								<img src="${base}template/system/images/skinw8/shengchan.png" alt="生产" />
				</a>
            </li>
            <li>
                <span style="position: absolute;bottom: 5px;width: 100%;text-align: center;">66666</span>
                <a  title="生产" href="#">
								<img src="${base}template/system/images/skinw8/shengchan.png" alt="生产" />
				</a>
            </li>
            <li>
                <span style="position: absolute;bottom: 5px;width: 100%;text-align: center;">77777</span>
                <a  title="生产" href="#">
								<img src="${base}template/system/images/skinw8/shengchan.png" alt="生产" />
				</a>
            </li>
            <li>
                <span style="position: absolute;bottom: 5px;width: 100%;text-align: center;">88888</span>
                <a  title="生产" href="#">
								<img src="${base}template/system/images/skinw8/shengchan.png" alt="生产" />
				</a>
            </li>
            <li>
                <span style="position: absolute;bottom: 5px;width: 100%;text-align: center;">99999</span>
                <a  title="仓储" href="#"> <img src="${base}template/system/images/skinw8/cangchu.png" alt="仓储"/><br/>
             </a>
            </li>
        </ul>
        <div style=" float:right; margin-left: 5px;height: 150px; line-height: 150px; background-color: #EEE">
            <span style="cursor: pointer;width: 35px;height: 150px;line-height: 150px; display: inline-block"
                  class="rightBtn">》》</span>
        </div>
    
    <div id="content">
    </div>


  </div>
  <!--kjdhtop end-->
  <div class="kjdhbottom">
    <div class="kcleft">
      <div class="kjdhtit3"><span class="btwz">待办事项</span><span class="more"><a onclick="fpdh('../remind/remind_schedule!all.action','待办事项','dbtit')">more>> </a> </span></div>
      <table class="tabindex" width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
         <th width="16%" bgcolor="#f8f8f8" scope="col"><div align="center">模块名</div></th>
          <th width="12%" bgcolor="#f8f8f8" scope="col"><div  align="center">操作类型</div></th>
          <th width="14%" bgcolor="#f8f8f8" scope="col"><div  align="center">单据编号</div></th>
          <th width="9%" bgcolor="#f8f8f8" scope="col"><div  align="center">创建人</div></th>
          <th width="9%" bgcolor="#f8f8f8" scope="col"><div  align="center">审核人</div></th>
        </tr>
        <tr><td id="mokuai1" bgcolor="#FFFFFF" style="text-align:center;text-decoration: none">&nbsp;</td>
          <td id="type1" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="trxCode1" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="createMan1" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="operater1" class="datacol" bgcolor="#FFFFFF" > </td>
        </tr>
        <tr><td id="mokuai2" bgcolor="#FFFEE5" style="text-align:center;text-decoration: none">&nbsp;</td>
          <td id="type2" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="trxCode2" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="createMan2" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="operater2" class="datacol" bgcolor="#FFFEE5" > </td>
        </tr>
        <tr>
          <td id="mokuai3" bgcolor="#FFFFFF" style="text-align:center;text-decoration: none">&nbsp;</td>
          <td id="type3" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="trxCode3" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="createMan3" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="operater3" class="datacol" bgcolor="#FFFFFF" > </td>
        </tr>
        <tr>
         <td id="mokuai4" bgcolor="#FFFEE5" style="text-align:center;text-decoration: none">&nbsp;</td>
          <td id="type4" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="trxCode4" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="createMan4" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="operater4" class="datacol" bgcolor="#FFFEE5" > </td>
        </tr>
        <tr>
        <td id="mokuai5" bgcolor="#FFFFFF" style="text-align:center;text-decoration: none">&nbsp;</td>
          <td id="type5" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="trxCode5" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="createMan5" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="operater5" class="datacol" bgcolor="#FFFFFF" > </td>
        </tr>
        <tr>
          <td id="mokuai6" bgcolor="#FFFEE5" style="text-align:center;text-decoration: none">&nbsp;</td>
          <td id="type6" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="trxCode6" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="createMan6" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="operater6" class="datacol" bgcolor="#FFFEE5" > </td>
        </tr>
       
        <tr>
         <td id="mokuai7" bgcolor="#FFFFFF" style="text-align:center;text-decoration: none">&nbsp;</td>
          <td  id="type7"   class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="trxCode7" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="createMan7" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="operater7" class="datacol" bgcolor="#FFFFFF" > </td>
        </tr>
      </table>
    </div>
    <div class="kcright">
      <div class="kjdhtit3"><span class="btwz">库存预警</span> <span class="more"><a onclick="fpdh('../remind/remind_schedule!list.action','库存预警','loctit')">more>> </a> </span></div>
      <table class="tabindex" width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
        <th width="22%" bgcolor="#f8f8f8" scope="col"><div  align="center">物料</div></th>
          <th width="14%" bgcolor="#f8f8f8" scope="col"><div  align="center">仓库</div></th>
          <th width="10%" bgcolor="#f8f8f8" scope="col"><div  align="center">仓库数量</div></th>
          <th width="10%" bgcolor="#f8f8f8" scope="col"><div  align="center">最低数量</div></th>
          <th width="10%" bgcolor="#f8f8f8" scope="col"><div  align="center">最高数量</div></th>
          <th width="12%" bgcolor="#f8f8f8" scope="col" style="border-right:none"><div align="center">状态</div></th>
        </tr>
        <tr>
		  <td id="wuliao1" class="datacol" bgcolor="#FFFFFF" >&nbsp;</td>
          <td id="cangku1" class="datacol" bgcolor="#FFFFFF" ></td>
          <td id="shuliang1" class="datacol" bgcolor="#FFFFFF" ></td>
          <td id="zuixiao1" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="zuida1" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="zhangtai1" bgcolor="#FFFFFF" style="border-right:none; "  class="yccol"></td>
        </tr>
        <tr>
         <td id="wuliao2" class="datacol" bgcolor="#FFFEE5" >&nbsp;</td>
          <td id="cangku2" class="datacol" bgcolor="#FFFEE5" ></td>
          <td id="shuliang2" class="datacol" bgcolor="#FFFEE5" ></td>
          <td id="zuixiao2" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="zuida2" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="zhangtai2" bgcolor="#FFFEE5" style="border-right:none; "  class="yccol"></td>
        </tr>
        <tr>
         <td id="wuliao3" class="datacol" bgcolor="#FFFFFF" >&nbsp;</td>
          <td id="cangku3" class="datacol" bgcolor="#FFFFFF" ></td>
          <td id="shuliang3" class="datacol" bgcolor="#FFFFFF" ></td>
          <td id="zuixiao3" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="zuida3" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="zhangtai3" bgcolor="#FFFFFF" style="border-right:none; "  class="yccol"></td>
        </tr>
        <tr>
         <td id="wuliao4" class="datacol" bgcolor="#FFFEE5" >&nbsp;</td>
          <td id="cangku4" class="datacol" bgcolor="#FFFEE5" ></td>
          <td id="shuliang4" class="datacol" bgcolor="#FFFEE5" ></td>
          <td id="zuixiao4" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="zuida4" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="zhangtai4" bgcolor="#FFFEE5" style="border-right:none; "  class="yccol"></td>
        </tr>
        <tr>
          <td id="wuliao5" class="datacol" bgcolor="#FFFFFF" >&nbsp;</td>
          <td id="cangku5" class="datacol" bgcolor="#FFFFFF" ></td>
          <td id="shuliang5" class="datacol" bgcolor="#FFFFFF" ></td>
          <td id="zuixiao5" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="zuida5" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="zhangtai5" bgcolor="#FFFFFF" style="border-right:none; "  class="yccol"></td>
        </tr>
        <tr>
         <td id="wuliao6" class="datacol" bgcolor="#FFFEE5" >&nbsp;</td>
          <td id="cangku6" class="datacol" bgcolor="#FFFEE5" ></td>
          <td id="shuliang6" class="datacol" bgcolor="#FFFEE5" ></td>
          <td id="zuixiao6" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="zuida6" class="datacol" bgcolor="#FFFEE5" > </td>
          <td id="zhangtai6" bgcolor="#FFFEE5" style="border-right:none; "  class="yccol"></td>
        </tr>
        <tr>
        <td id="wuliao7" class="datacol" bgcolor="#FFFFFF" >&nbsp;</td>
          <td id="cangku7" class="datacol" bgcolor="#FFFFFF" ></td>
          <td id="shuliang7" class="datacol" bgcolor="#FFFFFF" ></td>
          <td id="zuixiao7" class="datacol" bgcolor="#FFFFFF" > </td>
          <td id="zuida7" class="datacol" bgcolor="#FFFFFF" > </td>
       
          <td id="zhangtai7" bgcolor="#FFFFFF" style="border-right:none; "  class="yccol"></td>
        </tr>
      </table>
    </div>
  </div>
  <!--kjdhbottom end--> 
  
</div>
<!--grxxright end-->
<script type="text/javascript">
			mini.parse();
			$.ajax({
				dataType: "json",
			    url : "../../system/backend_role!treeshow1.action",
					type:"post",  
			  
			    success : function(respons) {
			        	var ss=8;
			        	if(ss>respons.data.length+1){
			        	ss=respons.data.length+1;
			        	}
			        	for(var i=1;i<ss;i++){
				        	document.getElementById("mokuai"+i).innerHTML = respons.data[i-1].mokuai;
				        	document.getElementById("type"+i).innerHTML = respons.data[i-1].type;
				        	document.getElementById("trxCode"+i).innerHTML = respons.data[i-1].trxCode;
				            document.getElementById("createMan"+i).innerHTML = respons.data[i-1].createMan;
				            document.getElementById("operater"+i).innerHTML = respons.data[i-1].operater==null?"":respons.data[i-1].operater;
			        	}
			    
			    },
				error:
						function(XMLHttpRequest, textStatus, errorThrown){
					
					}
			});
			
			$.ajax({
				dataType: "json",
			    url : "../../locmgt/loc_stk_loc!search.action",
					type:"GET",  
			  
			    success : function(respons) {
			        	var ss=8;
			        	if(ss>respons.data.length+1){
			        	ss=respons.data.length+1;
			        	}
			        	for(var i=1;i<ss;i++){
				        	document.getElementById("wuliao"+i).innerHTML = respons.data[i-1].itemCode;
				        	document.getElementById("cangku"+i).innerHTML = respons.data[i-1].ckdh;
				        	document.getElementById("shuliang"+i).innerHTML = respons.data[i-1].qty;
			               	document.getElementById("zuixiao"+i).innerHTML = respons.data[i-1].minQty;
			               	document.getElementById("zuida"+i).innerHTML = respons.data[i-1].maxQty;
			               	document.getElementById("zhangtai"+i).innerHTML = respons.data[i-1].state;
			        	}
			    
			    }
			});
       
        
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

 function openWindow(urlname,name) {
 
 var id = "add$"+urlname;
        var tab = tabs.getTab(id);
        if (!tab) {
            tab = {};
            tab.name = id;
            tab.title = name;
            tab.showCloseButton = true;
            tab.url = "${base}/jsp/icon/icon_"+urlname+".jsp";

            tabs.addTab(tab);
        }
        tabs.activeTab(tab);
    } 
	function onclickd(){
		
		        mini.open({
		            url: "../system/admin!edit2.action?",
		            title: "编辑用户", width:450, height: 310,
		            allowDrag:false,
		            allowResize: false, 
		            onload: function () {
		            },
		            ondestroy: function (action) {
		            }
		        });
			}  
			
		function txdh(url,name){
			 var id ="tab$"+name;
			 var tab = tabs.getTab(id);
			   if (!tab) {
    	            tab = {};
    	            tab.name = id;
    	            tab.title = name;
    	            tab.showCloseButton = true;
    	           
    	            tab.url = url;
    	            tabs.addTab(tab);
    	        }else{
    	        	tabs.removeTab(tab);
    	            tab = {};
    	            tab.name = id;
    	            tab.title = name;
    	            tab.showCloseButton = true;
    	           
    	            tab.url = url;
    	            tabs.addTab(tab);
    	        }
            tabs.activeTab(tab);
    		
			}    
    </script>
</body>
</html>
