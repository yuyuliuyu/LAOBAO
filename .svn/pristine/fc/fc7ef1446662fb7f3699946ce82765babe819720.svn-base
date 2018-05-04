package com.lingnet.qxgl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.qxgl.dao.BackendDictionaryDao;
import com.lingnet.qxgl.entity.QxDictionary;


/** 
 * @ClassName: BackendDictionaryDaoImpl 
 * @Description:  
 * @author 张明峰
 * @date 2013-7-31 上午8:30:28 
 *  
 */
@Repository("backendDictionaryDao")
public class BackendDictionaryDaoImpl extends BaseDaoImplInit<QxDictionary, String>
                                    
                                        implements BackendDictionaryDao{
    
    /*根据类型获取所有的对应字典内容*/
    @Override
    public List<QxDictionary> getAllByType(String type){
     
       String  hql = "from QxDictionary dr where dr.type = ?";
        
        return find(hql,type);
    }
    
    
    public QxDictionary getAllByName(String name){
        
        String hql ="from QxDictionary dr where dr.type=?";
        return uniqueResult(hql, name);
    }
    

}
