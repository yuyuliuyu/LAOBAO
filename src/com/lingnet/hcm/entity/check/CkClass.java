package com.lingnet.hcm.entity.check;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * @ClassName: CkClass 
 * @Description: 班组信息实体类 
 * @author wangqiang
 * @date 2017年3月22日 下午1:44:48 
 *
 */
@Entity
@Table(name = "CK_CLASS")
public class CkClass extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 5692746560342104607L;
	
	private String classNo;//班号
	private String className;//班组名称
	private String instituteId;//班制ID
	private Integer isDelete;//是否删除（0 否，1 是）
	private String field1;
	private String field2;
	private String userId;//创建者ID
	private String depId;//有效部门ID

	@Column(name = "CLASS_NO", length = 32)
	public String getClassNo() {
		return this.classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	@Column(name = "CLASS_NAME", length = 40)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "INSTITUTE_ID", length = 32)
	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
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
	
	@Column(name = "USER_ID", length = 32)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "DEP_ID")
	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}
}