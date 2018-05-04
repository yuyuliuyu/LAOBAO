package com.lingnet.hcm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * OrgChangeLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ORG_CHANGE_LOG")
public class OrgChangeLog extends BaseEntity implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6883088880353221716L;
    // Fields

    private String orgId;
    private String oldValue;
    private String newValue;

    // Constructors

    /** default constructor */
    public OrgChangeLog() {
    }

    

    /** full constructor */
    public OrgChangeLog(String id, Date createdate, Date modifydate,
            String orgId, String oldValue, String newValue) {
        this.orgId = orgId;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    // Property accessors
   
    @Column(name = "ORG_ID", length = 32)
    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Column(name = "OLD_VALUE")
    public String getOldValue() {
        return this.oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Column(name = "NEW_VALUE")
    public String getNewValue() {
        return this.newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

}