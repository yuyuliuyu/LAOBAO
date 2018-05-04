package com.lingnet.util;

import org.apache.commons.lang.StringUtils;

import com.lingnet.common.entity.ResultEntity;

/**
 * 检查参数是否为空的工具类
 * @ClassName: CheckEmptyParamUtil 
 * @Description: 若有为空的参数，则直接返回错误实体类 
 * @author 李志新
 * @date 2014-12-16 上午9:20:15 
 */
public class CheckParamUtil {
	
	private static ResultEntity result;//返回结果
	
	public static String checkEmptyParam(Object... args){
		//遍历参数数组
		for (Object arg : args){
			if (null == arg || StringUtils.isBlank(arg.toString())){
				result = ResultFactory.getInstance();
				//若存在空值参数，返回错误结果。代号0003为参数是空
				result.setResult(0);
				result.setError(ResultFactory.getErrorInstance("0003","参数不能为空！"));
				//存在空值参数则返回信息
				return JsonUtil.toJson(result);
			}else{
				continue;
			}
		}
		//无空值参数则返回null
		return null;
	}
	
	/**
	 * 验证邮箱格式
	 * @Title: isEmail 
	 * @param email
	 * @return 
	 * Boolean 
	 * @author 李志新
	 * @since 2015-1-27 V 1.0
	 */
	public static Boolean isEmail(String email){
		String regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		return email.matches(regex);
	}

	/**
	 * 验证手机格式
	 * @Title: isPhone 
	 * @param phone
	 * @return 
	 * Boolean 
	 * @author 李志新
	 * @since 2015-1-27 V 1.0
	 */
	public static Boolean isPhone(String phone){
		String regex = "^1[3|4|5|7|8][0-9]\\d{8}$";
		return phone.matches(regex);
	}
}
