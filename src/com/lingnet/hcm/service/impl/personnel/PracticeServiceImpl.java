package com.lingnet.hcm.service.impl.personnel;

import java.util.HashMap;
import java.util.HashSet;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.CareerDao;
import com.lingnet.hcm.dao.personnel.ChangeDao;
import com.lingnet.hcm.dao.personnel.PersonnelDao;
import com.lingnet.hcm.dao.personnel.PtJobDao;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.Change;
import com.lingnet.hcm.entity.person.PtJob;
import com.lingnet.hcm.service.personnel.ChangeService;
import com.lingnet.hcm.service.personnel.PracticeService;
import com.lingnet.qxgl.dao.AdminDao;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.qxgl.entity.QxRoles;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.qxgl.entity.QxUserDatauth;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.qxgl.service.BackendDepService;
import com.lingnet.qxgl.service.BackendRoleService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 出国service实现类
 */
@Service("practiceService")
public class PracticeServiceImpl extends BaseServiceImpl<BasicInformation, String> implements PracticeService{
	
	@Resource(name="personnelDao")
	private PersonnelDao personnelDao;
	
	@Resource(name="ptJobDao")
	private PtJobDao ptJobDao;
	
	@Resource(name="changeDao")
	private ChangeDao changeDao;
	
	@Resource(name = "adminDao")
    private AdminDao adminDao;
	
	@Resource(name="careerDao")
	private CareerDao careerDao;
	
	@Resource(name = "adminService")
    private AdminService adminService;
	
	@Resource(name = "backendRoleService")
    private BackendRoleService backendRoleService;
	
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdate(String formdata) throws Exception {
    	BasicInformation information=JsonUtil.toObject(formdata, BasicInformation.class);
    	if (information != null) {
    		String id = information.getId();
    		if (StringUtils.isBlank(id)){
    			information.setIsDelete(0);
    			BasicInformation empinfo=this.get(Restrictions.eq("practiceNum", information.getPracticeNum()));
    			if(empinfo!=null){
    				throw new Exception("实习生编号已经存在，保存失败！"); 
    			}
    			
    			information.setEmpType(3);//实习学生
    			information.setIsPractice(1);//实习标志位
            	information.setOnJob(0);//实习期
    			information.setCheckUnitId(information.getDepartId());
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
                QxUsers usenfo=adminService.getUserByName(information.getPracticeNum());
                if(usenfo==null||usenfo.getId()==null){
                    QxUser  userinfo=new QxUser();
                    userinfo.setUsername(information.getPracticeNum());
                    userinfo.setName(information.getName());
                    userinfo.setPassword("123456");
                    userinfo.setCid(information.getFilmId());
                    userinfo.setDepId(information.getDepartId());
                    userinfo.setIsDelete(0);
                    HashSet<QxRoles> rolelist =new HashSet<QxRoles>(); 
                    //超级管理员
                    QxRoles role = backendRoleService.get(QxRoles.class, Restrictions.eq("id","402881955c62c8f6015c62dc874d0006"));
                    rolelist.add(role);
                    adminService.saveuser(userinfo, rolelist);
                    
                    String userId=this.get(QxUsers.class, Restrictions.eq("username",information.getPracticeNum())).getId();
                    
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
            		ptJob.setIsHostPost(1);//主要职位
            		ptJob.setDepId(information.getDepartId());//部门ID
            		ptJob.setFirmId(information.getFilmId());//单位ID
            		ptJob.setStandardPost(information.getPost());
            		ptJob.setSpePost(information.getSpecificPost());
            		ptJob.setBeginTime(information.getBeginTime());
            		String jobid=ptJobDao.save(ptJob);//保存
            		if (StringUtils.isBlank(jobid))throw new Exception("信息导入兼职信息时发生异常！");
            		//人员异动
            		Change change=new Change();
            		change.setPersonId(information.getId());
            		change.setJobNumber(information.getJobNumber());
            		change.setChangedate(information.getBeginTime());
            		change.setDepbeginDate(information.getBeginTime());
            		change.setPostbeginDate(information.getBeginTime());
            		change.setChangeType(15);//新增
            		
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
            		career.setCompany(information.getFilmName());
            		career.setDepartment(information.getDepartName());
            		career.setPost(information.getSpecificPost());
            		if(information.getJobLevel()!=null&&!"".equals(information.getJobLevel())){
            			career.setJobLevel(Integer.valueOf(information.getJobLevel()));
            		}
            		career.setBegindate(information.getBeginTime());
            		career.setRemark("实习生");
            		String careerid=careerDao.save(career);
            		String changeid=changeDao.save(change);//保存
            		if (StringUtils.isBlank(careerid))throw new Exception("信息导入职位表时发生异常！");
            		if (StringUtils.isBlank(changeid))throw new Exception("信息导入异动表时发生异常！");
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
	
}
