package com.lingnet.qxgl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * CreateOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CREATEORDER")//订单表
public class CreateOrder extends BaseEntity implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String ddh;//订单号
    private String ddmc;//订单名称
    private String dddm;//订单代码
    private String khdwmcid;//客户单位名称ID
    private String txfs;//提醒方式(0:首页提醒、1:短信提醒)
    private String fsdxsjh;//发送短信手机号
    private Date cjddrq;//创建订单日期
    private String khdwdh;//客户单位电话
    private Date jhjqc;//计划检期从
    private Date jhjqd;//计划检期到
    private String sfyqdht;//是否已签订合同
    private String htbh;//合同编号
    private Long nxtjrs;//男性体检人数
    private Long vxtjrs;//女性体检人数
    private Double ddjg;//订单价格
    private Double ddzk;//订单折扣
    private Double ddyhj;//订单优惠价
    private String qtxz;//前台须知
    private Integer spzt;//审批状态//(0:草稿、1:已提交、2:已撤回、3:审核通过、4:（审核未通过)；已经废弃
    //0:审核未通过 、1:已撤回、2:草稿、3:已提交、4:审核通过
    private String spyj;//审批意见
    private String xsjlid;//销售经理ID
    private String xsjl;//销售经理
    private String fzxid;//分中心ID
    private Integer isDelete;//假删状态(0:未删、1:删除)
    private String urls; //文件路径

    // Constructors

    /** default constructor */
    public CreateOrder() {
    }

    /** full constructor */
    public CreateOrder(String ddh, String ddmc, String dddm,
            String khdwmcid, String txfs, String fsdxsjh, Date cjddrq,
            String khdwdh, Date jhjqc, Date jhjqd, String sfyqdht,
            String htbh, Long nxtjrs, Long vxtjrs, Double ddjg, Double ddzk,
            Double ddyhj, String qtxz, Integer spzt, String spyj, String xsjlid,String xsjl,
            String fzxid, Integer isDelete) {
        this.ddh = ddh;
        this.ddmc = ddmc;
        this.dddm = dddm;
        this.khdwmcid = khdwmcid;
        this.txfs = txfs;
        this.fsdxsjh = fsdxsjh;
        this.cjddrq = cjddrq;
        this.khdwdh = khdwdh;
        this.jhjqc = jhjqc;
        this.jhjqd = jhjqd;
        this.sfyqdht = sfyqdht;
        this.htbh = htbh;
        this.nxtjrs = nxtjrs;
        this.vxtjrs = vxtjrs;
        this.ddjg = ddjg;
        this.ddzk = ddzk;
        this.ddyhj = ddyhj;
        this.qtxz = qtxz;
        this.spzt = spzt;
        this.spyj = spyj;
        this.xsjlid = xsjlid;
        this.xsjl=xsjl;
        this.fzxid = fzxid;
        this.isDelete = isDelete;
    }

    // Property accessors

    @Column(name = "DDH", length = 50)
    public String getDdh() {
        return this.ddh;
    }

    public void setDdh(String ddh) {
        this.ddh = ddh;
    }

    @Column(name = "DDMC", length = 200)
    public String getDdmc() {
        return this.ddmc;
    }

    public void setDdmc(String ddmc) {
        this.ddmc = ddmc;
    }

    @Column(name = "DDDM", length = 100)
    public String getDddm() {
        return this.dddm;
    }

    public void setDddm(String dddm) {
        this.dddm = dddm;
    }

    @Column(name = "KHDWMCID", length = 32)
    public String getKhdwmcid() {
        return this.khdwmcid;
    }

    public void setKhdwmcid(String khdwmcid) {
        this.khdwmcid = khdwmcid;
    }

    @Column(name = "TXFS", length = 5)
    public String getTxfs() {
        return this.txfs;
    }

    public void setTxfs(String txfs) {
        this.txfs = txfs;
    }

    @Column(name = "FSDXSJH", length = 20)
    public String getFsdxsjh() {
        return this.fsdxsjh;
    }

    public void setFsdxsjh(String fsdxsjh) {
        this.fsdxsjh = fsdxsjh;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CJDDRQ", length = 7)
    public Date getCjddrq() {
        return this.cjddrq;
    }

    public void setCjddrq(Date cjddrq) {
        this.cjddrq = cjddrq;
    }

    @Column(name = "KHDWDH", length = 20)
    public String getKhdwdh() {
        return this.khdwdh;
    }

    public void setKhdwdh(String khdwdh) {
        this.khdwdh = khdwdh;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "JHJQC", length = 7)
    public Date getJhjqc() {
        return this.jhjqc;
    }

    public void setJhjqc(Date jhjqc) {
        this.jhjqc = jhjqc;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "JHJQD", length = 7)
    public Date getJhjqd() {
        return this.jhjqd;
    }

    public void setJhjqd(Date jhjqd) {
        this.jhjqd = jhjqd;
    }

    @Column(name = "SFYQDHT", length = 10)
    public String getSfyqdht() {
        return this.sfyqdht;
    }

    public void setSfyqdht(String sfyqdht) {
        this.sfyqdht = sfyqdht;
    }

    @Column(name = "HTBH", length = 500)
    public String getHtbh() {
        return this.htbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    @Column(name = "NXTJRS", precision = 10, scale = 0)
    public Long getNxtjrs() {
        return this.nxtjrs;
    }

    public void setNxtjrs(Long nxtjrs) {
        this.nxtjrs = nxtjrs;
    }

    @Column(name = "VXTJRS", precision = 10, scale = 0)
    public Long getVxtjrs() {
        return this.vxtjrs;
    }

    public void setVxtjrs(Long vxtjrs) {
        this.vxtjrs = vxtjrs;
    }

    @Column(name = "DDJG", precision = 8)
    public Double getDdjg() {
        return this.ddjg;
    }

    public void setDdjg(Double ddjg) {
        this.ddjg = ddjg;
    }

    @Column(name = "DDZK", precision = 8, scale = 3)
    public Double getDdzk() {
        return this.ddzk;
    }

    public void setDdzk(Double ddzk) {
        this.ddzk = ddzk;
    }

    @Column(name = "DDYHJ", precision = 8, scale = 3)
    public Double getDdyhj() {
        return this.ddyhj;
    }

    public void setDdyhj(Double ddyhj) {
        this.ddyhj = ddyhj;
    }

    @Column(name = "QTXZ", length = 2000)
    public String getQtxz() {
        return this.qtxz;
    }

    public void setQtxz(String qtxz) {
        this.qtxz = qtxz;
    }

    @Column(name = "SPZT", precision = 1, scale = 0)
    public Integer getSpzt() {
        return this.spzt;
    }

    public void setSpzt(Integer spzt) {
        this.spzt = spzt;
    }

    @Column(name = "SPYJ", length = 2000)
    public String getSpyj() {
        return this.spyj;
    }

    public void setSpyj(String spyj) {
        this.spyj = spyj;
    }
    
    @Column(name = "XSJL", length = 50)
    public String getXsjl() {
        return xsjl;
    }

    public void setXsjl(String xsjl) {
        this.xsjl = xsjl;
    }

    @Column(name = "XSJLID", length = 32)
    public String getXsjlid() {
        return this.xsjlid;
    }

    public void setXsjlid(String xsjlid) {
        this.xsjlid = xsjlid;
    }

    @Column(name = "FZXID", length = 2950)
    public String getFzxid() {
        return this.fzxid;
    }

    public void setFzxid(String fzxid) {
        this.fzxid = fzxid;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "URLS", length = 2950)
	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}
    
    

}