package com.lingnet.hcm.entity.basic;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;
/**
 * 地区工资信息
 * @ClassName: SocialWage 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年4月19日 下午2:15:56 
 *
 */
@Entity
@Table(name = "JC_SOCIAL_WAGE")
public class SocialWage extends BaseEntity implements Serializable {  
    private static final long serialVersionUID = 1L;
    private String pid;
    private String type;
    private String year;
    private Double wagemoney;
    private Date startdate;
    private Date enddate;
    private String field1;
    private String field2;

    public SocialWage() {
        super(); 
    }  
    @Column(name = "PID")
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    @Column(name = "WAGE_TYPE")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    } 
    @Column(name = "WAGE_YEAR")
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    @Column(name = "WAGE_MONEY")
    public Double getWagemoney() {
        return wagemoney;
    }
    public void setWagemoney(Double wagemoney) {
        this.wagemoney = wagemoney;
    }
    @Column(name = "WAGE_SATRT")
    public Date getStartdate() {
        return startdate;
    }
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }
    @Column(name = "WAGE_END")
    public Date getEnddate() {
        return enddate;
    }
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
    @Column(name = "FIELD1")
    public String getField1() {
       return field1;
    }
    public void setField1(String field1) {
        this.field1 = field1;
    }
    @Column(name = "FIELD2")
    public String getField2() {
        return field2;
    }
    public void setField2(String field2) {
        this.field2 = field2;
    }
}
