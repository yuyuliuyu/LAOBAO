package com.lingnet.hcm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * ComChange entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COM_CHANGE")
public class ComChange extends BaseEntity implements java.io.Serializable  {

	/**
     * 
     */
    private static final long serialVersionUID = -6115264075587621029L;
    // Fields
    private String middleId;//中间表id
    private String fzx;// 组织机构名字
    private String jm;// 简称，不能重复
    private String srm;// 输入码
    private String pid;//父级Id
    private Integer isDelete;//0未删除 1 已删除
    private String address;//地址
    private Integer isDefault;//（只能有一个为1的分中心,当前数据库的分中心）
    private String shortRemark;//公司简介
    private String webUrl;//网址
    private String honor;//荣誉
    private String remark;//备注
    private String flg;//标示为0
    private String imgpath;
    private String type;// 公司性质 0：合资公司、1：外包公司

    private String cmName;// 工商注册名称
    private String cmType;// 工商注册类型
    private String cmLegal;// 法定代表人
    private String cmAddr;// 住所
    private String cmZb;// 注册资本
    private String cmEstablishDate;// 成立日期
    private String cmYyqxHigh;// 营业期限上限
    private String cmYyqxLow;// 营业期限下限
    private String cmJyfw;// 经营范围
    private String cmTyshdm;// 统一社会信用代码
    private String cmBank;// 开户行
    private String cmZh;// 账号
    private String cmTxdz;// 通信地址
    private String cmLxbm;// 联系部门
    private String cmLxr;// 联系人
    private String cmZw;// 职务
    private String cmPhone;// 联系电话
    private String cmFax;// 传真
    private String cmWeb;// 网页
    private String field1;//预留字段1
    private String field2;//预留字段2
    private Date foundDate;//成立日期

	// Constructors

	/** default constructor */
	public ComChange() {
	}


	/** full constructor */
	public ComChange(String id, String fzx, String jm, String srm,
			Date createdate, Date modifydate, String pid, Integer isDelete,
			String address, Integer isDefault, String remark, String webUrl,
			String honor, String field1, String field2, String shortRemark,
			String flg, String imgpath, String type, String cmName,
			String cmType, String cmLegal, String cmAddr, String cmZb,
			Date cmEstablishDate, Date cmYyqxHigh, Date cmYyqxLow,
			String cmJyfw, String cmTyshdm, String cmBank, String cmZh,
			String cmTxdz, String cmLxbm, String cmLxr, String cmPhone,
			String cmFax, String cmWeb, String cmZw) {
		this.fzx = fzx;
		this.jm = jm;
		this.srm = srm;
		this.pid = pid;
		this.isDelete = isDelete;
		this.address = address;
		this.isDefault = isDefault;
		this.remark = remark;
		this.webUrl = webUrl;
		this.honor = honor;
		this.field1 = field1;
		this.field2 = field2;
		this.shortRemark = shortRemark;
		this.flg = flg;
		this.imgpath = imgpath;
		this.type = type;
		this.cmName = cmName;
		this.cmType = cmType;
		this.cmLegal = cmLegal;
		this.cmAddr = cmAddr;
		this.cmZb = cmZb;
		this.cmJyfw = cmJyfw;
		this.cmTyshdm = cmTyshdm;
		this.cmBank = cmBank;
		this.cmZh = cmZh;
		this.cmTxdz = cmTxdz;
		this.cmLxbm = cmLxbm;
		this.cmLxr = cmLxr;
		this.cmPhone = cmPhone;
		this.cmFax = cmFax;
		this.cmWeb = cmWeb;
		this.cmZw = cmZw;
	}

	// Property accessors

	@Column(name = "FZX", length = 50)
    public String getFzx() {
        return this.fzx;
    }

    public void setFzx(String fzx) {
        this.fzx = fzx;
    }

    @Column(name = "JM", length = 10)
    public String getJm() {
        return this.jm;
    }

    public void setJm(String jm) {
        this.jm = jm;
    }

    @Column(name = "SRM", length = 100)
    public String getSrm() {
        return this.srm;
    }

    public void setSrm(String srm) {
        this.srm = srm;
    }
    @Column(name="pid",length=32)
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
    @Column(name="is_delete")
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    @Column(name="address",length=255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="IS_DEFAULT")
    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Column(name="remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name="web_url")
    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @Column(name="honor")
    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    @Column(name="field1")
    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    @Column(name="field2")
    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    @Column(name="flg")
    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    @Column(name="short_remark")
    public String getShortRemark() {
        return shortRemark;
    }

    public void setShortRemark(String shortRemark) {
        this.shortRemark = shortRemark;
    }

    @Column(name="imgpath")
    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    @Column(name="TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name="CM_NAME")
    public String getCmName() {
        return cmName;
    }

    public void setCmName(String cmName) {
        this.cmName = cmName;
    }

    @Column(name="CM_TYPE")
    public String getCmType() {
        return cmType;
    }

    public void setCmType(String cmType) {
        this.cmType = cmType;
    }

    @Column(name="CM_LEGAL")
    public String getCmLegal() {
        return cmLegal;
    }

    public void setCmLegal(String cmLegal) {
        this.cmLegal = cmLegal;
    }

    @Column(name="CM_ADDR")
    public String getCmAddr() {
        return cmAddr;
    }

    public void setCmAddr(String cmAddr) {
        this.cmAddr = cmAddr;
    }

    @Column(name="CM_ZB")
    public String getCmZb() {
        return cmZb;
    }

    public void setCmZb(String cmZb) {
        this.cmZb = cmZb;
    }

    @Column(name="CM_ESTABLISH_DATE")
    public String getCmEstablishDate() {
        return cmEstablishDate;
    }

    public void setCmEstablishDate(String cmEstablishDate) {
        this.cmEstablishDate = cmEstablishDate;
    }

    @Column(name="CM_YYQX_HIGH")
    public String getCmYyqxHigh() {
        return cmYyqxHigh;
    }

    public void setCmYyqxHigh(String cmYyqxHigh) {
        this.cmYyqxHigh = cmYyqxHigh;
    }

    @Column(name="CM_YYQX_LOW")
    public String getCmYyqxLow() {
        return cmYyqxLow;
    }

    public void setCmYyqxLow(String cmYyqxLow) {
        this.cmYyqxLow = cmYyqxLow;
    }

    @Column(name="CM_JYFW")
    public String getCmJyfw() {
        return cmJyfw;
    }

    public void setCmJyfw(String cmJyfw) {
        this.cmJyfw = cmJyfw;
    }

    @Column(name="CM_TYSHDM")
    public String getCmTyshdm() {
        return cmTyshdm;
    }

    public void setCmTyshdm(String cmTyshdm) {
        this.cmTyshdm = cmTyshdm;
    }

    @Column(name="CM_BANK")
    public String getCmBank() {
        return cmBank;
    }

    public void setCmBank(String cmBank) {
        this.cmBank = cmBank;
    }

    @Column(name="CM_ZH")
    public String getCmZh() {
        return cmZh;
    }

    public void setCmZh(String cmZh) {
        this.cmZh = cmZh;
    }

    @Column(name="CM_TXDZ")
    public String getCmTxdz() {
        return cmTxdz;
    }

    public void setCmTxdz(String cmTxdz) {
        this.cmTxdz = cmTxdz;
    }

    @Column(name="CM_LXBM")
    public String getCmLxbm() {
        return cmLxbm;
    }

    public void setCmLxbm(String cmLxbm) {
        this.cmLxbm = cmLxbm;
    }

    @Column(name="CM_LXR")
    public String getCmLxr() {
        return cmLxr;
    }

    public void setCmLxr(String cmLxr) {
        this.cmLxr = cmLxr;
    }

    @Column(name="CM_ZW")
    public String getCmZw() {
        return cmZw;
    }

    public void setCmZw(String cmZw) {
        this.cmZw = cmZw;
    }

    @Column(name="CM_PHONE")
    public String getCmPhone() {
        return cmPhone;
    }

    public void setCmPhone(String cmPhone) {
        this.cmPhone = cmPhone;
    }

    @Column(name="CM_FAX")
    public String getCmFax() {
        return cmFax;
    }

    public void setCmFax(String cmFax) {
        this.cmFax = cmFax;
    }

    @Column(name="CM_WEB")
    public String getCmWeb() {
        return cmWeb;
    }

    public void setCmWeb(String cmWeb) {
        this.cmWeb = cmWeb;
    }

    @Column(name="MIDDLE_ID")
    public String getMiddleId() {
        return middleId;
    }


    public void setMiddleId(String middleId) {
        this.middleId = middleId;
    }

    @Column(name="FOUND_DATE")
    public Date getFoundDate() {
        return foundDate;
    }


    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }

}