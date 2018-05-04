package com.lingnet.qxgl.security.logout;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.lingnet.qxgl.security.shared.DirectUrlResolver;

public class MultipleLogoutSuccessHandler implements LogoutSuccessHandler {

	List<DirectUrlResolver> directUrlResolvers;

	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		for (DirectUrlResolver directUrlResolver : directUrlResolvers) {
			if (directUrlResolver.support(request)) {
				response.sendRedirect(request.getContextPath()
						+ directUrlResolver.getDirectUrl());
				return;
			}
		}
		throw new UnsupportedOperationException(
				"No login success url resolver found!");

	}

	public List<DirectUrlResolver> getDirectUrlResolvers() {
		return directUrlResolvers;
	}

	public void setDirectUrlResolvers(List<DirectUrlResolver> directUrlResolvers) {
		this.directUrlResolvers = directUrlResolvers;
	}

}
