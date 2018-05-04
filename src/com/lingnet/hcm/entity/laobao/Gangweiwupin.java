package com.lingnet.hcm.entity.laobao;

import com.lingnet.common.entity.BaseEntity;

public class Gangweiwupin extends BaseEntity implements java.io.Serializable{

	private String wpmc;   //物品名称
	private String ggxh;//规格型号
	private String jldw;//计量单位
	private String gys;//供应商
	private String gangwei; 
	private String remark;
	public String getWpmc() {
		return wpmc;
	}
	public void setWpmc(String wpmc) {
		this.wpmc = wpmc;
	}
	public String getGgxh() {
		return ggxh;
	}
	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}
	public String getJldw() {
		return jldw;
	}
	public void setJldw(String jldw) {
		this.jldw = jldw;
	}
	public String getGys() {
		return gys;
	}
	public void setGys(String gys) {
		this.gys = gys;
	}
	public String getGangwei() {
		return gangwei;
	}
	public void setGangwei(String gangwei) {
		this.gangwei = gangwei;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
