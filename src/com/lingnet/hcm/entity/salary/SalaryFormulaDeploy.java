package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 薪资组工资套分析表
 * XcSalaryFormulaDeploy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_FORMULA_DEPLOY")
public class SalaryFormulaDeploy extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -190520593691729500L;
    private String groupWageId;// 薪资组工资套ID
    private String gwatId;// 薪资组工资套与薪资项目关联表ID
    private Integer executeCount;// 执行级别
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryFormulaDeploy() {
    }

    @Column(name = "GROUP_WAGE_ID", length = 32)
    public String getGroupWageId() {
        return this.groupWageId;
    }

    public void setGroupWageId(String groupWageId) {
        this.groupWageId = groupWageId;
    }

    @Column(name = "GWAT_ID", length = 32)
    public String getGwatId() {
        return this.gwatId;
    }

    public void setGwatId(String gwatId) {
        this.gwatId = gwatId;
    }

    @Column(name = "EXECUTE_COUNT")
    public Integer getExecuteCount() {
        return this.executeCount;
    }

    public void setExecuteCount(Integer executeCount) {
        this.executeCount = executeCount;
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