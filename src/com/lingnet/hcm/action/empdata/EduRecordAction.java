package com.lingnet.hcm.action.empdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.entity.basic.TableColumInfo;
import com.lingnet.hcm.entity.check.CkContent;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Education;
import com.lingnet.hcm.service.empdata.EducationService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.hcm.service.personnel.PtJobService;
import com.lingnet.qxgl.entity.JcDictionary;
import com.lingnet.util.JsonUtil;

/**
 * 
 * @ClassName: DepartureAction 
 * @Description: 教育经历Action 
 * @author zrl
 * @date 2017年3月10日 上午10:56:46 
 *
 */
public class EduRecordAction extends BaseAction{
	private static final long serialVersionUID = -207409378802570789L;
	private String flag;//标志位
    private String formdata;
    private String personId;
    
    private BasicInformation info;
    private Education edu;
    @Resource(name="educationService")
    private EducationService educationService;
    
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
        return ajax(Status.success, JsonUtil.Encode(educationService.getListData(pager,searchData)));
    }
    
    /**
     * 添加页面
     */
    public String add(){
    	edu=this.getBeanByCriteria(Education.class, Restrictions.eq("id", id));
    	String personId=null;
    	if(edu!=null){
    		personId=edu.getPersonId();
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "add";
    }

    public String saveOrUpdate(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	//判断是否已存在最高学历
        	Map<String,String> formvalue = JsonUtil.parseProperties(formdata);
        	Conjunction and = Restrictions.conjunction();
        	if (!StringUtils.isBlank(formvalue.get("id"))) {
        	    and.add(Restrictions.ne("id", formvalue.get("id")));
        	}
        	if(formvalue.get("highestDegree").equals("1")){
            	List<Education> edus1 = this.getBeanListByCriteria(Education.class, Restrictions.eq("personId",formvalue.get("personId")),
            	        and,
            	        Restrictions.eq("highestDegree",1), Restrictions.eq("isAllday", Integer.valueOf(formvalue.get("isAllday"))));
            	if(edus1.size()>0){
            		return ajax(Status.success,"最高学位已存在！");
            	}
        	}
        	if(formvalue.get("highestEducation").equals("1")){
        	    List<Education> edus2 = this.getBeanListByCriteria(Education.class, Restrictions.eq("personId",formvalue.get("personId")),
        	        and,
        	        Restrictions.eq("highestEducation",1), Restrictions.eq("isAllday", Integer.valueOf(formvalue.get("isAllday"))));
            	if(edus2.size()>0){
            		return ajax(Status.success,"最高学历已存在！");
            	}
        	}
        } 
        try { 
            Education edu = JsonUtil.toObject(formdata, Education.class);
            if(edu.getId()==null||"".equals(edu.getId().trim())){
                this.save(edu);
            }else{
            	Education oldInfo=this.getBeanByCriteria(Education.class, Restrictions.eq("id", edu.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	edu.setPersonId(oldInfo.getPersonId());
            	oldInfo.copyFrom(edu);
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
    			educationService.delete(Education.class, idArrs);
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
	public Education getEdu() {
		return edu;
	}
	public void setEdu(Education edu) {
		this.edu = edu;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
    

}
