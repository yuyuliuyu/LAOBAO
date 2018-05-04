package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * JcJobTitle entity. 
 * @author MyEclipse Persistence Tools
 * 职称聘任
 */
@Entity
@Table(name = "JC_JOB_TITLE")
public class JobTitle extends BaseEntity implements java.io.Serializable {

    // Fields
	private static final long serialVersionUID = 2841791938449681466L;
	
    private String jobNumber;//
    private Integer classType;//系别
    private Integer statusClass;//资格类别
    private String profession;//专业
    private String jugdeOffice;//评审机关
    private String certificateNum;//证书编号
    private String sendCertificate;//发证机关
    private Date awardDate;//授予日期
    private String certificateOffice;//证书工作单位
    private Integer isHighest;//是否最高等级
    private Integer foreignType;//外语语种
    private Integer foreignLevel;//外语级别
    private String foreignClass;//外语专业类
    private Date foreignDate;//外语通过日期
    private String foreignRemark;//外语备注
    private Date computerDate;//计算机通过日期
    private String computerRemark;//计算机备注
    private Date professionDate;//专业技术通过日期
    private String professionRemark;//专业技术备注
    private Integer declareLevel1;//可晋升级别1
    private Date declareDate1;//可申报时间1
    private Integer declareLevel2;//可晋升级别2
    private Date declareDate2;//可申报时间2
    private String engageTitle;//聘任职称
    private Date beginDate;//聘期开始
    private Date endDate;//聘期结束
    private String engageTimes;//聘任次数
    private String remark;//备注
    private String personId;//

    // Property accessors
    @Column(name = "JOB_NUMBER", length = 20)
    public String getJobNumber() {
        return this.jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Column(name = "CLASS_TYPE", precision = 1, scale = 0)
    public Integer getClassType() {
        return this.classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    @Column(name = "STATUS_CLASS", precision = 1, scale = 0)
    public Integer getStatusClass() {
        return this.statusClass;
    }

    public void setStatusClass(Integer statusClass) {
        this.statusClass = statusClass;
    }

    @Column(name = "PROFESSION", length = 50)
    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Column(name = "JUGDE_OFFICE", length = 50)
    public String getJugdeOffice() {
        return this.jugdeOffice;
    }

    public void setJugdeOffice(String jugdeOffice) {
        this.jugdeOffice = jugdeOffice;
    }

    @Column(name = "CERTIFICATE_NUM", length = 50)
    public String getCertificateNum() {
        return this.certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    @Column(name = "SEND_CERTIFICATE", length = 50)
    public String getSendCertificate() {
        return this.sendCertificate;
    }

    public void setSendCertificate(String sendCertificate) {
        this.sendCertificate = sendCertificate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "AWARD_DATE", length = 7)
	public Date getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}

    @Column(name = "CERTIFICATE_OFFICE", length = 50)
    public String getCertificateOffice() {
        return this.certificateOffice;
    }

    public void setCertificateOffice(String certificateOffice) {
        this.certificateOffice = certificateOffice;
    }

    @Column(name = "IS_HIGHEST", precision = 1, scale = 0)
    public Integer getIsHighest() {
        return this.isHighest;
    }

    public void setIsHighest(Integer isHighest) {
        this.isHighest = isHighest;
    }

    @Column(name = "FOREIGN_TYPE", precision = 2, scale = 0)
    public Integer getForeignType() {
        return this.foreignType;
    }

    public void setForeignType(Integer foreignType) {
        this.foreignType = foreignType;
    }

    @Column(name = "FOREIGN_LEVEL", precision = 1, scale = 0)
    public Integer getForeignLevel() {
        return this.foreignLevel;
    }

    public void setForeignLevel(Integer foreignLevel) {
        this.foreignLevel = foreignLevel;
    }

    @Column(name = "FOREIGN_CLASS", length = 50)
    public String getForeignClass() {
        return this.foreignClass;
    }

    public void setForeignClass(String foreignClass) {
        this.foreignClass = foreignClass;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FOREIGN_DATE", length = 7)
    public Date getForeignDate() {
        return this.foreignDate;
    }

    public void setForeignDate(Date foreignDate) {
        this.foreignDate = foreignDate;
    }

    @Column(name = "FOREIGN_REMARK", length = 50)
    public String getForeignRemark() {
        return this.foreignRemark;
    }

    public void setForeignRemark(String foreignRemark) {
        this.foreignRemark = foreignRemark;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "COMPUTER_DATE", length = 7)
    public Date getComputerDate() {
        return this.computerDate;
    }

    public void setComputerDate(Date computerDate) {
        this.computerDate = computerDate;
    }

    @Column(name = "COMPUTER_REMARK", length = 50)
    public String getComputerRemark() {
        return this.computerRemark;
    }

    public void setComputerRemark(String computerRemark) {
        this.computerRemark = computerRemark;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "PROFESSION_DATE", length = 7)
    public Date getProfessionDate() {
        return this.professionDate;
    }

    public void setProfessionDate(Date professionDate) {
        this.professionDate = professionDate;
    }

    @Column(name = "PROFESSION_REMARK", length = 50)
    public String getProfessionRemark() {
        return this.professionRemark;
    }

    public void setProfessionRemark(String professionRemark) {
        this.professionRemark = professionRemark;
    }

    @Column(name = "DECLARE_LEVEL1", precision = 1, scale = 0)
    public Integer getDeclareLevel1() {
        return this.declareLevel1;
    }

    public void setDeclareLevel1(Integer declareLevel1) {
        this.declareLevel1 = declareLevel1;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DECLARE_DATE1", length = 7)
    public Date getDeclareDate1() {
        return this.declareDate1;
    }

    public void setDeclareDate1(Date declareDate1) {
        this.declareDate1 = declareDate1;
    }

    @Column(name = "DECLARE_LEVEL2", precision = 1, scale = 0)
    public Integer getDeclareLevel2() {
        return this.declareLevel2;
    }

    public void setDeclareLevel2(Integer declareLevel2) {
        this.declareLevel2 = declareLevel2;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DECLARE_DATE2", length = 7)
    public Date getDeclareDate2() {
        return this.declareDate2;
    }

    public void setDeclareDate2(Date declareDate2) {
        this.declareDate2 = declareDate2;
    }

    @Column(name = "ENGAGE_TITLE", length = 40)
    public String getEngageTitle() {
        return this.engageTitle;
    }

    public void setEngageTitle(String engageTitle) {
        this.engageTitle = engageTitle;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BEGIN_DATE", length = 7)
    public Date getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE", length = 7)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "ENGAGE_TIMES", length = 10)
    public String getEngageTimes() {
        return this.engageTimes;
    }

    public void setEngageTimes(String engageTimes) {
        this.engageTimes = engageTimes;
    }

    @Column(name = "REMARK", length = 50)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "PERSON_ID")
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}



}