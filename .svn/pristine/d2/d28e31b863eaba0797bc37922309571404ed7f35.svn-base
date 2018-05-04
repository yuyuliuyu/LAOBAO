package com.lingnet.qxgl.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * QxRoleResourceId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class QxRoleResourceId implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 3382545470407480364L;
    private String roleId;
    private String resourceId;

    // Constructors

    /** default constructor */
    public QxRoleResourceId() {
    }

    /** full constructor */
    public QxRoleResourceId(String roleId, String resourceId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
    }

    // Property accessors

    @Column(name = "ROLE_ID", nullable = false, length = 32)
    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "RESOURCE_ID", nullable = false, length = 32)
    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof QxRoleResourceId))
            return false;
        QxRoleResourceId castOther = (QxRoleResourceId) other;

        return ((this.getRoleId() == castOther.getRoleId()) || (this
                .getRoleId() != null && castOther.getRoleId() != null && this
                .getRoleId().equals(castOther.getRoleId())))
                && ((this.getResourceId() == castOther.getResourceId()) || (this
                        .getResourceId() != null
                        && castOther.getResourceId() != null && this
                        .getResourceId().equals(castOther.getResourceId())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getRoleId() == null ? 0 : this.getRoleId().hashCode());
        result = 37
                * result
                + (getResourceId() == null ? 0 : this.getResourceId()
                        .hashCode());
        return result;
    }

}