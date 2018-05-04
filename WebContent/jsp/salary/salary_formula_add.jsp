<%@ page trimDirectiveWhitespaces="true"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
    String path = request.getContextPath();
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + path;
    pageContext.setAttribute("base", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>公式编辑</title>
    <script src="../template/miniui/boot.js" type="text/javascript"></script>
    <link href="../template/system/css/base.css" rel="stylesheet" type="text/css" />
    <link href="../template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
    <link href="../template/plugin/public/css/tablepub.css" rel="stylesheet" type="text/css" />
    <script src="../template/plugin/public/add.js" type="text/javascript"></script>
    <style type="text/css">
        .m {
            list-style: none;
        }
        .math-box {
            width: 100%;
            height: 28px;
            margin-bottom: 6px;
        }
        .math-button {
            width: 28px;
            height: 28px;
            display: block;
            float: left;
            border: 1px solid #d6d6d6;
            text-align: center;
            color: #000;
            cursor: pointer;
        }
        .math-button-over {
            border: 1px solid #999;
        }
    </style>
</head>

<body>
    <div id="form1">
        <table style="width:100%;" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td class="list_left_box1" valign="top">工资套名称：</td>
                <td class="list_right_box">
                    <input class="mini-hidden" id="id" name="id" value="${salaryFormula.id}"/>
                    <!-- 工资套与薪资项目关联表ID -->
                    <input class="mini-hidden" id="bindId" name="bindId" value="${bindId}"/>
                    <input class="mini-textbox asLabel"allowInput="false"  style="width:90%" value="${wageName}" maxLength="100" required="true"/>
                </td>
                <td class="list_left_box1" valign="top">薪资项目名称：</td>
                <td class="list_right_box">
                    <input class="mini-textbox asLabel"allowInput="false"  style="width:90%" value="${salaryName}" maxLength="100" required="true"/>
                </td>
            </tr>
        </table>
        <div class="mini-toolbar" style="padding:2px;border-top:0;border-center:0;border-right:0;">
            <table style="width:100%;">
                <tr>
                    <td>
                        <span style="padding-left:5px;">项目公式</span>
                    </td>
                    <td style="float: right;">
                        &nbsp;名称：<input class="mini-textbox" id="key" onenter="findNodes()"/>
                        <a id="searchButton"class="mini-button" iconCls="icon-search" onclick="findNodes()">查询</a>
                    </td>
                </tr>
            </table>
            <input class="mini-hidden" id="cntContent" name="cntContent" value="${salaryFormula.cntContent}"/>
        </div>
        <div style="width: 100%;height:140px;">
            <div style="float:left;width:100%;">
                <div style="margin-right:240px;padding:4px">
                    <input class="mini-textarea" id="content" name="content" onvalidation = "onValidation"
                        style="width:100%;height:126px;float: left;margin-right: 4px;padding: 4px;resize: none" />
                </div>
            </div>
            <div style="position:relative;float:right;width:230px;margin-left:-230px;">
                <div style="float: left;padding: 0px;padding-top:4px">
                    <ul class="m" style="display: inline;padding:0px">
                        <li class="m math-box">
                            <span class="math-button math-button-nor">7</span>
                            <span class="math-button math-button-nor">8</span>
                            <span class="math-button math-button-nor">9</span>
                            <span class="math-button math-button-nor" >(</span>
                            <span class="math-button math-button-nor" >)</span>
                        </li>
                        <li class="m math-box" >
                            <span class="math-button math-button-nor">4</span>
                            <span class="math-button math-button-nor">5</span>
                            <span class="math-button math-button-nor">6</span>
                            <span class="math-button math-button-nor" >+</span>
                            <span class="math-button math-button-nor">-</span>
                        </li>
                        <li class="m math-box" >
                            <span class="math-button math-button-nor">1</span>
                            <span class="math-button math-button-nor">2</span>
                            <span class="math-button math-button-nor">3</span>
                            <span class="math-button math-button-nor">*</span>
                            <span class="math-button math-button-nor">/</span>
                        </li>
                        <li class="m math-box" >
                            <span class="math-button math-button-nor">0</span>
                            <span class="math-button math-button-nor">.</span>
                            <span class="math-button math-button-nor" style="width: 88px;">CE</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="mini-fit" style="height: 560px">
        <div id="notice" style="margin: 2px 0px;padding: 0px 30px 0px 4px;background-color: #ededed;border: 1px solid #ededed;">
            
        </div>
        <div style="overflow:auto;height: 100%">
            <ul class="m" style="height: 100%;display: inline-flex;margin:0px;padding: 0px">
                <s:iterator value="#request.formulas" var="t">
                    <s:if test="function != null">
                        <li class="m" style="width: 200px;height:100%;display:inline-block;margin: 0px 2px">
                            <div class="mini-toolbar" style="padding:2px;">
                                <span style="padding-left:5px;">${function[0]}</span>
                            </div>
                            <div class="mini-fit" style="border: 1px solid #ddd;">
                                <ul id="tree1" class="mini-tree" url="${function[1]}" style="width:100%;height:100%;" onnodeclick="onTreeNodeClick"
                                    showTreeIcon="false" textField="method" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="false">
                                </ul>
                            </div>
                        </li>
                    </s:if>
                    <s:if test="fun_items != null">
                        <li class="m" style="width: 200px;height:100%;display:inline-block;margin: 0px 2px">
                            <div class="mini-toolbar" style="padding:2px;">
                                <span style="padding-left:5px;">${fun_items[0]}</span>
                            </div>
                            <div class="mini-fit" style="border: 1px solid #ddd;">
                                <ul  id="tree2" class="mini-tree" url="${fun_items[1]}" style="width:100%;height:100%;" onnodeclick="onTreeNodeItemsClick"
                                    showTreeIcon="false" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="false">
                                </ul>
                            </div>
                        </li>
                    </s:if>
                    <s:if test="func_type != null">
                        <li class="m" style="width: 200px;height:100%;display:inline-block;margin: 0px 2px">
                            <div class="mini-toolbar" style="padding:2px;">
                                <span style="padding-left:5px;">${func_type[0]}</span>
                            </div>
                            <div class="mini-fit" style="border: 1px solid #ddd;">
                                <ul  id="tree4" class="mini-tree" url="${func_type[1]}" style="width:100%;height:100%;" onnodeclick="onTreeNodeItemsClick"
                                    showTreeIcon="false" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="false">
                                </ul>
                            </div>
                        </li>
                    </s:if>
                    <s:if test="insuranceItems != null">
                        <li class="m" style="width: 200px;height:100%;display:inline-block;margin: 0px 2px">
                            <div class="mini-toolbar" style="padding:2px;">
                                <span style="padding-left:5px;">${insuranceItems[0]}</span>
                            </div>
                            <div class="mini-fit" style="border: 1px solid #ddd;">
                                <ul id="tree3" class="mini-tree" url="${insuranceItems[1]}" style="width:100%;height:100%;" onnodeclick="onInsuranceNodeClick"
                                    showTreeIcon="false" textField="name" idField="id" parentField="pid" resultAsTree="false" expandOnLoad="false">
                                </ul>
                            </div>
                        </li>
                    </s:if>
                </s:iterator>
            </ul>
        </div>
    </div>
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" onclick="onOk('salary','salary_formula','',false)" style="width:60px;margin-right:20px;" iconCls="icon-save">保存</a>
        <a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">取消</a>
    </div>

    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("grid");
        var tree1 = mini.get("tree1");
        var tree2 = mini.get("tree2");
        var tree3 = mini.get("tree3");
        var tree4 = mini.get("tree4");
        var content = mini.get("content");
        content.focus();
        var cntContent = mini.get("cntContent");

        $(document).ready(function() {
            $(".math-button").on("mouseover mouseout",function() {
                $(this).toggleClass("math-button-over");
            });
            $(".math-button").on("click",function() {
                var v = $(this).html();
                if (v == "CE") {
                    content.setValue("");
                    content.focus();
                    return;
                }
                $("textarea[name=content]").insertAtCaret(v);
                copyValue();
            });
            var sContent = '${salaryFormula.content==null?"result=":salaryFormula.content}';
            content.setValue(sContent);
        });

        function onTreeNodeClick(e) {
            var node = e.node;
            if (e.isLeaf) {
                $("textarea[name=content]").insertAtCaret(node.methodCn);
                $("#notice").html(node.description);
                copyValue();
            }
        }

        /**
         * 项目点击
         */
        function onTreeNodeItemsClick(e) {
            var node = e.node;
            if (e.isLeaf) {
                $("textarea[name=content]").insertAtCaret(node.otherName);
                $("#notice").html(node.description);
                copyValue();
            }
        }

        /**
         * 福利项目点击
         */
        function onInsuranceNodeClick(e) {
            var node = e.node;
            if (e.isLeaf) {
                $("textarea[name=content]").insertAtCaret("L_" + node.name);
                copyValue();
            }
        }

        /**
         * 原生数据赋值给miniui
         */
        function copyValue() {
            var tValue = $.trim($("textarea[name=content]").val());
            var l_content = tValue.indexOf("result");
            if (l_content == -1) {
                content.setValue("result= " + tValue);
            } else {
                content.setValue(tValue);
            }
        }

        function onValidation(e) {
            var v = $.trim(e.value);
            if (v == "" || v == null) {
                e.errorText = "不能为空";
                e.isValid = false;
            } else {
                var l_content = v.indexOf("result");
                var l_equals = v.indexOf("=", l_content);
                if (l_content >= 0 && l_equals > 0 && l_content < l_equals) {
                    if ((v.length - l_equals) == 1) {
                        e.errorText = "请输入公式";
                        e.isValid = false;
                    } else
                        e.isValid = true;
                } else {
                    e.errorText = "必须要有 result=";
                    e.isValid = false;
                }
            }
        }

        /* 查找节点 */
        function findNodes() {
            var key = mini.get("key").getValue();
            if (key == "") {
                tree1.clearFilter();
                tree2.clearFilter();
                tree3.clearFilter();
            } else {
                tree1.filter(function (node) {
                    var text = node.method ? node.method : "";
                    if (text.indexOf(key) != -1) {
                        return true;
                    }
                });
                tree2.filter(function (node) {
                    var text = node.name ? node.name : "";
                    if (text.indexOf(key) != -1) {
                        return true;
                    }
                });
                tree3.filter(function (node) {
                    var text = node.name ? node.name : "";
                    if (text.indexOf(key) != -1) {
                        return true;
                    }
                });
                tree4.filter(function (node) {
                    var text = node.name ? node.name : "";
                    if (text.indexOf(key) != -1) {
                        return true;
                    }
                });
            }
        }

        /**
         * 扩展光标
         */
        (function ($) {
            $.fn.extend({
                insertAtCaret: function (myValue) {
                    var $t = $(this)[0];
                    if (document.selection) {
                        this.focus();
                        sel = document.selection.createRange();
                        sel.text = myValue;
                        this.focus();
                    } else {
                        if ($t.selectionStart || $t.selectionStart == '0') {
                            var startPos = $t.selectionStart;
                            var endPos = $t.selectionEnd;
                            var scrollTop = $t.scrollTop;
                            $t.value = $t.value.substring(0, startPos) + myValue + $t.value.substring(endPos, $t.value.length);
                            this.focus();
                            $t.selectionStart = startPos + myValue.length;
                            $t.selectionEnd = startPos + myValue.length;
                            $t.scrollTop = scrollTop;
                        } else {
                            this.value += myValue;
                            this.focus();
                        }
                    }
                }
            })
        })(jQuery);
    </script>
</body>
</html>