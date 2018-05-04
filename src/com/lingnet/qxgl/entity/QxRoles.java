package com.lingnet.qxgl.entity;
 
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "QX_ROLES")
public class QxRoles extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 7978205274775220870L;

	public static final String ROLE_BASE = "ROLE_BASE"; // 角色的基础权限
	private String name; // 角色名称
	private Boolean isSystem;// 是否为系统内置角色
	private String description;//描述
	private Set<QxUser> admins = new HashSet<QxUser>();
	private Set<QxResource> qxResources = new HashSet<QxResource>();
	

	@Column(nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "qxRoles")
	@JoinColumn(nullable = false)
	@JoinTable(name = "qx_user_role", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public Set<QxUser> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<QxUser> admins) {
		this.admins = admins;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@JoinTable(name = "qx_role_resource", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "RESOURCE_ID") })
	public Set<QxResource> getQxResources() {
		return qxResources;
	}

	public void setQxResources(Set<QxResource> qxResources) {
		this.qxResources = qxResources;
	}

	// 保存处理
	@Override
	@Transient
	public void onSave() {
		if (isSystem == null) {
			isSystem = false;
		}
	}

	// 更新处理
	@Override
	@Transient
	public void onUpdate() {
		if (isSystem == null) {
			isSystem = false;
		}
	}

}