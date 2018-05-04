package com.lingnet.hcm.entity.basic;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.lingnet.common.entity.BaseEntity;
/**
 * 缴费地区参数
 * @ClassName: PayAreas 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年4月19日 下午2:10:11 
 *
 */
@Entity
@Table(name = "JC_PAY_AREAS")
public class PayAreas extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String province;
    private String city;
    private Integer state;// 状态 0：无效 1：有效
    private String field1;
    private String field2;
    public PayAreas() {
        super(); 
    } 
    @Column(name = "AREA_CODE")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    @Column(name = "AREA_PROVINCE")
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    @Column(name = "AREA_CITY")
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Column(name = "AREA_STATE")
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
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
