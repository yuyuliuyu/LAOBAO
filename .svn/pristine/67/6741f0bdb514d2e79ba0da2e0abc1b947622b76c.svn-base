package com.lingnet.hcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;
@Entity
@Table(name = "AUTO_PROJECT_CODE")
public class AutoProjectCode  extends BaseEntity implements java.io.Serializable  {

    /**
     * 
     */
    private static final long serialVersionUID = 2279971500033832468L;
    private String startNo;//开始编号
    private Integer nowYear;//当前年
    
    public AutoProjectCode(){
    }
    
    public AutoProjectCode(String startNo, Integer nowYear) {
        this.startNo = startNo;
        this.nowYear = nowYear;
    }
    @Column(name = "START_NO")
    public String getStartNo() {
        return startNo;
    }
    public void setStartNo(String startNo) {
        this.startNo = startNo;
    }
    @Column(name = "NOW_YEAR")
    public Integer getNowYear() {
        return nowYear;
    }
    public void setNowYear(Integer nowYear) {
        this.nowYear = nowYear;
    }

}
