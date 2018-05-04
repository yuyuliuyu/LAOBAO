package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 工资条
 * XcPartSalary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_PART_SALARY")
public class PartSalary extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -2591689276365731889L;
    private String partId;// 工资条ID
    private String itemId;// 薪资项目ID
    private Integer sx;// 顺序
    private Integer isZreo;// 值为0时显示项目 0:不是1：是
    private Integer isDelete;// 删除标志 0:未删除 1：删除
    private String note;// 描述

    // Constructors

    /** default constructor */
    public PartSalary() {
    }

    @Column(name = "PART_ID", length = 32)
    public String getPartId() {
        return this.partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    @Column(name = "ITEM_ID", length = 32)
    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Column(name = "SX", precision = 22, scale = 0)
    public Integer getSx() {
        return this.sx;
    }

    public void setSx(Integer sx) {
        this.sx = sx;
    }

    @Column(name = "IS_ZREO", precision = 22, scale = 0)
    public Integer getIsZreo() {
        return this.isZreo;
    }

    public void setIsZreo(Integer isZreo) {
        this.isZreo = isZreo;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "NOTE")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}