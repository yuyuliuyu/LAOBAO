package com.lingnet.qxgl.action.system;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;


import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.service.LogService;
import com.lingnet.util.JsonUtil;

/**
 * 
 * @ClassName: LogAction 
 * @Description: 日志管理 
 * @author 姜平豹
 * @date 2014-3-26 上午10:04:07 
 *
 */
@Controller
public class LogAction extends BaseAction {
    
    private static final long serialVersionUID = -2684708119893418263L;
    
    @Resource(name="logService")
    private LogService logService;//日志管理Service类
    
    private String czUser;
    private String stadate;
    private String enddate;
    private String czType;
    private String djType;
    private String ip;
    private String pcName;
    private String fzx;
   
    public String list() {
        return LIST;
    }
    
    /**
     * 日志管理查询页
     */
    @SuppressWarnings("rawtypes")
    public String search() {
        HashMap<String, String> searchMap = new HashMap<String, String>();
        
        //根据查询条件查询
        searchMap.put("czUser", czUser);
        searchMap.put("stadate", stadate);
        searchMap.put("enddate", enddate);
        searchMap.put("czType", czType);
        searchMap.put("djType", djType);
        searchMap.put("ip", ip);
        searchMap.put("fzx", fzx);
        HashMap result = logService.search(pager, searchMap);
        
        String json = JsonUtil.Encode(result);
        
        return ajax(Status.success, json);
    }

    //////////////////////////////////////////////////////////
    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public String getStadate() {
        return stadate;
    }

    public void setStadate(String stadate) {
        this.stadate = stadate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getCzUser() {
        return czUser;
    }

    public void setCzUser(String czUser) {
        this.czUser = czUser;
    }

    public String getCzType() {
        return czType;
    }

    public void setCzType(String czType) {
        this.czType = czType;
    }

    public String getDjType() {
        return djType;
    }

    public void setDjType(String djType) {
        this.djType = djType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

	public String getFzx() {
		return fzx;
	}

	public void setFzx(String fzx) {
		this.fzx = fzx;
	}
}
