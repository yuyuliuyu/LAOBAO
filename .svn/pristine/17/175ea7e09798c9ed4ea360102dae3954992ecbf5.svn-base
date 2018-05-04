package com.lingnet.hcm.entity.basic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

@Entity
@Table(name = "JC_AREAS")
public class AreasInfo extends BaseEntity implements Serializable { 
    private static final long serialVersionUID = 1L;
    private String pid;
    private String name;
    private String value;
    public AreasInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Column(name = "PID")
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    @Column(name = "NAME")
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "VALUE")
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
