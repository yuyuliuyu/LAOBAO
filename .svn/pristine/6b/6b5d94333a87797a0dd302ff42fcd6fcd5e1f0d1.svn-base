package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 工资单可查看月份表
 * XcSalaryPayroll entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_PAYROLL")
public class SalaryPayroll extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 7582509532869322373L;
    private String userName;// 员工账号
    private Integer watchType;// 可查看类型 1：连续薪酬期间个数2：可查看年份 3：可查看哪几个薪酬期间
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String typeValue;// 类型值
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryPayroll() {
    }

    @Column(name = "USER_NAME", length = 255)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "WATCH_TYPE", precision = 1, scale = 0)
    public Integer getWatchType() {
        return this.watchType;
    }

    public void setWatchType(Integer watchType) {
        this.watchType = watchType;
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

    @Column(name = "TYPE_VALUE", length = 2000)
    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

}