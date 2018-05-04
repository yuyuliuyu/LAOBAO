package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 参保人员与福利项目中间表
 * XcInsuranceMiddle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_INSURANCE_MIDDLE")
public class InsuranceMiddle extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -32990232566493937L;
    private String staffId;// 职工ID
    private String depId;// 部门ID
    private Integer isDaijiao;// 是否代缴 0:不代缴 1：代缴
    private Integer isDelete;// 删除标志 0:未删除 1：删除

    // Constructors

    /** default constructor */
    public InsuranceMiddle() {
    }

    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return this.staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "DEP_ID", length = 32)
    public String getDepId() {
        return this.depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Column(name = "IS_DAIJIAO", precision = 1, scale = 0)
    public Integer getIsDaijiao() {
        return this.isDaijiao;
    }

    public void setIsDaijiao(Integer isDaijiao) {
        this.isDaijiao = isDaijiao;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}