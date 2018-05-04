package com.lingnet.hcm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * DeptChangeId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DEPT_CHANGE")
public class DeptChange extends BaseEntity implements java.io.Serializable {

	// Fields

	/**
     * 
     */
    private static final long serialVersionUID = 4769249795299767648L;
    private String middleId;//中间表id
    private Integer is_function; //是否为功能  0为不是；1为是/1显示
    private String name;
    private String description;
    private String parent;//父级id
    private String barchId;//组织id
    private String mappingId;//映射
    private String input_code; //拼音输入码
    private Integer xh;//序号
    private Integer sjbggs;//格式 
    private String jcdd;//地点
    private String jklx;//
    private String ksh;//
    private String imgpath;//图片路径
    private Integer isDelete;//是否删除 0 未删除 1 已删除
    private String reportPathHealth; //
    private String reportPathDisease; //
    private Integer addPicBefore;//
    private Integer reportSort;//
    private Date setupDate;//创立时间
    private String duty;//工作职责
    private String leader;//部门负责人
    private String phone;//办公电话
    private String email;//邮箱
    private String honor;//荣誉
    private String remark;//备注
    private String flg;//标示为1

	// Constructors

    /** default constructor */
    public DeptChange() {
    }

    /** full constructor */
    public DeptChange(String name, String description,
            String parent) {
        this.name = name;
        this.description = description;
        this.parent = parent;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParent() {
        return this.parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
  
    
    @Column(name="is_function")
    public Integer getIs_function() {
        return is_function;
    }

    public void setIs_function(Integer is_function) {
        this.is_function = is_function;
    }

    @Column(name="input_code",length=50)
    public String getInput_code() {
        return input_code;
    }

    public void setInput_code(String input_code) {
        this.input_code = input_code;
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
    @Column(name="imgpath",length=120)
    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
    @Column(name="is_delete")
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name="REPORT_PATH_HEALTH")
    public String getReportPathHealth() {
        return reportPathHealth;
    }

    public void setReportPathHealth(String reportPathHealth) {
        this.reportPathHealth = reportPathHealth;
    }
    @Column(name="REPORT_PATH_DISEASE")
    public String getReportPathDisease() {
        return reportPathDisease;
    }

    public void setReportPathDisease(String reportPathDisease) {
        this.reportPathDisease = reportPathDisease;
    }
     @Column(name="ADD_PIC_BEFORE")
    public Integer getAddPicBefore() {
        return addPicBefore;
    }

    public void setAddPicBefore(Integer addPicBefore) {
        this.addPicBefore = addPicBefore;
    }
    @Column(name="REPORT_SORT")
    public Integer getReportSort() {
        return reportSort;
    }

    public void setReportSort(Integer reportSort) {
        this.reportSort = reportSort;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    @Column(name="SETUP_DATE")
    public Date getSetupDate() {
        return setupDate;
    }

    public void setSetupDate(Date setupDate) {
        this.setupDate = setupDate;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name="BARCH_ID")
    public String getBarchId() {
        return barchId;
    }

    public void setBarchId(String barchId) {
        this.barchId = barchId;
    }

    @Column(name="MAPPING_ID")
    public String getMappingId() {
        return mappingId;
    }

    public void setMappingId(String mappingId) {
        this.mappingId = mappingId;
    }

    @Column(name="FLG")
    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }
    @Column(name="MIDDLE_ID")
    public String getMiddleId() {
        return middleId;
    }

    public void setMiddleId(String middleId) {
        this.middleId = middleId;
    }
    

}