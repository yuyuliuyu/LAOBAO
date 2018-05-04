/**
 * @QxUserDatauth.java
 * @com.lingnet.qxgl.entity
 * @Description：
 * 
 * @author xuhp 
 * @copyright  2017
 * @version V
 * @since 2017-3-16
 */
package com.lingnet.qxgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/** 
 * @ClassName: QxUserDatauth 
 * @Description: QxUserDatauth 
 * @author xuhp
 * @date 2017-3-16 下午1:30:33 
 *  
 */
@Entity
@Table(name="QX_USER_DATAUTH")
public class QxUserDatauth extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 2206209402031486671L;
	private String userid;//用户ID
	private String branchDep;//单位或部门ID
	private String flg;//1部门 0单位
	
	//////////////////////////////
	public QxUserDatauth() {
		super();
	}
	public QxUserDatauth(String userid, String branchDep, String flg) {
		super();
		this.userid = userid;
		this.branchDep = branchDep;
		this.flg = flg;
	}
	////////////////////////
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Column(name="BRANCH_DEP")
	public String getBranchDep() {
		return branchDep;
	}
	public void setBranchDep(String branchDep) {
		this.branchDep = branchDep;
	}
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	
}
