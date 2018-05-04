package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 每月保险缴费核对总表
 * XcPaymentCheckMonth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_PAYMENT_CHECK_MONTH")
public class PaymentCheckMonth extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 8963857014420351832L;
    private String companyId;// 公司ID
    private String insuranceId;// 福利项目ID
    private Integer effectiveYear;// 计算年
    private Integer effectiveMonth;// 计算月
    private String docLocation;// 单据位置
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public PaymentCheckMonth() {
    }

    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "INSURANCE_ID", length = 32)
    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
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

    @Column(name = "DOC_LOCATION", length = 2000)
    public String getDocLocation() {
        return docLocation;
    }

    public void setDocLocation(String docLocation) {
        this.docLocation = docLocation;
    }

}