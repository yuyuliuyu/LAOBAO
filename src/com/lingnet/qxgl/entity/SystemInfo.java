package com.lingnet.qxgl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "JC_SYSTEM_INFO")
public class SystemInfo extends BaseEntity implements java.io.Serializable  { 
    private static final long serialVersionUID = 1L;
    private String title;
    private String content;
    private Date fabutime;
    private Integer state;
    public SystemInfo() {
        super();
    }
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @Column(name = "FABUTIME")
    public Date getFabutime() {
        return fabutime;
    }
    public void setFabutime(Date fabutime) {
        this.fabutime = fabutime;
    }
    @Column(name = "STATE")
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
}
