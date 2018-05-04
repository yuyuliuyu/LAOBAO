package com.lingnet.hcm.entity.basic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;
/**
 * 保险福利项目
 * @ClassName: InsuranceBenefits 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年4月19日 下午2:05:43 
 *
 */
@Entity
@Table(name = "JC_INSURANCE_BENEFITS")
public class InsuranceBenefits extends BaseEntity implements Serializable{ 
    private static final long serialVersionUID = 1L;
    private String name;
    private String type;
    private Integer ispay;      //0： 否       ；  1：是
    private Integer isvalid;    //0： 无效       ；  1：有效
    private String insuranceModel;// 社保缴费模板
    private String field1;
    private String field2;
    public InsuranceBenefits() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Column(name = "INSUR_NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "INSUR_TYPE")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @Column(name = "IS_PAY_BY_PERSON")
    public Integer getIspay() {
        return ispay;
    }
    public void setIspay(Integer ispay) {
        this.ispay = ispay;
    }
    @Column(name = "IS_VALID")
    public Integer getIsvalid() {
        return isvalid;
    }
    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
    @Column(name = "FIELD1")
    public String getField1() {
        return field1;
    }
    public void setField1(String field1) {
        this.field1 = field1;
    }
    @Column(name = "FIELD2")
    public String getField2() {
        return field2;
    }
    public void setField2(String field2) {
        this.field2 = field2;
    }

    @Column(name = "INSURANCE_MODEL")
    public String getInsuranceModel() {
        return insuranceModel;
    }
    public void setInsuranceModel(String insuranceModel) {
        this.insuranceModel = insuranceModel;
    }
}
