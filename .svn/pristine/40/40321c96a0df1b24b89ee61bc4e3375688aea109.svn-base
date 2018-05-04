package com.lingnet.hcm.entity.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * JcAttention entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JC_ATTENTION")
public class Attention extends BaseEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = -6853288273035294007L;
	// Fields
	private String jobNumber;
	private Integer attentionType;//关注类型
	private Integer attentionStatus;//关注状态
	private String attentionDes;//描述
	private String remark;
	private String personId;


	@Column(name = "JOB_NUMBER", length = 20)
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "ATTENTION_TYPE", precision = 1, scale = 0)
	public Integer getAttentionType() {
		return this.attentionType;
	}

	public void setAttentionType(Integer attentionType) {
		this.attentionType = attentionType;
	}

	@Column(name = "ATTENTION_DES", length = 50)
	public String getAttentionDes() {
		return this.attentionDes;
	}

	public void setAttentionDes(String attentionDes) {
		this.attentionDes = attentionDes;
	}

	@Column(name = "REMARK", length = 50)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ATTENTION_STATUS")
	public Integer getAttentionStatus() {
		return attentionStatus;
	}

	
	public void setAttentionStatus(Integer attentionStatus) {
		this.attentionStatus = attentionStatus;
	}

	@Column(name = "PERSON_ID")
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}


}