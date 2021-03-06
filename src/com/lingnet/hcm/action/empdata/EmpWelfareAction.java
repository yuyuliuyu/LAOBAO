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
import com.lingnet.hcm.entity.person.EmpWelfare;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.hcm.service.empdata.CareerService;
import com.lingnet.hcm.service.empdata.EmpWelfareService;
import com.lingnet.hcm.service.empdata.RelationService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.hcm.service.personnel.PtJobService;
import com.lingnet.util.JsonUtil;

/**
 * 
 * @ClassName: EmpWelfareAction 
 * @Description: 员工福利Action 
 * @author zrl
 * @date 2017年3月10日 上午10:56:46 
 *
 */
public class EmpWelfareAction extends BaseAction{

	private static final long serialVersionUID = -7417624724272416782L;
	private String flag;//标志位
    private String formdata;
    private String personId;
    
    private BasicInformation info;
    private EmpWelfare wel;
    
    @Resource(name="empWelfareService")
    private EmpWelfareService empWelfareService;
    
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
        return ajax(Status.success, JsonUtil.Encode(empWelfareService.getListData(pager,searchData)));
    }
    
    /**
     * 添加页面
     */
    public String add(){
    	wel=this.getBeanByCriteria(EmpWelfare.class, Restrictions.eq("id", id));
    	String personId=null;
    	if(wel!=null){
    		personId=wel.getPersonId();
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "add";
    }

    public String saveOrUpdate(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	wel=JsonUtil.toObject(formdata, EmpWelfare.class);
        } 
        try { 
            if(wel.getId()==null||"".equals(wel.getId().trim())){
                this.save(wel);
            }else{
            	EmpWelfare oldInfo=this.getBeanByCriteria(EmpWelfare.class, Restrictions.eq("id", wel.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	wel.setPersonId(oldInfo.getPersonId());
            	oldInfo.copyFrom(wel);
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
    			empWelfareService.delete(EmpWelfare.class, idArrs);
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
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public EmpWelfare getWel() {
		return wel;
	}
	public void setWel(EmpWelfare wel) {
		this.wel = wel;
	}
    

}
