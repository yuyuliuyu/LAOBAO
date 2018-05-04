package com.lingnet.qxgl.action.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxDictionary;
import com.lingnet.qxgl.service.BackendDictionaryService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@ParentPackage("system")
@Results({ @Result(name = "login", type = "redirect", location = "/jsp/system/loginLayout.jsp") })
public class BackendLoginAction extends BaseAction {

	private static final long serialVersionUID = -1754357748424168916L;
	@Resource(name="backendDictionaryService")
    private BackendDictionaryService backendDictionaryService;

    // 登陆主页面
    public String main() {
        List<QxDictionary> list = backendDictionaryService.getAllByType("captcha");

        ActionContext.getContext().getSession().put("type", list.get(0).getFlg());
        return "login";
    }

    
    public BackendDictionaryService getBackendDictionaryService() {
        return backendDictionaryService;
    }

   
    public void setBackendDictionaryService(
            BackendDictionaryService backendDictionaryService) {
        this.backendDictionaryService = backendDictionaryService;
    }
    
    
}
