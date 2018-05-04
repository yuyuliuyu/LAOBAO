package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * Award entity. 
 * @author MyEclipse Persistence Tools
 * 员工奖励
 */
@Entity
@Table(name = "JC_AWARD")
public class Award extends BaseEntity implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -2397989839374219295L;
	
	private String jobNumber;
	private String awardName;//奖励名称
	private Date awarddate;//获奖时间
	private String approveNum;//批准文号
	private String approveOffice;//批准单位
	private Integer awardClass;//奖励种类
	private Integer awardType;//奖励方式
	private Integer awardLevel;//奖励级别
	private String awardCause;//奖励原因
	private String awardStep;//奖励措施
	private String awardAmount;//奖励金额
	private String fileNum;//文件编号
	private String fileName;//
	private String url;//
	private String remark;//
	private Integer isPractice;//是不是实习生
	private String personId;

	//*********************************************//
	@Column(name = "JOB_NUMBER", length = 32)
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "AWARD_NAME", length = 50)
	public String getAwardName() {
		return this.awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AWARDDATE", length = 7)
	public Date getAwarddate() {
		return this.awarddate;
	}

	public void setAwarddate(Date awarddate) {
		this.awarddate = awarddate;
	}

	@Column(name = "APPROVE_NUM", length = 32)
	public String getApproveNum() {
		return this.approveNum;
	}

	public void setApproveNum(String approveNum) {
		this.approveNum = approveNum;
	}

	@Column(name = "APPROVE_OFFICE", length = 50)
	public String getApproveOffice() {
		return this.approveOffice;
	}

	public void setApproveOffice(String approveOffice) {
		this.approveOffice = approveOffice;
	}

	@Column(name = "AWARD_CLASS", precision = 1, scale = 0)
	public Integer getAwardClass() {
		return this.awardClass;
	}

	public void setAwardClass(Integer awardClass) {
		this.awardClass = awardClass;
	}

	@Column(name = "AWARD_TYPE", precision = 1, scale = 0)
	public Integer getAwardType() {
		return this.awardType;
	}

	public void setAwardType(Integer awardType) {
		this.awardType = awardType;
	}

	@Column(name = "AWARD_LEVEL", precision = 1, scale = 0)
	public Integer getAwardLevel() {
		return this.awardLevel;
	}

	public void setAwardLevel(Integer awardLevel) {
		this.awardLevel = awardLevel;
	}

	@Column(name = "AWARD_CAUSE", length = 100)
	public String getAwardCause() {
		return this.awardCause;
	}

	public void setAwardCause(String awardCause) {
		this.awardCause = awardCause;
	}

	@Column(name = "AWARD_STEP", length = 50)
	public String getAwardStep() {
		return this.awardStep;
	}

	public void setAwardStep(String awardStep) {
		this.awardStep = awardStep;
	}

	@Column(name = "AWARD_AMOUNT", length = 40)
	public String getAwardAmount() {
		return this.awardAmount;
	}

	public void setAwardAmount(String awardAmount) {
		this.awardAmount = awardAmount;
	}

	@Column(name = "FILE_NUM", length = 32)
	public String getFileNum() {
		return this.fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
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

	@Column(name = "IS_PRACTICE")
	public Integer getIsPractice() {
		return isPractice;
	}

	public void setIsPractice(Integer isPractice) {
		this.isPractice = isPractice;
	}

	@Column(name = "PERSON_ID")
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

}