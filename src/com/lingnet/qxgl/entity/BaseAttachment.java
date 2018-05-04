package com.lingnet.qxgl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lingnet.common.entity.BaseEntity;

/**
 * 附件
 *
 */
@Entity
@Table(name="BASEATTACHMENT")
public class BaseAttachment extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -558926187919941310L;

	private String parentId;//关联数据id
	private String modelName;//模块名称
	private int sort;//序号
	private String path;//路径
	private String type;//说明
	private String creatId;//上传人id
	
	@Column(name="FILE_PATH")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Column(name="FILE_TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="CREATE_ID")
	public String getCreatId() {
		return creatId;
	}
	public void setCreatId(String creatId) {
		this.creatId = creatId;
	}
	
	@Column(name="PARENT_ID")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Column(name="MODEL_NAME")
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	@Column(name="FILE_SORT")
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
	
}
