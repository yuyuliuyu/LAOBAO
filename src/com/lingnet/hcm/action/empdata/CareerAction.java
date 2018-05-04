package com.lingnet.hcm.action.empdata;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.hcm.service.empdata.CareerService;
import com.lingnet.hcm.service.empdata.RelationService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.hcm.service.personnel.PtJobService;
import com.lingnet.util.JsonUtil;

/**
 * 
 * @ClassName: DepartureAction 
 * @Description: 职业履历Action 
 * @author zrl
 * @date 2017年3月10日 上午10:56:46 
 *
 */

public class CareerAction extends BaseAction{

	private static final long serialVersionUID = 707904150894239058L;
	private String flag;//标志位
    private String formdata;
    
    private BasicInformation info;
    private String jobNumber;
    private String personId;
    private Career car;
    
    @Resource(name="careerService")
    private CareerService careerService;
    
    /**
     * 列表页面
     */
    public String list(){
        return LIST;
    }
    /**
     * 列表页面
     */
    public String perlist(){
        return LIST;
    }
    /**
     * 列获取数据
     */
    public String getListData() {
    	if(personId!=null&&!"".equals(personId)){
    		Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
    		if(dataMap==null){
    			dataMap=new HashMap<String,String>();
    		}
			dataMap.put("personId", personId);
			
    		searchData=JsonUtil.Encode(dataMap);
    	}
        return ajax(Status.success, JsonUtil.Encode(careerService.getListData(pager,searchData)));
    }
    
    /**
     * 添加页面
     */
    public String add(){
    	car=this.getBeanByCriteria(Career.class, Restrictions.eq("id", id));
    	String personId=null;
    	if(car!=null){
    		personId=car.getPersonId();
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "add";
    }

    public String saveOrUpdate(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	car=JsonUtil.toObject(formdata, Career.class);
        } 
        try { 
            if(car.getId()==null||"".equals(car.getId().trim())){
                this.save(car);
            }else{
            	Career oldInfo=this.getBeanByCriteria(Career.class, Restrictions.eq("id", car.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	car.setPersonId(oldInfo.getPersonId());
            	oldInfo.copyFrom(car);
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
    			careerService.delete(Career.class, idArrs);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
        return ajax(Status.success, "success");
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
	public Career getCar() {
		return car;
	}
	public void setCar(Career car) {
		this.car = car;
	}
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}


    

}
