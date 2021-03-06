package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * JcEmpRetire entity. 
 * @author MyEclipse Persistence Tools
 * 退休实体类
 */
@Entity
@Table(name = "JC_EMP_RETIRE")
public class EmpRetire extends BaseEntity implements java.io.Serializable {

    // Fields
	private static final long serialVersionUID = 4674733589444799799L;
	private String jobNumber;//工号
    private Integer leaveType;//退休类型
    private Date leaveDate;//退休时间
    private String preFirm;//退休前公司
    private String preDep;//退休前部门
    private String laterFirm;//退休后管理公司
    private String laterDep;//退休后管理部门
    private String approveFirm;//批准单位
    private String fileNum;//批准文号
    private String url;//文件地址
    private String fileName;//文件名称
    private String remark;//
    private String personId;//工号
    
    



    @Column(name = "JOB_NUMBER", length = 20)
    public String getJobNumber() {
        return this.jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Column(name = "LEAVE_TYPE", precision = 1, scale = 0)
    public Integer getLeaveType() {
        return this.leaveType;
    }

    public void setLeaveType(Integer leaveType) {
        this.leaveType = leaveType;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "LEAVE_DATE", length = 7)
    public Date getLeaveDate() {
        return this.leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    @Column(name = "PRE_FIRM", length = 32)
    public String getPreFirm() {
        return this.preFirm;
    }

    public void setPreFirm(String preFirm) {
        this.preFirm = preFirm;
    }

    @Column(name = "PRE_DEP", length = 32)
    public String getPreDep() {
        return this.preDep;
    }

    public void setPreDep(String preDep) {
        this.preDep = preDep;
    }

    @Column(name = "LATER_FIRM", length = 32)
    public String getLaterFirm() {
        return this.laterFirm;
    }

    public void setLaterFirm(String laterFirm) {
        this.laterFirm = laterFirm;
    }

    @Column(name = "APPROVE_FIRM", length = 32)
    public String getApproveFirm() {
        return this.approveFirm;
    }

    public void setApproveFirm(String approveFirm) {
        this.approveFirm = approveFirm;
    }

    @Column(name = "FILE_NUM", length = 20)
    public String getFileNum() {
        return this.fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum;
    }

    @Column(name = "URL", length = 50)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "FILE_NAME", length = 10)
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "REMARK", length = 50)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "PERSON_ID")
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	 @Column(name = "LATER_DEP")
	public String getLaterDep() {
		return laterDep;
	}

	public void setLaterDep(String laterDep) {
		this.laterDep = laterDep;
	}

}