package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;


/**
 * SkilledEmpId entity. 
 * @author MyEclipse Persistence Tools
 * 技工信息
 */
@Entity
@Table(name = "JC_SKILLED_EMP")
public class SkilledEmp extends BaseEntity implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 4493491675729353279L;
	private String jobNumber;//职工工号
	private Integer skillType;//技术工种
	private Integer skillGrade;//技术等级
	private String judgeOffice;//评审机关
	private String papersNum;//证书编号
	private String office;//发证机关
	private Date awarddate;//授予日期
	private String papersOffice;//证书工作单位
	private String grade;//等级年限
	private Integer highestLevel;//是否最高等级
	private String appellation;//聘任职称
	private Date begindate;///聘期开始
	private Integer getForm;//获得形式
	private Date enddate;//聘期结束
	private String times;//聘任次数
	private String remark;//备注
	private Integer isPractice;//是否是实习生 1是，0或者空不是
	private String personId;//员工id

	//*******************************************//
	@Column(name = "JOB_NUMBER", length = 32)
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "SKILL_TYPE", precision = 1, scale = 0)
	public Integer getSkillType() {
		return this.skillType;
	}

	public void setSkillType(Integer skillType) {
		this.skillType = skillType;
	}

	@Column(name = "SKILL_GRADE", precision = 1, scale = 0)
	public Integer getSkillGrade() {
		return this.skillGrade;
	}

	public void setSkillGrade(Integer skillGrade) {
		this.skillGrade = skillGrade;
	}

	@Column(name = "JUDGE_OFFICE", length = 30)
	public String getJudgeOffice() {
		return this.judgeOffice;
	}

	public void setJudgeOffice(String judgeOffice) {
		this.judgeOffice = judgeOffice;
	}

	@Column(name = "PAPERS_NUM", length = 30)
	public String getPapersNum() {
		return this.papersNum;
	}

	public void setPapersNum(String papersNum) {
		this.papersNum = papersNum;
	}

	@Column(name = "OFFICE", length = 30)
	public String getOffice() {
		return this.office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AWARDDATE", length = 7)
	public Date getAwarddate() {
		return this.awarddate;
	}

	public void setAwarddate(Date awarddate) {
		this.awarddate = awarddate;
	}

	@Column(name = "PAPERS_OFFICE", length = 30)
	public String getPapersOffice() {
		return this.papersOffice;
	}

	public void setPapersOffice(String papersOffice) {
		this.papersOffice = papersOffice;
	}

	@Column(name = "GRADE", length = 10)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "HIGHEST_LEVEL", precision = 1, scale = 0)
	public Integer getHighestLevel() {
		return this.highestLevel;
	}

	public void setHighestLevel(Integer highestLevel) {
		this.highestLevel = highestLevel;
	}

	@Column(name = "APPELLATION", length = 30)
	public String getAppellation() {
		return this.appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BEGINDATE", length = 7)
	public Date getBegindate() {
		return this.begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	@Column(name = "GET_FORM", precision = 1, scale = 0)
	public Integer getGetForm() {
		return this.getForm;
	}

	public void setGetForm(Integer getForm) {
		this.getForm = getForm;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE")
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "TIMES", length = 10)
	public String getTimes() {
		return this.times;
	}

	public void setTimes(String times) {
		this.times = times;
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