package com.lingnet.hcm.entity.check;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 
 * @ClassName: CkYearDay 
 * @Description: 全年白班班制实体类 
 * @author wangqiang
 * @date 2017年4月14日 上午10:21:06 
 *
 */
@Entity
@Table(name = "CK_YEAR_DAY")
public class CkYearDay extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 3454751343786328837L;
	
	private String yearCalendar;//年份
	private String monthCalendar;//月份
	private Integer totalDays;//月份天数
	private String day1;//1日
	private String day2;//2日
	private String day3;//...
	private String day4;
	private String day5;
	private String day6;
	private String day7;
	private String day8;
	private String day9;
	private String day10;
	private String day11;
	private String day12;
	private String day13;
	private String day14;
	private String day15;
	private String day16;
	private String day17;
	private String day18;
	private String day19;
	private String day20;
	private String day21;
	private String day22;
	private String day23;
	private String day24;
	private String day25;
	private String day26;
	private String day27;
	private String day28;
	private String day29;
	private String day30;
	private String day31;
	private Integer isDelete;//是否删除（0 否，1 是）
	private String field1;
	private String field2;

	@Column(name = "YEAR_CALENDAR", length = 32)
	public String getYearCalendar() {
		return this.yearCalendar;
	}

	public void setYearCalendar(String yearCalendar) {
		this.yearCalendar = yearCalendar;
	}

	@Column(name = "MONTH_CALENDAR", length = 32)
	public String getMonthCalendar() {
		return this.monthCalendar;
	}

	public void setMonthCalendar(String monthCalendar) {
		this.monthCalendar = monthCalendar;
	}

	@Column(name = "DAY1", length = 10)
	public String getDay1() {
		return this.day1;
	}

	public void setDay1(String day1) {
		this.day1 = day1;
	}

	@Column(name = "DAY2", length = 10)
	public String getDay2() {
		return this.day2;
	}

	public void setDay2(String day2) {
		this.day2 = day2;
	}

	@Column(name = "DAY3", length = 10)
	public String getDay3() {
		return this.day3;
	}

	public void setDay3(String day3) {
		this.day3 = day3;
	}

	@Column(name = "DAY4", length = 10)
	public String getDay4() {
		return this.day4;
	}

	public void setDay4(String day4) {
		this.day4 = day4;
	}

	@Column(name = "DAY5", length = 10)
	public String getDay5() {
		return this.day5;
	}

	public void setDay5(String day5) {
		this.day5 = day5;
	}

	@Column(name = "DAY6", length = 10)
	public String getDay6() {
		return this.day6;
	}

	public void setDay6(String day6) {
		this.day6 = day6;
	}

	@Column(name = "DAY7", length = 10)
	public String getDay7() {
		return this.day7;
	}

	public void setDay7(String day7) {
		this.day7 = day7;
	}

	@Column(name = "DAY8", length = 10)
	public String getDay8() {
		return this.day8;
	}

	public void setDay8(String day8) {
		this.day8 = day8;
	}

	@Column(name = "DAY9", length = 10)
	public String getDay9() {
		return this.day9;
	}

	public void setDay9(String day9) {
		this.day9 = day9;
	}

	@Column(name = "DAY10", length = 10)
	public String getDay10() {
		return this.day10;
	}

	public void setDay10(String day10) {
		this.day10 = day10;
	}

	@Column(name = "DAY11", length = 10)
	public String getDay11() {
		return this.day11;
	}

	public void setDay11(String day11) {
		this.day11 = day11;
	}

	@Column(name = "DAY12", length = 10)
	public String getDay12() {
		return this.day12;
	}

	public void setDay12(String day12) {
		this.day12 = day12;
	}

	@Column(name = "DAY13", length = 10)
	public String getDay13() {
		return this.day13;
	}

	public void setDay13(String day13) {
		this.day13 = day13;
	}

	@Column(name = "DAY14", length = 10)
	public String getDay14() {
		return this.day14;
	}

	public void setDay14(String day14) {
		this.day14 = day14;
	}

	@Column(name = "DAY15", length = 10)
	public String getDay15() {
		return this.day15;
	}

	public void setDay15(String day15) {
		this.day15 = day15;
	}

	@Column(name = "DAY16", length = 10)
	public String getDay16() {
		return this.day16;
	}

	public void setDay16(String day16) {
		this.day16 = day16;
	}

	@Column(name = "DAY17", length = 10)
	public String getDay17() {
		return this.day17;
	}

	public void setDay17(String day17) {
		this.day17 = day17;
	}

	@Column(name = "DAY18", length = 10)
	public String getDay18() {
		return this.day18;
	}

	public void setDay18(String day18) {
		this.day18 = day18;
	}

	@Column(name = "DAY19", length = 10)
	public String getDay19() {
		return this.day19;
	}

	public void setDay19(String day19) {
		this.day19 = day19;
	}

	@Column(name = "DAY20", length = 10)
	public String getDay20() {
		return this.day20;
	}

	public void setDay20(String day20) {
		this.day20 = day20;
	}

	@Column(name = "DAY21", length = 10)
	public String getDay21() {
		return this.day21;
	}

	public void setDay21(String day21) {
		this.day21 = day21;
	}

	@Column(name = "DAY22", length = 10)
	public String getDay22() {
		return this.day22;
	}

	public void setDay22(String day22) {
		this.day22 = day22;
	}

	@Column(name = "DAY23", length = 10)
	public String getDay23() {
		return this.day23;
	}

	public void setDay23(String day23) {
		this.day23 = day23;
	}

	@Column(name = "DAY24", length = 10)
	public String getDay24() {
		return this.day24;
	}

	public void setDay24(String day24) {
		this.day24 = day24;
	}

	@Column(name = "DAY25", length = 10)
	public String getDay25() {
		return this.day25;
	}

	public void setDay25(String day25) {
		this.day25 = day25;
	}

	@Column(name = "DAY26", length = 10)
	public String getDay26() {
		return this.day26;
	}

	public void setDay26(String day26) {
		this.day26 = day26;
	}

	@Column(name = "DAY27", length = 10)
	public String getDay27() {
		return this.day27;
	}

	public void setDay27(String day27) {
		this.day27 = day27;
	}

	@Column(name = "DAY28", length = 10)
	public String getDay28() {
		return this.day28;
	}

	public void setDay28(String day28) {
		this.day28 = day28;
	}

	@Column(name = "DAY29", length = 10)
	public String getDay29() {
		return this.day29;
	}

	public void setDay29(String day29) {
		this.day29 = day29;
	}

	@Column(name = "DAY30", length = 10)
	public String getDay30() {
		return this.day30;
	}

	public void setDay30(String day30) {
		this.day30 = day30;
	}

	@Column(name = "DAY31", length = 10)
	public String getDay31() {
		return this.day31;
	}

	public void setDay31(String day31) {
		this.day31 = day31;
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
	
	@Column(name = "TOTAL_DAYS")
	public Integer getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}

}