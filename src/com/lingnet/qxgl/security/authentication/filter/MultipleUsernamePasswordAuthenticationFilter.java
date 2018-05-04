package com.lingnet.qxgl.security.authentication.filter;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lingnet.qxgl.entity.QxDictionary;
import com.lingnet.qxgl.service.BackendDictionaryService;
import com.lingnet.util.CaptchaUtil;

public class MultipleUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {

	private List<AuthenticationTokenResolver> tokenResolvers;
	public static final String CAPTCHA = "captcha"; // 验证码
	@Resource(name="backendDictionaryService")
    private BackendDictionaryService backendDictionaryService;

	/**
	 * 重写身份验证方法
	 */
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) {

	    List<QxDictionary> list = backendDictionaryService.getAllByType("captcha");

        if(list.get(0).getFlg().equals("T")){
            // 校验验证码
            checkValidateCode(request);
        }
		// 校验验证码
		//checkValidateCode(request);
		for (AuthenticationTokenResolver tokenResolver : tokenResolvers) {
			// 判断登陆类型
			if (tokenResolver.support(request)) {
				// 获得用户凭证
				Authentication authentication = tokenResolver.resolve(request);
				// 验证用户凭证
				return this.getAuthenticationManager().authenticate(
						authentication);
			}
		}
		throw new UnsupportedOperationException(
				"No authentication token resolver found!");
	}

	/**
	 * 使用工具类校验输入的验证码是否匹配
	 * 
	 * @param request
	 */
	protected void checkValidateCode(HttpServletRequest request) {
		if (!CaptchaUtil.validateCaptchaByRequest(request)) {
			throw new AuthenticationServiceException("ValidateCode Not Equals");
		}
	}

	public List<AuthenticationTokenResolver> getTokenResolvers() {
		return tokenResolvers;
	}

	public void setTokenResolvers(
			List<AuthenticationTokenResolver> tokenResolvers) {
		this.tokenResolvers = tokenResolvers;
	}

}
