package com.lingnet.common.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.dao.LogDao;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxLog;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ToolUtil;

@SuppressWarnings({ "unchecked" ,"rawtypes"})
@Repository("baseService")
@Transactional
public class BaseServiceImpl<T, PK extends Serializable> implements
		BaseService<T, PK> {
    @Resource(name = "baseDao")
	private BaseDao<T, PK> baseDao;
	private Class<T> entityClass;
	@Resource(name = "logDao")
    private LogDao logDao;//日志dao
	@Resource(name="baseDaoInit")
	private BaseDao<T, PK> baseDaoInit;
	@Resource(name = "toolUtil")
    public ToolUtil toolUtil;
	// 获得泛型的实体对象
	public BaseServiceImpl() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	public BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T, PK> baseDao) {
		baseDao.setEntityClass(entityClass);
		this.baseDao = baseDao;
	}
	
	@Transactional(readOnly = true)
	public T get(String id) {
		baseDao.setEntityClass(entityClass);
		return baseDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public <O> O get(Class<O> clazz, String id) {
		baseDao.setEntityClass(clazz);
		return (O)baseDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public T get(Criterion c) {
		baseDao.setEntityClass(entityClass);
		return baseDao.get(c);
	}
	
	@Transactional(readOnly = true)
	public <O> O get(Class<O> clazz, Object... c) {
		baseDao.setEntityClass(clazz);
		return (O)baseDao.get(c);
	}
	
	@Transactional(readOnly = true)
	public List<T> getList(Object... cList) {
		baseDao.setEntityClass(entityClass);
		return baseDao.getList(cList);
	}
	
	@Transactional(readOnly = true)
	public <O> List<O> getList(Class<O> clazz, Object... cList) {
		baseDao.setEntityClass(clazz);
		return (List<O>)baseDao.getList(cList);
	}
	public <O> List<O> getOrderList(Class<O> clazz,Order order, Object... cList) {
        baseDao.setEntityClass(clazz);
        return (List<O>)baseDao.getOrderList(order,cList);
    }
	
	@Transactional(readOnly = true)
    public Pager findPagerByOrder(Class<?> clazz, Pager pager, Order order,Criterion... criterions) {
        baseDao.setEntityClass(clazz);
        return baseDao.findPagerByOrder(pager,order, criterions);
    }
	
    @Transactional(readOnly = true)
    public <O> List<O> getFromList(Class<O> clazz, List list) {
        baseDao.setEntityClass(clazz);
        return (List<O>)baseDao.getFromList(list);
    }

//	@Transactional(readOnly = true)
//	public T load(PK id) {
//		baseDao.setEntityClass(entityClass);
//		return baseDao.load(id);
//	}

//	@Transactional(readOnly = true)
//	public <O> O load(Class<O> clazz, PK id) {
//		baseDao.setEntityClass(clazz);
//		return (O)baseDao.load(id);
//	}

	@Transactional(readOnly = true)
	public List<T> getAllList() {
		baseDao.setEntityClass(entityClass);
		return baseDao.getAllList();
	}
	

	@Transactional(readOnly = true)
	public <O> List<O> getAllList(Class<O> clazz) {
		baseDao.setEntityClass(clazz);
		return (List<O>)baseDao.getAllList();
	}
	
	@Transactional
	public void deleteByCriteria(Criterion c) {
		baseDao.setEntityClass(entityClass);
		baseDao.deleteByCriteria(c);
	}
	
	@Transactional
	public <O> void deleteByCriteria(Class<O> clazz, Object... c) {
		baseDao.setEntityClass(clazz);
		baseDao.deleteByCriteria(c);
	}

	@Transactional
	public void deleteAll() {
		baseDao.setEntityClass(entityClass);
		baseDao.deleteAll();
	}

	@Transactional
	public <O> void deleteAll(Class<O> clazz) {
		baseDao.setEntityClass(clazz);
		baseDao.deleteAll();
	}
	
	@Transactional(readOnly = true)
	public Long getTotalCount() {
		return baseDao.getTotalCount();
	}

	@Transactional
	public PK save(T entity) {
	    return baseDao.save(entity); 
	}

	@Transactional
	public void update(T entity) {
		baseDao.update(entity);
	}
	
	@Transactional
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	@Transactional
	public void delete(PK id) {
		baseDao.setEntityClass(entityClass);
		baseDao.delete(id);
	}

	@Transactional
	public <O> void delete(Class<O> clazz,PK[] ids) {
		baseDao.setEntityClass(clazz);
		baseDao.delete(ids);
	}

	@Transactional(readOnly = true)
	public void flush() {
		baseDao.flush();
	}

	@Transactional(readOnly = true)
	public void evict(Object object) {
		baseDao.evict(object);
	}
	
	@Transactional(readOnly = true)
	public void clear() {
		baseDao.clear();
	}
	
	/*@Transactional(readOnly = true)
	public Pager findPager(Pager pager) {
		return baseDao.findPager(pager);
	}
	
	@Transactional(readOnly = true)
	public Pager findPager(Pager pager, Object... criterions) {
		return baseDao.findPager(pager, criterions);
	}*/
	
	@Transactional(readOnly = true)
	public Pager findPager(Class<?> clazz, Pager pager, Object... criterions) {
		baseDao.setEntityClass(clazz);
		return baseDao.findPager(pager, criterions);
	}
	
	

	
	
	/*@Transactional(readOnly = true)
	public Pager findPager(Pager pager, Order... orders) {
		return baseDao.findPager(pager, orders);
	}
	
	@Transactional(readOnly = true)
	public Pager findPager(Pager pager, Criteria criteria) {
		return baseDao.findPager(pager, criteria);
	}*/

	/**
	 * @return the entityClass
	 */
	public Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}


	@Transactional(readOnly = true)
	public Pager findPagerBySql(Pager pager, String sql) {
		return baseDao.findPagerBySql(pager, sql);
	}
	
	@Transactional(readOnly = true)
    public List<T> findBySql(String sql) {
        return baseDao.findBySql(sql);
    }
	
	@Transactional(readOnly = true)
	public Pager findPagerBySql(Pager pager, String sql, HashMap<String, Object> mapPara) {
		return baseDao.findPagerBySql(pager, sql, mapPara);
	}
	
	public static void main(String[] args) {

        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumIntegerDigits(6);
        nf.setMinimumIntegerDigits(4);
		System.out.println(nf.format(1234567));
		
	}


    
     //获得客户端ip
     public static String getIpAddr() throws IOException{  
         HttpServletRequest req=ServletActionContext.getRequest();  
         String ipAddress = req.getHeader("x-forwarded-for");  
             if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                 ipAddress = req.getHeader("Proxy-Client-IP");               
             if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                 ipAddress = req.getHeader("WL-Proxy-Client-IP");      
             if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                 ipAddress = req.getHeader("HTTP_X_FORWARDED_FOR");       
             if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                 ipAddress = req.getHeader("HTTP_CLIENT_IP");         
             if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                 ipAddress = req.getRemoteAddr();  
             //String host = req.getRemoteHost();  
             String ip = ipAddress;  
            // System.out.println("访问者IP="+ip);  
             return ip;  
      }

     /**
      * 日志操作实现
      * **/
     public String operate(String djType, String czType,String czdj) {
         String ip="";
         //String address="";
         try {
             ip = BaseServiceImpl.getIpAddr();
             if(ip.equals("0:0:0:0:0:0:0:1")){
                 InetAddress addr = InetAddress.getLocalHost();
                 ip=addr.getHostAddress().toString();//获得本机IP
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         

         QxLog log=new QxLog();
         String username=LingUtil.userName();
         log.setCzUser(username);
         log.setCzDate(new Date());
         log.setDjType(djType);
         log.setCzType(czType);
         log.setIpDz(ip);
         //log.setPcName(address);
        //分中心
         QxUsers users=get(QxUsers.class, Restrictions.eq("username",username),Restrictions.eq("isDelete",0));
         if(users!=null){
         	Branch branch=get(Branch.class, Restrictions.eq("id", users.getCid()));
         	if(branch!=null){
         		log.setFzx(branch.getFzx());
         	}
         }
         log.setCzdj(czdj);
         logDao.save(log);
         return null;
     }
     /**返回当前登录用户*/
     public QxUsers getCurrentUser(){
    	 return get(QxUsers.class,Restrictions.eq("username",LingUtil.userName()));
     }
     
     @Transactional
     public void updateBatch(List<Object>objs){
    	 for(Object obj:objs){
    		 baseDao.clear();
    		 baseDao.update(obj);
    	 }
     }
     
    @Override
 	@Transactional
 	public void saveBatch(Collection<T> cols) {
 		baseDaoInit.setEntityClass(entityClass);
 		baseDaoInit.saveBatch(cols);
 	}
    
    @Transactional(readOnly = true)
    @Override
    public Pager findPagerByOrders(Class<?> clazz, Pager pager, Order[] orders,Criterion... criterions) {
        baseDao.setEntityClass(clazz);
        return baseDao.findPagerByOrders(pager,orders, criterions);
    }
}
