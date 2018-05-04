package com.lingnet.qxgl.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.qxgl.entity.QxResource;

public interface BackendResourceDao extends BaseDao<QxResource, String>{
    
    public QxResource getByName(String name);

    public QxResource getById(String id);
    
    public List<QxResource> getByRoleType(String roleId,String type);
    
    public Map<String, Collection<ConfigAttribute>> ListToCollection(String db);
    
    public Collection<ConfigAttribute> ListToCollection(String db,String url);
	
}
