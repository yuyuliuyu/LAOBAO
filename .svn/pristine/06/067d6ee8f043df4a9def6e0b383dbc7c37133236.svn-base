package com.lingnet.qxgl.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * QxUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "QX_USER_ROLE")
public class QxUserRole implements java.io.Serializable {

    // Fields

    private static final long serialVersionUID = 1312973182192214643L;
    private QxUserRoleId id;
    private QxRoles qxRoles;
    private QxUsers qxUsers;

    // Constructors

    /** default constructor */
    public QxUserRole() {
    }

    /** minimal constructor */
    public QxUserRole(QxUserRoleId id, QxRoles qxRoles) {
        this.id = id;
        this.qxRoles = qxRoles;
    }

    /** full constructor */
    public QxUserRole(QxUserRoleId id, QxRoles qxRoles, QxUsers qxUsers) {
        this.id = id;
        this.qxRoles = qxRoles;
        this.qxUsers = qxUsers;
    }

    // Property accessors
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "USER_ID", column = @Column(
                    name = "USER_ID", nullable = false, length = 32)),
            @AttributeOverride(name = "ROLE_ID", column = @Column(
                    name = "ROLE_ID", nullable = false, length = 32)) })
    public QxUserRoleId getId() {
        return this.id;
    }

    public void setId(QxUserRoleId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", nullable = false, insertable = false,
            updatable = false)
    public QxRoles getQxRoles() {
        return this.qxRoles;
    }

    public void setQxRoles(QxRoles qxRoles) {
        this.qxRoles = qxRoles;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_ID")
    public QxUsers getQxUsers() {
        return this.qxUsers;
    }

    public void setQxUsers(QxUsers qxUsers) {
        this.qxUsers = qxUsers;
    }

}