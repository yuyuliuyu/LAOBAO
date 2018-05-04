package com.lingnet.hcm.action.empdata;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.JobTitle;
import com.lingnet.hcm.entity.person.License;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.hcm.service.empdata.JobTitleService;
import com.lingnet.hcm.service.empdata.RelationService;
import com.lingnet.util.JsonUtil;

/**
 * 
 * @ClassName: DepartureAction 
 * @Description: 职称聘任Action 
 * @author zrl
 * @date 2017年3月10日 上午10:56:46
 *
 */
@Controller
public class JobTitleAction extends BaseAction{

    private static final long serialVersionUID = -4533377460351784579L;
    
    private String flag;//标志位
    private String formdata;
    private String personId;
    private String url;//
    
    private BasicInformation info;
    private JobTitle jobTitle;
    private License license;
    
    @Resource(name="jobTitleService")
    private JobTitleService jobTitleService;
    
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
        return ajax(Status.success, JsonUtil.Encode(jobTitleService.getListData(pager,searchData)));
    }
    
    /**
     * 添加页面
     */
    public String add(){
    	jobTitle=this.getBeanByCriteria(JobTitle.class, Restrictions.eq("id", id));
    	String personId=null;
    	if(jobTitle!=null){
    		personId=jobTitle.getPersonId();
    		license=this.getBeanByCriteria(License.class, Restrictions.eq("theClassId", jobTitle.getId()));
    		url=license==null?"":license.getUrl();
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "add";
    }

    public String saveOrUpdate(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	jobTitle=JsonUtil.toObject(formdata, JobTitle.class);
        	license=JsonUtil.toObject(formdata, License.class);
        } 
        try { 
            if(jobTitle.getId()==null||"".equals(jobTitle.getId().trim())){
                this.save(jobTitle);
                license.setTheClass(1);
                license.setTheClassId(jobTitle.getId());
                license.setOffice(jobTitle.getSendCertificate());
                license.setLicenseNum(jobTitle.getCertificateNum());
                
                this.save(license);
            }else{
            	JobTitle oldInfo=this.getBeanByCriteria(JobTitle.class, Restrictions.eq("id", jobTitle.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	jobTitle.setPersonId(oldInfo.getPersonId());
            	oldInfo.copyFrom(jobTitle);
                this.update(oldInfo);
                License oldLicense=this.getBeanByCriteria(License.class, Restrictions.eq("theClassId", jobTitle.getId()));
                if(oldLicense==null){
                	license.setTheClass(1);
                    license.setTheClassId(jobTitle.getId());
                    license.setOffice(jobTitle.getSendCertificate());
                    license.setLicenseNum(jobTitle.getCertificateNum());
                    
                    this.save(license);
                }else{
                	oldLicense.setOffice(jobTitle.getSendCertificate());
                	oldLicense.setLicenseNum(jobTitle.getCertificateNum());
                	oldLicense.setUrl(license.getUrl());
                	oldLicense.setFileName(license.getFileName());
                	oldLicense.setFileNum(license.getFileNum());
                	this.update(oldLicense);
                }
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
    			jobTitleService.delete(JobTitle.class, idArrs);
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

	public JobTitle getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public License getLicense() {
		return license;
	}
	public void setLicense(License license) {
		this.license = license;
	}

    

}
