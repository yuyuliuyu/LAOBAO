package com.lingnet.hcm.dao.check;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.check.CkInstitution;
import com.lingnet.util.Pager;

/**
 * 
 * @ClassName: InstitutionDao
 * @Description: 班制名称Dao
 * @author wangqiang
 * @date 2017年3月31日 上午10:57:24 
 *
 */
public interface InstitutionDao extends BaseDao<CkInstitution, String>{
	
	/**
	 * 获取班制名称信息集合
	 * @Title: getInfoByCond 
	 * @param pager
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月31日 V 1.0
	 */
	public List<Map<String, Object>> getInfoByCond(Pager pager);
	
	/**
	 * 获取所有班制名称信息集合
	 * @Title: getAllInfoList 
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月31日 V 1.0
	 */
	public List<Map<String, String>> getAllInfoList();
	
	/**
	 * 获取所有班制名称下拉集合
	 * @Title: getInstitutionList 
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月5日 V 1.0
	 */
	public List<Map<String, String>> getInstitutionList(String dayFlag);
	
	/**
	 * 根据班制名称获取班制ID
	 * @Title: getInstituteIdByName 
	 * @param name
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月17日 V 1.0
	 */
	public String getInstituteIdByName(String name);
	
	/**
	 * 根据班制名称获得班制名称信息
	 * @Title: getInfoByName 
	 * @param institueName
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月25日 V 1.0
	 */
	public CkInstitution getInfoByName(String institueName);
}
