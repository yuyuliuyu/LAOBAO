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
 * 市
 */
@Entity
@Table(name = "JC_ADDRESS_CITY")
public class AdressCity extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 7873549428318838909L;

	private String cityid;//市ID
    private String city;//市
    private String fatherid;//父级id
    
    @Column(name = "CITYID")
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	@Column(name = "CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "FATHER")
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}

}