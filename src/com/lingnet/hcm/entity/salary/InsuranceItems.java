package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 参保人员项目信息表
 * XcInsuranceItems entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_INSURANCE_ITEMS")
public class InsuranceItems extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -9031012863567741426L;
    private String ibfId;// 福利项目ID
    private String insuranceMiddleId;// 参保人员与福利项目中间表ID
    private String area;// 缴费地域
    private String jfCompany;// 缴费单位
    private String jfAccount;// 缴费账号
    private String baseCompany;// 公司缴纳基数
    private String basePersonal;// 员工缴纳基数
    private Integer year;// 开始缴费年
    private Integer month;// 开始缴费月
    private String note;// 起缴原因
    private Integer endYear;// 结束缴费年
    private Integer endMonth;// 结束缴费月
    private Integer isEnd;// 是否停缴 0:未停缴 1：停缴
    private String endNote;// 停缴原因 
    private Integer effectiveYear;// 生效年
    private Integer effectiveMonth;// 生效月
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String paymentCompany;// 公司缴纳
    private String paymentPersonal;// 员工缴纳
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public InsuranceItems() {
    }

    @Column(name = "INSURANCE_MIDDLE_ID", length = 32)
    public String getInsuranceMiddleId() {
        return insuranceMiddleId;
    }

    public void setInsuranceMiddleId(String insuranceMiddleId) {
        this.insuranceMiddleId = insuranceMiddleId;
    }

    @Column(name = "IBF_ID", length = 32)
    public String getIbfId() {
        return this.ibfId;
    }

    public void setIbfId(String ibfId) {
        this.ibfId = ibfId;
    }

    @Column(name = "AREA", length = 32)
    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Column(name = "JF_COMPANY", length = 32)
    public String getJfCompany() {
        return this.jfCompany;
    }

    public void setJfCompany(String jfCompany) {
        this.jfCompany = jfCompany;
    }

    @Column(name = "JF_ACCOUNT", length = 40)
    public String getJfAccount() {
        return this.jfAccount;
    }

    public void setJfAccount(String jfAccount) {
        this.jfAccount = jfAccount;
    }

    @Column(name = "BASE_COMPANY")
    public String getBaseCompany() {
        return this.baseCompany;
    }

    public void setBaseCompany(String baseCompany) {
        this.baseCompany = baseCompany;
    }

    @Column(name = "BASE_PERSONAL")
    public String getBasePersonal() {
        return this.basePersonal;
    }

    public void setBasePersonal(String basePersonal) {
        this.basePersonal = basePersonal;
    }

    @Column(name = "YEAR", precision = 4, scale = 0)
    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Column(name = "MONTH", precision = 2, scale = 0)
    public Integer getMonth() {
        return this.month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Column(name = "NOTE", length = 2000)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "END_YEAR", precision = 4, scale = 0)
    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    @Column(name = "END_MONTH", precision = 4, scale = 0)
    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }

    @Column(name = "END_NOTE", length = 2000)
    public String getEndNote() {
        return endNote;
    }

    public void setEndNote(String endNote) {
        this.endNote = endNote;
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

    @Column(name = "IS_END", precision = 1, scale = 0)
    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    @Column(name = "EFFECTIVE_YEAR", precision = 4, scale = 0)
    public Integer getEffectiveYear() {
        return effectiveYear;
    }

    public void setEffectiveYear(Integer effectiveYear) {
        this.effectiveYear = effectiveYear;
    }

    @Column(name = "EFFECTIVE_MONTH", precision = 2, scale = 0)
    public Integer getEffectiveMonth() {
        return effectiveMonth;
    }

    public void setEffectiveMonth(Integer effectiveMonth) {
        this.effectiveMonth = effectiveMonth;
    }

    @Column(name = "PAYMENT_COMPANY", length = 20)
    public String getPaymentCompany() {
        return paymentCompany;
    }

    public void setPaymentCompany(String paymentCompany) {
        this.paymentCompany = paymentCompany;
    }

    @Column(name = "PAYMENT_PERSONAL", length = 20)
    public String getPaymentPersonal() {
        return paymentPersonal;
    }

    public void setPaymentPersonal(String paymentPersonal) {
        this.paymentPersonal = paymentPersonal;
    }
}