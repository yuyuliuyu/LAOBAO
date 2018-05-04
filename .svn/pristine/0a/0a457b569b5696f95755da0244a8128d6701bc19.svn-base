/**
 * @BackendDepDao.java
 * @com.lingnet.qxgl.dao
 * @Description：
 * 
 * @author zhmf 
 * @copyright  2013
 * @version V
 * @since 2013-6-17
 */
package com.lingnet.qxgl.dao;
 
import java.util.List;
 

import com.lingnet.common.dao.BaseDao;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.util.Pager;

/** 
 * @ClassName: BackendDepDao 
 * @Description: TODO 
 * @author 张明峰
 * @date 2013-6-17 上午9:20:59 
 *  
 */

public interface BackendDepDao extends BaseDao<QxDepartment,String> {
    
    public QxDepartment getByName(String name);

    
    
    /**
     * 获取所有的功能部门（科室）
     * 
     * @Title: findAllDepartment
     * @return List<QxDepartment>
     * @author yinzl
     * @since 2016-8-7 V 1.0
     */
    public List<QxDepartment> getAllDepartment(Pager pager, String key);
    /**
     * 
     * @Title: getFunctionKsIds 
     * @return 
     * String[] 
     * @author xuhp
     * @since 2016-12-20 V 1.0
     */
    List<String> getFunctionKsIds();
}
