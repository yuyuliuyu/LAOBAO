package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * JcAbroad entity. 
 * @author MyEclipse Persistence Tools
 * 出国管理
 */
@Entity
@Table(name = "JC_ABROAD")
public class Abroad extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 2350661053740682023L;
	// Fields
    private String jobNumber;
    private String goalPiace;//目的地
    private Integer abroadGoal;//出行目的
    private Integer abroadCase;//出国缘由----0因公出国，1因私出国
    private Date begindate;//开始时间
    private Date planenddate;//计划结束时间
    private Date enddate;//实际结束时间
    private Integer idType;//证件类型
    private String idNum;//证件号码
    private Integer status;//状态
    private String remark;
    private String personId;

    // Property accessors

    @Column(name = "JOB_NUMBER", length = 20)
    public String getJobNumber() {
        return this.jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Column(name = "GOAL_PIACE", length = 50)
    public String getGoalPiace() {
        return this.goalPiace;
    }

    public void setGoalPiace(String goalPiace) {
        this.goalPiace = goalPiace;
    }

    @Column(name = "ABROAD_GOAL", precision = 2, scale = 0)
    public Integer getAbroadGoal() {
        return this.abroadGoal;
    }

    public void setAbroadGoal(Integer abroadGoal) {
        this.abroadGoal = abroadGoal;
    }

    @Column(name = "ABROAD_CASE", precision = 1, scale = 0)
    public Integer getAbroadCase() {
        return this.abroadCase;
    }

    public void setAbroadCase(Integer abroadCase) {
        this.abroadCase = abroadCase;
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

    @Column(name = "ID_TYPE", precision = 2, scale = 0)
    public Integer getIdType() {
        return this.idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    @Column(name = "ID_NUM", length = 50)
    public String getIdNum() {
        return this.idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Column(name = "STATUS", precision = 1, scale = 0)
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

    @Column(name = "PERSON_ID")
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

}