package com.lingnet.hcm.entity.salary;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * 企业年金年龄截止表
 * JcSalaryNjAge entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JC_SALARY_NJ_AGE")
public class SalaryNjAge extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -1081155396297937681L;
    private String companyId;// 单位ID
    private Date nljzrq;// 年龄截止日期
    private Integer bz;// 标志 1:年龄倾斜系数表 2：年龄系数表
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryNjAge() {
    }

    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "NLJZRQ", length = 7)
    public Date getNljzrq() {
        return this.nljzrq;
    }

    public void setNljzrq(Date nljzrq) {
        this.nljzrq = nljzrq;
    }

    @Column(name = "BZ", precision = 1, scale = 0)
    public Integer getBz() {
        return bz;
    }

    public void setBz(Integer bz) {
        this.bz = bz;
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