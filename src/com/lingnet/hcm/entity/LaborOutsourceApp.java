package com.lingnet.hcm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * LaborOutsourceApp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LABOR_OUTSOURCE_APP")
public class LaborOutsourceApp extends BaseEntity implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1635183212498017624L;
    // Fields

    private String projectCode;//项目编号
    private String applyCom;//申请单位
    private String projectName;//项目名称
    private String payCom;//付款单位
    private Double prePurPrice;//前期采购价格
    private Double estimateConValue;//预计合同值
    private String preContractor;//前期承包方
    private String timeRequest;//时间要求
    private String technicalReq;//技术要求
    private Integer state;//状态 0未提交 1审核中 2已审核 3已驳回
    

    // Constructors

    /** default constructor */
    public LaborOutsourceApp() {
    }


    /** full constructor */
    public LaborOutsourceApp(String id, String projectCode, Date createdate,
            Date modifydate, String applyCom, String projectName,
            String payCom, Double prePurPrice, Double estimateConValue,
            String preContractor, String timeRequest, String technicalReq,
            Integer state) {
        this.projectCode = projectCode;
        this.applyCom = applyCom;
        this.projectName = projectName;
        this.payCom = payCom;
        this.prePurPrice = prePurPrice;
        this.estimateConValue = estimateConValue;
        this.preContractor = preContractor;
        this.timeRequest = timeRequest;
        this.technicalReq = technicalReq;
        this.state = state;
    }

    // Property accessors

    @Column(name = "PROJECT_CODE", length = 50)
    public String getProjectCode() {
        return this.projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }


    @Column(name = "APPLY_COM", length = 32)
    public String getApplyCom() {
        return this.applyCom;
    }

    public void setApplyCom(String applyCom) {
        this.applyCom = applyCom;
    }

    @Column(name = "PROJECT_NAME", length = 32)
    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Column(name = "PAY_COM", length = 32)
    public String getPayCom() {
        return this.payCom;
    }

    public void setPayCom(String payCom) {
        this.payCom = payCom;
    }

    @Column(name = "PRE_PUR_PRICE", precision = 10)
    public Double getPrePurPrice() {
        return this.prePurPrice;
    }

    public void setPrePurPrice(Double prePurPrice) {
        this.prePurPrice = prePurPrice;
    }

    @Column(name = "ESTIMATE_CON_VALUE", precision = 10)
    public Double getEstimateConValue() {
        return this.estimateConValue;
    }

    public void setEstimateConValue(Double estimateConValue) {
        this.estimateConValue = estimateConValue;
    }

    @Column(name = "PRE_CONTRACTOR", length = 50)
    public String getPreContractor() {
        return this.preContractor;
    }

    public void setPreContractor(String preContractor) {
        this.preContractor = preContractor;
    }

    @Column(name = "TIME_REQUEST", length = 100)
    public String getTimeRequest() {
        return this.timeRequest;
    }

    public void setTimeRequest(String timeRequest) {
        this.timeRequest = timeRequest;
    }

    @Column(name = "TECHNICAL_REQ")
    public String getTechnicalReq() {
        return this.technicalReq;
    }

    public void setTechnicalReq(String technicalReq) {
        this.technicalReq = technicalReq;
    }

    @Column(name = "STATE", precision = 1, scale = 0)
    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}