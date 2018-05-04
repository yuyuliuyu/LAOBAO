package com.lingnet.qxgl.security.authentication.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.lingnet.qxgl.security.shared.DirectUrlResolver;

public class MultipleAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {
	private List<DirectUrlResolver> directUrlResolvers;

	//验证成功执行的方法，跳转到成功的URL
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		for (DirectUrlResolver resolver : directUrlResolvers) {
			if (resolver.support(request)) {
				String directUrl = resolver.getDirectUrl();
				//添加跳转的URL
				setDefaultTargetUrl(directUrl);
			}
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

	public List<DirectUrlResolver> getDirectUrlResolvers() {
		return directUrlResolvers;
	}

	public void setDirectUrlResolvers(List<DirectUrlResolver> directUrlResolvers) {
		this.directUrlResolvers = directUrlResolvers;
	}

}
