package com.lingnet.hcm.action.personnel;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.person.Abroad;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.hcm.entity.person.StayOut;
import com.lingnet.hcm.service.personnel.AbroadService;
import com.lingnet.util.JsonUtil;
/**
 * 出国管理action
 * @ClassName: BranchAction 
 * @Description: TODO 
 * @author zrl
 * @date 2016-8-3 下午1:50:42 
 *
 */
@Controller
public class AbroadAction extends BaseAction{
	private static final long serialVersionUID = 5348375340786130497L;
	
	private String flag;//标志位
    private String formdata;
    private String personId;
    
    private BasicInformation info;
    private Abroad abroad;
    
    @Resource(name="abroadService")
    private AbroadService abroadService;
    
    /**
     * 列表页面
     */
    public String list(){
        return LIST;
    }
    /**
     * 列获取数据
     */
    public String getListData() {
        return ajax(Status.success, JsonUtil.Encode(abroadService.getListData(pager,searchData)));
    }
    
    /**
     * 添加页面
     */
    public String add(){
    	abroad=this.getBeanByCriteria(Abroad.class, Restrictions.eq("id", id));
    	String personId="";
    	if(abroad!=null){
    		personId=abroad.getPersonId();
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "add";
    }

    public String saveOrUpdate(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	abroad=JsonUtil.toObject(formdata, Abroad.class);
        } 
        try { 
            if(abroad.getId()==null||"".equals(abroad.getId().trim())){
            	abroad.setStatus(1);
                this.save(abroad);
            }else{
            	Abroad oldInfo=this.getBeanByCriteria(Abroad.class, Restrictions.eq("id", abroad.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	oldInfo.copyFrom(abroad);
                this.update(oldInfo);
            }
        } catch (Exception e) {
            return ajax(Status.success,"保存/修改失败");
        }
        return ajax(Status.success,"success");
    } 

    
    /**
     * 删除方法
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
    			abroadService.delete(Abroad.class, idArrs);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
        return ajax(Status.success, "success");
    }
    /**
     * 添加页面
     */
    public String finish(){
    	abroad=this.getBeanByCriteria(Abroad.class, Restrictions.eq("id", id));
        return "finish";
    }
    public String updateFinish(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	abroad=JsonUtil.toObject(formdata, Abroad.class);
        } 
        try { 
            Abroad oldInfo=this.getBeanByCriteria(Abroad.class, Restrictions.eq("id", abroad.getId()));
            if (oldInfo == null) {
                throw new Exception("该条记录不存在");
             }
            oldInfo.setEnddate(abroad.getEnddate());
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
	public Abroad getAbroad() {
		return abroad;
	}
	public void setAbroad(Abroad abroad) {
		this.abroad = abroad;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}


    

}
