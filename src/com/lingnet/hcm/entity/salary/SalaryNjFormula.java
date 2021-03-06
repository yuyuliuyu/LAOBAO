package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 年金数据计算表
 * JcSalaryNjFormula entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JC_SALARY_NJ_FORMULA")
public class SalaryNjFormula extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 2217173430141287521L;
    private String companyId;// 单位ID
    private String companyName;// 单位名称
    private String staffId;// 职工ID
    private String staffName;// 职工姓名
    private String lastYearTotal;// 上年度月均工资总额
    private String deptLevel;// 职务类别
    private String sflb;// 身份类别
    private Integer isStop;// 停止缴费 0:未停止缴费 1：已停止缴费
    private String endAge;// 截至XXX年龄
    private String jzsnmnl;// 截至上年末年龄
    private String qxxjfzjbl;// 倾斜性缴费职级比例
    private String qxxjfnlxs;// 倾斜性缴费年龄系数
    private String qxxjfje;// 倾斜性缴费金额
    private String jbjfzjxs;// 基本缴费职级系数
    private String jbjfnlxs;// 基本缴费年龄系数
    private String jbjfje;// 基本缴费金额
    private String totalMoney;// 合计月缴费金额
    private Integer year;// 缴费年
    private Integer isDelete;//删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryNjFormula() {
    }

    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "COMPANY_NAME", length = 300)
    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return this.staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "STAFF_NAME", length = 50)
    public String getStaffName() {
        return this.staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Column(name = "LAST_YEAR_TOTAL", length = 20)
    public String getLastYearTotal() {
        return this.lastYearTotal;
    }

    public void setLastYearTotal(String lastYearTotal) {
        this.lastYearTotal = lastYearTotal;
    }

    @Column(name = "DEPT_LEVEL", length = 200)
    public String getDeptLevel() {
        return this.deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    @Column(name = "SFLB", length = 200)
    public String getSflb() {
        return this.sflb;
    }

    public void setSflb(String sflb) {
        this.sflb = sflb;
    }

    @Column(name = "IS_STOP", precision = 1, scale = 0)
    public Integer getIsStop() {
        return this.isStop;
    }

    public void setIsStop(Integer isStop) {
        this.isStop = isStop;
    }

    @Column(name = "END_AGE", length = 100)
    public String getEndAge() {
        return this.endAge;
    }

    public void setEndAge(String endAge) {
        this.endAge = endAge;
    }

    @Column(name = "JZSNMNL", length = 20)
    public String getJzsnmnl() {
        return this.jzsnmnl;
    }

    public void setJzsnmnl(String jzsnmnl) {
        this.jzsnmnl = jzsnmnl;
    }

    @Column(name = "QXXJFZJBL", length = 20)
    public String getQxxjfzjbl() {
        return this.qxxjfzjbl;
    }

    public void setQxxjfzjbl(String qxxjfzjbl) {
        this.qxxjfzjbl = qxxjfzjbl;
    }

    @Column(name = "QXXJFNLXS", length = 20)
    public String getQxxjfnlxs() {
        return this.qxxjfnlxs;
    }

    public void setQxxjfnlxs(String qxxjfnlxs) {
        this.qxxjfnlxs = qxxjfnlxs;
    }

    @Column(name = "QXXJFJE", length = 20)
    public String getQxxjfje() {
        return this.qxxjfje;
    }

    public void setQxxjfje(String qxxjfje) {
        this.qxxjfje = qxxjfje;
    }

    @Column(name = "JBJFZJXS", length = 20)
    public String getJbjfzjxs() {
        return this.jbjfzjxs;
    }

    public void setJbjfzjxs(String jbjfzjxs) {
        this.jbjfzjxs = jbjfzjxs;
    }

    @Column(name = "JBJFNLXS", length = 20)
    public String getJbjfnlxs() {
        return this.jbjfnlxs;
    }

    public void setJbjfnlxs(String jbjfnlxs) {
        this.jbjfnlxs = jbjfnlxs;
    }

    @Column(name = "JBJFJE", length = 20)
    public String getJbjfje() {
        return this.jbjfje;
    }

    public void setJbjfje(String jbjfje) {
        this.jbjfje = jbjfje;
    }

    @Column(name = "TOTAL_MONEY", length = 20)
    public String getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
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

    @Column(name = "YEAR", precision = 4, scale = 0)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}