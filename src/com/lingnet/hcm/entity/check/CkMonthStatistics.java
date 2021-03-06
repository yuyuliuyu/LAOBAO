package com.lingnet.hcm.entity.check;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 
 * @ClassName: CkMonthStatistics 
 * @Description: 员工月考勤统计实体类
 * @author wangqiang
 * @date 2017年5月15日 下午12:11:45 
 *
 */
@Entity
@Table(name = "CK_MONTH_STATISTICS")
public class CkMonthStatistics extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1199572338938831261L;
	
	private String jobNumber;//职工号
	private String yearMonth;//年月份
	private String name;//姓名
	private String postName;//具体岗位名称
	private String empType;//人员类别
	private String instituteName;//班制
	private String wageForm;//工资形式
	private String depId;//所属部门ID
	private Double outDuty;//出勤
	private Double centre;//中班
	private Double night;//夜班
	private Double vacationModulus;//节加
	private Double overtimeModulus;//休加
	private Double delayedModulus;//延时
	private Double piece;//计件
	private Double years;//年休
	private Double flip;//弹休
	private Double changes;//换休
	private Double thing;//事假
	private Double disease;//病假
	private Double free;//旷工
	private Double injury;//工伤
	private Double marry;//婚假
	private Double lost;//丧假
	private Double explore;//探亲
	private Double give;//产假
	private Double stay;//待岗
	private Double other;//其他
	private Double outWork;//出工
	private Double bigClass;//大班
	private Double smallClass;//小班
	private Double bigNight;//大夜
	private Double smallNight;//小夜
	private Double alls;//全
	private Double latency;//缓休
	private Double takeWork;//调休
	private Integer isDelete;//是否删除（0 否，1 是）
	private String field1;
	private String field2;
	private String remark;//备注
	private Double daytime;//白
	private Double offDuty;//下
	private Double rest;//休
	private Double take;//带
	private Double study;//学
	private Double very;//非
	private Double business;//公
	private Double evection;//差
	private Double team;//团
	private Double borrow;//借
	private Double retreat;//退
	private Double diction;//辞
	private Double leave;//离
	private Double totals;//合计
	private Double attendanceDays;//出勤天数
	private Double nightDays;//夜班
	private Double overseas;//外
	private String standardPostName;//标准岗位名称
	private String salaryPostName;//薪酬岗位名称
	
	public CkMonthStatistics() {   
		super();
		this.outDuty = 0.0;
		this.centre = 0.0;
		this.night = 0.0;
		this.vacationModulus = 0.0;
		this.overtimeModulus = 0.0;
		this.delayedModulus = 0.0;
		this.piece = 0.0;
		this.years = 0.0;
		this.flip = 0.0;
		this.changes = 0.0;
		this.thing = 0.0;
		this.disease = 0.0;
		this.free = 0.0;
		this.injury = 0.0;
		this.marry = 0.0;
		this.lost = 0.0;
		this.explore = 0.0;
		this.give = 0.0;
		this.stay = 0.0;
		this.other = 0.0;
		this.outWork = 0.0;
		this.bigClass = 0.0;
		this.smallClass = 0.0;
		this.bigNight = 0.0;
		this.smallNight = 0.0;
		this.alls = 0.0;
		this.latency = 0.0;
		this.takeWork = 0.0;
		this.daytime = 0.0;
		this.offDuty = 0.0;
		this.rest = 0.0;
		this.take = 0.0;
		this.study = 0.0;
		this.very = 0.0;
		this.business = 0.0;
		this.evection = 0.0;
		this.team = 0.0;
		this.borrow = 0.0;
		this.retreat = 0.0;
		this.diction = 0.0;
		this.leave = 0.0;
		this.totals = 0.0;
		this.attendanceDays = 0.0;
		this.nightDays = 0.0;
		this.overseas = 0.0;
	}

	@Column(name = "JOB_NUMBER", length = 10)
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "YEAR_MONTH", length = 4)
	public String getYearMonth() {
		return this.yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "OUT_DUTY", precision = 3, scale = 1)
	public Double getOutDuty() {
		return this.outDuty;
	}

	public void setOutDuty(Double outDuty) {
		this.outDuty = outDuty;
	}

	@Column(name = "CENTRE", precision = 3, scale = 1)
	public Double getCentre() {
		return this.centre;
	}

	public void setCentre(Double centre) {
		this.centre = centre;
	}

	@Column(name = "NIGHT", precision = 3, scale = 1)
	public Double getNight() {
		return this.night;
	}

	public void setNight(Double night) {
		this.night = night;
	}

	@Column(name = "OVERTIME_MODULUS", precision = 3, scale = 1)
	public Double getOvertimeModulus() {
		return this.overtimeModulus;
	}

	public void setOvertimeModulus(Double overtimeModulus) {
		this.overtimeModulus = overtimeModulus;
	}

	@Column(name = "VACATION_MODULUS", precision = 3, scale = 1)
	public Double getVacationModulus() {
		return this.vacationModulus;
	}

	public void setVacationModulus(Double vacationModulus) {
		this.vacationModulus = vacationModulus;
	}

	@Column(name = "DELAYED_MODULUS", precision = 3, scale = 1)
	public Double getDelayedModulus() {
		return this.delayedModulus;
	}

	public void setDelayedModulus(Double delayedModulus) {
		this.delayedModulus = delayedModulus;
	}

	@Column(name = "PIECE", precision = 3, scale = 1)
	public Double getPiece() {
		return this.piece;
	}

	public void setPiece(Double piece) {
		this.piece = piece;
	}

	@Column(name = "YEARS", precision = 3, scale = 1)
	public Double getYears() {
		return this.years;
	}

	public void setYears(Double years) {
		this.years = years;
	}

	@Column(name = "FLIP", precision = 3, scale = 1)
	public Double getFlip() {
		return this.flip;
	}

	public void setFlip(Double flip) {
		this.flip = flip;
	}

	@Column(name = "CHANGES", precision = 3, scale = 1)
	public Double getChanges() {
		return this.changes;
	}

	public void setChanges(Double changes) {
		this.changes = changes;
	}

	@Column(name = "THING", precision = 3, scale = 1)
	public Double getThing() {
		return this.thing;
	}

	public void setThing(Double thing) {
		this.thing = thing;
	}

	@Column(name = "DISEASE", precision = 3, scale = 1)
	public Double getDisease() {
		return this.disease;
	}

	public void setDisease(Double disease) {
		this.disease = disease;
	}

	@Column(name = "FREE", precision = 3, scale = 1)
	public Double getFree() {
		return this.free;
	}

	public void setFree(Double free) {
		this.free = free;
	}

	@Column(name = "INJURY", precision = 3, scale = 1)
	public Double getInjury() {
		return this.injury;
	}

	public void setInjury(Double injury) {
		this.injury = injury;
	}

	@Column(name = "MARRY", precision = 3, scale = 1)
	public Double getMarry() {
		return this.marry;
	}

	public void setMarry(Double marry) {
		this.marry = marry;
	}

	@Column(name = "LOST", precision = 3, scale = 1)
	public Double getLost() {
		return this.lost;
	}

	public void setLost(Double lost) {
		this.lost = lost;
	}

	@Column(name = "EXPLORE", precision = 3, scale = 1)
	public Double getExplore() {
		return this.explore;
	}

	public void setExplore(Double explore) {
		this.explore = explore;
	}

	@Column(name = "GIVE", precision = 3, scale = 1)
	public Double getGive() {
		return this.give;
	}

	public void setGive(Double give) {
		this.give = give;
	}

	@Column(name = "STAY", precision = 3, scale = 1)
	public Double getStay() {
		return this.stay;
	}

	public void setStay(Double stay) {
		this.stay = stay;
	}

	@Column(name = "OTHER", precision = 3, scale = 1)
	public Double getOther() {
		return this.other;
	}

	public void setOther(Double other) {
		this.other = other;
	}

	@Column(name = "OUT_WORK", precision = 3, scale = 1)
	public Double getOutWork() {
		return this.outWork;
	}

	public void setOutWork(Double outWork) {
		this.outWork = outWork;
	}

	@Column(name = "BIG_CLASS", precision = 3, scale = 1)
	public Double getBigClass() {
		return this.bigClass;
	}

	public void setBigClass(Double bigClass) {
		this.bigClass = bigClass;
	}

	@Column(name = "SMALL_CLASS", precision = 3, scale = 1)
	public Double getSmallClass() {
		return this.smallClass;
	}

	public void setSmallClass(Double smallClass) {
		this.smallClass = smallClass;
	}

	@Column(name = "BIG_NIGHT", precision = 3, scale = 1)
	public Double getBigNight() {
		return this.bigNight;
	}

	public void setBigNight(Double bigNight) {
		this.bigNight = bigNight;
	}

	@Column(name = "SMALL_NIGHT", precision = 3, scale = 1)
	public Double getSmallNight() {
		return this.smallNight;
	}

	public void setSmallNight(Double smallNight) {
		this.smallNight = smallNight;
	}

	@Column(name = "ALLS", precision = 3, scale = 1)
	public Double getAlls() {
		return this.alls;
	}

	public void setAlls(Double alls) {
		this.alls = alls;
	}

	@Column(name = "LATENCY", precision = 3, scale = 1)
	public Double getLatency() {
		return this.latency;
	}

	public void setLatency(Double latency) {
		this.latency = latency;
	}

	@Column(name = "TAKE_WORK", precision = 3, scale = 1)
	public Double getTakeWork() {
		return this.takeWork;
	}

	public void setTakeWork(Double takeWork) {
		this.takeWork = takeWork;
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

	@Column(name = "DEP_ID")
	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "POST_NAME")
	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	@Column(name = "EMP_TYPE")
	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	@Column(name = "INSTITUTE_NAME")
	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	@Column(name = "WAGE_FORM")
	public String getWageForm() {
		return wageForm;
	}

	public void setWageForm(String wageForm) {
		this.wageForm = wageForm;
	}

	@Column(name = "DAYTIME")
	public Double getDaytime() {
		return daytime;
	}

	public void setDaytime(Double daytime) {
		this.daytime = daytime;
	}

	@Column(name = "OFF_DUTY")
	public Double getOffDuty() {
		return offDuty;
	}

	public void setOffDuty(Double offDuty) {
		this.offDuty = offDuty;
	}

	@Column(name = "REST")
	public Double getRest() {
		return rest;
	}

	public void setRest(Double rest) {
		this.rest = rest;
	}

	@Column(name = "TAKE")
	public Double getTake() {
		return take;
	}

	public void setTake(Double take) {
		this.take = take;
	}

	@Column(name = "STUDY")
	public Double getStudy() {
		return study;
	}

	public void setStudy(Double study) {
		this.study = study;
	}

	@Column(name = "VERY")
	public Double getVery() {
		return very;
	}

	public void setVery(Double very) {
		this.very = very;
	}

	@Column(name = "BUSINESS")
	public Double getBusiness() {
		return business;
	}

	public void setBusiness(Double business) {
		this.business = business;
	}

	@Column(name = "EVECTION")
	public Double getEvection() {
		return evection;
	}

	public void setEvection(Double evection) {
		this.evection = evection;
	}

	@Column(name = "TEAM")
	public Double getTeam() {
		return team;
	}

	public void setTeam(Double team) {
		this.team = team;
	}

	@Column(name = "BORROW")
	public Double getBorrow() {
		return borrow;
	}

	public void setBorrow(Double borrow) {
		this.borrow = borrow;
	}

	@Column(name = "RETREAT")
	public Double getRetreat() {
		return retreat;
	}

	public void setRetreat(Double retreat) {
		this.retreat = retreat;
	}

	@Column(name = "DICTION")
	public Double getDiction() {
		return diction;
	}

	public void setDiction(Double diction) {
		this.diction = diction;
	}

	@Column(name = "LEAVE")
	public Double getLeave() {
		return leave;
	}

	public void setLeave(Double leave) {
		this.leave = leave;
	}

	@Column(name = "TOTALS")
	public Double getTotals() {
		return totals;
	}

	public void setTotals(Double totals) {
		this.totals = totals;
	}

	@Column(name = "ATTENDANCE_DAYS")
	public Double getAttendanceDays() {
		return attendanceDays;
	}

	public void setAttendanceDays(Double attendanceDays) {
		this.attendanceDays = attendanceDays;
	}

	@Column(name = "NIGHT_DAYS")
	public Double getNightDays() {
		return nightDays;
	}

	public void setNightDays(Double nightDays) {
		this.nightDays = nightDays;
	}

	@Column(name = "OVERSEAS")
	public Double getOverseas() {
		return overseas;
	}

	public void setOverseas(Double overseas) {
		this.overseas = overseas;
	}

	@Column(name = "STANDARD_POST_NAME")
	public String getStandardPostName() {
		return standardPostName;
	}

	public void setStandardPostName(String standardPostName) {
		this.standardPostName = standardPostName;
	}

	@Column(name = "SALARY_POST_NAME")
	public String getSalaryPostName() {
		return salaryPostName;
	}

	public void setSalaryPostName(String salaryPostName) {
		this.salaryPostName = salaryPostName;
	}

}