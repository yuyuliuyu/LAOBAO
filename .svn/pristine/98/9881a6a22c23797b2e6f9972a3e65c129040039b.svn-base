package com.lingnet.qxgl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * QxUseDep entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "QX_USE_DEP")
public class QxUseDep extends BaseEntity implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // Fields

    private String depId;
    private String userId;

    // Constructors

    /** default constructor */
    public QxUseDep() {
    }


    /** full constructor */
    public QxUseDep( String depId, String userId, Date createdate,
            Date modifydate) {
        this.depId = depId;
        this.userId = userId;
    }


    @Column(name = "DEP_ID", length = 32)
    public String getDepId() {
        return this.depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Column(name = "USER_ID", length = 32)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

  

}