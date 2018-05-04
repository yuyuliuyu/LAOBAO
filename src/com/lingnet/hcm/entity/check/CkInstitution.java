package com.lingnet.hcm.entity.check;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 
 * @ClassName: CkInstitution 
 * @Description: 班制名称表 
 * @author wangqiang
 * @date 2017年3月31日 上午10:53:22 
 *
 */
@Entity
@Table(name = "CK_INSTITUTION")
public class CkInstitution extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 2555080865442367267L;
	
	private String instituteName;//班制名称
	private Double overtimeModulus;//加班系数
	private Double vacationModulus;//休假系数
	private Integer isDelete;
	private String field1;
	private String field2;

	@Column(name = "INSTITUTE_NAME", length = 40)
	public String getInstituteName() {
		return this.instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	@Column(name = "OVERTIME_MODULUS", precision = 2, scale = 1)
	public Double getOvertimeModulus() {
		return this.overtimeModulus;
	}

	public void setOvertimeModulus(Double overtimeModulus) {
		this.overtimeModulus = overtimeModulus;
	}

	@Column(name = "VACATION_MODULUS", precision = 2, scale = 1)
	public Double getVacationModulus() {
		return this.vacationModulus;
	}

	public void setVacationModulus(Double vacationModulus) {
		this.vacationModulus = vacationModulus;
	}

	@Column(name = "IS_DELETE")
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