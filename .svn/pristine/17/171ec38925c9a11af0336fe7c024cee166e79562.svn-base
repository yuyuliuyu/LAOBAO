package com.lingnet.hcm.dao.check;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.check.CkAnnualLeave;
/**
 * 
 * @ClassName: AnnualLeaveRecordDao 
 * @Description: 年假休假记录Dao 
 * @author wangqiang
 * @date 2017年4月24日 下午5:19:11 
 *
 */
public interface AnnualLeaveRecordDao extends BaseDao<CkAnnualLeave, String>{
	
	/**
	 * 根据员工号集合查询员工年假休假记录集合
	 * @Title: getRecordByJobNumbers 
	 * @param jobNumbers
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月27日 V 1.0
	 */
	List<CkAnnualLeave> getRecordByJobNumbers(List<String> jobNumbers, String year);
}
