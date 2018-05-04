package com.lingnet.hcm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * Outcontract entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OUTCONTRACT")
public class Outcontract extends BaseEntity implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5438564086727816967L;
    // Fields

    private String contractCode;//合同编号
    private String contractName;//合同名称
    private String contractorJia;//合同甲方
    private String contractorYi;//合同乙方
    private String type;//合同类别
    private Integer payDirection;//付款方向 0付款1收款
    private String applyId;//申请单id
    private Date startDate;//合同开始日
    private Date endDate;//合同截止日

    // Constructors

    /** default constructor */
    public Outcontract() {
    }

    /** full constructor */
    public Outcontract(String id, Date createdate, Date modifydate,
            String contractCode, String contractName, String contractorJia,
            String contractorYi, String type, Integer payDirection) {
        this.contractCode = contractCode;
        this.contractName = contractName;
        this.contractorJia = contractorJia;
        this.contractorYi = contractorYi;
        this.type = type;
        this.payDirection = payDirection;
    }

    // Property accessors

    @Column(name = "CONTRACT_CODE", length = 50)
    public String getContractCode() {
        return this.contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Column(name = "CONTRACT_NAME", length = 200)
    public String getContractName() {
        return this.contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    @Column(name = "CONTRACTOR_JIA", length = 50)
    public String getContractorJia() {
        return this.contractorJia;
    }

    public void setContractorJia(String contractorJia) {
        this.contractorJia = contractorJia;
    }

    @Column(name = "CONTRACTOR_YI", length = 50)
    public String getContractorYi() {
        return this.contractorYi;
    }

    public void setContractorYi(String contractorYi) {
        this.contractorYi = contractorYi;
    }

    @Column(name = "TYPE", length = 50)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "PAY_DIRECTION", precision = 1, scale = 0)
    public Integer getPayDirection() {
        return this.payDirection;
    }

    public void setPayDirection(Integer payDirection) {
        this.payDirection = payDirection;
    }

    @Column(name = "APPLY_ID", precision = 1, scale = 0)
    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}