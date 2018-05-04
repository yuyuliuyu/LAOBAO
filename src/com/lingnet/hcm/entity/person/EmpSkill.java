package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * empSkill entity. 
 * @author MyEclipse Persistence Tools
 * 员工技能实体类
 */
@Entity
@Table(name = "JC_EMP_SKILL")
public class EmpSkill extends BaseEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 8900109450759183036L;
	private String skillDes;//技能描述
	private Integer papersClass;//证件类别
	private Integer papersType;//证照类型
	private String papersNum;//证照编号
	private Integer papersStatus;//证件状态
	private Date begindate;//生效起始日期
	private Date enddate;//生效结束日期
	private String validityPeriod;//有效期
	private String dates;//距离失效天数
	private String office;//发证机关
	private String remark;//备注
	private String jobNumber;//
	private Integer isPractice;//是否是实习生 1是，0或者空不是
	private String personId;
	

	// Property accessors

	@Column(name = "SKILL_DES", length = 50)
	public String getSkillDes() {
		return this.skillDes;
	}

	public void setSkillDes(String skillDes) {
		this.skillDes = skillDes;
	}

	@Column(name = "PAPERS_CLASS")
	public Integer getPapersClass() {
		return this.papersClass;
	}

	public void setPapersClass(Integer papersClass) {
		this.papersClass = papersClass;
	}

	@Column(name = "PAPERS_TYPE")
	public Integer getPapersType() {
		return this.papersType;
	}

	public void setPapersType(Integer papersType) {
		this.papersType = papersType;
	}

	@Column(name = "PAPERS_NUM", length = 32)
	public String getPapersNum() {
		return this.papersNum;
	}

	public void setPapersNum(String papersNum) {
		this.papersNum = papersNum;
	}

	@Column(name = "PAPERS_STATUS", precision = 1, scale = 0)
	public Integer getPapersStatus() {
		return this.papersStatus;
	}

	public void setPapersStatus(Integer papersStatus) {
		this.papersStatus = papersStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BEGINDATE", length = 7)
	public Date getBegindate() {
		return this.begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE", length = 7)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "VALIDITY_PERIOD", length = 10)
	public String getValidityPeriod() {
		return this.validityPeriod;
	}

	public void setValidityPeriod(String validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	@Column(name = "DATES", length = 10)
	public String getDates() {
		return this.dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	@Column(name = "OFFICE", length = 20)
	public String getOffice() {
		return this.office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	@Column(name = "REMARK", length = 50)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "JOB_NUMBER")
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
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