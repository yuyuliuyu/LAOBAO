package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * Record entity. 
 * @author MyEclipse Persistence Tools
 * 电子档案
 */
@Entity
@Table(name = "JC_E_RECORD")
public class Record extends BaseEntity implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 855614890097088430L;
	
	private String jobNumber;//
	private Integer reType;//档案类型
	private String reName;//档案名称
	private String reFileNum;//档案文号
	private String reContent;//档案内容简介
	private String reYear;//档案年份
	private String reNum;//档案编号
	private String extendName;//扩展名
	private String fileSize;//奖励，惩罚存储ID
	private String remark;//备注
	private String createName;//创建姓名
	private String imgpath;//图片路径
	private String createNum;//创建工号
	private Integer isPractice;//是否是实习生 1是，0或者空不是
	private String personId;//职工号

	// Property accessors
	@Column(name = "JOB_NUMBER", length = 20)
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "RE_TYPE")
	public Integer getReType() {
		return this.reType;
	}

	public void setReType(Integer reType) {
		this.reType = reType;
	}

	@Column(name = "RE_NAME", length = 50)
	public String getReName() {
		return this.reName;
	}

	public void setReName(String reName) {
		this.reName = reName;
	}

	@Column(name = "RE_FILE_NUM", length = 32)
	public String getReFileNum() {
		return this.reFileNum;
	}

	public void setReFileNum(String reFileNum) {
		this.reFileNum = reFileNum;
	}

	@Column(name = "RE_CONTENT", length = 50)
	public String getReContent() {
		return this.reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	@Column(name = "RE_YEAR")
	public String getReYear() {
		return this.reYear;
	}

	public void setReYear(String reYear) {
		this.reYear = reYear;
	}

	@Column(name = "RE_NUM", length = 32)
	public String getReNum() {
		return this.reNum;
	}

	public void setReNum(String reNum) {
		this.reNum = reNum;
	}

	@Column(name = "EXTEND_NAME", length = 10)
	public String getExtendName() {
		return this.extendName;
	}

	public void setExtendName(String extendName) {
		this.extendName = extendName;
	}

	@Column(name = "FILE_SIZE")
	public String getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "REMARK", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CREATE_NAME", length = 50)
	public String getCreateName() {
		return this.createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	@Column(name = "CREATE_NUM", length = 20)
	public String getCreateNum() {
		return this.createNum;
	}

	public void setCreateNum(String createNum) {
		this.createNum = createNum;
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

	@Column(name = "URL", length = 100)
	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

}