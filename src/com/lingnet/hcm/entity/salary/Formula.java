package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 公式集合
 * XcFormula entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_FORMULA")
public class Formula extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 2591957373969989498L;
    private String pid;// 父级
    private String method;// 方法
    private String methodCn;// 中文方法
    private String methodEn;// 英文方法
    private String pubClass;// 类
    private String description;// 说明
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private Integer executeIndex;// 执行顺序
    private Integer lineIndex;// 排列顺序

    // Constructors

    /** default constructor */
    public Formula() {
    }

    @Column(name = "PID")
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Column(name = "METHOD", length = 1000)
    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Column(name = "METHOD_EN", length = 1000)
    public String getMethodEn() {
        return methodEn;
    }

    public void setMethodEn(String methodEn) {
        this.methodEn = methodEn;
    }

    @Column(name = "PUB_CLASS", length = 1000)
    public String getPubClass() {
        return this.pubClass;
    }

    public void setPubClass(String pubClass) {
        this.pubClass = pubClass;
    }

    @Column(name = "DESCRIPTION", length = 2000)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "METHOD_CN", length = 1000)
    public String getMethodCn() {
        return methodCn;
    }

    public void setMethodCn(String methodCn) {
        this.methodCn = methodCn;
    }

    @Column(name = "EXECUTE_INDEX", precision = 4, scale = 0)
    public Integer getExecuteIndex() {
        return executeIndex;
    }

    public void setExecuteIndex(Integer executeIndex) {
        this.executeIndex = executeIndex;
    }

    @Column(name = "LINE_INDEX")
    public Integer getLineIndex() {
        return lineIndex;
    }

    public void setLineIndex(Integer lineIndex) {
        this.lineIndex = lineIndex;
    }

}