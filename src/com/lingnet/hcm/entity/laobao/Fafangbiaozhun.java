package com.lingnet.hcm.entity.laobao;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "FAFANG_BIAOZHUN")
public class Fafangbiaozhun extends BaseEntity implements java.io.Serializable{
	private String name;//岗位名称
	private String maqm;//棉安全帽
	private String daqm;//单安全帽
	private String cxgz;//长袖工装
	private String dxgz;//短袖工装
	private String yrf;//羽绒服
	private String yy;//雨衣
	private String yx;//雨鞋
	private String maqx;//棉安全鞋
	private String jyx;//绝缘鞋
	private String jyst;//绝缘手套
	private String dhst;//电焊手套
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaqm() {
		return maqm;
	}
	public void setMaqm(String maqm) {
		this.maqm = maqm;
	}
	public String getDaqm() {
		return daqm;
	}
	public void setDaqm(String daqm) {
		this.daqm = daqm;
	}
	public String getCxgz() {
		return cxgz;
	}
	public void setCxgz(String cxgz) {
		this.cxgz = cxgz;
	}
	public String getDxgz() {
		return dxgz;
	}
	public void setDxgz(String dxgz) {
		this.dxgz = dxgz;
	}
	public String getYrf() {
		return yrf;
	}
	public void setYrf(String yrf) {
		this.yrf = yrf;
	}
	public String getYy() {
		return yy;
	}
	public void setYy(String yy) {
		this.yy = yy;
	}
	public String getYx() {
		return yx;
	}
	public void setYx(String yx) {
		this.yx = yx;
	}
	public String getMaqx() {
		return maqx;
	}
	public void setMaqx(String maqx) {
		this.maqx = maqx;
	}
	public String getJyx() {
		return jyx;
	}
	public void setJyx(String jyx) {
		this.jyx = jyx;
	}
	public String getJyst() {
		return jyst;
	}
	public void setJyst(String jyst) {
		this.jyst = jyst;
	}
	public String getDhst() {
		return dhst;
	}
	public void setDhst(String dhst) {
		this.dhst = dhst;
	}
    
}
