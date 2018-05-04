package com.lingnet.hcm.entity.laobao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "WUPIN")
public class Wupin extends BaseEntity implements java.io.Serializable {
	
    private static final long serialVersionUID = 8561796294493917002L;
    private String wpmc;   //物品名称
	private String ggxh;//规格型号
	private String jldw;//计量单位
	private String gys;//供应商
	private String kcsl;
	private String postId;//标准岗位ID
	private Date nextdate;
	public Wupin() {
    }
	
	@Column(name = "WPMC", length = 32)
	public String getWpmc() {
		return wpmc;
	}
	public void setWpmc(String wpmc) {
		this.wpmc = wpmc;
	}
	@Column(name = "GGXH", length = 32)
	public String getGgxh() {
		return ggxh;
	}
	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}
	@Column(name = "JLDW", length = 32)
	public String getJldw() {
		return jldw;
	}
	public void setJldw(String jldw) {
		this.jldw = jldw;
	}
	@Column(name = "KCSL", length = 32)
	public String getKcsl() {
		return kcsl;
	}

	public void setKcsl(String kcsl) {
		this.kcsl = kcsl;
	}

	@Column(name = "GYS", length = 32)
	public String getGys() {
		return gys;
	}
	public void setGys(String gys) {
		this.gys = gys;
	}

	@Column(name = "POST_ID", length = 32)
    public String getPostId() {
        return postId;
    }
    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "NEXTDATE", length = 7)
    public Date getNextdate() {
        return nextdate;
    }

    public void setNextdate(Date nextdate) {
        this.nextdate = nextdate;
    }
	
	

}
