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
 * @ClassName: CkTimer 
 * @Description: 员工考勤时间实体类 
 * @author wangqiang
 * @date 2017年4月11日 下午3:59:41 
 *
 */
@Entity
@Table(name = "CK_TIME_RECORD")
public class CkTimer extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 3581721476959528739L;
	
	private String jobNumber;//职工号
	private String monthCalendar;//年月份
	private Date checkTime;//考勤时间
	private String sn;//设备号
	private Integer isDelete;//是否删除（0 否，1 是）
	private String field1;
	private String field2;

	@Column(name = "JOB_NUMBER", length = 10)
	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "MONTH_CALENDAR", length = 4)
	public String getMonthCalendar() {
		return this.monthCalendar;
	}

	public void setMonthCalendar(String monthCalendar) {
		this.monthCalendar = monthCalendar;
	}

	@Column(name = "CHECK_TIME")
	@DateTimeFormat(style = "yyyy/MM/dd HH:mm:ss")
	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "SN", length = 20)
	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
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

}