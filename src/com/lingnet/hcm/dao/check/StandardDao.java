package com.lingnet.hcm.dao.check;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.check.CkStandard;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: StandardDao 
 * @Description: 考勤标准Dao 
 * @author wangqiang
 * @date 2017年3月22日 下午2:08:19 
 *
 */
public interface StandardDao extends BaseDao<CkStandard, String>{
	
	/**
	 * 获得考勤标准信息分页集合
	 * @Title: getInfoByCond 
	 * @param pager
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月6日 V 1.0
	 */
	public List<Map<String, Object>> getInfoByCond(Pager pager);
	
	/**
	 * 获得考勤标准信息集合
	 * @Title: getAllInfoList 
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月6日 V 1.0
	 */
	public List<Map<String, String>> getAllInfoList();
	
	/**
	 * 根据年月份查询考勤标准信息
	 * @Title: getStandardByYearCalendar 
	 * @param yearMonth
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月17日 V 1.0
	 */
	public CkStandard getStandardByYearCalendar(String yearMonth);
}
