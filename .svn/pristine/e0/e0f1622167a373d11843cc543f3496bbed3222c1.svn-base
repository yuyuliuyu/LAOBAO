package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * health entity. 
 * @author MyEclipse Persistence Tools
 * 健康档案实体类
 */
@Entity
@Table(name = "JC_HEALTH")
public class Health extends BaseEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = -4353285825606174746L;
	
	private String jobNumber;
	private String height;//身高
	private Integer bloodType;//血型
	private String weight;//体重
	private Integer health;//健康状况
	private Integer checkType;//检查种类
	private Date checkDate;//检查日期
	private String checkResult;//检查结果<
	private Integer professionTaboo;//是否职业禁忌
	private String tabooContent;//职业禁忌内容
	private String remark;//
	private Integer isPractice;//是否是实习生 1是，0或者空不是
	private String personId;
	
	private Date beginDate;//病假开始时间
	private Date endDate;//病假结束时间
	private String content;//病假内容
	private String theEnd;//诊断结果
	private Integer isBigIllness;//是否大病
	private Integer isFatalIllness;//是否绝症
	private String theFactor;//危害因素
	private String theStep;//处置措施


	@Column(name = "JOB_NUMBER", length = 20)
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "HEIGHT")
	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@Column(name = "BLOOD_TYPE")
	public Integer getBloodType() {
		return this.bloodType;
	}

	public void setBloodType(Integer bloodType) {
		this.bloodType = bloodType;
	}

	@Column(name = "WEIGHT")
	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Column(name = "HEALTH")
	public Integer getHealth() {
		return this.health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	@Column(name = "CHECK_TYPE")
	public Integer getCheckType() {
		return this.checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHECK_DATE", length = 7)
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "CHECK_RESULT", length = 10)
	public String getCheckResult() {
		return this.checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	@Column(name = "PROFESSION_TABOO")
	public Integer getProfessionTaboo() {
		return this.professionTaboo;
	}

	public void setProfessionTaboo(Integer professionTaboo) {
		this.professionTaboo = professionTaboo;
	}

	@Column(name = "TABOO_CONTENT", length = 50)
	public String getTabooContent() {
		return this.tabooContent;
	}

	public void setTabooContent(String tabooContent) {
		this.tabooContent = tabooContent;
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

	@Column(name = "BEGIN_DATE")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "THE_END")
	public String getTheEnd() {
		return theEnd;
	}

	public void setTheEnd(String theEnd) {
		this.theEnd = theEnd;
	}

	@Column(name = "IS_BIG_ILLNESS")
	public Integer getIsBigIllness() {
		return isBigIllness;
	}

	public void setIsBigIllness(Integer isBigIllness) {
		this.isBigIllness = isBigIllness;
	}

	@Column(name = "IS_FATAL_ILLNESS")
	public Integer getIsFatalIllness() {
		return isFatalIllness;
	}

	public void setIsFatalIllness(Integer isFatalIllness) {
		this.isFatalIllness = isFatalIllness;
	}

	@Column(name = "THE_FACTOR")
	public String getTheFactor() {
		return theFactor;
	}

	public void setTheFactor(String theFactor) {
		this.theFactor = theFactor;
	}

	@Column(name = "THE_STEP")
	public String getTheStep() {
		return theStep;
	}

	public void setTheStep(String theStep) {
		this.theStep = theStep;
	}

}