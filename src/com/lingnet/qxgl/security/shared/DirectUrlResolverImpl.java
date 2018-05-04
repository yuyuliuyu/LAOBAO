package com.lingnet.qxgl.security.shared;

import javax.servlet.http.HttpServletRequest;

public class DirectUrlResolverImpl extends AbstarctDirectUrlResolver {

	public boolean support(HttpServletRequest request) {
		// 获得请求URL
		String requestURI = request.getRequestURI();
		// 是否与接口地址匹配
		return requestURI.contains(pattern);
	}

}
