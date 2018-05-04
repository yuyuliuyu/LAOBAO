package com.lingnet.hcm.action.organiz; 
import com.lingnet.common.action.BaseAction;  
/**
 * 党员关系处理Action
 * @ClassName: PartyRelationAction 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年3月14日 上午11:51:45 
 *
 */
public class PartyRelationAction  extends BaseAction{

    private static final long serialVersionUID = 1L;
    
    private String id;              //主键id
    private String jsondata;        //页面获取的json数据
    private String state;           //状态标志位
    private String ids;             //批量操作的id数组
 
    /**
     * 增加页面展现
     * @Title: add 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String cdadd(){
        return "cdadd";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String cdsaveorupdate(){
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
    public String rdadd(){
        return "rdadd";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String rdsaveorupdate(){
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
    public String ydlist(){
        return "ydlist";
    }
    /**
     * 列表数据获取
     * @Title: listjson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String ydlistjson(){
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
    public String zjadd(){
        return "zjadd";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String zjsaveorupdate(){
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
    public String dladd(){
        return "dladd";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String dlsaveorupdate(){
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
    public String dradd(){
        return "dradd";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String drsaveorupdate(){
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
