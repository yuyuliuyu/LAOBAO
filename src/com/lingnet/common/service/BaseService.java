package com.lingnet.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.Pager;


public interface BaseService<T, PK extends Serializable> {

    
    public Pager findPagerByOrder(Class<?> clazz, Pager pager, Order order,Criterion... criterions);
	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T get(String id);
	public <O> O get(Class<O> clazz, String id);

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param c 条件
	 * @return 实体对象
	 */
	public T get(Criterion c);
	public <O> O get(Class<O> clazz, Object... cList);

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param c 条件
	 * @return 实体对象
	 */
	public List<T> getList(Object... cList);
	public <O> List<O> getList(Class<O> clazz, Object... cList);

	public <O> List<O> getOrderList(Class<O> clazz, Order order,Object... cList);
	
	@SuppressWarnings("rawtypes")
    public <O> List<O> getFromList(Class<O> clazz, List list);
	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
//	public T load(PK id);
//	public <O> O load(Class<O> clazz, PK id);

	/**
	 * 获取所有实体对象集合
	 * 
	 * @return 实体对象集合
	 */
	public List<T> getAllList();
	public <O> List<O> getAllList(Class<O> clazz);
	
	/**
	 * 删除所有实体对象集合
	 */
	public void deleteAll();
	public <O> void deleteAll(Class<O> clazz);
	
	/**
	 * 根据条件删除实体对象
	 * @param c 条件
	 */
	public void deleteByCriteria(Criterion c);
	public <O> void deleteByCriteria(Class<O> clazz, Object... c);
	
	/**
	 * 获取所有实体对象总数
	 * 
	 * @return 实体对象总数
	 */
	public Long getTotalCount();

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	public PK save(T entity);

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	public void update(T entity);
	
	/**
	 * 删除实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return
	 */
	public void delete(T entity);

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	public void delete(PK id);

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	public <O> void delete(Class<O> clazz,PK[] ids);

	/**
	 * 刷新session
	 * 
	 */
	public void flush();

	/**
	 * 清除对象
	 * 
	 * @param object
	 *            需要清除的对象
	 */
	public void evict(Object object);
	
	/**
	 * 清除Session
	 * 
	 */
	public void clear();
	
	/**
	 * 根据Pager进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	//public Pager findPager(Pager pager);
	
	/**
	 * 根据Pager、Criterion进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 *            
	 * @param criterions
	 *            Criterion数组
	 * 
	 * @return Pager对象
	 */
	//public Pager findPager(Pager pager, Object... criterions);
	public Pager findPager(Class<?> clazz, Pager pager, Object... criterions);
	
	/**
	 * 根据Pager、Criterion进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 *            
	 * @param orders
	 *            Order数组
	 * 
	 * @return Pager对象
	 */
	//public Pager findPager(Pager pager, Order... orders);
	
	/**
	 * 根据Pager、Criteria进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 *            
	 * @param criteria
	 *            Criteria对象
	 * 
	 * @return Pager对象
	 */
	//public Pager findPager(Pager pager, Criteria criteria);
	/**
	 * @return the entityClass
	 */
	public Class<T> getEntityClass();

	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(Class<T> entityClass);
	public List<T> findBySql(String sql);
	public Pager findPagerBySql(Pager pager, String sql);
	public Pager findPagerBySql(Pager pager, String sql, HashMap<String, Object> mapPara);
	 public String operate(String djType,String czType,String czdj);
	 /**
	  * 
	  * @Title: getCurrentUser 
	  * @return 
	  * String 
	  * @author xuhp
	  * @since 2016-4-8 V 1.0
	  */
	public QxUsers getCurrentUser();
	/**
	 * 
	 * @Title: lock  
	 * void 
	 * @author xuhp
	 * @since 2016-4-8 V 1.0
	 */
	public void updateBatch(List<Object> objs);
	public void saveBatch(Collection<T> cols);
	/**
	 * 
	 * @Title: findPagerByOrders 
	 * @param clazz
	 * @param pager
	 * @param orders
	 * @param criterions
	 * @return 
	 * Pager 
	 * @author xuhp
	 * @since 2016-12-21 V 1.0
	 */
	public Pager findPagerByOrders(Class<?> clazz, Pager pager, Order[] orders,Criterion... criterions);
}
