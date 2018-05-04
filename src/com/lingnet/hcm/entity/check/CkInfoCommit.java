package com.lingnet.hcm.entity.check;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.lingnet.common.entity.BaseEntity;

/**
 * 
 * @ClassName: CkInfoCommit 
 * @Description: 员工考勤审核记录实体类
 * @author wangqiang
 * @date 2017年4月19日 上午11:12:06 
 *
 */
@Entity
@Table(name = "CK_INFO_COMMIT")
public class CkInfoCommit extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1909258418113944807L;
	
	private String empId;//上报人ID
	private String monthCalendar;//考勤年月份
	private Date commitDate;//上报时间
	private String depId;//上报部门ID
	private String personIds;//员工ID集合
	private Integer auditStatus;//审核状态:（0 待审核，1 审核通过，2 审核不通过）
	private String opinion;//审核意见
	private Integer isDelete;//是否删除：0 否，1 是
	private String field1;
	private String field2;

	@Column(name = "EMP_ID", length = 32)
	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Column(name = "MONTH_CALENDAR", length = 4)
	public String getMonthCalendar() {
		return this.monthCalendar;
	}

	public void setMonthCalendar(String monthCalendar) {
		this.monthCalendar = monthCalendar;
	}

	@Column(name = "COMMIT_DATE")
	@DateTimeFormat(style = "yyyy/MM/dd HH:mm:ss")
	public Date getCommitDate() {
		return this.commitDate;
	}

	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}

	@Column(name = "DEP_ID", length = 32)
	public String getDepId() {
		return this.depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	@Column(name = "AUDIT_STATUS", precision = 1, scale = 0)
	public Integer getAuditStatus() {
		return this.auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Column(name = "OPINION", length = 2000)
	public String getOpinion() {
		return this.opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
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
	
	@Column(name = "PERSON_IDS")
	public String getPersonIds() {
		return personIds;
	}
	
	public void setPersonIds(String personIds) {
		this.personIds = personIds;
	}
	
}