jQuery(function() {
	var protocol = document.location.protocol == 'https:' ? 'https://' : 'http://';
	var base = protocol + window.location.host;
    var $ = jQuery,
        $list = $('#thelist'),//存放文件信息
        $btn = $('#ctlBtn'),//上传按钮
        state = 'pending',
        uploader;

    uploader = WebUploader.create({

        // 不压缩image
        resize: false,

        // swf文件路径
        swf: base +'/LingUI/js/webuploader/Uploader.swf',

        // 文件接收服务端。
        server: '/hr/resume!upload.action',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',
        
        //指定接受哪些类型的文件
        //没有任何内容的文档不会被添加入队列
        accept:{
			title: 'word文档',
			extensions: 'docx,doc',
			mimeTypes: 'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document'
        }
    });

    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        $list.append( '<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
        '</div>' );
    });

    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
              '<div class="progress-bar" role="progressbar" style="width: 0%">' +
              '</div>' +
            '</div>').appendTo( $li ).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css( 'width', percentage * 100 + '%' );
    });

    uploader.on( 'uploadSuccess', function( file , response) {
        //$( '#'+file.id ).find('p.state').text('已上传');
    	$( '#'+file.id ).find('p.state').text(response.msg);
        var str="{\"id\":\""+response.id+"\",\"location\":\""+response.location+"\",\"msg\":\""+response.msg
        	+"\"},";
        $("#fileId").val($("#fileId").val() + str);
    });

    uploader.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('p.state').text('上传出错');
    });

    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });

    uploader.on( 'all', function( type ) {
        if ( type === 'startUpload' ) {
            state = 'uploading';
        } else if ( type === 'stopUpload' ) {
            state = 'paused';
        } else if ( type === 'uploadFinished' ) {
            state = 'done';
            //alert($("#fileId").val());
        }
        if ( state === 'uploading' ) {
            $btn.text('暂停上传');
        } else {
            $btn.text('开始上传');
        }
        
    });
    
    /**传递部门、岗位、简历来源参数*/
    uploader.on('uploadBeforeSend',function(obj, data, headers){
    	var depId=$('#depId',window.parent.document).val();
    	var jobId=$("#jobName",window.parent.document).val();
    	data.depId=depId;
    	data.jobId=jobId;
    	data.resumeFrom=$('#resumeFrom',window.parent.document).val();
    });
    
    $btn.on( 'click', function() {
    	var depId=$('#depId',window.parent.document).val();
    	var jobId=$("#jobName",window.parent.document).val();
        if ( state === 'uploading' ) {
            uploader.stop();
        }else if(depId==""){
        	lui.info("请选择部门");
        	uploader.stop();
        }else if(jobId=="-1" || jobId==""){
        	lui.info("请选择岗位");
        	uploader.stop();
        } else {
            uploader.upload();
        }
    });
    function da(file){
    	//alert(file.id);
    	uploader.removeFile( file );
    }
});