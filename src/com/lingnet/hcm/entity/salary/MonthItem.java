package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 平均工资计算过程与薪资项目关联表
 * XcMonthItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_MONTH_ITEM")
public class MonthItem extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -2905683193931150097L;
    private String salaryMonthId;// 平均工资计算过程ID
    private String itemsId;// 薪资项目ID
    private Integer isAdd;// 加减类型 1：减 2：加
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public MonthItem() {
    }

    @Column(name = "SALARY_MONTH_ID", length = 32)
    public String getSalaryMonthId() {
        return this.salaryMonthId;
    }

    public void setSalaryMonthId(String salaryMonthId) {
        this.salaryMonthId = salaryMonthId;
    }

    @Column(name = "ITEMS_ID", length = 32)
    public String getItemsId() {
        return this.itemsId;
    }

    public void setItemsId(String itemsId) {
        this.itemsId = itemsId;
    }

    @Column(name = "IS_ADD", precision = 1, scale = 0)
    public Integer getIsAdd() {
        return this.isAdd;
    }

    public void setIsAdd(Integer isAdd) {
        this.isAdd = isAdd;
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