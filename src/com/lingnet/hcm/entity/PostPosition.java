package com.lingnet.hcm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * PostPosition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "POST_POSITION")
public class PostPosition extends BaseEntity implements java.io.Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 2539556961237162858L;
    // Fields

	private String dutyId;//职务族id
	private String positionName;//岗位名称
	private String positionCode;//编码
	private String positionGrad;//职等
	private String positionnature;//xingzhi
	private String positioncategory;//leibie    
	private String jobContent;//工作内容
	private String education;//学历
	private Date applyDate;//生效日期
	private Integer state;//状态
	private String profession;//专业
	private String marriage;//婚姻
	private String foreign;//外语
	private String residence;//户籍
	private String sex;//性别
	private String proKnowledge;//专业知识
	private String wsq;//技能资格
	private String elegance;//形象气质
	private String wordExpression;//文字表达
	private Integer minAge;//最小年龄
	private Integer maxAge;//最大年龄
	private Double minHeight;//最小身高
	private Double maxHeight;//最大身高
	private Integer minWorkSeniority;//最小工作年限
	private Integer maxWorkSeniority;//最大工作年限
	private String degree;//学位
	private String remark;//其他信息
	private Integer isDelete;


	// Constructors

	/** default constructor */
	public PostPosition() {
	}


	/** full constructor */
	public PostPosition(String id, Date createdate, Date modifydate,
			String dutyId, String positionName, String positionGrad,
			String jobContent, String education, Date applyDate, Integer state,
			String profession, String marriage, String foreign,
			String residence, String sex, String proKnowledge, String wsq,
			String elegance, String wordExpression, Integer minAge, Integer maxAge,
			Double minHeight, Double maxHeight, Integer minWorkSeniority,
			Integer maxWorkSeniority, String degree) {
		this.dutyId = dutyId;
		this.setPositionName(positionName);
		this.positionGrad = positionGrad;
		this.jobContent = jobContent;
		this.education = education;
		this.applyDate = applyDate;
		this.state = state;
		this.profession = profession;
		this.marriage = marriage;
		this.foreign = foreign;
		this.residence = residence;
		this.sex = sex;
		this.proKnowledge = proKnowledge;
		this.wsq = wsq;
		this.elegance = elegance;
		this.wordExpression = wordExpression;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.minWorkSeniority = minWorkSeniority;
		this.maxWorkSeniority = maxWorkSeniority;
		this.degree = degree;
	}

	// Property accessors

	@Column(name = "DUTY_ID", length = 32)
	public String getDutyId() {
		return this.dutyId;
	}

	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}
	@Column(name = "POSITION_GRAD", length = 50)
	public String getPositionGrad() {
		return this.positionGrad;
	}
	public void setPositionGrad(String positionGrad) {
		this.positionGrad = positionGrad;
	}
	@Column(name = "JOB_CONTENT")
	public String getJobContent() {
		return this.jobContent;
	}
	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}
	@Column(name = "EDUCATION", length = 50)
	public String getEducation() {
		return this.education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "APPLY_DATE", length = 7)
	public Date getApplyDate() {
		return this.applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	@Column(name = "STATE", precision = 1, scale = 0)
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Column(name = "PROFESSION", length = 50)
	public String getProfession() {
		return this.profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	@Column(name = "MARRIAGE", length = 50)
	public String getMarriage() {
		return this.marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	@Column(name = "FOREIGN", length = 50)
	public String getForeign() {
		return this.foreign;
	}
	public void setForeign(String foreign) {
		this.foreign = foreign;
	}
	@Column(name = "RESIDENCE", length = 50)
	public String getResidence() {
		return this.residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	@Column(name = "SEX", length = 50)
	public String getSex() {
		return this.sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "PRO_KNOWLEDGE")
	public String getProKnowledge() {
		return this.proKnowledge;
	}
	public void setProKnowledge(String proKnowledge) {
		this.proKnowledge = proKnowledge;
	}
	@Column(name = "WSQ")
	public String getWsq() {
		return this.wsq;
	}
	public void setWsq(String wsq) {
		this.wsq = wsq;
	}
	@Column(name = "ELEGANCE", length = 50)
	public String getElegance() {
		return this.elegance;
	}
	public void setElegance(String elegance) {
		this.elegance = elegance;
	}
	@Column(name = "WORD_EXPRESSION")
	public String getWordExpression() {
		return this.wordExpression;
	}
	public void setWordExpression(String wordExpression) {
		this.wordExpression = wordExpression;
	}
	@Column(name = "MIN_AGE", precision = 10, scale = 0)
	public Integer getMinAge() {
		return this.minAge;
	}
	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}
	@Column(name = "MAX_AGE", precision = 10, scale = 0)
	public Integer getMaxAge() {
		return this.maxAge;
	}
	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}
	@Column(name = "MIN_HEIGHT", precision = 10)
	public Double getMinHeight() {
		return this.minHeight;
	}
	public void setMinHeight(Double minHeight) {
		this.minHeight = minHeight;
	}
	@Column(name = "MAX_HEIGHT", precision = 10)
	public Double getMaxHeight() {
		return this.maxHeight;
	}
	public void setMaxHeight(Double maxHeight) {
		this.maxHeight = maxHeight;
	}
	@Column(name = "MIN_WORK_SENIORITY", precision = 10, scale = 0)
	public Integer getMinWorkSeniority() {
		return this.minWorkSeniority;
	}
	public void setMinWorkSeniority(Integer minWorkSeniority) {
		this.minWorkSeniority = minWorkSeniority;
	}
	@Column(name = "MAX_WORK_SENIORITY", precision = 10, scale = 0)
	public Integer getMaxWorkSeniority() {
		return this.maxWorkSeniority;
	}
	public void setMaxWorkSeniority(Integer maxWorkSeniority) {
		this.maxWorkSeniority = maxWorkSeniority;
	}
	@Column(name = "DEGREE", length = 50)
	public String getDegree() {
		return this.degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	@Column(name = "POSITION_CODE", length = 50)
    public String getPositionCode() {
        return positionCode;
    }
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
    @Column(name = "POSITION_NAME", length = 50)
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    @Column(name = "REMARK", length = 200)
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Column(name = "IS_DELETE")
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    } 
    @Column(name = "POSITION_NATURE")
    public String getPositionnature() {
        return positionnature;
    }
    public void setPositionnature(String positionnature) {
        this.positionnature = positionnature;
    }
    @Column(name = "POSITION_CATEGORY")
    public String getPositioncategory() {
        return positioncategory;
    }
    public void setPositioncategory(String positioncategory) {
        this.positioncategory = positioncategory;
    }


   
}