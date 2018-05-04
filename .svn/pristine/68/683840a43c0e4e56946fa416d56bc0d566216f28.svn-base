package com.lingnet.hcm.entity.check;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;
/**
 * 
 * @ClassName: ChangeApply 
 * @Description: 申请变更考勤信息
 * @author wangqiang
 * @date 2017年4月24日 下午5:15:02 
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ck_change_apply")
public class ChangeApply extends BaseEntity implements Serializable {  
    private String month;
    private String day;
    private String reson;
    private String appman;
    private Date appdate;
    private String userid;
    private Date udate;
    private String field1;
    private String field2;
    private String username;
    private String applyname;
    private Integer state;  //0 申请中，1：审核通过  2：审核不通过
    private String chackno;
    private String jobnumner;
    public ChangeApply() {
        super(); 
    }
    @Column(name = "MONTH")
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    @Column(name = "DAY")
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    @Column(name = "RESON")
    public String getReson() {
        return reson;
    }
    public void setReson(String reson) {
        this.reson = reson;
    }
    @Column(name = "APPMAN")
    public String getAppman() {
        return appman;
    }
    public void setAppman(String appman) {
        this.appman = appman;
    }
    @Column(name = "APPDATE")
    public Date getAppdate() {
        return appdate;
    }
    public void setAppdate(Date appdate) {
        this.appdate = appdate;
    }
    @Column(name = "USERID")
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    @Column(name = "UDATE")
    public Date getUdate() {
        return udate;
    }
    public void setUdate(Date udate) {
        this.udate = udate;
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
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "APPLYNAME")
    public String getApplyname() {
        return applyname;
    }
    public void setApplyname(String applyname) {
        this.applyname = applyname;
    }
    @Column(name = "STATE")
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    @Column(name = "CHACKNO")
    public String getChackno() {
        return chackno;
    }
    public void setChackno(String chackno) {
        this.chackno = chackno;
    }
    @Column(name = "JOB_NUM")
    public String getJobnumner() {
        return jobnumner;
    }
    public void setJobnumner(String jobnumner) {
        this.jobnumner = jobnumner;
    }
    
}
