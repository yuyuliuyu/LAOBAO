package com.lingnet.hcm.action.organiz;

import com.lingnet.common.action.BaseAction; 
/**
 * 工作组成员信息
 * @ClassName: WorkingPersonAction 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年3月8日 下午3:07:05 
 *
 */
public class WorkingPersonAction  extends BaseAction{

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
    public String list(){
        return "list";
    }
    /**
     * 列表数据获取
     * @Title: listjson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String listjson(){
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
    public String add(){
        return "add";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String saveorupdate(){
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
    public String remove(){
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
