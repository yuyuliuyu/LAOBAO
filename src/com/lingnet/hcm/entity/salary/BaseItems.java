package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 缴费基数项目信息表
 * XcBaseItems entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_BASE_ITEMS")
public class BaseItems extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 8485725872962047030L;
    private String basePaymentId;// 缴费基数过程表缴费中间表ID
    private String ibfId;// 福利项目ID
    private String area;// 缴费地域
    private String baseCompany;// 公司缴纳基数
    private String basePersonal;// 员工缴纳基数
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public BaseItems() {
    }

    @Column(name = "BASE_PAYMENT_ID", length = 32)
    public String getBasePaymentId() {
        return this.basePaymentId;
    }

    public void setBasePaymentId(String basePaymentId) {
        this.basePaymentId = basePaymentId;
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

}