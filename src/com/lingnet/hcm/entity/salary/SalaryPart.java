package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 工资条与薪资项目关联表
 * XcSalaryPart entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_PART")
public class SalaryPart extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -7494990156003419292L;
    private String groupWageAndTypeId;// 薪资组工资套ID
    private String name;// 工资条名称
    private String companyId;// 公司ID
    private String noSend;// 值为0时不发送工资条 0:否 1 是
    private Integer isDefault;// 默认 0:不默认1：默认公式
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String note;// 描述

    // Constructors

    /** default constructor */
    public SalaryPart() {
    }

    @Column(name = "GROUP_WAGE_AND_TYPE_ID")
    public String getGroupWageAndTypeId() {
        return groupWageAndTypeId;
    }

    public void setGroupWageAndTypeId(String groupWageAndTypeId) {
        this.groupWageAndTypeId = groupWageAndTypeId;
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

    @Column(name = "NO_SEND", length = 32)
    public String getNoSend() {
        return this.noSend;
    }

    public void setNoSend(String noSend) {
        this.noSend = noSend;
    }

    @Column(name = "IS_DEFAULT", precision = 22, scale = 0)
    public Integer getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
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