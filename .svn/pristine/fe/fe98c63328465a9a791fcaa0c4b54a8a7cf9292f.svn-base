package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 参保人员缴费表
 * XcPaymentInsurance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_PAYMENT_INSURANCE")
public class PaymentInsurance extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -4457827073109401223L;
    private String salaryPaymentProcessId;// 缴费中间表ID
    private String ibfId;// 福利项目ID
    private String area;// 缴费地域
    private String jfCompany;// 缴费单位
    private String jfAccount;// 缴费账号
    private String baseCompany;// 公司缴纳基数
    private String basePersonal;// 员工缴纳基数
    private String paymentCompany;// 公司缴纳
    private String paymentPersonal;// 员工缴纳
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public PaymentInsurance() {
    }

    @Column(name = "SALARY_PAYMENT_PROCESS_ID", length = 32)
    public String getSalaryPaymentProcessId() {
        return this.salaryPaymentProcessId;
    }

    public void setSalaryPaymentProcessId(String salaryPaymentProcessId) {
        this.salaryPaymentProcessId = salaryPaymentProcessId;
    }

    @Column(name = "IBF_ID", length = 32)
    public String getIbfId() {
        return this.ibfId;
    }

    public void setIbfId(String ibfId) {
        this.ibfId = ibfId;
    }

    @Column(name = "AREA", length = 200)
    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Column(name = "JF_COMPANY", length = 200)
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

    @Column(name = "BASE_COMPANY", length = 20)
    public String getBaseCompany() {
        return this.baseCompany;
    }

    public void setBaseCompany(String baseCompany) {
        this.baseCompany = baseCompany;
    }

    @Column(name = "BASE_PERSONAL", length = 20)
    public String getBasePersonal() {
        return this.basePersonal;
    }

    public void setBasePersonal(String basePersonal) {
        this.basePersonal = basePersonal;
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