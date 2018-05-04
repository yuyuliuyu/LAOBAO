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
 * practiceCheck entity. 
 * @author MyEclipse Persistence Tools
 * 实习生考核信息
 */
@Entity
@Table(name = "JC_PRACTICE_CHECK")
public class PracticeCheck extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -915984481880808503L;
	
	private String empId;//实习生ID
	private String userId;//申请人ID(qxuser的ID)
    private String praticeNum;//实习编号
    private String applyName;//申请人姓名
	private Integer emtype;//类型------1实习生，2试岗期
	private String checkName;//考核名称
	private String score;//考核分数
	private Date begindate;//考核开始时间
	private Date enddate;//考核结束时间
	private String checkContent;//考核内容
	private String goalReach;//目标达成情况
	private String selfEvaluate;//自我评价
	private String principalsEvaluate;//负责人评价
	private String principalsName;//负责人名称
	private Date principalsDate;//负责人评价时间
	private String depEvaluate;//部门评价
	private String depName;//部门负责人名字
	private Date depDate;//评价时间
	private String hrEvaluate;//人力资源评价
	private String hr;//人力资源姓名
	private Date hrDate;//人力资源评定时间
	private String leadEvaluate;//领导意见
	private String leader;//领导名称
	private Date leadDate;//领导评价时间
	private String remark;//当前审批人
	private String fileName;//文件名称
	private String url;//
	
	private String processId;//流程ID
	private Integer auditStatus;//审核状态 (0 待审核，1 审核中，2 通过，3 审核不通过)
	private String nodeId;//当前流程节点ID

	// Property accessors
	@Column(name = "EMP_ID", length = 32)
	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Column(name = "CHECK_NAME", length = 50)
	public String getCheckName() {
		return this.checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	@Column(name = "SCORE")
	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
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
	@Column(name = "ENDDATE", length = 7)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "CHECK_CONTENT", length = 100)
	public String getCheckContent() {
		return this.checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	@Column(name = "GOAL_REACH", length = 100)
	public String getGoalReach() {
		return this.goalReach;
	}

	public void setGoalReach(String goalReach) {
		this.goalReach = goalReach;
	}

	@Column(name = "SELF_EVALUATE", length = 20)
	public String getSelfEvaluate() {
		return this.selfEvaluate;
	}

	public void setSelfEvaluate(String selfEvaluate) {
		this.selfEvaluate = selfEvaluate;
	}

	@Column(name = "PRINCIPALS_EVALUATE", length = 20)
	public String getPrincipalsEvaluate() {
		return this.principalsEvaluate;
	}

	public void setPrincipalsEvaluate(String principalsEvaluate) {
		this.principalsEvaluate = principalsEvaluate;
	}

	@Column(name = "PRINCIPALS_NAME", length = 20)
	public String getPrincipalsName() {
		return this.principalsName;
	}

	public void setPrincipalsName(String principalsName) {
		this.principalsName = principalsName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PRINCIPALS_DATE", length = 7)
	public Date getPrincipalsDate() {
		return this.principalsDate;
	}

	public void setPrincipalsDate(Date principalsDate) {
		this.principalsDate = principalsDate;
	}

	@Column(name = "DEP_EVALUATE", length = 100)
	public String getDepEvaluate() {
		return this.depEvaluate;
	}

	public void setDepEvaluate(String depEvaluate) {
		this.depEvaluate = depEvaluate;
	}

	@Column(name = "DEP_NAME", length = 20)
	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DEP_DATE", length = 7)
	public Date getDepDate() {
		return this.depDate;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}

	@Column(name = "HR_EVALUATE", length = 100)
	public String getHrEvaluate() {
		return this.hrEvaluate;
	}

	public void setHrEvaluate(String hrEvaluate) {
		this.hrEvaluate = hrEvaluate;
	}

	@Column(name = "HR", length = 20)
	public String getHr() {
		return this.hr;
	}

	public void setHr(String hr) {
		this.hr = hr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HR_DATE", length = 7)
	public Date getHrDate() {
		return this.hrDate;
	}

	public void setHrDate(Date hrDate) {
		this.hrDate = hrDate;
	}

	@Column(name = "LEAD_EVALUATE", length = 100)
	public String getLeadEvaluate() {
		return this.leadEvaluate;
	}

	public void setLeadEvaluate(String leadEvaluate) {
		this.leadEvaluate = leadEvaluate;
	}

	@Column(name = "LEADER", length = 20)
	public String getLeader() {
		return this.leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LEAD_DATE", length = 7)
	public Date getLeadDate() {
		return this.leadDate;
	}

	public void setLeadDate(Date leadDate) {
		this.leadDate = leadDate;
	}

	@Column(name = "REMARK", length = 50)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "FILE_NAME", length = 50)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "URL", length = 50)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "EMTYPE")
	public Integer getEmtype() {
		return emtype;
	}

	public void setEmtype(Integer emtype) {
		this.emtype = emtype;
	}

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "PRACTICE_NUM")
	public String getPraticeNum() {
		return praticeNum;
	}

	public void setPraticeNum(String praticeNum) {
		this.praticeNum = praticeNum;
	}

	@Column(name = "APPLY_NAME")
	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	
	@Column(name = "PROCESS_ID")
    public String getProcessId() {
        return this.processId;
    }
    public void setProcessId(String processId) {
        this.processId = processId;
    }
    @Column(name = "AUDIT_STATUS")
    public Integer getAuditStatus() {
        return this.auditStatus;
    }
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
    @Column(name = "NODE_ID")
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

}