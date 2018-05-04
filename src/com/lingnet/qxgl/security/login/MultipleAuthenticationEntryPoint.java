package com.lingnet.qxgl.security.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.lingnet.qxgl.security.shared.DirectUrlResolver;

public class MultipleAuthenticationEntryPoint implements
		AuthenticationEntryPoint {

	private List<DirectUrlResolver> directUrlResolvers;

	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {

		for (DirectUrlResolver directUrlResolver : directUrlResolvers) {
			// 请求地址是否包含登陆依据
			if (directUrlResolver.support(request)) {
				// 跳转到登陆依据所绑定的登录页面
				response.sendRedirect(request.getContextPath()
						+ directUrlResolver.getDirectUrl());
				return;
			}
		}

	}

	public List<DirectUrlResolver> getDirectUrlResolvers() {
		return directUrlResolvers;
	}

	public void setDirectUrlResolvers(
			List<DirectUrlResolver> directUrlResolvers) {
		this.directUrlResolvers = directUrlResolvers;
	}

}
