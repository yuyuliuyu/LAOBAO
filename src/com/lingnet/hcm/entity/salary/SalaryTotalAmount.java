package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 总量表
 * XcSalaryTotalAmount entity. @author MyEclipse Persistence Tools
 */
@Entity 
@Table(name = "XC_SALARY_TOTAL_AMOUNT")
public class SalaryTotalAmount extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -8575220871453724364L;
    private String companyId;// 公司ID
    private String depId;// 部门ID
    private Integer peopleCount;// 人数
    private String lastMonthAmount;// 上月结余
    private String byjxldkh;// 本月绩效联动考核
    private String specialReward;// 特殊激励
    private String otherReward;// 其他1
    private String otherReward2;// 其他2
    private String otherReward3;// 其他3
    private String rewardTotal;// 合计
    private String bykfpzl;// 本月可分配总量
    private String fpDate;// 发薪期间
    private String curMonthAmount;// 本月结余
    private String fpqx;// 分配权限 1：公司 2：基层
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String note;// 备注
    private String field1;
    private String field2;
    private String salaryAssignationId;// 薪酬分配过程ID

    // Constructors

    /** default constructor */
    public SalaryTotalAmount() {
    }

    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "DEP_ID", length = 32)
    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Column(name = "PEOPLE_COUNT", precision = 6, scale = 0)
    public Integer getPeopleCount() {
        return this.peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    @Column(name = "LAST_MONTH_AMOUNT", length = 20)
    public String getLastMonthAmount() {
        return this.lastMonthAmount;
    }

    public void setLastMonthAmount(String lastMonthAmount) {
        this.lastMonthAmount = lastMonthAmount;
    }

    @Column(name = "BYJXLDKH", length = 20)
    public String getByjxldkh() {
        return this.byjxldkh;
    }

    public void setByjxldkh(String byjxldkh) {
        this.byjxldkh = byjxldkh;
    }

    @Column(name = "SPECIAL_REWARD", length = 20)
    public String getSpecialReward() {
        return this.specialReward;
    }

    public void setSpecialReward(String specialReward) {
        this.specialReward = specialReward;
    }

    @Column(name = "OTHER_REWARD", length = 20)
    public String getOtherReward() {
        return this.otherReward;
    }

    public void setOtherReward(String otherReward) {
        this.otherReward = otherReward;
    }

    @Column(name = "REWARD_TOTAL", length = 20)
    public String getRewardTotal() {
        return this.rewardTotal;
    }

    public void setRewardTotal(String rewardTotal) {
        this.rewardTotal = rewardTotal;
    }

    @Column(name = "BYKFPZL", length = 20)
    public String getBykfpzl() {
        return this.bykfpzl;
    }

    public void setBykfpzl(String bykfpzl) {
        this.bykfpzl = bykfpzl;
    }

    @Column(name = "FP_DATE", length = 32)
    public String getFpDate() {
        return this.fpDate;
    }

    public void setFpDate(String fpDate) {
        this.fpDate = fpDate;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "NOTE", length = 2000)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
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

    @Column(name = "CUR_MONTH_AMOUNT", length = 20)
    public String getCurMonthAmount() {
        return curMonthAmount;
    }

    public void setCurMonthAmount(String curMonthAmount) {
        this.curMonthAmount = curMonthAmount;
    }

    @Column(name = "FPQX", length = 32)
    public String getFpqx() {
        return fpqx;
    }

    public void setFpqx(String fpqx) {
        this.fpqx = fpqx;
    }

    @Column(name = "SALARY_ASSIGNATION_ID", length = 32)
    public String getSalaryAssignationId() {
        return salaryAssignationId;
    }

    public void setSalaryAssignationId(String salaryAssignationId) {
        this.salaryAssignationId = salaryAssignationId;
    }

    @Column(name = "OTHER_REWARD2", length = 20)
    public String getOtherReward2() {
        return otherReward2;
    }

    public void setOtherReward2(String otherReward2) {
        this.otherReward2 = otherReward2;
    }

    @Column(name = "OTHER_REWARD3", length = 20)
    public String getOtherReward3() {
        return otherReward3;
    }

    public void setOtherReward3(String otherReward3) {
        this.otherReward3 = otherReward3;
    }

}