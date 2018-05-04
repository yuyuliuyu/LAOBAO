package com.lingnet.hcm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * PostDuty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "POST_DUTY")
public class PostDuty extends BaseEntity implements java.io.Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = -1531944727937008693L;
    // Fields

	private String postCode;//编码
	private String postName;//名称
	private String pid;//父id
	private Date applyDate;//生效日期
	private String compId;//所属公司
	private String description;//描述
	private Integer isDelete;//是否删除 0未删除 1删除

	// Constructors

	/** default constructor */
	public PostDuty() {
	}


	/** full constructor */
	public PostDuty(String id, Date createdate, Date modifydate,
			String postCode, String postName, String pid, Date applyDate,
			String compId, String description) {
		this.postCode = postCode;
		this.postName = postName;
		this.pid = pid;
		this.applyDate = applyDate;
		this.compId = compId;
		this.description = description;
	}

	// Property accessors

	@Column(name = "POST_CODE", length = 32)
	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Column(name = "POST_NAME", length = 32)
	public String getPostName() {
		return this.postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	@Column(name = "PID", length = 32)
	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APPLY_DATE", length = 7)
	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	@Column(name = "COMP_ID", length = 32)
	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "is_delete")
    public Integer getIsDelete() {
        return isDelete;
    }


    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}