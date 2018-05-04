package com.lingnet.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.impl.CriteriaImpl.OrderEntry;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.common.entity.BaseEntity;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ReflectionUtil;
import com.lingnet.util.ThreadLocalHolder;

@Repository("baseDaoInit")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseDaoImplInit<T, PK extends Serializable> extends
        HibernateDaoSupport implements BaseDao<T, PK> {

    
    private static final String ORDER_LIST_PROPERTY_NAME = "orderList";// "排序"属性名称
    private static final String CREATE_DATE_PROPERTY_NAME = "createDate";// "创建日期"属性名称
    private Class<T> entityClass;
    
    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        HashMap map = new HashMap();
        map.put("entityClass", entityClass);
        ThreadLocalHolder.setEntMap(map);
        this.entityClass = entityClass;
    }

    // 获得泛型的实体对象
    public BaseDaoImplInit() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type)
                    .getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
            
        }
    }

    // 重写父类的方法
    @Resource(name = "sessionFactory")
    public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public T get(String id) {
        return this.getHibernateTemplate().get(entityClass, id==null?"":id);
    }

    public synchronized T get(Object... cList) {
        
        Criteria criteria = getSession().createCriteria(entityClass);
           
            for(Object c : cList) {
                criteria.add((Criterion)c);
            }
        criteria.addOrder(Order.desc(CREATE_DATE_PROPERTY_NAME));
        List resultList = criteria.list();
        if(resultList.size()==0) {
            return null;
        } else {
            return (T)resultList.get(0);
        }
    }

    /**
     * 出现映射错误问题，禁用2016-12-26
     * 
     * **/
    public List<T> getList(Object... cList) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for(Object c : cList) {
            criteria.add((Criterion)c);
        }
        return (List<T>)criteria.list();
    }
    
    public List<T> getOrderList(Order order , Object... cList) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for(Object c : cList) {
            criteria.add((Criterion)c);
        }
        criteria.addOrder(order);
        return (List<T>)criteria.list();
    }
    
       public Pager findPagerByOrder(Pager pager, Order order,Criterion... criterions) {
            Criteria criteria = getSession().createCriteria(entityClass);
            if(criterions != null) {
                for (Object criterion : criterions) {
                    if(criterion==null)continue;
                    criteria.add((Criterion)criterion);
                    
                }
            }
            criteria.addOrder(order);
            return findPager(pager, criteria);
        }
    
    
    
    public List<T> getFromList(List list){
        Criteria criteria = getSession().createCriteria(entityClass);
        Disjunction dis=Restrictions.disjunction();  
        for(Object c : list) {
            dis.add((Criterion)c);
        }
        criteria.add(dis);
        return (List<T>)criteria.list();
    }

//    public T load(PK id) {
//        return this.getHibernateTemplate().load(entityClass, id);
//    }

    public List<T> getAllList() {
        return this.getHibernateTemplate().loadAll(entityClass);
    }

    public String getMax(String fieldName, String likeStr) {
        Criteria criteria = getSession().createCriteria(entityClass);
        if(likeStr != null  && !"".equals(likeStr)) {
            criteria.add(Restrictions.like(fieldName, likeStr + "%"));
        }
        criteria.setProjection(Projections.projectionList().add(Projections.max(fieldName)));
        List result = criteria.list();
        if(result == null || result.size() == 0 || result.get(0)==null) {
            return null;
        }
        return result.get(0).toString();
    }
    
    public void deleteAll() {
        List<T> deleteList = getAllList();
        for(T bean : deleteList) {
            delete(bean);
        }
    }
    
    public void deleteByCriteria(Object... c) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for(Object ci : c) {
            if(ci==null) {
                continue;
            }
            criteria.add((Criterion)ci);
        }
        List<T> deleteList = criteria.list();
        for(T bean : deleteList) {
            delete(bean);
        }
    }

    public Long getTotalCount() {
        String HQL = "SELECT COUNT(*) FROM " + entityClass.getName();
        return (Long) uniqueResult(HQL);
    }

    
    public PK save(Object entity) {
        if (entity instanceof BaseEntity) {
            try {
                Method method = entity.getClass().getMethod(
                        BaseEntity.ON_SAVE_METHOD_NAME);
                method.invoke(entity);
                return (PK) this.getHibernateTemplate().save(entity);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return (PK) this.getHibernateTemplate().save(entity);
    }

    
    public void update(Object entity) {
        if (entity instanceof BaseEntity) {
            try {
                Method method = entity.getClass().getMethod(
                        BaseEntity.ON_UPDATE_METHOD_NAME);

                method.invoke(entity);

                this.getHibernateTemplate().update(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    
    public void delete(Object entity) {
        this.getHibernateTemplate().delete(entity);
    }

    
    public void delete(PK id) {
        T entity = getHibernateTemplate().get(entityClass, id);
        getHibernateTemplate().delete(entity);
    }

    public void delete(PK[] ids) {
        for (PK id : ids) {
            T entity = getHibernateTemplate().get(entityClass, id);
            getHibernateTemplate().delete(entity);
        }
    }

    
    public List<T> find(String HQL) {
       
        return this.getHibernateTemplate().find(HQL);
    }

    
    public List<T> find(String HQL, Object... parameters) {
        return this.getHibernateTemplate().find(HQL, parameters);
    }

    
    public T uniqueResult(final String HQL, final Object... parameters)
            throws DataAccessException {
        return getHibernateTemplate().execute(new HibernateCallback<T>() {
            // 内部类重写doInHibernate方法
            public T doInHibernate(Session session) throws HibernateException,
                    SQLException {
                // 创建Query
                Query query = session.createQuery(HQL);
                // 遍历传递的可变参数
                if (parameters != null) {
                    // 添加查询条件
                    for (int i = 0; i < parameters.length; i++) {
                        query.setParameter(i, parameters[i]);
                    }
                }
                // 返回查询的唯一结果
                Object result = query.uniqueResult();
                return (T) result;
            }
        });
    }

    
    public Pager findPager(Pager pager) {
        Criteria criteria = getSession().createCriteria(entityClass);
        return findPager(pager, criteria);
    }

    
    public Pager findPager(Pager pager, Object... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        if(criterions != null) {
            for (Object criterion : criterions) {
                if(criterion==null)continue;
                criteria.add((Criterion)criterion);
            }
        }
        return findPager(pager, criteria);
    }

    
    public Pager findPager(Pager pager, Order... orders) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Order order : orders) {
            criteria.addOrder(order);
        }
        return findPager(pager, criteria);
    }

    
    public Pager findPager(Pager pager, Criteria criteria) {
        Integer pageNumber = pager.getPageNumber();
        Integer pageSize = pager.getPageSize();
        String searchBy = pager.getSearchBy();
        String keyword = pager.getKeyword();
        String orderBy = pager.getOrderBy();
        Pager.Order order = pager.getOrder();
        String searchData = pager.getSearchData();
        Class clz =  entityClass;
        try{
             if (StringUtils.isNotEmpty(searchBy) && StringUtils.isNotEmpty(keyword)) {
                 if (searchBy.contains(",")) {
                     String[] search = searchBy.split(",");
                     String[] key = keyword.split(",");
                     for(int i=0;i<search.length;i++){
                         String content = key[i].replaceAll("%", "/").trim();
                         if(!key[i].equals("null")){
                        	 Field field = clz.getDeclaredField(search[i].trim());
                         	 if(field!=null){
                         		 String type = field.getGenericType().toString();
                         		
                         		 if (type.equals("class java.lang.String")) {
                         			criteria.add(Restrictions.or(Restrictions.like(search[i].trim(), "%" + content.toUpperCase().trim() + "%"), Restrictions.like(search[i].trim(), "%" + content.toLowerCase().trim() + "%")));
                         		 }else if (type.equals("class java.lang.Integer")) {
                         			 criteria.add(Restrictions.eq(search[i].trim(), Integer.parseInt(key[i])));
                         		 }else  if (type.equals("class java.lang.Short")) {  
                         			 criteria.add(Restrictions.eq(search[i].trim(), Short.parseShort(key[i])));
                         		 }else  if (type.equals("class java.lang.Double")) { 
                         			 criteria.add(Restrictions.eq(search[i].trim(), Double.parseDouble(key[i])));
                         		 }else  if (type.equals("class java.util.Date")) { 
                         			 SimpleDateFormat form =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                         			 criteria.add(Restrictions.eq(search[i].trim(), form.format(key[i])));
                         		 }else if (type.equals("class java.lang.Boolean")) {  
                         			 criteria.add(Restrictions.eq(search[i].trim(), Boolean.parseBoolean(key[i])));
                         		 }else {
                         			 criteria.add(Restrictions.or(Restrictions.like(search[i].trim(), "%" + content.toUpperCase().trim() + "%"), Restrictions.like(search[i].trim(), "%" + content.toLowerCase().trim() + "%")));
                         		 }
                         	 }
                         }
                     }
                 }else{
                     if(!keyword.equals("null")){
                         keyword = keyword.replaceAll("%", "/").trim();
                         Field field = clz.getDeclaredField(searchBy.trim());
                         	 if(field!=null){
                         		 String type = field.getGenericType().toString(); 
                         		if (type.equals("class java.lang.String")) {
                         			criteria.add(Restrictions.or(Restrictions.like(searchBy.trim(), "%" + keyword.toUpperCase().trim() + "%"),Restrictions.like(searchBy.trim(), "%" + keyword.toLowerCase().trim() + "%")));
                                 }else if (type.equals("class java.lang.Integer")) {
                         			 criteria.add(Restrictions.eq(searchBy.trim(), Integer.parseInt(keyword)));
                         		 }else  if (type.equals("class java.lang.Short")) {  
                         			 criteria.add(Restrictions.eq(searchBy.trim(), Short.parseShort(keyword)));
                         		 }else  if (type.equals("class java.lang.Double")) { 
                         			 criteria.add(Restrictions.eq(searchBy.trim(), Double.parseDouble(keyword)));
                         		 }else  if (type.equals("class java.util.Date")) { 
                         			 SimpleDateFormat form =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                         			 criteria.add(Restrictions.eq(searchBy.trim(), form.format(keyword)));
                         		 }else if (type.equals("class java.lang.Boolean")) {  
                         			 criteria.add(Restrictions.eq(searchBy.trim(), Boolean.parseBoolean(keyword)));
                         		 }else {//if (type.equals("class java.lang.String")) { 
                         			 criteria.add(Restrictions.or(Restrictions.like(searchBy.trim(), "%" + keyword.toUpperCase().trim() + "%"),Restrictions.like(searchBy.trim(), "%" + keyword.toLowerCase().trim() + "%")));
                         		 }
                         	 }  
                         }
                 }
                 
             }
             if(StringUtils.isNotEmpty(searchData)){
            	 Map<String,String>searchValue=JsonUtil.parseProperties(searchData);
 				for(String key:searchValue.keySet()){
 					key = key.trim();
 					keyword = searchValue.get(key).replaceAll("%", "/").trim();
 					
                     Field field = clz.getDeclaredField(key);
                     	 if(field!=null){
                     		 if("depId".equals(key)){
                     			String[] ids = keyword.split(",");
                     			criteria.add(Restrictions.in("depId",ids));
                     		 }else{
                     			String type = field.getGenericType().toString(); 
                        		 if (type.equals("class java.lang.String")) {
                        			criteria.add(Restrictions.or(Restrictions.like(key, "%" + keyword.toUpperCase().trim() + "%"),Restrictions.like(key, "%" + keyword.toLowerCase().trim() + "%")));
                                }else if (type.equals("class java.lang.Integer")) {
                        			 criteria.add(Restrictions.eq(key, Integer.parseInt(keyword)));
                        		 }else  if (type.equals("class java.key.Short")) {  
                        			 criteria.add(Restrictions.eq(key, Short.parseShort(keyword)));
                        		 }else  if (type.equals("class java.lang.Double")) { 
                        			 criteria.add(Restrictions.eq(key, Double.parseDouble(keyword)));
                        		 }else  if (type.equals("class java.util.Date")) { 
                        			 SimpleDateFormat form =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        			 criteria.add(Restrictions.eq(key, form.format(keyword)));
                        		 }else if (type.equals("class java.lang.Boolean")) {  
                        			 criteria.add(Restrictions.eq(key, Boolean.parseBoolean(keyword)));
                        		 }else {
                        			 criteria.add(Restrictions.or(Restrictions.like(key, "%" + keyword.toUpperCase().trim() + "%"),Restrictions.like(key, "%" + keyword.toLowerCase().trim() + "%")));
                        		 }
                     		 }
                     		 
                     	 } 
 				}
             }
        }catch(Exception e){
        	e.printStackTrace();
        }
       

        pager.setTotalCount(criteriaResultTotalCount(criteria));

        if (StringUtils.isNotEmpty(orderBy) && order != null) {
            if (order == Pager.Order.asc) {
                criteria.addOrder(Order.asc(orderBy));
            } else {
                criteria.addOrder(Order.desc(orderBy));
            }
        }

        ClassMetadata classMetadata = getSessionFactory().getClassMetadata(
                entityClass);
        if (!StringUtils.equals(orderBy, ORDER_LIST_PROPERTY_NAME)
                && ArrayUtils.contains(classMetadata.getPropertyNames(),
                        ORDER_LIST_PROPERTY_NAME)) {
            criteria.addOrder(Order.asc(ORDER_LIST_PROPERTY_NAME));
            criteria.addOrder(Order.desc(CREATE_DATE_PROPERTY_NAME));
            if (StringUtils.isEmpty(orderBy) || order == null) {
                pager.setOrderBy(ORDER_LIST_PROPERTY_NAME);
                pager.setOrder(Pager.Order.asc);
            }
        } else if (!StringUtils.equals(orderBy, CREATE_DATE_PROPERTY_NAME)
                && ArrayUtils.contains(classMetadata.getPropertyNames(),
                        CREATE_DATE_PROPERTY_NAME)) {
            criteria.addOrder(Order.desc(CREATE_DATE_PROPERTY_NAME));
            if (StringUtils.isEmpty(orderBy) || order == null) {
                pager.setOrderBy(CREATE_DATE_PROPERTY_NAME);
                pager.setOrder(Pager.Order.desc);
            }
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);

        pager.setResult(criteria.list());
        return pager;
    }
    
    public Pager findPagerBySql(Pager pager, String sql) {
        /*Integer pageNumber = pager.getPageNumber();
        Integer pageSize = pager.getPageSize();
        SQLQuery query=this.getSession().createSQLQuery(sql);
        List<?> list = query.list();
        pager.setTotalCount(list.size());
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        pager.setResult(list);
        return pager;*/
    	Integer pageNumber = pager.getPageNumber();
        Integer pageSize = pager.getPageSize();
        SQLQuery query=this.getSession().createSQLQuery(sql);
        BigDecimal size =  (BigDecimal)this.getSession().createSQLQuery("SELECT COUNT(*) FROM (" +sql+") t").uniqueResult();
        pager.setTotalCount(size.intValueExact());
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        pager.setResult(query.list());
        return pager;
    }
    
    public List<T> findBySql(String sql) {
        SQLQuery query=this.getSession().createSQLQuery(sql);
        return query.list();
    }
    
    public Pager findPagerBySql(Pager pager, String sql, HashMap<String, Object> mapPara) {
        Integer pageNumber = pager.getPageNumber();
        Integer pageSize = pager.getPageSize();
        SQLQuery query=this.getSession().createSQLQuery(sql);
        
        for(Entry<String, Object> e : mapPara.entrySet()) {
            query.setParameter(e.getKey().toString(), e.getValue());
        }
        List<?> list = query.list();
        pager.setTotalCount(list.size());
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        pager.setResult(list);
        return pager;
    }

    private int criteriaResultTotalCount(Criteria criteria) {
        int criteriaResultTotalCount = 0;
        try {
            CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
            Projection projection = criteriaImpl.getProjection();
            ResultTransformer resultTransformer = criteriaImpl
                    .getResultTransformer();
            List<OrderEntry> orderEntries = (List<OrderEntry>) ReflectionUtil
                    .getFieldValue(criteriaImpl, "orderEntries");
            ReflectionUtil.setFieldValue(criteriaImpl, "orderEntries",
                    new ArrayList<Object>());
            
            Integer totalCount = ((Integer) criteriaImpl.setProjection(
                    Projections.rowCount()).uniqueResult()).intValue();
            if (totalCount != null) {
                criteriaResultTotalCount = totalCount;
            }

            criteriaImpl.setProjection(projection);
            if (projection == null) {
                criteriaImpl
                        .setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
            }
            if (resultTransformer != null) {
                criteriaImpl.setResultTransformer(resultTransformer);
            }
            ReflectionUtil.setFieldValue(criteriaImpl, "orderEntries",
                    orderEntries);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return criteriaResultTotalCount;
    }

    
    public void flush() {
        getHibernateTemplate().flush();
    }

    
    public void evict(Object object) {
        getHibernateTemplate().evict(object);
    }

    
    public void clear() {
        getHibernateTemplate().clear();
    }

    public void saveBatch(Collection<T> cols) {
		this.getHibernateTemplate().saveOrUpdateAll(cols);
	}
    @Override
    public Pager findPagerByOrders(Pager pager, Order[] orders,Criterion... criterions){
    	Criteria criteria = getSession().createCriteria(entityClass);
        if(criterions != null) {
            for (Object criterion : criterions) {
                if(criterion==null)continue;
                criteria.add((Criterion)criterion);
                
            }
        }
        for(Order order:orders){
        	criteria.addOrder(order);
        }
        return findPager(pager, criteria);
    }
}
