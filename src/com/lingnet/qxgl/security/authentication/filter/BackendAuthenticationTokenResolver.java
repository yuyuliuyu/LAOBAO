package com.lingnet.qxgl.security.authentication.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;

import com.lingnet.qxgl.security.authentication.token.BackendAuthenticationToken;

/**
 * 后台登陆凭证
 * 
 * @author Jerry
 * 
 */
public class BackendAuthenticationTokenResolver extends
		AbstractAuthenticationTokenResolver {

	private static final Logger logger = Logger
			.getLogger(BackendAuthenticationTokenResolver.class);
	public static final String USERNAME = "username"; // 登陆用户名
	public static final String PASSWORD = "password"; // 登陆密码

	public Authentication resolve(HttpServletRequest request) {
		String username = request.getParameter(USERNAME);
		String password = request.getParameter(PASSWORD);
		try {
		    String osName=System.getProperties().getProperty("os.name");
            if(!osName.toLowerCase().contains("windows")){
                username = new String(username.getBytes("ISO-8859-1"),"UTF-8");//用于解决中文用户名不能登录的问题
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		if (logger.isDebugEnabled()) {
			logger.info("后台登陆的用户名：" + username);
		}
		
		return new BackendAuthenticationToken(username,password);
	}

	

}
