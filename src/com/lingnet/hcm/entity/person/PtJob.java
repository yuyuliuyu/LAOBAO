package com.lingnet.hcm.entity.person;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * 人员岗位兼职实体类
 */
@Entity
@Table(name = "JC_PT_JOB")
public class PtJob extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8471618455627786540L;
	
	private String personId;//职工号
	
	private String firm;//兼职公司
    private String dep;//兼职部门
    private String standardPost;//标准岗位
	private String spePost;//具体岗位
	
	private String firmId;//单位ID
	private String depId;//部门ID
	private String standardPostId;//标准岗位id
	private String spePostId;//具体岗位id
	
	private Integer isHostPost;//是否是主要岗位（0 否，1 是） 
	private Date beginTime;
	private Date endTime;
	private Integer state;//状态---1兼职中，0结束兼职
	private Integer isDelete;//是否删除（0 否，1 是）
	private String bz;//备注
	private String fileId;//委派文件ID
	private String fileName;//委派文件名称
	private Integer isPractice;//是否是实习生 1是，0或者空不是
	private String endCase;//结束原因
	private Integer jobLevel;//职务级别

	@Column(name = "PERSON_ID")
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	@Column(name = "FIRM_ID")
	public String getFirmId() {
		return firmId;
	}

	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}

	@Column(name = "DEP_ID")
	public String getDepId() {
		return this.depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	@Column(name = "STANDARD_POST")
	public String getStandardPost() {
		return this.standardPost;
	}

	public void setStandardPost(String standardPost) {
		this.standardPost = standardPost;
	}

	@Column(name = "SPE_POST")
	public String getSpePost() {
		return this.spePost;
	}

	public void setSpePost(String spePost) {
		this.spePost = spePost;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BEGIN_TIME", length = 7)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_TIME", length = 7)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "IS_DELETE", precision = 1, scale = 0)
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	@Column(name = "IS_HOST_POST")
	public Integer getIsHostPost() {
		return isHostPost;
	}

	public void setIsHostPost(Integer isHostPost) {
		this.isHostPost = isHostPost;
	}

	@Column(name = "STATE")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "BZ")
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column(name = "FILEID")
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@Column(name = "FILENAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "IS_PRACTICE")
	public Integer getIsPractice() {
		return isPractice;
	}

	public void setIsPractice(Integer isPractice) {
		this.isPractice = isPractice;
	}

	@Column(name = "FIRM")

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	@Column(name = "DEP")
	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	@Column(name = "STANDARD_POST_ID")
	public String getStandardPostId() {
		return standardPostId;
	}

	public void setStandardPostId(String standardPostId) {
		this.standardPostId = standardPostId;
	}

	@Column(name = "SPE_POST_ID")
	public String getSpePostId() {
		return spePostId;
	}

	public void setSpePostId(String spePostId) {
		this.spePostId = spePostId;
	}

	@Column(name = "END_CASE")
	public String getEndCase() {
		return endCase;
	}

	public void setEndCase(String endCase) {
		this.endCase = endCase;
	}

	@Column(name = "JOB_LEVER")
	public Integer getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(Integer jobLevel) {
		this.jobLevel = jobLevel;
	}


	
	
}