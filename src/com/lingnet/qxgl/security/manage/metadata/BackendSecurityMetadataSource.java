package com.lingnet.qxgl.security.manage.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;

import com.lingnet.qxgl.service.BackendResourceService;

public class BackendSecurityMetadataSource extends AbstractMetadataSource
		implements SecurityMetadataSource {

	private static final Logger logger = Logger
			.getLogger(BackendSecurityMetadataSource.class);
	private Map<String, Collection<ConfigAttribute>> backendResourceMap = new HashMap<String, Collection<ConfigAttribute>>();
	@Resource(name = "backendResourceService")
	private BackendResourceService backendResourceService;
	/*@Resource(name = "backendRoleService")
    private BackendRoleService backendRoleService;*/

	@PostConstruct
	public void init() {
	    /*List<QxResource> backendResources = backendResourceService
                .getAllList();
        for (QxResource backendResource : backendResources) {
            List<QxRoles>  backendRoles = backendRoleService.findRolseByResource(backendResource.getResourceurl());
            
                backendResourceMap.put(backendResource.getResourceurl(),
                        ListToCollection(backendRoles));
            
	    }*/
	    backendResourceMap = backendResourceService.ListToCollection();
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public Collection<ConfigAttribute> getAttributes(Object filter)
			throws IllegalArgumentException {
		Collection<ConfigAttribute> collection = new ArrayList<ConfigAttribute>();
		String url = ((FilterInvocation) filter).getRequestUrl();
		if(url.indexOf("?")!=-1){
            url = url.substring(0,url.indexOf("?"));
        }

		if(backendResourceMap.size()<1){
            /*List<QxResource> backendResources = backendResourceService
                    .getAllList();
            for (QxResource backendResource : backendResources) {
                List<QxRoles>  backendRoles = backendRoleService.findRolseByResource(backendResource.getResourceurl());
                
                    backendResourceMap.put(backendResource.getResourceurl(),
                            ListToCollection(backendRoles));
            }*/
		    backendResourceMap = backendResourceService.ListToCollection();
            if (logger.isDebugEnabled()) {
                logger.info("重新赋予权限后,访问的URL：" + url);
            }
        }
		
		collection = backendResourceMap.get(url);
		
		if (logger.isDebugEnabled()) {
			logger.info("访问的URL：" + url);
		}
		return collection;
	}
	
	
	public Collection<ConfigAttribute> getPermAttributes(String url,HttpSession session)    throws IllegalArgumentException {
        Collection<ConfigAttribute> collection = new ArrayList<ConfigAttribute>();
        
        //update by hew 20150506 获取认证信息，如果匿名用户，如果匿名用户不用获取资源信息，直接返回。
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
          String username = ((UserDetails)principal).getUsername();
          if(logger.isInfoEnabled()){
              logger.info("   |当前用户："+(username==null?"":username)+"|   ");
          }
        } else {
          String username = principal.toString();
          collection.add(new SecurityConfig("{}"));
          if(logger.isInfoEnabled()){
              logger.info("   |匿名用户："+(username==null?"":username)+"|   ");
          }
          return collection;
        }
        
        if(url.indexOf("?")!=-1){
            url = url.substring(0,url.indexOf("?"));
        }
        
        //如果已经认证且size<1，重新加载权限。
        if(backendResourceMap.size()<1){
            if(logger.isInfoEnabled()){
                logger.info("***初始化角色权限菜单***");
            }
            
            backendResourceMap = backendResourceService.ListToCollection();
            
        }
        collection = backendResourceMap.get(url);
        if(logger.isInfoEnabled()){
            logger.info("   |角色组"+collection+"可访问的URL：" + url+"|   ");
        }
        return collection;
    }
	
	public void refresh() {
        backendResourceMap = new HashMap<String, Collection<ConfigAttribute>>();
    }

	public boolean supports(Class<?> arg0) {
		return true;
	}

	public void setBackendResourceService(
			BackendResourceService backendResourceService) {
		this.backendResourceService = backendResourceService;
	}


}
