package com.lingnet.hcm.service.check;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.check.CkCheckInfo;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: CheckInfosService 
 * @Description: 考勤动态service 
 * @author wangqiang
 * @date 2017年4月11日 下午4:33:56 
 *
 */
public interface CheckInfosService extends BaseService<CkCheckInfo, String>{
	
	/**
	 * 获取考勤动态信息
	 * @Title: getDataByCond 
	 * @param pager
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月11日 V 1.0
	 */
	Map<String, Object> getDataByCond(Pager pager, String searchData) throws Exception;
	
	/**
	 * 同步考勤机最新数据
	 * @Title: syncCheckData  
	 * @author wangqiang
	 * @since 2017年4月12日 V 1.0
	 */
	public void syncCheckData() throws Exception;
	
	/**
	 * 生成月度考勤表
	 * @Title: generateMonthTable 
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年4月13日 V 1.0
	 */
	public void generateMonthTable(Date beginDate, String monthDate, 
			String year, String month) throws Exception;
	
	/**
	 * 修改人员考勤班制信息
	 * @Title: updateCheckInfo 
	 * @param formdata
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年4月19日 V 1.0
	 */
	public void updateCheckInfo(String formdata) throws Exception;
	
	/**
	 * 查询当前考勤月份天数
	 * @Title: findMonthDays 
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月19日 V 1.0
	 */
	public Map<String, Object> findMonthDaysAndYearMonth();
	
	/**
	 * 根据员工id集合查询员工考勤动态信息
	 * @Title: getDataByEmpIds 
	 * @param pager
	 * @param empIdLists
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月20日 V 1.0
	 */
	public List<Map<String, String>> getDataByEmpIds(Pager pager, List<String> empIdLists);
	
	/**
	 * 根据条件获取按日考勤数据集合
	 * @Title: getDayDataByCond 
	 * @param pager
	 * @param searchData
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月21日 V 1.0
	 */
	Map<String, Object> getDayDataByCond(Pager pager, String searchData);
	
	/**
	 * 根据id查询员工考勤信息
	 * @Title: getInfoById 
	 * @param id
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月22日 V 1.0
	 */
	Map<String, String> getInfoById(String id, String day);
	
	/**
	 * 修改日期间的考勤内容
	 * @Title: updateCheckInfoByDate 
	 * @param instituteName
	 * @param startDate
	 * @param endDate
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年5月4日 V 1.0
	 */
	void updateCheckInfoByDate(String instituteName, Date startDate, Date endDate, String jobNumber) throws Exception;

	/**
	 * 修改日期间的加班状态
	 * @Title: updateOverTimeByDate 
	 * @param overtime
	 * @param startDate
	 * @param endDate
	 * @param jobNumber
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年5月5日 V 1.0
	 */
	void updateOverTimeByDate(String overtime, Date startDate, Date endDate, String jobNumber) throws Exception;
}
