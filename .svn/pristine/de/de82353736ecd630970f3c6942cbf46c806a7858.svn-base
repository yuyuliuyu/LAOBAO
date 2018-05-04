package com.lingnet.qxgl.security.authentication.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

import com.lingnet.qxgl.security.shared.DirectUrlResolver;

public class MultipleAuthenticationFailureHandler extends
		ExceptionMappingAuthenticationFailureHandler {
	private List<DirectUrlResolver> directUrlResolvers;

	//验证失败执行的方法，跳转到失败的URL
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
	   
		for (DirectUrlResolver resolver : directUrlResolvers) {
			if (resolver.support(request)) {
				String directUrl = resolver.getDirectUrl();
				//添加跳转的URL
				setDefaultFailureUrl(directUrl);
			}
		}
		super.onAuthenticationFailure(request, response, exception);
	}

	public List<DirectUrlResolver> getDirectUrlResolvers() {
		return directUrlResolvers;
	}

	public void setDirectUrlResolvers(List<DirectUrlResolver> directUrlResolvers) {
		this.directUrlResolvers = directUrlResolvers;
	}
}
