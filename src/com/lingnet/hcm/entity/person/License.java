package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * License entity. 
 * @author MyEclipse Persistence Tools
 * 证照管理
 */
@Entity
@Table(name = "JC_LICENSE")
public class License extends BaseEntity implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -3936714813813468277L;
	private String jobNumber;//
	private Integer licenseType;//证照类型
	private String licenseNum;//证照编号
	private Integer licenseStatus;//证件状态
	private Date begindate;//生效起始日期
	private Date enddate;//生效结束日期
	private String validity;//有效期
	private String loseDate;//距离失效天数
	private String office;//发证机关
	private Integer isRecheck;//是否复检
	private Date lastRecheckday;//上次复检日
	private Date nextRecheckday;//下次复检日
	private String recheckDays;//距离复检天数
	private Integer isBackpay;//是否回缴
	private Date backpayDate;//回缴日期
	private String tackOffice;//保管单位
	private String tackName;//保管人
	private Date tackDate;//保管日期
	private String deliverName;//转交人
	private Date deliverDate;//转交日期
	private String remark;//
	private Integer isPractice;//是否是实习生 1是，0或者空不是
	private String personId;//职工号
	private String url;//
	private String fileName;//
	private String fileNum;//
	private Integer theClass;//分类 0--技工信息，1--职称聘任，2--员工技能，3--复转，4--工伤
	private String theClassId;//分类id


	@Column(name = "JOB_NUMBER", length = 20)
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "LICENSE_TYPE", precision = 2, scale = 0)
	public Integer getLicenseType() {
		return this.licenseType;
	}

	public void setLicenseType(Integer licenseType) {
		this.licenseType = licenseType;
	}

	@Column(name = "LICENSE_NUM", length = 32)
	public String getLicenseNum() {
		return this.licenseNum;
	}

	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	@Column(name = "LICENSE_STATUS", precision = 2, scale = 0)
	public Integer getLicenseStatus() {
		return this.licenseStatus;
	}

	public void setLicenseStatus(Integer licenseStatus) {
		this.licenseStatus = licenseStatus;
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

	@Column(name = "VALIDITY", length = 10)
	public String getValidity() {
		return this.validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	@Column(name = "LOSE_DATE", length = 10)
	public String getLoseDate() {
		return this.loseDate;
	}

	public void setLoseDate(String loseDate) {
		this.loseDate = loseDate;
	}

	@Column(name = "OFFICE", length = 50)
	public String getOffice() {
		return this.office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	@Column(name = "IS_RECHECK", precision = 2, scale = 0)
	public Integer getIsRecheck() {
		return this.isRecheck;
	}

	public void setIsRecheck(Integer isRecheck) {
		this.isRecheck = isRecheck;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_RECHECKDAY", length = 7)
	public Date getLastRecheckday() {
		return this.lastRecheckday;
	}

	public void setLastRecheckday(Date lastRecheckday) {
		this.lastRecheckday = lastRecheckday;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "NEXT_RECHECKDAY", length = 7)
	public Date getNextRecheckday() {
		return this.nextRecheckday;
	}

	public void setNextRecheckday(Date nextRecheckday) {
		this.nextRecheckday = nextRecheckday;
	}

	@Column(name = "RECHECK_DAYS", length = 10)
	public String getRecheckDays() {
		return this.recheckDays;
	}

	public void setRecheckDays(String recheckDays) {
		this.recheckDays = recheckDays;
	}

	@Column(name = "IS_BACKPAY", precision = 2, scale = 0)
	public Integer getIsBackpay() {
		return this.isBackpay;
	}

	public void setIsBackpay(Integer isBackpay) {
		this.isBackpay = isBackpay;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BACKPAY_DATE", length = 7)
	public Date getBackpayDate() {
		return this.backpayDate;
	}

	public void setBackpayDate(Date backpayDate) {
		this.backpayDate = backpayDate;
	}

	@Column(name = "TACK_OFFICE", length = 50)
	public String getTackOffice() {
		return this.tackOffice;
	}

	public void setTackOffice(String tackOffice) {
		this.tackOffice = tackOffice;
	}

	@Column(name = "TACK_NAME", length = 50)
	public String getTackName() {
		return this.tackName;
	}

	public void setTackName(String tackName) {
		this.tackName = tackName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TACK_DATE", length = 7)
	public Date getTackDate() {
		return this.tackDate;
	}

	public void setTackDate(Date tackDate) {
		this.tackDate = tackDate;
	}

	@Column(name = "DELIVER_NAME", length = 50)
	public String getDeliverName() {
		return this.deliverName;
	}

	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DELIVER_DATE", length = 7)
	public Date getDeliverDate() {
		return this.deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	@Column(name = "REMARK", length = 100)
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

	@Column(name = "URL", length = 50)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_NUM")
	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	@Column(name = "THE_CLASS")
	public Integer getTheClass() {
		return theClass;
	}

	public void setTheClass(Integer theClass) {
		this.theClass = theClass;
	}

	@Column(name = "THE_CLASS_ID")
	public String getTheClassId() {
		return theClassId;
	}

	public void setTheClassId(String theClassId) {
		this.theClassId = theClassId;
	}

}