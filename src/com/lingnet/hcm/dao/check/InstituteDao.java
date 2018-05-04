package com.lingnet.hcm.dao.check;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.check.CkEmpInstitute;
import com.lingnet.util.Pager;

/**
 * 
 * @ClassName: InstituteDao
 * @Description: 考勤班次Dao
 * @author wangqiang
 * @date 2017年3月31日 上午10:57:24 
 *
 */
public interface InstituteDao extends BaseDao<CkEmpInstitute, String>{
	
	/**
	 * 获取考勤班次信息集合
	 * @Title: getInfoByCond 
	 * @param pager
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月31日 V 1.0
	 */
	public List<Map<String, Object>> getInfoByCond(Pager pager, String typeFlag);
	
	/**
	 * 获取所有考勤班次信息集合
	 * @Title: getAllInfoList 
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月31日 V 1.0
	 */
	public List<Map<String, String>> getAllInfoList();
	
	/**
	 * 根据班制名称Id查询班制信息
	 * @Title: getEmpInstituteByInstituteId 
	 * @param instituteId
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月25日 V 1.0
	 */
	public CkEmpInstitute getEmpInstituteByInstituteId(String instituteId);
}
