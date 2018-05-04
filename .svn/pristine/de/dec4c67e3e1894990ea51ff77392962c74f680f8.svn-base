package com.lingnet.qxgl.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * QxResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "QX_RESOURCE")
public class QxResource extends BaseEntity implements java.io.Serializable {

    // Fields
    private static final long serialVersionUID = 5126520242059869017L;
    private String resourcename;//资源名称
    private String resourceurl;//资源路径
    private String description;//描述
    private String modulename;//模块名称
    private String moduleid;//模块ID
    private String imgvalue;//图片
    private String presource;//父级路径
    private String state;//状态
    private Integer sortorder;//排序
    private Boolean issystem;//是否系统内置
    @SuppressWarnings("unused")
    private Set<QxRoleResource> qxRoleResources = new HashSet<QxRoleResource>(0);
    private String type;//资源类型 url||method
 // Constructors

    /** default constructor */
    public QxResource() {
    }

   

    /** full constructor */
    public QxResource(String id, String resourcename, String resourceurl,
            String description, String modulename, String moduleid,
            Date createdate, Date modifydate, String imgvalue,
            String presource, String state, Set<QxRoleResource> qxRoleResources) {

        this.resourcename = resourcename;
        this.resourceurl = resourceurl;
        this.description = description;
        this.modulename = modulename;
        this.moduleid = moduleid;

        this.imgvalue = imgvalue;
        this.presource = presource;
        this.state = state;
        this.qxRoleResources = qxRoleResources;
    }

    // Property accessors


    @Column(name = "RESOURCENAME", length = 50)
    public String getResourcename() {
        return this.resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    @Column(name = "RESOURCEURL", length = 50)
    public String getResourceurl() {
        return this.resourceurl;
    }

    public void setResourceurl(String resourceurl) {
        this.resourceurl = resourceurl;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "MODULENAME")
    public String getModulename() {
        return this.modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    @Column(name = "MODULEID", length = 10)
    public String getModuleid() {
        return this.moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid;
    }

    @Column(name = "IMGVALUE", length = 510)

    public String getImgvalue() {
        return imgvalue;
    }

    public void setImgvalue(String imgvalue) {
        this.imgvalue = imgvalue;
    }

    @Column(name = "PRESOURCE", length = 50)
    public String getPresource() {
        return this.presource;
    }

    public void setPresource(String presource) {
        this.presource = presource;
    }

    @Column(name = "STATE", length = 10)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private Set<QxRoles> qxRoles = new HashSet<QxRoles>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    //@JoinColumn(nullable = false)
    @JoinTable(name = "qx_role_resource", joinColumns = { @JoinColumn(name = "RESOURCE_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    //@ForeignKey(name = "BK_ADMIN_ROLE_ADMIN")
    public Set<QxRoles> getQxRoles() {
        return qxRoles;
    }

    public void setQxRoles(Set<QxRoles> qxRoles) {
        this.qxRoles = qxRoles;
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
    }
    @Column(name = "ISSYSTEM", length = 1)
    public Boolean getIssystem() {
        return issystem;
    }


    public void setIssystem(Boolean issystem) {
        this.issystem = issystem;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}