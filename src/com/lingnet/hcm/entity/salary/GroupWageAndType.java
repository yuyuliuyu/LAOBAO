package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 薪资组工资套与薪资项目关联表
 * XcGroupWageAndType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_GROUP_WAGE_AND_TYPE")
public class GroupWageAndType extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 8169362181092385581L;
    private String wageId;// 工资套ID
    private String itemId;// 薪资项目ID
    private Integer sx;// 顺序
    private Double high;// 上限
    private Double low;// 下限
    private Integer isEr;// 是否二次分配 0:不是1：是
    private Integer zljs;// 总量计算
    private Integer zlbj;// 总量比较
    private Integer isDisplay;// 默认显示 0:不显示1：显示
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private Integer type;// 状态 0：无效 1 有效
    private Integer sign;// 标志 1：引用的薪资项目 2 选择的薪资项目
    private String salaryWageId;// 工资套ID

    // Constructors

    /** default constructor */
    public GroupWageAndType() {
    }

    @Column(name = "WAGE_ID", length = 32)
    public String getWageId() {
        return this.wageId;
    }

    public void setWageId(String wageId) {
        this.wageId = wageId;
    }

    @Column(name = "ITEM_ID", length = 32)
    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Column(name = "SX", precision = 22, scale = 0)
    public Integer getSx() {
        return this.sx;
    }

    public void setSx(Integer sx) {
        this.sx = sx;
    }

    @Column(name = "HIGH", precision = 8)
    public Double getHigh() {
        return this.high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    @Column(name = "LOW", precision = 8)
    public Double getLow() {
        return this.low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    @Column(name = "IS_ER", precision = 22, scale = 0)
    public Integer getIsEr() {
        return this.isEr;
    }

    public void setIsEr(Integer isEr) {
        this.isEr = isEr;
    }

    @Column(name = "IS_DISPLAY", precision = 22, scale = 0)
    public Integer getIsDisplay() {
        return this.isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
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
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "SIGN", precision = 1, scale = 0)
    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    @Column(name="SALARY_WAGE_ID")
    public String getSalaryWageId() {
        return salaryWageId;
    }

    public void setSalaryWageId(String salaryWageId) {
        this.salaryWageId = salaryWageId;
    }

    @Column(name = "ZLJS", precision = 3, scale = 0)
    public Integer getZljs() {
        return zljs;
    }

    public void setZljs(Integer zljs) {
        this.zljs = zljs;
    }

    @Column(name = "ZLBJ", precision = 3, scale = 0)
    public Integer getZlbj() {
        return zlbj;
    }

    public void setZlbj(Integer zlbj) {
        this.zlbj = zlbj;
    }

}