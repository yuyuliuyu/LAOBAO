package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 薪酬核算数据主表
 * XcAssignationStaff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_ASSIGNATION_STAFF")
public class AssignationStaff extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -6746310192703083298L;
    private String salaryAssignationId;// 薪酬分配过程ID
    private String companyId;// 公司ID
    private String companyName;// 公司
    private String deptId;// 部门ID
    private String deptName;// 部门
    private String staffId;// 职工ID
    private String jobNumber;// 职工号
    private String staffName;// 职工姓名
    private String gwId;// 岗位ID
    private String gwName;// 岗位名称
    private Integer isAllMonth;// 不足整月 0:否 1：是
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String bggwId;// 标准岗位ID
    private String bggwName;// 标准岗位名称
    private String xcgwId;// 薪酬岗位ID
    private String xcgwName;// 薪酬岗位名称
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public AssignationStaff() {
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

    @Column(name = "JOB_NUMBER", length = 32)
    public String getJobNumber() {
        return this.jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Column(name = "STAFF_NAME", length = 50)
    public String getStaffName() {
        return this.staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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

    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "COMPANY_NAME", length = 300)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "DEPT_ID", length = 32)
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Column(name = "DEPT_NAME", length = 300)
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Column(name = "GW_ID", length = 32)
    public String getGwId() {
        return gwId;
    }

    public void setGwId(String gwId) {
        this.gwId = gwId;
    }

    @Column(name = "GW_NAME", length = 50)
    public String getGwName() {
        return gwName;
    }

    public void setGwName(String gwName) {
        this.gwName = gwName;
    }

    @Column(name = "IS_ALL_MONTH", precision = 1, scale = 0)
    public Integer getIsAllMonth() {
        return isAllMonth;
    }

    public void setIsAllMonth(Integer isAllMonth) {
        this.isAllMonth = isAllMonth;
    }

    @Column(name = "BZGW_ID", length = 32)
    public String getBggwId() {
        return bggwId;
    }

    public void setBggwId(String bggwId) {
        this.bggwId = bggwId;
    }

    @Column(name = "BZGW_NAME", length = 50)
    public String getBggwName() {
        return bggwName;
    }

    public void setBggwName(String bggwName) {
        this.bggwName = bggwName;
    }

    @Column(name = "XCGW_ID", length = 32)
    public String getXcgwId() {
        return xcgwId;
    }

    public void setXcgwId(String xcgwId) {
        this.xcgwId = xcgwId;
    }

    @Column(name = "XCGW_NAME", length = 50)
    public String getXcgwName() {
        return xcgwName;
    }

    public void setXcgwName(String xcgwName) {
        this.xcgwName = xcgwName;
    }

}