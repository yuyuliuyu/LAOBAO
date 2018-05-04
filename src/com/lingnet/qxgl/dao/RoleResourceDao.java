package com.lingnet.qxgl.dao;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.qxgl.entity.QxRoleResource;
import com.lingnet.qxgl.entity.QxRoleResourceId;

/** 
 * @ClassName: RoleResourceDao 
 * @Description: 
 * @author 张明峰
 * @date 2013-6-9 下午3:31:57 
 *  
 */

public interface RoleResourceDao  extends BaseDao<QxRoleResource,String> {
    
    @SuppressWarnings("rawtypes")
    public List getResourceByRole(String roleId);
    
    public int saveId(QxRoleResourceId qxRoleResourceId);
    
    public int deleId(QxRoleResourceId qxRoleResourceId);

}
