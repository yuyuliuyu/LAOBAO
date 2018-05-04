package com.lingnet.hcm.dao.check;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.check.CkInfoCommit;

/**
 * 
 * @ClassName: AuditInfosDao 
 * @Description: 员工考勤审核记录Dao 
 * @author wangqiang
 * @date 2017年4月19日 上午11:22:05 
 *
 */
public interface AuditInfosDao extends BaseDao<CkInfoCommit, String>{
	
	/**
	 * 根据条件查询考勤的审核状态
	 * @Title: getAuditStatusByCond 
	 * @param userId
	 * @param yearMonth
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月19日 V 1.0
	 */
	public Map<String, String> getAuditStatusByCond(String userId, String yearMonth);
	
	/**
	 * 根据条件查询人员
	 * @Title: getInfoByCond 
	 * @param pager
	 * @param depIds
	 * @param yearMonth
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月19日 V 1.0
	 */
	public List<Map<String, String>> getInfoByCond(String depIds, String yearMonth);
	
	/**
	 * 根据条件查询审核状态
	 * @Title: getAuditInfoByCond 
	 * @param depId
	 * @param yearMonth
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月21日 V 1.0
	 */
	public Map<String, String> getAuditInfoByCond(String depId, String yearMonth);
	
	/**
	 * 根据年月份查询上报信息
	 * @Title: getInfoByYearMonth 
	 * @param userId
	 * @param yearMonth
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月21日 V 1.0
	 */
	public CkInfoCommit getInfoByYearMonth(String userId, String yearMonth);

	/**
	 * @Title: 考勤数据维护上报时判断是否已经审核通过
	 * @param depId
	 * @param yearMonth
	 * @return 
	 * Map<String,String> 
	 * @author zhanghj
	 * @since 2017年9月6日 V 1.0
	 */
    public Map<String, String> getReportStatus(String depId, String yearMonth);
}
