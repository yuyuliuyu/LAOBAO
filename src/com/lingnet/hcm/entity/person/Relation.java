package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * relation entity. 
 * @author MyEclipse Persistence Tools
 * 社会关系
 */
@Entity
@Table(name = "JC_RELATIONS")
public class Relation extends BaseEntity implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 4061459007247378811L;
	private String jobNumber;//工号
	private String name;//名字
	private String sex;//性别
	private String politicsStatus;//政治面貌
	private String relation;//与本人关系
	private Date borthDate;//出生日期
	private String call;//称谓
	private String nation;//民族
	private String workUnit;//工作单位
	private String duty;//职务
	private String remark;//备注
	private String status;//状态 0 在职/1上学/2死亡/3离异
	private Integer age;//年龄
	private Integer isPractice;//是否是实习生 1是，0或者空不是
	private String personId;//员工ID

	//*******************************************//
	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SEX", length = 1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "POLITICS_STATUS", length = 2)
	public String getPoliticsStatus() {
		return this.politicsStatus;
	}

	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}

	@Column(name = "RELATION", length = 10)
	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BORTH_DATE", length = 7)
	public Date getBorthDate() {
		return this.borthDate;
	}

	public void setBorthDate(Date borthDate) {
		this.borthDate = borthDate;
	}

	@Column(name = "CALL", length = 10)
	public String getCall() {
		return this.call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	@Column(name = "NATION", length = 10)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "WORK_UNIT", length = 50)
	public String getWorkUnit() {
		return this.workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	@Column(name = "REMARK", length = 50)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "DUTY", length = 10)
	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Column(name = "JOB_NUMBER")
	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "AGE")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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