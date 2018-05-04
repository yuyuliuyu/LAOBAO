package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 薪资类别与薪资项目关联表
 * XcSalaryItemAndType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_ITEM_AND_TYPE")
public class SalaryItemAndType extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -1089211799753021929L;

    private String typeId;// 薪资类型ID
    private String itemId;// 薪资项目ID
    private Integer isDelete;// 0:未删除 1：删除

    // Constructors

    /** default constructor */
    public SalaryItemAndType() {
    }

    @Column(name = "TYPE_ID", length = 32)
    public String getTypeId() {
        return this.typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Column(name = "ITEM_ID", length = 32)
    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}