package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 企业年金缴费分配方案
 * JcSalaryNjProgram entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JC_SALARY_NJ_PROGRAM")
public class SalaryNjProgram extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -3900016737136778033L;
    private String companyId;// 单位ID
    private String deptLevel;// 职务类别ID
    private String zjjfbl;// 职级缴费比例
    private String zjxs;// 职级系数
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryNjProgram() {
    }

    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "DEPT_LEVEL", length = 32)
    public String getDeptLevel() {
        return this.deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    @Column(name = "ZJJFBL", length = 20)
    public String getZjjfbl() {
        return this.zjjfbl;
    }

    public void setZjjfbl(String zjjfbl) {
        this.zjjfbl = zjjfbl;
    }

    @Column(name = "ZJXS", length = 20)
    public String getZjxs() {
        return this.zjxs;
    }

    public void setZjxs(String zjxs) {
        this.zjxs = zjxs;
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