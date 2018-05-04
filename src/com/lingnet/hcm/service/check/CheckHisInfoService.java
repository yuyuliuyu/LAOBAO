package com.lingnet.hcm.service.check;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.check.CkCheckHisInfo;
import com.lingnet.util.Pager;

/**
 * 
 * @ClassName: CheckHisInfoService 
 * @Description: 员工考勤历史service 
 * @author wangqiang
 * @date 2017年4月18日 上午8:52:24 
 *
 */
public interface CheckHisInfoService extends BaseService<CkCheckHisInfo, String>{
	
	/**
	 * 根据条件查询考勤历史信息
	 * @Title: getHisInfoData 
	 * @param pager
	 * @param searchData
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月28日 V 1.0
	 */
	Map<String, Object> getHisInfoData(Pager pager, String searchData);
	
	/**
	 * 根据ID查询考勤历史详情
	 * @Title: getDetailInfoById 
	 * @param id
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月1日 V 1.0
	 */
	Map<String, String> getDetailInfoById(String id);
	
	/**
	 * 根据条件导出员工考勤历史信息
	 * @Title: exportCheckHisInfo 
	 * @param startTime
	 * @param endTime
	 * @param jobNumber
	 * @param depIds
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月1日 V 1.0
	 */
	HSSFWorkbook exportCheckHisInfo(String startTime, String endTime, String jobNumber, String depIds);
}
