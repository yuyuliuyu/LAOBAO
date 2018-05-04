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
 * 工伤实体类
 */
@Entity
@Table(name = "JC_INDUSTRIAL_INJURY")
public class Injury extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 3081677264920607551L;
	private String jobNumber;
	private Integer onPost;//是否在岗
	private String injuryUnit;//发生工伤时所属单位
	private String injuryDepartment;//所属部门
	private String injuryPost;//所属岗位
	private Date injuryDate;//工伤日期
	private String injuryProcess;//工伤事故经过
	private String injuryPart;//工伤部位
	private String injuryCause;//工伤原因
	private Integer injuryType;//工伤种类
	private Integer manageStatus;//处理状态
	
	private Date stopjobBegindate;//停工留薪开始日期
	private Date stopjobEnddate;//停工留薪结束日期
	private Date stopjobExtenddate;//停工留薪延长至日期
	private Integer injuryLever;//伤残等级
	private Date injuryAffirmdate;//工伤认定日期
	private String injuryAgainRecord;//复发记录
	private String causEffect;//因果关系鉴定
	
	private Date healBegindate;//医疗开始日期
	private Date healEnddate;//医疗结束日期
	private String healCosts;//医疗费用
	private String injurySubsidy;//伤残补助
	private Date authDate;//工伤鉴定日期
	private String healSubsidy;//医疗补助金
	private String healSubsidySocial;//医疗补助金社保部分
	private String healSubsidyFirm;//医疗补助金公司部分
	private String employSubsidy;//就业补助金
	private String employSubsidySocial;//就业补助金社保部分
	private String employSubsidyFilm;//就业补助金公司部分
	private String remark;//
	private String cuteProcess;//处理过程
	private Integer isPractice;//是否是实习生 1是，0或者空不是
	private String personId;


	@Column(name = "JOB_NUMBER")
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "ON_POST", precision = 1, scale = 0)
	public Integer getOnPost() {
		return this.onPost;
	}

	public void setOnPost(Integer onPost) {
		this.onPost = onPost;
	}

	@Column(name = "INJURY_UNIT")
	public String getInjuryUnit() {
		return this.injuryUnit;
	}

	public void setInjuryUnit(String injuryUnit) {
		this.injuryUnit = injuryUnit;
	}

	@Column(name = "INJURY_DEPARTMENT")
	public String getInjuryDepartment() {
		return this.injuryDepartment;
	}

	public void setInjuryDepartment(String injuryDepartment) {
		this.injuryDepartment = injuryDepartment;
	}

	@Column(name = "INJURY_POST")
	public String getInjuryPost() {
		return this.injuryPost;
	}

	public void setInjuryPost(String injuryPost) {
		this.injuryPost = injuryPost;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INJURY_DATE", length = 7)
	public Date getInjuryDate() {
		return this.injuryDate;
	}

	public void setInjuryDate(Date injuryDate) {
		this.injuryDate = injuryDate;
	}

	@Column(name = "INJURY_PROCESS", length = 100)
	public String getInjuryProcess() {
		return this.injuryProcess;
	}

	public void setInjuryProcess(String injuryProcess) {
		this.injuryProcess = injuryProcess;
	}

	@Column(name = "INJURY_PART", length = 20)
	public String getInjuryPart() {
		return this.injuryPart;
	}

	public void setInjuryPart(String injuryPart) {
		this.injuryPart = injuryPart;
	}

	@Column(name = "INJURY_CAUSE", length = 40)
	public String getInjuryCause() {
		return this.injuryCause;
	}

	public void setInjuryCause(String injuryCause) {
		this.injuryCause = injuryCause;
	}

	@Column(name = "INJURY_TYPE", precision = 2, scale = 0)
	public Integer getInjuryType() {
		return this.injuryType;
	}

	public void setInjuryType(Integer injuryType) {
		this.injuryType = injuryType;
	}

	@Column(name = "MANAGE_STATUS", precision = 2, scale = 0)
	public Integer getManageStatus() {
		return this.manageStatus;
	}

	public void setManageStatus(Integer manageStatus) {
		this.manageStatus = manageStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HEAL_BEGINDATE", length = 7)
	public Date getHealBegindate() {
		return this.healBegindate;
	}

	public void setHealBegindate(Date healBegindate) {
		this.healBegindate = healBegindate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HEAL_ENDDATE", length = 7)
	public Date getHealEnddate() {
		return this.healEnddate;
	}

	public void setHealEnddate(Date healEnddate) {
		this.healEnddate = healEnddate;
	}

	@Column(name = "HEAL_COSTS")
	public String getHealCosts() {
		return this.healCosts;
	}

	public void setHealCosts(String healCosts) {
		this.healCosts = healCosts;
	}

	@Column(name = "INJURY_SUBSIDY")
	public String getInjurySubsidy() {
		return this.injurySubsidy;
	}

	public void setInjurySubsidy(String injurySubsidy) {
		this.injurySubsidy = injurySubsidy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AUTH_DATE", length = 7)
	public Date getAuthDate() {
		return this.authDate;
	}

	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}

	@Column(name = "HEAL_SUBSIDY")
	public String getHealSubsidy() {
		return this.healSubsidy;
	}

	public void setHealSubsidy(String healSubsidy) {
		this.healSubsidy = healSubsidy;
	}

	@Column(name = "HEAL_SUBSIDY_SOCIAL")
	public String getHealSubsidySocial() {
		return this.healSubsidySocial;
	}

	public void setHealSubsidySocial(String healSubsidySocial) {
		this.healSubsidySocial = healSubsidySocial;
	}

	@Column(name = "HEAL_SUBSIDY_FIRM")
	public String getHealSubsidyFirm() {
		return this.healSubsidyFirm;
	}

	public void setHealSubsidyFirm(String healSubsidyFirm) {
		this.healSubsidyFirm = healSubsidyFirm;
	}

	@Column(name = "EMPLOY_SUBSIDY")
	public String getEmploySubsidy() {
		return this.employSubsidy;
	}

	public void setEmploySubsidy(String employSubsidy) {
		this.employSubsidy = employSubsidy;
	}

	@Column(name = "EMPLOY_SUBSIDY_SOCIAL")
	public String getEmploySubsidySocial() {
		return this.employSubsidySocial;
	}

	public void setEmploySubsidySocial(String employSubsidySocial) {
		this.employSubsidySocial = employSubsidySocial;
	}

	@Column(name = "EMPLOY_SUBSIDY_FILM")
	public String getEmploySubsidyFilm() {
		return this.employSubsidyFilm;
	}

	public void setEmploySubsidyFilm(String employSubsidyFilm) {
		this.employSubsidyFilm = employSubsidyFilm;
	}

	@Column(name = "REMARK", length = 50)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CUTE_PROCESS", length = 50)
	public String getCuteProcess() {
		return cuteProcess;
	}

	public void setCuteProcess(String cuteProcess) {
		this.cuteProcess = cuteProcess;
	}
	
	@Column(name = "IS_PRACTICE")
	public Integer getIsPractice() {
		return isPractice;
	}

	public void setIsPractice(Integer isPractice) {
		this.isPractice = isPractice;
	}

	@Column(name = "STOPJOB_BEGIN_DATE")
	public Date getStopjobBegindate() {
		return stopjobBegindate;
	}

	public void setStopjobBegindate(Date stopjobBegindate) {
		this.stopjobBegindate = stopjobBegindate;
	}

	@Column(name = "STOPJOB_END_DATE")
	public Date getStopjobEnddate() {
		return stopjobEnddate;
	}

	public void setStopjobEnddate(Date stopjobEnddate) {
		this.stopjobEnddate = stopjobEnddate;
	}

	@Column(name = "STOPJOB_EXTEND_DATE")
	public Date getStopjobExtenddate() {
		return stopjobExtenddate;
	}

	public void setStopjobExtenddate(Date stopjobExtenddate) {
		this.stopjobExtenddate = stopjobExtenddate;
	}

	@Column(name = "INJURY_LEVER")
	public Integer getInjuryLever() {
		return injuryLever;
	}

	public void setInjuryLever(Integer injuryLever) {
		this.injuryLever = injuryLever;
	}

	@Column(name = "INJURY_AFFIRMDATE")
	public Date getInjuryAffirmdate() {
		return injuryAffirmdate;
	}

	public void setInjuryAffirmdate(Date injuryAffirmdate) {
		this.injuryAffirmdate = injuryAffirmdate;
	}

	@Column(name = "INJURY_AGAIN_RECORD")
	public String getInjuryAgainRecord() {
		return injuryAgainRecord;
	}

	public void setInjuryAgainRecord(String injuryAgainRecord) {
		this.injuryAgainRecord = injuryAgainRecord;
	}

	@Column(name = "CAUSE_EFFECT")
	public String getCausEffect() {
		return causEffect;
	}

	public void setCausEffect(String causEffect) {
		this.causEffect = causEffect;
	}

	@Column(name = "PERSON_ID")
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

}