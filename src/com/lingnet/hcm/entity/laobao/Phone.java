package com.lingnet.hcm.entity.laobao;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "Phone")
public class Phone extends BaseEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = -2165173611838814030L;
    private String department;//部门名称
    private String postname;//岗位名称
    private String username;//使用人
    private String phonenumber;//电话号码
    private String type;//类型
	
    
    
    public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPostname() {
		return postname;
	}
	public void setPostname(String postname) {
		this.postname = postname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

    
    
    
    
	
}
