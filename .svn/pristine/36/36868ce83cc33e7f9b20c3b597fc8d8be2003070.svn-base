package com.lingnet.hcm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * PositionGrade entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "POSITION_GRADE")
public class PositionGrade extends BaseEntity implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = -7109221677894505701L;
    private String companyId;//公司id
    private Integer sortNo;//序号
    private String name;//名称
    private String positionLayer;//职层
    private Integer isDelete;//删除标记 0未删除 1已删除

    // Constructors

    /** default constructor */
    public PositionGrade() {
    }


    /** full constructor */
    public PositionGrade(String id, Date createdate, Date modifydate,
            String companyId, Integer sortNo, String name, String positionLayer) {
        this.companyId = companyId;
        this.sortNo = sortNo;
        this.name = name;
        this.positionLayer = positionLayer;
    }

    // Property accessors

    @Column(name = "COMPANY_ID", length = 32)
    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Column(name = "SORT_NO", precision = 10, scale = 0)
    public Integer getSortNo() {
        return this.sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    @Column(name = "NAME", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "POSITION_LAYER", length = 100)
    public String getPositionLayer() {
        return this.positionLayer;
    }

    public void setPositionLayer(String positionLayer) {
        this.positionLayer = positionLayer;
    }

    @Column(name = "IS_DELETE", length = 100)
    public Integer getIsDelete() {
        return isDelete;
    }


    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}