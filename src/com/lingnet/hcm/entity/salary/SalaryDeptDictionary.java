package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 薪酬岗位字典
 * XcSalaryDeptDictionary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_DEPT_DICTIONARY")
public class SalaryDeptDictionary extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String type;// 类别
    private String name;// 岗位名称
    private String gjdx;// 工资档序
    private String zjlb;// 职级类别
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String note;// 备注
    private String zwbm;// 职位编码
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryDeptDictionary() {
    }

    @Column(name = "TYPE", length = 32)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "NAME", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "GJDX", length = 20)
    public String getGjdx() {
        return this.gjdx;
    }

    public void setGjdx(String gjdx) {
        this.gjdx = gjdx;
    }

    @Column(name = "ZJLB", length = 32)
    public String getZjlb() {
        return this.zjlb;
    }

    public void setZjlb(String zjlb) {
        this.zjlb = zjlb;
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

    @Column(name = "NOTE", length = 2000)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "ZWBM", length = 8)
    public String getZwbm() {
        return zwbm;
    }

    public void setZwbm(String zwbm) {
        this.zwbm = zwbm;
    }

}