package com.lingnet.hcm.entity.laobao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "History")
public class History extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -2165173611838814030L;
    private String name;// 姓名
    private String wpmc;// 物品名称
    private String ggxh;// 规格型号
    private String jldw;// 计量单位
    private Integer isSp;// 审批 0：不通过 1：通过
    private String note;// 备注
    private String wpId;// 物品ID
    private String biaoZhunId;// 标准ID
    private String staffId;// 用户ID
    private Date nextdate;// 下次领取日期
    private Integer count;// 领取数量

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    @Column(name = "IS_SP", precision = 1, scale = 0)
    public Integer getIsSp() {
        return isSp;
    }

    public void setIsSp(Integer isSp) {
        this.isSp = isSp;
    }

    @Column(name = "NOTE", length = 2000)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "WP_ID", length = 32)
    public String getWpId() {
        return wpId;
    }

    public void setWpId(String wpId) {
        this.wpId = wpId;
    }

    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "NEXTDATE", length = 7)
    public Date getNextdate() {
        return nextdate;
    }

    public void setNextdate(Date nextdate) {
        this.nextdate = nextdate;
    }

    @Column(name = "COUNT", precision = 8, scale = 0)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Column(name = "BIAO_ZHUN_ID", length = 32)
    public String getBiaoZhunId() {
        return biaoZhunId;
    }

    public void setBiaoZhunId(String biaoZhunId) {
        this.biaoZhunId = biaoZhunId;
    }

}
