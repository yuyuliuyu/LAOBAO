package com.lingnet.common.entity;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.lingnet.util.ReflectionUtil;

@MappedSuperclass
public class BaseEntity {

	public static final String CREATE_DATE_PROPERTY_NAME = "createDate";// "创建日期"属性名称
	public static final String MODIFY_DATE_PROPERTY_NAME = "modifyDate";// "修改日期"属性名称
	public static final String ON_SAVE_METHOD_NAME = "onSave";// "保存"方法名称
	public static final String ON_UPDATE_METHOD_NAME = "onUpdate";// "更新"方法名称

	private String id; // ID
	private Date createDate;// 创建时间
	protected Date modifyDate;// 修改日期

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Transient
	public void onSave() {
	}

	@Transient
	public void onUpdate() { 
	}
	
	@SuppressWarnings("unchecked")
	public synchronized void copyFrom(BaseEntity bean) {

		if(bean == null) {
			return;
		}
		Class<BaseEntity> clazz = (Class<BaseEntity>)bean.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field f : fields) {
			if("serialVersionUID,id,".indexOf(f.getName() + ",") >= 0) {
				continue;
			}
			Object value = ReflectionUtil.invokeGetterMethod(bean, f.getName());
			if(value != null&&!value.equals("null")) {
				ReflectionUtil.invokeSetterMethod(this, f.getName(), value);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void copyFromByFieldList(BaseEntity bean, List<String> fieldList) {

		if(bean == null) {
			return;
		}
		Class<BaseEntity> clazz = (Class<BaseEntity>)bean.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field f : fields) {
			if("serialVersionUID,id,".indexOf(f.getName() + ",") >= 0) {
				continue;
			}
			Object value = ReflectionUtil.invokeGetterMethod(bean, f.getName());
			ReflectionUtil.invokeSetterMethodByFieldList(this, f.getName(), value, f.getType(), fieldList);
		}
	}
}
