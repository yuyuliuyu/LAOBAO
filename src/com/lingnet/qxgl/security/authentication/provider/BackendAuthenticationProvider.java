package com.lingnet.qxgl.security.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.lingnet.qxgl.security.authentication.token.BackendAuthenticationToken;

public class BackendAuthenticationProvider extends DaoAuthenticationProvider
		implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		BackendAuthenticationToken authenticationToken = (BackendAuthenticationToken) authentication;
		UserDetails userDetails = getUserDetailsService().loadUserByUsername(
				authenticationToken.getPrincipal().toString());

		// 获得的用户输入密码
		String presentedPassword = authentication.getCredentials().toString();
		// 获得用户名，作为加密盐值
		Object salt = getSaltSource().getSalt(userDetails);
		
		// 判断密码是否匹配
		if (!getPasswordEncoder().isPasswordValid(userDetails.getPassword(),
				presentedPassword, salt)) {
			throw new BadCredentialsException("password is error");
		}
		
		// 重新构建UsernamePasswordAuthenticationToken传递给决策管理器进行授权管理
		return new UsernamePasswordAuthenticationToken(userDetails,
				authentication.getPrincipal(), userDetails.getAuthorities());
	}

	// 根据凭证判断登陆类型(前台,后台)
	@Override
	public boolean supports(Class<?> authentication) {
		return BackendAuthenticationToken.class
				.isAssignableFrom(authentication);
	}
}
