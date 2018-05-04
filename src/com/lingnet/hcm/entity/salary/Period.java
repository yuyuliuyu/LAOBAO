package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 薪酬期间主表
 * XcPeriod entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_PERIOD")
public class Period extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 925647246949628382L;
    private String depId;// 部门ID
    private Integer year;// 年度
    private Integer payPerid;// 发薪频率 0：周 1：双周 2：半月 3：月
    private Integer dayHour; // 标准日工作小时
    private Float day; // 标准计薪天数
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String note;// 备注
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public Period() {
    }

    /** full constructor */
    public Period(String depId, Integer year, Integer payPerid,
            Integer dayHour, Integer isDelete, String note) {
        this.depId = depId;
        this.year = year;
        this.payPerid = payPerid;
        this.dayHour = dayHour;
        this.isDelete = isDelete;
        this.note = note;
    }

    // Property accessors
    @Column(name = "DEP_ID", length = 32)
    public String getDepId() {
        return this.depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Column(name = "YEAR", precision = 22, scale = 0)
    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Column(name = "PAY_PERID", precision = 1, scale = 0)
    public Integer getPayPerid() {
        return this.payPerid;
    }

    public void setPayPerid(Integer payPerid) {
        this.payPerid = payPerid;
    }

    @Column(name = "DAY_HOUR", precision = 2, scale = 0)
    public Integer getDayHour() {
        return this.dayHour;
    }

    public void setDayHour(Integer dayHour) {
        this.dayHour = dayHour;
    }

    @Column(name = "DAY", precision = 4, scale = 2)
    public Float getDay() {
        return day;
    }

    public void setDay(Float day) {
        this.day = day;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "NOTE", length = 2000)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "FIELD1", length = 2000)
    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    @Column(name = "FIELD2", length = 2000)
    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

}