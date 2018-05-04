package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 月平均工资
 * XcMonthStaff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_MONTH_STAFF")
public class MonthStaff extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -3671519827412206426L;
    private String salaryMonthId;// 月平均工资ID
    private String staffId;// 员工ID
    private String averageSalary;// 平均工资
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;
    private String recordMainId;// 档案表ID

    // Constructors

    /** default constructor */
    public MonthStaff() {
    }

    @Column(name = "SALARY_MONTH_ID", length = 32)
    public String getSalaryMonthId() {
        return this.salaryMonthId;
    }

    public void setSalaryMonthId(String salaryMonthId) {
        this.salaryMonthId = salaryMonthId;
    }

    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return this.staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "AVERAGE_SALARY", length = 20)
    public String getAverageSalary() {
        return this.averageSalary;
    }

    public void setAverageSalary(String averageSalary) {
        this.averageSalary = averageSalary;
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