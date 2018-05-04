package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 每月保险缴费核对数据表
 * XcCheckMonthInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_CHECK_MONTH_INFO")
public class CheckMonthInfo extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 5985421567574423148L;
    private String paymentCheckMonthId;// 每月保险缴费核对总表ID
    private String staffId;// 员工ID
    private String grbh;// 个人编号
    private String idCard;// 身份证
    private String name;// 姓名
    private Integer jfYear;// 缴费年
    private Integer jfMonth;// 缴费月
    private Integer jfType;// 缴费类型
    private Integer payBackYear;// 补缴年
    private Integer payBackMonth;// 补缴月
    private String jfjs;// 个人缴费基数
    private String paymentCompany;// 公司缴纳
    private String paymentPersonal;// 员工缴纳
    private String company;// 单位名称
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public CheckMonthInfo() {
    }

    @Column(name = "PAYMENT_CHECK_MONTH_ID", length = 32)
    public String getPaymentCheckMonthId() {
        return this.paymentCheckMonthId;
    }

    public void setPaymentCheckMonthId(String paymentCheckMonthId) {
        this.paymentCheckMonthId = paymentCheckMonthId;
    }

    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "GRBH", length = 30)
    public String getGrbh() {
        return this.grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh;
    }

    @Column(name = "ID_CARD", length = 18)
    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Column(name = "NAME", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "JF_YEAR", precision = 4, scale = 0)
    public Integer getJfYear() {
        return this.jfYear;
    }

    public void setJfYear(Integer jfYear) {
        this.jfYear = jfYear;
    }

    @Column(name = "JF_MONTH", precision = 2, scale = 0)
    public Integer getJfMonth() {
        return this.jfMonth;
    }

    public void setJfMonth(Integer jfMonth) {
        this.jfMonth = jfMonth;
    }

    @Column(name = "JF_TYPE", precision = 1, scale = 0)
    public Integer getJfType() {
        return this.jfType;
    }

    public void setJfType(Integer jfType) {
        this.jfType = jfType;
    }

    @Column(name = "PAY_BACK_YEAR", precision = 4, scale = 0)
    public Integer getPayBackYear() {
        return this.payBackYear;
    }

    public void setPayBackYear(Integer payBackYear) {
        this.payBackYear = payBackYear;
    }

    @Column(name = "PAY_BACK_MONTH", precision = 2, scale = 0)
    public Integer getPayBackMonth() {
        return this.payBackMonth;
    }

    public void setPayBackMonth(Integer payBackMonth) {
        this.payBackMonth = payBackMonth;
    }

    @Column(name = "JFJS", length = 20)
    public String getJfjs() {
        return this.jfjs;
    }

    public void setJfjs(String jfjs) {
        this.jfjs = jfjs;
    }

    @Column(name = "PAYMENT_COMPANY", length = 20)
    public String getPaymentCompany() {
        return this.paymentCompany;
    }

    public void setPaymentCompany(String paymentCompany) {
        this.paymentCompany = paymentCompany;
    }

    @Column(name = "PAYMENT_PERSONAL", length = 20)
    public String getPaymentPersonal() {
        return this.paymentPersonal;
    }

    public void setPaymentPersonal(String paymentPersonal) {
        this.paymentPersonal = paymentPersonal;
    }

    @Column(name = "COMPANY", length = 300)
    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
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