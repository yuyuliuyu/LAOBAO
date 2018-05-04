package com.lingnet.qxgl.security.manage.metadata;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;

/**
 * 返回所访问资源所匹配的属性集合
 * 因为该demo将前后台资源分开为2张表
 * 访问前台时根据URL返回前台的资源集合
 * 访问后台时根据URL返回后台的资源集合
 * 这样使用前台的用户,如果想访问后台资源时,只会用后台的资源集合区匹配该用户的权限
 * 
 * @author Jerry
 * 
 */
public interface MetadataSource {

	/**
	 * 返回请求URL的资源集合
	 * 
	 * @param filter
	 * @return
	 */
	public Collection<ConfigAttribute> getAttributes(Object filter);

	/**
	 * 根据URL判断所访问路径的资源类型(前台,后台)
	 * 
	 * @param request
	 * @return
	 */
	public boolean supports(HttpServletRequest request);
}
