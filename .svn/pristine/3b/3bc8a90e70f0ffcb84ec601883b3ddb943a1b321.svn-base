package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * JcStayOut entity. 
 * @author MyEclipse Persistence Tools
 * 驻外实体类
 */
@Entity
@Table(name = "JC_STAY_OUT")
public class StayOut extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 6065735812268817024L;
	// Fields
    private String jobNumber;
    private Integer outType;//驻外类型、
    private String theFirm;//外派公司
    private String theDep;//部门
    private String theSpapost;//标准岗位
    private String thePost;//具体岗位
    private String theDuty;//职务
    private String workContent;//工作内容简介
    private Date begindate;//开始时间
    private Date planenddate;//计划结束时间
    private Date enddate;//实际结束时间
    private Integer status;//状态、
    private String remark;//
    private String personId;

    // Property accessors
    @Column(name = "JOB_NUMBER", length = 32)
    public String getJobNumber() {
        return this.jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Column(name = "THE_FIRM", length = 50)
    public String getTheFirm() {
		return theFirm;
	}

	public void setTheFirm(String theFirm) {
		this.theFirm = theFirm;
	}

    @Column(name = "THE_DEP", length = 30)
    public String getTheDep() {
		return theDep;
	}

	public void setTheDep(String theDep) {
		this.theDep = theDep;
	}

    @Column(name = "THE_SPAPOST", length = 30)
    public String getTheSpapost() {
		return theSpapost;
	}

	public void setTheSpapost(String theSpapost) {
		this.theSpapost = theSpapost;
	}

    @Column(name = "THE_POST", length = 30)
    public String getThePost() {
		return thePost;
	}

	public void setThePost(String thePost) {
		this.thePost = thePost;
	}

    @Column(name = "THE_DUTY", length = 30)
    public String getTheDuty() {
		return theDuty;
	}

	public void setTheDuty(String theDuty) {
		this.theDuty = theDuty;
	}

    @Column(name = "WORK_CONTENT", length = 50)
	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}


    @Temporal(TemporalType.DATE)
    @Column(name = "BEGINDATE", length = 7)
    public Date getBegindate() {
        return this.begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "PLANENDDATE", length = 7)
    public Date getPlanenddate() {
        return this.planenddate;
    }

    public void setPlanenddate(Date planenddate) {
        this.planenddate = planenddate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ENDDATE", length = 7)
    public Date getEnddate() {
        return this.enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Column(name = "STATUS", precision = 2, scale = 0)
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "REMARK", length = 50)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "OUT_TYPE")
	public Integer getOutType() {
		return outType;
	}

	public void setOutType(Integer outType) {
		this.outType = outType;
	}

	@Column(name = "PERSON_ID")
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}



}