package com.lingnet.hcm.entity.salary;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * 薪酬期间中间表
 * Perioddata entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_PERIODDATA")
public class Perioddata extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -4114294839702254728L;
    private String periodId;// 薪酬期间ID
    private String name;// 名称
    private Date startDate;// 开始日期
    private Date endDate;// 结束日期
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public Perioddata() {
    }

    /** full constructor */
    public Perioddata(String periodId, String name,
            Date startDate, Date endDate) {
        this.periodId = periodId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Property accessors
    @Column(name = "PERIOD_ID", length = 32)
    public String getPeriodId() {
        return this.periodId;
    }

    public void setPeriodId(String periodId) {
        this.periodId = periodId;
    }

    @Column(name = "NAME", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", length = 7)
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE", length = 7)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "field1", length = 2000)
    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    @Column(name = "field2", length = 2000)
    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

}