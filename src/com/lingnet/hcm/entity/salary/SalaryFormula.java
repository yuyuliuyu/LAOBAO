package com.lingnet.hcm.entity.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 计算公式表
 * XcSalaryFormula entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XC_SALARY_FORMULA")
public class SalaryFormula extends BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = -4349208196153372550L;
    private String bindId;// 绑定ID
    private String content;// 内容
    private String cntContent;// 解析内容
    private String cntMath;// 解析公式
    private Integer isDefault;// 默认公式 0:不默认1：默认公式
    private Integer isDelete;// 删除标志 0:未删除 1：删除

    // Constructors

    /** default constructor */
    public SalaryFormula() {
    }

    // Property accessors
    @Column(name = "BIND_ID", length = 32)
    public String getBindId() {
        return this.bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    @Column(name = "CONTENT")
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "IS_DEFAULT", precision = 22, scale = 0)
    public Integer getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Column(name = "IS_DELETE", precision = 1, scale = 0)
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "CNT_MATH")
    public String getCntMath() {
        return cntMath;
    }

    public void setCntMath(String cntMath) {
        this.cntMath = cntMath;
    }

    @Column(name = "CNT_CONTENT")
    public String getCntContent() {
        return cntContent;
    }

    public void setCntContent(String cntContent) {
        this.cntContent = cntContent;
    }

}