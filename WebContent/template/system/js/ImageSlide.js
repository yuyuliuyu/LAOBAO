/**
 * Created with JetBrains WebStorm.
 * User: WinKi
 * Date: 13-10-14
 * Time: 下午2:20
 * To change this template use File | Settings | File Templates.
 */
(function ($) {
    $.fn.ImagesSlide = function (options) {
        var defaults = {
            showImages: 8,//默认显示图片数量,包括大的图
            bigImgWidth: 600,
            speed: 400,//移动速度
            leftBtn: ".leftBtn", //默认左按钮
            rightBtn: ".rightBtn", //默认右按钮
            autoSlide: true,//默认自动移动为false
            RightSlid: false, //默认向右移动为false
            autoSpeed: 2000,//自动移动的时间
            moreSlide: "setOne" //多处图片移动，设置不同的class
        };
        var options = $.extend(defaults, options);
        var $this = $(this),
            li = $this.find('li'),
            $lastLi,
            myFn;
        var li_width = li.outerWidth(true);
        var liwidth =  li.width();
        $this.css({
            width: 5500,
            left:- li_width,
            overflow: "hidden",
            position: "absolute"
        });
        li.css({
            float: 'left',
            overflow: "hidden",
            position: "relative"
        });

        init();
        $(options.leftBtn).click(function () {
            MoveLeft();
        });
        //点击按钮向右移动;
        $(options.rightBtn).click(function () {
            MoveRight();
        });
        function init() {
            var showWidth = li_width * (options.showImages - 1) + options.bigImgWidth;
            $this.wrap("<div class='slidediv'></div>");
            $('.slidediv').css({
                height:liwidth,
                width: showWidth,
                overflow: "hidden",
                position: "relative",
                float: "left"
            });
            $this.animate({ left: 0 },0, function () {
                $(this).css({ left: -li_width }).find("li:last").prependTo(this);
            });
            setBigImg();
            if (options.autoSlide) {
                if (options.RightSlid) {
                    myFn = setInterval(MoveRight, options.autoSpeed);
                    autoSlides(MoveRight);
                }
                else {
                    myFn = setInterval(MoveLeft, options.autoSpeed);
                    autoSlides(MoveLeft);
                }
            }
            $this.find("li").mouseover(function () {
                if ($lastLi != undefined) {
                    var lasturl = $lastLi.find('a').attr("Smallimg");
                    $lastLi.css("width",liwidth).find('img').attr({src: lasturl,width:liwidth});
                }
                var myurl = $(this).find('a').attr("Bigimg");
                $(this).css("width",options.bigImgWidth).find('img').attr({src: myurl,width:options.bigImgWidth});
                $lastLi = $(this);
            });
        }

        function setBigImg() {
            var $getli = $this.find('li').eq(2);
            var myurl = $getli.find('a').attr("Bigimg");
            $getli.css("width", options.bigImgWidth).find('img').attr({src: myurl, width: options.bigImgWidth});
            $lastLi = $getli;
        }

        function MoveLeft() {
            $this.stop().animate({ left: 0 }, options.speed, function () {
                $(this).css({ left: -li_width }).find("li:last").prependTo(this);
                if ($lastLi != undefined) {
                    var lasturl = $lastLi.find('a').attr("Smallimg");
                    $lastLi.css("width",liwidth).find('img').attr({src: lasturl,width: liwidth});
                }
                setBigImg();
            });
        }

        function MoveRight() {
            $this.stop().animate({ left: -li_width*2 }, options.speed, function () {
                $(this).css({ left: -li_width }).find("li:first").appendTo(this);
                if ($lastLi != undefined) {
                    var lasturl = $lastLi.find('a').attr("Smallimg");
                    $lastLi.css("width",liwidth).find('img').attr({src: lasturl,width: liwidth});
                }
                setBigImg();
            });
        }
        function autoSlides(fn) {
            $this.mouseover(function () {
                clearInterval(myFn);
            }).mouseout(function () {
                    myFn = setInterval(fn, options.autoSpeed);
                });
            $(options.leftBtn).mouseover(function () {
                clearInterval(myFn);
            }).mouseout(function () {
                    myFn = setInterval(fn, options.autoSpeed);
                });
            $(options.rightBtn).mouseover(function () {
                clearInterval(myFn);
            }).mouseout(function () {
                    myFn = setInterval(fn, options.autoSpeed);
                });
        }

    }
})(jQuery);