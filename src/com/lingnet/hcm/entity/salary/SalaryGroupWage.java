package com.lingnet.hcm.entity.salary;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * 薪资组工资套
 * XcSalaryGroupWage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_GROUP_WAGE")
public class SalaryGroupWage extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -5438097012502172166L;
    private String salaryGroupId;// 引用的薪资组
    private Date effectDate;// 生效日期
    private String salaryWageId;// 引用的工资套
    private String name;// 工资套名称
    private String companyId;// 公司ID
    private String sign;// 工资套类型
    private Integer type;// 状态 0：无效 1 有效
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String note;// 描述

    // Constructors

    /** default constructor */
    public SalaryGroupWage() {
    }

    @Column(name = "SALARY_GROUP_ID", length = 32)
    public String getSalaryGroupId() {
        return this.salaryGroupId;
    }

    public void setSalaryGroupId(String salaryGroupId) {
        this.salaryGroupId = salaryGroupId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "EFFECT_DATE", length = 7)
    public Date getEffectDate() {
        return this.effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    @Column(name = "SALARY_WAGE_ID", length = 32)
    public String getSalaryWageId() {
        return this.salaryWageId;
    }

    public void setSalaryWageId(String salaryWageId) {
        this.salaryWageId = salaryWageId;
    }

    @Column(name = "NAME", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "SIGN", length = 32)
    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Column(name = "TYPE", precision = 1, scale = 0)
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "NOTE", length = 2000)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}