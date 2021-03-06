package com.lingnet.hcm.action.personnel;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Change;
import com.lingnet.hcm.entity.person.PtJob;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.hcm.service.personnel.PtJobService;
import com.lingnet.util.JsonUtil;

/**
 * 
 * @ClassName: DepartureAction 
 * @Description: 兼职人员管理Action 
 * @author duanjj
 * @date 2017年3月10日 上午10:56:46 
 *
 */
@Controller
public class PtJobAction extends BaseAction{

    private static final long serialVersionUID = -4533377460351784579L;
    
    private String flag;//标志位
    private String formdata;
    private BasicInformation info;
    private PtJob ptjob;
    private String jobNumber;
    private String personId;
    
    @Resource(name="ptJobService")
    private PtJobService ptJobService;
    
    /**
     * 列表页面
     */
    public String list(){
        return "list";
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
        return ajax(Status.success, JsonUtil.Encode(ptJobService.getListData(pager,searchData)));
    }
    /**
     * 添加页面
     */
    public String add(){
    	ptjob=this.getBeanByCriteria(PtJob.class, Restrictions.eq("id", id));
    	String personId=null;
    	if(ptjob!=null){
    		personId=ptjob.getPersonId();
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "add";
    }

    public String saveOrUpdate(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	ptjob=JsonUtil.toObject(formdata, PtJob.class);
        } 
        try {
            if(ptjob.getId()==null||"".equals(ptjob.getId().trim())){
            	ptjob.setIsDelete(0);
            	ptjob.setIsHostPost(0);
            	ptjob.setIsPractice(0);
            	ptjob.setState(1);
                this.save(ptjob);
            }else{
            	PtJob oldInfo=this.getBeanByCriteria(PtJob.class, Restrictions.eq("id", ptjob.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	//ptjob.setPersonId(oldInfo.getPersonId());
            	oldInfo.copyFrom(ptjob);
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
    			ptJobService.delete(PtJob.class, idArrs);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
        return ajax(Status.success, "success");
    }
    
  //结束兼职
    public String finish(){
        return "finish";
    }
    //结束保存方法
    /*@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)*///-----事物，只在service管用
    public String finishInfo() {
    	if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }
    	ptjob=JsonUtil.toObject(formdata, PtJob.class);
    	PtJob oldInfo=this.getBeanByCriteria(PtJob.class, Restrictions.eq("id", ptjob.getId()));
    	if (oldInfo == null) {
    		return ajax(Status.success,"该条记录不存在!");
        }
        try { 
        	oldInfo.setEndTime(ptjob.getEndTime());
        	oldInfo.setEndCase(ptjob.getEndCase());
        	oldInfo.setState(0);
        	this.update(oldInfo);
        	
	    	
        } catch (Exception e) {
            return ajax(Status.success,"保操作失败！");
        }
        return ajax(Status.success,"success");
    }

    /**
     * 列表页面
     */
    public String list2(){
        return "list2";
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
	public PtJob getPtjob() {
		return ptjob;
	}
	public void setPtjob(PtJob ptjob) {
		this.ptjob = ptjob;
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
