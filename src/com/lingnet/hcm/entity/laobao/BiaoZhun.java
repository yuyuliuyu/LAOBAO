package com.lingnet.hcm.entity.laobao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "BIAOZHUN")
public class BiaoZhun extends BaseEntity implements java.io.Serializable {
	
    private static final long serialVersionUID = 5771808253059188820L;
    private String wpId;   //物品名称
	private String issueId;//规格型号
	private String classId;//计量单位
	private Integer lqjg;// 领取间隔
	private Integer lqdw;// 1:年 2：月3：天//领取单位
	private String gwId;//岗位ID
	private String depId;

	public BiaoZhun() {
    }
	
	@Column(name = "wp_id", length = 32)
	public String getWpId() {
		return wpId;
	}
	public void setWpId(String wpId) {
		this.wpId = wpId;
	}
	@Column(name = "issue_id", length = 32)
	public String getIssueId() {
		return issueId;
	}
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	@Column(name = "class_id", length = 32)
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}

	@Column(name = "LQJG", precision = 8, scale = 0)
    public Integer getLqjg() {
        return lqjg;
    }
    public void setLqjg(Integer lqjg) {
        this.lqjg = lqjg;
    }

    @Column(name = "LQDW", precision = 1, scale = 0)
    public Integer getLqdw() {
        return lqdw;
    }
    public void setLqdw(Integer lqdw) {
        this.lqdw = lqdw;
    }

    @Column(name = "GW_ID", length = 32)
    public String getGwId() {
        return gwId;
    }

    public void setGwId(String gwId) {
        this.gwId = gwId;
    }
}
