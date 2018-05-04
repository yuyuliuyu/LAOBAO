package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * JcEmpDimission entity. 
 * @author MyEclipse Persistence Tools
 * 离职实体类
 */
@Entity
@Table(name = "JC_EMP_DIMISSION")
public class EmpDimission extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -2609026062563157251L;
    private String jobNumber;//
    private Integer leaveType;///离职类型
    private Date leaveDate;//离职时间
    private String direction;//离职去向
    private String fileNumber;//离职文号
    private String approver;//批准人
    private Integer ishitlist;//是否加入黑名单
    private Integer iscompensate;//是否有补偿金
    private String cost;//补偿月数
    private Integer isdtzj;//是否有代通知金
    private String remark;//
    private String personId;//
    private Date raiseLeaveDate;//提出离职时间
    private Date approveLeaveDate;//批准离职时间
    private Integer leaveCause;//离职原因
    private String speLeaveCause;//离职具体原因

    // Property accessors
    @Column(name = "JOB_NUMBER", length = 20)
    public String getJobNumber() {
        return this.jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Column(name = "LEAVE_TYPE", precision = 1, scale = 0)
    public Integer getLeaveType() {
        return this.leaveType;
    }

    public void setLeaveType(Integer leaveType) {
        this.leaveType = leaveType;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "LEAVE_DATE", length = 7)
    public Date getLeaveDate() {
        return this.leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    @Column(name = "DIRECTION", length = 20)
    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Column(name = "FILE_NUMBER", length = 50)
    public String getFileNumber() {
        return this.fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    @Column(name = "APPROVER", length = 20)
    public String getApprover() {
        return this.approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    @Column(name = "ISHITLIST", precision = 1, scale = 0)
    public Integer getIshitlist() {
        return this.ishitlist;
    }

    public void setIshitlist(Integer ishitlist) {
        this.ishitlist = ishitlist;
    }

    @Column(name = "ISCOMPENSATE", precision = 1, scale = 0)
    public Integer getIscompensate() {
        return this.iscompensate;
    }

    public void setIscompensate(Integer iscompensate) {
        this.iscompensate = iscompensate;
    }

    @Column(name = "COST", length = 10)
    public String getCost() {
        return this.cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Column(name = "ISDTZJ", precision = 1, scale = 0)
    public Integer getIsdtzj() {
        return this.isdtzj;
    }

    public void setIsdtzj(Integer isdtzj) {
        this.isdtzj = isdtzj;
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

	@Column(name = "RAISE_LEAVE_DATE")
	public Date getRaiseLeaveDate() {
		return raiseLeaveDate;
	}

	public void setRaiseLeaveDate(Date raiseLeaveDate) {
		this.raiseLeaveDate = raiseLeaveDate;
	}

	@Column(name = "APPROVEL_LEAVE_DATE")
	public Date getApproveLeaveDate() {
		return approveLeaveDate;
	}

	public void setApproveLeaveDate(Date approveLeaveDate) {
		this.approveLeaveDate = approveLeaveDate;
	}

	@Column(name = "LEAVE_CAUSE")
	public Integer getLeaveCause() {
		return leaveCause;
	}

	public void setLeaveCause(Integer leaveCause) {
		this.leaveCause = leaveCause;
	}

	@Column(name = "SPE_LEAVE_CAUSE")
	public String getSpeLeaveCause() {
		return speLeaveCause;
	}

	public void setSpeLeaveCause(String speLeaveCause) {
		this.speLeaveCause = speLeaveCause;
	}

}