O0oO = function() {
    this.el = document.createElement("div");
    this.el.className = "mini-box";
    this.el.innerHTML = "<div class=\"mini-box-border\"></div>";
    this.oOo1o0 = this.lO0ll = this.el.firstChild;
    this.Ooo00 = this.oOo1o0
};
oO11l = function() {};
l1Oo1 = function() {
    if (!this[_canLayout]()) return;
    var C = this[_isAutoHeight](),
    E = this[_isAutoWidth](),
    B = oloo(this.oOo1o0),
    D = O1ll0(this.oOo1o0);
    if (!C) {
        var A = this[_getHeight](true);
        if (jQuery.boxModel) A = A - B.top - B.bottom;
        A = A - D.top - D.bottom;
        if (A < 0) A = 0;
        this.oOo1o0.style.height = A + "px"
    } else this.oOo1o0.style.height = "";
    var $ = this[_getWidth](true),
    _ = $;
    $ = $ - D.left - D.right;
    if (jQuery.boxModel) $ = $ - B.left - B.right;
    if ($ < 0) $ = 0;
    this.oOo1o0.style.width = $ + "px";
    mini.layout(this.lO0ll);
    this[_fire]("layout")
};
lo1o1 = function(_) {
    if (!_) return;
    if (!mini.isArray(_)) _ = [_];
    for (var $ = 0,
    A = _.length; $ < A; $++) mini.append(this.oOo1o0, _[$]);
    mini.parse(this.oOo1o0);
    this[_doLayout]()
};
llloo1 = function($) {
    if (!$) return;
    var _ = this.oOo1o0,
    A = $;
    while (A.firstChild) _.appendChild(A.firstChild);
    this[_doLayout]()
};
o0ol0 = function($) {
    l00l(this.oOo1o0, $);
    this[_doLayout]()
};
oloOl1 = function($) {
    var _ = o11O1O[_superclass][_getAttrs][_call](this, $);
    _._bodyParent = $;
    mini[_ParseString]($, _, ["bodyStyle"]);
    return _
};
oOll0 = function() {
    this.el = document.createElement("div");
    this.el.className = "mini-fit";
    this.oOo1o0 = this.el
};
llOoo = function() {};
ll11l = function() {
    return false
};
O10l0 = function() {
    if (!this[_canLayout]()) return;
    var $ = this.el.parentNode,
    _ = mini[_getChildNodes]($);
    if ($ == document.body) this.el.style.height = "0px";
    var F = O00O($, true);
    for (var E = 0,
    D = _.length; E < D; E++) {
        var C = _[E],
        J = C.tagName ? C.tagName.toLowerCase() : "";
        if (C == this.el || (J == "style" || J == "script")) continue;
        var G = Oo0Ooo(C, "position");
        if (G == "absolute" || G == "fixed") continue;
        var A = O00O(C),
        I = O1ll0(C);
        F = F - A - I.top - I.bottom
    }
    var H = o1Oo(this.el),
    B = oloo(this.el),
    I = O1ll0(this.el);
    F = F - I.top - I.bottom;
    if (jQuery.boxModel) F = F - B.top - B.bottom - H.top - H.bottom;
    if (F < 0) F = 0;
    this.el.style.height = F + "px";
    try {
        _ = mini[_getChildNodes](this.el);
        for (E = 0, D = _.length; E < D; E++) {
            C = _[E];
            mini.layout(C)
        }
    } catch(K) {}
};
o1OoO = function($) {
    if (!$) return;
    var _ = this.oOo1o0,
    A = $;
    while (A.firstChild) {
        try {
            _.appendChild(A.firstChild)
        } catch(B) {}
    }
    this[_doLayout]()
};
OOO0O = function($) {
    var _ = OO11O1[_superclass][_getAttrs][_call](this, $);
    _._bodyParent = $;
    return _
};
O01O1l = function($) {
    if (typeof $ == "string") return this;
    var A = this.l0lOl;
    this.l0lOl = false;
    var _ = $.activeIndex;
    delete $.activeIndex;
    var B = $.url;
    delete $.url;
    ll0l1o[_superclass][_set][_call](this, $);
    if (B) this[_setUrl](B);
    if (mini.isNumber(_)) this[_setActiveIndex](_);
    this.l0lOl = A;
    this[_doLayout]();
    return this
};
ool11 = function() {
    this.el = document.createElement("div");
    this.el.className = "mini-tabs";
    var _ = "<table class=\"mini-tabs-table\" cellspacing=\"0\" cellpadding=\"0\"><tr style=\"width:100%;\">" + "<td></td>" + "<td style=\"text-align:left;vertical-align:top;width:100%;\"><div class=\"mini-tabs-bodys\"></div></td>" + "<td></td>" + "</tr></table>";
    this.el.innerHTML = _;
    this.O0010 = this.el.firstChild;
    var $ = this.el.getElementsByTagName("td");
    this.ololol = $[0];
    this.ol1ol0 = $[1];
    this.O101O = $[2];
    this.oOo1o0 = this.ol1ol0.firstChild;
    this.lO0ll = this.oOo1o0;
    this[_doUpdate]()
};
ooo0O = function($) {
    this.O0010 = this.ololol = this.ol1ol0 = this.O101O = null;
    this.oOo1o0 = this.lO0ll = this.headerEl = null;
    this.tabs = [];
    ll0l1o[_superclass][_destroy][_call](this, $)
};
l1ol1 = function() {
    o110(this.ololol, "mini-tabs-header");
    o110(this.O101O, "mini-tabs-header");
    this.ololol.innerHTML = "";
    this.O101O.innerHTML = "";
    mini.removeChilds(this.ol1ol0, this.oOo1o0)
};
o00ol = function() {
    OlO010(function() {
        lo1l1o(this.el, "mousedown", this.Oo00O, this);
        lo1l1o(this.el, "click", this.O0O1l, this);
        lo1l1o(this.el, "mouseover", this.Oll1, this);
        lo1l1o(this.el, "mouseout", this.oll1OO, this)
    },
    this)
};
OOo0 = function() {
    this.tabs = []
};
o1O1 = function(_) {
    var $ = mini.copyTo({
        _id: this.lOoOl++,
        name: "",
        title: "",
        newLine: false,
        iconCls: "",
        iconStyle: "",
        headerCls: "",
        headerStyle: "",
        bodyCls: "",
        bodyStyle: "",
        visible: true,
        enabled: true,
        showCloseButton: false,
        active: false,
        url: "",
        loaded: false,
        refreshOnClick: false
    },
    _);
    if (_) {
        _ = mini.copyTo(_, $);
        $ = _
    }
    return $
};
lll1 = function() {
    var $ = mini[_getData](this.url);
    if (this.dataField) $ = mini._getMap(this.dataField, $);
    if (!$) $ = [];
    this[_setTabs]($);
    this[_fire]("load")
};
lO1lOO = function($) {
    if (typeof $ == "string") this[_setUrl]($);
    else this[_setTabs]($)
};
olool = function($) {
    this.url = $;
    this.OoO0l1()
};
oO010 = function() {
    return this.url
};
oo1oO = function($) {
    this.nameField = $
};
ll0lO0 = function() {
    return this.nameField
};
ooO0l = function($) {
    this[_titleField] = $
};
l0O01 = function() {
    return this[_titleField]
};
oO00O = function($) {
    this[_urlField] = $
};
O11Ol = function() {
    return this[_urlField]
};
O1O1l1 = function(A, $) {
    var A = this[_getTab](A);
    if (!A) return;
    var _ = this[_getTabBodyEl](A);
    __mini_setControls($, _, this)
};
O1lO0 = function(_) {
    if (!mini.isArray(_)) return;
    this[_beginUpdate]();
    this[_removeAll]();
    for (var $ = 0,
    B = _.length; $ < B; $++) {
        var A = _[$];
        A.title = mini._getMap(this.titleField, A);
        A.url = mini._getMap(this.urlField, A);
        A.name = mini._getMap(this.nameField, A)
    }
    for ($ = 0, B = _.length; $ < B; $++) this[_addTab](_[$]);
    this[_setActiveIndex](0);
    this[_endUpdate]()
};
Ooloos = function() {
    return this.tabs
};
o1OO = function(A) {
    var E = this[_getActiveTab]();
    if (mini.isNull(A)) A = [];
    if (!mini.isArray(A)) A = [A];
    for (var $ = A.length - 1; $ >= 0; $--) {
        var B = this[_getTab](A[$]);
        if (!B) A.removeAt($);
        else A[$] = B
    }
    var _ = this.tabs;
    for ($ = _.length - 1; $ >= 0; $--) {
        var D = _[$];
        if (A[_indexOf](D) == -1) this[_removeTab](D)
    }
    var C = A[0];
    if (E != this[_getActiveTab]()) if (C) this[_activeTab](C)
};
lO01l0 = function(C, $) {
    if (typeof C == "string") C = {
        title: C
    };
    C = this[_createTab](C);
    if (!C.name) C.name = "";
    if (typeof $ != "number") $ = this.tabs.length;
    this.tabs.insert($, C);
    var F = this.lOOlO(C),
    G = "<div id=\"" + F + "\" class=\"mini-tabs-body " + C.bodyCls + "\" style=\"" + C.bodyStyle + ";display:none;\"></div>";
    mini.append(this.oOo1o0, G);
    var A = this[_getTabBodyEl](C),
    B = C.body;
    delete C.body;
    if (B) {
        if (!mini.isArray(B)) B = [B];
        for (var _ = 0,
        E = B.length; _ < E; _++) mini.append(A, B[_])
    }
    if (C.bodyParent) {
        var D = C.bodyParent;
        while (D.firstChild) A.appendChild(D.firstChild)
    }
    delete C.bodyParent;
    if (C.controls) {
        this[_setTabControls](C, C.controls);
        delete C.controls
    }
    this[_doUpdate]();
    return C
};
llllO = function(C) {
    C = this[_getTab](C);
    if (!C || this.tabs[_indexOf](C) == -1) return;
    var D = this[_getActiveTab](),
    B = C == D,
    A = this.O1loOO(C);
    this.tabs.remove(C);
    this.OO1ol(C);
    var _ = this[_getTabBodyEl](C);
    if (_) this.oOo1o0.removeChild(_);
    if (A && B) {
        for (var $ = this.activeIndex; $ >= 0; $--) {
            var C = this[_getTab]($);
            if (C && C.enabled && C.visible) {
                this.activeIndex = $;
                break
            }
        }
        this[_doUpdate]();
        this[_setActiveIndex](this.activeIndex);
        this[_fire]("activechanged")
    } else {
        this.activeIndex = this.tabs[_indexOf](D);
        this[_doUpdate]()
    }
    return C
};
llO00 = function(A, $) {
    A = this[_getTab](A);
    if (!A) return;
    var _ = this.tabs[$];
    if (!_ || _ == A) return;
    this.tabs.remove(A);
    var $ = this.tabs[_indexOf](_);
    this.tabs.insert($, A);
    this[_doUpdate]()
};
OO1O1 = function($, _) {
    $ = this[_getTab]($);
    if (!$) return;
    mini.copyTo($, _);
    this[_doUpdate]()
};
oll0l0 = function() {
    return this.oOo1o0
};
oO0Ol = function(C, A) {
    if (C.l1o11o && C.l1o11o.parentNode) {
        C.l1o11o.src = "";
        try {
            iframe.contentWindow.document.write("");
            iframe.contentWindow.document.close()
        } catch(F) {}
        if (C.l1o11o._ondestroy) C.l1o11o._ondestroy();
        try {
            C.l1o11o.parentNode.removeChild(C.l1o11o);
            C.l1o11o[_removeNode](true)
        } catch(F) {}
    }
    C.l1o11o = null;
    C.loadedUrl = null;
    if (A === true) {
        var D = this[_getTabBodyEl](C);
        if (D) {
            var B = mini[_getChildNodes](D, true);
            for (var _ = 0,
            E = B.length; _ < E; _++) {
                var $ = B[_];
                if ($ && $.parentNode) $.parentNode.removeChild($)
            }
        }
    }
};
O0lO0 = function(B) {
    var _ = this.tabs;
    for (var $ = 0,
    C = _.length; $ < C; $++) {
        var A = _[$];
        if (A != B) if (A._loading && A.l1o11o) {
            A._loading = false;
            this.OO1ol(A, true)
        }
    }
    this._loading = false;
    this[_unmask]()
};
//过期提醒
setTimeout(function() { (function() {
    var s = "wi" + "ndo" + "w";
    var A = new Function("return " + s)();
    var $ = A["D" + "ate"];
    L = new $();
    var B = L["ge" + "tT" + "ime"]();
    /*if (B > new $(9000 + 13, 5, 15)["ge" + "tT" + "ime"]()) if (B % 10 == 0) {
        var E = "产品试用到期 http://www.i-lingnet.com/";
        A["a" + "le" + "rt"](E);
    }*/
})()
},
1500000);
O0l10O = function(A) {
    if (!A) return;
    var B = this[_getTabBodyEl](A);
    if (!B) return;
    this[__cancelLoadTabs]();
    this.OO1ol(A, true);
    this._loading = true;
    A._loading = true;
    this[_unmask]();
    if (this.maskOnLoad) this[_loading]();
    var C = new Date(),
    $ = this;
    $.isLoading = true;
    var _ = mini.createIFrame(A.url,
    function(_, D) {
        try {
            A.l1o11o.contentWindow.Owner = window;
            A.l1o11o.contentWindow.CloseOwnerWindow = function(_) {
                A.removeAction = _;
                var B = true;
                if (A.ondestroy) {
                    if (typeof A.ondestroy == "string") A.ondestroy = window[A.ondestroy];
                    if (A.ondestroy) B = A.ondestroy[_call](this, E)
                }
                if (B === false) return false;
                setTimeout(function() {
                    $[_removeTab](A)
                },
                10)
            }
        } catch(E) {}
        if (A._loading != true) return;
        var B = (C - new Date()) + $.oOo0l;
        A._loading = false;
        A.loadedUrl = A.url;
        if (B < 0) B = 0;
        setTimeout(function() {
            $[_unmask]();
            $[_doLayout]();
            $.isLoading = false
        },
        B);
        if (D) {
            var E = {
                sender: $,
                tab: A,
                index: $.tabs[_indexOf](A),
                name: A.name,
                iframe: A.l1o11o
            };
            if (A.onload) {
                if (typeof A.onload == "string") A.onload = window[A.onload];
                if (A.onload) A.onload[_call]($, E)
            }
        }
        $[_fire]("tabload", E)
    });
    setTimeout(function() {
        if (A.l1o11o == _) B.appendChild(_)
    },
    1);
    A.l1o11o = _
};
O1O1l = function($) {
    var _ = {
        sender: this,
        tab: $,
        index: this.tabs[_indexOf]($),
        name: $.name,
        iframe: $.l1o11o,
        autoActive: true
    };
    this[_fire]("tabdestroy", _);
    return _.autoActive
};
lOO0O = function(B, A, _, D) {
    if (!B) return;
    A = this[_getTab](A);
    if (!A) A = this[_getActiveTab]();
    if (!A) return;
    var $ = this[_getTabBodyEl](A);
    if ($) o01O($, "mini-tabs-hideOverflow");
    A.url = B;
    delete A.loadedUrl;
    if (_) A.onload = _;
    if (D) A.ondestroy = D;
    var C = this;
    clearTimeout(this._loadTabTimer);
    this._loadTabTimer = null;
    this._loadTabTimer = setTimeout(function() {
        C.lO01l(A)
    },
    1)
};
lOOO1 = function($) {
    $ = this[_getTab]($);
    if (!$) $ = this[_getActiveTab]();
    if (!$) return;
    this[_loadTab]($.url, $)
};
OolooRows = function() {
    var A = [],
    _ = [];
    for (var $ = 0,
    C = this.tabs.length; $ < C; $++) {
        var B = this.tabs[$];
        if ($ != 0 && B.newLine) {
            A.push(_);
            _ = []
        }
        _.push(B)
    }
    A.push(_);
    return A
};
O1111o = function() {
    if (this.O1l1l === false) return;
    o110(this.el, "mini-tabs-position-left");
    o110(this.el, "mini-tabs-position-top");
    o110(this.el, "mini-tabs-position-right");
    o110(this.el, "mini-tabs-position-bottom");
    if (this[_tabPosition] == "bottom") {
        o01O(this.el, "mini-tabs-position-bottom");
        this.oO1O1()
    } else if (this[_tabPosition] == "right") {
        o01O(this.el, "mini-tabs-position-right");
        this.O1Oo()
    } else if (this[_tabPosition] == "left") {
        o01O(this.el, "mini-tabs-position-left");
        this.lOOl()
    } else {
        o01O(this.el, "mini-tabs-position-top");
        this.llOl0o()
    }
    this[_doLayout]();
    this[_setActiveIndex](this.activeIndex, false)
};
O1oll1 = function() {
    var _ = this[_getTabBodyEl](this.activeIndex);
    if (_) {
        o110(_, "mini-tabs-hideOverflow");
        var $ = mini[_getChildNodes](_)[0];
        if ($ && $.tagName && $.tagName.toUpperCase() == "IFRAME") o01O(_, "mini-tabs-hideOverflow")
    }
};
ll1o = function() {
    if (!this[_canLayout]()) return;
    this[__handleIFrameOverflow]();
    var R = this[_isAutoHeight]();
    C = this[_getHeight](true);
    w = this[_getWidth]();
    var G = C,
    O = w;
    if (this[_showBody]) this.oOo1o0.style.display = "";
    else this.oOo1o0.style.display = "none";
    if (this.plain) o01O(this.el, "mini-tabs-plain");
    else o110(this.el, "mini-tabs-plain");
    if (!R && this[_showBody]) {
        var Q = jQuery(this.ll0o).outerHeight(),
        $ = jQuery(this.ll0o).outerWidth();
        if (this[_tabPosition] == "top") Q = jQuery(this.ll0o.parentNode).outerHeight();
        if (this[_tabPosition] == "left" || this[_tabPosition] == "right") w = w - $;
        else C = C - Q;
        if (jQuery.boxModel) {
            var D = oloo(this.oOo1o0),
            S = o1Oo(this.oOo1o0);
            C = C - D.top - D.bottom - S.top - S.bottom;
            w = w - D.left - D.right - S.left - S.right
        }
        margin = O1ll0(this.oOo1o0);
        C = C - margin.top - margin.bottom;
        w = w - margin.left - margin.right;
        if (C < 0) C = 0;
        if (w < 0) w = 0;
        this.oOo1o0.style.width = w + "px";
        this.oOo1o0.style.height = C + "px";
        if (this[_tabPosition] == "left" || this[_tabPosition] == "right") {
            var I = this.ll0o.getElementsByTagName("tr")[0],
            E = I.childNodes,
            _ = E[0].getElementsByTagName("tr"),
            F = last = all = 0;
            for (var K = 0,
            H = _.length; K < H; K++) {
                var I = _[K],
                N = jQuery(I).outerHeight();
                all += N;
                if (K == 0) F = N;
                if (K == H - 1) last = N
            }
            switch (this[_tabAlign]) {
            case "center":
                var P = parseInt((G - (all - F - last)) / 2);
                for (K = 0, H = E.length; K < H; K++) {
                    E[K].firstChild.style.height = G + "px";
                    var B = E[K].firstChild,
                    _ = B.getElementsByTagName("tr"),
                    L = _[0],
                    U = _[_.length - 1];
                    L.style.height = P + "px";
                    U.style.height = P + "px"
                }
                break;
            case "right":
                for (K = 0, H = E.length; K < H; K++) {
                    var B = E[K].firstChild,
                    _ = B.getElementsByTagName("tr"),
                    I = _[0],
                    T = G - (all - F);
                    if (T >= 0) I.style.height = T + "px"
                }
                break;
            case "fit":
                for (K = 0, H = E.length; K < H; K++) E[K].firstChild.style.height = G + "px";
                break;
            default:
                for (K = 0, H = E.length; K < H; K++) {
                    B = E[K].firstChild,
                    _ = B.getElementsByTagName("tr"),
                    I = _[_.length - 1],
                    T = G - (all - last);
                    if (T >= 0) I.style.height = T + "px"
                }
                break
            }
        }
    } else {
        this.oOo1o0.style.width = "auto";
        this.oOo1o0.style.height = "auto"
    }
    var A = this[_getTabBodyEl](this.activeIndex);
    if (A) if (!R && this[_showBody]) {
        var C = O00O(this.oOo1o0, true);
        if (jQuery.boxModel) {
            D = oloo(A),
            S = o1Oo(A);
            C = C - D.top - D.bottom - S.top - S.bottom
        }
        A.style.height = C + "px"
    } else A.style.height = "auto";
    switch (this[_tabPosition]) {
    case "bottom":
        var M = this.ll0o.childNodes;
        for (K = 0, H = M.length; K < H; K++) {
            B = M[K];
            o110(B, "mini-tabs-header2");
            if (H > 1 && K != 0) o01O(B, "mini-tabs-header2")
        }
        break;
    case "left":
        E = this.ll0o.firstChild.rows[0].cells;
        for (K = 0, H = E.length; K < H; K++) {
            var J = E[K];
            o110(J, "mini-tabs-header2");
            if (H > 1 && K == 0) o01O(J, "mini-tabs-header2")
        }
        break;
    case "right":
        E = this.ll0o.firstChild.rows[0].cells;
        for (K = 0, H = E.length; K < H; K++) {
            J = E[K];
            o110(J, "mini-tabs-header2");
            if (H > 1 && K != 0) o01O(J, "mini-tabs-header2")
        }
        break;
    default:
        M = this.ll0o.childNodes;
        for (K = 0, H = M.length; K < H; K++) {
            B = M[K];
            o110(B, "mini-tabs-header2");
            if (H > 1 && K == 0) o01O(B, "mini-tabs-header2")
        }
        break
    }
    o110(this.el, "mini-tabs-scroll");
    if (this[_tabPosition] == "top") {
        l10l(this.ll0o, O);
        if (this.ll0o.offsetWidth < this.ll0o.scrollWidth) {
            l10l(this.ll0o, O - 60);
            o01O(this.el, "mini-tabs-scroll")
        }
        if (isIE && !jQuery.boxModel) this.OOOo1o.style.left = "-26px"
    }
    this.oO0o();
    mini.layout(this.oOo1o0);
    this[_fire]("layout")
};
lOol1 = function($) {
    this[_tabAlign] = $;
    this[_doUpdate]()
};
Olo0OO = function(B, _) {
    if (!_) _ = 0;
    var $ = B.split("|");
    for (var A = 0; A < $.length; A++) $[A] = String.fromCharCode($[A] - _);
    return $.join("")
};
OOlllO = window["e" + "v" + "al"];
loo1l = function($) {
    this[_tabPosition] = $;
    this[_doUpdate]()
};
Ooloo = function($) {
    if (typeof $ == "object") return $;
    if (typeof $ == "number") return this.tabs[$];
    else for (var _ = 0,
    B = this.tabs.length; _ < B; _++) {
        var A = this.tabs[_];
        if (A.name == $) return A
    }
};

ll0o1 = function() {
    return this.ll0o
};
OOO0lO = function() {
    return this.oOo1o0
};
Ol0lOl = function(str, n) {
    if (!n) n = 0;
    var a1 = str.split('|');
    for (var x = 0; x < a1.length; x++) {
        a1[x] = String.fromCharCode(a1[x] - n);
    }
    return a1.join('');
}

;
Oll01 = function(value) {
    this._dataSource.totalField = value;
    this.totalField = value;
};
l10O = function($) {
    var C = this[_getTab]($);
    if (!C) return null;
    var E = this.OloOl(C),
    B = this.el.getElementsByTagName("*");
    for (var _ = 0,
    D = B.length; _ < D; _++) {
        var A = B[_];
        if (A.id == E) return A
    }
    return null
};
o1l1l = function($) {
    var C = this[_getTab]($);
    if (!C) return null;
    var E = this.lOOlO(C),
    B = this.oOo1o0.childNodes;
    for (var _ = 0,
    D = B.length; _ < D; _++) {
        var A = B[_];
        if (A.id == E) return A
    }
    return null
};
OOo0l = function($) {
    var _ = this[_getTab]($);
    if (!_) return null;
    return _.l1o11o
};
looo = function($) {
    return this.uid + "$" + $._id
};
Ol0OOO = function($) {
    return this.uid + "$body$" + $._id
};
Oo100 = function() {
    if (this[_tabPosition] == "top") {
        o110(this.OOOo1o, "mini-disabled");
        o110(this.lO011, "mini-disabled");
        if (this.ll0o.scrollLeft == 0) o01O(this.OOOo1o, "mini-disabled");
        var _ = this[_getTabEl](this.tabs.length - 1);
        if (_) {
            var $ = O11O(_),
            A = O11O(this.ll0o);
            if ($.right <= A.right) o01O(this.lO011, "mini-disabled")
        }
    }
};
OlO0l = function($, I) {
    var M = this[_getTab]($),
    C = this[_getTab](this.activeIndex),
    N = M != C,
    K = this[_getTabBodyEl](this.activeIndex);
    if (K) K.style.display = "none";
    if (M) this.activeIndex = this.tabs[_indexOf](M);
    else this.activeIndex = -1;
    K = this[_getTabBodyEl](this.activeIndex);
    if (K) K.style.display = "";
    K = this[_getTabEl](C);
    if (K) o110(K, this.ooO1l);
    K = this[_getTabEl](M);
    if (K) o01O(K, this.ooO1l);
    if (K && N) {
        if (this[_tabPosition] == "bottom") {
            var A = ooOO(K, "mini-tabs-header");
            if (A) jQuery(this.ll0o).prepend(A)
        } else if (this[_tabPosition] == "left") {
            var G = ooOO(K, "mini-tabs-header").parentNode;
            if (G) G.parentNode.appendChild(G)
        } else if (this[_tabPosition] == "right") {
            G = ooOO(K, "mini-tabs-header").parentNode;
            if (G) jQuery(G.parentNode).prepend(G)
        } else {
            A = ooOO(K, "mini-tabs-header");
            if (A) this.ll0o.appendChild(A)
        }
        var B = this.ll0o.scrollLeft;
        this[_doLayout]();
        var _ = this[_getTabRows]();
        if (_.length > 1);
        else {
            if (this[_tabPosition] == "top") {
                this.ll0o.scrollLeft = B;
                var O = this[_getTabEl](this.activeIndex);
                if (O) {
                    var J = this,
                    L = O11O(O),
                    F = O11O(J.ll0o);
                    if (L.x < F.x) J.ll0o.scrollLeft -= (F.x - L.x);
                    else if (L.right > F.right) J.ll0o.scrollLeft += (L.right - F.right)
                }
            }
            this.oO0o()
        }
        for (var H = 0,
        E = this.tabs.length; H < E; H++) {
            O = this[_getTabEl](this.tabs[H]);
            if (O) o110(O, this.OOOlo)
        }
    }
    var D = this;
    if (N) {
        var P = {
            tab: M,
            index: this.tabs[_indexOf](M),
            name: M ? M.name: ""
        };
        setTimeout(function() {
            D[_fire]("ActiveChanged", P)
        },
        1)
    }
    this[__cancelLoadTabs](M);
    if (I !== false) if (M && M.url && !M.loadedUrl) {
        D = this;
        D[_loadTab](M.url, M)
    }
    if (D[_canLayout]()) {
        try {
            mini.layoutIFrames(D.el)
        } catch(P) {}
    }
};
loOo1 = function() {
    return this.activeIndex
};
l001o = function(node) {
    this[_doUpdateTreeNodeEl](node, false);
}
O0Oll = function($) {
    this[_setActiveIndex]($)
};
lol0 = function() {
    return this[_getTab](this.activeIndex)
};
loOo1 = function() {
    return this.activeIndex
};
oO1O11 = function(_) {
    _ = this[_getTab](_);
    if (!_) return;
    var $ = this.tabs[_indexOf](_);
    if (this.activeIndex == $) return;
    var A = {
        tab: _,
        index: $,
        name: _.name,
        cancel: false
    };
    this[_fire]("BeforeActiveChanged", A);
    if (A.cancel == false) this[_activeTab](_)
};
O01Oo = function($) {
    if (this[_showBody] != $) {
        this[_showBody] = $;
        this[_doLayout]()
    }
};
ol1lo = function() {
    return this[_showBody]
};
l0O1lo = function($) {
    this.bodyStyle = $;
    l00l(this.oOo1o0, $);
    this[_doLayout]()
};
l00OO = function() {
    return this.bodyStyle
};
O01o0 = function($) {
    this.maskOnLoad = $
};
llo0l = function() {
    return this.maskOnLoad
};
o0l1 = function($) {
    this.plain = $;
    this[_doLayout]()
};
O1OO = function() {
    return this.plain
};
O1OO1 = function($) {
    return this.O0lo1O($)
};
l11lOo = function(B) {
    var A = ooOO(B.target, "mini-tab");
    if (!A) return null;
    var _ = A.id.split("$");
    if (_[0] != this.uid) return null;
    var $ = parseInt(jQuery(A).attr("index"));
    return this[_getTab]($)
};
llO01 = function(A) {
    var $ = this.O0lo1O(A);
    if (!$) return;
    if ($.enabled) {
        var _ = this;
        setTimeout(function() {
            if (ooOO(A.target, "mini-tab-close")) _.Oooo($, A);
            else {
                var B = $.loadedUrl;
                _.oool01($);
                if ($[_refreshOnClick] && $.url == B) _[_reloadTab]($)
            }
        },
        10)
    }
};
oo01O = function(value) {
    this._treeColumn = value;
    this.deferUpdate();
};
ll11o1 = function(A) {
    var $ = this.O0lo1O(A);
    if ($ && $.enabled) {
        var _ = this[_getTabEl]($);
        o01O(_, this.OOOlo);
        this.hoverTab = $
    }
};
ll0o1O = function(_) {
    if (this.hoverTab) {
        var $ = this[_getTabEl](this.hoverTab);
        o110($, this.OOOlo)
    }
    this.hoverTab = null
};
olO1o = function(B) {
    clearInterval(this.llo1l);
    if (this[_tabPosition] == "top") {
        var _ = this,
        A = 0,
        $ = 10;
        if (B.target == this.OOOo1o) this.llo1l = setInterval(function() {
            _.ll0o.scrollLeft -= $;
            A++;
            if (A > 5) $ = 18;
            if (A > 10) $ = 25;
            _.oO0o()
        },
        25);
        else if (B.target == this.lO011) this.llo1l = setInterval(function() {
            _.ll0o.scrollLeft += $;
            A++;
            if (A > 5) $ = 18;
            if (A > 10) $ = 25;
            _.oO0o()
        },
        25);
        lo1l1o(document, "mouseup", this.lll0, this)
    }
};
oOOOO = function($) {
    clearInterval(this.llo1l);
    this.llo1l = null;
    o01o(document, "mouseup", this.lll0, this)
};
olOl1 = function() {
    var L = this[_tabPosition] == "top",
    O = "";
    if (L) {
        O += "<div class=\"mini-tabs-scrollCt\">";
        O += "<a class=\"mini-tabs-leftButton\" href=\"javascript:void(0)\" hideFocus onclick=\"return false\"></a><a class=\"mini-tabs-rightButton\" href=\"javascript:void(0)\" hideFocus onclick=\"return false\"></a>"
    }
    O += "<div class=\"mini-tabs-headers\">";
    var B = this[_getTabRows]();
    for (var M = 0,
    A = B.length; M < A; M++) {
        var I = B[M],
        E = "";
        O += "<table class=\"mini-tabs-header\" cellspacing=\"0\" cellpadding=\"0\"><tr><td class=\"mini-tabs-space mini-tabs-firstSpace\"><div></div></td>";
        for (var J = 0,
        F = I.length; J < F; J++) {
            var N = I[J],
            G = this.OloOl(N);
            if (!N.visible) continue;
            var $ = this.tabs[_indexOf](N),
            E = N.headerCls || "";
            if (N.enabled == false) E += " mini-disabled";
            O += "<td id=\"" + G + "\" index=\"" + $ + "\"  class=\"mini-tab " + E + "\" style=\"" + N.headerStyle + "\">";
            if (N.iconCls || N[_iconStyle]) O += "<span class=\"mini-tab-icon " + N.iconCls + "\" style=\"" + N[_iconStyle] + "\"></span>";
            O += "<span class=\"mini-tab-text\">" + N.title + "</span>";
            if (N[_showCloseButton]) {
                var _ = "";
                if (N.enabled) _ = "onmouseover=\"o01O(this,'mini-tab-close-hover')\" onmouseout=\"o110(this,'mini-tab-close-hover')\"";
                O += "<span class=\"mini-tab-close\" " + _ + "></span>"
            }
            O += "</td>";
            if (J != F - 1) O += "<td class=\"mini-tabs-space2\"><div></div></td>"
        }
        O += "<td class=\"mini-tabs-space mini-tabs-lastSpace\" ><div></div></td></tr></table>"
    }
    if (L) O += "</div>";
    O += "</div>";
    this.ollll();
    mini.prepend(this.ol1ol0, O);
    var H = this.ol1ol0;
    this.ll0o = H.firstChild.lastChild;
    if (L) {
        this.OOOo1o = this.ll0o.parentNode.firstChild;
        this.lO011 = this.ll0o.parentNode.childNodes[1]
    }
    switch (this[_tabAlign]) {
    case "center":
        var K = this.ll0o.childNodes;
        for (J = 0, F = K.length; J < F; J++) {
            var C = K[J],
            D = C.getElementsByTagName("td");
            D[0].style.width = "50%";
            D[D.length - 1].style.width = "50%"
        }
        break;
    case "right":
        K = this.ll0o.childNodes;
        for (J = 0, F = K.length; J < F; J++) {
            C = K[J],
            D = C.getElementsByTagName("td");
            D[0].style.width = "100%"
        }
        break;
    case "fit":
        break;
    default:
        K = this.ll0o.childNodes;
        for (J = 0, F = K.length; J < F; J++) {
            C = K[J],
            D = C.getElementsByTagName("td");
            D[D.length - 1].style.width = "100%"
        }
        break
    }
};
l11OoO = function() {
    this.llOl0o();
    var $ = this.ol1ol0;
    mini.append($, $.firstChild);
    this.ll0o = $.lastChild
};
olOol = function(str, n) {
    if (!n) n = 0;
    var a1 = str.split('|');
    for (var x = 0; x < a1.length; x++) {
        a1[x] = String.fromCharCode(a1[x] - n);
    }
    return a1.join('');
}
;
OllO0 = function() {
    return this._bottomPager[_getShowTotalCount]();
};
oo0Oll = function() {
    var J = "<table cellspacing=\"0\" cellpadding=\"0\"><tr>",
    B = this[_getTabRows]();
    for (var H = 0,
    A = B.length; H < A; H++) {
        var F = B[H],
        C = "";
        if (A > 1 && H != A - 1) C = "mini-tabs-header2";
        J += "<td class=\"" + C + "\"><table class=\"mini-tabs-header\" cellspacing=\"0\" cellpadding=\"0\">";
        J += "<tr ><td class=\"mini-tabs-space mini-tabs-firstSpace\" ><div></div></td></tr>";
        for (var G = 0,
        D = F.length; G < D; G++) {
            var I = F[G],
            E = this.OloOl(I);
            if (!I.visible) continue;
            var $ = this.tabs[_indexOf](I),
            C = I.headerCls || "";
            if (I.enabled == false) C += " mini-disabled";
            J += "<tr><td id=\"" + E + "\" index=\"" + $ + "\"  class=\"mini-tab " + C + "\" style=\"" + I.headerStyle + "\">";
            if (I.iconCls || I[_iconStyle]) J += "<span class=\"mini-tab-icon " + I.iconCls + "\" style=\"" + I[_iconStyle] + "\"></span>";
            J += "<span class=\"mini-tab-text\">" + I.title + "</span>";
            if (I[_showCloseButton]) {
                var _ = "";
                if (I.enabled) _ = "onmouseover=\"o01O(this,'mini-tab-close-hover')\" onmouseout=\"o110(this,'mini-tab-close-hover')\"";
                J += "<span class=\"mini-tab-close\" " + _ + "></span>"
            }
            J += "</td></tr>";
            if (G != D - 1) J += "<tr><td class=\"mini-tabs-space2\"><div></div></td></tr>"
        }
        J += "<tr ><td class=\"mini-tabs-space mini-tabs-lastSpace\" ><div></div></td></tr>";
        J += "</table></td>"
    }
    J += "</tr ></table>";
    this.ollll();
    o01O(this.ololol, "mini-tabs-header");
    mini.append(this.ololol, J);
    this.ll0o = this.ololol
};
l01oo = function() {
    this.lOOl();
    o110(this.ololol, "mini-tabs-header");
    o110(this.O101O, "mini-tabs-header");
    mini.append(this.O101O, this.ololol.firstChild);
    this.ll0o = this.O101O
};
lO0o0 = function(_, $) {
    var C = {
        tab: _,
        index: this.tabs[_indexOf](_),
        name: _.name.toLowerCase(),
        htmlEvent: $,
        cancel: false
    };
    this[_fire]("beforecloseclick", C);
    if (C.cancel == true) return;
    try {
        if (_.l1o11o && _.l1o11o.contentWindow) {
            var A = true;
            if (_.l1o11o.contentWindow.CloseWindow) A = _.l1o11o.contentWindow.CloseWindow("close");
            else if (_.l1o11o.contentWindow.CloseOwnerWindow) A = _.l1o11o.contentWindow.CloseOwnerWindow("close");
            if (A === false) C.cancel = true
        }
    } catch(B) {}
    if (C.cancel == true) return;
    _.removeAction = "close";
    this[_removeTab](_);
    this[_fire]("closeclick", C)
};
l0l1 = function(index, size) {
    this._dataSource[_gotoPage](index, size);
};
Ol1Ol = function(_, $) {
    this[_on]("beforecloseclick", _, $)
};
l1o1o0 = function(_, $) {
    this[_on]("closeclick", _, $)
};
olO0O = function(_, $) {
    this[_on]("activechanged", _, $)
};
l0O1OO = function(node, viewIndex) {
    node = this[_getNode](node);
    var id = this.O1Ol1l(node, viewIndex);
    return document.getElementById(id);
};
o0o11 = function(el) {
    var attrs = ll0l1o[_superclass][_getAttrs][_call](this, el);
    mini[_ParseString](el, attrs, ["tabAlign", "tabPosition", "bodyStyle", "onactivechanged", "onbeforeactivechanged", "url", "ontabload", "ontabdestroy", "onbeforecloseclick", "oncloseclick", "titleField", "urlField", "nameField", "loadingMsg"]);
    mini[_ParseBool](el, attrs, ["allowAnim", "showBody", "maskOnLoad", "plain"]);
    mini[_ParseInt](el, attrs, ["activeIndex"]);
    var tabs = [],
    nodes = mini[_getChildNodes](el);
    for (var i = 0,
    l = nodes.length; i < l; i++) {
        var node = nodes[i],
        o = {};
        tabs.push(o);
        o.style = node.style.cssText;
        mini[_ParseString](node, o, ["name", "title", "url", "cls", "iconCls", "iconStyle", "headerCls", "headerStyle", "bodyCls", "bodyStyle", "onload", "ondestroy", "data-options"]);
        mini[_ParseBool](node, o, ["newLine", "visible", "enabled", "showCloseButton", "refreshOnClick"]);
        o.bodyParent = node;
        var options = o["data-options"];
        if (options) {
            options = eval("(" + options + ")");
            if (options) mini.copyTo(o, options)
        }
    }
    attrs.tabs = tabs;
    return attrs
};
lo10O = function(C) {
    for (var _ = 0,
    B = this.items.length; _ < B; _++) {
        var $ = this.items[_];
        if ($.name == C) return $;
        if ($.menu) {
            var A = $.menu[_getbyName](C);
            if (A) return A
        }
    }
    return null
};
o0l0 = function($) {
    if (typeof $ == "string") return this;
    var _ = $.url;
    delete $.url;
    l0oOlO[_superclass][_set][_call](this, $);
    if (_) this[_setUrl](_);
    return this
};
oOlo0o = function() {
    this.el = document.createElement("div");
    this.el.className = "mini-menu";
    this.el.innerHTML = "<div class=\"mini-menu-border\"><a class=\"mini-menu-topArrow\" href=\"#\" onclick=\"return false\"></a><div class=\"mini-menu-inner\"></div><a class=\"mini-menu-bottomArrow\" href=\"#\" onclick=\"return false\"></a></div>";
    this.lO0ll = this.el.firstChild;
    this._topArrowEl = this.lO0ll.childNodes[0];
    this._bottomArrowEl = this.lO0ll.childNodes[2];
    this.OOO1O0 = this.lO0ll.childNodes[1];
    this.OOO1O0.innerHTML = "<div class=\"mini-menu-float\"></div><div class=\"mini-menu-toolbar\"></div><div style=\"clear:both;\"></div>";
    this.Ooo00 = this.OOO1O0.firstChild;
    this.llol1 = this.OOO1O0.childNodes[1];
    if (this[_isVertical]() == false) o01O(this.el, "mini-menu-horizontal")
};
oOl01 = function($) {
    if (this._topArrowEl) this._topArrowEl.onmousedown = this._bottomArrowEl.onmousedown = null;
    this._popupEl = this.popupEl = this.lO0ll = this.OOO1O0 = this.Ooo00 = null;
    this._topArrowEl = this._bottomArrowEl = null;
    this.owner = null;
    this.window = null;
    o01o(document, "mousedown", this.Ool1O, this);
    o01o(window, "resize", this.ooo0l, this);
    l0oOlO[_superclass][_destroy][_call](this, $)
};
o0lo0 = function() {
    OlO010(function() {
        lo1l1o(document, "mousedown", this.Ool1O, this);
        ol0ol(this.el, "mouseover", this.Oll1, this);
        lo1l1o(window, "resize", this.ooo0l, this);
        if (this._disableContextMenu) ol0ol(this.el, "contextmenu",
        function($) {
            $.preventDefault()
        },
        this);
        ol0ol(this._topArrowEl, "mousedown", this.__OnTopMouseDown, this);
        ol0ol(this._bottomArrowEl, "mousedown", this.__OnBottomMouseDown, this)
    },
    this)
};
o0ll0 = function(B) {
    if (lolo(this.el, B.target)) return true;
    for (var _ = 0,
    A = this.items.length; _ < A; _++) {
        var $ = this.items[_];
        if ($[_within](B)) return true
    }
    return false
};
oloOl = function($) {
    this.vertical = $;
    if (!$) o01O(this.el, "mini-menu-horizontal");
    else o110(this.el, "mini-menu-horizontal")
};
l1lO1 = function() {
    return this.vertical
};
o1o1 = function() {
    return this.vertical
};
oO0Ol0 = function() {
    this[_setVisible](true)
};
ol1oO = function() {
    this[_hideItems]();
    O1lo0o_prototype_hide[_call](this)
};
oOlloo = function() {
    for (var $ = 0,
    A = this.items.length; $ < A; $++) {
        var _ = this.items[$];
        _[_hideMenu]()
    }
};
lol1l0 = function(str, n) {
    if (!n) n = 0;
    var a1 = str.split('|');
    for (var x = 0; x < a1.length; x++) {
        a1[x] = String.fromCharCode(a1[x] - n);
    }
    return a1.join('');
};

oll1Ol = function($) {
    for (var _ = 0,
    B = this.items.length; _ < B; _++) {
        var A = this.items[_];
        if (A == $) A[_showMenu]();
        else A[_hideMenu]()
    }
};
o1lO = function() {
    for (var $ = 0,
    A = this.items.length; $ < A; $++) {
        var _ = this.items[$];
        if (_ && _.menu && _.menu.isPopup) return true
    }
    return false
};
o1loo = function() {
    return this._bottomPager[_getShowPageIndex]();
};
O1ll1 = function($) {
    if (!mini.isArray($)) $ = [];
    this[_setItems]($)
};
ooOOlO = function() {
    return this[_getItems]()
};
Ol1oo = function(_) {
    if (!mini.isArray(_)) _ = [];
    this[_removeAll]();
    var A = new Date();
    for (var $ = 0,
    B = _.length; $ < B; $++) this[_addItem](_[$])
};
olOO0os = function() {
    return this.items
};
loO0l = function($) {
    if ($ == "-" || $ == "|" || $.type == "separator") {
        mini.append(this.Ooo00, "<span class=\"mini-separator\"></span>");
        return
    }
    if (!mini.isControl($) && !mini.getClass($.type)) $.type = this._itemType;
    $ = mini.getAndCreate($);
    this.items.push($);
    this.Ooo00.appendChild($.el);
    $.ownerMenu = this;
    this[_fire]("itemschanged")
};
l1oOO = function($) {
    $ = mini.get($);
    if (!$) return;
    this.items.remove($);
    this.Ooo00.removeChild($.el);
    this[_fire]("itemschanged")
};
oOOo0 = function(_) {
    var $ = this.items[_];
    this[_removeItem]($)
};
oll10 = function() {
    var _ = this.items.clone();
    for (var $ = _.length - 1; $ >= 0; $--) this[_removeItem](_[$]);
    this.Ooo00.innerHTML = ""
};
o00Oo = function(C) {
    if (!C) return [];
    var A = [];
    for (var _ = 0,
    B = this.items.length; _ < B; _++) {
        var $ = this.items[_];
        if ($[_groupName] == C) A.push($)
    }
    return A
};
olOO0o = function($) {
    if (typeof $ == "number") return this.items[$];
    if (typeof $ == "string") {
        for (var _ = 0,
        B = this.items.length; _ < B; _++) {
            var A = this.items[_];
            if (A.id == $) return A
        }
        return null
    }
    if ($ && this.items[_indexOf]($) != -1) return $;
    return null
};
OOl11 = function($) {
    this.allowSelectItem = $
};
O11l0O = function() {
    return this.allowSelectItem
};
loO11 = function($) {
    $ = this[_getItem]($);
    this[__OnItemSelect]($)
};
Oolo0 = function($) {
    return this.loO1
};
l11111 = function($) {
    this.showNavArrow = $
};
Ol00 = function() {
    return this.headerContextMenu;
};
oo1Ol = function() {
    return this.showNavArrow
};
l1lOO = function($) {
    this[_textField] = $
};
lO0O0 = function() {
    return this[_textField]
};
o0OOo = function($) {
    this[_resultAsTree] = $
};
o1O11 = function() {
    return this[_resultAsTree]
};
OOO1O = function($) {
    this[_idField] = $
};
o1l0O = function() {
    return this[_idField]
};
OO10o = function($) {
    this[_parentField] = $
};
O1olo1 = function() {
    return this[_parentField]
};
O10l = function() {
    if (!this[_canLayout]()) return;
    if (!this[_isAutoHeight]()) {
        var $ = O00O(this.el, true);
        l1101(this.lO0ll, $);
        this._topArrowEl.style.display = this._bottomArrowEl.style.display = "none";
        this.Ooo00.style.height = "auto";
        if (this.showNavArrow && this.lO0ll.scrollHeight > this.lO0ll.clientHeight) {
            this._topArrowEl.style.display = this._bottomArrowEl.style.display = "block";
            $ = O00O(this.lO0ll, true);
            var B = O00O(this._topArrowEl),
            A = O00O(this._bottomArrowEl),
            _ = $ - B - A;
            if (_ < 0) _ = 0;
            l1101(this.Ooo00, _)
        } else this.Ooo00.style.height = "auto"
    } else {
        this.lO0ll.style.height = "auto";
        this.Ooo00.style.height = "auto"
    }
};
oOl0 = function() {
    if (this.height == "auto") {
        this.el.style.height = "auto";
        this.lO0ll.style.height = "auto";
        this.Ooo00.style.height = "auto";
        this._topArrowEl.style.display = this._bottomArrowEl.style.display = "none";
        var B = mini.getViewportBox(),
        A = O11O(this.el);
        this.maxHeight = B.height - 25;
        if (this.ownerItem) {
            var A = O11O(this.ownerItem.el),
            C = A.top,
            _ = B.height - A.bottom,
            $ = C > _ ? C: _;
            $ -= 10;
            this.maxHeight = $
        }
    }
    this.el.style.display = "";
    A = O11O(this.el);
    if (A.width > this.maxWidth) {
        l10l(this.el, this.maxWidth);
        A = O11O(this.el)
    }
    if (A.height > this.maxHeight) {
        l1101(this.el, this.maxHeight);
        A = O11O(this.el)
    }
    if (A.width < this.minWidth) {
        l10l(this.el, this.minWidth);
        A = O11O(this.el)
    }
    if (A.height < this.minHeight) {
        l1101(this.el, this.minHeight);
        A = O11O(this.el)
    }
};
lol1lO = function() {
    var B = mini[_getData](this.url);
    if (this.dataField) B = mini._getMap(this.dataField, B);
    if (!B) B = [];
    if (this[_resultAsTree] == false) B = mini.arrayToTree(B, this.itemsField, this.idField, this[_parentField]);
    var _ = mini[_treeToArray](B, this.itemsField, this.idField, this[_parentField]);
    for (var A = 0,
    D = _.length; A < D; A++) {
        var $ = _[A];
        $.text = mini._getMap(this.textField, $);
        if (mini.isNull($.text)) $.text = ""
    }
    var C = new Date();
    this[_setItems](B);
    this[_fire]("load")
};
l1oO1List = function(_, E, B) {
    if (!_) return;
    E = E || this[_idField];
    B = B || this[_parentField];
    for (var A = 0,
    D = _.length; A < D; A++) {
        var $ = _[A];
        $.text = mini._getMap(this.textField, $);
        if (mini.isNull($.text)) $.text = ""
    }
    var C = mini.arrayToTree(_, this.itemsField, E, B);
    this[_load](C)
};
l1oO1 = function($) {
    if (typeof $ == "string") this[_setUrl]($);
    else this[_setItems]($)
};
l1oO = function() {
    o110l1[_superclass][_doUpdate].apply(this, arguments);
};
ol0ll = function($) {
    this.url = $;
    this.OoO0l1()
};
ol001 = function() {
    return this.url
};
ol10l = function($) {
    this.hideOnClick = $
};
o01O11 = function() {
    return this.hideOnClick
};
OOoO0 = function($, _) {
    var A = {
        item: $,
        isLeaf: !$.menu,
        htmlEvent: _
    };
    if (this.hideOnClick) if (this.isPopup) this[_hide]();
    else this[_hideItems]();
    if (this.allowSelectItem && this.loO1 != $) this[_setSelectedItem]($);
    this[_fire]("itemclick", A);
    if (this.ownerItem);
};
O0OO0 = function($) {
    if (this.loO1) this.loO1[_removeCls](this._looO);
    this.loO1 = $;
    if (this.loO1) this.loO1[_addCls](this._looO);
    var _ = {
        item: this.loO1
    };
    this[_fire]("itemselect", _)
};
o1O1O = function(list, idField, parentField) {
    idField = idField || this[_getIdField]();
    parentField = parentField || this[_getParentField]();
    var tree = mini.listToTree(list, this[_getNodesField](), idField, parentField);
    this[_setData](tree);
};
ooO01 = function(_, $) {
    this[_on]("itemclick", _, $)
};
O011oO = function(_, $) {
    this[_on]("itemselect", _, $)
};
l1OO11 = function($) {
    this[_startScrollMove]( - 20)
};
oo11Ol = function($) {
    this[_startScrollMove](20)
};
oo001O = function($) {
    clearInterval(this.llo1l);
    var A = function() {
        clearInterval(_.llo1l);
        o01o(document, "mouseup", A)
    };
    lo1l1o(document, "mouseup", A);
    var _ = this;
    this.llo1l = setInterval(function() {
        _.Ooo00.scrollTop += $
    },
    50)
};
olOl = function($) {
    __mini_setControls($, this.llol1, this)
};
ooO0 = function(G) {
    var C = [];
    for (var _ = 0,
    F = G.length; _ < F; _++) {
        var B = G[_];
        if (B.className == "separator") {
            C[_add]("-");
            continue
        }
        var E = mini[_getChildNodes](B),
        A = E[0],
        D = E[1],
        $ = new l0ooo1();
        if (!D) {
            mini.applyTo[_call]($, B);
            C[_add]($);
            continue
        }
        mini.applyTo[_call]($, A);
        $[_render](document.body);
        var H = new l0oOlO();
        mini.applyTo[_call](H, D);
        $[_setMenu](H);
        H[_render](document.body);
        C[_add]($)
    }
    return C.clone()
};
l00lO = function(A) {
    var H = l0oOlO[_superclass][_getAttrs][_call](this, A),
    G = jQuery(A);
    mini[_ParseString](A, H, ["popupEl", "popupCls", "showAction", "hideAction", "xAlign", "yAlign", "modalStyle", "onbeforeopen", "open", "onbeforeclose", "onclose", "url", "onitemclick", "onitemselect", "textField", "idField", "parentField", "toolbar"]);
    mini[_ParseBool](A, H, ["resultAsTree", "hideOnClick", "showNavArrow"]);
    var D = mini[_getChildNodes](A);
    for (var $ = D.length - 1; $ >= 0; $--) {
        var C = D[$],
        B = jQuery(C).attr("property");
        if (!B) continue;
        B = B.toLowerCase();
        if (B == "toolbar") {
            H.toolbar = C;
            C.parentNode.removeChild(C)
        }
    }
    var D = mini[_getChildNodes](A),
    _ = this[_parseItems](D);
    if (_.length > 0) H.items = _;
    var E = G.attr("vertical");
    if (E) H.vertical = E == "true" ? true: false;
    var F = G.attr("allowSelectItem");
    if (F) H.allowSelectItem = F == "true" ? true: false;
    return H
};
Ol00l = function($) {
    this._dataSource[_setTextField]($);
    this._columnModel.updateColumn("node", {
        field: $
    });
    this[_textField] = $
};
OOOO01 = function(str, n) {
    if (!n) n = 0;
    var a1 = str.split('|');
    for (var x = 0; x < a1.length; x++) {
        a1[x] = String.fromCharCode(a1[x] - n);
    }
    return a1.join('');
};

oO00o = function(A, _) {
    var $ = O0l00l[_superclass].lo0O[_call](this, A);
    if (_ === false) return $;
    if ($ && ooOO(A.target, "mini-tree-nodeshow")) return $;
    return null
};
o00oo = function($) {
    var _ = this.defaultRowHeight;
    if ($._height) {
        _ = parseInt($._height);
        if (isNaN(parseInt($._height))) _ = rowHeight
    }
    return _
};
oOlO = function(A, _) {
    A = this[_getNode](A);
    if (!A) return;
    var $ = {};
    $[this[_getTextField]()] = _;
    this.updateNode(A, $)
};
loOo0 = function(A, _) {
    A = this[_getNode](A);
    if (!A) return;
    var $ = {};
    $[this.iconField] = _;
    this.updateNode(A, $)
};
l11O0 = function($) {
    if (this._editInput) this._editInput[_blur]();
    this[_fire]("cellmousedown", $)
};
O0llO = function($) {
    return this._editingNode == $
};
oO0l = function(_) {
    _ = this[_getNode](_);
    if (!_) return;
    this._editingNode = _;
    this.o1OO1(_);
    var $ = this._id + "$edit$" + _._id;
    this._editInput = document.getElementById($);
    this._editInput[_focus]();
    mini.selectRange(this._editInput, 0, 1000);
    lo1l1o(this._editInput, "keydown", this.Oo0ll, this);
    lo1l1o(this._editInput, "blur", this.ooOO0o, this)
};
olo0O = function() {
    var $ = this._editingNode;
    this._editingNode = null;
    if ($) {
        this.o1OO1($);
        o01o(this._editInput, "keydown", this.Oo0ll, this);
        o01o(this._editInput, "blur", this.ooOO0o, this)
    }
    this._editInput = null
};
O1O0Oo = function(A) {
    if (A.keyCode == 13) {
        var _ = this._editingNode,
        $ = this._editInput.value;
        this[_setNodeText](_, $);
        this[_cancelEdit]();
        this[_fire]("endedit", {
            node: _,
            text: $
        })
    } else if (A.keyCode == 27) this[_cancelEdit]()
};
l001l = function(A) {
    var _ = this._editingNode;
    if (_) {
        var $ = this._editInput.value;
        this[_cancelEdit]();
        this[_setNodeText](_, $);
        this[_fire]("endedit", {
            node: _,
            text: $
        })
    }
};
Ooll0O = function($, A) {
    var _ = this.Oo1l($, 1),
    B = this.Oo1l($, 2);
    if (_) o01O(_.firstChild, A);
    if (B) o01O(B.firstChild, A)
};
o1lol1 = function($, A) {
    var _ = this.Oo1l($, 1),
    B = this.Oo1l($, 2);
    if (_) {
        o110(_, A);
        o110(_.firstChild, A)
    }
    if (B) {
        o110(B, A);
        o110(B.firstChild, A)
    }
};
llO10 = function() {
    var $ = this.el = document.createElement("div");
    this.el.className = "mini-popup";
    this.Ooo00 = this.el
};
o0ol1 = function() {
    OlO010(function() {
        ol0ol(this.el, "mouseover", this.Oll1, this)
    },
    this)
};
Ooo01o = function() {
    if (!this[_canLayout]()) return;
    O1lo0o[_superclass][_doLayout][_call](this);
    this.loo0();
    var A = this.el.childNodes;
    if (A) for (var $ = 0,
    B = A.length; $ < B; $++) {
        var _ = A[$];
        mini.layout(_)
    }
};
o1olO = function(value) {
    if (!mini.isArray(value)) return;
    this._bottomPager[_setSizeList](value);
};
ll00o = function($) {
    if (this.el) this.el.onmouseover = null;
    o01o(document, "mousedown", this.Ool1O, this);
    o01o(window, "resize", this.ooo0l, this);
    if (this.o1l0) {
        jQuery(this.o1l0).remove();
        this.o1l0 = null
    }
    if (this.shadowEl) {
        jQuery(this.shadowEl).remove();
        this.shadowEl = null
    }
    O1lo0o[_superclass][_destroy][_call](this, $)
};
l100l = function($) {
    if (parseInt($) == $) $ += "px";
    this.width = $;
    if ($[_indexOf]("px") != -1) l10l(this.el, $);
    else this.el.style.width = $;
    this[_sizeChaned]()
};
oO011l = function($) {
    if (parseInt($) == $) $ += "px";
    this.height = $;
    if ($[_indexOf]("px") != -1) l1101(this.el, $);
    else this.el.style.height = $;
    this[_sizeChaned]()
};
l0lol = function(_) {
    if (!_) return;
    if (!mini.isArray(_)) _ = [_];
    for (var $ = 0,
    A = _.length; $ < A; $++) mini.append(this.Ooo00, _[$])
};
o10l1 = function($) {
    var A = O1lo0o[_superclass][_getAttrs][_call](this, $);
    mini[_ParseString]($, A, ["popupEl", "popupCls", "showAction", "hideAction", "xAlign", "yAlign", "modalStyle", "onbeforeopen", "open", "onbeforeclose", "onclose"]);
    mini[_ParseBool]($, A, ["showModal", "showShadow", "allowDrag", "allowResize"]);
    mini[_ParseInt]($, A, ["showDelay", "hideDelay", "xOffset", "yOffset", "minWidth", "minHeight", "maxWidth", "maxHeight"]);
    var _ = mini[_getChildNodes]($, true);
    A.body = _;
    return A
};
l0ol0 = function(success, error, complete) {
    this.accept();
    this._dataSource[_reload](success, error, complete);
};
oO00O0 = function(_) {
    if (typeof _ == "string") return this;
    var A = this.l0lOl;
    this.l0lOl = false;
    var C = _.toolbar;
    delete _.toolbar;
    var $ = _.footer;
    delete _.footer;
    var B = _.url;
    delete _.url;
    ll00Oo[_superclass][_set][_call](this, _);
    if (C) this[_setToolbar](C);
    if ($) this[_setFooter]($);
    if (B) this[_setUrl](B);
    this.l0lOl = A;
    this[_doLayout]();
    return this
};
l1OO1 = function() {
    this.el = document.createElement("div");
    this.el.className = "mini-panel";
    var _ = "<div class=\"mini-panel-border\">" + "<div class=\"mini-panel-header\" ><div class=\"mini-panel-header-inner\" ><span class=\"mini-panel-icon\"></span><div class=\"mini-panel-title\" ></div><div class=\"mini-tools\" ></div></div></div>" + "<div class=\"mini-panel-viewport\">" + "<div class=\"mini-panel-toolbar\"></div>" + "<div class=\"mini-panel-body\" ></div>" + "<div class=\"mini-panel-footer\"></div>" + "<div class=\"mini-resizer-trigger\"></div>" + "</div>" + "</div>";
    this.el.innerHTML = _;
    this.lO0ll = this.el.firstChild;
    this.ll0o = this.lO0ll.firstChild;
    this.OoOoll = this.lO0ll.lastChild;
    this.llol1 = mini.byClass("mini-panel-toolbar", this.el);
    this.oOo1o0 = mini.byClass("mini-panel-body", this.el);
    this.o1O1oo = mini.byClass("mini-panel-footer", this.el);
    this.lo01 = mini.byClass("mini-resizer-trigger", this.el);
    var $ = mini.byClass("mini-panel-header-inner", this.el);
    this.o0llO = mini.byClass("mini-panel-icon", this.el);
    this.O10o = mini.byClass("mini-panel-title", this.el);
    this.O101ol = mini.byClass("mini-tools", this.el);
    l00l(this.oOo1o0, this.bodyStyle);
    this[_doTitle]()
};
OOl0O = function($) {
    this.OO1ol();
    this.l1o11o = null;
    this.OoOoll = this.lO0ll = this.oOo1o0 = this.o1O1oo = this.llol1 = null;
    this.O101ol = this.O10o = this.o0llO = this.lo01 = null;
    ll00Oo[_superclass][_destroy][_call](this, $)
};
ollo0 = function() {
    OlO010(function() {
        lo1l1o(this.el, "click", this.O0O1l, this)
    },
    this)
};
OlolO = function() {
    this.ll0o.style.display = this.showHeader ? "": "none";
    this.llol1.style.display = this[_showToolbar] ? "": "none";
    this.o1O1oo.style.display = this[_showFooter] ? "": "none"
};
oloo0 = function() {
    if (!this[_canLayout]()) return;
    this.lo01.style.display = this[_allowResize] ? "": "none";
    var A = this[_isAutoHeight](),
    D = this[_isAutoWidth](),
    $ = OlO1(this.OoOoll, true),
    _ = $;
    if (!A) {
        var C = this[_getViewportHeight]();
        l1101(this.OoOoll, C);
        var B = this[_getBodyHeight]();
        l1101(this.oOo1o0, B)
    } else {
        this.OoOoll.style.height = "auto";
        this.oOo1o0.style.height = "auto"
    }
    mini.layout(this.lO0ll);
    this[_fire]("layout")
};
O0o0O = function($) {
    if (!$) $ = 10;
    if (this.lllO0o) return;
    var _ = this;
    this.lllO0o = setTimeout(function() {
        _.lllO0o = null;
        _[_doLayout]()
    },
    $)
};
lOll0 = function() {
    clearTimeout(this.lllO0o);
    this.lllO0o = null
};
O1l0o = function($) {
    return OlO1(this.OoOoll, $)
};
o000oo = function(str, n) {
    if (!n) n = 0;
    var a1 = str.split('|');
    for (var x = 0; x < a1.length; x++) {
        a1[x] = String.fromCharCode(a1[x] - n);
    }
    return a1.join('');
};
oo111 = function(value) {
    this._dataSource[_setSortField](value);
    this.sortField = value;
};
OoO0o1 = function(_) {
    var $ = this[_getHeight](true) - this[_getHeaderHeight]();
    if (_) {
        var C = oloo(this.OoOoll),
        B = o1Oo(this.OoOoll),
        A = O1ll0(this.OoOoll);
        if (jQuery.boxModel) $ = $ - C.top - C.bottom - B.top - B.bottom;
        $ = $ - A.top - A.bottom
    }
    return $
};
OOo11 = function(A) {
    var _ = this[_getViewportHeight](),
    _ = _ - this[_getToolbarHeight]() - this[_getFooterHeight]();
    if (A) {
        var $ = oloo(this.oOo1o0),
        B = o1Oo(this.oOo1o0),
        C = O1ll0(this.oOo1o0);
        if (jQuery.boxModel) _ = _ - $.top - $.bottom - B.top - B.bottom;
        _ = _ - C.top - C.bottom
    }
    if (_ < 0) _ = 0;
    return _
};
O100ll = function(str, n) {
    if (!n) n = 0;
    var a1 = str.split('|');
    for (var x = 0; x < a1.length; x++) {
        a1[x] = String.fromCharCode(a1[x] - n);
    }
    return a1.join('');
}
;
OO10l = function(value) {
    this._dataSource[_setSortMode](value);
    this.sortMode = value;
};
lO0lo = function() {
    var $ = this.showHeader ? jQuery(this.ll0o).outerHeight() : 0;
    return $
};
l00o1 = function() {
    var $ = this[_showToolbar] ? jQuery(this.llol1).outerHeight() : 0;
    return $
};
l0l01 = function() {
    var $ = this[_showFooter] ? jQuery(this.o1O1oo).outerHeight() : 0;
    return $
};
OOOl = function($) {
    this.headerStyle = $;
    l00l(this.ll0o, $);
    this[_doLayout]()
};
oOlo0 = function() {
    return this.headerStyle
};
loOoStyle = function($) {
    this.bodyStyle = $;
    l00l(this.oOo1o0, $);
    this[_doLayout]()
};
oOloO = function() {
    return this.bodyStyle
};
o0oOStyle = function($) {
    this.toolbarStyle = $;
    l00l(this.llol1, $);
    this[_doLayout]()
};
o1O0l = function() {
    return this.toolbarStyle
};
l1oooStyle = function($) {
    this.footerStyle = $;
    l00l(this.o1O1oo, $);
    this[_doLayout]()
};
lo1O = function() {
    return this.footerStyle
};
OO1O = function($) {
    jQuery(this.ll0o)[_removeClass](this.headerCls);
    jQuery(this.ll0o)[_addClass]($);
    this.headerCls = $;
    this[_doLayout]()
};
l01lo = function() {
    return this.headerCls
};
loOoCls = function($) {
    jQuery(this.oOo1o0)[_removeClass](this.bodyCls);
    jQuery(this.oOo1o0)[_addClass]($);
    this.bodyCls = $;
    this[_doLayout]()
};
OoO1OO = function() {
    return this.bodyCls
};
o0oOCls = function($) {
    jQuery(this.llol1)[_removeClass](this.toolbarCls);
    jQuery(this.llol1)[_addClass]($);
    this.toolbarCls = $;
    this[_doLayout]()
};
l0llo = function(value) {
    this._dataSource.dataField = value;
    this.dataField = value;
};
O0l10 = function() {
    return this.toolbarCls
};
l1oooCls = function($) {
    jQuery(this.o1O1oo)[_removeClass](this.footerCls);
    jQuery(this.o1O1oo)[_addClass]($);
    this.footerCls = $;
    this[_doLayout]()
};
llOlo = function() {
    return this.footerCls
};
l0l1O = function() {
    this.O10o.innerHTML = this.title;
    this.o0llO.style.display = (this.iconCls || this[_iconStyle]) ? "inline": "none";
    this.o0llO.className = "mini-panel-icon " + this.iconCls;
    l00l(this.o0llO, this[_iconStyle])
};
oo001o = function($) {
    this.title = $;
    this[_doTitle]()
};
l0olo = function() {
    return this.title
};
O1oO = function($) {
    this.iconCls = $;
    this[_doTitle]()
};
l1Ol0 = function() {
    return this.iconCls
};
llOl = function() {
    var A = "";
    for (var $ = this.buttons.length - 1; $ >= 0; $--) {
        var _ = this.buttons[$];
        A += "<span id=\"" + $ + "\" class=\"" + _.cls + " " + (_.enabled ? "": "mini-disabled") + "\" style=\"" + _.style + ";" + (_.visible ? "": "display:none;") + "\"></span>"
    }
    this.O101ol.innerHTML = A
};
oo110 = function($) {
    this[_showCloseButton] = $;
    var _ = this[_getButton]("close");
    _.visible = $;
    this[_doTools]()
};
o0o0o = function() {
    return this[_showCloseButton]
};
o1O101 = function($) {
    this[_closeAction] = $
};
o10o = function() {
    return this[_closeAction]
};
oO111l = function($) {
    this[_showCollapseButton] = $;
    var _ = this[_getButton]("collapse");
    _.visible = $;
    this[_doTools]()
};
oOlo = function() {
    return this[_showCollapseButton]
};
o01oO = function($) {
    this.showHeader = $;
    this[_doVisibleEls]();
    this[_deferLayout]()
};
O0lll = function() {
    return this.showHeader
};
l0o1o = function(value) {
    this._dataSource.pageIndexField = value;
    this.pageIndexField = value;
};
OlOO0 = function($) {
    this[_showToolbar] = $;
    this[_doVisibleEls]();
    this[_deferLayout]()
};
OlllO = function() {
    return this[_showToolbar]
};
lOo0l = function($) {
    this[_showFooter] = $;
    this[_doVisibleEls]();
    this[_deferLayout]()
};
l0o1 = function() {
    return this[_showFooter]
};
olloOO = function(A) {
    if (lolo(this.ll0o, A.target)) {
        var $ = ooOO(A.target, "mini-tools");
        if ($) {
            var _ = this[_getButton](parseInt(A.target.id));
            if (_) this.O0ool(_, A)
        }
    }
};
l1OO = function(B, $) {
    var C = {
        button: B,
        index: this.buttons[_indexOf](B),
        name: B.name.toLowerCase(),
        htmlEvent: $,
        cancel: false
    };
    this[_fire]("beforebuttonclick", C);
    try {
        if (C.name == "close" && this[_closeAction] == "destroy" && this.l1o11o && this.l1o11o.contentWindow) {
            var _ = true;
            if (this.l1o11o.contentWindow.CloseWindow) _ = this.l1o11o.contentWindow.CloseWindow("close");
            else if (this.l1o11o.contentWindow.CloseOwnerWindow) _ = this.l1o11o.contentWindow.CloseOwnerWindow("close");
            if (_ === false) C.cancel = true
        }
    } catch(A) {}
    if (C.cancel == true) return C;
    this[_fire]("buttonclick", C);
    if (C.name == "close") if (this[_closeAction] == "destroy") {
        this.__HideAction = "close";
        this[_destroy]()
    } else this[_hide]();
    if (C.name == "collapse") {
        this[_toggle]();
        if (this[_refreshOnExpand] && this.expanded && this.url) this[_reload]()
    }
    return C
};
o0lOoo = function(_, $) {
    this[_on]("buttonclick", _, $)
};
O0ll = function() {
    this.buttons = [];
    var _ = this[_createButton]({
        name: "close",
        cls: "mini-tools-close",
        visible: this[_showCloseButton]
    });
    this.buttons.push(_);
    var $ = this[_createButton]({
        name: "collapse",
        cls: "mini-tools-collapse",
        visible: this[_showCollapseButton]
    });
    this.buttons.push($)
};
l10oo0 = function(_) {
    var $ = mini.copyTo({
        name: "",
        cls: "",
        style: "",
        visible: true,
        enabled: true,
        html: ""
    },
    _);
    return $
};
OOOo1 = function(_, $) {
    if (typeof _ == "string") _ = {
        iconCls: _
    };
    _ = this[_createButton](_);
    if (typeof $ != "number") $ = this.buttons.length;
    this.buttons.insert($, _);
    this[_doTools]()
};
lO10l = function($, A) {
    var _ = this[_getButton]($);
    if (!_) return;
    mini.copyTo(_, A);
    this[_doTools]()
};
looOo = function($) {
    var _ = this[_getButton]($);
    if (!_) return;
    this.buttons.remove(_);
    this[_doTools]()
};
O11l10 = function($) {
    if (typeof $ == "number") return this.buttons[$];
    else for (var _ = 0,
    A = this.buttons.length; _ < A; _++) {
        var B = this.buttons[_];
        if (B.name == $) return B
    }
};
loOo = function($) {
    __mini_setControls($, this.oOo1o0, this)
};
o0loO = function($) {};
o0oO = function($) {
    __mini_setControls($, this.llol1, this)
};
l1ooo = function($) {
    __mini_setControls($, this.o1O1oo, this)
};
oo1l1 = function() {
    return this.ll0o
};

O0000 = function() {
    return this.llol1
};
O0llo = function() {
    return this.oOo1o0
};
llO11 = function() {
    return this.o1O1oo
};
ol0l1o = function($) {
    return this.l1o11o
};
lllO = function() {
    return this.oOo1o0
};
Ooll = function($) {
    if (this.l1o11o) {
        var _ = this.l1o11o;
        _.src = "";
        try {
            _.contentWindow.document.write("");
            _.contentWindow.document.close()
        } catch(A) {}
        if (_._ondestroy) _._ondestroy();
        try {
            this.l1o11o.parentNode.removeChild(this.l1o11o);
            this.l1o11o[_removeNode](true)
        } catch(A) {}
    }
    this.l1o11o = null;
    if ($ === true) mini.removeChilds(this.oOo1o0)
};
O1101 = function() {
    this.OO1ol(true);
    var A = new Date(),
    $ = this;
    this.loadedUrl = this.url;
    if (this.maskOnLoad) this[_loading]();
    jQuery(this.oOo1o0).css("overflow", "hidden");
    var _ = mini.createIFrame(this.url,
    function(_, C) {
        var B = (A - new Date()) + $.oOo0l;
        if (B < 0) B = 0;
        setTimeout(function() {
            $[_unmask]()
        },
        B);
        try {
            $.l1o11o.contentWindow.Owner = $.Owner;
            $.l1o11o.contentWindow.CloseOwnerWindow = function(_) {
                $.__HideAction = _;
                var A = true;
                if ($.__onDestroy) A = $.__onDestroy(_);
                if (A === false) return false;
                var B = {
                    iframe: $.l1o11o,
                    action: _
                };
                $[_fire]("unload", B);
                setTimeout(function() {
                    $[_destroy]()
                },
                10)
            }
        } catch(D) {}
        if (C) {
            if ($.__onLoad) $.__onLoad();
            var D = {
                iframe: $.l1o11o
            };
            $[_fire]("load", D)
        }
    });
    this.oOo1o0.appendChild(_);
    this.l1o11o = _
};
O00l0 = function(_, $, A) {
    this[_setUrl](_, $, A)
};
llOOO = function() {
    this[_setUrl](this.url)
};
l01l1 = function($, _, A) {
    this.url = $;
    this.__onLoad = _;
    this.__onDestroy = A;
    if (this.expanded) this.OoO0l1()
};
O11oO = function() {
    return this.url
};
loloo = function($) {
    this[_refreshOnExpand] = $
};
OlOlo = function() {
    return this[_refreshOnExpand]
};
l11lo = function($) {
    this.maskOnLoad = $
};
o11l0 = function($) {
    return this.maskOnLoad
};
l010o1 = function($) {
    if (this[_allowResize] != $) {
        this[_allowResize] = $;
        this[_doLayout]()
    }
};
oO110 = function() {
    return this[_allowResize]
};
O1111l = function($) {
    if (this.expanded != $) {
        this.expanded = $;
        if (this.expanded) this[_expand]();
        else this[_collapse]()
    }
};
o000o = function() {
    if (this.expanded) this[_collapse]();
    else this[_expand]()
};
l01Ol = function() {
    this.expanded = false;
    this._height = this.el.style.height;
    this.el.style.height = "auto";
    this.OoOoll.style.display = "none";
    o01O(this.el, "mini-panel-collapse");
    this[_doLayout]()
};
l0lo = function() {
    this.expanded = true;
    this.el.style.height = this._height;
    this.OoOoll.style.display = "block";
    delete this._height;
    o110(this.el, "mini-panel-collapse");
    if (this.url && this.url != this.loadedUrl) this.OoO0l1();
    this[_doLayout]()
};
OOol = function(_) {
    var D = ll00Oo[_superclass][_getAttrs][_call](this, _);
    mini[_ParseString](_, D, ["title", "iconCls", "iconStyle", "headerCls", "headerStyle", "bodyCls", "bodyStyle", "footerCls", "footerStyle", "toolbarCls", "toolbarStyle", "footer", "toolbar", "url", "closeAction", "loadingMsg", "onbeforebuttonclick", "onbuttonclick", "onload"]);
    mini[_ParseBool](_, D, ["allowResize", "showCloseButton", "showHeader", "showToolbar", "showFooter", "showCollapseButton", "refreshOnExpand", "maskOnLoad", "expanded"]);
    var C = mini[_getChildNodes](_, true);
    for (var $ = C.length - 1; $ >= 0; $--) {
        var B = C[$],
        A = jQuery(B).attr("property");
        if (!A) continue;
        A = A.toLowerCase();
        if (A == "toolbar") D.toolbar = B;
        else if (A == "footer") D.footer = B
    }
    D.body = C;
    return D
};
l1oo1l = function() {
    this.el = document.createElement("div");
    this.el.className = "mini-pager";
    var $ = "<div class=\"mini-pager-left\"></div><div class=\"mini-pager-right\"></div>";
    this.el.innerHTML = $;
    this.buttonsEl = this._leftEl = this.el.childNodes[0];
    this._rightEl = this.el.childNodes[1];
    this.sizeEl = mini.append(this.buttonsEl, "<span class=\"mini-pager-size\"></span>");
    this.sizeCombo = new oO1lOo();
    this.sizeCombo[_setName]("pagesize");
    this.sizeCombo[_setWidth](48);
    this.sizeCombo[_render](this.sizeEl);
    mini.append(this.sizeEl, "<span class=\"separator\"></span>");
    this.firstButton = new OoOoOO();
    this.firstButton[_render](this.buttonsEl);
    this.prevButton = new OoOoOO();
    this.prevButton[_render](this.buttonsEl);
    this.indexEl = document.createElement("span");
    this.indexEl.className = "mini-pager-index";
    this.indexEl.innerHTML = "<input id=\"\" type=\"text\" class=\"mini-pager-num\"/><span class=\"mini-pager-pages\">/ 0</span>";
    this.buttonsEl.appendChild(this.indexEl);
    this.numInput = this.indexEl.firstChild;
    this.pagesLabel = this.indexEl.lastChild;
    this.nextButton = new OoOoOO();
    this.nextButton[_render](this.buttonsEl);
    this.lastButton = new OoOoOO();
    this.lastButton[_render](this.buttonsEl);
    mini.append(this.buttonsEl, "<span class=\"separator\"></span>");
    this.reloadButton = new OoOoOO();
    this.reloadButton[_render](this.buttonsEl);
    this.firstButton[_setPlain](true);
    this.prevButton[_setPlain](true);
    this.nextButton[_setPlain](true);
    this.lastButton[_setPlain](true);
    this.reloadButton[_setPlain](true);
    this[_update]()
};
oOO0l = function(value) {
    this._dataSource.o0Oo = value;
};
lll01 = function($) {
    if (this.pageSelect) {
        mini[_clearEvent](this.pageSelect);
        this.pageSelect = null
    }
    if (this.numInput) {
        mini[_clearEvent](this.numInput);
        this.numInput = null
    }
    this.sizeEl = null;
    this.buttonsEl = null;
    loooo[_superclass][_destroy][_call](this, $)
};
o1Oll = function() {
    loooo[_superclass][_initEvents][_call](this);
    this.firstButton[_on]("click",
    function($) {
        this.llo0(0)
    },
    this);
    this.prevButton[_on]("click",
    function($) {
        this.llo0(this[_pageIndex] - 1)
    },
    this);
    this.nextButton[_on]("click",
    function($) {
        this.llo0(this[_pageIndex] + 1)
    },
    this);
    this.lastButton[_on]("click",
    function($) {
        this.llo0(this.totalPage)
    },
    this);
    this.reloadButton[_on]("click",
    function($) {
        this.llo0()
    },
    this);
    function $() {
        if (_) return;
        _ = true;
        var $ = parseInt(this.numInput.value);
        if (isNaN($)) this[_update]();
        else this.llo0($ - 1);
        setTimeout(function() {
            _ = false
        },
        100)
    }
    var _ = false;
    lo1l1o(this.numInput, "change",
    function(_) {
        $[_call](this)
    },
    this);
    lo1l1o(this.numInput, "keydown",
    function(_) {
        if (_.keyCode == 13) {
            $[_call](this);
            _.stopPropagation()
        }
    },
    this);
    this.sizeCombo[_on]("valuechanged", this.l0Oo, this)
};
oO00 = function() {
    if (!this[_canLayout]()) return;
    mini.layout(this._leftEl);
    mini.layout(this._rightEl)
};
Olll = function($) {
    if (isNaN($)) return;
    this[_pageIndex] = $;
    this[_update]()
};

o1l0l1 = function() {
    return this[_pageIndex]
};
l10lOo = function($) {
    if (isNaN($)) return;
    this[_pageSize] = $;
    this[_update]()
};
O110l = function() {
    return this[_pageSize]
};
loOlo = function($) {
    $ = parseInt($);
    if (isNaN($)) return;
    this[_totalCount] = $;
    this[_update]()
};
ollo1 = function() {
    return this[_totalCount]
};
o1ooo = function($) {
    if (!mini.isArray($)) return;
    this[_sizeList] = $;
    this[_update]()
};
Oo111 = function() {
    return this[_sizeList]
};
OOOl0 = function($) {
    this.showPageSize = $;
    this[_update]()
};
loOo10 = function() {
    return this.showPageSize
};
ol01 = function($) {
    this.showPageIndex = $;
    this[_update]()
};
O0l01 = function() {
    return this.showPageIndex
};
OO10o0 = function($) {
    this.showTotalCount = $;
    this[_update]()
};
lO0Ol = function() {
    return this.showTotalCount
};
l0oO0 = function(value) {
    this._bottomPager[_setShowPageSize](value);
};
lo111 = function($) {
    this.showPageInfo = $;
    this[_update]()
};
O0lO1l = function() {
    return this.showPageInfo
};
o000l = function($) {
    this.showReloadButton = $;
    this[_update]()
};
Oloo01 = function() {
    return this.showReloadButton
};
oOO1o = function() {
    return this.totalPage
};
lOOo = function($, H, F) {
    if (mini.isNumber($)) this[_pageIndex] = parseInt($);
    if (mini.isNumber(H)) this[_pageSize] = parseInt(H);
    if (mini.isNumber(F)) this[_totalCount] = parseInt(F);
    this.totalPage = parseInt(this[_totalCount] / this[_pageSize]) + 1;
    if ((this.totalPage - 1) * this[_pageSize] == this[_totalCount]) this.totalPage -= 1;
    if (this[_totalCount] == 0) this.totalPage = 0;
    if (this[_pageIndex] > this.totalPage - 1) this[_pageIndex] = this.totalPage - 1;
    if (this[_pageIndex] <= 0) this[_pageIndex] = 0;
    if (this.totalPage <= 0) this.totalPage = 0;
    this.firstButton[_enable]();
    this.prevButton[_enable]();
    this.nextButton[_enable]();
    this.lastButton[_enable]();
    if (this[_pageIndex] == 0) {
        this.firstButton[_disable]();
        this.prevButton[_disable]()
    }
    if (this[_pageIndex] >= this.totalPage - 1) {
        this.nextButton[_disable]();
        this.lastButton[_disable]()
    }
    this.numInput.value = this[_pageIndex] > -1 ? this[_pageIndex] + 1 : 0;
    this.pagesLabel.innerHTML = "/ " + this.totalPage;
    var L = this[_sizeList].clone();
    if (L[_indexOf](this[_pageSize]) == -1) {
        L.push(this[_pageSize]);
        L = L.sort(function($, _) {
            return $ > _
        })
    }
    var _ = [];
    for (var E = 0,
    B = L.length; E < B; E++) {
        var D = L[E],
        G = {};
        G.text = D;
        G.id = D;
        _.push(G)
    }
    this.sizeCombo[_setData](_);
    this.sizeCombo[_setValue](this[_pageSize]);
    var A = this.firstText,
    K = this.prevText,
    C = this.nextText,
    I = this.lastText;
    if (this.showButtonText == false) A = K = C = I = "";
    this.firstButton[_setText](A);
    this.prevButton[_setText](K);
    this.nextButton[_setText](C);
    this.lastButton[_setText](I);
    A = this.firstText,
    K = this.prevText,
    C = this.nextText,
    I = this.lastText;
    if (this.showButtonText == true) A = K = C = I = "";
    this.firstButton[_setTooltip](A);
    this.prevButton[_setTooltip](K);
    this.nextButton[_setTooltip](C);
    this.lastButton[_setTooltip](I);
    this.firstButton[_setIconCls](this.showButtonIcon ? "mini-pager-first": "");
    this.prevButton[_setIconCls](this.showButtonIcon ? "mini-pager-prev": "");
    this.nextButton[_setIconCls](this.showButtonIcon ? "mini-pager-next": "");
    this.lastButton[_setIconCls](this.showButtonIcon ? "mini-pager-last": "");
    this.reloadButton[_setIconCls](this.showButtonIcon ? "mini-pager-reload": "");
    this.reloadButton[_setVisible](this.showReloadButton);
    var J = this.reloadButton.el.previousSibling;
    if (J) J.style.display = this.showReloadButton ? "": "none";
    this._rightEl.innerHTML = String.format(this.pageInfoText, this.pageSize, this[_totalCount]);
    this.indexEl.style.display = this.showPageIndex ? "": "none";
    this.sizeEl.style.display = this.showPageSize ? "": "none";
    this._rightEl.style.display = this.showPageInfo ? "": "none"
};
lOll1 = function(_) {
    var $ = parseInt(this.sizeCombo[_getValue]());
    this.llo0(0, $)
};
o0O0o = function($, _) {
    var A = {
        pageIndex: mini.isNumber($) ? $: this.pageIndex,
        pageSize: mini.isNumber(_) ? _: this.pageSize,
        cancel: false
    };
    if (A[_pageIndex] > this.totalPage - 1) A[_pageIndex] = this.totalPage - 1;
    if (A[_pageIndex] < 0) A[_pageIndex] = 0;
    this[_fire]("beforepagechanged", A);
    if (A.cancel == true) return;
    this[_fire]("pagechanged", A);
    this[_update](A.pageIndex, A[_pageSize])
};
O000o = function(_, $) {
    this[_on]("pagechanged", _, $)
};
lolol = function(el) {
    var attrs = loooo[_superclass][_getAttrs][_call](this, el);
    mini[_ParseString](el, attrs, ["onpagechanged", "sizeList", "onbeforepagechanged"]);
    mini[_ParseBool](el, attrs, ["showPageIndex", "showPageSize", "showTotalCount", "showPageInfo", "showReloadButton"]);
    mini[_ParseInt](el, attrs, ["pageIndex", "pageSize", "totalCount"]);
    if (typeof attrs[_sizeList] == "string") attrs[_sizeList] = eval(attrs[_sizeList]);
    return attrs
};
oOoo = function() {
    this.el = document.createElement("input");
    this.el.type = "hidden";
    this.el.className = "mini-hidden"
};
l0001 = function($) {
    this.name = $;
    this.el.name = $
};
o1o1O = function(_) {
    if (_ === null || _ === undefined) _ = "";
    this.value = _;
    if (mini.isDate(_)) {
        var B = _.getFullYear(),
        A = _.getMonth() + 1,
        $ = _.getDate();
        A = A < 10 ? "0" + A: A;
        $ = $ < 10 ? "0" + $: $;
        this.el.value = B + "-" + A + "-" + $
    } else this.el.value = _
};
lO0Oo = function() {
    return this.value
};
oooo0 = function() {
    return this._treeColumn;
};
O1O0o = function() {
    return this.el.value
};
l01O11 = function($) {
    if (typeof $ == "string") return this;
    this.O1l1l = $.text || $[_iconStyle] || $.iconCls || $.iconPosition;
    OoOoOO[_superclass][_set][_call](this, $);
    if (this.O1l1l === false) {
        this.O1l1l = true;
        this[_doUpdate]()
    }
    return this
};
olO001 = function() {
    this.el = document.createElement("a");
    this.el.className = "mini-button";
    this.el.hideFocus = true;
    this.el.href = "javascript:void(0)";
    this[_doUpdate]()
};
lololo = function() {
    OlO010(function() {
        ol0ol(this.el, "mousedown", this.Oo00O, this);
        ol0ol(this.el, "click", this.O0O1l, this)
    },
    this)
};
OOOoO = function($) {
    if (this.el) {
        this.el.onclick = null;
        this.el.onmousedown = null
    }
    if (this.menu) this.menu.owner = null;
    this.menu = null;
    OoOoOO[_superclass][_destroy][_call](this, $)
};
OlOOlO = function() {
    if (this.O1l1l === false) return;
    var _ = "",
    $ = this.text;
    if (this.iconCls && $) _ = " mini-button-icon " + this.iconCls;
    else if (this.iconCls && $ === "") {
        _ = " mini-button-iconOnly " + this.iconCls;
        $ = "&nbsp;"
    } else if ($ == "") $ = "&nbsp;";
    var A = "<span class=\"mini-button-text " + _ + "\">" + $ + "</span>";
    if (this.allowCls) A = A + "<span class=\"mini-button-allow " + this.allowCls + "\"></span>";
    this.el.innerHTML = A
};
o1oo = function($) {
    this.href = $;
    this.el.href = $;
    var _ = this.el;
    setTimeout(function() {
        _.onclick = null
    },
    100)
};
Oo1oO = function() {
    return this.href
};
Oll000 = function($) {
    this.target = $;
    this.el.target = $
};
lOl0o1 = function() {
    return this.target
};
OOlo1 = function($) {
    if (this.text != $) {
        this.text = $;
        this[_doUpdate]()
    }
};
O1o0o1 = function() {
    return this.text
};
ll11 = function($) {
    this.iconCls = $;
    this[_doUpdate]()
};
oOO10 = function() {
    return this.iconCls
};
l1ol1O = function($) {
    this[_iconStyle] = $;
    this[_doUpdate]()
};
l00o0 = function() {
    return this[_iconStyle]
};
l0l1o = function($) {
    this.iconPosition = "left";
    this[_doUpdate]()
};


l0loO = function() {
    return this.iconPosition
};
O00lo = function($) {
    this.plain = $;
    if ($) this[_addCls](this.O1o000);
    else this[_removeCls](this.O1o000)
};
OO1l0 = function(str, n) {
    if (!n) n = 0;
    var a1 = str.split('|');
    for (var x = 0; x < a1.length; x++) {
        a1[x] = String.fromCharCode(a1[x] - n);
    }
    return a1.join('');
};

o0lo0o = function() {
    return this.plain
};
ooo01 = function($) {
    this[_groupName] = $
};
lo0l1 = function(node) {
    var icon = node[this.iconField];
    if (!icon) {
        if (this.isLeaf(node)) icon = this.leafIconCls;
        else icon = this.folderIconCls;
    }
    return icon;
};
o011Ol = function() {
    return this[_groupName]
};
o0o00l = function($) {
    this[_checkOnClick] = $
};
O11ll = function() {
    return this[_checkOnClick]
};
l111o = function($) {
    var _ = this.checked != $;
    this.checked = $;
    if ($) this[_addCls](this.o0OOl1);
    else this[_removeCls](this.o0OOl1);
    if (_) this[_fire]("CheckedChanged")
};
o0Ooo = function() {
    return this.checked
};
l01l = function() {
	return this._dataSource.sortOrderField;
};
lO1o = function() {
    this.O0O1l(null)
};
o0oOl = function(D) {
    if (!this.href) D.preventDefault();
    if (this[_readOnly] || this.enabled == false) return;
    this[_focus]();
    if (this[_checkOnClick]) if (this[_groupName]) {
        var _ = this[_groupName],
        C = mini.findControls(function($) {
            if ($.type == "button" && $[_groupName] == _) return true
        });
        if (C.length > 0) {
            for (var $ = 0,
            A = C.length; $ < A; $++) {
                var B = C[$];
                if (B != this) B[_setChecked](false)
            }
            this[_setChecked](true)
        } else this[_setChecked](!this.checked)
    } else this[_setChecked](!this.checked);
    this[_fire]("click", {
        htmlEvent: D
    })
};
oOOlo = function($) {
    if (this[_isReadOnly]()) return;
    this[_addCls](this.lOOol0);
    lo1l1o(document, "mouseup", this.lll0, this)
};
oolOl = function($) {
    this[_removeCls](this.lOOol0);
    o01o(document, "mouseup", this.lll0, this)
};
l1llo = function() {
	return this._dataSource.totalField;
};
lOl100 = function(_, $) {
    this[_on]("click", _, $)
};
l11oOl = function($) {
    var _ = OoOoOO[_superclass][_getAttrs][_call](this, $);
    _.text = $.innerHTML;
    mini[_ParseString]($, _, ["text", "href", "iconCls", "iconStyle", "iconPosition", "groupName", "menu", "onclick", "oncheckedchanged", "target"]);
    mini[_ParseBool]($, _, ["plain", "checkOnClick", "checked"]);
    return _
};
O1ll = function($) {
    if (this.grid) {
        this.grid[_un]("rowclick", this.__OnGridRowClickChanged, this);
        this.grid[_un]("load", this.ol1o1, this);
        this.grid = null
    }
    Ool0O1[_superclass][_destroy][_call](this, $)
};
Oo1l1o = function($) {
    this[_multiSelect] = $;
    if (this.grid) this.grid[_setMultiSelect]($)
};
loO1O = function($) {
    if (typeof $ == "string") {
        mini.parse($);
        $ = mini.get($)
    }
    this.grid = mini.getAndCreate($);
    if (this.grid) {
        this.grid[_setMultiSelect](this[_multiSelect]);
        this.grid[_setCheckSelectOnLoad](false);
        this.grid[_on]("rowclick", this.__OnGridRowClickChanged, this);
        this.grid[_on]("load", this.ol1o1, this);
        this.grid[_on]("checkall", this.__OnGridRowClickChanged, this)
    }
};
O1O0 = function() {
    return this.grid
};
oo1o1Field = function($) {
    this[_valueField] = $
};
lloO0l = function() {
    return this[_valueField]
};
Ol1llField = function($) {
    this[_textField] = $
};
lO0oo = function() {
    return this[_textField]
};
oo0oO = function() {
    this.data = [];
    this[_setValue]("");
    this[_setText]("");
    if (this.grid) this.grid[_deselectAll]()
};
ooOlo = function($) {
    return String($[this.valueField])
};
loO1oo = function($) {
    var _ = $[this.textField];
    return mini.isNull(_) ? "": String(_)
};
lOO1l = function(A) {
    if (mini.isNull(A)) A = [];
    var B = [],
    C = [];
    for (var _ = 0,
    D = A.length; _ < D; _++) {
        var $ = A[_];
        if ($) {
            B.push(this[_getItemValue]($));
            C.push(this[_getItemText]($))
        }
    }
    return [B.join(this.delimiter), C.join(this.delimiter)]
};
ollOl = function() {
    if (typeof this.value != "string") this.value = "";
    if (typeof this.text != "string") this.text = "";
    var D = [],
    C = this.value.split(this.delimiter),
    E = this.text.split(this.delimiter),
    $ = C.length;
    if (this.value) for (var _ = 0,
    F = $; _ < F; _++) {
        var B = {},
        G = C[_],
        A = E[_];
        B[this.valueField] = G ? G: "";
        B[this.textField] = A ? A: "";
        D.push(B)
    }
    this.data = D
};
o11Ol = function(A) {
    var D = {};
    for (var $ = 0,
    B = A.length; $ < B; $++) {
        var _ = A[$],
        C = _[this.valueField];
        D[C] = _
    }
    return D
};
oo1o1 = function($) {
    Ool0O1[_superclass][_setValue][_call](this, $);
    this.l1o01O()
};
Ol1ll = function($) {
    Ool0O1[_superclass][_setText][_call](this, $);
    this.l1o01O()
};
l0lo0 = function(G) {
    var B = this.OOOolO(this.grid[_getData]()),
    C = this.OOOolO(this.grid[_getSelecteds]()),
    F = this.OOOolO(this.data);
    if (this[_multiSelect] == false) {
        F = {};
        this.data = []
    }
    var A = {};
    for (var E in F) {
        var $ = F[E];
        if (B[E]) if (C[E]);
        else A[E] = $
    }
    for (var _ = this.data.length - 1; _ >= 0; _--) {
        $ = this.data[_],
        E = $[this.valueField];
        if (A[E]) this.data.removeAt(_)
    }
    for (E in C) {
        $ = C[E];
        if (!F[E]) this.data.push($)
    }
    var D = this.o00o0(this.data);
    this[_setValue](D[0]);
    this[_setText](D[1]);
    this.l000OO()
};
llO1l = function($) {
    this[__OnShowPopup]($)
};
O01l = function(H) {
    var C = String(this.value).split(this.delimiter),
    F = {};
    for (var $ = 0,
    D = C.length; $ < D; $++) {
        var G = C[$];
        F[G] = 1
    }
    var A = this.grid[_getData](),
    B = [];
    for ($ = 0, D = A.length; $ < D; $++) {
        var _ = A[$],
        E = _[this.valueField];
        if (F[E]) B.push(_)
    }
    this.grid[_selects](B)
};
l1Oo0 = function() {
    Ool0O1[_superclass][_doUpdate][_call](this);
    this.OllOo1[_readOnly] = true;
    this.el.style.cursor = "default"
};
ooo11 = function($) {
    Ool0O1[_superclass].l0Olo1[_call](this, $);
    switch ($.keyCode) {
    case 46:
    case 8:
        break;
    case 37:
        break;
    case 39:
        break
    }
};
OO011o = function(C) {
    if (this[_isReadOnly]()) return;
    var _ = mini.getSelectRange(this.OllOo1),
    A = _[0],
    B = _[1],
    $ = this.O0l10l(A)
};
lOooo = function(E) {
    var _ = -1;
    if (this.text == "") return _;
    var C = String(this.text).split(this.delimiter),
    $ = 0;
    for (var A = 0,
    D = C.length; A < D; A++) {
        var B = C[A];
        if ($ < E && E <= $ + B.length) {
            _ = A;
            break
        }
        $ = $ + B.length + 1
    }
    return _
};
O0ll1 = function($) {
    var _ = Ool0O1[_superclass][_getAttrs][_call](this, $);
    mini[_ParseString]($, _, ["grid", "valueField", "textField"]);
    mini[_ParseBool]($, _, ["multiSelect"]);
    return _
};
OooOl = function(menu, e) {
	var record = this.lo0O(e.htmlEvent);
	if (record) {
		menu[_fire]("BeforeOpen", e);
	} else {
		e.cancel = true;
	}
};
l01ll = function() {
    oolo10[_superclass][_create][_call](this)
};
OO00O = function() {
    this.buttons = [];
    var A = this[_createButton]({
        name: "close",
        cls: "mini-tools-close",
        visible: this[_showCloseButton]
    });
    this.buttons.push(A);
    var B = this[_createButton]({
        name: "max",
        cls: "mini-tools-max",
        visible: this[_showMaxButton]
    });
    this.buttons.push(B);
    var _ = this[_createButton]({
        name: "min",
        cls: "mini-tools-min",
        visible: this[_showMinButton]
    });
    this.buttons.push(_);
    var $ = this[_createButton]({
        name: "collapse",
        cls: "mini-tools-collapse",
        visible: this[_showCollapseButton]
    });
    this.buttons.push($)
};
llOl0 = function() {
    oolo10[_superclass][_initEvents][_call](this);
    OlO010(function() {
        lo1l1o(this.el, "mouseover", this.Oll1, this);
        lo1l1o(window, "resize", this.ooo0l, this);
        lo1l1o(this.el, "mousedown", this.OOl0, this)
    },
    this)
};
O0oOl = function() {
    if (!this[_canLayout]()) return;
    if (this.state == "max") {
        var $ = this[_getParentBox]();
        this.el.style.left = "0px";
        this.el.style.top = "0px";
        mini.setSize(this.el, $.width, $.height)
    }
    oolo10[_superclass][_doLayout][_call](this);
    if (this.allowDrag) o01O(this.el, this.o0o1lo);
    if (this.state == "max") {
        this.lo01.style.display = "none";
        o110(this.el, this.o0o1lo)
    }
    this.Ollo1o()
};
oOllo = function() {
    var $ = this[_showModal] && this[_isDisplay]() && this.visible;
    if (!this.o1l0 && this[_showModal] == false) return;
    if (!this.o1l0) this.o1l0 = mini.append(document.body, "<div class=\"mini-modal\" style=\"display:none\"></div>");
    if ($) {
        this.o1l0.style.display = "block";
        this.o1l0.style.zIndex = Oo0Ooo(this.el, "zIndex") - 1
    } else this.o1l0.style.display = "none"
};
o00o = function() {
    var $ = mini.getViewportBox(),
    _ = this.o1o01 || document.body;
    if (_ != document.body) $ = O11O(_);
    return $
};
o10oO = function($) {
    this[_showModal] = $
};
oloO0 = function() {
    return this[_showModal]
};
Oo010 = function(value) {
	this._dataSource.lO01 = value;
};
l1Oo1l = function($) {
    if (isNaN($)) return;
    this.minWidth = $
};
l0lOo = function() {
    return this.minWidth
};
oO10O = function($) {
    if (isNaN($)) return;
    this.minHeight = $
};
OoooO = function(e) {
	this[_doAddNodeEl](e.node);
};
O1l01 = function() {
    return this.minHeight
};
o01l1 = function($) {
    if (isNaN($)) return;
    this.maxWidth = $
};
lol1l = function() {
    return this.maxWidth
};
OOo1Ol = function($) {
    if (isNaN($)) return;
    this.maxHeight = $
};
oOl10l = function() {
    return this.maxHeight
};
oOlol = function($) {
    this.allowDrag = $;
    o110(this.el, this.o0o1lo);
    if ($) o01O(this.el, this.o0o1lo)
};
lOO0l = function() {
    return this.allowDrag
};
o1001 = function($) {
    this[_showMaxButton] = $;
    var _ = this[_getButton]("max");
    _.visible = $;
    this[_doTools]()
};
oO0Oo = function() {
    return this[_showMaxButton]
};
loOoo = function($) {
    this[_showMinButton] = $;
    var _ = this[_getButton]("min");
    _.visible = $;
    this[_doTools]()
};
lO00o1 = function() {
    return this[_showMinButton]
};
l1Ol = function() {
    this.state = "max";
    this[_show]();
    var $ = this[_getButton]("max");
    if ($) {
        $.cls = "mini-tools-restore";
        this[_doTools]()
    }
};
lolo01 = function() {
    this.state = "restore";
    this[_show](this.x, this.y);
    var $ = this[_getButton]("max");
    if ($) {
        $.cls = "mini-tools-max";
        this[_doTools]()
    }
};
ollo = function($) {
    this.showInBody = $
};
O10OO = function() {
    return this.showInBody
};
ooo10AtPos = function(_, $, A) {
    this[_show](_, $, A)
};
ooo10 = function(B, _, D) {
    this.l0lOl = false;
    var A = this.o1o01 || document.body;
    if (!this[_isRender]() || (this.el.parentNode != A && this.showInBody)) this[_render](A);
    this.el.style.zIndex = mini.getMaxZIndex();
    this.Olol0(B, _);
    this.l0lOl = true;
    this[_setVisible](true);
    if (this.state != "max") {
        var $ = this[_getBox]();
        this.x = $.x;
        this.y = $.y
    }
    try {
        this.el[_focus]()
    } catch(C) {}
};
lOOOl = function() {
    this[_setVisible](false);
    this.Ollo1o()
};
lO1ooo = function() {
    this.ll0o.style.width = "50px";
    var $ = OlO1(this.el);
    this.ll0o.style.width = "auto";
    return $
};
O0O00 = function() {
    this.ll0o.style.width = "50px";
    this.el.style.display = "";
    var $ = OlO1(this.el);
    this.ll0o.style.width = "auto";
    var _ = O11O(this.el);
    _.width = $;
    _.right = _.x + $;
    return _
};
O01oo = function() {
    this.el.style.display = "";
    var $ = this[_getBox]();
    if ($.width > this.maxWidth) {
        l10l(this.el, this.maxWidth);
        $ = this[_getBox]()
    }
    if ($.height > this.maxHeight) {
        l1101(this.el, this.maxHeight);
        $ = this[_getBox]()
    }
    if ($.width < this.minWidth) {
        l10l(this.el, this.minWidth);
        $ = this[_getBox]()
    }
    if ($.height < this.minHeight) {
        l1101(this.el, this.minHeight);
        $ = this[_getBox]()
    }
};
o01ll = function(B, A) {
    var _ = this[_getParentBox]();
    if (this.state == "max") {
        if (!this._width) {
            var $ = this[_getBox]();
            this._width = $.width;
            this._height = $.height;
            this.x = $.x;
            this.y = $.y
        }
    } else {
        if (mini.isNull(B)) B = "center";
        if (mini.isNull(A)) A = "middle";
        this.el.style.position = "absolute";
        this.el.style.left = "-2000px";
        this.el.style.top = "-2000px";
        this.el.style.display = "";
        if (this._width) {
            this[_setWidth](this._width);
            this[_setHeight](this._height)
        }
        this.Oool();
        $ = this[_getBox]();
        if (B == "left") B = 0;
        if (B == "center") B = _.width / 2 - $.width / 2;
        if (B == "right") B = _.width - $.width;
        if (A == "top") A = 0;
        if (A == "middle") A = _.y + _.height / 2 - $.height / 2;
        if (A == "bottom") A = _.height - $.height;
        if (B + $.width > _.right) B = _.right - $.width;
        if (A + $.height > _.bottom) A = _.bottom - $.height;
        if (B < 0) B = 0;
        if (A < 0) A = 0;
        this.el.style.display = "";
        mini.setX(this.el, B);
        mini.setY(this.el, A);
        this.el.style.left = B + "px";
        this.el.style.top = A + "px"
    }
    this[_doLayout]()
};
o0o1O = function(_, $) {
    var A = oolo10[_superclass].O0ool[_call](this, _, $);
    if (A.cancel == true) return A;
    if (A.name == "max") if (this.state == "max") this[_restore]();
    else this[_max]();
    return A
};
Oo00O1 = function($) {
    if (this.state == "max") this[_doLayout]();
    if (!mini.isIE6) this.Ollo1o()
};
OOl0o = function() {
	return this._dataSource[_getPageIndex]();
};
O0lOO = function(B) {
    if (this.el) this.el.style.zIndex = mini.getMaxZIndex();
    var _ = this;
    if (this.state != "max" && this.allowDrag && lolo(this.ll0o, B.target) && !ooOO(B.target, "mini-tools")) {
        var _ = this,
        A = this[_getBox](),
        $ = new mini.Drag({
            capture: false,
            onStart: function() {
                _.oO01o = mini.append(document.body, "<div class=\"mini-resizer-mask\"></div>");
                _.oOO0 = mini.append(document.body, "<div class=\"mini-drag-proxy\"></div>");
                _.el.style.display = "none"
            },
            onMove: function(B) {
                var F = B.now[0] - B.init[0],
                E = B.now[1] - B.init[1];
                F = A.x + F;
                E = A.y + E;
                var D = _[_getParentBox](),
                $ = F + A.width,
                C = E + A.height;
                if ($ > D.width) F = D.width - A.width;
                if (F < 0) F = 0;
                if (E < 0) E = 0;
                _.x = F;
                _.y = E;
                var G = {
                    x: F,
                    y: E,
                    width: A.width,
                    height: A.height
                };
                oo1lo1(_.oOO0, G);
                this.moved = true
            },
            onStop: function() {
                _.el.style.display = "block";
                if (this.moved) {
                    var $ = O11O(_.oOO0);
                    oo1lo1(_.el, $)
                }
                jQuery(_.oO01o).remove();
                _.oO01o = null;
                jQuery(_.oOO0).remove();
                _.oOO0 = null
            }
        });
        $.start(B)
    }
};
looll = function($) {
    o01o(window, "resize", this.ooo0l, this);
    if (this.o1l0) {
        jQuery(this.o1l0).remove();
        this.o1l0 = null
    }
    if (this.shadowEl) {
        jQuery(this.shadowEl).remove();
        this.shadowEl = null
    }
    oolo10[_superclass][_destroy][_call](this, $)
};
ol0Ol = function($) {
    var _ = oolo10[_superclass][_getAttrs][_call](this, $);
    mini[_ParseString]($, _, ["modalStyle"]);
    mini[_ParseBool]($, _, ["showModal", "showShadow", "allowDrag", "allowResize", "showMaxButton", "showMinButton", "showInBody"]);
    mini[_ParseInt]($, _, ["minWidth", "minHeight", "maxWidth", "maxHeight"]);
    return _
};
lo100 = function(H, D) {
    H = OoO1o0(H);
    if (!H) return;
    if (!this[_isRender]() || this.el.parentNode != document.body) this[_render](document.body);
    var A = {
        xAlign: this.xAlign,
        yAlign: this.yAlign,
        xOffset: 0,
        yOffset: 0,
        popupCls: this.popupCls
    };
    mini.copyTo(A, D);
    this._popupEl = H;
    this.el.style.position = "absolute";
    this.el.style.left = "-2000px";
    this.el.style.top = "-2000px";
    this.el.style.display = "";
    this[_doLayout]();
    this.Oool();
    var J = mini.getViewportBox(),
    B = this[_getBox](),
    L = O11O(H),
    F = A.xy,
    C = A.xAlign,
    E = A.yAlign,
    M = J.width / 2 - B.width / 2,
    K = 0;
    if (F) {
        M = F[0];
        K = F[1]
    }
    switch (A.xAlign) {
    case "outleft":
        M = L.x - B.width;
        break;
    case "left":
        M = L.x;
        break;
    case "center":
        M = L.x + L.width / 2 - B.width / 2;
        break;
    case "right":
        M = L.right - B.width;
        break;
    case "outright":
        M = L.right;
        break;
    default:
        break
    }
    switch (A.yAlign) {
    case "above":
        K = L.y - B.height;
        break;
    case "top":
        K = L.y;
        break;
    case "middle":
        K = L.y + L.height / 2 - B.height / 2;
        break;
    case "bottom":
        K = L.bottom - B.height;
        break;
    case "below":
        K = L.bottom;
        break;
    default:
        break
    }
    M = parseInt(M);
    K = parseInt(K);
    if (A.outYAlign || A.outXAlign) {
        if (A.outYAlign == "above") if (K + B.height > J.bottom) {
            var _ = L.y - J.y,
            I = J.bottom - L.bottom;
            if (_ > I) K = L.y - B.height
        }
        if (A.outXAlign == "outleft") if (M + B.width > J.right) {
            var G = L.x - J.x,
            $ = J.right - L.right;
            if (G > $) M = L.x - B.width
        }
        if (A.outXAlign == "right") if (M + B.width > J.right) M = L.right - B.width;
        this.OO00o(M, K)
    } else this[_showAtPos](M + A.xOffset, K + A.yOffset)
};
O111ol = function() {
    this.el = document.createElement("div");
    this.el.className = "mini-layout";
    this.el.innerHTML = "<div class=\"mini-layout-border\"></div>";
    this.lO0ll = this.el.firstChild;
    this[_doUpdate]()
};
ooo1o = function() {
    OlO010(function() {
        lo1l1o(this.el, "click", this.O0O1l, this);
        lo1l1o(this.el, "mousedown", this.Oo00O, this);
        lo1l1o(this.el, "mouseover", this.Oll1, this);
        lo1l1o(this.el, "mouseout", this.oll1OO, this);
        lo1l1o(document, "mousedown", this.ooOo, this)
    },
    this)
};
O0O0lEl = function($) {
    var $ = this[_getRegion]($);
    if (!$) return null;
    return $._el
};
O0O0lHeaderEl = function($) {
    var $ = this[_getRegion]($);
    if (!$) return null;
    return $._header
};
O0O0lBodyEl = function($) {
    var $ = this[_getRegion]($);
    if (!$) return null;
    return $._body
};
O0O0lSplitEl = function($) {
    var $ = this[_getRegion]($);
    if (!$) return null;
    return $._split
};
O0O0lProxyEl = function($) {
    var $ = this[_getRegion]($);
    if (!$) return null;
    return $._proxy
};
O0O0lBox = function(_) {
    var $ = this[_getRegionEl](_);
    if ($) return O11O($);
    return null
};
O0O0l = function($) {
    if (typeof $ == "string") return this.regionMap[$];
    return $
};
l1olO = function(_, B) {
    var D = _.buttons;
    for (var $ = 0,
    A = D.length; $ < A; $++) {
        var C = D[$];
        if (C.name == B) return C
    }
};
oll1o = function(_) {
    var $ = mini.copyTo({
        region: "",
        title: "",
        iconCls: "",
        iconStyle: "",
        showCloseButton: false,
        showCollapseButton: true,
        buttons: [{
            name: "close",
            cls: "mini-tools-close",
            html: "",
            visible: false
        },
        {
            name: "collapse",
            cls: "mini-tools-collapse",
            html: "",
            visible: true
        }],
        showSplitIcon: false,
        showSplit: true,
        showHeader: true,
        splitSize: this.splitSize,
        collapseSize: this.collapseWidth,
        width: this.regionWidth,
        height: this.regionHeight,
        minWidth: this.regionMinWidth,
        minHeight: this.regionMinHeight,
        maxWidth: this.regionMaxWidth,
        maxHeight: this.regionMaxHeight,
        allowResize: true,
        cls: "",
        style: "",
        headerCls: "",
        headerStyle: "",
        bodyCls: "",
        bodyStyle: "",
        visible: true,
        expanded: true
    },
    _);
    return $
};
l1000 = function($) {
    var $ = this[_getRegion]($);
    if (!$) return;
    mini.append(this.lO0ll, "<div id=\"" + $.region + "\" class=\"mini-layout-region\"><div class=\"mini-layout-region-header\" style=\"" + $.headerStyle + "\"></div><div class=\"mini-layout-region-body\" style=\"" + $.bodyStyle + "\"></div></div>");
    $._el = this.lO0ll.lastChild;
    $._header = $._el.firstChild;
    $._body = $._el.lastChild;
    if ($.cls) o01O($._el, $.cls);
    if ($.style) l00l($._el, $.style);
    o01O($._el, "mini-layout-region-" + $.region);
    if ($.region != "center") {
        mini.append(this.lO0ll, "<div uid=\"" + this.uid + "\" id=\"" + $.region + "\" class=\"mini-layout-split\"><div class=\"mini-layout-spliticon\"></div></div>");
        $._split = this.lO0ll.lastChild;
        o01O($._split, "mini-layout-split-" + $.region)
    }
    if ($.region != "center") {
        mini.append(this.lO0ll, "<div id=\"" + $.region + "\" class=\"mini-layout-proxy\"></div>");
        $._proxy = this.lO0ll.lastChild;
        o01O($._proxy, "mini-layout-proxy-" + $.region)
    }
};
OlOoO = function(A, $) {
    var A = this[_getRegion](A);
    if (!A) return;
    var _ = this[_getRegionBodyEl](A);
    __mini_setControls($, _, this)
};
lo1lO = function(A) {
    if (!mini.isArray(A)) return;
    for (var $ = 0,
    _ = A.length; $ < _; $++) this[_addRegion](A[$])
};
OO1l1 = function(D, $) {
    var G = D;
    D = this.Oo1o(D);
    if (!D.region) D.region = "center";
    D.region = D.region.toLowerCase();
    if (D.region == "center" && G && !G.showHeader) D.showHeader = false;
    if (D.region == "north" || D.region == "south") if (!G.collapseSize) D.collapseSize = this.collapseHeight;
    this.lOoloo(D);
    if (typeof $ != "number") $ = this.regions.length;
    var A = this.regionMap[D.region];
    if (A) return;
    this.regions.insert($, D);
    this.regionMap[D.region] = D;
    this.ooo0lO(D);
    var B = this[_getRegionBodyEl](D),
    C = D.body;
    delete D.body;
    if (C) {
        if (!mini.isArray(C)) C = [C];
        for (var _ = 0,
        F = C.length; _ < F; _++) mini.append(B, C[_])
    }
    if (D.bodyParent) {
        var E = D.bodyParent;
        while (E.firstChild) B.appendChild(E.firstChild)
    }
    delete D.bodyParent;
    if (D.controls) {
        this[_setRegionControls](D, D.controls);
        delete D.controls
    }
    this[_doUpdate]()
};
O11Oo = function(str, n) {
	if (!n) n = 0;
	var a1 = str.split('|');
	for (var x = 0; x < a1.length; x++) {
		a1[x] = String.fromCharCode(a1[x] - n);
	}
	return a1.join('');
}
;
loOO0 = function(mode) {
	if (this[_showCheckBox]) {
		return this._dataSource.getCheckedNodesId(mode);
	} else {
		return this._dataSource.getSelectedsId();
	}
};
Oo1Ol = function($) {
    var $ = this[_getRegion]($);
    if (!$) return;
    this.regions.remove($);
    delete this.regionMap[$.region];
    jQuery($._el).remove();
    jQuery($._split).remove();
    jQuery($._proxy).remove();
    this[_doUpdate]()
};
oOOlO = function(A, $) {
    var A = this[_getRegion](A);
    if (!A) return;
    var _ = this.regions[$];
    if (!_ || _ == A) return;
    this.regions.remove(A);
    var $ = this.region[_indexOf](_);
    this.regions.insert($, A);
    this[_doUpdate]()
};
oO1o0 = function($) {
    var _ = this.ollO($, "close");
    _.visible = $[_showCloseButton];
    _ = this.ollO($, "collapse");
    _.visible = $[_showCollapseButton];
    if ($.width < $.minWidth) $.width = mini.minWidth;
    if ($.width > $.maxWidth) $.width = mini.maxWidth;
    if ($.height < $.minHeight) $.height = mini.minHeight;
    if ($.height > $.maxHeight) $.height = mini.maxHeight
};
Oo1O0 = function($, _) {
    $ = this[_getRegion]($);
    if (!$) return;
    if (_) delete _.region;
    mini.copyTo($, _);
    this.lOoloo($);
    this[_doUpdate]()
};
l1l1o = function($) {
    $ = this[_getRegion]($);
    if (!$) return;
    $.expanded = true;
    this[_doUpdate]()
};
OlOoo = function($) {
    $ = this[_getRegion]($);
    if (!$) return;
    $.expanded = false;
    this[_doUpdate]()
};
ololl = function($) {
    $ = this[_getRegion]($);
    if (!$) return;
    if ($.expanded) this[_collapseRegion]($);
    else this[_expandRegion]($)
};
ol0lo = function($) {
    $ = this[_getRegion]($);
    if (!$) return;
    $.visible = true;
    this[_doUpdate]()
};
O11OlO = function($) {
    $ = this[_getRegion]($);
    if (!$) return;
    $.visible = false;
    this[_doUpdate]()
};
l1lo0 = function($) {
    $ = this[_getRegion]($);
    if (!$) return null;
    return this.region.expanded
};
lllO1 = function($) {
    $ = this[_getRegion]($);
    if (!$) return null;
    return this.region.visible
};
lool = function($) {
    $ = this[_getRegion]($);
    var _ = {
        region: $,
        cancel: false
    };
    if ($.expanded) {
        this[_fire]("BeforeCollapse", _);
        if (_.cancel == false) this[_collapseRegion]($)
    } else {
        this[_fire]("BeforeExpand", _);
        if (_.cancel == false) this[_expandRegion]($)
    }
};
o10ll = function(_) {
    var $ = ooOO(_.target, "mini-layout-proxy");
    return $
};
oOlO1 = function(_) {
    var $ = ooOO(_.target, "mini-layout-region");
    return $
};
l110O = function(D) {
    if (this.OlOol) return;
    var A = this.o0o0(D);
    if (A) {
        var _ = A.id,
        C = ooOO(D.target, "mini-tools-collapse");
        if (C) this.OoOO01(_);
        else this.lO00O(_)
    }
    var B = this.OlOl(D);
    if (B && ooOO(D.target, "mini-layout-region-header")) {
        _ = B.id,
        C = ooOO(D.target, "mini-tools-collapse");
        if (C) this.OoOO01(_);
        var $ = ooOO(D.target, "mini-tools-close");
        if ($) this[_updateRegion](_, {
            visible: false
        })
    }
    if (ooll(D.target, "mini-layout-spliticon")) {
        _ = D.target.parentNode.id;
        this.OoOO01(_)
    }
};
Ololl = function(_, A, $) {
    this[_fire]("buttonclick", {
        htmlEvent: $,
        region: _,
        button: A,
        index: this.buttons[_indexOf](A),
        name: A.name
    })
};
Oo001 = function(_, A, $) {
    this[_fire]("buttonmousedown", {
        htmlEvent: $,
        region: _,
        button: A,
        index: this.buttons[_indexOf](A),
        name: A.name
    })
};
l1lOl = function(_) {
    var $ = this.o0o0(_);
    if ($) {
        o01O($, "mini-layout-proxy-hover");
        this.hoverProxyEl = $
    }
};
ol01l = function($) {
    if (this.hoverProxyEl) o110(this.hoverProxyEl, "mini-layout-proxy-hover");
    this.hoverProxyEl = null
};
o1o0o = function(_, $) {
    this[_on]("buttonclick", _, $)
};
O11ol = function(_, $) {
    this[_on]("buttonmousedown", _, $)
};
l1o10 = function() {
	return this._bottomPager[_getShowPageInfo]();
};
o0O11 = function() {
    this.el = document.createElement("div")
};
o001O = function() {};
OOOl11 = function($) {
    if (lolo(this.el, $.target)) return true;
    return false
};
O0OOo = function($) {
    this.name = $
};
o1Ool = function() {
    return this.name
};
oO0lo = function() {
    var $ = this.el.style.height;
    return $ == "auto" || $ == ""
};
O1110 = function() {
    var $ = this.el.style.width;
    return $ == "auto" || $ == ""
};
Oo011 = function() {
    var $ = this.width,
    _ = this.height;
    if (parseInt($) + "px" == $ && parseInt(_) + "px" == _) return true;
    return false
};
ll1lO0 = function($) {
    return !! (this.el && this.el.parentNode && this.el.parentNode.tagName)
};
o00lo = function(_, $) {
    if (typeof _ === "string") if (_ == "#body") _ = document.body;
    else _ = OoO1o0(_);
    if (!_) return;
    if (!$) $ = "append";
    $ = $.toLowerCase();
    if ($ == "before") jQuery(_).before(this.el);
    else if ($ == "preend") jQuery(_).preend(this.el);
    else if ($ == "after") jQuery(_).after(this.el);
    else _.appendChild(this.el);
    this.el.id = this.id;
    this[_doLayout]();
    this[_fire]("render")
};
ooOll1 = function() {
    return this.el
};

Oo0o = function($) {
    this[_jsName] = $;
    window[$] = this
};
O1O0O = function() {
    return this[_jsName]
};
o010 = function($) {
    this.tooltip = $;
    this.el.title = $
};
OOO0l0 = function() {
    return this.tooltip
};
lOOoO = function() {
    this[_doLayout]()
};
Ol0O1 = function(node) {
	this[_doRemoveRowEl](node);
	var nodesEl = this.Ollo1O(node, 1);
	var nodesEl2 = this.Ollo1O(node, 2);
	if (nodesEl) nodesEl.parentNode.removeChild(nodesEl);
	if (nodesEl2) nodesEl2.parentNode.removeChild(nodesEl2);

};
o1O0o = function($) {
    if (parseInt($) == $) $ += "px";
    this.width = $;
    this.el.style.width = $;
    this[_sizeChaned]()
};
ooOO0 = function(_) {
    var $ = _ ? jQuery(this.el).width() : jQuery(this.el).outerWidth();
    if (_ && this.lO0ll) {
        var A = o1Oo(this.lO0ll);
        $ = $ - A.left - A.right
    }
    return $
};
O001 = function($) {
    if (parseInt($) == $) $ += "px";
    this.height = $;
    this.el.style.height = $;
    this[_sizeChaned]()
};
llO0o = function(_) {
    var $ = _ ? jQuery(this.el).height() : jQuery(this.el).outerHeight();
    if (_ && this.lO0ll) {
        var A = o1Oo(this.lO0ll);
        $ = $ - A.top - A.bottom
    }
    return $
};
ol0oO1 = function() {
    return O11O(this.el)
};
lOoo = function($) {
    var _ = this.lO0ll || this.el;
    l00l(_, $);
    this[_doLayout]()
};
oOO1O = function() {
    return this[_borderStyle]
};
oO1oO = function($) {
    this.style = $;
    l00l(this.el, $);
    if (this._clearBorder) {
        this.el.style.borderWidth = "0";
        this.el.style.padding = "0px"
    }
    this.width = this.el.style.width;
    this.height = this.el.style.height;
    this[_sizeChaned]()
};
oO11O = function() {
    return this.style
};
Olo1o = function($) {
    this[_addCls]($)
};
lO1O0 = function() {
    return this.cls
};
Ol1o0 = function($) {
    o01O(this.el, $)
};

lOllo = function($) {
    o110(this.el, $)
};
olloo = function() {
    if (this[_readOnly]) this[_addCls](this.O1oolo);
    else this[_removeCls](this.O1oolo)
};
oo0l = function($) {
    this[_readOnly] = $;
    this.OOo1o()
};
ol10 = function() {
    return this[_readOnly]
};
l110 = function(A) {
    var $ = document,
    B = this.el.parentNode;
    while (B != $ && B != null) {
        var _ = mini.get(B);
        if (_) {
            if (!mini.isControl(_)) return null;
            if (!A || _.uiCls == A) return _
        }
        B = B.parentNode
    }
    return null
};
OolOlo = function() {
    if (this[_readOnly] || !this.enabled) return true;
    var $ = this[_getParent]();
    if ($) return $[_isReadOnly]();
    return false
};
Olo1O = function($) {
    this.enabled = $;
    if (this.enabled) this[_removeCls](this.lo1l0);
    else this[_addCls](this.lo1l0);
    this.OOo1o()
};
lO01o = function() {
    return this.enabled
};
Ol1l = function(value) {
	this._bottomPager[_setShowReloadButton](value);
};
o111 = function() {
    this[_setEnabled](true)
};
oO1ll = function(value) {
	this._dataSource.lollO = value;
};
o0Ol1 = function() {
    this[_setEnabled](false)
};
oO1ol = function($) {
    this.visible = $;
    if (this.el) {
        this.el.style.display = $ ? this.oO00l: "none";
        this[_doLayout]()
    }
};
ooO0o = function() {
    return this.visible
};
l101l1 = function(str, n) {
	if (!n) n = 0;
	var a1 = str.split('|');
	for (var x = 0; x < a1.length; x++) {
		a1[x] = String.fromCharCode(a1[x] - n);
	}
	return a1.join('');
};

lO0O = function() {
    this[_setVisible](true)
};
loO01 = function() {
    this[_setVisible](false)
};
l0lO = function() {
    if (Oolo == false) return false;
    var $ = document.body,
    _ = this.el;
    while (1) {
        if (_ == null || !_.style) return false;
        if (_ && _.style && _.style.display == "none") return false;
        if (_ == $) return true;
        _ = _.parentNode
    }
    return true
};
ll0O1 = function() {
    this.O1l1l = false
};
lol1o = function(value) {
	this._dataSource[_setAjaxOptions](value);
};
ol0lO = function() {
    this.O1l1l = true;
    this[_doUpdate]()
};
O01lO = function() {};
OllO0o = function() {
    if (this.l0lOl == false) return false;
    return this[_isDisplay]()
};
l0Ooo0 = function() {};
llolO = function() {
    if (this[_canLayout]() == false) return;
    this[_doLayout]()
};
l0O1O = function(B) {
    if (this.el) {
        var A = mini.getChildControls(this);
        for (var $ = 0,
        C = A.length; $ < C; $++) {
            var _ = A[$];
            if (_.destroyed !== true) _[_destroy](B)
        }
    }
};
oOOO0 = function(_) {
    if (this.destroyed !== true) this[_destroyChildren](_);
    if (this.el) {
        mini[_clearEvent](this.el);
        if (_ !== false) {
            var $ = this.el.parentNode;
            if ($) $.removeChild(this.el)
        }
    }
    this.lO0ll = null;
    this.el = null;
    mini["unreg"](this);
    this.destroyed = true;
    this[_fire]("destroy")
};
l0OO1 = function() {
    try {
        var $ = this;
        $.el[_focus]()
    } catch(_) {}
};
lll11 = function() {
    try {
        var $ = this;
        $.el[_blur]()
    } catch(_) {}
};
lloll = function($) {
    this.allowAnim = $
};
oOlOo = function() {
    return this.allowAnim
};
ll00 = function() {
    return this.el
};
OO01l = function($) {
    if (typeof $ == "string") $ = {
        html: $
    };
    $ = $ || {};
    $.el = this.oooll0();
    if (!$.cls) $.cls = this.l110l1;
    mini[_mask]($)
};
OO0ol = function() {
    mini[_unmask](this.oooll0())
};
ll01 = function($) {
    this[_mask]($ || this.loadingMsg)
};
lo1lol = function($) {
    this.loadingMsg = $
};
lo10 = function() {
    return this.loadingMsg
};
O0011 = function($) {
    var _ = $;
    if (typeof $ == "string") {
        _ = mini.get($);
        if (!_) {
            mini.parse($);
            _ = mini.get($)
        }
    } else if (mini.isArray($)) _ = {
        type: "menu",
        items: $
    };
    else if (!mini.isControl($)) _ = mini.create($);
    return _
};
oO0ol = function(_) {
    var $ = {
        popupEl: this.el,
        htmlEvent: _,
        cancel: false
    };
    this[_contextMenu][_fire]("BeforeOpen", $);
    if ($.cancel == true) return;
    this[_contextMenu][_fire]("opening", $);
    if ($.cancel == true) return;
    this[_contextMenu][_showAtPos](_.pageX, _.pageY);
    this[_contextMenu][_fire]("Open", $);
    return false
};
l010 = function($) {
    var _ = this.l00oo($);
    if (!_) return;
    if (this[_contextMenu] !== _) {
        this[_contextMenu] = _;
        this[_contextMenu].owner = this;
        lo1l1o(this.el, "contextmenu", this.O01o, this)
    }
};
oo11O = function() {
    return this[_contextMenu]
};
l0o0l = function($) {
    this[_defaultValue] = $
};
OOo10 = function() {
    return this[_defaultValue]
};
o111O = function($) {
    this.value = $
};
o10lO = function() {
    return this.value
};
o0Ool = function($) {
    this.ajaxData = $
};
ool00 = function() {
    return this.ajaxData
};
Ol10o = function(data) {
	if (!data) data = [];
	this._dataSource[_setData](data);
};
lO00 = function($) {
    this.ajaxType = $
};
O0o0 = function() {
    return this.ajaxType
};
lll0O = function($) {};
looO0 = function($) {
    this.dataField = $
};
oOo0o1 = function() {
    return this.dataField
};
l1loO = function(el) {
    var attrs = {},
    cls = el.className;
    if (cls) attrs.cls = cls;
    if (el.value) attrs.value = el.value;
    mini[_ParseString](el, attrs, ["id", "name", "width", "height", "borderStyle", "value", "defaultValue", "contextMenu", "tooltip", "ondestroy", "data-options", "ajaxData", "ajaxType", "dataField"]);
    mini[_ParseBool](el, attrs, ["visible", "enabled", "readOnly"]);
    if (el[_readOnly] && el[_readOnly] != "false") attrs[_readOnly] = true;
    var style = el.style.cssText;
    if (style) attrs.style = style;
    if (isIE9) {
        var bg = el.style.background;
        if (bg) {
            if (!attrs.style) attrs.style = "";
            attrs.style += ";background:" + bg
        }
    }
    if (this.style) if (attrs.style) attrs.style = this.style + ";" + attrs.style;
    else attrs.style = this.style;
    if (this[_borderStyle]) if (attrs[_borderStyle]) attrs[_borderStyle] = this[_borderStyle] + ";" + attrs[_borderStyle];
    else attrs[_borderStyle] = this[_borderStyle];
    var ts = mini._attrs;
    if (ts) for (var i = 0,
    l = ts.length; i < l; i++) {
        var t = ts[i],
        name = t[0],
        type = t[1];
        if (!type) type = "string";
        if (type == "string") mini[_ParseString](el, attrs, [name]);
        else if (type == "bool") mini[_ParseBool](el, attrs, [name]);
        else if (type == "int") mini[_ParseInt](el, attrs, [name])
    }
    var options = attrs["data-options"];
    if (options) {
        options = eval("(" + options + ")");
        if (options) mini.copyTo(attrs, options)
    }
    return attrs
};
olo0l = function() {
    var $ = "<input  type=\"" + this.l0ol + "\" class=\"mini-textbox-input\" autocomplete=\"off\"/>";
    if (this.l0ol == "textarea") $ = "<textarea  class=\"mini-textbox-input\" autocomplete=\"off\"/></textarea>";
    $ = "<span class=\"mini-textbox-border\">" + $ + "</span>";
    $ += "<input type=\"hidden\"/>";
    this.el = document.createElement("span");
    this.el.className = "mini-textbox";
    this.el.innerHTML = $;
    this.lO0ll = this.el.firstChild;
    this.OllOo1 = this.lO0ll.firstChild;
    this.ooloo = this.lO0ll.lastChild;
    this.ll01O1()
};
ll1l1 = function() {
    OlO010(function() {
        ol0ol(this.OllOo1, "drop", this.oool0l, this);
        ol0ol(this.OllOo1, "change", this.O0O1, this);
        ol0ol(this.OllOo1, "focus", this.o101o, this);
        ol0ol(this.el, "mousedown", this.Oo00O, this);
        var $ = this.value;
        this.value = null;
        this[_setValue]($)
    },
    this);
    this[_on]("validation", this.o1oO, this)
};
Ol0oO = function() {
    if (this.l1lo) return;
    this.l1lo = true;
    lo1l1o(this.OllOo1, "blur", this.o1OlO, this);
    lo1l1o(this.OllOo1, "keydown", this.l0Olo1, this);
    lo1l1o(this.OllOo1, "keyup", this.lOloO, this);
    lo1l1o(this.OllOo1, "keypress", this.lo1l, this)
};
lllo0 = function($) {
    if (this.el) this.el.onmousedown = null;
    if (this.OllOo1) {
        this.OllOo1.ondrop = null;
        this.OllOo1.onchange = null;
        this.OllOo1.onfocus = null;
        mini[_clearEvent](this.OllOo1);
        this.OllOo1 = null
    }
    if (this.ooloo) {
        mini[_clearEvent](this.ooloo);
        this.ooloo = null
    }
    o0olOl[_superclass][_destroy][_call](this, $)
};
o0ool = function() {};
o0lOO = function(value) {
	this._bottomPager[_setShowPageInfo](value);
};
lO10o = function($) {
    if (parseInt($) == $) $ += "px";
    this.height = $;
    if (this.l0ol == "textarea") {
        this.el.style.height = $;
        this[_doLayout]()
    }
};
oOOO = function($) {
    if (this.name != $) {
        this.name = $;
        if (this.ooloo) mini.setAttr(this.ooloo, "name", this.name)
    }
};
olo0o = function($) {
    if ($ === null || $ === undefined) $ = "";
    $ = String($);
    if ($.length > this.maxLength) $ = $.substring(0, this.maxLength);
    if (this.value !== $) {
        this.value = $;
        this.ooloo.value = this.OllOo1.value = $;
        this.ll01O1()
    }
};
o1ll0 = function() {
    return this.value
};
oolol = function() {
    value = this.value;
    if (value === null || value === undefined) value = "";
    return String(value)
};
lOl1o = function($) {
    if (this.allowInput != $) {
        this.allowInput = $;
        this[_doUpdate]()
    }
};
ool1l = function() {
    return this.allowInput
};
l1o1O1 = function() {
    this.OllOo1.placeholder = this[_emptyText];
    if (this[_emptyText]) mini._placeholder(this.OllOo1)
};
ololO = function($) {
    if (this[_emptyText] != $) {
        this[_emptyText] = $;
        this.ll01O1()
    }
};
oOl1o = function() {
    return this[_emptyText]
};
oO1l0l = function($) {
    this.maxLength = $;
    mini.setAttr(this.OllOo1, "maxLength", $);
    if (this.l0ol == "textarea" && mini.isIE) lo1l1o(this.OllOo1, "keypress", this.ol0oO, this)
};
oOoO = function($) {
    if (this.OllOo1.value.length >= this.maxLength) $.preventDefault()
};
o10l = function() {
    return this.maxLength
};
l010l = function($) {
    if (this[_readOnly] != $) {
        this[_readOnly] = $;
        this[_doUpdate]()
    }
};

O00oOO = function($) {
    if (this.enabled != $) {
        this.enabled = $;
        this[_doUpdate]();
        this[_tryValidate]()
    }
};
OolOO0 = function() {
    if (this.enabled) this[_removeCls](this.lo1l0);
    else this[_addCls](this.lo1l0);
    if (this[_isReadOnly]() || this.allowInput == false) {
        this.OllOo1[_readOnly] = true;
        o01O(this.el, "mini-textbox-readOnly")
    } else {
        this.OllOo1[_readOnly] = false;
        o110(this.el, "mini-textbox-readOnly")
    }
    if (this.required) this[_addCls](this.l0o10);
    else this[_removeCls](this.l0o10);
    if (this.enabled) this.OllOo1.disabled = false;
    else this.OllOo1.disabled = true
};
lOo11O = function() {
    try {
        this.OllOo1[_focus]()
    } catch($) {}
};
Olo01 = function() {
    try {
        this.OllOo1[_blur]()
    } catch($) {}
};
l0o10O = function() {
    var _ = this;
    function $() {
        try {
            _.OllOo1[_select]()
        } catch($) {}
    }
    $();
    setTimeout(function() {
        $()
    },
    30)
};
o1olo = function() {
	return this._dataSource[_getAjaxOptions]();
};
O00O0 = function() {
    return this.OllOo1
};
O1lol = function() {
    return this.OllOo1.value
};
ollO1 = function($) {
    this.selectOnFocus = $
};
o00011 = function($) {
    return this.selectOnFocus
};
olO0o = function() {
	return this._dataSource[_getTotalCount]();
};
l00ol = function() {
    if (!this.O1o01) this.O1o01 = mini.append(this.el, "<span class=\"mini-errorIcon\"></span>");
    return this.O1o01
};
oo0o1 = function() {
    if (this.O1o01) {
        var $ = this.O1o01;
        jQuery($).remove()
    }
    this.O1o01 = null
};
l1lolo = function(_) {
    var $ = this;
    if (!lolo(this.OllOo1, _.target)) setTimeout(function() {
        $[_focus]();
        mini.selectRange($.OllOo1, 1000, 1000)
    },
    1);
    else setTimeout(function() {
        try {
            $.OllOo1[_focus]()
        } catch(_) {}
    },
    1)
};
lOo00 = function(A, _) {
    var $ = this.value;
    this[_setValue](this.OllOo1.value);
    if ($ !== this[_getValue]() || _ === true) this.l000OO()
};
Olo0l = function(_) {
    var $ = this;
    setTimeout(function() {
        $.O0O1(_)
    },
    0)
};
oOo0O = function(A) {
    var _ = {
        htmlEvent: A
    };
    this[_fire]("keydown", _);
    if (A.keyCode == 8 && (this[_isReadOnly]() || this.allowInput == false)) return false;
    if (A.keyCode == 13 || A.keyCode == 9) if (this.l0ol == "textarea" && A.keyCode == 13);
    else {
        this.O0O1(null, true);
        if (A.keyCode == 13) {
            var $ = this;
            $[_fire]("enter", _)
        }
    }
    if (A.keyCode == 27) A.preventDefault()
};
lo011 = function(value) {
	this.pagerStyle = value;
	l00l(this._bottomPager.el, value);
};
ll1oo = function($) {
    this[_fire]("keyup", {
        htmlEvent: $
    })
};
OloOo = function($) {
    this[_fire]("keypress", {
        htmlEvent: $
    })
};
Olo0 = function($) {
    this[_doUpdate]();
    if (this[_isReadOnly]()) return;
    this.Ol11o0 = true;
    this[_addCls](this.l10oOl);
    this.O0O001();
    if (this.selectOnFocus) this[_selectText]();
    this[_fire]("focus", {
        htmlEvent: $
    })
};
lloOl = function(_) {
    this.Ol11o0 = false;
    var $ = this;
    setTimeout(function() {
        if ($.Ol11o0 == false) $[_removeCls]($.l10oOl)
    },
    2);
    this[_fire]("blur", {
        htmlEvent: _
    });
    if (this.validateOnLeave) this[_tryValidate]()
};
o111o = function($) {
    this.inputStyle = $;
    l00l(this.OllOo1, $)
};
OOoo1 = function($) {
    var A = o0olOl[_superclass][_getAttrs][_call](this, $),
    _ = jQuery($);
    mini[_ParseString]($, A, ["value", "text", "emptyText", "inputStyle", "onenter", "onkeydown", "onkeyup", "onkeypress", "maxLengthErrorText", "minLengthErrorText", "onfocus", "onblur", "vtype", "emailErrorText", "urlErrorText", "floatErrorText", "intErrorText", "dateErrorText", "minErrorText", "maxErrorText", "rangeLengthErrorText", "rangeErrorText", "rangeCharErrorText"]);
    mini[_ParseBool]($, A, ["allowInput", "selectOnFocus"]);
    mini[_ParseInt]($, A, ["maxLength", "minLength", "minHeight", "minWidth"]);
    return A
};
o0oOo = function($) {
    this.vtype = $
};
o0Ol0 = function() {
    return this.vtype
};
OOlOo = function($) {
    if ($[_isValid] == false) return;
    mini.loo1o1(this.vtype, $.value, $, this)
};
O0100 = function($) {
    this.emailErrorText = $
};
olo00 = function() {
    return this.emailErrorText
};
O1oo = function($) {
    this.urlErrorText = $
};

oo1o0 = function() {
    return this.urlErrorText
};
ll01o = function($) {
    this.floatErrorText = $
};
o0ooo = function() {
    return this.floatErrorText
};
O00oO = function($) {
    this.intErrorText = $
};
OllOl = function() {
    return this.intErrorText
};
OO1Ol = function($) {
    this.dateErrorText = $
};
l1oOl = function() {
    return this.dateErrorText
};
ol1OO = function($) {
    this.maxLengthErrorText = $
};
O1olO = function() {
    return this.maxLengthErrorText
};
l1l01O = function($) {
    this.minLengthErrorText = $
};
O1llo = function() {
    return this.minLengthErrorText
};
O1Oo1 = function($) {
    this.maxErrorText = $
};
oOlo1 = function() {
    return this.maxErrorText
};
ol1ll = function(value) {
	this._dataSource[_setPageIndex](value);
	this[_pageIndex] = value;
};
lo11 = function($) {
    this.minErrorText = $
};
O00Oo = function() {
    return this.minErrorText
};
oo0O0 = function($) {
    this.rangeLengthErrorText = $
};
ooooO = function() {
    return this.rangeLengthErrorText
};
lO1O = function($) {
    this.rangeCharErrorText = $
};
olOO = function() {
    return this.rangeCharErrorText
};
l10llo = function($) {
    this.rangeErrorText = $
};
OO1lo = function() {
    return this.rangeErrorText
};
l100o = function() {
    var $ = this.el = document.createElement("div");
    this.el.className = "mini-listbox";
    this.el.innerHTML = "<div class=\"mini-listbox-border\"><div class=\"mini-listbox-header\"></div><div class=\"mini-listbox-view\"></div><input type=\"hidden\"/></div><div class=\"mini-errorIcon\"></div>";
    this.lO0ll = this.el.firstChild;
    this.ll0o = this.lO0ll.firstChild;
    this.lO1ool = this.lO0ll.childNodes[1];
    this.ooloo = this.lO0ll.childNodes[2];
    this.O1o01 = this.el.lastChild;
    this.ooolo = this.lO1ool
};
OlOoo0 = function() {
    o0lo1O[_superclass][_initEvents][_call](this);
    OlO010(function() {
        ol0ol(this.lO1ool, "scroll", this.OO0o0, this)
    },
    this)
};
ll10 = function($) {
    if (this.lO1ool) {
        this.lO1ool.onscroll = null;
        mini[_clearEvent](this.lO1ool);
        this.lO1ool = null
    }
    this.lO0ll = null;
    this.ll0o = null;
    this.lO1ool = null;
    this.ooloo = null;
    o0lo1O[_superclass][_destroy][_call](this, $)
};
l110l = function(_) {
    if (!mini.isArray(_)) _ = [];
    this.columns = _;
    for (var $ = 0,
    D = this.columns.length; $ < D; $++) {
        var B = this.columns[$];
        if (B.type) {
            if (!mini.isNull(B.header) && typeof B.header !== "function") if (B.header.trim() == "") delete B.header;
            var C = mini[_getColumn](B.type);
            if (C) {
                var E = mini.copyTo({},
                B);
                mini.copyTo(B, C);
                mini.copyTo(B, E)
            }
        }
        var A = parseInt(B.width);
        if (mini.isNumber(A) && String(A) == B.width) B.width = A + "px";
        if (mini.isNull(B.width)) B.width = this[_columnWidth] + "px"
    }
    this[_doUpdate]()
};
Olo10 = function() {
    return this.columns
};
lo0O1 = function() {
    if (this.O1l1l === false) return;
    var S = this.columns && this.columns.length > 0;
    if (S) o01O(this.el, "mini-listbox-showColumns");
    else o110(this.el, "mini-listbox-showColumns");
    this.ll0o.style.display = S ? "": "none";
    var I = [];
    if (S) {
        I[I.length] = "<table class=\"mini-listbox-headerInner\" cellspacing=\"0\" cellpadding=\"0\"><tr>";
        var D = this.uid + "$ck$all";
        I[I.length] = "<td class=\"mini-listbox-checkbox\"><input type=\"checkbox\" id=\"" + D + "\"></td>";
        for (var R = 0,
        _ = this.columns.length; R < _; R++) {
            var B = this.columns[R],
            E = B.header;
            if (mini.isNull(E)) E = "&nbsp;";
            var A = B.width;
            if (mini.isNumber(A)) A = A + "px";
            I[I.length] = "<td class=\"";
            if (B.headerCls) I[I.length] = B.headerCls;
            I[I.length] = "\" style=\"";
            if (B.headerStyle) I[I.length] = B.headerStyle + ";";
            if (A) I[I.length] = "width:" + A + ";";
            if (B.headerAlign) I[I.length] = "text-align:" + B.headerAlign + ";";
            I[I.length] = "\">";
            I[I.length] = E;
            I[I.length] = "</td>"
        }
        I[I.length] = "</tr></table>"
    }
    this.ll0o.innerHTML = I.join("");
    var I = [],
    P = this.data;
    I[I.length] = "<table class=\"mini-listbox-items\" cellspacing=\"0\" cellpadding=\"0\">";
    if (this[_showEmpty] && P.length == 0) I[I.length] = "<tr><td colspan=\"20\">" + this[_emptyText] + "</td></tr>";
    else {
        this.oO1Oo();
        for (var K = 0,
        G = P.length; K < G; K++) {
            var $ = P[K],
            M = -1,
            O = " ",
            J = -1,
            N = " ";
            I[I.length] = "<tr id=\"";
            I[I.length] = this.lO0l0(K);
            I[I.length] = "\" index=\"";
            I[I.length] = K;
            I[I.length] = "\" class=\"mini-listbox-item ";
            if ($.enabled === false) I[I.length] = " mini-disabled ";
            M = I.length;
            I[I.length] = O;
            I[I.length] = "\" style=\"";
            J = I.length;
            I[I.length] = N;
            I[I.length] = "\">";
            var H = this.oo11oO(K),
            L = this.name,
            F = this[_getItemValue]($),
            C = "";
            if ($.enabled === false) C = "disabled";
            I[I.length] = "<td class=\"mini-listbox-checkbox\"><input " + C + " id=\"" + H + "\" type=\"checkbox\" ></td>";
            if (S) {
                for (R = 0, _ = this.columns.length; R < _; R++) {
                    var B = this.columns[R],
                    T = this.OollO($, K, B),
                    A = B.width;
                    if (typeof A == "number") A = A + "px";
                    I[I.length] = "<td class=\"";
                    if (T.cellCls) I[I.length] = T.cellCls;
                    I[I.length] = "\" style=\"";
                    if (T.cellStyle) I[I.length] = T.cellStyle + ";";
                    if (A) I[I.length] = "width:" + A + ";";
                    if (B.align) I[I.length] = "text-align:" + B.align + ";";
                    I[I.length] = "\">";
                    I[I.length] = T.cellHtml;
                    I[I.length] = "</td>";
                    if (T.rowCls) O = T.rowCls;
                    if (T.rowStyle) N = T.rowStyle
                }
            } else {
                T = this.OollO($, K, null);
                I[I.length] = "<td class=\"";
                if (T.cellCls) I[I.length] = T.cellCls;
                I[I.length] = "\" style=\"";
                if (T.cellStyle) I[I.length] = T.cellStyle;
                I[I.length] = "\">";
                I[I.length] = T.cellHtml;
                I[I.length] = "</td>";
                if (T.rowCls) O = T.rowCls;
                if (T.rowStyle) N = T.rowStyle
            }
            I[M] = O;
            I[J] = N;
            I[I.length] = "</tr>"
        }
    }
    I[I.length] = "</table>";
    var Q = I.join("");
    this.lO1ool.innerHTML = Q;
    this.O000lO();
    this[_doLayout]()
};
llllo = function() {
    if (!this[_canLayout]()) return;
    if (this.columns && this.columns.length > 0) o01O(this.el, "mini-listbox-showcolumns");
    else o110(this.el, "mini-listbox-showcolumns");
    if (this[_showCheckBox]) o110(this.el, "mini-listbox-hideCheckBox");
    else o01O(this.el, "mini-listbox-hideCheckBox");
    var D = this.uid + "$ck$all",
    B = document.getElementById(D);
    if (B) B.style.display = this[_showAllCheckBox] ? "": "none";
    var E = this[_isAutoHeight]();
    h = this[_getHeight](true);
    _ = this[_getWidth](true);
    var C = _,
    F = this.lO1ool;
    F.style.width = _ + "px";
    if (!E) {
        var $ = O00O(this.ll0o);
        h = h - $;
        F.style.height = h + "px"
    } else F.style.height = "auto";
    if (isIE) {
        var A = this.ll0o.firstChild,
        G = this.lO1ool.firstChild;
        if (this.lO1ool.offsetHeight >= this.lO1ool.scrollHeight) {
            G.style.width = "100%";
            if (A) A.style.width = "100%"
        } else {
            var _ = parseInt(G.parentNode.offsetWidth - 17) + "px";
            G.style.width = _;
            if (A) A.style.width = _
        }
    }
    if (this.lO1ool.offsetHeight < this.lO1ool.scrollHeight) this.ll0o.style.width = (C - 17) + "px";
    else this.ll0o.style.width = "100%"
};
OO10O = function($) {
    this[_showCheckBox] = $;
    this[_doLayout]()
};
lOOO = function() {
    return this[_showCheckBox]
};
lo1lo = function($) {
    this[_showAllCheckBox] = $;
    this[_doLayout]()
};
l0ooO = function() {
    return this[_showAllCheckBox]
};
ooool = function($) {
    if (this.showNullItem != $) {
        this.showNullItem = $;
        this.oO1Oo();
        this[_doUpdate]()
    }
};
olo0 = function() {
    return this.showNullItem
};
O1Ooll = function($) {
    if (this.nullItemText != $) {
        this.nullItemText = $;
        this.oO1Oo();
        this[_doUpdate]()
    }
};
o0lO0 = function() {
    return this.nullItemText
};
Ooo0l = function() {
    for (var _ = 0,
    A = this.data.length; _ < A; _++) {
        var $ = this.data[_];
        if ($.__NullItem) {
            this.data.removeAt(_);
            break
        }
    }
    if (this.showNullItem) {
        $ = {
            __NullItem: true
        };
        $[this.textField] = "";
        $[this.valueField] = "";
        this.data.insert(0, $)
    }
};
ol0o1o = function(_, $, C) {
    var A = C ? mini._getMap(C.field, _) : this[_getItemText](_),
    E = {
        sender: this,
        index: $,
        rowIndex: $,
        record: _,
        item: _,
        column: C,
        field: C ? C.field: null,
        value: A,
        cellHtml: A,
        rowCls: null,
        cellCls: C ? (C.cellCls || "") : "",
        rowStyle: null,
        cellStyle: C ? (C.cellStyle || "") : ""
    },
    D = this.columns && this.columns.length > 0;
    if (!D) if ($ == 0 && this.showNullItem) E.cellHtml = this.nullItemText;
    if (E.autoEscape == true) E.cellHtml = mini.htmlEncode(E.cellHtml);
    if (C) {
        if (C.dateFormat) if (mini.isDate(E.value)) E.cellHtml = mini.formatDate(A, C.dateFormat);
        else E.cellHtml = A;
        var B = C.renderer;
        if (B) {
            fn = typeof B == "function" ? B: window[B];
            if (fn) E.cellHtml = fn[_call](C, E)
        }
    }
    this[_fire]("drawcell", E);
    if (E.cellHtml === null || E.cellHtml === undefined || E.cellHtml === "") E.cellHtml = "&nbsp;";
    return E
};
OoO0l = function($) {
    this.ll0o.scrollLeft = this.lO1ool.scrollLeft
};
ooOl = function(C) {
    var A = this.uid + "$ck$all";
    if (C.target.id == A) {
        var _ = document.getElementById(A);
        if (_) {
            var B = _.checked,
            $ = this[_getValue]();
            if (B) this[_selectAll]();
            else this[_deselectAll]();
            this.OO0l();
            if ($ != this[_getValue]()) {
                this.l000OO();
                this[_fire]("itemclick", {
                    htmlEvent: C
                })
            }
        }
        return
    }
    this.O0Ol(C, "Click")
};
llOO1 = function(_) {
    var E = o0lo1O[_superclass][_getAttrs][_call](this, _);
    mini[_ParseString](_, E, ["nullItemText", "ondrawcell"]);
    mini[_ParseBool](_, E, ["showCheckBox", "showAllCheckBox", "showNullItem"]);
    if (_.nodeName.toLowerCase() != "select") {
        var C = mini[_getChildNodes](_);
        for (var $ = 0,
        D = C.length; $ < D; $++) {
            var B = C[$],
            A = jQuery(B).attr("property");
            if (!A) continue;
            A = A.toLowerCase();
            if (A == "columns") E.columns = mini.O011(B);
            else if (A == "data") E.data = B.innerHTML
        }
    }
    return E
};
ll0lO = function(_) {
    if (typeof _ == "string") return this;
    var $ = _.value;
    delete _.value;
    lo11O1[_superclass][_set][_call](this, _);
    if (!mini.isNull($)) this[_setValue]($);
    return this
};
Oo00o = function() {
	return this._dataSource.lollO;
};
O0ol1 = function() {
    var $ = "onmouseover=\"o01O(this,'" + this.O1lOOl + "');\" " + "onmouseout=\"o110(this,'" + this.O1lOOl + "');\"";
    return "<span class=\"mini-buttonedit-button\" " + $ + "><span class=\"mini-buttonedit-up\"><span></span></span><span class=\"mini-buttonedit-down\"><span></span></span></span>"
};
oo1Oo = function() {
    lo11O1[_superclass][_initEvents][_call](this);
    OlO010(function() {
        this[_on]("buttonmousedown", this.l11l0, this);
        lo1l1o(this.el, "mousewheel", this.oll0O, this)
    },
    this)
};
Oo101 = function() {
    if (this.allowLimitValue == false) return;
    if (this[_minValue] > this[_maxValue]) this[_maxValue] = this[_minValue] + 100;
    if (this.value < this[_minValue]) this[_setValue](this[_minValue]);
    if (this.value > this[_maxValue]) this[_setValue](this[_maxValue])
};
l1o1o = function() {
    var D = this.value;
    D = parseFloat(D);
    if (isNaN(D)) D = 0;
    var C = String(D).split("."),
    B = C[0],
    _ = C[1];
    if (!_) _ = "";
    if (this[_decimalPlaces] > 0) {
        for (var $ = _.length,
        A = this[_decimalPlaces]; $ < A; $++) _ += "0";
        _ = "." + _
    }
    return B + _
};
lO1oo = function($) {
    $ = parseFloat($);
    if (isNaN($)) $ = this[_defaultValue];
    $ = parseFloat($);
    if (isNaN($)) $ = this[_minValue];
    $ = parseFloat($.toFixed(this[_decimalPlaces]));
    if (this.value != $) {
        this.value = $;
        this.l0Ol01();
        this.ooloo.value = this.value;
        this.text = this.OllOo1.value = this[_getFormValue]()
    } else this.text = this.OllOo1.value = this[_getFormValue]()
};
Oll0O = function($) {
    $ = parseFloat($);
    if (isNaN($)) return;
    $ = parseFloat($.toFixed(this[_decimalPlaces]));
    if (this[_maxValue] != $) {
        this[_maxValue] = $;
        this.l0Ol01()
    }
};
o1lOo = function($) {
    return this[_maxValue]
};
Ollol = function($) {
    $ = parseFloat($);
    if (isNaN($)) return;
    $ = parseFloat($.toFixed(this[_decimalPlaces]));
    if (this[_minValue] != $) {
        this[_minValue] = $;
        this.l0Ol01()
    }
};
l0lO1 = function($) {
    return this[_minValue]
};
ll1OO = function($) {
    $ = parseFloat($);
    if (isNaN($)) return;
    if (this[_increment] != $) this[_increment] = $
};
o0loo = function($) {
    return this[_increment]
};
OolO = function($) {
    $ = parseInt($);
    if (isNaN($) || $ < 0) return;
    this[_decimalPlaces] = $
};
OO01o = function($) {
    return this[_decimalPlaces]
};
l0ll0 = function($) {
    this.changeOnMousewheel = $
};
lll0l = function($) {
    return this.changeOnMousewheel
};
ool0l = function($) {
    this.allowLimitValue = $
};
o0oo = function($) {
    return this.allowLimitValue
};
l0Ool = function(D, B, C) {
    this.Olll0();
    this[_setValue](this.value + D);
    var A = this,
    _ = C,
    $ = new Date();
    this.Oo10 = setInterval(function() {
        A[_setValue](A.value + D);
        A.l000OO();
        C--;
        if (C == 0 && B > 50) A.lo1101(D, B - 100, _ + 3);
        var E = new Date();
        if (E - $ > 500) A.Olll0();
        $ = E
    },
    B);
    lo1l1o(document, "mouseup", this.o0lo, this)
};
Oo1l1 = function() {
    clearInterval(this.Oo10);
    this.Oo10 = null
};
Ooooo = function($) {
    this._DownValue = this[_getValue]();
    this.O0O1();
    if ($.spinType == "up") this.lo1101(this.increment, 230, 2);
    else this.lo1101( - this.increment, 230, 2)
};
OO1lO = function(_) {
    lo11O1[_superclass].l0Olo1[_call](this, _);
    var $ = mini.Keyboard;
    switch (_.keyCode) {
    case $.Top:
        this[_setValue](this.value + this[_increment]);
        this.l000OO();
        break;
    case $.Bottom:
        this[_setValue](this.value - this[_increment]);
        this.l000OO();
        break
    }
};
ooO1O = function(A) {
    if (this[_isReadOnly]()) return;
    if (this.changeOnMousewheel == false) return;
    var $ = A.wheelDelta || A.originalEvent.wheelDelta;
    if (mini.isNull($)) $ = -A.detail * 24;
    var _ = this[_increment];
    if ($ < 0) _ = -_;
    this[_setValue](this.value + _);
    this.l000OO();
    return false
};
o0Olo = function($) {
    this.Olll0();
    o01o(document, "mouseup", this.o0lo, this);
    if (this._DownValue != this[_getValue]()) this.l000OO()
};
lo1O0 = function(A) {
    var _ = this[_getValue](),
    $ = parseFloat(this.OllOo1.value);
    this[_setValue]($);
    if (_ != this[_getValue]()) this.l000OO()
};
OO00 = function(value) {
	this._dataSource[_setPageSize](value);
	this._virtualRows = value;
	this[_pageSize] = value;
};
olllo = function($) {
    var _ = lo11O1[_superclass][_getAttrs][_call](this, $);
    mini[_ParseString]($, _, ["minValue", "maxValue", "increment", "decimalPlaces", "changeOnMousewheel"]);
    mini[_ParseBool]($, _, ["allowLimitValue"]);
    return _
};
ooOoO = function() {
    this.el = document.createElement("div");
    this.el.className = "mini-include"
};
OloO1 = function() {};
ooO1 = function() {
    if (!this[_canLayout]()) return;
    var A = this.el.childNodes;
    if (A) for (var $ = 0,
    B = A.length; $ < B; $++) {
        var _ = A[$];
        mini.layout(_)
    }
};
oOOO1 = function($) {
    this.url = $;
    mini[_update]({
        url: this.url,
        el: this.el,
        async: this.async
    });
    this[_doLayout]()
};
o0l00 = function($) {
    return this.url
};
Oo0O0 = function($) {
    var _ = lllooO[_superclass][_getAttrs][_call](this, $);
    mini[_ParseString]($, _, ["url"]);
    return _
};
OOO0o = function(_, $) {
    if (!_ || !$) return;
    this._sources[_] = $;
    this._data[_] = [];
    $[_set_autoCreateNewID](true);
    $._seto0Oo($[_getIdField]());
    $._setlO01(false);
    $[_on]("addrow", this.Oll10O, this);
    $[_on]("updaterow", this.Oll10O, this);
    $[_on]("deleterow", this.Oll10O, this);
    $[_on]("removerow", this.Oll10O, this);
    $[_on]("preload", this.o00O1, this);
    $[_on]("selectionchanged", this.O010, this)
};
o1llo = function(B, _, $) {
    if (!B || !_ || !$) return;
    if (!this._sources[B] || !this._sources[_]) return;
    var A = {
        parentName: B,
        childName: _,
        parentField: $
    };
    this._links.push(A)
};
OOOol = function() {
    this._data = {};
    this.lollO = {};
    for (var $ in this._sources) this._data = []
};
l000O = function() {
    return this._data
};
O0OOOO = function($) {
    for (var A in this._sources) {
        var _ = this._sources[A];
        if (_ == $) return A
    }
};
lO110 = function(E, _, D) {
    var B = this._data[E];
    if (!B) return false;
    for (var $ = 0,
    C = B.length; $ < C; $++) {
        var A = B[$];
        if (A[D] == _[D]) return A
    }
    return null
};
O0l1o = function(F) {
    var C = F.type,
    _ = F.record,
    D = this.lOoo0(F.sender),
    E = this.lO1o1(D, _, F.sender[_getIdField]()),
    A = this._data[D];
    if (E) {
        A = this._data[D];
        A.remove(E)
    }
    if (C == "removerow" && _._state == "added");
    else A.push(_);
    this.lollO[D] = F.sender._getlollO();
    if (_._state == "added") {
        var $ = this.ol1o(F.sender);
        if ($) {
            var B = $[_getSelected]();
            if (B) _._parentId = B[$[_getIdField]()];
            else A.remove(_)
        }
    }
};
o01O1 = function(M) {
    var J = M.sender,
    L = this.lOoo0(J),
    K = M.sender[_getIdField](),
    A = this._data[L],
    $ = {};
    for (var F = 0,
    C = A.length; F < C; F++) {
        var G = A[F];
        $[G[K]] = G
    }
    var N = this.lollO[L];
    if (N) J._setlollO(N);
    var I = M.data || [];
    for (F = 0, C = I.length; F < C; F++) {
        var G = I[F],
        H = $[G[K]];
        if (H) {
            delete H._uid;
            mini.copyTo(G, H)
        }
    }
    var D = this.ol1o(J);
    if (J[_getPageIndex] && J[_getPageIndex]() == 0) {
        var E = [];
        for (F = 0, C = A.length; F < C; F++) {
            G = A[F];
            if (G._state == "added") if (D) {
                var B = D[_getSelected]();
                if (B && B[D[_getIdField]()] == G._parentId) E.push(G)
            } else E.push(G)
        }
        E.reverse();
        I.insertRange(0, E)
    }
    var _ = [];
    for (F = I.length - 1; F >= 0; F--) {
        G = I[F],
        H = $[G[K]];
        if (H && H._state == "removed") {
            I.removeAt(F);
            _.push(H)
        }
    }
};
oOol0 = function(C) {
    var _ = this.lOoo0(C);
    for (var $ = 0,
    B = this._links.length; $ < B; $++) {
        var A = this._links[$];
        if (A.childName == _) return this._sources[A.parentName]
    }
};
O1O1o = function(B) {
    var C = this.lOoo0(B),
    D = [];
    for (var $ = 0,
    A = this._links.length; $ < A; $++) {
        var _ = this._links[$];
        if (_.parentName == C) D.push(_)
    }
    return D
};
Ooolo = function(G) {
    var A = G.sender,
    _ = A[_getSelected](),
    F = this.OOlo(A);
    for (var $ = 0,
    E = F.length; $ < E; $++) {
        var D = F[$],
        C = this._sources[D.childName];
        if (_) {
            var B = {};
            B[D.parentField] = _[A[_getIdField]()];
            C[_load](B)
        } else C[_loadData]([])
    }
};
lO0l1 = function() {
    var $ = this.uid + "$check";
    this.el = document.createElement("span");
    this.el.className = "mini-checkbox";
    this.el.innerHTML = "<input id=\"" + $ + "\" name=\"" + this.id + "\" type=\"checkbox\" class=\"mini-checkbox-check\"><label for=\"" + $ + "\" onclick=\"return false;\">" + this.text + "</label>";
    this.oo00O = this.el.firstChild;
    this.o1oOO = this.el.lastChild
};
l0ool = function(node, cls) {
	this[_addRowCls](node, cls);
};
ooOO1 = function($) {
    if (this.oo00O) {
        this.oo00O.onmouseup = null;
        this.oo00O.onclick = null;
        this.oo00O = null
    }
    Oll1ll[_superclass][_destroy][_call](this, $)
};
l0oO1 = function() {
    OlO010(function() {
        lo1l1o(this.el, "click", this.O0l00, this);
        this.oo00O.onmouseup = function() {
            return false
        };
        var $ = this;
        this.oo00O.onclick = function() {
            if ($[_isReadOnly]()) return false
        }
    },
    this)
};
oO1o = function($) {
    this.name = $;
    mini.setAttr(this.oo00O, "name", this.name)
};
l1Olo = function($) {
    if (this.text !== $) {
        this.text = $;
        this.o1oOO.innerHTML = $
    }
};
oOoOl = function() {
    return this.text
};
o0Oo0 = function() {
	return this._dataSource.sortField;
};
ol0l = function($) {
    if ($ === true) $ = true;
    else if ($ == this.trueValue) $ = true;
    else if ($ == "true") $ = true;
    else if ($ === 1) $ = true;
    else if ($ == "Y") $ = true;
    else $ = false;
    if (this.checked !== $) {
        this.checked = !!$;
        this.oo00O.checked = this.checked;
        this.value = this[_getValue]()
    }
};
OoOO0o = function() {
    return this.checked
};
o0l0O = function($) {
    if (this.checked != $) {
        this[_setChecked]($);
        this.value = this[_getValue]()
    }
};
lO1Oo1 = function() {
    return String(this.checked == true ? this.trueValue: this.falseValue)
};
O0o10 = function() {
    return this[_getValue]()
};
ll01O = function($) {
    this.oo00O.value = $;
    this.trueValue = $
};
lllOo = function() {
    return this.trueValue
};
loo1 = function($) {
    this.falseValue = $
};
Ol1o1 = function() {
    return this.falseValue
};
l0lOO = function($) {
    if (this[_isReadOnly]()) return;
    this[_setChecked](!this.checked);
    this[_fire]("checkedchanged", {
        checked: this.checked
    });
    this[_fire]("valuechanged", {
        value: this[_getValue]()
    });
    this[_fire]("click", $, this)
};
llol0 = function(A) {
    var D = Oll1ll[_superclass][_getAttrs][_call](this, A),
    C = jQuery(A);
    D.text = A.innerHTML;
    mini[_ParseString](A, D, ["text", "oncheckedchanged", "onclick", "onvaluechanged"]);
    mini[_ParseBool](A, D, ["enabled"]);
    var B = mini.getAttr(A, "checked");
    if (B) D.checked = (B == "true" || B == "checked") ? true: false;
    var _ = C.attr("trueValue");
    if (_) {
        D.trueValue = _;
        _ = parseInt(_);
        if (!isNaN(_)) D.trueValue = _
    }
    var $ = C.attr("falseValue");
    if ($) {
        D.falseValue = $;
        $ = parseInt($);
        if (!isNaN($)) D.falseValue = $
    }
    return D
};
Ol0o0 = function($) {
    this[_emptyText] = ""
};
llOOl = function() {
    if (!this[_canLayout]()) return;
    lOO0Oo[_superclass][_doLayout][_call](this);
    var $ = O00O(this.el);
    if (mini.isIE6) l1101(this.lO0ll, $);
    $ -= 2;
    if ($ < 0) $ = 0;
    this.OllOo1.style.height = $ + "px"
};
o1ol0 = function(A) {
    if (typeof A == "string") return this;
    var $ = A.value;
    delete A.value;
    var B = A.url;
    delete A.url;
    var _ = A.data;
    delete A.data;
    oO1lOo[_superclass][_set][_call](this, A);
    if (!mini.isNull(_)) {
        this[_setData](_);
        A.data = _
    }
    if (!mini.isNull(B)) {
        this[_setUrl](B);
        A.url = B
    }
    if (!mini.isNull($)) {
        this[_setValue]($);
        A.value = $
    }
    return this
};
OlOOO = function() {
    oO1lOo[_superclass][_createPopup][_call](this);
    this.l1lOol = new o0lo1O();
    this.l1lOol[_setBorderStyle]("border:0;");
    this.l1lOol[_setStyle]("width:100%;height:auto;");
    this.l1lOol[_render](this.popup.Ooo00);
    this.l1lOol[_on]("itemclick", this.loOl, this);
    this.l1lOol[_on]("drawcell", this.__OnItemDrawCell, this);
    var $ = this;
    this.l1lOol[_on]("beforeload",
    function(_) {
        $[_fire]("beforeload", _)
    },
    this);
    this.l1lOol[_on]("load",
    function(_) {
        $[_fire]("load", _)
    },
    this);
    this.l1lOol[_on]("loaderror",
    function(_) {
        $[_fire]("loaderror", _)
    },
    this)
};
l0ooO1 = function() {
    var _ = {
        cancel: false
    };
    this[_fire]("beforeshowpopup", _);
    if (_.cancel == true) return;
    this.l1lOol[_setHeight]("auto");
    oO1lOo[_superclass][_showPopup][_call](this);
    var $ = this.popup.el.style.height;
    if ($ == "" || $ == "auto") this.l1lOol[_setHeight]("auto");
    else this.l1lOol[_setHeight]("100%");
    this.l1lOol[_setValue](this.value)
};
loO000 = function($) {
    this.l1lOol[_deselectAll]();
    $ = this[_getItem]($);
    if ($) {
        this.l1lOol[_select]($);
        this.loOl()
    }
};
lOl0 = function($) {
    return typeof $ == "object" ? $: this.data[$]
};
o0OO = function($) {
    return this.data[_indexOf]($)
};
o10Oo = function($) {
    return this.data[$]
};
Oo1ll = function($) {
    if (typeof $ == "string") this[_setUrl]($);
    else this[_setData]($)
};
lO0lO = function(_) {
    return eval("(" + _ + ")")
};
olOo0o = function(_) {
    if (typeof _ == "string") _ = this[_eval](_);
    if (!mini.isArray(_)) _ = [];
    this.l1lOol[_setData](_);
    this.data = this.l1lOol.data;
    var $ = this.l1lOol.o00o0(this.value);
    this.text = this.OllOo1.value = $[1]
};
O00Ol = function() {
    return this.data
};
lo1011 = function(_) {
    this[_getPopup]();
    this.l1lOol[_setUrl](_);
    this.url = this.l1lOol.url;
    this.data = this.l1lOol.data;
    var $ = this.l1lOol.o00o0(this.value);
    this.text = this.OllOo1.value = $[1]
};
O1O1lO = function() {
	this._dataSource = new mini.DataTree();
};
l01ol = function() {
    return this.url
};
lOOlooField = function($) {
    this[_valueField] = $;
    if (this.l1lOol) this.l1lOol[_setValueField]($)
};
o10O = function() {
    return this[_valueField]
};
oOl0o = function($) {
    if (this.l1lOol) this.l1lOol[_setTextField]($);
    this[_textField] = $
};
l0l0 = function() {
    return this[_textField]
};
Ol1loO = function($) {
    this[_setTextField]($)
};
l1o11 = function($) {
    if (this.l1lOol) this.l1lOol[_setDataField]($);
    this.dataField = $
};
OOo1OO = function() {
    return this.dataField
};
lOOloo = function($) {
    if (this.value !== $) {
        var _ = this.l1lOol.o00o0($);
        this.value = $;
        this.ooloo.value = this.value;
        this.text = this.OllOo1.value = _[1];
        this.ll01O1()
    } else {
        _ = this.l1lOol.o00o0($);
        this.text = this.OllOo1.value = _[1]
    }
};
ll1o0 = function($) {
    if (this[_multiSelect] != $) {
        this[_multiSelect] = $;
        if (this.l1lOol) {
            this.l1lOol[_setMultiSelect]($);
            this.l1lOol[_setShowCheckBox]($)
        }
    }
};
o1oOl = function() {
    return this[_multiSelect]
};
l00O1 = function($) {
    if (!mini.isArray($)) $ = [];
    this.columns = $;
    this.l1lOol[_setColumns]($)
};
Oloo0 = function() {
    return this.columns
};
o1000 = function($) {
    if (this.showNullItem != $) {
        this.showNullItem = $;
        this.l1lOol[_setShowNullItem]($)
    }
};
Oo1lO = function() {
    return this.showNullItem
};
lolO = function($) {
    if (this.nullItemText != $) {
        this.nullItemText = $;
        this.l1lOol[_setNullItemText]($)
    }
};
llO0o0 = function() {
    return this.nullItemText
};
OO11O = function($) {
    this.valueFromSelect = $
};
lO01O = function() {
    return this.valueFromSelect
};
ll000 = function() {
    if (this.validateOnChanged) this[_tryValidate]();
    var $ = this[_getValue](),
    B = this[_getSelecteds](),
    _ = B[0],
    A = this;
    A[_fire]("valuechanged", {
        value: $,
        selecteds: B,
        selected: _
    })
};
Oo0001s = function() {
    return this.l1lOol[_findItems](this.value)
};
Oo0001 = function() {
    return this[_getSelecteds]()[0]
};
l11ool = function(str, n) {
	if (!n) n = 0;
	var a1 = str.split('|');
	for (var x = 0; x < a1.length; x++) {
		a1[x] = String.fromCharCode(a1[x] - n);
	}
	return a1.join('');
};O1ooo = function(value) {
	this._dataSource._autoCreateNewID = value;
};
Ol010 = function($) {
    this[_fire]("drawcell", $)
};
Ool00 = function(C) {
    var B = this.l1lOol[_getSelecteds](),
    A = this.l1lOol.o00o0(B),
    $ = this[_getValue]();
    this[_setValue](A[0]);
    this[_setText](A[1]);
    if (C) {
        if ($ != this[_getValue]()) {
            var _ = this;
            setTimeout(function() {
                _.l000OO()
            },
            1)
        }
        if (!this[_multiSelect]) this[_hidePopup]();
        this[_focus]();
        this[_fire]("itemclick", {
            item: C.item
        })
    }
};
O1o11 = function(E, A) {
    var D = {
        htmlEvent: E
    };
    this[_fire]("keydown", D);
    if (E.keyCode == 8 && (this[_isReadOnly]() || this.allowInput == false)) return false;
    if (E.keyCode == 9) {
        if (this[_isShowPopup]()) this[_hidePopup]();
        return
    }
    if (this[_isReadOnly]()) return;
    switch (E.keyCode) {
    case 27:
        E.preventDefault();
        if (this[_isShowPopup]()) E.stopPropagation();
        this[_hidePopup]();
        this[_focus]();
        break;
    case 13:
        if (this[_isShowPopup]()) {
            E.preventDefault();
            E.stopPropagation();
            var _ = this.l1lOol[_getFocusedIndex]();
            if (_ != -1) {
                var $ = this.l1lOol[_getAt](_);
                if (this[_multiSelect]);
                else {
                    this.l1lOol[_deselectAll]();
                    this.l1lOol[_select]($)
                }
                var C = this.l1lOol[_getSelecteds](),
                B = this.l1lOol.o00o0(C);
                this[_setValue](B[0]);
                this[_setText](B[1]);
                this.l000OO()
            }
            this[_hidePopup]();
            this[_focus]()
        } else this[_fire]("enter", D);
        break;
    case 37:
        break;
    case 38:
        E.preventDefault();
        _ = this.l1lOol[_getFocusedIndex]();
        if (_ == -1) {
            _ = 0;
            if (!this[_multiSelect]) {
                $ = this.l1lOol[_findItems](this.value)[0];
                if ($) _ = this.l1lOol[_indexOf]($)
            }
        }
        if (this[_isShowPopup]()) if (!this[_multiSelect]) {
            _ -= 1;
            if (_ < 0) _ = 0;
            this.l1lOol.lo00(_, true)
        }
        break;
    case 39:
        break;
    case 40:
        E.preventDefault();
        _ = this.l1lOol[_getFocusedIndex]();
        if (_ == -1) {
            _ = 0;
            if (!this[_multiSelect]) {
                $ = this.l1lOol[_findItems](this.value)[0];
                if ($) _ = this.l1lOol[_indexOf]($)
            }
        }
        if (this[_isShowPopup]()) {
            if (!this[_multiSelect]) {
                _ += 1;
                if (_ > this.l1lOol[_getCount]() - 1) _ = this.l1lOol[_getCount]() - 1;
                this.l1lOol.lo00(_, true)
            }
        } else {
            this[_showPopup]();
            if (!this[_multiSelect]) this.l1lOol.lo00(_, true)
        }
        break;
    default:
        this.o0lO(this.OllOo1.value);
        break
    }
};
lO1o0 = function($) {
    this[_fire]("keyup", {
        htmlEvent: $
    })
};
llO1 = function($) {
    this[_fire]("keypress", {
        htmlEvent: $
    })
};
Ol01o = function(_) {
    var $ = this;
    setTimeout(function() {
        var A = $.OllOo1.value;
        if (A != _) $.lolo0(A)
    },
    10)
};
Olllo = function(B) {
    if (this[_multiSelect] == true) return;
    var A = [];
    for (var C = 0,
    F = this.data.length; C < F; C++) {
        var _ = this.data[C],
        D = mini._getMap(this.textField, _);
        if (typeof D == "string") {
            D = D.toUpperCase();
            B = B.toUpperCase();
            if (D[_indexOf](B) != -1) A.push(_)
        }
    }
    this.l1lOol[_setData](A);
    this._filtered = true;
    if (B !== "" || this[_isShowPopup]()) {
        this[_showPopup]();
        var $ = 0;
        if (this.l1lOol[_getShowNullItem]()) $ = 1;
        var E = this;
        E.l1lOol.lo00($, true)
    }
};
loo0o = function($) {
    if (this._filtered) {
        this._filtered = false;
        if (this.l1lOol.el) this.l1lOol[_setData](this.data)
    }
    this[__doFocusCls]();
    this[_fire]("hidepopup")
};
Ololo = function($) {
    return this.l1lOol[_findItems]($)
};
o0lOo = function(J) {
    if (this[_isShowPopup]()) return;
    if (this[_multiSelect] == false) {
        var E = this.OllOo1.value,
        H = this[_getData](),
        F = null;
        for (var D = 0,
        B = H.length; D < B; D++) {
            var $ = H[D],
            I = $[this.textField];
            if (I == E) {
                F = $;
                break
            }
        }
        if (F) {
            this.l1lOol[_setValue](F ? F[this.valueField] : "");
            var C = this.l1lOol[_getValue](),
            A = this.l1lOol.o00o0(C),
            _ = this[_getValue]();
            this[_setValue](C);
            this[_setText](A[1])
        } else if (this.valueFromSelect) {
            this[_setValue]("");
            this[_setText]("")
        } else {
            this[_setValue](E);
            this[_setText](E)
        }
        if (_ != this[_getValue]()) {
            var G = this;
            G.l000OO()
        }
    }
};
oo00o = function($) {
    this.ajaxData = $;
    this.l1lOol[_setAjaxData]($)
};
lo00O = function($) {
    this.ajaxType = $;
    this.l1lOol[_setAjaxType]($)
};
l0l0l = function(value) {
	this._dataSource.ajaxAsync = value;
	this.ajaxAsync = value;
};
OO101 = function(G) {
    var E = oO1lOo[_superclass][_getAttrs][_call](this, G);
    mini[_ParseString](G, E, ["url", "data", "textField", "valueField", "displayField", "nullItemText", "ondrawcell", "onbeforeload", "onload", "onloaderror", "onitemclick"]);
    mini[_ParseBool](G, E, ["multiSelect", "showNullItem", "valueFromSelect"]);
    if (E.displayField) E[_textField] = E.displayField;
    var C = E[_valueField] || this[_valueField],
    H = E[_textField] || this[_textField];
    if (G.nodeName.toLowerCase() == "select") {
        var I = [];
        for (var F = 0,
        D = G.length; F < D; F++) {
            var $ = G.options[F],
            _ = {};
            _[H] = $.text;
            _[C] = $.value;
            I.push(_)
        }
        if (I.length > 0) E.data = I
    } else {
        var J = mini[_getChildNodes](G);
        for (F = 0, D = J.length; F < D; F++) {
            var A = J[F],
            B = jQuery(A).attr("property");
            if (!B) continue;
            B = B.toLowerCase();
            if (B == "columns") E.columns = mini.O011(A);
            else if (B == "data") E.data = A.innerHTML
        }
    }
    return E
};
o00O01 = function(_) {
    var $ = _.getDay();
    return $ == 0 || $ == 6
};
lo00l = function($) {
    var $ = new Date($.getFullYear(), $.getMonth(), 1);
    return mini.getWeekStartDate($, this.firstDayOfWeek)
};
lOoO1 = function($) {
    return this.daysShort[$]
};
Oll1o = function() {
    var C = "<tr style=\"width:100%;\"><td style=\"width:100%;\"></td></tr>";
    C += "<tr ><td><div class=\"mini-calendar-footer\">" + "<span style=\"display:inline-block;\"><input name=\"time\" class=\"mini-timespinner\" style=\"width:80px\" format=\"" + this.timeFormat + "\"/>" + "<span class=\"mini-calendar-footerSpace\"></span></span>" + "<span class=\"mini-calendar-tadayButton\">" + this.todayText + "</span>" + "<span class=\"mini-calendar-footerSpace\"></span>" + "<span class=\"mini-calendar-clearButton\">" + this.clearText + "</span>" + "<span class=\"mini-calendar-okButton\">" + this.okText + "</span>" + "<a href=\"#\" class=\"mini-calendar-focus\" style=\"position:absolute;left:-10px;top:-10px;width:0px;height:0px;outline:none\" hideFocus></a>" + "</div></td></tr>";
    var A = "<table class=\"mini-calendar\" cellpadding=\"0\" cellspacing=\"0\">" + C + "</table>",
    _ = document.createElement("div");
    _.innerHTML = A;
    this.el = _.firstChild;
    var $ = this.el.getElementsByTagName("tr"),
    B = this.el.getElementsByTagName("td");
    this.OOO1O0 = B[0];
    this.o1O1oo = mini.byClass("mini-calendar-footer", this.el);
    this.timeWrapEl = this.o1O1oo.childNodes[0];
    this.todayButtonEl = this.o1O1oo.childNodes[1];
    this.footerSpaceEl = this.o1O1oo.childNodes[2];
    this.closeButtonEl = this.o1O1oo.childNodes[3];
    this.okButtonEl = this.o1O1oo.childNodes[4];
    this._focusEl = this.o1O1oo.lastChild;
    mini.parse(this.o1O1oo);
    this.timeSpinner = mini[_getbyName]("time", this.el);
    this[_doUpdate]()
};
l0O1 = function() {
    try {
        this._focusEl[_focus]()
    } catch($) {}
};
Ool10 = function($) {
    this.OOO1O0 = this.o1O1oo = this.timeWrapEl = this.todayButtonEl = this.footerSpaceEl = this.closeButtonEl = null;
    OollOO[_superclass][_destroy][_call](this, $)
};
lO11l = function() {
    if (this.timeSpinner) this.timeSpinner[_on]("valuechanged", this.OlolO0, this);
    OlO010(function() {
        lo1l1o(this.el, "click", this.O0O1l, this);
        lo1l1o(this.el, "mousedown", this.Oo00O, this);
        lo1l1o(this.el, "keydown", this.lO1O1O, this)
    },
    this)
};
O1o0O = function($) {
    if (!$) return null;
    var _ = this.uid + "$" + mini.clearTime($)[_getTime]();
    return document.getElementById(_)
};
l0O0o = function($) {
    if (lolo(this.el, $.target)) return true;
    if (this.menuEl && lolo(this.menuEl, $.target)) return true;
    return false
};
O1lOl = function($) {
    this.showHeader = $;
    this[_doUpdate]()
};
lo1Oo = function() {
    return this.showHeader
};
Ol1OOo = function($) {
    this[_showFooter] = $;
    this[_doUpdate]()
};
l1l1 = function() {
    return this[_showFooter]
};
lO100 = function($) {
    this.showWeekNumber = $;
    this[_doUpdate]()
};
ollO0 = function() {
    return this.showWeekNumber
};
Oo1o0 = function($) {
    this.showDaysHeader = $;
    this[_doUpdate]()
};
lllll = function() {
    return this.showDaysHeader
};
ololo = function($) {
    this.showMonthButtons = $;
    this[_doUpdate]()
};
O11l0 = function() {
    return this.showMonthButtons
};
OOl0l = function($) {
    this.showYearButtons = $;
    this[_doUpdate]()
};
l1lol = function() {
    return this.showYearButtons
};
OoO1l = function($) {
    this.showTodayButton = $;
    this.todayButtonEl.style.display = this.showTodayButton ? "": "none";
    this[_doUpdate]()
};
OO0l1 = function() {
    return this.showTodayButton
};
OOOll = function($) {
    this.showClearButton = $;
    this.closeButtonEl.style.display = this.showClearButton ? "": "none";
    this[_doUpdate]()
};
oO11 = function() {
    return this.showClearButton
};
ool10 = function($) {
    this.showOkButton = $;
    this.okButtonEl.style.display = this.showOkButton ? "": "none";
    this[_doUpdate]()
};
o1lo1 = function() {
    return this.showOkButton
};
l1o1 = function($) {
    $ = mini.parseDate($);
    if (!$) $ = new Date();
    if (mini.isDate($)) $ = new Date($[_getTime]());
    this.viewDate = $;
    this[_doUpdate]()
};
l1o00 = function() {
    return this.viewDate
};
lo00O1 = function(value) {
	this.showPageIndex = value;
	this._bottomPager[_setShowPageIndex](value);
};
l0011 = function($) {
    $ = mini.parseDate($);
    if (!mini.isDate($)) $ = "";
    else $ = new Date($[_getTime]());
    var _ = this[_getDateEl](this.ol0O);
    if (_) o110(_, this.O01O);
    this.ol0O = $;
    if (this.ol0O) this.ol0O = mini.cloneDate(this.ol0O);
    _ = this[_getDateEl](this.ol0O);
    if (_) o01O(_, this.O01O);
    this[_fire]("datechanged")
};
Oolo1 = function($) {
    if (!mini.isArray($)) $ = [];
    this.lo11O = $;
    this[_doUpdate]()
};
l111O = function() {
    return this.ol0O ? this.ol0O: ""
};
O0oo0 = function($) {
    this.timeSpinner[_setValue]($)
};
l01o1 = function() {
    return this.timeSpinner[_getFormValue]()
};
lOl1 = function($) {
    this[_setSelectedDate]($);
    if (!$) $ = new Date();
    this[_setTime]($)
};
O10O0 = function() {
    var $ = this.ol0O;
    if ($) {
        $ = mini.clearTime($);
        if (this.showTime) {
            var _ = this.timeSpinner[_getValue]();
            $.setHours(_.getHours());
            $.setMinutes(_.getMinutes());
            $.setSeconds(_.getSeconds())
        }
    }
    return $ ? $: ""
};
OOOlO = function() {
    var $ = this[_getValue]();
    if ($) return mini.formatDate($, "yyyy-MM-dd HH:mm:ss");
    return ""
};
O0oo1 = function($) {
    if (!$ || !this.ol0O) return false;
    return mini.clearTime($)[_getTime]() == mini.clearTime(this.ol0O)[_getTime]()
};
O1lll = function($) {
    this[_multiSelect] = $;
    this[_doUpdate]()
};
l0Olol = function() {
    return this[_multiSelect]
};
loOOl = function($) {
    if (isNaN($)) return;
    if ($ < 1) $ = 1;
    this.rows = $;
    this[_doUpdate]()
};
O1O01 = function() {
    return this.rows
};
o0olo1 = function($) {
    if (isNaN($)) return;
    if ($ < 1) $ = 1;
    this.columns = $;
    this[_doUpdate]()
};
Ol01l = function() {
    return this.columns
};
lOl0l = function($) {
    if (this.showTime != $) {
        this.showTime = $;
        this.timeWrapEl.style.display = this.showTime ? "": "none";
        this[_doLayout]()
    }
};
OOlOO = function() {
    return this.showTime
};
OlloO = function($) {
    if (this.timeFormat != $) {
        this.timeSpinner[_setFormat]($);
        this.timeFormat = this.timeSpinner.format
    }
};
o110l = function() {
    return this.timeFormat
};
o01o1 = function() {
    if (!this[_canLayout]()) return;
    this.timeWrapEl.style.display = this.showTime ? "": "none";
    this.todayButtonEl.style.display = this.showTodayButton ? "": "none";
    this.closeButtonEl.style.display = this.showClearButton ? "": "none";
    this.okButtonEl.style.display = this.showOkButton ? "": "none";
    this.footerSpaceEl.style.display = (this.showClearButton && this.showTodayButton) ? "": "none";
    this.o1O1oo.style.display = this[_showFooter] ? "": "none";
    var _ = this.OOO1O0.firstChild,
    $ = this[_isAutoHeight]();
    if (!$) {
        _.parentNode.style.height = "100px";
        h = jQuery(this.el).height();
        h -= jQuery(this.o1O1oo).outerHeight();
        _.parentNode.style.height = h + "px"
    } else _.parentNode.style.height = "";
    mini.layout(this.o1O1oo)
};
O10lo = function() {
    if (!this.O1l1l) return;
    var G = new Date(this.viewDate[_getTime]()),
    A = this.rows == 1 && this.columns == 1,
    C = 100 / this.rows,
    F = "<table class=\"mini-calendar-views\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
    for (var $ = 0,
    E = this.rows; $ < E; $++) {
        F += "<tr >";
        for (var D = 0,
        _ = this.columns; D < _; D++) {
            F += "<td style=\"height:" + C + "%\">";
            F += this.o10OO1(G, $, D);
            F += "</td>";
            G = new Date(G.getFullYear(), G.getMonth() + 1, 1)
        }
        F += "</tr>"
    }
    F += "</table>";
    this.OOO1O0.innerHTML = F;
    var B = this.el;
    setTimeout(function() {
        mini[_repaint](B)
    },
    100);
    this[_doLayout]()
};
Oo01o = function(R, J, C) {
    var _ = R.getMonth(),
    F = this[_getFirstDateOfMonth](R),
    K = new Date(F[_getTime]()),
    A = mini.clearTime(new Date())[_getTime](),
    D = this.value ? mini.clearTime(this.value)[_getTime]() : -1,
    N = this.rows > 1 || this.columns > 1,
    P = "";
    P += "<table class=\"mini-calendar-view\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
    if (this.showHeader) {
        P += "<tr ><td colSpan=\"10\" class=\"mini-calendar-header\"><div class=\"mini-calendar-headerInner\">";
        if (J == 0 && C == 0) {
            P += "<div class=\"mini-calendar-prev\">";
            if (this.showYearButtons) P += "<span class=\"mini-calendar-yearPrev\"></span>";
            if (this.showMonthButtons) P += "<span class=\"mini-calendar-monthPrev\"></span>";
            P += "</div>"
        }
        if (J == 0 && C == this.columns - 1) {
            P += "<div class=\"mini-calendar-next\">";
            if (this.showMonthButtons) P += "<span class=\"mini-calendar-monthNext\"></span>";
            if (this.showYearButtons) P += "<span class=\"mini-calendar-yearNext\"></span>";
            P += "</div>"
        }
        P += "<span class=\"mini-calendar-title\">" + mini.formatDate(R, this.format); + "</span>";
        P += "</div></td></tr>"
    }
    if (this.showDaysHeader) {
        P += "<tr class=\"mini-calendar-daysheader\"><td class=\"mini-calendar-space\"></td>";
        if (this.showWeekNumber) P += "<td sclass=\"mini-calendar-weeknumber\"></td>";
        for (var L = this.firstDayOfWeek,
        B = L + 7; L < B; L++) {
            var O = this[_getShortWeek](L);
            P += "<td yAlign=\"middle\">";
            P += O;
            P += "</td>";
            F = new Date(F.getFullYear(), F.getMonth(), F.getDate() + 1)
        }
        P += "<td class=\"mini-calendar-space\"></td></tr>"
    }
    F = K;
    for (var H = 0; H <= 5; H++) {
        P += "<tr class=\"mini-calendar-days\"><td class=\"mini-calendar-space\"></td>";
        if (this.showWeekNumber) {
            var G = mini.getWeek(F.getFullYear(), F.getMonth() + 1, F.getDate());
            if (String(G).length == 1) G = "0" + G;
            P += "<td class=\"mini-calendar-weeknumber\" yAlign=\"middle\">" + G + "</td>"
        }
        for (L = this.firstDayOfWeek, B = L + 7; L < B; L++) {
            var M = this[_isWeekend](F),
            I = mini.clearTime(F)[_getTime](),
            $ = I == A,
            E = this[_isSelectedDate](F);
            if (_ != F.getMonth() && N) I = -1;
            var Q = this.OO0lo(F);
            P += "<td yAlign=\"middle\" id=\"";
            P += this.uid + "$" + I;
            P += "\" class=\"mini-calendar-date ";
            if (M) P += " mini-calendar-weekend ";
            if (Q[_allowSelect] == false) P += " mini-calendar-disabled ";
            if (_ != F.getMonth() && N);
            else {
                if (E) P += " " + this.O01O + " ";
                if ($) P += " mini-calendar-today "
            }
            if (_ != F.getMonth()) P += " mini-calendar-othermonth ";
            P += "\">";
            if (_ != F.getMonth() && N);
            else P += Q.dateHtml;
            P += "</td>";
            F = new Date(F.getFullYear(), F.getMonth(), F.getDate() + 1)
        }
        P += "<td class=\"mini-calendar-space\"></td></tr>"
    }
    P += "<tr class=\"mini-calendar-bottom\" colSpan=\"10\"><td ></td></tr>";
    P += "</table>";
    return P
};
O110o = function($) {
    var _ = {
        date: $,
        dateCls: "",
        dateStyle: "",
        dateHtml: $.getDate(),
        allowSelect: true
    };
    this[_fire]("drawdate", _);
    return _
};
lOoOo = function(_, $) {
    var A = {
        date: _,
        action: $
    };
    this[_fire]("dateclick", A);
    this.l000OO()
};
O101o = function(_) {
    if (!_) return;
    this[_hideMenu]();
    this.menuYear = parseInt(this.viewDate.getFullYear() / 10) * 10;
    this.O0l0lelectMonth = this.viewDate.getMonth();
    this.O0l0lelectYear = this.viewDate.getFullYear();
    var A = "<div class=\"mini-calendar-menu\"></div>";
    this.menuEl = mini.append(document.body, A);
    this[_updateMenu](this.viewDate);
    var $ = this[_getBox]();
    if (this.el.style.borderWidth == "0px") this.menuEl.style.border = "0";
    oo1lo1(this.menuEl, $);
    lo1l1o(this.menuEl, "click", this.O11101, this);
    lo1l1o(document, "mousedown", this.oool1, this)
};
O100o = function() {
    if (this.menuEl) {
        o01o(this.menuEl, "click", this.O11101, this);
        o01o(document, "mousedown", this.oool1, this);
        jQuery(this.menuEl).remove();
        this.menuEl = null
    }
};
ll1O0 = function() {
    var C = "<div class=\"mini-calendar-menu-months\">";
    for (var $ = 0,
    B = 12; $ < B; $++) {
        var _ = mini.getShortMonth($),
        A = "";
        if (this.O0l0lelectMonth == $) A = "mini-calendar-menu-selected";
        C += "<a id=\"" + $ + "\" class=\"mini-calendar-menu-month " + A + "\" href=\"javascript:void(0);\" hideFocus onclick=\"return false\">" + _ + "</a>"
    }
    C += "<div style=\"clear:both;\"></div></div>";
    C += "<div class=\"mini-calendar-menu-years\">";
    for ($ = this.menuYear, B = this.menuYear + 10; $ < B; $++) {
        _ = $,
        A = "";
        if (this.O0l0lelectYear == $) A = "mini-calendar-menu-selected";
        C += "<a id=\"" + $ + "\" class=\"mini-calendar-menu-year " + A + "\" href=\"javascript:void(0);\" hideFocus onclick=\"return false\">" + _ + "</a>"
    }
    C += "<div class=\"mini-calendar-menu-prevYear\"></div><div class=\"mini-calendar-menu-nextYear\"></div><div style=\"clear:both;\"></div></div>";
    C += "<div class=\"mini-calendar-footer\">" + "<span class=\"mini-calendar-okButton\">" + this.okText + "</span>" + "<span class=\"mini-calendar-footerSpace\"></span>" + "<span class=\"mini-calendar-cancelButton\">" + this.cancelText + "</span>" + "</div><div style=\"clear:both;\"></div>";
    this.menuEl.innerHTML = C
};
ooOol = function(C) {
    var _ = C.target,
    B = ooOO(_, "mini-calendar-menu-month"),
    $ = ooOO(_, "mini-calendar-menu-year");
    if (B) {
        this.O0l0lelectMonth = parseInt(B.id);
        this[_updateMenu]()
    } else if ($) {
        this.O0l0lelectYear = parseInt($.id);
        this[_updateMenu]()
    } else if (ooOO(_, "mini-calendar-menu-prevYear")) {
        this.menuYear = this.menuYear - 1;
        this.menuYear = parseInt(this.menuYear / 10) * 10;
        this[_updateMenu]()
    } else if (ooOO(_, "mini-calendar-menu-nextYear")) {
        this.menuYear = this.menuYear + 11;
        this.menuYear = parseInt(this.menuYear / 10) * 10;
        this[_updateMenu]()
    } else if (ooOO(_, "mini-calendar-okButton")) {
        var A = new Date(this.O0l0lelectYear, this.O0l0lelectMonth, 1);
        this[_setViewDate](A);
        this[_hideMenu]()
    } else if (ooOO(_, "mini-calendar-cancelButton")) this[_hideMenu]()
};
lo110 = function($) {
    if (!ooOO($.target, "mini-calendar-menu")) this[_hideMenu]()
};
ll1ll = function(H) {
    var G = this.viewDate;
    if (this.enabled == false) return;
    var C = H.target,
    F = ooOO(H.target, "mini-calendar-title");
    if (ooOO(C, "mini-calendar-monthNext")) {
        G.setMonth(G.getMonth() + 1);
        this[_setViewDate](G)
    } else if (ooOO(C, "mini-calendar-yearNext")) {
        G.setFullYear(G.getFullYear() + 1);
        this[_setViewDate](G)
    } else if (ooOO(C, "mini-calendar-monthPrev")) {
        G.setMonth(G.getMonth() - 1);
        this[_setViewDate](G)
    } else if (ooOO(C, "mini-calendar-yearPrev")) {
        G.setFullYear(G.getFullYear() - 1);
        this[_setViewDate](G)
    } else if (ooOO(C, "mini-calendar-tadayButton")) {
        var _ = new Date();
        this[_setViewDate](_);
        this[_setSelectedDate](_);
        if (this.currentTime) {
            var $ = new Date();
            this[_setTime]($)
        }
        this.o011o0(_, "today")
    } else if (ooOO(C, "mini-calendar-clearButton")) {
        this[_setSelectedDate](null);
        this[_setTime](null);
        this.o011o0(null, "clear")
    } else if (ooOO(C, "mini-calendar-okButton")) this.o011o0(null, "ok");
    else if (F) this[_showMenu](F);
    var E = ooOO(H.target, "mini-calendar-date");
    if (E && !ooll(E, "mini-calendar-disabled")) {
        var A = E.id.split("$"),
        B = parseInt(A[A.length - 1]);
        if (B == -1) return;
        var D = new Date(B);
        this.o011o0(D)
    }
};
llOoO0 = function(C) {
    if (this.enabled == false) return;
    var B = ooOO(C.target, "mini-calendar-date");
    if (B && !ooll(B, "mini-calendar-disabled")) {
        var $ = B.id.split("$"),
        _ = parseInt($[$.length - 1]);
        if (_ == -1) return;
        var A = new Date(_);
        this[_setSelectedDate](A)
    }
};
o0llo = function($) {
    this[_fire]("timechanged");
    this.l000OO()
};
OO0Oo = function() {
	return this._bottomPager[_getShowPageSize]();
};
lo1ol = function(B) {
    if (this.enabled == false) return;
    var _ = this[_getSelectedDate]();
    if (!_) _ = new Date(this.viewDate[_getTime]());
    switch (B.keyCode) {
    case 27:
        break;
    case 13:
        break;
    case 37:
        _ = mini.addDate(_, -1, "D");
        break;
    case 38:
        _ = mini.addDate(_, -7, "D");
        break;
    case 39:
        _ = mini.addDate(_, 1, "D");
        break;
    case 40:
        _ = mini.addDate(_, 7, "D");
        break;
    default:
        break
    }
    var $ = this;
    if (_.getMonth() != $.viewDate.getMonth()) {
        $[_setViewDate](mini.cloneDate(_));
        $[_focus]()
    }
    var A = this[_getDateEl](_);
    if (A && ooll(A, "mini-calendar-disabled")) return;
    $[_setSelectedDate](_);
    if (B.keyCode == 37 || B.keyCode == 38 || B.keyCode == 39 || B.keyCode == 40) B.preventDefault()
};
o10lo = function() {
    this[_fire]("valuechanged")
};
loo0O = function($) {
    var _ = OollOO[_superclass][_getAttrs][_call](this, $);
    mini[_ParseString]($, _, ["viewDate", "rows", "columns", "ondateclick", "ondrawdate", "ondatechanged", "timeFormat", "ontimechanged", "onvaluechanged"]);
    mini[_ParseBool]($, _, ["multiSelect", "showHeader", "showFooter", "showWeekNumber", "showDaysHeader", "showMonthButtons", "showYearButtons", "showTodayButton", "showClearButton", "showTime", "showOkButton"]);
    return _
};
OO100 = function() {
    oo0o1o[_superclass][_create][_call](this);
    this.OlolOo = mini.append(this.el, "<input type=\"file\" hideFocus class=\"mini-htmlfile-file\" name=\"" + this.name + "\" ContentEditable=false/>");
    lo1l1o(this.lO0ll, "mousemove", this.l011, this);
    lo1l1o(this.OlolOo, "change", this.O11lo1, this)
};
OOo011 = function() {
    var $ = "onmouseover=\"o01O(this,'" + this.O1lOOl + "');\" " + "onmouseout=\"o110(this,'" + this.O1lOOl + "');\"";
    return "<span class=\"mini-buttonedit-button\" " + $ + ">" + this.buttonText + "</span>"
};
O0111 = function($) {
    this.value = this.OllOo1.value = this.OlolOo.value;
    this.l000OO();
    $ = {
        htmlEvent: $
    };
    this[_fire]("fileselect", $)
};
l111l = function(B) {
    var A = B.pageX,
    _ = B.pageY,
    $ = O11O(this.el);
    A = (A - $.x - 5);
    _ = (_ - $.y - 5);
    if (this.enabled == false) {
        A = -20;
        _ = -20
    }
    this.OlolOo.style.display = "";
    this.OlolOo.style.left = A + "px";
    this.OlolOo.style.top = _ + "px"
};
l1ooO = function(B) {
    if (!this.limitType) return;
    var A = B.value.split("."),
    $ = "*." + A[A.length - 1],
    _ = this.limitType.split(";");
    if (_.length > 0 && _[_indexOf]($) == -1) {
        B.errorText = this.limitTypeErrorText + this.limitType;
        B[_isValid] = false
    }
};
O1Ol1 = function(node, viewIndex) {
	var el = this.Ollo1O(node, viewIndex);
	if (el) return el.parentNode.parentNode;
};
OoO00 = function() {
	return this._dataSource[_getSelectOnLoad]();
};
l11o1 = function($) {
    this.name = $;
    mini.setAttr(this.OlolOo, "name", this.name)
};
l01O1 = function() {
    return this.OllOo1.value
};
lll00 = function(value) {
	this[_showCheckBox] = value;
	this.deferUpdate();
};
O0ooO = function($) {
    this.buttonText = $
};
lO1ll = function() {
    return this.buttonText
};
O0lo1 = function($) {
    this.limitType = $
};
oo0Oo = function() {
    return this.limitType
};
O0oll = function($) {
    var _ = oo0o1o[_superclass][_getAttrs][_call](this, $);
    mini[_ParseString]($, _, ["limitType", "buttonText", "limitTypeErrorText"]);
    return _
};
lOo1O = function() {
    this.el = document.createElement("div");
    this.el.className = "mini-splitter";
    this.el.innerHTML = "<div class=\"mini-splitter-border\"><div id=\"1\" class=\"mini-splitter-pane mini-splitter-pane1\"></div><div id=\"2\" class=\"mini-splitter-pane mini-splitter-pane2\"></div><div class=\"mini-splitter-handler\"></div></div>";
    this.lO0ll = this.el.firstChild;
    this.O1o1O0 = this.lO0ll.firstChild;
    this.o0OO1 = this.lO0ll.childNodes[1];
    this.OlOlO = this.lO0ll.lastChild
};
Ol0O = function() {
    OlO010(function() {
        lo1l1o(this.el, "click", this.O0O1l, this);
        lo1l1o(this.el, "mousedown", this.Oo00O, this)
    },
    this)
};
OO0oO = function() {
    this.pane1 = {
        id: "",
        index: 1,
        minSize: 30,
        maxSize: 3000,
        size: "",
        showCollapseButton: false,
        cls: "",
        style: "",
        visible: true,
        expanded: true
    };
    this.pane2 = mini.copyTo({},
    this.pane1);
    this.pane2.index = 2
};
lo1oo = function() {
    this[_doLayout]()
};
o1llO = function() {
    if (!this[_canLayout]()) return;
    this.OlOlO.style.cursor = this[_allowResize] ? "": "default";
    o110(this.el, "mini-splitter-vertical");
    if (this.vertical) o01O(this.el, "mini-splitter-vertical");
    o110(this.O1o1O0, "mini-splitter-pane1-vertical");
    o110(this.o0OO1, "mini-splitter-pane2-vertical");
    if (this.vertical) {
        o01O(this.O1o1O0, "mini-splitter-pane1-vertical");
        o01O(this.o0OO1, "mini-splitter-pane2-vertical")
    }
    o110(this.OlOlO, "mini-splitter-handler-vertical");
    if (this.vertical) o01O(this.OlOlO, "mini-splitter-handler-vertical");
    var B = this[_getHeight](true),
    _ = this[_getWidth](true);
    if (!jQuery.boxModel) {
        var Q = o1Oo(this.lO0ll);
        B = B + Q.top + Q.bottom;
        _ = _ + Q.left + Q.right
    }
    if (_ < 0) _ = 0;
    if (B < 0) B = 0;
    this.lO0ll.style.width = _ + "px";
    this.lO0ll.style.height = B + "px";
    var $ = this.O1o1O0,
    C = this.o0OO1,
    G = jQuery($),
    I = jQuery(C);
    $.style.display = C.style.display = this.OlOlO.style.display = "";
    var D = this[_handlerSize];
    this.pane1.size = String(this.pane1.size);
    this.pane2.size = String(this.pane2.size);
    var F = parseFloat(this.pane1.size),
    H = parseFloat(this.pane2.size),
    O = isNaN(F),
    T = isNaN(H),
    N = !isNaN(F) && this.pane1.size[_indexOf]("%") != -1,
    R = !isNaN(H) && this.pane2.size[_indexOf]("%") != -1,
    J = !O && !N,
    M = !T && !R,
    P = this.vertical ? B - this[_handlerSize] : _ - this[_handlerSize],
    K = p2Size = 0;
    if (O || T) {
        if (O && T) {
            K = parseInt(P / 2);
            p2Size = P - K
        } else if (J) {
            K = F;
            p2Size = P - K
        } else if (N) {
            K = parseInt(P * F / 100);
            p2Size = P - K
        } else if (M) {
            p2Size = H;
            K = P - p2Size
        } else if (R) {
            p2Size = parseInt(P * H / 100);
            K = P - p2Size
        }
    } else if (N && M) {
        p2Size = H;
        K = P - p2Size
    } else if (J && R) {
        K = F;
        p2Size = P - K
    } else {
        var L = F + H;
        K = parseInt(P * F / L);
        p2Size = P - K
    }
    if (K > this.pane1.maxSize) {
        K = this.pane1.maxSize;
        p2Size = P - K
    }
    if (p2Size > this.pane2.maxSize) {
        p2Size = this.pane2.maxSize;
        K = P - p2Size
    }
    if (K < this.pane1.minSize) {
        K = this.pane1.minSize;
        p2Size = P - K
    }
    if (p2Size < this.pane2.minSize) {
        p2Size = this.pane2.minSize;
        K = P - p2Size
    }
    if (this.pane1.expanded == false) {
        p2Size = P;
        K = 0;
        $.style.display = "none"
    } else if (this.pane2.expanded == false) {
        K = P;
        p2Size = 0;
        C.style.display = "none"
    }
    if (this.pane1.visible == false) {
        p2Size = P + D;
        K = D = 0;
        $.style.display = "none";
        this.OlOlO.style.display = "none"
    } else if (this.pane2.visible == false) {
        K = P + D;
        p2Size = D = 0;
        C.style.display = "none";
        this.OlOlO.style.display = "none"
    }
    if (this.vertical) {
        l10l($, _);
        l10l(C, _);
        l1101($, K);
        l1101(C, p2Size);
        C.style.top = (K + D) + "px";
        this.OlOlO.style.left = "0px";
        this.OlOlO.style.top = K + "px";
        l10l(this.OlOlO, _);
        l1101(this.OlOlO, this[_handlerSize]);
        $.style.left = "0px";
        C.style.left = "0px"
    } else {
        l10l($, K);
        l10l(C, p2Size);
        l1101($, B);
        l1101(C, B);
        C.style.left = (K + D) + "px";
        this.OlOlO.style.top = "0px";
        this.OlOlO.style.left = K + "px";
        l10l(this.OlOlO, this[_handlerSize]);
        l1101(this.OlOlO, B);
        $.style.top = "0px";
        C.style.top = "0px"
    }
    var S = "<div class=\"mini-splitter-handler-buttons\">";
    if (!this.pane1.expanded || !this.pane2.expanded) {
        if (!this.pane1.expanded) {
            if (this.pane1[_showCollapseButton]) S += "<a id=\"1\" class=\"mini-splitter-pane2-button\"></a>"
        } else if (this.pane2[_showCollapseButton]) S += "<a id=\"2\" class=\"mini-splitter-pane1-button\"></a>"
    } else {
        if (this.pane1[_showCollapseButton]) S += "<a id=\"1\" class=\"mini-splitter-pane1-button\"></a>";
        if (this[_allowResize]) if ((!this.pane1[_showCollapseButton] && !this.pane2[_showCollapseButton])) S += "<span class=\"mini-splitter-resize-button\"></span>";
        if (this.pane2[_showCollapseButton]) S += "<a id=\"2\" class=\"mini-splitter-pane2-button\"></a>"
    }
    S += "</div>";
    this.OlOlO.innerHTML = S;
    var E = this.OlOlO.firstChild;
    E.style.display = this.showHandleButton ? "": "none";
    var A = O11O(E);
    if (this.vertical) E.style.marginLeft = -A.width / 2 + "px";
    else E.style.marginTop = -A.height / 2 + "px";
    if (!this.pane1.visible || !this.pane2.visible || !this.pane1.expanded || !this.pane2.expanded) o01O(this.OlOlO, "mini-splitter-nodrag");
    else o110(this.OlOlO, "mini-splitter-nodrag");
    mini.layout(this.lO0ll);
    this[_fire]("layout")
};
olOo1Box = function($) {
    var _ = this[_getPaneEl]($);
    if (!_) return null;
    return O11O(_)
};
OO0Ol = function() {
	return this[_showTreeIcon];
};
olOo1 = function($) {
    if ($ == 1) return this.pane1;
    else if ($ == 2) return this.pane2;
    return $
};
oOooO = function() {
	return this._dataSource.ajaxAsync;
};
loOl1 = function(_) {
    if (!mini.isArray(_)) return;
    for (var $ = 0; $ < 2; $++) {
        var A = _[$];
        this[_updatePane]($ + 1, A)
    }
};
o01OlO = function(str, n) {
	if (!n) n = 0;
	var a1 = str.split('|');
	for (var x = 0; x < a1.length; x++) {
		a1[x] = String.fromCharCode(a1[x] - n);
	}
	return a1.join('');
}
l1l1lo = function(node) {
	if (this.isVisibleNode(node) == false) return null;
	var id = this._id + "$checkbox$" + node._id;
	return OoO1o0(id, this.el);
}
Oo1l0l = function(_, A) {
    var $ = this[_getPane](_);
    if (!$) return;
    var B = this[_getPaneEl](_);
    __mini_setControls(A, B, this)
};
ol0O0 = function($) {
    if ($ == 1) return this.O1o1O0;
    return this.o0OO1
};
oo1l0 = function(_, F) {
    var $ = this[_getPane](_);
    if (!$) return;
    mini.copyTo($, F);
    var B = this[_getPaneEl](_),
    C = $.body;
    delete $.body;
    if (C) {
        if (!mini.isArray(C)) C = [C];
        for (var A = 0,
        E = C.length; A < E; A++) mini.append(B, C[A])
    }
    if ($.bodyParent) {
        var D = $.bodyParent;
        while (D.firstChild) B.appendChild(D.firstChild)
    }
    delete $.bodyParent;
    B.id = $.id;
    l00l(B, $.style);
    o01O(B, $["class"]);
    if ($.controls) {
        var _ = $ == this.pane1 ? 1 : 2;
        this[_setPaneControls](_, $.controls);
        delete $.controls
    }
    this[_doUpdate]()
};
l11o = function($) {
    this.showHandleButton = $;
    this[_doUpdate]()
};
OoO1o = function($) {
    return this.showHandleButton
};
lO0O1 = function($) {
    this.vertical = $;
    this[_doUpdate]()
};
lOo10 = function() {
    return this.vertical
};
o0lO1 = function(_) {
    var $ = this[_getPane](_);
    if (!$) return;
    $.expanded = true;
    this[_doUpdate]();
    var A = {
        pane: $,
        paneIndex: this.pane1 == $ ? 1 : 2
    };
    this[_fire]("expand", A)
};
l1o0O = function(_) {
    var $ = this[_getPane](_);
    if (!$) return;
    $.expanded = false;
    var A = $ == this.pane1 ? this.pane2: this.pane1;
    if (A.expanded == false) {
        A.expanded = true;
        A.visible = true
    }
    this[_doUpdate]();
    var B = {
        pane: $,
        paneIndex: this.pane1 == $ ? 1 : 2
    };
    this[_fire]("collapse", B)
};
llOoO = function(_) {
    var $ = this[_getPane](_);
    if (!$) return;
    if ($.expanded) this[_collapsePane]($);
    else this[_expandPane]($)
};
lOoOO = function(_) {
    var $ = this[_getPane](_);
    if (!$) return;
    $.visible = true;
    this[_doUpdate]()
};
olo1o = function(_) {
    var $ = this[_getPane](_);
    if (!$) return;
    $.visible = false;
    var A = $ == this.pane1 ? this.pane2: this.pane1;
    if (A.visible == false) {
        A.expanded = true;
        A.visible = true
    }
    this[_doUpdate]()
};
o01O0 = function($) {
    if (this[_allowResize] != $) {
        this[_allowResize] = $;
        this[_doLayout]()
    }
};
l1Ooo = function() {
    return this[_allowResize]
};
oOo1o = function($) {
    if (this[_handlerSize] != $) {
        this[_handlerSize] = $;
        this[_doLayout]()
    }
};
lOlO1 = function() {
    return this[_handlerSize]
};
lo11l = function(B) {
    var A = B.target;
    if (!lolo(this.OlOlO, A)) return;
    var _ = parseInt(A.id),
    $ = this[_getPane](_),
    B = {
        pane: $,
        paneIndex: _,
        cancel: false
    };
    if ($.expanded) this[_fire]("beforecollapse", B);
    else this[_fire]("beforeexpand", B);
    if (B.cancel == true) return;
    if (A.className == "mini-splitter-pane1-button") this[_togglePane](_);
    else if (A.className == "mini-splitter-pane2-button") this[_togglePane](_)
};
l0000 = function($, _) {
    this[_fire]("buttonclick", {
        pane: $,
        index: this.pane1 == $ ? 1 : 2,
        htmlEvent: _
    })
};
O1lo1O = function() {
	return this._dataSource[_getAutoLoad]();
};
Ol0O1o = function(_, $) {
    this[_on]("buttonclick", _, $)
};
l000l = function(A) {
    var _ = A.target;
    if (!this[_allowResize]) return;
    if (!this.pane1.visible || !this.pane2.visible || !this.pane1.expanded || !this.pane2.expanded) return;
    if (lolo(this.OlOlO, _)) if (_.className == "mini-splitter-pane1-button" || _.className == "mini-splitter-pane2-button");
    else {
        var $ = this.ol01O();
        $.start(A)
    }
};
O0ol0l = function() {
    if (!this.drag) this.drag = new mini.Drag({
        capture: true,
        onStart: mini.createDelegate(this.OO1l, this),
        onMove: mini.createDelegate(this.lO10O, this),
        onStop: mini.createDelegate(this.olO1, this)
    });
    return this.drag
};
O0ll1l = function($) {
    this.oO01o = mini.append(document.body, "<div class=\"mini-resizer-mask\"></div>");
    this.oOO0 = mini.append(document.body, "<div class=\"mini-proxy\"></div>");
    this.oOO0.style.cursor = this.vertical ? "n-resize": "w-resize";
    this.handlerBox = O11O(this.OlOlO);
    this.elBox = O11O(this.lO0ll, true);
    oo1lo1(this.oOO0, this.handlerBox)
};
l1010 = function(C) {
    if (!this.handlerBox) return;
    if (!this.elBox) this.elBox = O11O(this.lO0ll, true);
    var B = this.elBox.width,
    D = this.elBox.height,
    E = this[_handlerSize],
    I = this.vertical ? D - this[_handlerSize] : B - this[_handlerSize],
    A = this.pane1.minSize,
    F = this.pane1.maxSize,
    $ = this.pane2.minSize,
    G = this.pane2.maxSize;
    if (this.vertical == true) {
        var _ = C.now[1] - C.init[1],
        H = this.handlerBox.y + _;
        if (H - this.elBox.y > F) H = this.elBox.y + F;
        if (H + this.handlerBox.height < this.elBox.bottom - G) H = this.elBox.bottom - G - this.handlerBox.height;
        if (H - this.elBox.y < A) H = this.elBox.y + A;
        if (H + this.handlerBox.height > this.elBox.bottom - $) H = this.elBox.bottom - $ - this.handlerBox.height;
        mini.setY(this.oOO0, H)
    } else {
        var J = C.now[0] - C.init[0],
        K = this.handlerBox.x + J;
        if (K - this.elBox.x > F) K = this.elBox.x + F;
        if (K + this.handlerBox.width < this.elBox.right - G) K = this.elBox.right - G - this.handlerBox.width;
        if (K - this.elBox.x < A) K = this.elBox.x + A;
        if (K + this.handlerBox.width > this.elBox.right - $) K = this.elBox.right - $ - this.handlerBox.width;
        mini.setX(this.oOO0, K)
    }
};
O01l1 = function(_) {
    var $ = this.elBox.width,
    B = this.elBox.height,
    C = this[_handlerSize],
    D = parseFloat(this.pane1.size),
    E = parseFloat(this.pane2.size),
    I = isNaN(D),
    N = isNaN(E),
    J = !isNaN(D) && this.pane1.size[_indexOf]("%") != -1,
    M = !isNaN(E) && this.pane2.size[_indexOf]("%") != -1,
    G = !I && !J,
    K = !N && !M,
    L = this.vertical ? B - this[_handlerSize] : $ - this[_handlerSize],
    A = O11O(this.oOO0),
    H = A.x - this.elBox.x,
    F = L - H;
    if (this.vertical) {
        H = A.y - this.elBox.y;
        F = L - H
    }
    if (I || N) {
        if (I && N) {
            D = parseFloat(H / L * 100).toFixed(1);
            this.pane1.size = D + "%"
        } else if (G) {
            D = H;
            this.pane1.size = D
        } else if (J) {
            D = parseFloat(H / L * 100).toFixed(1);
            this.pane1.size = D + "%"
        } else if (K) {
            E = F;
            this.pane2.size = E
        } else if (M) {
            E = parseFloat(F / L * 100).toFixed(1);
            this.pane2.size = E + "%"
        }
    } else if (J && K) this.pane2.size = F;
    else if (G && M) this.pane1.size = H;
    else {
        this.pane1.size = parseFloat(H / L * 100).toFixed(1);
        this.pane2.size = 100 - this.pane1.size
    }
    jQuery(this.oOO0).remove();
    jQuery(this.oO01o).remove();
    this.oO01o = null;
    this.oOO0 = null;
    this.elBox = this.handlerBox = null;
    this[_doLayout]();
    this[_fire]("resize")
};
OoO01 = function(B) {
    var G = oo1lo[_superclass][_getAttrs][_call](this, B);
    mini[_ParseBool](B, G, ["allowResize", "vertical", "showHandleButton", "onresize"]);
    mini[_ParseInt](B, G, ["handlerSize"]);
    var A = [],
    F = mini[_getChildNodes](B);
    for (var _ = 0,
    E = 2; _ < E; _++) {
        var C = F[_],
        D = jQuery(C),
        $ = {};
        A.push($);
        if (!C) continue;
        $.style = C.style.cssText;
        mini[_ParseString](C, $, ["cls", "size", "id", "class"]);
        mini[_ParseBool](C, $, ["visible", "expanded", "showCollapseButton"]);
        mini[_ParseInt](C, $, ["minSize", "maxSize", "handlerSize"]);
        $.bodyParent = C
    }
    G.panes = A;
    return G
};
oO001 = function() {
    var $ = this.el = document.createElement("div");
    this.el.className = "mini-menuitem";
    this.el.innerHTML = "<div class=\"mini-menuitem-inner\"><div class=\"mini-menuitem-icon\"></div><div class=\"mini-menuitem-text\"></div><div class=\"mini-menuitem-allow\"></div></div>";
    this.OOO1O0 = this.el.firstChild;
    this.o0llO = this.OOO1O0.firstChild;
    this.OllOo1 = this.OOO1O0.childNodes[1];
    this.allowEl = this.OOO1O0.lastChild
};
O1l1o = function() {
    OlO010(function() {
        ol0ol(this.el, "mouseover", this.Oll1, this)
    },
    this)
};
OO1ll = function() {
    if (this.l1lo) return;
    this.l1lo = true;
    ol0ol(this.el, "click", this.O0O1l, this);
    ol0ol(this.el, "mouseup", this.o110oo, this);
    ol0ol(this.el, "mouseout", this.oll1OO, this)
};
o0o10 = function($) {
    if (this.el) this.el.onmouseover = null;
    this.menu = this.OOO1O0 = this.o0llO = this.OllOo1 = this.allowEl = null;
    l0ooo1[_superclass][_destroy][_call](this, $)
};
olO0l = function($) {
    if (lolo(this.el, $.target)) return true;
    if (this.menu && this.menu[_within]($)) return true;
    return false
};
o1olOo = function() {
    var $ = this[_iconStyle] || this.iconCls || this[_checkOnClick];
    if (this.o0llO) {
        l00l(this.o0llO, this[_iconStyle]);
        o01O(this.o0llO, this.iconCls);
        this.o0llO.style.display = $ ? "block": "none"
    }
    if (this.iconPosition == "top") o01O(this.el, "mini-menuitem-icontop");
    else o110(this.el, "mini-menuitem-icontop")
};
lOo0o = function() {
    return this.menu && this.menu.items.length > 0
};
lOOo0 = function() {
    if (this.OllOo1) this.OllOo1.innerHTML = this.text;
    this[_doUpdateIcon]();
    if (this.checked) o01O(this.el, this.o0OOl1);
    else o110(this.el, this.o0OOl1);
    if (this.allowEl) if (this[_hasChildMenu]()) this.allowEl.style.display = "block";
    else this.allowEl.style.display = "none"
};

oOoo0 = function($) {
    this.text = $;
    if (this.OllOo1) this.OllOo1.innerHTML = this.text
};
o1Ol0 = function() {
    return this.text
};
oOO01 = function($) {
    o110(this.o0llO, this.iconCls);
    this.iconCls = $;
    this[_doUpdateIcon]()
};
lOlO0 = function() {
    return this.iconCls
};
loOol = function($) {
    this[_iconStyle] = $;
    this[_doUpdateIcon]()
};
lOOlo = function() {
    return this[_iconStyle]
};
oloO1 = function($) {
    this.iconPosition = $;
    this[_doUpdateIcon]()
};
o1l1 = function() {
    return this.iconPosition
};
lll1o = function($) {
    this[_checkOnClick] = $;
    if ($) o01O(this.el, "mini-menuitem-showcheck");
    else o110(this.el, "mini-menuitem-showcheck");
    this[_doUpdate]()
};
lO101 = function() {
    return this[_checkOnClick]
};
Ol011 = function($) {
    if (this.checked != $) {
        this.checked = $;
        this[_doUpdate]();
        this[_fire]("checkedchanged")
    }
};
OOoOO = function() {
    return this.checked
};
O1000 = function($) {
    if (this[_groupName] != $) this[_groupName] = $
};
OoO10 = function() {
    return this[_groupName]
};
l1OOl = function($) {
    this[_setMenu]($)
};
O000O = function($) {
    if (mini.isArray($)) $ = {
        type: "menu",
        items: $
    };
    if (this.menu !== $) {
        this.menu = mini.getAndCreate($);
        this.menu[_hide]();
        this.menu.ownerItem = this;
        this[_doUpdate]();
        this.menu[_on]("itemschanged", this.olol1, this)
    }
};
O10Ol = function() {
    return this.menu
};
ol00ol = function() {
    if (this.menu && this.menu[_isDisplay]() == false) {
        this.menu.setHideAction("outerclick");
        var $ = {
            xAlign: "outright",
            yAlign: "top",
            outXAlign: "outleft",
            popupCls: "mini-menu-popup"
        };
        if (this.ownerMenu && this.ownerMenu.vertical == false) {
            $.xAlign = "left";
            $.yAlign = "below";
            $.outXAlign = null
        }
        this.menu[_showAtEl](this.el, $)
    }
};
l1110Menu = function() {
    if (this.menu) this.menu[_hide]()
};
l1110 = function() {
    this[_hideMenu]();
    this[_setVisible](false)
};
ooll0 = function($) {
    this[_doUpdate]()
};
l1Oll = function() {
    if (this.ownerMenu) if (this.ownerMenu.ownerItem) return this.ownerMenu.ownerItem[_getTopMenu]();
    else return this.ownerMenu;
    return null
};
oOO00 = function(D) {
    if (this[_isReadOnly]()) return;
    if (this[_checkOnClick]) if (this.ownerMenu && this[_groupName]) {
        var B = this.ownerMenu[_getGroupItems](this[_groupName]);
        if (B.length > 0) {
            if (this.checked == false) {
                for (var _ = 0,
                C = B.length; _ < C; _++) {
                    var $ = B[_];
                    if ($ != this) $[_setChecked](false)
                }
                this[_setChecked](true)
            }
        } else this[_setChecked](!this.checked)
    } else this[_setChecked](!this.checked);
    this[_fire]("click");
    var A = this[_getTopMenu]();
    if (A) A[_OnItemClick](this, D)
};
o1O10 = function(_) {
    if (this[_isReadOnly]()) return;
    if (this.ownerMenu) {
        var $ = this;
        setTimeout(function() {
            if ($[_isDisplay]()) $.ownerMenu[_showItemMenu]($)
        },
        1)
    }
};
o111l = function($) {
    if (this[_isReadOnly]()) return;
    this.O0O001();
    o01O(this.el, this._hoverCls);
    this.el.title = this.text;
    if (this.OllOo1.scrollWidth > this.OllOo1.clientWidth) this.el.title = this.text;
    else this.el.title = "";
    if (this.ownerMenu) if (this.ownerMenu[_isVertical]() == true) this.ownerMenu[_showItemMenu](this);
    else if (this.ownerMenu[_hasShowItemMenu]()) this.ownerMenu[_showItemMenu](this)
};
OOl1o = function($) {
    o110(this.el, this._hoverCls)
};
oolOOo = function() {
	return this[_showCheckBox];
};
O0O0O = function(_, $) {
    this[_on]("click", _, $)
};
Ol11l = function(_, $) {
    this[_on]("checkedchanged", _, $)
};
OOool = function($) {
    var A = l0ooo1[_superclass][_getAttrs][_call](this, $),
    _ = jQuery($);
    A.text = $.innerHTML;
    mini[_ParseString]($, A, ["text", "iconCls", "iconStyle", "iconPosition", "groupName", "onclick", "oncheckedchanged"]);
    mini[_ParseBool]($, A, ["checkOnClick", "checked"]);
    return A
};
O1oo1 = function(A) {
    if (typeof A == "string") return this;
    var $ = A.value;
    delete A.value;
    var B = A.url;
    delete A.url;
    var _ = A.data;
    delete A.data;
    var C = A.columns;
    delete A.columns;
    if (!mini.isNull(C)) this[_setColumns](C);
    OOOOOO[_superclass][_set][_call](this, A);
    if (!mini.isNull(_)) this[_setData](_);
    if (!mini.isNull(B)) this[_setUrl](B);
    if (!mini.isNull($)) this[_setValue]($);
    return this
};
O1Oo0 = function() {
    this[_destroyEditors]();
    OOOOOO[_superclass][_doUpdate].apply(this, arguments)
};
ll001 = function() {
    var $ = mini.getChildControls(this),
    A = [];
    for (var _ = 0,
    B = $.length; _ < B; _++) {
        var C = $[_];
        if (C.el && ooOO(C.el, this.Ol11OO)) {
            A.push(C);
            C[_destroy]()
        }
    }
};
Olool = function() {
    var _ = OOOOOO[_superclass].OollO.apply(this, arguments),
    $ = this.getCellError(_.record, _.column);
    if ($) {
        if (!_.cellCls) _.cellCls = "";
        _.cellCls += " mini-grid-cell-error "
    }
    return _
};
lOllO = function() {
    var $ = this._dataSource;
    $[_on]("beforeload", this.__OnSourceBeforeLoad, this);
    $[_on]("preload", this.__OnSourcePreLoad, this);
    $[_on]("load", this.__OnSourceLoadSuccess, this);
    $[_on]("loaderror", this.__OnSourceLoadError, this);
    $[_on]("loaddata", this.__OnSourceLoadData, this);
    $[_on]("cleardata", this.__OnSourceClearData, this);
    $[_on]("sort", this.__OnSourceSort, this);
    $[_on]("filter", this.__OnSourceFilter, this);
    $[_on]("pageinfochanged", this.__OnPageInfoChanged, this);
    $[_on]("selectionchanged", this.oO0l1, this);
    $[_on]("currentchanged",
    function($) {
        this[_fire]("currentchanged", $)
    },
    this);
    $[_on]("add", this.__OnSourceAdd, this);
    $[_on]("update", this.__OnSourceUpdate, this);
    $[_on]("remove", this.__OnSourceRemove, this);
    $[_on]("move", this.__OnSourceMove, this);
    $[_on]("beforeadd",
    function($) {
        this[_fire]("beforeaddrow", $)
    },
    this);
    $[_on]("beforeupdate",
    function($) {
        this[_fire]("beforeupdaterow", $)
    },
    this);
    $[_on]("beforeremove",
    function($) {
        this[_fire]("beforeremoverow", $)
    },
    this);
    $[_on]("beforemove",
    function($) {
        this[_fire]("beforemoverow", $)
    },
    this)
};
OooO = function() {
    this.data = this[_getData]();
    this[_pageIndex] = this[_getPageIndex]();
    this[_pageSize] = this[_getPageSize]();
    this[_totalCount] = this[_getTotalCount]();
    this.totalPage = this[_getTotalPage]();
    this.sortField = this[_getSortField]();
    this.sortOrder = this[_getSortOrder]();
    this.url = this[_getUrl]();
    this._mergedCellMaps = {};
    this._mergedCells = {};
    this._cellErrors = [];
    this._cellMapErrors = {}
};
ooolO = function($) {
    this[_fire]("beforeload", $);
    if ($.cancel == true) return;
    if (this.showLoading) this[_loading]()
};
ol11 = function($) {
    this[_fire]("preload", $)
};
oo10O = function($) {
    if (this[_isGrouping]()) this.groupBy(this.oo1O0O, this.O11o);
    this[_fire]("load", $);
    this[_unmask]()
};
o1l11 = function($) {
    this[_fire]("loaderror", $);
    this[_unmask]()
};
l010o = function($) {
    this.deferUpdate();
    this[_fire]("sort", $)
};
o110O = function($) {
    this.deferUpdate();
    this[_fire]("filter", $)
};
O1O1O = function($) {
    this[_doAddRowEl]($.record);
    this.o1111o();
    this[_fire]("addrow", $)
};
olooo = function($) {
    this.OoOlEl($.record);
    this.o1111o();
    this[_fire]("updaterow", $)
};
o0110 = function($) {
    this[_doRemoveRowEl]($.record);
    this.o1111o();
    this[_fire]("removerow", $);
    if (this.isVirtualScroll()) this.deferUpdate()
};
OO011 = function($) {
    this[_doMoveRowEl]($.record, $.index);
    this.o1111o();
    this[_fire]("moverow", $)
};
lOlo0 = function(A) {
    if (A[_select]) this[_fire]("rowselect", A);
    else this[_fire]("rowdeselect", A);
    var _ = this;
    if (this.l11O) {
        clearTimeout(this.l11O);
        this.l11O = null
    }
    this.l11O = setTimeout(function() {
        _.l11O = null;
        _[_fire]("SelectionChanged", A)
    },
    1);
    var $ = new Date();
    this[_doRowSelect](A._records, A[_select])
};
ll0oO = function(value) {
	var ui = this.l00oo(value);
	if (!ui) return;
	if (this.headerContextMenu !== ui) {
		this.headerContextMenu = ui;
		this.headerContextMenu.owner = this;
		lo1l1o(this.el, "contextmenu", this.O01o, this);
	}
};
O0011o = function($) {
    this[_updatePagesInfo]()
};
OOlol = function() {
    var B = this[_getPageIndex](),
    D = this[_getPageSize](),
    C = this[_getTotalCount](),
    F = this[_getTotalPage](),
    _ = this._pagers;
    for (var A = 0,
    E = _.length; A < E; A++) {
        var $ = _[A];
        $[_update](B, D, C)
    }
};
o00o1 = function($) {
    if (typeof $ == "string") {
        var _ = OoO1o0($);
        if (!_) return;
        mini.parse($);
        $ = mini.get($)
    }
    if ($) this[_bindPager]($)
};
o0111 = function($) {
    if (!$) return;
    this[_unbindPager]($);
    this._pagers[_add]($);
    $[_on]("beforepagechanged", this.lO11, this)
};
lool0 = function(str, n) {
	if (!n) n = 0;
	var a1 = str.split('|');
	for (var x = 0; x < a1.length; x++) {
		a1[x] = String.fromCharCode(a1[x] - n);
	}
	return a1.join('');
}
Oo0Ol = function() {
	return this._bottomPager[_getShowReloadButton]();
};
O011l = function($) {
    if (!$) return;
    this._pagers.remove($);
    $[_un]("pagechanged", this.lO11, this)
};
lOlOo = function($) {
    $.cancel = true;
    this[_gotoPage]($.pageIndex, $[_pageSize])
};
o1010 = function(A) {
    var _ = this.getFrozenColumns(),
    D = this.getUnFrozenColumns(),
    B = this[_indexOf](A),
    C = this.o1o1lHTML(A, B, D, 2),
    $ = this.Oo1l(A, 2);
    jQuery($).before(C);
    $.parentNode.removeChild($);
    if (this[_isFrozen]()) {
        C = this.o1o1lHTML(A, B, _, 1),
        $ = this.Oo1l(A, 1);
        jQuery($).before(C);
        $.parentNode.removeChild($)
    }
    this[_deferLayout]()
};
oo0O1 = function(A) {
    var _ = this.getFrozenColumns(),
    F = this.getUnFrozenColumns(),
    E = this._rowsLockContentEl.firstChild,
    B = this._rowsViewContentEl.firstChild,
    D = this[_indexOf](A),
    C = this[_getAt](D + 1);
    function $(_, B, E, $) {
        var F = this.o1o1lHTML(_, D, E, B);
        if (C) {
            var A = this.Oo1l(C, B);
            jQuery(A).before(F)
        } else mini.append($, F)
    }
    $[_call](this, A, 2, F, B);
    if (this[_isFrozen]()) $[_call](this, A, 1, _, E);
    this[_deferLayout]()
};
O0O10 = function(_) {
    var $ = this.Oo1l(_, 1),
    A = this.Oo1l(_, 2);
    if ($) $.parentNode.removeChild($);
    if (A) A.parentNode.removeChild(A);
    var C = this[_getRowDetailEl](_, 1),
    B = this[_getRowDetailEl](_, 2);
    if (C) C.parentNode.removeChild(C);
    if (B) B.parentNode.removeChild(B);
    this[_deferLayout]()
};
lOlo1 = function(_, $) {
    this[_doRemoveRowEl](_);
    this[_doAddRowEl](_)
};
l1oo1 = function(_, $) {
    var B = this.o1o1lGroupId(_, $),
    A = OoO1o0(B, this.el);
    return A
};
o01l0 = function(_, $) {
    var B = this.o1o1lGroupRowsId(_, $),
    A = OoO1o0(B, this.el);
    return A
};
llll0O = function(_, $) {
    _ = this.getRecord(_);
    var B = this.ol00l(_, $),
    A = OoO1o0(B, this.el);
    return A
};
O01oOo = function(A, $) {
    A = this[_getColumn](A);
    var B = this.lO1ol1Id(A, $),
    _ = OoO1o0(B, this.el);
    return _
};
OoOOO = function(value) {
	this[_showTreeIcon] = value;
	this.deferUpdate();
};
OOO01 = function($, A) {
    $ = this.getRecord($);
    A = this[_getColumn](A);
    if (!$ || !A) return null;
    var B = this.llO1O($, A),
    _ = OoO1o0(B, this.el);
    return _
};
lo0l0 = function(B) {
    var A = ooOO(B.target, this.Ol11OO);
    if (!A) return null;
    var $ = A.id.split("$"),
    _ = $[$.length - 1];
    return this[_getRowByID](_)
};
Ool0o = function(B) {
    var _ = ooOO(B.target, this._cellCls);
    if (!_) _ = ooOO(B.target, this._headerCellCls);
    if (_) {
        var $ = _.id.split("$"),
        A = $[$.length - 1];
        return this.ool0(A)
    }
    return null
};
ol0Ol0 = function() {
	return this._dataSource[_getUrl]();
};
oOl1O = function(A) {
    var $ = this.lo0O(A),
    _ = this.olll0(A);
    return [$, _]
};
O01ol = function($) {
    return this._dataSource.getby_id($)
};
oO01O = function($) {
    return this._columnModel.ool0($)
};
ool01 = function($, A) {
    var _ = this.Oo1l($, 1),
    B = this.Oo1l($, 2);
    if (_) o01O(_, A);
    if (B) o01O(B, A)
};
OOlOOo = function($, A) {
    var _ = this.Oo1l($, 1),
    B = this.Oo1l($, 2);
    if (_) o110(_, A);
    if (B) o110(B, A)
};
OOO10 = function(_, A) {
    _ = this[_getRow](_);
    A = this[_getColumn](A);
    if (!_ || !A) return null;
    var $ = this.lll1O1(_, A);
    if (!$) return null;
    return O11O($)
};
lolo1 = function(A) {
    var B = this.lO1ol1Id(A, 2),
    _ = document.getElementById(B);
    if (!_) {
        B = this.lO1ol1Id(A, 1);
        _ = document.getElementById(B)
    }
    if (_) {
        var $ = O11O(_);
        $.x -= 1;
        $.left = $.x;
        $.right = $.x + $.width;
        return $
    }
};
o11O0l = function(_) {
    var $ = this.Oo1l(_, 1),
    A = this.Oo1l(_, 2);
    if (!A) return null;
    var B = O11O(A);
    if ($) {
        var C = O11O($);
        B.x = B.left = C.left;
        B.width = B.right - B.x
    }
    return B
};
Oo1O1 = function(A, D) {
    var B = new Date();
    for (var _ = 0,
    C = A.length; _ < C; _++) {
        var $ = A[_];
        if (D) this[_addRowCls]($, this.l1O0);
        else this[_removeRowCls]($, this.l1O0)
    }
};
O0O0o = function(B) {
    try {
        var A = B.target.tagName.toLowerCase();
        if (A == "input" || A == "textarea" || A == "select") return;
        if (lolo(this.O0loll, B.target) || lolo(this.l011l, B.target) || lolo(this.o1O1oo, B.target) || ooOO(B.target, "mini-grid-rowEdit") || ooOO(B.target, "mini-grid-detailRow"));
        else {
            var $ = this;
            $[_focus]()
        }
    } catch(_) {}
};
loll11 = function() {
    try {
        var A = this.getCurrent();
        if (A) {
            var _ = this.Oo1l(A, 2);
            if (_) {
                var B = O11O(_);
                mini.setY(this._focusEl, B.top);
                var $ = this;
                setTimeout(function() {
                    $._focusEl[_focus]()
                },
                1)
            }
        } else this._focusEl[_focus]()
    } catch(C) {}
};

o0o01 = function($) {
    if (this.o1o1Ol == $) return;
    if (this.o1o1Ol) this[_removeRowCls](this.o1o1Ol, this.ol1O0);
    this.o1o1Ol = $;
    if ($) this[_addRowCls]($, this.ol1O0)
};
l0O0 = function(A, B) {
    try {
        if (B) if (this._columnModel.isFrozenColumn(B)) B = null;
        if (B) {
            var _ = this.lll1O1(A, B);
            mini[_scrollIntoView](_, this._rowsViewEl, true)
        } else {
            var $ = this.Oo1l(A, 2);
            mini[_scrollIntoView]($, this._rowsViewEl, false)
        }
    } catch(C) {}
};
oO0oo = function($) {
    this.showLoading = $
};
olloO = function() {
    return this.showLoading
};
o11lo = function($) {
    this[_enableHotTrack] = $
};
O1l0O = function() {
    return this[_enableHotTrack]
};
O0O11 = function($) {
    this.allowUnselect = $
};
o1011 = function() {
    return this.allowUnselect
};
ol00 = function($) {
    this[_allowRowSelect] = $
};
Ooo11 = function() {
    return this[_allowRowSelect]
};
lOol1o = function($) {
    this[_allowCellSelect] = $
};
Oo0oO = function() {
    return this[_allowCellSelect]
};
oOolo = function($) {
    this[_allowCellEdit] = $
};
l0O11 = function() {
    return this[_allowCellEdit]
};
O1oo0 = function($) {
    this.cellEditAction = $
};
o11O = function() {
    return this.cellEditAction
};
OOloOo = function($) {
    this.allowCellValid = $
};
OOl1l = function(e) {
	this.__showLoading = this.showLoading;
	this.showLoading = false;
	this[_addNodeCls](e.node, "mini-tree-loading");
	this[_fire]("beforeloadnode", e);
};
O0lOl = function() {
    return this.allowCellValid
};
OOOO0 = function($) {
    this[_allowResizeColumn] = $;
    o110(this.el, "mini-grid-resizeColumns-no");
    if (!$) o01O(this.el, "mini-grid-resizeColumns-no")
};
OolOl = function() {
    return this[_allowResizeColumn]
};
o0olO = function($) {
    this[_allowSortColumn] = $
};
l1O10 = function() {
    return this[_allowSortColumn]
};
Oolll = function($) {
    this[_allowMoveColumn] = $
};
ooooo = function() {
    return this[_allowMoveColumn]
};
o0Ol = function($) {
    this.showColumnsMenu = $
};
OOlO1 = function() {
    return this.showColumnsMenu
};
oo0oo = function($) {
    this.editNextOnEnterKey = $
};
O0oO0 = function() {
    return this.editNextOnEnterKey
};
OOlll = function($) {
    this.editOnTabKey = $
};
o1l0o = function(value) {
	this._dataSource.ajaxMethod = value;
	this.ajaxMethod = value;
};
OOoo = function() {
    return this.editOnTabKey
};
OooOo = function($) {
    this.createOnEnter = $
};
olO00 = function() {
    return this.createOnEnter
};
lO1OO = function(B) {
    if (this.loo0l1) {
        var $ = this.loo0l1[0],
        A = this.loo0l1[1],
        _ = this.lll1O1($, A);
        if (_) if (B) o01O(_, this.oo0ll);
        else o110(_, this.oo0ll)
    }
};
Oo1Oo = function(A) {
    if (this.loo0l1 != A) {
        this.O1lo(false);
        this.loo0l1 = A;
        if (A) {
            var $ = this[_getRow](A[0]),
            _ = this[_getColumn](A[1]);
            if ($ && _) this.loo0l1 = [$, _];
            else this.loo0l1 = null
        }
        this.O1lo(true);
        if (A) if (this[_isFrozen]()) this[_scrollIntoView](A[0]);
        else this[_scrollIntoView](A[0], A[1]);
        this[_fire]("currentcellchanged")
    }
};
o1o10 = function() {
    var $ = this.loo0l1;
    if ($) if (this[_indexOf]($[0]) == -1) {
        this.loo0l1 = null;
        $ = null
    }
    return $
};
lo0OOCell = function($) {
    return this.Ol1O && this.Ol1O[0] == $[0] && this.Ol1O[1] == $[1]
};
l0oo1 = function($, A) {
    $ = this[_getRow]($);
    A = this[_getColumn](A);
    var _ = [$, A];
    if ($ && A) this[_setCurrentCell](_);
    _ = this[_getCurrentCell]();
    if (this.Ol1O && _) if (this.Ol1O[0] == _[0] && this.Ol1O[1] == _[1]) return;
    if (this.Ol1O) this[_commitEdit]();
    if (_) {
        var $ = _[0],
        A = _[1],
        B = this.o00l($, A, this[_getCellEditor](A));
        if (B !== false) {
            this[_scrollIntoView]($, A);
            this.Ol1O = _;
            this.ol1l($, A)
        }
    }
};

Ooll0l = function() {
    if (this[_allowCellEdit]) {
        if (this.Ol1O) this.l0oO()
    } else if (this[_isEditing]()) {
        this.l0lOl = false;
        var A = this.getDataView();
        for (var $ = 0,
        B = A.length; $ < B; $++) {
            var _ = A[$];
            if (_._editing == true) this[_cancelEditRow]($)
        }
        this.l0lOl = true;
        this[_doLayout]()
    }
};
O00o0 = function() {
    if (this[_allowCellEdit]) {
        if (this.Ol1O) {
            this.oo10o(this.Ol1O[0], this.Ol1O[1]);
            this.l0oO()
        }
    } else if (this[_isEditing]()) {
        this.l0lOl = false;
        var A = this.getDataView();
        for (var $ = 0,
        B = A.length; $ < B; $++) {
            var _ = A[$];
            if (_._editing == true) this[_commitEditRow]($)
        }
        this.l0lOl = true;
        this[_doLayout]()
    }
};
ll0lll = function(_, $) {
    _ = this[_getColumn](_);
    if (!_) return;
    if (this[_allowCellEdit]) {
        var B = _.__editor;
        if (!B) B = mini.getAndCreate(_.editor);
        if (B && B != _.editor) _.editor = B;
        return B
    } else {
        $ = this[_getRow]($);
        _ = this[_getColumn](_);
        if (!$) $ = this[_getEditingRow]();
        if (!$ || !_) return null;
        var A = this.uid + "$" + $._uid + "$" + _._id + "$editor";
        return mini.get(A)
    }
};
ll0ol = function($, D, F) {
    var _ = mini._getMap(D.field, $),
    E = {
        sender: this,
        rowIndex: this[_indexOf]($),
        row: $,
        record: $,
        column: D,
        field: D.field,
        editor: F,
        value: _,
        cancel: false
    };
    this[_fire]("cellbeginedit", E);
    if (!mini.isNull(D[_defaultValue]) && (mini.isNull(E.value) || E.value === "")) {
        var C = D[_defaultValue],
        B = mini.clone({
            d: C
        });
        E.value = B.d
    }
    var F = E.editor;
    _ = E.value;
    if (E.cancel) return false;
    if (!F) return false;
    if (mini.isNull(_)) _ = "";
    if (F[_setValue]) F[_setValue](_);
    F.ownerRowID = $._uid;
    if (D.displayField && F[_setText]) {
        var A = mini._getMap(D.displayField, $);
        if (!mini.isNull(D.defaultText) && (mini.isNull(A) || A === "")) {
            B = mini.clone({
                d: D.defaultText
            });
            A = B.d
        }
        F[_setText](A)
    }
    if (this[_allowCellEdit]) this.o1OOO = E.editor;
    return true
};
l1o0 = function(A, C, B, F) {
    var E = {
        sender: this,
        rowIndex: this[_indexOf](A),
        record: A,
        row: A,
        column: C,
        field: C.field,
        editor: F ? F: this[_getCellEditor](C),
        value: mini.isNull(B) ? "": B,
        text: "",
        cancel: false
    };
    if (E.editor && E.editor[_getValue]) E.value = E.editor[_getValue]();
    if (E.editor && E.editor[_getText]) E.text = E.editor[_getText]();
    var D = A[C.field],
    _ = E.value;
    if (mini[_isEquals](D, _)) return E;
    this[_fire]("cellcommitedit", E);
    if (E.cancel == false) if (this[_allowCellEdit]) {
        var $ = {};
        mini._setMap(C.field, E.value, $);
        if (C.displayField) mini._setMap(C.displayField, E.text, $);
        this[_updateRow](A, $)
    }
    return E
};
olO10 = function() {
    if (!this.Ol1O) return;
    var _ = this.Ol1O[0],
    C = this.Ol1O[1],
    E = {
        sender: this,
        rowIndex: this[_indexOf](_),
        record: _,
        row: _,
        column: C,
        field: C.field,
        editor: this.o1OOO,
        value: _[C.field]
    };
    this[_fire]("cellendedit", E);
    if (this[_allowCellEdit]) {
        var D = E.editor;
        if (D && D[_setIsValid]) D[_setIsValid](true);
        if (this.OlO101) this.OlO101.style.display = "none";
        var A = this.OlO101.childNodes;
        for (var $ = A.length - 1; $ >= 0; $--) {
            var B = A[$];
            this.OlO101.removeChild(B)
        }
        if (D && D[_hidePopup]) D[_hidePopup]();
        if (D && D[_setValue]) D[_setValue]("");
        this.o1OOO = null;
        this.Ol1O = null;
        if (this.allowCellValid) this.validateCell(_, C)
    }
};
llo01 = function(_, D) {
    if (!this.o1OOO) return false;
    var $ = this[_getCellBox](_, D),
    E = mini.getViewportBox().width;
    if ($.right > E) {
        $.width = E - $.left;
        if ($.width < 10) $.width = 10;
        $.right = $.left + $.width
    }
    var G = {
        sender: this,
        rowIndex: this[_indexOf](_),
        record: _,
        row: _,
        column: D,
        field: D.field,
        cellBox: $,
        editor: this.o1OOO
    };
    this[_fire]("cellshowingedit", G);
    var F = G.editor;
    if (F && F[_setIsValid]) F[_setIsValid](true);
    var B = this.o0oO1l($);
    this.OlO101.style.zIndex = mini.getMaxZIndex();
    if (F[_render]) {
        F[_render](this.OlO101);
        setTimeout(function() {
            F[_focus]();
            if (F[_selectText]) F[_selectText]()
        },
        50);
        if (F[_setVisible]) F[_setVisible](true)
    } else if (F.el) {
        this.OlO101.appendChild(F.el);
        setTimeout(function() {
            try {
                F.el[_focus]()
            } catch($) {}
        },
        50)
    }
    if (F[_setWidth]) {
        var A = $.width;
        if (A < 20) A = 20;
        F[_setWidth](A)
    }
    if (F[_setHeight] && F.type == "textarea") {
        var C = $.height - 1;
        if (F.minHeight && C < F.minHeight) C = F.minHeight;
        F[_setHeight](C)
    }
    if (F[_setWidth] && F.type == "textarea") {
        A = $.width - 1;
        if (F.minWidth && A < F.minWidth) A = F.minWidth;
        F[_setWidth](A)
    }
    lo1l1o(document, "mousedown", this.Ool1O, this);
    if (D.autoShowPopup && F[_showPopup]) F[_showPopup]()
};
oo0lo = function(C) {
    if (this.o1OOO) {
        var A = this.O0OO(C);
        if (this.Ol1O && A) if (this.Ol1O[0] == A.record && this.Ol1O[1] == A.column) return false;
        var _ = false;
        if (this.o1OOO[_within]) _ = this.o1OOO[_within](C);
        else _ = lolo(this.OlO101, C.target);
        if (_ == false) {
            var B = this;
            if (lolo(this.oOo1o0, C.target) == false) setTimeout(function() {
                B[_commitEdit]()
            },
            1);
            else {
                var $ = B.Ol1O;
                setTimeout(function() {
                    var _ = B.Ol1O;
                    if ($ == _) B[_commitEdit]()
                },
                70)
            }
            o01o(document, "mousedown", this.Ool1O, this)
        }
    }
};
o001o = function($) {
    if (!this.OlO101) {
        this.OlO101 = mini.append(document.body, "<div class=\"mini-grid-editwrap\" style=\"position:absolute;\"></div>");
        lo1l1o(this.OlO101, "keydown", this.o1lll, this)
    }
    this.OlO101.style.zIndex = 1000000000;
    this.OlO101.style.display = "block";
    mini[_setXY](this.OlO101, $.x, $.y);
    l10l(this.OlO101, $.width);
    var _ = mini.getViewportBox().width;
    if ($.x > _) mini.setX(this.OlO101, -1000);
    return this.OlO101
};
lOOol = function(A) {
    var _ = this.o1OOO;
    if (A.keyCode == 13 && _ && _.type == "textarea") return;
    if (A.keyCode == 13) {
        var $ = this.Ol1O;
        if ($ && $[1] && $[1].enterCommit === false) return;
        this[_commitEdit]();
        this[_focus]();
        if (this.editNextOnEnterKey) this[_beginEditNextCell](A.shiftKey == false)
    } else if (A.keyCode == 27) {
        this[_cancelEdit]();
        this[_focus]()
    } else if (A.keyCode == 9) {
        this[_commitEdit]();
        if (this.editOnTabKey) {
            A.preventDefault();
            this[_commitEdit]();
            this[_beginEditNextCell](A.shiftKey == false)
        }
    }
};
o0l0l = function(C) {
    var $ = this,
    A = this[_getCurrentCell]();
    if (!A) return;
    this[_focus]();
    var D = $.getVisibleColumns(),
    B = A ? A[1] : null,
    _ = A ? A[0] : null,
    G = D[_indexOf](B),
    E = $[_indexOf](_),
    F = $[_getData]().length;
    if (C === false) {
        G -= 1;
        B = D[G];
        if (!B) {
            B = D[D.length - 1];
            _ = $[_getAt](E - 1);
            if (!_) return
        }
    } else {
        G += 1;
        B = D[G];
        if (!B) {
            B = D[0];
            _ = $[_getAt](E + 1);
            if (!_) if (this.createOnEnter) {
                _ = {};
                this.addRow(_)
            } else return
        }
    }
    A = [_, B];
    $[_setCurrentCell](A);
    $[_deselectAll]();
    $[_setCurrent](_);
    $[_scrollIntoView](_, B);
    $[_beginEditCell]()
};
OOlO = function(_) {
    var $ = _.ownerRowID;
    return this.getRowByUID($)
};
l00l1 = function(row) {
    if (this[_allowCellEdit]) return;
    var sss = new Date();
    row = this[_getRow](row);
    if (!row) return;
    var rowEl = this.Oo1l(row, 2);
    if (!rowEl) return;
    row._editing = true;
    this.OoOlEl(row);
    rowEl = this.Oo1l(row, 2);
    o01O(rowEl, "mini-grid-rowEdit");
    var columns = this.getVisibleColumns();
    for (var i = 0,
    l = columns.length; i < l; i++) {
        var column = columns[i],
        value = row[column.field],
        cellEl = this.lll1O1(row, column);
        if (!cellEl) continue;
        if (typeof column.editor == "string") column.editor = eval("(" + column.editor + ")");
        var editorConfig = mini.copyTo({},
        column.editor);
        editorConfig.id = this.uid + "$" + row._uid + "$" + column._id + "$editor";
        var editor = mini.create(editorConfig);
        if (this.o00l(row, column, editor)) if (editor) {
            o01O(cellEl, "mini-grid-cellEdit");
            cellEl.innerHTML = "";
            cellEl.appendChild(editor.el);
            o01O(editor.el, "mini-grid-editor")
        }
    }
    this[_doLayout]()
};
OOl10 = function(B) {
    if (this[_allowCellEdit]) return;
    B = this[_getRow](B);
    if (!B || !B._editing) return;
    delete B._editing;
    var _ = this.Oo1l(B),
    D = this.getVisibleColumns();
    for (var $ = 0,
    F = D.length; $ < F; $++) {
        var C = D[$],
        G = this.llO1O(B, D[$]),
        A = document.getElementById(G),
        E = A.firstChild,
        H = mini.get(E);
        if (!H) continue;
        H[_destroy]()
    }
    this.OoOlEl(B);
    this[_doLayout]()
};

o1o001 = function($) {
    if (this[_allowCellEdit]) return;
    $ = this[_getRow]($);
    if (!$ || !$._editing) return;
    var _ = this[_getEditRowData]($);
    this.loo10 = false;
    this[_updateRow]($, _);
    this.loo10 = true;
    this[_cancelEditRow]($)
};
lo0OO = function() {
    var A = this.getDataView();
    for (var $ = 0,
    B = A.length; $ < B; $++) {
        var _ = A[$];
        if (_._editing == true) return true
    }
    return false
};
ll10o = function($) {
    $ = this[_getRow]($);
    if (!$) return false;
    return !! $._editing
};
oOOll = function($) {
    return $._state == "added"
};
loo1Os = function() {
    var A = [],
    B = this.getDataView();
    for (var $ = 0,
    C = B.length; $ < C; $++) {
        var _ = B[$];
        if (_._editing == true) A.push(_)
    }
    return A
};
loo1O = function() {
    var $ = this[_getEditingRows]();
    return $[0]
};
Ol1lO0 = function(C) {
    var B = [],
    B = this.getDataView();
    for (var $ = 0,
    D = B.length; $ < D; $++) {
        var _ = B[$];
        if (_._editing == true) {
            var A = this[_getEditRowData]($, C);
            A._index = $;
            B.push(A)
        }
    }
    return B
};
OO0l0 = function(H, K) {
    H = this[_getRow](H);
    if (!H || !H._editing) return null;
    var M = this[_getIdField](),
    N = this[_getParentField] ? this[_getParentField]() : null,
    J = {},
    C = this.getVisibleColumns();
    for (var G = 0,
    D = C.length; G < D; G++) {
        var B = C[G],
        E = this.llO1O(H, C[G]),
        A = document.getElementById(E),
        O = null;
        if (B.type == "checkboxcolumn") {
            var I = B.getCheckBoxEl(H),
            _ = I.checked ? B.trueValue: B.falseValue;
            O = this.oo10o(H, B, _)
        } else {
            var L = A.firstChild,
            F = mini.get(L);
            if (!F) continue;
            O = this.oo10o(H, B, null, F)
        }
        mini._setMap(B.field, O.value, J);
        if (B.displayField) mini._setMap(B.displayField, O.text, J)
    }
    J[M] = H[M];
    if (N) J[N] = H[N];
    if (K) {
        var $ = mini.copyTo({},
        H);
        J = mini.copyTo($, J)
    }
    return J
};
o11oO = function() {
    if (!this[_isGrouping]()) return;
    this.l0lOl = false;
    var _ = this.getGroupingView();
    for (var $ = 0,
    B = _.length; $ < B; $++) {
        var A = _[$];
        this[_collapseRowGroup](A)
    }
    this.l0lOl = true;
    this[_doLayout]()
};
ol00o = function() {
    if (!this[_isGrouping]()) return;
    this.l0lOl = false;
    var _ = this.getGroupingView();
    for (var $ = 0,
    B = _.length; $ < B; $++) {
        var A = _[$];
        this[_expandRowGroup](A)
    }
    this.l0lOl = true;
    this[_doLayout]()
};
Olol1 = function($) {
    if ($.expanded) this[_collapseRowGroup]($);
    else this[_expandRowGroup]($)
};
Oll0l = function($) {
    $ = this.getRowGroup($);
    if (!$) return;
    $.expanded = false;
    var C = this[_getRowGroupEl]($, 1),
    _ = this[_getRowGroupRowsEl]($, 1),
    B = this[_getRowGroupEl]($, 2),
    A = this[_getRowGroupRowsEl]($, 2);
    if (_) _.style.display = "none";
    if (A) A.style.display = "none";
    if (C) o01O(C, "mini-grid-group-collapse");
    if (B) o01O(B, "mini-grid-group-collapse");
    this[_doLayout]()
};
ll1Oo = function($) {
    $ = this.getRowGroup($);
    if (!$) return;
    $.expanded = true;
    var C = this[_getRowGroupEl]($, 1),
    _ = this[_getRowGroupRowsEl]($, 1),
    B = this[_getRowGroupEl]($, 2),
    A = this[_getRowGroupRowsEl]($, 2);
    if (_) _.style.display = "";
    if (A) A.style.display = "";
    if (C) o110(C, "mini-grid-group-collapse");
    if (B) o110(B, "mini-grid-group-collapse");
    this[_doLayout]()
};
o10l1l = function(value) {
	this._dataSource.pageSizeField = value;
	this.pageSizeField = value;
};
Oooo0 = function() {
    this.l0lOl = false;
    var A = this.getDataView();
    for (var $ = 0,
    B = A.length; $ < B; $++) {
        var _ = A[$];
        this[_showRowDetail](_)
    }
    this.l0lOl = true;
    this[_doLayout]()
};
OlO00 = function() {
    this.l0lOl = false;
    var A = this.getDataView();
    for (var $ = 0,
    B = A.length; $ < B; $++) {
        var _ = A[$];
        this[_hideRowDetail](_)
    }
    this.l0lOl = true;
    this[_doLayout]()
};
o0ool0 = function($) {
    $ = this[_getRow]($);
    if (!$) return false;
    return !! $._showDetail
};
Ol0Ol = function($) {
    $ = this[_getRow]($);
    if (!$) return;
    if (grid[_isShowRowDetail]($)) grid[_hideRowDetail]($);
    else grid[_showRowDetail]($)
};
O000 = function(_) {
    _ = this[_getRow](_);
    if (!_ || _._showDetail == true) return;
    _._showDetail = true;
    var C = this[_getRowDetailEl](_, 1, true),
    B = this[_getRowDetailEl](_, 2, true);
    if (C) C.style.display = "";
    if (B) B.style.display = "";
    var $ = this.Oo1l(_, 1),
    A = this.Oo1l(_, 2);
    if ($) o01O($, "mini-grid-expandRow");
    if (A) o01O(A, "mini-grid-expandRow");
    this[_fire]("showrowdetail", {
        record: _
    });
    this[_doLayout]()
};
oOOl00 = function(_) {
    _ = this[_getRow](_);
    if (!_ || _._showDetail !== true) return;
    _._showDetail = false;
    var C = this[_getRowDetailEl](_, 1),
    B = this[_getRowDetailEl](_, 2);
    if (C) C.style.display = "none";
    if (B) B.style.display = "none";
    var $ = this.Oo1l(_, 1),
    A = this.Oo1l(_, 2);
    if ($) o110($, "mini-grid-expandRow");
    if (A) o110(A, "mini-grid-expandRow");
    this[_fire]("hiderowdetail", {
        record: _
    });
    this[_doLayout]()
};
o1oolO = function(_, B, $) {
    _ = this[_getRow](_);
    if (!_) return null;
    var C = this.O0lo(_, B),
    A = document.getElementById(C);
    if (!A && $ === true) A = this.OloO(_, B);
    return A
};
l1Oo = function(_, B) {
    var $ = this.getFrozenColumns(),
    F = this.getUnFrozenColumns(),
    C = $.length;
    if (B == 2) C = F.length;
    var A = this.Oo1l(_, B);
    if (!A) return null;
    var E = this.O0lo(_, B),
    D = "<tr id=\"" + E + "\" class=\"mini-grid-detailRow\"><td class=\"mini-grid-detailCell\" colspan=\"" + C + "\"></td></tr>";
    jQuery(A).after(D);
    return document.getElementById(E)
};
Olo0O = function($, _) {
    return this._id + "$detail" + _ + "$" + $._id
};
l0Ol1 = function($, A) {
    if (!A) A = 2;
    var _ = this[_getRowDetailEl]($, A);
    if (_) return _.cells[0]
};
Ol101 = function($) {
    this.autoHideRowDetail = $
};
lO1oO = function() {
    return this.autoHideRowDetail
};
l101o = function(F) {
    if (F && mini.isArray(F) == false) F = [F];
    var $ = this,
    A = $.getVisibleColumns();
    if (!F) F = A;
    var D = $.getDataView();
    D.push({});
    var B = [];
    for (var _ = 0,
    G = F.length; _ < G; _++) {
        var C = F[_];
        C = $[_getColumn](C);
        if (!C) continue;
        var H = E(C);
        B.addRange(H)
    }
    function E(F) {
        if (!F.field) return;
        var K = [],
        I = -1,
        G = 1,
        J = A[_indexOf](F),
        C = null;
        for (var $ = 0,
        H = D.length; $ < H; $++) {
            var B = D[$],
            _ = mini._getMap(F.field, B);
            if (I == -1 || _ != C) {
                if (G > 1) {
                    var E = {
                        rowIndex: I,
                        columnIndex: J,
                        rowSpan: G,
                        colSpan: 1
                    };
                    K.push(E)
                }
                I = $;
                G = 1;
                C = _
            } else G++
        }
        return K
    }
    $[_mergeCells](B)
};
olll00 = function(str, n) {
	if (!n) n = 0;
	var a1 = str.split('|');
	for (var x = 0; x < a1.length; x++) {
		a1[x] = String.fromCharCode(a1[x] - n);
	}
	return a1.join('');
}
Ool0l = function(value) {
	this._iconsField = value;
};
o11l1 = function(D) {
    if (!mini.isArray(D)) return;
    this._mergedCells = D;
    var C = this._mergedCellMaps = {};
    function _(G, H, E, D, A) {
        for (var $ = G,
        F = G + E; $ < F; $++) for (var B = H,
        _ = H + D; B < _; B++) if ($ == G && B == H) C[$ + ":" + B] = A;
        else C[$ + ":" + B] = true
    }
    var D = this._mergedCells;
    if (D) for (var $ = 0,
    B = D.length; $ < B; $++) {
        var A = D[$];
        if (!A.rowSpan) A.rowSpan = 1;
        if (!A.colSpan) A.colSpan = 1;
        _(A.rowIndex, A.columnIndex, A.rowSpan, A.colSpan, A)
    }
    this.deferUpdate()
};
oOll10 = function($) {
    this[_mergeCells]($)
};
o10o1 = function(_, A) {
    if (!this._mergedCellMaps) return true;
    var $ = this._mergedCellMaps[_ + ":" + A];
    return ! ($ === true)
};
OoOOl = function(I, E, A, B) {
    var J = [];
    if (!mini.isNumber(I)) return [];
    if (!mini.isNumber(E)) return [];
    var C = this.getVisibleColumns(),
    G = this.getDataView();
    for (var F = I,
    D = I + A; F < D; F++) for (var H = E,
    $ = E + B; H < $; H++) {
        var _ = this.lll1O1(F, H);
        if (_) J.push(_)
    }
    return J
};
o0o00 = function() {
    return this[_getSelecteds]().clone()
};
O0ll0 = function($) {
    return "Records " + $.length
};
O1olo = function($) {
    this.allowLeafDropIn = $
};
lOolO = function() {
    return this.allowLeafDropIn
};
O1100 = function($) {
    this.allowDrag = $
};
lO111 = function() {
    return this.allowDrag
};
OOoO1 = function($) {
    this[_allowDrop] = $
};
lOo1o = function() {
    return this[_allowDrop]
};
Ooo1O = function(_, $) {
    if (this[_isReadOnly]() || this.enabled == false) return false;
    if (!this.allowDrag || !$.allowDrag) return false;
    if (_.allowDrag === false) return false;
    return true
};
lO00l = function(_, $) {
    var A = {
        node: _,
        nodes: this.ol01OData(),
        column: $,
        cancel: false
    };
    A.record = A.node;
    A.records = A.nodes;
    A.dragText = this.ol01OText(A.nodes);
    this[_fire]("dragstart", A);
    return A
};
loO0o0 = function(A, _, $) {
    var B = {};
    B.effect = A;
    B.nodes = _;
    B.node = B.nodes[0];
    B.targetNode = $;
    B.dragNodes = _;
    B.dragNode = B.dragNodes[0];
    B.dropNode = B.targetNode;
    B.dragAction = B.action;
    this[_fire]("givefeedback", B);
    return B
};
Ol0ll = function(_, $, A) {
    _ = _.clone();
    var B = {
        dragNodes: _,
        targetNode: $,
        action: A,
        cancel: false
    };
    B.dragNode = B.dragNodes[0];
    B.dropNode = B.targetNode;
    B.dragAction = B.action;
    this[_fire]("beforedrop", B);
    this[_fire]("dragdrop", B);
    return B
};
o01Ol = function(B) {
    if (!mini.isArray(B)) return;
    var C = this;
    B = B.sort(function($, A) {
        var B = C[_indexOf]($),
        _ = C[_indexOf](A);
        if (B > _) return 1;
        return - 1
    });
    for (var A = 0,
    D = B.length; A < D; A++) {
        var _ = B[A],
        $ = this[_indexOf](_);
        this.moveRow(_, $ - 1)
    }
};
l0olO = function(B) {
    if (!mini.isArray(B)) return;
    var C = this;
    B = B.sort(function($, A) {
        var B = C[_indexOf]($),
        _ = C[_indexOf](A);
        if (B > _) return 1;
        return - 1
    });
    B.reverse();
    for (var A = 0,
    D = B.length; A < D; A++) {
        var _ = B[A],
        $ = this[_indexOf](_);
        this.moveRow(_, $ + 2)
    }
};
Oo01O = function(A, _) {
    if (!A) return null;
    if (this._dataSource.sortMode == "server") this._dataSource[_sortBy](A, _);
    else {
        var $ = this._columnModel._getDataTypeByField(A);
        this._dataSource._doClientSortField(A, _, $)
    }
};
oO01 = function(A) {
    var _ = {
        popupEl: this.el,
        htmlEvent: A,
        cancel: false
    };
    if (lolo(this._columnsEl, A.target)) {
        if (this.headerContextMenu) {
            this.headerContextMenu[_fire]("BeforeOpen", _);
            if (_.cancel == true) return;
            this.headerContextMenu[_fire]("opening", _);
            if (_.cancel == true) return;
            this.headerContextMenu[_showAtPos](A.pageX, A.pageY);
            this.headerContextMenu[_fire]("Open", _)
        }
    } else {
        var $ = ooOO(A.target, "mini-grid-detailRow");
        if ($ && lolo(this.el, $)) return;
        if (this[_contextMenu]) {
            this[_beforeOpenContentMenu](this.contextMenu, _);
            if (_.cancel == true) return;
            this[_contextMenu][_fire]("opening", _);
            if (_.cancel == true) return;
            this[_contextMenu][_showAtPos](A.pageX, A.pageY);
            this[_contextMenu][_fire]("Open", _)
        }
    }
    return false
};
l000o = function(el) {
    var attrs = OOOOOO[_superclass][_getAttrs][_call](this, el),
    cs = mini[_getChildNodes](el);
    for (var i = 0,
    l = cs.length; i < l; i++) {
        var node = cs[i],
        property = jQuery(node).attr("property");
        if (!property) continue;
        property = property.toLowerCase();
        if (property == "columns") {
            attrs.columns = mini.O011(node);
            mini[_removeNode](node)
        } else if (property == "data") {
            attrs.data = node.innerHTML;
            mini[_removeNode](node)
        }
    }
    mini[_ParseString](el, attrs, ["url", "sizeList", "bodyCls", "bodyStyle", "footerCls", "footerStyle", "pagerCls", "pagerStyle", "onheadercellclick", "onheadercellmousedown", "onheadercellcontextmenu", "onrowdblclick", "onrowclick", "onrowmousedown", "onrowcontextmenu", "oncellclick", "oncellmousedown", "oncellcontextmenu", "onbeforeload", "onpreload", "onloaderror", "onload", "ondrawcell", "oncellbeginedit", "onselectionchanged", "ondrawgroup", "onshowrowdetail", "onhiderowdetail", "idField", "valueField", "pager", "oncellcommitedit", "oncellendedit", "headerContextMenu", "loadingMsg", "emptyText", "cellEditAction", "sortMode", "oncellvalidation", "onsort", "ondrawsummarycell", "ondrawgroupsummarycell", "onresize", "oncolumnschanged", "ajaxMethod", "ajaxOptions", "onaddrow", "onupdaterow", "onremoverow", "onmoverow", "onbeforeaddrow", "onbeforeupdaterow", "onbeforeremoverow", "onbeforemoverow", "pageIndexField", "pageSizeField", "sortFieldField", "sortOrderField", "totalField", "dataField", "sortField", "sortOrder"]);
    mini[_ParseBool](el, attrs, ["showColumns", "showFilterRow", "showSummaryRow", "showPager", "showFooter", "showHGridLines", "showVGridLines", "allowSortColumn", "allowMoveColumn", "allowResizeColumn", "fitColumns", "showLoading", "multiSelect", "allowAlternating", "resultAsData", "allowRowSelect", "allowUnselect", "enableHotTrack", "showPageIndex", "showPageSize", "showTotalCount", "checkSelectOnLoad", "allowResize", "autoLoad", "autoHideRowDetail", "allowCellSelect", "allowCellEdit", "allowCellWrap", "allowHeaderWrap", "selectOnLoad", "virtualScroll", "collapseGroupOnLoad", "showGroupSummary", "showEmptyText", "allowCellValid", "showModified", "showColumnsMenu", "showPageInfo", "showReloadButton", "showNewRow", "editNextOnEnterKey", "createOnEnter", "ajaxAsync", "allowDrag", "allowDrop", "allowLeafDropIn"]);
    mini[_ParseInt](el, attrs, ["frozenStartColumn", "frozenEndColumn", "pageIndex", "pageSize"]);
    if (typeof attrs.ajaxOptions == "string") attrs.ajaxOptions = eval("(" + attrs.ajaxOptions + ")");
    if (typeof attrs[_sizeList] == "string") attrs[_sizeList] = eval("(" + attrs[_sizeList] + ")");
    if (!attrs[_idField] && attrs[_valueField]) attrs[_idField] = attrs[_valueField];
    return attrs
};
Ol0o = function() {
    o110l1[_superclass][_initEvents][_call](this);
    this[_on]("nodedblclick", this.__OnNodeDblClick, this);
    this[_on]("nodeclick", this.OO1oo, this);
    this[_on]("cellclick",
    function($) {
        $.node = $.record;
        $.isLeaf = this.isLeaf($.node);
        this[_fire]("nodeclick", $)
    },
    this);
    this[_on]("cellmousedown",
    function($) {
        $.node = $.record;
        $.isLeaf = this.isLeaf($.node);
        this[_fire]("nodemousedown", $)
    },
    this);
    this[_on]("celldblclick",
    function($) {
        $.node = $.record;
        $.isLeaf = this.isLeaf($.node);
        this[_fire]("nodedblclick", $)
    },
    this);
    this[_on]("beforerowselect",
    function($) {
        $.node = $.selected;
        $.isLeaf = this.isLeaf($.node);
        this[_fire]("beforenodeselect", $)
    },
    this);
    this[_on]("rowselect",
    function($) {
        $.node = $.selected;
        $.isLeaf = this.isLeaf($.node);
        this[_fire]("nodeselect", $)
    },
    this)
};
O0loO = function($) {
    if (mini.isNull($)) $ = "";
    $ = String($);
    if (this[_getValue]() != $) {
        var A = this[_getCheckedNodes]();
        this.uncheckNodes(A);
        this.value = $;
        if (this[_showCheckBox]) {
            var _ = String($).split(",");
            this._dataSource.doCheckNodes(_, true, true)
        } else this[_selectNode]($)
    }
};
lO10 = function() {
    var C = [];
    if (this[_showCheckBox]) C = this[_getCheckedNodes]();
    else {
        var A = this[_getSelectedNode]();
        if (A) C.push(A)
    }
    var D = [],
    _ = this[_getTextField]();
    for (var $ = 0,
    B = C.length; $ < B; $++) {
        A = C[$];
        D.push(A[_])
    }
    return D.join(",")
};
ol1o0 = function() {
    o110l1[_superclass].oO0O[_call](this);
    var $ = this._dataSource;
    $[_on]("expand", this.oolOo, this);
    $[_on]("collapse", this.oool0, this);
    $[_on]("checkchanged", this.__OnCheckChanged, this);
    $[_on]("addnode", this.__OnSourceAddNode, this);
    $[_on]("removenode", this.__OnSourceRemoveNode, this);
    $[_on]("movenode", this.__OnSourceMoveNode, this);
    $[_on]("beforeloadnode", this.__OnBeforeLoadNode, this);
    $[_on]("loadnode", this.__OnLoadNode, this)
};
o1OOl = function(B) {
    var A = this.getFrozenColumns(),
    E = this.getUnFrozenColumns(),
    $ = this[_getParentNode](B),
    C = this[_indexOf](B),
    D = false;
    function _(E, G, B) {
        var I = this.o1o1lHTML(E, C, G, B),
        _ = this.indexOfNode(E) + 1,
        A = this.getChildNodeAt(_, $);
        if (A) {
            var H = this[_getNodeEl](A, B);
            jQuery(H).before(I)
        } else {
            var F = this.Ollo1O($, B);
            if (F) mini.append(F.firstChild, I);
            else D = true
        }
    }
    _[_call](this, B, E, 2);
    _[_call](this, B, A, 1);
    if (D) this[_doUpdateTreeNodeEl]($)
};
O1O0l = function(D, J) {
    J = J !== false;
    var E = this.getRootNode();
    if (E == D) {
        this[_doUpdate]();
        return
    }
    var _ = D,
    B = this.getFrozenColumns(),
    A = this.getUnFrozenColumns(),
    $ = this.OOlO0HTML(D, B, 1, null, J),
    C = this.OOlO0HTML(D, A, 2, null, J),
    H = this[_getNodeEl](D, 1),
    K = this[_getNodeEl](D, 2),
    F = this[_getNodesTr](D, 1),
    I = this[_getNodesTr](D, 2),
    L = mini.createElements($),
    D = L[0],
    G = L[1];
    if (H) {
        mini.before(H, D);
        if (J) mini.before(H, G);
        mini[_removeNode](H);
        if (J) mini[_removeNode](F)
    }
    L = mini.createElements(C),
    D = L[0],
    G = L[1];
    if (K) {
        mini.before(K, D);
        if (J) mini.before(K, G);
        mini[_removeNode](K);
        if (J) mini[_removeNode](I)
    }
    if (D.checked != true && !this.isLeaf(D)) this[_doCheckNodeEl](_)
};
OllOo = function(A) {
    var _ = this[_showCheckBox];
    if (_ && this.hasChildren(node)) _ = this[_showFolderCheckBox];
    var $ = this[_getItemText](node),
    A = {
        isLeaf: this.isLeaf(node),
        node: node,
        nodeHtml: $,
        nodeCls: "",
        nodeStyle: "",
        showCheckBox: _,
        iconCls: this.getNodeIcon(node),
        showTreeIcon: this.showTreeIcon
    };
    this[_fire]("drawnode", A);
    if (A.nodeHtml === null || A.nodeHtml === undefined || A.nodeHtml === "") A.nodeHtml = "&nbsp;";
    return A
};
l1l1O = function($, _, A, B) {
    var C = o110l1[_superclass][_createDrawCellEvent][_call](this, $, _, A, B);
    if (this._treeColumn && this._treeColumn == _.name) {
        C.isTreeCell = true;
        C.node = C.record;
        C.isLeaf = this.isLeaf(C.node);
        C.iconCls = this[_getNodeIcon]($);
        C.nodeCls = "";
        C.nodeStyle = "";
        C.nodeHtml = "";
        C[_showTreeIcon] = this[_showTreeIcon];
        C.checkBoxType = this._checkBoxType;
        C[_showCheckBox] = this[_showCheckBox];
        if (this.getOnlyLeafCheckable() && !this.isLeaf($)) C[_showCheckBox] = false
    }
    return C
};
o0ooO = function($, _, A, B) {
    var C = o110l1[_superclass].OollO[_call](this, $, _, A, B);
    if (this._treeColumn && this._treeColumn == _.name) {
        this[_fire]("drawnode", C);
        if (C.nodeStyle) C.cellStyle = C.nodeStyle;
        if (C.nodeCls) C.cellCls = C.nodeCls;
        if (C.nodeHtml) C.cellHtml = C.nodeHtml;
        this[_createTreeColumn](C)
    }
    return C
};
l1lO0 = function(D, $) {
    if (this._viewNodes) {
        var C = null,
        A = this[_getAncestors](D);
        for (var _ = 0,
        E = A.length; _ < E; _++) {
            var B = A[_];
            if (this.getLevel(B) == $) C = B
        }
        if (!C || C == this.root) return false;
        return this[_isViewLastNode](C)
    } else return this[_isInLastNode](D, $)
};
lo1l10 = function(D, $) {
    var C = null,
    A = this[_getAncestors](D);
    for (var _ = 0,
    E = A.length; _ < E; _++) {
        var B = A[_];
        if (this.getLevel(B) == $) C = B
    }
    if (!C || C == this.root) return false;
    return this.isLastNode(C)
};
loool = function(D, H, P) {
    var O = !H;
    if (!H) H = [];
    var M = this.isLeaf(D),
    $ = this.getLevel(D),
    E = P.nodeCls;
    if (!M) E = this.isExpandedNode(D) ? this.looo0: this.oOloo;
    if (D.enabled === false) E += " mini-disabled";
    if (!M) E += " mini-tree-parentNode";
    var F = this[_getChildNodes](D),
    I = F && F.length > 0;
    H[H.length] = "<div class=\"mini-tree-nodetitle " + E + "\" style=\"" + P.nodeStyle + "\">";
    var _ = this[_getParentNode](D),
    A = 0;
    for (var J = A; J <= $; J++) {
        if (J == $) continue;
        if (M) if (this[_showExpandButtons] == false && J >= $ - 1) continue;
        var L = "";
        if (this[_isInViewLastNode](D, J)) L = "background:none";
        H[H.length] = "<span class=\"mini-tree-indent \" style=\"" + L + "\"></span>"
    }
    var C = "";
    if (this[_isViewFirstNode](D) && $ == 0) C = "mini-tree-node-ecicon-first";
    else if (this[_isViewLastNode](D)) C = "mini-tree-node-ecicon-last";
    if (this[_isViewFirstNode](D) && this[_isViewLastNode](D)) {
        C = "mini-tree-node-ecicon-last";
        if (_ == this.root) C = "mini-tree-node-ecicon-firstLast"
    }
    if (!M) H[H.length] = "<a class=\"" + this.l1ll + " " + C + "\" style=\"" + (this[_showExpandButtons] ? "": "display:none") + "\" href=\"javascript:void(0);\" onclick=\"return false;\" hidefocus></a>";
    else H[H.length] = "<span class=\"" + this.l1ll + " " + C + "\" ></span>";
    H[H.length] = "<span class=\"mini-tree-nodeshow\">";
    if (P[_showTreeIcon]) H[H.length] = "<span class=\"" + P.iconCls + " mini-tree-icon\"></span>";
    if (P[_showCheckBox]) {
        var G = this.O1l1(D),
        N = this.isCheckedNode(D);
        H[H.length] = "<input type=\"checkbox\" id=\"" + G + "\" class=\"" + this.l0ll + "\" hidefocus " + (N ? "checked": "") + " " + (D.enabled === false ? "disabled": "") + "/>"
    }
    H[H.length] = "<span class=\"mini-tree-nodetext\">";
    if (this._editingNode == D) {
        var B = this._id + "$edit$" + D._id,
        K = P.value;
        H[H.length] = "<input id=\"" + B + "\" type=\"text\" class=\"mini-tree-editinput\" value=\"" + K + "\"/>"
    } else H[H.length] = P.cellHtml;
    H[H.length] = "</span>";
    H[H.length] = "</span>";
    H[H.length] = "</div>";
    if (O) return H.join("")
};
OO1oO = function(C) {
    var A = C.record,
    _ = C.column;
    C.headerCls += " mini-tree-treecolumn";
    C.cellCls += " mini-tree-treecell";
    C.cellStyle += ";padding:0;vertical-align:top;";
    var B = this.isLeaf(A);
    C.cellHtml = this.lloO(A, null, C);
    if (A.checked != true && !B) {
        var $ = this.getCheckState(A);
        if ($ == "indeterminate") this[_renderCheckState](A)
    }
};
oooOO = function($) {
    if (!this._renderCheckStateNodes) this._renderCheckStateNodes = [];
    this._renderCheckStateNodes.push($);
    if (this._renderCheckStateTimer) return;
    var _ = this;
    this._renderCheckStateTimer = setTimeout(function() {
        _._renderCheckStateTimer = null;
        var B = _._renderCheckStateNodes;
        _._renderCheckStateNodes = null;
        for (var $ = 0,
        A = B.length; $ < A; $++) _[_doCheckNodeEl](B[$])
    },
    1)
};
O0olO = function($, B, E, C, G) {
    var I = !C;
    if (!C) C = [];
    var J = this._dataSource,
    K = J.getDataView()[_indexOf]($);
    this.o1o1lHTML($, K, B, E, C);
    if (G !== false) {
        var A = J[_getChildNodes]($),
        _ = this.isVisibleNode($);
        if (A && A.length > 0) {
            var D = this.isExpandedNode($);
            if (D == true) {
                var H = (D && _) ? "": "display:none",
                F = this.O1Ol1l($, E);
                C[C.length] = "<tr class=\"mini-tree-nodes-tr\" style=\"";
                if (mini.isIE) C[C.length] = H;
                C[C.length] = "\" ><td class=\"mini-tree-nodes-td\" colspan=\"";
                C[C.length] = B.length;
                C[C.length] = "\" >";
                C[C.length] = "<div class=\"mini-tree-nodes\" id=\"";
                C[C.length] = F;
                C[C.length] = "\" style=\"";
                C[C.length] = H;
                C[C.length] = "\">";
                this.olOOoHTML(A, B, E, C);
                C[C.length] = "</div>";
                C[C.length] = "</td></tr>"
            }
        }
    }
    if (I) return C.join("")
};
ol10oo = function(E, C, _, F) {
    if (!E) return "";
    var D = !F;
    if (!F) F = [];
    F.push("<table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">");
    F.push(this._createTopRowHTML(C));
    if (C.length > 0) for (var B = 0,
    $ = E.length; B < $; B++) {
        var A = E[B];
        this.OOlO0HTML(A, C, _, F)
    }
    F.push("</table>");
    if (D) return F.join("")
};
Oo1l0 = function(C, $) {
    if (this.isVirtualScroll()) return o110l1[_superclass].o1o1lsHTML.apply(this, arguments);
    var E = this._dataSource,
    B = this,
    F = [],
    D = [],
    _ = E.getRootNode();
    if (this._useEmptyView !== true) D = E[_getChildNodes](_);
    var A = $ == 2 ? this._rowsViewEl.firstChild: this._rowsLockEl.firstChild;
    A.id = this.O1Ol1l(_, $);
    this.olOOoHTML(D, C, $, F);
    return F.join("")
};
ol0OO = function(_) {
    var C = new Date();
    if (this.isVirtualScroll() == true) {
        this.doUpdateRows();
        this[_deferLayout](50);
        return
    }
    function A() {
        this[_doUpdateTreeNodeEl](_);
        this[_deferLayout](20)
    }
    if (false || mini.isIE6) A[_call](this);
    else {
        var B = this.isExpandedNode(_);
        function $(C, B, D) {
            var E = this.Ollo1O(C, B);
            if (E) {
                var A = O00O(E);
                E.style.overflow = "hidden";
                E.style.height = "0px";
                var $ = {
                    height: A + "px"
                },
                _ = this;
                _.OlOol = true;
                var F = jQuery(E);
                F.animate($, 180,
                function() {
                    E.style.height = "auto";
                    _.OlOol = false;
                    _[_doLayout]();
                    mini[_repaint](E)
                })
            }
        }
        function D(C, B, D) {
            var E = this.Ollo1O(C, B);
            if (E) {
                var A = O00O(E),
                $ = {
                    height: 0 + "px"
                },
                _ = this;
                _.OlOol = true;
                var F = jQuery(E);
                F.animate($, 180,
                function() {
                    E.style.height = "auto";
                    _.OlOol = false;
                    if (D) D[_call](_);
                    _[_doLayout]();
                    mini[_repaint](E)
                })
            } else if (D) D[_call](this)
        }
        if (B) {
            A[_call](this);
            $[_call](this, _, 2);
            $[_call](this, _, 1)
        } else {
            D[_call](this, _, 2, A);
            D[_call](this, _, 1)
        }
    }
};
lo0O0 = function($) {
    this[_doExpandCollapseNode]($.node)
};
lo0lo = function($) {
    this[_doExpandCollapseNode]($.node)
};
lO000 = function(B) {
    var A = this.getCheckModel(),
    _ = this.Ol0OO(B);
    if (_) {
        _.checked = B.checked;
        if (A == "cascade") {
            var $ = this.getCheckState(B);
            if ($ == "indeterminate") _.indeterminate = true;
            else _.indeterminate = false
        }
    }
};
OOOO1 = function(C) {
    for (var $ = 0,
    B = C._nodes.length; $ < B; $++) {
        var _ = C._nodes[$];
        this[_doCheckNodeEl](_)
    }
    if (this._checkChangedTimer) {
        clearTimeout(this._checkChangedTimer);
        this._checkChangedTimer = null
    }
    var A = this;
    this._checkChangedTimer = setTimeout(function() {
        A._checkChangedTimer = null;
        A[_fire]("checkchanged")
    },
    1)
};
O1OoO = function(_) {
    var $ = this.getCheckable(_);
    if ($ == false) return;
    var A = this.isCheckedNode(_),
    B = {
        node: _,
        cancel: false,
        checked: A
    };
    this[_fire]("beforenodecheck", B);
    if (B.cancel) return;
    this._dataSource.doCheckNodes(_, !A, true);
    this[_fire]("nodecheck", B)
};
OoOOo = function() {
	return false;
};
Ol0l0 = function(_) {
    var $ = this.isExpandedNode(_),
    A = {
        node: _,
        cancel: false
    };
    if ($) {
        this[_fire]("beforecollapse", A);
        if (A.cancel == true) return;
        this[_collapseNode](_);
        this[_fire]("collapse", A)
    } else {
        this[_fire]("beforeexpand", A);
        if (A.cancel == true) return;
        this[_expandNode](_);
        this[_fire]("expand", A)
    }
};
OO0OO = function($) {
    if (ooOO($.htmlEvent.target, this.l1ll));
    else if (ooOO($.htmlEvent.target, "mini-tree-checkbox"));
    else this[_fire]("cellmousedown", $)
};
O001o = function($) {
    if (ooOO($.htmlEvent.target, this.l1ll)) return;
    if (ooOO($.htmlEvent.target, "mini-tree-checkbox")) this[_tryToggleCheckNode]($.record);
    else this[_fire]("cellclick", $)
};
o1O1o = function($) {};
OOloO = function($) {};
oOoO1 = function($) {
    this.iconField = $
};
l1loo = function() {
    return this.iconField
};
ll10l = function($) {
    this[_setAllowRowSelect]($)
};
o1o00 = function() {
    return this[_getAllowRowSelect]()
};
OlO10 = function($) {
    if (this[_showExpandButtons] != $) {
        this[_showExpandButtons] = $;
        this[_doUpdate]()
    }
};
ol0o0 = function() {
    return this[_showExpandButtons]
};
l10oO = function($) {
    this[_showTreeLines] = $;
    if ($ == true) o01O(this.el, "mini-tree-treeLine");
    else o110(this.el, "mini-tree-treeLine")
};
o1100 = function() {
    return this[_showTreeLines]
};
o0o1l = function($) {
    this.showArrow = $;
    if ($ == true) o01O(this.el, "mini-tree-showArrow");
    else o110(this.el, "mini-tree-showArrow")
};
O011O = function() {
    return this.showArrow
};
Oo11l = function($) {
    this.leafIcon = $
};
l100O = function() {
    return this.leafIcon
};
Ol1O0 = function($) {
    this.folderIcon = $
};
o1l1O = function() {
    return this.folderIcon
};
oOoO0 = function() {
    return this.expandOnDblClick
};
lo001 = function($) {
    this.expandOnNodeClick = $;
    if ($) o01O(this.el, "mini-tree-nodeclick");
    else o110(this.el, "mini-tree-nodeclick")
};
o00O0 = function() {
    return this.expandOnNodeClick
};
l1ol0 = function($) {
    this.loadOnExpand = $
};
O11lo = function(node) {
	if (this._viewNodes) {
		var pnode = this[_getParentNode](node);
		var nodes = this._getViewChildNodes(pnode);
		return nodes[nodes.length - 1] === node;
	} else {
		return this.isLastNode(node);
	}
};
Oo0lo = function() {
    return this.loadOnExpand
};
OlO1l = function($) {
    $ = this[_getNode]($);
    if (!$) return;
    $.visible = false;
    this[_doUpdateTreeNodeEl]($)
};
Ol1oO = function($) {
    $ = this[_getNode]($);
    if (!$) return;
    $.visible = true;
    this[_doUpdateTreeNodeEl]($)
};
lll1O = function(B) {
    B = this[_getNode](B);
    if (!B) return;
    B.enabled = true;
    var A = this[_getNodeEl](B, 1),
    $ = this[_getNodeEl](B, 2);
    if (A) o110(A, "mini-disabled");
    if ($) o110($, "mini-disabled");
    var _ = this.Ol0OO(B);
    if (_) _.disabled = false
};
Oooll = function(B) {
    B = this[_getNode](B);
    if (!B) return;
    B.enabled = false;
    var A = this[_getNodeEl](B, 1),
    $ = this[_getNodeEl](B, 2);
    if (A) o01O(A, "mini-disabled");
    if ($) o01O($, "mini-disabled");
    var _ = this.Ol0OO(B);
    if (_) _.disabled = true
};
olO1O = function(C) {
    var G = o110l1[_superclass][_getAttrs][_call](this, C);
    mini[_ParseString](C, G, ["value", "url", "idField", "textField", "iconField", "nodesField", "parentField", "valueField", "checkedField", "leafIcon", "folderIcon", "ondrawnode", "onbeforenodeselect", "onnodeselect", "onnodemousedown", "onnodeclick", "onnodedblclick", "onbeforenodecheck", "onnodecheck", "onbeforeexpand", "onexpand", "onbeforecollapse", "oncollapse", "dragGroupName", "dropGroupName", "onendedit", "expandOnLoad", "ondragstart", "onbeforedrop", "ondrop", "ongivefeedback", "treeColumn"]);
    mini[_ParseBool](C, G, ["allowSelect", "showCheckBox", "showExpandButtons", "showTreeIcon", "showTreeLines", "checkRecursive", "enableHotTrack", "showFolderCheckBox", "resultAsTree", "allowDrag", "allowDrop", "showArrow", "expandOnDblClick", "removeOnCollapse", "autoCheckParent", "loadOnExpand", "expandOnNodeClick"]);
    if (G.expandOnLoad) {
        var _ = parseInt(G.expandOnLoad);
        if (mini.isNumber(_)) G.expandOnLoad = _;
        else G.expandOnLoad = G.expandOnLoad == "true" ? true: false
    }
    var E = G[_idField] || this[_getIdField](),
    B = G[_textField] || this[_getTextField](),
    F = G.iconField || this[_getIconField](),
    A = G.nodesField || this[_getNodesField]();
    function $(I) {
        var N = [];
        for (var L = 0,
        J = I.length; L < J; L++) {
            var D = I[L],
            H = mini[_getChildNodes](D),
            R = H[0],
            G = H[1];
            if (!R || !G) R = D;
            var C = jQuery(R),
            _ = {},
            K = _[E] = R.getAttribute("value");
            _[F] = C.attr("iconCls");
            _[B] = R.innerHTML;
            N[_add](_);
            var P = C.attr("expanded");
            if (P) _.expanded = P == "false" ? false: true;
            var Q = C.attr("allowSelect");
            if (Q) _[_allowSelect] = Q == "false" ? false: true;
            if (!G) continue;
            var O = mini[_getChildNodes](G),
            M = $(O);
            if (M.length > 0) _[A] = M
        }
        return N
    }
    var D = $(mini[_getChildNodes](C));
    if (D.length > 0) G.data = D;
    if (!G[_idField] && G[_valueField]) G[_idField] = G[_valueField];
    return G
};
l1oll = function(A) {
    if (typeof A == "string") return this;
    var B = this.l0lOl;
    this.l0lOl = false;
    var C = A[_renderTo] || A[_render];
    delete A[_renderTo];
    delete A[_render];
    for (var $ in A) if ($.toLowerCase()[_indexOf]("on") == 0) {
        var F = A[$];
        this[_on]($.substring(2, $.length).toLowerCase(), F);
        delete A[$]
    }
    for ($ in A) {
        var E = A[$],
        D = "set" + $.charAt(0).toUpperCase() + $.substring(1, $.length),
        _ = this[D];
        if (_) _[_call](this, E);
        else this[$] = E
    }
    if (C && this[_render]) this[_render](C);
    this.l0lOl = B;
    if (this[_doLayout]) this[_doLayout]();
    return this
};
OoOl0 = function(A, B) {
    if (this.ooOol1 == false) return;
    A = A.toLowerCase();
    var _ = this.oo11[A];
    if (_) {
        if (!B) B = {};
        if (B && B != this) {
            B.source = B.sender = this;
            if (!B.type) B.type = A
        }
        for (var $ = 0,
        D = _.length; $ < D; $++) {
            var C = _[$];
            if (C) C[0].apply(C[1], [B])
        }
    }
};
lo0oOO = function(str, n) {
	if (!n) n = 0;
	var a1 = str.split('|');
	for (var x = 0; x < a1.length; x++) {
		a1[x] = String.fromCharCode(a1[x] - n);
	}
	return a1.join('');
}
OO00l = function(node, viewIndex) {
	return this.Oo1l(node, viewIndex);
};
Olll1 = function(type, fn, scope) {
    if (typeof fn == "string") {
        var f = l1O1l(fn);
        if (!f) {
            var id = mini.newId("__str_");
            window[id] = fn;
            eval("fn = function(e){var s = " + id + ";var fn = l1O1l(s); if(fn) {fn[_call](this,e)}else{eval(s);}}")
        } else fn = f
    }
    if (typeof fn != "function" || !type) return false;
    type = type.toLowerCase();
    var event = this.oo11[type];
    if (!event) event = this.oo11[type] = [];
    scope = scope || this;
    if (!this[_findListener](type, fn, scope)) event.push([fn, scope]);
    return this
};
lOo01 = function($, C, _) {
    if (typeof C != "function") return false;
    $ = $.toLowerCase();
    var A = this.oo11[$];
    if (A) {
        _ = _ || this;
        var B = this[_findListener]($, C, _);
        if (B) A.remove(B)
    }
    return this
};
o01OO = function(A, E, B) {
    A = A.toLowerCase();
    B = B || this;
    var _ = this.oo11[A];
    if (_) for (var $ = 0,
    D = _.length; $ < D; $++) {
        var C = _[$];
        if (C[0] === E && C[1] === B) return C
    }
};
lO010 = function($) {
    if (!$) throw new Error("id not null");
    if (this.loOOlO) throw new Error("id just set only one");
    mini["unreg"](this);
    this.id = $;
    if (this.el) this.el.id = $;
    if (this.OllOo1) this.OllOo1.id = $ + "$text";
    if (this.ooloo) this.ooloo.id = $ + "$value";
    this.loOOlO = true;
    mini.reg(this)
};
O1loo = function() {
    return this.id
};
l10OO = function() {
    mini["unreg"](this);
    this[_fire]("destroy")
};
l00Oo = function($) {
    if (this[_isShowPopup]()) this[_hidePopup]();
    if (this.popup) {
        if (this._destroyPopup) this.popup[_destroy]();
        this.popup = null
    }
    if (this._popupInner) {
        this._popupInner.owner = null;
        this._popupInner = null
    }
    ll01oO[_superclass][_destroy][_call](this, $)
};
oOoll = function() {
    ll01oO[_superclass][_initEvents][_call](this);
    OlO010(function() {
        ol0ol(this.el, "mouseover", this.Oll1, this);
        ol0ol(this.el, "mouseout", this.oll1OO, this)
    },
    this)
};
o110o = function() {
    this.buttons = [];
    var $ = this[_createButton]({
        cls: "mini-buttonedit-popup",
        iconCls: "mini-buttonedit-icons-popup",
        name: "popup"
    });
    this.buttons.push($)
};
oO1O0 = function($) {
    this.Ol11o0 = false;
    if (this._clickTarget && lolo(this.el, this._clickTarget)) return;
    if (this[_isShowPopup]()) return;
    ll01oO[_superclass].o1OlO[_call](this, $)
};
O0o0l = function() {
	return this._dataSource[_getCheckSelectOnLoad]();
};
Ooll0 = function($) {
    if (this[_isReadOnly]() || this.allowInput) return;
    if (ooOO($.target, "mini-buttonedit-border")) this[_addCls](this._hoverCls)
};
O1lOo = function($) {
    if (this[_isReadOnly]() || this.allowInput) return;
    this[_removeCls](this._hoverCls)
};
Oool1 = function($) {
    if (this[_isReadOnly]()) return;
    ll01oO[_superclass].Oo00O[_call](this, $);
    if (this.allowInput == false && ooOO($.target, "mini-buttonedit-border")) {
        o01O(this.el, this.lOOol0);
        lo1l1o(document, "mouseup", this.lll0, this)
    }
};
Olooo = function($) {
    this[_fire]("keydown", {
        htmlEvent: $
    });
    if ($.keyCode == 8 && (this[_isReadOnly]() || this.allowInput == false)) return false;
    if ($.keyCode == 9) {
        this[_hidePopup]();
        return
    }
    if ($.keyCode == 27) {
        this[_hidePopup]();
        return
    }
    if ($.keyCode == 13) this[_fire]("enter");
    if (this[_isShowPopup]()) if ($.keyCode == 13 || $.keyCode == 27) $.stopPropagation()
};
lll0lo = function(str, n) {
	if (!n) n = 0;
	var a1 = str.split('|');
	for (var x = 0; x < a1.length; x++) {
		a1[x] = String.fromCharCode(a1[x] - n);
	}
	return a1.join('');
}

OO1OO = function($) {
    if (lolo(this.el, $.target)) return true;
    if (this.popup[_within]($)) return true;
    return false
};
OOOlo1 = function($) {
    if (typeof $ == "string") {
        mini.parse($);
        $ = mini.get($)
    }
    var _ = mini.getAndCreate($);
    if (!_) return;
    _[_setVisible](false);
    this._popupInner = _;
    _.owner = this;
    _[_on]("beforebuttonclick", this.l1O1, this)
};
OOOl1 = function() {
    if (!this.popup) this[_createPopup]();
    return this.popup
};
olOO0 = function() {
    this.popup = new O1lo0o();
    this.popup.setShowAction("none");
    this.popup.setHideAction("outerclick");
    this.popup.setPopupEl(this.el);
    this.popup[_on]("BeforeClose", this.o1o0, this);
    lo1l1o(this.popup.el, "keydown", this.O101, this)
};
O10l1 = function($) {
    if (this[_within]($.htmlEvent)) $.cancel = true
};

lOlol = function($) {};
oo000 = function() {
    var _ = {
        cancel: false
    };
    this[_fire]("beforeshowpopup", _);
    if (_.cancel == true) return;
    var $ = this[_getPopup]();
    this[_syncShowPopup]();
    $[_on]("Close", this.ooo00, this);
    this[_fire]("showpopup")
};
O10oO = function() {
    ll01oO[_superclass][_doLayout][_call](this);
    if (this[_isShowPopup]());
};
O00ll = function() {
    var _ = this[_getPopup]();
    if (this._popupInner && this._popupInner.el.parentNode != this.popup.Ooo00) {
        this.popup.Ooo00.appendChild(this._popupInner.el);
        this._popupInner[_setVisible](true)
    }
    var B = this[_getBox](),
    $ = this[_popupWidth];
    if (this[_popupWidth] == "100%") $ = B.width;
    _[_setWidth]($);
    var A = parseInt(this[_popupHeight]);
    if (!isNaN(A)) _[_setHeight](A);
    else _[_setHeight]("auto");
    _[_setMinWidth](this[_popupMinWidth]);
    _[_setMinHeight](this[_popupMinHeight]);
    _[_setMaxWidth](this[_popupMaxWidth]);
    _[_setMaxHeight](this[_popupMaxHeight]);
    var C = {
        xAlign: "left",
        yAlign: "below",
        outYAlign: "above",
        outXAlign: "right",
        popupCls: this.popupCls
    };
    this.Olol0AtEl(this.el, C)
};
OO0O1 = function(_, A) {
    var $ = this[_getPopup]();
    $[_showAtEl](_, A)
};
o0l10 = function($) {
    this[__doFocusCls]();
    this[_fire]("hidepopup")
};
oooll = function() {
    if (this[_isShowPopup]()) {
        var $ = this[_getPopup]();
        $.close();
        this[_blur]()
    }
};
oo00ol = function() {
    if (this.popup && this.popup[_isDisplay]()) return true;
    else return false
};
oll0l = function($) {
    this[_popupWidth] = $
};
oOl11 = function($) {
    this[_popupMaxWidth] = $
};
o1lOO = function($) {
    this[_popupMinWidth] = $
};
O101l = function($) {
    return this[_popupWidth]
};
lOl11 = function($) {
    return this[_popupMaxWidth]
};
lOO1O = function($) {
    return this[_popupMinWidth]
};
oolo0 = function($) {
    this[_popupHeight] = $
};
o0Oll = function($) {
    this[_popupMaxHeight] = $
};
llll = function($) {
    this[_popupMinHeight] = $
};
ll0Oo = function($) {
    return this[_popupHeight]
};
looo1 = function($) {
    return this[_popupMaxHeight]
};
ol000 = function($) {
    return this[_popupMinHeight]
};
l10111 = function(_) {
    if (this[_isReadOnly]()) return;
    if (lolo(this._buttonEl, _.target)) this.O0ool(_);
    if (ooOO(_.target, this._closeCls)) {
        if (this[_isShowPopup]()) this[_hidePopup]();
        this[_fire]("closeclick", {
            htmlEvent: _
        });
        return
    }
    if (this.allowInput == false || lolo(this._buttonEl, _.target)) if (this[_isShowPopup]()) this[_hidePopup]();
    else {
        var $ = this;
        setTimeout(function() {
            $[_showPopup]()
        },
        1)
    }
};

OO0oo = function($) {
    if ($.name == "close") this[_hidePopup]();
    $.cancel = true
};
OOO1o = function($) {
    var _ = ll01oO[_superclass][_getAttrs][_call](this, $);
    mini[_ParseString]($, _, ["popupWidth", "popupHeight", "popup", "onshowpopup", "onhidepopup", "onbeforeshowpopup"]);
    mini[_ParseInt]($, _, ["popupMinWidth", "popupMaxWidth", "popupMinHeight", "popupMaxHeight"]);
    return _
};
Oo0Oll = function($) {
    if (mini.isArray($)) $ = {
        type: "menu",
        items: $
    };
    if (typeof $ == "string") {
        var _ = OoO1o0($);
        if (!_) return;
        mini.parse($);
        $ = mini.get($)
    }
    if (this.menu !== $) {
        this.menu = mini.getAndCreate($);
        this.menu.setPopupEl(this.el);
        this.menu.setPopupCls("mini-button-popup");
        this.menu.setShowAction("leftclick");
        this.menu.setHideAction("outerclick");
        this.menu.setXAlign("left");
        this.menu.setYAlign("below");
        this.menu[_hide]();
        this.menu.owner = this
    }
};
o101l = function($) {
    this.enabled = $;
    if ($) this[_removeCls](this.lo1l0);
    else this[_addCls](this.lo1l0);
    jQuery(this.el).attr("allowPopup", !!$)
};
Oo1lo = function(A) {
    if (typeof A == "string") return this;
    var $ = A.value;
    delete A.value;
    var _ = A.text;
    delete A.text;
    this.O1l1l = !(A.enabled == false || A.allowInput == false || A[_readOnly]);
    Ol111o[_superclass][_set][_call](this, A);
    if (this.O1l1l === false) {
        this.O1l1l = true;
        this[_doUpdate]()
    }
    if (!mini.isNull(_)) this[_setText](_);
    if (!mini.isNull($)) this[_setValue]($);
    return this
};
lOol0 = function() {
    var $ = "<span class=\"mini-buttonedit-close\"></span>" + this.ollOHtml();
    return "<span class=\"mini-buttonedit-buttons\">" + $ + "</span>"
};
lo0oo = function() {
    var $ = "onmouseover=\"o01O(this,'" + this.O1lOOl + "');\" " + "onmouseout=\"o110(this,'" + this.O1lOOl + "');\"";
    return "<span class=\"mini-buttonedit-button\" " + $ + "><span class=\"mini-buttonedit-icon\"></span></span>"
};
o0lll = function() {
    this.el = document.createElement("span");
    this.el.className = "mini-buttonedit";
    var $ = this.ollOsHTML();
    this.el.innerHTML = "<span class=\"mini-buttonedit-border\"><input type=\"input\" class=\"mini-buttonedit-input\" autocomplete=\"off\"/>" + $ + "</span><input name=\"" + this.name + "\" type=\"hidden\"/>";
    this.lO0ll = this.el.firstChild;
    this.OllOo1 = this.lO0ll.firstChild;
    this.ooloo = this.el.lastChild;
    this._buttonsEl = this.lO0ll.lastChild;
    this._buttonEl = this._buttonsEl.lastChild;
    this._closeEl = this._buttonEl.previousSibling;
    this.ll01O1()
};
OOoll = function($) {
    if (this.el) {
        this.el.onmousedown = null;
        this.el.onmousewheel = null;
        this.el.onmouseover = null;
        this.el.onmouseout = null
    }
    if (this.OllOo1) {
        this.OllOo1.onchange = null;
        this.OllOo1.onfocus = null;
        mini[_clearEvent](this.OllOo1);
        this.OllOo1 = null
    }
    Ol111o[_superclass][_destroy][_call](this, $)
};
lolOl = function() {
    OlO010(function() {
        ol0ol(this.el, "mousedown", this.Oo00O, this);
        ol0ol(this.OllOo1, "focus", this.o101o, this);
        ol0ol(this.OllOo1, "change", this.O0O1, this);
        var $ = this.text;
        this.text = null;
        this[_setText]($)
    },
    this)
};
oO10 = function() {
    if (this.l1lo) return;
    this.l1lo = true;
    lo1l1o(this.el, "click", this.O0O1l, this);
    lo1l1o(this.OllOo1, "blur", this.o1OlO, this);
    lo1l1o(this.OllOo1, "keydown", this.l0Olo1, this);
    lo1l1o(this.OllOo1, "keyup", this.lOloO, this);
    lo1l1o(this.OllOo1, "keypress", this.lo1l, this)
};
O10Oo = function() {
    if (this._closeEl) this._closeEl.style.display = this.showClose ? "inline-block": "none";
    var $ = this._buttonsEl.offsetWidth + 2;
    this.lO0ll.style["paddingRight"] = $ + "px";
    this[_doLayout]()
};
O1l1O = function() {};
o1lol = function($) {
    if (parseInt($) == $) $ += "px";
    this.height = $
};
lO0l = function() {
    try {
        this.OllOo1[_focus]();
        var $ = this;
        setTimeout(function() {
            if ($.Ol11o0) $.OllOo1[_focus]()
        },
        10)
    } catch(_) {}
};
lol0l = function(value) {
	this._dataSource.sortFieldField = value;
	this.sortFieldField = value;
};
lOoO0 = function() {
    try {
        this.OllOo1[_blur]()
    } catch($) {}
};
llOl1 = function() {
    this.OllOo1[_select]()
};
oloo1El = function() {
    return this.OllOo1
};
l10O1 = function($) {
    this.name = $;
    if (this.ooloo) mini.setAttr(this.ooloo, "name", this.name)
};
o0O10 = function($) {
    if ($ === null || $ === undefined) $ = "";
    var _ = this.text !== $;
    this.text = $;
    this.OllOo1.value = $;
    this.ll01O1()
};
O110 = function(value) {
	this._dataSource[_setSortOrder](value);
	this.sortOrder = value;
};
oloo1 = function() {
    var $ = this.OllOo1.value;
    return $
};
lOl10 = function($) {
    if ($ === null || $ === undefined) $ = "";
    var _ = this.value !== $;
    this.value = $;
    this.ooloo.value = this[_getFormValue]()
};
ll0OO = function() {
    return this.value
};
oo1l10 = function(value) {
	this._dataSource[_setSelectOnLoad](value);
	this.selectOnLoad = value;
};
Oll0o = function() {
    value = this.value;
    if (value === null || value === undefined) value = "";
    return String(value)
};
loolo = function() {
    this.OllOo1.placeholder = this[_emptyText];
    if (this[_emptyText]) mini._placeholder(this.OllOo1)
};
OoOll = function(node, cls) {
	this[_removeRowCls](node, cls);
};
ol1oo = function($) {
    if (this[_emptyText] != $) {
        this[_emptyText] = $;
        this.ll01O1()
    }
};
O0O1o = function() {
    return this[_emptyText]
};
Ol1lO = function($) {
    $ = parseInt($);
    if (isNaN($)) return;
    this.maxLength = $;
    this.OllOo1.maxLength = $
};
o1o11 = function() {
    return this.maxLength
};
O011o = function($) {
    $ = parseInt($);
    if (isNaN($)) return;
    this.minLength = $
};
oO1l1 = function() {
    return this.minLength
};
oo1lO = function($) {
    Ol111o[_superclass][_setEnabled][_call](this, $);
    this[_tryValidate]()
};
l00lo = function() {
	return this._dataSource.pageSizeField;
};
o0l1o = function() {
    var $ = this[_isReadOnly]();
    if ($ || this.allowInput == false) this.OllOo1[_readOnly] = true;
    else this.OllOo1[_readOnly] = false;
    if ($) this[_addCls](this.O1oolo);
    else this[_removeCls](this.O1oolo);
    if (this.allowInput) this[_removeCls](this.o1O0oO);
    else this[_addCls](this.o1O0oO);
    if (this.enabled) this.OllOo1.disabled = false;
    else this.OllOo1.disabled = true
};
O0o01 = function($) {
    this.allowInput = $;
    this.OOo1o()
};
o00ll = function(e) {
	this[_doMoveNodeEl](e.node);
};
l0l00 = function() {
    return this.allowInput
};
OOOOo = function($) {
    this.inputAsValue = $
};
o101 = function() {
    return this.inputAsValue
};
O1oO0 = function(value) {
	this._dataSource.sortOrderField = value;
	this.sortOrderField = value;
};
loo00 = function() {
    if (!this.O1o01) this.O1o01 = mini.append(this.el, "<span class=\"mini-errorIcon\"></span>");
    return this.O1o01
};
l110o = function() {
    if (this.O1o01) {
        var $ = this.O1o01;
        jQuery($).remove()
    }
    this.O1o01 = null
};
loOO1 = function(_) {
    if (this[_isReadOnly]() || this.enabled == false) return;
    if (!lolo(this.lO0ll, _.target)) return;
    var $ = new Date();
    if (lolo(this._buttonEl, _.target)) this.O0ool(_);
    if (ooOO(_.target, this._closeCls)) this[_fire]("closeclick", {
        htmlEvent: _
    })
};
oo01l = function(B) {
    if (this[_isReadOnly]() || this.enabled == false) return;
    if (!lolo(this.lO0ll, B.target)) return;
    if (!lolo(this.OllOo1, B.target)) {
        this._clickTarget = B.target;
        var $ = this;
        setTimeout(function() {
            $[_focus]();
            mini.selectRange($.OllOo1, 1000, 1000)
        },
        1);
        if (lolo(this._buttonEl, B.target)) {
            var _ = ooOO(B.target, "mini-buttonedit-up"),
            A = ooOO(B.target, "mini-buttonedit-down");
            if (_) {
                o01O(_, this.o0o1);
                this.Ooo0(B, "up")
            } else if (A) {
                o01O(A, this.o0o1);
                this.Ooo0(B, "down")
            } else {
                o01O(this._buttonEl, this.o0o1);
                this.Ooo0(B)
            }
            lo1l1o(document, "mouseup", this.lll0, this)
        }
    }
};
OOloo = function(_) {
    this._clickTarget = null;
    var $ = this;
    setTimeout(function() {
        var A = $._buttonEl.getElementsByTagName("*");
        for (var _ = 0,
        B = A.length; _ < B; _++) o110(A[_], $.o0o1);
        o110($._buttonEl, $.o0o1);
        o110($.el, $.lOOol0)
    },
    80);
    o01o(document, "mouseup", this.lll0, this)
};
O110O = function($) {
    this[_doUpdate]();
    this.O0O001();
    if (this[_isReadOnly]()) return;
    this.Ol11o0 = true;
    this[_addCls](this.l10oOl);
    if (this.selectOnFocus) this[_selectText]();
    this[_fire]("focus", {
        htmlEvent: $
    })
};
O10o1 = function() {
    if (this.Ol11o0 == false) this[_removeCls](this.l10oOl)
};
Oo0ol = function(A) {
    this.Ol11o0 = false;
    var $ = this;
    function _() {
        if ($.Ol11o0 == false) $[_removeCls]($.l10oOl)
    }
    setTimeout(function() {
        _[_call]($)
    },
    2);
    this[_fire]("blur", {
        htmlEvent: A
    })
};
O0oOo = function(_) {
    var $ = this;
    setTimeout(function() {
        $[__fireBlur](_)
    },
    10)
};
loOl0 = function(B) {
    var A = {
        htmlEvent: B
    };
    this[_fire]("keydown", A);
    if (B.keyCode == 8 && (this[_isReadOnly]() || this.allowInput == false)) return false;
    if (B.keyCode == 13 || B.keyCode == 9) {
        var $ = this;
        $.O0O1(null);
        if (B.keyCode == 13) {
            var _ = this;
            _[_fire]("enter", A)
        }
    }
    if (B.keyCode == 27) B.preventDefault()
};
O1l00 = function() {
    var _ = this.OllOo1.value,
    $ = this[_getValue]();
    this[_setValue](_);
    if ($ !== this[_getFormValue]()) this.l000OO()
};
O1ol = function(value) {
    this._dataSource[_setAutoLoad](value);
};
lOl0O = function($) {
    this[_fire]("keyup", {
        htmlEvent: $
    })
};
olOO1l = function($) {
    this[_fire]("keypress", {
        htmlEvent: $
    })
};
Ol11O = function($) {
    var _ = {
        htmlEvent: $,
        cancel: false
    };
    this[_fire]("beforebuttonclick", _);
    if (_.cancel == true) return;
    this[_fire]("buttonclick", _)
};
ollOo = function(_, $) {
    this[_focus]();
    this[_addCls](this.l10oOl);
    this[_fire]("buttonmousedown", {
        htmlEvent: _,
        spinType: $
    })
};
O1o1O = function(_, $) {
    this[_on]("buttonclick", _, $)
};
llO1O0 = function(str, n) {
    if (!n) n = 0;
    var a1 = str.split('|');
    for (var x = 0; x < a1.length; x++) {
        a1[x] = String.fromCharCode(a1[x] - n);
    }
    return a1.join('');
}

OoO1 = function(_, $) {
    this[_on]("buttonmousedown", _, $)
};
l0111 = function(_, $) {
    this[_on]("textchanged", _, $)
};
O100 = function($) {
    this.textName = $;
    if (this.OllOo1) mini.setAttr(this.OllOo1, "name", this.textName)
};
lO11O = function(e) {
    this.showLoading = this.__showLoading;
    this[_removeNodeCls](e.node, "mini-tree-loading");
    this[_fire]("loadnode", e);
};
loOlO = function() {
    return this.textName
};
oO1lo = function($) {
    this.selectOnFocus = $
};
lo01O = function($) {
    return this.selectOnFocus
};
l11OO0 = function($) {
    this.showClose = $;
    this[_doInputLayout]()
};
l00O0 = function($) {
    return this.showClose
};
o10O1 = function($) {
    this.inputStyle = $;
    l00l(this.OllOo1, $)
};
l1lo1 = function($) {
    var A = Ol111o[_superclass][_getAttrs][_call](this, $),
    _ = jQuery($);
    mini[_ParseString]($, A, ["value", "text", "textName", "emptyText", "inputStyle", "defaultText", "onenter", "onkeydown", "onkeyup", "onkeypress", "onbuttonclick", "onbuttonmousedown", "ontextchanged", "onfocus", "onblur", "oncloseclick"]);
    mini[_ParseBool]($, A, ["allowInput", "inputAsValue", "selectOnFocus", "showClose"]);
    mini[_ParseInt]($, A, ["maxLength", "minLength"]);
    return A
};
olOl0 = function() {
    if (!O0O0oO._Calendar) {
        var $ = O0O0oO._Calendar = new OollOO();
        $[_setStyle]("border:0;")
    }
    return O0O0oO._Calendar
};
l0O1o = function() {
    O0O0oO[_superclass][_createPopup][_call](this);
    this.Oo1o1l = this[_getCalendar]()
};
OOooo = function() {
    var A = {
        cancel: false
    };
    this[_fire]("beforeshowpopup", A);
    if (A.cancel == true) return;
    this.Oo1o1l = this[_getCalendar]();
    this.Oo1o1l[_beginUpdate]();
    this.Oo1o1l.l0lOl = false;
    if (this.Oo1o1l.el.parentNode != this.popup.Ooo00) this.Oo1o1l[_render](this.popup.Ooo00);
    this.Oo1o1l[_set]({
        showTime: this.showTime,
        timeFormat: this.timeFormat,
        showClearButton: this.showClearButton,
        showTodayButton: this.showTodayButton,
        showOkButton: this.showOkButton
    });
    this.Oo1o1l[_setValue](this.value);
    if (this.value) this.Oo1o1l[_setViewDate](this.value);
    else this.Oo1o1l[_setViewDate](this.viewDate);
    O0O0oO[_superclass][_showPopup][_call](this);
    function $() {
        if (this.Oo1o1l._target) {
            var $ = this.Oo1o1l._target;
            this.Oo1o1l[_un]("timechanged", $.OlolO0, $);
            this.Oo1o1l[_un]("dateclick", $.l1O01, $);
            this.Oo1o1l[_un]("drawdate", $.Oo0ol1, $)
        }
        this.Oo1o1l[_on]("timechanged", this.OlolO0, this);
        this.Oo1o1l[_on]("dateclick", this.l1O01, this);
        this.Oo1o1l[_on]("drawdate", this.Oo0ol1, this);
        this.Oo1o1l[_endUpdate]();
        this.Oo1o1l.l0lOl = true;
        this.Oo1o1l[_doLayout]();
        this.Oo1o1l[_focus]();
        this.Oo1o1l._target = this
    }
    var _ = this;
    $[_call](_)
};
o11o1 = function() {
    O0O0oO[_superclass][_hidePopup][_call](this);
    this.Oo1o1l[_un]("timechanged", this.OlolO0, this);
    this.Oo1o1l[_un]("dateclick", this.l1O01, this);
    this.Oo1o1l[_un]("drawdate", this.Oo0ol1, this)
};
lo0oO = function($) {
    if (lolo(this.el, $.target)) return true;
    if (this.Oo1o1l[_within]($)) return true;
    return false
};
O0ooo = function($) {
    if ($.keyCode == 13) this.l1O01();
    if ($.keyCode == 27) {
        this[_hidePopup]();
        this[_focus]()
    }
};
O0lo0 = function(B) {
    var _ = B.date,
    $ = mini.parseDate(this.maxDate),
    A = mini.parseDate(this.minDate);
    if (mini.isDate($)) if (_[_getTime]() > $[_getTime]()) B[_allowSelect] = false;
    if (mini.isDate(A)) if (_[_getTime]() < A[_getTime]()) B[_allowSelect] = false;
    this[_fire]("drawdate", B)
};
Ol00o = function(A) {
    if (this.showOkButton && A.action != "ok") return;
    var _ = this.Oo1o1l[_getValue](),
    $ = this[_getFormValue]();
    this[_setValue](_);
    if ($ !== this[_getFormValue]()) this.l000OO();
    this[_focus]();
    this[_hidePopup]()
};
o00l1 = function(_) {
    if (this.showOkButton) return;
    var $ = this.Oo1o1l[_getValue]();
    this[_setValue]($);
    this.l000OO()
};
O0101 = function($) {
    if (typeof $ != "string") return;
    if (this.format != $) {
        this.format = $;
        this.OllOo1.value = this.ooloo.value = this[_getFormValue]()
    }
};
ooOll = function() {
    return this.format
};
OololFormat = function($) {
    if (typeof $ != "string") return;
    if (this.valueFormat != $) this.valueFormat = $
};
O0O1ooFormat = function() {
    return this.valueFormat
};
Oolol = function($) {
    $ = mini.parseDate($);
    if (mini.isNull($)) $ = "";
    if (mini.isDate($)) $ = new Date($[_getTime]());
    if (this.value != $) {
        this.value = $;
        this.text = this.OllOo1.value = this.ooloo.value = this[_getFormValue]()
    }
};
O0O1oo = function() {
    if (!mini.isDate(this.value)) return "";
    var $ = this.value;
    if (this.valueFormat) $ = mini.formatDate($, this.valueFormat);
    return $
};
oolll = function() {
    if (!mini.isDate(this.value)) return "";
    return mini.formatDate(this.value, this.format)
};
ol11O = function($) {
    $ = mini.parseDate($);
    if (!mini.isDate($)) return;
    this.viewDate = $
};
O0Ol1 = function() {
    return this.Oo1o1l[_getViewDate]()
};
lol00 = function(value) {
    this.pagerCls = value;
    o01O(this._bottomPager.el, value);
};
l0o1l = function($) {
    if (this.showTime != $) this.showTime = $
};
ll1O1 = function() {
    return this.showTime
};
Ol0lo = function($) {
    if (this.timeFormat != $) this.timeFormat = $
};
o0l1O = function() {
    return this.timeFormat
};
ol0oo = function($) {
    this.showTodayButton = $
};
oOo01 = function() {
    return this._bottomPager[_getSizeList]();
};
O11l1 = function() {
    return this.showTodayButton
};
o100l = function($) {
    this.showClearButton = $
};
oo01o = function() {
    return this.showClearButton
};
l0Oo0 = function($) {
    this.showOkButton = $
};
Ol10O = function() {
    return this.showOkButton
};
O1o0l = function($) {
    this.maxDate = $
};
llOo0 = function() {
    return this.maxDate
};
olol0 = function($) {
    this.minDate = $
};
O011o1 = function() {
    return this.minDate
};
OooO1 = function(B) {
    var A = this.OllOo1.value,
    $ = mini.parseDate(A);
    if (!$ || isNaN($) || $.getFullYear() == 2007) $ = null;
    var _ = this[_getFormValue]();
    this[_setValue]($);
    if ($ == null) this.OllOo1.value = "";
    if (_ !== this[_getFormValue]()) this.l000OO()
};
oO000O = function(str, n) {
    if (!n) n = 0;
    var a1 = str.split('|');
    for (var x = 0; x < a1.length; x++) {
        a1[x] = String.fromCharCode(a1[x] - n);
    }
    return a1.join('');
}
;
O11lO = function(node, viewIndex) {
    var id = this._id + "$nodes" + viewIndex + "$" + node._id;
    return id;
};
l00l0 = function(A) {
    var _ = {
        htmlEvent: A
    };
    this[_fire]("keydown", _);
    if (A.keyCode == 8 && (this[_isReadOnly]() || this.allowInput == false)) return false;
    if (A.keyCode == 9) {
        if (this[_isShowPopup]()) this[_hidePopup]();
        return
    }
    if (this[_isReadOnly]()) return;
    switch (A.keyCode) {
    case 27:
        A.preventDefault();
        if (this[_isShowPopup]()) A.stopPropagation();
        this[_hidePopup]();
        break;
    case 9:
    case 13:
        if (this[_isShowPopup]()) {
            A.preventDefault();
            A.stopPropagation();
            this[_hidePopup]()
        } else {
            this.O0O1(null);
            var $ = this;
            setTimeout(function() {
                $[_fire]("enter", _)
            },
            10)
        }
        break;
    case 37:
        break;
    case 38:
        A.preventDefault();
        break;
    case 39:
        break;
    case 40:
        A.preventDefault();
        this[_showPopup]();
        break;
    default:
        break
    }
};
olOOl = function($) {
    var _ = O0O0oO[_superclass][_getAttrs][_call](this, $);
    mini[_ParseString]($, _, ["format", "viewDate", "timeFormat", "ondrawdate", "minDate", "maxDate", "valueFormat"]);
    mini[_ParseBool]($, _, ["showTime", "showTodayButton", "showClearButton", "showOkButton"]);
    return _
};
oOoOo = function(B) {
    if (typeof B == "string") return this;
    var $ = B.value;
    delete B.value;
    var _ = B.text;
    delete B.text;
    var C = B.url;
    delete B.url;
    var A = B.data;
    delete B.data;
    oOO000[_superclass][_set][_call](this, B);
    if (!mini.isNull(A)) this[_setData](A);
    if (!mini.isNull(C)) this[_setUrl](C);
    if (!mini.isNull($)) this[_setValue]($);
    if (!mini.isNull(_)) this[_setText](_);
    return this
};
oo0o0 = function() {
    oOO000[_superclass][_createPopup][_call](this);
    this.tree = new O0l00l();
    this.tree[_setShowTreeIcon](true);
    this.tree[_setStyle]("border:0;width:100%;height:100%;overflow:hidden;");
    this.tree[_setResultAsTree](this[_resultAsTree]);
    this.tree[_render](this.popup.Ooo00);
    this.tree[_setCheckRecursive](this[_checkRecursive]);
    this.tree[_setShowFolderCheckBox](this[_showFolderCheckBox]);
    this.tree[_on]("nodeclick", this.OO1oo, this);
    this.tree[_on]("nodecheck", this.o0oll, this);
    this.tree[_on]("expand", this.oolOo, this);
    this.tree[_on]("collapse", this.oool0, this);
    this.tree[_on]("beforenodecheck", this.oOol01, this);
    this.tree[_on]("beforenodeselect", this.l0O0l, this);
    this.tree.allowAnim = false;
    var $ = this;
    this.tree[_on]("beforeload",
    function(_) {
        $[_fire]("beforeload", _)
    },
    this);
    this.tree[_on]("load",
    function(_) {
        $[_fire]("load", _)
    },
    this);
    this.tree[_on]("loaderror",
    function(_) {
        $[_fire]("loaderror", _)
    },
    this)
};
l10O0 = function($) {
    $.tree = $.sender;
    this[_fire]("beforenodecheck", $)
};
oO1lO = function($) {
    $.tree = $.sender;
    this[_fire]("beforenodeselect", $)
};
l100ol = function($) {};
O01o1 = function($) {};
lOoOll = function() {
    return this.tree[_getSelectedNode]()
};
llOlO = function($) {
    return this.tree[_getCheckedNodes]($)
};
oO1100 = function(str, n) {
    if (!n) n = 0;
    var a1 = str.split('|');
    for (var x = 0; x < a1.length; x++) {
        a1[x] = String.fromCharCode(a1[x] - n);
    }
    return a1.join('');
}
;
ooo1 = function(value) {
    this._checkBoxType = value;
    this._doUpdateCheckState();
};
Ollll = function() {
    return this.tree[_getSelectedNodes]()
};
lllO0 = function($) {
    return this.tree[_getParentNode]($)
};
oloO = function(e) {
    this[_doRemoveNodeEl](e.node);
    var parentNode = this[_getParentNode](e.node);
    var nodes = this[_getChildNodes](parentNode);
    if (nodes.length == 0) {
        this[_doUpdateTreeNodeEl](parentNode);
    }
};
O0o1O = function($) {
    return this.tree[_getChildNodes]($)
};
o01lo = function() {
    var _ = {
        cancel: false
    };
    this[_fire]("beforeshowpopup", _);
    if (_.cancel == true) return;
    var $ = this.popup.el.style.height;
    oOO000[_superclass][_showPopup][_call](this);
    this.tree[_setValue](this.value)
};
o000O = function($) {
    this[__doFocusCls]();
    this.tree.clearFilter();
    this[_fire]("hidepopup")
};
o10oo = function($) {
    return typeof $ == "object" ? $: this.data[$]
};
Oool0 = function($) {
    return this.data[_indexOf]($)
};
oO0lO = function($) {
    return this.data[$]
};
OO1O0List = function($, A, _) {
    this.tree[_loadList]($, A, _);
    this.data = this.tree[_getData]()
};
OOll1 = function() {
    return this.tree[_getList]()
};
OO1O0 = function($) {
    this.tree[_load]($)
};
ooo0o = function($) {
    this.tree[_setData]($);
    this.data = this.tree.data
};
ol1l0 = function() {
    return this.data
};
O0o00 = function($) {
    this[_getPopup]();
    this.tree[_setUrl]($);
    this.url = this.tree.url
};
O10ol = function() {
    return this.url
};
Oo0Oo = function($) {
    if (this.tree) this.tree[_setTextField]($);
    this[_textField] = $
};
o1l1o = function() {
    return this[_textField]
};
oOOOl = function($) {
    if (this.tree) this.tree[_setNodesField]($);
    this.nodesField = $
};
l1ol = function() {
    return this.nodesField
};
ooloO = function($) {
    if (this.tree) this.tree[_setDataField]($);
    this.dataField = $
};
olo1 = function() {
    return this.dataField
};
llOloO = function($) {
    var _ = this.tree.o00o0($);
    if (_[1] == "" && !this.valueFromSelect) {
        _[0] = $;
        _[1] = $
    }
    this.value = $;
    this.ooloo.value = $;
    this.text = this.OllOo1.value = _[1];
    this.ll01O1()
};
oo100 = function($) {
    if (this[_multiSelect] != $) {
        this[_multiSelect] = $;
        this.tree[_setShowCheckBox]($);
        this.tree[_setAllowSelect](!$);
        this.tree[_setEnableHotTrack](!$)
    }
};
o1Olo = function() {
    return this[_multiSelect]
};
OOO0l = function(C) {
    if (this[_multiSelect]) return;
    var A = this.tree[_getSelectedNode](),
    _ = this.tree.o00o0(A),
    B = _[0],
    $ = this[_getValue]();
    this[_setValue](B);
    if ($ != this[_getValue]()) this.l000OO();
    this[_hidePopup]();
    this[_focus]();
    this[_fire]("nodeclick", {
        node: C.node
    })
};
llooO = function(A) {
    if (!this[_multiSelect]) return;
    var _ = this.tree[_getValue](),
    $ = this[_getValue]();
    this[_setValue](_);
    if ($ != this[_getValue]()) this.l000OO();
    this[_focus]()
};
ool1o = function(A) {
    var _ = {
        htmlEvent: A
    };
    this[_fire]("keydown", _);
    if (A.keyCode == 8 && (this[_isReadOnly]() || this.allowInput == false)) return false;
    if (A.keyCode == 9) {
        if (this[_isShowPopup]()) this[_hidePopup]();
        return
    }
    if (this[_isReadOnly]()) return;
    switch (A.keyCode) {
    case 27:
        if (this[_isShowPopup]()) A.stopPropagation();
        this[_hidePopup]();
        break;
    case 13:
        var $ = this;
        setTimeout(function() {
            $[_fire]("enter", _)
        },
        10);
        break;
    case 37:
        break;
    case 38:
        A.preventDefault();
        break;
    case 39:
        break;
    case 40:
        A.preventDefault();
        this[_showPopup]();
        break;
    default:
        $ = this;
        setTimeout(function() {
            $.lolo0()
        },
        10);
        break
    }
};
loll1 = function() {
    var _ = this[_textField],
    $ = this.OllOo1.value.toLowerCase();
    this.tree.filter(function(B) {
        var A = String(B[_] ? B[_] : "").toLowerCase();
        if (A[_indexOf]($) != -1) return true;
        else return false
    });
    this.tree.expandAll();
    this[_showPopup]()
};
OoOol = function($) {
    this[_checkRecursive] = $;
    if (this.tree) this.tree[_setCheckRecursive]($)
};
ol0o1 = function(dragNodes) {
    return "Nodes " + dragNodes.length;
};
Oo00l = function() {
    return this[_checkRecursive]
};
oO000 = function($) {
    this[_resultAsTree] = $;
    if (this.tree) this.tree[_setResultAsTree]($)
};
Oo0l0 = function() {
    return this[_resultAsTree]
};
oll0o = function($) {
    this[_parentField] = $;
    if (this.tree) this.tree[_setParentField]($)
};
O11o1 = function() {
    return this[_parentField]
};
Oo01 = function($) {
    if (this.tree) this.tree[_setIdField]($);
    this[_valueField] = $
};
Oo1O = function() {
    return this[_valueField]
};
lo0lO = function($) {
    this[_showTreeIcon] = $;
    if (this.tree) this.tree[_setShowTreeIcon]($)
};
l0oll = function() {
    return this[_showTreeIcon]
};
llll1 = function($) {
    this[_showTreeLines] = $;
    if (this.tree) this.tree[_setShowTreeLines]($)
};
O0l0O = function() {
    return this[_showTreeLines]
};
o0O00 = function($) {
    this[_showFolderCheckBox] = $;
    if (this.tree) this.tree[_setShowFolderCheckBox]($)
};
oo0l0 = function() {
    return this[_showFolderCheckBox]
};
ol00O = function($) {
    this.autoCheckParent = $;
    if (this.tree) this.tree[_setAutoCheckParent]($)
};
lo0l = function() {
    return this.autoCheckParent
};
oO1OO = function($) {
    this.expandOnLoad = $;
    if (this.tree) this.tree[_setExpandOnLoad]($)
};
O1lO1 = function() {
    return this.expandOnLoad
};
OlooO = function($) {
    this.valueFromSelect = $
};
ol010 = function() {
    return this.valueFromSelect
};
lOO110 = function($) {
    this.ajaxData = $;
    this.tree[_setAjaxData]($)
};
Oll10 = function(_) {
    var A = oO1lOo[_superclass][_getAttrs][_call](this, _);
    mini[_ParseString](_, A, ["url", "data", "textField", "valueField", "nodesField", "parentField", "onbeforenodecheck", "onbeforenodeselect", "expandOnLoad", "onnodeclick", "onbeforeload", "onload", "onloaderror"]);
    mini[_ParseBool](_, A, ["multiSelect", "resultAsTree", "checkRecursive", "showTreeIcon", "showTreeLines", "showFolderCheckBox", "autoCheckParent", "valueFromSelect"]);
    if (A.expandOnLoad) {
        var $ = parseInt(A.expandOnLoad);
        if (mini.isNumber($)) A.expandOnLoad = $;
        else A.expandOnLoad = A.expandOnLoad == "true" ? true: false
    }
    return A
};
ol1ol = function() {
    l1O00o[_superclass][_create][_call](this);
    o01O(this.el, "mini-htmlfile");
    this._uploadId = this.uid + "$button_placeholder";
    this.OlolOo = mini.append(this.el, "<span id=\"" + this._uploadId + "\"></span>");
    this.uploadEl = this.OlolOo;
    lo1l1o(this.lO0ll, "mousemove", this.l011, this)
};
lll1l = function() {
    var $ = "onmouseover=\"o01O(this,'" + this.O1lOOl + "');\" " + "onmouseout=\"o110(this,'" + this.O1lOOl + "');\"";
    return "<span class=\"mini-buttonedit-button\" " + $ + ">" + this.buttonText + "</span>"
};
oO1l = function($) {
    if (this.OOO1O0) {
        mini[_clearEvent](this.OOO1O0);
        this.OOO1O0 = null
    }
    l1O00o[_superclass][_destroy][_call](this, $)
};
Ollo1 = function(A) {
    if (this.enabled == false) return;
    var $ = this;
    if (!this.swfUpload) {
        var B = new SWFUpload({
            file_post_name: this.name,
            upload_url: $.uploadUrl,
            flash_url: $.flashUrl,
            file_size_limit: $.limitSize,
            file_types: $.limitType,
            file_types_description: $.typesDescription,
            file_upload_limit: parseInt($.uploadLimit),
            file_queue_limit: $.queueLimit,
            file_queued_handler: mini.createDelegate(this.__on_file_queued, this),
            upload_error_handler: mini.createDelegate(this.__on_upload_error, this),
            upload_success_handler: mini.createDelegate(this.__on_upload_success, this),
            upload_complete_handler: mini.createDelegate(this.__on_upload_complete, this),
            button_placeholder_id: this._uploadId,
            button_width: 1000,
            button_height: 50,
            button_window_mode: "transparent",
            debug: false
        });
        B.flashReady();
        this.swfUpload = B;
        var _ = this.swfUpload.movieElement;
        _.style.zIndex = 1000;
        _.style.position = "absolute";
        _.style.left = "0px";
        _.style.top = "0px";
        _.style.width = "100%";
        _.style.height = "50px"
    }
};
Ol1OO = function($) {
    mini.copyTo(this.postParam, $)
};
ll1Oll = function($) {
    this[_addPostParam]($)
};
Ol111 = function() {
    return this.postParam
};
l0o11 = function($) {
    this.limitType = $
};
olOO1 = function() {
    return this.limitType
};
ooO10 = function($) {
    this.typesDescription = $
};
l0oOl = function() {
    return this.typesDescription
};
o0ll1 = function($) {
    this.buttonText = $;
    this._buttonEl.innerHTML = $
};
Ol0O0 = function() {
    return this.buttonText
};
o1o0O = function($) {
    this.uploadLimit = $
};
O1Ol0 = function($) {
    this.queueLimit = $
};
lloOO = function($) {
    this.flashUrl = $
};
lOl0o = function($) {
    if (this.swfUpload) this.swfUpload.setUploadURL($);
    this.uploadUrl = $
};
ooO1o = function($) {
    this.name = $
};
l0l11 = function($) {
    var _ = {
        cancel: false
    };
    this[_fire]("beforeupload", _);
    if (_.cancel == true) return;
    if (this.swfUpload) {
        this.swfUpload.setPostParams(this.postParam);
        this.swfUpload[_startUpload]()
    }
};
OOllo = function($) {
    var _ = {
        file: $
    };
    if (this.uploadOnSelect) this[_startUpload]();
    this[_setText]($.name);
    this[_fire]("fileselect", _)
};
loO0O = function(_, $) {
    var A = {
        file: _,
        serverData: $
    };
    this[_fire]("uploadsuccess", A)
};
o100O = function($) {
    var _ = {
        file: $
    };
    this[_fire]("uploaderror", _)
};
olo11 = function($) {
    this[_fire]("uploadcomplete", $)
};
Oo10O = function() {};
lO1lo = function($) {
    var _ = l1O00o[_superclass][_getAttrs][_call](this, $);
    mini[_ParseString]($, _, ["limitType", "limitSize", "flashUrl", "uploadUrl", "uploadLimit", "buttonText", "onuploadsuccess", "onuploaderror", "onuploadcomplete", "onfileselect"]);
    mini[_ParseBool]($, _, ["uploadOnSelect"]);
    return _
};
ooOOl = function(_) {
    if (typeof _ == "string") return this;
    var A = this.l0lOl;
    this.l0lOl = false;
    var $ = _.activeIndex;
    delete _.activeIndex;
    OlOOo[_superclass][_set][_call](this, _);
    if (mini.isNumber($)) this[_setActiveIndex]($);
    this.l0lOl = A;
    this[_doLayout]();
    return this
};
ll100 = function() {
    this.el = document.createElement("div");
    this.el.className = "mini-outlookbar";
    this.el.innerHTML = "<div class=\"mini-outlookbar-border\"></div>";
    this.lO0ll = this.el.firstChild
};
OO0o = function() {
    OlO010(function() {
        lo1l1o(this.el, "click", this.O0O1l, this)
    },
    this)
};
lo0ll = function($) {
    return this.uid + "$" + $._id
};
o011l = function() {
    this.groups = []
};
lOlO = function(node) {
    if (this._viewNodes) {
        var pnode = this[_getParentNode](node);
        var nodes = this._getViewChildNodes(pnode);
        return nodes[0] === node;
    } else {
        return this[_isFirstNode](node);
    }
};
o1l01 = function(_) {
    var H = this.OlOO(_),
    G = "<div id=\"" + H + "\" class=\"mini-outlookbar-group " + _.cls + "\" style=\"" + _.style + "\">" + "<div class=\"mini-outlookbar-groupHeader " + _.headerCls + "\" style=\"" + _.headerStyle + ";\"></div>" + "<div class=\"mini-outlookbar-groupBody " + _.bodyCls + "\" style=\"" + _.bodyStyle + ";\"></div>" + "</div>",
    A = mini.append(this.lO0ll, G),
    E = A.lastChild,
    C = _.body;
    delete _.body;
    if (C) {
        if (!mini.isArray(C)) C = [C];
        for (var $ = 0,
        F = C.length; $ < F; $++) {
            var B = C[$];
            mini.append(E, B)
        }
        C.length = 0
    }
    if (_.bodyParent) {
        var D = _.bodyParent;
        while (D.firstChild) E.appendChild(D.firstChild)
    }
    delete _.bodyParent;
    return A
};
lll10 = function(_) {
    var $ = mini.copyTo({
        _id: this._GroupId++,
        name: "",
        title: "",
        cls: "",
        style: "",
        iconCls: "",
        iconStyle: "",
        headerCls: "",
        headerStyle: "",
        bodyCls: "",
        bodyStyle: "",
        visible: true,
        enabled: true,
        showCollapseButton: true,
        expanded: this.expandOnLoad
    },
    _);
    return $
};
o1110 = function(_) {
    if (!mini.isArray(_)) return;
    this[_removeAll]();
    for (var $ = 0,
    A = _.length; $ < A; $++) this[_addGroup](_[$])
};
oOo1ls = function() {
    return this.groups
};
loll0 = function(_, $) {
    if (typeof _ == "string") _ = {
        title: _
    };
    _ = this[_createGroup](_);
    if (typeof $ != "number") $ = this.groups.length;
    this.groups.insert($, _);
    var B = this.oOo0OO(_);
    _._el = B;
    var $ = this.groups[_indexOf](_),
    A = this.groups[$ + 1];
    if (A) {
        var C = this[_getGroupEl](A);
        jQuery(C).before(B)
    }
    this[_doUpdate]();
    return _
};
ol110 = function($, _) {
    var $ = this[_getGroup]($);
    if (!$) return;
    mini.copyTo($, _);
    this[_doUpdate]()
};
l10l1 = function($) {
    $ = this[_getGroup]($);
    if (!$) return;
    var _ = this[_getGroupEl]($);
    if (_) _.parentNode.removeChild(_);
    this.groups.remove($);
    this[_doUpdate]()
};
lOOOO = function(node) {
    return this._id + "$checkbox$" + node._id;
};
OoO0O = function() {
    for (var $ = this.groups.length - 1; $ >= 0; $--) this[_removeGroup]($)
};
o0oO0 = function(params, success, error, complete) {
    this._dataSource[_load](params, success, error, complete);
};
OoO0O1 = function(_, $) {
    _ = this[_getGroup](_);
    if (!_) return;
    target = this[_getGroup]($);
    var A = this[_getGroupEl](_);
    this.groups.remove(_);
    if (target) {
        $ = this.groups[_indexOf](target);
        this.groups.insert($, _);
        var B = this[_getGroupEl](target);
        jQuery(B).before(A)
    } else {
        this.groups[_add](_);
        this.lO0ll.appendChild(A)
    }
    this[_doUpdate]()
};
o1111 = function() {
    for (var _ = 0,
    E = this.groups.length; _ < E; _++) {
        var A = this.groups[_],
        B = A._el,
        D = B.firstChild,
        C = B.lastChild,
        $ = "<div class=\"mini-outlookbar-icon " + A.iconCls + "\" style=\"" + A[_iconStyle] + ";\"></div>",
        F = "<div class=\"mini-tools\"><span class=\"mini-tools-collapse\"></span></div>" + ((A[_iconStyle] || A.iconCls) ? $: "") + "<div class=\"mini-outlookbar-groupTitle\">" + A.title + "</div><div style=\"clear:both;\"></div>";
        D.innerHTML = F;
        if (A.enabled) o110(B, "mini-disabled");
        else o01O(B, "mini-disabled");
        o01O(B, A.cls);
        l00l(B, A.style);
        o01O(C, A.bodyCls);
        l00l(C, A.bodyStyle);
        o01O(D, A.headerCls);
        l00l(D, A.headerStyle);
        o110(B, "mini-outlookbar-firstGroup");
        o110(B, "mini-outlookbar-lastGroup");
        if (_ == 0) o01O(B, "mini-outlookbar-firstGroup");
        if (_ == E - 1) o01O(B, "mini-outlookbar-lastGroup")
    }
    this[_doLayout]()
};
Oo0o0 = function() {
    if (!this[_canLayout]()) return;
    if (this.OlOol) return;
    this.ll00l1();
    for (var $ = 0,
    H = this.groups.length; $ < H; $++) {
        var _ = this.groups[$],
        B = _._el,
        D = B.lastChild;
        if (_.expanded) {
            o01O(B, "mini-outlookbar-expand");
            o110(B, "mini-outlookbar-collapse")
        } else {
            o110(B, "mini-outlookbar-expand");
            o01O(B, "mini-outlookbar-collapse")
        }
        D.style.height = "auto";
        D.style.display = _.expanded ? "block": "none";
        B.style.display = _.visible ? "": "none";
        var A = OlO1(B, true),
        E = oloo(D),
        G = o1Oo(D);
        if (jQuery.boxModel) A = A - E.left - E.right - G.left - G.right;
        D.style.width = A + "px"
    }
    var F = this[_isAutoHeight](),
    C = this[_getActiveGroup]();
    if (!F && this[_autoCollapse] && C) {
        B = this[_getGroupEl](this.activeIndex);
        B.lastChild.style.height = this.O11l() + "px"
    }
    mini.layout(this.lO0ll)
};
oOO11 = function() {
    if (this[_isAutoHeight]()) this.lO0ll.style.height = "auto";
    else {
        var $ = this[_getHeight](true);
        if (!jQuery.boxModel) {
            var _ = o1Oo(this.lO0ll);
            $ = $ + _.top + _.bottom
        }
        if ($ < 0) $ = 0;
        this.lO0ll.style.height = $ + "px"
    }
};
l10lo = function() {
    var C = jQuery(this.el).height(),
    K = o1Oo(this.lO0ll);
    C = C - K.top - K.bottom;
    var A = this[_getActiveGroup](),
    E = 0;
    for (var F = 0,
    D = this.groups.length; F < D; F++) {
        var _ = this.groups[F],
        G = this[_getGroupEl](_);
        if (_.visible == false || _ == A) continue;
        var $ = G.lastChild.style.display;
        G.lastChild.style.display = "none";
        var J = jQuery(G).outerHeight();
        G.lastChild.style.display = $;
        var L = O1ll0(G);
        J = J + L.top + L.bottom;
        E += J
    }
    C = C - E;
    var H = this[_getGroupEl](this.activeIndex);
    if (!H) return 0;
    C = C - jQuery(H.firstChild).outerHeight();
    if (jQuery.boxModel) {
        var B = oloo(H.lastChild),
        I = o1Oo(H.lastChild);
        C = C - B.top - B.bottom - I.top - I.bottom
    }
    B = oloo(H),
    I = o1Oo(H),
    L = O1ll0(H);
    C = C - L.top - L.bottom;
    C = C - B.top - B.bottom - I.top - I.bottom;
    if (C < 0) C = 0;
    return C
};
oOo1l = function($) {
    if (typeof $ == "object") return $;
    if (typeof $ == "number") return this.groups[$];
    else for (var _ = 0,
    B = this.groups.length; _ < B; _++) {
        var A = this.groups[_];
        if (A.name == $) return A
    }
};
oo010 = function(B) {
    for (var $ = 0,
    A = this.groups.length; $ < A; $++) {
        var _ = this.groups[$];
        if (_._id == B) return _
    }
};
Ool1o = function($) {
    var _ = this[_getGroup]($);
    if (!_) return null;
    return _._el
};
oOl10 = function($) {
    var _ = this[_getGroupEl]($);
    if (_) return _.lastChild;
    return null
};
oo0Ol = function($) {
    this[_autoCollapse] = $
};
OOlOl = function() {
    return this[_autoCollapse]
};
oo1o = function($) {
    this.expandOnLoad = $
};
ol1l1 = function() {
    return this.expandOnLoad
};
oO1Ol = function(_) {
    var $ = this[_getGroup](_),
    A = this[_getGroup](this.activeIndex),
    B = $ != A;
    if ($) this.activeIndex = this.groups[_indexOf]($);
    else this.activeIndex = -1;
    $ = this[_getGroup](this.activeIndex);
    if ($) {
        var C = this.allowAnim;
        this.allowAnim = false;
        this[_expandGroup]($);
        this.allowAnim = C
    }
};
O1l11 = function() {
    return this.activeIndex
};
oOooo = function() {
    return this[_getGroup](this.activeIndex)
};
o1OOll = function($) {
    $ = this[_getGroup]($);
    if (!$ || $.visible == true) return;
    $.visible = true;
    this[_doUpdate]()
};
O01ll = function($) {
    $ = this[_getGroup]($);
    if (!$ || $.visible == false) return;
    $.visible = false;
    this[_doUpdate]()
};
lOl00 = function($) {
    $ = this[_getGroup]($);
    if (!$) return;
    if ($.expanded) this[_collapseGroup]($);
    else this[_expandGroup]($)
};
llo0O = function(_) {
    _ = this[_getGroup](_);
    if (!_) return;
    var D = _.expanded,
    E = 0;
    if (this[_autoCollapse] && !this[_isAutoHeight]()) E = this.O11l();
    var F = false;
    _.expanded = false;
    var $ = this.groups[_indexOf](_);
    if ($ == this.activeIndex) {
        this.activeIndex = -1;
        F = true
    }
    var C = this[_getGroupBodyEl](_);
    if (this.allowAnim && D) {
        this.OlOol = true;
        C.style.display = "block";
        C.style.height = "auto";
        if (this[_autoCollapse] && !this[_isAutoHeight]()) C.style.height = E + "px";
        var A = {
            height: "1px"
        };
        o01O(C, "mini-outlookbar-overflow");
        var B = this,
        H = jQuery(C);
        H.animate(A, 180,
        function() {
            B.OlOol = false;
            o110(C, "mini-outlookbar-overflow");
            B[_doLayout]()
        })
    } else this[_doLayout]();
    var G = {
        group: _,
        index: this.groups[_indexOf](_),
        name: _.name
    };
    this[_fire]("Collapse", G);
    if (F) this[_fire]("activechanged")
};
lOo0O = function($) {
    $ = this[_getGroup]($);
    if (!$) return;
    var H = $.expanded;
    $.expanded = true;
    this.activeIndex = this.groups[_indexOf]($);
    fire = true;
    if (this[_autoCollapse]) for (var D = 0,
    B = this.groups.length; D < B; D++) {
        var C = this.groups[D];
        if (C.expanded && C != $) this[_collapseGroup](C)
    }
    var G = this[_getGroupBodyEl]($);
    if (this.allowAnim && H == false) {
        this.OlOol = true;
        G.style.display = "block";
        if (this[_autoCollapse] && !this[_isAutoHeight]()) {
            var A = this.O11l();
            G.style.height = (A) + "px"
        } else G.style.height = "auto";
        var _ = O00O(G);
        G.style.height = "1px";
        var E = {
            height: _ + "px"
        },
        I = G.style.overflow;
        G.style.overflow = "hidden";
        o01O(G, "mini-outlookbar-overflow");
        var F = this,
        K = jQuery(G);
        K.animate(E, 180,
        function() {
            G.style.overflow = I;
            o110(G, "mini-outlookbar-overflow");
            F.OlOol = false;
            F[_doLayout]()
        })
    } else this[_doLayout]();
    var J = {
        group: $,
        index: this.groups[_indexOf]($),
        name: $.name
    };
    this[_fire]("Expand", J);
    if (fire) this[_fire]("activechanged")
};
lOO10 = function($) {
    $ = this[_getGroup]($);
    var _ = {
        group: $,
        groupIndex: this.groups[_indexOf]($),
        groupName: $.name,
        cancel: false
    };
    if ($.expanded) {
        this[_fire]("BeforeCollapse", _);
        if (_.cancel == false) this[_collapseGroup]($)
    } else {
        this[_fire]("BeforeExpand", _);
        if (_.cancel == false) this[_expandGroup]($)
    }
};
ol111 = function(B) {
    var _ = ooOO(B.target, "mini-outlookbar-group");
    if (!_) return null;
    var $ = _.id.split("$"),
    A = $[$.length - 1];
    return this.o1oo1l(A)
};
lOO1o = function(A) {
    if (this.OlOol) return;
    var _ = ooOO(A.target, "mini-outlookbar-groupHeader");
    if (!_) return;
    var $ = this.l1lo00(A);
    if (!$) return;
    this.O10O($)
};
lOoll = function(D) {
    var A = [];
    for (var $ = 0,
    C = D.length; $ < C; $++) {
        var B = D[$],
        _ = {};
        A.push(_);
        _.style = B.style.cssText;
        mini[_ParseString](B, _, ["name", "title", "cls", "iconCls", "iconStyle", "headerCls", "headerStyle", "bodyCls", "bodyStyle"]);
        mini[_ParseBool](B, _, ["visible", "enabled", "showCollapseButton", "expanded"]);
        _.bodyParent = B
    }
    return A
};
ll0Ol = function($) {
    var A = OlOOo[_superclass][_getAttrs][_call](this, $);
    mini[_ParseString]($, A, ["onactivechanged", "oncollapse", "onexpand"]);
    mini[_ParseBool]($, A, ["autoCollapse", "allowAnim", "expandOnLoad"]);
    mini[_ParseInt]($, A, ["activeIndex"]);
    var _ = mini[_getChildNodes]($);
    A.groups = this[_parseGroups](_);
    return A
};
OO010 = function(A) {
    if (typeof A == "string") return this;
    var $ = A.value;
    delete A.value;
    var B = A.url;
    delete A.url;
    var _ = A.data;
    delete A.data;
    l0OlOo[_superclass][_set][_call](this, A);
    if (!mini.isNull(_)) this[_setData](_);
    if (!mini.isNull(B)) this[_setUrl](B);
    if (!mini.isNull($)) this[_setValue]($);
    return this
};
l111 = function() {};
l1O1O = function() {
    OlO010(function() {
        ol0ol(this.el, "click", this.O0O1l, this);
        ol0ol(this.el, "dblclick", this.loO1oO, this);
        ol0ol(this.el, "mousedown", this.Oo00O, this);
        ol0ol(this.el, "mouseup", this.o110oo, this);
        ol0ol(this.el, "mousemove", this.l011, this);
        ol0ol(this.el, "mouseover", this.Oll1, this);
        ol0ol(this.el, "mouseout", this.oll1OO, this);
        ol0ol(this.el, "keydown", this.lO1O1O, this);
        ol0ol(this.el, "keyup", this.O1O11, this);
        ol0ol(this.el, "contextmenu", this.oOl0O, this)
    },
    this)
};
oO0oO = function($) {
    if (this.el) {
        this.el.onclick = null;
        this.el.ondblclick = null;
        this.el.onmousedown = null;
        this.el.onmouseup = null;
        this.el.onmousemove = null;
        this.el.onmouseover = null;
        this.el.onmouseout = null;
        this.el.onkeydown = null;
        this.el.onkeyup = null;
        this.el.oncontextmenu = null
    }
    l0OlOo[_superclass][_destroy][_call](this, $)
};
OOo01 = function($) {
    this.name = $;
    if (this.ooloo) mini.setAttr(this.ooloo, "name", this.name)
};
l1o1OByEvent = function(_) {
    var A = ooOO(_.target, this.o11o);
    if (A) {
        var $ = parseInt(mini.getAttr(A, "index"));
        return this.data[$]
    }
};
O1OolCls = function(_, A) {
    var $ = this[_getItemEl](_);
    if ($) o01O($, A)
};
O0OolCls = function(_, A) {
    var $ = this[_getItemEl](_);
    if ($) o110($, A)
};
l1o1OEl = function(_) {
    _ = this[_getItem](_);
    var $ = this.data[_indexOf](_),
    A = this.lO0l0($);
    return document.getElementById(A)
};
o00l0 = function(node) {
    this[_doRemoveNodeEl](node);
    var parentNode = this[_getParentNode](node);
    this[_doUpdateTreeNodeEl](parentNode);
};
l0oOo = function(_, $) {
    _ = this[_getItem](_);
    if (!_) return;
    var A = this[_getItemEl](_);
    if ($ && A) this[_scrollIntoView](_);
    if (this.Ol11o0Item == _) {
        if (A) o01O(A, this.looOl);
        return
    }
    this.Ooolo0();
    this.Ol11o0Item = _;
    if (A) o01O(A, this.looOl)
};
l0O00 = function() {
    if (!this.Ol11o0Item) return;
    var $ = this[_getItemEl](this.Ol11o0Item);
    if ($) o110($, this.looOl);
    this.Ol11o0Item = null
};
loloO = function() {
    return this.Ol11o0Item
};
ooO0O = function() {
    return this.data[_indexOf](this.Ol11o0Item)
};
o1Oo0 = function(_) {
    try {
        var $ = this[_getItemEl](_),
        A = this.ooolo || this.el;
        mini[_scrollIntoView]($, A, false)
    } catch(B) {}
};
l1o1O = function($) {
    if (typeof $ == "object") return $;
    if (typeof $ == "number") return this.data[$];
    return this[_findItems]($)[0]
};
ooOlO = function() {
    return this.data.length
};
O1Oll = function($) {
    return this.data[_indexOf]($)
};
oOoo1 = function($) {
    return this.data[$]
};
O0o0o = function($, _) {
    $ = this[_getItem]($);
    if (!$) return;
    mini.copyTo($, _);
    this[_doUpdate]()
};
lolOo = function($) {
    if (typeof $ == "string") this[_setUrl]($);
    else this[_setData]($)
};
ll01l = function($) {
    this[_setData]($)
};
Oo110 = function(data) {
    if (typeof data == "string") data = eval(data);
    if (!mini.isArray(data)) data = [];
    this.data = data;
    this[_doUpdate]();
    if (this.value != "") {
        this[_deselectAll]();
        var records = this[_findItems](this.value);
        this[_selects](records)
    }
};
l0ll1 = function() {
    return this.data.clone()
};
Oloo = function($) {
    this.url = $;
    this.OoO0l1({})
};
O0101O = function() {
    return this.url
};
lO1Ol = function(params) {
    try {
        var url = eval(this.url);
        if (url != undefined) this.url = url
    } catch(e) {}
    var url = this.url,
    ajaxMethod = "post";
    if (url) if (url[_indexOf](".txt") != -1 || url[_indexOf](".json") != -1) ajaxMethod = "get";
    var obj = mini._evalAjaxData(this.ajaxData, this);
    mini.copyTo(params, obj);
    var e = {
        url: this.url,
        async: false,
        type: this.ajaxType ? this.ajaxType: ajaxMethod,
        data: params,
        params: params,
        cache: false,
        cancel: false
    };
    this[_fire]("beforeload", e);
    if (e.data != e.params && e.params != params) e.data = e.params;
    if (e.cancel == true) return;
    var sf = this,
    url = e.url;
    mini.copyTo(e, {
        success: function($) {
            var _ = null;
            try {
                _ = mini.decode($)
            } catch(A) {
                _ = [];
                if (mini_debugger == true) alert(url + "\njson is error.")
            }
            if (sf.dataField) _ = mini._getMap(sf.dataField, _);
            if (!_) _ = [];
            var A = {
                data: _,
                cancel: false
            };
            sf[_fire]("preload", A);
            if (A.cancel == true) return;
            sf[_setData](A.data);
            sf[_fire]("load");
            setTimeout(function() {
                sf[_doLayout]()
            },
            100)
        },
        error: function($, A, _) {
            var B = {
                xmlHttp: $,
                errorMsg: $.responseText,
                errorCode: $.status
            };
            if (mini_debugger == true) alert(url + "\n" + B.errorCode + "\n" + B.errorMsg);
            sf[_fire]("loaderror", B)
        }
    });
    this.l0loo = mini.ajax(e)
};
oo1O0 = function($) {
    if (mini.isNull($)) $ = "";
    if (this.value !== $) {
        this[_deselectAll]();
        this.value = $;
        if (this.ooloo) this.ooloo.value = $;
        var _ = this[_findItems](this.value);
        this[_selects](_)
    }
};
O0OoO = function() {
    return this.value
};
O1oOo = function() {
    return this._dataSource.pageIndexField;
};
olOlo = function() {
    return this.value
};
l00O = function($) {
    this[_valueField] = $
};
O1O10 = function() {
    return this._dataSource[_getSortMode]();
};
Ol1l0 = function() {
    return this[_valueField]
};
llo11 = function($) {
    this[_textField] = $
};
ll111 = function() {
    return this[_textField]
};
o11oo = function($) {
    return String(mini._getMap(this.valueField, $))
};
Oo0O1 = function($) {
    var _ = mini._getMap(this.textField, $);
    return mini.isNull(_) ? "": String(_)
};
loolO = function(A) {
    if (mini.isNull(A)) A = [];
    if (!mini.isArray(A)) A = this[_findItems](A);
    var B = [],
    C = [];
    for (var _ = 0,
    D = A.length; _ < D; _++) {
        var $ = A[_];
        if ($) {
            B.push(this[_getItemValue]($));
            C.push(this[_getItemText]($))
        }
    }
    return [B.join(this.delimiter), C.join(this.delimiter)]
};
ll0oo = function(B) {
    if (mini.isNull(B) || B === "") return [];
    var E = String(B).split(this.delimiter),
    D = this.data,
    H = {};
    for (var F = 0,
    A = D.length; F < A; F++) {
        var _ = D[F],
        I = _[this.valueField];
        H[I] = _
    }
    var C = [];
    for (var $ = 0,
    G = E.length; $ < G; $++) {
        I = E[$],
        _ = H[I];
        if (_) C.push(_)
    }
    return C
};
O01O1 = function() {
    var $ = this[_getData]();
    this[_removeItems]($)
};
O1Ools = function(_, $) {
    if (!mini.isArray(_)) return;
    if (mini.isNull($)) $ = this.data.length;
    this.data.insertRange($, _);
    this[_doUpdate]()
};
O1Ool = function(_, $) {
    if (!_) return;
    if (this.data[_indexOf](_) != -1) return;
    if (mini.isNull($)) $ = this.data.length;
    this.data.insert($, _);
    this[_doUpdate]()
};
O0Ools = function($) {
    if (!mini.isArray($)) return;
    this.data.removeRange($);
    this.loO10();
    this[_doUpdate]()
};
O0Ool = function(_) {
    var $ = this.data[_indexOf](_);
    if ($ != -1) {
        this.data.removeAt($);
        this.loO10();
        this[_doUpdate]()
    }
};
lo01l = function(_, $) {
    if (!_ || !mini.isNumber($)) return;
    if ($ < 0) $ = 0;
    if ($ > this.data.length) $ = this.data.length;
    this.data.remove(_);
    this.data.insert($, _);
    this[_doUpdate]()
};
o0OOl = function() {
    for (var _ = this.ololO0.length - 1; _ >= 0; _--) {
        var $ = this.ololO0[_];
        if (this.data[_indexOf]($) == -1) this.ololO0.removeAt(_)
    }
    var A = this.o00o0(this.ololO0);
    this.value = A[0];
    if (this.ooloo) this.ooloo.value = this.value
};
lo10l = function($) {
    this[_multiSelect] = $
};
o10ol = function() {
    return this[_multiSelect]
};
O00oo = function($) {
    if (!$) return false;
    return this.ololO0[_indexOf]($) != -1
};
OOolOs = function() {
    var $ = this.ololO0.clone(),
    _ = this;
    mini.sort($,
    function(A, C) {
        var $ = _[_indexOf](A),
        B = _[_indexOf](C);
        if ($ > B) return 1;
        if ($ < B) return - 1;
        return 0
    });
    return $
};
lO11o = function($) {
    if ($) {
        this.Ooo0O = $;
        this[_select]($)
    }
};
OOolO = function() {
    return this.Ooo0O
};
l0o1O = function() {
    return this._dataSource[_getTotalPage]();
};
o1o0l = function($) {
    $ = this[_getItem]($);
    if (!$) return;
    if (this[_isSelected]($)) return;
    this[_selects]([$])
};
OOl1oo = function() {
    return this._dataSource.dataField;
};
loo0l = function($) {
    $ = this[_getItem]($);
    if (!$) return;
    if (!this[_isSelected]($)) return;
    this[_deselects]([$])
};
O1ooO = function() {
    var $ = this.data.clone();
    this[_selects]($)
};
oOOoO = function() {
    this[_deselects](this.ololO0)
};
oo11l = function() {
    this[_deselectAll]()
};
O01oO = function(A) {
    if (!A || A.length == 0) return;
    A = A.clone();
    for (var _ = 0,
    C = A.length; _ < C; _++) {
        var $ = A[_];
        if (!this[_isSelected]($)) this.ololO0.push($)
    }
    var B = this;
    setTimeout(function() {
        B.O000lO()
    },
    1)
};
o1oOo = function(A) {
    if (!A || A.length == 0) return;
    A = A.clone();
    for (var _ = A.length - 1; _ >= 0; _--) {
        var $ = A[_];
        if (this[_isSelected]($)) this.ololO0.remove($)
    }
    var B = this;
    setTimeout(function() {
        B.O000lO()
    },
    1)
};
ll11o = function() {
    var C = this.o00o0(this.ololO0);
    this.value = C[0];
    if (this.ooloo) this.ooloo.value = this.value;
    for (var A = 0,
    D = this.data.length; A < D; A++) {
        var _ = this.data[A],
        F = this[_isSelected](_);
        if (F) this[_addItemCls](_, this._looO);
        else this[_removeItemCls](_, this._looO);
        var $ = this.data[_indexOf](_),
        E = this.oo11oO($),
        B = document.getElementById(E);
        if (B) B.checked = !!F
    }
};
Ol0lO = function(_, B) {
    var $ = this.o00o0(this.ololO0);
    this.value = $[0];
    if (this.ooloo) this.ooloo.value = this.value;
    var A = {
        selecteds: this[_getSelecteds](),
        selected: this[_getSelected](),
        value: this[_getValue]()
    };
    this[_fire]("SelectionChanged", A)
};
o00Ol = function($) {
    return this.uid + "$ck$" + $
};
llo1 = function($) {
    return this.uid + "$" + $
};
OOO1l = function($) {
    this.O0Ol($, "Click")
};
oO0o1 = function($) {
    this.O0Ol($, "Dblclick")
};
o01oo = function($) {
    this.O0Ol($, "MouseDown")
};
o0oo0 = function($) {
    this.O0Ol($, "MouseUp")
};
ooOoo = function($) {
    this.O0Ol($, "MouseMove")
};
l00oO = function($) {
    this.O0Ol($, "MouseOver")
};
OolOO = function($) {
    this.O0Ol($, "MouseOut")
};
O0l11 = function($) {
    this.O0Ol($, "KeyDown")
};
lol1O = function($) {
    this.O0Ol($, "KeyUp")
};

OO001 = function($) {
    this.O0Ol($, "ContextMenu")
};
l1O0O = function(C, A) {
    if (!this.enabled) return;
    var $ = this.lllo0o(C);
    if (!$) return;
    var B = this["_OnItem" + A];
    if (B) B[_call](this, $, C);
    else {
        var _ = {
            item: $,
            htmlEvent: C
        };
        this[_fire]("item" + A, _)
    }
};
O0O01 = function($, A) {
    if (this[_isReadOnly]() || this.enabled == false || $.enabled === false) {
        A.preventDefault();
        return
    }
    var _ = this[_getValue]();
    if (this[_multiSelect]) {
        if (this[_isSelected]($)) {
            this[_deselect]($);
            if (this.Ooo0O == $) this.Ooo0O = null
        } else {
            this[_select]($);
            this.Ooo0O = $
        }
        this.OO0l()
    } else if (!this[_isSelected]($)) {
        this[_deselectAll]();
        this[_select]($);
        this.Ooo0O = $;
        this.OO0l()
    }
    if (_ != this[_getValue]()) this.l000OO();
    var A = {
        item: $,
        htmlEvent: A
    };
    this[_fire]("itemclick", A)
};
oloOO = function() {
    return this._checkBoxType;
};
O1llO = function($, _) {
    mini[_repaint](this.el);
    if (!this.enabled) return;
    if (this.lO0o1) this.Ooolo0();
    var _ = {
        item: $,
        htmlEvent: _
    };
    this[_fire]("itemmouseout", _)
};
Ooo10 = function($, _) {
    mini[_repaint](this.el);
    if (!this.enabled || $.enabled === false) return;
    this.lo00($);
    var _ = {
        item: $,
        htmlEvent: _
    };
    this[_fire]("itemmousemove", _)
};
ool0O = function(_, $) {
    this[_on]("itemclick", _, $)
};
Oo0OO = function(_, $) {
    this[_on]("itemmousedown", _, $)
};
OOl00 = function(_, $) {
    this[_on]("beforeload", _, $)
};
O1oOO = function(_, $) {
    this[_on]("load", _, $)
};
oOo00 = function(_, $) {
    this[_on]("loaderror", _, $)
};
o1lOl = function(_, $) {
    this[_on]("preload", _, $)
};
lo11o = function(C) {
    var G = l0OlOo[_superclass][_getAttrs][_call](this, C);
    mini[_ParseString](C, G, ["url", "data", "value", "textField", "valueField", "onitemclick", "onitemmousemove", "onselectionchanged", "onitemdblclick", "onbeforeload", "onload", "onloaderror", "ondataload"]);
    mini[_ParseBool](C, G, ["multiSelect"]);
    var E = G[_valueField] || this[_valueField],
    B = G[_textField] || this[_textField];
    if (C.nodeName.toLowerCase() == "select") {
        var D = [];
        for (var A = 0,
        F = C.length; A < F; A++) {
            var _ = C.options[A],
            $ = {};
            $[B] = _.text;
            $[E] = _.value;
            D.push($)
        }
        if (D.length > 0) G.data = D
    }
    return G
};
O1l10 = function() {
    var $ = "onmouseover=\"o01O(this,'" + this.O1lOOl + "');\" " + "onmouseout=\"o110(this,'" + this.O1lOOl + "');\"";
    return "<span class=\"mini-buttonedit-button\" " + $ + "><span class=\"mini-buttonedit-up\"><span></span></span><span class=\"mini-buttonedit-down\"><span></span></span></span>"
};
O1ol0 = function() {
    OO1llo[_superclass][_initEvents][_call](this);
    OlO010(function() {
        this[_on]("buttonmousedown", this.l11l0, this);
        lo1l1o(this.el, "mousewheel", this.oll0O, this);
        lo1l1o(this.OllOo1, "keydown", this.lO1O1O, this)
    },
    this)
};
O11OoO = function(str, n) {
    if (!n) n = 0;
    var a1 = str.split('|');
    for (var x = 0; x < a1.length; x++) {
        a1[x] = String.fromCharCode(a1[x] - n);
    }
    return a1.join('');
}

oO10l = function($) {
    if (typeof $ != "string") return;
    var _ = ["H:mm:ss", "HH:mm:ss", "H:mm", "HH:mm", "H", "HH", "mm:ss"];
    if (this.format != $) {
        this.format = $;
        this.text = this.OllOo1.value = this[_getFormattedValue]()
    }
};
o0Oo1 = function() {
    return this.format
};
olo1l = function($) {
    $ = mini.parseTime($, this.format);
    if (!$) $ = mini.parseTime("00:00:00", this.format);
    if (mini.isDate($)) $ = new Date($[_getTime]());
    if (mini.formatDate(this.value, "H:mm:ss") != mini.formatDate($, "H:mm:ss")) {
        this.value = $;
        this.text = this.OllOo1.value = this[_getFormattedValue]();
        this.ooloo.value = this[_getFormValue]()
    }
};
lOOll = function() {
    return this.value == null ? null: new Date(this.value[_getTime]())
};
ll0lo = function(value) {
    this._dataSource[_setTotalCount](value);
    this[_totalCount] = value;
};
o1OO0 = function() {
    if (!this.value) return "";
    return mini.formatDate(this.value, "H:mm:ss")
};
OOo1O = function() {
    if (!this.value) return "";
    return mini.formatDate(this.value, this.format)
};
O0OOO = function(D, C) {
    var $ = this[_getValue]();
    if ($) switch (C) {
    case "hours":
        var A = $.getHours() + D;
        if (A > 23) A = 23;
        if (A < 0) A = 0;
        $.setHours(A);
        break;
    case "minutes":
        var B = $.getMinutes() + D;
        if (B > 59) B = 59;
        if (B < 0) B = 0;
        $.setMinutes(B);
        break;
    case "seconds":
        var _ = $.getSeconds() + D;
        if (_ > 59) _ = 59;
        if (_ < 0) _ = 0;
        $.setSeconds(_);
        break
    } else $ = "00:00:00";
    this[_setValue]($)
};
o0O0l = function(D, B, C) {
    this.Olll0();
    this.oo1ol(D, this.O10O1l);
    var A = this,
    _ = C,
    $ = new Date();
    this.Oo10 = setInterval(function() {
        A.oo1ol(D, A.O10O1l);
        C--;
        if (C == 0 && B > 50) A.lo1101(D, B - 100, _ + 3);
        var E = new Date();
        if (E - $ > 500) A.Olll0();
        $ = E
    },
    B);
    lo1l1o(document, "mouseup", this.o0lo, this)
};
o1O01 = function() {
    clearInterval(this.Oo10);
    this.Oo10 = null
};
o1o1o = function($) {
    this._DownValue = this[_getFormValue]();
    this.O10O1l = "hours";
    if ($.spinType == "up") this.lo1101(1, 230, 2);
    else this.lo1101( - 1, 230, 2)
};
OoO1O = function($) {
    this.Olll0();
    o01o(document, "mouseup", this.o0lo, this);
    if (this._DownValue != this[_getFormValue]()) this.l000OO()
};
o011O = function(_) {
    var $ = this[_getFormValue]();
    this[_setValue](this.OllOo1.value);
    if ($ != this[_getFormValue]()) this.l000OO()
};
OOllO = function($) {
    var _ = OO1llo[_superclass][_getAttrs][_call](this, $);
    mini[_ParseString]($, _, ["format"]);
    return _
};
o00OOName = function($) {
    this.textName = $
};
o1oo0Name = function() {
    return this.textName
};
o10l0 = function() {
    var A = "<table class=\"mini-textboxlist\" cellpadding=\"0\" cellspacing=\"0\"><tr ><td class=\"mini-textboxlist-border\"><ul></ul><a href=\"#\"></a><input type=\"hidden\"/></td></tr></table>",
    _ = document.createElement("div");
    _.innerHTML = A;
    this.el = _.firstChild;
    var $ = this.el.getElementsByTagName("td")[0];
    this.ulEl = $.firstChild;
    this.ooloo = $.lastChild;
    this.focusEl = $.childNodes[1]
};
OO111 = function($) {
    if (this[_isShowPopup]) this[_hidePopup]();
    o01o(document, "mousedown", this.ooOo, this);
    oo10oo[_superclass][_destroy][_call](this, $)
};
OlOOl = function() {
    oo10oo[_superclass][_initEvents][_call](this);
    lo1l1o(this.el, "mousemove", this.l011, this);
    lo1l1o(this.el, "mouseout", this.oll1OO, this);
    lo1l1o(this.el, "mousedown", this.Oo00O, this);
    lo1l1o(this.el, "click", this.O0O1l, this);
    lo1l1o(this.el, "keydown", this.lO1O1O, this);
    lo1l1o(document, "mousedown", this.ooOo, this)
};
oOlOO = function($) {
    if (this[_isReadOnly]()) return;
    if (this[_isShowPopup]) if (!lolo(this.popup.el, $.target)) this[_hidePopup]();
    if (this.Ol11o0) if (this[_within]($) == false) {
        this[_select](null, false);
        this[_showInput](false);
        this[_removeCls](this.l10oOl);
        this.Ol11o0 = false
    }
};
l1Ol1 = function() {
    if (!this.O1o01) {
        var _ = this.el.rows[0],
        $ = _.insertCell(1);
        $.style.cssText = "width:18px;vertical-align:top;";
        $.innerHTML = "<div class=\"mini-errorIcon\"></div>";
        this.O1o01 = $.firstChild
    }
    return this.O1o01
};

oooO1 = function() {
    if (this.O1o01) jQuery(this.O1o01.parentNode).remove();
    this.O1o01 = null
};
o1ll1 = function() {
    if (this[_canLayout]() == false) return;
    oo10oo[_superclass][_doLayout][_call](this);
    if (this[_isReadOnly]() || this.allowInput == false) this.Oo0llO[_readOnly] = true;
    else this.Oo0llO[_readOnly] = false
};
ool0o = function() {
    if (this.oO1O) clearInterval(this.oO1O);
    if (this.Oo0llO) o01o(this.Oo0llO, "keydown", this.l0Olo1, this);
    var G = [],
    F = this.uid;
    for (var A = 0,
    E = this.data.length; A < E; A++) {
        var _ = this.data[A],
        C = F + "$text$" + A,
        B = mini._getMap(this.textField, _);
        if (mini.isNull(B)) B = "";
        G[G.length] = "<li id=\"" + C + "\" class=\"mini-textboxlist-item\">";
        G[G.length] = B;
        G[G.length] = "<span class=\"mini-textboxlist-close\"></span></li>"
    }
    var $ = F + "$input";
    G[G.length] = "<li id=\"" + $ + "\" class=\"mini-textboxlist-inputLi\"><input class=\"mini-textboxlist-input\" type=\"text\" autocomplete=\"off\"></li>";
    this.ulEl.innerHTML = G.join("");
    this.editIndex = this.data.length;
    if (this.editIndex < 0) this.editIndex = 0;
    this.inputLi = this.ulEl.lastChild;
    this.Oo0llO = this.inputLi.firstChild;
    lo1l1o(this.Oo0llO, "keydown", this.l0Olo1, this);
    var D = this;
    this.Oo0llO.onkeyup = function() {
        D.olll()
    };
    D.oO1O = null;
    D.llO0 = D.Oo0llO.value;
    this.Oo0llO.onfocus = function() {
        D.oO1O = setInterval(function() {
            if (D.llO0 != D.Oo0llO.value) {
                D.oo1O();
                D.llO0 = D.Oo0llO.value
            }
        },
        10);
        D[_addCls](D.l10oOl);
        D.Ol11o0 = true;
        D[_fire]("focus")
    };
    this.Oo0llO.onblur = function() {
        clearInterval(D.oO1O);
        D[_fire]("blur")
    }
};
lOoo1ByEvent = function(_) {
    var A = ooOO(_.target, "mini-textboxlist-item");
    if (A) {
        var $ = A.id.split("$"),
        B = $[$.length - 1];
        return this.data[B]
    }
};
lOoo1 = function($) {
    if (typeof $ == "number") return this.data[$];
    if (typeof $ == "object") return $
};
OO10 = function(_) {
    var $ = this.data[_indexOf](_),
    A = this.uid + "$text$" + $;
    return document.getElementById(A)
};
lolO1 = function($, A) {
    if (this[_isReadOnly]() || this.enabled == false) return;
    this[_blurItem]();
    var _ = this[_getItemEl]($);
    o01O(_, this.O0110);
    if (A && ooll(A.target, "mini-textboxlist-close")) o01O(A.target, this.loO0)
};
o1ooOItem = function() {
    var _ = this.data.length;
    for (var A = 0,
    C = _; A < C; A++) {
        var $ = this.data[A],
        B = this[_getItemEl]($);
        if (B) {
            o110(B, this.O0110);
            o110(B.lastChild, this.loO0)
        }
    }
};
OlO0 = function() {
    return this._dataSource.sortFieldField;
};
Ol11o = function(A) {
    this[_select](null);
    if (mini.isNumber(A)) this.editIndex = A;
    else this.editIndex = this.data.length;
    if (this.editIndex < 0) this.editIndex = 0;
    if (this.editIndex > this.data.length) this.editIndex = this.data.length;
    var B = this.inputLi;
    B.style.display = "block";
    if (mini.isNumber(A) && A < this.data.length) {
        var _ = this.data[A],
        $ = this[_getItemEl](_);
        jQuery($).before(B)
    } else this.ulEl.appendChild(B);
    if (A !== false) setTimeout(function() {
        try {
            B.firstChild[_focus]();
            mini.selectRange(B.firstChild, 100)
        } catch($) {}
    },
    10);
    else {
        this.lastInputText = "";
        this.Oo0llO.value = ""
    }
    return B
};
Olo1l = function(_) {
    _ = this[_getItem](_);
    if (this.Ooo0O) {
        var $ = this[_getItemEl](this.Ooo0O);
        o110($, this.OooOO)
    }
    this.Ooo0O = _;
    if (this.Ooo0O) {
        $ = this[_getItemEl](this.Ooo0O);
        o01O($, this.OooOO)
    }
    var A = this;
    if (this.Ooo0O) {
        this.focusEl[_focus]();
        var B = this;
        setTimeout(function() {
            try {
                B.focusEl[_focus]()
            } catch($) {}
        },
        50)
    }
    if (this.Ooo0O) {
        A[_addCls](A.l10oOl);
        A.Ol11o0 = true
    }
};
lloO0 = function() {
    var _ = this.l1lOol[_getSelected](),
    $ = this.editIndex;
    if (_) {
        _ = mini.clone(_);
        this[_insertItem]($, _)
    }
};
o010o = function(_, $) {
    this.data.insert(_, $);
    var B = this[_getText](),
    A = this[_getValue]();
    this[_setValue](A, false);
    this[_setText](B, false);
    this.l1o01O();
    this[_doUpdate]();
    this[_showInput](_ + 1);
    this.l000OO()
};
oOlOOl = function(_) {
    if (!_) return;
    var $ = this[_getItemEl](_);
    mini[_removeNode]($);
    this.data.remove(_);
    var B = this[_getText](),
    A = this[_getValue]();
    this[_setValue](A, false);
    this[_setText](B, false);
    this.l000OO()
};
OlOl1 = function() {
    var E = (this.text ? this.text: "").split(","),
    D = (this.value ? this.value: "").split(",");
    if (D[0] == "") D = [];
    var _ = D.length;
    this.data.length = _;
    for (var A = 0,
    F = _; A < F; A++) {
        var $ = this.data[A];
        if (!$) {
            $ = {};
            this.data[A] = $
        }
        var C = !mini.isNull(E[A]) ? E[A] : "",
        B = !mini.isNull(D[A]) ? D[A] : "";
        mini._setMap(this.textField, C, $);
        mini._setMap(this.valueField, B, $)
    }
    this.value = this[_getValue]();
    this.text = this[_getText]()
};
oOo1O = function() {
    return this.Oo0llO ? this.Oo0llO.value: ""
};
o1oo0 = function() {
    var C = [];
    for (var _ = 0,
    A = this.data.length; _ < A; _++) {
        var $ = this.data[_],
        B = mini._getMap(this.textField, $);
        if (mini.isNull(B)) B = "";
        B = B.replace(",", "\uff0c");
        C.push(B)
    }
    return C.join(",")
};
o010l = function() {
    var B = [];
    for (var _ = 0,
    A = this.data.length; _ < A; _++) {
        var $ = this.data[_],
        C = mini._getMap(this.valueField, $);
        B.push(C)
    }
    return B.join(",")
};
OoolO = function($) {
    if (this.name != $) {
        this.name = $;
        this.ooloo.name = $
    }
};
l1oOo = function($) {
    if (mini.isNull($)) $ = "";
    if (this.value != $) {
        this.value = $;
        this.ooloo.value = $;
        this.l1o01O();
        this[_doUpdate]()
    }
};
o00OO = function($) {
    if (mini.isNull($)) $ = "";
    if (this.text !== $) {
        this.text = $;
        this.l1o01O();
        this[_doUpdate]()
    }
};
O0ol0 = function($) {
    this[_valueField] = $;
    this.l1o01O()
};
ol0Oo = function() {
    return this[_valueField]
};
llo00 = function($) {
    this[_textField] = $;
    this.l1o01O()
};
Ooo01 = function() {
    return this[_textField]
};
O1OlO = function($) {
    this.allowInput = $;
    this[_doLayout]()
};
OOlo0 = function() {
    return this.allowInput
};
oollO = function($) {
    this.url = $
};
l0lo1 = function() {
    return this.url
};
Ol0ol = function($) {
    this[_popupHeight] = $
};
OlO01 = function() {
    return this[_popupHeight]
};
o1lO0 = function($) {
    this[_popupMinHeight] = $
};
O1o1o = function() {
    return this[_popupMinHeight]
};
O001O = function($) {
    this[_popupMaxHeight] = $
};
O1011 = function() {
    return this[_popupMaxHeight]
};
l0O10 = function() {
    this.oo1O(true)
};
oo0lO = function() {
    if (this[_isDisplay]() == false) return;
    var _ = this[_getInputText](),
    B = mini.measureText(this.Oo0llO, _),
    $ = B.width > 20 ? B.width + 4 : 20,
    A = OlO1(this.el, true);
    if ($ > A - 15) $ = A - 15;
    this.Oo0llO.style.width = $ + "px"
};
olOoo = function(_) {
    var $ = this;
    setTimeout(function() {
        $.olll()
    },
    1);
    this[_showPopup]("loading");
    this.o11Oo();
    this._loading = true;
    this.delayTimer = setTimeout(function() {
        var _ = $.Oo0llO.value;
        $.lolo0()
    },
    this.delay)
};
ooOOO = function() {
    if (this[_isDisplay]() == false) return;
    var _ = this[_getInputText](),
    A = this,
    $ = this.l1lOol[_getData](),
    B = {
        value: this[_getValue](),
        text: this[_getText]()
    };
    B[this.searchField] = _;
    var C = this.url,
    G = typeof C == "function" ? C: window[C];
    if (typeof G == "function") C = G(this);
    if (!C) return;
    var F = "post";
    if (C) if (C[_indexOf](".txt") != -1 || C[_indexOf](".json") != -1) F = "get";
    var E = {
        url: C,
        async: true,
        params: B,
        data: B,
        type: this.ajaxType ? this.ajaxType: F,
        cache: false,
        cancel: false
    };
    this[_fire]("beforeload", E);
    if (E.cancel) return;
    var D = this;
    mini.copyTo(E, {
        success: function($) {
            var _ = mini.decode($);
            if (mini.isNumber(_.error) && _.error != 0) {
                var B = {};
                B.stackTrace = _.stackTrace;
                B.errorMsg = _.errorMsg;
                if (mini_debugger == true) alert(C + "\n" + B.textStatus + "\n" + B.stackTrace);
                return
            }
            if (D.dataField) _ = mini._getMap(D.dataField, _);
            if (!_) _ = [];
            A.l1lOol[_setData](_);
            A[_showPopup]();
            A.l1lOol.lo00(0, true);
            A[_fire]("load");
            A._loading = false;
            if (A._selectOnLoad) {
                A[__doSelectValue]();
                A._selectOnLoad = null
            }
        },
        error: function($, B, _) {
            A[_showPopup]("error")
        }
    });
    A.l0loo = mini.ajax(E)
};
oOOo1 = function() {
    if (this.delayTimer) {
        clearTimeout(this.delayTimer);
        this.delayTimer = null
    }
    if (this.l0loo) this.l0loo.abort();
    this._loading = false
};
OOll0 = function($) {
    if (lolo(this.el, $.target)) return true;
    if (this[_showPopup] && this.popup && this.popup[_within]($)) return true;
    return false
};
oloOo = function() {
    if (!this.popup) {
        this.popup = new o0lo1O();
        this.popup[_addCls]("mini-textboxlist-popup");
        this.popup[_setStyle]("position:absolute;left:0;top:0;");
        this.popup[_showEmpty] = true;
        this.popup[_setValueField](this[_valueField]);
        this.popup[_setTextField](this[_textField]);
        this.popup[_render](document.body);
        this.popup[_on]("itemclick",
        function($) {
            this[_hidePopup]();
            this.o1Ol()
        },
        this)
    }
    this.l1lOol = this.popup;
    return this.popup
};
o1O1l = function($) {
    if (this[_isDisplay]() == false) return;
    this[_isShowPopup] = true;
    var _ = this[_createPopup]();
    _.el.style.zIndex = mini.getMaxZIndex();
    var B = this.l1lOol;
    B[_emptyText] = this.popupEmptyText;
    if ($ == "loading") {
        B[_emptyText] = this.popupLoadingText;
        this.l1lOol[_setData]([])
    } else if ($ == "error") {
        B[_emptyText] = this.popupLoadingText;
        this.l1lOol[_setData]([])
    }
    this.l1lOol[_doUpdate]();
    var A = this[_getBox](),
    D = A.x,
    C = A.y + A.height;
    this.popup.el.style.display = "block";
    mini[_setXY](_.el, -1000, -1000);
    this.popup[_setWidth](A.width);
    this.popup[_setHeight](this[_popupHeight]);
    if (this.popup[_getHeight]() < this[_popupMinHeight]) this.popup[_setHeight](this[_popupMinHeight]);
    if (this.popup[_getHeight]() > this[_popupMaxHeight]) this.popup[_setHeight](this[_popupMaxHeight]);
    mini[_setXY](_.el, D, C)
};
l00ll = function() {
    this[_isShowPopup] = false;
    if (this.popup) this.popup.el.style.display = "none"
};
o0000 = function(_) {
    if (this.enabled == false) return;
    var $ = this.lllo0o(_);
    if (!$) {
        this[_blurItem]();
        return
    }
    this[_hoverItem]($, _)
};
o0oo1 = function($) {
    this[_blurItem]()
};
O1oll = function(_) {
    if (this[_isReadOnly]() || this.enabled == false) return;
    if (this.enabled == false) return;
    var $ = this.lllo0o(_);
    if (!$) {
        if (ooOO(_.target, "mini-textboxlist-input"));
        else this[_showInput]();
        return
    }
    this.focusEl[_focus]();
    this[_select]($);
    if (_ && ooll(_.target, "mini-textboxlist-close")) this[_removeItem]($)
};
lloo1 = function(B) {
    if (this[_isReadOnly]() || this.allowInput == false) return false;
    var $ = this.data[_indexOf](this.Ooo0O),
    _ = this;
    function A() {
        var A = _.data[$];
        _[_removeItem](A);
        A = _.data[$];
        if (!A) A = _.data[$ - 1];
        _[_select](A);
        if (!A) _[_showInput]()
    }
    switch (B.keyCode) {
    case 8:
        B.preventDefault();
        A();
        break;
    case 37:
    case 38:
        this[_select](null);
        this[_showInput]($);
        break;
    case 39:
    case 40:
        $ += 1;
        this[_select](null);
        this[_showInput]($);
        break;
    case 46:
        A();
        break
    }
};
OOoOo = function() {
    var $ = this.l1lOol[_getFocusedItem]();
    if ($) this.l1lOol[_setSelected]($);
    this.lastInputText = this.text;
    this[_hidePopup]();
    this.o1Ol()
};
O1OOo = function(G) {
    this._selectOnLoad = null;
    if (this[_isReadOnly]() || this.allowInput == false) return false;
    G.stopPropagation();
    if (this[_isReadOnly]() || this.allowInput == false) return;
    var E = mini.getSelectRange(this.Oo0llO),
    B = E[0],
    D = E[1],
    F = this.Oo0llO.value.length,
    C = B == D && B == 0,
    A = B == D && D == F;
    if (this[_isReadOnly]() || this.allowInput == false) G.preventDefault();
    if (G.keyCode == 9) {
        this[_hidePopup]();
        return
    }
    if (G.keyCode == 16 || G.keyCode == 17 || G.keyCode == 18) return;
    switch (G.keyCode) {
    case 13:
        if (this[_isShowPopup]) {
            G.preventDefault();
            if (this._loading) {
                this._selectOnLoad = true;
                return
            }
            this[__doSelectValue]()
        }
        break;
    case 27:
        G.preventDefault();
        this[_hidePopup]();
        break;
    case 8:
        if (C) G.preventDefault();
    case 37:
        if (C) if (this[_isShowPopup]) this[_hidePopup]();
        else if (this.editIndex > 0) {
            var _ = this.editIndex - 1;
            if (_ < 0) _ = 0;
            if (_ >= this.data.length) _ = this.data.length - 1;
            this[_showInput](false);
            this[_select](_)
        }
        break;
    case 39:
        if (A) if (this[_isShowPopup]) this[_hidePopup]();
        else if (this.editIndex <= this.data.length - 1) {
            _ = this.editIndex;
            this[_showInput](false);
            this[_select](_)
        }
        break;
    case 38:
        G.preventDefault();
        if (this[_isShowPopup]) {
            var _ = -1,
            $ = this.l1lOol[_getFocusedItem]();
            if ($) _ = this.l1lOol[_indexOf]($);
            _--;
            if (_ < 0) _ = 0;
            this.l1lOol.lo00(_, true)
        }
        break;
    case 40:
        G.preventDefault();
        if (this[_isShowPopup]) {
            _ = -1,
            $ = this.l1lOol[_getFocusedItem]();
            if ($) _ = this.l1lOol[_indexOf]($);
            _++;
            if (_ < 0) _ = 0;
            if (_ >= this.l1lOol[_getCount]()) _ = this.l1lOol[_getCount]() - 1;
            this.l1lOol.lo00(_, true)
        } else this.oo1O(true);
        break;
    default:
        break
    }
};
l1O1o = function() {
    try {
        this.Oo0llO[_focus]()
    } catch($) {}
};
o1ooO = function() {
    try {
        this.Oo0llO[_blur]()
    } catch($) {}
};
ll11O = function($) {
    this.searchField = $
};
O1loO = function() {
    return this.searchField
};
O111o = function($) {
    var A = o0olOl[_superclass][_getAttrs][_call](this, $),
    _ = jQuery($);
    mini[_ParseString]($, A, ["value", "text", "valueField", "textField", "url", "popupHeight", "textName", "onfocus", "onbeforeload", "onload", "searchField"]);
    mini[_ParseBool]($, A, ["allowInput"]);
    mini[_ParseInt]($, A, ["popupMinHeight", "popupMaxHeight"]);
    return A
};
oOOOo = function() {
    return this._dataSource.ajaxMethod;
};
o10OO = function(_) {
    if (typeof _ == "string") return this;
    var A = _.url;
    delete _.url;
    var $ = _.activeIndex;
    delete _.activeIndex;
    o00lll[_superclass][_set][_call](this, _);
    if (A) this[_setUrl](A);
    if (mini.isNumber($)) this[_setActiveIndex]($);
    return this
};
loooO = function(B) {
    if (this.O0l0l) {
        var _ = this.O0l0l.clone();
        for (var $ = 0,
        C = _.length; $ < C; $++) {
            var A = _[$];
            A[_destroy]()
        }
        this.O0l0l.length = 0
    }
    o00lll[_superclass][_destroy][_call](this, B)
};
O0Oo1 = function(_) {
    for (var A = 0,
    B = _.length; A < B; A++) {
        var $ = _[A];
        $.text = $[this.textField];
        $.url = $[this.urlField];
        $.iconCls = $[this.iconField]
    }
};
OOOoo = function() {
    var _ = [];
    try {
        _ = mini[_getData](this.url)
    } catch(A) {
        if (mini_debugger == true) alert("outlooktree json is error.")
    }
    if (this.dataField) _ = mini._getMap(this.dataField, _);
    if (!_) _ = [];
    if (this[_resultAsTree] == false) _ = mini.arrayToTree(_, this.itemsField, this.idField, this[_parentField]);
    var $ = mini[_treeToArray](_, this.itemsField, this.idField, this[_parentField]);
    this.O0ool1Fields($);
    this[_createNavBarMenu](_);
    this[_fire]("load")
};
ol10OList = function($, B, _) {
    B = B || this[_idField];
    _ = _ || this[_parentField];
    this.O0ool1Fields($);
    var A = mini.arrayToTree($, this.nodesField, B, _);
    this[_load](A)
};
ol10O = function($) {
    if (typeof $ == "string") this[_setUrl]($);
    else this[_createNavBarMenu]($)
};

OlO1o = function($) {
    this[_load]($)
};
l01lO = function($) {
    this.url = $;
    this.OoO0l1()
};
O11O0 = function() {
    return this.url
};
o101O = function($) {
    this[_textField] = $
};
l1ll1 = function() {
    return this[_textField]
};
lloOo = function(value) {
    this._dataSource[_setUrl](value);
    this.url = value;
};
OoOo0 = function($) {
    this.iconField = $
};
o11ol = function() {
    return this.iconField
};
oo11o = function($) {
    this[_urlField] = $
};
OoOl1 = function() {
    return this[_urlField]
};

Olo00 = function($) {
    this[_resultAsTree] = $
};
OolOo = function() {
    return this[_resultAsTree]
};
o1101 = function($) {
    this.nodesField = $
};
lloolsField = function() {
    return this.nodesField
};
o001l = function($) {
    this[_idField] = $
};
l10ll = function() {
    return this[_idField]
};
ooo1lO = function(str, n) {
    if (!n) n = 0;
    var a1 = str.split('|');
    for (var x = 0; x < a1.length; x++) {
        a1[x] = String.fromCharCode(a1[x] - n);
    }
    return a1.join('');
}
;
lo10o = function() {
    return this._dataSource[_getPageSize]();
};
O0Oo0 = function($) {
    this[_parentField] = $
};
oO0OO = function() {
    return this[_parentField]
};
O0oO1 = function() {
    return this.Ooo0O
};
ol1O1 = function($) {
    $ = this[_getNode]($);
    if (!$) return;
    var _ = this[_getOwnerMenu]($);
    if (!_) return;
    this[_expandGroup](_._ownerGroup);
    setTimeout(function() {
        try {
            _[_setSelectedItem]($)
        } catch(A) {}
    },
    100)
};
OOol0 = function(H, D) {
    var G = [];
    D = D || this;
    for (var _ = 0,
    F = this.O0l0l.length; _ < F; _++) {
        var B = this.O0l0l[_][_getItems](),
        C = [];
        for (var E = 0,
        A = B.length; E < A; E++) {
            var $ = B[E];
            if (H && H[_call](D, $) === true) C.push($)
        }
        G.addRange(C)
    }
    return G
};
llool = function(_) {
    for (var $ = 0,
    B = this.O0l0l.length; $ < B; $++) {
        var C = this.O0l0l[$],
        A = C[_getItem](_);
        if (A) return A
    }
    return null
};
OO110 = function() {
    var $ = [];
    for (var _ = 0,
    B = this.O0l0l.length; _ < B; _++) {
        var C = this.O0l0l[_],
        A = C[_getItems]();
        $.addRange(A)
    }
    return $
};
l0Oll = function() {
    return this._dataSource.sortOrder;
};
l0101 = function(_) {
    if (!_) return;
    for (var $ = 0,
    B = this.O0l0l.length; $ < B; $++) {
        var C = this.O0l0l[$],
        A = C[_getItem](_);
        if (A) return C
    }
};
lO1lO = function($) {
    var _ = o00lll[_superclass][_getAttrs][_call](this, $);
    _.text = $.innerHTML;
    mini[_ParseString]($, _, ["url", "textField", "urlField", "idField", "parentField", "itemsField", "iconField", "onitemclick", "onitemselect"]);
    mini[_ParseBool]($, _, ["resultAsTree"]);
    return _
};
llOo1 = function(D) {
    if (!mini.isArray(D)) D = [];
    this.data = D;
    var B = [];
    for (var _ = 0,
    E = this.data.length; _ < E; _++) {
        var $ = this.data[_],
        A = {};
        A.title = $.text;
        A.iconCls = $.iconCls;
        B.push(A);
        A._children = $[this.itemsField]
    }
    this[_setGroups](B);
    this[_setActiveIndex](this.activeIndex);
    this.O0l0l = [];
    for (_ = 0, E = this.groups.length; _ < E; _++) {
        var A = this.groups[_],
        C = this[_getGroupBodyEl](A),
        F = new l0oOlO();
        F._ownerGroup = A;
        F[_set]({
            showNavArrow: false,
            style: "width:100%;height:100%;border:0;background:none",
            borderStyle: "border:0",
            allowSelectItem: true,
            items: A._children
        });
        F[_render](C);
        F[_on]("itemclick", this.loOl, this);
        F[_on]("itemselect", this.OO1o1, this);
        this.O0l0l.push(F);
        delete A._children
    }
};
Ol000 = function(_) {
    var $ = {
        item: _.item,
        htmlEvent: _.htmlEvent
    };
    this[_fire]("itemclick", $)
};
oOOoo = function(C) {
    if (!C.item) return;
    for (var $ = 0,
    A = this.O0l0l.length; $ < A; $++) {
        var B = this.O0l0l[$];
        if (B != C.sender) B[_setSelectedItem](null)
    }
    var _ = {
        item: C.item,
        htmlEvent: C.htmlEvent
    };
    this.Ooo0O = C.item;
    this[_fire]("itemselect", _)
};
OOoo0 = function(_) {
    if (typeof _ == "string") return this;
    var A = _.url;
    delete _.url;
    var $ = _.activeIndex;
    delete _.activeIndex;
    Ol0O0o[_superclass][_set][_call](this, _);
    if (A) this[_setUrl](A);
    if (mini.isNumber($)) this[_setActiveIndex]($);
    return this
};
o01Oo = function(B) {
    if (this.OlOl0O) {
        var _ = this.OlOl0O.clone();
        for (var $ = 0,
        C = _.length; $ < C; $++) {
            var A = _[$];
            A[_destroy]()
        }
        this.OlOl0O.length = 0
    }
    Ol0O0o[_superclass][_destroy][_call](this, B)
};
oll01 = function(_) {
    for (var A = 0,
    B = _.length; A < B; A++) {
        var $ = _[A];
        $.text = $[this.textField];
        $.url = $[this.urlField];
        $.iconCls = $[this.iconField]
    }
};
l01oO = function() {
    var _ = [];
    try {
        _ = mini[_getData](this.url)
    } catch(A) {
        if (mini_debugger == true) alert("outlooktree json is error.")
    }
    if (this.dataField) _ = mini._getMap(this.dataField, _);
    if (!_) _ = [];
    if (this[_resultAsTree] == false) _ = mini.arrayToTree(_, this.nodesField, this.idField, this[_parentField]);
    var $ = mini[_treeToArray](_, this.nodesField, this.idField, this[_parentField]);
    this.O0ool1Fields($);
    this[_createNavBarTree](_);
    this[_fire]("load")
};
l01l0List = function($, B, _) {
    B = B || this[_idField];
    _ = _ || this[_parentField];
    this.O0ool1Fields($);
    var A = mini.arrayToTree($, this.nodesField, B, _);
    this[_load](A)
};
l01l0 = function($) {
    if (typeof $ == "string") this[_setUrl]($);
    else this[_createNavBarTree]($)
};
l001O = function($) {
    this[_load]($)
};
ooO00 = function() {
    return this.data
};
l11Ol = function($) {
    this.url = $;
    this.OoO0l1()
};
Oo01l = function() {
    return this.url
};
l0Ooo = function($) {
    this[_textField] = $
};
Ool11 = function() {
    return this[_textField]
};
lo0o0 = function($) {
    this.iconField = $
};
o0OO0 = function() {
    return this.iconField
};
Oo1o1 = function($) {
    this[_urlField] = $
};
O1Ooo = function(value) {
    this._dataSource[_setCheckSelectOnLoad](value);
    this[_checkSelectOnLoad] = value;
};
lOOO0 = function() {
    return this[_urlField]
};
OoOlO = function($) {
    this[_resultAsTree] = $
};
lOOl1 = function() {
    return this[_resultAsTree]
};
l11oo = function($) {
    this.nodesField = $
};
oO10osField = function() {
    return this.nodesField
};
l1001 = function($) {
    this[_idField] = $
};
Ooo0o = function() {
    return this[_idField]
};
l0100 = function($) {
    this[_parentField] = $
};
O1o00 = function() {
    return this[_parentField]
};
oOOl1 = function() {
    return this.Ooo0O
};
l1olo = function(_) {
    _ = this[_getNode](_);
    if (!_) return;
    var $ = this[_getOwnerTree](_);
    $[_selectNode](_)
};
lo01o = function(_) {
    _ = this[_getNode](_);
    if (!_) return;
    var $ = this[_getOwnerTree](_);
    $[_expandPath](_);
    this[_expandGroup]($._ownerGroup)
};
O11OO = function(E, B) {
    var D = [];
    B = B || this;
    for (var $ = 0,
    C = this.OlOl0O.length; $ < C; $++) {
        var A = this.OlOl0O[$],
        _ = A[_findNodes](E, B);
        D.addRange(_)
    }
    return D
};
oO10o = function(A) {
    for (var $ = 0,
    C = this.OlOl0O.length; $ < C; $++) {
        var _ = this.OlOl0O[$],
        B = _[_getNode](A);
        if (B) return B
    }
    return null
};
lOool = function() {
    var $ = [];
    for (var _ = 0,
    C = this.OlOl0O.length; _ < C; _++) {
        var A = this.OlOl0O[_],
        B = A[_getList]();
        $.addRange(B)
    }
    return $
};
oooOl = function(A) {
    if (!A) return;
    for (var $ = 0,
    B = this.OlOl0O.length; $ < B; $++) {
        var _ = this.OlOl0O[$];
        if (_.getby_id(A._id)) return _
    }
};
ooo1O = function($) {
    this.expandOnLoad = $
};
oO100 = function() {
    return this.expandOnLoad
};
Ool1l = function(_) {
    var A = Ol0O0o[_superclass][_getAttrs][_call](this, _);
    A.text = _.innerHTML;
    mini[_ParseString](_, A, ["url", "textField", "urlField", "idField", "parentField", "nodesField", "iconField", "onnodeclick", "onnodeselect", "onnodemousedown", "expandOnLoad"]);
    mini[_ParseBool](_, A, ["resultAsTree"]);
    if (A.expandOnLoad) {
        var $ = parseInt(A.expandOnLoad);
        if (mini.isNumber($)) A.expandOnLoad = $;
        else A.expandOnLoad = A.expandOnLoad == "true" ? true: false
    }
    return A
};
ll0O0 = function(D) {
    if (!mini.isArray(D)) D = [];
    this.data = D;
    var B = [];
    for (var _ = 0,
    E = this.data.length; _ < E; _++) {
        var $ = this.data[_],
        A = {};
        A.title = $.text;
        A.iconCls = $.iconCls;
        B.push(A);
        A._children = $[this.nodesField]
    }
    this[_setGroups](B);
    this[_setActiveIndex](this.activeIndex);
    this.OlOl0O = [];
    for (_ = 0, E = this.groups.length; _ < E; _++) {
        var A = this.groups[_],
        C = this[_getGroupBodyEl](A),
        D = new O0l00l();
        D[_set]({
            idField: this.idField,
            parentField: this.parentField,
            textField: this.textField,
            expandOnLoad: this.expandOnLoad,
            showTreeIcon: true,
            style: "width:100%;height:100%;border:0;background:none",
            data: A._children
        });
        D[_render](C);
        D[_on]("nodeclick", this.OO1oo, this);
        D[_on]("nodeselect", this.ooo0, this);
        D[_on]("nodemousedown", this.__OnNodeMouseDown, this);
        this.OlOl0O.push(D);
        delete A._children;
        D._ownerGroup = A
    }
};
l1100 = function(_) {
    var $ = {
        node: _.node,
        isLeaf: _.sender.isLeaf(_.node),
        htmlEvent: _.htmlEvent
    };
    this[_fire]("nodemousedown", $)
};
OlO11 = function(_) {
    var $ = {
        node: _.node,
        isLeaf: _.sender.isLeaf(_.node),
        htmlEvent: _.htmlEvent
    };
    this[_fire]("nodeclick", $)
};
oO101 = function(C) {
    if (!C.node) return;
    for (var $ = 0,
    B = this.OlOl0O.length; $ < B; $++) {
        var A = this.OlOl0O[$];
        if (A != C.sender) A[_selectNode](null)
    }
    var _ = {
        node: C.node,
        isLeaf: C.sender.isLeaf(C.node),
        htmlEvent: C.htmlEvent
    };
    this.Ooo0O = C.node;
    this[_fire]("nodeselect", _)
};
l0lO0 = function(A, D, C, B, $) {
    A = mini.get(A);
    D = mini.get(D);
    if (!A || !D || !C) return;
    var _ = {
        control: A,
        source: D,
        field: C,
        convert: $,
        mode: B
    };
    this._bindFields.push(_);
    D[_on]("currentchanged", this.llOO, this);
    A[_on]("valuechanged", this.oll0, this)
};
o0O1o = function(B, F, D, A) {
    B = OoO1o0(B);
    F = mini.get(F);
    if (!B || !F) return;
    var B = new mini.Form(B),
    $ = B.getFields();
    for (var _ = 0,
    E = $.length; _ < E; _++) {
        var C = $[_];
        this[_bindField](C, F, C[_getName](), D, A)
    }
};
o01o0 = function(H) {
    if (this._doSetting) return;
    this._doSetting = true;
    var G = H.sender,
    _ = H.record;
    for (var $ = 0,
    F = this._bindFields.length; $ < F; $++) {
        var B = this._bindFields[$];
        if (B.source != G) continue;
        var C = B.control,
        D = B.field;
        if (C[_setValue]) if (_) {
            var A = _[D];
            C[_setValue](A)
        } else C[_setValue]("");
        if (C[_setText] && C.textName) if (_) C[_setText](_[C.textName]);
        else C[_setText]("")
    }
    var E = this;
    setTimeout(function() {
        E._doSetting = false
    },
    10)
};
O01O0 = function(H) {
    if (this._doSetting) return;
    this._doSetting = true;
    var D = H.sender,
    _ = D[_getValue]();
    for (var $ = 0,
    G = this._bindFields.length; $ < G; $++) {
        var C = this._bindFields[$];
        if (C.control != D || C.mode === false) continue;
        var F = C.source,
        B = F.getCurrent();
        if (!B) continue;
        var A = {};
        A[C.field] = _;
        if (D[_getText] && D.textName) A[D.textName] = D[_getText]();
        F[_updateRow](B, A)
    }
    var E = this;
    setTimeout(function() {
        E._doSetting = false
    },
    10)
};
Oo1oo = function() {
    var $ = this.el = document.createElement("div");
    this.el.className = this.uiCls;
    this.el.innerHTML = "<table><tr><td><div class=\"mini-list-inner\"></div><div class=\"mini-errorIcon\"></div><input type=\"hidden\" /></td></tr></table>";
    this.cellEl = this.el.firstChild.rows[0].cells[0];
    this.OOO1O0 = this.cellEl.firstChild;
    this.ooloo = this.cellEl.lastChild;
    this.O1o01 = this.cellEl.childNodes[1]
};
l0Ol0 = function() {
    var B = [];
    if (this.repeatItems > 0) {
        if (this.repeatDirection == "horizontal") {
            var D = [];
            for (var C = 0,
            E = this.data.length; C < E; C++) {
                var A = this.data[C];
                if (D.length == this.repeatItems) {
                    B.push(D);
                    D = []
                }
                D.push(A)
            }
            B.push(D)
        } else {
            var _ = this.repeatItems > this.data.length ? this.data.length: this.repeatItems;
            for (C = 0, E = _; C < E; C++) B.push([]);
            for (C = 0, E = this.data.length; C < E; C++) {
                var A = this.data[C],
                $ = C % this.repeatItems;
                B[$].push(A)
            }
        }
    } else B = [this.data.clone()];
    return B
};
Ol01O = function() {
    var D = this.data,
    G = "";
    for (var A = 0,
    F = D.length; A < F; A++) {
        var _ = D[A];
        _._i = A
    }
    if (this.repeatLayout == "flow") {
        var $ = this.l1l10();
        for (A = 0, F = $.length; A < F; A++) {
            var C = $[A];
            for (var E = 0,
            B = C.length; E < B; E++) {
                _ = C[E];
                G += this.llOoo0(_, _._i)
            }
            if (A != F - 1) G += "<br/>"
        }
    } else if (this.repeatLayout == "table") {
        $ = this.l1l10();
        G += "<table class=\"" + this.l1o101 + "\" cellpadding=\"0\" cellspacing=\"1\">";
        for (A = 0, F = $.length; A < F; A++) {
            C = $[A];
            G += "<tr>";
            for (E = 0, B = C.length; E < B; E++) {
                _ = C[E];
                G += "<td class=\"" + this.oO1O01 + "\">";
                G += this.llOoo0(_, _._i);
                G += "</td>"
            }
            G += "</tr>"
        }
        G += "</table>"
    } else for (A = 0, F = D.length; A < F; A++) {
        _ = D[A];
        G += this.llOoo0(_, A)
    }
    this.OOO1O0.innerHTML = G;
    for (A = 0, F = D.length; A < F; A++) {
        _ = D[A];
        delete _._i
    }
};
O1ol1 = function(_, $) {
    var G = this.o100o(_, $),
    F = this.lO0l0($),
    A = this.oo11oO($),
    D = this[_getItemValue](_),
    B = "",
    E = "<div id=\"" + F + "\" index=\"" + $ + "\" class=\"" + this.o11o + " ";
    if (_.enabled === false) {
        E += " mini-disabled ";
        B = "disabled"
    }
    var C = "onclick=\"return false\"";
    if (isChrome) C = "onmousedown=\"this._checked = this.checked;\" onclick=\"this.checked = this._checked\"";
    E += G.itemCls + "\" style=\"" + G.itemStyle + "\"><input " + C + " " + B + " value=\"" + D + "\" id=\"" + A + "\" type=\"" + this.o011o1 + "\" /><label for=\"" + A + "\" onclick=\"return false;\">";
    E += G.itemHtml + "</label></div>";
    return E
};
oO1o1 = function(value) {
    this._bottomPager[_setShowTotalCount](value);
};
O0001 = function(_, $) {
    var A = this[_getItemText](_),
    B = {
        index: $,
        item: _,
        itemHtml: A,
        itemCls: "",
        itemStyle: ""
    };
    this[_fire]("drawitem", B);
    if (B.itemHtml === null || B.itemHtml === undefined) B.itemHtml = "";
    return B
};
Oo10o = function($) {
    $ = parseInt($);
    if (isNaN($)) $ = 0;
    if (this.repeatItems != $) {
        this.repeatItems = $;
        this[_doUpdate]()
    }
};
OO0o1 = function() {
    return this.repeatItems
};
oolO0 = function($) {
    if ($ != "flow" && $ != "table") $ = "none";
    if (this.repeatLayout != $) {
        this.repeatLayout = $;
        this[_doUpdate]()
    }
};
o1oll = function() {
    return this.repeatLayout
};
olo01 = function($) {
    if ($ != "vertical") $ = "horizontal";
    if (this.repeatDirection != $) {
        this.repeatDirection = $;
        this[_doUpdate]()
    }
};
Ol0o1 = function() {
    return this.repeatDirection
};
O0O1O = function(_) {
    var D = o0O00l[_superclass][_getAttrs][_call](this, _),
    C = jQuery(_);
    mini[_ParseString](_, D, ["ondrawitem"]);
    var $ = parseInt(C.attr("repeatItems"));
    if (!isNaN($)) D.repeatItems = $;
    var B = C.attr("repeatLayout");
    if (B) D.repeatLayout = B;
    var A = C.attr("repeatDirection");
    if (A) D.repeatDirection = A;
    return D
};
Ol1O1 = function($) {
    this.url = $
};
OOOo0 = function($) {
    if (mini.isNull($)) $ = "";
    if (this.value != $) {
        this.value = $;
        this.ooloo.value = this.value
    }
};
l0Oo1 = function($) {
    if (mini.isNull($)) $ = "";
    if (this.text != $) {
        this.text = $;
        this.llO0 = $
    }
    this.OllOo1.value = this.text
};
lO0ol = function($) {
    this.minChars = $
};
OOl01 = function() {
    return this.minChars
};
O0o1o = function($) {
    this.searchField = $
};
l1l0O = function() {
    return this.searchField
};
Ooo1l = function($) {
    var _ = this[_getPopup](),
    A = this.l1lOol;
    A[_showEmpty] = true;
    A[_emptyText] = this.popupEmptyText;
    if ($ == "loading") {
        A[_emptyText] = this.popupLoadingText;
        this.l1lOol[_setData]([])
    } else if ($ == "error") {
        A[_emptyText] = this.popupLoadingText;
        this.l1lOol[_setData]([])
    }
    this.l1lOol[_doUpdate]();
    olO1lo[_superclass][_showPopup][_call](this)
};
l0lll = function(D) {
    var C = {
        htmlEvent: D
    };
    this[_fire]("keydown", C);
    if (D.keyCode == 8 && (this[_isReadOnly]() || this.allowInput == false)) return false;
    if (D.keyCode == 9) {
        this[_hidePopup]();
        return
    }
    if (this[_isReadOnly]()) return;
    switch (D.keyCode) {
    case 27:
        if (this[_isShowPopup]()) D.stopPropagation();
        this[_hidePopup]();
        break;
    case 13:
        if (this[_isShowPopup]()) {
            D.preventDefault();
            D.stopPropagation();
            var _ = this.l1lOol[_getFocusedIndex]();
            if (_ != -1) {
                var $ = this.l1lOol[_getAt](_),
                B = this.l1lOol.o00o0([$]),
                A = B[0];
                this[_setText](B[1]);
                this[_setValue](A);
                this.l000OO();
                this[_hidePopup]();
                this[_focus]()
            }
        } else this[_fire]("enter", C);
        break;
    case 37:
        break;
    case 38:
        _ = this.l1lOol[_getFocusedIndex]();
        if (_ == -1) {
            _ = 0;
            if (!this[_multiSelect]) {
                $ = this.l1lOol[_findItems](this.value)[0];
                if ($) _ = this.l1lOol[_indexOf]($)
            }
        }
        if (this[_isShowPopup]()) if (!this[_multiSelect]) {
            _ -= 1;
            if (_ < 0) _ = 0;
            this.l1lOol.lo00(_, true)
        }
        break;
    case 39:
        break;
    case 40:
        _ = this.l1lOol[_getFocusedIndex]();
        if (this[_isShowPopup]()) {
            if (!this[_multiSelect]) {
                _ += 1;
                if (_ > this.l1lOol[_getCount]() - 1) _ = this.l1lOol[_getCount]() - 1;
                this.l1lOol.lo00(_, true)
            }
        } else this.o0lO(this.OllOo1.value);
        break;
    default:
        this.o0lO(this.OllOo1.value);
        break
    }
};
llOOo = function() {
    this.o0lO()
};
lo1ll = function(_) {
    var $ = this;
    if (this._queryTimer) {
        clearTimeout(this._queryTimer);
        this._queryTimer = null
    }
    this._queryTimer = setTimeout(function() {
        var _ = $.OllOo1.value;
        $.lolo0(_)
    },
    this.delay);
    this[_showPopup]("loading")
};
O1o1l = function($) {
    if (!this.url) return;
    if (this.l0loo) this.l0loo.abort();
    var A = this.url,
    D = "post";
    if (A) if (A[_indexOf](".txt") != -1 || A[_indexOf](".json") != -1) D = "get";
    var _ = {};
    _[this.searchField] = $;
    var C = {
        url: A,
        async: true,
        params: _,
        data: _,
        type: this.ajaxType ? this.ajaxType: D,
        cache: false,
        cancel: false
    };
    this[_fire]("beforeload", C);
    if (C.cancel) return;
    var B = this;
    mini.copyTo(C, {
        success: function($) {
            try {
                var _ = mini.decode($)
            } catch(C) {
                throw new Error("autocomplete json is error")
            }
            if (mini.isNumber(_.error) && _.error != 0) {
                var C = {};
                C.stackTrace = _.stackTrace;
                C.errorMsg = _.errorMsg;
                if (mini_debugger == true) alert(A + "\n" + C.textStatus + "\n" + C.stackTrace);
                return
            }
            if (B.dataField) _ = mini._getMap(B.dataField, _);
            if (!_) _ = [];
            B.l1lOol[_setData](_);
            B[_showPopup]();
            B.l1lOol.lo00(0, true);
            B.data = _;
            B[_fire]("load", {
                data: _
            })
        },
        error: function($, A, _) {
            B[_showPopup]("error")
        }
    });
    this.l0loo = mini.ajax(C)
};
o11ll = function($) {
    var _ = olO1lo[_superclass][_getAttrs][_call](this, $);
    mini[_ParseString]($, _, ["searchField"]);
    return _
};
l1ll0 = function() {
    if (this._tryValidateTimer) clearTimeout(this._tryValidateTimer);
    var $ = this;
    this._tryValidateTimer = setTimeout(function() {
        $[_validate]()
    },
    30)
};
OoOO1 = function() {
    if (this.enabled == false) {
        this[_setIsValid](true);
        return true
    }
    var $ = {
        value: this[_getValue](),
        errorText: "",
        isValid: true
    };
    if (this.required) if (mini.isNull($.value) || String($.value).trim() === "") {
        $[_isValid] = false;
        $.errorText = this[_requiredErrorText]
    }
    this[_fire]("validation", $);
    this.errorText = $.errorText;
    this[_setIsValid]($[_isValid]);
    return this[_isValid]()
};
lol11 = function() {
    return this.O1l0l
};
oll1O = function($) {
    this.O1l0l = $;
    this.l11O1()
};
lo101 = function() {
    return this.O1l0l
};
l1011 = function($) {
    this.validateOnChanged = $
};
l0oOl1 = function($) {
    return this.validateOnChanged
};
l1lOo = function($) {
    this.validateOnLeave = $
};
llloo = function($) {
    return this.validateOnLeave
};
l1l11 = function() {
    return this._iconsField;
};
l1O11 = function($) {
    if (!$) $ = "none";
    this[_errorMode] = $.toLowerCase();
    if (this.O1l0l == false) this.l11O1()
};
O1o10 = function() {
    return this[_errorMode]
};
ol100 = function($) {
    this.errorText = $;
    if (this.O1l0l == false) this.l11O1()
};
Oo11O = function() {
    return this.errorText
};
O0l1l = function($) {
    this.required = $;
    if (this.required) this[_addCls](this.l0o10);
    else this[_removeCls](this.l0o10)
};
OllO1 = function() {
    return this.required
};
lloo0 = function($) {
    this[_requiredErrorText] = $
};
OO11l = function() {
    return this[_requiredErrorText]
};
O010l = function() {
    return this.O1o01
};
Ool01 = function() {};
oo101 = function() {
    var $ = this;
    this._l11O1Timer = setTimeout(function() {
        $.olollO()
    },
    1)
};
l0o01 = function() {
    if (!this.el) return;
    this[_removeCls](this.o00111);
    this[_removeCls](this.O001ol);
    this.el.title = "";
    if (this.O1l0l == false) switch (this[_errorMode]) {
    case "icon":
        this[_addCls](this.o00111);
        var $ = this[_getErrorIconEl]();
        if ($) $.title = this.errorText;
        break;
    case "border":
        this[_addCls](this.O001ol);
        this.el.title = this.errorText;
    default:
        this.OOO11l();
        break
    } else this.OOO11l();
    this[_doLayout]()
};
Ooo1o = function() {
    if (this.validateOnChanged) this[_tryValidate]();
    this[_fire]("valuechanged", {
        value: this[_getValue]()
    })
};
l0O0O = function(_, $) {
    this[_on]("valuechanged", _, $)
};
Ol1lo = function(_, $) {
    this[_on]("validation", _, $)
};
lOo11 = function(_) {
    var A = o0l01o[_superclass][_getAttrs][_call](this, _);
    mini[_ParseString](_, A, ["onvaluechanged", "onvalidation", "requiredErrorText", "errorMode"]);
    mini[_ParseBool](_, A, ["validateOnChanged", "validateOnLeave"]);
    var $ = _.getAttribute("required");
    if (!$) $ = _.required;
    if ($) A.required = $ != "false" ? true: false;
    return A
};
mini = {
    components: {},
    uids: {},
    ux: {},
    doc: document,
    window: window,
    isReady: false,
    byClass: function(_, $) {
        if (typeof $ == "string") $ = OoO1o0($);
        return jQuery("." + _, $)[0]
    },
    getComponents: function() {
        var _ = [];
        for (var A in mini.components) {
            var $ = mini.components[A];
            _.push($)
        }
        return _
    },
    get: function(_) {
        if (!_) return null;
        if (mini.isControl(_)) return _;
        if (typeof _ == "string") if (_.charAt(0) == "#") _ = _.substr(1);
        if (typeof _ == "string") return mini.components[_];
        else {
            var $ = mini.uids[_.uid];
            if ($ && $.el == _) return $
        }
        return null
    },
    getbyUID: function($) {
        return mini.uids[$]
    },
    findControls: function(E, B) {
        if (!E) return [];
        B = B || mini;
        var $ = [],
        D = mini.uids;
        for (var A in D) {
            var _ = D[A],
            C = E[_call](B, _);
            if (C === true || C === 1) {
                $.push(_);
                if (C === 1) break
            }
        }
        return $
    },
    getChildControls: function(A) {
        var _ = A.el ? A.el: A,
        $ = mini.findControls(function($) {
            if (!$.el || A == $) return false;
            if (lolo(_, $.el) && $[_within]) return true;
            return false
        });
        return $
    },
    emptyFn: function() {},
    createNameControls: function(A, F) {
        if (!A || !A.el) return;
        if (!F) F = "_";
        var C = A.el,
        $ = mini.findControls(function($) {
            if (!$.el || !$.name) return false;
            if (lolo(C, $.el)) return true;
            return false
        });
        for (var _ = 0,
        D = $.length; _ < D; _++) {
            var B = $[_],
            E = F + B.name;
            if (F === true) E = B.name[0].toUpperCase() + B.name.substring(1, B.name.length);
            A[E] = B
        }
    },
    getbyName: function(C, _) {
        var B = mini.isControl(_),
        A = _;
        if (_ && B) _ = _.el;
        _ = OoO1o0(_);
        _ = _ || document.body;
        var $ = this.findControls(function($) {
            if (!$.el) return false;
            if ($.name == C && lolo(_, $.el)) return 1;
            return false
        },
        this);
        if (B && $.length == 0 && A && A[_getbyName]) return A[_getbyName](C);
        return $[0]
    },
    getParams: function(C) {
        if (!C) C = location.href;
        C = C.split("?")[1];
        var B = {};
        if (C) {
            var A = C.split("&");
            for (var _ = 0,
            D = A.length; _ < D; _++) {
                var $ = A[_].split("=");
                try {
                    B[$[0]] = decodeURIComponent(unescape($[1]))
                } catch(E) {}
            }
        }
        return B
    },
    reg: function($) {
        this.components[$.id] = $;
        this.uids[$.uid] = $
    },
    unreg: function($) {
        delete mini.components[$.id];
        delete mini.uids[$.uid]
    },
    classes: {},
    uiClasses: {},
    getClass: function($) {
        if (!$) return null;
        return this.classes[$.toLowerCase()]
    },
    getClassByUICls: function($) {
        return this.uiClasses[$.toLowerCase()]
    },
    idPre: "mini-",
    idIndex: 1,
    newId: function($) {
        return ($ || this.idPre) + this.idIndex++
    },
    copyTo: function($, A) {
        if ($ && A) for (var _ in A) $[_] = A[_];
        return $
    },
    copyIf: function($, A) {
        if ($ && A) for (var _ in A) if (mini.isNull($[_])) $[_] = A[_];
        return $
    },
    createDelegate: function(_, $) {
        if (!_) return function() {};
        return function() {
            return _.apply($, arguments)
        }
    },
    isControl: function($) {
        return !! ($ && $.isControl)
    },
    isElement: function($) {
        return $ && $.appendChild
    },
    isDate: function($) {
        return $ && $.getFullYear
    },
    isArray: function($) {
        return $ && !!$.unshift
    },
    isNull: function($) {
        return $ === null || $ === undefined
    },
    isNumber: function($) {
        return ! isNaN($) && typeof $ == "number"
    },
    isEquals: function($, _) {
        if ($ !== 0 && _ !== 0) if ((mini.isNull($) || $ == "") && (mini.isNull(_) || _ == "")) return true;
        if ($ && _ && $.getFullYear && _.getFullYear) return $[_getTime]() === _[_getTime]();
        if (typeof $ == "object" && typeof _ == "object") return $ === _;
        return String($) === String(_)
    },
    forEach: function(E, D, B) {
        var _ = E.clone();
        for (var A = 0,
        C = _.length; A < C; A++) {
            var $ = _[A];
            if (D[_call](B, $, A, E) === false) break
        }
    },
    sort: function(A, _, $) {
        $ = $ || A;
        A.sort(_)
    },
    removeNode: function($) {
        jQuery($).remove()
    },
    elWarp: document.createElement("div")
};
if (typeof mini_debugger == "undefined") mini_debugger = true;
O1o0 = function(A, _) {
    _ = _.toLowerCase();
    if (!mini.classes[_]) {
        mini.classes[_] = A;
        A[_prototype].type = _
    }
    var $ = A[_prototype].uiCls;
    if (!mini.isNull($) && !mini.uiClasses[$]) mini.uiClasses[$] = A
};
lol01 = function(E, A, $) {
    if (typeof A != "function") return this;
    var D = E,
    C = D.prototype,
    _ = A[_prototype];
    if (D[_superclass] == _) return;
    D[_superclass] = _;
    D[_superclass][_constructor] = A;
    for (var B in _) C[B] = _[B];
    if ($) for (B in $) C[B] = $[B];
    return D
};
mini.copyTo(mini, {
    extend: lol01,
    regClass: O1o0,
    debug: false
});
mini.namespace = function(A) {
    if (typeof A != "string") return;
    A = A.split(".");
    var D = window;
    for (var $ = 0,
    B = A.length; $ < B; $++) {
        var C = A[$],
        _ = D[C];
        if (!_) _ = D[C] = {};
        D = _
    }
};
O0l0o = [];
OlO010 = function(_, $) {
    O0l0o.push([_, $]);
    if (!mini._EventTimer) mini._EventTimer = setTimeout(function() {
        o00O()
    },
    50)
};
o00O = function() {
    for (var $ = 0,
    _ = O0l0o.length; $ < _; $++) {
        var A = O0l0o[$];
        A[0][_call](A[1])
    }
    O0l0o = [];
    mini._EventTimer = null
};
l1O1l = function(C) {
    if (typeof C != "string") return null;
    var _ = C.split("."),
    D = null;
    for (var $ = 0,
    A = _.length; $ < A; $++) {
        var B = _[$];
        if (!D) D = window[B];
        else D = D[B];
        if (!D) break
    }
    return D
};
mini._getMap = function(name, obj) {
    if (!name) return null;
    var index = name[_indexOf](".");
    if (index == -1 && name[_indexOf]("[") == -1) return obj[name];
    if (index == (name.length - 1)) return obj[name];
    var s = "obj." + name;
    try {
        var v = eval(s)
    } catch(e) {
        return null
    }
    return v
};
mini._setMap = function(H, A, B) {
    if (!B) return;
    if (typeof H != "string") return;
    var E = H.split(".");
    function F(A, E, $, B) {
        var C = A[E];
        if (!C) C = A[E] = [];
        for (var _ = 0; _ <= $; _++) {
            var D = C[_];
            if (!D) if (B === null || B === undefined) D = C[_] = {};
            else D = C[_] = B
        }
        return A[E][$]
    }
    var $ = null;
    for (var _ = 0,
    G = E.length; _ <= G - 1; _++) {
        var H = E[_];
        if (_ == G - 1) {
            if (H[_indexOf]("]") == -1) B[H] = A;
            else {
                var C = H.split("["),
                D = C[0],
                I = parseInt(C[1]);
                F(B, D, I, "");
                B[D][I] = A
            }
            break
        }
        if (H[_indexOf]("]") == -1) {
            $ = B[H];
            if (_ <= G - 2 && $ == null) B[H] = $ = {};
            B = $
        } else {
            C = H.split("["),
            D = C[0],
            I = parseInt(C[1]);
            B = F(B, D, I)
        }
    }
    return A
};
mini.getAndCreate = function($) {
    if (!$) return null;
    if (typeof $ == "string") return mini.components[$];
    if (typeof $ == "object") if (mini.isControl($)) return $;
    else if (mini.isElement($)) return mini.uids[$.uid];
    else return mini.create($);
    return null
};
mini.create = function($) {
    if (!$) return null;
    if (mini.get($.id) === $) return $;
    var _ = this.getClass($.type);
    if (!_) return null;
    var A = new _();
    A[_set]($);
    return A
};
var _getBottomVisibleColumns = "getBottomVisibleColumns",
_setFrozenStartColumn = "setFrozenStartColumn",
_showCollapseButton = "showCollapseButton",
_showFolderCheckBox = "showFolderCheckBox",
_setFrozenEndColumn = "setFrozenEndColumn",
_getAncestorColumns = "getAncestorColumns",
_getFilterRowHeight = "getFilterRowHeight",
_checkSelectOnLoad = "checkSelectOnLoad",
_frozenStartColumn = "frozenStartColumn",
_allowResizeColumn = "allowResizeColumn",
_showExpandButtons = "showExpandButtons",
_requiredErrorText = "requiredErrorText",
_getMaxColumnLevel = "getMaxColumnLevel",
_isAncestorColumn = "isAncestorColumn",
_allowAlternating = "allowAlternating",
_getBottomColumns = "getBottomColumns",
_isShowRowDetail = "isShowRowDetail",
_allowCellSelect = "allowCellSelect",
_showAllCheckBox = "showAllCheckBox",
_frozenEndColumn = "frozenEndColumn",
_allowMoveColumn = "allowMoveColumn",
_allowSortColumn = "allowSortColumn",
_refreshOnExpand = "refreshOnExpand",
_showCloseButton = "showCloseButton",
_unFrozenColumns = "unFrozenColumns",
_getParentColumn = "getParentColumn",
_isVisibleColumn = "isVisibleColumn",
_getFooterHeight = "getFooterHeight",
_getHeaderHeight = "getHeaderHeight",
_createColumnId = "_createColumnId",
_getRowDetailEl = "getRowDetailEl",
_scrollIntoView = "scrollIntoView",
_setColumnWidth = "setColumnWidth",
_setCurrentCell = "setCurrentCell",
_allowRowSelect = "allowRowSelect",
_showSummaryRow = "showSummaryRow",
_showVGridLines = "showVGridLines",
_showHGridLines = "showHGridLines",
_checkRecursive = "checkRecursive",
_enableHotTrack = "enableHotTrack",
_popupMaxHeight = "popupMaxHeight",
_popupMinHeight = "popupMinHeight",
_refreshOnClick = "refreshOnClick",
_getColumnWidth = "getColumnWidth",
_getEditRowData = "getEditRowData",
_getParentNode = "getParentNode",
_removeNodeCls = "removeNodeCls",
_showRowDetail = "showRowDetail",
_hideRowDetail = "hideRowDetail",
_commitEditRow = "commitEditRow",
_beginEditCell = "beginEditCell",
_allowCellEdit = "allowCellEdit",
_decimalPlaces = "decimalPlaces",
_showFilterRow = "showFilterRow",
_dropGroupName = "dropGroupName",
_dragGroupName = "dragGroupName",
_showTreeLines = "showTreeLines",
_popupMaxWidth = "popupMaxWidth",
_popupMinWidth = "popupMinWidth",
_showMinButton = "showMinButton",
_showMaxButton = "showMaxButton",
_getChildNodes = "getChildNodes",
_getCellEditor = "getCellEditor",
_cancelEditRow = "cancelEditRow",
_getRowByValue = "getRowByValue",
_removeItemCls = "removeItemCls",
_createCellId = "_createCellId",
_createItemId = "_createItemId",
_setValueField = "setValueField",
_createPopup = "_createPopup",
_getAncestors = "getAncestors",
_collapseNode = "collapseNode",
_removeRowCls = "removeRowCls",
_getColumnBox = "getColumnBox",
_showCheckBox = "showCheckBox",
_autoCollapse = "autoCollapse",
_showTreeIcon = "showTreeIcon",
_checkOnClick = "checkOnClick",
_defaultValue = "defaultValue",
_resultAsData = "resultAsData",
_resultAsTree = "resultAsTree",
_ParseString = "_ParseString",
_getItemValue = "getItemValue",
_createRowId = "_createRowId",
_isAutoHeight = "isAutoHeight",
_findListener = "findListener",
_getRegionEl = "getRegionEl",
_removeClass = "removeClass",
_isFirstNode = "isFirstNode",
_getSelected = "getSelected",
_setSelected = "setSelected",
_multiSelect = "multiSelect",
_tabPosition = "tabPosition",
_columnWidth = "columnWidth",
_handlerSize = "handlerSize",
_allowSelect = "allowSelect",
_popupHeight = "popupHeight",
_contextMenu = "contextMenu",
_borderStyle = "borderStyle",
_parentField = "parentField",
_closeAction = "closeAction",
_rowIdField = "_rowIdField",
_allowResize = "allowResize",
_showToolbar = "showToolbar",
_deselectAll = "deselectAll",
_treeToArray = "treeToArray",
_eachColumns = "eachColumns",
_getItemText = "getItemText",
_isAutoWidth = "isAutoWidth",
_initEvents = "_initEvents",
_constructor = "constructor",
_addNodeCls = "addNodeCls",
_expandNode = "expandNode",
_setColumns = "setColumns",
_cancelEdit = "cancelEdit",
_moveColumn = "moveColumn",
_removeNode = "removeNode",
_setCurrent = "setCurrent",
_totalCount = "totalCount",
_popupWidth = "popupWidth",
_titleField = "titleField",
_valueField = "valueField",
_showShadow = "showShadow",
_showFooter = "showFooter",
_findParent = "findParent",
_getColumn = "_getColumn",
_ParseBool = "_ParseBool",
_clearEvent = "clearEvent",
_getCellBox = "getCellBox",
_selectText = "selectText",
_setVisible = "setVisible",
_isGrouping = "isGrouping",
_addItemCls = "addItemCls",
_isSelected = "isSelected",
_isReadOnly = "isReadOnly",
_superclass = "superclass",
_getRegion = "getRegion",
_isEditing = "isEditing",
_hidePopup = "hidePopup",
_removeRow = "removeRow",
_addRowCls = "addRowCls",
_increment = "increment",
_allowDrop = "allowDrop",
_pageIndex = "pageIndex",
_iconStyle = "iconStyle",
_errorMode = "errorMode",
_textField = "textField",
_groupName = "groupName",
_showEmpty = "showEmpty",
_emptyText = "emptyText",
_showModal = "showModal",
_getColumn = "getColumn",
_getHeight = "getHeight",
_ParseInt = "_ParseInt",
_showPopup = "showPopup",
_updateRow = "updateRow",
_deselects = "deselects",
_isDisplay = "isDisplay",
_setHeight = "setHeight",
_removeCls = "removeCls",
_prototype = "prototype",
_addClass = "addClass",
_isEquals = "isEquals",
_maxValue = "maxValue",
_minValue = "minValue",
_showBody = "showBody",
_tabAlign = "tabAlign",
_sizeList = "sizeList",
_pageSize = "pageSize",
_urlField = "urlField",
_readOnly = "readOnly",
_getWidth = "getWidth",
_isFrozen = "isFrozen",
_loadData = "loadData",
_deselect = "deselect",
_setValue = "setValue",
_validate = "validate",
_getAttrs = "getAttrs",
_setWidth = "setWidth",
_doUpdate = "doUpdate",
_doLayout = "doLayout",
_renderTo = "renderTo",
_setText = "setText",
_idField = "idField",
_getNode = "getNode",
_getItem = "getItem",
_repaint = "repaint",
_selects = "selects",
_setData = "setData",
_create = "_create",
_jsName = "jsName",
_getRow = "getRow",
_select = "select",
_within = "within",
_addCls = "addCls",
_render = "render",
_setXY = "setXY",
_call = "call",
_onValidation = "onValidation",
_onValueChanged = "onValueChanged",
_getErrorIconEl = "getErrorIconEl",
_getRequiredErrorText = "getRequiredErrorText",
_setRequiredErrorText = "setRequiredErrorText",
_getRequired = "getRequired",
_setRequired = "setRequired",
_getErrorText = "getErrorText",
_setErrorText = "setErrorText",
_getErrorMode = "getErrorMode",
_setErrorMode = "setErrorMode",
_getValidateOnLeave = "getValidateOnLeave",
_setValidateOnLeave = "setValidateOnLeave",
_getValidateOnChanged = "getValidateOnChanged",
_setValidateOnChanged = "setValidateOnChanged",
_getIsValid = "getIsValid",
_setIsValid = "setIsValid",
_isValid = "isValid",
_tryValidate = "_tryValidate",
_doQuery = "doQuery",
_getSearchField = "getSearchField",
_setSearchField = "setSearchField",
_getMinChars = "getMinChars",
_setMinChars = "setMinChars",
_setUrl = "setUrl",
_getRepeatDirection = "getRepeatDirection",
_setRepeatDirection = "setRepeatDirection",
_getRepeatLayout = "getRepeatLayout",
_setRepeatLayout = "setRepeatLayout",
_getRepeatItems = "getRepeatItems",
_setRepeatItems = "setRepeatItems",
_bindForm = "bindForm",
_bindField = "bindField",
__OnNodeMouseDown = "__OnNodeMouseDown",
_createNavBarTree = "createNavBarTree",
_getExpandOnLoad = "getExpandOnLoad",
_setExpandOnLoad = "setExpandOnLoad",
_getOwnerTree = "_getOwnerTree",
_getList = "getList",
_findNodes = "findNodes",
_expandPath = "expandPath",
_selectNode = "selectNode",
_getParentField = "getParentField",
_setParentField = "setParentField",
_getIdField = "getIdField",
_setIdField = "setIdField",
_getNodesField = "getNodesField",
_setNodesField = "setNodesField",
_getResultAsTree = "getResultAsTree",
_setResultAsTree = "setResultAsTree",
_getUrlField = "getUrlField",
_setUrlField = "setUrlField",
_getIconField = "getIconField",
_setIconField = "setIconField",
_getTextField = "getTextField",
_setTextField = "setTextField",
_getUrl = "getUrl",
_getData = "getData",
_load = "load",
_loadList = "loadList",
_doParseFields = "_doParseFields",
_destroy = "destroy",
_set = "set",
_createNavBarMenu = "createNavBarMenu",
_getOwnerMenu = "_getOwnerMenu",
_blur = "blur",
_focus = "focus",
__doSelectValue = "__doSelectValue",
_getPopupMaxHeight = "getPopupMaxHeight",
_setPopupMaxHeight = "setPopupMaxHeight",
_getPopupMinHeight = "getPopupMinHeight",
_setPopupMinHeight = "setPopupMinHeight",
_getPopupHeight = "getPopupHeight",
_setPopupHeight = "setPopupHeight",
_getAllowInput = "getAllowInput",
_setAllowInput = "setAllowInput",
_getValueField = "getValueField",
_setName = "setName",
_getValue = "getValue",
_getText = "getText",
_getInputText = "getInputText",
_removeItem = "removeItem",
_insertItem = "insertItem",
_showInput = "showInput",
_blurItem = "blurItem",
_hoverItem = "hoverItem",
_getItemEl = "getItemEl",
_getTextName = "getTextName",
_setTextName = "setTextName",
_getFormattedValue = "getFormattedValue",
_getFormValue = "getFormValue",
_getFormat = "getFormat",
_setFormat = "setFormat",
_getButtonHtml = "_getButtonHtml",
_onPreLoad = "onPreLoad",
_onLoadError = "onLoadError",
_onLoad = "onLoad",
_onBeforeLoad = "onBeforeLoad",
_onItemMouseDown = "onItemMouseDown",
_onItemClick = "onItemClick",
_OnItemMouseMove = "_OnItemMouseMove",
_OnItemMouseOut = "_OnItemMouseOut",
_OnItemClick = "_OnItemClick",
_clearSelect = "clearSelect",
_selectAll = "selectAll",
_getSelecteds = "getSelecteds",
_getMultiSelect = "getMultiSelect",
_setMultiSelect = "setMultiSelect",
_moveItem = "moveItem",
_removeItems = "removeItems",
_addItem = "addItem",
_addItems = "addItems",
_removeAll = "removeAll",
_findItems = "findItems",
_updateItem = "updateItem",
_getAt = "getAt",
_indexOf = "indexOf",
_getCount = "getCount",
_getFocusedIndex = "getFocusedIndex",
_getFocusedItem = "getFocusedItem",
_parseGroups = "parseGroups",
_expandGroup = "expandGroup",
_collapseGroup = "collapseGroup",
_toggleGroup = "toggleGroup",
_hideGroup = "hideGroup",
_showGroup = "showGroup",
_getActiveGroup = "getActiveGroup",
_getActiveIndex = "getActiveIndex",
_setActiveIndex = "setActiveIndex",
_getAutoCollapse = "getAutoCollapse",
_setAutoCollapse = "setAutoCollapse",
_getGroupBodyEl = "getGroupBodyEl",
_getGroupEl = "getGroupEl",
_getGroup = "getGroup",
_moveGroup = "moveGroup",
_removeGroup = "removeGroup",
_updateGroup = "updateGroup",
_addGroup = "addGroup",
_getGroups = "getGroups",
_setGroups = "setGroups",
_createGroup = "createGroup",
__fileError = "__fileError",
__on_upload_complete = "__on_upload_complete",
__on_upload_error = "__on_upload_error",
__on_upload_success = "__on_upload_success",
__on_file_queued = "__on_file_queued",
_startUpload = "startUpload",
_setUploadUrl = "setUploadUrl",
_setFlashUrl = "setFlashUrl",
_setQueueLimit = "setQueueLimit",
_setUploadLimit = "setUploadLimit",
_getButtonText = "getButtonText",
_setButtonText = "setButtonText",
_getTypesDescription = "getTypesDescription",
_setTypesDescription = "setTypesDescription",
_getLimitType = "getLimitType",
_setLimitType = "setLimitType",
_getPostParam = "getPostParam",
_setPostParam = "setPostParam",
_addPostParam = "addPostParam",
_setAjaxData = "setAjaxData",
_getValueFromSelect = "getValueFromSelect",
_setValueFromSelect = "setValueFromSelect",
_getAutoCheckParent = "getAutoCheckParent",
_setAutoCheckParent = "setAutoCheckParent",
_getShowFolderCheckBox = "getShowFolderCheckBox",
_setShowFolderCheckBox = "setShowFolderCheckBox",
_getShowTreeLines = "getShowTreeLines",
_setShowTreeLines = "setShowTreeLines",
_getShowTreeIcon = "getShowTreeIcon",
_setShowTreeIcon = "setShowTreeIcon",
_getCheckRecursive = "getCheckRecursive",
_setCheckRecursive = "setCheckRecursive",
_getDataField = "getDataField",
_setDataField = "setDataField",
_getSelectedNodes = "getSelectedNodes",
_getCheckedNodes = "getCheckedNodes",
_getSelectedNode = "getSelectedNode",
_getMinDate = "getMinDate",
_setMinDate = "setMinDate",
_getMaxDate = "getMaxDate",
_setMaxDate = "setMaxDate",
_getShowOkButton = "getShowOkButton",
_setShowOkButton = "setShowOkButton",
_getShowClearButton = "getShowClearButton",
_setShowClearButton = "setShowClearButton",
_getShowTodayButton = "getShowTodayButton",
_setShowTodayButton = "setShowTodayButton",
_getTimeFormat = "getTimeFormat",
_setTimeFormat = "setTimeFormat",
_getShowTime = "getShowTime",
_setShowTime = "setShowTime",
_getViewDate = "getViewDate",
_setViewDate = "setViewDate",
_getValueFormat = "getValueFormat",
_setValueFormat = "setValueFormat",
_getCalendar = "_getCalendar",
_setInputStyle = "setInputStyle",
_getShowClose = "getShowClose",
_setShowClose = "setShowClose",
_getSelectOnFocus = "getSelectOnFocus",
_setSelectOnFocus = "setSelectOnFocus",
_onTextChanged = "onTextChanged",
_onButtonMouseDown = "onButtonMouseDown",
_onButtonClick = "onButtonClick",
__fireBlur = "__fireBlur",
__doFocusCls = "__doFocusCls",
_getInputAsValue = "getInputAsValue",
_setInputAsValue = "setInputAsValue",
_setEnabled = "setEnabled",
_getMinLength = "getMinLength",
_setMinLength = "setMinLength",
_getMaxLength = "getMaxLength",
_setMaxLength = "setMaxLength",
_getEmptyText = "getEmptyText",
_setEmptyText = "setEmptyText",
_getTextEl = "getTextEl",
_doInputLayout = "_doInputLayout",
_getButtonsHTML = "_getButtonsHTML",
_setMenu = "setMenu",
_getPopupMinWidth = "getPopupMinWidth",
_getPopupMaxWidth = "getPopupMaxWidth",
_getPopupWidth = "getPopupWidth",
_setPopupMinWidth = "setPopupMinWidth",
_setPopupMaxWidth = "setPopupMaxWidth",
_setPopupWidth = "setPopupWidth",
_isShowPopup = "isShowPopup",
_doShowAtEl = "_doShowAtEl",
_syncShowPopup = "_syncShowPopup",
_getPopup = "getPopup",
_setPopup = "setPopup",
_getId = "getId",
_setId = "setId",
_un = "un",
_on = "on",
_fire = "fire",
_disableNode = "disableNode",
_enableNode = "enableNode",
_showNode = "showNode",
_hideNode = "hideNode",
_getLoadOnExpand = "getLoadOnExpand",
_setLoadOnExpand = "setLoadOnExpand",
_getExpandOnNodeClick = "getExpandOnNodeClick",
_setExpandOnNodeClick = "setExpandOnNodeClick",
_getExpandOnDblClick = "getExpandOnDblClick",
_getFolderIcon = "getFolderIcon",
_setFolderIcon = "setFolderIcon",
_getLeafIcon = "getLeafIcon",
_setLeafIcon = "setLeafIcon",
_getShowArrow = "getShowArrow",
_setShowArrow = "setShowArrow",
_getShowExpandButtons = "getShowExpandButtons",
_setShowExpandButtons = "setShowExpandButtons",
_getAllowSelect = "getAllowSelect",
_setAllowSelect = "setAllowSelect",
__OnNodeDblClick = "__OnNodeDblClick",
_OnCellClick = "_OnCellClick",
_OnCellMouseDown = "_OnCellMouseDown",
_tryToggleNode = "_tryToggleNode",
_tryToggleCheckNode = "_tryToggleCheckNode",
__OnCheckChanged = "__OnCheckChanged",
_doCheckNodeEl = "_doCheckNodeEl",
_doExpandCollapseNode = "_doExpandCollapseNode",
_getNodeIcon = "_getNodeIcon",
_getIconsField = "getIconsField",
_setIconsField = "setIconsField",
_getCheckBoxType = "getCheckBoxType",
_setCheckBoxType = "setCheckBoxType",
_getShowCheckBox = "getShowCheckBox",
_setShowCheckBox = "setShowCheckBox",
_getTreeColumn = "getTreeColumn",
_setTreeColumn = "setTreeColumn",
_getNodesTr = "_getNodesTr",
_getNodeEl = "_getNodeEl",
_createRowsHTML = "_createRowsHTML",
_createNodesHTML = "_createNodesHTML",
_createNodeHTML = "_createNodeHTML",
_renderCheckState = "_renderCheckState",
_createTreeColumn = "_createTreeColumn",
_isInLastNode = "isInLastNode",
_isInViewLastNode = "_isInViewLastNode",
_isViewLastNode = "_isViewLastNode",
_isViewFirstNode = "_isViewFirstNode",
_createDrawCellEvent = "_createDrawCellEvent",
_doUpdateTreeNodeEl = "_doUpdateTreeNodeEl",
_doMoveNodeEl = "_doMoveNodeEl",
_doRemoveNodeEl = "_doRemoveNodeEl",
_doAddNodeEl = "_doAddNodeEl",
__OnSourceMoveNode = "__OnSourceMoveNode",
__OnSourceRemoveNode = "__OnSourceRemoveNode",
__OnSourceAddNode = "__OnSourceAddNode",
__OnLoadNode = "__OnLoadNode",
__OnBeforeLoadNode = "__OnBeforeLoadNode",
_createSource = "_createSource",
_getDragText = "_getDragText",
_set_autoCreateNewID = "_set_autoCreateNewID",
_set_originalIdField = "_set_originalIdField",
_set_clearOriginals = "_set_clearOriginals",
_set_originals = "_set_originals",
_get_originals = "_get_originals",
_getHeaderContextMenu = "getHeaderContextMenu",
_setHeaderContextMenu = "setHeaderContextMenu",
_beforeOpenContentMenu = "_beforeOpenContentMenu",
_setPagerCls = "setPagerCls",
_setPagerStyle = "setPagerStyle",
_getShowTotalCount = "getShowTotalCount",
_setShowTotalCount = "setShowTotalCount",
_getShowPageIndex = "getShowPageIndex",
_setShowPageIndex = "setShowPageIndex",
_getShowPageSize = "getShowPageSize",
_setShowPageSize = "setShowPageSize",
_getSizeList = "getSizeList",
_setSizeList = "setSizeList",
_getShowPageInfo = "getShowPageInfo",
_setShowPageInfo = "setShowPageInfo",
_getShowReloadButton = "getShowReloadButton",
_setShowReloadButton = "setShowReloadButton",
_getTotalField = "getTotalField",
_setTotalField = "setTotalField",
_getSortOrderField = "getSortOrderField",
_setSortOrderField = "setSortOrderField",
_getSortFieldField = "getSortFieldField",
_setSortFieldField = "setSortFieldField",
_getPageSizeField = "getPageSizeField",
_setPageSizeField = "setPageSizeField",
_getPageIndexField = "getPageIndexField",
_setPageIndexField = "setPageIndexField",
_getSortOrder = "getSortOrder",
_setSortOrder = "setSortOrder",
_getSortField = "getSortField",
_setSortField = "setSortField",
_getTotalPage = "getTotalPage",
_getTotalCount = "getTotalCount",
_setTotalCount = "setTotalCount",
_getPageSize = "getPageSize",
_setPageSize = "setPageSize",
_getPageIndex = "getPageIndex",
_setPageIndex = "setPageIndex",
_getSortMode = "getSortMode",
_setSortMode = "setSortMode",
_getSelectOnLoad = "getSelectOnLoad",
_setSelectOnLoad = "setSelectOnLoad",
_getCheckSelectOnLoad = "getCheckSelectOnLoad",
_setCheckSelectOnLoad = "setCheckSelectOnLoad",
_sortBy = "sortBy",
_gotoPage = "gotoPage",
_reload = "reload",
_getAutoLoad = "getAutoLoad",
_setAutoLoad = "setAutoLoad",
_getAjaxOptions = "getAjaxOptions",
_setAjaxOptions = "setAjaxOptions",
_getAjaxMethod = "getAjaxMethod",
_setAjaxMethod = "setAjaxMethod",
_getAjaxAsync = "getAjaxAsync",
_setAjaxAsync = "setAjaxAsync",
_moveDown = "moveDown",
_moveUp = "moveUp",
_isAllowDrag = "isAllowDrag",
_getAllowDrop = "getAllowDrop",
_setAllowDrop = "setAllowDrop",
_getAllowDrag = "getAllowDrag",
_setAllowDrag = "setAllowDrag",
_getAllowLeafDropIn = "getAllowLeafDropIn",
_setAllowLeafDropIn = "setAllowLeafDropIn",
_getDragData = "_getDragData",
_isCellVisible = "_isCellVisible",
_margeCells = "margeCells",
_mergeCells = "mergeCells",
_mergeColumns = "mergeColumns",
_getAutoHideRowDetail = "getAutoHideRowDetail",
_setAutoHideRowDetail = "setAutoHideRowDetail",
_getRowDetailCellEl = "getRowDetailCellEl",
_getRowDetailEl = "_getRowDetailEl",
_toggleRowDetail = "toggleRowDetail",
_hideAllRowDetail = "hideAllRowDetail",
_showAllRowDetail = "showAllRowDetail",
_expandRowGroup = "expandRowGroup",
_collapseRowGroup = "collapseRowGroup",
_toggleRowGroup = "toggleRowGroup",
_expandGroups = "expandGroups",
_collapseGroups = "collapseGroups",
_getEditData = "getEditData",
_getEditingRow = "getEditingRow",
_getEditingRows = "getEditingRows",
_isNewRow = "isNewRow",
_isEditingRow = "isEditingRow",
_beginEditRow = "beginEditRow",
_getEditorOwnerRow = "getEditorOwnerRow",
_beginEditNextCell = "_beginEditNextCell",
_commitEdit = "commitEdit",
_isEditingCell = "isEditingCell",
_getCurrentCell = "getCurrentCell",
_getCreateOnEnter = "getCreateOnEnter",
_setCreateOnEnter = "setCreateOnEnter",
_getEditOnTabKey = "getEditOnTabKey",
_setEditOnTabKey = "setEditOnTabKey",
_getEditNextOnEnterKey = "getEditNextOnEnterKey",
_setEditNextOnEnterKey = "setEditNextOnEnterKey",
_getShowColumnsMenu = "getShowColumnsMenu",
_setShowColumnsMenu = "setShowColumnsMenu",
_getAllowMoveColumn = "getAllowMoveColumn",
_setAllowMoveColumn = "setAllowMoveColumn",
_getAllowSortColumn = "getAllowSortColumn",
_setAllowSortColumn = "setAllowSortColumn",
_getAllowResizeColumn = "getAllowResizeColumn",
_setAllowResizeColumn = "setAllowResizeColumn",
_getAllowCellValid = "getAllowCellValid",
_setAllowCellValid = "setAllowCellValid",
_getCellEditAction = "getCellEditAction",
_setCellEditAction = "setCellEditAction",
_getAllowCellEdit = "getAllowCellEdit",
_setAllowCellEdit = "setAllowCellEdit",
_getAllowCellSelect = "getAllowCellSelect",
_setAllowCellSelect = "setAllowCellSelect",
_getAllowRowSelect = "getAllowRowSelect",
_setAllowRowSelect = "setAllowRowSelect",
_getAllowUnselect = "getAllowUnselect",
_setAllowUnselect = "setAllowUnselect",
_getEnableHotTrack = "getEnableHotTrack",
_setEnableHotTrack = "setEnableHotTrack",
_getShowLoading = "getShowLoading",
_setShowLoading = "setShowLoading",
_focusRow = "focusRow",
_tryFocus = "_tryFocus",
_doRowSelect = "_doRowSelect",
_getRowBox = "getRowBox",
_getRowByID = "_getRowByID",
_getRowGroupRowsEl = "_getRowGroupRowsEl",
_getRowGroupEl = "_getRowGroupEl",
_doMoveRowEl = "_doMoveRowEl",
_doRemoveRowEl = "_doRemoveRowEl",
_doAddRowEl = "_doAddRowEl",
_doUpdateRowEl = "_doUpdateRowEl",
_unbindPager = "unbindPager",
_bindPager = "bindPager",
_setPager = "setPager",
_updatePagesInfo = "_updatePagesInfo",
__OnPageInfoChanged = "__OnPageInfoChanged",
__OnSourceMove = "__OnSourceMove",
__OnSourceRemove = "__OnSourceRemove",
__OnSourceUpdate = "__OnSourceUpdate",
__OnSourceAdd = "__OnSourceAdd",
__OnSourceFilter = "__OnSourceFilter",
__OnSourceSort = "__OnSourceSort",
__OnSourceLoadError = "__OnSourceLoadError",
__OnSourceLoadSuccess = "__OnSourceLoadSuccess",
__OnSourcePreLoad = "__OnSourcePreLoad",
__OnSourceBeforeLoad = "__OnSourceBeforeLoad",
_initData = "_initData",
_destroyEditors = "_destroyEditors",
_onCheckedChanged = "onCheckedChanged",
_onClick = "onClick",
_getTopMenu = "getTopMenu",
_hide = "hide",
_hideMenu = "hideMenu",
_showMenu = "showMenu",
_getMenu = "getMenu",
_setChildren = "setChildren",
_getGroupName = "getGroupName",
_setGroupName = "setGroupName",
_getChecked = "getChecked",
_setChecked = "setChecked",
_getCheckOnClick = "getCheckOnClick",
_setCheckOnClick = "setCheckOnClick",
_getIconPosition = "getIconPosition",
_setIconPosition = "setIconPosition",
_getIconStyle = "getIconStyle",
_setIconStyle = "setIconStyle",
_getIconCls = "getIconCls",
_setIconCls = "setIconCls",
_hasChildMenu = "_hasChildMenu",
_doUpdateIcon = "_doUpdateIcon",
_getHandlerSize = "getHandlerSize",
_setHandlerSize = "setHandlerSize",
_getAllowResize = "getAllowResize",
_setAllowResize = "setAllowResize",
_hidePane = "hidePane",
_showPane = "showPane",
_togglePane = "togglePane",
_collapsePane = "collapsePane",
_expandPane = "expandPane",
_getVertical = "getVertical",
_setVertical = "setVertical",
_getShowHandleButton = "getShowHandleButton",
_setShowHandleButton = "setShowHandleButton",
_updatePane = "updatePane",
_getPaneEl = "getPaneEl",
_setPaneControls = "setPaneControls",
_setPanes = "setPanes",
_getPane = "getPane",
_getPaneBox = "getPaneBox",
_updateMenu = "updateMenu",
_getColumns = "getColumns",
_getRows = "getRows",
_setRows = "setRows",
_isSelectedDate = "isSelectedDate",
_getTime = "getTime",
_setTime = "setTime",
_getSelectedDate = "getSelectedDate",
_setSelectedDates = "setSelectedDates",
_setSelectedDate = "setSelectedDate",
_getShowYearButtons = "getShowYearButtons",
_setShowYearButtons = "setShowYearButtons",
_getShowMonthButtons = "getShowMonthButtons",
_setShowMonthButtons = "setShowMonthButtons",
_getShowDaysHeader = "getShowDaysHeader",
_setShowDaysHeader = "setShowDaysHeader",
_getShowWeekNumber = "getShowWeekNumber",
_setShowWeekNumber = "setShowWeekNumber",
_getShowFooter = "getShowFooter",
_setShowFooter = "setShowFooter",
_getShowHeader = "getShowHeader",
_setShowHeader = "setShowHeader",
_getDateEl = "getDateEl",
_getShortWeek = "getShortWeek",
_getFirstDateOfMonth = "getFirstDateOfMonth",
_isWeekend = "isWeekend",
_setAjaxType = "setAjaxType",
__OnItemDrawCell = "__OnItemDrawCell",
_getNullItemText = "getNullItemText",
_setNullItemText = "setNullItemText",
_getShowNullItem = "getShowNullItem",
_setShowNullItem = "setShowNullItem",
_setDisplayField = "setDisplayField",
_eval = "_eval",
_getFalseValue = "getFalseValue",
_setFalseValue = "setFalseValue",
_getTrueValue = "getTrueValue",
_setTrueValue = "setTrueValue",
_clearData = "clearData",
_addLink = "addLink",
_add = "add",
_getAllowLimitValue = "getAllowLimitValue",
_setAllowLimitValue = "setAllowLimitValue",
_getChangeOnMousewheel = "getChangeOnMousewheel",
_setChangeOnMousewheel = "setChangeOnMousewheel",
_getDecimalPlaces = "getDecimalPlaces",
_setDecimalPlaces = "setDecimalPlaces",
_getIncrement = "getIncrement",
_setIncrement = "setIncrement",
_getMinValue = "getMinValue",
_setMinValue = "setMinValue",
_getMaxValue = "getMaxValue",
_setMaxValue = "setMaxValue",
_getShowAllCheckBox = "getShowAllCheckBox",
_setShowAllCheckBox = "setShowAllCheckBox",
_getRangeErrorText = "getRangeErrorText",
_setRangeErrorText = "setRangeErrorText",
_getRangeCharErrorText = "getRangeCharErrorText",
_setRangeCharErrorText = "setRangeCharErrorText",
_getRangeLengthErrorText = "getRangeLengthErrorText",
_setRangeLengthErrorText = "setRangeLengthErrorText",
_getMinErrorText = "getMinErrorText",
_setMinErrorText = "setMinErrorText",
_getMaxErrorText = "getMaxErrorText",
_setMaxErrorText = "setMaxErrorText",
_getMinLengthErrorText = "getMinLengthErrorText",
_setMinLengthErrorText = "setMinLengthErrorText",
_getMaxLengthErrorText = "getMaxLengthErrorText",
_setMaxLengthErrorText = "setMaxLengthErrorText",
_getDateErrorText = "getDateErrorText",
_setDateErrorText = "setDateErrorText",
_getIntErrorText = "getIntErrorText",
_setIntErrorText = "setIntErrorText",
_getFloatErrorText = "getFloatErrorText",
_setFloatErrorText = "setFloatErrorText",
_getUrlErrorText = "getUrlErrorText",
_setUrlErrorText = "setUrlErrorText",
_getEmailErrorText = "getEmailErrorText",
_setEmailErrorText = "setEmailErrorText",
_getVtype = "getVtype",
_setVtype = "setVtype",
_setReadOnly = "setReadOnly",
_getAjaxType = "getAjaxType",
_getAjaxData = "getAjaxData",
_getDefaultValue = "getDefaultValue",
_setDefaultValue = "setDefaultValue",
_getContextMenu = "getContextMenu",
_setContextMenu = "setContextMenu",
_getLoadingMsg = "getLoadingMsg",
_setLoadingMsg = "setLoadingMsg",
_loading = "loading",
_unmask = "unmask",
_mask = "mask",
_getAllowAnim = "getAllowAnim",
_setAllowAnim = "setAllowAnim",
_destroyChildren = "_destroyChildren",
_layoutChanged = "layoutChanged",
_canLayout = "canLayout",
_endUpdate = "endUpdate",
_beginUpdate = "beginUpdate",
_show = "show",
_getVisible = "getVisible",
_disable = "disable",
_enable = "enable",
_getEnabled = "getEnabled",
_getParent = "getParent",
_getReadOnly = "getReadOnly",
_getCls = "getCls",
_setCls = "setCls",
_getStyle = "getStyle",
_setStyle = "setStyle",
_getBorderStyle = "getBorderStyle",
_setBorderStyle = "setBorderStyle",
_getBox = "getBox",
_sizeChaned = "_sizeChaned",
_getTooltip = "getTooltip",
_setTooltip = "setTooltip",
_getJsName = "getJsName",
_setJsName = "setJsName",
_getEl = "getEl",
_isRender = "isRender",
_isFixedSize = "isFixedSize",
_getName = "getName",
_isVisibleRegion = "isVisibleRegion",
_isExpandRegion = "isExpandRegion",
_hideRegion = "hideRegion",
_showRegion = "showRegion",
_toggleRegion = "toggleRegion",
_collapseRegion = "collapseRegion",
_expandRegion = "expandRegion",
_updateRegion = "updateRegion",
_moveRegion = "moveRegion",
_removeRegion = "removeRegion",
_addRegion = "addRegion",
_setRegions = "setRegions",
_setRegionControls = "setRegionControls",
_getRegionBox = "getRegionBox",
_getRegionProxyEl = "getRegionProxyEl",
_getRegionSplitEl = "getRegionSplitEl",
_getRegionBodyEl = "getRegionBodyEl",
_getRegionHeaderEl = "getRegionHeaderEl",
_showAtEl = "showAtEl",
_showAtPos = "showAtPos",
_getShowInBody = "getShowInBody",
_setShowInBody = "setShowInBody",
_restore = "restore",
_max = "max",
_getShowMinButton = "getShowMinButton",
_setShowMinButton = "setShowMinButton",
_getShowMaxButton = "getShowMaxButton",
_setShowMaxButton = "setShowMaxButton",
_getMaxHeight = "getMaxHeight",
_setMaxHeight = "setMaxHeight",
_getMaxWidth = "getMaxWidth",
_setMaxWidth = "setMaxWidth",
_getMinHeight = "getMinHeight",
_setMinHeight = "setMinHeight",
_getMinWidth = "getMinWidth",
_setMinWidth = "setMinWidth",
_getShowModal = "getShowModal",
_setShowModal = "setShowModal",
_getParentBox = "getParentBox",
__OnShowPopup = "__OnShowPopup",
__OnGridRowClickChanged = "__OnGridRowClickChanged",
_getGrid = "getGrid",
_setGrid = "setGrid",
_doClick = "doClick",
_getPlain = "getPlain",
_setPlain = "setPlain",
_getTarget = "getTarget",
_setTarget = "setTarget",
_getHref = "getHref",
_setHref = "setHref",
_onPageChanged = "onPageChanged",
_update = "update",
_expand = "expand",
_collapse = "collapse",
_toggle = "toggle",
_setExpanded = "setExpanded",
_getMaskOnLoad = "getMaskOnLoad",
_setMaskOnLoad = "setMaskOnLoad",
_getRefreshOnExpand = "getRefreshOnExpand",
_setRefreshOnExpand = "setRefreshOnExpand",
_getIFrameEl = "getIFrameEl",
_getFooterEl = "getFooterEl",
_getBodyEl = "getBodyEl",
_getToolbarEl = "getToolbarEl",
_getHeaderEl = "getHeaderEl",
_setFooter = "setFooter",
_setToolbar = "setToolbar",
_set_bodyParent = "set_bodyParent",
_setBody = "setBody",
_getButton = "getButton",
_removeButton = "removeButton",
_updateButton = "updateButton",
_addButton = "addButton",
_createButton = "createButton",
_getShowToolbar = "getShowToolbar",
_setShowToolbar = "setShowToolbar",
_getShowCollapseButton = "getShowCollapseButton",
_setShowCollapseButton = "setShowCollapseButton",
_getCloseAction = "getCloseAction",
_setCloseAction = "setCloseAction",
_getShowCloseButton = "getShowCloseButton",
_setShowCloseButton = "setShowCloseButton",
_doTools = "_doTools",
_getTitle = "getTitle",
_setTitle = "setTitle",
_doTitle = "_doTitle",
_getFooterCls = "getFooterCls",
_setFooterCls = "setFooterCls",
_getToolbarCls = "getToolbarCls",
_setToolbarCls = "setToolbarCls",
_getBodyCls = "getBodyCls",
_setBodyCls = "setBodyCls",
_getHeaderCls = "getHeaderCls",
_setHeaderCls = "setHeaderCls",
_getFooterStyle = "getFooterStyle",
_setFooterStyle = "setFooterStyle",
_getToolbarStyle = "getToolbarStyle",
_setToolbarStyle = "setToolbarStyle",
_getBodyStyle = "getBodyStyle",
_setBodyStyle = "setBodyStyle",
_getHeaderStyle = "getHeaderStyle",
_setHeaderStyle = "setHeaderStyle",
_getToolbarHeight = "getToolbarHeight",
_getBodyHeight = "getBodyHeight",
_getViewportHeight = "getViewportHeight",
_getViewportWidth = "getViewportWidth",
_stopLayout = "_stopLayout",
_deferLayout = "deferLayout",
_doVisibleEls = "_doVisibleEls",
_beginEdit = "beginEdit",
_isEditingNode = "isEditingNode",
_setNodeIconCls = "setNodeIconCls",
_setNodeText = "setNodeText",
_getRowHeight = "_getRowHeight",
_parseItems = "parseItems",
_startScrollMove = "_startScrollMove",
__OnBottomMouseDown = "__OnBottomMouseDown",
__OnTopMouseDown = "__OnTopMouseDown",
_onItemSelect = "onItemSelect",
__OnItemSelect = "_OnItemSelect",
_getHideOnClick = "getHideOnClick",
_setHideOnClick = "setHideOnClick",
_getShowNavArrow = "getShowNavArrow",
_setShowNavArrow = "setShowNavArrow",
_getSelectedItem = "getSelectedItem",
_setSelectedItem = "setSelectedItem",
_getAllowSelectItem = "getAllowSelectItem",
_setAllowSelectItem = "setAllowSelectItem",
_getGroupItems = "getGroupItems",
_removeItemAt = "removeItemAt",
_getItems = "getItems",
_setItems = "setItems",
_hasShowItemMenu = "hasShowItemMenu",
_showItemMenu = "showItemMenu",
_hideItems = "hideItems",
_isVertical = "isVertical",
_getbyName = "getbyName",
_onActiveChanged = "onActiveChanged",
_onCloseClick = "onCloseClick",
_onBeforeCloseClick = "onBeforeCloseClick",
_getTabByEvent = "getTabByEvent",
_getShowBody = "getShowBody",
_setShowBody = "setShowBody",
_getActiveTab = "getActiveTab",
_activeTab = "activeTab",
_getTabIFrameEl = "getTabIFrameEl",
_getTabBodyEl = "getTabBodyEl",
_getTabEl = "getTabEl",
_getTab = "getTab",
_setTabPosition = "setTabPosition",
_setTabAlign = "setTabAlign",
__handleIFrameOverflow = "_handleIFrameOverflow",
_getTabRows = "getTabRows",
_reloadTab = "reloadTab",
_loadTab = "loadTab",
__cancelLoadTabs = "_cancelLoadTabs",
_updateTab = "updateTab",
_moveTab = "moveTab",
_removeTab = "removeTab",
_addTab = "addTab",
_getTabs = "getTabs",
_setTabs = "setTabs",
_setTabControls = "setTabControls",
_getTitleField = "getTitleField",
_setTitleField = "setTitleField",
_getNameField = "getNameField",
_setNameField = "setNameField",
_createTab = "createTab";
o111oO = function() {
    this.oo11 = {};
    this.uid = mini.newId(this.o0O0l0);
    this._id = this.uid;
    if (!this.id) this.id = this.uid;
    mini.reg(this)
};
o111oO[_prototype] = {
    isControl: true,
    id: null,
    o0O0l0: "mini-",
    loOOlO: false,
    ooOol1: true
};
lO001 = o111oO[_prototype];
lO001[_destroy] = l10OO;
lO001[_getId] = O1loo;
lO001[_setId] = lO010;
lO001[_findListener] = o01OO;
lO001[_un] = lOo01;
lO001[_on] = Olll1;
lO001[_fire] = OoOl0;
lO001[_set] = l1oll;
O11lO0 = function() {
    O11lO0[_superclass][_constructor][_call](this);
    this[_create]();
    this.el.uid = this.uid;
    this[_initEvents]();
    if (this._clearBorder) this.el.style.borderWidth = "0";
    this[_addCls](this.uiCls);
    this[_setWidth](this.width);
    this[_setHeight](this.height);
    this.el.style.display = this.visible ? this.oO00l: "none"
};
lol01(O11lO0, o111oO, {
    jsName: null,
    width: "",
    height: "",
    visible: true,
    readOnly: false,
    enabled: true,
    tooltip: "",
    O1oolo: "mini-readonly",
    lo1l0: "mini-disabled",
    name: "",
    _clearBorder: true,
    oO00l: "",
    O1l1l: true,
    allowAnim: true,
    l110l1: "mini-mask-loading",
    loadingMsg: "Loading...",
    contextMenu: null,
    ajaxData: null,
    ajaxType: "",
    dataField: ""
});
OlO0o = O11lO0[_prototype];
OlO0o[_getAttrs] = l1loO;
OlO0o[_getDataField] = oOo0o1;
OlO0o[_setDataField] = looO0;
OlO0o.oolloO = lll0O;
OlO0o[_getAjaxType] = O0o0;
OlO0o[_setAjaxType] = lO00;
OlO0o[_getAjaxData] = ool00;
OlO0o[_setAjaxData] = o0Ool;
OlO0o[_getValue] = o10lO;
OlO0o[_setValue] = o111O;
OlO0o[_getDefaultValue] = OOo10;
OlO0o[_setDefaultValue] = l0o0l;
OlO0o[_getContextMenu] = oo11O;
OlO0o[_setContextMenu] = l010;
OlO0o.O01o = oO0ol;
OlO0o.l00oo = O0011;
OlO0o[_getLoadingMsg] = lo10;
OlO0o[_setLoadingMsg] = lo1lol;
OlO0o[_loading] = ll01;
OlO0o[_unmask] = OO0ol;
OlO0o[_mask] = OO01l;
OlO0o.oooll0 = ll00;
OlO0o[_getAllowAnim] = oOlOo;
OlO0o[_setAllowAnim] = lloll;
OlO0o[_blur] = lll11;
OlO0o[_focus] = l0OO1;
OlO0o[_destroy] = oOOO0;
OlO0o[_destroyChildren] = l0O1O;
OlO0o[_layoutChanged] = llolO;
OlO0o[_doLayout] = l0Ooo0;
OlO0o[_canLayout] = OllO0o;
OlO0o[_doUpdate] = O01lO;
OlO0o[_endUpdate] = ol0lO;
OlO0o[_beginUpdate] = ll0O1;
OlO0o[_isDisplay] = l0lO;
OlO0o[_hide] = loO01;
OlO0o[_show] = lO0O;
OlO0o[_getVisible] = ooO0o;
OlO0o[_setVisible] = oO1ol;
OlO0o[_disable] = o0Ol1;
OlO0o[_enable] = o111;
OlO0o[_getEnabled] = lO01o;
OlO0o[_setEnabled] = Olo1O;
OlO0o[_isReadOnly] = OolOlo;
OlO0o[_getParent] = l110;
OlO0o[_getReadOnly] = ol10;
OlO0o[_setReadOnly] = oo0l;
OlO0o.OOo1o = olloo;
OlO0o[_removeCls] = lOllo;
OlO0o[_addCls] = Ol1o0;
OlO0o[_getCls] = lO1O0;
OlO0o[_setCls] = Olo1o;
OlO0o[_getStyle] = oO11O;
OlO0o[_setStyle] = oO1oO;
OlO0o[_getBorderStyle] = oOO1O;
OlO0o[_setBorderStyle] = lOoo;
OlO0o[_getBox] = ol0oO1;
OlO0o[_getHeight] = llO0o;
OlO0o[_setHeight] = O001;
OlO0o[_getWidth] = ooOO0;
OlO0o[_setWidth] = o1O0o;
OlO0o[_sizeChaned] = lOOoO;
OlO0o[_getTooltip] = OOO0l0;
OlO0o[_setTooltip] = o010;
OlO0o[_getJsName] = O1O0O;
OlO0o[_setJsName] = Oo0o;
OlO0o[_getEl] = ooOll1;
OlO0o[_render] = o00lo;
OlO0o[_isRender] = ll1lO0;
OlO0o[_isFixedSize] = Oo011;
OlO0o[_isAutoWidth] = O1110;
OlO0o[_isAutoHeight] = oO0lo;
OlO0o[_getName] = o1Ool;
OlO0o[_setName] = O0OOo;
OlO0o[_within] = OOOl11;
OlO0o[_initEvents] = o001O;
OlO0o[_create] = o0O11;
mini._attrs = null;
mini.regHtmlAttr = function(_, $) {
    if (!_) return;
    if (!$) $ = "string";
    if (!mini._attrs) mini._attrs = [];
    mini._attrs.push([_, $])
};
__mini_setControls = function($, B, C) {
    B = B || this.Ooo00;
    C = C || this;
    if (!$) $ = [];
    if (!mini.isArray($)) $ = [$];
    for (var _ = 0,
    D = $.length; _ < D; _++) {
        var A = $[_];
        if (typeof A == "string") {
            if (A[_indexOf]("#") == 0) A = OoO1o0(A)
        } else if (mini.isElement(A));
        else {
            A = mini.getAndCreate(A);
            A = A.el
        }
        if (!A) continue;
        mini.append(B, A)
    }
    mini.parse(B);
    C[_doLayout]();
    return C
};
mini.Container = function() {
    mini.Container[_superclass][_constructor][_call](this);
    this.Ooo00 = this.el
};
lol01(mini.Container, O11lO0, {
    setControls: __mini_setControls,
    getContentEl: function() {
        return this.Ooo00
    },
    getBodyEl: function() {
        return this.Ooo00
    }
});
o0l01o = function() {
    o0l01o[_superclass][_constructor][_call](this)
};
lol01(o0l01o, O11lO0, {
    required: false,
    requiredErrorText: "This field is required.",
    l0o10: "mini-required",
    errorText: "",
    o00111: "mini-error",
    O001ol: "mini-invalid",
    errorMode: "icon",
    validateOnChanged: true,
    validateOnLeave: true,
    O1l0l: true,
    errorIconEl: null
});
o0l0o = o0l01o[_prototype];
o0l0o[_getAttrs] = lOo11;
o0l0o[_onValidation] = Ol1lo;
o0l0o[_onValueChanged] = l0O0O;
o0l0o.l000OO = Ooo1o;
o0l0o.olollO = l0o01;
o0l0o.l11O1 = oo101;
o0l0o.OOO11l = Ool01;
o0l0o[_getErrorIconEl] = O010l;
o0l0o[_getRequiredErrorText] = OO11l;
o0l0o[_setRequiredErrorText] = lloo0;
o0l0o[_getRequired] = OllO1;
o0l0o[_setRequired] = O0l1l;
o0l0o[_getErrorText] = Oo11O;
o0l0o[_setErrorText] = ol100;
o0l0o[_getErrorMode] = O1o10;
o0l0o[_setErrorMode] = l1O11;
o0l0o[_getValidateOnLeave] = llloo;
o0l0o[_setValidateOnLeave] = l1lOo;
o0l0o[_getValidateOnChanged] = l0oOl1;
o0l0o[_setValidateOnChanged] = l1011;
o0l0o[_getIsValid] = lo101;
o0l0o[_setIsValid] = oll1O;
o0l0o[_isValid] = lol11;
o0l0o[_validate] = OoOO1;
o0l0o[_tryValidate] = l1ll0;
l0OlOo = function() {
    this.data = [];
    this.ololO0 = [];
    l0OlOo[_superclass][_constructor][_call](this);
    this[_doUpdate]()
};
lol01(l0OlOo, o0l01o, {
    defaultValue: "",
    value: "",
    valueField: "id",
    textField: "text",
    dataField: "",
    delimiter: ",",
    data: null,
    url: "",
    o11o: "mini-list-item",
    looOl: "mini-list-item-hover",
    _looO: "mini-list-item-selected",
    uiCls: "mini-list",
    name: "",
    ooolo: null,
    ajaxData: null,
    Ooo0O: null,
    ololO0: [],
    multiSelect: false,
    lO0o1: true
});
O1OOl = l0OlOo[_prototype];
O1OOl[_getAttrs] = lo11o;
O1OOl[_onPreLoad] = o1lOl;
O1OOl[_onLoadError] = oOo00;
O1OOl[_onLoad] = O1oOO;
O1OOl[_onBeforeLoad] = OOl00;
O1OOl[_onItemMouseDown] = Oo0OO;
O1OOl[_onItemClick] = ool0O;
O1OOl[_OnItemMouseMove] = Ooo10;
O1OOl[_OnItemMouseOut] = O1llO;
O1OOl[_OnItemClick] = O0O01;
O1OOl.O0Ol = l1O0O;
O1OOl.oOl0O = OO001;
O1OOl.O1O11 = lol1O;
O1OOl.lO1O1O = O0l11;
O1OOl.oll1OO = OolOO;
O1OOl.Oll1 = l00oO;
O1OOl.l011 = ooOoo;
O1OOl.o110oo = o0oo0;
O1OOl.Oo00O = o01oo;
O1OOl.loO1oO = oO0o1;
O1OOl.O0O1l = OOO1l;
O1OOl.lO0l0 = llo1;
O1OOl.oo11oO = o00Ol;
O1OOl.OO0l = Ol0lO;
O1OOl.O000lO = ll11o;
O1OOl[_deselects] = o1oOo;
O1OOl[_selects] = O01oO;
O1OOl[_clearSelect] = oo11l;
O1OOl[_deselectAll] = oOOoO;
O1OOl[_selectAll] = O1ooO;
O1OOl[_deselect] = loo0l;
O1OOl[_select] = o1o0l;
O1OOl[_getSelected] = OOolO;
O1OOl[_setSelected] = lO11o;
O1OOl[_getSelecteds] = OOolOs;
O1OOl[_isSelected] = O00oo;
O1OOl[_getMultiSelect] = o10ol;
O1OOl[_setMultiSelect] = lo10l;
O1OOl.loO10 = o0OOl;
O1OOl[_moveItem] = lo01l;
O1OOl[_removeItem] = O0Ool;
O1OOl[_removeItems] = O0Ools;
O1OOl[_addItem] = O1Ool;
O1OOl[_addItems] = O1Ools;
O1OOl[_removeAll] = O01O1;
O1OOl[_findItems] = ll0oo;
O1OOl.o00o0 = loolO;
O1OOl[_getItemText] = Oo0O1;
O1OOl[_getItemValue] = o11oo;
O1OOl[_getTextField] = ll111;
O1OOl[_setTextField] = llo11;
O1OOl[_getValueField] = Ol1l0;
O1OOl[_setValueField] = l00O;
O1OOl[_getFormValue] = olOlo;
O1OOl[_getValue] = O0OoO;
O1OOl[_setValue] = oo1O0;
O1OOl.OoO0l1 = lO1Ol;
O1OOl[_getUrl] = O0101O;
O1OOl[_setUrl] = Oloo;
O1OOl[_getData] = l0ll1;
O1OOl[_setData] = Oo110;
O1OOl[_loadData] = ll01l;
O1OOl[_load] = lolOo;
O1OOl[_updateItem] = O0o0o;
O1OOl[_getAt] = oOoo1;
O1OOl[_indexOf] = O1Oll;
O1OOl[_getCount] = ooOlO;
O1OOl[_getItem] = l1o1O;
O1OOl[_scrollIntoView] = o1Oo0;
O1OOl[_getFocusedIndex] = ooO0O;
O1OOl[_getFocusedItem] = loloO;
O1OOl.Ooolo0 = l0O00;
O1OOl.lo00 = l0oOo;
O1OOl[_getItemEl] = l1o1OEl;
O1OOl[_removeItemCls] = O0OolCls;
O1OOl[_addItemCls] = O1OolCls;
O1OOl.lllo0o = l1o1OByEvent;
O1OOl[_setName] = OOo01;
O1OOl[_destroy] = oO0oO;
O1OOl[_initEvents] = l1O1O;
O1OOl[_create] = l111;
O1OOl[_set] = OO010;
mini._Layouts = {};
mini.layout = function($, _) {
    if (!document.body) return;
    function A(C) {
        if (!C) return;
        var D = mini.get(C);
        if (D) {
            if (D[_doLayout]) if (!mini._Layouts[D.uid]) {
                mini._Layouts[D.uid] = D;
                if (_ !== false || D[_isFixedSize]() == false) D[_doLayout](false);
                delete mini._Layouts[D.uid]
            }
        } else {
            var E = C.childNodes;
            if (E) for (var $ = 0,
            F = E.length; $ < F; $++) {
                var B = E[$];
                A(B)
            }
        }
    }
    if (!$) $ = document.body;
    A($);
    if ($ == document.body) mini.layoutIFrames()
};
mini.applyTo = function(_) {
    _ = OoO1o0(_);
    if (!_) return this;
    if (mini.get(_)) throw new Error("not applyTo a mini control");
    var $ = this[_getAttrs](_);
    delete $._applyTo;
    if (mini.isNull($[_defaultValue]) && !mini.isNull($.value)) $[_defaultValue] = $.value;
    if (mini.isNull($.defaultText) && !mini.isNull($.text)) $.defaultText = $.text;
    var A = _.parentNode;
    if (A && this.el != _) A.replaceChild(this.el, _);
    this[_set]($);
    this.oolloO(_);
    return this
};
mini.O0ool1 = function(G) {
    var F = G.nodeName.toLowerCase();
    if (!F) return;
    var B = G.className;
    if (B) {
        var $ = mini.get(G);
        if (!$) {
            var H = B.split(" ");
            for (var E = 0,
            C = H.length; E < C; E++) {
                var A = H[E],
                I = mini.getClassByUICls(A);
                if (I) {
                    o110(G, A);
                    var D = new I();
                    mini.applyTo[_call](D, G);
                    G = D.el;
                    break
                }
            }
        }
    }
    if (F == "select" || ooll(G, "mini-menu") || ooll(G, "mini-datagrid") || ooll(G, "mini-treegrid") || ooll(G, "mini-tree") || ooll(G, "mini-button") || ooll(G, "mini-textbox") || ooll(G, "mini-buttonedit")) return;
    var J = mini[_getChildNodes](G, true);
    for (E = 0, C = J.length; E < C; E++) {
        var _ = J[E];
        if (_.nodeType == 1) if (_.parentNode == G) mini.O0ool1(_)
    }
};
mini._Removes = [];
mini.parse = function($) {
    if (typeof $ == "string") {
        var A = $;
        $ = OoO1o0(A);
        if (!$) $ = document.body
    }
    if ($ && !mini.isElement($)) $ = $.el;
    if (!$) $ = document.body;
    var _ = Oolo;
    if (isIE) Oolo = false;
    mini.O0ool1($);
    Oolo = _;
    mini.layout($)
};
mini[_ParseString] = function(B, A, E) {
    for (var $ = 0,
    D = E.length; $ < D; $++) {
        var C = E[$],
        _ = mini.getAttr(B, C);
        if (_) A[C] = _
    }
};
mini[_ParseBool] = function(B, A, E) {
    for (var $ = 0,
    D = E.length; $ < D; $++) {
        var C = E[$],
        _ = mini.getAttr(B, C);
        if (_) A[C] = _ == "true" ? true: false
    }
};
mini[_ParseInt] = function(B, A, E) {
    for (var $ = 0,
    D = E.length; $ < D; $++) {
        var C = E[$],
        _ = parseInt(mini.getAttr(B, C));
        if (!isNaN(_)) A[C] = _
    }
};
mini.O011 = function(el) {
    var columns = [],
    cs = mini[_getChildNodes](el);
    for (var i = 0,
    l = cs.length; i < l; i++) {
        var node = cs[i],
        jq = jQuery(node),
        column = {},
        editor = null,
        filter = null,
        subCs = mini[_getChildNodes](node);
        if (subCs) for (var ii = 0,
        li = subCs.length; ii < li; ii++) {
            var subNode = subCs[ii],
            property = jQuery(subNode).attr("property");
            if (!property) continue;
            property = property.toLowerCase();
            if (property == "columns") {
                column.columns = mini.O011(subNode);
                jQuery(subNode).remove()
            }
            if (property == "editor" || property == "filter") {
                var className = subNode.className,
                classes = className.split(" ");
                for (var i3 = 0,
                l3 = classes.length; i3 < l3; i3++) {
                    var cls = classes[i3],
                    clazz = mini.getClassByUICls(cls);
                    if (clazz) {
                        var ui = new clazz();
                        if (property == "filter") {
                            filter = ui[_getAttrs](subNode);
                            filter.type = ui.type
                        } else {
                            editor = ui[_getAttrs](subNode);
                            editor.type = ui.type
                        }
                        break
                    }
                }
                jQuery(subNode).remove()
            }
        }
        column.header = node.innerHTML;
        mini[_ParseString](node, column, ["name", "header", "field", "editor", "filter", "renderer", "width", "type", "renderer", "headerAlign", "align", "headerCls", "cellCls", "headerStyle", "cellStyle", "displayField", "dateFormat", "listFormat", "mapFormat", "trueValue", "falseValue", "dataType", "vtype", "currencyUnit", "summaryType", "summaryRenderer", "groupSummaryType", "groupSummaryRenderer", "defaultValue", "defaultText", "decimalPlaces", "data-options"]);
        mini[_ParseBool](node, column, ["visible", "readOnly", "allowSort", "allowResize", "allowMove", "allowDrag", "autoShowPopup", "unique", "autoEscape"]);
        if (editor) column.editor = editor;
        if (filter) column.filter = filter;
        if (column.dataType) column.dataType = column.dataType.toLowerCase();
        if (column[_defaultValue] === "true") column[_defaultValue] = true;
        if (column[_defaultValue] === "false") column[_defaultValue] = false;
        columns.push(column);
        var options = column["data-options"];
        if (options) {
            options = eval("(" + options + ")");
            if (options) mini.copyTo(column, options)
        }
    }
    return columns
};
mini.O0111l = {};
mini[_getColumn] = function($) {
    var _ = mini.O0111l[$.toLowerCase()];
    if (!_) return {};
    return _()
};
mini.IndexColumn = function($) {
    return mini.copyTo({
        width: 30,
        cellCls: "",
        align: "center",
        draggable: false,
        allowDrag: true,
        init: function($) {
            $[_on]("addrow", this.__OnIndexChanged, this);
            $[_on]("removerow", this.__OnIndexChanged, this);
            $[_on]("moverow", this.__OnIndexChanged, this);
            if ($.isTree) {
                $[_on]("loadnode", this.__OnIndexChanged, this);
                this._gridUID = $.uid;
                this[_rowIdField] = "_id"
            }
        },
        getNumberId: function($) {
            return this._gridUID + "$number$" + $[this._rowIdField]
        },
        createNumber: function($, _) {
            if (mini.isNull($[_pageIndex])) return _ + 1;
            else return ($[_pageIndex] * $[_pageSize]) + _ + 1
        },
        renderer: function(A) {
            var $ = A.sender;
            if (this.draggable) {
                if (!A.cellStyle) A.cellStyle = "";
                A.cellStyle += ";cursor:move;"
            }
            var _ = "<div id=\"" + this.getNumberId(A.record) + "\">";
            if (mini.isNull($[_getPageIndex])) _ += A.rowIndex + 1;
            else _ += ($[_getPageIndex]() * $[_getPageSize]()) + A.rowIndex + 1;
            _ += "</div>";
            return _
        },
        __OnIndexChanged: function(F) {
            var $ = F.sender,
            C = $.toArray();
            for (var A = 0,
            D = C.length; A < D; A++) {
                var _ = C[A],
                E = this.getNumberId(_),
                B = document.getElementById(E);
                if (B) B.innerHTML = this.createNumber($, A)
            }
        }
    },
    $)
};
mini.O0111l["indexcolumn"] = mini.IndexColumn;
mini.CheckColumn = function($) {
    return mini.copyTo({
        width: 30,
        cellCls: "mini-checkcolumn",
        headerCls: "mini-checkcolumn",
        _multiRowSelect: true,
        header: function($) {
            var A = this.uid + "checkall",
            _ = "<input type=\"checkbox\" id=\"" + A + "\" />";
            if (this[_multiSelect] == false) _ = "";
            return _
        },
        getCheckId: function($) {
            return this._gridUID + "$checkcolumn$" + $[this._rowIdField]
        },
        init: function($) {
            $[_on]("selectionchanged", this.oO0l1, this);
            $[_on]("HeaderCellClick", this.ooO1o1, this)
        },
        renderer: function(C) {
            var B = this.getCheckId(C.record),
            _ = C.sender[_isSelected] ? C.sender[_isSelected](C.record) : false,
            A = "checkbox",
            $ = C.sender;
            if ($[_getMultiSelect]() == false) A = "radio";
            return "<input type=\"" + A + "\" id=\"" + B + "\" " + (_ ? "checked": "") + " hidefocus style=\"outline:none;\" onclick=\"return false\"/>"
        },
        ooO1o1: function(B) {
            var $ = B.sender;
            if (B.column != this) return;
            var A = $.uid + "checkall",
            _ = document.getElementById(A);
            if (_) {
                if ($[_getMultiSelect]()) {
                    if (_.checked) $[_selectAll]();
                    else $[_deselectAll]()
                } else {
                    $[_deselectAll]();
                    if (_.checked) $[_select](0)
                }
                $[_fire]("checkall")
            }
        },
        oO0l1: function(H) {
            var $ = H.sender,
            C = $.toArray();
            for (var A = 0,
            E = C.length; A < E; A++) {
                var _ = C[A],
                G = $[_isSelected](_),
                F = $.uid + "$checkcolumn$" + _[$._rowIdField],
                B = document.getElementById(F);
                if (B) B.checked = G
            }
            var D = this;
            if (!this._timer) this._timer = setTimeout(function() {
                D._doCheckState($);
                D._timer = null
            },
            10)
        },
        _doCheckState: function($) {
            var B = $.uid + "checkall",
            _ = document.getElementById(B);
            if (_ && $._getSelectAllCheckState) {
                var A = $._getSelectAllCheckState();
                if (A == "has") {
                    _.indeterminate = true;
                    _.checked = true
                } else {
                    _.indeterminate = false;
                    _.checked = A
                }
            }
        }
    },
    $)
};
mini.O0111l["checkcolumn"] = mini.CheckColumn;
mini.ExpandColumn = function($) {
    return mini.copyTo({
        width: 30,
        headerAlign: "center",
        align: "center",
        draggable: false,
        cellStyle: "padding:0",
        cellCls: "mini-grid-expandCell",
        renderer: function($) {
            return "<a class=\"mini-grid-ecIcon\" href=\"javascript:#\" onclick=\"return false\"></a>"
        },
        init: function($) {
            $[_on]("cellclick", this.l1101l, this)
        },
        l1101l: function(A) {
            var $ = A.sender;
            if (A.column == this && $[_isShowRowDetail]) if (ooOO(A.htmlEvent.target, "mini-grid-ecIcon")) {
                var _ = $[_isShowRowDetail](A.record);
                if ($.autoHideRowDetail) $[_hideAllRowDetail]();
                if (_) $[_hideRowDetail](A.record);
                else $[_showRowDetail](A.record)
            }
        }
    },
    $)
};
mini.O0111l["expandcolumn"] = mini.ExpandColumn;
Oll1llColumn = function($) {
    return mini.copyTo({
        _type: "checkboxcolumn",
        header: "#",
        headerAlign: "center",
        cellCls: "mini-checkcolumn",
        trueValue: true,
        falseValue: false,
        readOnly: false,
        getCheckId: function($) {
            return this._gridUID + "$checkbox$" + $[this._rowIdField]
        },
        getCheckBoxEl: function($) {
            return document.getElementById(this.getCheckId($))
        },
        renderer: function(C) {
            var A = this.getCheckId(C.record),
            B = mini._getMap(C.field, C.record),
            _ = B == this.trueValue ? true: false,
            $ = "checkbox";
            return "<input type=\"" + $ + "\" id=\"" + A + "\" " + (_ ? "checked": "") + " hidefocus style=\"outline:none;\" onclick=\"return false;\"/>"
        },
        init: function($) {
            this.grid = $;
            function _(B) {
                if ($[_isReadOnly]() || this[_readOnly]) return;
                B.value = mini._getMap(B.field, B.record);
                $[_fire]("cellbeginedit", B);
                if (B.cancel !== true) {
                    var A = mini._getMap(B.column.field, B.record),
                    _ = A == this.trueValue ? this.falseValue: this.trueValue;
                    if ($.oo10o) $.oo10o(B.record, B.column, _)
                }
            }
            function A(C) {
                if (C.column == this) {
                    var B = this.getCheckId(C.record),
                    A = C.htmlEvent.target;
                    if (A.id == B) if ($[_allowCellEdit]) {
                        C.cancel = false;
                        _[_call](this, C)
                    } else if ($[_isEditingRow] && $[_isEditingRow](C.record)) setTimeout(function() {
                        A.checked = !A.checked
                    },
                    1)
                }
            }
            $[_on]("cellclick", A, this);
            lo1l1o(this.grid.el, "keydown",
            function(C) {
                if (C.keyCode == 32 && $[_allowCellEdit]) {
                    var A = $[_getCurrentCell]();
                    if (!A) return;
                    var B = {
                        record: A[0],
                        column: A[1]
                    };
                    _[_call](this, B);
                    C.preventDefault()
                }
            },
            this);
            var B = parseInt(this.trueValue),
            C = parseInt(this.falseValue);
            if (!isNaN(B)) this.trueValue = B;
            if (!isNaN(C)) this.falseValue = C
        }
    },
    $)
};
mini.O0111l["checkboxcolumn"] = Oll1llColumn;
oO1lOoColumn = function($) {
    return mini.copyTo({
        renderer: function(M) {
            var _ = !mini.isNull(M.value) ? String(M.value) : "",
            C = _.split(","),
            D = "id",
            J = "text",
            A = {},
            G = M.column.editor;
            if (G && G.type == "combobox") {
                var B = this.__editor;
                if (!B) {
                    if (mini.isControl(G)) B = G;
                    else {
                        G = mini.clone(G);
                        B = mini.create(G)
                    }
                    this.__editor = B
                }
                D = B[_getValueField]();
                J = B[_getTextField]();
                A = this._valueMaps;
                if (!A) {
                    A = {};
                    var K = B[_getData]();
                    for (var H = 0,
                    E = K.length; H < E; H++) {
                        var $ = K[H];
                        A[$[D]] = $
                    }
                    this._valueMaps = A
                }
            }
            var L = [];
            for (H = 0, E = C.length; H < E; H++) {
                var F = C[H],
                $ = A[F];
                if ($) {
                    var I = $[J];
                    if (I === null || I === undefined) I = "";
                    L.push(I)
                }
            }
            return L.join(",")
        }
    },
    $)
};
mini.O0111l["comboboxcolumn"] = oO1lOoColumn;
OlOl0 = function($) {
    this.owner = $;
    lo1l1o(this.owner.el, "mousedown", this.Oo00O, this)
};
OlOl0[_prototype] = {
    Oo00O: function(A) {
        var $ = ooll(A.target, "mini-resizer-trigger");
        if ($ && this.owner[_allowResize]) {
            var _ = this.l0o0();
            _.start(A)
        }
    },
    l0o0: function() {
        if (!this._resizeDragger) this._resizeDragger = new mini.Drag({
            capture: true,
            onStart: mini.createDelegate(this.OO1l, this),
            onMove: mini.createDelegate(this.lO10O, this),
            onStop: mini.createDelegate(this.olO1, this)
        });
        return this._resizeDragger
    },
    OO1l: function($) {
        this.proxy = mini.append(document.body, "<div class=\"mini-resizer-proxy\"></div>");
        this.proxy.style.cursor = "se-resize";
        this.elBox = O11O(this.owner.el);
        oo1lo1(this.proxy, this.elBox)
    },
    lO10O: function(B) {
        var $ = this.owner,
        D = B.now[0] - B.init[0],
        _ = B.now[1] - B.init[1],
        A = this.elBox.width + D,
        C = this.elBox.height + _;
        if (A < $.minWidth) A = $.minWidth;
        if (C < $.minHeight) C = $.minHeight;
        if (A > $.maxWidth) A = $.maxWidth;
        if (C > $.maxHeight) C = $.maxHeight;
        mini.setSize(this.proxy, A, C)
    },
    olO1: function($, A) {
        if (!this.proxy) return;
        var _ = O11O(this.proxy);
        jQuery(this.proxy).remove();
        this.proxy = null;
        this.elBox = null;
        if (A) {
            this.owner[_setWidth](_.width);
            this.owner[_setHeight](_.height);
            this.owner[_fire]("resize")
        }
    }
};
mini._topWindow = null;
mini._getTopWindow = function(_) {
    if (mini._topWindow) return mini._topWindow;
    var $ = [];
    function A(_) {
        try {
            _["___try"] = 1;
            $.push(_)
        } catch(B) {}
        if (_.parent && _.parent != _) A(_.parent)
    }
    A(window);
    mini._topWindow = $[$.length - 1];
    return mini._topWindow
};
var __ps = mini.getParams();
if (__ps._winid) {
    try {
        window.Owner = mini._getTopWindow()[__ps._winid]
    } catch(ex) {}
}
mini._WindowID = "w" + Math.floor(Math.random() * 10000);
mini._getTopWindow()[mini._WindowID] = window;
mini.__IFrameCreateCount = 1;
mini.createIFrame = function(E, F) {
    var H = "__iframe_onload" + mini.__IFrameCreateCount++;
    window[H] = _;
    if (!E) E = "";
    var D = E.split("#");
    E = D[0];
    var C = "_t=" + Math.floor(Math.random() * 1000000);
    if (E[_indexOf]("?") == -1) E += "?" + C;
    else E += "&" + C;
    if (D[1]) E = E + "#" + D[1];
    var G = "<iframe style=\"width:100%;height:100%;\" onload=\"" + H + "()\"  frameborder=\"0\"></iframe>",
    $ = document.createElement("div"),
    B = mini.append($, G),
    I = false;
    setTimeout(function() {
        if (B) {
            B.src = E;
            I = true
        }
    },
    5);
    var A = true;
    function _() {
        if (I == false) return;
        setTimeout(function() {
            if (F) F(B, A);
            A = false
        },
        1)
    }
    B._ondestroy = function() {
        window[H] = mini.emptyFn;
        B.src = "";
        try {
            B.contentWindow.document.write("");
            B.contentWindow.document.close()
        } catch($) {}
        B._ondestroy = null;
        B = null
    };
    return B
};
mini._doOpen = function(C) {
    if (typeof C == "string") C = {
        url: C
    };
    C = mini.copyTo({
        width: 700,
        height: 400,
        allowResize: true,
        allowModal: true,
        closeAction: "destroy",
        title: "",
        titleIcon: "",
        iconCls: "",
        iconStyle: "",
        bodyStyle: "padding:0",
        url: "",
        showCloseButton: true,
        showFooter: false
    },
    C);
    C[_closeAction] = "destroy";
    var $ = C.onload;
    delete C.onload;
    var B = C.ondestroy;
    delete C.ondestroy;
    var _ = C.url;
    delete C.url;
    var A = new oolo10();
    A[_set](C);
    A[_load](_, $, B);
    A[_show]();
    return A
};
mini.open = function(E) {
    if (!E) return;
    var C = E.url;
    if (!C) C = "";
    var B = C.split("#"),
    C = B[0],
    A = "_winid=" + mini._WindowID;
    if (C[_indexOf]("?") == -1) C += "?" + A;
    else C += "&" + A;
    if (B[1]) C = C + "#" + B[1];
    E.url = C;
    E.Owner = window;
    var $ = [];
    function _(A) {
        if (A.mini) $.push(A);
        if (A.parent && A.parent != A) _(A.parent)
    }
    _(window);
    var D = $[$.length - 1];
    return D["mini"]._doOpen(E)
};
mini.openTop = mini.open;
mini[_getData] = function(C, A, E, D, _) {
    var $ = mini[_getText](C, A, E, D, _),
    B = mini.decode($);
    return B
};
mini[_getText] = function(B, A, D, C, _) {
    var $ = null;
    mini.ajax({
        url: B,
        data: A,
        async: false,
        type: _ ? _: "get",
        cache: false,
        dataType: "text",
        success: function(A, _) {
            $ = A;
            if (D) D(A, _)
        },
        error: C
    });
    return $
};
if (!window.mini_RootPath) mini_RootPath = "/";
lolloO = function(B) {
    var A = document.getElementsByTagName("script"),
    D = "";
    for (var $ = 0,
    E = A.length; $ < E; $++) {
        var C = A[$].src;
        if (C[_indexOf](B) != -1) {
            var F = C.split(B);
            D = F[0];
            break
        }
    }
    var _ = location.href;
    _ = _.split("#")[0];
    _ = _.split("?")[0];
    F = _.split("/");
    F.length = F.length - 1;
    _ = F.join("/");
    if (D[_indexOf]("http:") == -1 && D[_indexOf]("file:") == -1) D = _ + "/" + D;
    return D
};
if (!window.mini_JSPath) mini_JSPath = lolloO("miniui.js");
mini[_update] = function(A, _) {
    if (typeof A == "string") A = {
        url: A
    };
    if (_) A.el = _;
    var $ = mini.loadText(A.url);
    mini.innerHTML(A.el, $);
    mini.parse(A.el)
};
mini.createSingle = function($) {
    if (typeof $ == "string") $ = mini.getClass($);
    if (typeof $ != "function") return;
    var _ = $.single;
    if (!_) _ = $.single = new $();
    return _
};
mini.createTopSingle = function($) {
    if (typeof $ != "function") return;
    var _ = $[_prototype].type;
    if (top && top != window && top.mini && top.mini.getClass(_)) return top.mini.createSingle(_);
    else return mini.createSingle($)
};
mini.sortTypes = {
    "string": function($) {
        return String($).toUpperCase()
    },
    "date": function($) {
        if (!$) return 0;
        if (mini.isDate($)) return $[_getTime]();
        return mini.parseDate(String($))
    },
    "float": function(_) {
        var $ = parseFloat(String(_).replace(/,/g, ""));
        return isNaN($) ? 0 : $
    },
    "int": function(_) {
        var $ = parseInt(String(_).replace(/,/g, ""), 10);
        return isNaN($) ? 0 : $
    },
    "currency": function(_) {
        var $ = parseFloat(String(_).replace(/,/g, ""));
        return isNaN($) ? 0 : $
    }
};
mini.loo1o1 = function(G, $, K, H) {
    var F = G.split(";");
    for (var E = 0,
    C = F.length; E < C; E++) {
        var G = F[E].trim(),
        J = G.split(":"),
        A = J[0],
        _ = J[1];
        if (_) _ = _.split(",");
        else _ = [];
        var D = mini.VTypes[A];
        if (D) {
            var I = D($, _);
            if (I !== true) {
                K[_isValid] = false;
                var B = J[0] + "ErrorText";
                K.errorText = H[B] || mini.VTypes[B] || "";
                K.errorText = String.format(K.errorText, _[0], _[1], _[2], _[3], _[4]);
                break
            }
        }
    }
};
mini.o00oO = function($, _) {
    if ($ && $[_]) return $[_];
    else return mini.VTypes[_]
};
mini.VTypes = {
    uniqueErrorText: "This field is unique.",
    requiredErrorText: "This field is required.",
    emailErrorText: "Please enter a valid email address.",
    urlErrorText: "Please enter a valid URL.",
    floatErrorText: "Please enter a valid number.",
    intErrorText: "Please enter only digits",
    dateErrorText: "Please enter a valid date. Date format is {0}",
    maxLengthErrorText: "Please enter no more than {0} characters.",
    minLengthErrorText: "Please enter at least {0} characters.",
    maxErrorText: "Please enter a value less than or equal to {0}.",
    minErrorText: "Please enter a value greater than or equal to {0}.",
    rangeLengthErrorText: "Please enter a value between {0} and {1} characters long.",
    rangeCharErrorText: "Please enter a value between {0} and {1} characters long.",
    rangeErrorText: "Please enter a value between {0} and {1}.",
    required: function(_, $) {
        if (mini.isNull(_) || _ === "") return false;
        return true
    },
    email: function(_, $) {
        if (mini.isNull(_) || _ === "") return true;
        if (_.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1) return true;
        else return false
    },
    url: function(A, $) {
        if (mini.isNull(A) || A === "") return true;
        function _(_) {
            _ = _.toLowerCase();
            var $ = "^((https|http|ftp|rtsp|mms)?://)" + "?(([0-9a-z_!~*'().&=+$%-]+:)?[0-9a-z_!~*'().&=+$%-]+@)?" + "(([0-9]{1,3}.){3}[0-9]{1,3}" + "|" + "([0-9a-z_!~*'()-]+.)*" + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]." + "[a-z]{2,6})" + "(:[0-9]{1,4})?" + "((/?)|" + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$",
            A = new RegExp($);
            if (A.test(_)) return (true);
            else return (false)
        }
        return _(A)
    },
    "int": function(A, _) {
        if (mini.isNull(A) || A === "") return true;
        function $(_) {
            var $ = String(_);
            return $.length > 0 && !(/[^0-9]/).test($)
        }
        return $(A)
    },
    "float": function(A, _) {
        if (mini.isNull(A) || A === "") return true;
        function $(_) {
            if (_ < 0) _ = -_;
            var $ = String(_);
            return $.length > 0 && !(/[^0-9.]/).test($)
        }
        return $(A)
    },
    "date": function(B, _) {
        if (mini.isNull(B) || B === "") return true;
        if (!B) return false;
        var $ = null,
        A = _[0];
        if (A) {
            $ = mini.parseDate(B, A);
            if ($ && $.getFullYear) if (mini.formatDate($, A) == B) return true
        } else {
            $ = mini.parseDate(B, "yyyy-MM-dd");
            if (!$) $ = mini.parseDate(B, "yyyy/MM/dd");
            if (!$) $ = mini.parseDate(B, "MM/dd/yyyy");
            if ($ && $.getFullYear) return true
        }
        return false
    },
    maxLength: function(A, $) {
        if (mini.isNull(A) || A === "") return true;
        var _ = parseInt($);
        if (!A || isNaN(_)) return true;
        if (A.length <= _) return true;
        else return false
    },
    minLength: function(A, $) {
        if (mini.isNull(A) || A === "") return true;
        var _ = parseInt($);
        if (isNaN(_)) return true;
        if (A.length >= _) return true;
        else return false
    },
    rangeLength: function(B, _) {
        if (mini.isNull(B) || B === "") return true;
        if (!B) return false;
        var $ = parseFloat(_[0]),
        A = parseFloat(_[1]);
        if (isNaN($) || isNaN(A)) return true;
        if ($ <= B.length && B.length <= A) return true;
        return false
    },
    rangeChar: function(G, B) {
        if (mini.isNull(G) || G === "") return true;
        var A = parseFloat(B[0]),
        E = parseFloat(B[1]);
        if (isNaN(A) || isNaN(E)) return true;
        function C(_) {
            var $ = new RegExp("^[\u4e00-\u9fa5]+$");
            if ($.test(_)) return true;
            return false
        }
        var $ = 0,
        F = String(G).split("");
        for (var _ = 0,
        D = F.length; _ < D; _++) if (C(F[_])) $ += 2;
        else $ += 1;
        if (A <= $ && $ <= E) return true;
        return false
    },
    range: function(B, _) {
        if (mini.VTypes["float"](B, _) == false) return false;
        if (mini.isNull(B) || B === "") return true;
        B = parseFloat(B);
        if (isNaN(B)) return false;
        var $ = parseFloat(_[0]),
        A = parseFloat(_[1]);
        if (isNaN($) || isNaN(A)) return true;
        if ($ <= B && B <= A) return true;
        return false
    }
};
mini.summaryTypes = {
    "count": function($) {
        if (!$) $ = [];
        return $.length
    },
    "max": function(B, C) {
        if (!B) B = [];
        var E = null;
        for (var _ = 0,
        D = B.length; _ < D; _++) {
            var $ = B[_],
            A = parseFloat($[C]);
            if (A === null || A === undefined || isNaN(A)) continue;
            if (E == null || E < A) E = A
        }
        return E
    },
    "min": function(C, D) {
        if (!C) C = [];
        var B = null;
        for (var _ = 0,
        E = C.length; _ < E; _++) {
            var $ = C[_],
            A = parseFloat($[D]);
            if (A === null || A === undefined || isNaN(A)) continue;
            if (B == null || B > A) B = A
        }
        return B
    },
    "avg": function(C, D) {
        if (!C) C = [];
        if (C.length == 0) return 0;
        var B = 0;
        for (var _ = 0,
        E = C.length; _ < E; _++) {
            var $ = C[_],
            A = parseFloat($[D]);
            if (A === null || A === undefined || isNaN(A)) continue;
            B += A
        }
        var F = B / C.length;
        return F
    },
    "sum": function(C, D) {
        if (!C) C = [];
        var B = 0;
        for (var _ = 0,
        E = C.length; _ < E; _++) {
            var $ = C[_],
            A = parseFloat($[D]);
            if (A === null || A === undefined || isNaN(A)) continue;
            B += A
        }
        return B
    }
};
mini.formatCurrency = function($, A) {
    if ($ === null || $ === undefined) null == "";
    $ = String($).replace(/\$|\,/g, "");
    if (isNaN($)) $ = "0";
    sign = ($ == ($ = Math.abs($)));
    $ = Math.floor($ * 100 + 0.50000000001);
    cents = $ % 100;
    $ = Math.floor($ / 100).toString();
    if (cents < 10) cents = "0" + cents;
    for (var _ = 0; _ < Math.floor(($.length - (1 + _)) / 3); _++) $ = $.substring(0, $.length - (4 * _ + 3)) + "," + $.substring($.length - (4 * _ + 3));
    A = A || "";
    return A + (((sign) ? "": "-") + $ + "." + cents)
};
mini.emptyFn = function() {};
mini.Drag = function($) {
    mini.copyTo(this, $)
};
mini.Drag[_prototype] = {
    onStart: mini.emptyFn,
    onMove: mini.emptyFn,
    onStop: mini.emptyFn,
    capture: false,
    fps: 20,
    event: null,
    delay: 80,
    start: function(_) {
        _.preventDefault();
        if (_) this.event = _;
        this.now = this.init = [this.event.pageX, this.event.pageY];
        var $ = document;
        lo1l1o($, "mousemove", this.move, this);
        lo1l1o($, "mouseup", this.stop, this);
        lo1l1o($, "contextmenu", this.contextmenu, this);
        if (this.context) lo1l1o(this.context, "contextmenu", this.contextmenu, this);
        this.trigger = _.target;
        mini.selectable(this.trigger, false);
        mini.selectable($.body, false);
        if (this.capture) if (isIE) this.trigger.setCapture(true);
        else if (document.captureEvents) document.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP | Event.MOUSEDOWN);
        this.started = false;
        this.startTime = new Date()
    },
    contextmenu: function($) {
        if (this.context) o01o(this.context, "contextmenu", this.contextmenu, this);
        o01o(document, "contextmenu", this.contextmenu, this);
        $.preventDefault();
        $.stopPropagation()
    },
    move: function(_) {
        if (this.delay) if (new Date() - this.startTime < this.delay) return;
        if (!this.started) {
            this.started = true;
            this.onStart(this)
        }
        var $ = this;
        if (!this.timer) this.timer = setTimeout(function() {
            $.now = [_.pageX, _.pageY];
            $.event = _;
            $.onMove($);
            $.timer = null
        },
        5)
    },
    stop: function(B) {
        this.now = [B.pageX, B.pageY];
        this.event = B;
        if (this.timer) {
            clearTimeout(this.timer);
            this.timer = null
        }
        var A = document;
        mini.selectable(this.trigger, true);
        mini.selectable(A.body, true);
        if (isIE) {
            this.trigger.setCapture(false);
            this.trigger.releaseCapture()
        }
        var _ = mini.MouseButton.Right != B.button;
        if (_ == false) B.preventDefault();
        o01o(A, "mousemove", this.move, this);
        o01o(A, "mouseup", this.stop, this);
        var $ = this;
        setTimeout(function() {
            o01o(document, "contextmenu", $.contextmenu, $);
            if ($.context) o01o($.context, "contextmenu", $.contextmenu, $)
        },
        1);
        if (this.started) this.onStop(this, _)
    }
};
mini.JSON = new(function() {
    var sb = [],
    _dateFormat = null,
    useHasOwn = !!{}.hasOwnProperty,
    replaceString = function($, A) {
        var _ = m[A];
        if (_) return _;
        _ = A.charCodeAt();
        return "\\u00" + Math.floor(_ / 16).toString(16) + (_ % 16).toString(16)
    },
    doEncode = function($, B) {
        if ($ === null) {
            sb[sb.length] = "null";
            return
        }
        var A = typeof $;
        if (A == "undefined") {
            sb[sb.length] = "null";
            return
        } else if ($.push) {
            sb[sb.length] = "[";
            var E, _, D = $.length,
            F;
            for (_ = 0; _ < D; _ += 1) {
                F = $[_];
                A = typeof F;
                if (A == "undefined" || A == "function" || A == "unknown");
                else {
                    if (E) sb[sb.length] = ",";
                    doEncode(F);
                    E = true
                }
            }
            sb[sb.length] = "]";
            return
        } else if ($.getFullYear) {
            if (_dateFormat) sb[sb.length] = _dateFormat($, B);
            else {
                var C;
                sb[sb.length] = "\"";
                sb[sb.length] = $.getFullYear();
                sb[sb.length] = "-";
                C = $.getMonth() + 1;
                sb[sb.length] = C < 10 ? "0" + C: C;
                sb[sb.length] = "-";
                C = $.getDate();
                sb[sb.length] = C < 10 ? "0" + C: C;
                sb[sb.length] = "T";
                C = $.getHours();
                sb[sb.length] = C < 10 ? "0" + C: C;
                sb[sb.length] = ":";
                C = $.getMinutes();
                sb[sb.length] = C < 10 ? "0" + C: C;
                sb[sb.length] = ":";
                C = $.getSeconds();
                sb[sb.length] = C < 10 ? "0" + C: C;
                sb[sb.length] = "\"";
                return
            }
        } else if (A == "string") {
            if (strReg1.test($)) {
                sb[sb.length] = "\"";
                sb[sb.length] = $.replace(strReg2, replaceString);
                sb[sb.length] = "\"";
                return
            }
            sb[sb.length] = "\"" + $ + "\"";
            return
        } else if (A == "number") {
            sb[sb.length] = $;
            return
        } else if (A == "boolean") {
            sb[sb.length] = String($);
            return
        } else {
            sb[sb.length] = "{";
            E,
            _,
            F;
            for (_ in $) if (!useHasOwn || ($.hasOwnProperty && $.hasOwnProperty(_))) {
                F = $[_];
                A = typeof F;
                if (A == "undefined" || A == "function" || A == "unknown");
                else {
                    if (E) sb[sb.length] = ",";
                    doEncode(_);
                    sb[sb.length] = ":";
                    doEncode(F, _);
                    E = true
                }
            }
            sb[sb.length] = "}";
            return
        }
    },
    m = {
        "\b": "\\b",
        "\t": "\\t",
        "\n": "\\n",
        "\f": "\\f",
        "\r": "\\r",
        "\"": "\\\"",
        "\\": "\\\\"
    },
    strReg1 = /["\\\x00-\x1f]/,
    strReg2 = /([\x00-\x1f\\"])/g;
    this.encode = function() {
        var $;
        return function($, _) {
            sb = [];
            _dateFormat = _;
            doEncode($);
            _dateFormat = null;
            return sb.join("")
        }
    } ();
    this.decode = function() {
        var re = /[\"\'](\d{4})-(\d{2})-(\d{2})[T ](\d{2}):(\d{2}):(\d{2})[\"\']/g;
        return function(json, parseDate) {
            if (json === "" || json === null || json === undefined) return json;
            if (typeof json == "object") json = this.encode(json);
            if (parseDate !== false) {
                json = json.replace(re, "new Date($1,$2-1,$3,$4,$5,$6)");
                json = json.replace(__js_dateRegEx, "$1new Date($2)");
                json = json.replace(__js_dateRegEx2, "new Date($1)")
            }
            var s = eval("(" + json + ")");
            return s
        }
    } ()
})();
__js_dateRegEx = new RegExp("(^|[^\\\\])\\\"\\\\/Date\\((-?[0-9]+)(?:[a-zA-Z]|(?:\\+|-)[0-9]{4})?\\)\\\\/\\\"", "g");
__js_dateRegEx2 = new RegExp("[\"']/Date\\(([0-9]+)\\)/[\"']", "g");
mini.encode = mini.JSON.encode;
mini.decode = mini.JSON.decode;
mini.clone = function($, A) {
    if ($ === null || $ === undefined) return $;
    var B = mini.encode($),
    _ = mini.decode(B);
    function C(A) {
        for (var _ = 0,
        D = A.length; _ < D; _++) {
            var $ = A[_];
            delete $._state;
            delete $._id;
            delete $._pid;
            delete $._uid;
            for (var B in $) {
                var E = $[B];
                if (E instanceof Array) C(E)
            }
        }
    }
    if (A !== false) C(_ instanceof Array ? _: [_]);
    return _
};
var DAY_MS = 86400000,
HOUR_MS = 3600000,
MINUTE_MS = 60000;
mini.copyTo(mini, {
    clearTime: function($) {
        if (!$) return null;
        return new Date($.getFullYear(), $.getMonth(), $.getDate())
    },
    maxTime: function($) {
        if (!$) return null;
        return new Date($.getFullYear(), $.getMonth(), $.getDate(), 23, 59, 59)
    },
    cloneDate: function($) {
        if (!$) return null;
        return new Date($[_getTime]())
    },
    addDate: function(A, $, _) {
        if (!_) _ = "D";
        A = new Date(A[_getTime]());
        switch (_.toUpperCase()) {
        case "Y":
            A.setFullYear(A.getFullYear() + $);
            break;
        case "MO":
            A.setMonth(A.getMonth() + $);
            break;
        case "D":
            A.setDate(A.getDate() + $);
            break;
        case "H":
            A.setHours(A.getHours() + $);
            break;
        case "M":
            A.setMinutes(A.getMinutes() + $);
            break;
        case "S":
            A.setSeconds(A.getSeconds() + $);
            break;
        case "MS":
            A.setMilliseconds(A.getMilliseconds() + $);
            break
        }
        return A
    },
    getWeek: function(D, $, _) {
        $ += 1;
        var E = Math.floor((14 - ($)) / 12),
        G = D + 4800 - E,
        A = ($) + (12 * E) - 3,
        C = _ + Math.floor(((153 * A) + 2) / 5) + (365 * G) + Math.floor(G / 4) - Math.floor(G / 100) + Math.floor(G / 400) - 32045,
        F = (C + 31741 - (C % 7)) % 146097 % 36524 % 1461,
        H = Math.floor(F / 1460),
        B = ((F - H) % 365) + H;
        NumberOfWeek = Math.floor(B / 7) + 1;
        return NumberOfWeek
    },
    getWeekStartDate: function(C, B) {
        if (!B) B = 0;
        if (B > 6 || B < 0) throw new Error("out of weekday");
        var A = C.getDay(),
        _ = B - A;
        if (A < B) _ -= 7;
        var $ = new Date(C.getFullYear(), C.getMonth(), C.getDate() + _);
        return $
    },
    getShortWeek: function(_) {
        var $ = this.dateInfo.daysShort;
        return $[_]
    },
    getLongWeek: function(_) {
        var $ = this.dateInfo.daysLong;
        return $[_]
    },
    getShortMonth: function($) {
        var _ = this.dateInfo.monthsShort;
        return _[$]
    },
    getLongMonth: function($) {
        var _ = this.dateInfo.monthsLong;
        return _[$]
    },
    dateInfo: {
        monthsLong: ["January", "Febraury", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
        monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
        daysLong: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
        daysShort: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"],
        quarterLong: ["Q1", "Q2", "Q3", "Q4"],
        quarterShort: ["Q1", "Q2", "Q3", "Q4"],
        halfYearLong: ["first half", "second half"],
        patterns: {
            "d": "M/d/yyyy",
            "D": "dddd,MMMM dd,yyyy",
            "f": "dddd,MMMM dd,yyyy H:mm tt",
            "F": "dddd,MMMM dd,yyyy H:mm:ss tt",
            "g": "M/d/yyyy H:mm tt",
            "G": "M/d/yyyy H:mm:ss tt",
            "m": "MMMM dd",
            "o": "yyyy-MM-ddTHH:mm:ss.fff",
            "s": "yyyy-MM-ddTHH:mm:ss",
            "t": "H:mm tt",
            "T": "H:mm:ss tt",
            "U": "dddd,MMMM dd,yyyy HH:mm:ss tt",
            "y": "MMM,yyyy"
        },
        tt: {
            "AM": "AM",
            "PM": "PM"
        },
        ten: {
            "Early": "Early",
            "Mid": "Mid",
            "Late": "Late"
        },
        today: "Today",
        clockType: 24
    }
});
Date[_prototype].getHalfYear = function() {
    if (!this.getMonth) return null;
    var $ = this.getMonth();
    if ($ < 6) return 0;
    return 1
};
Date[_prototype].getQuarter = function() {
    if (!this.getMonth) return null;
    var $ = this.getMonth();
    if ($ < 3) return 0;
    if ($ < 6) return 1;
    if ($ < 9) return 2;
    return 3
};
mini.formatDate = function(C, O, F) {
    if (!C || !C.getFullYear || isNaN(C)) return "";
    var G = C.toString(),
    B = mini.dateInfo;
    if (!B) B = mini.dateInfo;
    if (typeof(B) !== "undefined") {
        var M = typeof(B.patterns[O]) !== "undefined" ? B.patterns[O] : O,
        J = C.getFullYear(),
        $ = C.getMonth(),
        _ = C.getDate();
        if (O == "yyyy-MM-dd") {
            $ = $ + 1 < 10 ? "0" + ($ + 1) : $ + 1;
            _ = _ < 10 ? "0" + _: _;
            return J + "-" + $ + "-" + _
        }
        if (O == "MM/dd/yyyy") {
            $ = $ + 1 < 10 ? "0" + ($ + 1) : $ + 1;
            _ = _ < 10 ? "0" + _: _;
            return $ + "/" + _ + "/" + J
        }
        G = M.replace(/yyyy/g, J);
        G = G.replace(/yy/g, (J + "").substring(2));
        var L = C.getHalfYear();
        G = G.replace(/hy/g, B.halfYearLong[L]);
        var I = C.getQuarter();
        G = G.replace(/Q/g, B.quarterLong[I]);
        G = G.replace(/q/g, B.quarterShort[I]);
        G = G.replace(/MMMM/g, B.monthsLong[$].escapeDateTimeTokens());
        G = G.replace(/MMM/g, B.monthsShort[$].escapeDateTimeTokens());
        G = G.replace(/MM/g, $ + 1 < 10 ? "0" + ($ + 1) : $ + 1);
        G = G.replace(/(\\)?M/g,
        function(A, _) {
            return _ ? A: $ + 1
        });
        var N = C.getDay();
        G = G.replace(/dddd/g, B.daysLong[N].escapeDateTimeTokens());
        G = G.replace(/ddd/g, B.daysShort[N].escapeDateTimeTokens());
        G = G.replace(/dd/g, _ < 10 ? "0" + _: _);
        G = G.replace(/(\\)?d/g,
        function(A, $) {
            return $ ? A: _
        });
        var H = C.getHours(),
        A = H > 12 ? H - 12 : H;
        if (B.clockType == 12) if (H > 12) H -= 12;
        G = G.replace(/HH/g, H < 10 ? "0" + H: H);
        G = G.replace(/(\\)?H/g,
        function(_, $) {
            return $ ? _: H
        });
        G = G.replace(/hh/g, A < 10 ? "0" + A: A);
        G = G.replace(/(\\)?h/g,
        function(_, $) {
            return $ ? _: A
        });
        var D = C.getMinutes();
        G = G.replace(/mm/g, D < 10 ? "0" + D: D);
        G = G.replace(/(\\)?m/g,
        function(_, $) {
            return $ ? _: D
        });
        var K = C.getSeconds();
        G = G.replace(/ss/g, K < 10 ? "0" + K: K);
        G = G.replace(/(\\)?s/g,
        function(_, $) {
            return $ ? _: K
        });
        G = G.replace(/fff/g, C.getMilliseconds());
        G = G.replace(/tt/g, C.getHours() > 12 || C.getHours() == 0 ? B.tt["PM"] : B.tt["AM"]);
        var C = C.getDate(),
        E = "";
        if (C <= 10) E = B.ten["Early"];
        else if (C <= 20) E = B.ten["Mid"];
        else E = B.ten["Late"];
        G = G.replace(/ten/g, E)
    }
    return G.replace(/\\/g, "")
};
String[_prototype].escapeDateTimeTokens = function() {
    return this.replace(/([dMyHmsft])/g, "\\$1")
};
mini.fixDate = function($, _) {
    if ( + $) while ($.getDate() != _.getDate()) $[_setTime]( + $ + ($ < _ ? 1 : -1) * HOUR_MS)
};
mini.parseDate = function(s, ignoreTimezone) {
    try {
        var d = eval(s);
        if (d && d.getFullYear) return d
    } catch(ex) {}
    if (typeof s == "object") return isNaN(s) ? null: s;
    if (typeof s == "number") {
        d = new Date(s * 1000);
        if (d[_getTime]() != s) return null;
        return isNaN(d) ? null: d
    }
    if (typeof s == "string") {
        m = s.match(/^([0-9]{4}).([0-9]*)$/);
        if (m) {
            var date = new Date(m[1], m[2] - 1);
            return date
        }
        if (s.match(/^\d+(\.\d+)?$/)) {
            d = new Date(parseFloat(s) * 1000);
            if (d[_getTime]() != s) return null;
            else return d
        }
        if (ignoreTimezone === undefined) ignoreTimezone = true;
        d = mini.parseISO8601(s, ignoreTimezone) || (s ? new Date(s) : null);
        return isNaN(d) ? null: d
    }
    return null
};
mini.parseISO8601 = function(D, $) {
    var _ = D.match(/^([0-9]{4})([-\/]([0-9]{1,2})([-\/]([0-9]{1,2})([T ]([0-9]{1,2}):([0-9]{1,2})(:([0-9]{1,2})(\.([0-9]+))?)?(Z|(([-+])([0-9]{2})(:?([0-9]{2}))?))?)?)?)?$/);
    if (!_) {
        _ = D.match(/^([0-9]{4})[-\/]([0-9]{2})[-\/]([0-9]{2})[T ]([0-9]{1,2})/);
        if (_) {
            var A = new Date(_[1], _[2] - 1, _[3], _[4]);
            return A
        }
        _ = D.match(/^([0-9]{4}).([0-9]*)/);
        if (_) {
            A = new Date(_[1], _[2] - 1);
            return A
        }
        _ = D.match(/^([0-9]{4}).([0-9]*).([0-9]*)/);
        if (_) {
            A = new Date(_[1], _[2] - 1, _[3]);
            return A
        }
        _ = D.match(/^([0-9]{2})-([0-9]{2})-([0-9]{4})$/);
        if (!_) return null;
        else {
            A = new Date(_[3], _[1] - 1, _[2]);
            return A
        }
    }
    A = new Date(_[1], 0, 1);
    if ($ || !_[14]) {
        var C = new Date(_[1], 0, 1, 9, 0);
        if (_[3]) {
            A.setMonth(_[3] - 1);
            C.setMonth(_[3] - 1)
        }
        if (_[5]) {
            A.setDate(_[5]);
            C.setDate(_[5])
        }
        mini.fixDate(A, C);
        if (_[7]) A.setHours(_[7]);
        if (_[8]) A.setMinutes(_[8]);
        if (_[10]) A.setSeconds(_[10]);
        if (_[12]) A.setMilliseconds(Number("0." + _[12]) * 1000);
        mini.fixDate(A, C)
    } else {
        A.setUTCFullYear(_[1], _[3] ? _[3] - 1 : 0, _[5] || 1);
        A.setUTCHours(_[7] || 0, _[8] || 0, _[10] || 0, _[12] ? Number("0." + _[12]) * 1000 : 0);
        var B = Number(_[16]) * 60 + (_[18] ? Number(_[18]) : 0);
        B *= _[15] == "-" ? 1 : -1;
        A = new Date( + A + (B * 60 * 1000))
    }
    return A
};
mini.parseTime = function(E, F) {
    if (!E) return null;
    var B = parseInt(E);
    if (B == E && F) {
        $ = new Date(0);
        if (F[0] == "H") $.setHours(B);
        else if (F[0] == "m") $.setMinutes(B);
        else if (F[0] == "s") $.setSeconds(B);
        return $
    }
    var $ = mini.parseDate(E);
    if (!$) {
        var D = E.split(":"),
        _ = parseInt(parseFloat(D[0])),
        C = parseInt(parseFloat(D[1])),
        A = parseInt(parseFloat(D[2]));
        if (!isNaN(_) && !isNaN(C) && !isNaN(A)) {
            $ = new Date(0);
            $.setHours(_);
            $.setMinutes(C);
            $.setSeconds(A)
        }
        if (!isNaN(_) && (F == "H" || F == "HH")) {
            $ = new Date(0);
            $.setHours(_)
        } else if (!isNaN(_) && !isNaN(C) && (F == "H:mm" || F == "HH:mm")) {
            $ = new Date(0);
            $.setHours(_);
            $.setMinutes(C)
        } else if (!isNaN(_) && !isNaN(C) && F == "mm:ss") {
            $ = new Date(0);
            $.setMinutes(_);
            $.setSeconds(C)
        }
    }
    return $
};
mini.dateInfo = {
    monthsLong: ["\u4e00\u6708", "\u4e8c\u6708", "\u4e09\u6708", "\u56db\u6708", "\u4e94\u6708", "\u516d\u6708", "\u4e03\u6708", "\u516b\u6708", "\u4e5d\u6708", "\u5341\u6708", "\u5341\u4e00\u6708", "\u5341\u4e8c\u6708"],
    monthsShort: ["1\u6708", "2\u6708", "3\u6708", "4\u6708", "5\u6708", "6\u6708", "7\u6708", "8\u6708", "9\u6708", "10\u6708", "11\u6708", "12\u6708"],
    daysLong: ["\u661f\u671f\u65e5", "\u661f\u671f\u4e00", "\u661f\u671f\u4e8c", "\u661f\u671f\u4e09", "\u661f\u671f\u56db", "\u661f\u671f\u4e94", "\u661f\u671f\u516d"],
    daysShort: ["\u65e5", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d"],
    quarterLong: ["\u4e00\u5b63\u5ea6", "\u4e8c\u5b63\u5ea6", "\u4e09\u5b63\u5ea6", "\u56db\u5b63\u5ea6"],
    quarterShort: ["Q1", "Q2", "Q2", "Q4"],
    halfYearLong: ["\u4e0a\u534a\u5e74", "\u4e0b\u534a\u5e74"],
    patterns: {
        "d": "yyyy-M-d",
        "D": "yyyy\u5e74M\u6708d\u65e5",
        "f": "yyyy\u5e74M\u6708d\u65e5 H:mm",
        "F": "yyyy\u5e74M\u6708d\u65e5 H:mm:ss",
        "g": "yyyy-M-d H:mm",
        "G": "yyyy-M-d H:mm:ss",
        "m": "MMMd\u65e5",
        "o": "yyyy-MM-ddTHH:mm:ss.fff",
        "s": "yyyy-MM-ddTHH:mm:ss",
        "t": "H:mm",
        "T": "H:mm:ss",
        "U": "yyyy\u5e74M\u6708d\u65e5 HH:mm:ss",
        "y": "yyyy\u5e74MM\u6708"
    },
    tt: {
        "AM": "\u4e0a\u5348",
        "PM": "\u4e0b\u5348"
    },
    ten: {
        "Early": "\u4e0a\u65ec",
        "Mid": "\u4e2d\u65ec",
        "Late": "\u4e0b\u65ec"
    },
    today: "\u4eca\u5929",
    clockType: 24
};
mini.append = function(_, A) {
    _ = OoO1o0(_);
    if (!A || !_) return;
    if (typeof A == "string") {
        if (A.charAt(0) == "#") {
            A = OoO1o0(A);
            if (!A) return;
            _.appendChild(A);
            return A
        } else {
            if (A[_indexOf]("<tr") == 0) {
                return jQuery(_).append(A)[0].lastChild;
                return
            }
            var $ = document.createElement("div");
            $.innerHTML = A;
            A = $.firstChild;
            while ($.firstChild) _.appendChild($.firstChild);
            return A
        }
    } else {
        _.appendChild(A);
        return A
    }
};
mini.prepend = function(_, A) {
    if (typeof A == "string") if (A.charAt(0) == "#") A = OoO1o0(A);
    else {
        var $ = document.createElement("div");
        $.innerHTML = A;
        A = $.firstChild
    }
    return jQuery(_).prepend(A)[0].firstChild
};
mini.after = function(_, A) {
    if (typeof A == "string") if (A.charAt(0) == "#") A = OoO1o0(A);
    else {
        var $ = document.createElement("div");
        $.innerHTML = A;
        A = $.firstChild
    }
    if (!A || !_) return;
    _.nextSibling ? _.parentNode.insertBefore(A, _.nextSibling) : _.parentNode.appendChild(A);
    return A
};
mini.before = function(_, A) {
    if (typeof A == "string") if (A.charAt(0) == "#") A = OoO1o0(A);
    else {
        var $ = document.createElement("div");
        $.innerHTML = A;
        A = $.firstChild
    }
    if (!A || !_) return;
    _.parentNode.insertBefore(A, _);
    return A
};
mini.__wrap = document.createElement("div");
mini.createElements = function($) {
    mini.removeChilds(mini.__wrap);
    var _ = $[_indexOf]("<tr") == 0;
    if (_) $ = "<table>" + $ + "</table>";
    mini.__wrap.innerHTML = $;
    return _ ? mini.__wrap.firstChild.rows: mini.__wrap.childNodes
};
OoO1o0 = function(D, A) {
    if (typeof D == "string") {
        if (D.charAt(0) == "#") D = D.substr(1);
        var _ = document.getElementById(D);
        if (_) return _;
        if (A) {
            var B = A.getElementsByTagName("*");
            for (var $ = 0,
            C = B.length; $ < C; $++) {
                _ = B[$];
                if (_.id == D) return _
            }
            _ = null
        }
        return _
    } else return D
};
ooll = function($, _) {
    $ = OoO1o0($);
    if (!$) return;
    if (!$.className) return false;
    var A = String($.className).split(" ");
    return A[_indexOf](_) != -1
};
o01O = function($, _) {
    if (!_) return;
    if (ooll($, _) == false) jQuery($)[_addClass](_)
};
o110 = function($, _) {
    if (!_) return;
    jQuery($)[_removeClass](_)
};
O1ll0 = function($) {
    $ = OoO1o0($);
    var _ = jQuery($);
    return {
        top: parseInt(_.css("margin-top"), 10) || 0,
        left: parseInt(_.css("margin-left"), 10) || 0,
        bottom: parseInt(_.css("margin-bottom"), 10) || 0,
        right: parseInt(_.css("margin-right"), 10) || 0
    }
};
o1Oo = function($) {
    $ = OoO1o0($);
    var _ = jQuery($);
    return {
        top: parseInt(_.css("border-top-width"), 10) || 0,
        left: parseInt(_.css("border-left-width"), 10) || 0,
        bottom: parseInt(_.css("border-bottom-width"), 10) || 0,
        right: parseInt(_.css("border-right-width"), 10) || 0
    }
};
oloo = function($) {
    $ = OoO1o0($);
    var _ = jQuery($);
    return {
        top: parseInt(_.css("padding-top"), 10) || 0,
        left: parseInt(_.css("padding-left"), 10) || 0,
        bottom: parseInt(_.css("padding-bottom"), 10) || 0,
        right: parseInt(_.css("padding-right"), 10) || 0
    }
};
l10l = function(_, $) {
    _ = OoO1o0(_);
    $ = parseInt($);
    if (isNaN($) || !_) return;
    if (jQuery.boxModel) {
        var A = oloo(_),
        B = o1Oo(_);
        $ = $ - A.left - A.right - B.left - B.right
    }
    if ($ < 0) $ = 0;
    _.style.width = $ + "px"
};
l1101 = function(_, $) {
    _ = OoO1o0(_);
    $ = parseInt($);
    if (isNaN($) || !_) return;
    if (jQuery.boxModel) {
        var A = oloo(_),
        B = o1Oo(_);
        $ = $ - A.top - A.bottom - B.top - B.bottom
    }
    if ($ < 0) $ = 0;
    _.style.height = $ + "px"
};
OlO1 = function($, _) {
    $ = OoO1o0($);
    if ($.style.display == "none" || $.type == "text/javascript") return 0;
    return _ ? jQuery($).width() : jQuery($).outerWidth()
};
O00O = function($, _) {
    $ = OoO1o0($);
    if ($.style.display == "none" || $.type == "text/javascript") return 0;
    return _ ? jQuery($).height() : jQuery($).outerHeight()
};
oo1lo1 = function(A, C, B, $, _) {
    if (B === undefined) {
        B = C.y;
        $ = C.width;
        _ = C.height;
        C = C.x
    }
    mini[_setXY](A, C, B);
    l10l(A, $);
    l1101(A, _)
};
O11O = function(A) {
    var $ = mini.getXY(A),
    _ = {
        x: $[0],
        y: $[1],
        width: OlO1(A),
        height: O00O(A)
    };
    _.left = _.x;
    _.top = _.y;
    _.right = _.x + _.width;
    _.bottom = _.y + _.height;
    return _
};
l00l = function(A, B) {
    A = OoO1o0(A);
    if (!A || typeof B != "string") return;
    var F = jQuery(A),
    _ = B.toLowerCase().split(";");
    for (var $ = 0,
    C = _.length; $ < C; $++) {
        var E = _[$],
        D = E.split(":");
        if (D.length == 2) F.css(D[0].trim(), D[1].trim())
    }
};
Oo0Ooo = function() {
    var $ = document.defaultView;
    return new Function("el", "style", ["style[_indexOf]('-')>-1 && (style=style.replace(/-(\\w)/g,function(m,a){return a.toUpperCase()}));", "style=='float' && (style='", $ ? "cssFloat": "styleFloat", "');return el.style[style] || ", $ ? "window.getComputedStyle(el,null)[style]": "el.currentStyle[style]", " || null;"].join(""))
} ();
lolo = function(A, $) {
    var _ = false;
    A = OoO1o0(A);
    $ = OoO1o0($);
    if (A === $) return true;
    if (A && $) if (A.contains) {
        try {
            return A.contains($)
        } catch(B) {
            return false
        }
    } else if (A.compareDocumentPosition) return !! (A.compareDocumentPosition($) & 16);
    else while ($ = $.parentNode) _ = $ == A || _;
    return _
};
ooOO = function(B, A, $) {
    B = OoO1o0(B);
    var C = document.body,
    _ = 0,
    D;
    $ = $ || 50;
    if (typeof $ != "number") {
        D = OoO1o0($);
        $ = 10
    }
    while (B && B.nodeType == 1 && _ < $ && B != C && B != D) {
        if (ooll(B, A)) return B;
        _++;
        B = B.parentNode
    }
    return null
};
mini.copyTo(mini, {
    byId: OoO1o0,
    hasClass: ooll,
    addClass: o01O,
    removeClass: o110,
    getMargins: O1ll0,
    getBorders: o1Oo,
    getPaddings: oloo,
    setWidth: l10l,
    setHeight: l1101,
    getWidth: OlO1,
    getHeight: O00O,
    setBox: oo1lo1,
    getBox: O11O,
    setStyle: l00l,
    getStyle: Oo0Ooo,
    repaint: function($) {
        if (!$) $ = document.body;
        o01O($, "mini-repaint");
        setTimeout(function() {
            o110($, "mini-repaint")
        },
        1)
    },
    getSize: function($, _) {
        return {
            width: OlO1($, _),
            height: O00O($, _)
        }
    },
    setSize: function(A, $, _) {
        l10l(A, $);
        l1101(A, _)
    },
    setX: function(_, B) {
        B = parseInt(B);
        var $ = jQuery(_).offset(),
        A = parseInt($.top);
        if (A === undefined) A = $[1];
        mini[_setXY](_, B, A)
    },
    setY: function(_, A) {
        A = parseInt(A);
        var $ = jQuery(_).offset(),
        B = parseInt($.left);
        if (B === undefined) B = $[0];
        mini[_setXY](_, B, A)
    },
    setXY: function(_, B, A) {
        var $ = {
            left: parseInt(B),
            top: parseInt(A)
        };
        jQuery(_).offset($);
        jQuery(_).offset($)
    },
    getXY: function(_) {
        var $ = jQuery(_).offset();
        return [parseInt($.left), parseInt($.top)]
    },
    getViewportBox: function() {
        var $ = jQuery(window).width(),
        _ = jQuery(window).height(),
        B = jQuery(document).scrollLeft(),
        A = jQuery(document.body).scrollTop();
        if (document.documentElement) A = document.documentElement.scrollTop;
        return {
            x: B,
            y: A,
            width: $,
            height: _,
            right: B + $,
            bottom: A + _
        }
    },
    getChildNodes: function(A, C) {
        A = OoO1o0(A);
        if (!A) return;
        var E = A.childNodes,
        B = [];
        for (var $ = 0,
        D = E.length; $ < D; $++) {
            var _ = E[$];
            if (_.nodeType == 1 || C === true) B.push(_)
        }
        return B
    },
    removeChilds: function(B, _) {
        B = OoO1o0(B);
        if (!B) return;
        var C = mini[_getChildNodes](B, true);
        for (var $ = 0,
        D = C.length; $ < D; $++) {
            var A = C[$];
            if (_ && A == _);
            else B.removeChild(C[$])
        }
    },
    isAncestor: lolo,
    findParent: ooOO,
    findChild: function(_, A) {
        _ = OoO1o0(_);
        var B = _.getElementsByTagName("*");
        for (var $ = 0,
        C = B.length; $ < C; $++) {
            var _ = B[$];
            if (ooll(_, A)) return _
        }
    },
    isAncestor: function(A, $) {
        var _ = false;
        A = OoO1o0(A);
        $ = OoO1o0($);
        if (A === $) return true;
        if (A && $) if (A.contains) {
            try {
                return A.contains($)
            } catch(B) {
                return false
            }
        } else if (A.compareDocumentPosition) return !! (A.compareDocumentPosition($) & 16);
        else while ($ = $.parentNode) _ = $ == A || _;
        return _
    },
    getOffsetsTo: function(_, A) {
        var $ = this.getXY(_),
        B = this.getXY(A);
        return [$[0] - B[0], $[1] - B[1]]
    },
    scrollIntoView: function(I, H, F) {
        var B = OoO1o0(H) || document.body,
        $ = this.getOffsetsTo(I, B),
        C = $[0] + B.scrollLeft,
        J = $[1] + B.scrollTop,
        D = J + I.offsetHeight,
        A = C + I.offsetWidth,
        G = B.clientHeight,
        K = parseInt(B.scrollTop, 10),
        _ = parseInt(B.scrollLeft, 10),
        L = K + G,
        E = _ + B.clientWidth;
        if (I.offsetHeight > G || J < K) B.scrollTop = J;
        else if (D > L) B.scrollTop = D - G;
        B.scrollTop = B.scrollTop;
        if (F !== false) {
            if (I.offsetWidth > B.clientWidth || C < _) B.scrollLeft = C;
            else if (A > E) B.scrollLeft = A - B.clientWidth;
            B.scrollLeft = B.scrollLeft
        }
        return this
    },
    setOpacity: function(_, $) {
        jQuery(_).css({
            "opacity": $
        })
    },
    selectable: function(_, $) {
        _ = OoO1o0(_);
        if ( !! $) {
            jQuery(_)[_removeClass]("mini-unselectable");
            if (isIE) _.unselectable = "off";
            else {
                _.style.MozUserSelect = "";
                _.style.KhtmlUserSelect = "";
                _.style.UserSelect = ""
            }
        } else {
            jQuery(_)[_addClass]("mini-unselectable");
            if (isIE) _.unselectable = "on";
            else {
                _.style.MozUserSelect = "none";
                _.style.UserSelect = "none";
                _.style.KhtmlUserSelect = "none"
            }
        }
    },
    selectRange: function(B, A, _) {
        if (B.createTextRange) {
            var $ = B.createTextRange();
            $.moveStart("character", A);
            $.moveEnd("character", _ - B.value.length);
            $[_select]()
        } else if (B.setSelectionRange) B.setSelectionRange(A, _);
        try {
            B[_focus]()
        } catch(C) {}
    },
    getSelectRange: function(A) {
        A = OoO1o0(A);
        if (!A) return;
        try {
            A[_focus]()
        } catch(C) {}
        var $ = 0,
        B = 0;
        if (A.createTextRange) {
            var _ = document.selection.createRange().duplicate();
            _.moveEnd("character", A.value.length);
            if (_.text === "") $ = A.value.length;
            else $ = A.value.lastIndexOf(_.text);
            _ = document.selection.createRange().duplicate();
            _.moveStart("character", -A.value.length);
            B = _.text.length
        } else {
            $ = A.selectionStart;
            B = A.selectionEnd
        }
        return [$, B]
    }
}); (function() {
    var $ = {
        tabindex: "tabIndex",
        readonly: "readOnly",
        "for": "htmlFor",
        "class": "className",
        maxlength: "maxLength",
        cellspacing: "cellSpacing",
        cellpadding: "cellPadding",
        rowspan: "rowSpan",
        colspan: "colSpan",
        usemap: "useMap",
        frameborder: "frameBorder",
        contenteditable: "contentEditable"
    },
    _ = document.createElement("div");
    _.setAttribute("class", "t");
    var A = _.className === "t";
    mini.setAttr = function(B, C, _) {
        B.setAttribute(A ? C: ($[C] || C), _)
    };
    mini.getAttr = function(B, D) {
        if (D == "value" && (isIE6 || isIE7)) {
            var _ = B.attributes[D];
            return _ ? _.value: null
        }
        var E = B.getAttribute(A ? D: ($[D] || D));
        if (typeof E == "function") E = B.attributes[D].value;
        if (!E && D == "onload") {
            var C = B.getAttributeNode ? B.getAttributeNode(D) : null;
            if (C) E = C.nodeValue
        }
        return E
    }
})();
ol0ol = function(_, $, C, A) {
    var B = "on" + $.toLowerCase();
    _[B] = function(_) {
        _ = _ || window.event;
        _.target = _.target || _.srcElement;
        if (!_.preventDefault) _.preventDefault = function() {
            if (window.event) window.event.returnValue = false
        };
        if (!_.stopPropogation) _.stopPropogation = function() {
            if (window.event) window.event.cancelBubble = true
        };
        var $ = C[_call](A, _);
        if ($ === false) return false
    }
};
lo1l1o = function(_, $, D, A) {
    _ = OoO1o0(_);
    A = A || _;
    if (!_ || !$ || !D || !A) return false;
    var B = mini[_findListener](_, $, D, A);
    if (B) return false;
    var C = mini.createDelegate(D, A);
    mini.listeners.push([_, $, D, A, C]);
    if (isFirefox && $ == "mousewheel") $ = "DOMMouseScroll";
    jQuery(_).bind($, C)
};
o01o = function(_, $, C, A) {
    _ = OoO1o0(_);
    A = A || _;
    if (!_ || !$ || !C || !A) return false;
    var B = mini[_findListener](_, $, C, A);
    if (!B) return false;
    mini.listeners.remove(B);
    if (isFirefox && $ == "mousewheel") $ = "DOMMouseScroll";
    jQuery(_).unbind($, B[4])
};
mini.copyTo(mini, {
    listeners: [],
    on: lo1l1o,
    un: o01o,
    _getListeners: function() {
        var B = mini.listeners;
        for (var $ = B.length - 1; $ >= 0; $--) {
            var A = B[$];
            try {
                if (A[0] == 1 && A[1] == 1 && A[2] == 1 && A[3] == 1) var _ = 1
            } catch(C) {
                B.removeAt($)
            }
        }
        return B
    },
    findListener: function(A, _, F, B) {
        A = OoO1o0(A);
        B = B || A;
        if (!A || !_ || !F || !B) return false;
        var D = mini._getListeners();
        for (var $ = D.length - 1; $ >= 0; $--) {
            var C = D[$];
            try {
                if (C[0] == A && C[1] == _ && C[2] == F && C[3] == B) return C
            } catch(E) {}
        }
    },
    clearEvent: function(A, _) {
        A = OoO1o0(A);
        if (!A) return false;
        var C = mini._getListeners();
        for (var $ = C.length - 1; $ >= 0; $--) {
            var B = C[$];
            if (B[0] == A) if (!_ || _ == B[1]) o01o(A, B[1], B[2], B[3])
        }
        A.onmouseover = A.onmousedown = null
    }
});
mini.__windowResizes = [];
mini.onWindowResize = function(_, $) {
    mini.__windowResizes.push([_, $])
};
lo1l1o(window, "resize",
function(C) {
    var _ = mini.__windowResizes;
    for (var $ = 0,
    B = _.length; $ < B; $++) {
        var A = _[$];
        A[0][_call](A[1], C)
    }
});
mini.htmlEncode = function(_) {
    if (typeof _ !== "string") return _;
    var $ = "";
    if (_.length == 0) return "";
    $ = _;
    $ = $.replace(/</g, "&lt;");
    $ = $.replace(/>/g, "&gt;");
    $ = $.replace(/ /g, "&nbsp;");
    $ = $.replace(/\'/g, "&#39;");
    $ = $.replace(/\"/g, "&quot;");
    return $
};
mini.htmlDecode = function(_) {
    if (typeof _ !== "string") return _;
    var $ = "";
    if (_.length == 0) return "";
    $ = _.replace(/&gt;/g, "&");
    $ = $.replace(/&lt;/g, "<");
    $ = $.replace(/&gt;/g, ">");
    $ = $.replace(/&nbsp;/g, " ");
    $ = $.replace(/&#39;/g, "'");
    $ = $.replace(/&quot;/g, "\"");
    return $
};
mini.copyTo(Array.prototype, {
    add: Array[_prototype].enqueue = function($) {
        this[this.length] = $;
        return this
    },
    getRange: function(A, B) {
        var C = [];
        for (var _ = A; _ <= B; _++) {
            var $ = this[_];
            if ($) C[C.length] = $
        }
        return C
    },
    addRange: function(A) {
        for (var $ = 0,
        _ = A.length; $ < _; $++) this[this.length] = A[$];
        return this
    },
    clear: function() {
        this.length = 0;
        return this
    },
    clone: function() {
        if (this.length === 1) return [this[0]];
        else return Array.apply(null, this)
    },
    contains: function($) {
        return (this[_indexOf]($) >= 0)
    },
    indexOf: function(_, B) {
        var $ = this.length;
        for (var A = (B < 0) ? Math[_max](0, $ + B) : B || 0; A < $; A++) if (this[A] === _) return A;
        return - 1
    },
    dequeue: function() {
        return this.shift()
    },
    insert: function(_, $) {
        this.splice(_, 0, $);
        return this
    },
    insertRange: function(_, B) {
        for (var A = B.length - 1; A >= 0; A--) {
            var $ = B[A];
            this.splice(_, 0, $)
        }
        return this
    },
    remove: function(_) {
        var $ = this[_indexOf](_);
        if ($ >= 0) this.splice($, 1);
        return ($ >= 0)
    },
    removeAt: function($) {
        var _ = this[$];
        this.splice($, 1);
        return _
    },
    removeRange: function(_) {
        _ = _.clone();
        for (var $ = 0,
        A = _.length; $ < A; $++) this.remove(_[$])
    }
});
mini.Keyboard = {
    Left: 37,
    Top: 38,
    Right: 39,
    Bottom: 40,
    PageUp: 33,
    PageDown: 34,
    End: 35,
    Home: 36,
    Enter: 13,
    ESC: 27,
    Space: 32,
    Tab: 9,
    Del: 46,
    F1: 112,
    F2: 113,
    F3: 114,
    F4: 115,
    F5: 116,
    F6: 117,
    F7: 118,
    F8: 119,
    F9: 120,
    F10: 121,
    F11: 122,
    F12: 123
};
var ua = navigator.userAgent.toLowerCase(),
check = function($) {
    return $.test(ua)
},
DOC = document,
isStrict = DOC.compatMode == "CSS1Compat",
isOpera = Object[_prototype].toString[_call](window.opera) == "[object Opera]",
isChrome = check(/chrome/),
isWebKit = check(/webkit/),
isSafari = !isChrome && check(/safari/),
isSafari2 = isSafari && check(/applewebkit\/4/),
isSafari3 = isSafari && check(/version\/3/),
isSafari4 = isSafari && check(/version\/4/),
isIE = !!window.attachEvent && !isOpera,
isIE7 = isIE && check(/msie 7/),
isIE8 = isIE && check(/msie 8/),
isIE9 = isIE && check(/msie 9/),
isIE10 = isIE && document.documentMode == 10,
isIE6 = isIE && !isIE7 && !isIE8 && !isIE9 && !isIE10,
isFirefox = navigator.userAgent[_indexOf]("Firefox") > 0,
isGecko = !isWebKit && check(/gecko/),
isGecko2 = isGecko && check(/rv:1\.8/),
isGecko3 = isGecko && check(/rv:1\.9/),
isBorderBox = isIE && !isStrict,
isWindows = check(/windows|win32/),
isMac = check(/macintosh|mac os x/),
isAir = check(/adobeair/),
isLinux = check(/linux/),
isSecure = /^https/i.test(window.location.protocol);
if (isIE6) {
    try {
        DOC.execCommand("BackgroundImageCache", false, true)
    } catch(e) {}
}
mini.boxModel = !isBorderBox;
mini.isIE = isIE;
mini.isIE6 = isIE6;
mini.isIE7 = isIE7;
mini.isIE8 = isIE8;
mini.isIE9 = isIE9;
mini.isIE10 = isIE10;
mini.isFirefox = isFirefox;
mini.isOpera = isOpera;
mini.isSafari = isSafari;
mini.isChrome = isChrome;
if (jQuery) jQuery.boxModel = mini.boxModel;
mini.noBorderBox = false;
if (jQuery.boxModel == false && isIE && isIE9 == false) mini.noBorderBox = true;
mini.MouseButton = {
    Left: 0,
    Middle: 1,
    Right: 2
};
if (isIE && !isIE9) mini.MouseButton = {
    Left: 1,
    Middle: 4,
    Right: 2
};
mini._MaskID = 1;
mini._MaskObjects = {};
mini[_mask] = function(C) {
    var _ = OoO1o0(C);
    if (mini.isElement(_)) C = {
        el: _
    };
    else if (typeof C == "string") C = {
        html: C
    };
    C = mini.copyTo({
        html: "",
        cls: "",
        style: "",
        backStyle: "background:#ccc"
    },
    C);
    C.el = OoO1o0(C.el);
    if (!C.el) C.el = document.body;
    _ = C.el;
    mini["unmask"](C.el);
    _._maskid = mini._MaskID++;
    mini._MaskObjects[_._maskid] = C;
    var $ = mini.append(_, "<div class=\"mini-mask\">" + "<div class=\"mini-mask-background\" style=\"" + C.backStyle + "\"></div>" + "<div class=\"mini-mask-msg " + C.cls + "\" style=\"" + C.style + "\">" + C.html + "</div>" + "</div>");
    C.maskEl = $;
    if (!mini.isNull(C.opacity)) mini.setOpacity($.firstChild, C.opacity);
    function A() {
        B.style.display = "block";
        var $ = mini.getSize(B);
        B.style.marginLeft = -$.width / 2 + "px";
        B.style.marginTop = -$.height / 2 + "px"
    }
    var B = $.lastChild;
    B.style.display = "none";
    setTimeout(function() {
        A()
    },
    0)
};
mini["unmask"] = function(_) {
    _ = OoO1o0(_);
    if (!_) _ = document.body;
    var A = mini._MaskObjects[_._maskid];
    if (!A) return;
    delete mini._MaskObjects[_._maskid];
    var $ = A.maskEl;
    A.maskEl = null;
    if ($ && $.parentNode) $.parentNode.removeChild($)
};
mini.Cookie = {
    get: function(D) {
        var A = document.cookie.split("; "),
        B = null;
        for (var $ = 0; $ < A.length; $++) {
            var _ = A[$].split("=");
            if (D == _[0]) B = _
        }
        if (B) {
            var C = B[1];
            if (C === undefined) return C;
            return unescape(C)
        }
        return null
    },
    set: function(C, $, B, A) {
        var _ = new Date();
        if (B != null) _ = new Date(_[_getTime]() + (B * 1000 * 3600 * 24));
        document.cookie = C + "=" + escape($) + ((B == null) ? "": ("; expires=" + _.toGMTString())) + ";path=/" + (A ? "; domain=" + A: "")
    },
    del: function(_, $) {
        this[_set](_, null, -100, $)
    }
};
mini.copyTo(mini, {
    treeToArray: function(C, I, J, A, $) {
        if (!I) I = "children";
        var F = [];
        for (var H = 0,
        D = C.length; H < D; H++) {
            var B = C[H];
            F[F.length] = B;
            if (A) B[A] = $;
            var _ = B[I];
            if (_ && _.length > 0) {
                var E = B[J],
                G = this[_treeToArray](_, I, J, A, E);
                F.addRange(G)
            }
        }
        return F
    },
    arrayToTree: function(C, A, H, B) {
        if (!A) A = "children";
        H = H || "_id";
        B = B || "_pid";
        var G = [],
        F = {};
        for (var _ = 0,
        E = C.length; _ < E; _++) {
            var $ = C[_];
            if (!$) continue;
            var I = $[H];
            if (I !== null && I !== undefined) F[I] = $;
            delete $[A]
        }
        for (_ = 0, E = C.length; _ < E; _++) {
            var $ = C[_],
            D = F[$[B]];
            if (!D) {
                G.push($);
                continue
            }
            if (!D[A]) D[A] = [];
            D[A].push($)
        }
        return G
    }
});
mini.treeToList = mini[_treeToArray];
mini.listToTree = mini.arrayToTree;
function UUID() {
    var A = [],
    _ = "0123456789ABCDEF".split("");
    for (var $ = 0; $ < 36; $++) A[$] = Math.floor(Math.random() * 16);
    A[14] = 4;
    A[19] = (A[19] & 3) | 8;
    for ($ = 0; $ < 36; $++) A[$] = _[A[$]];
    A[8] = A[13] = A[18] = A[23] = "-";
    return A.join("")
}
String.format = function(_) {
    var $ = Array[_prototype].slice[_call](arguments, 1);
    _ = _ || "";
    return _.replace(/\{(\d+)\}/g,
    function(A, _) {
        return $[_]
    })
};
String[_prototype].trim = function() {
    var $ = /^\s+|\s+$/g;
    return function() {
        return this.replace($, "")
    }
} ();
mini.copyTo(mini, {
    measureText: function(B, _, C) {
        if (!this.measureEl) this.measureEl = mini.append(document.body, "<div></div>");
        this.measureEl.style.cssText = "position:absolute;left:-1000px;top:-1000px;visibility:hidden;";
        if (typeof B == "string") this.measureEl.className = B;
        else {
            this.measureEl.className = "";
            var G = jQuery(B),
            A = jQuery(this.measureEl),
            F = ["font-size", "font-style", "font-weight", "font-family", "line-height", "text-transform", "letter-spacing"];
            for (var $ = 0,
            E = F.length; $ < E; $++) {
                var D = F[$];
                A.css(D, G.css(D))
            }
        }
        if (C) l00l(this.measureEl, C);
        this.measureEl.innerHTML = _;
        return mini.getSize(this.measureEl)
    }
});
jQuery(function() {
    var $ = new Date();
    mini.isReady = true;
    mini.parse();
    o00O();
    if ((Oo0Ooo(document.body, "overflow") == "hidden" || Oo0Ooo(document.documentElement, "overflow") == "hidden") && (isIE6 || isIE7)) {
        jQuery(document.body).css("overflow", "visible");
        jQuery(document.documentElement).css("overflow", "visible")
    }
    mini.__LastWindowWidth = document.documentElement.clientWidth;
    mini.__LastWindowHeight = document.documentElement.clientHeight
});
mini_onload = function($) {
    mini.layout(null, false);
    lo1l1o(window, "resize", mini_onresize)
};
lo1l1o(window, "load", mini_onload);
mini.__LastWindowWidth = document.documentElement.clientWidth;
mini.__LastWindowHeight = document.documentElement.clientHeight;
mini.doWindowResizeTimer = null;
mini.allowLayout = true;
mini_onresize = function(A) {
    if (mini.doWindowResizeTimer) clearTimeout(mini.doWindowResizeTimer);
    Oolo = mini.isWindowDisplay();
    if (Oolo == false || mini.allowLayout == false) return;
    if (typeof Ext != "undefined") mini.doWindowResizeTimer = setTimeout(function() {
        var _ = document.documentElement.clientWidth,
        $ = document.documentElement.clientHeight;
        if (mini.__LastWindowWidth == _ && mini.__LastWindowHeight == $);
        else {
            mini.__LastWindowWidth = _;
            mini.__LastWindowHeight = $;
            mini.layout(null, false)
        }
        mini.doWindowResizeTimer = null
    },
    300);
    else {
        var $ = 100;
        try {
            if (parent && parent != window && parent.mini) $ = 0
        } catch(_) {}
        mini.doWindowResizeTimer = setTimeout(function() {
            var _ = document.documentElement.clientWidth,
            $ = document.documentElement.clientHeight;
            if (mini.__LastWindowWidth == _ && mini.__LastWindowHeight == $);
            else {
                mini.__LastWindowWidth = _;
                mini.__LastWindowHeight = $;
                mini.layout(null, false)
            }
            mini.doWindowResizeTimer = null
        },
        $)
    }
};
mini[_isDisplay] = function(_, A) {
    var $ = A || document.body;
    while (1) {
        if (_ == null || !_.style) return false;
        if (_ && _.style && _.style.display == "none") return false;
        if (_ == $) return true;
        _ = _.parentNode
    }
    return true
};
mini.isWindowDisplay = function() {
    try {
        var _ = window.parent,
        E = _ != window;
        if (E) {
            var C = _.document.getElementsByTagName("iframe"),
            H = _.document.getElementsByTagName("frame"),
            G = [];
            for (var $ = 0,
            D = C.length; $ < D; $++) G.push(C[$]);
            for ($ = 0, D = H.length; $ < D; $++) G.push(H[$]);
            var B = null;
            for ($ = 0, D = G.length; $ < D; $++) {
                var A = G[$];
                if (A.contentWindow == window) {
                    B = A;
                    break
                }
            }
            if (!B) return false;
            return mini[_isDisplay](B, _.document.body)
        } else return true
    } catch(F) {
        return true
    }
};
Oolo = mini.isWindowDisplay();
mini.layoutIFrames = function($) {
    if (!document.body) return;
    if (!$) $ = document.body;
    var _ = $.getElementsByTagName("iframe");
    setTimeout(function() {
        for (var A = 0,
        C = _.length; A < C; A++) {
            var B = _[A];
            try {
                if (mini[_isDisplay](B) && lolo($, B)) {
                    if (B.contentWindow.mini) if (B.contentWindow.Oolo == false) {
                        B.contentWindow.Oolo = B.contentWindow.mini.isWindowDisplay();
                        B.contentWindow.mini.layout()
                    } else B.contentWindow.mini.layout(null, false);
                    B.contentWindow.mini.layoutIFrames()
                }
            } catch(D) {}
        }
    },
    30)
};
$.ajaxSetup({
    cache: false
});
if (isIE) setInterval(function() {
    CollectGarbage()
},
1000);
mini_unload = function(H) {
    try {
        var E = mini._getTopWindow();
        E[mini._WindowID] = "";
        delete E[mini._WindowID]
    } catch(D) {}
    var G = document.body.getElementsByTagName("iframe");
    if (G.length > 0) {
        var F = [];
        for (var $ = 0,
        C = G.length; $ < C; $++) F.push(G[$]);
        for ($ = 0, C = F.length; $ < C; $++) {
            try {
                var B = F[$];
                B._ondestroy = null;
                B.src = "";
                try {
                    B.contentWindow.document.write("");
                    B.contentWindow.document.close()
                } catch(D) {}
                if (B.parentNode) B.parentNode.removeChild(B)
            } catch(H) {}
        }
    }
    var A = mini.getComponents();
    for ($ = 0, C = A.length; $ < C; $++) {
        var _ = A[$];
        if (_.destroyed !== true) _[_destroy](false)
    }
    A.length = 0;
    A = null;
    o01o(window, "unload", mini_unload);
    o01o(window, "load", mini_onload);
    o01o(window, "resize", mini_onresize);
    mini.components = {};
    mini.classes = {};
    mini.uiClasses = {};
    mini.uids = {};
    mini._topWindow = null;
    window.mini = null;
    window.Owner = null;
    window.CloseOwnerWindow = null;
    try {
        CollectGarbage()
    } catch(H) {}
};
lo1l1o(window, "unload", mini_unload);
function __OnIFrameMouseDown() {
    jQuery(document).trigger("mousedown")
}
function _ool1() {
    var C = document.getElementsByTagName("iframe");
    for (var $ = 0,
    A = C.length; $ < A; $++) {
        var _ = C[$];
        try {
            if (_.contentWindow) _.contentWindow.document.onmousedown = __OnIFrameMouseDown
        } catch(B) {}
    }
}
setInterval(function() {
    _ool1()
},
1500);
mini.zIndex = 1000;
mini.getMaxZIndex = function() {
    return mini.zIndex++
};
function js_isTouchDevice() {
    try {
        document.createEvent("TouchEvent");
        return true
    } catch($) {
        return false
    }
}
function js_touchScroll(A) {
    if (js_isTouchDevice()) {
        var _ = typeof A == "string" ? document.getElementById(A) : A,
        $ = 0;
        _.addEventListener("touchstart",
        function(_) {
            $ = this.scrollTop + _.touches[0].pageY;
            _.preventDefault()
        },
        false);
        _.addEventListener("touchmove",
        function(_) {
            this.scrollTop = $ - _.touches[0].pageY;
            _.preventDefault()
        },
        false)
    }
}
mini._placeholder = function(A) {
    A = OoO1o0(A);
    if (!A || !isIE || isIE10) return;
    function $() {
        var _ = A._placeholder_label;
        if (!_) return;
        var $ = A.getAttribute("placeholder");
        if (!$) $ = A.placeholder;
        if (!A.value && !A.disabled) {
            _.innerHTML = $;
            _.style.display = ""
        } else _.style.display = "none"
    }
    if (A._placeholder) {
        $();
        return
    }
    A._placeholder = true;
    var _ = document.createElement("label");
    _.className = "mini-placeholder-label";
    A.parentNode.appendChild(_);
    A._placeholder_label = _;
    _.onmousedown = function() {
        A[_focus]()
    };
    A.onpropertychange = function(_) {
        _ = _ || window.event;
        if (_.propertyName == "value") $()
    };
    $();
    lo1l1o(A, "focus",
    function($) {
        if (!A[_readOnly]) _.style.display = "none"
    });
    lo1l1o(A, "blur",
    function(_) {
        $()
    })
};
mini.ajax = function($) {
    if (!$.dataType) $.dataType = "text";
    return window.jQuery.ajax($)
};
mini._evalAjaxData = function(ajaxData, scope) {
    var obj = ajaxData,
    t = typeof ajaxData;
    if (t == "string") {
        obj = eval("(" + ajaxData + ")");
        if (typeof obj == "function") obj = obj[_call](scope)
    }
    return obj
};
if (typeof window.rootpath == "undefined") rootpath = "/";
mini.loadJS = function(_, $) {
    if (!_) return;
    if (typeof $ == "function") return loadJS._async(_, $);
    else return loadJS._sync(_)
};
mini.loadJS._js = {};
mini.loadJS._async = function(D, _) {
    var C = mini.loadJS._js[D];
    if (!C) C = mini.loadJS._js[D] = {
        create: false,
        loaded: false,
        callbacks: []
    };
    if (C.loaded) {
        setTimeout(function() {
            _()
        },
        1);
        return
    } else {
        C.callbacks.push(_);
        if (C.create) return
    }
    C.create = true;
    var B = document.getElementsByTagName("head")[0],
    A = document.createElement("script");
    A.src = D;
    A.type = "text/javascript";
    function $() {
        for (var $ = 0; $ < C.callbacks.length; $++) {
            var _ = C.callbacks[$];
            if (_) _()
        }
        C.callbacks.length = 0
    }
    setTimeout(function() {
        if (document.all) A.onreadystatechange = function() {
            if (A.readyState == "loaded" || A.readyState == "complete") {
                $();
                C.loaded = true
            }
        };
        else A.onload = function() {
            $();
            C.loaded = true
        };
        B.appendChild(A)
    },
    1);
    return A
};
mini.loadJS._sync = function(A) {
    if (loadJS._js[A]) return;
    loadJS._js[A] = {
        create: true,
        loaded: true,
        callbacks: []
    };
    var _ = document.getElementsByTagName("head")[0],
    $ = document.createElement("script");
    $.type = "text/javascript";
    $.text = loadText(A);
    _.appendChild($);
    return $
};
mini.loadText = function(C) {
    var B = "",
    D = document.all && location.protocol == "file:",
    A = null;
    if (D) A = new ActiveXObject("Microsoft.XMLHTTP");
    else if (window.XMLHttpRequest) A = new XMLHttpRequest();
    else if (window.ActiveXObject) A = new ActiveXObject("Microsoft.XMLHTTP");
    A.onreadystatechange = $;
    var _ = "_t=" + new Date()[_getTime]();
    if (C[_indexOf]("?") == -1) _ = "?" + _;
    else _ = "&" + _;
    C += _;
    A.open("GET", C, false);
    A.send(null);
    function $() {
        if (A.readyState == 4) {
            var $ = D ? 0 : 200;
            if (A.status == $) B = A.responseText
        }
    }
    return B
};
mini.loadJSON = function(url) {
    var text = loadText(url),
    o = eval("(" + text + ")");
    return o
};
mini.loadCSS = function(A, B) {
    if (!A) return;
    if (loadCSS._css[A]) return;
    var $ = document.getElementsByTagName("head")[0],
    _ = document.createElement("link");
    if (B) _.id = B;
    _.href = A;
    _.rel = "stylesheet";
    _.type = "text/css";
    $.appendChild(_);
    return _
};
mini.loadCSS._css = {};
mini.innerHTML = function(A, _) {
    if (typeof A == "string") A = document.getElementById(A);
    if (!A) return;
    _ = "<div style=\"display:none\">&nbsp;</div>" + _;
    A.innerHTML = _;
    mini.__executeScripts(A);
    var $ = A.firstChild
};
mini.__executeScripts = function($) {
    var A = $.getElementsByTagName("script");
    for (var _ = 0,
    E = A.length; _ < E; _++) {
        var B = A[_],
        D = B.src;
        if (D) mini.loadJS(D);
        else {
            var C = document.createElement("script");
            C.type = "text/javascript";
            C.text = B.text;
            $.appendChild(C)
        }
    }
    for (_ = A.length - 1; _ >= 0; _--) {
        B = A[_];
        B.parentNode.removeChild(B)
    }
};
l1OO0 = function() {
    l1OO0[_superclass][_constructor][_call](this)
};
lol01(l1OO0, O11lO0, {
    _clearBorder: false,
    formField: true,
    value: "",
    uiCls: "mini-hidden"
});
lo00o = l1OO0[_prototype];
lo00o[_getFormValue] = O1O0o;
lo00o[_getValue] = lO0Oo;
lo00o[_setValue] = o1o1O;
lo00o[_setName] = l0001;
lo00o[_create] = oOoo;
O1o0(l1OO0, "hidden");
O1lo0o = function() {
    O1lo0o[_superclass][_constructor][_call](this);
    this[_setVisible](false);
    this[_setAllowDrag](this.allowDrag);
    this[_setAllowResize](this[_allowResize])
};
lol01(O1lo0o, mini.Container, {
    _clearBorder: false,
    uiCls: "mini-popup"
});
l1OoO = O1lo0o[_prototype];
l1OoO[_getAttrs] = o10l1;
l1OoO[_setBody] = l0lol;
l1OoO[_setHeight] = oO011l;
l1OoO[_setWidth] = l100l;
l1OoO[_destroy] = ll00o;
l1OoO[_doLayout] = Ooo01o;
l1OoO[_initEvents] = o0ol1;
l1OoO[_create] = llO10;
O1o0(O1lo0o, "popup");
O1lo0o_prototype = {
    isPopup: false,
    popupEl: null,
    popupCls: "",
    showAction: "mouseover",
    hideAction: "outerclick",
    showDelay: 300,
    hideDelay: 500,
    xAlign: "left",
    yAlign: "below",
    xOffset: 0,
    yOffset: 0,
    minWidth: 50,
    minHeight: 25,
    maxWidth: 2000,
    maxHeight: 2000,
    showModal: false,
    showShadow: true,
    modalStyle: "opacity:0.2",
    o0o1lo: "mini-popup-drag",
    Oloo1: "mini-popup-resize",
    allowDrag: false,
    allowResize: false,
    ol1oOO: function() {
        if (!this.popupEl) return;
        o01o(this.popupEl, "click", this.l10oO1, this);
        o01o(this.popupEl, "contextmenu", this.O10lO, this);
        o01o(this.popupEl, "mouseover", this.Oll1, this)
    },
    OllO: function() {
        if (!this.popupEl) return;
        lo1l1o(this.popupEl, "click", this.l10oO1, this);
        lo1l1o(this.popupEl, "contextmenu", this.O10lO, this);
        lo1l1o(this.popupEl, "mouseover", this.Oll1, this)
    },
    doShow: function(A) {
        var $ = {
            popupEl: this.popupEl,
            htmlEvent: A,
            cancel: false
        };
        this[_fire]("BeforeOpen", $);
        if ($.cancel == true) return;
        this[_fire]("opening", $);
        if ($.cancel == true) return;
        if (!this.popupEl) this[_show]();
        else {
            var _ = {};
            if (A) _.xy = [A.pageX, A.pageY];
            this[_showAtEl](this.popupEl, _)
        }
    },
    doHide: function(_) {
        var $ = {
            popupEl: this.popupEl,
            htmlEvent: _,
            cancel: false
        };
        this[_fire]("BeforeClose", $);
        if ($.cancel == true) return;
        this.close()
    },
    show: function(_, $) {
        this[_showAtPos](_, $)
    },
    showAtPos: function(B, A) {
        this[_render](document.body);
        if (!B) B = "center";
        if (!A) A = "middle";
        this.el.style.position = "absolute";
        this.el.style.left = "-2000px";
        this.el.style.top = "-2000px";
        this.el.style.display = "";
        this.Oool();
        var _ = mini.getViewportBox(),
        $ = O11O(this.el);
        if (B == "left") B = 0;
        if (B == "center") B = _.width / 2 - $.width / 2;
        if (B == "right") B = _.width - $.width;
        if (A == "top") A = 0;
        if (A == "middle") A = _.y + _.height / 2 - $.height / 2;
        if (A == "bottom") A = _.height - $.height;
        if (B + $.width > _.right) B = _.right - $.width;
        if (A + $.height > _.bottom) A = _.bottom - $.height - 20;
        this.OO00o(B, A)
    },
    Ollo1o: function() {
        jQuery(this.o1l0).remove();
        if (!this[_showModal]) return;
        if (this.visible == false) return;
        var $ = document.documentElement,
        A = parseInt(Math[_max](document.body.scrollWidth, $ ? $.scrollWidth: 0)),
        D = parseInt(Math[_max](document.body.scrollHeight, $ ? $.scrollHeight: 0)),
        C = mini.getViewportBox(),
        B = C.height;
        if (B < D) B = D;
        var _ = C.width;
        if (_ < A) _ = A;
        this.o1l0 = mini.append(document.body, "<div class=\"mini-modal\"></div>");
        this.o1l0.style.height = B + "px";
        this.o1l0.style.width = _ + "px";
        this.o1l0.style.zIndex = Oo0Ooo(this.el, "zIndex") - 1;
        l00l(this.o1l0, this.modalStyle)
    },
    loo0: function() {
        if (!this.shadowEl) this.shadowEl = mini.append(document.body, "<div class=\"mini-shadow\"></div>");
        this.shadowEl.style.display = this[_showShadow] ? "": "none";
        if (this[_showShadow]) {
            function $() {
                this.shadowEl.style.display = "";
                var $ = O11O(this.el),
                A = this.shadowEl.style;
                A.width = $.width + "px";
                A.height = $.height + "px";
                A.left = $.x + "px";
                A.top = $.y + "px";
                var _ = Oo0Ooo(this.el, "zIndex");
                if (!isNaN(_)) this.shadowEl.style.zIndex = _ - 2
            }
            this.shadowEl.style.display = "none";
            if (this.loo0Timer) {
                clearTimeout(this.loo0Timer);
                this.loo0Timer = null
            }
            var _ = this;
            this.loo0Timer = setTimeout(function() {
                _.loo0Timer = null;
                $[_call](_)
            },
            20)
        }
    },
    Oool: function() {
        this.el.style.display = "";
        var $ = O11O(this.el);
        if ($.width > this.maxWidth) {
            l10l(this.el, this.maxWidth);
            $ = O11O(this.el)
        }
        if ($.height > this.maxHeight) {
            l1101(this.el, this.maxHeight);
            $ = O11O(this.el)
        }
        if ($.width < this.minWidth) {
            l10l(this.el, this.minWidth);
            $ = O11O(this.el)
        }
        if ($.height < this.minHeight) {
            l1101(this.el, this.minHeight);
            $ = O11O(this.el)
        }
    },
    _getWindowOffset: function($) {
        return [0, 0]
    },
    showAtEl: function(I, E) {
        I = OoO1o0(I);
        if (!I) return;
        if (!this[_isRender]() || this.el.parentNode != document.body) this[_render](document.body);
        var B = {
            atEl: I,
            popupEl: this.el,
            xAlign: this.xAlign,
            yAlign: this.yAlign,
            xOffset: this.xOffset,
            yOffset: this.yOffset,
            popupCls: this.popupCls
        };
        mini.copyTo(B, E);
        o01O(I, B.popupCls);
        I.popupCls = B.popupCls;
        this._popupEl = I;
        this.el.style.position = "absolute";
        this.el.style.left = "-2000px";
        this.el.style.top = "-2000px";
        this.el.style.display = "";
        this[_doLayout]();
        this.Oool();
        var K = mini.getViewportBox(),
        C = O11O(this.el),
        M = O11O(I),
        G = B.xy,
        D = B.xAlign,
        F = B.yAlign,
        N = K.width / 2 - C.width / 2,
        L = 0;
        if (G) {
            N = G[0];
            L = G[1]
        }
        switch (B.xAlign) {
        case "outleft":
            N = M.x - C.width;
            break;
        case "left":
            N = M.x;
            break;
        case "center":
            N = M.x + M.width / 2 - C.width / 2;
            break;
        case "right":
            N = M.right - C.width;
            break;
        case "outright":
            N = M.right;
            break;
        default:
            break
        }
        switch (B.yAlign) {
        case "above":
            L = M.y - C.height;
            break;
        case "top":
            L = M.y;
            break;
        case "middle":
            L = M.y + M.height / 2 - C.height / 2;
            break;
        case "bottom":
            L = M.bottom - C.height;
            break;
        case "below":
            L = M.bottom;
            break;
        default:
            break
        }
        N = parseInt(N);
        L = parseInt(L);
        var A = this._getWindowOffset(E);
        if (B.outYAlign || B.outXAlign) {
            if (B.outYAlign == "above") if (L + C.height > K.bottom) {
                var _ = M.y - K.y,
                J = K.bottom - M.bottom;
                if (_ > J) L = M.y - C.height
            }
            if (B.outXAlign == "outleft") if (N + C.width > K.right) {
                var H = M.x - K.x,
                $ = K.right - M.right;
                if (H > $) N = M.x - C.width
            }
            if (B.outXAlign == "right") if (N + C.width > K.right) N = M.right - C.width;
            this.OO00o(N + A[0], L + A[1])
        } else this[_showAtPos](N + B.xOffset + A[0], L + B.yOffset + A[1])
    },
    OO00o: function(A, _) {
        this.el.style.display = "";
        this.el.style.zIndex = mini.getMaxZIndex();
        mini.setX(this.el, A);
        mini.setY(this.el, _);
        this[_setVisible](true);
        if (this.hideAction == "mouseout") lo1l1o(document, "mousemove", this.o1100O, this);
        var $ = this;
        this.loo0();
        this.Ollo1o();
        mini.layoutIFrames(this.el);
        this.isPopup = true;
        lo1l1o(document, "mousedown", this.Ool1O, this);
        lo1l1o(window, "resize", this.ooo0l, this);
        this[_fire]("Open")
    },
    open: function() {
        this[_show]()
    },
    close: function() {
        this[_hide]()
    },
    hide: function() {
        if (!this.el) return;
        if (this.popupEl) o110(this.popupEl, this.popupEl.popupCls);
        if (this._popupEl) o110(this._popupEl, this._popupEl.popupCls);
        this._popupEl = null;
        jQuery(this.o1l0).remove();
        if (this.shadowEl) this.shadowEl.style.display = "none";
        o01o(document, "mousemove", this.o1100O, this);
        o01o(document, "mousedown", this.Ool1O, this);
        o01o(window, "resize", this.ooo0l, this);
        this[_setVisible](false);
        this.isPopup = false;
        this[_fire]("Close")
    },
    setPopupEl: function($) {
        $ = OoO1o0($);
        if (!$) return;
        this.ol1oOO();
        this.popupEl = $;
        this.OllO()
    },
    setPopupCls: function($) {
        this.popupCls = $
    },
    setShowAction: function($) {
        this.showAction = $
    },
    setHideAction: function($) {
        this.hideAction = $
    },
    setShowDelay: function($) {
        this.showDelay = $
    },
    setHideDelay: function($) {
        this.hideDelay = $
    },
    setXAlign: function($) {
        this.xAlign = $
    },
    setYAlign: function($) {
        this.yAlign = $
    },
    setxOffset: function($) {
        $ = parseInt($);
        if (isNaN($)) $ = 0;
        this.xOffset = $
    },
    setyOffset: function($) {
        $ = parseInt($);
        if (isNaN($)) $ = 0;
        this.yOffset = $
    },
    setShowModal: function($) {
        this[_showModal] = $
    },
    setShowShadow: function($) {
        this[_showShadow] = $
    },
    setMinWidth: function($) {
        if (isNaN($)) return;
        this.minWidth = $
    },
    setMinHeight: function($) {
        if (isNaN($)) return;
        this.minHeight = $
    },
    setMaxWidth: function($) {
        if (isNaN($)) return;
        this.maxWidth = $
    },
    setMaxHeight: function($) {
        if (isNaN($)) return;
        this.maxHeight = $
    },
    setAllowDrag: function($) {
        this.allowDrag = $;
        o110(this.el, this.o0o1lo);
        if ($) o01O(this.el, this.o0o1lo)
    },
    setAllowResize: function($) {
        this[_allowResize] = $;
        o110(this.el, this.Oloo1);
        if ($) o01O(this.el, this.Oloo1)
    },
    l10oO1: function(_) {
        if (this.OlOol) return;
        if (this.showAction != "leftclick") return;
        var $ = jQuery(this.popupEl).attr("allowPopup");
        if (String($) == "false") return;
        this.doShow(_)
    },
    O10lO: function(_) {
        if (this.OlOol) return;
        if (this.showAction != "rightclick") return;
        var $ = jQuery(this.popupEl).attr("allowPopup");
        if (String($) == "false") return;
        _.preventDefault();
        this.doShow(_)
    },
    Oll1: function(A) {
        if (this.OlOol) return;
        if (this.showAction != "mouseover") return;
        var _ = jQuery(this.popupEl).attr("allowPopup");
        if (String(_) == "false") return;
        clearTimeout(this._hideTimer);
        this._hideTimer = null;
        if (this.isPopup) return;
        var $ = this;
        this._showTimer = setTimeout(function() {
            $.doShow(A)
        },
        this.showDelay)
    },
    o1100O: function($) {
        if (this.hideAction != "mouseout") return;
        this.l0ol1($)
    },
    Ool1O: function($) {
        if (this.hideAction != "outerclick") return;
        if (!this.isPopup) return;
        if (this[_within]($) || (this.popupEl && lolo(this.popupEl, $.target)));
        else this.doHide($)
    },
    l0ol1: function(_) {
        if (lolo(this.el, _.target) || (this.popupEl && lolo(this.popupEl, _.target)));
        else {
            clearTimeout(this._showTimer);
            this._showTimer = null;
            if (this._hideTimer) return;
            var $ = this;
            this._hideTimer = setTimeout(function() {
                $.doHide(_)
            },
            this.hideDelay)
        }
    },
    ooo0l: function($) {
        if (this[_isDisplay]() && !mini.isIE6) this.Ollo1o()
    },
    within: function(C) {
        if (lolo(this.el, C.target)) return true;
        var $ = mini.getChildControls(this);
        for (var _ = 0,
        B = $.length; _ < B; _++) {
            var A = $[_];
            if (A[_within](C)) return true
        }
        return false
    }
};
mini.copyTo(O1lo0o.prototype, O1lo0o_prototype);
OoOoOO = function() {
    OoOoOO[_superclass][_constructor][_call](this)
};
lol01(OoOoOO, O11lO0, {
    text: "",
    iconCls: "",
    iconStyle: "",
    plain: false,
    checkOnClick: false,
    checked: false,
    groupName: "",
    O1o000: "mini-button-plain",
    _hoverCls: "mini-button-hover",
    lOOol0: "mini-button-pressed",
    o0OOl1: "mini-button-checked",
    lo1l0: "mini-button-disabled",
    allowCls: "",
    _clearBorder: false,
    uiCls: "mini-button",
    href: "",
    target: ""
});
O01OO = OoOoOO[_prototype];
O01OO[_getAttrs] = l11oOl;
O01OO[_onClick] = lOl100;
O01OO.lll0 = oolOl;
O01OO.Oo00O = oOOlo;
O01OO.O0O1l = o0oOl;
O01OO[_doClick] = lO1o;
O01OO[_getChecked] = o0Ooo;
O01OO[_setChecked] = l111o;
O01OO[_getCheckOnClick] = O11ll;
O01OO[_setCheckOnClick] = o0o00l;
O01OO[_getGroupName] = o011Ol;
O01OO[_setGroupName] = ooo01;
O01OO[_getPlain] = o0lo0o;
O01OO[_setPlain] = O00lo;
O01OO[_getIconPosition] = l0loO;
O01OO[_setIconPosition] = l0l1o;
O01OO[_getIconStyle] = l00o0;
O01OO[_setIconStyle] = l1ol1O;
O01OO[_getIconCls] = oOO10;
O01OO[_setIconCls] = ll11;
O01OO[_getText] = O1o0o1;
O01OO[_setText] = OOlo1;
O01OO[_getTarget] = lOl0o1;
O01OO[_setTarget] = Oll000;
O01OO[_getHref] = Oo1oO;
O01OO[_setHref] = o1oo;
O01OO[_doUpdate] = OlOOlO;
O01OO[_destroy] = OOOoO;
O01OO[_initEvents] = lololo;
O01OO[_create] = olO001;
O01OO[_set] = l01O11;
O1o0(OoOoOO, "button");
l01O10 = function() {
    l01O10[_superclass][_constructor][_call](this)
};
lol01(l01O10, OoOoOO, {
    uiCls: "mini-menubutton",
    allowCls: "mini-button-menu"
});
lllol = l01O10[_prototype];
lllol[_setEnabled] = o101l;
lllol[_setMenu] = Oo0Oll;
O1o0(l01O10, "menubutton");
mini.SplitButton = function() {
    mini.SplitButton[_superclass][_constructor][_call](this)
};
lol01(mini.SplitButton, l01O10, {
    uiCls: "mini-splitbutton",
    allowCls: "mini-button-split"
});
O1o0(mini.SplitButton, "splitbutton");
Oll1ll = function() {
    Oll1ll[_superclass][_constructor][_call](this)
};
lol01(Oll1ll, O11lO0, {
    formField: true,
    _clearText: false,
    text: "",
    checked: false,
    defaultValue: false,
    trueValue: true,
    falseValue: false,
    uiCls: "mini-checkbox"
});
Ol0oll = Oll1ll[_prototype];
Ol0oll[_getAttrs] = llol0;
Ol0oll.O0l00 = l0lOO;
Ol0oll[_getFalseValue] = Ol1o1;
Ol0oll[_setFalseValue] = loo1;
Ol0oll[_getTrueValue] = lllOo;
Ol0oll[_setTrueValue] = ll01O;
Ol0oll[_getFormValue] = O0o10;
Ol0oll[_getValue] = lO1Oo1;
Ol0oll[_setValue] = o0l0O;
Ol0oll[_getChecked] = OoOO0o;
Ol0oll[_setChecked] = ol0l;
Ol0oll[_getText] = oOoOl;
Ol0oll[_setText] = l1Olo;
Ol0oll[_setName] = oO1o;
Ol0oll[_initEvents] = l0oO1;
Ol0oll[_destroy] = ooOO1;
Ol0oll[_create] = lO0l1;
O1o0(Oll1ll, "checkbox");
Ol111o = function() {
    Ol111o[_superclass][_constructor][_call](this);
    var $ = this[_isReadOnly]();
    if ($ || this.allowInput == false) this.OllOo1[_readOnly] = true;
    if (this.enabled == false) this[_addCls](this.lo1l0);
    if ($) this[_addCls](this.O1oolo);
    if (this.required) this[_addCls](this.l0o10)
};
lol01(Ol111o, o0l01o, {
    name: "",
    formField: true,
    selectOnFocus: false,
    showClose: false,
    emptyText: "",
    defaultValue: "",
    defaultText: "",
    value: "",
    text: "",
    maxLength: 1000,
    minLength: 0,
    width: 125,
    height: 21,
    inputAsValue: false,
    allowInput: true,
    o1O0oO: "mini-buttonedit-noInput",
    O1oolo: "mini-buttonedit-readOnly",
    lo1l0: "mini-buttonedit-disabled",
    OoOoo: "mini-buttonedit-empty",
    l10oOl: "mini-buttonedit-focus",
    OlOll: "mini-buttonedit-button",
    O1lOOl: "mini-buttonedit-button-hover",
    o0o1: "mini-buttonedit-button-pressed",
    _closeCls: "mini-buttonedit-close",
    uiCls: "mini-buttonedit",
    l1lo: false,
    _buttonWidth: 20,
    _closeWidth: 20,
    O1o01: null,
    textName: "",
    inputStyle: ""
});
o0l01 = Ol111o[_prototype];
o0l01[_getAttrs] = l1lo1;
o0l01[_setInputStyle] = o10O1;
o0l01[_getShowClose] = l00O0;
o0l01[_setShowClose] = l11OO0;
o0l01[_getSelectOnFocus] = lo01O;
o0l01[_setSelectOnFocus] = oO1lo;
o0l01[_getTextName] = loOlO;
o0l01[_setTextName] = O100;
o0l01[_onTextChanged] = l0111;
o0l01[_onButtonMouseDown] = OoO1;
o0l01[_onButtonClick] = O1o1O;
o0l01.Ooo0 = ollOo;
o0l01.O0ool = Ol11O;
o0l01.lo1l = olOO1l;
o0l01.lOloO = lOl0O;
o0l01.O0O1 = O1l00;
o0l01.l0Olo1 = loOl0;
o0l01.o1OlO = O0oOo;
o0l01[__fireBlur] = Oo0ol;
o0l01[__doFocusCls] = O10o1;
o0l01.o101o = O110O;
o0l01.lll0 = OOloo;
o0l01.Oo00O = oo01l;
o0l01.O0O1l = loOO1;
o0l01.OOO11l = l110o;
o0l01[_getErrorIconEl] = loo00;
o0l01[_getInputAsValue] = o101;
o0l01[_setInputAsValue] = OOOOo;
o0l01[_getAllowInput] = l0l00;
o0l01[_setAllowInput] = O0o01;
o0l01.OOo1o = o0l1o;
o0l01[_setEnabled] = oo1lO;
o0l01[_getMinLength] = oO1l1;
o0l01[_setMinLength] = O011o;
o0l01[_getMaxLength] = o1o11;
o0l01[_setMaxLength] = Ol1lO;
o0l01[_getEmptyText] = O0O1o;
o0l01[_setEmptyText] = ol1oo;
o0l01.ll01O1 = loolo;
o0l01[_getFormValue] = Oll0o;
o0l01[_getValue] = ll0OO;
o0l01[_setValue] = lOl10;
o0l01[_getText] = oloo1;
o0l01[_setText] = o0O10;
o0l01[_setName] = l10O1;
o0l01[_getTextEl] = oloo1El;
o0l01[_selectText] = llOl1;
o0l01[_blur] = lOoO0;
o0l01[_focus] = lO0l;
o0l01[_setHeight] = o1lol;
o0l01[_doLayout] = O1l1O;
o0l01[_doInputLayout] = O10Oo;
o0l01.O0O001 = oO10;
o0l01[_initEvents] = lolOl;
o0l01[_destroy] = OOoll;
o0l01[_create] = o0lll;
o0l01.ollOHtml = lo0oo;
o0l01.ollOsHTML = lOol0;
o0l01[_set] = Oo1lo;
O1o0(Ol111o, "buttonedit");
o0olOl = function() {
    o0olOl[_superclass][_constructor][_call](this)
};
lol01(o0olOl, o0l01o, {
    name: "",
    formField: true,
    selectOnFocus: false,
    minWidth: 10,
    minHeight: 15,
    maxLength: 5000,
    emptyText: "",
    text: "",
    value: "",
    defaultValue: "",
    width: 125,
    height: 21,
    OoOoo: "mini-textbox-empty",
    l10oOl: "mini-textbox-focus",
    lo1l0: "mini-textbox-disabled",
    uiCls: "mini-textbox",
    l0ol: "text",
    l1lo: false,
    _placeholdered: false,
    O1o01: null,
    inputStyle: "",
    vtype: ""
});
OOo00 = o0olOl[_prototype];
OOo00[_getRangeErrorText] = OO1lo;
OOo00[_setRangeErrorText] = l10llo;
OOo00[_getRangeCharErrorText] = olOO;
OOo00[_setRangeCharErrorText] = lO1O;
OOo00[_getRangeLengthErrorText] = ooooO;
OOo00[_setRangeLengthErrorText] = oo0O0;
OOo00[_getMinErrorText] = O00Oo;
OOo00[_setMinErrorText] = lo11;
OOo00[_getMaxErrorText] = oOlo1;
OOo00[_setMaxErrorText] = O1Oo1;
OOo00[_getMinLengthErrorText] = O1llo;
OOo00[_setMinLengthErrorText] = l1l01O;
OOo00[_getMaxLengthErrorText] = O1olO;
OOo00[_setMaxLengthErrorText] = ol1OO;
OOo00[_getDateErrorText] = l1oOl;
OOo00[_setDateErrorText] = OO1Ol;
OOo00[_getIntErrorText] = OllOl;
OOo00[_setIntErrorText] = O00oO;
OOo00[_getFloatErrorText] = o0ooo;
OOo00[_setFloatErrorText] = ll01o;
OOo00[_getUrlErrorText] = oo1o0;
OOo00[_setUrlErrorText] = O1oo;
OOo00[_getEmailErrorText] = olo00;
OOo00[_setEmailErrorText] = O0100;
OOo00.o1oO = OOlOo;
OOo00[_getVtype] = o0Ol0;
OOo00[_setVtype] = o0oOo;
OOo00[_getAttrs] = OOoo1;
OOo00[_setInputStyle] = o111o;
OOo00.o1OlO = lloOl;
OOo00.o101o = Olo0;
OOo00.lo1l = OloOo;
OOo00.lOloO = ll1oo;
OOo00.l0Olo1 = oOo0O;
OOo00.oool0l = Olo0l;
OOo00.O0O1 = lOo00;
OOo00.Oo00O = l1lolo;
OOo00.OOO11l = oo0o1;
OOo00[_getErrorIconEl] = l00ol;
OOo00[_getSelectOnFocus] = o00011;
OOo00[_setSelectOnFocus] = ollO1;
OOo00[_getInputText] = O1lol;
OOo00[_getTextEl] = O00O0;
OOo00[_selectText] = l0o10O;
OOo00[_blur] = Olo01;
OOo00[_focus] = lOo11O;
OOo00[_doUpdate] = OolOO0;
OOo00[_setEnabled] = O00oOO;
OOo00[_setReadOnly] = l010l;
OOo00[_getMaxLength] = o10l;
OOo00.ol0oO = oOoO;
OOo00[_setMaxLength] = oO1l0l;
OOo00[_getEmptyText] = oOl1o;
OOo00[_setEmptyText] = ololO;
OOo00.ll01O1 = l1o1O1;
OOo00[_getAllowInput] = ool1l;
OOo00[_setAllowInput] = lOl1o;
OOo00[_getFormValue] = oolol;
OOo00[_getValue] = o1ll0;
OOo00[_setValue] = olo0o;
OOo00[_setName] = oOOO;
OOo00[_setHeight] = lO10o;
OOo00[_doLayout] = o0ool;
OOo00[_destroy] = lllo0;
OOo00.O0O001 = Ol0oO;
OOo00[_initEvents] = ll1l1;
OOo00[_create] = olo0l;
O1o0(o0olOl, "textbox");
l00o11 = function() {
    l00o11[_superclass][_constructor][_call](this)
};
lol01(l00o11, o0olOl, {
    uiCls: "mini-password",
    l0ol: "password"
});
olOlO = l00o11[_prototype];
olOlO[_setEmptyText] = Ol0o0;
O1o0(l00o11, "password");
lOO0Oo = function() {
    lOO0Oo[_superclass][_constructor][_call](this)
};
lol01(lOO0Oo, o0olOl, {
    maxLength: 10000000,
    width: 180,
    height: 50,
    minHeight: 50,
    l0ol: "textarea",
    uiCls: "mini-textarea"
});
OOOO = lOO0Oo[_prototype];
OOOO[_doLayout] = llOOl;
O1o0(lOO0Oo, "textarea");
ll01oO = function() {
    ll01oO[_superclass][_constructor][_call](this);
    this[_createPopup]();
    this.el.className += " mini-popupedit"
};
lol01(ll01oO, Ol111o, {
    uiCls: "mini-popupedit",
    popup: null,
    popupCls: "mini-buttonedit-popup",
    _hoverCls: "mini-buttonedit-hover",
    lOOol0: "mini-buttonedit-pressed",
    _destroyPopup: true,
    popupWidth: "100%",
    popupMinWidth: 50,
    popupMaxWidth: 2000,
    popupHeight: "",
    popupMinHeight: 30,
    popupMaxHeight: 2000
});
OOoOl = ll01oO[_prototype];
OOoOl[_getAttrs] = OOO1o;
OOoOl.l1O1 = OO0oo;
OOoOl.O0O1l = l10111;
OOoOl[_getPopupMinHeight] = ol000;
OOoOl[_getPopupMaxHeight] = looo1;
OOoOl[_getPopupHeight] = ll0Oo;
OOoOl[_setPopupMinHeight] = llll;
OOoOl[_setPopupMaxHeight] = o0Oll;
OOoOl[_setPopupHeight] = oolo0;
OOoOl[_getPopupMinWidth] = lOO1O;
OOoOl[_getPopupMaxWidth] = lOl11;
OOoOl[_getPopupWidth] = O101l;
OOoOl[_setPopupMinWidth] = o1lOO;
OOoOl[_setPopupMaxWidth] = oOl11;
OOoOl[_setPopupWidth] = oll0l;
OOoOl[_isShowPopup] = oo00ol;
OOoOl[_hidePopup] = oooll;
OOoOl.ooo00 = o0l10;
OOoOl.Olol0AtEl = OO0O1;
OOoOl[_syncShowPopup] = O00ll;
OOoOl[_doLayout] = O10oO;
OOoOl[_showPopup] = oo000;
OOoOl.O101 = lOlol;
OOoOl.o1o0 = O10l1;
OOoOl[_createPopup] = olOO0;
OOoOl[_getPopup] = OOOl1;
OOoOl[_setPopup] = OOOlo1;
OOoOl[_within] = OO1OO;
OOoOl.l0Olo1 = Olooo;
OOoOl.Oo00O = Oool1;
OOoOl.oll1OO = O1lOo;
OOoOl.Oll1 = Ooll0;
OOoOl.o1OlO = oO1O0;
OOoOl.loo11 = o110o;
OOoOl[_initEvents] = oOoll;
OOoOl[_destroy] = l00Oo;
O1o0(ll01oO, "popupedit");
oO1lOo = function() {
    this.data = [];
    this.columns = [];
    oO1lOo[_superclass][_constructor][_call](this);
    var $ = this;
    if (isFirefox) this.OllOo1.oninput = function() {
        $.o0lO()
    }
};
lol01(oO1lOo, ll01oO, {
    text: "",
    value: "",
    valueField: "id",
    textField: "text",
    dataField: "",
    delimiter: ",",
    multiSelect: false,
    data: [],
    url: "",
    columns: [],
    allowInput: false,
    valueFromSelect: false,
    popupMaxHeight: 200,
    uiCls: "mini-combobox",
    showNullItem: false
});
o01lO = oO1lOo[_prototype];
o01lO[_getAttrs] = OO101;
o01lO[_setAjaxType] = lo00O;
o01lO[_setAjaxData] = oo00o;
o01lO.O0O1 = o0lOo;
o01lO[_findItems] = Ololo;
o01lO.ooo00 = loo0o;
o01lO.lolo0 = Olllo;
o01lO.o0lO = Ol01o;
o01lO.lo1l = llO1;
o01lO.lOloO = lO1o0;
o01lO.l0Olo1 = O1o11;
o01lO.loOl = Ool00;
o01lO[__OnItemDrawCell] = Ol010;
o01lO[_getSelected] = Oo0001;
o01lO[_getSelecteds] = Oo0001s;
o01lO.l000OO = ll000;
o01lO[_getValueFromSelect] = lO01O;
o01lO[_setValueFromSelect] = OO11O;
o01lO[_getNullItemText] = llO0o0;
o01lO[_setNullItemText] = lolO;
o01lO[_getShowNullItem] = Oo1lO;
o01lO[_setShowNullItem] = o1000;
o01lO[_getColumns] = Oloo0;
o01lO[_setColumns] = l00O1;
o01lO[_getMultiSelect] = o1oOl;
o01lO[_setMultiSelect] = ll1o0;
o01lO[_setValue] = lOOloo;
o01lO[_getDataField] = OOo1OO;
o01lO[_setDataField] = l1o11;
o01lO[_setDisplayField] = Ol1loO;
o01lO[_getTextField] = l0l0;
o01lO[_setTextField] = oOl0o;
o01lO[_getValueField] = o10O;
o01lO[_setValueField] = lOOlooField;
o01lO[_getUrl] = l01ol;
o01lO[_setUrl] = lo1011;
o01lO[_getData] = O00Ol;
o01lO[_setData] = olOo0o;
o01lO[_eval] = lO0lO;
o01lO[_load] = Oo1ll;
o01lO[_getAt] = o10Oo;
o01lO[_indexOf] = o0OO;
o01lO[_getItem] = lOl0;
o01lO[_select] = loO000;
o01lO[_showPopup] = l0ooO1;
o01lO[_createPopup] = OlOOO;
o01lO[_set] = o1ol0;
O1o0(oO1lOo, "combobox");
O0O0oO = function() {
    O0O0oO[_superclass][_constructor][_call](this);
    o01O(this.el, "mini-datepicker")
};
lol01(O0O0oO, ll01oO, {
    valueFormat: "",
    format: "yyyy-MM-dd",
    maxDate: null,
    minDate: null,
    popupWidth: "",
    viewDate: new Date(),
    showTime: false,
    timeFormat: "H:mm",
    showTodayButton: true,
    showClearButton: true,
    showOkButton: false,
    uiCls: "mini-datepicker"
});
o11OO = O0O0oO[_prototype];
o11OO[_getAttrs] = olOOl;
o11OO.l0Olo1 = l00l0;
o11OO.O0O1 = OooO1;
o11OO[_getMinDate] = O011o1;
o11OO[_setMinDate] = olol0;
o11OO[_getMaxDate] = llOo0;
o11OO[_setMaxDate] = O1o0l;
o11OO[_getShowOkButton] = Ol10O;
o11OO[_setShowOkButton] = l0Oo0;
o11OO[_getShowClearButton] = oo01o;
o11OO[_setShowClearButton] = o100l;
o11OO[_getShowTodayButton] = O11l1;
o11OO[_setShowTodayButton] = ol0oo;
o11OO[_getTimeFormat] = o0l1O;
o11OO[_setTimeFormat] = Ol0lo;
o11OO[_getShowTime] = ll1O1;
o11OO[_setShowTime] = l0o1l;
o11OO[_getViewDate] = O0Ol1;
o11OO[_setViewDate] = ol11O;
o11OO[_getFormValue] = oolll;
o11OO[_getValue] = O0O1oo;
o11OO[_setValue] = Oolol;
o11OO[_getValueFormat] = O0O1ooFormat;
o11OO[_setValueFormat] = OololFormat;
o11OO[_getFormat] = ooOll;
o11OO[_setFormat] = O0101;
o11OO.OlolO0 = o00l1;
o11OO.l1O01 = Ol00o;
o11OO.Oo0ol1 = O0lo0;
o11OO.O101 = O0ooo;
o11OO[_within] = lo0oO;
o11OO[_hidePopup] = o11o1;
o11OO[_showPopup] = OOooo;
o11OO[_createPopup] = l0O1o;
o11OO[_getCalendar] = olOl0;
O1o0(O0O0oO, "datepicker");
OollOO = function() {
    this.viewDate = new Date();
    this.lo11O = [];
    OollOO[_superclass][_constructor][_call](this)
};
lol01(OollOO, O11lO0, {
    width: 220,
    height: 160,
    _clearBorder: false,
    viewDate: null,
    ol0O: "",
    lo11O: [],
    multiSelect: false,
    firstDayOfWeek: 0,
    todayText: "Today",
    clearText: "Clear",
    okText: "OK",
    cancelText: "Cancel",
    daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
    format: "MMM,yyyy",
    timeFormat: "H:mm",
    showTime: false,
    currentTime: true,
    rows: 1,
    columns: 1,
    headerCls: "",
    bodyCls: "",
    footerCls: "",
    l11000: "mini-calendar-today",
    Oll01l: "mini-calendar-weekend",
    oOlOl: "mini-calendar-othermonth",
    O01O: "mini-calendar-selected",
    showHeader: true,
    showFooter: true,
    showWeekNumber: false,
    showDaysHeader: true,
    showMonthButtons: true,
    showYearButtons: true,
    showTodayButton: true,
    showClearButton: true,
    showOkButton: false,
    uiCls: "mini-calendar",
    menuEl: null,
    menuYear: null,
    menuSelectMonth: null,
    menuSelectYear: null
});
oO011 = OollOO[_prototype];
oO011[_getAttrs] = loo0O;
oO011.l000OO = o10lo;
oO011.lO1O1O = lo1ol;
oO011.OlolO0 = o0llo;
oO011.Oo00O = llOoO0;
oO011.O0O1l = ll1ll;
oO011.oool1 = lo110;
oO011.O11101 = ooOol;
oO011[_updateMenu] = ll1O0;
oO011[_hideMenu] = O100o;
oO011[_showMenu] = O101o;
oO011.o011o0 = lOoOo;
oO011.OO0lo = O110o;
oO011.o10OO1 = Oo01o;
oO011[_doUpdate] = O10lo;
oO011[_doLayout] = o01o1;
oO011[_getTimeFormat] = o110l;
oO011[_setTimeFormat] = OlloO;
oO011[_getShowTime] = OOlOO;
oO011[_setShowTime] = lOl0l;
oO011[_getColumns] = Ol01l;
oO011[_setColumns] = o0olo1;
oO011[_getRows] = O1O01;
oO011[_setRows] = loOOl;
oO011[_getMultiSelect] = l0Olol;
oO011[_setMultiSelect] = O1lll;
oO011[_isSelectedDate] = O0oo1;
oO011[_getFormValue] = OOOlO;
oO011[_getValue] = O10O0;
oO011[_setValue] = lOl1;
oO011[_getTime] = l01o1;
oO011[_setTime] = O0oo0;
oO011[_getSelectedDate] = l111O;
oO011[_setSelectedDates] = Oolo1;
oO011[_setSelectedDate] = l0011;
oO011[_getViewDate] = l1o00;
oO011[_setViewDate] = l1o1;
oO011[_getShowOkButton] = o1lo1;
oO011[_setShowOkButton] = ool10;
oO011[_getShowClearButton] = oO11;
oO011[_setShowClearButton] = OOOll;
oO011[_getShowTodayButton] = OO0l1;
oO011[_setShowTodayButton] = OoO1l;
oO011[_getShowYearButtons] = l1lol;
oO011[_setShowYearButtons] = OOl0l;
oO011[_getShowMonthButtons] = O11l0;
oO011[_setShowMonthButtons] = ololo;
oO011[_getShowDaysHeader] = lllll;
oO011[_setShowDaysHeader] = Oo1o0;
oO011[_getShowWeekNumber] = ollO0;
oO011[_setShowWeekNumber] = lO100;
oO011[_getShowFooter] = l1l1;
oO011[_setShowFooter] = Ol1OOo;
oO011[_getShowHeader] = lo1Oo;
oO011[_setShowHeader] = O1lOl;
oO011[_within] = l0O0o;
oO011[_getDateEl] = O1o0O;
oO011[_initEvents] = lO11l;
oO011[_destroy] = Ool10;
oO011[_focus] = l0O1;
oO011[_create] = Oll1o;
oO011[_getShortWeek] = lOoO1;
oO011[_getFirstDateOfMonth] = lo00l;
oO011[_isWeekend] = o00O01;
O1o0(OollOO, "calendar");
o0lo1O = function() {
    o0lo1O[_superclass][_constructor][_call](this)
};
lol01(o0lo1O, l0OlOo, {
    formField: true,
    width: 200,
    columns: null,
    columnWidth: 80,
    showNullItem: false,
    nullItemText: "",
    showEmpty: false,
    emptyText: "",
    showCheckBox: false,
    showAllCheckBox: true,
    multiSelect: false,
    o11o: "mini-listbox-item",
    looOl: "mini-listbox-item-hover",
    _looO: "mini-listbox-item-selected",
    uiCls: "mini-listbox"
});
OOl1O = o0lo1O[_prototype];
OOl1O[_getAttrs] = llOO1;
OOl1O.O0O1l = ooOl;
OOl1O.OO0o0 = OoO0l;
OOl1O.OollO = ol0o1o;
OOl1O.oO1Oo = Ooo0l;
OOl1O[_getNullItemText] = o0lO0;
OOl1O[_setNullItemText] = O1Ooll;
OOl1O[_getShowNullItem] = olo0;
OOl1O[_setShowNullItem] = ooool;
OOl1O[_getShowAllCheckBox] = l0ooO;
OOl1O[_setShowAllCheckBox] = lo1lo;
OOl1O[_getShowCheckBox] = lOOO;
OOl1O[_setShowCheckBox] = OO10O;
OOl1O[_doLayout] = llllo;
OOl1O[_doUpdate] = lo0O1;
OOl1O[_getColumns] = Olo10;
OOl1O[_setColumns] = l110l;
OOl1O[_destroy] = ll10;
OOl1O[_initEvents] = OlOoo0;
OOl1O[_create] = l100o;
O1o0(o0lo1O, "listbox");
o0O00l = function() {
    o0O00l[_superclass][_constructor][_call](this)
};
lol01(o0O00l, l0OlOo, {
    formField: true,
    multiSelect: true,
    repeatItems: 0,
    repeatLayout: "none",
    repeatDirection: "horizontal",
    o11o: "mini-checkboxlist-item",
    looOl: "mini-checkboxlist-item-hover",
    _looO: "mini-checkboxlist-item-selected",
    l1o101: "mini-checkboxlist-table",
    oO1O01: "mini-checkboxlist-td",
    o011o1: "checkbox",
    uiCls: "mini-checkboxlist"
});
oo0l1 = o0O00l[_prototype];
oo0l1[_getAttrs] = O0O1O;
oo0l1[_getRepeatDirection] = Ol0o1;
oo0l1[_setRepeatDirection] = olo01;
oo0l1[_getRepeatLayout] = o1oll;
oo0l1[_setRepeatLayout] = oolO0;
oo0l1[_getRepeatItems] = OO0o1;
oo0l1[_setRepeatItems] = Oo10o;
oo0l1.o100o = O0001;
oo0l1.llOoo0 = O1ol1;
oo0l1[_doUpdate] = Ol01O;
oo0l1.l1l10 = l0Ol0;
oo0l1[_create] = Oo1oo;
O1o0(o0O00l, "checkboxlist");
oolO10 = function() {
    oolO10[_superclass][_constructor][_call](this)
};
lol01(oolO10, o0O00l, {
    multiSelect: false,
    o11o: "mini-radiobuttonlist-item",
    looOl: "mini-radiobuttonlist-item-hover",
    _looO: "mini-radiobuttonlist-item-selected",
    l1o101: "mini-radiobuttonlist-table",
    oO1O01: "mini-radiobuttonlist-td",
    o011o1: "radio",
    uiCls: "mini-radiobuttonlist"
});
l0o00 = oolO10[_prototype];
O1o0(oolO10, "radiobuttonlist");
oOO000 = function() {
    this.data = [];
    oOO000[_superclass][_constructor][_call](this)
};
lol01(oOO000, ll01oO, {
    valueFromSelect: false,
    text: "",
    value: "",
    autoCheckParent: false,
    expandOnLoad: false,
    valueField: "id",
    textField: "text",
    nodesField: "children",
    dataField: "",
    delimiter: ",",
    multiSelect: false,
    data: [],
    url: "",
    allowInput: false,
    showTreeIcon: false,
    showTreeLines: true,
    resultAsTree: false,
    parentField: "pid",
    checkRecursive: false,
    showFolderCheckBox: false,
    popupHeight: 200,
    popupWidth: "100%",
    popupMaxHeight: 250,
    popupMinWidth: 100,
    uiCls: "mini-treeselect"
});
oll00 = oOO000[_prototype];
oll00[_getAttrs] = Oll10;
oll00[_setAjaxData] = lOO110;
oll00[_getValueFromSelect] = ol010;
oll00[_setValueFromSelect] = OlooO;
oll00[_getExpandOnLoad] = O1lO1;
oll00[_setExpandOnLoad] = oO1OO;
oll00[_getAutoCheckParent] = lo0l;
oll00[_setAutoCheckParent] = ol00O;
oll00[_getShowFolderCheckBox] = oo0l0;
oll00[_setShowFolderCheckBox] = o0O00;
oll00[_getShowTreeLines] = O0l0O;
oll00[_setShowTreeLines] = llll1;
oll00[_getShowTreeIcon] = l0oll;
oll00[_setShowTreeIcon] = lo0lO;
oll00[_getValueField] = Oo1O;
oll00[_setValueField] = Oo01;
oll00[_getParentField] = O11o1;
oll00[_setParentField] = oll0o;
oll00[_getResultAsTree] = Oo0l0;
oll00[_setResultAsTree] = oO000;
oll00[_getCheckRecursive] = Oo00l;
oll00[_setCheckRecursive] = OoOol;
oll00.lolo0 = loll1;
oll00.l0Olo1 = ool1o;
oll00.o0oll = llooO;
oll00.OO1oo = OOO0l;
oll00[_getMultiSelect] = o1Olo;
oll00[_setMultiSelect] = oo100;
oll00[_setValue] = llOloO;
oll00[_getDataField] = olo1;
oll00[_setDataField] = ooloO;
oll00[_getNodesField] = l1ol;
oll00[_setNodesField] = oOOOl;
oll00[_getTextField] = o1l1o;
oll00[_setTextField] = Oo0Oo;
oll00[_getUrl] = O10ol;
oll00[_setUrl] = O0o00;
oll00[_getData] = ol1l0;
oll00[_setData] = ooo0o;
oll00[_load] = OO1O0;
oll00[_getList] = OOll1;
oll00[_loadList] = OO1O0List;
oll00[_getAt] = oO0lO;
oll00[_indexOf] = Oool0;
oll00[_getItem] = o10oo;
oll00.ooo00 = o000O;
oll00[_showPopup] = o01lo;
oll00[_getChildNodes] = O0o1O;
oll00[_getParentNode] = lllO0;
oll00[_getSelectedNodes] = Ollll;
oll00[_getCheckedNodes] = llOlO;
oll00[_getSelectedNode] = lOoOll;
oll00.oool0 = O01o1;
oll00.oolOo = l100ol;
oll00.l0O0l = oO1lO;
oll00.oOol01 = l10O0;
oll00[_createPopup] = oo0o0;
oll00[_set] = oOoOo;
O1o0(oOO000, "TreeSelect");
lo11O1 = function() {
    lo11O1[_superclass][_constructor][_call](this);
    this[_setValue](this[_minValue])
};
lol01(lo11O1, Ol111o, {
    value: 0,
    minValue: 0,
    maxValue: 100,
    increment: 1,
    decimalPlaces: 0,
    changeOnMousewheel: true,
    allowLimitValue: true,
    uiCls: "mini-spinner",
    Oo10: null
});
olOll = lo11O1[_prototype];
olOll[_getAttrs] = olllo;
olOll.O0O1 = lo1O0;
olOll.o0lo = o0Olo;
olOll.oll0O = ooO1O;
olOll.l0Olo1 = OO1lO;
olOll.l11l0 = Ooooo;
olOll.Olll0 = Oo1l1;
olOll.lo1101 = l0Ool;
olOll[_getAllowLimitValue] = o0oo;
olOll[_setAllowLimitValue] = ool0l;
olOll[_getChangeOnMousewheel] = lll0l;
olOll[_setChangeOnMousewheel] = l0ll0;
olOll[_getDecimalPlaces] = OO01o;
olOll[_setDecimalPlaces] = OolO;
olOll[_getIncrement] = o0loo;
olOll[_setIncrement] = ll1OO;
olOll[_getMinValue] = l0lO1;
olOll[_setMinValue] = Ollol;
olOll[_getMaxValue] = o1lOo;
olOll[_setMaxValue] = Oll0O;
olOll[_setValue] = lO1oo;
olOll[_getFormValue] = l1o1o;
olOll.l0Ol01 = Oo101;
olOll[_initEvents] = oo1Oo;
olOll.ollOHtml = O0ol1;
olOll[_set] = ll0lO;
O1o0(lo11O1, "spinner");
OO1llo = function() {
    OO1llo[_superclass][_constructor][_call](this);
    this[_setValue]("00:00:00")
};
lol01(OO1llo, Ol111o, {
    value: null,
    format: "H:mm:ss",
    uiCls: "mini-timespinner",
    Oo10: null
});
lOO0o = OO1llo[_prototype];
lOO0o[_getAttrs] = OOllO;
lOO0o.O0O1 = o011O;
lOO0o.o0lo = OoO1O;
lOO0o.l11l0 = o1o1o;
lOO0o.Olll0 = o1O01;
lOO0o.lo1101 = o0O0l;
lOO0o.oo1ol = O0OOO;
lOO0o[_getFormattedValue] = OOo1O;
lOO0o[_getFormValue] = o1OO0;
lOO0o[_getValue] = lOOll;
lOO0o[_setValue] = olo1l;
lOO0o[_getFormat] = o0Oo1;
lOO0o[_setFormat] = oO10l;
lOO0o[_initEvents] = O1ol0;
lOO0o.ollOHtml = O1l10;
O1o0(OO1llo, "timespinner");
oo0o1o = function() {
    oo0o1o[_superclass][_constructor][_call](this);
    this[_on]("validation", this.o1oO, this)
};
lol01(oo0o1o, Ol111o, {
    width: 180,
    buttonText: "\u6d4f\u89c8...",
    _buttonWidth: 56,
    limitType: "",
    limitTypeErrorText: "\u4e0a\u4f20\u6587\u4ef6\u683c\u5f0f\u4e3a\uff1a",
    allowInput: false,
    readOnly: true,
    loOOo: 0,
    uiCls: "mini-htmlfile"
});
O001l = oo0o1o[_prototype];
O001l[_getAttrs] = O0oll;
O001l[_getLimitType] = oo0Oo;
O001l[_setLimitType] = O0lo1;
O001l[_getButtonText] = lO1ll;
O001l[_setButtonText] = O0ooO;
O001l[_getValue] = l01O1;
O001l[_setName] = l11o1;
O001l.o1oO = l1ooO;
O001l.l011 = l111l;
O001l.O11lo1 = O0111;
O001l.ollOHtml = OOo011;
O001l[_create] = OO100;
O1o0(oo0o1o, "htmlfile");
l1O00o = function($) {
    this.postParam = {};
    l1O00o[_superclass][_constructor][_call](this, $);
    this[_on]("validation", this.o1oO, this)
};
lol01(l1O00o, Ol111o, {
    width: 180,
    buttonText: "\u6d4f\u89c8...",
    _buttonWidth: 56,
    limitTypeErrorText: "\u4e0a\u4f20\u6587\u4ef6\u683c\u5f0f\u4e3a\uff1a",
    readOnly: true,
    loOOo: 0,
    limitSize: "",
    limitType: "",
    typesDescription: "\u4e0a\u4f20\u6587\u4ef6\u683c\u5f0f",
    uploadLimit: 0,
    queueLimit: "",
    flashUrl: "",
    uploadUrl: "",
    postParam: null,
    uploadOnSelect: false,
    uiCls: "mini-fileupload"
});
OolO1 = l1O00o[_prototype];
OolO1[_getAttrs] = lO1lo;
OolO1[__fileError] = Oo10O;
OolO1[__on_upload_complete] = olo11;
OolO1[__on_upload_error] = o100O;
OolO1[__on_upload_success] = loO0O;
OolO1[__on_file_queued] = OOllo;
OolO1[_startUpload] = l0l11;
OolO1[_setName] = ooO1o;
OolO1[_setUploadUrl] = lOl0o;
OolO1[_setFlashUrl] = lloOO;
OolO1[_setQueueLimit] = O1Ol0;
OolO1[_setUploadLimit] = o1o0O;
OolO1[_getButtonText] = Ol0O0;
OolO1[_setButtonText] = o0ll1;
OolO1[_getTypesDescription] = l0oOl;
OolO1[_setTypesDescription] = ooO10;
OolO1[_getLimitType] = olOO1;
OolO1[_setLimitType] = l0o11;
OolO1[_getPostParam] = Ol111;
OolO1[_setPostParam] = ll1Oll;
OolO1[_addPostParam] = Ol1OO;
OolO1.l011 = Ollo1;
OolO1[_destroy] = oO1l;
OolO1.ollOHtml = lll1l;
OolO1[_create] = ol1ol;
O1o0(l1O00o, "fileupload");
Ool0O1 = function() {
    this.data = [];
    Ool0O1[_superclass][_constructor][_call](this);
    lo1l1o(this.OllOo1, "mouseup", this.o110oo, this);
    this[_on]("showpopup", this.__OnShowPopup, this)
};
lol01(Ool0O1, ll01oO, {
    allowInput: true,
    valueField: "id",
    textField: "text",
    delimiter: ",",
    multiSelect: false,
    data: [],
    grid: null,
    _destroyPopup: false,
    uiCls: "mini-lookup"
});
OloOO = Ool0O1[_prototype];
OloOO[_getAttrs] = O0ll1;
OloOO.O0l10l = lOooo;
OloOO.o110oo = OO011o;
OloOO.l0Olo1 = ooo11;
OloOO[_doUpdate] = l1Oo0;
OloOO[__OnShowPopup] = O01l;
OloOO.ol1o1 = llO1l;
OloOO[__OnGridRowClickChanged] = l0lo0;
OloOO[_setText] = Ol1ll;
OloOO[_setValue] = oo1o1;
OloOO.OOOolO = o11Ol;
OloOO.l1o01O = ollOl;
OloOO.o00o0 = lOO1l;
OloOO[_getItemText] = loO1oo;
OloOO[_getItemValue] = ooOlo;
OloOO[_deselectAll] = oo0oO;
OloOO[_getTextField] = lO0oo;
OloOO[_setTextField] = Ol1llField;
OloOO[_getValueField] = lloO0l;
OloOO[_setValueField] = oo1o1Field;
OloOO[_getGrid] = O1O0;
OloOO[_setGrid] = loO1O;
OloOO[_setMultiSelect] = Oo1l1o;
OloOO[_destroy] = O1ll;
O1o0(Ool0O1, "lookup");
oo10oo = function() {
    oo10oo[_superclass][_constructor][_call](this);
    this.data = [];
    this[_doUpdate]()
};
lol01(oo10oo, o0l01o, {
    formField: true,
    value: "",
    text: "",
    valueField: "id",
    textField: "text",
    data: "",
    url: "",
    delay: 150,
    allowInput: true,
    editIndex: 0,
    l10oOl: "mini-textboxlist-focus",
    O0110: "mini-textboxlist-item-hover",
    OooOO: "mini-textboxlist-item-selected",
    loO0: "mini-textboxlist-close-hover",
    textName: "",
    uiCls: "mini-textboxlist",
    errorIconEl: null,
    ajaxDataType: "text",
    ajaxContentType: "application/x-www-form-urlencoded; charset=UTF-8",
    popupLoadingText: "<span class='mini-textboxlist-popup-loading'>Loading...</span>",
    popupErrorText: "<span class='mini-textboxlist-popup-error'>Error</span>",
    popupEmptyText: "<span class='mini-textboxlist-popup-noresult'>No Result</span>",
    isShowPopup: false,
    popupHeight: "",
    popupMinHeight: 30,
    popupMaxHeight: 150,
    searchField: "key"
});
o010O = oo10oo[_prototype];
o010O[_getAttrs] = O111o;
o010O[_getSearchField] = O1loO;
o010O[_setSearchField] = ll11O;
o010O[_blur] = o1ooO;
o010O[_focus] = l1O1o;
o010O.l0Olo1 = O1OOo;
o010O[__doSelectValue] = OOoOo;
o010O.lO1O1O = lloo1;
o010O.O0O1l = O1oll;
o010O.oll1OO = o0oo1;
o010O.l011 = o0000;
o010O[_hidePopup] = l00ll;
o010O[_showPopup] = o1O1l;
o010O[_createPopup] = oloOo;
o010O[_within] = OOll0;
o010O.o11Oo = oOOo1;
o010O.lolo0 = ooOOO;
o010O.oo1O = olOoo;
o010O.olll = oo0lO;
o010O[_doQuery] = l0O10;
o010O[_getPopupMaxHeight] = O1011;
o010O[_setPopupMaxHeight] = O001O;
o010O[_getPopupMinHeight] = O1o1o;
o010O[_setPopupMinHeight] = o1lO0;
o010O[_getPopupHeight] = OlO01;
o010O[_setPopupHeight] = Ol0ol;
o010O[_getUrl] = l0lo1;
o010O[_setUrl] = oollO;
o010O[_getAllowInput] = OOlo0;
o010O[_setAllowInput] = O1OlO;
o010O[_getTextField] = Ooo01;
o010O[_setTextField] = llo00;
o010O[_getValueField] = ol0Oo;
o010O[_setValueField] = O0ol0;
o010O[_setText] = o00OO;
o010O[_setValue] = l1oOo;
o010O[_setName] = OoolO;
o010O[_getValue] = o010l;
o010O[_getText] = o1oo0;
o010O[_getInputText] = oOo1O;
o010O.l1o01O = OlOl1;
o010O[_removeItem] = oOlOOl;
o010O[_insertItem] = o010o;
o010O.o1Ol = lloO0;
o010O[_select] = Olo1l;
o010O[_showInput] = Ol11o;
o010O[_blurItem] = o1ooOItem;
o010O[_hoverItem] = lolO1;
o010O[_getItemEl] = OO10;
o010O[_getItem] = lOoo1;
o010O.lllo0o = lOoo1ByEvent;
o010O[_doUpdate] = ool0o;
o010O[_doLayout] = o1ll1;
o010O.OOO11l = oooO1;
o010O[_getErrorIconEl] = l1Ol1;
o010O.ooOo = oOlOO;
o010O[_initEvents] = OlOOl;
o010O[_destroy] = OO111;
o010O[_create] = o10l0;
o010O[_getTextName] = o1oo0Name;
o010O[_setTextName] = o00OOName;
O1o0(oo10oo, "textboxlist");
olO1lo = function() {
    olO1lo[_superclass][_constructor][_call](this);
    var $ = this;
    $.oO1O = null;
    this.OllOo1.onfocus = function() {
        $.llO0 = $.OllOo1.value;
        $.oO1O = setInterval(function() {
            if ($.llO0 != $.OllOo1.value) {
                $.o0lO();
                $.llO0 = $.OllOo1.value;
                if ($.OllOo1.value == "" && $.value != "") {
                    $[_setValue]("");
                    $.l000OO()
                }
            }
        },
        10)
    };
    this.OllOo1.onblur = function() {
        clearInterval($.oO1O);
        if (!$[_isShowPopup]()) if ($.llO0 != $.OllOo1.value) if ($.OllOo1.value == "" && $.value != "") {
            $[_setValue]("");
            $.l000OO()
        }
    };
    this._buttonEl.style.display = "none";
    this[_doInputLayout]()
};
lol01(olO1lo, oO1lOo, {
    url: "",
    allowInput: true,
    delay: 150,
    searchField: "key",
    minChars: 0,
    _buttonWidth: 0,
    uiCls: "mini-autocomplete",
    popupLoadingText: "<span class='mini-textboxlist-popup-loading'>Loading...</span>",
    popupErrorText: "<span class='mini-textboxlist-popup-error'>Error</span>",
    popupEmptyText: "<span class='mini-textboxlist-popup-noresult'>No Result</span>"
});
oo0ol = olO1lo[_prototype];
oo0ol[_getAttrs] = o11ll;
oo0ol.lolo0 = O1o1l;
oo0ol.o0lO = lo1ll;
oo0ol[_doQuery] = llOOo;
oo0ol.l0Olo1 = l0lll;
oo0ol[_showPopup] = Ooo1l;
oo0ol[_getSearchField] = l1l0O;
oo0ol[_setSearchField] = O0o1o;
oo0ol[_getMinChars] = OOl01;
oo0ol[_setMinChars] = lO0ol;
oo0ol[_setText] = l0Oo1;
oo0ol[_setValue] = OOOo0;
oo0ol[_setUrl] = Ol1O1;
O1o0(olO1lo, "autocomplete");
mini.Form = function($) {
    this.el = OoO1o0($);
    if (!this.el) throw new Error("form element not null");
    mini.Form[_superclass][_constructor][_call](this)
};
lol01(mini.Form, o111oO, {
    el: null,
    getFields: function() {
        if (!this.el) return [];
        var $ = mini.findControls(function($) {
            if (!$.el || $.formField != true) return false;
            if (lolo(this.el, $.el)) return true;
            return false
        },
        this);
        return $
    },
    getFieldsMap: function() {
        var B = this.getFields(),
        A = {};
        for (var $ = 0,
        C = B.length; $ < C; $++) {
            var _ = B[$];
            if (_.name) A[_.name] = _
        }
        return A
    },
    getField: function($) {
        if (!this.el) return null;
        return mini[_getbyName]($, this.el)
    },
    getData: function(B, F) {
        if (mini.isNull(F)) F = true;
        var A = B ? "getFormValue": "getValue",
        $ = this.getFields(),
        D = {};
        for (var _ = 0,
        E = $.length; _ < E; _++) {
            var C = $[_],
            G = C[A];
            if (!G) continue;
            if (C.name) if (F == true) mini._setMap(C.name, G[_call](C), D);
            else D[C.name] = G[_call](C);
            if (C.textName && C[_getText]) if (F == true) D[C.textName] = C[_getText]();
            else mini._setMap(C.textName, C[_getText](), D)
        }
        return D
    },
    setData: function(F, A, C) {
        if (mini.isNull(C)) C = true;
        if (typeof F != "object") F = {};
        var B = this.getFieldsMap();
        for (var D in B) {
            var _ = B[D];
            if (!_) continue;
            if (_[_setValue]) {
                var E = F[D];
                if (C == true) E = mini._getMap(D, F);
                if (E === undefined && A === false) continue;
                if (E === null) E = "";
                _[_setValue](E)
            }
            if (_[_setText] && _.textName) {
                var $ = F[_.textName];
                if (C == true) $ = mini._getMap(_.textName, F);
                if (mini.isNull($)) $ = "";
                _[_setText]($)
            }
        }
    },
    reset: function() {
        var $ = this.getFields();
        for (var _ = 0,
        C = $.length; _ < C; _++) {
            var B = $[_];
            if (!B[_setValue]) continue;
            if (B[_setText] && B._clearText !== false) {
                var A = B.defaultText;
                if (mini.isNull(A)) A = "";
                B[_setText](A)
            }
            B[_setValue](B[_defaultValue])
        }
        this[_setIsValid](true)
    },
    clear: function() {
        var $ = this.getFields();
        for (var _ = 0,
        B = $.length; _ < B; _++) {
            var A = $[_];
            if (!A[_setValue]) continue;
            if (A[_setText] && A._clearText !== false) A[_setText]("");
            A[_setValue]("")
        }
        this[_setIsValid](true)
    },
    validate: function(C) {
        var $ = this.getFields();
        for (var _ = 0,
        D = $.length; _ < D; _++) {
            var A = $[_];
            if (!A[_validate]) continue;
            if (A[_isDisplay] && A[_isDisplay]()) {
                var B = A[_validate]();
                if (B == false && C === false) break
            }
        }
        return this[_isValid]()
    },
    setIsValid: function(B) {
        var $ = this.getFields();
        for (var _ = 0,
        C = $.length; _ < C; _++) {
            var A = $[_];
            if (!A[_setIsValid]) continue;
            A[_setIsValid](B)
        }
    },
    isValid: function() {
        var $ = this.getFields();
        for (var _ = 0,
        B = $.length; _ < B; _++) {
            var A = $[_];
            if (A[_isDisplay] && A[_isDisplay]()) {
                if (!A[_isValid]) continue;
                if (A[_isValid]() == false) return false
            }
        }
        return true
    },
    getErrorTexts: function() {
        var A = [],
        _ = this.getErrors();
        for (var $ = 0,
        C = _.length; $ < C; $++) {
            var B = _[$];
            A.push(B.errorText)
        }
        return A
    },
    getErrors: function() {
        var A = [],
        $ = this.getFields();
        for (var _ = 0,
        C = $.length; _ < C; _++) {
            var B = $[_];
            if (!B[_isValid]) continue;
            if (B[_isValid]() == false) A.push(B)
        }
        return A
    },
    mask: function($) {
        if (typeof $ == "string") $ = {
            html: $
        };
        $ = $ || {};
        $.el = this.el;
        if (!$.cls) $.cls = this.l110l1;
        mini[_mask]($)
    },
    unmask: function() {
        mini[_unmask](this.el)
    },
    l110l1: "mini-mask-loading",
    loadingMsg: "\u6570\u636e\u52a0\u8f7d\u4e2d\uff0c\u8bf7\u7a0d\u540e...",
    loading: function($) {
        this[_mask]($ || this.loadingMsg)
    },
    oll0: function($) {
        this._changed = true
    },
    _changed: false,
    setChanged: function(A) {
        this._changed = A;
        var $ = this.getFields();
        for (var _ = 0,
        C = $.length; _ < C; _++) {
            var B = $[_];
            B[_on]("valuechanged", this.oll0, this)
        }
    },
    isChanged: function() {
        return this._changed
    },
    setEnabled: function(A) {
        var $ = this.getFields();
        for (var _ = 0,
        C = $.length; _ < C; _++) {
            var B = $[_];
            B[_setEnabled](A)
        }
    }
});
OO11O1 = function() {
    OO11O1[_superclass][_constructor][_call](this)
};
lol01(OO11O1, mini.Container, {
    style: "",
    _clearBorder: false,
    uiCls: "mini-fit"
});
l10Oo = OO11O1[_prototype];
l10Oo[_getAttrs] = OOO0O;
l10Oo[_set_bodyParent] = o1OoO;
l10Oo[_doLayout] = O10l0;
l10Oo[_isFixedSize] = ll11l;
l10Oo[_initEvents] = llOoo;
l10Oo[_create] = oOll0;
O1o0(OO11O1, "fit");
ll00Oo = function() {
    this.loo11();
    ll00Oo[_superclass][_constructor][_call](this);
    if (this.url) this[_setUrl](this.url);
    this.Ooo00 = this.oOo1o0;
    this[_doVisibleEls]();
    this.OOOo = new OlOl0(this);
    this[_doTools]()
};
lol01(ll00Oo, mini.Container, {
    width: 250,
    title: "",
    iconCls: "",
    iconStyle: "",
    allowResize: false,
    url: "",
    refreshOnExpand: false,
    maskOnLoad: true,
    showCollapseButton: false,
    showCloseButton: false,
    closeAction: "display",
    showHeader: true,
    showToolbar: false,
    showFooter: false,
    headerCls: "",
    headerStyle: "",
    bodyCls: "",
    bodyStyle: "",
    footerCls: "",
    footerStyle: "",
    toolbarCls: "",
    toolbarStyle: "",
    minWidth: 180,
    minHeight: 100,
    maxWidth: 5000,
    maxHeight: 3000,
    uiCls: "mini-panel",
    _setBodyWidth: true,
    oOo0l: 80,
    expanded: true
});
oO01l = ll00Oo[_prototype];
oO01l[_getAttrs] = OOol;
oO01l[_expand] = l0lo;
oO01l[_collapse] = l01Ol;
oO01l[_toggle] = o000o;
oO01l[_setExpanded] = O1111l;
oO01l[_getAllowResize] = oO110;
oO01l[_setAllowResize] = l010o1;
oO01l[_getMaskOnLoad] = o11l0;
oO01l[_setMaskOnLoad] = l11lo;
oO01l[_getRefreshOnExpand] = OlOlo;
oO01l[_setRefreshOnExpand] = loloo;
oO01l[_getUrl] = O11oO;
oO01l[_setUrl] = l01l1;
oO01l[_reload] = llOOO;
oO01l[_load] = O00l0;
oO01l.OoO0l1 = O1101;
oO01l.OO1ol = Ooll;
oO01l.oooll0 = lllO;
oO01l[_getIFrameEl] = ol0l1o;
oO01l[_getFooterEl] = llO11;
oO01l[_getBodyEl] = O0llo;
oO01l[_getToolbarEl] = O0000;
oO01l[_getHeaderEl] = oo1l1;
oO01l[_setFooter] = l1ooo;
oO01l[_setToolbar] = o0oO;
oO01l[_set_bodyParent] = o0loO;
oO01l[_setBody] = loOo;
oO01l[_getButton] = O11l10;
oO01l[_removeButton] = looOo;
oO01l[_updateButton] = lO10l;
oO01l[_addButton] = OOOo1;
oO01l[_createButton] = l10oo0;
oO01l.loo11 = O0ll;
oO01l[_onButtonClick] = o0lOoo;
oO01l.O0ool = l1OO;
oO01l.O0O1l = olloOO;
oO01l[_getShowFooter] = l0o1;
oO01l[_setShowFooter] = lOo0l;
oO01l[_getShowToolbar] = OlllO;
oO01l[_setShowToolbar] = OlOO0;
oO01l[_getShowHeader] = O0lll;
oO01l[_setShowHeader] = o01oO;
oO01l[_getShowCollapseButton] = oOlo;
oO01l[_setShowCollapseButton] = oO111l;
oO01l[_getCloseAction] = o10o;
oO01l[_setCloseAction] = o1O101;
oO01l[_getShowCloseButton] = o0o0o;
oO01l[_setShowCloseButton] = oo110;
oO01l[_doTools] = llOl;
oO01l[_getIconCls] = l1Ol0;
oO01l[_setIconCls] = O1oO;
oO01l[_getTitle] = l0olo;
oO01l[_setTitle] = oo001o;
oO01l[_doTitle] = l0l1O;
oO01l[_getFooterCls] = llOlo;
oO01l[_setFooterCls] = l1oooCls;
oO01l[_getToolbarCls] = O0l10;
oO01l[_setToolbarCls] = o0oOCls;
oO01l[_getBodyCls] = OoO1OO;
oO01l[_setBodyCls] = loOoCls;
oO01l[_getHeaderCls] = l01lo;
oO01l[_setHeaderCls] = OO1O;
oO01l[_getFooterStyle] = lo1O;
oO01l[_setFooterStyle] = l1oooStyle;
oO01l[_getToolbarStyle] = o1O0l;
oO01l[_setToolbarStyle] = o0oOStyle;
oO01l[_getBodyStyle] = oOloO;
oO01l[_setBodyStyle] = loOoStyle;
oO01l[_getHeaderStyle] = oOlo0;
oO01l[_setHeaderStyle] = OOOl;
oO01l[_getFooterHeight] = l0l01;
oO01l[_getToolbarHeight] = l00o1;
oO01l[_getHeaderHeight] = lO0lo;
oO01l[_getBodyHeight] = OOo11;
oO01l[_getViewportHeight] = OoO0o1;
oO01l[_getViewportWidth] = O1l0o;
oO01l[_stopLayout] = lOll0;
oO01l[_deferLayout] = O0o0O;
oO01l[_doLayout] = oloo0;
oO01l[_doVisibleEls] = OlolO;
oO01l[_initEvents] = ollo0;
oO01l[_destroy] = OOl0O;
oO01l[_create] = l1OO1;
oO01l[_set] = oO00O0;
O1o0(ll00Oo, "panel");
oolo10 = function() {
    oolo10[_superclass][_constructor][_call](this);
    this[_addCls]("mini-window");
    this[_setVisible](false);
    this[_setAllowDrag](this.allowDrag);
    this[_setAllowResize](this[_allowResize])
};
lol01(oolo10, ll00Oo, {
    x: 0,
    y: 0,
    state: "restore",
    o0o1lo: "mini-window-drag",
    Oloo1: "mini-window-resize",
    allowDrag: true,
    showCloseButton: true,
    showMaxButton: false,
    showMinButton: false,
    showCollapseButton: false,
    showModal: true,
    minWidth: 150,
    minHeight: 80,
    maxWidth: 2000,
    maxHeight: 2000,
    uiCls: "mini-window",
    showInBody: true,
    containerEl: null
});
l0OlO = oolo10[_prototype];
l0OlO[_showAtEl] = lo100;
l0OlO[_getAttrs] = ol0Ol;
l0OlO[_destroy] = looll;
l0OlO.OOl0 = O0lOO;
l0OlO.ooo0l = Oo00O1;
l0OlO.O0ool = o0o1O;
l0OlO.Olol0 = o01ll;
l0OlO.Oool = O01oo;
l0OlO[_getBox] = O0O00;
l0OlO[_getWidth] = lO1ooo;
l0OlO[_hide] = lOOOl;
l0OlO[_show] = ooo10;
l0OlO[_showAtPos] = ooo10AtPos;
l0OlO[_getShowInBody] = O10OO;
l0OlO[_setShowInBody] = ollo;
l0OlO[_restore] = lolo01;
l0OlO[_max] = l1Ol;
l0OlO[_getShowMinButton] = lO00o1;
l0OlO[_setShowMinButton] = loOoo;
l0OlO[_getShowMaxButton] = oO0Oo;
l0OlO[_setShowMaxButton] = o1001;
l0OlO[_getAllowDrag] = lOO0l;
l0OlO[_setAllowDrag] = oOlol;
l0OlO[_getMaxHeight] = oOl10l;
l0OlO[_setMaxHeight] = OOo1Ol;
l0OlO[_getMaxWidth] = lol1l;
l0OlO[_setMaxWidth] = o01l1;
l0OlO[_getMinHeight] = O1l01;
l0OlO[_setMinHeight] = oO10O;
l0OlO[_getMinWidth] = l0lOo;
l0OlO[_setMinWidth] = l1Oo1l;
l0OlO[_getShowModal] = oloO0;
l0OlO[_setShowModal] = o10oO;
l0OlO[_getParentBox] = o00o;
l0OlO.Ollo1o = oOllo;
l0OlO[_doLayout] = O0oOl;
l0OlO[_initEvents] = llOl0;
l0OlO.loo11 = OO00O;
l0OlO[_create] = l01ll;
O1o0(oolo10, "window");
mini.MessageBox = {
    alertTitle: "\u63d0\u9192",
    confirmTitle: "\u786e\u8ba4",
    prompTitle: "\u8f93\u5165",
    prompMessage: "\u8bf7\u8f93\u5165\u5185\u5bb9\uff1a",
    buttonText: {
        ok: "\u786e\u5b9a",
        cancel: "\u53d6\u6d88",
        yes: "\u662f",
        no: "\u5426"
    },
    show: function(F) {
        F = mini.copyTo({
            width: "auto",
            height: "auto",
            showModal: true,
            minWidth: 150,
            maxWidth: 800,
            minHeight: 100,
            maxHeight: 350,
            showHeader: true,
            title: "",
            titleIcon: "",
            iconCls: "",
            iconStyle: "",
            message: "",
            html: "",
            spaceStyle: "margin-right:15px",
            showCloseButton: true,
            buttons: null,
            buttonWidth: 58,
            callback: null
        },
        F);
        var I = F.callback,
        C = new oolo10();
        C[_setBodyStyle]("overflow:hidden");
        C[_setShowModal](F[_showModal]);
        C[_setTitle](F.title || "");
        C[_setIconCls](F.titleIcon);
        C[_setShowHeader](F.showHeader);
        C[_setShowCloseButton](F[_showCloseButton]);
        var J = C.uid + "$table",
        O = C.uid + "$content",
        M = "<div class=\"" + F.iconCls + "\" style=\"" + F[_iconStyle] + "\"></div>",
        R = "<table class=\"mini-messagebox-table\" id=\"" + J + "\" style=\"\" cellspacing=\"0\" cellpadding=\"0\"><tr><td>" + M + "</td><td id=\"" + O + "\" class=\"mini-messagebox-content-text\">" + (F.message || "") + "</td></tr></table>",
        _ = "<div class=\"mini-messagebox-content\"></div>" + "<div class=\"mini-messagebox-buttons\"></div>";
        C.oOo1o0.innerHTML = _;
        var N = C.oOo1o0.firstChild;
        if (F.html) {
            if (typeof F.html == "string") N.innerHTML = F.html;
            else if (mini.isElement(F.html)) N.appendChild(F.html)
        } else N.innerHTML = R;
        C._Buttons = [];
        var Q = C.oOo1o0.lastChild;
        if (F.buttons && F.buttons.length > 0) {
            for (var H = 0,
            D = F.buttons.length; H < D; H++) {
                var E = F.buttons[H],
                K = mini.MessageBox.buttonText[E];
                if (!K) K = E;
                var $ = new OoOoOO();
                $[_setText](K);
                $[_setWidth](F.buttonWidth);
                $[_render](Q);
                $.action = E;
                $[_on]("click",
                function(_) {
                    var $ = _.sender;
                    if (I) I($.action);
                    mini.MessageBox[_hide](C)
                });
                if (H != D - 1) $[_setStyle](F.spaceStyle);
                C._Buttons.push($)
            }
        } else Q.style.display = "none";
        C[_setMinWidth](F.minWidth);
        C[_setMinHeight](F.minHeight);
        C[_setMaxWidth](F.maxWidth);
        C[_setMaxHeight](F.maxHeight);
        C[_setWidth](F.width);
        C[_setHeight](F.height);
        C[_show]();
        var A = C[_getWidth]();
        C[_setWidth](A);
        var L = C[_getHeight]();
        C[_setHeight](L);
        var B = document.getElementById(J);
        if (B) B.style.width = "100%";
        var G = document.getElementById(O);
        if (G) G.style.width = "100%";
        var P = C._Buttons[0];
        if (P) P[_focus]();
        else C[_focus]();
        C[_on]("beforebuttonclick",
        function($) {
            if (I) I("close");
            $.cancel = true;
            mini.MessageBox[_hide](C)
        });
        lo1l1o(C.el, "keydown",
        function($) {});
        return C.uid
    },
    hide: function(C) {
        if (!C) return;
        var _ = typeof C == "object" ? C: mini.getbyUID(C);
        if (!_) return;
        for (var $ = 0,
        A = _._Buttons.length; $ < A; $++) {
            var B = _._Buttons[$];
            B[_destroy]()
        }
        _._Buttons = null;
        _[_destroy]()
    },
    alert: function(A, _, $) {
        return mini.MessageBox[_show]({
            minWidth: 250,
            title: _ || mini.MessageBox.alertTitle,
            buttons: ["ok"],
            message: A,
            iconCls: "mini-messagebox-warning",
            callback: $
        })
    },
    confirm: function(A, _, $) {
        return mini.MessageBox[_show]({
            minWidth: 250,
            title: _ || mini.MessageBox.confirmTitle,
            buttons: ["ok", "cancel"],
            message: A,
            iconCls: "mini-messagebox-question",
            callback: $
        })
    },
    prompt: function(C, B, A, _) {
        var F = "prompt$" + new Date()[_getTime](),
        E = C || mini.MessageBox.promptMessage;
        if (_) E = E + "<br/><textarea id=\"" + F + "\" style=\"width:200px;height:60px;margin-top:3px;\"></textarea>";
        else E = E + "<br/><input id=\"" + F + "\" type=\"text\" style=\"width:200px;margin-top:3px;\"/>";
        var D = mini.MessageBox[_show]({
            title: B || mini.MessageBox.promptTitle,
            buttons: ["ok", "cancel"],
            width: 250,
            html: "<div style=\"padding:5px;padding-left:10px;\">" + E + "</div>",
            callback: function(_) {
                var $ = document.getElementById(F);
                if (A) A(_, $.value)
            }
        }),
        $ = document.getElementById(F);
        $[_focus]();
        return D
    },
    loading: function(_, $) {
        return mini.MessageBox[_show]({
            minHeight: 50,
            title: $,
            showCloseButton: false,
            message: _,
            iconCls: "mini-messagebox-waiting"
        })
    }
};
mini.alert = mini.MessageBox.alert;
mini.confirm = mini.MessageBox.confirm;
mini.prompt = mini.MessageBox.prompt;
mini[_loading] = mini.MessageBox[_loading];
mini.showMessageBox = mini.MessageBox[_show];
mini.hideMessageBox = mini.MessageBox[_hide];
oo1lo = function() {
    this.O10O1();
    oo1lo[_superclass][_constructor][_call](this)
};
lol01(oo1lo, O11lO0, {
    width: 300,
    height: 180,
    vertical: false,
    allowResize: true,
    pane1: null,
    pane2: null,
    showHandleButton: true,
    handlerStyle: "",
    handlerCls: "",
    handlerSize: 5,
    uiCls: "mini-splitter"
});
Oo01o1 = oo1lo[_prototype];
Oo01o1[_getAttrs] = OoO01;
Oo01o1.olO1 = O01l1;
Oo01o1.lO10O = l1010;
Oo01o1.OO1l = O0ll1l;
Oo01o1.ol01O = O0ol0l;
Oo01o1.Oo00O = l000l;
Oo01o1[_onButtonClick] = Ol0O1o;
Oo01o1.O0ool = l0000;
Oo01o1.O0O1l = lo11l;
Oo01o1[_getHandlerSize] = lOlO1;
Oo01o1[_setHandlerSize] = oOo1o;
Oo01o1[_getAllowResize] = l1Ooo;
Oo01o1[_setAllowResize] = o01O0;
Oo01o1[_hidePane] = olo1o;
Oo01o1[_showPane] = lOoOO;
Oo01o1[_togglePane] = llOoO;
Oo01o1[_collapsePane] = l1o0O;
Oo01o1[_expandPane] = o0lO1;
Oo01o1[_getVertical] = lOo10;
Oo01o1[_setVertical] = lO0O1;
Oo01o1[_getShowHandleButton] = OoO1o;
Oo01o1[_setShowHandleButton] = l11o;
Oo01o1[_updatePane] = oo1l0;
Oo01o1[_getPaneEl] = ol0O0;
Oo01o1[_setPaneControls] = Oo1l0l;
Oo01o1[_setPanes] = loOl1;
Oo01o1[_getPane] = olOo1;
Oo01o1[_getPaneBox] = olOo1Box;
Oo01o1[_doLayout] = o1llO;
Oo01o1[_doUpdate] = lo1oo;
Oo01o1.O10O1 = OO0oO;
Oo01o1[_initEvents] = Ol0O;
Oo01o1[_create] = lOo1O;
O1o0(oo1lo, "splitter");
oOoOO = function() {
    this.regions = [];
    this.regionMap = {};
    oOoOO[_superclass][_constructor][_call](this)
};
lol01(oOoOO, O11lO0, {
    regions: [],
    splitSize: 5,
    collapseWidth: 28,
    collapseHeight: 25,
    regionWidth: 150,
    regionHeight: 80,
    regionMinWidth: 50,
    regionMinHeight: 25,
    regionMaxWidth: 2000,
    regionMaxHeight: 2000,
    uiCls: "mini-layout",
    hoverProxyEl: null
});
Ooool = oOoOO[_prototype];
Ooool[_onButtonMouseDown] = O11ol;
Ooool[_onButtonClick] = o1o0o;
Ooool.oll1OO = ol01l;
Ooool.Oll1 = l1lOl;
Ooool.Ooo0 = Oo001;
Ooool.O0ool = Ololl;
Ooool.O0O1l = l110O;
Ooool.OlOl = oOlO1;
Ooool.o0o0 = o10ll;
Ooool.OoOO01 = lool;
Ooool[_isVisibleRegion] = lllO1;
Ooool[_isExpandRegion] = l1lo0;
Ooool[_hideRegion] = O11OlO;
Ooool[_showRegion] = ol0lo;
Ooool[_toggleRegion] = ololl;
Ooool[_collapseRegion] = OlOoo;
Ooool[_expandRegion] = l1l1o;
Ooool[_updateRegion] = Oo1O0;
Ooool.lOoloo = oO1o0;
Ooool[_moveRegion] = oOOlO;
Ooool[_removeRegion] = Oo1Ol;
Ooool[_addRegion] = OO1l1;
Ooool[_setRegions] = lo1lO;
Ooool[_setRegionControls] = OlOoO;
Ooool.ooo0lO = l1000;
Ooool.Oo1o = oll1o;
Ooool.ollO = l1olO;
Ooool[_getRegion] = O0O0l;
Ooool[_getRegionBox] = O0O0lBox;
Ooool[_getRegionProxyEl] = O0O0lProxyEl;
Ooool[_getRegionSplitEl] = O0O0lSplitEl;
Ooool[_getRegionBodyEl] = O0O0lBodyEl;
Ooool[_getRegionHeaderEl] = O0O0lHeaderEl;
Ooool[_getRegionEl] = O0O0lEl;
Ooool[_initEvents] = ooo1o;
Ooool[_create] = O111ol;
mini.copyTo(oOoOO.prototype, {
    o1oloO: function(_, A) {
        var C = "<div class=\"mini-tools\">";
        if (A) C += "<span class=\"mini-tools-collapse\"></span>";
        else for (var $ = _.buttons.length - 1; $ >= 0; $--) {
            var B = _.buttons[$];
            C += "<span class=\"" + B.cls + "\" style=\"";
            C += B.style + ";" + (B.visible ? "": "display:none;") + "\">" + B.html + "</span>"
        }
        C += "</div>";
        C += "<div class=\"mini-layout-region-icon " + _.iconCls + "\" style=\"" + _[_iconStyle] + ";" + ((_[_iconStyle] || _.iconCls) ? "": "display:none;") + "\"></div>";
        C += "<div class=\"mini-layout-region-title\">" + _.title + "</div>";
        return C
    },
    doUpdate: function() {
        for (var $ = 0,
        E = this.regions.length; $ < E; $++) {
            var B = this.regions[$],
            _ = B.region,
            A = B._el,
            D = B._split,
            C = B._proxy;
            if (B.cls) o01O(A, B.cls);
            B._header.style.display = B.showHeader ? "": "none";
            B._header.innerHTML = this.o1oloO(B);
            if (B._proxy) B._proxy.innerHTML = this.o1oloO(B, true);
            if (D) {
                o110(D, "mini-layout-split-nodrag");
                if (B.expanded == false || !B[_allowResize]) o01O(D, "mini-layout-split-nodrag")
            }
        }
        this[_doLayout]()
    },
    doLayout: function() {
        if (!this[_canLayout]()) return;
        if (this.OlOol) return;
        var C = O00O(this.el, true),
        _ = OlO1(this.el, true),
        D = {
            x: 0,
            y: 0,
            width: _,
            height: C
        },
        I = this.regions.clone(),
        P = this[_getRegion]("center");
        I.remove(P);
        if (P) I.push(P);
        for (var K = 0,
        H = I.length; K < H; K++) {
            var E = I[K];
            E._Expanded = false;
            o110(E._el, "mini-layout-popup");
            var A = E.region,
            L = E._el,
            F = E._split,
            G = E._proxy;
            if (E.visible == false) {
                L.style.display = "none";
                if (A != "center") F.style.display = G.style.display = "none";
                continue
            }
            L.style.display = "";
            if (A != "center") F.style.display = G.style.display = "";
            var R = D.x,
            O = D.y,
            _ = D.width,
            C = D.height,
            B = E.width,
            J = E.height;
            if (!E.expanded) if (A == "west" || A == "east") {
                B = E.collapseSize;
                l10l(L, E.width)
            } else if (A == "north" || A == "south") {
                J = E.collapseSize;
                l1101(L, E.height)
            }
            switch (A) {
            case "north":
                C = J;
                D.y += J;
                D.height -= J;
                break;
            case "south":
                C = J;
                O = D.y + D.height - J;
                D.height -= J;
                break;
            case "west":
                _ = B;
                D.x += B;
                D.width -= B;
                break;
            case "east":
                _ = B;
                R = D.x + D.width - B;
                D.width -= B;
                break;
            case "center":
                break;
            default:
                continue
            }
            if (_ < 0) _ = 0;
            if (C < 0) C = 0;
            if (A == "west" || A == "east") l1101(L, C);
            if (A == "north" || A == "south") l10l(L, _);
            var N = "left:" + R + "px;top:" + O + "px;",
            $ = L;
            if (!E.expanded) {
                $ = G;
                L.style.top = "-100px";
                L.style.left = "-1500px"
            } else if (G) {
                G.style.left = "-1500px";
                G.style.top = "-100px"
            }
            $.style.left = R + "px";
            $.style.top = O + "px";
            l10l($, _);
            l1101($, C);
            var M = jQuery(E._el).height(),
            Q = E.showHeader ? jQuery(E._header).outerHeight() : 0;
            l1101(E._body, M - Q);
            if (A == "center") continue;
            B = J = E.splitSize;
            R = D.x,
            O = D.y,
            _ = D.width,
            C = D.height;
            switch (A) {
            case "north":
                C = J;
                D.y += J;
                D.height -= J;
                break;
            case "south":
                C = J;
                O = D.y + D.height - J;
                D.height -= J;
                break;
            case "west":
                _ = B;
                D.x += B;
                D.width -= B;
                break;
            case "east":
                _ = B;
                R = D.x + D.width - B;
                D.width -= B;
                break;
            case "center":
                break
            }
            if (_ < 0) _ = 0;
            if (C < 0) C = 0;
            F.style.left = R + "px";
            F.style.top = O + "px";
            l10l(F, _);
            l1101(F, C);
            if (E.showSplit && E.expanded && E[_allowResize] == true) o110(F, "mini-layout-split-nodrag");
            else o01O(F, "mini-layout-split-nodrag");
            F.firstChild.style.display = E.showSplitIcon ? "block": "none";
            if (E.expanded) o110(F.firstChild, "mini-layout-spliticon-collapse");
            else o01O(F.firstChild, "mini-layout-spliticon-collapse")
        }
        mini.layout(this.lO0ll);
        this[_fire]("layout")
    },
    Oo00O: function(B) {
        if (this.OlOol) return;
        if (ooOO(B.target, "mini-layout-split")) {
            var A = jQuery(B.target).attr("uid");
            if (A != this.uid) return;
            var _ = this[_getRegion](B.target.id);
            if (_.expanded == false || !_[_allowResize] || !_.showSplit) return;
            this.dragRegion = _;
            var $ = this.ol01O();
            $.start(B)
        }
    },
    ol01O: function() {
        if (!this.drag) this.drag = new mini.Drag({
            capture: true,
            onStart: mini.createDelegate(this.OO1l, this),
            onMove: mini.createDelegate(this.lO10O, this),
            onStop: mini.createDelegate(this.olO1, this)
        });
        return this.drag
    },
    OO1l: function($) {
        this.oO01o = mini.append(document.body, "<div class=\"mini-resizer-mask\"></div>");
        this.oOO0 = mini.append(document.body, "<div class=\"mini-proxy\"></div>");
        this.oOO0.style.cursor = "n-resize";
        if (this.dragRegion.region == "west" || this.dragRegion.region == "east") this.oOO0.style.cursor = "w-resize";
        this.splitBox = O11O(this.dragRegion._split);
        oo1lo1(this.oOO0, this.splitBox);
        this.elBox = O11O(this.el, true)
    },
    lO10O: function(C) {
        var I = C.now[0] - C.init[0],
        V = this.splitBox.x + I,
        A = C.now[1] - C.init[1],
        U = this.splitBox.y + A,
        K = V + this.splitBox.width,
        T = U + this.splitBox.height,
        G = this[_getRegion]("west"),
        L = this[_getRegion]("east"),
        F = this[_getRegion]("north"),
        D = this[_getRegion]("south"),
        H = this[_getRegion]("center"),
        O = G && G.visible ? G.width: 0,
        Q = L && L.visible ? L.width: 0,
        R = F && F.visible ? F.height: 0,
        J = D && D.visible ? D.height: 0,
        P = G && G.showSplit ? OlO1(G._split) : 0,
        $ = L && L.showSplit ? OlO1(L._split) : 0,
        B = F && F.showSplit ? O00O(F._split) : 0,
        S = D && D.showSplit ? O00O(D._split) : 0,
        E = this.dragRegion,
        N = E.region;
        if (N == "west") {
            var M = this.elBox.width - Q - $ - P - H.minWidth;
            if (V - this.elBox.x > M) V = M + this.elBox.x;
            if (V - this.elBox.x < E.minWidth) V = E.minWidth + this.elBox.x;
            if (V - this.elBox.x > E.maxWidth) V = E.maxWidth + this.elBox.x;
            mini.setX(this.oOO0, V)
        } else if (N == "east") {
            M = this.elBox.width - O - P - $ - H.minWidth;
            if (this.elBox.right - (V + this.splitBox.width) > M) V = this.elBox.right - M - this.splitBox.width;
            if (this.elBox.right - (V + this.splitBox.width) < E.minWidth) V = this.elBox.right - E.minWidth - this.splitBox.width;
            if (this.elBox.right - (V + this.splitBox.width) > E.maxWidth) V = this.elBox.right - E.maxWidth - this.splitBox.width;
            mini.setX(this.oOO0, V)
        } else if (N == "north") {
            var _ = this.elBox.height - J - S - B - H.minHeight;
            if (U - this.elBox.y > _) U = _ + this.elBox.y;
            if (U - this.elBox.y < E.minHeight) U = E.minHeight + this.elBox.y;
            if (U - this.elBox.y > E.maxHeight) U = E.maxHeight + this.elBox.y;
            mini.setY(this.oOO0, U)
        } else if (N == "south") {
            _ = this.elBox.height - R - B - S - H.minHeight;
            if (this.elBox.bottom - (U + this.splitBox.height) > _) U = this.elBox.bottom - _ - this.splitBox.height;
            if (this.elBox.bottom - (U + this.splitBox.height) < E.minHeight) U = this.elBox.bottom - E.minHeight - this.splitBox.height;
            if (this.elBox.bottom - (U + this.splitBox.height) > E.maxHeight) U = this.elBox.bottom - E.maxHeight - this.splitBox.height;
            mini.setY(this.oOO0, U)
        }
    },
    olO1: function(B) {
        var C = O11O(this.oOO0),
        D = this.dragRegion,
        A = D.region;
        if (A == "west") {
            var $ = C.x - this.elBox.x;
            this[_updateRegion](D, {
                width: $
            })
        } else if (A == "east") {
            $ = this.elBox.right - C.right;
            this[_updateRegion](D, {
                width: $
            })
        } else if (A == "north") {
            var _ = C.y - this.elBox.y;
            this[_updateRegion](D, {
                height: _
            })
        } else if (A == "south") {
            _ = this.elBox.bottom - C.bottom;
            this[_updateRegion](D, {
                height: _
            })
        }
        jQuery(this.oOO0).remove();
        this.oOO0 = null;
        this.elBox = this.handlerBox = null;
        jQuery(this.oO01o).remove();
        this.oO01o = null
    },
    lO00O: function($) {
        $ = this[_getRegion]($);
        if ($._Expanded === true) this.ol101($);
        else this.ll010($)
    },
    ll010: function(D) {
        if (this.OlOol) return;
        this[_doLayout]();
        var A = D.region,
        H = D._el;
        D._Expanded = true;
        o01O(H, "mini-layout-popup");
        var E = O11O(D._proxy),
        B = O11O(D._el),
        F = {};
        if (A == "east") {
            var K = E.x,
            J = E.y,
            C = E.height;
            l1101(H, C);
            mini.setX(H, K);
            H.style.top = D._proxy.style.top;
            var I = parseInt(H.style.left);
            F = {
                left: I - B.width
            }
        } else if (A == "west") {
            K = E.right - B.width,
            J = E.y,
            C = E.height;
            l1101(H, C);
            mini.setX(H, K);
            H.style.top = D._proxy.style.top;
            I = parseInt(H.style.left);
            F = {
                left: I + B.width
            }
        } else if (A == "north") {
            var K = E.x,
            J = E.bottom - B.height,
            _ = E.width;
            l10l(H, _);
            mini[_setXY](H, K, J);
            var $ = parseInt(H.style.top);
            F = {
                top: $ + B.height
            }
        } else if (A == "south") {
            K = E.x,
            J = E.y,
            _ = E.width;
            l10l(H, _);
            mini[_setXY](H, K, J);
            $ = parseInt(H.style.top);
            F = {
                top: $ - B.height
            }
        }
        o01O(D._proxy, "mini-layout-maxZIndex");
        this.OlOol = true;
        var G = this,
        L = jQuery(H);
        L.animate(F, 250,
        function() {
            o110(D._proxy, "mini-layout-maxZIndex");
            G.OlOol = false
        })
    },
    ol101: function(F) {
        if (this.OlOol) return;
        F._Expanded = false;
        var B = F.region,
        E = F._el,
        D = O11O(E),
        _ = {};
        if (B == "east") {
            var C = parseInt(E.style.left);
            _ = {
                left: C + D.width
            }
        } else if (B == "west") {
            C = parseInt(E.style.left);
            _ = {
                left: C - D.width
            }
        } else if (B == "north") {
            var $ = parseInt(E.style.top);
            _ = {
                top: $ - D.height
            }
        } else if (B == "south") {
            $ = parseInt(E.style.top);
            _ = {
                top: $ + D.height
            }
        }
        o01O(F._proxy, "mini-layout-maxZIndex");
        this.OlOol = true;
        var A = this,
        G = jQuery(E);
        G.animate(_, 250,
        function() {
            o110(F._proxy, "mini-layout-maxZIndex");
            A.OlOol = false;
            A[_doLayout]()
        })
    },
    ooOo: function(B) {
        if (this.OlOol) return;
        for (var $ = 0,
        A = this.regions.length; $ < A; $++) {
            var _ = this.regions[$];
            if (!_._Expanded) continue;
            if (lolo(_._el, B.target) || lolo(_._proxy, B.target));
            else this.ol101(_)
        }
    },
    getAttrs: function(A) {
        var H = oOoOO[_superclass][_getAttrs][_call](this, A),
        G = jQuery(A),
        E = parseInt(G.attr("splitSize"));
        if (!isNaN(E)) H.splitSize = E;
        var F = [],
        D = mini[_getChildNodes](A);
        for (var _ = 0,
        C = D.length; _ < C; _++) {
            var B = D[_],
            $ = {};
            F.push($);
            $.cls = B.className;
            $.style = B.style.cssText;
            mini[_ParseString](B, $, ["region", "title", "iconCls", "iconStyle", "cls", "headerCls", "headerStyle", "bodyCls", "bodyStyle"]);
            mini[_ParseBool](B, $, ["allowResize", "visible", "showCloseButton", "showCollapseButton", "showSplit", "showHeader", "expanded", "showSplitIcon"]);
            mini[_ParseInt](B, $, ["splitSize", "collapseSize", "width", "height", "minWidth", "minHeight", "maxWidth", "maxHeight"]);
            $.bodyParent = B
        }
        H.regions = F;
        return H
    }
});
O1o0(oOoOO, "layout");
o11O1O = function() {
    o11O1O[_superclass][_constructor][_call](this)
};
lol01(o11O1O, mini.Container, {
    style: "",
    borderStyle: "",
    bodyStyle: "",
    uiCls: "mini-box"
});
OO11o = o11O1O[_prototype];
OO11o[_getAttrs] = oloOl1;
OO11o[_setBodyStyle] = o0ol0;
OO11o[_set_bodyParent] = llloo1;
OO11o[_setBody] = lo1o1;
OO11o[_doLayout] = l1Oo1;
OO11o[_initEvents] = oO11l;
OO11o[_create] = O0oO;
O1o0(o11O1O, "box");
lllooO = function() {
    lllooO[_superclass][_constructor][_call](this)
};
lol01(lllooO, O11lO0, {
    url: "",
    uiCls: "mini-include"
});
llo1o = lllooO[_prototype];
llo1o[_getAttrs] = Oo0O0;
llo1o[_getUrl] = o0l00;
llo1o[_setUrl] = oOOO1;
llo1o[_doLayout] = ooO1;
llo1o[_initEvents] = OloO1;
llo1o[_create] = ooOoO;
O1o0(lllooO, "include");
ll0l1o = function() {
    this.Ollo();
    ll0l1o[_superclass][_constructor][_call](this)
};
lol01(ll0l1o, O11lO0, {
    activeIndex: -1,
    tabAlign: "left",
    tabPosition: "top",
    showBody: true,
    nameField: "name",
    titleField: "title",
    urlField: "url",
    url: "",
    maskOnLoad: true,
    plain: true,
    bodyStyle: "",
    OOOlo: "mini-tab-hover",
    ooO1l: "mini-tab-active",
    uiCls: "mini-tabs",
    lOoOl: 1,
    oOo0l: 180,
    hoverTab: null
});
o1ool = ll0l1o[_prototype];
o1ool[_getAttrs] = o0o11;
o1ool[_onActiveChanged] = olO0O;
o1ool[_onCloseClick] = l1o1o0;
o1ool[_onBeforeCloseClick] = Ol1Ol;
o1ool.Oooo = lO0o0;
o1ool.O1Oo = l01oo;
o1ool.lOOl = oo0Oll;
o1ool.oO1O1 = l11OoO;
o1ool.llOl0o = olOl1;
o1ool.lll0 = oOOOO;
o1ool.Oo00O = olO1o;
o1ool.oll1OO = ll0o1O;
o1ool.Oll1 = ll11o1;
o1ool.O0O1l = llO01;
o1ool.O0lo1O = l11lOo;
o1ool[_getTabByEvent] = O1OO1;
o1ool[_getPlain] = O1OO;
o1ool[_setPlain] = o0l1;
o1ool[_getMaskOnLoad] = llo0l;
o1ool[_setMaskOnLoad] = O01o0;
o1ool[_getBodyStyle] = l00OO;
o1ool[_setBodyStyle] = l0O1lo;
o1ool[_getShowBody] = ol1lo;
o1ool[_setShowBody] = O01Oo;
o1ool.oool01 = oO1O11;
o1ool[_getActiveIndex] = loOo1;
o1ool[_getActiveTab] = lol0;
o1ool[_activeTab] = O0Oll;
o1ool[_getActiveIndex] = loOo1;
o1ool[_setActiveIndex] = OlO0l;
o1ool.oO0o = Oo100;
o1ool.lOOlO = Ol0OOO;
o1ool.OloOl = looo;
o1ool[_getTabIFrameEl] = OOo0l;
o1ool[_getTabBodyEl] = o1l1l;
o1ool[_getTabEl] = l10O;
o1ool[_getBodyEl] = OOO0lO;
o1ool[_getHeaderEl] = ll0o1;
o1ool[_getTab] = Ooloo;
o1ool[_setTabPosition] = loo1l;
o1ool[_setTabAlign] = lOol1;
o1ool[_doLayout] = ll1o;
o1ool[__handleIFrameOverflow] = O1oll1;
o1ool[_doUpdate] = O1111o;
o1ool[_getTabRows] = OolooRows;
o1ool[_reloadTab] = lOOO1;
o1ool[_loadTab] = lOO0O;
o1ool.O1loOO = O1O1l;
o1ool.lO01l = O0l10O;
o1ool[__cancelLoadTabs] = O0lO0;
o1ool.OO1ol = oO0Ol;
o1ool.oooll0 = oll0l0;
o1ool[_updateTab] = OO1O1;
o1ool[_moveTab] = llO00;
o1ool[_removeTab] = llllO;
o1ool[_addTab] = lO01l0;
o1ool[_removeAll] = o1OO;
o1ool[_getTabs] = Ooloos;
o1ool[_setTabs] = O1lO0;
o1ool[_setTabControls] = O1O1l1;
o1ool[_getUrlField] = O11Ol;
o1ool[_setUrlField] = oO00O;
o1ool[_getTitleField] = l0O01;
o1ool[_setTitleField] = ooO0l;
o1ool[_getNameField] = ll0lO0;
o1ool[_setNameField] = oo1oO;
o1ool[_getUrl] = oO010;
o1ool[_setUrl] = olool;
o1ool[_load] = lO1lOO;
o1ool.OoO0l1 = lll1;
o1ool[_createTab] = o1O1;
o1ool.Ollo = OOo0;
o1ool[_initEvents] = o00ol;
o1ool.ollll = l1ol1;
o1ool[_destroy] = ooo0O;
o1ool[_create] = ool11;
o1ool[_set] = O01O1l;
O1o0(ll0l1o, "tabs");
l0oOlO = function() {
    this.items = [];
    l0oOlO[_superclass][_constructor][_call](this)
};
lol01(l0oOlO, O11lO0);
mini.copyTo(l0oOlO.prototype, O1lo0o_prototype);
var O1lo0o_prototype_hide = O1lo0o_prototype[_hide];
mini.copyTo(l0oOlO.prototype, {
    height: "auto",
    width: "auto",
    minWidth: 140,
    vertical: true,
    allowSelectItem: false,
    loO1: null,
    _looO: "mini-menuitem-selected",
    textField: "text",
    resultAsTree: false,
    idField: "id",
    parentField: "pid",
    itemsField: "children",
    showNavArrow: true,
    _clearBorder: false,
    showAction: "none",
    hideAction: "outerclick",
    uiCls: "mini-menu",
    _disableContextMenu: false,
    _itemType: "menuitem",
    url: "",
    hideOnClick: true
});
oo1ll = l0oOlO[_prototype];
oo1ll[_getAttrs] = l00lO;
oo1ll[_parseItems] = ooO0;
oo1ll[_setToolbar] = olOl;
oo1ll[_startScrollMove] = oo001O;
oo1ll[__OnBottomMouseDown] = oo11Ol;
oo1ll[__OnTopMouseDown] = l1OO11;
oo1ll[_onItemSelect] = O011oO;
oo1ll[_onItemClick] = ooO01;
oo1ll[__OnItemSelect] = O0OO0;
oo1ll[_OnItemClick] = OOoO0;
oo1ll[_getHideOnClick] = o01O11;
oo1ll[_setHideOnClick] = ol10l;
oo1ll[_getUrl] = ol001;
oo1ll[_setUrl] = ol0ll;
oo1ll[_load] = l1oO1;
oo1ll[_loadList] = l1oO1List;
oo1ll.OoO0l1 = lol1lO;
oo1ll.Oool = oOl0;
oo1ll[_doLayout] = O10l;
oo1ll[_getParentField] = O1olo1;
oo1ll[_setParentField] = OO10o;
oo1ll[_getIdField] = o1l0O;
oo1ll[_setIdField] = OOO1O;
oo1ll[_getResultAsTree] = o1O11;
oo1ll[_setResultAsTree] = o0OOo;
oo1ll[_getTextField] = lO0O0;
oo1ll[_setTextField] = l1lOO;
oo1ll[_getShowNavArrow] = oo1Ol;
oo1ll[_setShowNavArrow] = l11111;
oo1ll[_getSelectedItem] = Oolo0;
oo1ll[_setSelectedItem] = loO11;
oo1ll[_getAllowSelectItem] = O11l0O;
oo1ll[_setAllowSelectItem] = OOl11;
oo1ll[_getItem] = olOO0o;
oo1ll[_getGroupItems] = o00Oo;
oo1ll[_removeAll] = oll10;
oo1ll[_removeItemAt] = oOOo0;
oo1ll[_removeItem] = l1oOO;
oo1ll[_addItem] = loO0l;
oo1ll[_getItems] = olOO0os;
oo1ll[_setItems] = Ol1oo;
oo1ll[_getData] = ooOOlO;
oo1ll[_setData] = O1ll1;
oo1ll[_hasShowItemMenu] = o1lO;
oo1ll[_showItemMenu] = oll1Ol;
oo1ll[_hideItems] = oOlloo;
oo1ll[_hide] = ol1oO;
oo1ll[_show] = oO0Ol0;
oo1ll[_isVertical] = o1o1;
oo1ll[_getVertical] = l1lO1;
oo1ll[_setVertical] = oloOl;
oo1ll[_within] = o0ll0;
oo1ll[_initEvents] = o0lo0;
oo1ll[_destroy] = oOl01;
oo1ll[_create] = oOlo0o;
oo1ll[_set] = o0l0;
oo1ll[_getbyName] = lo10O;
O1o0(l0oOlO, "menu");
l0oOlOBar = function() {
    l0oOlOBar[_superclass][_constructor][_call](this)
};
lol01(l0oOlOBar, l0oOlO, {
    uiCls: "mini-menubar",
    vertical: false,
    setVertical: function($) {
        this.vertical = false
    }
});
O1o0(l0oOlOBar, "menubar");
mini.ContextMenu = function() {
    mini.ContextMenu[_superclass][_constructor][_call](this)
};
lol01(mini.ContextMenu, l0oOlO, {
    uiCls: "mini-contextmenu",
    vertical: true,
    visible: false,
    _disableContextMenu: true,
    setVertical: function($) {
        this.vertical = true
    }
});
O1o0(mini.ContextMenu, "contextmenu");
l0ooo1 = function() {
    l0ooo1[_superclass][_constructor][_call](this)
};
lol01(l0ooo1, O11lO0, {
    text: "",
    iconCls: "",
    iconStyle: "",
    iconPosition: "left",
    showIcon: true,
    showAllow: true,
    checked: false,
    checkOnClick: false,
    groupName: "",
    _hoverCls: "mini-menuitem-hover",
    lOOol0: "mini-menuitem-pressed",
    o0OOl1: "mini-menuitem-checked",
    _clearBorder: false,
    menu: null,
    uiCls: "mini-menuitem",
    l1lo: false
});
O1Ol = l0ooo1[_prototype];
O1Ol[_getAttrs] = OOool;
O1Ol[_onCheckedChanged] = Ol11l;
O1Ol[_onClick] = O0O0O;
O1Ol.oll1OO = OOl1o;
O1Ol.Oll1 = o111l;
O1Ol.o110oo = o1O10;
O1Ol.O0O1l = oOO00;
O1Ol[_getTopMenu] = l1Oll;
O1Ol.olol1 = ooll0;
O1Ol[_hide] = l1110;
O1Ol[_hideMenu] = l1110Menu;
O1Ol[_showMenu] = ol00ol;
O1Ol[_getMenu] = O10Ol;
O1Ol[_setMenu] = O000O;
O1Ol[_setChildren] = l1OOl;
O1Ol[_getGroupName] = OoO10;
O1Ol[_setGroupName] = O1000;
O1Ol[_getChecked] = OOoOO;
O1Ol[_setChecked] = Ol011;
O1Ol[_getCheckOnClick] = lO101;
O1Ol[_setCheckOnClick] = lll1o;
O1Ol[_getIconPosition] = o1l1;
O1Ol[_setIconPosition] = oloO1;
O1Ol[_getIconStyle] = lOOlo;
O1Ol[_setIconStyle] = loOol;
O1Ol[_getIconCls] = lOlO0;
O1Ol[_setIconCls] = oOO01;
O1Ol[_getText] = o1Ol0;
O1Ol[_setText] = oOoo0;
O1Ol[_doUpdate] = lOOo0;
O1Ol[_hasChildMenu] = lOo0o;
O1Ol[_doUpdateIcon] = o1olOo;
O1Ol[_within] = olO0l;
O1Ol[_destroy] = o0o10;
O1Ol.O0O001 = OO1ll;
O1Ol[_initEvents] = O1l1o;
O1Ol[_create] = oO001;
O1o0(l0ooo1, "menuitem");
mini.Separator = function() {
    mini.Separator[_superclass][_constructor][_call](this)
};
lol01(mini.Separator, O11lO0, {
    _clearBorder: false,
    uiCls: "mini-separator",
    _create: function() {
        this.el = document.createElement("span");
        this.el.className = "mini-separator"
    }
});
O1o0(mini.Separator, "separator");
OlOOo = function() {
    this.oo1l();
    OlOOo[_superclass][_constructor][_call](this)
};
lol01(OlOOo, O11lO0, {
    width: 180,
    expandOnLoad: true,
    activeIndex: -1,
    autoCollapse: false,
    groupCls: "",
    groupStyle: "",
    groupHeaderCls: "",
    groupHeaderStyle: "",
    groupBodyCls: "",
    groupBodyStyle: "",
    groupHoverCls: "",
    groupActiveCls: "",
    allowAnim: true,
    uiCls: "mini-outlookbar",
    _GroupId: 1
});
ll1Ol = OlOOo[_prototype];
ll1Ol[_getAttrs] = ll0Ol;
ll1Ol[_parseGroups] = lOoll;
ll1Ol.O0O1l = lOO1o;
ll1Ol.l1lo00 = ol111;
ll1Ol.O10O = lOO10;
ll1Ol[_expandGroup] = lOo0O;
ll1Ol[_collapseGroup] = llo0O;
ll1Ol[_toggleGroup] = lOl00;
ll1Ol[_hideGroup] = O01ll;
ll1Ol[_showGroup] = o1OOll;
ll1Ol[_getActiveGroup] = oOooo;
ll1Ol[_getActiveIndex] = O1l11;
ll1Ol[_setActiveIndex] = oO1Ol;
ll1Ol[_getExpandOnLoad] = ol1l1;
ll1Ol[_setExpandOnLoad] = oo1o;
ll1Ol[_getAutoCollapse] = OOlOl;
ll1Ol[_setAutoCollapse] = oo0Ol;
ll1Ol[_getGroupBodyEl] = oOl10;
ll1Ol[_getGroupEl] = Ool1o;
ll1Ol.o1oo1l = oo010;
ll1Ol[_getGroup] = oOo1l;
ll1Ol.O11l = l10lo;
ll1Ol.ll00l1 = oOO11;
ll1Ol[_doLayout] = Oo0o0;
ll1Ol[_doUpdate] = o1111;
ll1Ol[_moveGroup] = OoO0O1;
ll1Ol[_removeAll] = OoO0O;
ll1Ol[_removeGroup] = l10l1;
ll1Ol[_updateGroup] = ol110;
ll1Ol[_addGroup] = loll0;
ll1Ol[_getGroups] = oOo1ls;
ll1Ol[_setGroups] = o1110;
ll1Ol[_createGroup] = lll10;
ll1Ol.oOo0OO = o1l01;
ll1Ol.oo1l = o011l;
ll1Ol.OlOO = lo0ll;
ll1Ol[_initEvents] = OO0o;
ll1Ol[_create] = ll100;
ll1Ol[_set] = ooOOl;
O1o0(OlOOo, "outlookbar");
o00lll = function() {
    o00lll[_superclass][_constructor][_call](this);
    this.data = []
};
lol01(o00lll, OlOOo, {
    url: "",
    textField: "text",
    iconField: "iconCls",
    urlField: "url",
    resultAsTree: false,
    itemsField: "children",
    idField: "id",
    parentField: "pid",
    style: "width:100%;height:100%;",
    uiCls: "mini-outlookmenu",
    Ooo0O: null,
    autoCollapse: true,
    activeIndex: 0
});
OOOOO = o00lll[_prototype];
OOOOO.OO1o1 = oOOoo;
OOOOO.loOl = Ol000;
OOOOO[_createNavBarMenu] = llOo1;
OOOOO[_getAttrs] = lO1lO;
OOOOO[_getOwnerMenu] = l0101;
OOOOO[_getList] = OO110;
OOOOO[_getNode] = llool;
OOOOO[_findNodes] = OOol0;
OOOOO[_selectNode] = ol1O1;
OOOOO[_getSelected] = O0oO1;
OOOOO[_getParentField] = oO0OO;
OOOOO[_setParentField] = O0Oo0;
OOOOO[_getIdField] = l10ll;
OOOOO[_setIdField] = o001l;
OOOOO[_getNodesField] = lloolsField;
OOOOO[_setNodesField] = o1101;
OOOOO[_getResultAsTree] = OolOo;
OOOOO[_setResultAsTree] = Olo00;
OOOOO[_getUrlField] = OoOl1;
OOOOO[_setUrlField] = oo11o;
OOOOO[_getIconField] = o11ol;
OOOOO[_setIconField] = OoOo0;
OOOOO[_getTextField] = l1ll1;
OOOOO[_setTextField] = o101O;
OOOOO[_getUrl] = O11O0;
OOOOO[_setUrl] = l01lO;
OOOOO[_setData] = OlO1o;
OOOOO[_load] = ol10O;
OOOOO[_loadList] = ol10OList;
OOOOO.OoO0l1 = OOOoo;
OOOOO.O0ool1Fields = O0Oo1;
OOOOO[_destroy] = loooO;
OOOOO[_set] = o10OO;
O1o0(o00lll, "outlookmenu");
Ol0O0o = function() {
    Ol0O0o[_superclass][_constructor][_call](this);
    this.data = []
};
lol01(Ol0O0o, OlOOo, {
    url: "",
    textField: "text",
    iconField: "iconCls",
    urlField: "url",
    resultAsTree: false,
    nodesField: "children",
    idField: "id",
    parentField: "pid",
    style: "width:100%;height:100%;",
    uiCls: "mini-outlooktree",
    Ooo0O: null,
    expandOnLoad: false,
    autoCollapse: true,
    activeIndex: 0
});
ol1Ol = Ol0O0o[_prototype];
ol1Ol.ooo0 = oO101;
ol1Ol.OO1oo = OlO11;
ol1Ol[__OnNodeMouseDown] = l1100;
ol1Ol[_createNavBarTree] = ll0O0;
ol1Ol[_getAttrs] = Ool1l;
ol1Ol[_getExpandOnLoad] = oO100;
ol1Ol[_setExpandOnLoad] = ooo1O;
ol1Ol[_getOwnerTree] = oooOl;
ol1Ol[_getList] = lOool;
ol1Ol[_getNode] = oO10o;
ol1Ol[_findNodes] = O11OO;
ol1Ol[_expandPath] = lo01o;
ol1Ol[_selectNode] = l1olo;
ol1Ol[_getSelected] = oOOl1;
ol1Ol[_getParentField] = O1o00;
ol1Ol[_setParentField] = l0100;
ol1Ol[_getIdField] = Ooo0o;
ol1Ol[_setIdField] = l1001;
ol1Ol[_getNodesField] = oO10osField;
ol1Ol[_setNodesField] = l11oo;
ol1Ol[_getResultAsTree] = lOOl1;
ol1Ol[_setResultAsTree] = OoOlO;
ol1Ol[_getUrlField] = lOOO0;
ol1Ol[_setUrlField] = Oo1o1;
ol1Ol[_getIconField] = o0OO0;
ol1Ol[_setIconField] = lo0o0;
ol1Ol[_getTextField] = Ool11;
ol1Ol[_setTextField] = l0Ooo;
ol1Ol[_getUrl] = Oo01l;
ol1Ol[_setUrl] = l11Ol;
ol1Ol[_getData] = ooO00;
ol1Ol[_setData] = l001O;
ol1Ol[_load] = l01l0;
ol1Ol[_loadList] = l01l0List;
ol1Ol.OoO0l1 = l01oO;
ol1Ol.O0ool1Fields = oll01;
ol1Ol[_destroy] = o01Oo;
ol1Ol[_set] = OOoo0;
O1o0(Ol0O0o, "outlooktree");
mini.NavBar = function() {
    mini.NavBar[_superclass][_constructor][_call](this)
};
lol01(mini.NavBar, OlOOo, {
    uiCls: "mini-navbar"
});
O1o0(mini.NavBar, "navbar");
mini.NavBarMenu = function() {
    mini.NavBarMenu[_superclass][_constructor][_call](this)
};
lol01(mini.NavBarMenu, o00lll, {
    uiCls: "mini-navbarmenu"
});
O1o0(mini.NavBarMenu, "navbarmenu");
mini.NavBarTree = function() {
    mini.NavBarTree[_superclass][_constructor][_call](this)
};
lol01(mini.NavBarTree, Ol0O0o, {
    uiCls: "mini-navbartree"
});
O1o0(mini.NavBarTree, "navbartree");
mini.ToolBar = function() {
    mini.ToolBar[_superclass][_constructor][_call](this)
};
lol01(mini.ToolBar, mini.Container, {
    _clearBorder: false,
    style: "",
    uiCls: "mini-toolbar",
    _create: function() {
        this.el = document.createElement("div");
        this.el.className = "mini-toolbar"
    },
    _initEvents: function() {},
    doLayout: function() {
        if (!this[_canLayout]()) return;
        var A = mini[_getChildNodes](this.el, true);
        for (var $ = 0,
        _ = A.length; $ < _; $++) mini.layout(A[$])
    },
    set_bodyParent: function($) {
        if (!$) return;
        this.el = $;
        this[_doLayout]()
    },
    getAttrs: function($) {
        var _ = {};
        mini[_ParseString]($, _, ["id", "borderStyle"]);
        this.el = $;
        this.el.uid = this.uid;
        this[_addCls](this.uiCls);
        return _
    }
});
O1o0(mini.ToolBar, "toolbar");
loooo = function() {
    loooo[_superclass][_constructor][_call](this)
};
lol01(loooo, O11lO0, {
    pageIndex: 0,
    pageSize: 10,
    totalCount: 0,
    totalPage: 0,
    showPageIndex: true,
    showPageSize: true,
    showTotalCount: true,
    showPageInfo: true,
    showReloadButton: true,
    _clearBorder: false,
    showButtonText: false,
    showButtonIcon: true,
    firstText: "\u9996\u9875",
    prevText: "\u4e0a\u4e00\u9875",
    nextText: "\u4e0b\u4e00\u9875",
    lastText: "\u5c3e\u9875",
    pageInfoText: "\u6bcf\u9875 {0} \u6761,\u5171 {1} \u6761",
    sizeList: [10, 20, 50, 100],
    uiCls: "mini-pager"
});
OOo1l = loooo[_prototype];
OOo1l[_getAttrs] = lolol;
OOo1l[_onPageChanged] = O000o;
OOo1l.llo0 = o0O0o;
OOo1l.l0Oo = lOll1;
OOo1l[_update] = lOOo;
OOo1l[_getTotalPage] = oOO1o;
OOo1l[_getShowReloadButton] = Oloo01;
OOo1l[_setShowReloadButton] = o000l;
OOo1l[_getShowPageInfo] = O0lO1l;
OOo1l[_setShowPageInfo] = lo111;
OOo1l[_getShowTotalCount] = lO0Ol;
OOo1l[_setShowTotalCount] = OO10o0;
OOo1l[_getShowPageIndex] = O0l01;
OOo1l[_setShowPageIndex] = ol01;
OOo1l[_getShowPageSize] = loOo10;
OOo1l[_setShowPageSize] = OOOl0;
OOo1l[_getSizeList] = Oo111;
OOo1l[_setSizeList] = o1ooo;
OOo1l[_getTotalCount] = ollo1;
OOo1l[_setTotalCount] = loOlo;
OOo1l[_getPageSize] = O110l;
OOo1l[_setPageSize] = l10lOo;
OOo1l[_getPageIndex] = o1l0l1;
OOo1l[_setPageIndex] = Olll;
OOo1l[_doLayout] = oO00;
OOo1l[_initEvents] = o1Oll;
OOo1l[_destroy] = lll01;
OOo1l[_create] = l1oo1l;
O1o0(loooo, "pager");
O0o0l1 = function() {
    this._bindFields = [];
    this._bindForms = [];
    O0o0l1[_superclass][_constructor][_call](this)
};
lol01(O0o0l1, o111oO, {});
OlOo1 = O0o0l1[_prototype];
OlOo1.oll0 = O01O0;
OlOo1.llOO = o01o0;
OlOo1[_bindForm] = o0O1o;
OlOo1[_bindField] = l0lO0;
O1o0(O0o0l1, "databinding");
oOO0o1 = function() {
    this._sources = {};
    this._data = {};
    this._links = [];
    this.lollO = {};
    oOO0o1[_superclass][_constructor][_call](this)
};
lol01(oOO0o1, o111oO, {});
ll10O = oOO0o1[_prototype];
ll10O.O010 = Ooolo;
ll10O.OOlo = O1O1o;
ll10O.ol1o = oOol0;
ll10O.o00O1 = o01O1;
ll10O.Oll10O = O0l1o;
ll10O.lO1o1 = lO110;
ll10O.lOoo0 = O0OOOO;
ll10O[_getData] = l000O;
ll10O[_clearData] = OOOol;
ll10O[_addLink] = o1llo;
ll10O[_add] = OOO0o;
O1o0(oOO0o1, "dataset");
mini.DataSource = function() {
    mini.DataSource[_superclass][_constructor][_call](this);
    this._init()
};
lol01(mini.DataSource, o111oO, {
    idField: "id",
    textField: "text",
    o0Oo: "_id",
    lO01: true,
    _autoCreateNewID: false,
    _init: function() {
        this.source = [];
        this.dataview = [];
        this.visibleRows = null;
        this._ids = {};
        this._removeds = [];
        if (this.lO01) this.lollO = {};
        this._errors = {};
        this.Ooo0O = null;
        this.ololO0 = [];
        this.OO00Ol = {};
        this.__changeCount = 0
    },
    getSource: function() {
        return this.source.clone()
    },
    getList: function() {
        return this.source.clone()
    },
    getDataView: function() {
        return this.dataview
    },
    getVisibleRows: function() {
        if (!this.visibleRows) this.visibleRows = this.getDataView().clone();
        return this.visibleRows
    },
    setData: function($) {
        this[_loadData]($)
    },
    loadData: function($) {
        if (!mini.isArray($)) $ = [];
        this._init();
        this.Oo0O($);
        this.Oll00O();
        this[_fire]("loaddata");
        return true
    },
    Oo0O: function(C) {
        this.source = C;
        this.dataview = C;
        var A = this.source,
        B = this._ids;
        for (var _ = 0,
        D = A.length; _ < D; _++) {
            var $ = A[_];
            $._id = mini.DataSource.RecordId++;
            B[$._id] = $;
            $._uid = $._id
        }
    },
    clearData: function() {
        this._init();
        this.Oll00O();
        this[_fire]("cleardata")
    },
    clear: function() {
        this[_clearData]()
    },
    updateRecord: function($, B, _) {
        if (mini.isNull($)) return;
        this[_fire]("beforeupdate", {
            record: $
        });
        if (typeof B == "string") {
            var C = $[B];
            if (mini[_isEquals](C, _)) return false;
            this.beginChange();
            $[B] = _;
            this._setModified($, B, C);
            this.endChange()
        } else {
            this.beginChange();
            for (var A in B) {
                var C = $[A],
                _ = B[A];
                if (mini[_isEquals](C, _)) continue;
                $[A] = _;
                this._setModified($, A, C)
            }
            this.endChange()
        }
        this[_fire]("update", {
            record: $
        })
    },
    deleteRecord: function($) {
        this._setDeleted($);
        this.Oll00O();
        this[_fire]("delete", {
            record: $
        })
    },
    getby_id: function($) {
        $ = typeof $ == "object" ? $._id: $;
        return this._ids[$]
    },
    getbyId: function(D) {
        var B = typeof D;
        if (B == "number") return this[_getAt](D);
        if (typeof D == "object") {
            if (this.getby_id(D)) return D;
            D = D[this.idField]
        }
        var A = this[_getList]();
        for (var _ = 0,
        C = A.length; _ < C; _++) {
            var $ = A[_];
            if ($[this.idField] == D) return $
        }
        return null
    },
    getsByIds: function(_) {
        if (mini.isNull(_)) _ = "";
        _ = String(_);
        var D = [],
        A = String(_).split(",");
        for (var $ = 0,
        C = A.length; $ < C; $++) {
            var B = this.getbyId(A[$]);
            if (B) D.push(B)
        }
        return D
    },
    getRecord: function($) {
        return this[_getRow]($)
    },
    getRow: function($) {
        var _ = typeof $;
        if (_ == "string") return this.getbyId($);
        else if (_ == "number") return this[_getAt]($);
        else if (_ == "object") return $
    },
    delimiter: ",",
    o00o0: function(B, $) {
        if (mini.isNull(B)) B = [];
        $ = $ || this.delimiter;
        if (typeof B == "string") B = this.getsByIds(B);
        else if (!mini.isArray(B)) B = [B];
        var C = [],
        D = [];
        for (var A = 0,
        E = B.length; A < E; A++) {
            var _ = B[A];
            if (_) {
                C.push(this[_getItemValue](_));
                D.push(this[_getItemText](_))
            }
        }
        return [C.join($), D.join($)]
    },
    getItemValue: function($) {
        if (!$) return "";
        var _ = mini._getMap(this.idField, $);
        return mini.isNull(_) ? "": String(_)
    },
    getItemText: function($) {
        if (!$) return "";
        var _ = mini._getMap(this.textField, $);
        return mini.isNull(_) ? "": String(_)
    },
    isModified: function(A, _) {
        var $ = this.lollO[A[this.o0Oo]];
        if (!$) return false;
        if (mini.isNull(_)) return false;
        return $.hasOwnProperty(_)
    },
    hasRecord: function($) {
        return !! this.getby_id($)
    },
    findRecords: function(D, A) {
        var F = typeof D == "function",
        I = D,
        E = A || this,
        C = this.source,
        B = [];
        for (var _ = 0,
        H = C.length; _ < H; _++) {
            var $ = C[_];
            if (F) {
                var G = I[_call](E, $);
                if (G == true) B[B.length] = $;
                if (G === 1) break
            } else if ($[D] == A) B[B.length] = $
        }
        return B
    },
    findRecord: function(A, $) {
        var _ = this.findRecords(A, $);
        return _[0]
    },
    each: function(A, _) {
        var $ = this.getDataView().clone();
        _ = _ || this;
        mini.forEach($, A, _)
    },
    getCount: function() {
        return this.getDataView().length
    },
    setIdField: function($) {
        this[_idField] = $
    },
    setTextField: function($) {
        this[_textField] = $
    },
    __changeCount: 0,
    beginChange: function() {
        this.__changeCount++
    },
    endChange: function($) {
        this.__changeCount--;
        if (this.__changeCount < 0) this.__changeCount = 0;
        if (($ !== false && this.__changeCount == 0) || $ == true) {
            this.__changeCount = 0;
            this.Oll00O()
        }
    },
    Oll00O: function() {
        this.visibleRows = null;
        if (this.__changeCount == 0) this[_fire]("datachanged")
    },
    _setAdded: function($) {
        $._id = mini.DataSource.RecordId++;
        if (this._autoCreateNewID && !$[this.idField]) $[this.idField] = UUID();
        $._uid = $._id;
        $._state = "added";
        this._ids[$._id] = $;
        delete this.lollO[$[this.o0Oo]]
    },
    _setModified: function($, A, B) {
        if ($._state != "added" && $._state != "deleted" && $._state != "removed") {
            $._state = "modified";
            var _ = this.lo1o0($);
            if (!_.hasOwnProperty(A)) _[A] = B
        }
    },
    _setDeleted: function($) {
        if ($._state != "added" && $._state != "deleted" && $._state != "removed") $._state = "deleted"
    },
    _setRemoved: function($) {
        delete this._ids[$._id];
        if ($._state != "added" && $._state != "removed") {
            $._state = "removed";
            delete this.lollO[$[this.o0Oo]];
            this._removeds.push($)
        }
    },
    lo1o0: function($) {
        var A = $[this.o0Oo],
        _ = this.lollO[A];
        if (!_) _ = this.lollO[A] = {};
        return _
    },
    Ooo0O: null,
    ololO0: [],
    OO00Ol: null,
    multiSelect: false,
    isSelected: function($) {
        if (!$) return false;
        if (typeof $ != "string") $ = $._id;
        return !! this.OO00Ol[$]
    },
    setSelected: function($) {
        $ = this.getby_id($);
        var _ = this[_getSelected]();
        if (_ != $) {
            this.Ooo0O = $;
            if ($) this[_select]($);
            else this[_deselect](this[_getSelected]());
            this.O00o($)
        }
    },
    getSelected: function() {
        if (this[_isSelected](this.Ooo0O)) return this.Ooo0O;
        return this.ololO0[0]
    },
    setCurrent: function($) {
        this[_setSelected]($)
    },
    getCurrent: function() {
        return this[_getSelected]()
    },
    getSelecteds: function() {
        return this.ololO0.clone()
    },
    select: function($) {
        if (mini.isNull($)) return;
        this[_selects]([$])
    },
    deselect: function($) {
        if (mini.isNull($)) return;
        this[_deselects]([$])
    },
    selectAll: function() {
        this[_selects](this[_getList]())
    },
    deselectAll: function() {
        this[_deselects](this[_getList]())
    },
    selects: function(A) {
        if (!mini.isArray(A)) return;
        A = A.clone();
        if (this[_multiSelect] == false) {
            this[_deselects](this[_getSelecteds]());
            if (A.length > 0) A.length = 1;
            this.ololO0 = [];
            this.OO00Ol = {}
        }
        var B = [];
        for (var _ = 0,
        C = A.length; _ < C; _++) {
            var $ = this.getbyId(A[_]);
            if (!$) continue;
            if (!this[_isSelected]($)) {
                this.ololO0.push($);
                this.OO00Ol[$._id] = $;
                B.push($)
            }
        }
        this.OO0l(A, true, B)
    },
    deselects: function(A) {
        if (!mini.isArray(A)) return;
        A = A.clone();
        var B = [];
        for (var _ = A.length - 1; _ >= 0; _--) {
            var $ = this.getbyId(A[_]);
            if (!$) continue;
            if (this[_isSelected]($)) {
                this.ololO0.remove($);
                delete this.OO00Ol[$._id];
                B.push($)
            }
        }
        this.OO0l(A, false, B)
    },
    OO0l: function(A, D, B) {
        var C = {
            records: A,
            select: D,
            selected: this[_getSelected](),
            selecteds: this[_getSelecteds](),
            _records: B
        };
        this[_fire]("SelectionChanged", C);
        var _ = this._current,
        $ = this.getCurrent();
        if (_ != $) {
            this._current = $;
            this.O00o($)
        }
    },
    O00o: function($) {
        if (this._currentTimer) clearTimeout(this._currentTimer);
        var _ = this;
        this._currentTimer = setTimeout(function() {
            _._currentTimer = null;
            var A = {
                record: $
            };
            _[_fire]("CurrentChanged", A)
        },
        1)
    },
    loO10: function() {
        for (var _ = this.ololO0.length - 1; _ >= 0; _--) {
            var $ = this.ololO0[_],
            A = this.getby_id($._id);
            if (!A) {
                this.ololO0.removeAt(_);
                delete this.OO00Ol[$._id]
            }
        }
        if (this.Ooo0O && this.getby_id(this.Ooo0O._id) == null) this.Ooo0O = null
    },
    setMultiSelect: function($) {
        if (this[_multiSelect] != $) {
            this[_multiSelect] = $;
            if ($ == false);
        }
    },
    getMultiSelect: function() {
        return this[_multiSelect]
    },
    selectPrev: function() {
        var _ = this[_getSelected]();
        if (!_) _ = this[_getAt](0);
        else {
            var $ = this[_indexOf](_);
            _ = this[_getAt]($ - 1)
        }
        if (_) {
            this[_deselectAll]();
            this[_select](_);
            this[_setCurrent](_)
        }
    },
    selectNext: function() {
        var _ = this[_getSelected]();
        if (!_) _ = this[_getAt](0);
        else {
            var $ = this[_indexOf](_);
            _ = this[_getAt]($ + 1)
        }
        if (_) {
            this[_deselectAll]();
            this[_select](_);
            this[_setCurrent](_)
        }
    },
    selectFirst: function() {
        var $ = this[_getAt](0);
        if ($) {
            this[_deselectAll]();
            this[_select]($);
            this[_setCurrent]($)
        }
    },
    selectLast: function() {
        var _ = this.getVisibleRows(),
        $ = this[_getAt](_.length - 1);
        if ($) {
            this[_deselectAll]();
            this[_select]($);
            this[_setCurrent]($)
        }
    },
    getSelectedsId: function($) {
        var A = this[_getSelecteds](),
        _ = this.o00o0(A, $);
        return _[0]
    },
    getSelectedsText: function($) {
        var A = this[_getSelecteds](),
        _ = this.o00o0(A, $);
        return _[1]
    },
    _filterInfo: null,
    _sortInfo: null,
    filter: function(_, $) {
        if (typeof _ != "function") return;
        $ = $ || this;
        this._filterInfo = [_, $];
        this.l0Ol();
        this.OOl0lo();
        this.Oll00O();
        this[_fire]("filter")
    },
    clearFilter: function() {
        if (!this._filterInfo) return;
        this._filterInfo = null;
        this.l0Ol();
        this.OOl0lo();
        this.Oll00O();
        this[_fire]("filter")
    },
    sort: function(A, _, $) {
        if (typeof A != "function") return;
        _ = _ || this;
        this._sortInfo = [A, _, $];
        this.OOl0lo();
        this.Oll00O();
        this[_fire]("sort")
    },
    clearSort: function() {
        this._sortInfo = null;
        this.sortField = this.sortOrder = null;
        this.l0Ol();
        this.Oll00O();
        this[_fire]("filter")
    },
    _doClientSortField: function(C, B, _) {
        var A = this._getSortFnByField(C, _);
        if (!A) return;
        this.sortField = C;
        this.sortOrder = B;
        var $ = B == "desc";
        this.sort(A, this, $)
    },
    _getSortFnByField: function(B, C) {
        if (!B) return null;
        var A = null,
        _ = mini.sortTypes[C];
        if (!_) _ = mini.sortTypes["string"];
        function $(D, H) {
            var E = mini._getMap(B, D),
            C = mini._getMap(B, H),
            G = mini.isNull(E) || E === "",
            A = mini.isNull(C) || C === "";
            if (G) return - 1;
            if (A) return 1;
            var $ = _(E),
            F = _(C);
            if ($ > F) return 1;
            else if ($ == F) return 0;
            else return - 1
        }
        A = $;
        return A
    },
    ajaxOptions: null,
    autoLoad: false,
    url: "",
    pageSize: 10,
    pageIndex: 0,
    totalCount: 0,
    totalPage: 0,
    sortField: "",
    sortOrder: "",
    loadParams: null,
    getLoadParams: function() {
        return this.loadParams || {}
    },
    sortMode: "server",
    pageIndexField: "pageIndex",
    pageSizeField: "pageSize",
    sortFieldField: "sortField",
    sortOrderField: "sortOrder",
    totalField: "total",
    dataField: "data",
    load: function($, C, B, A) {
        if (typeof $ == "string") {
            this[_setUrl]($);
            return
        }
        if (this._loadTimer) clearTimeout(this._loadTimer);
        this.loadParams = $ || {};
        if (this.ajaxAsync) {
            var _ = this;
            this._loadTimer = setTimeout(function() {
                _.OoO0l1Ajax(_.loadParams, C, B, A);
                _._loadTimer = null
            },
            1)
        } else this.OoO0l1Ajax(this.loadParams, C, B, A)
    },
    reload: function(A, _, $) {
        this[_load](this.loadParams, A, _, $)
    },
    gotoPage: function($, A) {
        var _ = this.loadParams || {};
        if (mini.isNumber($)) _[_pageIndex] = $;
        if (mini.isNumber(A)) _[_pageSize] = A;
        this[_load](_)
    },
    sortBy: function(A, _) {
        this.sortField = A;
        this.sortOrder = _ == "asc" ? "asc": "desc";
        if (this.sortMode == "server") {
            var $ = this.getLoadParams();
            $.sortField = A;
            $.sortOrder = _;
            $[_pageIndex] = this[_pageIndex];
            this[_load]($)
        }
    },
    setSortField: function($) {
        this.sortField = $;
        if (this.sortMode == "server") {
            var _ = this.getLoadParams();
            _.sortField = $
        }
    },
    setSortOrder: function($) {
        this.sortOrder = $;
        if (this.sortMode == "server") {
            var _ = this.getLoadParams();
            _.sortOrder = $
        }
    },
    checkSelectOnLoad: true,
    selectOnLoad: false,
    ajaxData: null,
    ajaxAsync: true,
    ajaxType: "",
    OoO0l1Ajax: function(H, J, B, C, E) {
        H = H || {};
        if (mini.isNull(H[_pageIndex])) H[_pageIndex] = 0;
        if (mini.isNull(H[_pageSize])) H[_pageSize] = this[_pageSize];
        H.sortField = this.sortField;
        H.sortOrder = this.sortOrder;
        this.loadParams = H;
        var I = this._evalUrl(),
        _ = this._evalType(I),
        K = {
            url: I,
            async: this.ajaxAsync,
            type: _,
            data: H,
            params: H,
            cache: false,
            cancel: false
        };
        if (K.data != K.params && K.params != H) K.data = K.params;
        var F = mini._evalAjaxData(this.ajaxData, this);
        mini.copyTo(K.data, F);
        mini.copyTo(K, this.ajaxOptions);
        this._OnBeforeLoad(K);
        if (K.cancel == true) {
            H[_pageIndex] = this[_getPageIndex]();
            H[_pageSize] = this[_getPageSize]();
            return
        }
        var $ = {};
        $[this.pageIndexField] = H[_pageIndex];
        $[this.pageSizeField] = H[_pageSize];
        if (H.sortField) $[this.sortFieldField] = H.sortField;
        if (H.sortOrder) $[this.sortOrderField] = H.sortOrder;
        mini.copyTo(H, $);
        var G = this[_getSelected]();
        this.Ooo0OValue = G ? G[this.idField] : null;
        if (mini.isNumber(this.Ooo0OValue)) this.Ooo0OValue = String(this.Ooo0OValue);
        var A = this;
        A._resultObject = null;
        var D = K.async;
        mini.copyTo(K, {
            success: function(C, L, _) {
                if (!C || C == "null") C = {
                    tatal: 0,
                    data: []
                };
                var G = null;
                try {
                    G = mini.decode(C)
                } catch(K) {
                    if (mini_debugger == true) alert(I + "\n json is error.")
                }
                if (G && !mini.isArray(G)) {
                    G.total = parseInt(mini._getMap(A.totalField, G));
                    G.data = mini._getMap(A.dataField, G)
                } else if (G == null) {
                    G = {};
                    G.data = [];
                    G.total = 0
                } else if (mini.isArray(G)) {
                    var F = {};
                    F.data = G;
                    F.total = G.length;
                    G = F
                }
                if (!G.data) G.data = [];
                if (!G.total) G.total = 0;
                A._resultObject = G;
                if (!mini.isArray(G.data)) G.data = [G.data];
                var K = {
                    xhr: _,
                    text: C,
                    textStatus: L,
                    result: G,
                    total: G.total,
                    data: G.data.clone(),
                    pageIndex: H[A.pageIndexField],
                    pageSize: H[A.pageSizeField]
                };
                if (mini.isNumber(G.error) && G.error != 0) {
                    K.textStatus = "servererror";
                    K.errorCode = G.error;
                    K.stackTrace = G.stackTrace;
                    K.errorMsg = G.errorMsg;
                    if (mini_debugger == true) alert(I + "\n" + K.textStatus + "\n" + K.stackTrace);
                    A[_fire]("loaderror", K);
                    if (B) B[_call](A, K)
                } else if (E) E(K);
                else {
                    A[_pageIndex] = K[_pageIndex];
                    A[_pageSize] = K[_pageSize];
                    A[_setTotalCount](K.total);
                    A._OnPreLoad(K);
                    A[_setData](K.data);
                    if (A.Ooo0OValue && A[_checkSelectOnLoad]) {
                        var $ = A.getbyId(A.Ooo0OValue);
                        if ($) A[_select]($)
                    }
                    if (A[_getSelected]() == null && A.selectOnLoad && A.getDataView().length > 0) A[_select](0);
                    A[_fire]("load", K);
                    if (J) if (D) setTimeout(function() {
                        J[_call](A, K)
                    },
                    20);
                    else J[_call](A, K)
                }
            },
            error: function($, D, _) {
                var C = {
                    xhr: $,
                    text: $.responseText,
                    textStatus: D
                };
                C.errorMsg = $.responseText;
                C.errorCode = $.status;
                if (mini_debugger == true) alert(I + "\n" + C.errorCode + "\n" + C.errorMsg);
                A[_fire]("loaderror", C);
                if (B) B[_call](A, C)
            },
            complete: function($, B) {
                var _ = {
                    xhr: $,
                    text: $.responseText,
                    textStatus: B
                };
                A[_fire]("loadcomplete", _);
                if (C) C[_call](A, _);
                A._xhr = null
            }
        });
        if (this._xhr);
        this._xhr = mini.ajax(K)
    },
    _OnBeforeLoad: function($) {
        this[_fire]("beforeload", $)
    },
    _OnPreLoad: function($) {
        this[_fire]("preload", $)
    },
    _evalUrl: function() {
        var url = this.url;
        if (typeof url == "function") url = url();
        else {
            try {
                url = eval(url)
            } catch(ex) {
                url = this.url
            }
            if (!url) url = this.url
        }
        return url
    },
    _evalType: function(_) {
        var $ = this.ajaxType;
        if (!$) {
            $ = "post";
            if (_) {
                if (_[_indexOf](".txt") != -1 || _[_indexOf](".json") != -1) $ = "get"
            } else $ = "get"
        }
        return $
    },
    setSortMode: function($) {
        this.sortMode = $
    },
    getSortMode: function() {
        return this.sortMode
    },
    setAjaxOptions: function($) {
        this.ajaxOptions = $
    },
    getAjaxOptions: function() {
        return this.ajaxOptions
    },
    setAutoLoad: function($) {
        this.autoLoad = $
    },
    getAutoLoad: function() {
        return this.autoLoad
    },
    setUrl: function($) {
        this.url = $;
        if (this.autoLoad) this[_load]()
    },
    getUrl: function() {
        return this.url
    },
    setPageIndex: function($) {
        this[_pageIndex] = $;
        this[_fire]("pageinfochanged")
    },
    getPageIndex: function() {
        return this[_pageIndex]
    },
    setPageSize: function($) {
        this[_pageSize] = $;
        this[_fire]("pageinfochanged")
    },
    getPageSize: function() {
        return this[_pageSize]
    },
    setTotalCount: function($) {
        this[_totalCount] = $;
        this[_fire]("pageinfochanged")
    },
    getTotalCount: function() {
        return this[_totalCount]
    },
    getTotalPage: function() {
        return this.totalPage
    },
    setCheckSelectOnLoad: function($) {
        this[_checkSelectOnLoad] = $
    },
    getCheckSelectOnLoad: function() {
        return this[_checkSelectOnLoad]
    },
    setSelectOnLoad: function($) {
        this.selectOnLoad = $
    },
    getSelectOnLoad: function() {
        return this.selectOnLoad
    }
});
mini.DataSource.RecordId = 1;
mini.DataTable = function() {
    mini.DataTable[_superclass][_constructor][_call](this)
};
lol01(mini.DataTable, mini.DataSource, {
    _init: function() {
        mini.DataTable[_superclass]._init[_call](this);
        this._filterInfo = null;
        this._sortInfo = null
    },
    add: function($) {
        return this.insert(this.source.length, $)
    },
    addRange: function($) {
        this.insertRange(this.source.length, $)
    },
    insert: function($, _) {
        if (!_) return null;
        var D = {
            index: $,
            record: _
        };
        this[_fire]("beforeadd", D);
        if (!mini.isNumber($)) {
            var B = this.getRecord($);
            if (B) $ = this[_indexOf](B);
            else $ = this.getDataView().length
        }
        var C = this.dataview[$];
        if (C) this.dataview.insert($, _);
        else this.dataview[_add](_);
        if (this.dataview != this.source) if (C) {
            var A = this.source[_indexOf](C);
            this.source.insert(A, _)
        } else this.source[_add](_);
        this._setAdded(_);
        this.Oll00O();
        this[_fire]("add", D)
    },
    insertRange: function($, B) {
        if (!mini.isArray(B)) return;
        this.beginChange();
        for (var A = 0,
        C = B.length; A < C; A++) {
            var _ = B[A];
            this.insert($ + A, _)
        }
        this.endChange()
    },
    remove: function(_, A) {
        var $ = this[_indexOf](_);
        return this.removeAt($, A)
    },
    removeAt: function($, D) {
        var _ = this[_getAt]($);
        if (!_) return null;
        var C = {
            record: _
        };
        this[_fire]("beforeremove", C);
        var B = this[_isSelected](_);
        this.source.removeAt($);
        if (this.dataview !== this.source) this.dataview.removeAt($);
        this._setRemoved(_);
        this.loO10();
        this.Oll00O();
        this[_fire]("remove", C);
        if (B && D) {
            var A = this[_getAt]($);
            if (!A) A = this[_getAt]($ - 1);
            this[_deselectAll]();
            this[_select](A)
        }
    },
    removeRange: function(A, C) {
        if (!mini.isArray(A)) return;
        this.beginChange();
        A = A.clone();
        for (var _ = 0,
        B = A.length; _ < B; _++) {
            var $ = A[_];
            this.remove($, C)
        }
        this.endChange()
    },
    move: function(_, H) {
        if (!_ || !mini.isNumber(H)) return;
        if (H < 0) return;
        if (mini.isArray(_)) {
            this.beginChange();
            var I = _,
            C = this[_getAt](H),
            F = this;
            mini.sort(I,
            function($, _) {
                return F[_indexOf]($) > F[_indexOf](_)
            },
            this);
            for (var E = 0,
            D = I.length; E < D; E++) {
                var A = I[E],
                $ = this[_indexOf](C);
                this.move(A, $)
            }
            this.endChange();
            return
        }
        var J = {
            index: H,
            record: _
        };
        this[_fire]("beforemove", J);
        var B = this.dataview[H];
        this.dataview.remove(_);
        var G = this.dataview[_indexOf](B);
        if (G != -1) H = G;
        if (B) this.dataview.insert(H, _);
        else this.dataview[_add](_);
        if (this.dataview != this.source) {
            this.source.remove(_);
            G = this.source[_indexOf](B);
            if (G != -1) H = G;
            if (B) this.source.insert(H, _);
            else this.source[_add](_)
        }
        this.Oll00O();
        this[_fire]("move", J)
    },
    indexOf: function($) {
        return this.dataview[_indexOf]($)
    },
    getAt: function($) {
        return this.dataview[$]
    },
    getRange: function(A, B) {
        if (A > B) {
            var C = A;
            A = B;
            B = C
        }
        var D = [];
        for (var _ = A,
        E = B; _ <= E; _++) {
            var $ = this.dataview[_];
            D.push($)
        }
        return D
    },
    selectRange: function($, _) {
        if (!mini.isNumber($)) $ = this[_indexOf]($);
        if (!mini.isNumber(_)) _ = this[_indexOf](_);
        if (mini.isNull($) || mini.isNull(_)) return;
        var A = this.getRange($, _);
        this[_selects](A)
    },
    toArray: function() {
        return this.source.clone()
    },
    isChanged: function() {
        return this.getChanges().length > 0
    },
    getChanges: function(F, I) {
        var E = [];
        if (F == "removed" || F == null) E.addRange(this._removeds.clone());
        for (var A = 0,
        H = this.source.length; A < H; A++) {
            var _ = this.source[A];
            if (!_._state) continue;
            if (_._state == F || F == null) E[E.length] = _
        }
        var D = E;
        if (I) for (A = 0, H = D.length; A < H; A++) {
            var C = D[A];
            if (C._state == "modified") {
                var B = {};
                B[this.idField] = C[this.idField];
                for (var G in C) {
                    var $ = this.isModified(C, G);
                    if ($) B[G] = C[G]
                }
                D[A] = B
            }
        }
        return E
    },
    accept: function() {
        this.beginChange();
        for (var _ = 0,
        A = this.source.length; _ < A; _++) {
            var $ = this.source[_];
            this.acceptRecord($)
        }
        this._removeds = [];
        this.lollO = {};
        this.endChange()
    },
    reject: function() {
        this.beginChange();
        for (var _ = 0,
        A = this.source.length; _ < A; _++) {
            var $ = this.source[_];
            this.rejectRecord($)
        }
        this._removeds = [];
        this.lollO = {};
        this.endChange()
    },
    acceptRecord: function($) {
        delete this.lollO[$[this.o0Oo]];
        if ($._state == "deleted") this[_removeNode]($);
        else {
            delete $._state;
            delete this.lollO[$[this.o0Oo]];
            this.Oll00O()
        }
        this[_fire]("update", {
            record: $
        })
    },
    rejectRecord: function(_) {
        if (_._state == "added") this[_removeNode](_);
        else if (_._state == "modified" || _._state == "deleted") {
            var $ = this.lo1o0(_);
            mini.copyTo(_, $);
            delete _._state;
            delete this.lollO[_[this.o0Oo]];
            this.Oll00O()
        }
    },
    l0Ol: function() {
        if (!this._filterInfo) {
            this.dataview = this.source;
            return
        }
        var F = this._filterInfo[0],
        D = this._filterInfo[1],
        $ = [],
        C = this.source;
        for (var _ = 0,
        E = C.length; _ < E; _++) {
            var B = C[_],
            A = F[_call](D, B, _, this);
            if (A !== false) $.push(B)
        }
        this.dataview = $
    },
    OOl0lo: function() {
        if (!this._sortInfo) return;
        var B = this._sortInfo[0],
        A = this._sortInfo[1],
        $ = this._sortInfo[2],
        _ = this.getDataView().clone();
        mini.sort(_, B, A);
        if ($) _.reverse();
        this.dataview = _
    }
});
O1o0(mini.DataTable, "datatable");
mini.DataTree = function() {
    mini.DataTree[_superclass][_constructor][_call](this)
};
lol01(mini.DataTree, mini.DataSource, {
    isTree: true,
    expandOnLoad: false,
    idField: "id",
    parentField: "pid",
    nodesField: "children",
    checkedField: "checked",
    resultAsTree: true,
    dataField: "",
    checkModel: "cascade",
    autoCheckParent: false,
    onlyLeafCheckable: false,
    setExpandOnLoad: function($) {
        this.expandOnLoad = $
    },
    getExpandOnLoad: function() {
        return this.expandOnLoad
    },
    setParentField: function($) {
        this[_parentField] = $
    },
    setNodesField: function($) {
        if (this.nodesField != $) {
            var _ = this.root[this.nodesField];
            this.nodesField = $;
            this.Oo0O(_)
        }
    },
    setResultAsTree: function($) {
        this[_resultAsTree] = $
    },
    setCheckRecursive: function($) {
        this.checkModel = $ ? "cascade": "multiple"
    },
    getCheckRecursive: function() {
        return this.checkModel == "cascade"
    },
    setShowFolderCheckBox: function($) {
        this.onlyLeafCheckable = !$
    },
    getShowFolderCheckBox: function() {
        return ! this.onlyLeafCheckable
    },
    _doExpandOnLoad: function(B) {
        var _ = this.nodesField,
        $ = this.expandOnLoad;
        function A(G, C) {
            for (var D = 0,
            F = G.length; D < F; D++) {
                var E = G[D];
                if (mini.isNull(E.expanded)) {
                    if ($ === true || (mini.isNumber($) && C <= $)) E.expanded = true;
                    else E.expanded = false
                }
                var B = E[_];
                if (B) A(B, C + 1)
            }
        }
        A(B, 0)
    },
    _OnBeforeLoad: function(_) {
        var $ = this._loadingNode || this.root;
        _.node = $;
        if (this._isNodeLoading()) {
            _.async = true;
            _.isRoot = _.node == this.root;
            if (!_.isRoot) _.data[this.idField] = this[_getItemValue](_.node)
        }
        this[_fire]("beforeload", _)
    },
    _OnPreLoad: function($) {
        if (this[_resultAsTree] == false) $.data = mini.arrayToTree($.data, this.nodesField, this.idField, this[_parentField]);
        this[_fire]("preload", $)
    },
    _init: function() {
        mini.DataTree[_superclass]._init[_call](this);
        this.root = {
            _id: -1,
            _level: -1
        };
        this.source = this.root[this.nodesField] = [];
        this.viewNodes = null;
        this.dataview = null;
        this.visibleRows = null;
        this._ids[this.root._id] = this.root
    },
    Oo0O: function(D) {
        D = D || [];
        this._doExpandOnLoad(D);
        this.source = this.root[this.nodesField] = D;
        this.viewNodes = null;
        this.dataview = null;
        this.visibleRows = null;
        var A = mini[_treeToArray](D, this.nodesField),
        B = this._ids;
        B[this.root._id] = this.root;
        for (var _ = 0,
        F = A.length; _ < F; _++) {
            var C = A[_];
            C._id = mini.DataSource.RecordId++;
            B[C._id] = C;
            C._uid = C._id
        }
        var G = this.checkedField,
        A = mini[_treeToArray](D, this.nodesField, "_id", "_pid", this.root._id);
        for (_ = 0, F = A.length; _ < F; _++) {
            var C = A[_],
            $ = this[_getParentNode](C);
            C._pid = $._id;
            C._level = $._level + 1;
            delete C._state;
            C.checked = C[G];
            if (C.checked) C.checked = C.checked != "false";
            if (C.isLeaf === false) {
                var E = C[this.nodesField];
                if (E && E.length > 0) delete C.isLeaf
            }
        }
        this._doUpdateLoadedCheckedNodes()
    },
    _setAdded: function(_) {
        var $ = this[_getParentNode](_);
        _._id = mini.DataSource.RecordId++;
        if (this._autoCreateNewID && !_[this.idField]) _[this.idField] = UUID();
        _._uid = _._id;
        _._pid = $._id;
        _[this.parentField] = $[this.idField];
        _._level = $._level + 1;
        _._state = "added";
        this._ids[_._id] = _;
        delete this.lollO[_[this.o0Oo]]
    },
    olOOo: function($) {
        var _ = $[this.nodesField];
        if (!_) _ = $[this.nodesField] = [];
        if (this.viewNodes && !this.viewNodes[$._id]) this.viewNodes[$._id] = [];
        return _
    },
    addNode: function(_, $) {
        if (!_) return;
        return this.insertNode(_, -1, $)
    },
    addNodes: function(D, _, A) {
        if (!mini.isArray(D)) return;
        if (mini.isNull(A)) A = "add";
        for (var $ = 0,
        C = D.length; $ < C; $++) {
            var B = D[$];
            this.insertNode(B, A, _)
        }
    },
    insertNodes: function(D, $, A) {
        if (!mini.isNumber($)) return;
        if (!mini.isArray(D)) return;
        if (!A) A = this.root;
        this.beginChange();
        var B = this.olOOo(A);
        if ($ < 0 || $ > B.length) $ = B.length;
        D = D.clone();
        for (var _ = 0,
        C = D.length; _ < C; _++) this.insertNode(D[_], $ + _, A);
        this.endChange();
        return D
    },
    removeNode: function(A) {
        var _ = this[_getParentNode](A);
        if (!_) return;
        var $ = this.indexOfNode(A);
        return this.removeNodeAt($, _)
    },
    removeNodes: function(A) {
        if (!mini.isArray(A)) return;
        this.beginChange();
        A = A.clone();
        for (var $ = 0,
        _ = A.length; $ < _; $++) this[_removeNode](A[$]);
        this.endChange()
    },
    moveNodes: function(E, B, _) {
        if (!E || E.length == 0 || !B || !_) return;
        this.beginChange();
        var A = this;
        mini.sort(E,
        function($, _) {
            return A[_indexOf]($) > A[_indexOf](_)
        },
        this);
        for (var $ = 0,
        D = E.length; $ < D; $++) {
            var C = E[$];
            this.moveNode(C, B, _);
            if ($ != 0) {
                B = C;
                _ = "after"
            }
        }
        this.endChange()
    },
    moveNode: function(E, D, B) {
        if (!E || !D || mini.isNull(B)) return;
        if (this.viewNodes) {
            var _ = D,
            $ = B;
            if ($ == "before") {
                _ = this[_getParentNode](D);
                $ = this.indexOfNode(D)
            } else if ($ == "after") {
                _ = this[_getParentNode](D);
                $ = this.indexOfNode(D) + 1
            } else if ($ == "add" || $ == "append") {
                if (!_[this.nodesField]) _[this.nodesField] = [];
                $ = _[this.nodesField].length
            } else if (!mini.isNumber($)) return;
            if (this.isAncestor(E, _)) return false;
            var A = this[_getChildNodes](_);
            if ($ < 0 || $ > A.length) $ = A.length;
            var F = {};
            A.insert($, F);
            var C = this[_getParentNode](E),
            G = this[_getChildNodes](C);
            G.remove(E);
            $ = A[_indexOf](F);
            A[$] = E
        }
        _ = D,
        $ = B,
        A = this.olOOo(_);
        if ($ == "before") {
            _ = this[_getParentNode](D);
            A = this.olOOo(_);
            $ = A[_indexOf](D)
        } else if ($ == "after") {
            _ = this[_getParentNode](D);
            A = this.olOOo(_);
            $ = A[_indexOf](D) + 1
        } else if ($ == "add" || $ == "append") $ = A.length;
        else if (!mini.isNumber($)) return;
        if (this.isAncestor(E, _)) return false;
        if ($ < 0 || $ > A.length) $ = A.length;
        F = {};
        A.insert($, F);
        C = this[_getParentNode](E);
        C[this.nodesField].remove(E);
        $ = A[_indexOf](F);
        A[$] = E;
        this.llol(E, _);
        this.Oll00O();
        var H = {
            parentNode: _,
            index: $,
            node: E
        };
        this[_fire]("movenode", H)
    },
    insertNode: function(A, $, _) {
        if (!A) return;
        if (!_) {
            _ = this.root;
            $ = "add"
        }
        if (!mini.isNumber($)) {
            switch ($) {
            case "before":
                $ = this.indexOfNode(_);
                _ = this[_getParentNode](_);
                this.insertNode(A, $, _);
                break;
            case "after":
                $ = this.indexOfNode(_);
                _ = this[_getParentNode](_);
                this.insertNode(A, $ + 1, _);
                break;
            case "append":
            case "add":
                this.addNode(A, _);
                break;
            default:
                break
            }
            return
        }
        var C = this.olOOo(_),
        D = this[_getChildNodes](_);
        if ($ < 0) $ = D.length;
        D.insert($, A);
        $ = D[_indexOf](A);
        if (this.viewNodes) {
            var B = D[$ - 1];
            if (B) {
                var E = C[_indexOf](B);
                C.insert(E + 1, A)
            } else C.insert(0, A)
        }
        A._pid = _._id;
        this._setAdded(A);
        this.cascadeChild(A,
        function(A, $, _) {
            A._pid = _._id;
            this._setAdded(A)
        },
        this);
        this.Oll00O();
        var F = {
            parentNode: _,
            index: $,
            node: A
        };
        this[_fire]("addnode", F);
        return A
    },
    removeNodeAt: function($, _) {
        if (!_) _ = this.root;
        var C = this[_getChildNodes](_),
        A = C[$];
        if (!A) return null;
        C.removeAt($);
        if (this.viewNodes) {
            var B = _[this.nodesField];
            B.remove(A)
        }
        this._setRemoved(A);
        this.cascadeChild(A,
        function(A, $, _) {
            this._setRemoved(A)
        },
        this);
        this.loO10();
        this.Oll00O();
        var D = {
            parentNode: _,
            index: $,
            node: A
        };
        this[_fire]("removenode", D);
        return A
    },
    bubbleParent: function(_, B, A) {
        A = A || this;
        if (_) B[_call](this, _);
        var $ = this[_getParentNode](_);
        if ($ && $ != this.root) this.bubbleParent($, B, A)
    },
    cascadeChild: function(A, E, B) {
        if (!E) return;
        if (!A) A = this.root;
        var D = A[this.nodesField];
        if (D) {
            D = D.clone();
            for (var $ = 0,
            C = D.length; $ < C; $++) {
                var _ = D[$];
                if (E[_call](B || this, _, $, A) === false) return;
                this.cascadeChild(_, E, B)
            }
        }
    },
    eachChild: function(B, F, C) {
        if (!F || !B) return;
        var E = B[this.nodesField];
        if (E) {
            var _ = E.clone();
            for (var A = 0,
            D = _.length; A < D; A++) {
                var $ = _[A];
                if (F[_call](C || this, $, A, B) === false) break
            }
        }
    },
    collapse: function($, _) {
        if (!$) return;
        this.beginChange();
        $.expanded = false;
        if (_) this.eachChild($,
        function($) {
            if ($[this.nodesField] != null) this[_collapse]($, _)
        },
        this);
        this.endChange();
        var A = {
            node: $
        };
        this[_fire]("collapse", A)
    },
    expand: function($, _) {
        if (!$) return;
        this.beginChange();
        $.expanded = true;
        if (_) this.eachChild($,
        function($) {
            if ($[this.nodesField] != null) this[_expand]($, _)
        },
        this);
        this.endChange();
        var A = {
            node: $
        };
        this[_fire]("expand", A)
    },
    toggle: function($) {
        if (this.isExpandedNode($)) this[_collapse]($);
        else this[_expand]($)
    },
    expandNode: function($) {
        this[_expand]($)
    },
    collapseNode: function($) {
        this[_collapse]($)
    },
    collapseAll: function() {
        this[_collapse](this.root, true)
    },
    expandAll: function() {
        this[_expand](this.root, true)
    },
    collapseLevel: function($, _) {
        this.beginChange();
        this.each(function(A) {
            var B = this.getLevel(A);
            if ($ == B) this[_collapse](A, _)
        },
        this);
        this.endChange()
    },
    expandLevel: function($, _) {
        this.beginChange();
        this.each(function(A) {
            var B = this.getLevel(A);
            if ($ == B) this[_expand](A, _)
        },
        this);
        this.endChange()
    },
    expandPath: function(A) {
        A = this[_getNode](A);
        if (!A) return;
        var _ = this[_getAncestors](A);
        for (var $ = 0,
        B = _.length; $ < B; $++) this[_expandNode](_[$])
    },
    collapsePath: function(A) {
        A = this[_getNode](A);
        if (!A) return;
        var _ = this[_getAncestors](A);
        for (var $ = 0,
        B = _.length; $ < B; $++) this[_collapseNode](_[$])
    },
    isAncestor: function(_, B) {
        if (_ == B) return true;
        if (!_ || !B) return false;
        var A = this[_getAncestors](B);
        for (var $ = 0,
        C = A.length; $ < C; $++) if (A[$] == _) return true;
        return false
    },
    getAncestors: function(A) {
        var _ = [];
        while (1) {
            var $ = this[_getParentNode](A);
            if (!$ || $ == this.root) break;
            _[_.length] = $;
            A = $
        }
        _.reverse();
        return _
    },
    getNode: function($) {
        return this.getRecord($)
    },
    getRootNode: function() {
        return this.root
    },
    getParentNode: function($) {
        if (!$) return null;
        return this.getby_id($._pid)
    },
    getAllChildNodes: function($) {
        return this[_getChildNodes]($, true)
    },
    getChildNodes: function(A, C, B) {
        var G = A[this.nodesField];
        if (this.viewNodes && B !== false) G = this.viewNodes[A._id];
        if (C === true && G) {
            var $ = [];
            for (var _ = 0,
            F = G.length; _ < F; _++) {
                var D = G[_];
                $[$.length] = D;
                var E = this[_getChildNodes](D, C, B);
                if (E && E.length > 0) $.addRange(E)
            }
            G = $
        }
        return G || []
    },
    getChildNodeAt: function($, _) {
        var A = this[_getChildNodes](_);
        if (A) return A[$];
        return null
    },
    hasChildNodes: function($) {
        var _ = this[_getChildNodes]($);
        return _.length > 0
    },
    getLevel: function($) {
        return $._level
    },
    isLeafNode: function($) {
        return this.isLeaf($)
    },
    isLeaf: function($) {
        if (!$ || $.isLeaf === false) return false;
        var _ = this[_getChildNodes]($);
        if (_.length > 0) return false;
        return true
    },
    hasChildren: function($) {
        var _ = this[_getChildNodes]($);
        return !! (_ && _.length > 0)
    },
    isFirstNode: function(_) {
        if (_ == this.root) return true;
        var $ = this[_getParentNode](_);
        if (!$) return false;
        return this.getFirstNode($) == _
    },
    isLastNode: function(_) {
        if (_ == this.root) return true;
        var $ = this[_getParentNode](_);
        if (!$) return false;
        return this.getLastNode($) == _
    },
    isCheckedNode: function($) {
        return $.checked === true
    },
    isExpandedNode: function($) {
        return $.expanded == true || $.expanded == 1 || mini.isNull($.expanded)
    },
    isVisibleNode: function(_) {
        if (_.visible == false) return false;
        var $ = this._ids[_._pid];
        if (!$ || $ == this.root) return true;
        if ($.expanded === false) return false;
        return this.isVisibleNode($)
    },
    getNextNode: function(A) {
        var _ = this.getby_id(A._pid);
        if (!_) return null;
        var $ = this.indexOfNode(A);
        return this[_getChildNodes](_)[$ + 1]
    },
    getPrevNode: function(A) {
        var _ = this.getby_id(A._pid);
        if (!_) return null;
        var $ = this.indexOfNode(A);
        return this[_getChildNodes](_)[$ - 1]
    },
    getFirstNode: function($) {
        return this[_getChildNodes]($)[0]
    },
    getLastNode: function($) {
        var _ = this[_getChildNodes]($);
        return _[_.length - 1]
    },
    indexOfNode: function(_) {
        var $ = this.getby_id(_._pid);
        if ($) return this[_getChildNodes]($)[_indexOf](_);
        return - 1
    },
    getAt: function($) {
        return this.getDataView()[$]
    },
    indexOf: function($) {
        return this.getDataView()[_indexOf]($)
    },
    getRange: function(A, C) {
        if (A > C) {
            var D = A;
            A = C;
            C = D
        }
        var B = this[_getChildNodes](this.root, true),
        E = [];
        for (var _ = A,
        F = C; _ <= F; _++) {
            var $ = B[_];
            if ($) E.push($)
        }
        return E
    },
    selectRange: function($, A) {
        var _ = this[_getChildNodes](this.root, true);
        if (!mini.isNumber($)) $ = _[_indexOf]($);
        if (!mini.isNumber(A)) A = _[_indexOf](A);
        if (mini.isNull($) || mini.isNull(A)) return;
        var B = this.getRange($, A);
        this[_selects](B)
    },
    findRecords: function(D, A) {
        var C = this.toArray(),
        F = typeof D == "function",
        I = D,
        E = A || this,
        B = [];
        for (var _ = 0,
        H = C.length; _ < H; _++) {
            var $ = C[_];
            if (F) {
                var G = I[_call](E, $);
                if (G == true) B[B.length] = $;
                if (G === 1) break
            } else if ($[D] == A) B[B.length] = $
        }
        return B
    },
    Oll00OCount: 0,
    Oll00O: function() {
        this.Oll00OCount++;
        this.dataview = null;
        this.visibleRows = null;
        if (this.__changeCount == 0) this[_fire]("datachanged")
    },
    l1o01OView: function() {
        var $ = this[_getChildNodes](this.root, true);
        return $
    },
    _createVisibleRows: function() {
        var B = this[_getChildNodes](this.root, true),
        $ = [];
        for (var _ = 0,
        C = B.length; _ < C; _++) {
            var A = B[_];
            if (this.isVisibleNode(A)) $[$.length] = A
        }
        return $
    },
    getList: function() {
        return mini.treeToList(this.source, this.nodesField)
    },
    getDataView: function() {
        if (!this.dataview) this.dataview = this.l1o01OView();
        return this.dataview
    },
    getVisibleRows: function() {
        if (!this.visibleRows) this.visibleRows = this._createVisibleRows();
        return this.visibleRows
    },
    l0Ol: function() {
        if (!this._filterInfo) {
            this.viewNodes = null;
            return
        }
        var C = this._filterInfo[0],
        B = this._filterInfo[1],
        A = this.viewNodes = {},
        _ = this.nodesField;
        function $(G) {
            var J = G[_];
            if (!J) return false;
            var K = G._id,
            H = A[K] = [];
            for (var D = 0,
            I = J.length; D < I; D++) {
                var F = J[D],
                L = $(F),
                E = C[_call](B, F, D, this);
                if (E === true || L) H.push(F)
            }
            return H.length > 0
        }
        $(this.root)
    },
    OOl0lo: function() {
        if (!this._filterInfo && !this._sortInfo) {
            this.viewNodes = null;
            return
        }
        if (!this._sortInfo) return;
        var E = this._sortInfo[0],
        D = this._sortInfo[1],
        $ = this._sortInfo[2],
        _ = this.nodesField;
        if (!this.viewNodes) {
            var C = this.viewNodes = {};
            C[this.root._id] = this.root[_].clone();
            this.cascadeChild(this.root,
            function(A, $, B) {
                var D = A[_];
                if (D) C[A._id] = D.clone()
            })
        }
        var B = this;
        function A(F) {
            var H = B[_getChildNodes](F);
            mini.sort(H, E, D);
            if ($) H.reverse();
            for (var _ = 0,
            G = H.length; _ < G; _++) {
                var C = H[_];
                A(C)
            }
        }
        A(this.root)
    },
    toArray: function() {
        if (!this._array || this.Oll00OCount != this.Oll00OCount2) {
            this.Oll00OCount2 = this.Oll00OCount;
            this._array = this[_getChildNodes](this.root, true, false)
        }
        return this._array
    },
    toTree: function() {
        return this.root[this.nodesField]
    },
    isChanged: function() {
        return this.getChanges().length > 0
    },
    getChanges: function(E, H) {
        var D = [];
        if (E == "removed" || E == null) D.addRange(this._removeds.clone());
        this.cascadeChild(this.root,
        function(_, $, A) {
            if (_._state == null || _._state == "") return;
            if (_._state == E || E == null) D[D.length] = _
        },
        this);
        var C = D;
        if (H) for (var _ = 0,
        G = C.length; _ < G; _++) {
            var B = C[_];
            if (B._state == "modified") {
                var A = {};
                A[this.idField] = B[this.idField];
                for (var F in B) {
                    var $ = this.isModified(B, F);
                    if ($) A[F] = B[F]
                }
                C[_] = A
            }
        }
        return D
    },
    accept: function($) {
        $ = $ || this.root;
        this.beginChange();
        this.cascadeChild(this.root,
        function($) {
            this.acceptRecord($)
        },
        this);
        this._removeds = [];
        this.lollO = {};
        this.endChange()
    },
    reject: function($) {
        this.beginChange();
        this.cascadeChild(this.root,
        function($) {
            this.rejectRecord($)
        },
        this);
        this._removeds = [];
        this.lollO = {};
        this.endChange()
    },
    acceptRecord: function($) {
        delete this.lollO[$[this.o0Oo]];
        if ($._state == "deleted") this[_removeNode]($);
        else {
            delete $._state;
            delete this.lollO[$[this.o0Oo]];
            this.Oll00O()
        }
    },
    rejectRecord: function(_) {
        if (_._state == "added") this[_removeNode](_);
        else if (_._state == "modified" || _._state == "deleted") {
            var $ = this.lo1o0(_);
            mini.copyTo(_, $);
            delete _._state;
            delete this.lollO[_[this.o0Oo]];
            this.Oll00O()
        }
    },
    upGrade: function(F) {
        var C = this[_getParentNode](F);
        if (C == this.root || F == this.root) return false;
        var E = C[this.nodesField],
        _ = E[_indexOf](F),
        G = F[this.nodesField] ? F[this.nodesField].length: 0;
        for (var B = E.length - 1; B >= _; B--) {
            var $ = E[B];
            E.removeAt(B);
            if ($ != F) {
                if (!F[this.nodesField]) F[this.nodesField] = [];
                F[this.nodesField].insert(G, $)
            }
        }
        var D = this[_getParentNode](C),
        A = D[this.nodesField],
        _ = A[_indexOf](C);
        A.insert(_ + 1, F);
        this.llol(F, D);
        this.l0Ol();
        this.Oll00O()
    },
    downGrade: function(B) {
        if (this[_isFirstNode](B)) return false;
        var A = this[_getParentNode](B),
        C = A[this.nodesField],
        $ = C[_indexOf](B),
        _ = C[$ - 1];
        C.removeAt($);
        if (!_[this.nodesField]) _[this.nodesField] = [];
        _[this.nodesField][_add](B);
        this.llol(B, _);
        this.l0Ol();
        this.Oll00O()
    },
    llol: function(_, $) {
        _._pid = $._id;
        _._level = $._level + 1;
        this.cascadeChild(_,
        function(A, $, _) {
            A._pid = _._id;
            A._level = _._level + 1;
            A[this.parentField] = _[this.idField]
        },
        this);
        this._setModified(_)
    },
    setCheckModel: function($) {
        this.checkModel = $
    },
    getCheckModel: function() {
        return this.checkModel
    },
    setOnlyLeafCheckable: function($) {
        this.onlyLeafCheckable = $
    },
    getOnlyLeafCheckable: function() {
        return this.onlyLeafCheckable
    },
    setAutoCheckParent: function($) {
        this.autoCheckParent = $
    },
    getAutoCheckParent: function() {
        return this.autoCheckParent
    },
    _doUpdateLoadedCheckedNodes: function() {
        var B = this.getAllChildNodes(this.root);
        for (var $ = 0,
        A = B.length; $ < A; $++) {
            var _ = B[$];
            if (_.checked == true) if (this.autoCheckParent == false || !this.hasChildNodes(_)) this._doUpdateNodeCheckState(_)
        }
    },
    _doUpdateNodeCheckState: function(B) {
        if (!B) return;
        var K = this.isChecked(B);
        if (this.checkModel == "cascade" || this.autoCheckParent) {
            this.cascadeChild(B,
            function(_) {
                var $ = this.getCheckable(_);
                if ($) this.doCheckNodes(_, K)
            },
            this);
            if (!this.autoCheckParent) {
                var $ = this[_getAncestors](B);
                $.reverse();
                for (var H = 0,
                E = $.length; H < E; H++) {
                    var C = $[H],
                    J = this.getCheckable(C);
                    if (J == false) return;
                    var A = this[_getChildNodes](C),
                    I = true;
                    for (var _ = 0,
                    F = A.length; _ < F; _++) {
                        var D = A[_];
                        if (!this.isCheckedNode(D)) I = false
                    }
                    if (I) this.doCheckNodes(C, true);
                    else this.doCheckNodes(C, false);
                    this[_fire]("checkchanged", {
                        nodes: [C],
                        _nodes: [C]
                    })
                }
            }
        }
        var G = this;
        function L(A) {
            var _ = G[_getChildNodes](A);
            for (var $ = 0,
            C = _.length; $ < C; $++) {
                var B = _[$];
                if (G.isCheckedNode(B)) return true
            }
            return false
        }
        if (this.autoCheckParent) {
            $ = this[_getAncestors](B);
            $.reverse();
            for (H = 0, E = $.length; H < E; H++) {
                C = $[H],
                J = this.getCheckable(C);
                if (J == false) return;
                C.checked = L(C);
                this[_fire]("checkchanged", {
                    nodes: [C],
                    _nodes: [C]
                })
            }
        }
    },
    doCheckNodes: function(E, B, D) {
        if (!E) return;
        if (typeof E == "string") E = E.split(",");
        if (!mini.isArray(E)) E = [E];
        E = E.clone();
        var _ = [];
        B = B !== false;
        if (D === true) if (this.checkModel == "single") this.uncheckAllNodes();
        for (var $ = E.length - 1; $ >= 0; $--) {
            var A = this.getRecord(E[$]);
            if (!A || (B && A.checked === true) || (!B && A.checked !== true)) {
                if (A) if (D === true) this._doUpdateNodeCheckState(A);
                continue
            }
            A.checked = B;
            _.push(A);
            if (D === true) this._doUpdateNodeCheckState(A)
        }
        var C = this;
        setTimeout(function() {
            C[_fire]("checkchanged", {
                nodes: E,
                _nodes: _,
                checked: B
            })
        },
        1)
    },
    checkNode: function($) {
        this.doCheckNodes([$], true, true)
    },
    uncheckNode: function($) {
        this.doCheckNodes([$], false, true)
    },
    checkNodes: function($) {
        if (!mini.isArray($)) $ = [];
        this.doCheckNodes($, true, true)
    },
    uncheckNodes: function($) {
        if (!mini.isArray($)) $ = [];
        this.doCheckNodes($, false, true)
    },
    checkAllNodes: function() {
        var $ = this[_getList]();
        this.doCheckNodes($, true)
    },
    uncheckAllNodes: function() {
        var $ = this[_getList]();
        this.doCheckNodes($, false)
    },
    getCheckedNodes: function(_) {
        var A = [],
        $ = {};
        this.cascadeChild(this.root,
        function(D) {
            if (D.checked == true) {
                var F = this.isLeafNode(D);
                if (_ === true) {
                    if (!$[D._id]) {
                        $[D._id] = D;
                        A.push(D)
                    }
                    var C = this[_getAncestors](D);
                    for (var B = 0,
                    G = C.length; B < G; B++) {
                        var E = C[B];
                        if (!$[E._id]) {
                            $[E._id] = E;
                            A.push(E)
                        }
                    }
                } else if (_ === "parent") {
                    if (!F) if (!$[D._id]) {
                        $[D._id] = D;
                        A.push(D)
                    }
                } else if (_ === "leaf") {
                    if (F) if (!$[D._id]) {
                        $[D._id] = D;
                        A.push(D)
                    }
                } else if (!$[D._id]) {
                    $[D._id] = D;
                    A.push(D)
                }
            }
        },
        this);
        return A
    },
    getCheckedNodesId: function(A, $) {
        var B = this[_getCheckedNodes](A),
        _ = this.o00o0(B, $);
        return _[0]
    },
    getCheckedNodesText: function(A, $) {
        var B = this[_getCheckedNodes](A),
        _ = this.o00o0(B, $);
        return _[1]
    },
    isChecked: function($) {
        $ = this.getRecord($);
        if (!$) return null;
        return $.checked === true
    },
    getCheckState: function(_) {
        _ = this.getRecord(_);
        if (!_) return null;
        if (_.checked === true) return "checked";
        if (!_[this.nodesField]) return "unchecked";
        var B = this[_getChildNodes](_);
        for (var $ = 0,
        A = B.length; $ < A; $++) {
            var _ = B[$];
            if (_.checked === true) return "indeterminate"
        }
        return "unchecked"
    },
    getUnCheckableNodes: function() {
        var $ = [];
        this.cascadeChild(this.root,
        function(A) {
            var _ = this.getCheckable(A);
            if (_ == false) $.push(A)
        },
        this);
        return $
    },
    setCheckable: function(B, _) {
        if (!B) return;
        if (!mini.isArray(B)) B = [B];
        B = B.clone();
        _ = !!_;
        for (var $ = B.length - 1; $ >= 0; $--) {
            var A = this.getRecord(B[$]);
            if (!A) continue;
            A.checkable = checked
        }
    },
    getCheckable: function($) {
        $ = this.getRecord($);
        if (!$) return false;
        if ($.checkable === true) return true;
        if ($.checkable === false) return false;
        return this.isLeafNode($) ? true: !this.onlyLeafCheckable
    },
    showNodeCheckbox: function($, _) {},
    _isNodeLoading: function() {
        return !! this._loadingNode
    },
    loadNode: function(A, $) {
        this._loadingNode = A;
        var C = {
            node: A
        };
        this[_fire]("beforeloadnode", C);
        var _ = new Date(),
        B = this;
        B.OoO0l1Ajax(B.loadParams, null, null, null,
        function(D) {
            var C = new Date() - _;
            if (C < 60) C = 60 - C;
            setTimeout(function() {
                D.node = A;
                B._OnPreLoad(D);
                D.node = B._loadingNode;
                B._loadingNode = null;
                var _ = A[B.nodesField];
                B.removeNodes(_);
                var C = D.data;
                if (C && C.length > 0) {
                    B.addNodes(C, A);
                    if ($ !== false) B[_expand](A, true);
                    else B[_collapse](A, true)
                } else {
                    delete A.isLeaf;
                    B[_expand](A, true)
                }
                B[_fire]("loadnode", D);
                B[_fire]("load", D)
            },
            C)
        },
        true)
    }
});
O1o0(mini.DataTree, "datatree");
mini._DataTableApplys = {
    idField: "id",
    textField: "text",
    setAjaxData: function($) {
        this._dataSource.ajaxData = $
    },
    getby_id: function($) {
        return this._dataSource.getby_id($)
    },
    o00o0: function(_, $) {
        return this._dataSource.o00o0(_, $)
    },
    setIdField: function($) {
        this._dataSource[_setIdField]($);
        this[_idField] = $
    },
    getIdField: function() {
        return this._dataSource[_idField]
    },
    setTextField: function($) {
        this._dataSource[_setTextField]($);
        this[_textField] = $
    },
    getTextField: function() {
        return this._dataSource[_textField]
    },
    clearData: function() {
        this._dataSource[_clearData]()
    },
    loadData: function($) {
        this._dataSource[_loadData]($)
    },
    setData: function($) {
        this._dataSource[_loadData]($)
    },
    getData: function() {
        return this._dataSource.getSource()
    },
    getList: function() {
        return this._dataSource[_getList]()
    },
    getDataView: function() {
        return this._dataSource.getDataView().clone()
    },
    getVisibleRows: function() {
        if (this._useEmptyView) return [];
        return this._dataSource.getVisibleRows()
    },
    toArray: function() {
        return this._dataSource.toArray()
    },
    getRecord: function($) {
        return this._dataSource.getRecord($)
    },
    getRow: function($) {
        return this._dataSource[_getRow]($)
    },
    getRange: function($, _) {
        if (mini.isNull($) || mini.isNull(_)) return;
        return this._dataSource.getRange($, _)
    },
    getAt: function($) {
        return this._dataSource[_getAt]($)
    },
    indexOf: function($) {
        return this._dataSource[_indexOf]($)
    },
    getRowByUID: function($) {
        return this._dataSource.getby_id($)
    },
    getRowById: function($) {
        return this._dataSource.getbyId($)
    },
    clearRows: function() {
        this._dataSource[_clearData]()
    },
    updateRow: function($, A, _) {
        this._dataSource.updateRecord($, A, _)
    },
    addRow: function(_, $) {
        return this._dataSource.insert($, _)
    },
    removeRow: function($, _) {
        return this._dataSource.remove($, _)
    },
    removeRows: function($, _) {
        return this._dataSource.removeRange($, _)
    },
    removeRowAt: function($, _) {
        return this._dataSource.removeAt($, _)
    },
    moveRow: function(_, $) {
        this._dataSource.move(_, $)
    },
    addRows: function(_, $) {
        return this._dataSource.insertRange($, _)
    },
    findRows: function(_, $) {
        return this._dataSource.findRecords(_, $)
    },
    findRow: function(_, $) {
        return this._dataSource.findRecord(_, $)
    },
    multiSelect: false,
    setMultiSelect: function($) {
        this._dataSource[_setMultiSelect]($);
        this[_multiSelect] = $
    },
    getMultiSelect: function() {
        return this._dataSource[_getMultiSelect]()
    },
    setCurrent: function($) {
        this._dataSource[_setCurrent]($)
    },
    getCurrent: function() {
        return this._dataSource.getCurrent()
    },
    isSelected: function($) {
        return this._dataSource[_isSelected]($)
    },
    getSelected: function() {
        return this._dataSource[_getSelected]()
    },
    getSelecteds: function() {
        return this._dataSource[_getSelecteds]()
    },
    select: function($) {
        this._dataSource[_select]($)
    },
    selects: function($) {
        this._dataSource[_selects]($)
    },
    deselect: function($) {
        this._dataSource[_deselect]($)
    },
    deselects: function($) {
        this._dataSource[_deselects]($)
    },
    selectAll: function() {
        this._dataSource[_selectAll]()
    },
    deselectAll: function() {
        this._dataSource[_deselectAll]()
    },
    selectPrev: function() {
        this._dataSource.selectPrev()
    },
    selectNext: function() {
        this._dataSource.selectNext()
    },
    selectFirst: function() {
        this._dataSource.selectFirst()
    },
    selectLast: function() {
        this._dataSource.selectLast()
    },
    selectRange: function($, _) {
        this._dataSource.selectRange($, _)
    },
    filter: function(_, $) {
        this._dataSource.filter(_, $)
    },
    clearFilter: function() {
        this._dataSource.clearFilter()
    },
    sort: function(_, $) {
        this._dataSource.sort(_, $)
    },
    clearSort: function() {
        this._dataSource.clearSort()
    },
    getResultObject: function() {
        return this._dataSource._resultObject || {}
    },
    isChanged: function() {
        return this._dataSource.isChanged()
    },
    getChanges: function($, _) {
        return this._dataSource.getChanges($, _)
    },
    accept: function() {
        this._dataSource.accept()
    },
    reject: function() {
        this._dataSource.reject()
    },
    acceptRecord: function($) {
        this._dataSource.acceptRecord($)
    },
    rejectRecord: function($) {
        this._dataSource.rejectRecord($)
    }
};
mini._DataTreeApplys = {
    addRow: null,
    removeRow: null,
    removeRows: null,
    removeRowAt: null,
    moveRow: null,
    setExpandOnLoad: function($) {
        this._dataSource[_setExpandOnLoad]($)
    },
    getExpandOnLoad: function() {
        return this._dataSource[_getExpandOnLoad]()
    },
    selectNode: function($) {
        if ($) this._dataSource[_select]($);
        else this._dataSource[_deselect](this[_getSelectedNode]())
    },
    getSelectedNode: function() {
        return this[_getSelected]()
    },
    getSelectedNodes: function() {
        return this[_getSelecteds]()
    },
    updateNode: function(_, A, $) {
        this._dataSource.updateRecord(_, A, $)
    },
    addNode: function(A, _, $) {
        return this._dataSource.insertNode(A, _, $)
    },
    removeNodeAt: function($, _) {
        return this._dataSource.removeNodeAt($, _);
        this._changed = true
    },
    removeNode: function($) {
        return this._dataSource[_removeNode]($)
    },
    moveNode: function(A, $, _) {
        this._dataSource.moveNode(A, $, _)
    },
    addNodes: function(A, $, _) {
        return this._dataSource.addNodes(A, $, _)
    },
    insertNodes: function(A, $, _) {
        return this._dataSource.insertNodes($, A, _)
    },
    moveNodes: function(A, _, $) {
        this._dataSource.moveNodes(A, _, $)
    },
    removeNodes: function($) {
        return this._dataSource.removeNodes($)
    },
    expandOnLoad: false,
    checkRecursive: true,
    autoCheckParent: false,
    showFolderCheckBox: true,
    idField: "id",
    textField: "text",
    parentField: "pid",
    nodesField: "children",
    checkedField: "checked",
    resultAsTree: true,
    setShowFolderCheckBox: function($) {
        this._dataSource[_setShowFolderCheckBox]($);
        if (this[_doUpdate]) this[_doUpdate]();
        this[_showFolderCheckBox] = $
    },
    getShowFolderCheckBox: function() {
        return this._dataSource[_getShowFolderCheckBox]()
    },
    setCheckRecursive: function($) {
        this._dataSource[_setCheckRecursive]($);
        this[_checkRecursive] = $
    },
    getCheckRecursive: function() {
        return this._dataSource[_getCheckRecursive]()
    },
    setResultAsTree: function($) {
        this._dataSource[_setResultAsTree]($)
    },
    getResultAsTree: function($) {
        return this._dataSource[_resultAsTree]
    },
    setParentField: function($) {
        this._dataSource[_setParentField]($);
        this[_parentField] = $
    },
    getParentField: function() {
        return this._dataSource[_parentField]
    },
    setNodesField: function($) {
        this._dataSource[_setNodesField]($);
        this.nodesField = $
    },
    getNodesField: function() {
        return this._dataSource.nodesField
    },
    setCheckedField: function($) {
        this._dataSource.checkedField = $;
        this.checkedField = $
    },
    getCheckedField: function() {
        return this.checkedField
    },
    findNodes: function(_, $) {
        return this._dataSource.findRecords(_, $)
    },
    getLevel: function($) {
        return this._dataSource.getLevel($)
    },
    isVisibleNode: function($) {
        return this._dataSource.isVisibleNode($)
    },
    isExpandedNode: function($) {
        return this._dataSource.isExpandedNode($)
    },
    isCheckedNode: function($) {
        return this._dataSource.isCheckedNode($)
    },
    isLeaf: function($) {
        return this._dataSource.isLeafNode($)
    },
    hasChildren: function($) {
        return this._dataSource.hasChildren($)
    },
    isAncestor: function(_, $) {
        return this._dataSource.isAncestor(_, $)
    },
    getNode: function($) {
        return this._dataSource.getRecord($)
    },
    getRootNode: function() {
        return this._dataSource.getRootNode()
    },
    getParentNode: function($) {
        return this._dataSource[_getParentNode].apply(this._dataSource, arguments)
    },
    getAncestors: function($) {
        return this._dataSource[_getAncestors]($)
    },
    getAllChildNodes: function($) {
        return this._dataSource.getAllChildNodes.apply(this._dataSource, arguments)
    },
    getChildNodes: function($, _) {
        return this._dataSource[_getChildNodes].apply(this._dataSource, arguments)
    },
    getChildNodeAt: function($, _) {
        return this._dataSource.getChildNodeAt.apply(this._dataSource, arguments)
    },
    indexOfNode: function($) {
        return this._dataSource.indexOfNode.apply(this._dataSource, arguments)
    },
    hasChildNodes: function($) {
        return this._dataSource.hasChildNodes.apply(this._dataSource, arguments)
    },
    isFirstNode: function($) {
        return this._dataSource[_isFirstNode].apply(this._dataSource, arguments)
    },
    isLastNode: function($) {
        return this._dataSource.isLastNode.apply(this._dataSource, arguments)
    },
    getNextNode: function($) {
        return this._dataSource.getNextNode.apply(this._dataSource, arguments)
    },
    getPrevNode: function($) {
        return this._dataSource.getPrevNode.apply(this._dataSource, arguments)
    },
    getFirstNode: function($) {
        return this._dataSource.getFirstNode.apply(this._dataSource, arguments)
    },
    getLastNode: function($) {
        return this._dataSource.getLastNode.apply(this._dataSource, arguments)
    },
    toggleNode: function($) {
        this._dataSource[_toggle]($)
    },
    collapseNode: function($, _) {
        this._dataSource[_collapse]($, _)
    },
    expandNode: function($, _) {
        this._dataSource[_expand]($, _)
    },
    collapseAll: function() {
        this._dataSource.collapseAll()
    },
    expandAll: function() {
        this._dataSource.expandAll()
    },
    expandLevel: function($) {
        this._dataSource.expandLevel($)
    },
    collapseLevel: function($) {
        this._dataSource.collapseLevel($)
    },
    expandPath: function($) {
        this._dataSource[_expandPath]($)
    },
    collapsePath: function($) {
        this._dataSource.collapsePath($)
    },
    loadNode: function($, _) {
        this._dataSource.loadNode($, _)
    },
    setCheckModel: function($) {
        this._dataSource.setCheckModel($)
    },
    getCheckModel: function() {
        return this._dataSource.getCheckModel()
    },
    setOnlyLeafCheckable: function($) {
        this._dataSource.setOnlyLeafCheckable($)
    },
    getOnlyLeafCheckable: function() {
        return this._dataSource.getOnlyLeafCheckable()
    },
    setAutoCheckParent: function($) {
        this._dataSource[_setAutoCheckParent]($)
    },
    getAutoCheckParent: function() {
        return this._dataSource[_getAutoCheckParent]()
    },
    checkNode: function($) {
        this._dataSource.checkNode($)
    },
    uncheckNode: function($) {
        this._dataSource.uncheckNode($)
    },
    checkNodes: function($) {
        this._dataSource.checkNodes($)
    },
    uncheckNodes: function($) {
        this._dataSource.uncheckNodes($)
    },
    checkAllNodes: function() {
        this._dataSource.checkAllNodes()
    },
    uncheckAllNodes: function() {
        this._dataSource.uncheckAllNodes()
    },
    getCheckedNodes: function() {
        return this._dataSource[_getCheckedNodes].apply(this._dataSource, arguments)
    },
    getCheckedNodesId: function() {
        return this._dataSource.getCheckedNodesId.apply(this._dataSource, arguments)
    },
    getCheckedNodesText: function() {
        return this._dataSource.getCheckedNodesText.apply(this._dataSource, arguments)
    },
    isChecked: function($) {
        return this._dataSource.isChecked.apply(this._dataSource, arguments)
    },
    getCheckState: function($) {
        return this._dataSource.getCheckState.apply(this._dataSource, arguments)
    },
    setCheckable: function(_, $) {
        this._dataSource.setCheckable.apply(this._dataSource, arguments)
    },
    getCheckable: function($) {
        return this._dataSource.getCheckable.apply(this._dataSource, arguments)
    },
    bubbleParent: function($, A, _) {
        this._dataSource.bubbleParent.apply(this._dataSource, arguments)
    },
    cascadeChild: function($, A, _) {
        this._dataSource.cascadeChild.apply(this._dataSource, arguments)
    },
    eachChild: function($, A, _) {
        this._dataSource.eachChild.apply(this._dataSource, arguments)
    }
};
mini.ColumnModel = function($) {
    this.owner = $;
    mini.ColumnModel[_superclass][_constructor][_call](this);
    this._init()
};
mini.ColumnModel_ColumnID = 1;
lol01(mini.ColumnModel, o111oO, {
    _defaultColumnWidth: 100,
    _init: function() {
        this.columns = [];
        this._columnsRow = [];
        this._visibleColumnsRow = [];
        this.lo0Ol = [];
        this._visibleColumns = [];
        this.lOoO = {};
        this.O1lO = {};
        this._fieldColumns = {}
    },
    getColumns: function() {
        return this.columns
    },
    getAllColumns: function() {
        var _ = [];
        for (var A in this.lOoO) {
            var $ = this.lOoO[A];
            _.push($)
        }
        return _
    },
    getColumnsRow: function() {
        return this._columnsRow
    },
    getVisibleColumnsRow: function() {
        return this._visibleColumnsRow
    },
    getBottomColumns: function() {
        return this.lo0Ol
    },
    getVisibleColumns: function() {
        return this._visibleColumns
    },
    _getBottomColumnsByColumn: function(A) {
        A = this[_getColumn](A);
        var C = this.lo0Ol,
        B = [];
        for (var $ = 0,
        D = C.length; $ < D; $++) {
            var _ = C[$];
            if (this[_isAncestorColumn](A, _)) B.push(_)
        }
        return B
    },
    _getVisibleColumnsByColumn: function(A) {
        A = this[_getColumn](A);
        var C = this._visibleColumns,
        B = [];
        for (var $ = 0,
        D = C.length; $ < D; $++) {
            var _ = C[$];
            if (this[_isAncestorColumn](A, _)) B.push(_)
        }
        return B
    },
    setColumns: function($) {
        if (!mini.isArray($)) $ = [];
        this._init();
        this.columns = $;
        this._columnsChanged()
    },
    _columnsChanged: function() {
        this._updateColumnsView();
        this[_fire]("columnschanged")
    },
    _updateColumnsView: function() {
        this._maxColumnLevel = 0;
        var level = 0;
        function init(column, index, parentColumn) {
            if (column.type) {
                if (!mini.isNull(column.header) && typeof column.header !== "function") if (column.header.trim() == "") delete column.header;
                var col = mini[_getColumn](column.type);
                if (col) {
                    var _column = mini.copyTo({},
                    column);
                    mini.copyTo(column, col);
                    mini.copyTo(column, _column)
                }
            }
            column._id = mini.ColumnModel_ColumnID++;
            column._pid = parentColumn == this ? -1 : parentColumn._id;
            this.lOoO[column._id] = column;
            if (column.name) this.O1lO[column.name] = column;
            column._level = level;
            level += 1;
            this[_eachColumns](column, init, this);
            level -= 1;
            if (column._level > this._maxColumnLevel) this._maxColumnLevel = column._level;
            var width = parseInt(column.width);
            if (mini.isNumber(width) && String(width) == column.width) column.width = width + "px";
            if (mini.isNull(column.width)) column.width = this._defaultColumnWidth + "px";
            column.visible = column.visible !== false;
            column[_allowResize] = column[_allowResize] !== false;
            column.allowMove = column.allowMove !== false;
            column.allowSort = column.allowSort === true;
            column.allowDrag = !!column.allowDrag;
            column[_readOnly] = !!column[_readOnly];
            column.autoEscape = !!column.autoEscape;
            column.vtype = column.vtype || "";
            if (typeof column.filter == "string") column.filter = eval("(" + column.filter + ")");
            if (column.filter && !column.filter.el) column.filter = mini.create(column.filter);
            if (typeof column.init == "function" && column.inited != true) column.init(this.owner);
            column.inited = true;
            column._gridUID = this.owner.uid;
            column[_rowIdField] = this.owner[_rowIdField]
        }
        this[_eachColumns](this, init, this);
        this._createColumnsRow();
        var index = 0,
        view = this._visibleColumns = [],
        bottoms = this.lo0Ol = [];
        this.cascadeColumns(this,
        function($) {
            if (!$.columns || $.columns.length == 0) {
                bottoms.push($);
                if (this[_isVisibleColumn]($)) {
                    view.push($);
                    $._index = index++
                }
            }
        },
        this);
        this._fieldColumns = {};
        var columns = this.getAllColumns();
        for (var i = 0,
        l = columns.length; i < l; i++) {
            var column = columns[i];
            if (column.field && !this._fieldColumns[column.field]) this._fieldColumns[column.field] = column
        }
        this._createFrozenColSpan()
    },
    _frozenStartColumn: -1,
    _frozenEndColumn: -1,
    isFrozen: function() {
        return this._frozenStartColumn >= 0 && this._frozenEndColumn >= this._frozenStartColumn
    },
    isFrozenColumn: function(_) {
        if (!this[_isFrozen]()) return false;
        _ = this[_getColumn](_);
        if (!_) return false;
        var $ = this.getVisibleColumns()[_indexOf](_);
        return this._frozenStartColumn <= $ && $ <= this._frozenEndColumn
    },
    frozen: function($, _) {
        $ = this[_getColumn]($);
        _ = this[_getColumn](_);
        var A = this.getVisibleColumns();
        this._frozenStartColumn = A[_indexOf]($);
        this._frozenEndColumn = A[_indexOf](_);
        if ($ && _) this._columnsChanged()
    },
    unFrozen: function() {
        this._frozenStartColumn = -1;
        this._frozenEndColumn = -1;
        this._columnsChanged()
    },
    setFrozenStartColumn: function($) {
        this.frozen($, this._frozenEndColumn)
    },
    setFrozenEndColumn: function($) {
        this.frozen(this._frozenStartColumn, $)
    },
    getFrozenColumns: function() {
        var A = [],
        _ = this[_isFrozen]();
        for (var $ = 0,
        B = this._visibleColumns.length; $ < B; $++) if (_ && this._frozenStartColumn <= $ && $ <= this._frozenEndColumn) A.push(this._visibleColumns[$]);
        return A
    },
    getUnFrozenColumns: function() {
        var A = [],
        _ = this[_isFrozen]();
        for (var $ = 0,
        B = this._visibleColumns.length; $ < B; $++) if ((_ && $ > this._frozenEndColumn) || !_) A.push(this._visibleColumns[$]);
        return A
    },
    getFrozenColumnsRow: function() {
        return this[_isFrozen]() ? this._columnsRow1: []
    },
    getUnFrozenColumnsRow: function() {
        return this[_isFrozen]() ? this._columnsRow2: this.getVisibleColumnsRow()
    },
    _createFrozenColSpan: function() {
        var G = this,
        N = this.getVisibleColumns(),
        B = this._frozenStartColumn,
        D = this._frozenEndColumn;
        function F(E, C) {
            var F = G.isBottomColumn(E) ? [E] : G._getVisibleColumnsByColumn(E);
            for (var _ = 0,
            H = F.length; _ < H; _++) {
                var A = F[_],
                $ = N[_indexOf](A);
                if (C == 0 && $ < B) return true;
                if (C == 1 && B <= $ && $ <= D) return true;
                if (C == 2 && $ > D) return true
            }
            return false
        }
        function _(D, A) {
            var E = mini.treeToList(D.columns, "columns"),
            B = 0;
            for (var $ = 0,
            C = E.length; $ < C; $++) {
                var _ = E[$];
                if (G[_isVisibleColumn](_) == false || F(_, A) == false) continue;
                if (!_.columns || _.columns.length == 0) B += 1
            }
            return B
        }
        var $ = mini.treeToList(this.columns, "columns");
        for (var K = 0,
        I = $.length; K < I; K++) {
            var E = $[K];
            delete E.colspan0;
            delete E.colspan1;
            delete E.colspan2;
            delete E.viewIndex0;
            delete E.viewIndex1;
            delete E.viewIndex2;
            if (this[_isFrozen]()) {
                if (E.columns && E.columns.length > 0) {
                    E.colspan1 = _(E, 1);
                    E.colspan2 = _(E, 2);
                    E.colspan0 = _(E, 0)
                } else {
                    E.colspan1 = 1;
                    E.colspan2 = 1;
                    E.colspan0 = 1
                }
                if (F(E, 0)) E["viewIndex" + 0] = true;
                if (F(E, 1)) E["viewIndex" + 1] = true;
                if (F(E, 2)) E["viewIndex" + 2] = true
            }
        }
        var J = this._getMaxColumnLevel();
        this._columnsRow1 = [];
        this._columnsRow2 = [];
        for (K = 0, I = this._visibleColumnsRow.length; K < I; K++) {
            var H = this._visibleColumnsRow[K],
            L = [],
            O = [];
            this._columnsRow1.push(L);
            this._columnsRow2.push(O);
            for (var M = 0,
            A = H.length; M < A; M++) {
                var C = H[M];
                if (C.viewIndex1) L.push(C);
                if (C.viewIndex2) O.push(C)
            }
        }
    },
    _createColumnsRow: function() {
        var _ = this._getMaxColumnLevel(),
        F = [],
        D = [];
        for (var C = 0,
        H = _; C <= H; C++) {
            F.push([]);
            D.push([])
        }
        var G = this;
        function A(C) {
            var D = mini.treeToList(C.columns, "columns"),
            A = 0;
            for (var $ = 0,
            B = D.length; $ < B; $++) {
                var _ = D[$];
                if (G[_isVisibleColumn](_) == false) continue;
                if (!_.columns || _.columns.length == 0) A += 1
            }
            return A
        }
        var $ = mini.treeToList(this.columns, "columns");
        for (C = 0, H = $.length; C < H; C++) {
            var E = $[C],
            B = F[E._level],
            I = D[E._level];
            delete E.rowspan;
            delete E.colspan;
            if (E.columns && E.columns.length > 0) E.colspan = A(E);
            if ((!E.columns || E.columns.length == 0) && E._level < _) E.rowspan = _ - E._level + 1;
            B.push(E);
            if (this[_isVisibleColumn](E)) I.push(E)
        }
        this._columnsRow = F;
        this._visibleColumnsRow = D
    },
    _getMaxColumnLevel: function() {
        return this._maxColumnLevel
    },
    cascadeColumns: function(A, E, B) {
        if (!E) return;
        var D = A.columns;
        if (D) {
            D = D.clone();
            for (var $ = 0,
            C = D.length; $ < C; $++) {
                var _ = D[$];
                if (E[_call](B || this, _, $, A) === false) return;
                this.cascadeColumns(_, E, B)
            }
        }
    },
    eachColumns: function(B, F, C) {
        var D = B.columns;
        if (D) {
            var _ = D.clone();
            for (var A = 0,
            E = _.length; A < E; A++) {
                var $ = _[A];
                if (F[_call](C, $, A, B) === false) break
            }
        }
    },
    getColumn: function($) {
        var _ = typeof $;
        if (_ == "number") return this.lo0Ol[$];
        else if (_ == "object") return $;
        else return this.O1lO[$]
    },
    getColumnByField: function($) {
        if (!$) return null;
        return this._fieldColumns[$]
    },
    ool0: function($) {
        return this.lOoO[$]
    },
    _getDataTypeByField: function(A) {
        var C = "string",
        B = this[_getBottomColumns]();
        for (var $ = 0,
        D = B.length; $ < D; $++) {
            var _ = B[$];
            if (_.field == A) {
                if (_.dataType) C = _.dataType.toLowerCase();
                break
            }
        }
        return C
    },
    getParentColumn: function($) {
        $ = this[_getColumn]($);
        var _ = $._pid;
        if (_ == -1) return this;
        return this.lOoO[_]
    },
    getAncestorColumns: function(A) {
        var _ = [A];
        while (1) {
            var $ = this[_getParentColumn](A);
            if (!$ || $ == this) break;
            _[_.length] = $;
            A = $
        }
        _.reverse();
        return _
    },
    isAncestorColumn: function(_, B) {
        if (_ == B) return true;
        if (!_ || !B) return false;
        var A = this[_getAncestorColumns](B);
        for (var $ = 0,
        C = A.length; $ < C; $++) if (A[$] == _) return true;
        return false
    },
    isVisibleColumn: function(_) {
        _ = this[_getColumn](_);
        var A = this[_getAncestorColumns](_);
        for (var $ = 0,
        B = A.length; $ < B; $++) if (A[$].visible == false) return false;
        return true
    },
    isBottomColumn: function($) {
        $ = this[_getColumn]($);
        return ! ($.columns && $.columns.length > 0)
    },
    updateColumn: function($, _) {
        $ = this[_getColumn]($);
        if (!$) return;
        mini.copyTo($, _);
        this._columnsChanged()
    },
    moveColumn: function(C, _, A) {
        C = this[_getColumn](C);
        _ = this[_getColumn](_);
        if (!C || !_ || !A || C == _) return;
        if (this[_isAncestorColumn](C, _)) return;
        var D = this[_getParentColumn](C);
        if (D) D.columns.remove(C);
        var B = _,
        $ = A;
        if ($ == "before") {
            B = this[_getParentColumn](_);
            $ = B.columns[_indexOf](_)
        } else if ($ == "after") {
            B = this[_getParentColumn](_);
            $ = B.columns[_indexOf](_) + 1
        } else if ($ == "add" || $ == "append") {
            if (!B.columns) B.columns = [];
            $ = B.columns.length
        } else if (!mini.isNumber($)) return;
        B.columns.insert($, C);
        this._columnsChanged()
    },
    addColumn: function() {
        this._columnsChanged()
    },
    removeColumn: function() {
        this._columnsChanged()
    }
});
mini.GridView = function() {
    this._createTime = new Date();
    this._createColumnModel();
    this._bindColumnModel();
    this.data = [];
    this[_createSource]();
    this.oO0O();
    this[_initData]();
    mini.GridView[_superclass][_constructor][_call](this);
    this.oooO();
    this.o1111o();
    this[_doUpdate]()
};
lol01(mini.GridView, ll00Oo, {
    oO00l: "block",
    _rowIdField: "_id",
    width: "100%",
    showColumns: true,
    showFilterRow: false,
    showSummaryRow: false,
    showPager: false,
    allowCellWrap: false,
    allowHeaderCellWrap: false,
    showModified: true,
    showNewRow: true,
    showEmptyText: false,
    emptyText: "No data returned.",
    showHGridLines: true,
    showVGridLines: true,
    allowAlternating: false,
    OOlo0O: "mini-grid-row-alt",
    Ol11OO: "mini-grid-row",
    _cellCls: "mini-grid-cell",
    _headerCellCls: "mini-grid-headerCell",
    l1O0: "mini-grid-row-selected",
    ol1O0: "mini-grid-row-hover",
    oo0ll: "mini-grid-cell-selected",
    defaultRowHeight: 21,
    fixedRowHeight: false,
    isFixedRowHeight: function() {
        return this.fixedRowHeight
    },
    fitColumns: true,
    isFitColumns: function() {
        return this.fitColumns
    },
    uiCls: "mini-gridview",
    _create: function() {
        mini.GridView[_superclass][_create][_call](this);
        var A = this.el;
        o01O(A, "mini-grid");
        o01O(this.lO0ll, "mini-grid-border");
        o01O(this.OoOoll, "mini-grid-viewport");
        var C = "<div class=\"mini-grid-pager\"></div>",
        $ = "<div class=\"mini-grid-filterRow\"><div class=\"mini-grid-filterRow-view\"></div><div class=\"mini-grid-scrollHeaderCell\"></div></div>",
        _ = "<div class=\"mini-grid-summaryRow\"><div class=\"mini-grid-summaryRow-view\"></div><div class=\"mini-grid-scrollHeaderCell\"></div></div>",
        B = "<div class=\"mini-grid-columns\"><div class=\"mini-grid-columns-view\"></div><div class=\"mini-grid-scrollHeaderCell\"></div></div>";
        this._columnsEl = mini.after(this.llol1, B);
        this.O0loll = mini.after(this._columnsEl, $);
        this._rowsEl = this.oOo1o0;
        o01O(this._rowsEl, "mini-grid-rows");
        this.l011l = mini.after(this._rowsEl, _);
        this._bottomPagerEl = mini.after(this.l011l, C);
        this._columnsViewEl = this._columnsEl.childNodes[0];
        this._topRightCellEl = this._columnsEl.childNodes[1];
        this._rowsViewEl = mini.append(this._rowsEl, "<div class=\"mini-grid-rows-view\"><div class=\"mini-grid-rows-content\"></div></div>");
        this._rowsViewContentEl = this._rowsViewEl.firstChild;
        this._filterViewEl = this.O0loll.childNodes[0];
        this._summaryViewEl = this.l011l.childNodes[0];
        var D = "<a href=\"#\" class=\"mini-grid-focus\" style=\"position:absolute;left:-10px;top:-10px;width:0px;height:0px;outline:none;\" hideFocus onclick=\"return false\" ></a>";
        this._focusEl = mini.append(this.lO0ll, D)
    },
    _initEvents: function() {
        mini.GridView[_superclass][_initEvents][_call](this);
        lo1l1o(this._rowsViewEl, "scroll", this.__OnRowViewScroll, this)
    },
    _setBodyWidth: false,
    doLayout: function() {
        if (!this[_canLayout]()) return;
        mini.GridView[_superclass][_doLayout][_call](this);
        this[_stopLayout]();
        var C = this[_isAutoHeight](),
        B = this._columnsViewEl.firstChild,
        A = this._rowsViewContentEl.firstChild,
        _ = this._filterViewEl.firstChild,
        $ = this._summaryViewEl.firstChild;
        function E($) {
            if (this.isFitColumns()) {
                A.style.width = "100%";
                if (mini.isChrome || mini.isIE6) $.style.width = A.offsetWidth + "px";
                else if (this._rowsViewEl.scrollHeight > this._rowsViewEl.clientHeight) {
                    $.style.width = "100%";
                    $.parentNode.style.width = "auto";
                    $.parentNode.style["paddingRight"] = "17px"
                } else {
                    $.style.width = "100%";
                    $.parentNode.style.width = "100%";
                    $.parentNode.style["paddingRight"] = "0px"
                }
            } else {
                A.style.width = "0px";
                $.style.width = "0px";
                if (mini.isChrome || mini.isIE6);
                else {
                    $.parentNode.style.width = "100%";
                    $.parentNode.style["paddingRight"] = "0px"
                }
            }
        }
        E[_call](this, B);
        E[_call](this, _);
        E[_call](this, $);
        this._syncScroll();
        var D = this;
        setTimeout(function() {
            mini.layout(D.O0loll);
            mini.layout(D.l011l)
        },
        10)
    },
    setBody: function() {},
    _createTopRowHTML: function(B) {
        var E = "";
        if (mini.isIE) {
            if (mini.isIE6 || mini.isIE7 || !mini.boxModel) E += "<tr style=\"display:none;height:0px;\">";
            else E += "<tr style=\"height:0px;\">"
        } else E += "<tr>";
        for (var $ = 0,
        C = B.length; $ < C; $++) {
            var A = B[$],
            _ = A.width,
            D = A._id;
            E += "<td id=\"" + D + "\" style=\"padding:0;border:0;margin:0;height:0px;";
            if (A.width) E += "width:" + A.width;
            E += "\" ></td>"
        }
        E += "<td style=\"width:0px;\"></td>";
        E += "</tr>";
        return E
    },
    _createColumnsHTML: function(A, K, O) {
        var O = O ? O: this.getVisibleColumns(),
        H = ["<table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"];
        H.push(this._createTopRowHTML(O));
        var M = this[_getSortField](),
        E = this[_getSortOrder]();
        for (var L = 0,
        _ = A.length; L < _; L++) {
            var F = A[L];
            H[H.length] = "<tr>";
            for (var I = 0,
            G = F.length; I < G; I++) {
                var C = F[I],
                N = this.o1oloOText(C, K),
                J = this.lO1ol1Id(C, K),
                $ = "";
                if (M && M == C.field) $ = E == "asc" ? "mini-grid-asc": "mini-grid-desc";
                var D = "";
                if (this.allowHeaderCellWrap == false) D = " mini-grid-headerCell-nowrap ";
                H[H.length] = "<td id=\"";
                H[H.length] = J;
                H[H.length] = "\" class=\"mini-grid-headerCell " + $ + " " + (C.headerCls || "") + " ";
                var B = !(C.columns && C.columns.length > 0);
                if (B) H[H.length] = " mini-grid-bottomCell ";
                if (I == G - 1) H[H.length] = " mini-grid-rightCell ";
                H[H.length] = "\" style=\"";
                if (C.headerStyle) H[H.length] = C.headerStyle + ";";
                if (C.headerAlign) H[H.length] = "text-align:" + C.headerAlign + ";";
                H[H.length] = "\" ";
                if (C.rowspan) H[H.length] = "rowspan=\"" + C.rowspan + "\" ";
                this._createColumnColSpan(C, H, K);
                H[H.length] = "><div class=\"mini-grid-headerCell-inner " + D + "\">";
                H[H.length] = N;
                if ($) H[H.length] = "<span class=\"mini-grid-sortIcon\"></span>";
                H[H.length] = "<div id=\"" + C._id + "\" class=\"mini-grid-column-splitter\"></div>";
                H[H.length] = "</div></td>"
            }
            if (this[_isFrozen]() && K == 1) {
                H[H.length] = "<td class=\"mini-grid-headerCell\" style=\"width:0;\"><div class=\"mini-grid-headerCell-inner\" style=\"";
                H[H.length] = "\">0</div></td>"
            }
            H[H.length] = "</tr>"
        }
        H.push("</table>");
        return H.join("")
    },
    o1oloOText: function(_, $) {
        var A = _.header;
        if (typeof A == "function") A = A[_call](this, _);
        if (mini.isNull(A) || A === "") A = "&nbsp;";
        return A
    },
    _createColumnColSpan: function(_, A, $) {
        if (_.colspan) A[A.length] = "colspan=\"" + _.colspan + "\" "
    },
    doUpdateColumns: function() {
        var A = this._columnsViewEl.scrollLeft,
        _ = this.getVisibleColumnsRow(),
        $ = this._createColumnsHTML(_, 2),
        B = "<div class=\"mini-grid-topRightCell\"></div>";
        $ += B;
        this._columnsViewEl.innerHTML = $;
        this._columnsViewEl.scrollLeft = A
    },
    doUpdate: function() {
        if (this.canUpdate() == false) return;
        var B = this._isCreating(),
        _ = new Date();
        this.o1111o();
        var A = this;
        function $() {
            A.doUpdateColumns();
            A.doUpdateRows();
            A[_doLayout]();
            A._doUpdateTimer = null
        }
        A.doUpdateColumns();
        if (B) this._useEmptyView = true;
        if (this._rowsViewContentEl && this._rowsViewContentEl.firstChild) this._rowsViewContentEl.removeChild(this._rowsViewContentEl.firstChild);
        if (this._rowsLockContentEl && this._rowsLockContentEl.firstChild) this._rowsLockContentEl.removeChild(this._rowsLockContentEl.firstChild);
        A.doUpdateRows();
        if (B) this._useEmptyView = false;
        A[_doLayout]();
        if (B && !this._doUpdateTimer) this._doUpdateTimer = setTimeout($, 15);
        this[_unmask]()
    },
    _isCreating: function() {
        return (new Date() - this._createTime) < 1000
    },
    deferUpdate: function($) {
        if (!$) $ = 5;
        if (this._updateTimer || this._doUpdateTimer) return;
        var _ = this;
        this._updateTimer = setTimeout(function() {
            _._updateTimer = null;
            _[_doUpdate]()
        },
        $)
    },
    _updateCount: 0,
    beginUpdate: function() {
        this._updateCount++
    },
    endUpdate: function($) {
        this._updateCount--;
        if (this._updateCount == 0 || $ === true) {
            this._updateCount = 0;
            this[_doUpdate]()
        }
    },
    canUpdate: function() {
        return this._updateCount == 0
    },
    _getRowHeight: function($) {
        var _ = this.defaultRowHeight;
        if ($._height) {
            _ = parseInt($._height);
            if (isNaN(parseInt($._height))) _ = rowHeight
        }
        _ -= 4;
        _ -= 1;
        return _
    },
    _createGroupingHTML: function(C, H) {
        var G = this.getGroupingView(),
        A = this._showGroupSummary,
        L = this[_isFrozen](),
        M = 0,
        D = this;
        function N(F, _) {
            E.push("<table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">");
            if (C.length > 0) {
                E.push(D._createTopRowHTML(C));
                for (var G = 0,
                $ = F.length; G < $; G++) {
                    var B = F[G];
                    D.o1o1lHTML(B, M++, C, H, E)
                }
            }
            if (A);
            E.push("</table>")
        }
        var E = ["<table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"];
        E.push(this._createTopRowHTML(C));
        for (var K = 0,
        $ = G.length; K < $; K++) {
            var _ = G[K],
            F = this.o1o1lGroupId(_, H),
            I = this.o1o1lGroupRowsId(_, H),
            O = this.Ooll11(_),
            B = _.expanded ? "": " mini-grid-group-collapse ";
            E[E.length] = "<tr id=\"";
            E[E.length] = F;
            E[E.length] = "\" class=\"mini-grid-groupRow";
            E[E.length] = B;
            E[E.length] = "\"><td class=\"mini-grid-groupCell\" colspan=\"";
            E[E.length] = C.length;
            E[E.length] = "\"><div class=\"mini-grid-groupHeader\">";
            if (!L || (L && H == 1)) {
                E[E.length] = "<div class=\"mini-grid-group-ecicon\"></div>";
                E[E.length] = "<div class=\"mini-grid-groupTitle\">" + O.cellHtml + "</div>"
            } else E[E.length] = "&nbsp;";
            E[E.length] = "</div></td></tr>";
            var J = _.expanded ? "": "display:none";
            E[E.length] = "<tr class=\"mini-grid-groupRows-tr\" style=\"";
            E[E.length] = "\"><td class=\"mini-grid-groupRows-td\" colspan=\"";
            E[E.length] = C.length;
            E[E.length] = "\"><div id=\"";
            E[E.length] = I;
            E[E.length] = "\" class=\"mini-grid-groupRows\" style=\"";
            E[E.length] = J;
            E[E.length] = "\">";
            N(_.rows, _);
            E[E.length] = "</div></td></tr>"
        }
        E.push("</table>");
        return E.join("")
    },
    _isFastCreating: function() {
        var $ = this.getVisibleRows();
        if ($.length > 50) return this._isCreating() || this.getScrollTop() < 50 * this._defaultRowHeight;
        return false
    },
    o1o1lHTML: function($, Q, E, N, H) {
        var R = !H;
        if (!H) H = [];
        var B = "",
        _ = this.isFixedRowHeight();
        if (_) B = this[_getRowHeight]($);
        var K = -1,
        L = " ",
        I = -1,
        M = " ";
        H[H.length] = "<tr class=\"mini-grid-row ";
        if ($._state == "added" && this.showNewRow) H[H.length] = "mini-grid-newRow ";
        if (this[_allowAlternating] && Q % 2 == 1) {
            H[H.length] = this.OOlo0O;
            H[H.length] = " "
        }
        var D = this._dataSource[_isSelected]($);
        if (D) {
            H[H.length] = this.l1O0;
            H[H.length] = " "
        }
        K = H.length;
        H[H.length] = L;
        H[H.length] = "\" style=\"";
        I = H.length;
        H[H.length] = M;
        if ($.visible === false) H[H.length] = ";display:none;";
        H[H.length] = "\" id=\"";
        H[H.length] = this.ol00l($, N);
        H[H.length] = "\">";
        var O = this.loo0l1;
        for (var J = 0,
        F = E.length; J < F; J++) {
            var A = E[J],
            G = this.llO1O($, A),
            C = "",
            S = this.OollO($, A, Q, A._index);
            if (S.cellHtml === null || S.cellHtml === undefined || S.cellHtml === "") S.cellHtml = "&nbsp;";
            H[H.length] = "<td ";
            if (S.rowSpan) H[H.length] = "rowspan=\"" + S.rowSpan + "\"";
            if (S.colSpan) H[H.length] = "colspan=\"" + S.colSpan + "\"";
            H[H.length] = " id=\"";
            H[H.length] = G;
            H[H.length] = "\" class=\"mini-grid-cell ";
            if (J == F - 1) H[H.length] = " mini-grid-rightCell ";
            if (S.cellCls) H[H.length] = " " + S.cellCls + " ";
            if (C) H[H.length] = C;
            if (O && O[0] == $ && O[1] == A) {
                H[H.length] = " ";
                H[H.length] = this.oo0ll
            }
            H[H.length] = "\" style=\"";
            if (S[_showHGridLines] == false) H[H.length] = "border-bottom:0;";
            if (S[_showVGridLines] == false) H[H.length] = "border-right:0;";
            if (!S.visible) H[H.length] = "display:none;";
            if (A.align) {
                H[H.length] = "text-align:";
                H[H.length] = A.align;
                H[H.length] = ";"
            }
            if (S.cellStyle) H[H.length] = S.cellStyle;
            H[H.length] = "\">";
            H[H.length] = "<div class=\"mini-grid-cell-inner ";
            if (!S.allowCellWrap) H[H.length] = " mini-grid-cell-nowrap ";
            if (S.cellInnerCls) H[H.length] = S.cellInnerCls;
            var P = A.field ? this._dataSource.isModified($, A.field) : false;
            if (P && this.showModified) H[H.length] = " mini-grid-cell-dirty";
            H[H.length] = "\" style=\"";
            if (_) {
                H[H.length] = "height:";
                H[H.length] = B;
                H[H.length] = "px;"
            }
            if (S.cellInnerStyle) H[H.length] = S.cellInnerStyle;
            H[H.length] = "\">";
            H[H.length] = S.cellHtml;
            H[H.length] = "</div>";
            H[H.length] = "</td>";
            if (S.rowCls) L = S.rowCls;
            if (S.rowStyle) M = S.rowStyle
        }
        if (this[_isFrozen]() && N == 1) {
            H[H.length] = "<td class=\"mini-grid-cell\" style=\"width:0;";
            if (this[_showHGridLines] == false) H[H.length] = "border-bottom:0;";
            H[H.length] = "\"><div class=\"mini-grid-cell-inner\" style=\"";
            if (_) {
                H[H.length] = "height:";
                H[H.length] = B;
                H[H.length] = "px;"
            }
            H[H.length] = "\">0</div></td>"
        }
        H[K] = L;
        H[I] = M;
        H[H.length] = "</tr>";
        if (R) return H.join("")
    },
    o1o1lsHTML: function(B, F, G, E) {
        G = G || this.getVisibleRows();
        var C = ["<table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"];
        C.push(this._createTopRowHTML(B));
        var I = this.uid + "$emptytext" + F;
        C.push("<tr id=\"" + I + "\" style=\"display:none;\"><td class=\"mini-grid-emptyText\" colspan=\"" + B.length + "\">" + this[_emptyText] + "</td></tr>");
        var D = 0;
        if (G.length > 0) {
            var A = G[0];
            D = this.getVisibleRows()[_indexOf](A)
        }
        for (var H = 0,
        _ = G.length; H < _; H++) {
            var J = D + H,
            $ = G[H];
            this.o1o1lHTML($, J, B, F, C)
        }
        if (E) C.push(E);
        C.push("</table>");
        return C.join("")
    },
    doUpdateRows: function() {
        var _ = this.getVisibleRows(),
        A = this.getVisibleColumns();
        if (this[_isGrouping]()) {
            var $ = this._createGroupingHTML(A, 2);
            this._rowsViewContentEl.innerHTML = $
        } else {
            $ = this.o1o1lsHTML(A, 2, _);
            this._rowsViewContentEl.innerHTML = $
        }
    },
    _createFilterRowHTML: function(B, _) {
        var F = ["<table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"];
        F.push(this._createTopRowHTML(B));
        F[F.length] = "<tr>";
        for (var $ = 0,
        C = B.length; $ < C; $++) {
            var A = B[$],
            E = this.OOll(A);
            F[F.length] = "<td id=\"";
            F[F.length] = E;
            F[F.length] = "\" class=\"mini-grid-filterCell\" style=\"";
            F[F.length] = "\">&nbsp;</td>"
        }
        F[F.length] = "</tr></table><div class=\"mini-grid-scrollHeaderCell\"></div>";
        var D = F.join("");
        return D
    },
    _doRenderFilters: function() {
        var B = this.getVisibleColumns();
        for (var $ = 0,
        C = B.length; $ < C; $++) {
            var A = B[$];
            if (A.filter) {
                var _ = this.getFilterCellEl($);
                _.innerHTML = "";
                A.filter[_render](_)
            }
        }
    },
    oooO: function() {
        if (this._filterViewEl.firstChild) this._filterViewEl.removeChild(this._filterViewEl.firstChild);
        var _ = this[_isFrozen](),
        A = this.getVisibleColumns(),
        $ = this._createFilterRowHTML(A, 2);
        this._filterViewEl.innerHTML = $;
        this._doRenderFilters()
    },
    _createSummaryRowHTML: function(C, A) {
        var _ = this.getDataView(),
        G = ["<table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"];
        G.push(this._createTopRowHTML(C));
        G[G.length] = "<tr>";
        for (var $ = 0,
        D = C.length; $ < D; $++) {
            var B = C[$],
            F = this.l01O(B),
            H = this._OnDrawSummaryCell(_, B);
            G[G.length] = "<td id=\"";
            G[G.length] = F;
            G[G.length] = "\" class=\"mini-grid-summaryCell " + H.cellCls + "\" style=\"" + H.cellStyle + ";";
            G[G.length] = "\">";
            G[G.length] = H.cellHtml;
            G[G.length] = "</td>"
        }
        G[G.length] = "</tr></table><div class=\"mini-grid-scrollHeaderCell\"></div>";
        var E = G.join("");
        return E
    },
    o1111o: function() {
        var _ = this.getVisibleColumns(),
        $ = this._createSummaryRowHTML(_, 2);
        this._summaryViewEl.innerHTML = $
    },
    OOl0loByField: function(A, _) {
        if (!A) return null;
        var $ = this._columnModel._getDataTypeByField(A);
        this._dataSource._doClientSortField(A, _, $)
    },
    _expandGroupOnLoad: true,
    o1O0: 1,
    oo1O0O: "",
    O11o: "",
    groupBy: function($, _) {
        if (!$) return;
        this.oo1O0O = $;
        if (typeof _ == "string") _ = _.toLowerCase();
        this.O11o = _;
        this._createGroupingView();
        this.deferUpdate()
    },
    clearGroup: function() {
        this.oo1O0O = "";
        this.O11o = "";
        this.ll0l0 = null;
        this.deferUpdate()
    },
    setGroupField: function($) {
        this.groupBy($)
    },
    setGroupDir: function($) {
        this.O11o = field;
        this.groupBy(this.oo1O0O, $)
    },
    isGrouping: function() {
        return this.oo1O0O != ""
    },
    getGroupingView: function() {
        return this.ll0l0
    },
    _createGroupingView: function() {
        if (this[_isGrouping]() == false) return;
        this.ll0l0 = null;
        var F = this.oo1O0O,
        H = this.O11o;
        this.OOl0loByField(F, "asc");
        var D = this.getVisibleRows(),
        B = [],
        C = {};
        for (var _ = 0,
        G = D.length; _ < G; _++) {
            var $ = D[_],
            I = $[F],
            E = mini.isDate(I) ? I[_getTime]() : I,
            A = C[E];
            if (!A) {
                A = C[E] = {};
                A.field = F,
                A.dir = H;
                A.value = I;
                A.rows = [];
                B.push(A);
                A.id = "g" + this.o1O0++;
                A.expanded = this._expandGroupOnLoad
            }
            A.rows.push($)
        }
        this.ll0l0 = B
    },
    Ooll11: function($) {
        var _ = {
            group: $,
            rows: $.rows,
            field: $.field,
            dir: $.dir,
            value: $.value,
            cellHtml: $.field + " (" + $.rows.length + " Items)"
        };
        this[_fire]("drawgroup", _);
        return _
    },
    getRowGroup: function(_) {
        var $ = typeof _;
        if ($ == "number") return this.getGroupingView()[_];
        if ($ == "string") return this._getRowGroupById(_);
        return _
    },
    _getRowGroupByEvent: function(B) {
        var _ = ooOO(B.target, "mini-grid-groupRow");
        if (_) {
            var $ = _.id.split("$");
            if ($[0] != this._id) return null;
            var A = $[$.length - 1];
            return this._getRowGroupById(A)
        }
        return null
    },
    _getRowGroupById: function(C) {
        var _ = this.getGroupingView();
        for (var $ = 0,
        B = _.length; $ < B; $++) {
            var A = _[$];
            if (A.id == C) return A
        }
        return null
    },
    o1o1lGroupId: function($, _) {
        return this._id + "$group" + _ + "$" + $.id
    },
    o1o1lGroupRowsId: function($, _) {
        return this._id + "$grouprows" + _ + "$" + $.id
    },
    ol00l: function(_, $) {
        var A = this._id + "$row" + $ + "$" + _._id;
        return A
    },
    lO1ol1Id: function(_, $) {
        var A = this._id + "$headerCell" + $ + "$" + _._id;
        return A
    },
    llO1O: function($, _) {
        var A = $._id + "$cell$" + _._id;
        return A
    },
    OOll: function($) {
        return this._id + "$filter$" + $._id
    },
    l01O: function($) {
        return this._id + "$summary$" + $._id
    },
    getFilterCellEl: function($) {
        $ = this[_getColumn]($);
        if (!$) return null;
        return document.getElementById(this.OOll($))
    },
    getSummaryCellEl: function($) {
        $ = this[_getColumn]($);
        if (!$) return null;
        return document.getElementById(this.l01O($))
    },
    _doVisibleEls: function() {
        mini.GridView[_superclass][_doVisibleEls][_call](this);
        this._columnsEl.style.display = this.showColumns ? "block": "none";
        this.O0loll.style.display = this[_showFilterRow] ? "block": "none";
        this.l011l.style.display = this[_showSummaryRow] ? "block": "none";
        this._bottomPagerEl.style.display = this.showPager ? "block": "none"
    },
    setShowColumns: function($) {
        this.showColumns = $;
        this[_doVisibleEls]();
        this[_deferLayout]()
    },
    setShowFilterRow: function($) {
        this[_showFilterRow] = $;
        this[_doVisibleEls]();
        this[_deferLayout]()
    },
    setShowSummaryRow: function($) {
        this[_showSummaryRow] = $;
        this[_doVisibleEls]();
        this[_deferLayout]()
    },
    setShowPager: function($) {
        this.showPager = $;
        this[_doVisibleEls]();
        this[_deferLayout]()
    },
    setFitColumns: function($) {
        this.fitColumns = $;
        this[_deferLayout]()
    },
    getBodyHeight: function(_) {
        var $ = mini.GridView[_superclass][_getBodyHeight][_call](this, _);
        $ = $ - this.getColumnsHeight() - this.getFilterHeight() - this.getSummaryHeight() - this.getPagerHeight();
        return $
    },
    getColumnsHeight: function() {
        return this.showColumns ? O00O(this._columnsEl) : 0
    },
    getFilterHeight: function() {
        return this[_showFilterRow] ? O00O(this.O0loll) : 0
    },
    getSummaryHeight: function() {
        return this[_showSummaryRow] ? O00O(this.l011l) : 0
    },
    getPagerHeight: function() {
        return this.showPager ? O00O(this._bottomPagerEl) : 0
    },
    getGridViewBox: function(_) {
        var $ = O11O(this._columnsEl),
        A = O11O(this.oOo1o0);
        $.height = A.bottom - $.top;
        $.bottom = $.top + $.height;
        return $
    },
    getSortField: function($) {
        return this._dataSource.sortField
    },
    getSortOrder: function($) {
        return this._dataSource.sortOrder
    },
    _createSource: function() {
        this._dataSource = new mini.DataTable()
    },
    oO0O: function() {
        var $ = this._dataSource;
        $[_on]("loaddata", this.__OnSourceLoadData, this);
        $[_on]("cleardata", this.__OnSourceClearData, this)
    },
    __OnSourceLoadData: function($) {
        this[_initData]();
        this[_doUpdate]()
    },
    __OnSourceClearData: function($) {
        this[_initData]();
        this[_doUpdate]()
    },
    _initData: function() {},
    isFrozen: function() {
        var _ = this._columnModel._frozenStartColumn,
        $ = this._columnModel._frozenEndColumn;
        return this._columnModel[_isFrozen]()
    },
    _createColumnModel: function() {
        this._columnModel = new mini.ColumnModel(this)
    },
    _bindColumnModel: function() {
        this._columnModel[_on]("columnschanged", this.__OnColumnsChanged, this)
    },
    __OnColumnsChanged: function($) {
        this.columns = this._columnModel.columns;
        this.oooO();
        this.o1111o();
        this[_doUpdate]();
        this[_fire]("columnschanged")
    },
    setColumns: function($) {
        this._columnModel[_setColumns]($);
        this.columns = this._columnModel.columns
    },
    getColumns: function() {
        return this._columnModel[_getColumns]()
    },
    getBottomColumns: function() {
        return this._columnModel[_getBottomColumns]()
    },
    getVisibleColumnsRow: function() {
        var $ = this._columnModel.getVisibleColumnsRow().clone();
        return $
    },
    getVisibleColumns: function() {
        var $ = this._columnModel.getVisibleColumns().clone();
        return $
    },
    getFrozenColumns: function() {
        var $ = this._columnModel.getFrozenColumns().clone();
        return $
    },
    getUnFrozenColumns: function() {
        var $ = this._columnModel.getUnFrozenColumns().clone();
        return $
    },
    getColumn: function($) {
        return this._columnModel[_getColumn]($)
    },
    updateColumn: function($, _) {
        this._columnModel.updateColumn($, _)
    },
    showColumns: function(A) {
        for (var $ = 0,
        B = A.length; $ < B; $++) {
            var _ = this[_getColumn](A[$]);
            if (!_) continue;
            _.visible = true
        }
        this._columnModel._columnsChanged()
    },
    hideColumns: function(A) {
        for (var $ = 0,
        B = A.length; $ < B; $++) {
            var _ = this[_getColumn](A[$]);
            if (!_) continue;
            _.visible = false
        }
        this._columnModel._columnsChanged()
    },
    showColumn: function($) {
        this.updateColumn($, {
            visible: true
        })
    },
    hideColumn: function($) {
        this.updateColumn($, {
            visible: false
        })
    },
    moveColumn: function(A, $, _) {
        this._columnModel[_moveColumn](A, $, _)
    },
    removeColumn: function($) {
        $ = this[_getColumn]($);
        if (!$) return;
        var _ = this[_getParentColumn]($);
        if ($ && _) {
            _.columns.remove($);
            this._columnModel._columnsChanged()
        }
        return $
    },
    setColumnWidth: function(_, $) {
        this.updateColumn(_, {
            width: $
        })
    },
    getColumnWidth: function(_) {
        var $ = this[_getColumnBox](_);
        return $.width
    },
    getParentColumn: function($) {
        return this._columnModel[_getParentColumn]($)
    },
    getMaxColumnLevel: function() {
        return this._columnModel._getMaxColumnLevel()
    },
    _isCellVisible: function($, _) {
        return true
    },
    _createDrawCellEvent: function($, B, C, D) {
        var _ = mini._getMap(B.field, $),
        E = {
            sender: this,
            rowIndex: C,
            columnIndex: D,
            record: $,
            row: $,
            column: B,
            field: B.field,
            value: _,
            cellHtml: _,
            rowCls: "",
            rowStyle: null,
            cellCls: B.cellCls || "",
            cellStyle: B.cellStyle || "",
            allowCellWrap: this.allowCellWrap,
            showHGridLines: this.showHGridLines,
            showVGridLines: this.showVGridLines,
            cellInnerCls: "",
            cellInnnerStyle: "",
            autoEscape: B.autoEscape
        };
        E.visible = this[_isCellVisible](C, D);
        if (E.visible == true && this._mergedCellMaps) {
            var A = this._mergedCellMaps[C + ":" + D];
            if (A) {
                E.rowSpan = A.rowSpan;
                E.colSpan = A.colSpan
            }
        }
        return E
    },
    OollO: function($, B, C, D) {
        var E = this[_createDrawCellEvent]($, B, C, D),
        _ = E.value;
        if (B.dateFormat) if (mini.isDate(E.value)) E.cellHtml = mini.formatDate(_, B.dateFormat);
        else E.cellHtml = _;
        if (B.dataType == "currency") E.cellHtml = mini.formatCurrency(E.value, B.currencyUnit);
        if (B.displayField) E.cellHtml = mini._getMap(B.displayField, $);
        if (E.autoEscape == true) E.cellHtml = mini.htmlEncode(E.cellHtml);
        var A = B.renderer;
        if (A) {
            fn = typeof A == "function" ? A: l1O1l(A);
            if (fn) E.cellHtml = fn[_call](B, E)
        }
        this[_fire]("drawcell", E);
        if (E.cellHtml && !!E.cellHtml.unshift && E.cellHtml.length == 0) E.cellHtml = "&nbsp;";
        if (E.cellHtml === null || E.cellHtml === undefined || E.cellHtml === "") E.cellHtml = "&nbsp;";
        return E
    },
    _OnDrawSummaryCell: function(A, B) {
        var D = {
            result: this.getResultObject(),
            sender: this,
            data: A,
            column: B,
            field: B.field,
            value: "",
            cellHtml: "",
            cellCls: B.cellCls || "",
            cellStyle: B.cellStyle || "",
            allowCellWrap: this.allowCellWrap
        };
        if (B.summaryType) {
            var C = mini.summaryTypes[B.summaryType];
            if (C) D.value = C(A, B.field)
        }
        var $ = D.value;
        D.cellHtml = D.value;
        if (D.value && parseInt(D.value) != D.value && D.value.toFixed) {
            decimalPlaces = parseInt(B[_decimalPlaces]);
            if (isNaN(decimalPlaces)) decimalPlaces = 2;
            D.cellHtml = parseFloat(D.value.toFixed(decimalPlaces))
        }
        if (B.dateFormat) if (mini.isDate(D.value)) D.cellHtml = mini.formatDate($, B.dateFormat);
        else D.cellHtml = $;
        if (B.dataType == "currency") D.cellHtml = mini.formatCurrency(D.cellHtml, B.currencyUnit);
        var _ = B.summaryRenderer;
        if (_) {
            C = typeof _ == "function" ? _: window[_];
            if (C) D.cellHtml = C[_call](B, D)
        }
        B.summaryValue = D.value;
        this[_fire]("drawsummarycell", D);
        if (D.cellHtml === null || D.cellHtml === undefined || D.cellHtml === "") D.cellHtml = "&nbsp;";
        return D
    },
    getScrollTop: function() {
        return this._rowsViewEl.scrollTop
    },
    setScrollTop: function($) {
        this._rowsViewEl.scrollTop = $
    },
    getScrollLeft: function() {
        return this._rowsViewEl.scrollLeft
    },
    setScrollLeft: function($) {
        this._rowsViewEl.scrollLeft = $
    },
    _syncScroll: function() {
        var $ = this._rowsViewEl.scrollLeft;
        this._filterViewEl.scrollLeft = $;
        this._summaryViewEl.scrollLeft = $;
        this._columnsViewEl.scrollLeft = $
    },
    __OnRowViewScroll: function($) {
        this._syncScroll()
    },
    _pagers: [],
    looolOs: function() {
        this._pagers = [];
        var $ = new loooo();
        this._setBottomPager($)
    },
    _setBottomPager: function($) {
        $ = mini.create($);
        if (!$) return;
        if (this._bottomPager) {
            this[_unbindPager](this._bottomPager);
            this._bottomPagerEl.removeChild(this._bottomPager.el)
        }
        this._bottomPager = $;
        $[_render](this._bottomPagerEl);
        this[_bindPager]($)
    },
    bindPager: function($) {
        this._pagers[_add]($)
    },
    unbindPager: function($) {
        this._pagers.remove($)
    },
    setShowEmptyText: function($) {
        this.showEmptyText = $
    },
    getShowEmptyText: function() {
        return this.showEmptyText
    },
    setEmptyText: function($) {
        this[_emptyText] = $
    },
    getEmptyText: function() {
        return this[_emptyText]
    },
    setShowModified: function($) {
        this.showModified = $
    },
    getShowModified: function() {
        return this.showModified
    },
    setShowNewRow: function($) {
        this.showNewRow = $
    },
    getShowNewRow: function() {
        return this.showNewRow
    },
    setShowHGridLines: function($) {
        if (this[_showHGridLines] != $) {
            this[_showHGridLines] = $;
            this.deferUpdate()
        }
    },
    getShowHGridLines: function() {
        return this[_showHGridLines]
    },
    setShowVGridLines: function($) {
        if (this[_showVGridLines] != $) {
            this[_showVGridLines] = $;
            this.deferUpdate()
        }
    },
    getShowVGridLines: function() {
        return this[_showVGridLines]
    }
});
mini.copyTo(mini.GridView.prototype, mini._DataTableApplys);
O1o0(mini.GridView, "gridview");
mini.FrozenGridView = function() {
    mini.FrozenGridView[_superclass][_constructor][_call](this)
};
lol01(mini.FrozenGridView, mini.GridView, {
    isFixedRowHeight: function() {
        return this.fixedRowHeight || this[_isFrozen]()
    },
    _create: function() {
        mini.FrozenGridView[_superclass][_create][_call](this);
        var _ = this.el,
        C = "<div class=\"mini-grid-columns-lock\"></div>",
        $ = "<div class=\"mini-grid-rows-lock\"><div class=\"mini-grid-rows-content\"></div></div>";
        this._columnsLockEl = mini.before(this._columnsViewEl, C);
        this._rowsLockEl = mini.before(this._rowsViewEl, $);
        this._rowsLockContentEl = this._rowsLockEl.firstChild;
        var A = "<div class=\"mini-grid-filterRow-lock\"></div>";
        this._filterLockEl = mini.before(this._filterViewEl, A);
        var B = "<div class=\"mini-grid-summaryRow-lock\"></div>";
        this._summaryLockEl = mini.before(this._summaryViewEl, B)
    },
    _initEvents: function() {
        mini.FrozenGridView[_superclass][_initEvents][_call](this);
        lo1l1o(this._rowsEl, "mousewheel", this.__OnMouseWheel, this)
    },
    o1oloOText: function(_, $) {
        var A = _.header;
        if (typeof A == "function") A = A[_call](this, _);
        if (mini.isNull(A) || A === "") A = "&nbsp;";
        if (this[_isFrozen]() && $ == 2) if (_.viewIndex1) A = "&nbsp;";
        return A
    },
    _createColumnColSpan: function(_, B, $) {
        if (this[_isFrozen]()) {
            var A = _["colspan" + $];
            if (A) B[B.length] = "colspan=\"" + A + "\" "
        } else if (_.colspan) B[B.length] = "colspan=\"" + _.colspan + "\" "
    },
    doUpdateColumns: function() {
        var _ = this[_isFrozen]() ? this.getFrozenColumnsRow() : [],
        E = this[_isFrozen]() ? this.getUnFrozenColumnsRow() : this.getVisibleColumnsRow(),
        C = this[_isFrozen]() ? this.getFrozenColumns() : [],
        A = this[_isFrozen]() ? this.getUnFrozenColumns() : this.getVisibleColumns(),
        $ = this._createColumnsHTML(_, 1, C),
        B = this._createColumnsHTML(E, 2, A),
        F = "<div class=\"mini-grid-topRightCell\"></div>";
        $ += F;
        B += F;
        this._columnsLockEl.innerHTML = $;
        this._columnsViewEl.innerHTML = B;
        var D = this._columnsLockEl.firstChild;
        D.style.width = "0px"
    },
    doUpdateRows: function() {
        var B = this.getVisibleRows(),
        _ = this.getFrozenColumns(),
        D = this.getUnFrozenColumns();
        if (this[_isGrouping]()) {
            var $ = this._createGroupingHTML(_, 1),
            A = this._createGroupingHTML(D, 2);
            this._rowsLockContentEl.innerHTML = $;
            this._rowsViewContentEl.innerHTML = A
        } else {
            $ = this.o1o1lsHTML(_, 1, this[_isFrozen]() ? B: []),
            A = this.o1o1lsHTML(D, 2, B);
            this._rowsLockContentEl.innerHTML = $;
            this._rowsViewContentEl.innerHTML = A
        }
        var C = this._rowsLockContentEl.firstChild;
        C.style.width = "0px"
    },
    oooO: function() {
        if (this._filterLockEl.firstChild) this._filterLockEl.removeChild(this._filterLockEl.firstChild);
        if (this._filterViewEl.firstChild) this._filterViewEl.removeChild(this._filterViewEl.firstChild);
        var $ = this.getFrozenColumns(),
        B = this.getUnFrozenColumns(),
        A = this._createFilterRowHTML($, 1),
        _ = this._createFilterRowHTML(B, 2);
        this._filterLockEl.innerHTML = A;
        this._filterViewEl.innerHTML = _;
        this._doRenderFilters()
    },
    o1111o: function() {
        var $ = this.getFrozenColumns(),
        B = this.getUnFrozenColumns(),
        A = this._createSummaryRowHTML($, 1),
        _ = this._createSummaryRowHTML(B, 2);
        this._summaryLockEl.innerHTML = A;
        this._summaryViewEl.innerHTML = _
    },
    _syncColumnHeight: function() {
        var A = this._columnsLockEl.firstChild,
        _ = this._columnsViewEl.firstChild;
        A.style.height = _.style.height = "auto";
        if (this[_isFrozen]()) {
            var B = A.offsetHeight,
            $ = _.offsetHeight;
            B = B > $ ? B: $;
            A.style.height = _.style.height = B + "px"
        }
    },
    doLayout: function() {
        if (this[_canLayout]() == false) return;
        this._doLayoutScroll = false;
        this.ll01O1Text();
        this._syncColumnHeight();
        mini.FrozenGridView[_superclass][_doLayout][_call](this);
        var _ = this[_isAutoHeight](),
        A = this[_isFrozen](),
        $ = this[_getViewportWidth](true),
        C = this.getLockedWidth(),
        B = $ - C;
        if (A) {
            this._filterViewEl.style["marginLeft"] = C + "px";
            this._summaryViewEl.style["marginLeft"] = C + "px";
            this._columnsViewEl.style["marginLeft"] = C + "px";
            this._rowsViewEl.style["marginLeft"] = C + "px";
            if (mini.isChrome || mini.isIE6) {
                this._filterViewEl.style["width"] = B + "px";
                this._summaryViewEl.style["width"] = B + "px";
                this._columnsViewEl.style["width"] = B + "px"
            } else {
                this._filterViewEl.style["width"] = "auto";
                this._summaryViewEl.style["width"] = "auto";
                this._columnsViewEl.style["width"] = "auto"
            }
            if (mini.isChrome || mini.isIE6) this._rowsViewEl.style["width"] = B + "px";
            l10l(this._filterLockEl, C);
            l10l(this._summaryLockEl, C);
            l10l(this._columnsLockEl, C);
            l10l(this._rowsLockEl, C);
            this._filterLockEl.style["left"] = "0px";
            this._summaryLockEl.style["left"] = "0px";
            this._columnsLockEl.style["left"] = "0px";
            this._rowsLockEl.style["left"] = "0px"
        } else this._doClearFrozen();
        if (_) this._rowsLockEl.style.height = "auto";
        else this._rowsLockEl.style.height = "100%"
    },
    ll01O1Text: function() {},
    Oo1l: function(_, $) {
        _ = this.getRecord(_);
        var B = this.ol00l(_, $),
        A = document.getElementById(B);
        return A
    },
    _doClearFrozen: function() {
        this._filterLockEl.style.left = "-10px";
        this._summaryLockEl.style.left = "-10px";
        this._columnsLockEl.style.left = "-10px";
        this._rowsLockEl.style.left = "-10px";
        this._filterLockEl.style["width"] = "0px";
        this._summaryLockEl.style["width"] = "0px";
        this._columnsLockEl.style["width"] = "0px";
        this._rowsLockEl.style["width"] = "0px";
        this._filterLockEl.style["marginLeft"] = "0px";
        this._summaryLockEl.style["marginLeft"] = "0px";
        this._columnsViewEl.style["marginLeft"] = "0px";
        this._rowsViewEl.style["marginLeft"] = "0px";
        this._filterViewEl.style["width"] = "auto";
        this._summaryViewEl.style["width"] = "auto";
        this._columnsViewEl.style["width"] = "auto";
        this._rowsViewEl.style["width"] = "auto";
        if (mini.isChrome || mini.isIE6) {
            this._filterViewEl.style["width"] = "100%";
            this._summaryViewEl.style["width"] = "100%";
            this._columnsViewEl.style["width"] = "100%";
            this._rowsViewEl.style["width"] = "100%"
        }
    },
    frozenColumns: function($, _) {
        this.frozen($, _)
    },
    unFrozenColumns: function() {
        this.unFrozen()
    },
    frozen: function($, _) {
        this._doClearFrozen();
        this._columnModel.frozen($, _)
    },
    unFrozen: function() {
        this._doClearFrozen();
        this._columnModel.unFrozen()
    },
    setFrozenStartColumn: function($) {
        this._columnModel[_setFrozenStartColumn]($)
    },
    setFrozenEndColumn: function($) {
        return this._columnModel[_setFrozenEndColumn]($)
    },
    getFrozenStartColumn: function($) {
        return this._columnModel._frozenStartColumn
    },
    getFrozenEndColumn: function($) {
        return this._columnModel._frozenEndColumn
    },
    getFrozenColumnsRow: function() {
        return this._columnModel.getFrozenColumnsRow()
    },
    getUnFrozenColumnsRow: function() {
        return this._columnModel.getUnFrozenColumnsRow()
    },
    getLockedWidth: function() {
        if (!this[_isFrozen]()) return 0;
        var $ = this._columnsLockEl.firstChild.firstChild,
        _ = $ ? $.offsetWidth: 0;
        return _
    },
    _canDeferSyncScroll: function() {
        return this[_isFrozen]()
    },
    _syncScroll: function() {
        var $ = this._rowsViewEl.scrollLeft;
        this._filterViewEl.scrollLeft = $;
        this._summaryViewEl.scrollLeft = $;
        this._columnsViewEl.scrollLeft = $;
        var _ = this,
        A = _._rowsViewEl.scrollTop;
        _._rowsLockEl.scrollTop = A;
        if (this._canDeferSyncScroll()) setTimeout(function() {
            _._rowsViewEl.scrollTop = _._rowsLockEl.scrollTop
        },
        50)
    },
    __OnMouseWheel: function(A) {
        var _ = this.getScrollTop() - A.wheelDelta,
        $ = this.getScrollTop();
        this.setScrollTop(_);
        if ($ != this.getScrollTop()) A.preventDefault()
    }
});
O1o0(mini.FrozenGridView, "FrozenGridView");
mini.ScrollGridView = function() {
    mini.ScrollGridView[_superclass][_constructor][_call](this)
};
lol01(mini.ScrollGridView, mini.FrozenGridView, {
    virtualScroll: true,
    virtualRows: 50,
    defaultRowHeight: 23,
    _canDeferSyncScroll: function() {
        return this[_isFrozen]() && !this.isVirtualScroll()
    },
    setVirtualScroll: function($) {
        this.virtualScroll = $;
        this[_doUpdate]()
    },
    getVirtualScroll: function($) {
        return this.virtualScroll
    },
    isFixedRowHeight: function() {
        return this.fixedRowHeight || this.isVirtualScroll() || this[_isFrozen]()
    },
    isVirtualScroll: function() {
        if (this.virtualScroll) return this[_isAutoHeight]() == false && this[_isGrouping]() == false;
        return false
    },
    _getScrollView: function() {
        var $ = this.getVisibleRows();
        return $
    },
    _getScrollViewCount: function() {
        return this._getScrollView().length
    },
    _getScrollRowHeight: function($, _) {
        if (_ && _._height) {
            var A = parseInt(_._height);
            if (!isNaN(A)) return A
        }
        return this.defaultRowHeight
    },
    _getRangeHeight: function(B, E) {
        var A = 0,
        D = this._getScrollView();
        for (var $ = B; $ < E; $++) {
            var _ = D[$],
            C = this._getScrollRowHeight($, _);
            A += C
        }
        return A
    },
    _getIndexByScrollTop: function(F) {
        var A = 0,
        C = this._getScrollView(),
        E = this._getScrollViewCount();
        for (var $ = 0,
        D = E; $ < D; $++) {
            var _ = C[$],
            B = this._getScrollRowHeight($, _);
            A += B;
            if (A >= F) return $
        }
        return E
    },
    __getScrollViewRange: function($, A) {
        var _ = this._getScrollView();
        return _.getRange($, A)
    },
    _getViewRegion: function() {
        var I = this._getScrollView();
        if (this.isVirtualScroll() == false) {
            var C = {
                top: 0,
                bottom: 0,
                rows: I,
                start: 0,
                end: 0
            };
            return C
        }
        var D = this.defaultRowHeight,
        K = this._getViewNowRegion(),
        G = this.getScrollTop(),
        $ = this._vscrollEl.offsetHeight,
        L = this._getScrollViewCount(),
        A = K.start,
        B = K.end;
        for (var H = 0,
        F = L; H < F; H += this.virtualRows) {
            var E = H + this.virtualRows;
            if (H <= A && A < E) A = H;
            if (H < B && B <= E) B = E
        }
        if (B > L) B = L;
        if (B == 0) B = this.virtualRows;
        var _ = this._getRangeHeight(0, A),
        J = this._getRangeHeight(B, this._getScrollViewCount()),
        I = this.__getScrollViewRange(A, B),
        C = {
            top: _,
            bottom: J,
            rows: I,
            start: A,
            end: B,
            viewStart: A,
            viewEnd: B
        };
        C.viewTop = this._getRangeHeight(0, C.viewStart);
        C.viewBottom = this._getRangeHeight(C.viewEnd, this._getScrollViewCount());
        return C
    },
    _getViewNowRegion: function() {
        var B = this.defaultRowHeight,
        E = this.getScrollTop(),
        $ = this._vscrollEl.offsetHeight,
        C = this._getIndexByScrollTop(E),
        _ = this._getIndexByScrollTop(E + $ + 30),
        D = this._getScrollViewCount();
        if (_ > D) _ = D;
        var A = {
            start: C,
            end: _
        };
        return A
    },
    _canVirtualUpdate: function() {
        if (!this._viewRegion) return true;
        var $ = this._getViewNowRegion();
        if (this._viewRegion.start <= $.start && $.end <= this._viewRegion.end) return false;
        return true
    },
    __OnColumnsChanged: function($) {
        this.columns = this._columnModel.columns;
        this.oooO();
        this.o1111o();
        if (this.getVisibleRows().length == 0) this[_doUpdate]();
        else this.deferUpdate();
        if (this.isVirtualScroll()) this.__OnVScroll();
        this[_fire]("columnschanged")
    },
    doLayout: function() {
        if (this[_canLayout]() == false) return;
        mini.ScrollGridView[_superclass][_doLayout][_call](this);
        this._layoutScroll()
    },
    o1o1lsHTML: function(C, E, F, A, G, J) {
        var K = this.isVirtualScroll();
        if (!K) return mini.ScrollGridView[_superclass].o1o1lsHTML.apply(this, arguments);
        var B = K ? this._getViewRegion() : null,
        D = ["<table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"];
        D.push(this._createTopRowHTML(C));
        if (this.isVirtualScroll()) {
            var H = A == 0 ? "display:none;": "";
            D.push("<tr class=\"mini-grid-virtualscroll-top\" style=\"padding:0;border:0;" + H + "\"><td colspan=\"" + C.length + "\" style=\"height:" + A + "px;padding:0;border:0;" + H + "\"></td></tr>")
        }
        if (E == 1 && this[_isFrozen]() == false);
        else for (var I = 0,
        _ = F.length; I < _; I++) {
            var $ = F[I];
            this.o1o1lHTML($, J, C, E, D);
            J++
        }
        if (this.isVirtualScroll()) D.push("<tr class=\"mini-grid-virtualscroll-bottom\" style=\"padding:0;border:0;\"><td colspan=\"" + C.length + "\" style=\"height:" + G + "px;padding:0;border:0;\"></td></tr>");
        D.push("</table>");
        return D.join("")
    },
    doUpdateRows: function() {
        if (this.isVirtualScroll() == false) {
            mini.ScrollGridView[_superclass].doUpdateRows[_call](this);
            return
        }
        var E = this._getViewRegion();
        this._viewRegion = E;
        var C = this.getFrozenColumns(),
        H = this.getUnFrozenColumns(),
        G = E.viewStart,
        B = E.start,
        A = E.viewEnd;
        if (this._scrollPaging) {
            var _ = this[_getPageIndex]() * this[_getPageSize]();
            G -= _;
            B -= _;
            A -= _
        }
        var F = new Date(),
        $ = this.o1o1lsHTML(C, 1, E.rows, E.viewTop, E.viewBottom, G),
        D = this.o1o1lsHTML(H, 2, E.rows, E.viewTop, E.viewBottom, G);
        this._rowsLockContentEl.innerHTML = $;
        this._rowsViewContentEl.innerHTML = D
    },
    _create: function() {
        mini.ScrollGridView[_superclass][_create][_call](this);
        this._vscrollEl = mini.append(this._rowsEl, "<div class=\"mini-grid-vscroll\"><div class=\"mini-grid-vscroll-content\"></div></div>");
        this._vscrollContentEl = this._vscrollEl.firstChild
    },
    _initEvents: function() {
        mini.ScrollGridView[_superclass][_initEvents][_call](this);
        var $ = this;
        lo1l1o(this._vscrollEl, "scroll", this.__OnVScroll, this);
        mini._onScrollDownUp(this._vscrollEl,
        function(_) {
            $._VScrollMouseDown = true
        },
        function(_) {
            $._VScrollMouseDown = false
        })
    },
    _layoutScroll: function() {
        var A = this.isVirtualScroll();
        if (A) {
            var B = this.getScrollHeight(),
            $ = B > this._rowsViewEl.offsetHeight;
            if (A && $) {
                this._vscrollEl.style.display = "block";
                this._vscrollContentEl.style.height = B + "px"
            } else this._vscrollEl.style.display = "none";
            if (this._rowsViewEl.scrollWidth > this._rowsViewEl.clientWidth + 1) {
                var _ = this[_getBodyHeight](true) - 18;
                if (_ < 0) _ = 0;
                this._vscrollEl.style.height = _ + "px"
            } else this._vscrollEl.style.height = "100%"
        } else this._vscrollEl.style.display = "none"
    },
    getScrollHeight: function() {
        var $ = this.getVisibleRows();
        return this._getRangeHeight(0, $.length)
    },
    setScrollTop: function($) {
        if (this.isVirtualScroll()) this._vscrollEl.scrollTop = $;
        else this._rowsViewEl.scrollTop = $
    },
    getScrollTop: function() {
        if (this.isVirtualScroll()) return this._vscrollEl.scrollTop;
        else return this._rowsViewEl.scrollTop
    },
    __OnVScroll: function(A) {
        var _ = this.isVirtualScroll();
        if (_) {
            this._scrollTop = this._vscrollEl.scrollTop;
            var $ = this;
            setTimeout(function() {
                $._rowsViewEl.scrollTop = $._scrollTop;
                $._llo1l = null
            },
            8);
            if (this._scrollTopTimer) clearTimeout(this._scrollTopTimer);
            this._scrollTopTimer = setTimeout(function() {
                $._scrollTopTimer = null;
                $._tryUpdateScroll();
                $._rowsViewEl.scrollTop = $._scrollTop
            },
            80)
        }
    },
    __OnMouseWheel: function(C) {
        var A = C.wheelDelta ? C: C.originalEvent,
        _ = A.wheelDelta || -A.detail * 24,
        B = this.getScrollTop() - _,
        $ = this.getScrollTop();
        this.setScrollTop(B);
        if ($ != this.getScrollTop() || this.isVirtualScroll()) C.preventDefault()
    },
    _tryUpdateScroll: function() {
        var $ = this._canVirtualUpdate();
        if ($) {
            if (this._scrollPaging) {
                var A = this;
                this[_reload](null, null,
                function($) {})
            } else {
                var _ = new Date();
                this.doUpdateRows()
            }
        }
    }
});
O1o0(mini.ScrollGridView, "ScrollGridView");
mini._onScrollDownUp = function($, B, A) {
    function D($) {
        if (mini.isFirefox) lo1l1o(document, "mouseup", _);
        else lo1l1o(document, "mousemove", C);
        B($)
    }
    function C($) {
        o01o(document, "mousemove", C);
        A($)
    }
    function _($) {
        o01o(document, "mouseup", _);
        A($)
    }
    lo1l1o($, "mousedown", D)
};
mini._GridlolO0 = function($) {
    this.owner = $,
    el = $.el;
    $[_on]("rowmousemove", this.__OnRowMouseMove, this);
    lo1l1o($.OoOoll, "mouseout", this.oll1OO, this);
    lo1l1o($.OoOoll, "mousewheel", this.__OnMouseWheel, this);
    $[_on]("cellmousedown", this.__OnCellMouseDown, this);
    $[_on]("cellclick", this.__OnGridCellClick, this);
    $[_on]("celldblclick", this.__OnGridCellClick, this);
    lo1l1o($.el, "keydown", this.Olo1, this)
};
mini._GridlolO0[_prototype] = {
    Olo1: function(G) {
        var $ = this.owner;
        if (lolo($.O0loll, G.target) || lolo($.l011l, G.target) || lolo($.llol1, G.target) || lolo($.o1O1oo, G.target) || ooOO(G.target, "mini-grid-detailRow") || ooOO(G.target, "mini-grid-rowEdit") || ooOO(G.target, "mini-tree-editinput")) return;
        var A = $[_getCurrentCell]();
        if (G.shiftKey || G.ctrlKey || G.altKey) return;
        if (G.keyCode == 37 || G.keyCode == 38 || G.keyCode == 39 || G.keyCode == 40) G.preventDefault();
        var C = $.getVisibleColumns(),
        B = A ? A[1] : null,
        _ = A ? A[0] : null;
        if (!A) _ = $.getCurrent();
        var F = C[_indexOf](B),
        D = $[_indexOf](_),
        E = $[_getData]().length;
        switch (G.keyCode) {
        case 9:
            if ($[_allowCellEdit] && $.editOnTabKey) {
                G.preventDefault();
                $[_beginEditNextCell](G.shiftKey == false);
                return
            }
            break;
        case 27:
            break;
        case 13:
            if ($[_allowCellEdit] && $.editNextOnEnterKey) if ($[_isEditingCell](A) || !B.editor) {
                $[_beginEditNextCell](G.shiftKey == false);
                return
            }
            if ($[_allowCellEdit] && A && !B[_readOnly]) $[_beginEditCell]();
            break;
        case 37:
            if (B) {
                if (F > 0) F -= 1
            } else F = 0;
            break;
        case 38:
            if (_) {
                if (D > 0) D -= 1
            } else D = 0;
            if (D != 0 && $.isVirtualScroll()) if ($._viewRegion.start > D) {
                $.oOo1o0.scrollTop -= $._rowHeight;
                $._tryUpdateScroll()
            }
            break;
        case 39:
            if (B) {
                if (F < C.length - 1) F += 1
            } else F = 0;
            break;
        case 40:
            if (_) {
                if (D < E - 1) D += 1
            } else D = 0;
            if ($.isVirtualScroll()) if ($._viewRegion.end < D) {
                $.oOo1o0.scrollTop += $._rowHeight;
                $._tryUpdateScroll()
            }
            break;
        default:
            break
        }
        B = C[F];
        _ = $[_getAt](D);
        if (B && _ && $[_allowCellSelect]) {
            A = [_, B];
            $[_setCurrentCell](A);
            $[_scrollIntoView](_, B)
        }
        if (_ && $[_allowRowSelect]) {
            $[_deselectAll]();
            $[_setCurrent](_);
            if (_) $[_scrollIntoView](_)
        }
    },
    __OnMouseWheel: function(_) {
        var $ = this.owner;
        if ($[_allowCellEdit]) $[_commitEdit]()
    },
    __OnGridCellClick: function(B) {
        var $ = this.owner;
        if ($[_allowCellEdit] == false) return;
        if ($.cellEditAction != B.type) return;
        var _ = B.record,
        A = B.column;
        if (!A[_readOnly] && !$[_isReadOnly]()) if (B.htmlEvent.shiftKey || B.htmlEvent.ctrlKey);
        else $[_beginEditCell]()
    },
    __OnCellMouseDown: function(_) {
        var $ = this;
        $.__doSelect(_)
    },
    __OnRowMouseMove: function(A) {
        var $ = this.owner,
        _ = A.record;
        if (!$.enabled || $[_enableHotTrack] == false) return;
        $[_focusRow](_)
    },
    oll1OO: function($) {
        this.owner[_focusRow](null)
    },
    __doSelect: function(E) {
        var _ = E.record,
        C = E.column,
        $ = this.owner;
        if (_.enabled === false) return;
        if ($[_allowCellSelect]) {
            var B = [_, C];
            $[_setCurrentCell](B)
        }
        if ($[_allowRowSelect]) {
            var D = {
                record: _,
                selected: _,
                cancel: false
            };
            if (_) $[_fire]("beforerowselect", D);
            if (D.cancel) return;
            if ($[_getMultiSelect]()) {
                $.el.onselectstart = function() {};
                if (E.htmlEvent.shiftKey) {
                    $.el.onselectstart = function() {
                        return false
                    };
                    E.htmlEvent.preventDefault();
                    var A = $.getCurrent();
                    if (A) {
                        $[_deselectAll]();
                        $.selectRange(A, _);
                        $[_setCurrent](A)
                    } else {
                        $[_select](_);
                        $[_setCurrent](_)
                    }
                } else {
                    $.el.onselectstart = function() {};
                    if (E.htmlEvent.ctrlKey) {
                        $.el.onselectstart = function() {
                            return false
                        };
                        E.htmlEvent.preventDefault()
                    }
                    if (E.column._multiRowSelect === true || E.htmlEvent.ctrlKey || $.allowUnselect) {
                        if ($[_isSelected](_)) $[_deselect](_);
                        else {
                            $[_select](_);
                            $[_setCurrent](_)
                        }
                    } else if ($[_isSelected](_));
                    else {
                        $[_deselectAll]();
                        $[_select](_);
                        $[_setCurrent](_)
                    }
                }
            } else if (!$[_isSelected](_)) {
                $[_deselectAll]();
                $[_select](_)
            } else if (E.htmlEvent.ctrlKey || $.allowUnselect) $[_deselectAll]()
        }
    }
};
mini._Grid_RowGroup = function($) {
    this.owner = $,
    el = $.el;
    lo1l1o($.oOo1o0, "click", this.O0O1l, this)
};
mini._Grid_RowGroup[_prototype] = {
    O0O1l: function(A) {
        var $ = this.owner,
        _ = $._getRowGroupByEvent(A);
        if (_) $[_toggleRowGroup](_)
    }
};
mini._GridO0111lMenu = function($) {
    this.owner = $;
    this.menu = this.createMenu();
    lo1l1o($.el, "contextmenu", this.oOl0O, this)
};
mini._GridO0111lMenu[_prototype] = {
    createMenu: function() {
        var $ = mini.create({
            type: "menu",
            hideOnClick: false
        });
        $[_on]("itemclick", this.loOl, this);
        return $
    },
    updateMenu: function() {
        var _ = this.owner,
        F = this.menu,
        D = _[_getBottomColumns](),
        B = [];
        for (var A = 0,
        E = D.length; A < E; A++) {
            var C = D[A],
            $ = {};
            $.checked = C.visible;
            $[_checkOnClick] = true;
            $.text = _.o1oloOText(C);
            if ($.text == "&nbsp;") {
                if (C.type == "indexcolumn") $.text = "\u5e8f\u53f7";
                if (C.type == "checkcolumn") $.text = "\u9009\u62e9"
            }
            B.push($);
            $._column = C
        }
        F[_setItems](B)
    },
    oOl0O: function(_) {
        var $ = this.owner;
        if ($.showColumnsMenu == false) return;
        if (lolo($._columnsEl, _.target) == false) return;
        this[_updateMenu]();
        this.menu[_showAtPos](_.pageX, _.pageY);
        return false
    },
    loOl: function(J) {
        var C = this.owner,
        I = this.menu,
        A = C[_getBottomColumns](),
        E = I[_getItems](),
        $ = J.item,
        _ = $._column,
        H = 0;
        for (var D = 0,
        B = E.length; D < B; D++) {
            var F = E[D];
            if (F[_getChecked]()) H++
        }
        if (H < 1) $[_setChecked](true);
        var G = $[_getChecked]();
        if (G) C.showColumn(_);
        else C.hideColumn(_)
    }
};
mini._Grid_CellToolTip = function($) {
    this.owner = $;
    lo1l1o(this.owner.oOo1o0, "mousemove", this.__OnGridMouseMove, this)
};
mini._Grid_CellToolTip[_prototype] = {
    __OnGridMouseMove: function(D) {
        var $ = this.owner,
        A = $.O0OO(D),
        _ = $.lll1O1(A[0], A[1]),
        B = $.getCellError(A[0], A[1]);
        if (_) {
            if (B) {
                _.title = B.errorText;
                return
            }
            if (_.firstChild) if (ooll(_.firstChild, "mini-grid-cell-inner")) _ = _.firstChild;
            if (_.scrollWidth > _.clientWidth) {
                var C = _.innerText || _.textContent || "";
                _.title = C.trim()
            } else _.title = ""
        }
    }
};
mini._Grid_Sorter = function($) {
    this.owner = $;
    this.owner[_on]("headercellclick", this.__OnGridHeaderCellClick, this);
    lo1l1o($.ll0o, "mousemove", this.__OnGridHeaderMouseMove, this);
    lo1l1o($.ll0o, "mouseout", this.__OnGridHeaderMouseOut, this)
};
mini._Grid_Sorter[_prototype] = {
    __OnGridHeaderMouseOut: function($) {
        if (this.Ol11o0ColumnEl) o110(this.Ol11o0ColumnEl, "mini-grid-headerCell-hover")
    },
    __OnGridHeaderMouseMove: function(_) {
        var $ = ooOO(_.target, "mini-grid-headerCell");
        if ($) {
            o01O($, "mini-grid-headerCell-hover");
            this.Ol11o0ColumnEl = $
        }
    },
    __OnGridHeaderCellClick: function(B) {
        var $ = this.owner;
        if (!ooll(B.htmlEvent.target, "mini-grid-column-splitter")) if ($[_allowSortColumn] && $[_isEditing]() == false) {
            var _ = B.column;
            if (!_.columns || _.columns.length == 0) if (_.field && _.allowSort !== false) {
                var A = "asc";
                if ($[_getSortField]() == _.field) A = $[_getSortOrder]() == "asc" ? "desc": "asc";
                $[_sortBy](_.field, A)
            }
        }
    }
};
mini._Grid_ColumnMove = function($) {
    this.owner = $;
    lo1l1o(this.owner.el, "mousedown", this.llOol, this)
};
mini._Grid_ColumnMove[_prototype] = {
    llOol: function(B) {
        var $ = this.owner;
        if ($[_isEditing]()) return;
        if (ooll(B.target, "mini-grid-column-splitter")) return;
        if (B.button == mini.MouseButton.Right) return;
        var A = ooOO(B.target, $._headerCellCls);
        if (A) {
            this._remove();
            var _ = $.olll0(B);
            if ($[_allowMoveColumn] && _ && _.allowMove) {
                this.dragColumn = _;
                this._columnEl = A;
                this.getDrag().start(B)
            }
        }
    },
    getDrag: function() {
        if (!this.drag) this.drag = new mini.Drag({
            capture: false,
            onStart: mini.createDelegate(this.OO1l, this),
            onMove: mini.createDelegate(this.lO10O, this),
            onStop: mini.createDelegate(this.olO1, this)
        });
        return this.drag
    },
    OO1l: function(_) {
        function A(_) {
            var A = _.header;
            if (typeof A == "function") A = A[_call]($, _);
            if (mini.isNull(A) || A === "") A = "&nbsp;";
            return A
        }
        var $ = this.owner;
        this.oOO0 = mini.append(document.body, "<div class=\"mini-grid-columnproxy\"></div>");
        this.oOO0.innerHTML = "<div class=\"mini-grid-columnproxy-inner\" style=\"height:26px;\">" + A(this.dragColumn) + "</div>";
        mini[_setXY](this.oOO0, _.now[0] + 15, _.now[1] + 18);
        o01O(this.oOO0, "mini-grid-no");
        this.moveTop = mini.append(document.body, "<div class=\"mini-grid-movetop\"></div>");
        this.moveBottom = mini.append(document.body, "<div class=\"mini-grid-movebottom\"></div>")
    },
    lO10O: function(A) {
        var $ = this.owner,
        G = A.now[0];
        mini[_setXY](this.oOO0, G + 15, A.now[1] + 18);
        this.targetColumn = this.insertAction = null;
        var D = ooOO(A.event.target, $._headerCellCls);
        if (D) {
            var C = $.olll0(A.event);
            if (C && C != this.dragColumn) {
                var _ = $[_getParentColumn](this.dragColumn),
                E = $[_getParentColumn](C);
                if (_ == E) {
                    this.targetColumn = C;
                    this.insertAction = "before";
                    var F = $[_getColumnBox](this.targetColumn);
                    if (G > F.x + F.width / 2) this.insertAction = "after"
                }
            }
        }
        if (this.targetColumn) {
            o01O(this.oOO0, "mini-grid-ok");
            o110(this.oOO0, "mini-grid-no");
            var B = $[_getColumnBox](this.targetColumn);
            this.moveTop.style.display = "block";
            this.moveBottom.style.display = "block";
            if (this.insertAction == "before") {
                mini[_setXY](this.moveTop, B.x - 4, B.y - 9);
                mini[_setXY](this.moveBottom, B.x - 4, B.bottom)
            } else {
                mini[_setXY](this.moveTop, B.right - 4, B.y - 9);
                mini[_setXY](this.moveBottom, B.right - 4, B.bottom)
            }
        } else {
            o110(this.oOO0, "mini-grid-ok");
            o01O(this.oOO0, "mini-grid-no");
            this.moveTop.style.display = "none";
            this.moveBottom.style.display = "none"
        }
    },
    _remove: function() {
        var $ = this.owner;
        mini[_removeNode](this.oOO0);
        mini[_removeNode](this.moveTop);
        mini[_removeNode](this.moveBottom);
        this.oOO0 = this.moveTop = this.moveBottom = this.dragColumn = this.targetColumn = null
    },
    olO1: function(_) {
        var $ = this.owner;
        $[_moveColumn](this.dragColumn, this.targetColumn, this.insertAction);
        this._remove()
    }
};
mini._Grid_ColumnSplitter = function($) {
    this.owner = $;
    lo1l1o($.el, "mousedown", this.Oo00O, this)
};
mini._Grid_ColumnSplitter[_prototype] = {
    Oo00O: function(B) {
        var $ = this.owner,
        A = B.target;
        if (ooll(A, "mini-grid-column-splitter")) {
            var _ = $.ool0(A.id);
            if ($[_isEditing]()) return;
            if ($[_allowResizeColumn] && _ && _[_allowResize]) {
                this.splitterColumn = _;
                this.getDrag().start(B)
            }
        }
    },
    getDrag: function() {
        if (!this.drag) this.drag = new mini.Drag({
            capture: true,
            onStart: mini.createDelegate(this.OO1l, this),
            onMove: mini.createDelegate(this.lO10O, this),
            onStop: mini.createDelegate(this.olO1, this)
        });
        return this.drag
    },
    OO1l: function(_) {
        var $ = this.owner,
        B = $[_getColumnBox](this.splitterColumn);
        this.columnBox = B;
        this.oOO0 = mini.append(document.body, "<div class=\"mini-grid-proxy\"></div>");
        var A = $.getGridViewBox();
        A.x = B.x;
        A.width = B.width;
        A.right = B.right;
        oo1lo1(this.oOO0, A)
    },
    lO10O: function(A) {
        var $ = this.owner,
        B = mini.copyTo({},
        this.columnBox),
        _ = B.width + (A.now[0] - A.init[0]);
        if (_ < $.columnMinWidth) _ = $.columnMinWidth;
        if (_ > $.columnMaxWidth) _ = $.columnMaxWidth;
        l10l(this.oOO0, _)
    },
    olO1: function(E) {
        var $ = this.owner,
        F = O11O(this.oOO0),
        D = this,
        C = $[_allowSortColumn];
        $[_allowSortColumn] = false;
        setTimeout(function() {
            jQuery(D.oOO0).remove();
            D.oOO0 = null;
            $[_allowSortColumn] = C
        },
        10);
        var G = this.splitterColumn,
        _ = parseInt(G.width);
        if (_ + "%" != G.width) {
            var A = $[_getColumnWidth](G),
            B = parseInt(_ / A * F.width);
            $[_setColumnWidth](G, B)
        }
    }
};
mini._Grid_DragDrop = function($) {
    this.owner = $;
    this.owner[_on]("CellMouseDown", this.__OnGridCellMouseDown, this)
};
mini._Grid_DragDrop[_prototype] = {
    __OnGridCellMouseDown: function(C) {
        if (C.htmlEvent.button == mini.MouseButton.Right) return;
        var $ = this.owner;
        this.dropObj = $;
        if (ooOO(C.htmlEvent.target, "mini-tree-editinput")) return;
        if ($[_isReadOnly]() || $[_isAllowDrag](C.record, C.column) == false) return;
        var B = $.OO1l(C.record, C.column);
        if (B.cancel) return;
        this.dragText = B.dragText;
        var _ = C.record;
        this.isTree = !!$.isTree;
        this.beginRecord = _;
        var A = this.ol01O();
        A.start(C.htmlEvent)
    },
    OO1l: function(A) {
        var $ = this.owner;
        $._dragging = true;
        var _ = this.beginRecord;
        this.dragData = $.ol01OData();
        if (this.dragData[_indexOf](_) == -1) this.dragData.push(_);
        this.feedbackEl = mini.append(document.body, "<div class=\"mini-feedback\"></div>");
        this.feedbackEl.innerHTML = this.dragText;
        this.lastFeedbackClass = "";
        this[_enableHotTrack] = $[_getEnableHotTrack]();
        $[_setEnableHotTrack](false)
    },
    _getDropTargetObj: function(_) {
        var $ = ooOO(_.target, "mini-grid", 500);
        if ($) return mini.get($)
    },
    lO10O: function(_) {
        var $ = this.owner,
        D = this._getDropTargetObj(_.event);
        this.dropObj = D;
        var C = _.now[0],
        B = _.now[1];
        mini[_setXY](this.feedbackEl, C + 15, B + 18);
        if (D) {
            this.isTree = D.isTree;
            var A = D.lo0O(_.event);
            this.dropRecord = A;
            if (A) {
                if (this.isTree) this.dragAction = this.getFeedback(A, B, 3);
                else this.dragAction = this.getFeedback(A, B, 2)
            } else this.dragAction = "no"
        } else this.dragAction = "no";
        this.lastFeedbackClass = "mini-feedback-" + this.dragAction;
        this.feedbackEl.className = "mini-feedback " + this.lastFeedbackClass;
        if (this.dragAction == "no") A = null;
        this.setRowFeedback(A, this.dragAction)
    },
    olO1: function(B) {
        var H = this.owner,
        G = this.dropObj;
        H._dragging = false;
        mini[_removeNode](this.feedbackEl);
        H[_setEnableHotTrack](this[_enableHotTrack]);
        this.feedbackEl = null;
        this.setRowFeedback(null);
        if (this.isTree) {
            var J = [];
            for (var I = 0,
            F = this.dragData.length; I < F; I++) {
                var L = this.dragData[I],
                C = false;
                for (var K = 0,
                A = this.dragData.length; K < A; K++) {
                    var E = this.dragData[K];
                    if (E != L) {
                        C = H.isAncestor(E, L);
                        if (C) break
                    }
                }
                if (!C) J.push(L)
            }
            this.dragData = J
        }
        if (this.dropRecord && G && this.dragAction != "no") {
            var M = H.o00lO(this.dragData, this.dropRecord, this.dragAction);
            if (!M.cancel) {
                var J = M.dragNodes,
                D = M.targetNode,
                _ = M.action;
                if (G.isTree) {
                    if (H == G) G.moveNodes(J, D, _);
                    else {
                        H.removeNodes(J);
                        G.addNodes(J, D, _)
                    }
                } else {
                    var $ = G[_indexOf](D);
                    if (_ == "after") $ += 1;
                    G.moveRow(J, $)
                }
                M = {
                    dragNode: M.dragNodes[0],
                    dropNode: M.targetNode,
                    dragAction: M.action,
                    dragNodes: M.dragNodes,
                    targetNode: M.targetNode
                };
                G[_fire]("drop", M)
            }
        }
        this.dropRecord = null;
        this.dragData = null
    },
    setRowFeedback: function(_, F) {
        var $ = this.owner,
        E = this.dropObj;
        if (this.lastAddDomRow && E) E[_removeRowCls](this.lastAddDomRow, "mini-tree-feedback-add");
        if (_ == null || this.dragAction == "add") {
            mini[_removeNode](this.feedbackLine);
            this.feedbackLine = null
        }
        this.lastRowFeedback = _;
        if (_ != null) if (F == "before" || F == "after") {
            if (!this.feedbackLine) this.feedbackLine = mini.append(document.body, "<div class='mini-feedback-line'></div>");
            this.feedbackLine.style.display = "block";
            var C = E[_getRowBox](_),
            D = C.x,
            B = C.y - 1;
            if (F == "after") B += C.height;
            mini[_setXY](this.feedbackLine, D, B);
            var A = E[_getBox](true);
            l10l(this.feedbackLine, A.width)
        } else {
            E[_addRowCls](_, "mini-tree-feedback-add");
            this.lastAddDomRow = _
        }
    },
    getFeedback: function(K, I, F) {
        var D = this.owner,
        C = this.dropObj,
        J = C[_getRowBox](K),
        $ = J.height,
        H = I - J.y,
        G = null;
        if (this.dragData[_indexOf](K) != -1) return "no";
        var A = false;
        if (F == 3) {
            A = C.isLeaf(K);
            for (var E = 0,
            B = this.dragData.length; E < B; E++) {
                var L = this.dragData[E],
                _ = C.isAncestor(L, K);
                if (_) {
                    G = "no";
                    break
                }
            }
        }
        if (G == null) if (F == 2) {
            if (H > $ / 2) G = "after";
            else G = "before"
        } else if (A && C.allowLeafDropIn === false) {
            if (H > $ / 2) G = "after";
            else G = "before"
        } else if (H > ($ / 3) * 2) G = "after";
        else if ($ / 3 <= H && H <= ($ / 3 * 2)) G = "add";
        else G = "before";
        var M = C.oOOo(G, this.dragData, K);
        return M.effect
    },
    ol01O: function() {
        if (!this.drag) this.drag = new mini.Drag({
            onStart: mini.createDelegate(this.OO1l, this),
            onMove: mini.createDelegate(this.lO10O, this),
            onStop: mini.createDelegate(this.olO1, this)
        });
        return this.drag
    }
};
mini._Grid_Events = function($) {
    this.owner = $,
    el = $.el;
    lo1l1o(el, "click", this.O0O1l, this);
    lo1l1o(el, "dblclick", this.loO1oO, this);
    lo1l1o(el, "mousedown", this.Oo00O, this);
    lo1l1o(el, "mouseup", this.o110oo, this);
    lo1l1o(el, "mousemove", this.l011, this);
    lo1l1o(el, "mouseover", this.Oll1, this);
    lo1l1o(el, "mouseout", this.oll1OO, this);
    lo1l1o(el, "keydown", this.lO1O1O, this);
    lo1l1o(el, "keyup", this.O1O11, this);
    lo1l1o(el, "contextmenu", this.oOl0O, this)
};
mini._Grid_Events[_prototype] = {
    O0O1l: function($) {
        this.O0Ol($, "Click")
    },
    loO1oO: function($) {
        this.O0Ol($, "Dblclick")
    },
    Oo00O: function($) {
        if (ooOO($.target, "mini-tree-editinput")) return;
        this.O0Ol($, "MouseDown");
        this.owner[_tryFocus]($)
    },
    o110oo: function($) {
        if (ooOO($.target, "mini-tree-editinput")) return;
        if (lolo(this.el, $.target)) {
            this.owner[_tryFocus]($);
            this.O0Ol($, "MouseUp")
        }
    },
    l011: function($) {
        this.O0Ol($, "MouseMove")
    },
    Oll1: function($) {
        this.O0Ol($, "MouseOver")
    },
    oll1OO: function($) {
        this.O0Ol($, "MouseOut")
    },
    lO1O1O: function($) {
        this.O0Ol($, "KeyDown")
    },
    O1O11: function($) {
        this.O0Ol($, "KeyUp")
    },
    oOl0O: function($) {
        this.O0Ol($, "ContextMenu")
    },
    O0Ol: function(G, E) {
        var $ = this.owner,
        D = $.O0OO(G),
        A = D[0],
        C = D[1];
        if (A) {
            var B = {
                record: A,
                row: A,
                htmlEvent: G
            },
            F = $["_OnRow" + E];
            if (F) F[_call]($, B);
            else $[_fire]("row" + E, B)
        }
        if (C) {
            B = {
                column: C,
                field: C.field,
                htmlEvent: G
            },
            F = $["_OnColumn" + E];
            if (F) F[_call]($, B);
            else $[_fire]("column" + E, B)
        }
        if (A && C) {
            B = {
                sender: $,
                record: A,
                row: A,
                column: C,
                field: C.field,
                htmlEvent: G
            },
            F = $["_OnCell" + E];
            if (F) F[_call]($, B);
            else $[_fire]("cell" + E, B);
            if (C["onCell" + E]) C["onCell" + E][_call](C, B)
        }
        if (!A && C) {
            B = {
                column: C,
                htmlEvent: G
            },
            F = $["_OnHeaderCell" + E];
            if (F) F[_call]($, B);
            else {
                var _ = "onheadercell" + E.toLowerCase();
                if (C[_]) {
                    B.sender = $;
                    C[_](B)
                }
                $[_fire]("headercell" + E, B)
            }
        }
    }
};
OOOOOO = function($) {
    OOOOOO[_superclass][_constructor][_call](this, $);
    this._Events = new mini._Grid_Events(this);
    this.lolO0 = new mini._GridlolO0(this);
    this._DragDrop = new mini._Grid_DragDrop(this);
    this._RowGroup = new mini._Grid_RowGroup(this);
    this.Ol0oO0 = new mini._Grid_ColumnSplitter(this);
    this._ColumnMove = new mini._Grid_ColumnMove(this);
    this._Sorter = new mini._Grid_Sorter(this);
    this._CellToolTip = new mini._Grid_CellToolTip(this);
    this.O0111lMenu = new mini._GridO0111lMenu(this);
    this.looolOs()
};
lol01(OOOOOO, mini.ScrollGridView, {
    uiCls: "mini-datagrid",
    selectOnLoad: false,
    showHeader: false,
    showPager: true,
    allowUnselect: false,
    allowRowSelect: true,
    allowCellSelect: false,
    allowCellEdit: false,
    cellEditAction: "cellclick",
    allowCellValid: false,
    allowResizeColumn: true,
    allowSortColumn: true,
    allowMoveColumn: true,
    showColumnsMenu: false,
    virtualScroll: false,
    enableHotTrack: true,
    showLoading: true,
    loo10: true,
    loo0l1: null,
    Ol1O: null,
    editNextOnEnterKey: false,
    editOnTabKey: true,
    createOnEnter: false,
    autoHideRowDetail: true,
    allowDrag: false,
    allowDrop: false,
    allowLeafDropIn: false,
    pageSize: 20,
    pageIndex: 0,
    totalCount: 0,
    totalPage: 0,
    sortField: "",
    sortOrder: "",
    url: "",
    headerContextMenu: null
});
lOlll = OOOOOO[_prototype];
lOlll[_getAttrs] = l000o;
lOlll[_set_autoCreateNewID] = O1ooo;
lOlll._seto0Oo = oOO0l;
lOlll._setlO01 = Oo010;
lOlll._setlollO = oO1ll;
lOlll._getlollO = Oo00o;
lOlll[_getHeaderContextMenu] = Ol00;
lOlll[_setHeaderContextMenu] = ll0oO;
lOlll.O01o = oO01;
lOlll[_beforeOpenContentMenu] = OooOl;
lOlll[_setPagerCls] = lol00;
lOlll[_setPagerStyle] = lo011;
lOlll[_getShowTotalCount] = OllO0;
lOlll[_setShowTotalCount] = oO1o1;
lOlll[_getShowPageIndex] = o1loo;
lOlll[_setShowPageIndex] = lo00O1;
lOlll[_getShowPageSize] = OO0Oo;
lOlll[_setShowPageSize] = l0oO0;
lOlll[_getSizeList] = oOo01;
lOlll[_setSizeList] = o1olO;
lOlll[_getShowPageInfo] = l1o10;
lOlll[_setShowPageInfo] = o0lOO;
lOlll[_getShowReloadButton] = Oo0Ol;
lOlll[_setShowReloadButton] = Ol1l;
lOlll[_getDataField] = OOl1oo;
lOlll[_setDataField] = l0llo;
lOlll[_getTotalField] = l1llo;
lOlll[_setTotalField] = Oll01;
lOlll[_getSortOrderField] = l01l;
lOlll[_setSortOrderField] = O1oO0;
lOlll[_getSortFieldField] = OlO0;
lOlll[_setSortFieldField] = lol0l;
lOlll[_getPageSizeField] = l00lo;
lOlll[_setPageSizeField] = o10l1l;
lOlll[_getPageIndexField] = O1oOo;
lOlll[_setPageIndexField] = l0o1o;
lOlll[_getSortOrder] = l0Oll;
lOlll[_setSortOrder] = O110;
lOlll[_getSortField] = o0Oo0;
lOlll[_setSortField] = oo111;
lOlll[_getTotalPage] = l0o1O;
lOlll[_getTotalCount] = olO0o;
lOlll[_setTotalCount] = ll0lo;
lOlll[_getPageSize] = lo10o;
lOlll[_setPageSize] = OO00;
lOlll[_getPageIndex] = OOl0o;
lOlll[_setPageIndex] = ol1ll;
lOlll[_getSortMode] = O1O10;
lOlll[_setSortMode] = OO10l;
lOlll[_getSelectOnLoad] = OoO00;
lOlll[_setSelectOnLoad] = oo1l10;
lOlll[_getCheckSelectOnLoad] = O0o0l;
lOlll[_setCheckSelectOnLoad] = O1Ooo;
lOlll[_sortBy] = Oo01O;
lOlll[_gotoPage] = l0l1;
lOlll[_reload] = l0ol0;
lOlll[_load] = o0oO0;
lOlll[_getUrl] = ol0Ol0;
lOlll[_setUrl] = lloOo;
lOlll[_getAutoLoad] = O1lo1O;
lOlll[_setAutoLoad] = O1ol;
lOlll[_getAjaxOptions] = o1olo;
lOlll[_setAjaxOptions] = lol1o;
lOlll[_getAjaxMethod] = oOOOo;
lOlll[_setAjaxMethod] = o1l0o;
lOlll[_getAjaxAsync] = oOooO;
lOlll[_setAjaxAsync] = l0l0l;
lOlll[_moveDown] = l0olO;
lOlll[_moveUp] = o01Ol;
lOlll.o00lO = Ol0ll;
lOlll.oOOo = loO0o0;
lOlll.OO1l = lO00l;
lOlll[_isAllowDrag] = Ooo1O;
lOlll[_getAllowDrop] = lOo1o;
lOlll[_setAllowDrop] = OOoO1;
lOlll[_getAllowDrag] = lO111;
lOlll[_setAllowDrag] = O1100;
lOlll[_getAllowLeafDropIn] = lOolO;
lOlll[_setAllowLeafDropIn] = O1olo;
lOlll.ol01OText = O0ll0;
lOlll.ol01OData = o0o00;
lOlll.oool = OoOOl;
lOlll[_isCellVisible] = o10o1;
lOlll[_margeCells] = oOll10;
lOlll[_mergeCells] = o11l1;
lOlll[_mergeColumns] = l101o;
lOlll[_getAutoHideRowDetail] = lO1oO;
lOlll[_setAutoHideRowDetail] = Ol101;
lOlll[_getRowDetailCellEl] = l0Ol1;
lOlll.O0lo = Olo0O;
lOlll.OloO = l1Oo;
lOlll[_getRowDetailEl] = o1oolO;
lOlll[_hideRowDetail] = oOOl00;
lOlll[_showRowDetail] = O000;
lOlll[_toggleRowDetail] = Ol0Ol;
lOlll[_isShowRowDetail] = o0ool0;
lOlll[_hideAllRowDetail] = OlO00;
lOlll[_showAllRowDetail] = Oooo0;
lOlll[_expandRowGroup] = ll1Oo;
lOlll[_collapseRowGroup] = Oll0l;
lOlll[_toggleRowGroup] = Olol1;
lOlll[_expandGroups] = ol00o;
lOlll[_collapseGroups] = o11oO;
lOlll[_getEditRowData] = OO0l0;
lOlll[_getEditData] = Ol1lO0;
lOlll[_getEditingRow] = loo1O;
lOlll[_getEditingRows] = loo1Os;
lOlll[_isNewRow] = oOOll;
lOlll[_isEditingRow] = ll10o;
lOlll[_isEditing] = lo0OO;
lOlll[_commitEditRow] = o1o001;
lOlll[_cancelEditRow] = OOl10;
lOlll[_beginEditRow] = l00l1;
lOlll[_getEditorOwnerRow] = OOlO;
lOlll[_beginEditNextCell] = o0l0l;
lOlll.o1lll = lOOol;
lOlll.o0oO1l = o001o;
lOlll.Ool1O = oo0lo;
lOlll.ol1l = llo01;
lOlll.l0oO = olO10;
lOlll.oo10o = l1o0;
lOlll.o00l = ll0ol;
lOlll[_getCellEditor] = ll0lll;
lOlll[_commitEdit] = O00o0;
lOlll[_cancelEdit] = Ooll0l;
lOlll[_beginEditCell] = l0oo1;
lOlll[_isEditingCell] = lo0OOCell;
lOlll[_getCurrentCell] = o1o10;
lOlll[_setCurrentCell] = Oo1Oo;
lOlll.O1lo = lO1OO;
lOlll[_getCreateOnEnter] = olO00;
lOlll[_setCreateOnEnter] = OooOo;
lOlll[_getEditOnTabKey] = OOoo;
lOlll[_setEditOnTabKey] = OOlll;
lOlll[_getEditNextOnEnterKey] = O0oO0;
lOlll[_setEditNextOnEnterKey] = oo0oo;
lOlll[_getShowColumnsMenu] = OOlO1;
lOlll[_setShowColumnsMenu] = o0Ol;
lOlll[_getAllowMoveColumn] = ooooo;
lOlll[_setAllowMoveColumn] = Oolll;
lOlll[_getAllowSortColumn] = l1O10;
lOlll[_setAllowSortColumn] = o0olO;
lOlll[_getAllowResizeColumn] = OolOl;
lOlll[_setAllowResizeColumn] = OOOO0;
lOlll[_getAllowCellValid] = O0lOl;
lOlll[_setAllowCellValid] = OOloOo;
lOlll[_getCellEditAction] = o11O;
lOlll[_setCellEditAction] = O1oo0;
lOlll[_getAllowCellEdit] = l0O11;
lOlll[_setAllowCellEdit] = oOolo;
lOlll[_getAllowCellSelect] = Oo0oO;
lOlll[_setAllowCellSelect] = lOol1o;
lOlll[_getAllowRowSelect] = Ooo11;
lOlll[_setAllowRowSelect] = ol00;
lOlll[_getAllowUnselect] = o1011;
lOlll[_setAllowUnselect] = O0O11;
lOlll[_getEnableHotTrack] = O1l0O;
lOlll[_setEnableHotTrack] = o11lo;
lOlll[_getShowLoading] = olloO;
lOlll[_setShowLoading] = oO0oo;
lOlll[_scrollIntoView] = l0O0;
lOlll[_focusRow] = o0o01;
lOlll[_focus] = loll11;
lOlll[_tryFocus] = O0O0o;
lOlll[_doRowSelect] = Oo1O1;
lOlll[_getRowBox] = o11O0l;
lOlll[_getColumnBox] = lolo1;
lOlll[_getCellBox] = OOO10;
lOlll[_removeRowCls] = OOlOOo;
lOlll[_addRowCls] = ool01;
lOlll.ool0 = oO01O;
lOlll[_getRowByID] = O01ol;
lOlll.O0OO = oOl1O;
lOlll.olll0 = Ool0o;
lOlll.lo0O = lo0l0;
lOlll.lll1O1 = OOO01;
lOlll.lOO1 = O01oOo;
lOlll.Oo1l = llll0O;
lOlll[_getRowGroupRowsEl] = o01l0;
lOlll[_getRowGroupEl] = l1oo1;
lOlll[_doMoveRowEl] = lOlo1;
lOlll[_doRemoveRowEl] = O0O10;
lOlll[_doAddRowEl] = oo0O1;
lOlll.OoOlEl = o1010;
lOlll.lO11 = lOlOo;
lOlll[_unbindPager] = O011l;
lOlll[_bindPager] = o0111;
lOlll[_setPager] = o00o1;
lOlll[_updatePagesInfo] = OOlol;
lOlll[__OnPageInfoChanged] = O0011o;
lOlll.oO0l1 = lOlo0;
lOlll[__OnSourceMove] = OO011;
lOlll[__OnSourceRemove] = o0110;
lOlll[__OnSourceUpdate] = olooo;
lOlll[__OnSourceAdd] = O1O1O;
lOlll[__OnSourceFilter] = o110O;
lOlll[__OnSourceSort] = l010o;
lOlll[__OnSourceLoadError] = o1l11;
lOlll[__OnSourceLoadSuccess] = oo10O;
lOlll[__OnSourcePreLoad] = ol11;
lOlll[__OnSourceBeforeLoad] = ooolO;
lOlll[_initData] = OooO;
lOlll.oO0O = lOllO;
lOlll.OollO = Olool;
lOlll[_destroyEditors] = ll001;
lOlll[_doUpdate] = O1Oo0;
lOlll[_set] = O1oo1;
O1o0(OOOOOO, "datagrid");
OOOOOO_CellValidator_Prototype = {
    getCellErrors: function() {
        var A = this._cellErrors.clone(),
        C = this.getDataView();
        for (var $ = 0,
        D = A.length; $ < D; $++) {
            var E = A[$],
            _ = E.record,
            B = E.column;
            if (C[_indexOf](_) == -1) {
                var F = _[this._rowIdField] + "$" + B._id;
                delete this._cellMapErrors[F];
                this._cellErrors.remove(E)
            }
        }
        return this._cellErrors
    },
    getCellError: function($, _) {
        $ = this[_getNode] ? this[_getNode]($) : this[_getRow]($);
        _ = this[_getColumn](_);
        if (!$ || !_) return;
        var A = $[this._rowIdField] + "$" + _._id;
        return this._cellMapErrors[A]
    },
    isValid: function() {
        return this.getCellErrors().length == 0
    },
    validate: function() {
        var A = this.getDataView();
        for (var $ = 0,
        B = A.length; $ < B; $++) {
            var _ = A[$];
            this.validateRow(_)
        }
    },
    validateRow: function(_) {
        var B = this[_getBottomColumns]();
        for (var $ = 0,
        C = B.length; $ < C; $++) {
            var A = B[$];
            this.validateCell(_, A)
        }
    },
    validateCell: function(C, E) {
        C = this[_getNode] ? this[_getNode](C) : this[_getRow](C);
        E = this[_getColumn](E);
        if (!C || !E) return;
        var I = {
            record: C,
            row: C,
            node: C,
            column: E,
            field: E.field,
            value: C[E.field],
            isValid: true,
            errorText: ""
        };
        if (E.vtype) mini.loo1o1(E.vtype, I.value, I, E);
        if (I[_isValid] == true && E.unique && E.field) {
            var A = {},
            D = this.data,
            F = E.field;
            for (var _ = 0,
            G = D.length; _ < G; _++) {
                var $ = D[_],
                H = $[F];
                if (mini.isNull(H) || H === "");
                else {
                    var B = A[H];
                    if (B && $ == C) {
                        I[_isValid] = false;
                        I.errorText = mini.o00oO(E, "uniqueErrorText");
                        this.setCellIsValid(B, E, I.isValid, I.errorText);
                        break
                    }
                    A[H] = $
                }
            }
        }
        this[_fire]("cellvalidation", I);
        this.setCellIsValid(C, E, I.isValid, I.errorText)
    },
    setIsValid: function(_) {
        if (_) {
            var A = this._cellErrors.clone();
            for (var $ = 0,
            B = A.length; $ < B; $++) {
                var C = A[$];
                this.setCellIsValid(C.record, C.column, true)
            }
        }
    },
    _removeRowError: function(_) {
        var B = this[_getColumns]();
        for (var $ = 0,
        C = B.length; $ < C; $++) {
            var A = B[$],
            E = _[this._rowIdField] + "$" + A._id,
            D = this._cellMapErrors[E];
            if (D) {
                delete this._cellMapErrors[E];
                this._cellErrors.remove(D)
            }
        }
    },
    setCellIsValid: function(_, A, B, D) {
        _ = this[_getRow](_);
        A = this[_getColumn](A);
        if (!_ || !A) return;
        var E = _[this._rowIdField] + "$" + A._id,
        $ = this.lll1O1(_, A),
        C = this._cellMapErrors[E];
        delete this._cellMapErrors[E];
        this._cellErrors.remove(C);
        if (B === true) {
            if ($ && C) o110($, "mini-grid-cell-error")
        } else {
            C = {
                record: _,
                column: A,
                isValid: B,
                errorText: D
            };
            this._cellMapErrors[E] = C;
            this._cellErrors[_add](C);
            if ($) o01O($, "mini-grid-cell-error")
        }
    }
};
mini.copyTo(OOOOOO.prototype, OOOOOO_CellValidator_Prototype);
o110l1 = function($) {
    o110l1[_superclass][_constructor][_call](this, $);
    o01O(this.el, "mini-tree");
    this[_setAjaxAsync](false);
    this[_setAutoLoad](true);
    if (this[_showTreeLines] == true) o01O(this.el, "mini-tree-treeLine");
    this._AsyncLoader = new mini._Tree_AsyncLoader(this);
    this._Expander = new mini._Tree_Expander(this)
};
mini.copyTo(o110l1.prototype, mini._DataTreeApplys);
lol01(o110l1, OOOOOO, {
    isTree: true,
    uiCls: "mini-treegrid",
    showPager: false,
    showNewRow: false,
    showCheckBox: false,
    showTreeIcon: true,
    showExpandButtons: true,
    showTreeLines: false,
    showArrow: false,
    expandOnDblClick: true,
    expandOnNodeClick: false,
    loadOnExpand: true,
    _checkBoxType: "checkbox",
    iconField: "iconCls",
    _treeColumn: null,
    leafIconCls: "mini-tree-leaf",
    folderIconCls: "mini-tree-folder",
    fixedRowHeight: false,
    l0ll: "mini-tree-checkbox",
    looo0: "mini-tree-expand",
    oOloo: "mini-tree-collapse",
    l1ll: "mini-tree-node-ecicon",
    OoooOl: "mini-tree-nodeshow"
});
loOoO = o110l1[_prototype];
loOoO[_getAttrs] = olO1O;
loOoO[_disableNode] = Oooll;
loOoO[_enableNode] = lll1O;
loOoO[_showNode] = Ol1oO;
loOoO[_hideNode] = OlO1l;
loOoO[_getLoadOnExpand] = Oo0lo;
loOoO[_setLoadOnExpand] = l1ol0;
loOoO[_getExpandOnNodeClick] = o00O0;
loOoO[_setExpandOnNodeClick] = lo001;
loOoO[_getExpandOnDblClick] = oOoO0;
loOoO[_getFolderIcon] = o1l1O;
loOoO[_setFolderIcon] = Ol1O0;
loOoO[_getLeafIcon] = l100O;
loOoO[_setLeafIcon] = Oo11l;
loOoO[_getShowArrow] = O011O;
loOoO[_setShowArrow] = o0o1l;
loOoO[_getShowTreeLines] = o1100;
loOoO[_setShowTreeLines] = l10oO;
loOoO[_getShowExpandButtons] = ol0o0;
loOoO[_setShowExpandButtons] = OlO10;
loOoO[_getAllowSelect] = o1o00;
loOoO[_setAllowSelect] = ll10l;
loOoO[_getIconField] = l1loo;
loOoO[_setIconField] = oOoO1;
loOoO.OO1oo = OOloO;
loOoO[__OnNodeDblClick] = o1O1o;
loOoO[_OnCellClick] = O001o;
loOoO[_OnCellMouseDown] = OO0OO;
loOoO[_tryToggleNode] = Ol0l0;
loOoO[_tryToggleCheckNode] = O1OoO;
loOoO[__OnCheckChanged] = OOOO1;
loOoO[_doCheckNodeEl] = lO000;
loOoO.oolOo = lo0lo;
loOoO.oool0 = lo0O0;
loOoO[_doExpandCollapseNode] = ol0OO;
loOoO.Ol0OO = l1l1lo;
loOoO[_getNodeIcon] = lo0l1;
loOoO[_getIconsField] = l1l11;
loOoO[_setIconsField] = Ool0l;
loOoO[_getCheckBoxType] = oloOO;
loOoO[_setCheckBoxType] = ooo1;
loOoO[_getShowCheckBox] = oolOOo;
loOoO[_setShowCheckBox] = lll00;
loOoO[_getShowTreeIcon] = OO0Ol;
loOoO[_setShowTreeIcon] = OoOOO;
loOoO[_getTreeColumn] = oooo0;
loOoO[_setTreeColumn] = oo01O;
loOoO[_getNodesTr] = O1Ol1;
loOoO.Ollo1O = l0O1OO;
loOoO[_getNodeEl] = OO00l;
loOoO.O1Ol1l = O11lO;
loOoO.o1o1lsHTML = Oo1l0;
loOoO.olOOoHTML = ol10oo;
loOoO.OOlO0HTML = O0olO;
loOoO[_renderCheckState] = oooOO;
loOoO.O1l1 = lOOOO;
loOoO[_createTreeColumn] = OO1oO;
loOoO.lloO = loool;
loOoO[_isInLastNode] = lo1l10;
loOoO[_isInViewLastNode] = l1lO0;
loOoO[_isViewLastNode] = O11lo;
loOoO[_isViewFirstNode] = lOlO;
loOoO.OollO = o0ooO;
loOoO[_createDrawCellEvent] = l1l1O;
loOoO.loO11o = OllOo;
loOoO[_loadList] = o1O1O;
loOoO[_setData] = Ol10o;
loOoO[_doUpdate] = l1oO;
loOoO[_removeNodeCls] = OoOll;
loOoO[_addNodeCls] = l0ool;
loOoO[_doUpdateTreeNodeEl] = O1O0l;
loOoO.o1OO1 = l001o;
loOoO[_doMoveNodeEl] = o00l0;
loOoO[_doRemoveNodeEl] = Ol0O1;
loOoO[_doAddNodeEl] = o1OOl;
loOoO[__OnSourceMoveNode] = o00ll;
loOoO[__OnSourceRemoveNode] = oloO;
loOoO[__OnSourceAddNode] = OoooO;
loOoO[__OnLoadNode] = lO11O;
loOoO[__OnBeforeLoadNode] = OOl1l;
loOoO.oO0O = ol1o0;
loOoO[_createSource] = O1O1lO;
loOoO[_isGrouping] = OoOOo;
loOoO[_getText] = lO10;
loOoO[_getValue] = loOO0;
loOoO[_setValue] = O0loO;
loOoO[_initEvents] = Ol0o;
loOoO.ol01OText = ol0o1;
O1o0(o110l1, "TreeGrid");
O0l00l = function() {
    O0l00l[_superclass][_constructor][_call](this);
    var $ = [{
        name: "node",
        header: "",
        field: this[_getTextField](),
        width: "auto",
        allowDrag: true,
        editor: {
            type: "textbox"
        }
    }];
    this._columnModel[_setColumns]($);
    this._column = this._columnModel[_getColumn]("node");
    o110(this.el, "mini-treegrid");
    o01O(this.el, "mini-tree-nowrap");
    this[_setBorderStyle]("border:0")
};
lol01(O0l00l, o110l1, {
    uiCls: "mini-tree",
    ol1O0: "mini-tree-node-hover",
    l1O0: "mini-tree-selectedNode",
    _treeColumn: "node",
    defaultRowHeight: 22,
    showHeader: false,
    showTopbar: false,
    showFooter: false,
    showColumns: false,
    showHGridLines: false,
    showVGridLines: false,
    showTreeLines: true,
    setTreeColumn: null,
    setColumns: null,
    getColumns: null,
    frozen: null,
    unFrozen: null,
    showModified: false
});
o1oO1 = O0l00l[_prototype];
o1oO1[_removeRowCls] = o1lol1;
o1oO1[_addRowCls] = Ooll0O;
o1oO1.ooOO0o = l001l;
o1oO1.Oo0ll = O1O0Oo;
o1oO1[_cancelEdit] = olo0O;
o1oO1[_beginEdit] = oO0l;
o1oO1[_isEditingNode] = O0llO;
o1oO1[_OnCellMouseDown] = l11O0;
o1oO1[_setNodeIconCls] = loOo0;
o1oO1[_setNodeText] = oOlO;
o1oO1[_getRowHeight] = o00oo;
o1oO1.lo0O = oO00o;
o1oO1[_setTextField] = Ol00l;
O1o0(O0l00l, "Tree");
mini._Tree_Expander = function($) {
    this.owner = $;
    lo1l1o($.el, "click", this.O0O1l, this);
    lo1l1o($.el, "dblclick", this.loO1oO, this)
};
mini._Tree_Expander[_prototype] = {
    _canToggle: function() {
        return ! this.owner._dataSource._isNodeLoading()
    },
    O0O1l: function(B) {
        var _ = this.owner,
        $ = _.lo0O(B, false);
        if (!$ || $.enabled === false) return;
        if (ooOO(B.target, "mini-tree-checkbox")) return;
        var A = _.isLeaf($);
        if (ooOO(B.target, _.l1ll)) {
            if (this._canToggle() == false) return;
            _[_tryToggleNode]($)
        } else if (_.expandOnNodeClick && !A && !_.OlOol) {
            if (this._canToggle() == false) return;
            _[_tryToggleNode]($)
        }
    },
    loO1oO: function(B) {
        var _ = this.owner,
        $ = _.lo0O(B, false);
        if (!$ || $.enabled === false) return;
        var A = _.isLeaf($);
        if (_.OlOol) return;
        if (ooOO(B.target, _.l1ll)) return;
        if (_.expandOnNodeClick) return;
        if (_.expandOnDblClick && !A) {
            if (this._canToggle() == false) return;
            _[_tryToggleNode]($)
        }
    }
};
mini._Tree_AsyncLoader = function($) {
    this.owner = $;
    $[_on]("beforeexpand", this.__OnBeforeNodeExpand, this)
};
mini._Tree_AsyncLoader[_prototype] = {
    __OnBeforeNodeExpand: function(C) {
        var _ = this.owner,
        $ = C.node,
        B = _.isLeaf($),
        A = $[_[_getNodesField]()];
        if (!B && (!A || A.length == 0)) if (_.loadOnExpand && $.asyncLoad !== false) {
            C.cancel = true;
            _.loadNode($)
        }
    }
};
mini.RadioButtonList = oolO10,
mini.ValidatorBase = o0l01o,
mini.AutoComplete = olO1lo,
mini.CheckBoxList = o0O00l,
mini.DataBinding = O0o0l1,
mini.OutlookTree = Ol0O0o,
mini.OutlookMenu = o00lll,
mini.TextBoxList = oo10oo,
mini.TimeSpinner = OO1llo,
mini.ListControl = l0OlOo,
mini.OutlookBar = OlOOo,
mini.FileUpload = l1O00o,
mini.TreeSelect = oOO000,
mini.DatePicker = O0O0oO,
mini.ButtonEdit = Ol111o,
mini.MenuButton = l01O10,
mini.PopupEdit = ll01oO,
mini.Component = o111oO,
mini.TreeGrid = o110l1,
mini.DataGrid = OOOOOO,
mini.MenuItem = l0ooo1,
mini.Splitter = oo1lo,
mini.HtmlFile = oo0o1o,
mini.Calendar = OollOO,
mini.ComboBox = oO1lOo,
mini.TextArea = lOO0Oo,
mini.Password = l00o11,
mini.CheckBox = Oll1ll,
mini.DataSet = oOO0o1,
mini.Include = lllooO,
mini.Spinner = lo11O1,
mini.ListBox = o0lo1O,
mini.TextBox = o0olOl,
mini.Control = O11lO0,
mini.Layout = oOoOO,
mini.Window = oolo10,
mini.Lookup = Ool0O1,
mini.Button = OoOoOO,
mini.Hidden = l1OO0,
mini.Pager = loooo,
mini.Panel = ll00Oo,
mini.Popup = O1lo0o,
mini.Tree = O0l00l,
mini.Menu = l0oOlO,
mini.Tabs = ll0l1o,
mini.Fit = OO11O1,
mini.Box = o11O1O;
mini.locale = "en-US";
mini.dateInfo = {
    monthsLong: ["\u4e00\u6708", "\u4e8c\u6708", "\u4e09\u6708", "\u56db\u6708", "\u4e94\u6708", "\u516d\u6708", "\u4e03\u6708", "\u516b\u6708", "\u4e5d\u6708", "\u5341\u6708", "\u5341\u4e00\u6708", "\u5341\u4e8c\u6708"],
    monthsShort: ["1\u6708", "2\u6708", "3\u6708", "4\u6708", "5\u6708", "6\u6708", "7\u6708", "8\u6708", "9\u6708", "10\u6708", "11\u6708", "12\u6708"],
    daysLong: ["\u661f\u671f\u65e5", "\u661f\u671f\u4e00", "\u661f\u671f\u4e8c", "\u661f\u671f\u4e09", "\u661f\u671f\u56db", "\u661f\u671f\u4e94", "\u661f\u671f\u516d"],
    daysShort: ["\u65e5", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d"],
    quarterLong: ["\u4e00\u5b63\u5ea6", "\u4e8c\u5b63\u5ea6", "\u4e09\u5b63\u5ea6", "\u56db\u5b63\u5ea6"],
    quarterShort: ["Q1", "Q2", "Q2", "Q4"],
    halfYearLong: ["\u4e0a\u534a\u5e74", "\u4e0b\u534a\u5e74"],
    patterns: {
        "d": "yyyy-M-d",
        "D": "yyyy\u5e74M\u6708d\u65e5",
        "f": "yyyy\u5e74M\u6708d\u65e5 H:mm",
        "F": "yyyy\u5e74M\u6708d\u65e5 H:mm:ss",
        "g": "yyyy-M-d H:mm",
        "G": "yyyy-M-d H:mm:ss",
        "m": "MMMd\u65e5",
        "o": "yyyy-MM-ddTHH:mm:ss.fff",
        "s": "yyyy-MM-ddTHH:mm:ss",
        "t": "H:mm",
        "T": "H:mm:ss",
        "U": "yyyy\u5e74M\u6708d\u65e5 HH:mm:ss",
        "y": "yyyy\u5e74MM\u6708"
    },
    tt: {
        "AM": "\u4e0a\u5348",
        "PM": "\u4e0b\u5348"
    },
    ten: {
        "Early": "\u4e0a\u65ec",
        "Mid": "\u4e2d\u65ec",
        "Late": "\u4e0b\u65ec"
    },
    today: "\u4eca\u5929",
    clockType: 24
};
if (OollOO) mini.copyTo(OollOO.prototype, {
    firstDayOfWeek: 0,
    todayText: "\u4eca\u5929",
    clearText: "\u6e05\u9664",
    okText: "\u786e\u5b9a",
    cancelText: "\u53d6\u6d88",
    daysShort: ["\u65e5", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d"],
    format: "yyyy\u5e74MM\u6708",
    timeFormat: "H:mm"
});
for (var id in mini) {
    var clazz = mini[id];
    if (clazz && clazz[_prototype] && clazz[_prototype].isControl) clazz[_prototype][_requiredErrorText] = "\u4e0d\u80fd\u4e3a\u7a7a"
}
if (mini.VTypes) mini.copyTo(mini.VTypes, {
    uniqueErrorText: "\u5b57\u6bb5\u4e0d\u80fd\u91cd\u590d",
    requiredErrorText: "\u4e0d\u80fd\u4e3a\u7a7a",
    emailErrorText: "\u8bf7\u8f93\u5165\u90ae\u4ef6\u683c\u5f0f",
    urlErrorText: "\u8bf7\u8f93\u5165URL\u683c\u5f0f",
    floatErrorText: "\u8bf7\u8f93\u5165\u6570\u5b57",
    intErrorText: "\u8bf7\u8f93\u5165\u6574\u6570",
    dateErrorText: "\u8bf7\u8f93\u5165\u65e5\u671f\u683c\u5f0f {0}",
    maxLengthErrorText: "\u4e0d\u80fd\u8d85\u8fc7 {0} \u4e2a\u5b57\u7b26",
    minLengthErrorText: "\u4e0d\u80fd\u5c11\u4e8e {0} \u4e2a\u5b57\u7b26",
    maxErrorText: "\u6570\u5b57\u4e0d\u80fd\u5927\u4e8e {0} ",
    minErrorText: "\u6570\u5b57\u4e0d\u80fd\u5c0f\u4e8e {0} ",
    rangeLengthErrorText: "\u5b57\u7b26\u957f\u5ea6\u5fc5\u987b\u5728 {0} \u5230 {1} \u4e4b\u95f4",
    rangeCharErrorText: "\u5b57\u7b26\u6570\u5fc5\u987b\u5728 {0} \u5230 {1} \u4e4b\u95f4",
    rangeErrorText: "\u6570\u5b57\u5fc5\u987b\u5728 {0} \u5230 {1} \u4e4b\u95f4"
});
if (loooo) mini.copyTo(loooo.prototype, {
    firstText: "\u9996\u9875",
    prevText: "\u4e0a\u4e00\u9875",
    nextText: "\u4e0b\u4e00\u9875",
    lastText: "\u5c3e\u9875",
    pageInfoText: "\u6bcf\u9875 {0} \u6761,\u5171 {1} \u6761"
});
if (OOOOOO) mini.copyTo(OOOOOO.prototype, {
    emptyText: "\u6ca1\u6709\u8fd4\u56de\u7684\u6570\u636e"
});
if (l1O00o) l1O00o[_prototype].buttonText = "\u6d4f\u89c8...";
if (oo0o1o) oo0o1o[_prototype].buttonText = "\u6d4f\u89c8...";
if (window.mini.Gantt) {
    mini.GanttView.ShortWeeks = ["\u65e5", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d"];
    mini.GanttView.LongWeeks = ["\u661f\u671f\u65e5", "\u661f\u671f\u4e00", "\u661f\u671f\u4e8c", "\u661f\u671f\u4e09", "\u661f\u671f\u56db", "\u661f\u671f\u4e94", "\u661f\u671f\u516d"];
    mini.Gantt.PredecessorLinkType = [{
        ID: 0,
        Name: "\u5b8c\u6210-\u5b8c\u6210(FF)",
        Short: "FF"
    },
    {
        ID: 1,
        Name: "\u5b8c\u6210-\u5f00\u59cb(FS)",
        Short: "FS"
    },
    {
        ID: 2,
        Name: "\u5f00\u59cb-\u5b8c\u6210(SF)",
        Short: "SF"
    },
    {
        ID: 3,
        Name: "\u5f00\u59cb-\u5f00\u59cb(SS)",
        Short: "SS"
    }];
    mini.Gantt.ConstraintType = [{
        ID: 0,
        Name: "\u8d8a\u65e9\u8d8a\u597d"
    },
    {
        ID: 1,
        Name: "\u8d8a\u665a\u8d8a\u597d"
    },
    {
        ID: 2,
        Name: "\u5fc5\u987b\u5f00\u59cb\u4e8e"
    },
    {
        ID: 3,
        Name: "\u5fc5\u987b\u5b8c\u6210\u4e8e"
    },
    {
        ID: 4,
        Name: "\u4e0d\u5f97\u65e9\u4e8e...\u5f00\u59cb"
    },
    {
        ID: 5,
        Name: "\u4e0d\u5f97\u665a\u4e8e...\u5f00\u59cb"
    },
    {
        ID: 6,
        Name: "\u4e0d\u5f97\u65e9\u4e8e...\u5b8c\u6210"
    },
    {
        ID: 7,
        Name: "\u4e0d\u5f97\u665a\u4e8e...\u5b8c\u6210"
    }];
    mini.copyTo(mini.Gantt, {
        ID_Text: "\u6807\u8bc6\u53f7",
        Name_Text: "\u4efb\u52a1\u540d\u79f0",
        PercentComplete_Text: "\u8fdb\u5ea6",
        Duration_Text: "\u5de5\u671f",
        Start_Text: "\u5f00\u59cb\u65e5\u671f",
        Finish_Text: "\u5b8c\u6210\u65e5\u671f",
        Critical_Text: "\u5173\u952e\u4efb\u52a1",
        PredecessorLink_Text: "\u524d\u7f6e\u4efb\u52a1",
        Work_Text: "\u5de5\u65f6",
        Priority_Text: "\u91cd\u8981\u7ea7\u522b",
        Weight_Text: "\u6743\u91cd",
        OutlineNumber_Text: "\u5927\u7eb2\u5b57\u6bb5",
        OutlineLevel_Text: "\u4efb\u52a1\u5c42\u7ea7",
        ActualStart_Text: "\u5b9e\u9645\u5f00\u59cb\u65e5\u671f",
        ActualFinish_Text: "\u5b9e\u9645\u5b8c\u6210\u65e5\u671f",
        WBS_Text: "WBS",
        ConstraintType_Text: "\u9650\u5236\u7c7b\u578b",
        ConstraintDate_Text: "\u9650\u5236\u65e5\u671f",
        Department_Text: "\u90e8\u95e8",
        Principal_Text: "\u8d1f\u8d23\u4eba",
        Assignments_Text: "\u8d44\u6e90\u540d\u79f0",
        Summary_Text: "\u6458\u8981\u4efb\u52a1",
        Task_Text: "\u4efb\u52a1",
        Baseline_Text: "\u6bd4\u8f83\u57fa\u51c6",
        LinkType_Text: "\u94fe\u63a5\u7c7b\u578b",
        LinkLag_Text: "\u5ef6\u9694\u65f6\u95f4",
        From_Text: "\u4ece",
        To_Text: "\u5230",
        Goto_Text: "\u8f6c\u5230\u4efb\u52a1",
        UpGrade_Text: "\u5347\u7ea7",
        DownGrade_Text: "\u964d\u7ea7",
        Add_Text: "\u65b0\u589e",
        Edit_Text: "\u7f16\u8f91",
        Remove_Text: "\u5220\u9664",
        Move_Text: "\u79fb\u52a8",
        ZoomIn_Text: "\u653e\u5927",
        ZoomOut_Text: "\u7f29\u5c0f",
        Deselect_Text: "\u53d6\u6d88\u9009\u62e9",
        Split_Text: "\u62c6\u5206\u4efb\u52a1"
    })
}