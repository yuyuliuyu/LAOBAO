package com.lingnet.hcm.entity.salary;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * 团体商业保险缴费
 * XcSalaryOrgInsurance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_ORG_INSURANCE")
public class SalaryOrgInsurance extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 4324379302629180219L;
    private String insuranceId;// 投保单位ID
    private String cbgs;// 承保公司
    private String ibfId;// 福利项目ID
    private String bf;// 保费
    private String bxfl;// 保险费率(%)
    private String syyg;// 适用员工
    private Date beginDate;// 投保开始时间
    private Date endDate;// 投保结束时间
    private String bxjems;// 保险金额（描述）
    private String note;// 备注
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryOrgInsurance() {
    }

    @Column(name = "INSURANCE_ID", length = 32)
    public String getInsuranceId() {
        return this.insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    @Column(name = "CBGS", length = 100)
    public String getCbgs() {
        return this.cbgs;
    }

    public void setCbgs(String cbgs) {
        this.cbgs = cbgs;
    }

    @Column(name = "IBF_ID", length = 32)
    public String getIbfId() {
        return this.ibfId;
    }

    public void setIbfId(String ibfId) {
        this.ibfId = ibfId;
    }

    @Column(name = "BF", length = 20)
    public String getBf() {
        return this.bf;
    }

    public void setBf(String bf) {
        this.bf = bf;
    }

    @Column(name = "BXFL", length = 20)
    public String getBxfl() {
        return this.bxfl;
    }

    public void setBxfl(String bxfl) {
        this.bxfl = bxfl;
    }

    @Column(name = "SYYG", length = 2000)
    public String getSyyg() {
        return this.syyg;
    }

    public void setSyyg(String syyg) {
        this.syyg = syyg;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BEGIN_DATE", length = 7)
    public Date getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE", length = 7)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "BXJEMS", length = 2000)
    public String getBxjems() {
        return this.bxjems;
    }

    public void setBxjems(String bxjems) {
        this.bxjems = bxjems;
    }

    @Column(name = "NOTE", length = 2000)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
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

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}