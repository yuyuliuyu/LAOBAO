/**
 * @BackendTestAction.java
 * @com.lingnet.qxgl.action.system
 * @Description：
 * 
 * @author zhmf 
 * @copyright  2013
 * @version V
 * @since 2013-5-30
 */
package com.lingnet.qxgl.action.system;

import com.lingnet.common.action.BaseAction;

/** 
 * @ClassName: BackendTestAction 
 * @author zhmf
 * @date 2013-5-30 下午3:40:04 
 *  
 */

public class BackendTestAction extends BaseAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1358438282323083782L;
    
 // 后台主页面
    public String main() {
        return "main";
    }

    // 后台Header
    public String header() {
        return "header";
    }

    // 后台菜单
    public String menu() {
        return "menu";
    }

    // 后台中间(显示/隐藏菜单)
    public String middle() {
        return "middle";
    }

    // 后台首页
    public String index() {
        return "index";
    }

}
