$(function(){
	
	//staa = $("#sta").val();
	
	//sta2 = window.parent.frames.document.getElementById("sta").value;
	
		
	/*$(document).keydown(function(e){

		if(e.keyCode==116){
			
			if(staa=="F" || sta2=="F"){
				return false;
			}
			
			return true;
		}	
		
	});*/
	
	var tpl = "<ul id='contextMenu' class='mini-contextmenu' >"+
			    "<li iconCls='icon-reload' onclick='reloadf();'>刷新</li>"+	
						
				/*"<li iconCls='icon-print' onclick='printf();' >打印</li>"+*/
				
				"<li iconCls='icon-find' onclick='mini.alert(window.location.href );' >属性</li>"+
				
				"<li iconCls='icon-undo' onclick='lout();' >注销</li>"+
				
			  "</ul>";
	
	$("body").append(tpl);
	

	
	
});

function lout(){

	var address= window.parent.document.getElementById("zhu");
	
	
	top.location=address;
}

function printf(){
	
	window.print();
}

function reloadf(){
	
	location.reload();
}

function banBackSpace(e){
    var ev = e || window.event;//获取event对象
    var obj = ev.target || ev.srcElement;//获取事件源
    var t = obj.type || obj.getAttribute('type');//获取事件源类型
    //获取作为判断条件的事件类型
    var vReadOnly = obj.readOnly;
    var vDisabled = obj.disabled;
    //处理undefined值情况
    vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
    vDisabled = (vDisabled == undefined) ? true : vDisabled;
    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
    //并且readOnly属性为true或disabled属性为true的，则退格键失效
    var flag1= ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")&& (vReadOnly==true || vDisabled==true);
    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
    var flag2= ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea" ;
    //判断
    if(flag2 || flag1)return false;
}
//禁止退格键 作用于Firefox、Opera
document.onkeypress=banBackSpace;
//禁止退格键 作用于IE、Chrome
document.onkeydown=banBackSpace;

$(document).ajaxComplete(function (evt, request, settings) {
    if (request.status == 403 ) {
    	 window.parent.noAuth();
    }/*else if(request.status!=200){
		window.parent.unmask();
		//mini.alert("<font color='red'>请求错误：服务器返回状态为"+request.status+"</br>请刷新重试,若还有问题请联系客服人员!</font>");
		mini.alert("<font color='red'>服务器繁忙：请刷新重试,若还有问题请联系客服人员!</br>由此给您带来的不便我们深表歉意,敬请谅解,感谢您的理解与支持!</font>");
	} */   	
    	
	
});
$(document).ajaxStop(function () {
	if (typeof(window.parent.unmask) == "function") {
		window.parent.unmask();
	} else window.parent.parent.unmask();
});

window.onload = function () {
    $(document).bind("contextmenu", function (e) {
            var menu = mini.get("contextMenu");
            menu.showAtPos(e.pageX, e.pageY);
            return false;
        });
};
