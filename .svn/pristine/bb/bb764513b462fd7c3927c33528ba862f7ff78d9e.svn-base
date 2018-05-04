package com.lingnet.qxgl.entity;

import java.util.Collection;
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
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "QX_USERS")
public class QxUsers extends BaseEntity implements java.io.Serializable, 

UserDetails {

	private static final long serialVersionUID = -2400769758495080278L;
	private String username; // 用户名
	private String password; // 密码
	private String name;// 姓名
	private String email; // 邮箱
	private Date loginDate;// 最后登录日期
	private String loginIp;// 最后登录IP
	private boolean userAccountNonExpired; // 账号是否未过期
	private boolean userAccountNonLocked; // 账号是否未锁定
	private boolean userCredentialsNonExpired; // 账号凭证是否未过期
	private boolean userEnabled; // 账号是否可用
	private String depId;
	private Boolean isSystem;// 是否为系统内置
	private double sdiscount;//销售折扣
	private double ldiscount;//领导折扣
	private String isleader;//是否领导(0:否,1:是)
	private String cid;//分中心id
	private Integer isDelete;// 是否删除 0未删除 1 已删除
	private String inputCode;//输入码
	private String reciveCode;///交接密码
	private String phone;//电话
	private Integer isDoc;//是否是医师
	private String userCode;//lis代码
	/**
     * @return the depId
     */
    public String getDepId() {
        return depId;
    }

    /**
     * @param depId the depId to set
     */
    public void setDepId(String depId) {
        this.depId = depId;
    }

    // UserDetails的角色资源属性集合
	private Collection<GrantedAuthority> authorities;
	// 与Role的多对多关系
	private Set<QxRoles> qxRoles = new HashSet<QxRoles>();

	@Column(nullable = false, updatable = false, unique = true)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column()
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(nullable = false)
	public boolean isUserAccountNonExpired() {
		return userAccountNonExpired;
	}

	public void setUserAccountNonExpired(boolean userAccountNonExpired) 

{
		this.userAccountNonExpired = userAccountNonExpired;
	}

	@Column(nullable = false)
	public boolean isUserAccountNonLocked() {
		return userAccountNonLocked;
	}

	public void setUserAccountNonLocked(boolean userAccountNonLocked) {
		this.userAccountNonLocked = userAccountNonLocked;
	}

	@Column(nullable = false)
	public boolean isUserCredentialsNonExpired() {
		return userCredentialsNonExpired;
	}

	public void setUserCredentialsNonExpired(boolean 

userCredentialsNonExpired) {
		this.userCredentialsNonExpired = userCredentialsNonExpired;
	}

	@Column(nullable = false)
	public boolean isUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "qx_user_role", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	public Set<QxRoles> getQxRoles() {
		return qxRoles;
	}

	public void setQxRoles(Set<QxRoles> qxRoles) {
		this.qxRoles = qxRoles;
	}

	// 重写此方法,获取用户权限集合
	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) 

{
		this.authorities = authorities;
	}

	// true ，表示账号未过期
	@Transient
	public boolean isAccountNonExpired() {
		return this.userAccountNonExpired;
	}

	// true，表示账号未锁定
	@Transient
	public boolean isAccountNonLocked() {
		return this.userAccountNonLocked;
	}

	// true，表示账号凭证未过期
	@Transient
	public boolean isCredentialsNonExpired() {
		return this.userCredentialsNonExpired;
	}

	// true，表示账号可使用
	@Transient
	public boolean isEnabled() {
		return this.userEnabled;
	}

    
    public Boolean getIsSystem() {
        return isSystem;
    }

    
    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }
    
    @Column(name="SDISCOUNT", precision=16, scale=4)
    public double getSdiscount() {
        return sdiscount;
    }

    public void setSdiscount(double sdiscount) {
        this.sdiscount = sdiscount;
    }
    
    @Column(name="LDISCOUNT", precision=16, scale=4)
    public double getLdiscount() {
        return ldiscount;
    }

    public void setLdiscount(double ldiscount) {
        this.ldiscount = ldiscount;
    }
    
    @Column(name="ISLEADER", length=10)
    public String getIsleader() {
        return isleader;
    }

    public void setIsleader(String isleader) {
        this.isleader = isleader;
    }
    @Column(name="cid",length=32)
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
    
    @Column(name="IS_DELETE")
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    @Column(name="input_code")
    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }
    
    @Column(name="RECIVE_CODE")
    public String getReciveCode() {
		return reciveCode;
	}

	public void setReciveCode(String reciveCode) {
		this.reciveCode = reciveCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name="IS_DOC")
	public Integer getIsDoc() {
		return isDoc;
	}

	public void setIsDoc(Integer isDoc) {
		this.isDoc = isDoc;
	}

	@Column(name="USER_CODE")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}