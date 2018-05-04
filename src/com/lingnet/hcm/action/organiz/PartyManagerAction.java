package com.lingnet.hcm.action.organiz;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;

/**
 * 党员日常管理Action
 * @ClassName: PartyManagerAction 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年3月14日 上午9:00:56 
 *
 */
public class PartyManagerAction extends BaseAction{

    private static final long serialVersionUID = 1L;
    
    private String id;              //主键id
    private String jsondata;        //页面获取的json数据
    private String state;           //状态标志位
    private String ids;             //批量操作的id数组

    /**
     * 列表页面展现
     * @Title: list 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String dflist(){
        return "dflist";
    }
    /**
     * 列表数据获取
     * @Title: listjson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String dflistjson(){
        return ajax(Status.success,"");
    }

    /**
     * 增加页面展现
     * @Title: add 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String dfadd(){
        return "dfadd";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String dfsaveorupdate(){
        return ajax(Status.success,"");
    }
    /**
     * 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String dfremove(){
        return ajax(Status.success,"");
    }
    

    /**
     * 列表页面展现
     * @Title: list 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String dfsylist(){
        return "dfsylist";
    }
    /**
     * 列表数据获取
     * @Title: listjson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String dfsylistjson(){
        return ajax(Status.success,"");
    }

    /**
     * 增加页面展现
     * @Title: add 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String dfsyadd(){
        return "dfadd";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String dfsysaveorupdate(){
        return ajax(Status.success,"");
    }
    /**
     * 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String dfsyremove(){
        return ajax(Status.success,"");
    }
    
    


    /**
     * 列表页面展现
     * @Title: list 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String zflist(){
        return "zflist";
    }
    /**
     * 列表数据获取
     * @Title: listjson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String zflistjson(){
        return ajax(Status.success,"");
    }

    /**
     * 增加页面展现
     * @Title: add 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String zfadd(){
        return "dfadd";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String zfsaveorupdate(){
        return ajax(Status.success,"");
    }
    /**
     * 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String zfremove(){
        return ajax(Status.success,"");
    }
    

    /**
     * 列表页面展现
     * @Title: list 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String tzlist(){
        return "tzlist";
    }
    /**
     * 列表数据获取
     * @Title: listjson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String tzlistjson(){
        return ajax(Status.success,"");
    }

    /**
     * 增加页面展现
     * @Title: add 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String tzadd(){
        return "tzadd";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String tzsaveorupdate(){
        return ajax(Status.success,"");
    }
    /**
     * 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String tzremove(){
        return ajax(Status.success,"");
    }
    
    
    

    /**
     * 列表页面展现
     * @Title: list 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String sylist(){
        return "sylist";
    }
    /**
     * 列表数据获取
     * @Title: listjson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String sylistjson(){
        return ajax(Status.success,"");
    }

    /**
     * 增加页面展现
     * @Title: add 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String syadd(){
        return "syadd";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String sysaveorupdate(){
        return ajax(Status.success,"");
    }
    /**
     * 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String syremove(){
        return ajax(Status.success,"");
    }
    
    
    
    

    /**
     * 列表页面展现
     * @Title: list 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String rclist(){
        return "rclist";
    }
    /**
     * 列表数据获取
     * @Title: listjson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String rclistjson(){
        return ajax(Status.success,"");
    }

    /**
     * 增加页面展现
     * @Title: add 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String rcadd(){
        return "rcadd";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String rcsaveorupdate(){
        return ajax(Status.success,"");
    }
    /**
     * 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String rcremove(){
        return ajax(Status.success,"");
    }
    
    
    

    /**
     * 列表页面展现
     * @Title: list 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String jslist(){
        return "jslist";
    }
    /**
     * 列表数据获取
     * @Title: listjson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String jslistjson(){
        return ajax(Status.success,"");
    }

    /**
     * 增加页面展现
     * @Title: add 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String jsadd(){
        return "jsadd";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String jssaveorupdate(){
        return ajax(Status.success,"");
    }
    /**
     * 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String jsremove(){
        return ajax(Status.success,"");
    }
    
    
    

    /**
     * 列表页面展现
     * @Title: list 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String fplist(){
        return "fplist";
    }
    /**
     * 列表数据获取
     * @Title: listjson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String fplistjson(){
        return ajax(Status.success,"");
    }

    /**
     * 增加页面展现
     * @Title: add 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String fpadd(){
        return "fpadd";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String fpsaveorupdate(){
        return ajax(Status.success,"");
    }
    /**
     * 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String fpremove(){
        return ajax(Status.success,"");
    }
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getJsondata() {
        return jsondata;
    }
    public void setJsondata(String jsondata) {
        this.jsondata = jsondata;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getIds() {
        return ids;
    }
    public void setIds(String ids) {
        this.ids = ids;
    } 

}
