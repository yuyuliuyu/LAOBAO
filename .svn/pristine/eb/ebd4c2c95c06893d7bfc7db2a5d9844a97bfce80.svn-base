package com.lingnet.hcm.entity.check;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 
 * @ClassName: CONTRACT_LOGS 
 * @Description: 合同协议修改记录
 * @author wangqiang
 * @date 2017年4月14日 上午10:21:06  
 */
@Entity
@Table(name = "CONTRACT_LOGS")
public class ContractLogs extends BaseEntity implements Serializable {  
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String contractid;
    private String changeman;
    private String changemanid;
    private String chengememo;
    private String field1;
    private String field2;
    public ContractLogs() {
        super(); 
    }
    @Column(name = "CONTRACT_ID")
    public String getContractid() {
        return contractid;
    }
    public void setContractid(String contractid) {
        this.contractid = contractid;
    }
    @Column(name = "CHANGEMAN")
    public String getChangeman() {
        return changeman;
    }
    public void setChangeman(String changeman) {
        this.changeman = changeman;
    }
    @Column(name = "CHANGEMANID")
    public String getChangemanid() {
        return changemanid;
    }
    public void setChangemanid(String changemanid) {
        this.changemanid = changemanid;
    }
    @Column(name = "CHANGEMEMO")
    public String getChengememo() {
        return chengememo;
    }
    public void setChengememo(String chengememo) {
        this.chengememo = chengememo;
    }
    @Column(name = "FILE1")
    public String getField1() {
        return this.field1;
    } 
    public void setField1(String field1) {
        this.field1 = field1;
    } 
    @Column(name = "FILE2")
    public String getField2() {
        return this.field2;
    } 
    public void setField2(String field2) {
        this.field2 = field2;
    } 
}
