package com.lingnet.hcm.entity.salary;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * 个人商业保险缴费
 * XcSalaryPerInsurance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_PER_INSURANCE")
public class SalaryPerInsurance extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 4099086105784981052L;
    private String staffId;// 员工ID
    private String deptId;// 部门ID
    private Integer year;// 年份
    private String ibfId;// 福利项目ID
    private String bf;// 保额（万元） 
    private String bxfl;// 费率(‰)
    private String gsjfje;// 公司缴费金额（元）
    private String grjfje;// 个人缴费金额（元）
    private Date beginDate;// 缴费日期
    private String bbxr;// 被保险人
    private String ytbrgx;// 与投保人关系
    private String idCard;// 证件号
    private Integer sex;// 性别
    private Date birthDate;// 被保险人出生日期
    private String note;// 备注
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String field1;
    private String field2;

    // Constructors

    /** default constructor */
    public SalaryPerInsurance() {
    }


    @Column(name = "STAFF_ID", length = 32)
    public String getStaffId() {
        return this.staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Column(name = "DEPT_ID", length = 32)
    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Column(name = "YEAR", precision = 4, scale = 0)
    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Column(name = "IBF_ID", length = 32)
    public String getIbfId() {
        return this.ibfId;
    }

    public void setIbfId(String ibfId) {
        this.ibfId = ibfId;
    }

    @Column(name = "BF", length = 20)
    public String getBf() {
        return this.bf;
    }

    public void setBf(String bf) {
        this.bf = bf;
    }

    @Column(name = "BXFL", length = 20)
    public String getBxfl() {
        return this.bxfl;
    }

    public void setBxfl(String bxfl) {
        this.bxfl = bxfl;
    }

    @Column(name = "GSJFJE", length = 20)
    public String getGsjfje() {
        return this.gsjfje;
    }

    public void setGsjfje(String gsjfje) {
        this.gsjfje = gsjfje;
    }

    @Column(name = "GRJFJE", length = 20)
    public String getGrjfje() {
        return this.grjfje;
    }

    public void setGrjfje(String grjfje) {
        this.grjfje = grjfje;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BEGIN_DATE", length = 7)
    public Date getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @Column(name = "BBXR", length = 50)
    public String getBbxr() {
        return this.bbxr;
    }

    public void setBbxr(String bbxr) {
        this.bbxr = bbxr;
    }

    @Column(name = "YTBRGX", length = 50)
    public String getYtbrgx() {
        return this.ytbrgx;
    }

    public void setYtbrgx(String ytbrgx) {
        this.ytbrgx = ytbrgx;
    }

    @Column(name = "ID_CARD", length = 18)
    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Column(name = "SEX", precision = 1, scale = 0)
    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE", length = 7)
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "NOTE", length = 2000)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "FIELD1", length = 2000)
    public String getField1() {
        return this.field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    @Column(name = "FIELD2", length = 2000)
    public String getField2() {
        return this.field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return isDelete;
    }


    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}