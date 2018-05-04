package com.lingnet.hcm.action.personnel;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.entity.contract.EmployAgreement;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.Change;
import com.lingnet.hcm.entity.person.PtJob;
import com.lingnet.hcm.service.personnel.ChangeService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.hcm.service.personnel.PtJobService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.util.JsonUtil;

/**
 * 员工公司内部流动，以及上岗，待岗长病假，内退等岗位操作action
 * @ClassName: BranchAction 
 * @Description: TODO 
 * @author lsp
 * @date 2016-8-3 下午1:50:42 
 *
 */
@Controller
public class ChangeAction extends BaseAction{
    
	private static final long serialVersionUID = 5453221435881869026L;

	@Resource(name="personnelService")
    private PersonnelService personnelService;
	
	@Resource(name="ptJobService")
    private PtJobService ptJobService;
	
	@Resource(name="changeService")
    private ChangeService changeService;
	
    private BasicInformation information;
    private Branch branch;
    private Change change;
    
    private String formdata;//添加数据的JSON串
    private String flag;//标志位
    private String departId;//部门ID
    private String departName;//部门
    private String filmId;//单位ID
    private String filmName;
    private String jobNumber;//
    private EmployAgreement employagre;//协议实体类
    private String username;
    private String personId;
    private String depshowId;//只做展示用
    private String isEnd;

    //人员异动list页面
    public String list(){
        return "list";
    }
    //内部调动页面
    public String add(){
		information=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
		depshowId=information.getDepartId();
        return "add";
    }
    /**
     * 列获取数据
     */
    public String getListData() {
        return ajax(Status.success, JsonUtil.Encode(changeService.getListData(pager,searchData)));
    }
    
    //内部调动保存方法
    public String saveOrUpdate() {
    	try {
    		if(formdata==null||formdata==""){
                return ajax(Status.success,"数据错误请联系管理员!");
            }else{
            	return ajax(Status.success, changeService.saveOrUpdate(formdata));
            } 
            
        } catch (Exception e) {
            return ajax(Status.success, e.getMessage());
        }
    }

    //离岗页面
    public String ligang(){
    	information=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "ligang";
    }
    //离岗信息保存
    public String saveOrUpdateLigang(){
    	try {
    		if(formdata==null||formdata==""){
                return ajax(Status.success,"数据错误请联系管理员!");
            }else{
            	return ajax(Status.success, changeService.saveOrUpdateLigang(formdata,username,isEnd));
            } 
            
        } catch (Exception e) {
            return ajax(Status.success, e.getMessage());
        }
    }
    
    //上岗页面
    public String onpost(){
    	information=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "onpost";
    }
    //上岗保存方法
    public String saveOrUpdateOnpost(){
    	
    	try {
    		if(formdata==null||formdata==""){
                return ajax(Status.success,"数据错误请联系管理员!");
            }else{
            	return ajax(Status.success, changeService.saveOrUpdateOnpost(formdata));
            } 
            
        } catch (Exception e) {
            return ajax(Status.success, e.getMessage());
        }
    }
    //根据公司ID查询公司名字
    public String findFilmName(){
    	branch=this.getBeanById(Branch.class, filmId);
    	if(branch!=null){
    		filmName=branch.getFzx();
    	}
    	return ajax(Status.success, filmName);
    }
    //验证节点是不是部门
    public String findDep(){
    	String msg="sucsess";
    	QxDepartment depart=this.getBeanById(QxDepartment.class, departId);
    	
    	if(depart==null||"".equals(depart)){
    		msg="error";
    	}
    	return ajax(Status.success, msg);
    }
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
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getDepartName() {
		return departName;
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

	public Change getChange() {
		return change;
	}

	public void setChange(Change change) {
		this.change = change;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public EmployAgreement getEmployagre() {
		return employagre;
	}
	public void setEmployagre(EmployAgreement employagre) {
		this.employagre = employagre;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getDepshowId() {
		return depshowId;
	}
	public void setDepshowId(String depshowId) {
		this.depshowId = depshowId;
	}
	public String getIsEnd() {
		return isEnd;
	}
	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}


  
}
