package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 薪资账号
 * XcSalaryAccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_ACCOUNT")
public class SalaryAccount extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -4072637943680694392L;
    private String staffId;// 员工ID
    private String accountType;// 账户类型
    private String accountBank;// 金融机构
    private String account;// 账号
    private String userName;// 用户名
    private String note;// 描述
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;
    private String recordMainId;// 档案表ID

    // Constructors

    /** default constructor */
    public SalaryAccount() {
    }

    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return this.staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "ACCOUNT_TYPE", length = 32)
    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Column(name = "ACCOUNT", length = 50)
    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Column(name = "USER_NAME", length = 100)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "NOTE", length = 2000)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
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

    @Column(name = "ACCOUNT_BANK", length = 32)
    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    @Column(name = "RECORD_MAIN_ID", length = 32)
    public String getRecordMainId() {
        return recordMainId;
    }

    public void setRecordMainId(String recordMainId) {
        this.recordMainId = recordMainId;
    }

}