package com.lingnet.qxgl.security.authentication.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * 构建后台登陆的用户凭证
 * 
 * @author anywgte
 * 
 */

public class BackendAuthenticationToken extends
		UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 8047783698414202726L;

	public BackendAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);

	}

}
