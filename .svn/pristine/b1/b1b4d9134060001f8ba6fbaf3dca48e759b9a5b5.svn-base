package com.lingnet.qxgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "JC_WORK_BENCH")
public class WorkBench extends BaseEntity implements java.io.Serializable  { 
    private static final long serialVersionUID = 1L;
    private String title;
    private String content;
    private String pageURL;
    private String dataURL;
    private String memo;
    private Integer state;   //0：关闭状态     1：开启状态
    public WorkBench() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Column(name = "TITLE")
    public String getTitle(){
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
    @Column(name = "PAGE_URL")
    public String getPageURL() {
        return pageURL;
    }
    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }
    @Column(name = "DATA_URL")
    public String getDataURL() {
        return dataURL;
    }
    public void setDataURL(String dataURL) {
        this.dataURL = dataURL;
    }
    @Column(name = "MEMO")
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    @Column(name = "STATE")
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
}
