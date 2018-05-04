package com.lingnet.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataAccessException;

import com.lingnet.util.Pager;

@SuppressWarnings({ "rawtypes" })
public interface BaseDao<T, PK extends Serializable> {

    
    public Pager findPagerByOrder(Pager pager, Order order,Criterion... criterions);
	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T get(String id);
	
	/**
	 * 根据ID获取实体对象
	 * @param c 条件
	 */
	public T get(Object... cList);
	
	/**
	 * 根据ID获取实体对象
	 * @param c 条件
	 */
	public List<T> getList(Object... c);
	/**
     * 根据ID获取实体对象
     * @param c 条件
     */
	public List<T> getFromList(List list);
	/**
     * 根据ID获取实体对象
     * @param c 条件
     */
	public List<T> getOrderList(Order Order,Object... c);
	
	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
//	public T load(PK id);

	/**
	 * 获取所有实体对象集合
	 * 
	 * @return 实体对象集合
	 */
	public List<T> getAllList();
	
	/**
	 * 获取最大值
	 * @param fieldName
	 * @return
	 */
	public String getMax(String fieldName, String likeStr);
	
	/**
	 * 删除所有实体对象集合
	 */
	public void deleteAll();
	
	/**
	 * 根据条件删除实体对象
	 * @param c 条件
	 */
	public void deleteByCriteria(Object... c);

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
	public PK save(Object entity);

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	public void update(Object entity);

	/**
	 * 删除实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return
	 */
	public void delete(Object entity);

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
	public void delete(PK[] ids);

	/**
	 * 根据HQL获得实体对象集合
	 * 
	 * @param HQL
	 *            HQL语句
	 * @return
	 */
	public List<T> find(String HQL);

	/**
	 * 根据HQL获得实体对象集合
	 * 
	 * @param HQL
	 *            HQL语句
	 * @param parameters
	 *            可变参数
	 * @return
	 */
	public List<T> find(String HQL, Object... parameters);

	/**
	 * 根据HQL获得实体对象集合中的唯一结果
	 * 
	 * @param HQL
	 *            HQL语句
	 * @param parameters
	 *            可变参数
	 * @return
	 * @throws DataAccessException
	 */
	public T uniqueResult(final String HQL, final Object... parameters)
			throws DataAccessException;

	/**
	 * 根据Pager进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	public Pager findPager(Pager pager);

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
	public Pager findPager(Pager pager, Object... criterions);

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
	public Pager findPager(Pager pager, Order... orders);

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
	public Pager findPager(Pager pager, Criteria criteria);

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
	

	public Class getEntityClass();

	public void setEntityClass(Class entityClass);
	public List<T> findBySql(String sql);
	public Pager findPagerBySql(Pager pager, String sql);
	public Pager findPagerBySql(Pager pager, String sql, HashMap<String, Object> mapPara);
	public void saveBatch(Collection<T> cols);
	/**
	 * 
	 * @Title: findPagerByOrders 
	 * @param pager
	 * @param orders
	 * @param criterions
	 * @return 
	 * Pager 
	 * @author xuhp
	 * @since 2016-12-21 V 1.0
	 */
	public Pager findPagerByOrders(Pager pager, Order[] orders,Criterion... criterions);
}
