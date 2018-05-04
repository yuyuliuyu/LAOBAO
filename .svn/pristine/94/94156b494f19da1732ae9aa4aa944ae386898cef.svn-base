var ieAlert = '<div id="outer"><div id="inner"><h6>对不起，本站不支持Internet Explorer 8 及以下版本的浏览器<br/>为了更好地浏览网站，建议您使用以下浏览器</h6><div id="links"><a href="http://browser.qq.com/" target="_blank"><img src="http://browser.qq.com/favicon.ico">QQ浏览器</a><a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank"><img src="http://img3sw.baidu.com/soft/9d/14744/b746f41a61876795b7980f56a07a4e47.png">Chrome</a><a href="http://www.uc.cn/" target="_blank"><img src="http://www.uc.cn/favicon.ico">UC浏览器</a><a href="http://www.liebao.cn/download.html" target="_blank"><img src="http://www.liebao.cn/favicon.ico">猎豹浏览器</a><a href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie" target="_blank"><img src="http://res1.windows.microsoft.com/resources/4.2/wol/shared/images/favicon.ico">Internet Explorer 11</a></div></div></div>';

var ie8css = "html{overflow:hidden;height:100%;}#ie8-warning {background-color: #999;background-repeat:no-repeat;background-position: center; background-size: cover; background-image: url(../js/bg-2.jpg);*zoom:1;color: #fff;position: fixed;bottom: 0;left: 0;width: 100%;height:100%;text-align: center;font-size: 14px;line-height: 1.5;z-index: 9999;font-family: 'Microsoft YaHei', sans-serif;display: table;}#ie8-warning span {position: absolute;cursor: pointer;right: 40px;top: 25px;font-size: 40px;line-height: 40px;color: #fff;}#ie8-warning h6 {margin: 0;margin-bottom: 32px;font-size: 32px;text-align: center;cursor: default;font-weight:bold;}#ie8-warning a {text-decoration: none;font-size: 16px;color: #eee;position: relative;margin-left:5px;margin-right:5px;}#ie8-warning a img{width:20px;height:20px;margin-right:3px;margin-top:1px;vertical-align:top;border:0;}#ie8-warning a:hover {text-decoration: underline;}#ie8-warning #outer {display: table-cell;vertical-align: middle;text-align: center;position: relative;}";

function closeme() {
  var div = document.getElementById("ie8-warning");
  div.parentNode.removeChild(div);
}

(function Main() {
  var isIE = !-[1, ];
  if (isIE) {
    if (document.styleSheets[0]) {
      var style = document.styleSheets[0];
      style.cssText += ie8css;
    } else {
      var style = document.createElement('style');
      document.getElementsByTagName('head')[0].appendChild(style);
      style.cssText = ie8css;
    }
  } else {
    var style = document.createElement('style');
    style.innerHTML = ie8css;
    document.getElementsByTagName('head')[0].appendChild(style);
  }
  var content = document.createElement('div');
  content.setAttribute('id', 'ie8-warning');
  content.innerHTML = ieAlert;
  var first = document.body.firstChild;
  document.body.insertBefore(content, first);
})();
