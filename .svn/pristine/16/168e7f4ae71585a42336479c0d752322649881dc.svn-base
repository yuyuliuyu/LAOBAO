package com.lingnet.qxgl.security.perm;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lingnet.qxgl.security.manage.metadata.BackendSecurityMetadataSource;


/** 
 * @ClassName: PermissionChecker 
 * @Description: 权限按钮展现
 * @author zmf
 * @date 2015-8-18 上午9:25:56 
 *  
 */

public class PermissionChecker {
    
    private BackendSecurityMetadataSource backendSecurityMetadataSource;

    public BackendSecurityMetadataSource getBackendSecurityMetadataSource() {
        return backendSecurityMetadataSource;
    }
    public void setBackendSecurityMetadataSource(
            BackendSecurityMetadataSource backendSecurityMetadataSource) {
        this.backendSecurityMetadataSource = backendSecurityMetadataSource;
    }
    
    public boolean isAuthorized(String value,HttpSession session) {
        //获取对应url的访问角色集合
        SecurityContext context = SecurityContextHolder.getContext();
        //获取资源对应的角色
        Collection<ConfigAttribute> collection = backendSecurityMetadataSource.getPermAttributes(value,session);
           if(collection==null){
               return true;
           }
        //获取当前角色
        Authentication authentication = context.getAuthentication();
        authentication.getAuthorities();
        
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            for (ConfigAttribute coll : collection) {
                if(coll.getAttribute().equals(authority.getAuthority())){
                    return true;
                }
            }
        }

        return false;
    }

}
