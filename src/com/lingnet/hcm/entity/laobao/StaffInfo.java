package com.lingnet.hcm.entity.laobao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "STAFF_INFO")
public class StaffInfo extends BaseEntity implements java.io.Serializable{

	private String job_number;
	private String shoe_number;
	private String clothes_number;
	private String class_id;
	private String class_name;
	
	@Column(name = "JOB_NUMBER", length = 32)
	public String getJob_number() {
		return job_number;
	}
	
	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}
	
	@Column(name = "SHOE_NUMBER", length = 32)
	public String getShoe_number() {
		return shoe_number;
	}
	
	public void setShoe_number(String shoe_number) {
		this.shoe_number = shoe_number;
	}
	
	@Column(name = "CLOTHES_NUMBER", length = 32)
	public String getClothes_number() {
		return clothes_number;
	}
	
	public void setClothes_number(String clothes_number) {
		this.clothes_number = clothes_number;
	}
	
	@Column(name = "CLASS_ID", length = 32)
	public String getClass_id() {
		return class_id;
	}
	
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	@Column(name = "CLASSZ_NAME", length = 20)
	public String getClass_name() {
		return class_name;
	}
	
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	
	
	
}
