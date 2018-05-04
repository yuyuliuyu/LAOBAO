var laih=1	
function shen(){
	//固定内框架大小
	var weisuo=mfrom1.window.document.getElementById("jiantou");//获取伸缩箭头 
	var laogun=mfrom1.window.document.getElementById("laogun");//触碰大小区域
	var leibxs=mfrom1.window.document.getElementById("liebiao");//获取列表
	var llqk=document.body.clientWidth;//获取浏览器显示区域大小
	var chuangkou=document.getElementById("mainTabs");//获取内框架
	var lbkj=document.getElementById("mfrom1");//获取内框架
	var ggdt=llqk-parseInt(lbkj.style.width);
	chuangkou.style.width=ggdt+"px";
	$(".mini-tabs-bodys").width(ggdt);
	$(".mini-tabs-headers").width(ggdt);
	//固定内框架大小
	//控制栏伸缩
	weisuo.onclick=function(){
		var lbkd=parseInt(lbkj.style.width);
        function lsxg(){	
				lbkj.style.width=lbkd+"px";
				chuangkou.style.width=llqk-lbkd+"px";
				var ceshi=parseInt(chuangkou.style.width);
				$(".mini-tabs-bodys").width(ceshi-10);
				lbkd=lbkd-10;
				if(lbkd<10)
				{
					clearInterval(dhkz);
					laih=1;
					lbkj.style.width=10+"px";
					leibxs.style.display="none";
					$(".mini-tabs-bodys").width(ggdt+39);
					$(".mini-tabs-headers").width(ggdt+39);
					shen();
				}
			}
		function xxa(){
				lbkj.style.width=lbkd+"px";
				chuangkou.style.width=llqk-lbkd+"px";
				var ceshi=parseInt(chuangkou.style.width);
				$(".mini-tabs-bodys").width(ceshi-10);
				lbkd=lbkd+10;
				if(lbkd>50)
				{
					clearInterval(dhkz);
					lbkj.style.width=49+"px";
					laih=0;
					$(".mini-tabs-bodys").width(ggdt);
					$(".mini-tabs-headers").width(ggdt);
					shen();
				}
			}
		if (laih==0){
			$(weisuo).toggleClass("nei");
			var dhkz=setInterval(lsxg,10);
		}
		else if(laih==1)
		{
			leibxs.style.display="block";
			$(weisuo).toggleClass("nei");
			var dhkz=setInterval(xxa,10);
		} 
      }
	  //控制栏伸缩
}


