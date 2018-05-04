package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 年龄倾斜系数表
 * JcSalaryAgeQx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JC_SALARY_AGE_QX")
public class SalaryAgeQx extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 8100721030365811331L;
    private String companyId;// 单位ID
    private Integer sexMan;// 职工年龄（男）
    private Integer sexWoman;// 职工年龄（女）
    private String zjxs;// 年龄倾斜系数
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryAgeQx() {
    }

    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "SEX_MAN", precision = 3, scale = 0)
    public Integer getSexMan() {
        return this.sexMan;
    }

    public void setSexMan(Integer sexMan) {
        this.sexMan = sexMan;
    }

    @Column(name = "SEX_WOMAN", precision = 3, scale = 0)
    public Integer getSexWoman() {
        return this.sexWoman;
    }

    public void setSexWoman(Integer sexWoman) {
        this.sexWoman = sexWoman;
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