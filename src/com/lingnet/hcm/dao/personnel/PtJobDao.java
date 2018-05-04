package com.lingnet.hcm.dao.personnel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.person.PtJob;
import com.lingnet.util.Pager;
/**
 *	人员岗位兼职Dao
 */
public interface PtJobDao extends BaseDao<PtJob, String>{
	
	/**
	 * 
	 * @Title: getInfoByCond 
	 * @param pager
	 * @param searchData
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月22日 V 1.0
	 */
	public List<Map<String, Object>> getInfoByCond(Pager pager, String searchData, String depIds);
	
	/**
	 * 根据兼职ID查询人员信息
	 * @Title: getPersonInfoById 
	 * @param id
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月23日 V 1.0
	 */
	public Map<String, String> getPersonInfoById(String id);
	
	/**
	 * 导出员工考勤基本信息
	 * @Title: getCheckInfosByDepIds 
	 * @param depIdList
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月28日 V 1.0
	 */
	public List<Map<String, String>> getCheckInfosByDepIds(List<String> depIdList, String depIds);
	/**
	 * 
	 * @Title: getListData 
	 * @param id
	 * @return 
	 * @author ZRL
	 * @since 2017年3月23日 V 1.0
	 */
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds);
	
	public List<Object[]> getExceptData(String thePersonId);
}
