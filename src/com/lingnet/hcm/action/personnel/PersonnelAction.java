package com.lingnet.hcm.action.personnel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.DeptPosition;
import com.lingnet.hcm.entity.person.AdressArea;
import com.lingnet.hcm.entity.person.AdressCity;
import com.lingnet.hcm.entity.person.AdressProvince;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.Change;
import com.lingnet.hcm.entity.person.Education;
import com.lingnet.hcm.entity.person.PtJob;
import com.lingnet.hcm.entity.salary.SalaryAccount;
import com.lingnet.hcm.entity.salary.SalaryRecord;
import com.lingnet.hcm.service.empdata.EducationService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.hcm.service.post.PostService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.ToolUtil;

/**
 * 人员action
 * @ClassName: BranchAction 
 * @Description: TODO 
 * @author lsp
 * @date 2016-8-3 下午1:50:42 
 *
 */
@Controller
public class PersonnelAction extends BaseAction{

    private static final long serialVersionUID = 1L;
    
    @Resource(name="personnelService")
    private PersonnelService personnelService;
    
    @Resource(name="educationService")
    private EducationService educationService;
    
    @Resource(name="postService")
    private PostService postService;
    
    private BasicInformation information;
    private DeptPosition deptPosition;
    private SalaryAccount salary;
    private SalaryRecord  record;
    private Branch branch;
    private Education education;
    private Education education2;
    
    private AdressProvince  adrp;//省
    private AdressCity  adrc;//市
    private AdressArea  adra;//区
    
    private String formdata;//添加数据的JSON串
    private String flag;//标志位
    
    private String departId;//部门ID
    private String departName;//部门
    private String filmId;//单位ID
    private String filmName;
    private String jobNumber;//
    private String personId;
    private Integer isChange;//試崗期轉正用
    private String imgpath;
    private String msg;//标志位
    private Integer age;
    private String fatherid;
    private String adressNum;
    private String positionName;// 具体岗位名称 zhanghj
    private String staffName;// 员工名称 zhanghj

    //所有人员list页面
    public String list(){
        return "list";
    }
    //总页面
    public String all(){
        return "all";
    }
    // 其他页面调用的选择页
    public String show(){
        return "show";
    }
    
    //普通员工中的正式工list页面
    public String zsglist(){
        return "zsglist";
    }
    
    //普通员工中的试岗期list页面
    public String sgqlist(){
        return "sgqlist";
    }
    //普通员工中的试岗期转正
    public String zhuanzheng(){
        return "finish";
    }
    //普通员工中的试岗期转正保存数据
    public String finishInfo() {
    	information=JsonUtil.toObject(formdata, BasicInformation.class);
    	BasicInformation oldInfo=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", information.getId()));
    	if (oldInfo == null) {
    		return ajax(Status.success,"该条记录不存在!");
        }
    	if("1".equals(flag)){
    		if(isChange==0){
        		oldInfo.setEmpType(0);
    	    	oldInfo.setOnPost(0);
        		oldInfo.setZhuanzDate(information.getZhuanzDate());
            }else if(isChange==1){
            	oldInfo.setZhuanzDate(information.getZhuanzDate());
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
    	    	oldInfo.setEmpType(0);
    	    	oldInfo.setOnPost(0);
    	    	oldInfo.setCheckUnitId(information.getDepartId());//考勤使用
            }
    	}else{
    		if(isChange==0){
        		oldInfo.setOnJob(2);
    	    	oldInfo.setOnPost(0);
        		oldInfo.setZhuanzDate(information.getZhuanzDate());
            }else if(isChange==1){
            	oldInfo.setZhuanzDate(information.getZhuanzDate());
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
    	    	oldInfo.setOnJob(2);
    	    	oldInfo.setOnPost(0);
    	    	oldInfo.setCheckUnitId(information.getDepartId());//考勤使用
    	    	//岗位变动职业履历增加一条
    	    	Career career=new Career();
        		career.setPersonId(oldInfo.getId());
        		career.setJobNumber(oldInfo.getJobNumber());
        		career.setCompany(oldInfo.getFilmName());
        		career.setDepartment(oldInfo.getDepartName());
        		career.setPost(oldInfo.getSpecificPost());
        		if(oldInfo.getJobLevel()!=null&&!"".equals(oldInfo.getJobLevel())){
        			career.setJobLevel(Integer.valueOf(oldInfo.getJobLevel()));
        		}
        		career.setBegindate(oldInfo.getBeginTime());
        		if("0".equals(oldInfo.getEmpType())){
        			career.setRemark("正式工");
        		}else if("1".equals(oldInfo.getEmpType())){
        			career.setRemark("劳务外包");
        		}else if("2".equals(oldInfo.getEmpType())){
        			career.setRemark("业务外包");
        		}
        		this.update(career);
    	    	
            }
    	}
    	
    	this.update(oldInfo);
        try { 
	        //异动表中插入数据
	        Change change=new Change();
	        change.setChangeType(1);
	        change.setPersonId(information.getId());
	        change.setJobNumber(oldInfo.getJobNumber());
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
	        change.setChangedate(oldInfo.getZhuanzDate());//异动时间
	        change.setJobLevel2(oldInfo.getJobLevel());
	        this.save(change);
	        //兼职履历中插入主要职业
    		PtJob ptJob=this.getBeanByCriteria(PtJob.class, Restrictions.eq("personId", oldInfo.getId()),
    				Restrictions.eq("isHostPost",1));
    		if(ptJob!=null&&!"".equals(ptJob)){
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
    			PtJob ptJob2=new PtJob();
        		ptJob2.setPersonId(oldInfo.getId());
        		ptJob2.setIsDelete(0);
        		ptJob2.setIsHostPost(1);//主要职位
        		
        		ptJob.setDepId(oldInfo.getDepartId());//部门ID
        		ptJob.setFirmId(oldInfo.getFilmId());//单位ID
        		ptJob.setBeginTime(oldInfo.getBeginTime());
        		ptJob.setStandardPost(oldInfo.getPostId());
        		ptJob.setSpePost(oldInfo.getSpecificPostId());
        		
        		ptJob.setDep(oldInfo.getDepartName());//部门
        		ptJob.setFirm(oldInfo.getFilmName());//单位
        		ptJob.setStandardPost(oldInfo.getPost());
        		ptJob.setSpePost(oldInfo.getSpecificPost());
        		this.save(ptJob2);
    		}
	    	
        } catch (Exception e) {
        	e.printStackTrace();
            return ajax(Status.success,"操作失败！");
        }
        return ajax(Status.success,"success");
    }
    
    //劳务外包list页面
    public String lwwblist(){
        return "lwwblist";
    }
    //业务外包list页面
    public String ywwblist(){
        return "ywwblist";
    }
    //退休人员页面
    public String txlist(){
        return "txlist";
    }
    //离职人员页面
    public String lzlist(){
        return "lzlist";
    }
    //返聘人员页面
    public String fplist(){
        return "fplist";
    }
    //非在岗人员页面
    public String fzglist(){
        return "fzglist";
    }
    //技术工人页面
    public String jsgrlist(){
        return "jsgrlist";
    }
    //专业技术人员页面
    public String zyjsrylist(){
        return "zyjsrylist";
    }
    //复员军人页面
    public String fyjrlist(){
        return "fyjrlist";
    }
    
    //获取人员list列表数据
    public String getPersonData(){
    	String josn=JsonUtil.Encode(personnelService.getPersonByDepId(pager,searchData));
    	return ajax(Status.success,josn);
    }

    /**
     * @Title: 获取工作流程定义人员列表
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月13日 V 1.0
     */
    public String getProcessStaffDatas(){
        String josn=JsonUtil.Encode(personnelService.getProcessStaffDatas(pager,searchData));
        return ajax(Status.success,josn);
    }
    //人事资料编辑页
    public String add(){
    	try {
			information=this.getBeanById(BasicInformation.class, personId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(information!=null&&!"".equals(information)){
    		education=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 0),Restrictions.eq("highestEducation", 1));//全日制
    	    education2=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 1),Restrictions.eq("highestEducation", 1));//在职
    	    deptPosition = postService.get(DeptPosition.class, eq("id",information.getSpecificPostId()));
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
    
    //人事资料，员工自我编辑
    public String personedit(){
    	information=this.getBeanById(BasicInformation.class, personId);
    	if(information!=null&&!"".equals(information)){
    		education=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 0),Restrictions.eq("highestEducation", 1));//全日制
    	    education2=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 1),Restrictions.eq("highestEducation", 1));//在职
    	    deptPosition = postService.get(DeptPosition.class, eq("id",information.getSpecificPostId()));
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
        return "add2";
    }
    //人事资料查看基本信息
    public String lookjb(){
    	information=this.getBeanById(BasicInformation.class, personId);
    	if(information!=null&&!"".equals(information)){
    		education=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 0),Restrictions.eq("highestEducation", 1));//全日制
    	    education2=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 1),Restrictions.eq("highestEducation", 1));//在职
    	    deptPosition = postService.get(DeptPosition.class, eq("id",information.getSpecificPostId()));
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
  //人事资料编辑页
    public String edit(){
    	information=this.getBeanById(BasicInformation.class, personId);
    	if(information!=null&&!"".equals(information)){
    		education=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 0),Restrictions.eq("highestEducation", 1));//全日制
    	    education2=educationService.get(Education.class, Restrictions.eq("personId", information.getId()),
    	    		Restrictions.eq("isAllday", 1),Restrictions.eq("highestEducation", 1));//在职
    	    deptPosition = postService.get(DeptPosition.class, eq("id",information.getSpecificPostId()));
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
    //人事资料添加页
    public String addmini(){
        return "addmini";
    }
    //人事资料保存
    public String saveOrUpdate() {
    	try {
            return ajax(Status.success, personnelService.saveOrUpdate(formdata,filmId));
        } catch (Exception e) {
            return ajax(Status.success, e.getMessage());
        }
    }

    //获取正式工list列表数据
    public String getZsgPersonData(){
    	String josn=JsonUtil.Encode(personnelService.getZsgPerson(pager,searchData));
    	return ajax(Status.success,josn);
    }
    
    //获取试岗期list列表数据
    public String getSgqPersonData(){
    	String josn=null;
		try {
			josn = JsonUtil.Encode(personnelService.getSgqPerson(pager,searchData));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ajax(Status.success,josn);
    }
    
    //获取实习生list列表数据
    public String getSxsPersonData(){
    	String josn=JsonUtil.Encode(personnelService.getSxsPerson(pager, searchData));
    	return ajax(Status.success,josn);
    }
    
    //获取劳务外包list列表数据
    public String getLwwbPersonData(){
    	String josn=JsonUtil.Encode(personnelService.getLwwbPerson(pager, searchData));
    	return ajax(Status.success,josn);
    }
    
    //获取业务外包list列表数据
    public String getYwwbPersonData(){
    	String josn=JsonUtil.Encode(personnelService.getYwwbPerson(pager, searchData));
    	return ajax(Status.success,josn);
    }
    //获取离职人员list列表数据
    public String getLzPersonData(){
    	String josn=JsonUtil.Encode(personnelService.getLzPerson(pager, searchData));
    	return ajax(Status.success,josn);
    }
    //获取退休人员list列表数据
    public String getTxPersonData(){
    	String josn=JsonUtil.Encode(personnelService.getTxPerson(pager, searchData));
    	return ajax(Status.success,josn);
    }
    //获取返聘人员list列表数据
    public String getFpPersonData(){
    	String josn=JsonUtil.Encode(personnelService.getFpPerson(pager, searchData));
    	return ajax(Status.success,josn);
    }
    //获取复原军人人员list列表数据
    public String getFyjrPersonData(){
    	String josn=JsonUtil.Encode(personnelService.getFyjrPerson(pager, searchData));
    	return ajax(Status.success,josn);
    }
    //获取非在岗人员list列表数据
    public String getFzgPersonData(){
    	String josn=JsonUtil.Encode(personnelService.getFzgPerson(pager, searchData));
    	return ajax(Status.success,josn);
    }
    //获取技术工人list列表数据
    public String getJsgrPersonData(){
    	String josn=JsonUtil.Encode(personnelService.getJsgrPerson(pager, searchData));
    	return ajax(Status.success,josn);
    }
    //获取专业技术人员list列表数据
    public String getZyjsryPersonData(){
    	String josn=JsonUtil.Encode(personnelService.getZyjsryPerson(pager, searchData));
    	return ajax(Status.success,josn);
    }
    
    public String getProvinceData(){
    	List adrp=this.getAllList(AdressProvince.class);
    	return ajax(Status.success,JsonUtil.Encode(adrp));
    }
    @SuppressWarnings("rawtypes")
	public String getCityData(){
    	List adrp=null;
    	if(fatherid!=null&&!"".equals(fatherid)){
    		adrp=this.getBeanListByCriteria(AdressCity.class, Restrictions.eq("fatherid", fatherid));
    	}else{
    		adrp=this.getBeanListByCriteria(AdressCity.class, Restrictions.eq("cityid", adressNum));
    	}
    	return ajax(Status.success,JsonUtil.Encode(adrp));
    }
    public String getAreaData(){
    	List adrp=null;
    	if(fatherid!=null&&!"".equals(fatherid)){
    		adrp=this.getBeanListByCriteria(AdressArea.class, Restrictions.eq("fatherid", fatherid));
    		
    	}else{
    		adrp=this.getBeanListByCriteria(AdressArea.class, Restrictions.eq("areaid", adressNum));
    	}
    	return ajax(Status.success,JsonUtil.Encode(adrp));
    }
    
    
    //*********************上传**************************//
    public String pictureupload(){
    	return "pictureshow";
    }
    /**
     * 上传文件
     * @Title: remove 
     * @return 
     * String 
     * @author zrl
     * @since 2017年3月8日 V 1.0
     */
    public String uploadFile() {
    	MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this.getRequest();
        // 获取上传文件的名字
        String[] fileName=wrapper.getFileNames("uploadFile");
        File[] files = wrapper.getFiles("uploadFile");
        if(files[0].length()>104857600){
			msg = "上传失败，文件大小不能超过100M！";
		}
    	//创建文件夹
    	String filePath = ToolUtil.getPropert("config.properties", "real_path");
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String stamp = sdf.format(new Date());
        String str = fileName[0].substring(fileName[0].lastIndexOf("."));
        //相对路径
        String fileUrl=stamp+str;
        try {
            //判断文件夹是否存在，不存在则创建
            File dirTest = new File(filePath+"/baseInfo");
            if (!dirTest.exists()) {
                dirTest.mkdirs();
            }
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new FileInputStream(files[0]);
                File saveFile = new File(dirTest,fileUrl);
                out = new FileOutputStream(saveFile);
                byte[] buffer = new byte[1024 * 1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                msg="success";
            } catch (Exception e) {
            	msg="上传失败！";
            	e.printStackTrace();
            } finally {
                in.close();
                out.close();
            }
        } catch (Exception e) {
        	msg="上传失败！";
        	e.printStackTrace();
        }
        
        HashMap<String, String> map=new HashMap<String,String>();
        map.put("msg", msg);
        map.put("fileUrl", fileUrl);
        String json=JsonUtil.Encode(map);
        
        return ajax(json);
    }
    //*********************上传**************************//
    
//////////////////////////////////////////////////////////////////////////

    public String getFormdata() {
        return formdata;
    }
    public void setFormdata(String formdata) {
        this.formdata = formdata;
    }

	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public BasicInformation getInformation() {
		return information;
	}
	public void setInformation(BasicInformation information) {
		this.information = information;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
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
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getDepartName() {
		return departName;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public String getFilmId() {
		return filmId;
	}
	public void setFilmId(String filmId) {
		this.filmId = filmId;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public Integer getIsChange() {
		return isChange;
	}
	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	public DeptPosition getDeptPosition() {
		return deptPosition;
	}
	public void setDeptPosition(DeptPosition deptPosition) {
		this.deptPosition = deptPosition;
	}
	public AdressProvince getAdrp() {
		return adrp;
	}
	public void setAdrp(AdressProvince adrp) {
		this.adrp = adrp;
	}
	public AdressCity getAdrc() {
		return adrc;
	}
	public void setAdrc(AdressCity adrc) {
		this.adrc = adrc;
	}
	public AdressArea getAdra() {
		return adra;
	}
	public void setAdra(AdressArea adra) {
		this.adra = adra;
	}
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public String getAdressNum() {
		return adressNum;
	}
	public void setAdressNum(String adressNum) {
		this.adressNum = adressNum;
	}
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }


  
}
