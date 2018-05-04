package com.lingnet.hcm.service.impl.personnel;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.check.AnnualLeaveRecordDao;
import com.lingnet.hcm.dao.empdata.AwardDao;
import com.lingnet.hcm.dao.empdata.CareerDao;
import com.lingnet.hcm.dao.empdata.EmpSkillDao;
import com.lingnet.hcm.dao.empdata.EmpWelfareDao;
import com.lingnet.hcm.dao.empdata.HealthDao;
import com.lingnet.hcm.dao.empdata.InjuryDao;
import com.lingnet.hcm.dao.empdata.JobTitleDao;
import com.lingnet.hcm.dao.empdata.LicenseDao;
import com.lingnet.hcm.dao.empdata.PunishDao;
import com.lingnet.hcm.dao.empdata.RecordDao;
import com.lingnet.hcm.dao.empdata.RelationDao;
import com.lingnet.hcm.dao.empdata.SkilledEmpDao;
import com.lingnet.hcm.dao.empdata.SoldierBackDao;
import com.lingnet.hcm.dao.personnel.ChangeDao;
import com.lingnet.hcm.dao.personnel.PersonnelDao;
import com.lingnet.hcm.dao.personnel.PtJobDao;
import com.lingnet.hcm.dao.post.DeptPositionDao;
import com.lingnet.hcm.dao.post.PostPositionDao;
import com.lingnet.hcm.entity.check.CkAnnualLeave;
import com.lingnet.hcm.entity.person.AdressArea;
import com.lingnet.hcm.entity.person.AdressCity;
import com.lingnet.hcm.entity.person.AdressProvince;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.Change;
import com.lingnet.hcm.entity.person.PtJob;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.qxgl.dao.AdminDao;
import com.lingnet.qxgl.dao.BackendDepDao;
import com.lingnet.qxgl.dao.BranchDao;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.JcDictionary;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.qxgl.entity.QxRoles;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.qxgl.entity.QxUserDatauth;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.qxgl.service.BackendDepService;
import com.lingnet.qxgl.service.BackendRoleService;
import com.lingnet.util.ExcelUtil;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: PersonnelServiceImpl 
 * @Description: 人员信息service实现类 
 * @author zrl
 * @date 2017年3月20日 下午1:32:00 
 *
 */
@Service("personnelService")
public class PersonnelServiceImpl extends BaseServiceImpl<BasicInformation, String> 
	implements PersonnelService{
	
	@Resource(name="personnelDao")
	private PersonnelDao personnelDao;
	
	@Resource(name="ptJobDao")
	private PtJobDao ptJobDao;
	
	@Resource(name="careerDao")
	private CareerDao careerDao;
	
	@Resource(name="changeDao")
	private ChangeDao changeDao;
	
	@Resource(name="branchDao")
	private BranchDao branchDao;
	
	@Resource(name="backendDepDao")
	private BackendDepDao backendDepDao;
	
	@Resource(name="postPositionDao")
	private PostPositionDao postPositionDao;
	@Resource(name="deptPositionDao")
	private DeptPositionDao deptPositionDao;
	
	@Resource(name = "backendRoleService")
    private BackendRoleService backendRoleService;
	
	@Resource(name = "adminService")
    private AdminService adminService;
	
	@Resource(name="backendDepService")
    private BackendDepService backendDepService;
	
	@Resource(name = "annualLeaveRecordDao")
	private AnnualLeaveRecordDao annualLeaveRecordDao;
	
	@Resource(name = "adminDao")
    private AdminDao adminDao;
	
	@Resource(name = "relationDao")
	private RelationDao relationDao;
	
	@Resource(name = "jobTitleDao")
	private JobTitleDao jobTitleDao;
	
	@Resource(name = "recordDao")
	private RecordDao recordDao;
	
	@Resource(name = "awardDao")
	private AwardDao awardDao;
	
	@Resource(name = "punishDao")
	private PunishDao punishDao;
	
	@Resource(name = "healthDao")
	private HealthDao healthDao;
	
	@Resource(name = "licenseDao")
	private LicenseDao licenseDao;
	
	@Resource(name = "empWelfareDao")
	private EmpWelfareDao empWelfareDao;
	
	@Resource(name = "injuryDao")
	private InjuryDao injuryDao;
	
	@Resource(name = "empSkillDao")
	private EmpSkillDao empSkillDao;
	
	@Resource(name = "skilledEmpDao")
	private SkilledEmpDao skilledEmpDao;
	
	@Resource(name = "soldierBackDao")
	private SoldierBackDao soldierBackDao;
	
	
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//人员基本信息list页面
	@SuppressWarnings({ "rawtypes" })
	@Override
    public HashMap getPersonByDepId(Pager pager, String searchData) {
		String depIds = getAllDepIdStrs();
		QxUser user=LingUtil.userinfo();
		String userId=user.getId();
		String num=adminDao.getRolesByUser(userId);
		if("1".equals(num)){
			String username=user.getUsername();
			BasicInformation info=this.get(Restrictions.eq("jobNumber", username));
			if(info==null||"".equals(info)){
				info=this.get(Restrictions.eq("practiceNum", username));
			}
			Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
			if(dataMap==null){
				dataMap=new HashMap<String,String>();
			}
			dataMap.put("personId", info.getId());
			searchData=JsonUtil.Encode(dataMap);
		}
		
        return personnelDao.getPersonByDepId(pager, searchData,depIds);
    }

	@Override
    public Map<String, Object> getProcessStaffDatas(Pager pager, String searchData) {
        String depIds = getAllDepIdStrs();
        QxUser user=LingUtil.userinfo();
        String userId=user.getId();
        String num=adminDao.getRolesByUser(userId);
        if("1".equals(num)){
            String username=user.getUsername();
            BasicInformation info=this.get(Restrictions.eq("jobNumber", username));
            if(info==null||"".equals(info)){
                info=this.get(Restrictions.eq("practiceNum", username));
            }
            Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
            if(dataMap==null){
                dataMap=new HashMap<String,String>();
            }
            dataMap.put("personId", info.getId());
            searchData=JsonUtil.Encode(dataMap);
        }
        
        return personnelDao.getProcessStaffDatas(pager, searchData,depIds);
    }

    @Override
	public void updateCheckInfo(String formdata) throws Exception {
		Map<String,String> dataMap = JsonUtil.parseProperties(formdata);
		String jobNumber = "";
		String checkUnitId = "";
		
		String classNo = "";
		String isMonitor = "";
		String isSpecialHour = "";
		String wageForm = "";
		String checkNo = "";
		if (!dataMap.isEmpty()){
			jobNumber = dataMap.get("jobNumber");
			checkUnitId = dataMap.get("checkUnitId");
			classNo = dataMap.get("classNo");
			isMonitor = dataMap.get("isMonitor");
			isSpecialHour = dataMap.get("isSpecialHour");
			wageForm = dataMap.get("wageForm");
			checkNo = dataMap.get("checkNo");
		}
		BasicInformation info = personnelDao.getByJobNumber(jobNumber);
		if (info != null){
			info.setCheckUnitId(checkUnitId);
			info.setClassNo(classNo);
			info.setIsMonitor(Integer.parseInt(isMonitor));
			info.setIsSpecialHour(Integer.parseInt(isSpecialHour));
			info.setWageForm(Integer.parseInt(wageForm));
			info.setCheckNo(checkNo);
			personnelDao.update(info);
		}
	}

	@Override
	public String saveImportInfos(String endSuffix, File uploadFile) throws Exception {
		// 读取数据
        List<String[]> list = ExcelUtil.readData(1, 2, endSuffix, uploadFile);
        // 获取所有职工号集合
        List<Object> jobNumbers = personnelDao.getAllJobNumbers();
        // 判断人员信息是否存在
        if (jobNumbers.size() == 0) {
            return "文件导入失败： 职工信息不存在！";
        }
        //获取所有考勤机号集合
        List<Object> checkNumbers = personnelDao.getAllCheckNumbers();
        // 导入名单只有表头没有人员信息
        if (list.size() == 1) {
            return "文件导入失败：导入名单中不存在人员信息";
        }
        // 第一行内容
        String[] firstRow = {"职工号*", "班号*", "是否班长*", "是否特殊工时*", "工资形式*", "考勤编号*"};
        // 单独把第一行抽取出来
        String[] firstValues = list.get(0);
        for (int i = 0, z = firstRow.length; i < z; i++) {
            if (!firstRow[i].trim().equals(firstValues[i].trim())) {
                return "文件导入失败：导入名单标题存在问题！<br/><font color='red'>" + firstValues[i].trim() + "</font>列必须是 " + firstRow[i].trim();
            }
        }
        List<BasicInformation> importData = new ArrayList<BasicInformation>();
        // 匹配消息
        String msg = StringUtils.EMPTY;

        // 循环遍历存储的Excel值
        for (int i = 1; i < list.size(); i++) {
            // 组装列
            String[] columns = new String[6];
            String[] values = list.get(i);
            // 判断是否整行是空行
            Boolean isEmpty= true;
            for(int q=0;q<values.length;q++){
                if (q < 6) {
                    columns[q]=values[q].trim();
                    if (!StringUtils.isBlank(columns[q])) {
                        isEmpty = false;
                    }
                } else continue;
            }
            // 存在空白行
            if (isEmpty) {
                // 文档结束
                break;
            }
            for(int k=values.length;k<6;k++){
                columns[k]="";
            }
            BasicInformation infos = new BasicInformation();// 存储人员基本信息
            //验证判断
            infos.setClassNo(columns[1]+"");
            if ("是".equals(columns[2])){
            	infos.setIsMonitor(1);
            } else if ("否".equals(columns[2])){
            	infos.setIsMonitor(0);
            }
            if ("是".equals(columns[3])){
            	infos.setIsSpecialHour(1);
            } else if ("否".equals(columns[3])){
            	infos.setIsSpecialHour(0);
            }
            if ("计件".equals(columns[4])){
            	infos.setWageForm(1);
            } else if ("计时".equals(columns[4])){
            	infos.setWageForm(0);
            }
            // 职工号验证
            if (!StringUtils.isBlank(columns[0])) {
                String message = checkLength(columns[0], "职工号长度不能超过10位", 10);
                if (!StringUtils.isBlank(message)) {
                    throw new Exception("文件导入失败： 第"+ (i+1) +"行，"+message);
                }
                if (!jobNumbers.contains(columns[0])){
                	throw new Exception("文件导入失败： 第"+ (i+1) +"行，职工号不存在");
                }
                infos.setJobNumber(columns[0]);
            } else throw new Exception("文件导入失败： 第"+ (i+1) +"行，职工号不能为空！");
            // 考勤编号验证
            if (!StringUtils.isBlank(columns[5])) {
                String message = checkLength(columns[5], "考勤编号长度不能超过10位", 10);
                if (!StringUtils.isBlank(message)) {
                    throw new Exception("文件导入失败： 第"+ (i+1) +"行，"+message);
                }
                if (checkNumbers.contains(columns[5])){
                	throw new Exception("文件导入失败： 第"+ (i+1) +"行，考勤编号已存在");
                }
                infos.setCheckNo(columns[5]);
            } else throw new Exception("文件导入失败： 第"+ (i+1) +"行，考勤编号不能为空！");
            importData.add(infos);
        }
        // 没有导入任何人员名单
        if (importData.size() == 0) {
            throw new Exception("@导入失败：" + msg);
        }
        // 批量保存所有导入人员信息
        Session session = null;
        try {
	        session = sessionFactory.openSession();
	        session.getTransaction().begin();
	        for (int i = 0; i < importData.size(); i++){
	        	BasicInformation info = importData.get(i);
	        	String sql = "update JC_BASIC_INFORMATION set CLASS_NO = '"+ info.getClassNo() +"', IS_MONITOR = "
	        			+ info.getIsMonitor() +", IS_SPECIAL_HOUR = "+ info.getIsSpecialHour() 
	        			+", WAGE_FORM = "+ info.getWageForm() +", CHECK_NO = '"+ info.getCheckNo() 
	        			+"' where JOB_NUMBER = '"+ info.getJobNumber() +"'";
	        	session.createSQLQuery(sql).executeUpdate();
	        	if (i%10 == 0){
	        		session.flush();//保持与数据库数据的同步
	        		session.clear();//清除内部缓存的全部数据，及时释放出占用的内存
	        	}
	        }
	        session.getTransaction().commit();//提交事务
        } catch (Exception e){
        	session.getTransaction().rollback();
        } finally {
        	session.close();//关闭session
        }
        return "success";
	}
	
	/**
     * @Title: 检测列的长度 
     * @param column
     * @param msg
     * @param length
     * @return 
     * String 
     * @author zhanghj
     * @since 2016年12月6日 V 1.0
     */
    private String checkLength(Object column, String msg, int length) {
        if (null != column) {
            if (column.toString().length() > length) return msg;
            else return "";
        } else return "";
    }
    /**
     * @Title: 人员基本信息保存
     * @param column
     * @param msg
     * @param length
     * @return 
     * String 
     * @author zrl
     * @since 2016年12月6日 V 1.0
     */
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdate(String formdata,String branchId) throws Exception {
    	BasicInformation information=JsonUtil.toObject(formdata, BasicInformation.class);
    	if (information != null) {
    		String id = information.getId();
    		if (StringUtils.isBlank(id)){
    			information.setIsDelete(0);
    			BasicInformation empinfo= this.get(Restrictions.eq("jobNumber", information.getJobNumber()));
    			if(empinfo!=null){
    				throw new Exception("职工号已经存在，保存失败！"); 
    			}
    			BasicInformation empinfo2= this.get(Restrictions.eq("idNumber", information.getIdNumber()));
    			if(empinfo2!=null){
    				throw new Exception("证件号码存在重复，保存失败！"); 
    			}
    			information.setCheckUnitId(information.getDepartId());//考勤用
    			//*****根据部门ID，获得部门名称公司ID，以及公司名称***//
    			if(information.getDepartId()!=null&&!"".equals(information.getDepartId())){
    				QxDepartment dep=this.get(QxDepartment.class, information.getDepartId());
    				if(dep==null){
    					throw new Exception("部门已删除，操作错误！"); 
    				}
    				information.setDepartName(dep.getName());
					information.setFilmId(dep.getBarchId());
					Branch branch=this.get(Branch.class, dep.getBarchId());
					information.setFilmName(branch.getFzx());
    			}
    			
        		id=this.save(information);
        		if (StringUtils.isBlank(id))throw new Exception("保存失败");
        		//*****创建用户***//
                QxUsers usenfo=adminService.getUserByName(information.getJobNumber());
                if(usenfo==null||usenfo.getId()==null){
                    QxUser  userinfo=new QxUser();
                    userinfo.setUsername(information.getJobNumber());
                    userinfo.setName(information.getName());
                    userinfo.setPassword("123456");
                    userinfo.setCid(information.getFilmId());
                    userinfo.setDepId(information.getDepartId());
                    userinfo.setIsDelete(0);
                    
                    HashSet<QxRoles> rolelist =new HashSet<QxRoles>(); 
                  //普通员工权限
                    QxRoles role = backendRoleService.get(QxRoles.class, Restrictions.eq("id","402881955c62c8f6015c62dc874d0006"));
                    rolelist.add(role);
                    adminService.saveuser(userinfo, rolelist);
                    String userId=this.get(QxUsers.class, Restrictions.eq("username",information.getJobNumber())).getId();
                            
                    String depIds =getall(information.getDepartId(),"","","");
                    String branchid=depIds.split("branchid")[1];
                    String branchs =getall1(branchid,"","","");
                    
                    String depall=depIds.split("branchid")[0].split("and")[0];
                    String flgall=depIds.split("branchid")[0].split("and")[1];
                    String branchall=branchs.split("and")[0];
                    String flgall1=branchs.split("and")[1];
                    
                    String depId=depall+","+branchall;
                    String flgs=flgall+","+flgall1;
                    
                    if(StringUtils.isNotEmpty(depId)){
                        String[] depArr=depId.split(",");
                        String[] flgArr=flgs.split(",");
                        for(int i=0,l=depArr.length;i<l;i++){
                            QxUserDatauth datauth=new QxUserDatauth(userId, depArr[i], flgArr[i]);
                            adminDao.save(datauth);
                        }
                    }
                }else{
                	throw new Exception("这个用户已经存在，不需要重新添加！");
                }
                
        		if(information.getSpecificPost()!=null&&!"".equals(information.getSpecificPost())){
        			//兼职信息
            		PtJob ptJob=new PtJob();
            		ptJob.setPersonId(information.getId());
            		ptJob.setIsDelete(0);
            		ptJob.setIsHostPost(1);
            		ptJob.setDepId(information.getDepartId());//部门ID
            		ptJob.setFirmId(information.getFilmId());//单位ID
            		ptJob.setStandardPostId(information.getPostId());
            		ptJob.setSpePostId(information.getSpecificPostId());
            		ptJob.setDep(information.getDepartName());//部门
            		ptJob.setFirm(information.getFilmName());//单位
            		ptJob.setStandardPost(information.getPost());
            		ptJob.setSpePost(information.getSpecificPost());
            		ptJob.setBeginTime(information.getBeginTime());
            		//人员异动
            		Change change=new Change();
            		change.setPersonId(information.getId());
            		change.setJobNumber(information.getJobNumber());
            		change.setChangedate(information.getBeginTime());
            		change.setDepbeginDate(information.getBeginTime());
            		change.setPostbeginDate(information.getBeginTime());
            		change.setChangeType(15);
            		//change.setDep2(information.getDepartId());
            		//change.setFilm2(information.getFilmId());
            		//change.setSpanPost2(information.getPostId());
            		//change.setSpPost2(information.getSpecificPostId());
            		change.setDepName2(information.getDepartName());
            		change.setFilmName2(information.getFilmName());
            		change.setSpanPostName2(information.getPost());
            		change.setSpPostName2(information.getSpecificPost());
            		change.setEmpType2(information.getEmpType());
            		change.setOnJob2(information.getOnJob());
            		change.setOnPost2(information.getOnPost());
            		change.setJobLevel2(information.getJobLevel());
            		Career career=new Career();
            		career.setPersonId(information.getId());
            		career.setJobNumber(information.getJobNumber());
            		career.setBegindate(information.getBeginTime());
            		career.setCompany(information.getFilmName());
            		career.setDepartment(information.getDepartName());
            		career.setPost(information.getSpecificPost());
            		if(information.getJobLevel()!=null&&!"".equals(information.getJobLevel())){
            			career.setJobLevel(Integer.valueOf(information.getJobLevel()));
            		}
            		career.setBegindate(information.getBeginTime());
            		if(information.getEmpType()==0){
            			career.setRemark("正式工");
            		}else if(information.getEmpType()==1){
            			career.setRemark("劳务外包");
            		}else if(information.getEmpType()==2){
            			career.setRemark("业务外包");
            		}
            		
            		String sql=" SELECT JI.NAME,JI.JOB_LEVEL,JI.PHONE FROM JC_BASIC_INFORMATION JI  ";
            		sql+=" LEFT JOIN POST_POSITION PP ON PP.ID = JI.POST_ID ";
            		sql+=" WHERE JI.DEPART_ID='"+information.getDepartId()+"'";
            		sql+=" ORDER BY PP.POSITION_CODE ";
            		List<?> leader1=this.findBySql(sql);
            		if(leader1.size()>0){
            			Object[] leader=(Object[]) leader1.get(0);
            			career.setProve(leader[0]==null?"":leader[0].toString());
        				career.setProTel(leader[2]==null?"":leader[2].toString());
        				career.setProDuty(leader[1]==null?"":leader[1].toString());
            		}
            		
            		String careerid=careerDao.save(career);
            		String changeid=changeDao.save(change);//保存
            		String jobid=ptJobDao.save(ptJob);//保存
            		if (StringUtils.isBlank(jobid))throw new Exception("信息导入兼职信息时发生异常！");
            		if (StringUtils.isBlank(changeid))throw new Exception("信息导入异动表时发生异常！");
            		if (StringUtils.isBlank(careerid))throw new Exception("信息导入职位表时发生异常！");
        		}
        		
        	}else{
        		BasicInformation oldInfo=this.get(BasicInformation.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
        		information.setFilmId(oldInfo.getFilmId());
        		information.setFilmName(oldInfo.getFilmName());
        		information.setDepartId(oldInfo.getDepartId());
        		information.setDepartName(oldInfo.getDepartName());
        		if(information.getImgpath()!=null){
        			int pos = information.getImgpath().indexOf("/baseInfo/") > -1 ? information.getImgpath().indexOf("/baseInfo/") + 10 : 0;
        			String newimg = information.getImgpath().substring(pos);
        			information.setImgpath(newimg);
        		}
        		oldInfo.copyFrom(information);
        		
				this.update(oldInfo);
        	}
    		
    	}else return "信息不能为空";
    	
    	return "success";
    }

	@Override
	public void updateBatchClass(String empIds, String depIds, String classNo)
			throws Exception {
		personnelDao.updateBatchClass(empIds, depIds, classNo);
	}

	@Override
	public List<Map<String, String>> getDataByEmpIds(Pager pager, List<String> empIdArrs) {
		return personnelDao.getDataByEmpIds(pager, empIdArrs);
	}

	@Override
	public String getCheckUnitIdByJobNumber(String jobNumber) {
		return personnelDao.getCheckUnitIdByJobNumber(jobNumber);
	}

	@Override
	public Map<String, Object> getAnnualLeaveData(Pager pager, String searchData) {
        //获得当前用户的所有权限部门集合
        String depIds = getAllDepIdStrs();
        List<Map<String, String>> list = personnelDao.getAnnualLeaveData(pager, searchData, depIds);
        Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
        if (dataMap == null){
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("data", list);
            returnMap.put("total", pager.getTotalCount());
            return returnMap;
        }
        String year = dataMap.get("year");//搜索的年份
        if (year == null || "".equals(year)){
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("data", list);
            returnMap.put("total", pager.getTotalCount());
            return returnMap;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfAll = new SimpleDateFormat("yyyy-MM-dd");
        if (Integer.parseInt(year) <= Integer.parseInt(sdf.format(new Date()))){
            String thisDate = "";
            if (Integer.parseInt(year) < Integer.parseInt(sdf.format(new Date()))){//输入年度小于当前年份
                thisDate = year + "-12-31";
            } else if (Integer.parseInt(year) == Integer.parseInt(sdf.format(new Date()))){
                thisDate = sdfAll.format(new Date());
            }
            if (list.size() > 0){
                SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
                List<String> jobNumbers = new ArrayList<String>();
                for (Map<String, String> map :list){
                    jobNumbers.add(map.get("jobNumber")); 
                    BasicInformation bsinfo=personnelDao.getByJobNumber(map.get("jobNumber"));
                    Double legalDay = 0.0;//全部休假天数
                    Double allDay = 0.0;//法定休假天数
                    String workDate = map.get("workDate");//开始工作日期
                    if(bsinfo!=null&&bsinfo.getAnnualLeave()!=null){
                        int kjgl=Integer.parseInt(bsinfo.getAnnualLeave()); 
                        Date d=new Date(); 
                        Calendar calendar = Calendar.getInstance();    
                        try {
                            d = sdfAll.parse(workDate);
                            calendar.setTime(d);
                        } catch (ParseException e) { 
                            e.printStackTrace();
                        } 
                        int gznx=calendar.get(Calendar.YEAR);
                        int sjnx=gznx-kjgl;
                        workDate=sjnx+workDate.substring(4);
                    }
                    String portDate = map.get("portDate");//入港时间
                    int legalMonths = 0;//当前日期距离开始工作日期相差月份数
                    try {
                        legalMonths = countMonths(workDate, thisDate);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (legalMonths <= 0){
                        allDay = 0.0;
                    } else if (legalMonths > 0 && legalMonths < 12){
                        allDay = 0.0;
                    } else if (legalMonths/12 >=1 && legalMonths/12 < 10){
                        allDay = 5.0;
                    } else if (legalMonths/12 >= 10 && legalMonths/12 < 20){
                        allDay = 10.0;
                    } else {
                        allDay = 15.0;
                    }
                    Double actualDay = 0.0;//应休休假天数
                    int actualMonths = 0;//搜索年份距离开始工作日期相差月份数
                    try {
                        actualMonths = countMonths(portDate, thisDate);
                    } catch (Exception e) {
                        actualMonths=0;
                        e.printStackTrace();
                    }
                    if (actualMonths <= 0){
                        legalDay = 0.0;
                    } else {
                        if (Integer.parseInt(portDate.substring(0, 4)) == Integer.parseInt(thisDate.substring(0, 4))){
                            if (Integer.parseInt(portDate.substring(0, 4)) == Integer.parseInt(sdfMonth.format(new Date()))){
                                int thisMonth = Integer.parseInt(sdfMonth.format(new Date()))-Integer.parseInt(portDate.substring(5, 7))+1;
                                legalDay = thisMonth/12.0*allDay;
                            } else {
                                int thisMonth = 12-Integer.parseInt(portDate.substring(5, 7))+1;
                                legalDay = thisMonth/12.0*allDay;
                            }
                        } else {
                            legalDay = allDay;
                        }
                    }
                    map.put("legalDay", Math.ceil(legalDay)+"");//全部
                    if (actualMonths <= 0){
                        actualDay = 0.0;
                    } else {
                        if (Integer.parseInt(year) < Integer.parseInt(sdf.format(new Date()))){//输入年度小于当前年份
                            if (Integer.parseInt(thisDate.substring(0, 4)) == Integer.parseInt(portDate.substring(0, 4))){
                                int thisMonth = Integer.parseInt(thisDate.substring(5, 7))-Integer.parseInt(portDate.substring(5, 7))+1;
                                actualDay = thisMonth/12.0*allDay;
                            } else {
                                actualDay = allDay;
                            }
                        } else if (Integer.parseInt(year) == Integer.parseInt(sdf.format(new Date()))){
                            if (Integer.parseInt(thisDate.substring(0, 4)) == Integer.parseInt(portDate.substring(0, 4))){
                                int thisMonth = Integer.parseInt(sdfMonth.format(new Date()))-Integer.parseInt(portDate.substring(5, 7))+1;
                                actualDay = thisMonth/12.0*allDay;
                            } else {
                                actualDay = allDay;
                            }
                        }
                    }
                    map.put("shouldTake", Math.ceil(actualDay)+"");//应休天数
                }
                //查询员工年假休假记录
                List<CkAnnualLeave> annualList = annualLeaveRecordDao.getRecordByJobNumbers(jobNumbers, year);
                Map<String, List<CkAnnualLeave>> annualMap = new HashMap<String, List<CkAnnualLeave>>();
                String flag = "";
                if (annualList != null && annualList.size() > 0){
                    List<CkAnnualLeave> leaveList = null;
                    for (CkAnnualLeave leave :annualList){
                        if ("".equals(flag)){
                            flag = leave.getJobNumber();
                            leaveList = new ArrayList<CkAnnualLeave>();
                            leaveList.add(leave);
                        } else {
                            if (flag.equals(leave.getJobNumber())){
                                leaveList.add(leave);
                            } else {
                                annualMap.put(flag, leaveList);
                                flag = leave.getJobNumber();
                                leaveList = new ArrayList<CkAnnualLeave>();
                                leaveList.add(leave);
                            }
                        }
                    }
                    if (annualMap.get(flag) == null){
                        annualMap.put(flag, leaveList);
                    }
                }
                //循环封装
                if (list.size() > 0){
                    for (Map<String, String> map :list){
                        if (!annualMap.isEmpty()){
                            List<CkAnnualLeave> recordList = annualMap.get(map.get("jobNumber"));
                            Double total = 0.0;
                            if (recordList != null){
                                for (CkAnnualLeave leave:recordList){
                                    total += leave.getDays();
                                    String days = map.get("month"+leave.getMonthCalendar());
                                    if ("".equals(days)){
                                        map.put("month"+leave.getMonthCalendar(), leave.getDays()+"");
                                    } else {
                                        map.put("month"+leave.getMonthCalendar(), Double.parseDouble(days)+(double)leave.getDays()+"");
                                    }
                                }
                                map.put("haveTake", total+"");//已休
                                if (!"0".equals(map.get("shouldTake"))){
                                    map.put("surplus", (Double.parseDouble(map.get("shouldTake"))
                                            -Double.parseDouble(map.get("haveTake")))+"");
                                } else {
                                    map.put("surplus", "0");//剩余
                                }
                            } else {
                                map.put("haveTake", "0");//已休
                                map.put("surplus", map.get("shouldTake"));//剩余
                            }
                        } else {
                            map.put("haveTake", "0");//已休
                            map.put("surplus", map.get("shouldTake"));//剩余
                        }
                    }
                }
            }
        }
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("data", list);
        returnMap.put("total", pager.getTotalCount());
        return returnMap;
    }
	
	public static int countMonths(String date1,String date2) throws Exception{
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    Calendar c1 = Calendar.getInstance();
	    Calendar c2 = Calendar.getInstance();
	    c1.setTime(sdf.parse(date1));
	    c2.setTime(sdf.parse(date2));
	    int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
	    return year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
	}
	
	/**
	 * @Description 获得当前用户的所有权限部门集合
	 * @Title: getAllDepIdStr 
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月30日 V 1.0
	 */
	@SuppressWarnings("rawtypes")
	
	public String getAllDepIdStrs(){
		String depIds = "";
  		String userId = toolUtil.getUserId();
  		List data = this.findBySql("SELECT B.ID FROM BRANCH B " 
  				+ "INNER JOIN QX_USER_DATAUTH UD ON UD.BRANCH_DEP=B.ID AND UD.FLG='0' AND UD.USERID = '"+ userId +"' " 
  				+ "WHERE B.IS_DELETE = 0 ");
  		if (data!=null&&data.size()>0){
  			for (int i=0;i<data.size();i++){
  				Object b = (Object)data.get(i);
  				depIds += ("'"+ (b==null?"":b.toString()) + "', ");
  				//获取下级部门
  				List dataP = backendDepService.findBySql("SELECT D.PARENT,D.ID FROM QX_DEPARTMENT D " +
               		"INNER JOIN QX_USER_DATAUTH UD ON UD.BRANCH_DEP=D.ID AND UD.FLG='1' AND UD.USERID='"+userId+"' " +
               		"WHERE D.BARCH_ID='"+b+"' AND D.IS_DELETE=0 ");
  				for (int k=0; k<dataP.size(); k++){
  					Object[] dep=(Object[])dataP.get(k);
            	    String parent=dep[0]==null?"":dep[0].toString();
            	    if ("ROOT".equals(parent)){
            	    	depIds += ("'"+ (dep[1]==null?"":dep[1].toString()) + "', ");
            	    }else{
            	    	depIds += ("'"+ (dep[1]==null?"":dep[1].toString()) + "', ");
            	    }
  				}
  			}
  		}
  		if (!"".equals(depIds)){
  			depIds = depIds.substring(0,depIds.length()-2);
  		}
		/*String depIds = "";
  		String userId = toolUtil.getUserId();
  		List data = this.findBySql("SELECT B.ID FROM BRANCH B " 
  				+ "INNER JOIN QX_USER_SHOWAUTH UD ON UD.BRANCH_DEP=B.ID AND UD.FLG='0' AND UD.USERID = '"+ userId +"' " 
  				+ "WHERE B.IS_DELETE = 0 ");
  		if (data!=null&&data.size()>0){
  			for (int i=0;i<data.size();i++){
  				Object b = (Object)data.get(i);
  				depIds += ("'"+ (b==null?"":b.toString()) + "', ");
  				//获取下级部门
  				List dataP = backendDepService.findBySql("SELECT D.PARENT,D.ID FROM QX_DEPARTMENT D " +
               		"INNER JOIN QX_USER_SHOWAUTH UD ON UD.BRANCH_DEP=D.ID AND UD.FLG='1' AND UD.USERID='"+userId+"' " +
               		"WHERE D.BARCH_ID='"+b+"' AND D.IS_DELETE=0 ");
  				for (int k=0; k<dataP.size(); k++){
  					Object[] dep=(Object[])dataP.get(k);
            	    String parent=dep[0]==null?"":dep[0].toString();
            	    if ("ROOT".equals(parent)){
            	    	depIds += ("'"+ (dep[1]==null?"":dep[1].toString()) + "', ");
            	    }else{
            	    	depIds += ("'"+ (dep[1]==null?"":dep[1].toString()) + "', ");
            	    }
  				}
  			}
  		}
  		if (!"".equals(depIds)){
  			depIds = depIds.substring(0,depIds.length()-2);
  		} else depIds = "''";*/
  		return depIds;
	}
	
	/**
	 * 根据部门ID获得所有部门-----递归
	 * @Title: getDepId 
	 * @return 
	 * String 
	 * @author zrl
	 * @since 2017年6月17日 V 1.0
	 */
	public String getall(String deptid,String retstr,String flgs,String fepflg){
	    QxDepartment dept=this.get(QxDepartment.class, deptid);
	    if("ROOT".equals(dept.getParent())){
	        if("".equals(retstr)){
	            retstr=dept.getId();
	        }else{
	            retstr=retstr+","+dept.getId();
	        }
	        if("".equals(flgs)){
	            flgs="1";
	        }else{
	            flgs=flgs+","+1;
	        }
	        fepflg=retstr+"and"+flgs+"branchid"+dept.getBarchId();
	        return fepflg;
	    }else{
	        retstr=retstr+","+dept.getId();
	        flgs=flgs+","+1;
	        fepflg=retstr+"and"+flgs+"branchid"+dept.getBarchId();
            return getall(dept.getParent() , retstr,flgs,fepflg);
	    } 
	    
	}
	/**
     * 根据部门ID获得所有公司-----递归
     * @Title: getDepId 
     * @return 
     * String 
     * @author zrl
     * @since 2017年6月17日 V 1.0
     */
    public String getall1(String deptid,String retstr,String flgs,String fepflg){
        Branch branch=this.get(Branch.class, deptid);
        
        if("-1".equals(branch.getPid())){
            if("".equals(retstr)){
                retstr= branch.getId();
            }else{
                retstr=retstr+","+branch.getId(); 
            }
            if("".equals(flgs)){
                flgs="0"; 
            }else{
                flgs=flgs+","+0;
            }
            
            fepflg=retstr+"and"+flgs; 
            return fepflg;
        }else{
            if("".equals(retstr)){
                retstr= branch.getId();
            }else{
                retstr=retstr+","+branch.getId(); 
            }
            if("".equals(flgs)){
                flgs="0"; 
            }else{
                flgs=flgs+","+0;
            }
            fepflg= retstr+"and"+flgs;
           return getall1(branch.getPid(), retstr,flgs,fepflg); 
        } 
    }
	//**************************************************************//
	//普通员工  正式工list页面
	@SuppressWarnings({ "rawtypes" })
	@Override
    public HashMap getZsgPerson(Pager pager, String searchData) {
		String depIds = getAllDepIdStrs();
        return personnelDao.getZsgPerson(pager, searchData,depIds);
    }

	//普通员工  试岗期list页面
	@SuppressWarnings({ "rawtypes" })
	@Override
	public HashMap getSgqPerson(Pager pager, String searchData)throws Exception {
		String depIds = getAllDepIdStrs();
	    return personnelDao.getSgqPerson(pager, searchData,depIds);
	}
	@Override
	public HashMap<String, Object> getSxsPerson(Pager pager, String searchData) {
		String depIds = getAllDepIdStrs();
		
		QxUser user=LingUtil.userinfo();
		String userId=user.getId();
		String num=adminDao.getRolesByUser(userId);
		if("1".equals(num)){
			String username=user.getUsername();
			BasicInformation info=this.get(Restrictions.eq("jobNumber", username));
			if(info==null||"".equals(info)){
				info=this.get(Restrictions.eq("practiceNum", username));
			}
			Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
			if(dataMap==null){
				dataMap=new HashMap<String,String>();
			}
			dataMap.put("personId", info.getId());
			searchData=JsonUtil.Encode(dataMap);
		}
		return personnelDao.getSxsPerson(pager, searchData,depIds);
	}

	@Override
	public HashMap<String, Object> getLwwbPerson(Pager pager, String searchData) {
		String depIds = getAllDepIdStrs();
		return personnelDao.getLwwbPerson(pager, searchData,depIds);
	}

	@Override
	public HashMap<String, Object> getYwwbPerson(Pager pager, String searchData) {
		String depIds = getAllDepIdStrs();
		return personnelDao.getYwwbPerson(pager, searchData,depIds);
	}

	@Override
	public HashMap<String, Object> getTxPerson(Pager pager, String searchData) {
		return personnelDao.getTxPerson(pager, searchData);
	}

	@Override
	public HashMap<String, Object> getLzPerson(Pager pager, String searchData) {
		return personnelDao.getLzPerson(pager, searchData);
	}

	@Override
	public HashMap<String, Object> getFpPerson(Pager pager, String searchData) {
		String depIds = getAllDepIdStrs();
		return personnelDao.getFpPerson(pager, searchData,depIds);
	}

	@Override
	public HashMap<String, Object> getFyjrPerson(Pager pager, String searchData) {
		String depIds = getAllDepIdStrs();
		return personnelDao.getFyjrPerson(pager, searchData,depIds);
	}
	
	@Override
	public HashMap<String, Object> getFzgPerson(Pager pager, String searchData) {
		String depIds = getAllDepIdStrs();
		return personnelDao.getFzgPerson(pager, searchData,depIds);
	}
	@Override
	public HashMap<String, Object> getJsgrPerson(Pager pager, String searchData) {
		String depIds = getAllDepIdStrs();
		return personnelDao.getJsgrPerson(pager, searchData,depIds);
	}
	@Override
	public HashMap<String, Object> getZyjsryPerson(Pager pager, String searchData) {
		String depIds = getAllDepIdStrs();
		return personnelDao.getZyjsryPerson(pager, searchData,depIds);
	}
	/**
	 * 动态表头导出数据
	 * @param searchData
	 * @param thetype
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	public List getOtherExcept(String searchData,String thetype){
		String depIds = getAllDepIdStrs();
		QxUser user=LingUtil.userinfo();
		String userId=user.getId();
		String num=adminDao.getRolesByUser(userId);
		if("1".equals(num)){
			String username=user.getUsername();
			BasicInformation info=this.get(Restrictions.eq("jobNumber", username));
			if(info==null||"".equals(info)){
				info=this.get(Restrictions.eq("practiceNum", username));
			}
			Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
			if(dataMap==null){
				dataMap=new HashMap<String,String>();
			}
			dataMap.put("personId", info.getId());
			searchData=JsonUtil.Encode(dataMap);
		}
		List<Object[]> list=personnelDao.getExceptPerson(searchData,depIds,thetype);//数据库获取数据
		List dataList = new ArrayList();
		 for (int i=0; i < list.size(); i++){
			 Map<String, Object> map=null;
			 if(thetype!=null&&!"".equals(thetype)){
			 
			 if(thetype.indexOf("shgx")>-1){
				List<Object[]> relation =relationDao.getExceptData(list.get(i)[0].toString());
				if(relation.size()>0){
					for(int j=0;j<relation.size();j++){//社会关系
						map = new HashMap<String, Object>();
						Object[] obj1 = (Object[]) relation.get(j);
						map=this.getPersonMap(list, i, map);
						map.put("shgxid", obj1[0]);//
						map.put("shgxname", obj1[1]);//名字
						map.put("shgxsex", obj1[2]);//性别
						map.put("shgxpoliticsStatus", obj1[3]);//政治面貌
						map.put("shgxrelation", obj1[4]);//与本人关系
						map.put("shgxborthDate", obj1[5]);//出生日期
						map.put("shgxcall", obj1[6]);//称谓
						map.put("shgxnation", obj1[7]);//民族
						map.put("shgxworkUnit", obj1[8]);//工作单位
						map.put("shgxremark", obj1[9]);//备注
						map.put("shgxstatus", obj1[10]);//状态
						map.put("shgxduty", obj1[11]);//职务
						dataList.add(map);
					}
				}else{
					map = new HashMap<String, Object>();
					dataList.add(this.getPersonMap(list, i, map));
				}
			 }
			//兼职
			 if(thetype.indexOf("jzgl")>-1){//兼职
					List<Object[]> relation =ptJobDao.getExceptData(list.get(i)[0].toString());
					if(relation.size()>0){
						for(int j=0;j<relation.size();j++){
							map = new HashMap<String, Object>();
							Object[] obj1 = (Object[]) relation.get(j);
							map=this.getPersonMap(list, i, map);
							map.put("id", obj1[0]);//
							map.put("standardPost", obj1[1]);//标准岗位
							map.put("spePost", obj1[2]);//部门岗位
							map.put("beginTime", obj1[3]);//开始时间
							map.put("endTime", obj1[4]);//结束时间
							map.put("state", obj1[5]);//状态
							map.put("bz", obj1[6]);//备注
							map.put("firm", obj1[7]);//公司
							map.put("dep", obj1[8]);//部门
							map.put("endCase", obj1[9]);//结束原因
							map.put("jobLevel", obj1[10]);//职级
							dataList.add(map);
						}
					}else{
						map = new HashMap<String, Object>();
						dataList.add(this.getPersonMap(list, i, map));
					}
			 }
			//职称聘任
			 if(thetype.indexOf("zcpr")>-1){//职称聘任
					List<Object[]> relation =jobTitleDao.getExceptData(list.get(i)[0].toString());
					if(relation.size()>0){
						for(int j=0;j<relation.size();j++){
							map = new HashMap<String, Object>();
							Object[] obj1 = (Object[]) relation.get(j);
							map=this.getPersonMap(list, i, map);
							
							map.put("classType", obj1[1]);//系别
							map.put("statusClass", obj1[2]);//资格类别
							map.put("profession", obj1[3]);//专业
							map.put("jugdeOffice", obj1[4]);//评审机关
							map.put("certificateNum", obj1[5]);//证书编号
							map.put("sendCertificate", obj1[6]);//发证机关
							map.put("awardDate", obj1[7]);//授予日期
							map.put("certificateOffice", obj1[8]);//证书工作单位
							map.put("isHighest", obj1[9]);//是否最高级别
							map.put("foreignType", obj1[10]);//外语语种
							map.put("foreignLevel", obj1[11]);//外语等级
							map.put("foreignClass", obj1[12]);//外语专业类
							map.put("foreignDate", obj1[13]);//外语通过日期
							map.put("foreignRemark", obj1[14]);//外语备注
							map.put("computerDate", obj1[15]);//计算机通过日期
							map.put("computerRemark", obj1[16]);//计算机备注
							map.put("professionDate", obj1[17]);//专业通过日期
							map.put("professionRemark", obj1[18]);//专业技术备注
							map.put("declareLevel1", obj1[19]);//可晋升级别1
							map.put("declareDate1", obj1[20]);//可申报时间1
							map.put("declareLevel2", obj1[21]);//可晋升级别2
							map.put("declareDate2", obj1[22]);//可申报时间2
							map.put("engageTitle", obj1[23]);//聘任职称
							map.put("beginDate", obj1[24]);//聘期开始时间
							map.put("endDate", obj1[25]);//聘期结束时间
							map.put("engageTimes", obj1[26]);//聘任次数
							map.put("remark", obj1[27]);//
							dataList.add(map);
						}
					}else{
						map = new HashMap<String, Object>();
						dataList.add(this.getPersonMap(list, i, map));
					}
			 }
			 
			//工作经历
			 if(thetype.indexOf("gzjl")>-1){//工作经历
					List<Object[]> relation =careerDao.getExceptData(list.get(i)[0].toString());
					if(relation.size()>0){
						for(int j=0;j<relation.size();j++){
							map = new HashMap<String, Object>();
							Object[] obj1 = (Object[]) relation.get(j);
							map=this.getPersonMap(list, i, map);
							
							map.put("gzjlcompany", obj1[2]);//公司
							map.put("gzjldepartment", obj1[3]);//部门
							map.put("gzjlpost", obj1[4]);//岗位
							map.put("gzjlproDuty", obj1[5]);//证明人职务
							map.put("gzjlproTel", obj1[7]);//证明人电话
							map.put("gzjlbegindate", obj1[10]);//开始时间
							map.put("gzjlenddate", obj1[11]);//结束时间
							map.put("gzjlremark", obj1[12]);//备注
							map.put("gzjlprove", obj1[16]);//证明人
							map.put("gzjljobLevel", obj1[20]);//职务
							
							dataList.add(map);
						}
					}else{
						map = new HashMap<String, Object>();
						dataList.add(this.getPersonMap(list, i, map));
					}
			 }
			 if(thetype.indexOf("dzda")>-1){//电子档案
					List<Object[]> relation =recordDao.getExceptData(list.get(i)[0].toString());
					if(relation.size()>0){
						for(int j=0;j<relation.size();j++){
							map = new HashMap<String, Object>();
							Object[] obj1 = (Object[]) relation.get(j);
							map=this.getPersonMap(list, i, map);
							map.put("reType", obj1[1]);//档案类型
							map.put("reName", obj1[2]);//档案名字
							map.put("reFileNum", obj1[3]);//档案文号
							map.put("reContent", obj1[4]);//档案内容简介
							map.put("reYear", obj1[5]);//但按年份
							map.put("reNum", obj1[6]);//档案编号
							map.put("extendName", obj1[7]);//扩展名
							map.put("fileSize", obj1[8]);//文件大小
							map.put("dzdaremark", obj1[9]);//备注
							map.put("createName", obj1[10]);//创建者名称
							map.put("createDate", obj1[11]);//创建日期
							map.put("createNum", obj1[12]);//创建者工号
							dataList.add(map);
						}
					}else{
						map = new HashMap<String, Object>();
						dataList.add(this.getPersonMap(list, i, map));
					}
			 }
			 if(thetype.indexOf("jljl")>-1){//奖励记录
					List<Object[]> relation =awardDao.getExceptData(list.get(i)[0].toString());
					if(relation.size()>0){
						for(int j=0;j<relation.size();j++){
							map = new HashMap<String, Object>();
							Object[] obj1 = (Object[]) relation.get(j);
							map=this.getPersonMap(list, i, map);
							
							map.put("awardName", obj1[1]);//奖励名称
							map.put("awarddate", obj1[2]);//奖励时间
							map.put("approveNum", obj1[3]);//批准文号
							map.put("approveOffice", obj1[4]);//批准单位
							map.put("awardClass", obj1[5]);//奖励种类 
							map.put("awardType", obj1[6]);//奖励方式
							map.put("awardLevel", obj1[7]);//奖励级别
							map.put("awardCause", obj1[8]);//奖励原因
							map.put("awardStep", obj1[9]);//奖励措施
							map.put("awardAmount", obj1[10]);//奖励金额
							dataList.add(map);
						}
					}else{
						map = new HashMap<String, Object>();
						dataList.add(this.getPersonMap(list, i, map));
					}
			 }
			 
			 if(thetype!=null&&thetype.indexOf("cfjl")>-1){//惩罚记录
					List<Object[]> relation =punishDao.getExceptData(list.get(i)[0].toString());
					SimpleDateFormat form =new SimpleDateFormat("yyyy-MM-dd");
					String stamp = "";
					if(relation.size()>0){
						for(int j=0;j<relation.size();j++){
							map = new HashMap<String, Object>();
							Object[] obj1 = (Object[]) relation.get(j);
							map=this.getPersonMap(list, i, map);
							
							
							map.put("punishName", obj1[1]);//惩处名称
							
							if(obj1[2]!=null&&!"".equals(obj1[2])){
								stamp=form.format(obj1[2]);
								map.put("punishdate", stamp);//惩处时间
							}
							map.put("approveNum", obj1[3]);//惩处文号
							map.put("approveOffice", obj1[4]);//惩处单位
							map.put("punishClass", obj1[5]);//惩处种类
							map.put("punishType", obj1[6]);//惩处方式
							map.put("punishLevel", obj1[7]);//惩处级别
							map.put("punishCause", obj1[8]);//惩处原因
							map.put("punishStep", obj1[9]);//惩处措施
							map.put("punishAmount", obj1[10]);//惩处金额
							if(obj1[11]!=null&&!"".equals(obj1[11])){
								stamp=form.format(obj1[11]);//
								map.put("cfjlbegindate", stamp);//开始时间
							}
							if(obj1[12]!=null&&!"".equals(obj1[12])){
								stamp=form.format(obj1[12]);
								map.put("cfjlenddate", stamp);//结束时间
							}
							map.put("isRecall", obj1[13]);//是否撤销
							map.put("recallOffice", obj1[14]);//撤销单位
							map.put("recallNum", obj1[15]);//撤销文号
							if(obj1[16]!=null&&!"".equals(obj1[16])){
								stamp=form.format(obj1[16]);
								map.put("recalldate", stamp);//撤销时间
							}
							dataList.add(map);
						}
					}else{
						map = new HashMap<String, Object>();
						dataList.add(this.getPersonMap(list, i, map));
					}
			 }
			 if(thetype.indexOf("jkda")>-1){//健康档案
					List<Object[]> relation =healthDao.getExceptData(list.get(i)[0].toString());
					SimpleDateFormat form =new SimpleDateFormat("yyyy-MM-dd");
					String stamp = "";
					if(relation.size()>0){
						for(int j=0;j<relation.size();j++){
							map = new HashMap<String, Object>();
							Object[] obj1 = (Object[]) relation.get(j);
							map=this.getPersonMap(list, i, map);
							
							map.put("jkdaid", obj1[0]);
							map.put("jkdaheight", obj1[1]);//身高
							map.put("jkdabloodType", obj1[2]);//血型
							map.put("jkdaweight", obj1[3]);//体重
							map.put("jkdahealth", obj1[4]);//健康状况
							map.put("jkdacheckType", obj1[5]);//检查种类
           					if(obj1[6]!=null&&!"".equals(obj1[6])){
								stamp=form.format(obj1[6]);
								map.put("checkDate", stamp);//检查日期
							}
							map.put("checkResult", obj1[7]);//检查结果
							map.put("professionTaboo", obj1[8]);//是否职业禁忌
							map.put("tabooContent", obj1[9]);//职业禁忌内容
							map.put("remark", obj1[10]);//备注
							dataList.add(map);
						}
					}else{
						map = new HashMap<String, Object>();
						dataList.add(this.getPersonMap(list, i, map));
					}
			 }
			 if(thetype.indexOf("zzgl")>-1){//证照管理
					List<Object[]> relation =licenseDao.getExceptData(list.get(i)[0].toString());
					
					if(relation.size()>0){
						for(int j=0;j<relation.size();j++){
							map = new HashMap<String, Object>();
							Object[] obj1 = (Object[]) relation.get(j);
							map=this.getPersonMap(list, i, map);
							
							map.put("licenseType", obj1[1]);
							map.put("licenseNum", obj1[2]);
							
							//map.put("licenseStatus", obj[9]);
							map.put("begindate", obj1[4]);//
							map.put("enddate", obj1[5]);
							if(obj1[5]!=null&&!"".equals(obj1[5])){
								String countdate=countDate(obj1[5].toString());
								if(countdate!=null&&"-1".equals(countdate)){
									map.put("licenseStatus", "2");
									map.put("loseDate", "-1");
					    		}else if(countdate==null){
					    			
					    		}else{
					    			map.put("licenseStatus", "1");
									map.put("loseDate", countdate);
					    		}
							}
							map.put("validity", obj1[6]);
							map.put("office", obj1[8]);
							map.put("isRecheck", obj1[9]);
							map.put("lastRecheckday", obj1[10]);
							map.put("nextRecheckday", obj1[11]);
							map.put("recheckDays", obj1[12]);
							map.put("isBackpay", obj1[13]);
							map.put("backpayDate", obj1[14]);
							map.put("tackOffice", obj1[15]);
							map.put("tackName", obj1[16]);
							map.put("tackDate", obj1[17]);
							map.put("deliverName", obj1[18]);
							map.put("deliverDate", obj1[19]);
							map.put("remark", obj1[20]);
							map.put("theClass", obj1[21]);
							dataList.add(map);
						}
					}else{
						map = new HashMap<String, Object>();
						dataList.add(this.getPersonMap(list, i, map));
					}
			 }
			 if(thetype.indexOf("ygfl")>-1){//员工福利
					List<Object[]> relation =empWelfareDao.getExceptData(list.get(i)[0].toString());
					SimpleDateFormat form =new SimpleDateFormat("yyyy-MM-dd");
					String stamp = "";
					if(relation.size()>0){
						for(int j=0;j<relation.size();j++){
							map = new HashMap<String, Object>();
							Object[] obj1 = (Object[]) relation.get(j);
							map=this.getPersonMap(list, i, map);
							
							map.put("welType", obj1[1]);
							map.put("welName", obj1[2]);
							map.put("welContent", obj1[3]);
							
							if(obj1[4]!=null&&!"".equals(obj1[4])){
								stamp=form.format(obj1[4]);
								map.put("begindate", stamp);
							}
							if(obj1[5]!=null&&!"".equals(obj1[5])){
								stamp=form.format(obj1[5]);
								map.put("enddate", stamp);
							}
							map.put("isend", obj1[6]);
							map.put("cost", obj1[7]);
							map.put("unitApproved", obj1[8]);
							map.put("approver", obj1[9]);
							map.put("remark", obj1[10]);
							dataList.add(map);
						}
					}else{
						map = new HashMap<String, Object>();
						dataList.add(this.getPersonMap(list, i, map));
					}
			 }
			 
			 if(thetype.indexOf("gsgl")>-1){//工伤管理
					List<Object[]> relation =injuryDao.getExceptData(list.get(i)[0].toString());
					SimpleDateFormat form =new SimpleDateFormat("yyyy-MM-dd");
					String stamp = "";
					if(relation.size()>0){
						for(int j=0;j<relation.size();j++){
							map = new HashMap<String, Object>();
							Object[] obj1 = (Object[]) relation.get(j);
							map=this.getPersonMap(list, i, map);
							map.put("onPost", obj1[1]);
							if(obj1[5]!=null&&!"".equals(obj1[5])){
								stamp=form.format(obj1[5]);
								map.put("injuryDate", stamp);
							}
							
							map.put("injuryProcess", obj1[6]);
							map.put("injuryPart", obj1[7]);
							map.put("injuryCause", obj1[8]);
							map.put("injuryType", obj1[9]);
							map.put("manageStatus", obj1[10]);
							//map.put("healBegindate", obj[17]);
							if(obj1[11]!=null&&!"".equals(obj1[11])){
								stamp=form.format(obj1[11]);
								map.put("healBegindate", stamp);
							}
							if(obj1[12]!=null&&!"".equals(obj1[12])){
								stamp=form.format(obj1[12]);
								map.put("healEnddate", stamp);
							}
							
							map.put("healCosts", obj1[13]);
							map.put("injurySubsidy", obj1[14]);
							if(obj1[15]!=null&&!"".equals(obj1[15])){
								stamp=form.format(obj1[15]);
								map.put("authDate", stamp);
							}
							
							map.put("healSubsidy", obj1[16]);
							map.put("healSubsidySocial", obj1[17]);
							map.put("healSubsidyFirm", obj1[18]);
							map.put("employSubsidy", obj1[19]);
							map.put("employSubsidySocial", obj1[20]);
							map.put("employSubsidyFilm", obj1[21]);
							map.put("remark", obj1[22]);
							map.put("cuteProcess", obj1[23]);
							map.put("injuryUnit", obj1[24]);
							map.put("injuryDepartment", obj1[25]);
							map.put("injuryPost", obj1[26]);
							dataList.add(map);
						}
					}else{
						map = new HashMap<String, Object>();
						dataList.add(this.getPersonMap(list, i, map));
					}
			 }
			 
			 if(thetype.indexOf("ygjn")>-1){//员工技能
					List<Object[]> relation =empSkillDao.getExceptData(list.get(i)[0].toString());
					SimpleDateFormat form =new SimpleDateFormat("yyyy-MM-dd");
					String stamp = "";
					if(relation.size()>0){
						for(int j=0;j<relation.size();j++){
							map = new HashMap<String, Object>();
							Object[] obj1 = (Object[]) relation.get(j);
							map=this.getPersonMap(list, i, map);
							
							map.put("skillDes", obj1[1]);
							map.put("papersClass", obj1[2]);
							map.put("papersType", obj1[3]);
							map.put("papersNum", obj1[4]);//人名字
							map.put("papersStatus", obj1[5]);
							if(obj1[6]!=null&&!"".equals(obj1[6])){
								stamp=form.format(obj1[6]);
								map.put("begindate", stamp);
							}
							if(obj1[7]!=null&&!"".equals(obj1[7])){
								stamp=form.format(obj1[7]);
								map.put("enddate", stamp);
							}
							map.put("validityPeriod", obj1[8]);
							map.put("dates", obj1[9]);
							map.put("office", obj1[10]);
							map.put("remark", obj1[11]);
							dataList.add(map);
						}
					}else{
						map = new HashMap<String, Object>();
						dataList.add(this.getPersonMap(list, i, map));
					}
			 }
			 if(thetype.indexOf("jgxx")>-1){//技工信息
					List<Object[]> relation =skilledEmpDao.getExceptData(list.get(i)[0].toString());
					SimpleDateFormat form =new SimpleDateFormat("yyyy-MM-dd");
					String stamp = "";
					if(relation.size()>0){
						for(int j=0;j<relation.size();j++){
							map = new HashMap<String, Object>();
							Object[] obj1 = (Object[]) relation.get(j);
							map=this.getPersonMap(list, i, map);
							
							
							map.put("skillType", obj1[1]);
							map.put("skillGrade", obj1[2]);
							map.put("judgeOffice", obj1[3]);
							map.put("papersNum", obj1[4]);//人名字
							map.put("office", obj1[5]);
							if(obj1[6]!=null&&!"".equals(obj1[6])){
								stamp=form.format(obj1[6]);
								map.put("awarddate", stamp);
							}
							map.put("papersOffice", obj1[7]);
							
							map.put("grade", obj1[8]);
							map.put("highestLevel", obj1[9]);
							map.put("appellation", obj1[10]);
							if(obj1[11]!=null&&!"".equals(obj1[11])){
								stamp=form.format(obj1[11]);
								map.put("begindate", stamp);
							}
							//map.put("begindate", obj[17]);
							map.put("getForm", obj1[12]);
							if(obj1[13]!=null&&!"".equals(obj1[13])){
								stamp=form.format(obj1[13]);
								map.put("enddate", stamp);
							}
							//map.put("enddate", obj[19]);
							map.put("times", obj1[14]);
							map.put("remark", obj1[15]);
							dataList.add(map);
						}
					}else{
						map = new HashMap<String, Object>();
						dataList.add(this.getPersonMap(list, i, map));
					}
			 }
			 if(thetype.indexOf("fzxx")>-1){//复转信息
					List<Object[]> relation =soldierBackDao.getExceptData(list.get(i)[0].toString());
					SimpleDateFormat form =new SimpleDateFormat("yyyy-MM-dd");
					String stamp = "";
					if(relation.size()>0){
						for(int j=0;j<relation.size();j++){
							map = new HashMap<String, Object>();
							Object[] obj1 = (Object[]) relation.get(j);
							map=this.getPersonMap(list, i, map);
							
							map.put("insolDate", obj1[1]);
							map.put("outsolDate", obj1[2]);
							map.put("solType", obj1[3]);
							map.put("solLevel", obj1[4]);
							map.put("solDuty", obj1[5]);
							map.put("solRank", obj1[6]);
							map.put("troops", obj1[7]);
							dataList.add(map);
						}
					}else{
						map = new HashMap<String, Object>();
						dataList.add(this.getPersonMap(list, i, map));
					}
			 }
			 } else{
				 map = new HashMap<String, Object>();
				 dataList.add(this.getPersonMap(list, i, map));
			 }
		}
		return dataList;
	 }
	 
	 
	 public Map getPersonMap(List list,Integer i,Map map){
		 	Object[] obj = (Object[]) list.get(i);
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);//职工号
			map.put("name", obj[3]);//职工姓名
			if(obj[17]!=null&&!"".equals(obj[17])){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
			String thisDay = sdf.format(new Date());
			String brithDay = sdf.format(obj[17]);
			Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
			map.put("age", age);//年龄
			}
			//map.put("sex", obj[5]);
			if(obj[5]!=null){
				String emp=obj[5].toString();
				if("0".equals(emp)){
					map.put("sex", "女");//性别
				}else if("1".equals(emp)){
					map.put("sex", "男");//
				}
			}
			
			if(obj[6]!=null){
				String emp=obj[6].toString();
				if("0".equals(emp)){
					map.put("isMarried", "未婚");///婚姻状况
				}else if("1".equals(emp)){
					map.put("isMarried", "已婚");
				}else if("2".equals(emp)){
					map.put("isMarried", "离婚");
				}else if("3".equals(emp)){
					map.put("isMarried", "丧偶");
				}
			}
			String jobLevel="";
			if(obj[7]!=null){
				JcDictionary aa=this.get(JcDictionary.class, Restrictions.eq("dicvalue", obj[7].toString()),Restrictions.eq("pid", "402881935ba8c785015ba8e043c80011"));
				if(aa!=null){
					jobLevel=aa.getDicname();
				}
			}
			map.put("jobLevel", jobLevel);//职级
			if(obj[8]!=null){
				String emp=obj[8].toString();
				if("0".equals(emp)){
					map.put("empType", "正式工");//职工类型
				}else if("1".equals(emp)){
					map.put("empType", "劳务外包");
				}else if("2".equals(emp)){
					map.put("empType", "业务外包");
				}else if("3".equals(emp)){
					map.put("empType", "实习生");
				}
			}
			if(obj[9]!=null){
				String emp=obj[9].toString();
				if("0".equals(emp)){
					map.put("onJob", "实习期");//在职状态
				}else if("1".equals(emp)){
					map.put("onJob", "试用期");
				}else if("2".equals(emp)){
					map.put("onJob", "正式工");
				}else if("3".equals(emp)){
					map.put("onJob", "离职");
				}else if("4".equals(emp)){
					map.put("onJob", "退休");
				}else if("5".equals(emp)){
					map.put("onJob", "返聘");
				}
			}
			
			if(obj[10]!=null){
				String emp=obj[10].toString();
				if("0".equals(emp)){
					map.put("onPost", "正常在岗");//在岗状态
				}else if("3".equals(emp)){
					map.put("onPost", "长病假");
				}else if("4".equals(emp)){
					map.put("onPost", "内退");
				}else if("11".equals(emp)){
					map.put("onPost", "长伤假");
				}else if("12".equals(emp)){
					map.put("onPost", "待岗");
				}
			}
			map.put("post", obj[11]);//标准岗位
			map.put("specificPost", obj[12]);//部门岗位
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);///公司
			map.put("departName", obj[15]);//部门
			map.put("jobNumberJt", obj[16]);//集团工号
			
			String idType="";
			if(obj[18]!=null){
				JcDictionary aa=this.get(JcDictionary.class, Restrictions.eq("dicvalue", obj[18].toString()),Restrictions.eq("pid", "402881935ba8c785015ba8d9d7c0000b"));
				if(aa!=null){
					idType=aa.getDicname();
				}
			}
			map.put("idType", idType);//证件类型
			//map.put("idType", obj[18]);//-----------
			
			map.put("idNumber", obj[19]);//证件号码
			//map.put("edu1", obj[21]);
			if(obj[21]!=null){
				String emp=obj[21].toString();
				if(emp.equals("1")){
					map.put("edu1", "本科");//全日制最高学历
				}else if(emp.equals("0")){
					map.put("edu1", "研究生");
				}else if(emp.equals("2")){
					map.put("edu1", "专科");
				}else if(emp.equals("3")){
					map.put("edu1", "中专");
				}else if(emp.equals("4")){
					map.put("edu1", "技工学校");
				}else if(emp.equals("5")){
					map.put("edu1", "高中");
				}else if(emp.equals("6")){
					map.put("edu1", "初中");
				}else if(emp.equals("7")){
					map.put("edu1", "小学");
				}else if(emp.equals("8")){
					map.put("edu1", "文盲或半文盲");
				}
			}
			if(obj[22]!=null){
				String emp=obj[22].toString();
				if(emp.equals("1")){
					map.put("edu2", "本科");//在职最高学历
				}else if(emp.equals("0")){
					map.put("edu2", "研究生");
				}else if(emp.equals("2")){
					map.put("edu2", "专科");
				}else if(emp.equals("3")){
					map.put("edu2", "中专");
				}else if(emp.equals("4")){
					map.put("edu2", "技工学校");
				}else if(emp.equals("5")){
					map.put("edu2", "高中");
				}else if(emp.equals("6")){
					map.put("edu2", "初中");
				}else if(emp.equals("7")){
					map.put("edu2", "小学");
				}else if(emp.equals("8")){
					map.put("edu2", "文盲或半文盲");
				}
			}
			//map.put("workType", obj[23]);//用工形式---------
			String workType="";
			if(obj[23]!=null){
				JcDictionary jd=this.get(JcDictionary.class, Restrictions.eq("dicvalue", obj[23].toString()),Restrictions.eq("pid", "402881955da5b404015da5b98cad000a"));
				if(jd!=null){
					workType=jd.getDicname();
				}
				//workType=this.get(JcDictionary.class, Restrictions.eq("dicvalue", obj[23].toString()),Restrictions.eq("pid", "402881955da5b404015da5b98cad000a")).getDicname();
			}
			map.put("workType", workType);//用工形式
			
			
			map.put("oldName", obj[24]);//曾用名
			//map.put("nation", obj[25]);
			String nation="";
			if(obj[25]!=null){
				JcDictionary jd=this.get(JcDictionary.class, Restrictions.eq("dicvalue", obj[25].toString()),Restrictions.eq("pid", "402881955cce6bd4015cce9be464000b"));
				if(jd!=null){
					nation=jd.getDicname();
				}
			}
			map.put("nation", nation);//民族
			
			map.put("borthPlaceP", obj[26]);//出生地--省
			map.put("borthPlaceC", obj[27]);//出生地--市
			map.put("borthPlaceA", obj[28]);//出生地--区
			map.put("borthPlace", obj[29]);//出生地具体
			
			map.put("nativePlaceP", obj[30]);//籍贯--省
			map.put("nativePlaceC", obj[31]);//籍贯--市
			map.put("nativePlaceA", obj[32]);//籍贯--区
			map.put("nativePlace", obj[33]);//籍贯
			
			map.put("setWorkDate", obj[34]);//开始工作日期
			map.put("annualLeave", obj[35]);//年假扣减工龄
			if(obj[36]!=null){
				String emp=obj[36].toString();
				if("0".equals(emp)){
					map.put("politicsStatus", "党员");//政治面貌
				}else if("1".equals(emp)){
					map.put("politicsStatus", "预备党员");
				}else if("2".equals(emp)){
					map.put("politicsStatus", "团员");
				}else if("3".equals(emp)){
					map.put("politicsStatus", "群众");
				}else{
					map.put("politicsStatus", "其它");
				}
			}
			///map.put("politicsStatus", obj[36]);//
			//map.put("highestTitle", obj[37]);//------------
			
			String highestTitle="";
			if(obj[37]!=null){
				JcDictionary jcd=this.get(JcDictionary.class, Restrictions.eq("dicvalue", obj[37].toString()),Restrictions.eq("pid", "402881955d176487015d17682e54000a"));
				if(jcd!=null){
					highestTitle=jcd.getDicname();
				}
			}
			map.put("highestTitle", highestTitle);//国家序列最高职称
			
			String proQuaLevel="";
			if(obj[38]!=null){
				JcDictionary jcd=this.get(JcDictionary.class, Restrictions.eq("dicvalue", obj[38].toString()),Restrictions.eq("pid", "402881955d176487015d17685ddb000b"));
				if(jcd!=null){
					proQuaLevel=jcd.getDicname();
				}
				//proQuaLevel=this.get(JcDictionary.class, Restrictions.eq("dicvalue", obj[38].toString()),Restrictions.eq("pid", "402881955d176487015d17685ddb000b")).getDicname();
			}
			map.put("proQuaLevel", proQuaLevel);//国家职业资格等级
			
			String foreignType="";
			if(obj[39]!=null){
			    JcDictionary jcd= this.get(JcDictionary.class, Restrictions.eq("dicvalue", obj[39].toString()),Restrictions.eq("pid", "402881a05b650042015b663bdfe9003d"));
				if(jcd!=null){
				    foreignType=jcd.getDicname();
                }
			}
			map.put("foreignType", foreignType);//外语语种
			if(obj[40]!=null){
				String emp=obj[40].toString();
				if("0".equals(emp)){
					map.put("english", "二级");//外语等级
				}else if("1".equals(emp)){
					map.put("english", "三级");
				}else if("2".equals(emp)){
					map.put("english", "四级");
				}else if("3".equals(emp)){
					map.put("english", "六级");
				}else if("4".equals(emp)){
					map.put("english", "八级");
				}
			}
			//map.put("english", obj[40]);//
			if(obj[41]!=null){
				String emp=obj[41].toString();
				if("0".equals(emp)){
					map.put("computerLevel", "一级");//计算机等级
				}else if("1".equals(emp)){
					map.put("computerLevel", "二级");
				}else if("2".equals(emp)){
					map.put("computerLevel", "三级");
				}else if("3".equals(emp)){
					map.put("computerLevel", "四级");
				}
			}
			//map.put("computerLevel", obj[41]);//
			map.put("hobby", obj[42]);//特长，爱好
			//map.put("empSource", obj[43]);//---------------
			String empSource="";
			if(obj[43]!=null){
				JcDictionary aa=this.get(JcDictionary.class, Restrictions.eq("dicvalue", obj[43].toString()),Restrictions.eq("pid", "402881935baceaa1015badf989e30015"));
				if(aa!=null){
					empSource=aa.getDicname();
				}
			}
			map.put("empSource", empSource);//职工来源（例如：学生）
			map.put("formerIdentity", obj[44]);//原身份（例如：项目经理）
			map.put("height", obj[45]);//身高
			map.put("weight", obj[46]);//体重
			if(obj[47]!=null){
				String emp=obj[47].toString();
				if("0".equals(emp)){
					map.put("blood", "A型");//血型
				}else if("1".equals(emp)){
					map.put("blood", "B型");
				}else if("2".equals(emp)){
					map.put("blood", "AB型");
				}else if("3".equals(emp)){
					map.put("blood", "O型");
				}else if("4".equals(emp)){
					map.put("blood", "熊猫血");
				}else if("5".equals(emp)){
					map.put("blood", "其它");
				}
			}
			//map.put("blood", obj[47]);//
			map.put("health", obj[48]);//健康状况
			if(obj[49]!=null){
				String emp=obj[49].toString();
				if("0".equals(emp)){
					map.put("isSoldier", "不是");//是否军人
				}else if("1".equals(emp)){
					map.put("isSoldier", "是");
				}
			}
			//map.put("isSoldier", obj[49]);
			map.put("remark1", obj[50]);//备注1
			map.put("remark3", obj[51]);//备注2
			map.put("officePhone", obj[52]);//办公室电话
			map.put("phone", obj[53]);//手机 
			map.put("officeEmail", obj[54]);//办公邮箱
			map.put("email", obj[55]);//私人邮箱
			
			String residencePlacep1="";
			String residencePlacec1="";
			String residencePlacea1="";
			String residencePlace1="";
			if(obj[56]!=null){
				residencePlacep1=this.get(AdressProvince.class, Restrictions.eq("proid", obj[56].toString()))!=null?this.get(AdressProvince.class, Restrictions.eq("proid", obj[56].toString())).getPro():"";
			}
			if(obj[57]!=null){
				residencePlacec1=this.get(AdressCity.class, Restrictions.eq("cityid", obj[57].toString()))!=null?this.get(AdressCity.class, Restrictions.eq("cityid", obj[57].toString())).getCity():"";
			}
			if(obj[58]!=null){
				residencePlacea1=this.get(AdressArea.class, Restrictions.eq("areaid", obj[58].toString()))!=null?this.get(AdressArea.class, Restrictions.eq("areaid", obj[58].toString())).getArea():"";
			}
			if(obj[59]!=null){
				residencePlace1=obj[59].toString();
			}
			String residencePlace=residencePlacep1+residencePlacec1+residencePlacea1+residencePlace1;
			map.put("residencePlace", residencePlace);//户籍地址---整合
			
			String addressP1="";
			String addressC1="";
			String addressA1="";
			String address1="";
			if(obj[60]!=null){
				addressP1=this.get(AdressProvince.class, Restrictions.eq("proid", obj[60].toString()))!=null?this.get(AdressProvince.class, Restrictions.eq("proid", obj[60].toString())).getPro():"";
			}
			if(obj[61]!=null){
				addressC1=this.get(AdressCity.class, Restrictions.eq("cityid", obj[61].toString()))!=null?this.get(AdressCity.class, Restrictions.eq("cityid", obj[61].toString())).getCity():"";
			}
			if(obj[62]!=null){
				addressA1=this.get(AdressArea.class, Restrictions.eq("areaid", obj[62].toString()))!=null?this.get(AdressArea.class, Restrictions.eq("areaid", obj[62].toString())).getArea():"";
			}
			if(obj[63]!=null){
				address1=obj[63].toString();
			}
			String address=addressP1+addressC1+addressA1+address1;
			map.put("address", address);//现住址
			
			
			map.put("messageAddress", obj[64]);//通讯地址
			map.put("urgentPeople", obj[65]);//紧急联系人
			map.put("upPhone", obj[66]);//联系电话
			
			String upAddressP1="";
			String upAddressC1="";
			String upAddressA1="";
			String upAddress1="";
			if(obj[67]!=null){
			    AdressProvince province =this.get(AdressProvince.class, Restrictions.eq("proid", obj[67].toString()));
			    if (null != province) {
			        upAddressP1=province.getPro();
			    }
			}
			if(obj[68]!=null){
			    AdressCity city=this.get(AdressCity.class, Restrictions.eq("cityid", obj[68].toString()));
			    if (null != city)
			        upAddressC1=city.getCity();
			}
			if(obj[69]!=null){
			    AdressArea area=this.get(AdressArea.class, Restrictions.eq("areaid", obj[69].toString()));
			    if (null != area)
			        upAddressA1=area.getArea();
			}
			if(obj[70]!=null){
				upAddress1=obj[70].toString();
			}
			String upAddress=upAddressP1+upAddressC1+upAddressA1+upAddress1;
			map.put("upAddress", upAddress);//联系地址
			map.put("inCompanyTime", obj[71]);////入司时间
			map.put("inCompanyTime2", obj[72]);////入司时间2
			map.put("inPortTime", obj[73]);//入港时间
			map.put("inPortTime2", obj[74]);//入港时间2
			String laborCompany="";
			if(obj[75]!=null){
				JcDictionary aa=this.get(JcDictionary.class, Restrictions.eq("dicvalue", obj[75].toString()),Restrictions.eq("pid", "402881955d1ba95e015d1bbcbed5000a"));
				if(aa!=null){
					laborCompany=aa.getDicname();
				}
				
			}
			map.put("laborCompany", laborCompany);//劳务公司
			
			if(obj[76]!=null){
				String emp=obj[76].toString();
				if("0".equals(emp)){
					map.put("isSkilled", "不是");//是否技术工人
				}else if("1".equals(emp)){
					map.put("isSkilled", "是");
				}
			}
			if(obj[77]!=null){
				String emp=obj[77].toString();
				if("0".equals(emp)){
					map.put("isSpecialty", "不是");//是否专业技术人员
				}else if("1".equals(emp)){
					map.put("isSpecialty", "是");
				}
			}
			if(obj[78]!=null){
				String emp=obj[78].toString();
				if("0".equals(emp)){
					map.put("jgbz", "不是");//机关编制
				}else if("1".equals(emp)){
					map.put("jgbz", "是");
				}
			}
			map.put("beginTime", obj[79]);//开始时间
			map.put("career", obj[80]);//任职年限
			map.put("degree", obj[81]);//全日制所得学位
			map.put("degree2", obj[82]);//在职所得学位
			map.put("entranceDate", obj[83]);//全日制入学时间
			map.put("entranceDate2", obj[84]);//在职入学时间
			map.put("graduateDate", obj[85]);//全日制毕业时间
			map.put("graduateDate2", obj[86]);//在职毕业时间
			map.put("school", obj[87]);//全日制学校
			map.put("school2", obj[88]);//在职学校
			map.put("profession", obj[89]);//全日制专业
			map.put("profession2", obj[90]);//在职专业
			return map;
	 }
	
	 
	 public String countDate(String date2){
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    	Date aaa=new Date();
	    	Date bbb=null;
			try {
				bbb = sdf.parse(date2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    	long time1 = aaa.getTime();  
	        long time2 = bbb.getTime();  
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
}