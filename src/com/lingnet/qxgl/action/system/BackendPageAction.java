package com.lingnet.qxgl.action.system;


import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxDictionary;
import com.lingnet.qxgl.service.BackendDictionaryService;
import com.lingnet.util.LingUtil;



@Controller
@ParentPackage("system")
public class BackendPageAction extends BaseAction {

	private static final long serialVersionUID = 423160112817183884L;
	
	@Resource(name="backendDictionaryService")
    private BackendDictionaryService backendDictionaryService;
    private String flg;
    private String flg1;
    private String name;// 姓名
	// 后台主页面
	public String main() { 
		return "main";
	}
	// 后台主页面1
	public String mainshow(){
	    QxDictionary dictionary = backendDictionaryService.get(QxDictionary.class,Restrictions.eq("type", "F5"));
	    QxDictionary dictionary1 = backendDictionaryService.get(QxDictionary.class,Restrictions.eq("type", "tanchuang"));
        if(dictionary!=null){
            // 校验验证码
            flg = dictionary.getFlg();
            
        }else{
            flg = "T";
        }
        if(dictionary1!=null){
            // 校验验证码
            flg1 = dictionary1.getFlg();
            
        }else{
            flg1 = "T";
        }
        name = LingUtil.userinfo().getName();
        return "mainshow";
	}
	
	// 后台主页面1
    public String mainlist(){
        return "mainlist";
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

	public BackendDictionaryService getBackendDictionaryService() {
        return backendDictionaryService;
    }
    public void setBackendDictionaryService(
            BackendDictionaryService backendDictionaryService) {
        this.backendDictionaryService = backendDictionaryService;
    }
    public String getFlg() {
        return flg;
    }
    public void setFlg(String flg) {
        this.flg = flg;
    }
    /**
     * @return the flg1
     */
    public String getFlg1() {
        return flg1;
    }
    /**
     * @param flg1 the flg1 to set
     */
    public void setFlg1(String flg1) {
        this.flg1 = flg1;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	
	
}
