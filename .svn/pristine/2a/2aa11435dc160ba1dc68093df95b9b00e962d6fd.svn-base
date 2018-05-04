package com.lingnet.hcm.action.empdata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.hcm.service.empdata.RelationService;
import com.lingnet.util.JsonUtil;

/**
 * 
 * @ClassName: DepartureAction 
 * @Description: 社会关系Action 
 * @author zrl
 * @date 2017年3月10日 上午10:56:46 
 *
 */
@Controller
public class SocialRelationAction extends BaseAction{

    private static final long serialVersionUID = -4533377460351784579L;
    
    private String flag;//标志位
    private String formdata;
    private Integer age;
    
    private BasicInformation info;
    private Relation rel;
    private String jobNumber;
    private String personId;
    
    @Resource(name="relationService")
    private RelationService relationService;
    
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
        return ajax(Status.success, JsonUtil.Encode(relationService.getListData(pager,searchData)));
    }
    
    /**
     * 添加页面
     */
    public String add(){
    	
    	rel=this.getBeanByCriteria(Relation.class, Restrictions.eq("id", id));
    	String personId=null;
    	if(rel!=null){
    		personId=rel.getPersonId();
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
        	String thisDay = sdf.format(new Date());
        	String brithDay = sdf.format(rel.getBorthDate());
        	age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
    	
        return "add";
    }

    public String saveOrUpdate(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	rel=JsonUtil.toObject(formdata, Relation.class);
        } 
        try { 
            if(rel.getId()==null||"".equals(rel.getId().trim())){
                this.save(rel);
            }else{
            	Relation oldInfo=this.getBeanByCriteria(Relation.class, Restrictions.eq("id", rel.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	rel.setPersonId(oldInfo.getPersonId());
            	oldInfo.copyFrom(rel);
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
    			relationService.delete(Relation.class, idArrs);
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
	public Relation getRel() {
		return rel;
	}
	public void setRel(Relation rel) {
		this.rel = rel;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
    

}
