package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 工资条关联主表
 * XcSalaryWageMain entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_WAGE_MAIN")
public class SalaryWageMain extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 4496135879792014652L;
    private String salaryAssignationId;// 薪酬分配过程ID
    private String staffId;// 职工ID
    private String jobNumber;// 职工号
    private String staffName;// 职工姓名
    private String companyId;// 公司ID
    private String companyName;// 公司名称
    private String deptId;// 部门ID
    private String deptName;// 部门名称
    private Integer isRelease;// 发布状态 0:取消发布 1：已发布
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryWageMain() {
    }
    @Column(name = "SALARY_ASSIGNATION_ID", length = 32)
    public String getSalaryAssignationId() {
        return this.salaryAssignationId;
    }

    public void setSalaryAssignationId(String salaryAssignationId) {
        this.salaryAssignationId = salaryAssignationId;
    }

    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return this.staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "STAFF_NAME", length = 100)
    public String getStaffName() {
        return this.staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "COMPANY_NAME", length = 500)
    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "DEPT_ID", length = 32)
    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Column(name = "DEPT_NAME", length = 500)
    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Column(name = "IS_RELEASE", precision = 1, scale = 0)
    public Integer getIsRelease() {
        return this.isRelease;
    }

    public void setIsRelease(Integer isRelease) {
        this.isRelease = isRelease;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "FIELD1", length = 2000)
    public String getField1() {
        return this.field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    @Column(name = "FIELD2", length = 2000)
    public String getField2() {
        return this.field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    @Column(name = "JOB_NUMBER")
    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

}