package com.lingnet.util;

import com.lingnet.common.entity.ErrorEntity;
import com.lingnet.common.entity.ResultEntity;

/**
 * 生产返回实体类的工厂
 * @ClassName: ResultFactory 
 * @Description: TODO 
 * @author 李志新
 * @date 2014-12-15 下午5:32:02 
 */
public class ResultFactory {

	/**
	 * 无参返回结果实体类
	 * @Title: getInstance 
	 * @return 
	 * ResultEntity 
	 * @author 李志新
	 * @since 2014-12-16 V 1.0
	 */
	public static ResultEntity getInstance(){
		return new ResultEntity();
	}
	
	/**
	 * 有参返回结果实体类
	 * @Title: getInstance 
	 * @param result
	 * @param error
	 * @param data
	 * @return 
	 * ResultEntity 
	 * @author 李志新
	 * @since 2014-12-16 V 1.0
	 */
	public static ResultEntity getInstance(Integer result, 
			ErrorEntity error, String data){
		
		return new ResultEntity(result,error,data);
	}
	
	/**
	 * 无参错误实体类
	 * @Title: getErrorInstance 
	 * @return 
	 * ErrorEntity 
	 * @author 李志新
	 * @since 2014-12-16 V 1.0
	 */
	public static ErrorEntity getErrorInstance(){
		return new ErrorEntity();
	}
	
	/**
	 * 有参错误实体类
	 * @Title: getErrorInstance 
	 * @param errorCode
	 * @param errorInfo
	 * @return 
	 * ErrorEntity 
	 * @author 李志新
	 * @since 2014-12-16 V 1.0
	 */
	public static ErrorEntity getErrorInstance(String errorCode, 
			String errorInfo){
		return new ErrorEntity(errorCode,errorInfo);
	}
	
}
