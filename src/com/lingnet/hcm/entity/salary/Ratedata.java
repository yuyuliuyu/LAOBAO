package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 税率中间表
 * XcRatedata entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_RATEDATA")
public class Ratedata extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 8004642000094962363L;
    private String rateId;// 税率表ID
    private Double high;// 上限
    private Double low;// 下限
    private Double rate;// 税率
    private Double kcs;// 速算扣除数
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public Ratedata() {
    }

    @Column(name = "RATE_ID", length = 32)
    public String getRateId() {
        return this.rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
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

    @Column(name = "RATE", precision = 5)
    public Double getRate() {
        return this.rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Column(name = "KCS", precision = 8)
    public Double getKcs() {
        return this.kcs;
    }

    public void setKcs(Double kcs) {
        this.kcs = kcs;
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