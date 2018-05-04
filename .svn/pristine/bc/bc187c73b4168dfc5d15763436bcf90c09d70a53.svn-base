package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 保险缴费过程福利项目中间表
 * XcPaymentProcessFuli entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_PAYMENT_PROCESS_FULI")
public class PaymentProcessFuli extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -398402309845682987L;
    private String fuliId;// 福利项目ID
    private String salaryPaymentProcessId;// 缴费过程表ID
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public PaymentProcessFuli() {
    }

    @Column(name = "FULI_ID", length = 32)
    public String getFuliId() {
        return this.fuliId;
    }

    public void setFuliId(String fuliId) {
        this.fuliId = fuliId;
    }

    @Column(name = "SALARY_PAYMENT_PROCESS_ID", length = 32)
    public String getSalaryPaymentProcessId() {
        return this.salaryPaymentProcessId;
    }

    public void setSalaryPaymentProcessId(String salaryPaymentProcessId) {
        this.salaryPaymentProcessId = salaryPaymentProcessId;
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