package com.lingnet.qxgl.security.shared;

import javax.servlet.http.HttpServletRequest;

/**
 * 获得当前请求的匹配页面
 * 
 * @author Jerry
 * 
 */

public interface DirectUrlResolver {

	/**
	 * 当前请求Url是否是接口允许的请求地址
	 * 
	 * @param request
	 * @return
	 */
	public boolean support(HttpServletRequest request);

	/**
	 * 跳转页面的Url
	 * 
	 * @return
	 */
	public String getDirectUrl();
}
