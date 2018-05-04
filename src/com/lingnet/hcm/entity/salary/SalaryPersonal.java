package com.lingnet.hcm.entity.salary;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * 个人薪资组
 * XcSalaryPersonal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_PERSONAL")
public class SalaryPersonal extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -4042450748174685714L;
    private String salaryGroupId;// 薪资组
    private Date startDate;// 开始日期
    private String salaryRecordId;// 员工ID
    private String salaryComId;// 发薪单位
    private Integer isSalary;// 是否发薪 0:不发薪 1：发薪
    private String costComId;// 成本单位
    private String costDeptId;// 成本部门
    private String costScale;// 成本比例
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private Integer isOff;// 是否注销 0:不注销 1：注销
    private Date offDate;// 注销日期
    private Integer isPei;// 是否员工薪资组配置 0:不是1：是
    private String depId;// 部门ID
    private String recordMainId;// 档案表ID

    // Constructors

    /** default constructor */
    public SalaryPersonal() {
    }

    @Column(name = "SALARY_GROUP_ID", length = 32)
    public String getSalaryGroupId() {
        return this.salaryGroupId;
    }

    public void setSalaryGroupId(String salaryGroupId) {
        this.salaryGroupId = salaryGroupId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", length = 7)
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "SALARY_RECORD_ID", length = 32)
    public String getSalaryRecordId() {
        return this.salaryRecordId;
    }

    public void setSalaryRecordId(String salaryRecordId) {
        this.salaryRecordId = salaryRecordId;
    }

    @Column(name = "SALARY_COM_ID", length = 32)
    public String getSalaryComId() {
        return this.salaryComId;
    }

    public void setSalaryComId(String salaryComId) {
        this.salaryComId = salaryComId;
    }

    @Column(name = "IS_SALARY", precision = 1, scale = 0)
    public Integer getIsSalary() {
        return this.isSalary;
    }

    public void setIsSalary(Integer isSalary) {
        this.isSalary = isSalary;
    }

    @Column(name = "COST_COM_ID", length = 32)
    public String getCostComId() {
        return this.costComId;
    }

    public void setCostComId(String costComId) {
        this.costComId = costComId;
    }

    @Column(name = "COST_DEPT_ID", length = 32)
    public String getCostDeptId() {
        return this.costDeptId;
    }

    public void setCostDeptId(String costDeptId) {
        this.costDeptId = costDeptId;
    }

    @Column(name = "COST_SCALE", length = 8)
    public String getCostScale() {
        return this.costScale;
    }

    public void setCostScale(String costScale) {
        this.costScale = costScale;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "IS_OFF", precision = 1, scale = 0)
    public Integer getIsOff() {
        return isOff;
    }

    public void setIsOff(Integer isOff) {
        this.isOff = isOff;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "OFF_DATE", length = 7)
    public Date getOffDate() {
        return offDate;
    }

    public void setOffDate(Date offDate) {
        this.offDate = offDate;
    }

    @Column(name = "IS_PEI", precision = 1, scale = 0)
    public Integer getIsPei() {
        return isPei;
    }

    public void setIsPei(Integer isPei) {
        this.isPei = isPei;
    }

    @Column(name = "DEP_ID", length = 32)
    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Column(name = "RECORD_MAIN_ID", length = 32)
    public String getRecordMainId() {
        return recordMainId;
    }

    public void setRecordMainId(String recordMainId) {
        this.recordMainId = recordMainId;
    }

}