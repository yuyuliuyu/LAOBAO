package com.lingnet.hcm.action.empdata;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.EmpSkill;
import com.lingnet.hcm.entity.person.License;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.hcm.entity.person.SkilledEmp;
import com.lingnet.hcm.service.empdata.CareerService;
import com.lingnet.hcm.service.empdata.EmpSkillService;
import com.lingnet.hcm.service.empdata.RelationService;
import com.lingnet.hcm.service.empdata.SkilledEmpService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.hcm.service.personnel.PtJobService;
import com.lingnet.util.JsonUtil;

/**
 * 
 * @ClassName: EmpSkillAction 
 * @Description: 技术工人Action 
 * @author zrl
 * @date 2017年3月10日 上午10:56:46 
 *
 */
@Controller
public class SkilledEmpAction extends BaseAction{

    private static final long serialVersionUID = -4533377460351784579L;
    
    private String flag;//标志位
    private String formdata;
    private String personId;
    private String url;//证书编号
    
    private BasicInformation info;
    private SkilledEmp skill;
    private License license;
    
    @Resource(name="skilledEmpService")
    private SkilledEmpService skilledEmpService;
    
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
        return ajax(Status.success, JsonUtil.Encode(skilledEmpService.getListData(pager,searchData)));
    }
    
    /**
     * 添加页面
     */
    public String add(){
    	skill=this.getBeanByCriteria(SkilledEmp.class, Restrictions.eq("id", id));
    	String personId=null;
    	if(skill!=null){
    		personId=skill.getPersonId();
    		license=this.getBeanByCriteria(License.class, Restrictions.eq("theClassId", skill.getId()));
    		url=license==null?"":license.getUrl();
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
    	
        return "add";
    }

    public String saveOrUpdate(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	skill=JsonUtil.toObject(formdata, SkilledEmp.class);
        	license=JsonUtil.toObject(formdata, License.class);
        } 
        try { 
            if(skill.getId()==null||"".equals(skill.getId().trim())){
            	
                this.save(skill);
                license.setTheClass(0);
            	license.setTheClassId(skill.getId());
            	license.setPersonId(skill.getPersonId());
            	license.setLicenseNum(skill.getPapersNum());
            	license.setOffice(skill.getOffice());
            	license.setBegindate(skill.getAwarddate());
            	String contDate=countDate(skill.getBegindate(),skill.getEnddate());
            	license.setValidity(contDate);
            	this.save(license);
            }else{
            	SkilledEmp oldInfo=this.getBeanByCriteria(SkilledEmp.class, Restrictions.eq("id", skill.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	skill.setPersonId(oldInfo.getPersonId());
            	oldInfo.copyFrom(skill);
                this.update(oldInfo);
                License oldLicense=this.getBeanByCriteria(License.class, Restrictions.eq("theClassId", skill.getId()));
                if(oldLicense==null){
                	license.setTheClass(0);
                	license.setTheClassId(skill.getId());
                	license.setPersonId(skill.getPersonId());
                	license.setLicenseNum(skill.getPapersNum());
                	license.setOffice(skill.getOffice());
                	license.setBegindate(skill.getAwarddate());
                	String contDate=countDate(skill.getBegindate(),skill.getEnddate());
                	license.setValidity(contDate);
                	this.save(license);
                }else{
                	oldLicense.setTheClass(0);
                	oldLicense.setTheClassId(skill.getId());
                	oldLicense.setPersonId(skill.getPersonId());
                	oldLicense.setLicenseNum(skill.getPapersNum());
                	oldLicense.setOffice(skill.getOffice());
                	oldLicense.setBegindate(skill.getAwarddate());
                	oldLicense.setFileNum(license.getFileNum());
                	oldLicense.setFileName(license.getFileName());
                	oldLicense.setUrl(license.getUrl());
                	String contDate=countDate(skill.getBegindate(),skill.getEnddate());
                	oldLicense.setValidity(contDate);
                	this.update(oldLicense);
                }
            }
        } catch (Exception e) {
            return ajax(Status.success,"保存/修改失败");
        }
        return ajax(Status.success,"success");
    } 

    /**
     * 计算时间差
     * zrl
     */
    public String countDate(Date date1,Date date2){
    	long time1 = date1.getTime();  
        long time2 = date2.getTime();  
        long diff ;  
        if(time1<time2) {  
            diff = time2 - time1;  
            long days = (diff / (1000 * 60 * 60 * 24))+1; 
            String day=String.valueOf(days);
            return day;
        } else {  
        	return "-1";  
        }  
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
    			skilledEmpService.delete(SkilledEmp.class, idArrs);
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
	public SkilledEmp getSkill() {
		return skill;
	}
	public void setSkill(SkilledEmp skill) {
		this.skill = skill;
	}
	public License getLicense() {
		return license;
	}
	public void setLicense(License license) {
		this.license = license;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
