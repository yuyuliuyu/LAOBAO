package com.lingnet.qxgl.security.manage.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import com.lingnet.qxgl.entity.QxRoles;

public abstract class AbstractMetadataSource implements MetadataSource {
	
    protected String pattern; // 访问资源依据

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	// 自定义方法,转换为 框架需要的Collection<ConfigAttribute>数据
	public Collection<ConfigAttribute> ListToCollection(List<QxRoles>  backendRoles) {
	    Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
        if(backendRoles==null||backendRoles.size()<1){
            configAttributes.add(new SecurityConfig("{}"));
        }else{
            for(QxRoles backendRole:backendRoles){
                
                configAttributes.add(new SecurityConfig(backendRole.getName()));
            }
        }
        
        return configAttributes;
	}

	public boolean supports(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		return requestURI.contains(pattern);

	}

}
