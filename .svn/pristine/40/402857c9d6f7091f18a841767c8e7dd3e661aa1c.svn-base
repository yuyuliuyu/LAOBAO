var editor;
		var _editor;
		$(function() {
		     editor = UE.getEditor('myEditor');
		     
		     editor.ready(function () {
		    	 editor.setHide();
		     });
		    //重新实例化一个编辑器，防止在上面的editor编辑器中显示上传的图片或者文件
		    _editor = UE.getEditor('upload_ue');
		    _editor.ready(function () {
		        //设置编辑器不可用
		       // _editor.setDisabled();
		        //隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
		        _editor.setHide();
		        //侦听图片上传
		        var protocol = document.location.protocol == 'https:' ? 'https://' : 'http://';
				//var base = protocol + window.location.host;
				var base=$("#path").val();
		        _editor.addListener('beforeInsertImage', function (t, arg) {
		        	for(var i=0;i<arg.length;i++){
		        		var img = (arg[i].src).substring(arg[i].src.indexOf("ueditor"),arg[i].src.length+1);
		        		 $("#imgmu").append("<div id='upImg"+i+"' style='width:30%;margin-left:2%;text-align:center;float:left;height:auto;padding-bottom:70px;'><img src='"+base+"/"+img+"' name='upImg'style='width:300px;height:278px;float:left;border:1px solid #ccc;'/><div style='width:100%;float:left;text-align:center;margin-top:15px;'><a href='#' onClick='deleImg("+i+")' style='width:50px;height:30px;line-height:30px;background:#2CA8F7;color:#FFF; text-decoration:none;padding:10px 30px;border-radius:10px;'>删除</a></div></div>")
		        	}
		        });
		        //侦听文件上传，取上传文件列表中第一个上传的文件的路径
		       /** _editor.addListener('afterUpfile', function (t, arg){
		                  alert('这是文件地址：'+arg[0].url);
		        });**/
		    });
		});   
		
		//弹出图片上传的对话框
		function upImage() {
		    var myImage = _editor.getDialog("insertimage");
		    myImage.open();
		}
		//弹出文件上传的对话框
		function upFiles() {
		    var myFiles = _editor.getDialog("attachment");
		    myFiles.open();
		}
		
		//删除预览
		function deleImg(i) {
			$("#upImg"+i).children().remove()
			 $("#upImg"+i).remove();
		}
		
		//删除预览
		function deleImgA(i) {
			$("#upImgA"+i).children().remove()
			 $("#upImgA"+i).remove();
		}