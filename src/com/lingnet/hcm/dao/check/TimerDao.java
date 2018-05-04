package com.lingnet.hcm.dao.check;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.check.CkTimer;
/**
 * 
 * @ClassName: TimerDao 
 * @Description: 考勤时间Dao 
 * @author wangqiang
 * @date 2017年4月11日 下午4:03:38 
 *
 */
public interface TimerDao extends BaseDao<CkTimer, String>{
	
	/**
	 * 根据年月份获取签到时间集合
	 * @Title: getListByCond 
	 * @param monthCalendar
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月18日 V 1.0
	 */
	List<CkTimer> getListByCond(String jobNumber, String monthCalendar);
	
	/**
	 * 根据职工号集合查询职工考勤时间记录
	 * @Title: getTimesByJobNumbers 
	 * @param jobNumbers
	 * @param startTime
	 * @param endTime
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月22日 V 1.0
	 */
	List<Map<String, Object>> getTimesByJobNumbers(String jobNumbers, String startTime, String endTime);
	
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
	
	/**
	 * 根据员工号集合查询考勤时间集合
	 * @Title: getCheckTimesByJobNumbers 
	 * @param jobNumbers
	 * @param monthCalendar
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月26日 V 1.0
	 */
	List<CkTimer> getCheckTimesByJobNumbers(List<String> jobNumbers, String monthCalendar);
}
