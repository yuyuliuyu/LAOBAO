package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 二次分配总量主表
 * XcSalaryAgain entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_AGAIN")
public class SalaryAgain extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -6848089099062896539L;
    private String companyId;// 公司ID
    private String depId;// 部门ID
    private String fpDate;// 发薪期间
    private String rewardTotal;// 合计
    private Integer reportStatus;// 上报状态 0:未上报 1：已上报
    private Integer isSp;// 审批状态 0:未提交 1：已提交 2:未通过 3：通过
    private String note;// 审批意见
    private Integer fpqx;// 分配权限 1：公司 2：基层
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;
    private String salaryAssignationId;// 薪酬分配过程ID

    // Constructors

    /** default constructor */
    public SalaryAgain() {
    }

    @Column(name = "FP_DATE", length = 32)
    public String getFpDate() {
        return this.fpDate;
    }

    public void setFpDate(String fpDate) {
        this.fpDate = fpDate;
    }

    @Column(name = "REWARD_TOTAL", length = 20)
    public String getRewardTotal() {
        return this.rewardTotal;
    }

    public void setRewardTotal(String rewardTotal) {
        this.rewardTotal = rewardTotal;
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

    @Column(name = "DEP_ID", length = 32)
    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Column(name = "REPORT_STATUS", precision = 1, scale = 0)
    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    @Column(name = "IS_SP", precision = 1, scale = 0)
    public Integer getIsSp() {
        return isSp;
    }

    public void setIsSp(Integer isSp) {
        this.isSp = isSp;
    }

    @Column(name = "FPQX", precision = 1, scale = 0)
    public Integer getFpqx() {
        return fpqx;
    }

    public void setFpqx(Integer fpqx) {
        this.fpqx = fpqx;
    }

    @Column(name = "NOTE", length = 2000)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "SALARY_ASSIGNATION_ID", length = 32)
    public String getSalaryAssignationId() {
        return salaryAssignationId;
    }

    public void setSalaryAssignationId(String salaryAssignationId) {
        this.salaryAssignationId = salaryAssignationId;
    }

}