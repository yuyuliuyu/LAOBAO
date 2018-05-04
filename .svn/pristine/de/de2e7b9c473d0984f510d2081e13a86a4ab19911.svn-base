package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 工资分配核对配置表
 * XcCheckMonthSalary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_CHECK_MONTH_SALARY")
public class CheckMonthSalary extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -7079277799838571097L;
    private String checkId;// 数据表ID
    private String staffId;// 员工ID
    private String ibfId;// 薪资项目ID
    private String note;// 配置说明
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public CheckMonthSalary() {
    }

    @Column(name = "CHECK_ID", length = 32)
    public String getCheckId() {
        return this.checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return this.staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "IBF_ID", length = 32)
    public String getIbfId() {
        return this.ibfId;
    }

    public void setIbfId(String ibfId) {
        this.ibfId = ibfId;
    }

    @Lob
    @Column(name = "NOTE")
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