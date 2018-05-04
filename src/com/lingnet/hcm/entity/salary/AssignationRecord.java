package com.lingnet.hcm.entity.salary;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * 分配过程审批记录
 * XcAssignationRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_ASSIGNATION_RECORD")
public class AssignationRecord extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -5517779133066075318L;
    private String salaryAssignationId;// 薪酬分配过程ID
    private Integer isSp;// 审批状态 0:未通过 1：通过
    private String assignationAccount;// 审批人
    private String note;// 审批意见
    private Date checkDate;// 审批时间
    private Integer isGiveup;// 作废标志 0:未作废 1：已作废
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public AssignationRecord() {
    }

    @Column(name = "SALARY_ASSIGNATION_ID", length = 32)
    public String getSalaryAssignationId() {
        return this.salaryAssignationId;
    }

    public void setSalaryAssignationId(String salaryAssignationId) {
        this.salaryAssignationId = salaryAssignationId;
    }

    @Column(name = "ASSIGNATION_ACCOUNT", length = 32)
    public String getAssignationAccount() {
        return this.assignationAccount;
    }

    public void setAssignationAccount(String assignationAccount) {
        this.assignationAccount = assignationAccount;
    }

    @Column(name = "NOTE", length = 2000)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    @Column(name = "IS_SP", precision = 1, scale = 0)
    public Integer getIsSp() {
        return isSp;
    }

    public void setIsSp(Integer isSp) {
        this.isSp = isSp;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHECK_DATE")
    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    @Column(name = "IS_GIVEUP", precision = 1, scale = 0)
    public Integer getIsGiveup() {
        return isGiveup;
    }

    public void setIsGiveup(Integer isGiveup) {
        this.isGiveup = isGiveup;
    }

}