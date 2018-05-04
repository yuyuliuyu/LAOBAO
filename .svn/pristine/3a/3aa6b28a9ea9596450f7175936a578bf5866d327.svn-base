package com.lingnet.hcm.action.check;

import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.check.CkMonthStatistics;
import com.lingnet.hcm.service.check.AnnualLeaveRecordService;
import com.lingnet.hcm.service.check.CheckHisInfoService;
import com.lingnet.hcm.service.check.MonthStatisticsService;
import com.lingnet.hcm.service.check.TimerService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.JsonUtil;
/**
 * 
 * @ClassName: Statistics 
 * @Description: 统计查询控制层 
 * @author wangqiang
 * @date 2017年3月7日 下午4:44:05 
 *
 */
public class StatisticsAction extends BaseAction{
	
	private static final long serialVersionUID = 5623909238890670719L;
	private String year;//年度
	private String startTime;//起始年月份
	private String endTime;//截止年月份
	private String jobNumber;//职工号
	private String depIds;//部门ID集合
	private String yearMonth;//年月份
	private CkMonthStatistics statistics;//月度考勤统计
	
	@Resource(name = "personnelService")
    private PersonnelService personnelService;
	
	@Resource(name = "annualLeaveRecordService")
	private AnnualLeaveRecordService annualLeaveRecordService;
	
	@Resource(name = "checkHisInfoService")
	private CheckHisInfoService checkHisInfoService;
	
	@Resource(name = "timerService")
	private TimerService timerService;
	
	@Resource(name = "monthStatisticsService")
	private MonthStatisticsService monthStatisticsService;
	
	/**
	 * 年假查询树页面
	 * @Title: leaveTreeList 
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月15日 V 1.0
	 */
	public String leaveTreeList(){
        return "leaveTreeList";
    }
	
	/**
	 * 获取年假查询信息
	 * @Title: getAnnualLeaveData 
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月15日 V 1.0
	 */
	public String getAnnualLeaveData(){
		Map<String, Object> result = personnelService.getAnnualLeaveData(pager, searchData);
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
	
	/**
	 * 导出员工年假信息集合
	 * @Title: exportAnnualLeaveInfo  
	 * @author wangqiang
	 * @since 2017年4月27日 V 1.0
	 */
	public void exportAnnualLeaveInfo(){
		HttpServletResponse resp = getResponse(); 
    	try {
            HSSFWorkbook hwb = annualLeaveRecordService.exportAnnualLeaveInfo(year, searchData);
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-Disposition", "attachment;filename=\""
            		+ new String("员工年假信息".getBytes("gb2312"), "ISO8859-1")+ ".xls" + "\"");
            OutputStream out = resp.getOutputStream();
            hwb.write(out);
            out.flush();
            out.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/************************************************************/
	
	/**
	 * 考勤历史查询树页面
	 * @Title: checkHisTreeList 
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月15日 V 1.0
	 */
	public String checkHisTreeList(){
        return "checkHisTreeList";
    }
	
	/**
	 * 获取考勤历史查询信息
	 * @Title: getCheckHisData 
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月15日 V 1.0
	 */
	public String getCheckHisData(){
		Map<String, Object> result = checkHisInfoService.getHisInfoData(pager, searchData);
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
	
	/**
	 * 根据ID获取考勤历史信息详情
	 * @Title: getDetailInfoById 
	 * @return 
	 * @author wangqiang
	 * @since 2017年5月1日 V 1.0
	 */
	public String getDetailInfoById(){
		Map<String, String> infoMap = checkHisInfoService.getDetailInfoById(id);
		if (!infoMap.isEmpty()){
			//查询员工的最早昨晚打卡时间
			Map<String, String> timeMap = timerService.getListByCond(infoMap.get("detailJobNumber"), infoMap.get("detailMonthCalendar"));
			infoMap.putAll(timeMap);
		}
		String json = JsonUtil.Encode(infoMap);
		return ajax(Status.success, json);
	}
	
	/**
	 * 导出员工考勤历史信息
	 * @Title: exportCheckHisInfo  
	 * @author wangqiang
	 * @since 2017年5月1日 V 1.0
	 */
	public void exportCheckHisInfo(){
		HttpServletResponse resp = getResponse(); 
    	try {
            HSSFWorkbook hwb = checkHisInfoService.exportCheckHisInfo(startTime, endTime, jobNumber, depIds);
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-Disposition", "attachment;filename=\""
            		+ new String("员工考勤历史信息".getBytes("gb2312"), "ISO8859-1")+ ".xls" + "\"");
            OutputStream out = resp.getOutputStream();
            hwb.write(out);
            out.flush();
            out.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/*******************************************************/
	
	/**
	 * 月度考勤统计数页面
	 * @Title: hisMonthTreeList 
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月15日 V 1.0
	 */
	public String hisMonthTreeList(){
        return "hisMonthTreeList";
    }
	
	/**
	 * 获取月度考勤统计信息
	 */
	public String getMonthStatisticsData(){
		Map<String, Object> result = monthStatisticsService.getStatisticsInfoData(pager, searchData);
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
	
	/**
     * 进入编辑页面
     */
    public String editMonth() {
    	statistics = monthStatisticsService.get(id);
    	return "editMonth";
    }
    
    /**
     * 修改员工考勤统计信息
     * @Title: updateStatisInfos 
     * @return 
     * @author wangqiang
     * @since 2017年5月16日 V 1.0
     */
    public String updateStatisInfos() {
    	try {
    		monthStatisticsService.updateStatisInfos(formdata);
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.error, "操作失败"); 
		}
    	return ajax(Status.success, "success");
    }
	
	/**
	 * 导出月度考勤统计信息
	 * @Title: exportMonthStatistics
	 * @author wangqiang
	 * @since 2017年5月15日 V 1.0
	 */
	public void exportMonthStatistics(){
		HttpServletResponse resp = getResponse(); 
    	try {
            HSSFWorkbook hwb = monthStatisticsService.exportStatisticsInfo(yearMonth, jobNumber, depIds);
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-Disposition", "attachment;filename=\""
            		+ new String("月度考勤统计信息".getBytes("gb2312"), "ISO8859-1")+ ".xls" + "\"");
            OutputStream out = resp.getOutputStream();
            hwb.write(out);
            out.flush();
            out.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getDepIds() {
		return depIds;
	}

	public void setDepIds(String depIds) {
		this.depIds = depIds;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public CkMonthStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(CkMonthStatistics statistics) {
		this.statistics = statistics;
	}
	
}
