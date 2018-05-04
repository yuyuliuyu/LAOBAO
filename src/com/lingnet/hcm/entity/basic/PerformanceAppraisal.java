package com.lingnet.hcm.entity.basic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * 绩效考核
 * JcPerformanceAppraisal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JC_PERFORMANCE_APPRAISAL")
public class PerformanceAppraisal extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -6136445770637127523L;
    private String deptid;// 部门ID
    private String deptName;// 部门名称
    private String userid;// 责任人ID
    private String jobNumber;// 责任人工号
    private String userName;// 责任人名称
    private String perforDeptId;// 考核部门ID
    private String perforDeptName;// 考核部门名称
    private String perforStyle;// 考核分类
    private String perforThing;// 考核事件
    private Date perforDate;// 考核日期
    private Integer perforAppr;// 考核确认 0:未确认 1：已确认
    private Integer perforState;// 考核兑现 0:未兑现 1：已兑现
    private String perforRewardScroe;// 考核奖励分数
    private String perforRewardMoney;// 考核奖励金额
    private String perforPunishScroe;// 考核扣罚分数
    private String perforPunishMoney;// 考核扣罚金额
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String ctrId;// 操作人ID
    private String perforApprId;// 考核确认人员ID
    private String perforStateId;// 考核兑现人员ID
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public PerformanceAppraisal() {
    }

    @Column(name = "DEPTID", length = 32)
    public String getDeptid() {
        return this.deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    @Column(name = "DEPT_NAME")
    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Column(name = "USERID", length = 32)
    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Column(name = "USER_NAME", length = 50)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "PERFOR_DEPT_ID", length = 32)
    public String getPerforDeptId() {
        return this.perforDeptId;
    }

    public void setPerforDeptId(String perforDeptId) {
        this.perforDeptId = perforDeptId;
    }

    @Column(name = "PERFOR_DEPT_NAME")
    public String getPerforDeptName() {
        return this.perforDeptName;
    }

    public void setPerforDeptName(String perforDeptName) {
        this.perforDeptName = perforDeptName;
    }

    @Column(name = "PERFOR_STYLE", length = 32)
    public String getPerforStyle() {
        return this.perforStyle;
    }

    public void setPerforStyle(String perforStyle) {
        this.perforStyle = perforStyle;
    }

    @Column(name = "PERFOR_THING", length = 2000)
    public String getPerforThing() {
        return this.perforThing;
    }

    public void setPerforThing(String perforThing) {
        this.perforThing = perforThing;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "PERFOR_DATE", length = 7)
    public Date getPerforDate() {
        return this.perforDate;
    }

    public void setPerforDate(Date perforDate) {
        this.perforDate = perforDate;
    }

    @Column(name = "PERFOR_APPR", precision = 1, scale = 0)
    public Integer getPerforAppr() {
        return this.perforAppr;
    }

    public void setPerforAppr(Integer perforAppr) {
        this.perforAppr = perforAppr;
    }

    @Column(name = "PERFOR_STATE", precision = 1, scale = 0)
    public Integer getPerforState() {
        return this.perforState;
    }

    public void setPerforState(Integer perforState) {
        this.perforState = perforState;
    }

    @Column(name = "PERFOR_REWARD_SCROE", length = 20)
    public String getPerforRewardScroe() {
        return this.perforRewardScroe;
    }

    public void setPerforRewardScroe(String perforRewardScroe) {
        this.perforRewardScroe = perforRewardScroe;
    }

    @Column(name = "PERFOR_REWARD_MONEY", length = 20)
    public String getPerforRewardMoney() {
        return this.perforRewardMoney;
    }

    public void setPerforRewardMoney(String perforRewardMoney) {
        this.perforRewardMoney = perforRewardMoney;
    }

    @Column(name = "PERFOR_PUNISH_SCROE", length = 20)
    public String getPerforPunishScroe() {
        return this.perforPunishScroe;
    }

    public void setPerforPunishScroe(String perforPunishScroe) {
        this.perforPunishScroe = perforPunishScroe;
    }

    @Column(name = "PERFOR_PUNISH_MONEY", length = 20)
    public String getPerforPunishMoney() {
        return this.perforPunishMoney;
    }

    public void setPerforPunishMoney(String perforPunishMoney) {
        this.perforPunishMoney = perforPunishMoney;
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

    @Column(name = "CTR_ID", length = 32)
    public String getCtrId() {
        return ctrId;
    }

    public void setCtrId(String ctrId) {
        this.ctrId = ctrId;
    }

    @Column(name = "PERFOR_APPR_ID", length = 32)
    public String getPerforApprId() {
        return perforApprId;
    }

    public void setPerforApprId(String perforApprId) {
        this.perforApprId = perforApprId;
    }

    @Column(name = "PERFOR_STATE_ID", length = 32)
    public String getPerforStateId() {
        return perforStateId;
    }

    public void setPerforStateId(String perforStateId) {
        this.perforStateId = perforStateId;
    }

    @Column(name = "JOB_NUMBER", length = 32)
    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

}