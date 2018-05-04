package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * XcSalaryBaseDictionary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_BASE_DICTIONARY")
public class SalaryBaseDictionary extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String jcgz;// 基础工资
    private String zjlb;// 职级类别
    private String zjlbCode;// 职级类别CODE
    private String zjlbCode2;// 职级类别CODE2
    private Integer xs;// 系数
    private String content;// 内容
    private String display;// 显示
    private Integer type;// 类型 1:职级系数 2:人员类别系数 3:在港工龄系数 4:年龄倾斜系数(男) 5:年龄倾斜系数(女)
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryBaseDictionary() {
    }

    @Column(name = "JCGZ", length = 50)
    public String getJcgz() {
        return this.jcgz;
    }

    public void setJcgz(String jcgz) {
        this.jcgz = jcgz;
    }

    @Column(name = "ZJLB", length = 50)
    public String getZjlb() {
        return this.zjlb;
    }

    public void setZjlb(String zjlb) {
        this.zjlb = zjlb;
    }

    @Column(name = "ZJLB_CODE", length = 32)
    public String getZjlbCode() {
        return zjlbCode;
    }

    public void setZjlbCode(String zjlbCode) {
        this.zjlbCode = zjlbCode;
    }

    @Column(name = "ZJLB_CODE2", length = 32)
    public String getZjlbCode2() {
        return zjlbCode2;
    }

    public void setZjlbCode2(String zjlbCode2) {
        this.zjlbCode2 = zjlbCode2;
    }

    @Column(name = "XS", precision = 22, scale = 0)
    public Integer getXs() {
        return this.xs;
    }

    public void setXs(Integer xs) {
        this.xs = xs;
    }

    @Column(name = "CONTENT", length = 200)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "DISPLAY", length = 50)
    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "TYPE", precision = 1, scale = 0)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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