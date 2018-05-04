package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * JcAbroad entity. 
 * @author MyEclipse Persistence Tools
 * 地区
 */
@Entity
@Table(name = "JC_ADDRESS_AREA")
public class AdressArea extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 7029020975540601020L;
	
	private String areaid;//市ID
    private String area;//市
    private String fatherid;//父级id
    
    @Column(name = "AREAID")
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	@Column(name = "AREA")
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Column(name = "FATHER")
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}

	

}