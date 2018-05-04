package com.lingnet.hcm.dao.check;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.check.CkMonthStatistics;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: MonthStatisticsDao 
 * @Description: 员工月度考勤统计Dao 
 * @author wangqiang
 * @date 2017年5月15日 下午1:41:06 
 *
 */
public interface MonthStatisticsDao extends BaseDao<CkMonthStatistics, String>{

	/**
	 * 根据条件获得考勤统计分页信息
	 * @Title: getStatisticsInfoByCond 
	 * @param pager
	 * @param yearMonth
	 * @param depId
	 * @param jobNumber
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月16日 V 1.0
	 */
	List<Map<String, String>> getStatisticsInfoByCond(Pager pager, String yearMonth, 
			String depId, String jobNumber);
	
	/**
	 * 根据条件查询考勤统计信息
	 * @Title: getStatisInfoBySearchs 
	 * @param yearMonth
	 * @param depIds
	 * @param jobNumber
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月16日 V 1.0
	 */
	List<Map<String, String>> getStatisInfoBySearchs(String yearMonth, String depIds, String jobNumber);
	
	/**
	 * 根据年月份查询部门下员工缓休天数信息集合
	 * @Title: getLastLatencyInfos 
	 * @param depId
	 * @param yearMonth
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月17日 V 1.0
	 */
	List<Map<String, String>> getLastLatencyInfos(String depId, String yearMonth);
	
	/**
	 * 根据条件查询月度考勤信息
	 * @Title: getReportInfoByCond 
	 * @param pager
	 * @param yearMonth
	 * @param depId
	 * @param jobNumber
	 * @param searchTb
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月19日 V 1.0
	 */
	List<Map<String, String>> getReportInfoByCond(Pager pager, String yearMonth, 
			String depId, String jobNumber, String searchTb);
	
	/**
	 * 
	 * @Title: getCheckInfoBySearchs 
	 * @param yearMonth
	 * @param depIds
	 * @param jobNumber
	 * @param searchTb
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月19日 V 1.0
	 */
	List<Map<String, String>> getCheckInfoBySearchs(String yearMonth, String depIds, 
			String jobNumber, String searchTb);
	
	/**
	 * 根据条件获得缺勤全月统计信息
	 * @Title: getAbsenceReportByCond 
	 * @param pager
	 * @param yearMonth
	 * @param depIds
	 * @param isAbsence
	 * @param searchTb
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月20日 V 1.0
	 */
	List<Map<String, String>> getAbsenceReportByCond(Pager pager, String yearMonth, 
			String depIds, String isAbsence, String searchTb);
	/**
	 * 获得出勤率报表pager信息
	 * @Title: getEmpNumsByDepId 
	 * @param pager
	 * @param yearMonth
	 * @param depIds
	 * @return 
	 * List<Map<String,String>> 
	 * @author 马晓鹏
	 * @since 2017年6月13日 V 1.0
	 */
	public List<Map<String, String>> getEmpNumsByDepId(Pager pager, String yearMonth, String depIds);
}
