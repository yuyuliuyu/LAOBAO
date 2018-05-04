package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 年龄系数表
 * JcSalaryAgeXs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JC_SALARY_AGE_XS")
public class SalaryAgeXs extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -8688615297858266118L;
    private String companyId;// 单位ID
    private Integer zgnl;// 职工年龄
    private Integer zgnlMax;// 职工年龄高值
    private Integer zgnlMin;// 职工年龄低值
    private String zjxs;// 年龄倾斜系数
    private Integer sex;// 性别 0:男 1：女
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryAgeXs() {
    }

    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "ZGNL", precision = 3, scale = 0)
    public Integer getZgnl() {
        return this.zgnl;
    }

    public void setZgnl(Integer zgnl) {
        this.zgnl = zgnl;
    }

    @Column(name = "ZGNL_MAX", precision = 3, scale = 0)
    public Integer getZgnlMax() {
        return this.zgnlMax;
    }

    public void setZgnlMax(Integer zgnlMax) {
        this.zgnlMax = zgnlMax;
    }

    @Column(name = "ZGNL_MIN", precision = 3, scale = 0)
    public Integer getZgnlMin() {
        return this.zgnlMin;
    }

    public void setZgnlMin(Integer zgnlMin) {
        this.zgnlMin = zgnlMin;
    }

    @Column(name = "ZJXS", length = 20)
    public String getZjxs() {
        return this.zjxs;
    }

    public void setZjxs(String zjxs) {
        this.zjxs = zjxs;
    }

    @Column(name = "SEX", precision = 1, scale = 0)
    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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