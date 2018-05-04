package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 保险缴费过程表缴费中间表
 * XcProcessPayment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_PROCESS_PAYMENT")
public class ProcessPayment extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -8785471486599660748L;
    private String salaryPaymentProcessId;// 缴费过程表ID
    private String staffId;// 员工ID
    private String pjgz;// 平均工资
    private Integer jfYear;// 缴费年
    private Integer jfMonth;// 缴费月
    private Integer isDaijiao;// 是否代缴 0:不代缴 1：代缴
    private Integer payBackYear;// 补缴年
    private Integer payBackMonth;// 补缴月
    private Integer isPayBack;// 是否补缴 0:不是补缴1：补缴
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public ProcessPayment() {
    }

    @Column(name = "SALARY_PAYMENT_PROCESS_ID", length = 32)
    public String getSalaryPaymentProcessId() {
        return this.salaryPaymentProcessId;
    }

    public void setSalaryPaymentProcessId(String salaryPaymentProcessId) {
        this.salaryPaymentProcessId = salaryPaymentProcessId;
    }

    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return this.staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "PJGZ", length = 50)
    public String getPjgz() {
        return this.pjgz;
    }

    public void setPjgz(String pjgz) {
        this.pjgz = pjgz;
    }

    @Column(name = "JF_YEAR", precision = 4, scale = 0)
    public Integer getJfYear() {
        return jfYear;
    }

    public void setJfYear(Integer jfYear) {
        this.jfYear = jfYear;
    }

    @Column(name = "JF_MONTH", precision = 2, scale = 0)
    public Integer getJfMonth() {
        return jfMonth;
    }

    public void setJfMonth(Integer jfMonth) {
        this.jfMonth = jfMonth;
    }

    @Column(name = "IS_DAIJIAO", precision = 1, scale = 0)
    public Integer getIsDaijiao() {
        return this.isDaijiao;
    }

    public void setIsDaijiao(Integer isDaijiao) {
        this.isDaijiao = isDaijiao;
    }

    @Column(name = "PAY_BACK_YEAR", precision = 4, scale = 0)
    public Integer getPayBackYear() {
        return payBackYear;
    }

    public void setPayBackYear(Integer payBackYear) {
        this.payBackYear = payBackYear;
    }

    @Column(name = "PAY_BACK_MONTH", precision = 4, scale = 0)
    public Integer getPayBackMonth() {
        return payBackMonth;
    }

    public void setPayBackMonth(Integer payBackMonth) {
        this.payBackMonth = payBackMonth;
    }

    @Column(name = "IS_PAY_BACK", precision = 1, scale = 0)
    public Integer getIsPayBack() {
        return isPayBack;
    }

    public void setIsPayBack(Integer isPayBack) {
        this.isPayBack = isPayBack;
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