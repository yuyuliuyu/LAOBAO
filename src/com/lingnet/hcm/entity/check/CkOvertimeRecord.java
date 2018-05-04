package com.lingnet.hcm.entity.check;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.lingnet.common.entity.BaseEntity;

/**
 * 加班申请
 * @ClassName: CkOvertimeRecord 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年4月14日 下午4:34:42 
 *
 */
@Entity
@Table(name = "CK_OVERTIME_RECORD")
public class CkOvertimeRecord extends BaseEntity implements java.io.Serializable {
	
    private static final long serialVersionUID = 1L; 
    
    private String empId;
    private String depId;
    private String applyName;
    private Date applyDate;
    private String evectionType;
    private Date startDate;
    private Date endDate;
    private Double overtimeDay;  //加班小时数
    private String processId;
    private String remark;
    private Integer auditStatus; //审核状态 (0 待审核，1 审核中，2 通过，3 审核不通过)
    private Integer isDelete;
    private String field1;
    private String field2;
    private String nodeId;//当前流程节点ID
    private String jobNumber;//职工号

    // Constructors

    /** default constructor */
    public CkOvertimeRecord() {
    }
 
    @Column(name = "EMP_ID", length = 32)
    public String getEmpId() {
        return this.empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Column(name = "DEP_ID", length = 32)
    public String getDepId() {
        return this.depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Column(name = "APPLY_NAME", length = 40)
    public String getApplyName() {
        return this.applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    @Column(name = "APPLY_DATE")
    @DateTimeFormat(style = "yyyy/MM/dd HH:mm:ss")
    public Date getApplyDate() {
        return this.applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    @Column(name = "EVECTION_TYPE", length = 2)
    public String getEvectionType() {
        return this.evectionType;
    }

    public void setEvectionType(String evectionType) {
        this.evectionType = evectionType;
    }

    @Column(name = "START_DATE")
    @DateTimeFormat(style = "yyyy/MM/dd HH:mm:ss")
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "END_DATE")
    @DateTimeFormat(style = "yyyy/MM/dd HH:mm:ss")
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "OVERTIME_DAY", precision = 2, scale = 1)
    public Double getOvertimeDay() {
        return this.overtimeDay;
    }

    public void setOvertimeDay(Double overtimeDay) {
        this.overtimeDay = overtimeDay;
    }

    @Column(name = "PROCESS_ID", length = 32)
    public String getProcessId() {
        return this.processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    @Column(name = "REMARK", length = 2000)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "AUDIT_STATUS", precision = 1, scale = 0)
    public Integer getAuditStatus() {
        return this.auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
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
    
    @Column(name = "NODE_ID")
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	@Column(name = "JOB_NUMBER")
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
}