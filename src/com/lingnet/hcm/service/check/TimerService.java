package com.lingnet.hcm.service.check;

import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.check.CkTimer;

/**
 * 
 * @ClassName: TimerService 
 * @Description: 考勤时间Service 
 * @author wangqiang
 * @date 2017年4月11日 下午4:08:26 
 *
 */
public interface TimerService extends BaseService<CkTimer, String>{
	
	/**
	 * 根据年月份获取签到时间集合
	 * @Title: getListByCond 
	 * @param monthCalendar
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月18日 V 1.0
	 */
	Map<String, String> getListByCond(String jobNumber, String monthCalendar);
	
	/**
	 * 根据时间范围查询员工考勤时间集合串
	 * @Title: getAllListByCond 
	 * @param startTime
	 * @param endTime
	 * @param jobNumber
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月26日 V 1.0
	 */
	String getAllListByCond(String startTime, String endTime, String jobNumber);
}
