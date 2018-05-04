package com.lingnet.qxgl.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.qxgl.dao.RoleResourceDao;
import com.lingnet.qxgl.entity.QxRoleResource;
import com.lingnet.qxgl.entity.QxRoleResourceId;
import com.lingnet.qxgl.service.RoleResourceService;

/** 
 * @ClassName: RoleResourceServiceImpl 
 * @Description: 角色资源操作类
 * @author 张明峰
 * @date 2013-6-9 下午3:11:53 
 *  
 */
@Service("RoleResourceService")
public class RoleResourceServiceImpl 
                extends BaseServiceImpl<QxRoleResource,String>
                
                implements RoleResourceService {
    
    @Resource(name="RoleResourceDao")
    private RoleResourceDao roleResource;
    
    /* 
     * 根据用户获取资源信息
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public HashMap getResourceByRole(String roleId){
        
        
       List list =  roleResource.getResourceByRole(roleId);
       
       HashMap mapResource = new HashMap();
       
       Iterator<QxRoleResourceId> it = list.iterator();
       while(it.hasNext()){
           QxRoleResourceId roleResource  = it.next();
           
           mapResource.put(roleResource.getResourceId(),roleResource.getRoleId());
       }
        
       return mapResource;
    }
    
    @Override
    public int saveId(QxRoleResourceId qxRoleResourceId) {
       
        int m = roleResource.saveId(qxRoleResourceId);
        
        return m;
    }

    @Override
    public int deleId(QxRoleResourceId qxRoleResourceId) {
        
        int m = roleResource.deleId(qxRoleResourceId);
        return m;
    }
    

    public RoleResourceDao getRoleResource() {
        return roleResource;
    }

    public void setRoleResource(RoleResourceDao roleResource) {
        this.roleResource = roleResource;
    }

   

    

}
