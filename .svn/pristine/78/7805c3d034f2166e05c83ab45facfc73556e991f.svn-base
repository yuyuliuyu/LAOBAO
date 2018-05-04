package com.lingnet.hcm.dao.check;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.check.CkOvertimeRecord;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: OvertimeRecordDao 
 * @Description: 加班申请记录Dao 
 * @author wangqiang
 * @date 2017年5月5日 上午10:56:51 
 *
 */
public interface OvertimeRecordDao extends BaseDao<CkOvertimeRecord, String>{
	
	/**
	 * 获得员工加班申请记录集合
	 * @Title: getInfoByCond 
	 * @param pager
	 * @param userId
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月3日 V 1.0
	 */
	public List<Map<String, Object>> getInfoByCond(Pager pager, String userId, String jobNumber, String state);
}
