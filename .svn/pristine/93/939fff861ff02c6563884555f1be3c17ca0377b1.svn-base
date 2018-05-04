package com.lingnet.hcm.action.salary;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.basic.InsuranceBenefits;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.salary.SalaryOrgInsurance;
import com.lingnet.hcm.entity.salary.SalaryPerInsurance;
import com.lingnet.hcm.service.salary.SalaryOrgInsuranceService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.util.JsonUtil;

/**
 * 商业保险
 * @ClassName: SalaryOrgInsuranceAction 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月14日 下午4:34:56 
 *
 */
public class SalaryBusinessAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;

    private SalaryOrgInsurance salaryOrgInsurance;
    private SalaryPerInsurance salaryPerInsurance;
    private String tbdw;// 投保单位
    private String bxName;// 保险名称
    private String staffName;// 员工名称
    private String deptName;// 部门名称

    @Resource(name="salaryOrgInsuranceService")
    private SalaryOrgInsuranceService salaryOrgInsuranceService;

    /**
     * @Title: 列表展现页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String list() {
        return "list";
    }

    /**
     * @Title: 列表增加页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String add() {
        return "add";
    }

    /**
     * @Title: 列表编辑页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String edit() {
        return "add";
    }

    /**
     * @Title: 团体商业保险缴费展示页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String orgList() {
        return "orglist";
    }

    /**
     * @Title: 团体商业保险缴费增加页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String orgAdd() {
        return "orgadd";
    }

    /**
     * @Title: 团体商业保险缴费编辑页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String orgEdit() {
        salaryOrgInsurance = getBeanByCriteria(SalaryOrgInsurance.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
        if (salaryOrgInsurance != null) {
            Branch branch=  getBeanByCriteria(Branch.class, Restrictions.eq("id", salaryOrgInsurance.getInsuranceId()));
            if (branch != null){
                tbdw = branch.getFzx();
            }
            InsuranceBenefits benefits=  getBeanByCriteria(InsuranceBenefits.class, Restrictions.eq("id", salaryOrgInsurance.getIbfId()));
            if (benefits != null){
                bxName = benefits.getName();
            }
        }

        return "orgadd";
    }

    /**
     * @Title: 列表编辑页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String perList() {
        return "perlist";
    }

    /**
     * @Title: 个人商业保险缴费增加页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String perAdd() {
        return "peradd";
    }

    /**
     * @Title: 个人商业保险缴费编辑页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String perEdit() {
        salaryPerInsurance = getBeanByCriteria(SalaryPerInsurance.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
        if (salaryPerInsurance != null) {
            BasicInformation information = getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", salaryPerInsurance.getStaffId()));
            if (information != null){
                staffName = information.getName();
            }
            InsuranceBenefits benefits=  getBeanByCriteria(InsuranceBenefits.class, Restrictions.eq("id", salaryPerInsurance.getIbfId()));
            if (benefits != null){
                bxName = benefits.getName();
            }
            QxDepartment qxDepartment=  getBeanByCriteria(QxDepartment.class, Restrictions.eq("id", salaryPerInsurance.getDeptId()));
            if (qxDepartment != null){
                deptName = qxDepartment.getName();
            }
        }
        return "peradd";
    }

    /**
     * @Title: 人员选择展示页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月15日 V 1.0
     */
    public String person() {
        return "person";
    }

    /**
     * @Title: 团体商业保险缴费保存更新
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月13日 V 1.0
     */
    public String saveOrUpdate() {
        return ajax(Status.success, salaryOrgInsuranceService.saveOrUpdate(formdata));
    }

    /**
     * @Title: 团体商业保险缴费删除
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月24日 V 1.0
     */
    public String remove() {
        return ajax(Status.success, salaryOrgInsuranceService.remove(ids));
    }

    /**
     * @Title: 团体商业保险缴费列表数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月24日 V 1.0
     */
    public String getListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryOrgInsuranceService.getListData(searchData, pager)));
    }

    /**
     * @Title: 个人商业保险缴费保存更新
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月13日 V 1.0
     */
    public String saveOrUpdateToPer() {
        return ajax(Status.success, salaryOrgInsuranceService.saveOrUpdateToPer(formdata));
    }

    /**
     * @Title: 个人商业保险缴费删除
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月24日 V 1.0
     */
    public String removeToPer() {
        return ajax(Status.success, salaryOrgInsuranceService.removeToPer(ids));
    }

    /**
     * @Title: 个人商业保险缴费列表数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月24日 V 1.0
     */
    public String getPerListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryOrgInsuranceService.getPerListData(searchData, pager)));
    }

    /**
     * @Title: 获取所有的员工
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月10日 V 1.0
     */
    public String getAllPersonalListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryOrgInsuranceService.getAllPersonalListData(searchData, pager)));
    }

    public SalaryOrgInsurance getSalaryOrgInsurance() {
        return salaryOrgInsurance;
    }

    public void setSalaryOrgInsurance(SalaryOrgInsurance salaryOrgInsurance) {
        this.salaryOrgInsurance = salaryOrgInsurance;
    }

    public SalaryPerInsurance getSalaryPerInsurance() {
        return salaryPerInsurance;
    }

    public void setSalaryPerInsurance(SalaryPerInsurance salaryPerInsurance) {
        this.salaryPerInsurance = salaryPerInsurance;
    }

    public String getTbdw() {
        return tbdw;
    }

    public void setTbdw(String tbdw) {
        this.tbdw = tbdw;
    }

    public String getBxName() {
        return bxName;
    }

    public void setBxName(String bxName) {
        this.bxName = bxName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}
