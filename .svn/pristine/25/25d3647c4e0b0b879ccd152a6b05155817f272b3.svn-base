package com.lingnet.qxgl.security.manage.decide;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;


public class MultipleAccessDecisionManager implements AccessDecisionManager {
    private MultipleAccessDecisionManager source;
	/**
	 * 判断用户所拥有的角色是否有访问当前请求资源的权限
	 * 
	 * @author Jerry
	 */

	private static final Logger logger = Logger
			.getLogger(MultipleAccessDecisionManager.class);

	public void decide(Authentication authentication, Object filter,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		String URL = ((FilterInvocation) filter).getRequestUrl();
		if(URL.indexOf("/basemanage/base_manage")<0){
			if(URL.indexOf("?")!=-1){
	            URL = URL.substring(0,URL.indexOf("?"));
	        }
		}
		// 判断当前访问的资源是否属于受限资源
		if (configAttributes == null || configAttributes.size() == 0) {
			if (logger.isDebugEnabled()) {
				logger.info("当前访问的资源" + URL + "不属于受限资源");
			}
			return;
		}else{
            for (ConfigAttribute attribute : configAttributes) {
                // 遍历访问用户当前的权限属性
                for (GrantedAuthority authority : authentication.getAuthorities()) {
                    if (attribute.getAttribute().equals(authority.getAuthority())) {
                        if (logger.isDebugEnabled()) {
                            logger.info("当前用户的角色：" + authority.getAuthority()
                                    + "满足访问条件");
                        }
                        return;
                    }
                }
            }
        }

		// 不满足访问条件抛出异常
		throw new AccessDeniedException(URL+"没有权限访问！");

	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	/**
     * @return the source
     */
    public MultipleAccessDecisionManager getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(MultipleAccessDecisionManager source) {
        this.source = source;
    }

}
