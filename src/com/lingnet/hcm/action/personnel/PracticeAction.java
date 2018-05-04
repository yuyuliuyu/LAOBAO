package com.lingnet.hcm.action.personnel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.WorkFlowChild;

import com.lingnet.hcm.entity.check.CkOvertimeSub;

import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Change;
import com.lingnet.hcm.entity.person.Education;
import com.lingnet.hcm.entity.person.PracticeAuthenticate;
import com.lingnet.hcm.entity.person.PracticeCheck;
import com.lingnet.hcm.entity.person.PtJob;
import com.lingnet.hcm.entity.salary.SalaryAccount;
import com.lingnet.hcm.entity.salary.SalaryRecord;
import com.lingnet.hcm.service.empdata.EducationService;
import com.lingnet.hcm.service.personnel.AuthenticateService;
import com.lingnet.hcm.service.personnel.CheckService;
import com.lingnet.hcm.service.personnel.PracticeService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.ToolUtil;
/**
 * 实习action
 * @ClassName: BranchAction 
 * @Description: TODO 
 * @author zrl
 * @date 2016-8-3 下午1:50:42 
 *
 */
@Controller
public class PracticeAction extends BaseAction{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Resource(name="checkService")
    private CheckService checkService;//考核信息service
    
    @Resource(name="authenticateService")
    private AuthenticateService authenticateService;//鉴定结果service
    
    @Resource(name="practiceService")
    private PracticeService practiceService;//考核信息service
    
    @Resource(name="educationService")
    private EducationService educationService;
    
    private BasicInformation information;
    
    private PracticeCheck check;
    private PracticeAuthenticate pau;
    private Education education;
    private Education education2;
    private SalaryAccount salary;
    private SalaryRecord  record;
    private CkOvertimeSub evection;
    
    private String formdata;
    private String advid;
    private String flag;//标志位
    private String departId;//部门ID
    private String branchId;//单位ID
    private String personId;//人员id
    private Integer age;
    private String imgpath;
    private String state;
    
    public String list(){
        return "list";
    }
    //编辑页
    public String add(){
    	information=this.getBeanById(BasicInformation.class, personId);
    	if(information!=null&&!"".equals(information)){
    		education=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 0),Restrictions.eq("highestEducation", 1));//全日制
    	    education2=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 1),Restrictions.eq("highestEducation", 1));//在职
    	    String Pathbase = ToolUtil.getPropert("config.properties", "file_path");
        	imgpath=Pathbase+"/baseInfo/"+information.getImgpath();//文件路径
        	if(information.getBorthDate()!=null){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(information.getBorthDate());
            	age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
        	}
    	}
    	salary=this.getBeanByCriteria(SalaryAccount.class, Restrictions.eq("staffId", personId));
    	record=this.getBeanByCriteria(SalaryRecord.class, Restrictions.eq("staffId", personId));
        return "add";
    }
    //编辑页
    public String edit(){
    	information=this.getBeanById(BasicInformation.class, personId);
    	if(information!=null&&!"".equals(information)){
    		education=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 0),Restrictions.eq("highestEducation", 1));//全日制
    	    education2=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 1),Restrictions.eq("highestEducation", 1));//在职
    	    String Pathbase = ToolUtil.getPropert("config.properties", "file_path");
        	imgpath=Pathbase+"/baseInfo/"+information.getImgpath();//文件路径
        	if(information.getBorthDate()!=null){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(information.getBorthDate());
            	age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
        	}
    	}
    	salary=this.getBeanByCriteria(SalaryAccount.class, Restrictions.eq("staffId", personId));
    	record=this.getBeanByCriteria(SalaryRecord.class, Restrictions.eq("staffId", personId));
        return "add";
    }
    //编辑页
    public String edit1(){
    	information=this.getBeanById(BasicInformation.class, personId);
    	if(information!=null&&!"".equals(information)){
    		education=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 0),Restrictions.eq("highestEducation", 1));//全日制
    	    education2=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 1),Restrictions.eq("highestEducation", 1));//在职
    	    String Pathbase = ToolUtil.getPropert("config.properties", "file_path");
        	imgpath=Pathbase+"/baseInfo/"+information.getImgpath();//文件路径
        	if(information.getBorthDate()!=null){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(information.getBorthDate());
            	age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
        	}
    	}
    	salary=this.getBeanByCriteria(SalaryAccount.class, Restrictions.eq("staffId", personId));
    	record=this.getBeanByCriteria(SalaryRecord.class, Restrictions.eq("staffId", personId));
        return "add1";
    }
    //添加页
    public String addmini(){
        return "addmini";
    }
    //人事资料保存
    public String saveOrUpdate() {
    	if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	try {
                return ajax(Status.success, practiceService.saveOrUpdate(formdata));
            } catch (Exception e) {
                return ajax(Status.success, e.getMessage());
            }
        } 
    }
    

    //总页面
    public String all(){
        return "all";
    }
   
    //考核信息
    public String checklist(){
        return "checklist";
    }
    
    //考核信息查看页面
    public String checkview(){
        return "checkview";
    }
    //获取数据--考核信息
    public String getKhData() {
    	
    	//List<PracticeCheck> pcheck=checkService.getList(Restrictions.eq("empId", personId));
    	Map<String, Object> result = checkService.getDataByCond(pager, state,personId);
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
    }
    //添加页面--考核信息
    public String checkadd(){
    	if (id == null || "".equals(id.trim())){
    		check = new PracticeCheck();  
    		check.setUserId(LingUtil.userinfo().getId());//qxuser的ID
    		check.setPraticeNum(LingUtil.userinfo().getUsername());
    		check.setApplyName(LingUtil.userinfo().getName());
        }else{
        	check=this.getBeanByCriteria(PracticeCheck.class, Restrictions.eq("id", id));
        }
    	
        return "checkadd";
    }
    // 考核信息--数据保存
    public String saveOrUpdateKhxx(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	check=JsonUtil.toObject(formdata, PracticeCheck.class);
        }
        try { 
            if(check.getId()==null||"".equals(check.getId().trim())){
            	check.setAuditStatus(0);//待审核状态
                this.save(check);
            }else{
            	PracticeCheck oldInfo=this.getBeanByCriteria(PracticeCheck.class, Restrictions.eq("id", check.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	
            	oldInfo.copyFrom(check);
                this.update(oldInfo);
            }
        } catch (Exception e) {
            return ajax(Status.success,"保存/修改失败");
        }
        return ajax(Status.success,"success");
    }
    // 考核信息--删除
    public String remove() {
    	if (ids != null && !"".equals(ids)){
    		String[] idArrs = ids.split(",");
    		try {
    			checkService.delete(PracticeCheck.class, idArrs);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
        return ajax(Status.success, "success");
    }
    /**
     * 考核信息提报上级
     * @Title: giveapp 
     * @return 
     * String 
     * @author zrl
     * @since 2017年4月15日 V 1.0
     */
    public String nextapp(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据错误，请联系管理员");
        }
        check=this.getBeanById(PracticeCheck.class, id);
        if(check==null){
            return ajax(Status.success,"数据错误，请联系管理员");
        }
        if(advid==null||"".equals(advid)){
        	check.setNodeId(this.getBeanByCriteria(WorkFlowChild.class, Restrictions.eq("pid", check.getProcessId()),Restrictions.eq("sort", 1)).getId());
        }else{
        	check.setNodeId(advid);  //审批流程节点
        }
        check.setRemark(state);  //当前审批人
        this.update(check);
        return ajax(Status.success,"提报成功");
    }
    
    
    
    //鉴定结果
    public String resultlist(){
    	pau=this.getBeanByCriteria(PracticeAuthenticate.class, Restrictions.eq("empId", personId));
        return "resultlist";
    }
    // 鉴定结果--数据保存
    public String saveOrUpdateJdjg(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	pau=JsonUtil.toObject(formdata, PracticeAuthenticate.class);
        } 
        try { 
            if(pau.getId()==null||"".equals(pau.getId().trim())){
                this.save(pau);
            }else{
            	PracticeAuthenticate oldInfo=this.getBeanByCriteria(PracticeAuthenticate.class, Restrictions.eq("id", pau.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	pau.setEmpId(oldInfo.getEmpId());
            	oldInfo.copyFrom(pau);
                this.update(oldInfo);
            }
        } catch (Exception e) {
            return ajax(Status.success,"保存/修改失败");
        }
        return ajax(Status.success,"success");
    }
    //实习结束
    public String finish(){
        return "finish";
    }
    //实习结束，修改人员基本资料
    /*@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)*///-----事物，只在service管用
    public String finishInfo() {
    	if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	information=JsonUtil.toObject(formdata, BasicInformation.class);
        }
    	BasicInformation oldInfo=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", information.getId()));
    	if (oldInfo == null) {
    		return ajax(Status.success,"该条记录不存在!");
        }
        try { 
        	if(information.getIsPractice()==0){
    	    	oldInfo.setIsPractice(0);//实习结束离开
    	    	oldInfo.setRemark2(information.getRemark2());
    	    	oldInfo.setOverPracticeDate(information.getOverPracticeDate());
    	        this.update(oldInfo);
        	}else if(information.getIsPractice()==2){
        		BasicInformation empinfo=this.getBeanByCriteria(BasicInformation.class,Restrictions.eq("jobNumber", information.getJobNumber()));
    			if(empinfo!=null){
    				return ajax(Status.success,"职工号已经存在，保存失败！");
    			}
        		oldInfo.setIsPractice(2);//实习结束转正
    	    	oldInfo.setRemark2(information.getRemark2());
    	    	oldInfo.setOverPracticeDate(information.getOverPracticeDate());
    	    	oldInfo.setJobNumber(information.getJobNumber());
    	    	oldInfo.setJobNumberJt(information.getJobNumberJt());
    	    	oldInfo.setZzDate(information.getZzDate());
    	    	oldInfo.setJssyDate(information.getJssyDate());
    	    	oldInfo.setEmpType(information.getEmpType());
    	    	oldInfo.setJobLevel(information.getJobLevel());
    	    	oldInfo.setPost(information.getPost());
    	    	oldInfo.setPostId(information.getPostId());
    	    	oldInfo.setSpecificPost(information.getSpecificPost());
    	    	oldInfo.setSpecificPostId(information.getSpecificPostId());
    	    	oldInfo.setBeginTime(information.getBeginTime());
    	    	oldInfo.setDepartId(information.getDepartId());
    	    	oldInfo.setDepartName(information.getDepartName());
    	    	oldInfo.setFilmId(information.getFilmId());
    	    	oldInfo.setFilmName(information.getFilmName());
    	    	oldInfo.setCareer(information.getCareer());
    	    	oldInfo.setIsSkilled(information.getIsSkilled());
    	    	oldInfo.setIsSpecialty(information.getIsSpecialty());
    	    	oldInfo.setOnJob(1);
    	    	oldInfo.setOnPost(0);
    	    	oldInfo.setCheckUnitId(information.getDepartId());//考勤使用
    	        this.update(oldInfo);
    	        //异动表中插入数据
    	        Change change=new Change();
    	        change.setChangeType(0);
    	        change.setPersonId(oldInfo.getId());
    	        change.setJobNumber(oldInfo.getJobNumber());
    	        change.setChangedate(oldInfo.getZzDate());//异动时间
        		change.setDepbeginDate(oldInfo.getBeginTime());
        		change.setPostbeginDate(oldInfo.getBeginTime());
        		change.setDep2(oldInfo.getDepartId());
        		change.setDepName2(oldInfo.getDepartName());
        		change.setFilm2(oldInfo.getFilmId());
        		change.setFilmName2(oldInfo.getFilmName());
        		change.setSpanPost2(oldInfo.getPostId());
        		change.setSpanPostName2(oldInfo.getPost());
        		change.setSpPost2(oldInfo.getSpecificPostId());
        		change.setSpPostName2(oldInfo.getSpecificPost());
        		change.setEmpType2(oldInfo.getEmpType());
        		change.setOnJob2(oldInfo.getOnJob());
        		change.setOnPost2(oldInfo.getOnPost());
        		change.setJobLevel2(oldInfo.getJobLevel());
    	        this.save(change);
    	        //兼职履历中插入主要职业
        		PtJob ptJob=this.getBeanByCriteria(PtJob.class, Restrictions.eq("personId", information.getId()),
        				Restrictions.eq("isHostPost", 1));
        		if(ptJob==null){
        			return ajax(Status.success,"兼职信息为空，操作错误！");
				}
        		ptJob.setIsDelete(0);
        		ptJob.setIsHostPost(1);//主要职位
        		ptJob.setDepId(oldInfo.getDepartId());//部门ID
        		ptJob.setFirmId(oldInfo.getFilmId());//单位ID
        		ptJob.setBeginTime(oldInfo.getBeginTime());
        		ptJob.setStandardPost(oldInfo.getPostId());
        		ptJob.setSpePost(oldInfo.getSpecificPostId());
        		
        		ptJob.setDep(oldInfo.getDepartName());//部门
        		ptJob.setFirm(oldInfo.getFilmName());//单位
        		ptJob.setStandardPost(oldInfo.getPost());
        		ptJob.setSpePost(oldInfo.getSpecificPost());
        		
        		this.update(ptJob);
        	}else{
        		return ajax(Status.success,"请选择结束方式！");
        	}
	    	
        } catch (Exception e) {
            return ajax(Status.success,"保操作失败！");
        }
        return ajax(Status.success,"success");
    }
    
    
    
    //教育经历
    public String edulist(){
        return "edulist";
    }
    public String eduadd(){
        return "eduadd";
    }
    
    
//////////////////////////////////////////////////////////////////////////
    
    public String getFormdata() {
        return formdata;
    }
    public void setFormdata(String formdata) {
        this.formdata = formdata;
    }
	public BasicInformation getInformation() {
		return information;
	}
	public void setInformation(BasicInformation information) {
		this.information = information;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public PracticeCheck getCheck() {
		return check;
	}
	public void setCheck(PracticeCheck check) {
		this.check = check;
	}
	public PracticeAuthenticate getPau() {
		return pau;
	}
	public void setPau(PracticeAuthenticate pau) {
		this.pau = pau;
	}

	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	public Education getEducation2() {
		return education2;
	}
	public void setEducation2(Education education2) {
		this.education2 = education2;
	}
	public SalaryAccount getSalary() {
		return salary;
	}
	public void setSalary(SalaryAccount salary) {
		this.salary = salary;
	}
	public SalaryRecord getRecord() {
		return record;
	}
	public void setRecord(SalaryRecord record) {
		this.record = record;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public CkOvertimeSub getEvection() {
		return evection;
	}
	public void setEvection(CkOvertimeSub evection) {
		this.evection = evection;
	}
	public String getAdvid() {
		return advid;
	}
	public void setAdvid(String advid) {
		this.advid = advid;
	}
  
}
