package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 薪资组薪资项目薪酬值表
 * XcSalaryValue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_VALUE")
public class SalaryValue extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -7744096992236185037L;
    private String staffId;// 员工ID
    private String salaryGroupId;// 薪资组ID
    private String salaryItemsId;// 薪资项目ID
    private String staticValue;// 值
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;
    private String recordMainId;// 档案表ID

    // Constructors

    /** default constructor */
    public SalaryValue() {
    }

    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "SALARY_GROUP_ID", length = 32)
    public String getSalaryGroupId() {
        return this.salaryGroupId;
    }

    public void setSalaryGroupId(String salaryGroupId) {
        this.salaryGroupId = salaryGroupId;
    }

    @Column(name = "SALARY_ITEMS_ID", length = 32)
    public String getSalaryItemsId() {
        return this.salaryItemsId;
    }

    public void setSalaryItemsId(String salaryItemsId) {
        this.salaryItemsId = salaryItemsId;
    }

    @Column(name = "STATIC_VALUE", length = 50)
    public String getStaticValue() {
        return this.staticValue;
    }

    public void setStaticValue(String staticValue) {
        this.staticValue = staticValue;
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

    @Column(name = "RECORD_MAIN_ID", length = 32)
    public String getRecordMainId() {
        return recordMainId;
    }

    public void setRecordMainId(String recordMainId) {
        this.recordMainId = recordMainId;
    }

}