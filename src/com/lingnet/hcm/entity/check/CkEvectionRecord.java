package com.lingnet.hcm.entity.check;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.lingnet.common.entity.BaseEntity;

/**
 * 
 * @ClassName: CkEvectionRecord 
 * @Description: 出差申请记录实体类 
 * @author 马晓鹏
 * @date 2017年4月14日 下午4:34:07 
 *
 */
@Entity
@Table(name = "CK_EVECTION_RECORD")
public class CkEvectionRecord extends BaseEntity implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private String empId;//申请人ID(qxuser的ID)
    private String jobNumber;//职工号
    private String depId;//部门ID
    private String applyName;//申请人姓名
    private Date applyDate;//申请时间
    private String evectionType;//出差类型
    private String address;//出差地址
    private String vehicle;//出差工具
    private Date startDate;//出差开始日期
    private Date endDate;//出差结束日期
    private Double evectionDay;//出差天数
    private String processId;//流程ID
    private String remark;//备注
    private Integer auditStatus;//审核状态 (0 待审核，1 审核中，2 通过，3 审核不通过)
    private Integer isDelete;//是否删除（0 否；1 是）
    private String field1;//备用字段1
    private String field2;//备用字段2
    private String nodeId;//当前流程节点ID
    
    @Column(name = "EMP_ID")
    public String getEmpId() {
        return this.empId;
    }
    public void setEmpId(String empId) {
        this.empId = empId;
    }
    @Column(name = "DEP_ID")
    public String getDepId() {
        return this.depId;
    }
    public void setDepId(String depId) {
        this.depId = depId;
    }
    @Column(name = "APPLY_NAME")
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
    @Column(name = "EVECTION_TYPE")
    public String getEvectionType() {
        return this.evectionType;
    }
    public void setEvectionType(String evectionType) {
        this.evectionType = evectionType;
    }
    @Column(name = "ADDRESS")
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Column(name = "VEHICLE")
    public String getVehicle() {
        return this.vehicle;
    }
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
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
    @Column(name = "EVECTION_DAY")
    public Double getEvectionDay() {
        return this.evectionDay;
    }
    public void setEvectionDay(Double evectionDay) {
        this.evectionDay = evectionDay;
    }
    @Column(name = "PROCESS_ID")
    public String getProcessId() {
        return this.processId;
    }
    public void setProcessId(String processId) {
        this.processId = processId;
    }
    @Column(name = "REMARK")
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Column(name = "AUDIT_STATUS")
    public Integer getAuditStatus() {
        return this.auditStatus;
    }
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
    @Column(name = "IS_DELETE")
    public Integer getIsDelete() {
        return this.isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    @Column(name = "FIELD1")
    public String getField1() {
        return this.field1;
    }
    public void setField1(String field1) {
        this.field1 = field1;
    }
    @Column(name = "FIELD2")
    public String getField2() {
        return this.field2;
    }
    public void setField2(String field2) {
        this.field2 = field2;
    }
    @Column(name = "JOB_NUMBER")
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	@Column(name = "NODE_ID")
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

}