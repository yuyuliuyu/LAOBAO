package com.lingnet.qxgl.security.manage.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;


public class MultipleFilterInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	List<MetadataSource> metadataSources;

	public void setMetadataSources(List<MetadataSource> metadataSources) {
		this.metadataSources = metadataSources;
	}

	// 返回所有权限属性的集合对象
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	// 接口中规定的方法， 这核心方法 ，用户获取正在访问的资源所对应的权限集合
	public Collection<ConfigAttribute> getAttributes(Object filter)
			throws IllegalArgumentException {
		//HttpServletRequest request = ((FilterInvocation) filter).getRequest();
		Collection<ConfigAttribute> collection = new ArrayList<ConfigAttribute>();
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		for (MetadataSource metadataSource : metadataSources) {
			//if (metadataSource.supports(request)) {
				collection = metadataSource.getAttributes(filter);
			//}
		}
		return collection;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
