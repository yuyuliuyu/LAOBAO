package com.lingnet.hcm.entity.salary;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * 员工薪酬调整历史
 * XcSalaryHistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_HISTORY")
public class SalaryHistory extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -6049766481133319743L;
    private String staffId;// 员工ID
    private String itemsId;// 薪资项目ID
    private String adjustFront;// 调整前值
    private String adjustNext;// 调整后值
    private Date adjustDate;// 调整日期
    private String note;// 调整原因
    private String field1;
    private String field2;
    private String recordMainId;// 档案表ID

    // Constructors

    /** default constructor */
    public SalaryHistory() {
    }

    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "ITEMS_ID", length = 32)
    public String getItemsId() {
        return this.itemsId;
    }

    public void setItemsId(String itemsId) {
        this.itemsId = itemsId;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "ADJUST_DATE", length = 7)
    public Date getAdjustDate() {
        return this.adjustDate;
    }

    public void setAdjustDate(Date adjustDate) {
        this.adjustDate = adjustDate;
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

    @Column(name = "NOTE", length = 2000)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "RECORD_MAIN_ID", length = 32)
    public String getRecordMainId() {
        return recordMainId;
    }

    public void setRecordMainId(String recordMainId) {
        this.recordMainId = recordMainId;
    }

}