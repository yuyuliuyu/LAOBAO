package com.lingnet.hcm.dao.check;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.check.CkRestRecord;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: RestRecordDao 
 * @Description: 休假申请记录Dao  
 * @author wangqiang
 * @date 2017年5月4日 下午2:15:19 
 *
 */
public interface RestRecordDao extends BaseDao<CkRestRecord, String>{
	
	/**
	 * 获得员工休假申请记录集合
	 * @Title: getInfoByCond 
	 * @param pager
	 * @param userId
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月3日 V 1.0
	 */
	public List<Map<String, Object>> getInfoByCond(Pager pager, String userId, String jobNumber, String state);
}
