package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * empWelfareId entity. 
 * @author MyEclipse Persistence Tools
 * 员工福利
 */
@Entity
@Table(name = "JC_EMP_WELFARE")
public class EmpWelfare extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -3655903272070887569L;
	private String jobNumber;
	private Integer welType;//福利类别
	private String welName;//福利名称
	private String welContent;//福利内容
	private Date begindate;//开始日期
	private Date enddate;//结束日期
	private String isend;//是否终止
	private String cost;//费用
	private String unitApproved;//批准单位
	private String approver;//批准人
	private String remark;//备注
	private Integer isPractice;//是否是实习生 1是，0或者空不是
	private String personId;

	@Column(name = "JOB_NUMBER", length = 20)
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "WEL_TYPE")
	public Integer getWelType() {
		return this.welType;
	}

	public void setWelType(Integer welType) {
		this.welType = welType;
	}

	@Column(name = "WEL_NAME")
	public String getWelName() {
		return this.welName;
	}

	public void setWelName(String welName) {
		this.welName = welName;
	}

	@Column(name = "WEL_CONTENT")
	public String getWelContent() {
		return this.welContent;
	}

	public void setWelContent(String welContent) {
		this.welContent = welContent;
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

	@Column(name = "ISEND", length = 10)
	public String getIsend() {
		return this.isend;
	}

	public void setIsend(String isend) {
		this.isend = isend;
	}

	@Column(name = "COST", length = 10)
	public String getCost() {
		return this.cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	@Column(name = "UNIT_APPROVED", length = 20)
	public String getUnitApproved() {
		return this.unitApproved;
	}

	public void setUnitApproved(String unitApproved) {
		this.unitApproved = unitApproved;
	}

	@Column(name = "APPROVER", length = 50)
	public String getApprover() {
		return this.approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
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