package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * XcSalaryStandHistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_STAND_HISTORY")
public class SalaryStandHistory extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -5536016961408753043L;
    private String empChangeId;// 异动表ID
    private String staffId;// 职工ID
    private String ibfId;// 薪资项目ID
    private String itemsName;// 薪资项目名称
    private String adjustFront;// 调整前值
    private String adjustNext;// 调整后值
    private String field1;
    private String field2;
    private String recordMainId;// 档案表ID

    // Constructors

    /** default constructor */
    public SalaryStandHistory() {
    }

    @Column(name = "EMP_CHANGE_ID", length = 32)
    public String getEmpChangeId() {
        return this.empChangeId;
    }

    public void setEmpChangeId(String empChangeId) {
        this.empChangeId = empChangeId;
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
        return ibfId;
    }

    public void setIbfId(String ibfId) {
        this.ibfId = ibfId;
    }

    @Column(name = "ITEMS_NAME", length = 200)
    public String getItemsName() {
        return this.itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    @Column(name = "ADJUST_FRONT", length = 50)
    public String getAdjustFront() {
        return this.adjustFront;
    }

    public void setAdjustFront(String adjustFront) {
        this.adjustFront = adjustFront;
    }

    @Column(name = "ADJUST_NEXT", length = 50)
    public String getAdjustNext() {
        return this.adjustNext;
    }

    public void setAdjustNext(String adjustNext) {
        this.adjustNext = adjustNext;
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