package com.lingnet.qxgl.dao;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.qxgl.entity.QxRoles;
import com.lingnet.util.Pager;

public interface BackendRoleDao extends BaseDao<QxRoles, String> {
    
    public QxRoles findRoleByName(String username);
    
    public List<QxRoles> findRole(String name,int start,int end);
    
    @SuppressWarnings({ "rawtypes"})
    public List getUsersByRoleId(Pager pager,String roleId);
    
    public List<QxRoles> findRolseByResource(String resourceName);

}
