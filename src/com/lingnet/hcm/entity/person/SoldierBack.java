package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * JcSoldierBack entity. 
 * @author MyEclipse Persistence Tools
 * 复转军人
 */
@Entity
@Table(name = "JC_SOLDIER_BACK")
public class SoldierBack extends BaseEntity implements java.io.Serializable {

    // Fields
	private static final long serialVersionUID = -8004741546004036001L;
    private String jobNumber;
    private Date insolDate;//入伍时间
    private Date outsolDate;//复转时间
    private Integer solType;//类别
    private Integer solLevel;//级别
    private String solDuty;//职务
    private String solRank;//军衔
    private String troops;//部队
    private String remark;//
    private String personId;

    // Property accessors
    @Column(name = "JOB_NUMBER", length = 20)
    public String getJobNumber() {
        return this.jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "INSOL_DATE", length = 7)
    public Date getInsolDate() {
        return this.insolDate;
    }

    public void setInsolDate(Date insolDate) {
        this.insolDate = insolDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "OUTSOL_DATE", length = 7)
    public Date getOutsolDate() {
        return this.outsolDate;
    }

    public void setOutsolDate(Date outsolDate) {
        this.outsolDate = outsolDate;
    }

    @Column(name = "SOL_TYPE", precision = 1, scale = 0)
    public Integer getSolType() {
        return this.solType;
    }

    public void setSolType(Integer solType) {
        this.solType = solType;
    }

    @Column(name = "SOL_LEVEL", precision = 1, scale = 0)
    public Integer getSolLevel() {
        return this.solLevel;
    }

    public void setSolLevel(Integer solLevel) {
        this.solLevel = solLevel;
    }

    @Column(name = "SOL_DUTY", length = 20)
    public String getSolDuty() {
        return this.solDuty;
    }

    public void setSolDuty(String solDuty) {
        this.solDuty = solDuty;
    }

    @Column(name = "SOL_RANK", length = 20)
    public String getSolRank() {
        return this.solRank;
    }

    public void setSolRank(String solRank) {
        this.solRank = solRank;
    }

    @Column(name = "TROOPS", length = 40)
    public String getTroops() {
        return this.troops;
    }

    public void setTroops(String troops) {
        this.troops = troops;
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