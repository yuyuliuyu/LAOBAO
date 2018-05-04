package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 工资条关联副表
 * XcSalaryWageSecond entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_WAGE_SECOND")
public class SalaryWageSecond extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -275638304812101605L;
    private String salaryWageMainId;// 工资条关联主表ID
    private String ibfId;// 薪资项目ID
    private String itemsName;// 薪资项目名称
    private Integer sx;// 顺序
    private String salaryValue;// 实际值
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryWageSecond() {
    }

    @Column(name = "SALARY_WAGE_MAIN_ID", length = 32)
    public String getSalaryWageMainId() {
        return this.salaryWageMainId;
    }

    public void setSalaryWageMainId(String salaryWageMainId) {
        this.salaryWageMainId = salaryWageMainId;
    }

    @Column(name = "IBF_ID", length = 32)
    public String getIbfId() {
        return this.ibfId;
    }

    public void setIbfId(String ibfId) {
        this.ibfId = ibfId;
    }

    @Column(name = "ITEMS_NAME", length = 200)
    public String getItemsName() {
        return this.itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    @Column(name = "SALARY_VALUE", length = 20)
    public String getSalaryValue() {
        return this.salaryValue;
    }

    public void setSalaryValue(String salaryValue) {
        this.salaryValue = salaryValue;
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

    @Column(name = "SX")
    public Integer getSx() {
        return sx;
    }

    public void setSx(Integer sx) {
        this.sx = sx;
    }

}