package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 企业缴费信息汇总
 * JcSalaryNjTotal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JC_SALARY_NJ_TOTAL")
public class SalaryNjTotal extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -6331847064976784055L;
    private String companyId;// 单位ID
    private String companyName;// 单位名称
    private String qyjfbl;// 企业缴费比例
    private Integer year;// 缴费年
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryNjTotal() {
    }

    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "COMPANY_NAME", length = 300)
    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "QYJFBL", length = 20)
    public String getQyjfbl() {
        return this.qyjfbl;
    }

    public void setQyjfbl(String qyjfbl) {
        this.qyjfbl = qyjfbl;
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

    @Column(name = "YEAR", precision = 4, scale = 0)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}