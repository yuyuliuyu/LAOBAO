package com.lingnet.hcm.entity.check;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 
 * @ClassName: CkStandard 
 * @Description: 考勤标准实体类 
 * @author wangqiang
 * @date 2017年4月5日 下午8:59:23 
 *
 */
@Entity
@Table(name = "CK_STANDARD")
public class CkStandard extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -3595140025964322346L;
	
	private String yearCalendar;//年月份
	private String monthDays;//月历天数
	private String workDays;//工作日
	private String restDays;//公休日
	private String holidayDays;//节假日
	private String festival1;//节一
	private String festival2;//节二
	private String festival3;//节三
	private String festival4;//节四
	private Integer isDelete;//是否删除：0 否，1 是
	private String field1;
	private String field2;

	@Column(name = "YEAR_CALENDAR", length = 4)
	public String getYearCalendar() {
		return this.yearCalendar;
	}

	public void setYearCalendar(String yearCalendar) {
		this.yearCalendar = yearCalendar;
	}

	@Column(name = "MONTH_DAYS", length = 2)
	public String getMonthDays() {
		return monthDays;
	}

	public void setMonthDays(String monthDays) {
		this.monthDays = monthDays;
	}
	
	@Column(name = "WORK_DAYS", length = 2)
	public String getWorkDays() {
		return workDays;
	}

	public void setWorkDays(String workDays) {
		this.workDays = workDays;
	}

	@Column(name = "REST_DAYS", length = 2)
	public String getRestDays() {
		return restDays;
	}

	public void setRestDays(String restDays) {
		this.restDays = restDays;
	}

	@Column(name = "HOLIDAY_DAYS", length = 2)
	public String getHolidayDays() {
		return holidayDays;
	}

	public void setHolidayDays(String holidayDays) {
		this.holidayDays = holidayDays;
	}
	
	@Column(name = "FESTIVAL1", length = 2)
	public String getFestival1() {
		return festival1;
	}

	public void setFestival1(String festival1) {
		this.festival1 = festival1;
	}
	
	@Column(name = "FESTIVAL2", length = 2)
	public String getFestival2() {
		return festival2;
	}

	public void setFestival2(String festival2) {
		this.festival2 = festival2;
	}
	
	@Column(name = "FESTIVAL3", length = 2)
	public String getFestival3() {
		return festival3;
	}

	public void setFestival3(String festival3) {
		this.festival3 = festival3;
	}
	
	@Column(name = "FESTIVAL4", length = 2)
	public String getFestival4() {
		return festival4;
	}

	public void setFestival4(String festival4) {
		this.festival4 = festival4;
	}
	
	@Column(name = "IS_DELETE", precision = 1, scale = 0)
	public Integer getIsDelete() {
		return isDelete;
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