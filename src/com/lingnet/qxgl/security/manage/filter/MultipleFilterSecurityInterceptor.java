package com.lingnet.qxgl.security.manage.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * 该过滤器的主要作用就是通过spring的IoC生成securityMetadataSource。
 * securityMetadataSource相当于本包中自定义的SecurityMetadataSourceServiceImpl。
 * 该SecurityMetadataSourceServiceImpl的作用提从数据库提取权限和资源，装配到HashMap中， 供Spring
 * Security使用，用于权限校验。
 * 
 * @author Jerry
 * 
 */

public class MultipleFilterSecurityInterceptor extends
		AbstractSecurityInterceptor implements Filter {

	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	// 注入SecurityMetadataSource
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 封装对象供FilterSecurityInterceptor的处理代码调用
		FilterInvocation invocation = new FilterInvocation(request, response,
				chain);
		invoke(invocation);
	}

	public void invoke(FilterInvocation invocation) throws IOException,
			ServletException {
		// 框架获得封装的对象
		InterceptorStatusToken token = super.beforeInvocation(invocation);
		try {
			// 通过过滤器提交验证请求
			invocation.getChain().doFilter(invocation.getRequest(),
					invocation.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	// 过滤器封装的对象
	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	public void destroy() {

	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

}
