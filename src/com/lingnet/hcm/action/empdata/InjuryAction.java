package com.lingnet.hcm.action.empdata;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.DeptPosition;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Injury;
import com.lingnet.hcm.entity.person.License;
import com.lingnet.hcm.service.empdata.InjuryService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.util.JsonUtil;

/**
 * 
 * @ClassName: InjuryAction 
 * @Description: 工伤Action 
 * @author zrl
 * @date 2017年3月10日 上午10:56:46 
 *
 */
@Controller
public class InjuryAction extends BaseAction{

    private static final long serialVersionUID = -4533377460351784579L;
    
    private String flag;//标志位
    private String formdata;
    private String personId;
    private String url;//
    
    private BasicInformation info;
    private Injury injury;
    private Branch branch;
    private QxDepartment qxdep;
    private DeptPosition deppost;
    private License license;
    
    @Resource(name="injuryService")
    private InjuryService injuryService;
    
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
        return ajax(Status.success, JsonUtil.Encode(injuryService.getListData(pager,searchData)));
    }
    
    /**
     * 添加页面
     */
    public String add(){
    	injury=this.getBeanByCriteria(Injury.class, Restrictions.eq("id", id));
    	String personId=null;
    	if(injury!=null){
    		personId=injury.getPersonId();
    		branch=this.getBeanByCriteria(Branch.class, Restrictions.eq("id", injury.getInjuryUnit()));
        	qxdep=this.getBeanByCriteria(QxDepartment.class, Restrictions.eq("id", injury.getInjuryDepartment()));
        	deppost=this.getBeanByCriteria(DeptPosition.class, Restrictions.eq("id", injury.getInjuryPost()));
        	
        	license=this.getBeanByCriteria(License.class, Restrictions.eq("theClassId", injury.getId()));
    		url=license==null?"":license.getUrl();
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
    	
    	
    	
        return "add";
    }

    public String saveOrUpdate(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	injury=JsonUtil.toObject(formdata, Injury.class);
        	license=JsonUtil.toObject(formdata, License.class);
        } 
        try { 
            if(injury.getId()==null||"".equals(injury.getId().trim())){
                this.save(injury);
                license.setTheClass(4);
                license.setTheClassId(injury.getId());
                this.save(license);
            }else{
            	Injury oldInfo=this.getBeanByCriteria(Injury.class, Restrictions.eq("id", injury.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	injury.setPersonId(oldInfo.getPersonId());
            	oldInfo.copyFrom(injury);
                this.update(oldInfo);
                License oldLicense=this.getBeanByCriteria(License.class, Restrictions.eq("theClassId", injury.getId()));
                if(oldLicense==null){
                	license.setTheClass(4);
                    license.setTheClassId(injury.getId());
                    
                    this.save(license);
                }else{
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
    			injuryService.delete(Injury.class, idArrs);
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
	public Injury getInjury() {
		return injury;
	}
	public void setInjury(Injury injury) {
		this.injury = injury;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public QxDepartment getQxdep() {
		return qxdep;
	}
	public void setQxdep(QxDepartment qxdep) {
		this.qxdep = qxdep;
	}
	public DeptPosition getDeppost() {
		return deppost;
	}
	public void setDeppost(DeptPosition deppost) {
		this.deppost = deppost;
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
