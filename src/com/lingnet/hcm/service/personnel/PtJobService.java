package com.lingnet.hcm.service.personnel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.person.PtJob;
import com.lingnet.util.Pager;

/**
 * 人员岗位兼职service
 */
public interface PtJobService extends BaseService<PtJob, String>{
	
	/**
	 * 
	 * @Title: getDataByCond 
	 * @param pager
	 * @param searchData
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月22日 V 1.0
	 */
	public Map<String, Object> getDataByCond(Pager pager, String searchData);
	
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
	 * @Title: exportEmpInfo 
	 * @param depIdList
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月28日 V 1.0
	 */
	public HSSFWorkbook exportInfoByDepIds(List<String> depIdList);
	/**
	 * 兼职列表数据
	 * @Title: exportEmpInfo 
	 * @param depIdList
	 * @return 
	 * @author zrl
	 * @since 2017年3月28日 V 1.0
	 */
	public HashMap getListData(Pager pager, String searchData);
}
