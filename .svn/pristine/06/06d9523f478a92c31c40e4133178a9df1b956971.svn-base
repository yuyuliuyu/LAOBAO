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
 * JcTalk entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JC_TALK")
public class Talk extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -1367679550889123593L;
	// Fields
	private String jobNumber;//被访谈人
	private Byte talkType;//访谈类型
	private Date talkDate;//访谈时间
	private String jobNumber2;//访谈人
	private String talkMessage;//访谈信息
	private String remark;
	private String personId;

	@Column(name = "JOB_NUMBER", length = 32)
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "TALK_TYPE", precision = 2, scale = 0)
	public Byte getTalkType() {
		return this.talkType;
	}

	public void setTalkType(Byte talkType) {
		this.talkType = talkType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TALK_DATE", length = 7)
	public Date getTalkDate() {
		return this.talkDate;
	}

	public void setTalkDate(Date talkDate) {
		this.talkDate = talkDate;
	}

	@Column(name = "JOB_NUMBER2", length = 32)
	public String getJobNumber2() {
		return this.jobNumber2;
	}

	public void setJobNumber2(String jobNumber2) {
		this.jobNumber2 = jobNumber2;
	}

	@Column(name = "TALK_MESSAGE", length = 200)
	public String getTalkMessage() {
		return this.talkMessage;
	}

	public void setTalkMessage(String talkMessage) {
		this.talkMessage = talkMessage;
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


}