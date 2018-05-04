package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 薪酬核算数据薪资中间表
 * XcAssignationStaffSalary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_ASSIGNATION_STAFF_SALARY")
public class AssignationStaffSalary extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 37496816255705202L;
    private String assignationStaffId;// 薪酬核算数据主表ID
    private String ibfId;// 薪资项目ID
    private String ibfName;// 薪资项目名称
    private String assignationCharge;// 费用
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private Integer sx;// 顺序
    private Integer isDisplay;// 是否二次分配 0：不显示1 显示(标准类)
    private Integer addOrLess;// 增减属性 1:加 2:减3:其他
    private Integer isNumber;// 是数值
    private String numberAccuracy;// 小数位数
    private String field1;
    private String field2;
    private Integer isDefaultDisplay;// 默认显示 0:不显示1：显示

    // Constructors

    /** default constructor */
    public AssignationStaffSalary() {
    }

    @Column(name = "ASSIGNATION_STAFF_ID", length = 32)
    public String getAssignationStaffId() {
        return this.assignationStaffId;
    }

    public void setAssignationStaffId(String assignationStaffId) {
        this.assignationStaffId = assignationStaffId;
    }

    @Column(name = "IBF_ID", length = 32)
    public String getIbfId() {
        return this.ibfId;
    }

    public void setIbfId(String ibfId) {
        this.ibfId = ibfId;
    }

    @Column(name = "IBF_NAME", length = 200)
    public String getIbfName() {
        return ibfName;
    }

    public void setIbfName(String ibfName) {
        this.ibfName = ibfName;
    }

    @Column(name = "ASSIGNATION_CHARGE", length = 2000)
    public String getAssignationCharge() {
        return this.assignationCharge;
    }

    public void setAssignationCharge(String assignationCharge) {
        this.assignationCharge = assignationCharge;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "SX")
    public Integer getSx() {
        return sx;
    }

    public void setSx(Integer sx) {
        this.sx = sx;
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

    @Column(name = "IS_DISPLAY", precision = 1, scale = 0)
    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    @Column(name = "ADD_OR_LESS", precision = 1, scale = 0)
    public Integer getAddOrLess() {
        return this.addOrLess;
    }

    public void setAddOrLess(Integer addOrLess) {
        this.addOrLess = addOrLess;
    }

    @Column(name = "IS_NUMBER", precision = 1, scale = 0)
    public Integer getIsNumber() {
        return isNumber;
    }

    public void setIsNumber(Integer isNumber) {
        this.isNumber = isNumber;
    }

    @Column(name = "NUMBER_ACCURACY")
    public String getNumberAccuracy() {
        return this.numberAccuracy;
    }

    public void setNumberAccuracy(String numberAccuracy) {
        this.numberAccuracy = numberAccuracy;
    }

    @Column(name = "IS_DEFAULT_DISPLAY")
    public Integer getIsDefaultDisplay() {
        return isDefaultDisplay;
    }

    public void setIsDefaultDisplay(Integer isDefaultDisplay) {
        this.isDefaultDisplay = isDefaultDisplay;
    }

}