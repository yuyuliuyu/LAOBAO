package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 总量其他薪资项目表
 * XcSalaryAmountItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_AMOUNT_ITEM")
public class SalaryAmountItem extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -1718739949927040406L;
    private String totalAmountId;// 总量主表ID
    private String depId;// 部门ID
    private String salaryItemsId;// 二次分配薪资项目ID
    private String salaryItemsName;// 二次分配薪资项目名称
    private String needAmount;// 实际分配总量
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private Integer sx;// 顺序
    private String numberAccuracy;// 保留小数
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryAmountItem() {
    }

    @Column(name = "TOTAL_AMOUNT_ID", length = 32)
    public String getTotalAmountId() {
        return this.totalAmountId;
    }

    public void setTotalAmountId(String totalAmountId) {
        this.totalAmountId = totalAmountId;
    }

    @Column(name = "DEP_ID", length = 32)
    public String getDepId() {
        return this.depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Column(name = "SALARY_ITEMS_ID", length = 32)
    public String getSalaryItemsId() {
        return this.salaryItemsId;
    }

    public void setSalaryItemsId(String salaryItemsId) {
        this.salaryItemsId = salaryItemsId;
    }

    @Column(name = "SALARY_ITEMS_NAME", length = 200)
    public String getSalaryItemsName() {
        return this.salaryItemsName;
    }

    public void setSalaryItemsName(String salaryItemsName) {
        this.salaryItemsName = salaryItemsName;
    }

    @Column(name = "NEED_AMOUNT", length = 20)
    public String getNeedAmount() {
        return this.needAmount;
    }

    public void setNeedAmount(String needAmount) {
        this.needAmount = needAmount;
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

    @Column(name = "SX", precision = 22, scale = 0)
    public Integer getSx() {
        return this.sx;
    }

    public void setSx(Integer sx) {
        this.sx = sx;
    }

    @Column(name = "NUMBER_ACCURACY", length = 20)
    public String getNumberAccuracy() {
        return this.numberAccuracy;
    }

    public void setNumberAccuracy(String numberAccuracy) {
        this.numberAccuracy = numberAccuracy;
    }

}