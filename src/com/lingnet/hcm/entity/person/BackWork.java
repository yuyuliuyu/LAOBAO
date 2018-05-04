package com.lingnet.hcm.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * JcEmpRetire entity. 
 * @author MyEclipse Persistence Tools
 * 返聘实体类
 */
@Entity
@Table(name = "JC_EMP_BACK_WORK")
public class BackWork extends BaseEntity implements java.io.Serializable {

    // Fields
	private static final long serialVersionUID = 4674733589444799799L;
	private String jobNumber;//工号
	private String personId;//工号
    private Integer isRepost;//是否原岗位
    private String backFirm;//返聘公司
    private String backDep;//返聘部门
    private String backPost;//返聘标准岗位
    private String backSpPost;//返聘具体岗位
    
    private String fileNum;//文件编号
    private String url;//文件地址
    private String fileName;//文件名称
    private String remark;//
    private Date beginDate;//开始时间
    private Date endDate;//结束时间

    @Column(name = "JOB_NUMBER", length = 20)
    public String getJobNumber() {
        return this.jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }


    @Column(name = "FILE_NUM")
    public String getFileNum() {
        return this.fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum;
    }

    @Column(name = "URL", length = 50)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "FILE_NAME")
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "REMARK", length = 50)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	@Column(name = "IS_REPOST")
	public Integer getIsRepost() {
		return isRepost;
	}

	public void setIsRepost(Integer isRepost) {
		this.isRepost = isRepost;
	}

	@Column(name = "BACK_FIRM")
	public String getBackFirm() {
		return backFirm;
	}

	public void setBackFirm(String backFirm) {
		this.backFirm = backFirm;
	}

	@Column(name = "IS_DEP")
	public String getBackDep() {
		return backDep;
	}

	public void setBackDep(String backDep) {
		this.backDep = backDep;
	}

	@Column(name = "BACK_SP_POST")
	public String getBackSpPost() {
		return backSpPost;
	}

	public void setBackSpPost(String backSpPost) {
		this.backSpPost = backSpPost;
	}

	@Column(name = "BACK_POST")
	public String getBackPost() {
		return backPost;
	}

	public void setBackPost(String backPost) {
		this.backPost = backPost;
	}

	@Column(name = "BEGIN_DATE")
	public Date getBeginDate() {
		return beginDate;
	}

	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "PERSON_ID")
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}



}