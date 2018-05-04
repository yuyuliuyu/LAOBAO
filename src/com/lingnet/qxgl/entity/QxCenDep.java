package com.lingnet.qxgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * QxCenDep entity. @author MyEclipse Persistence Tools
 * 分中心部门中间表 lsp
 */
@Entity
@Table(name = "QX_CEN_DEP" )
public class QxCenDep extends BaseEntity {
    // Fields
    private String cid;//分中心id
    private String did;//部门id
    private String imgpath;//图片路径
    private Integer xh;//序号
    private Integer sjbggs;//数据报告格式
    private String jcdd;//检查地点
    private String jklx;//接口类型
    private String ksh;//科室号
    private String description;
    
    // Constructors
    @Column(name = "CID", length = 32)
    public String getCid() {
        return this.cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Column(name = "DID", length = 32)
    public String getDid() {
        return this.did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    @Column(name="IMGPATH")
    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    @Column(name="sjbggs")
    public Integer getSjbggs() {
        return sjbggs;
    }

    public void setSjbggs(Integer sjbggs) {
        this.sjbggs = sjbggs;
    }
    @Column(name="jcdd",length=32)
    public String getJcdd() {
        return jcdd;
    }

    public void setJcdd(String jcdd) {
        this.jcdd = jcdd;
    }
    @Column(name="jklx",length=10)
    public String getJklx() {
        return jklx;
    }

    public void setJklx(String jklx) {
        this.jklx = jklx;
    }
    @Column(name="ksh",length=32)
    public String getKsh() {
        return ksh;
    }

    public void setKsh(String ksh) {
        this.ksh = ksh;
    }
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}
}