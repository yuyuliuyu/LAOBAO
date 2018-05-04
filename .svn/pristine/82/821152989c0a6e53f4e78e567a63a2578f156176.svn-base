package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * practiceAuthenticate entity. 
 * @author MyEclipse Persistence Tools
 *  实习生鉴定信息
 */
@Entity
@Table(name = "JC_PRACTICE_AUTHENTICATE")
public class PracticeAuthenticate extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 3795573753462357081L;
	
	private String empId;//实习生ID
	
	private String theResult;//鉴定结果
	private Date theDate;//时间
	private String selfEvaluate;//自我鉴定评价
	private String principalsEvaluate;//负责人评价
	private String principalsName;//负责人名字
	private Date principalsDate;//评价时间
	private String depEvaluate;//部门评价
	private String depName;//部门负责人名字
	private Date depDate;//部门评价时间
	private String hrEvaluate;//人力资源评价
	private String hr;//人力资源
	private Date hrDate;//人力资源评价时间
	private String leadEvaluate;//领导评价
	private String leader;//领导名称
	private Date leadDate;//领导评价时间
	private String fileName;//文件名称
	private String url;//
	private String remark;//备注u
	private Integer emtype;//类型

	// Property accessors

	@Column(name = "EMP_ID", length = 32)
	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Column(name = "THE_RESULT", length = 10)
	public String getTheResult() {
		return this.theResult;
	}

	public void setTheResult(String theResult) {
		this.theResult = theResult;
	}

	@Column(name = "SELF_EVALUATE", length = 20)
	public String getSelfEvaluate() {
		return this.selfEvaluate;
	}

	public void setSelfEvaluate(String selfEvaluate) {
		this.selfEvaluate = selfEvaluate;
	}

	@Column(name = "PRINCIPALS_EVALUATE", length = 20)
	public String getPrincipalsEvaluate() {
		return this.principalsEvaluate;
	}

	public void setPrincipalsEvaluate(String principalsEvaluate) {
		this.principalsEvaluate = principalsEvaluate;
	}

	@Column(name = "PRINCIPALS_NAME", length = 20)
	public String getPrincipalsName() {
		return this.principalsName;
	}

	public void setPrincipalsName(String principalsName) {
		this.principalsName = principalsName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PRINCIPALS_DATE", length = 7)
	public Date getPrincipalsDate() {
		return this.principalsDate;
	}

	public void setPrincipalsDate(Date principalsDate) {
		this.principalsDate = principalsDate;
	}

	@Column(name = "DEP_EVALUATE", length = 100)
	public String getDepEvaluate() {
		return this.depEvaluate;
	}

	public void setDepEvaluate(String depEvaluate) {
		this.depEvaluate = depEvaluate;
	}

	@Column(name = "DEP_NAME", length = 20)
	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DEP_DATE", length = 7)
	public Date getDepDate() {
		return this.depDate;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}

	@Column(name = "HR_EVALUATE", length = 100)
	public String getHrEvaluate() {
		return this.hrEvaluate;
	}

	public void setHrEvaluate(String hrEvaluate) {
		this.hrEvaluate = hrEvaluate;
	}

	@Column(name = "HR", length = 20)
	public String getHr() {
		return this.hr;
	}

	public void setHr(String hr) {
		this.hr = hr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HR_DATE", length = 7)
	public Date getHrDate() {
		return this.hrDate;
	}

	public void setHrDate(Date hrDate) {
		this.hrDate = hrDate;
	}

	@Column(name = "LEAD_EVALUATE", length = 100)
	public String getLeadEvaluate() {
		return this.leadEvaluate;
	}

	public void setLeadEvaluate(String leadEvaluate) {
		this.leadEvaluate = leadEvaluate;
	}

	@Column(name = "LEADER", length = 20)
	public String getLeader() {
		return this.leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LEAD_DATE", length = 7)
	public Date getLeadDate() {
		return this.leadDate;
	}

	public void setLeadDate(Date leadDate) {
		this.leadDate = leadDate;
	}

	@Column(name = "FILE_NAME", length = 50)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "URL", length = 50)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "REMARK", length = 50)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "THE_DATE", length = 7)
	public Date getTheDate() {
		return theDate;
	}

	public void setTheDate(Date theDate) {
		this.theDate = theDate;
	}

	@Column(name = "EMTYPE")
	public Integer getEmtype() {
		return emtype;
	}

	public void setEmtype(Integer emtype) {
		this.emtype = emtype;
	}

}