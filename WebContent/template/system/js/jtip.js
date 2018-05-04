/*
 * JTip
 * By Cody Lindley (http://www.codylindley.com)
 * Under an Attribution, Share Alike License
 * JTip is built on top of the very light weight jquery library.
 */

//on page load (as soon as its ready) call JT_init
$(document).ready(JT_init);

function JT_init(){
	       $("a.jTip")
		   .hover(function(){JT_show(this.href,this.id,this.name)},function(){$('#JT').remove()})
           .click(function(){return false});	   
}

function JT_show(url,linkId,title){
	if(title == false)title="&nbsp;";
	var de = document.documentElement;
	var w = self.innerWidth || (de&&de.clientWidth) || document.body.clientWidth;
	var hasArea = w - getAbsoluteLeft(linkId);
	var clickElementy = getAbsoluteTop(linkId) - 3; //set y position
	
	var queryString = url.replace(/^[^\?]+\??/,'');
	var params = parseQuery( queryString );
	if(params['width'] === undefined){params['width'] = 250};
	if(params['link'] !== undefined){
	$('#' + linkId).bind('click',function(){window.location = params['link']});
	$('#' + linkId).css('cursor','pointer');
	}
	
	
	if(hasArea>((params['width']*1)+75)){
		$("body").append("<div id='JT' style='width:"+params['width']*1+"px'><div id='JT_arrow_left'></div><div id='JT_close_left'>详细提示</div><div id='JT_copy'><div class='JT_loader'><div></div></div>");//right side
		var arrowOffset = getElementWidth(linkId) + 11;
		var clickElementx = getAbsoluteLeft(linkId) + arrowOffset; //set x position
	}else{
		$("body").append("<div id='JT' style='width:"+params['width']*1+"px'><div id='JT_arrow_right' style='left:"+((params['width']*1)+1)+"px'></div><div id='JT_close_right'>详细提示</div><div id='JT_copy'><div class='JT_loader'><div></div></div>");//left side
		var clickElementx = getAbsoluteLeft(linkId) - ((params['width']*1) + 15); //set x position
	}
	
	$('#JT').css({left: clickElementx+"px", top: clickElementy+"px"});
	$('#JT').show();
	
	tshi=$('#'+title).html();
	$('#JT_copy').html(tshi);

}

function getElementWidth(objectId) {
	x = document.getElementById(objectId);
	return x.offsetWidth;
}

function getAbsoluteLeft(objectId) {
	// Get an object left position from the upper left viewport corner
	o = document.getElementById(objectId)
	oLeft = o.offsetLeft            // Get left position from the parent object
	while(o.offsetParent!=null) {   // Parse the parent hierarchy up to the document element
		oParent = o.offsetParent    // Get parent object reference
		oLeft += oParent.offsetLeft // Add parent left position
		o = oParent
	}
	return oLeft
}

function getAbsoluteTop(objectId) {
	// Get an object top position from the upper left viewport corner
	o = document.getElementById(objectId)
	oTop = o.offsetTop            // Get top position from the parent object
	while(o.offsetParent!=null) { // Parse the parent hierarchy up to the document element
		oParent = o.offsetParent  // Get parent object reference
		oTop += oParent.offsetTop // Add parent top position
		o = oParent
	}
	return oTop
}

function parseQuery ( query ) {
   var Params = new Object ();
   if ( ! query ) return Params; // return empty object
   var Pairs = query.split(/[;&]/);
   for ( var i = 0; i < Pairs.length; i++ ) {
      var KeyVal = Pairs[i].split('=');
      if ( ! KeyVal || KeyVal.length != 2 ) continue;
      var key = unescape( KeyVal[0] );
      var val = unescape( KeyVal[1] );
      val = val.replace(/\+/g, ' ');
      Params[key] = val;
   }
   return Params;
}

function blockEvents(evt) {
              if(evt.target){
              evt.preventDefault();
              }else{
              evt.returnValue = false;
              }
}

function htmlClickon(data) {
    var type = false;
   
    $("#htmlContent").append("<div class='tishi'><ul id='htmlContent2'></ul></div>"); 
   
	  for(var i=0; i<data.length; i++)
	  {
	  	if(data[i].line==0){
	  		if(data[i].type=="错误"){
				  		$("#htmlContent2").append("<li>&nbsp&nbsp"+(i+1)+".<span>存在注意项</span>  <span class='tkzt'>"+data[i].type+"</span> <span><a href='' name='as"+i+"' id='yahooCopy"+i+"' class='jTip'>点击查看详情</a></span><span id='as"+i+"' style='display:none'>"+data[i].content+"</span></li>"); 
					  
			  		type = true;
			  }else{
			  		$("#htmlContent2").append("<li>&nbsp&nbsp"+(i+1)+".<span>存在注意项</span>  <span class='tkztl'>"+data[i].type+"</span> <span><a href='' name='as"+i+"' id='yahooCopy"+i+"' class='jTip'>点击查看详情</a></span><span id='as"+i+"' style='display:none'>"+data[i].content+"</span></li>"); 
				  
			  }
	  	}else{
	  		if(data[i].type=="错误"){
				  if(i<9){
				  		$("#htmlContent2").append("<li>&nbsp&nbsp"+(i+1)+".<span>第&nbsp"+data[i].line+"&nbsp条数据</span>  <span class='tkzt'>"+data[i].type+"</span> <span><a href='' name='as"+i+"' id='yahooCopy"+i+"' class='jTip'>点击查看详情</a></span><span id='as"+i+"' style='display:none'>"+data[i].content+"</span></li>"); 
					  
				  	}else{
				  		$("#htmlContent2").append("<li>"+(i+1)+".<span>第"+data[i].line+"条数据</span>  <span class='tkzt'>"+data[i].type+"</span> <span><a href='' name='as"+i+"' id='yahooCopy"+i+"' class='jTip'>点击查看详情</a></span><span id='as"+i+"' style='display:none'>"+data[i].content+"</span></li>"); 
					  }
			  		type = true;
			  }else{
			  	if(i<9){
			  		$("#htmlContent2").append("<li>&nbsp&nbsp"+(i+1)+".<span>第&nbsp"+data[i].line+"&nbsp条数据</span>  <span class='tkztl'>"+data[i].type+"</span> <span><a href='' name='as"+i+"' id='yahooCopy"+i+"' class='jTip'>点击查看详情</a></span><span id='as"+i+"' style='display:none'>"+data[i].content+"</span></li>"); 
				  
			  	}else{
			  		$("#htmlContent2").append("<li>"+(i+1)+".<span>第"+data[i].line+"条数据</span>  <span class='tkztl'>"+data[i].type+"</span> <span><a href='' name='as"+i+"' id='yahooCopy"+i+"' class='jTip'>点击查看详情</a></span><span id='as"+i+"' style='display:none'>"+data[i].content+"</span></li>"); 
				  }
			  }
	  	}
		  
}

JT_init();
mini.showMessageBox({
 width: 290,
 maxHeight:900,
 title: "提示信息",
 buttons: ["ok", "cancel"],
 message: "提示信息",
 html: htmlContent,
 showModal: Boolean,
 callback: function (action) {
if (action == "ok") {
        if(type){
				  mini.showMessageBox({
					               title: "提醒",
					               width: 250,
					               iconCls: "mini-messagebox-warning",
					               buttons: ["ok"],
					               message: "存在错误信息，请修改后再进行此操作",
					               callback: function (action) {
					                    //document.location.reload();
					            	   $("body").append("<div id='htmlContent'></div>");
					                  }
					             });
			   }else{
				   $("body").append("<div id='htmlContent'></div>");
				  doback();
		 	}
     }else{
     	//document.location.reload();
    	 $("body").append("<div id='htmlContent'></div>");
     } 
 }
});

}

function htmlClick(data,row) {
    var type = false;
  
   $("#htmlContent").append("<div class='tishi'><ul id='htmlContent2'></ul></div>"); 
	  for(var i=0; i<data.length; i++)
	  {
		  	if(data[i].line==0){
		  		if(data[i].type=="错误"){
					  		$("#htmlContent2").append("<li>&nbsp&nbsp"+(i+1)+".<span>存在注意项</span>  <span class='tkzt'>"+data[i].type+"</span> <span><a href='' name='as"+i+"' id='yahooCopy"+i+"' class='jTip'>点击查看详情</a></span><span id='as"+i+"' style='display:none'>"+data[i].content+"</span></li>"); 
						  
				  		type = true;
				  }else{
				  		$("#htmlContent2").append("<li>&nbsp&nbsp"+(i+1)+".<span>存在注意项</span>  <span class='tkztl'>"+data[i].type+"</span> <span><a href='' name='as"+i+"' id='yahooCopy"+i+"' class='jTip'>点击查看详情</a></span><span id='as"+i+"' style='display:none'>"+data[i].content+"</span></li>"); 
					  
				  }
		  	}else{
			  if(data[i].type=="错误"){
				  if(i<9){
				  		$("#htmlContent2").append("<li>&nbsp&nbsp"+(i+1)+".<span>第&nbsp"+data[i].line+"&nbsp条数据</span>  <span class='tkzt'>"+data[i].type+"</span> <span><a href='' name='as"+i+"' id='yahooCopy"+i+"' class='jTip'>点击查看详情</a></span><span id='as"+i+"' style='display:none'>"+data[i].content+"</span></li>"); 
					  
				  	}else{
				  		$("#htmlContent2").append("<li>"+(i+1)+".<span>第"+data[i].line+"条数据</span>  <span class='tkzt'>"+data[i].type+"</span> <span><a href='' name='as"+i+"' id='yahooCopy"+i+"' class='jTip'>点击查看详情</a></span><span id='as"+i+"' style='display:none'>"+data[i].content+"</span></li>"); 
					  }
			  		type = true;
			  }else{
			  	if(i<9){
			  		$("#htmlContent2").append("<li>&nbsp&nbsp"+(i+1)+".<span>第&nbsp"+data[i].line+"&nbsp条数据</span>  <span class='tkztl'>"+data[i].type+"</span> <span><a href='' name='as"+i+"' id='yahooCopy"+i+"' class='jTip'>点击查看详情</a></span><span id='as"+i+"' style='display:none'>"+data[i].content+"</span></li>"); 
				  
			  	}else{
			  		$("#htmlContent2").append("<li>"+(i+1)+".<span>第"+data[i].line+"条数据</span>  <span class='tkztl'>"+data[i].type+"</span> <span><a href='' name='as"+i+"' id='yahooCopy"+i+"' class='jTip'>点击查看详情</a></span><span id='as"+i+"' style='display:none'>"+data[i].content+"</span></li>"); 
				  }
			  }
		  	}
}

JT_init();
mini.showMessageBox({
 width: 290,
 maxHeight:900,
 title: "提示信息",
 buttons: ["ok", "cancel"],
 message: "提示信息",
 html: htmlContent,
 showModal: false,
 callback: function (action) {
if (action == "ok") {
        if(type){
				  mini.showMessageBox({
					               title: "提醒",
					               width: 250,
					               iconCls: "mini-messagebox-warning",
					               buttons: ["ok"],
					               message: "存在错误信息，请修改后再进行此操作",
					               callback: function (action) {
					                    //document.location.reload();
					            	   $("body").append("<div id='htmlContent'></div>");
					                  }
					             });
			   }else{
				   $("body").append("<div id='htmlContent'></div>");
				  doback(row);
		 	}
     }else{
     	//document.location.reload();
    	 $("body").append("<div id='htmlContent'></div>");
     } 
 }
});
}