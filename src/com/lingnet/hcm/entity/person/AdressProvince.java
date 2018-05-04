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
 * 省
 */
@Entity
@Table(name = "JC_ADDRESS_PROVINCE")
public class AdressProvince extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -1884722306531965071L;
	// Fields
    private String proid;//省份ID
    private String pro;//省份
    // Property accessors
    @Column(name = "PROVINCEID")
    public String getProid() {
		return proid;
	}
	public void setProid(String proid) {
		this.proid = proid;
	}
	@Column(name = "PROVINCE")
	public String getPro() {
		return pro;
	}
	public void setPro(String pro) {
		this.pro = pro;
	}
	

}