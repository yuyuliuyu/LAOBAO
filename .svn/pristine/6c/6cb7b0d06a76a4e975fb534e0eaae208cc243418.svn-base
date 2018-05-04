package com.lingnet.qxgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.qxgl.dao.BackendDepDao;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.util.Pager;

/** 
 * @ClassName: BackendDepDaoImpl 
 * @Description: TODO 
 * @author zhmf
 * @date 2013-6-17 上午9:23:35 
 *  
 */

@Repository("backendDepDao")
public class BackendDepDaoImpl extends BaseDaoImplInit<QxDepartment, String> 
                                implements BackendDepDao {

    @Override
    public QxDepartment getByName(String name){
        
        String sHql = "from QxDepartment as dep where dep.name = ? and isDelete = 0";
        
        QxDepartment dep = uniqueResult(sHql, name);
        
        return dep;
    }

    
    /**
     * 获取所有的功能部门（科室）
     * 
     * @Title: findAllDepartment
     * @return List<QxDepartment>
     * @author yinzl
     * @since 2016-8-7 V 1.0
     */
    @SuppressWarnings({ "unchecked" })
    @Override
    public List<QxDepartment> getAllDepartment(Pager pager, String key) {
        List<QxDepartment> departments = new ArrayList<QxDepartment>();
        Criteria criteria = getSession().createCriteria(QxDepartment.class);
        criteria.add(Restrictions.eq("is_function", 1));
        criteria.add(Restrictions.eq("isDelete", 0));
        if (key != null && !key.equals("") && !key.equals("0")) {
            criteria.add(Restrictions.like("input_code", "%"+key.toUpperCase().trim()+"%"));
        }
        try {
            pager = findPager(pager, criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            departments = (List<QxDepartment>) pager.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }
    
    @Override
    public List<String> getFunctionKsIds(){
    	/*List<QxDepartment> depts=getList(Restrictions.eq("is_function",1)
    			,Restrictions.eq("isDelete",0));*/
    	
    	String sHql = "from QxDepartment as dep where dep.is_function = 1 and isDelete = 0 ";
    	List<QxDepartment> depts = find(sHql);
    	
    	List<String> ids=new ArrayList<String>();
    	for(QxDepartment dept:depts){
    		ids.add(dept.getId());
    	}
    	 
         
    	 
    	return ids;
    }
}
