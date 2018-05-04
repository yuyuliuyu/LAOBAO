package com.lingnet.hcm.dao.check;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.check.CkClass;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: ClassGroupDao 
 * @Description: 班组信息Dao 
 * @author wangqiang
 * @date 2017年3月22日 下午2:08:19 
 *
 */
public interface ClassGroupDao extends BaseDao<CkClass, String>{
	
	/**
	 * 获得班组分页信息集合
	 * @Title: getInfoByCond 
	 * @param pager
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月6日 V 1.0
	 */
	public List<Map<String, Object>> getInfoByCond(Pager pager, String userId);
	
	/**
	 * 获得班组信息集合
	 * @Title: getAllInfoList 
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月6日 V 1.0
	 */
	public List<Map<String, String>> getAllInfoList(String userId);
	
	/**
	 * 根据班号查询班组信息
	 * @Title: getInfoByClassNo 
	 * @param classNo
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月25日 V 1.0
	 */
	public CkClass getInfoByClassNo(String classNo);
	
	/**
	 * 根据用户ID查询班组信息集合
	 * @Title: getEntityListByUserId 
	 * @param userId
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月25日 V 1.0
	 */
	public List<CkClass> getEntityListByUserId(String userId);
}
