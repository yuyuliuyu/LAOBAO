package com.lingnet.hcm.entity.check;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 流程审批表
 * @ClassName: CkOvertimeSub 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年4月14日 下午4:33:51 
 *
 */
@Entity
@Table(name = "CK_OVERTIME_SUB")
public class CkOvertimeSub extends BaseEntity implements java.io.Serializable { 
    private static final long serialVersionUID = 1L;
    private String pid;//申请记录的ID
    private String nodeId;//工作流节点ID
    private String auditId;//审核人职工号
    private String auditName;//审核人姓名
    private Date auditDate;//审核时间
    private Integer auditStatus;//审核状态（1 审核通过，2 审核不通过）
    private String opinion; //审核意见
    private Integer isDelete;//是否删除（0 否； 1 是）
    private String field1;
    private String field2;
    private String evectType;  //审核流程类型：1.出差 2.休假。3.加班   10.人事实习生考核

    // Constructors

    /** default constructor */
    public CkOvertimeSub() {
    } 

    @Column(name = "APPLY_ID", length = 32)
    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Column(name = "NODE_ID", length = 32)
    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Column(name = "AUDIT_ID", length = 32)
    public String getAuditId() {
        return this.auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    @Column(name = "AUDIT_NAME", length = 40)
    public String getAuditName() {
        return this.auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    @Column(name = "AUDIT_DATE")
    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    } 
    @Column(name = "AUDIT_STATUS")
    public Integer getAuditStatus() {
        return this.auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    @Column(name = "OPINION", length = 2000)
    public String getOpinion() {
        return this.opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    } 
    @Column(name = "IS_DELETE")
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

    @Column(name = "EVECT_TYPE")
    public String getEvectType() {
        return this.evectType;
    }

    public void setEvectType(String evectType) {
        this.evectType = evectType;
    }

}