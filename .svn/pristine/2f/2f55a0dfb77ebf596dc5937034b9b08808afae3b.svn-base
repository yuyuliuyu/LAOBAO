var uploader;
jQuery(function() {
	var protocol = document.location.protocol == 'https:' ? 'https://' : 'http://';
	var base = protocol + window.location.host;
	    var $ = jQuery,
	        $list = $('#fileList'),
	        // 优化retina, 在retina下这个值是2
	        ratio = window.devicePixelRatio || 1,

	        // 缩略图大小
	        thumbnailWidth = 150 * ratio,
	        thumbnailHeight = 150 * ratio,

	        // Web Uploader实例
	        uploader;

	    // 初始化Web Uploader
	    uploader = WebUploader.create({

	        // 自动上传。
	        auto: true,

	        // swf文件路径
	        swf: base+'/hcm/template/plugin/webuploader/Uploader.swf',

	        // 文件接收服务端。
	        //server: '../upload!upLoadFile.action',
	        server : '../upload!upLoadFile.action?path='+ path,

	        // 选择文件的按钮。可选。
	        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	        pick: '#filePicker',

	        // 只允许选择文件，可选。
	        accept: {
	            title: 'Images',
	            extensions: 'gif,jpg,jpeg,bmp,png',
	            mimeTypes: 'image/jpg,image/jpeg,image/png,image/bmp,image/gif'
	        }
	    });
	    uploaders = uploader;
	    // 当有文件添加进来的时候
	    var i=0;
	    uploader.on( 'fileQueued', function( file ) {
	        var $li = $(
	                '<div id ="upimgone"><div id="' + file.id + '"  class="file-item thumbnail;" style="border:1px solid #ccc;">' +
	                    '<img name="upImgName">' +
	                    '<div class="info">' + file.name + '</div>' +
	                '</div>'
	                    +
	                    '<a href="javascript:;" onClick="deleUpImg(\''+file.id+'\')" '+
        				'style="width:50px;height:30px;line-height:120px;background:#2CA8F7;color:#FFF;'+
        				' text-decoration:none;padding:10px 30px;border-radius:10px;">删除</a></div>'
	                );
	       /**
	         
	    	 var a = $("img[name='upImg']");
	    	 if(a.size()>2){
	    		 mini.alert("最多上传3张图片");
	    		 return;
	    	 }
	         
	         var $li =$("<div id='upImg"+i+"' style='width:30%;margin-left:2%;text-align:center;float:left;height:auto;padding-bottom:70px;'>"+
	        				"<img  name='upImg'style='width:328px;height:278px;float:left;border:1px solid #ccc;'/>"+
	        				"<div style='width:100%;float:left;text-align:center;margin-top:15px;'>"+
	        				"<a href='javascript:;' onClick='deleImg("+i+")' "+
	        				"style='width:50px;height:30px;line-height:30px;background:#2CA8F7;color:#FFF;"+
	        				" text-decoration:none;padding:10px 30px;border-radius:10px;'>删除</a></div></div>");
	       **/
    	
	       var   $img = $li.find('img');
	        
	        $list.children().remove();
	        $list.append( $li );

	        // 创建缩略图
	        uploader.makeThumb( file, function( error, src ) {
	            if ( error ) {
	                $img.replaceWith('<span>不能预览</span>');
	                return;
	            }

	            $img.attr( 'src', src );
	        }, thumbnailWidth, thumbnailHeight );
	    });

	    // 文件上传过程中创建进度条实时显示。
	    uploader.on( 'uploadProgress', function( file, percentage ) {
	        var $li = $( '#'+file.id ),
	            $percent = $li.find('.progress span');

	        // 避免重复创建
	        if ( !$percent.length ) {
	            $percent = $('<p class="progress"><span></span></p>')
	                    .appendTo( $li )
	                    .find('span');
	        }

	        $percent.css( 'width', percentage * 100 + '%' );
	    });

	    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
	    uploader.on( 'uploadSuccess', function( file ,response) {
	        $( '#'+file.id ).addClass('upload-state-done');
	        if(response.data=="false"){
	        	alert("上传失败,请重新上传!");
	        }
	      //  var data = eval('('+response.newFileName+')');
	       // $("#imgpath").val(response.data)
	        //mini.get("imgpath").setValue(response.data);
	        mini.get("imgpath").setValue(response._raw);
	    });

	    // 文件上传失败，现实上传出错。
	    uploader.on( 'uploadError', function( file ) {
	        var $li = $( '#'+file.id ),
	            $error = $li.find('div.error');

	        // 避免重复创建
	        if ( !$error.length ) {
	            $error = $('<div class="error"></div>').appendTo( $li );
	        }

	        $error.text('上传失败');
	    });

	    // 完成上传完了，成功或者失败，先删除进度条。
	    uploader.on( 'uploadComplete', function( file ) {
	        $( '#'+file.id ).find('.progress').remove();
	    });
	    
	    uploader.on( 'all', function( type ) {
	        if ( type === 'uploadFinished' ) {
	        	//alert($("input[name=img]").val())
	        } 
	    });
	});

//删除预览
function deleUpImg(id) {
	uploaders.removeFile(id,true );
	$("#upimgone").children().remove()
	 $("#upimgone").remove();
	mini.get("imgpath").setValue("");
}

