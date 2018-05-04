package com.lingnet.hcm.entity.check;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;
/**
 * 
 * @ClassName: CkAnnualLeave 
 * @Description: 员工年假休假记录实体类 
 * @author wangqiang
 * @date 2017年4月24日 下午5:15:02 
 *
 */
@Entity
@Table(name = "CK_ANNUAL_LEAVE_RECORD")
public class CkAnnualLeave extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1802198340125228881L;
	
	private String jobNumber;//员工号
	private Integer yearCalendar;//年份
	private Integer monthCalendar;//月份
	private Double days;//休假天数
	private Integer isDelete;//是否删除（0 否；1 是）
	private String field1;
	private String field2;

	@Column(name = "JOB_NUMBER", length = 10)
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "YEAR_CALENDAR", length = 4)
	public Integer getYearCalendar() {
		return yearCalendar;
	}

	public void setYearCalendar(Integer yearCalendar) {
		this.yearCalendar = yearCalendar;
	}
	
	@Column(name = "MONTH_CALENDAR", length = 2)
	public Integer getMonthCalendar() {
		return monthCalendar;
	}

	public void setMonthCalendar(Integer monthCalendar) {
		this.monthCalendar = monthCalendar;
	}
	
	@Column(name = "DAYS")
	public Double getDays() {
		return this.days;
	}
	
	public void setDays(Double days) {
		this.days = days;
	}

	@Column(name = "IS_DELETE")
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

}