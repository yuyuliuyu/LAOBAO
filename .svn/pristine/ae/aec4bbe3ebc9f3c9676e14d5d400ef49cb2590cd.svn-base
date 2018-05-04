package com.lingnet.hcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;
/**
 * 
 * @ClassName: WorkFlowChild 
 * @Description: 工作流节点实体类 
 * @author wangqiang
 * @date 2017年5月3日 上午8:26:52 
 *
 */
@Entity
@Table(name = "JC_WORK_FLOWC")
public class WorkFlowChild  extends BaseEntity implements java.io.Serializable  { 
	
    private static final long serialVersionUID = 1L;
    
    private Integer sort;// 审批层级
    private String appman;//审批人
    private String appid;//审批人id
    private String appdeptname;//审批人部门名称
    private String appdeptid;//审批人部门id
    private Integer isCheckEdit;// 审核后可以编辑 0：否 1：是
    private String pid;//流程id
    
    public WorkFlowChild() {
        super();
    }
    
    @Column(name = "SORT")
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    @Column(name = "APRO_MAN")
    public String getAppman() {
        return appman;
    }
    public void setAppman(String appman) {
        this.appman = appman;
    }
    @Column(name = "APRO_ID")
    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    @Column(name = "APRO_DEPTNAME")
    public String getAppdeptname() {
        return appdeptname;
    }
    public void setAppdeptname(String appdeptname) {
        this.appdeptname = appdeptname;
    }
    @Column(name = "APRO_DEPTID")
    public String getAppdeptid() {
        return appdeptid;
    }
    public void setAppdeptid(String appdeptid) {
        this.appdeptid = appdeptid;
    }
    @Column(name = "PID")
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }

    @Column(name = "IS_CHECK_EDIT", length = 1)
    public Integer getIsCheckEdit() {
        return isCheckEdit;
    }

    public void setIsCheckEdit(Integer isCheckEdit) {
        this.isCheckEdit = isCheckEdit;
    }
    
}
