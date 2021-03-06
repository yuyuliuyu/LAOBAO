package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 二次分配薪资表
 * XcSalaryAgainItems entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_AGAIN_ITEMS")
public class SalaryAgainItems extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -7383687617243389204L;
    private String staffId;// 员工ID
    private String salaryAgainId;// 二次分配总量主表ID
    private String salaryItemsId;// 二次分配薪资项目ID
    private String salaryItemsName;// 二次分配薪资项目名称
    private String needAmount;// 实际分配总量
    private String reportAmount;// 上报分配总量
    private Integer sx;// 顺序
    private String numberAccuracy;// 小数位数
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String staffName;// 员工姓名
    private String jobNumber;// 职工号
    private String salaryGroupId;// 薪资组ID
    private String salaryGroupName;// 薪资组名称
    private Integer fpqx;// 分配权限 1：公司(不下发) 2：基层
    private String jtgwId;// 具体岗位ID
    private String jtgw;// 具体岗位
    private String bggwId;// 标准岗位ID
    private String bzgw;// 标准岗位
    private String xcgwId;// 薪酬岗位ID
    private String xcgw;// 薪酬岗位
    private String gzxs;// 工资形式
    private String specialMark;// 特殊标记
    private String staticValue;// 标准值
    private String field1;
    private String field2;
    private Integer isDefaultDisplay;// 默认显示 0:不显示1：显示

    // Constructors

    /** default constructor */
    public SalaryAgainItems() {
    }

    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "SALARY_AGAIN_ID", length = 32)
    public String getSalaryAgainId() {
        return this.salaryAgainId;
    }

    public void setSalaryAgainId(String salaryAgainId) {
        this.salaryAgainId = salaryAgainId;
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

    @Column(name = "NEED_AMOUNT", length = 20)
    public String getNeedAmount() {
        return needAmount;
    }

    public void setNeedAmount(String needAmount) {
        this.needAmount = needAmount;
    }

    @Column(name = "REPORT_AMOUNT", length = 20)
    public String getReportAmount() {
        return reportAmount;
    }

    public void setReportAmount(String reportAmount) {
        this.reportAmount = reportAmount;
    }

    @Column(name = "SX")
    public Integer getSx() {
        return sx;
    }

    public void setSx(Integer sx) {
        this.sx = sx;
    }

    @Column(name = "NUMBER_ACCURACY")
    public String getNumberAccuracy() {
        return numberAccuracy;
    }

    public void setNumberAccuracy(String numberAccuracy) {
        this.numberAccuracy = numberAccuracy;
    }

    @Column(name = "STAFF_NAME")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Column(name = "JOB_NUMBER")
    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Column(name = "SALARY_GROUP_ID", length = 20)
    public String getSalaryGroupId() {
        return salaryGroupId;
    }

    public void setSalaryGroupId(String salaryGroupId) {
        this.salaryGroupId = salaryGroupId;
    }

    @Column(name = "SALARY_GROUP_NAME", length = 200)
    public String getSalaryGroupName() {
        return salaryGroupName;
    }

    public void setSalaryGroupName(String salaryGroupName) {
        this.salaryGroupName = salaryGroupName;
    }

    @Column(name = "FPQX", precision = 1, scale = 0)
    public Integer getFpqx() {
        return fpqx;
    }

    public void setFpqx(Integer fpqx) {
        this.fpqx = fpqx;
    }

    @Column(name = "JTGW", length = 100)
    public String getJtgw() {
        return jtgw;
    }

    public void setJtgw(String jtgw) {
        this.jtgw = jtgw;
    }

    @Column(name = "BZGW", length = 100)
    public String getBzgw() {
        return bzgw;
    }

    public void setBzgw(String bzgw) {
        this.bzgw = bzgw;
    }

    @Column(name = "XCGW", length = 100)
    public String getXcgw() {
        return xcgw;
    }

    public void setXcgw(String xcgw) {
        this.xcgw = xcgw;
    }

    @Column(name = "GZXS", length = 50)
    public String getGzxs() {
        return gzxs;
    }

    public void setGzxs(String gzxs) {
        this.gzxs = gzxs;
    }

    @Column(name = "SPECIAL_MARK", length = 50)
    public String getSpecialMark() {
        return specialMark;
    }

    public void setSpecialMark(String specialMark) {
        this.specialMark = specialMark;
    }

    @Column(name = "STATIC_VALUE", length = 50)
    public String getStaticValue() {
        return staticValue;
    }

    public void setStaticValue(String staticValue) {
        this.staticValue = staticValue;
    }

    @Column(name = "JTGW_ID", length = 32)
    public String getJtgwId() {
        return jtgwId;
    }

    public void setJtgwId(String jtgwId) {
        this.jtgwId = jtgwId;
    }

    @Column(name = "BZGW_ID", length = 32)
    public String getBggwId() {
        return bggwId;
    }

    public void setBggwId(String bggwId) {
        this.bggwId = bggwId;
    }

    @Column(name = "XCGW_ID", length = 32)
    public String getXcgwId() {
        return xcgwId;
    }

    public void setXcgwId(String xcgwId) {
        this.xcgwId = xcgwId;
    }

    @Column(name = "IS_DEFAULT_DISPLAY")
    public Integer getIsDefaultDisplay() {
        return isDefaultDisplay;
    }

    public void setIsDefaultDisplay(Integer isDefaultDisplay) {
        this.isDefaultDisplay = isDefaultDisplay;
    }

}