package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 保险缴费过程表
 * XcSalaryPaymentProcess entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_PAYMENT_PROCESS")
public class SalaryPaymentProcess extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 7748636885013583041L;
    private String name;// 名称
    private String companyId;// 公司ID
    private Integer effectiveYear;// 计算年
    private Integer effectiveMonth;// 计算月
    private String note;// 备注
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryPaymentProcess() {
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

    @Column(name = "EFFECTIVE_YEAR", precision = 4, scale = 0)
    public Integer getEffectiveYear() {
        return this.effectiveYear;
    }

    public void setEffectiveYear(Integer effectiveYear) {
        this.effectiveYear = effectiveYear;
    }

    @Column(name = "EFFECTIVE_MONTH", precision = 2, scale = 0)
    public Integer getEffectiveMonth() {
        return this.effectiveMonth;
    }

    public void setEffectiveMonth(Integer effectiveMonth) {
        this.effectiveMonth = effectiveMonth;
    }

    @Column(name = "NOTE", length = 2000)
    public String getNote() {
        return this.note;
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

}