package com.lingnet.qxgl.security.shared;


/**
 * 抽象类,接口实现类注入参数
 * 
 * @author Jerry
 * 
 */
public abstract class AbstarctDirectUrlResolver implements DirectUrlResolver {

	protected String pattern; // URL登陆依据
	protected String parameterName; // 参数名
	protected String directUrl; // 跳转登陆地址
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getDirectUrl() {
		return directUrl;
	}

	public void setDirectUrl(String directUrl) {
		this.directUrl = directUrl;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
    
}
