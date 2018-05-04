package com.lingnet.qxgl.security.authentication.filter;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 获得请求URL的路径参数,判断登陆的类型 根据登录类型,返回不同的用户凭证
 * 
 * @author Jerry
 * 
 */
public interface AuthenticationTokenResolver {

	/**
	 * 判断登录URL类型(前台,后台)
	 * 
	 * @param request
	 * @return
	 */
	public boolean support(HttpServletRequest request);

	/**
	 * 返回登录的用户凭证(前台,后台)
	 * 
	 * @param request
	 * @return
	 */
	public Authentication resolve(HttpServletRequest request);

}
