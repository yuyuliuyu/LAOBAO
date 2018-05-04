package com.lingnet.hcm.entity.salary;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * 工资档案表
 * XcSalaryRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_RECORD")
public class SalaryRecord extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 2307008670502149826L;
    private String staffId;// 职工ID
    private Integer isBzgs;// 是否标准工时
    private Integer isSpecialHour;// 是否特殊工时
    private Integer jsType;// 员工缴税类型 1:本国员工 2：外籍员工 3：劳务员工 4：免税员工
    private Date startDate;// 开始日期
    private Date effectDate;// 异动薪酬生效日期
    private String filmName;// 单位
    private String deptname;// 部门
    private String classGroup;// 班组
    private String kqname;// 考勤单位
    private String kqdept;// 考勤部门
    private String classNo;// 考勤班组
    private String post;// 标准岗位
    private String specificPost;// 具体岗位
    private String salaryPost;// 薪酬岗位
    private String salaryPostName;// 薪酬岗位名称
    private Integer isJz;// 是否兼职 0：不是兼职 1：是兼职
    private Integer fpqx;// 分配权限 1：公司(不下发) 2：基层
    private Integer zfxs;// 支付形式 1：自发薪（可以发薪，报盘文件体现，只有最后发薪的时候约束，是否有发薪记录） 2：股东劳务费
    private Integer gzxs;// 工资形式 0：计时1：计件 2:联产
    private Integer specialMark;// 特殊标记
    private Integer kjgl;// 扣减工龄
    private Date gdDate;// 股东双方日期

    // Constructors

    /** default constructor */
    public SalaryRecord() {
    }

    @Column(name = "STAFF_ID")
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "IS_BZGS", precision = 1, scale = 0)
    public Integer getIsBzgs() {
        return this.isBzgs;
    }

    public void setIsBzgs(Integer isBzgs) {
        this.isBzgs = isBzgs;
    }

    @Column(name = "IS_SPECIAL_HOUR", precision = 1, scale = 0)
    public Integer getIsSpecialHour() {
        return isSpecialHour;
    }

    public void setIsSpecialHour(Integer isSpecialHour) {
        this.isSpecialHour = isSpecialHour;
    }

    @Column(name = "JS_TYPE", precision = 1, scale = 0)
    public Integer getJsType() {
        return jsType;
    }

    public void setJsType(Integer jsType) {
        this.jsType = jsType;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", length = 7)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "EFFECT_DATE", length = 7)
    public Date getEffectDate() {
        return this.effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    @Column(name = "FILM_NAME")
    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    @Column(name = "DEPTNAME")
    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    @Column(name = "CLASS_GROUP")
    public String getClassGroup() {
        return classGroup;
    }

    public void setClassGroup(String classGroup) {
        this.classGroup = classGroup;
    }

    @Column(name = "KQNAME")
    public String getKqname() {
        return kqname;
    }

    public void setKqname(String kqname) {
        this.kqname = kqname;
    }

    @Column(name="KQDEPT")
    public String getKqdept() {
        return kqdept;
    }

    public void setKqdept(String kqdept) {
        this.kqdept = kqdept;
    }

    @Column(name = "CLASS_NO")
    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    @Column(name = "POST")
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Column(name = "SALARY_POST")
    public String getSalaryPost() {
        return salaryPost;
    }

    public void setSalaryPost(String salaryPost) {
        this.salaryPost = salaryPost;
    }

    @Column(name = "SALARY_POST_NAME")
    public String getSalaryPostName() {
        return salaryPostName;
    }

    public void setSalaryPostName(String salaryPostName) {
        this.salaryPostName = salaryPostName;
    }

    @Column(name = "SPECIFIC_POST")
    public String getSpecificPost() {
        return specificPost;
    }

    public void setSpecificPost(String specificPost) {
        this.specificPost = specificPost;
    }

    @Column(name = "IS_JZ", precision = 1, scale = 0)
    public Integer getIsJz() {
        return isJz;
    }

    public void setIsJz(Integer isJz) {
        this.isJz = isJz;
    }

    @Column(name = "FPQX", precision = 1, scale = 0)
    public Integer getFpqx() {
        return fpqx;
    }

    public void setFpqx(Integer fpqx) {
        this.fpqx = fpqx;
    }

    @Column(name = "ZFXS", precision = 1, scale = 0)
    public Integer getZfxs() {
        return zfxs;
    }

    public void setZfxs(Integer zfxs) {
        this.zfxs = zfxs;
    }

    @Column(name = "GZXS", precision = 1, scale = 0)
    public Integer getGzxs() {
        return gzxs;
    }

    public void setGzxs(Integer gzxs) {
        this.gzxs = gzxs;
    }

    @Column(name = "SPECIAL_MARK", precision = 2, scale = 0)
    public Integer getSpecialMark() {
        return specialMark;
    }

    public void setSpecialMark(Integer specialMark) {
        this.specialMark = specialMark;
    }

    @Column(name = "KJGL", precision = 3, scale = 0)
    public Integer getKjgl() {
        return kjgl;
    }

    public void setKjgl(Integer kjgl) {
        this.kjgl = kjgl;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "GD_DATE", length = 7)
    public Date getGdDate() {
        return gdDate;
    }

    public void setGdDate(Date gdDate) {
        this.gdDate = gdDate;
    }

}