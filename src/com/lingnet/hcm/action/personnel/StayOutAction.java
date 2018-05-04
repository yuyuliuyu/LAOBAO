package com.lingnet.hcm.action.personnel;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.entity.person.Abroad;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.hcm.entity.person.StayOut;
import com.lingnet.hcm.service.personnel.StayOutService;
import com.lingnet.util.JsonUtil;

/**
 * 
 * @ClassName: DepartureAction 
 * @Description: 驻外Action
 * @author zrl
 * @date 2017年3月10日 上午10:56:46 
 *
 */
@Controller
public class StayOutAction extends BaseAction{

    private static final long serialVersionUID = -4533377460351784579L;
    
    private String flag;//标志位
    private String formdata;
    
    private BasicInformation info;
    private StayOut stayOut;
    private String personId;
    
    @Resource(name="stayOutService")
    private StayOutService stayOutService;
    
    /**
     * 驻外列表页面
     */
    public String list(){
        return LIST;
    }
    /**
     * 驻外列获取数据
     */
    public String getListData() {
        return ajax(Status.success, JsonUtil.Encode(stayOutService.getListData(pager,searchData)));
    }
    
    /**
     * 驻外添加页面
     */
    public String add(){
    	stayOut=this.getBeanByCriteria(StayOut.class, Restrictions.eq("id", id));
    	String personId="";
    	if(stayOut!=null){
    		personId=stayOut.getPersonId();
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "add";
    }

    /**
     * 驻外添加保存数据
     */
    public String saveOrUpdate(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	stayOut=JsonUtil.toObject(formdata, StayOut.class);
        } 
        try { 
            if(stayOut.getId()==null||"".equals(stayOut.getId().trim())){
            	stayOut.setStatus(1);
                this.save(stayOut);
                
            }else{
            	StayOut oldInfo=this.getBeanByCriteria(StayOut.class, Restrictions.eq("id", stayOut.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	oldInfo.copyFrom(stayOut);
                this.update(oldInfo);
            }
            info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", stayOut.getPersonId()));
            if (info == null) {
                throw new Exception("人员不存在");
            }
            if(stayOut.getOutType()==1){//借调
            	info.setIsEvection(1);
            	info.setEvectionType(0);
            }else if(stayOut.getOutType()==0){//驻外
            	info.setIsEvection(1);
            	info.setEvectionType(1);
            }
            this.update(info);
        } catch (Exception e) {
        	e.printStackTrace();
            return ajax(Status.success,"保存/修改失败");
        }
        return ajax(Status.success,"success");
    } 

    
    /**
     *驻外 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author zrl
     * @since 2017年3月8日 V 1.0
     */
    public String remove() {
    	if (ids != null && !"".equals(ids)){
    		String[] idArrs = ids.split(",");
    		try {
    			stayOutService.delete(StayOut.class, idArrs);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
        return ajax(Status.success, "success");
    }
    /**
     * 驻外添加页面
     */
    public String finish(){
    	stayOut=this.getBeanByCriteria(StayOut.class, Restrictions.eq("id", id));
        return "finish";
    }
    /**
     * 驻外添加页面
     */
    public String updateFinish(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	stayOut=JsonUtil.toObject(formdata, StayOut.class);
        } 
        try { 
            Abroad oldInfo=this.getBeanByCriteria(Abroad.class, Restrictions.eq("id", stayOut.getId()));
            if (oldInfo == null) {
                throw new Exception("该条记录不存在");
             }
            oldInfo.setEnddate(stayOut.getEnddate());
            oldInfo.setStatus(0);
            this.update(oldInfo);
        } catch (Exception e) {
            return ajax(Status.success,"操作失败");
        }
        return ajax(Status.success,"success");
    }
    
   //**********************set/get**********************//
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFormdata() {
		return formdata;
	}
	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}
	public BasicInformation getInfo() {
		return info;
	}
	public void setInfo(BasicInformation info) {
		this.info = info;
	}
	public StayOut getStayOut() {
		return stayOut;
	}
	public void setStayOut(StayOut stayOut) {
		this.stayOut = stayOut;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}

    

}
