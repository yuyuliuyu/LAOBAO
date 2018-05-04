package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 工资分配核对色值配置表
 * XcCheckSalaryColor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_CHECK_SALARY_COLOR")
public class CheckSalaryColor extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -3666862009726331867L;
    private String companyId;// 公司ID
    private String ibfId;// 薪资项目ID
    private String color;// 色值
    private String colorDiff;// 色值差
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public CheckSalaryColor() {
    }

    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "IBF_ID", length = 32)
    public String getIbfId() {
        return this.ibfId;
    }

    public void setIbfId(String ibfId) {
        this.ibfId = ibfId;
    }

    @Column(name = "COLOR", length = 20)
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "COLOR_DIFF", precision = 20)
    public String getColorDiff() {
        return this.colorDiff;
    }

    public void setColorDiff(String colorDiff) {
        this.colorDiff = colorDiff;
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