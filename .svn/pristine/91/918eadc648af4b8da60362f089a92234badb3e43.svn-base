package com.lingnet.hcm.service.check;

import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.check.CkYearDay;
/**
 * 
 * @ClassName: YearDayService 
 * @Description: 全年白班班制service 
 * @author wangqiang
 * @date 2017年4月14日 下午1:55:36 
 *
 */
public interface YearDayService extends BaseService<CkYearDay, String>{
	
	/**
	 * 根据年月份获取排班信息
	 * @Title: getDayInfoByCond 
	 * @param thisYear
	 * @param thisMonth
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月14日 V 1.0
	 */
	CkYearDay getDayInfoByCond(String thisYear, String thisMonth);
	
	/**
	 * 根据条件修改全年排班信息
	 * @Title: updateInfo 
	 * @param dataMap 
	 * @author wangqiang
	 * @since 2017年4月14日 V 1.0
	 */
	public void updateInfo(Map<String,String> dataMap);
}
