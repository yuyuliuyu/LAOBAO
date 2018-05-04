/**
 * 文件上传web控件
 * zhanghj
 *
 */

(function() {
    var bigWebUploader = (function() {
        var bigWebUploader = function() {
            return bigWebUploader.fn.init();
        };
        bigWebUploader.fn = bigWebUploader.prototype = {
            init: function() {
                return this;
            }
        };

        bigWebUploader.e = bigWebUploader.fn.e = function(evt) {
            return evt?evt:(window.event?window.event:null);
        };

        /**
         * 设置url
         */
        bigWebUploader.setUrl = bigWebUploader.fn.setUrl = function(form, url) {
            option.form = form;
            option.uploadUrl = url;
            $("#"+option.form).attr("action", url);
        };

        /**
         * 打开miniui控件
         */
        bigWebUploader.openMini = bigWebUploader.fn.openMini = function(form, windowId, url) {
            // 初始化form表单，让控件可以重新选择和提交
            option.form = form;
            $("#"+option.form)[0].reset();
            if (url != undefined && url != null && url != "")
                this.setUrl(form, url);
            else if ($("#"+option.form).attr("action") != "") {
            	this.setUrl($("#"+option.form).attr("action"));
            }
            $("#"+windowId+" > input:file")[0].click();
        };

        /**
         * 提交
         */
        bigWebUploader.submit = bigWebUploader.fn.submit = function(callback) {
            if (option.uploadUrl != "" && option.form != "") {
                if (callback != undefined && callback != null) option.createIframe(callback);
                $("#"+option.form)[0].submit();
            }
        };

        bigWebUploader.success = bigWebUploader.fn.success = function(data) {
            func(data);
        };

        var option = {
            form: "",
            bigFrame: "",
            uploadUrl: "",// 上传url
            createIframe: function(func) {
                var iframe = document.getElementById("appFrame");
                if (iframe == undefined) {
                    iframe = document.createElement("iframe");
                    iframe.setAttribute("id", "appFrame");
                    iframe.setAttribute("name", "appFrame");
                    iframe.style.display = "none";
                    document.body.appendChild(iframe);
                    this.bigFrame = iframe;
                    option.addEvent(func);
                } else this.bigFrame = iframe;
            },
            addEvent: function(func) {
                // 判断iframe的监听类型（IE只支持attachEvent，火狐支持addEventListener，W3C标准）
                if (this.bigFrame.addEventListener) {
                    this.bigFrame.addEventListener('load', function(evt) {
                        option.onListener(func);
                    }, false);
                } else if(this.bigFrame.attachEvent) {
                    this.bigFrame.attachEvent('onload', function() {
                        option.onListener(func);
                    });
                }
            },
            onListener : function(func) {
                // 获取上传之后的iframe返回值
                var doc = this.bigFrame.contentWindow.document;
                var pre = doc.getElementsByTagName('pre');
                // 是否存在(默认会自动生成pre标签)
                if (pre && pre.length > 0) {
                    var response = pre[0].innerHTML;
                    func(this.decode(response));
                }
            },
            decode: function(text) {
                var s = "";
                if (text.length == 0)
                    return "";
                s = text.replace(/&lt;/g, "<");
                s = s.replace(/&gt;/g, ">");
                s = s.replace(/&nbsp;/g, " ");
                s = s.replace(/&#39;/g, "\'");
                s = s.replace(/&quot;/g, "\"");
                s = s.replace(/<br>/g, "\n");
                return s;
            }
        };

        return bigWebUploader;
    })();

    window.bigWebUploader = bigWebUploader();

})();