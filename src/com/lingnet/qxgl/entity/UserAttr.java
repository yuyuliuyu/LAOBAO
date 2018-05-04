package com.lingnet.qxgl.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "user_arrt")
public class UserAttr extends BaseEntity implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6575968217039855442L;
	private String pic;//图片
	private String userid;//用户id
	public String getPic() {
		return pic;
	}
	
	public UserAttr(){
		
	}
	
	public UserAttr(String pic, String userid, Date createdate) {
		super();
		this.pic = pic;
		this.userid = userid;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
