package com.lingnet.hcm.service.check;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.check.CkAnnualLeave;
/**
 * 
 * @ClassName: AnnualLeaveRecordService 
 * @Description: 员工年假休假记录Service
 * @author wangqiang
 * @date 2017年4月24日 下午5:24:02 
 *
 */
public interface AnnualLeaveRecordService extends BaseService<CkAnnualLeave, String>{
	
	/**
	 * 导出员工年假信息
	 * @Title: exportAnnualLeaveInfo 
	 * @param year
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月27日 V 1.0
	 */
	public HSSFWorkbook exportAnnualLeaveInfo(String year, String searchData);
}
