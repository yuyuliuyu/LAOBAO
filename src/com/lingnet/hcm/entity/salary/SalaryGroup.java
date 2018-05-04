package com.lingnet.hcm.entity.salary;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * 薪资组
 * XcSalaryGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_GROUP")
public class SalaryGroup extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 7221095959053314677L;
    private String name;// 薪资组名称
    private String companyId;// 公司ID
    private String depId;// 部门ID
    private String salaryPeriod;// 发薪频率 1:周 2：双周 3：半月 4：月
    private Integer salaryDate;// 约定发薪日
    private Date effectDate;// 生效日期
    private Integer type;// 状态 0：无效 1 有效
    private Integer isDelete;// 删除标志 0:未删除 1：删除

    // Constructors

    /** default constructor */
    public SalaryGroup() {
    }


    @Column(name = "NAME", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
        return this.depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Column(name = "SALARY_PERIOD", length = 32)
    public String getSalaryPeriod() {
        return this.salaryPeriod;
    }

    public void setSalaryPeriod(String salaryPeriod) {
        this.salaryPeriod = salaryPeriod;
    }

    @Column(name = "SALARY_DATE", precision = 2, scale = 0)
    public Integer getSalaryDate() {
        return this.salaryDate;
    }

    public void setSalaryDate(Integer salaryDate) {
        this.salaryDate = salaryDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "EFFECT_DATE", length = 7)
    public Date getEffectDate() {
        return this.effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    @Column(name = "TYPE", precision = 1, scale = 0)
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}