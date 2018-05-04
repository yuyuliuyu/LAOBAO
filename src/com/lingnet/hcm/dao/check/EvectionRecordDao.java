package com.lingnet.hcm.dao.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.check.CkEvectionRecord;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: EvectionRecordDao 
 * @Description: 出差申请记录Dao 
 * @author wangqiang
 * @date 2017年5月3日 上午8:59:54 
 *
 */
public interface EvectionRecordDao extends BaseDao<CkEvectionRecord, String>{
	
	/**
	 * 获得员工出差申请记录集合
	 * @Title: getInfoByCond 
	 * @param pager
	 * @param userId
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月3日 V 1.0
	 */
	public List<Map<String, Object>> getInfoByCond(Pager pager, String userId, String jobNumber, String state);
	
	public HashMap<String, Object> getListData(Pager pager, String searchData);
}
