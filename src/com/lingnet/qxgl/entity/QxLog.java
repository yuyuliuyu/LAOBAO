package com.lingnet.qxgl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lingnet.common.entity.BaseEntity;

/**
 * 
 * @ClassName: QxLog 
 * @Description: 日志管理 
 * @author 姜平豹
 * @date 2014-3-26 上午10:02:29 
 *
 */
@Entity
@Table(name = "QX_LOG")
public class QxLog extends BaseEntity implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = -4479932872034312275L;
    private String czUser;//操作用户
    private Date czDate;//操作时间
    private String djType;//菜单名称
    private String czType;//操作类型
    private String czObject;//操作对象
    private String ipDz;//ip地址
    private String pcName;//电脑名称
    private String rmk;//备注
    private String czdj;//操作的哪张单据
    private String fzx;//分中心名称

    // Constructors

    /** default constructor */
    public QxLog() {
    }


    /** full constructor */
    public QxLog(String id, String czUser, Date czDate, String djType,
            String czType, String czObject, String ipDz, String pcName,
            String rmk, Date createdate, Date modifydate,String czdj) {
        this.czUser = czUser;
        this.czDate = czDate;
        this.djType = djType;
        this.czType = czType;
        this.czObject = czObject;
        this.ipDz = ipDz;
        this.pcName = pcName;
        this.rmk = rmk;
        this.czdj =czdj;
    }


    @Column(name = "CZ_USER", length = 32)
    public String getCzUser() {
        return this.czUser;
    }

    public void setCzUser(String czUser) {
        this.czUser = czUser;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CZ_DATE", length = 7)
    public Date getCzDate() {
        return this.czDate;
    }

    public void setCzDate(Date czDate) {
        this.czDate = czDate;
    }

    @Column(name = "DJ_TYPE", length = 32)
    public String getDjType() {
        return this.djType;
    }

    public void setDjType(String djType) {
        this.djType = djType;
    }

    @Column(name = "CZ_TYPE", length = 32)
    public String getCzType() {
        return this.czType;
    }

    public void setCzType(String czType) {
        this.czType = czType;
    }

    @Column(name = "CZ_OBJECT", length = 50)
    public String getCzObject() {
        return this.czObject;
    }

    public void setCzObject(String czObject) {
        this.czObject = czObject;
    }

    @Column(name = "IP_DZ", length = 50)
    public String getIpDz() {
        return this.ipDz;
    }

    public void setIpDz(String ipDz) {
        this.ipDz = ipDz;
    }

    @Column(name = "PC_NAME", length = 50)
    public String getPcName() {
        return this.pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    @Column(name = "RMK")
    public String getRmk() {
        return this.rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }


    @Column(name = "CZDJ", length = 50)
    public String getCzdj() {
        return czdj;
    }


    /**
     * @param czdj the czdj to set
     */
    public void setCzdj(String czdj) {
        this.czdj = czdj;
    }

	public String getFzx() {
		return fzx;
	}

	public void setFzx(String fzx) {
		this.fzx = fzx;
	}

}