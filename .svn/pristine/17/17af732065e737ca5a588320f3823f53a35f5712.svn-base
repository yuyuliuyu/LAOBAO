package com.lingnet.qxgl.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * QxRoleResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "QX_ROLE_RESOURCE")
public class QxRoleResource implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 6184929578931333341L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private QxRoleResourceId id;
    private QxRoles qxRolesByRoleId;
    private QxRoles qxRolesById;
    private QxResource qxResource;

    // Constructors

    /** default constructor */
    public QxRoleResource() {
    }

    /** minimal constructor */
    public QxRoleResource(QxRoleResourceId id, QxRoles qxRolesByRoleId,
            QxResource qxResource) {
        this.id = id;
        this.qxRolesByRoleId = qxRolesByRoleId;
        this.qxResource = qxResource;
    }

    /** full constructor */
    public QxRoleResource(QxRoleResourceId id, QxRoles qxRolesByRoleId,
            QxRoles qxRolesById, QxResource qxResource) {
        this.id = id;
        this.qxRolesByRoleId = qxRolesByRoleId;
        this.qxRolesById = qxRolesById;
        this.qxResource = qxResource;
    }

    // Property accessors
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "roleId", column = @Column(
                    name = "ROLE_ID", nullable = false, length = 32)),
            @AttributeOverride(name = "resourceId", column = @Column(
                    name = "RESOURCE_ID", nullable = false, length = 32)) })
    public QxRoleResourceId getId() {
        return this.id;
    }

    public void setId(QxRoleResourceId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", nullable = false, insertable = false,
            updatable = false)
    public QxRoles getQxRolesByRoleId() {
        return this.qxRolesByRoleId;
    }

    public void setQxRolesByRoleId(QxRoles qxRolesByRoleId) {
        this.qxRolesByRoleId = qxRolesByRoleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    public QxRoles getQxRolesById() {
        return this.qxRolesById;
    }

    public void setQxRolesById(QxRoles qxRolesById) {
        this.qxRolesById = qxRolesById;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESOURCE_ID", nullable = false, insertable = false,
            updatable = false)
    public QxResource getQxResource() {
        return this.qxResource;
    }

    public void setQxResource(QxResource qxResource) {
        this.qxResource = qxResource;
    }

}