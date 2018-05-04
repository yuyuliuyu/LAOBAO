package com.lingnet.hcm.dao.check;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.check.CkCheckHisInfo;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: CheckHisInfoDao 
 * @Description: 员工考勤历史Dao 
 * @author wangqiang
 * @date 2017年4月18日 上午8:48:44 
 *
 */
public interface CheckHisInfoDao extends BaseDao<CkCheckHisInfo, String>{
	
	/**
	 * 根据分页条件查询员工考勤历史集合
	 * @Title: getHisInfoByCond 
	 * @param pager
	 * @param startTime
	 * @param endTime
	 * @param depId
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月28日 V 1.0
	 */
	List<Map<String, String>> getHisInfoByCond(Pager pager, String startTime, String endTime, 
			String depId, String jobNumber);
	
	/**
	 * 根据查询条件查找员工考勤历史信息集合
	 * @Title: getHisInfoBySearchs 
	 * @param startTime
	 * @param endTime
	 * @param depIds
	 * @param jobNumber
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月1日 V 1.0
	 */
	List<Map<String, String>> getHisInfoBySearchs(String startTime, String endTime, String depIds, String jobNumber);
}
