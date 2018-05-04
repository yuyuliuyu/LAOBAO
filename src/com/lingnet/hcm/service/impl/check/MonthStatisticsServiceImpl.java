package com.lingnet.hcm.service.impl.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.check.CheckInfosDao;
import com.lingnet.hcm.dao.check.ContentDao;
import com.lingnet.hcm.dao.check.MonthStatisticsDao;
import com.lingnet.hcm.dao.check.StandardDao;
import com.lingnet.hcm.entity.DeptPosition;
import com.lingnet.hcm.entity.check.CkContent;
import com.lingnet.hcm.entity.check.CkInfoCommit;
import com.lingnet.hcm.entity.check.CkMonthStatistics;
import com.lingnet.hcm.entity.check.CkStandard;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.service.check.AuditInfosService;
import com.lingnet.hcm.service.check.MonthStatisticsService;
import com.lingnet.qxgl.service.BackendDepService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: MonthStatisticsServiceImpl 
 * @Description: 月度考勤统计service实现类 
 * @author wangqiang
 * @date 2017年5月15日 下午1:57:14 
 *
 */
@Service("monthStatisticsService")
public class MonthStatisticsServiceImpl extends BaseServiceImpl<CkMonthStatistics, String> 
	implements MonthStatisticsService{

	@Resource(name = "monthStatisticsDao")
	private MonthStatisticsDao monthStatisticsDao;
	
	@Resource(name = "checkInfosDao")
	private CheckInfosDao checkInfosDao;
	
	@Resource(name = "contentDao")
	private ContentDao contentDao;
	
	@Resource(name="backendDepService")
    private BackendDepService backendDepService;
	@Resource(name="auditInfosService")
	private AuditInfosService auditInfosService;
	
	@Resource(name = "standardDao")
	private StandardDao standardDao;

	@Override
	public String saveMonthStatistics(CkInfoCommit ckInfo, String companyId, int status, String opinion) throws Exception {
	    ckInfo.setAuditStatus(status);//审核状态
	    ckInfo.setOpinion(opinion);//审核意见
	    auditInfosService.update(ckInfo);
	    if (status == 2) {// 审核不通过
	        return "success";
	    }
		String yearMonth = ckInfo.getMonthCalendar();//年月份
		String depId = ckInfo.getDepId();
		String empIds = ckInfo.getPersonIds();//员工号集合串
//		String[] empIdArrs = empIds.split(",");
//		String empIdStr = "";
//		for (int i=0; i < empIdArrs.length; i++){
//			empIdStr += ("'"+empIdArrs[i]+"', ");
//		}
//		if (!"".equals(empIdStr)){
//			empIdStr = empIdStr.substring(0, empIdStr.length()-2);
//		}
		//查询部门考勤内容信息
        List<CkContent> contentList = contentDao.getContentListByDepId(companyId, new ArrayList<String>());
//        List<CkContent> contentList = contentDao.getContentListByDepId(depId, new ArrayList<String>());
		Map<String, CkContent> contentMap = new HashMap<String, CkContent>();
		if (contentList.size() > 0){
			for (CkContent content:contentList){
				contentMap.put(content.getContent(), content);
			}
		}
		//查询员工考勤信息
		List<Map<String, String>> list = checkInfosDao.getEmpCheckInfosByIds(empIds);
		//统计
		List<CkMonthStatistics> statisticsList = statisticsMonthInfo(list, contentMap, depId, yearMonth);
		//批量保存
//		try {
	        saveBatch(statisticsList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

		return "success";
	}
	
	/**
	 * 统计员工月考勤
	 * @Title: statisticsMonthInfo 
	 * @param list
	 * @param contentMap
	 * @param depId
	 * @return 
	 * @author wangqiang
	 * @throws Exception 
	 * @since 2017年5月15日 V 1.0
	 */
	public List<CkMonthStatistics> statisticsMonthInfo(List<Map<String, String>> list, 
			Map<String, CkContent> contentMap, String depId, String yearMonth) throws Exception{
		List<CkMonthStatistics> statisticsList = new ArrayList<CkMonthStatistics>();
		if (list.size() > 0){
			//查询上月该部门员工缓休信息
			String lastMonth = "";
			if ("1".equals(yearMonth.substring(2, 4))){
				int beginYear = Integer.parseInt(yearMonth.substring(0, 2))-1;
				lastMonth = (beginYear<10?"0"+beginYear:beginYear)+"12";
			} else {
				int beginMonth = Integer.parseInt(yearMonth.substring(2, 4))-1;
				lastMonth = yearMonth.substring(0, 2) + "" + (beginMonth<10?"0"+beginMonth:beginMonth);
			}
			List<Map<String, String>> lastList = monthStatisticsDao.getLastLatencyInfos(depId, lastMonth);
			Map<String, String> lastMap = new HashMap<String, String>();//上月员工缓休天数map
			if (lastList.size() > 0){
				for(Map<String, String> mapInfo :lastList){
					lastMap.put(mapInfo.get("jobNumber"), mapInfo.get("latency"));
				}
			}
			//查询节假日日期
			CkStandard standard = standardDao.getStandardByYearCalendar(yearMonth);//获得改年月份的考勤标准
			List<String> standardList = new ArrayList<String>();
			if (standard != null){
				if (standard.getFestival1() != null && !"".equals(standard.getFestival1())){
					standardList.add(standard.getFestival1());
				}
				if (standard.getFestival2() != null && !"".equals(standard.getFestival2())){
					standardList.add(standard.getFestival2());
				}
				if (standard.getFestival3() != null && !"".equals(standard.getFestival3())){
					standardList.add(standard.getFestival3());
				}
				if (standard.getFestival4() != null && !"".equals(standard.getFestival4())){
					standardList.add(standard.getFestival4());
				}
			}
			for (Map<String, String> map:list){
				CkMonthStatistics info = new CkMonthStatistics();
				info.setJobNumber(map.get("jobNumber"));
				info.setName(map.get("name"));
				info.setWageForm(map.get("wageForm"));
				info.setPostName(map.get("postName"));
				info.setStandardPostName(map.get("standardPostName"));
				info.setSalaryPostName(map.get("salaryPostName"));
				info.setInstituteName(map.get("instituteName"));
				info.setEmpType(map.get("empType"));
				info.setRemark(map.get("remark"));
				info.setDepId(depId);
				info.setYearMonth(yearMonth);
				String wageForm = map.get("wageForm");//工资形式（0 计时， 1 计件）
				double outHours = 0.0;//总出勤小时数
				//节加（根据维护的考勤标准中节假日判断是否是节假日）
				int jjTotal = 0;// 节加
				List<String> vacationList = new ArrayList<String>();
				if (standardList.size() > 0){
					for (String day:standardList){
					    int dayOther = Integer.valueOf(day);
						if ("1".equals(map.get("overtime"+dayOther))){
							vacationList.add("overtime"+dayOther);//添加到节日加班集合
							String institute = map.get("day"+dayOther);
							CkContent content = contentMap.get(institute);
							if (content != null){
							    jjTotal++;
								info.setVacationModulus(info.getVacationModulus()+(1.0*content.getVacationModulus()));//节加（根据维护的考勤标准中节假日判断是否是节假日）
							}
						}
					}
				}
				//统计考勤内容的天数
				String instituteStr = "";
				Map<String, Object> numMap = new HashMap<String, Object>();
				Set<String> keys = map.keySet();
				BasicInformation jbi = get(BasicInformation.class, Restrictions.eq("jobNumber", info.getJobNumber()));
				int jbHigh = 0;// 加班上限
				if (null != jbi) {
				    DeptPosition deptPosition = get(DeptPosition.class, Restrictions.eq("id", jbi.getSpecificPostId()));
				    if (null != deptPosition) jbHigh = null == deptPosition.getOvertimemax()? 0 : deptPosition.getOvertimemax();
				}
				int jbCount = 0;// 加班个数
				Double jbTotal = 0d;// 加班系数与个数*
				Double hxTotal = 0d;// 加班系数与个数*
				for (String key :keys){
					if (key.indexOf("day") >= 0){
						instituteStr += (map.get(key)+",");
					}
					//休加（除节日加班之外的加班）
					if (key.indexOf("overtime") >= 0){
						if (!"overtimeAll".equals(key) && "1".equals(map.get(key)) && !vacationList.contains(key)){
							String institute = map.get("day"+key.substring(8, key.length()));
							CkContent content = contentMap.get(institute);
							if (content != null){
							    jbCount++;
							    if (jbCount <= jbHigh)
							        jbTotal += content.getOvertimeModulus();
							    else hxTotal ++;
//								info.setOvertimeModulus(info.getOvertimeModulus()+(1.0*jbxs));//休加
							}
						}
					} 
				}
				info.setOvertimeModulus(jbTotal);

				if (!"".equals(instituteStr)){
					instituteStr = instituteStr.substring(0, instituteStr.length()-1);
					String[] instituteArrs = instituteStr.split(",");
					for (int i=0; i < instituteArrs.length; i++){
						if (!"null".equals(instituteArrs[i]) && !"".equals(instituteArrs[i])){
							if (i==0){
								numMap.put(instituteArrs[i], 1);
							} else {
								if (numMap.containsKey(instituteArrs[i])){
									numMap.put(instituteArrs[i], Integer.parseInt(numMap.get(instituteArrs[i])+"")+1);
								} else {
									numMap.put(instituteArrs[i], 1);
								}
							}
						}
					}
				}
				if (!numMap.isEmpty()){
					Set<String> institutes = numMap.keySet();
					for(String institute:institutes){
						CkContent content = contentMap.get(institute);
						if (null == content) throw new Exception("考勤统计中没有 "+institute);
						Object value = numMap.get(institute);
						double num = Double.parseDouble(value+"");
						if (content.getOutHour() != null && !"".equals(content.getOutHour())){
							outHours += content.getOutHour()*num;
						}
						if (content.getOutDuty() != null && !"".equals(content.getOutDuty())){
							info.setOutDuty(info.getOutDuty()+(content.getOutDuty()*num));//出勤
						}
						if (content.getDaytime() != null && !"".equals(content.getDaytime())){
							info.setDaytime(info.getDaytime()+(content.getDaytime()*num));//白
						}
						if (content.getNight() != null && !"".equals(content.getNight())){
							info.setNight(info.getNight()+(content.getNight()*num));//夜
						}
						if (content.getOffDuty() != null && !"".equals(content.getOffDuty())){
							info.setOffDuty(info.getOffDuty()+(content.getOffDuty()*num));//下
						}
						if (content.getCentre() != null && !"".equals(content.getCentre())){
							info.setCentre(info.getCentre()+(content.getCentre()*num));//中
						}
						if (content.getOverseas() != null && !"".equals(content.getOverseas())){
							info.setOverseas(info.getOverseas()+(content.getOverseas()*num));//外
						}
						if (content.getRest() != null && !"".equals(content.getRest())){
							info.setRest(info.getRest()+(content.getRest()*num));//休
						}
						if (content.getAllDay() != null && !"".equals(content.getAllDay())){
							info.setAlls(info.getAlls()+(content.getAllDay()*num));//全
						}
						if (content.getTake() != null && !"".equals(content.getTake())){
							info.setTake(info.getTake()+(content.getTake()*num));//带
						}
						if (content.getFlip() != null && !"".equals(content.getFlip())){
							info.setFlip(info.getFlip()+(content.getYear()*num));//弹
						}
						if (content.getCall() != null && !"".equals(content.getCall())){
							info.setTakeWork(info.getTakeWork()+(content.getCall()*num));//调
						}
						if (content.getChanges() != null && !"".equals(content.getChanges())){
							info.setChanges(info.getChanges()+(content.getChanges()*num));//换
						}
						if (content.getDisease() != null && !"".equals(content.getDisease())){
							info.setDisease(info.getDisease()+(content.getDisease()));//病
						}
						if (content.getThing() != null && !"".equals(content.getThing())){
							info.setThing(info.getThing()+(content.getThing()*num));//事
						}
						if (content.getMarry() != null && !"".equals(content.getMarry())){
							info.setMarry(info.getMarry()+(content.getMarry()*num));//婚
						}
						if (content.getInjury() != null && !"".equals(content.getInjury())){
							info.setInjury(info.getInjury()+(content.getInjury()*num));//伤
						}
						if (content.getLost() != null && !"".equals(content.getLost())){
							info.setLost(info.getLost()+(content.getLost()*num));//丧
						}
						if (content.getGive() != null && !"".equals(content.getGive())){
							info.setGive(info.getGive()+(content.getGive()*num));//产
						}
						if (content.getExplore() != null && !"".equals(content.getExplore())){
							info.setExplore(info.getExplore()+(content.getExplore()*num));//探
						}
						if (content.getFree() != null && !"".equals(content.getFree())){
							info.setFree(info.getFree()+(content.getFree()*num));//旷
						}
						if (content.getStay() != null && !"".equals(content.getStay())){
							info.setStay(info.getStay()+(content.getStay()*num));//待
						}
						if (content.getStudy() != null && !"".equals(content.getStudy())){
							info.setStudy(info.getStudy()+(content.getStudy()*num));//学
						}
						if (content.getVery() != null && !"".equals(content.getVery())){
							info.setVery(info.getVery()+(content.getVery()*num));//非
						}
						if (content.getBusiness() != null && !"".equals(content.getBusiness())){
							info.setBusiness(info.getBusiness()+(content.getBusiness()*num));//公
						}
						if (content.getEvection() != null && !"".equals(content.getEvection())){
							info.setEvection(info.getEvection()+(content.getEvection()*num));//差
						}
						if (content.getTeam() != null && !"".equals(content.getTeam())){
							info.setTeam(info.getTeam()+(content.getTeam()*num));//团
						}
						if (content.getBorrow() != null && !"".equals(content.getBorrow())){
							info.setBorrow(info.getBorrow()+(content.getBorrow()*num));//借
						}
						if (content.getRetreat() != null && !"".equals(content.getRetreat())){
							info.setRetreat(info.getRetreat()+(content.getRetreat()*num));//退
						}
						if (content.getDiction() != null && !"".equals(content.getDiction())){
							info.setDiction(info.getDiction()+(content.getDiction()*num));//辞
						}
						if (content.getLeave() != null && !"".equals(content.getLeave())){
							info.setLeave(info.getLeave()+(content.getLeave()*num));//离
						}
						if ("计件".equals(wageForm)){//若是计件
							if (content.getPiece() != null && !"".equals(content.getPiece())){
								info.setPiece(info.getPiece()+(content.getPiece()*num));//计件
							}
						}
						if (content.getYear() != null && !"".equals(content.getYear())){
							info.setYears(info.getYears()+(content.getYear()*num));//年休
						}
						if (content.getOther() != null && !"".equals(content.getOther())){
							info.setOther(info.getOther()+(content.getOther()*num));//其他
						}
						if (content.getOutWork() != null && !"".equals(content.getOutWork())){
							info.setOutWork(info.getOutWork()+(content.getOutWork()*num));//出工
						}
						if (content.getBigClass() != null && !"".equals(content.getBigClass())){
							info.setBigClass(info.getBigClass()+(content.getBigClass()*num));//大班
						}
						if (content.getSmallClass() != null && !"".equals(content.getSmallClass())){
							info.setSmallClass(info.getSmallClass()+(content.getSmallClass()*num));//小班
						}
						if (content.getBigNight() != null && !"".equals(content.getBigNight())){
							info.setBigNight(info.getBigNight()+(content.getBigNight()*num));//大夜
						}
						if (content.getSmallNight() != null && !"".equals(content.getSmallNight())){
							info.setSmallNight(info.getSmallNight()+(content.getSmallNight()*num));//小夜
						}
					}
				}
				//缓休（之前未支付加班费的天数：上月缓休天数+本月总加班天数-应付加班天数-当月换休天数）
				double lastLatencyDays = 0.0;
				String jobNumber = map.get("jobNumber");
				if(lastMap.get(jobNumber) != null){
					lastLatencyDays = Double.valueOf(lastMap.get(map.get("jobNumber")));//上月缓休天数
				}
				double allOvertimes = Double.parseDouble(map.get("overtimeAll"));//本月总加班天数
				double overtimemax = Double.parseDouble(map.get("overtimemax"));//应付加班天数
//				double overtimemax = hxTotal;//应付加班天数
				double changes = info.getChanges();//当月换休天数
				double latencyLast = lastLatencyDays+allOvertimes-overtimemax-changes-jjTotal;// 减去节加
				info.setLatency(latencyLast<0.0?0.0:latencyLast);
				//延时小时数
				String isSpecialHour = map.get("isSpecialHour");//是否特殊工时（0 否，1 是） 
				if ("1".equals(isSpecialHour)){//若是特殊工时
					double time = outHours-167.0;
					info.setDelayedModulus(time<0.0?0.0:time);
				}
				info.setTotals(info.getDaytime()+info.getNight()+info.getOffDuty()+info.getCentre()+info.getOverseas()
						+info.getRest()+info.getAlls()+info.getTake()+info.getFlip()+info.getTakeWork()+info.getChanges()
						+info.getDisease()+info.getThing()+info.getMarry()+info.getInjury()+info.getLost()+info.getGive()
						+info.getExplore()+info.getFree()+info.getStay()+info.getStudy()+info.getVery()+info.getBusiness()
						+info.getEvection()+info.getTeam()+info.getBorrow()+info.getRetreat()+info.getDiction()+info.getLeave()
//						+info.getPiece()+info.getYears()+info.getOther());//合计
						+info.getYears()+info.getOther());//合计,去掉计件
				info.setAttendanceDays(info.getDaytime()+info.getNight()+info.getCentre()+info.getOverseas()+info.getAlls()
						+info.getStudy()+info.getVery()+info.getBusiness()+info.getEvection());//出勤天数
				info.setNightDays(info.getNight()+info.getAlls());//夜班
				info.setIsDelete(0);
				statisticsList.add(info);
			}
		}
		return statisticsList;
	}

	@Override
	public Map<String, Object> getStatisticsInfoData(Pager pager,
			String searchData) {
		String yearMonth = "";
		String jobNumber = "";
		String depId = "";
		String depIds = "";
		Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
		if (dataMap != null){
			yearMonth = dataMap.get("yearMonth");
			jobNumber = dataMap.get("jobNumber");
			depId = dataMap.get("depId");
		}
		if (depId == null || "".equals(depId)){
			//获得当前用户的所有权限部门集合
			depIds = getAllDepIdStrs();
		} else {
			String[] depIdArrs = depId.split(",");
			for (String dId:depIdArrs){
				if (dId != null && !"".equals(dId)){
//					depIds += ("'"+dId+"', "); 

                    depIds  += dId.replace("'", "") + ","; 
				}
			}
			depIds = depIds.substring(0, depIds.length()-1);
		}
        depIds  = depIds.replace("'", ""); 
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (searchData != null){
			list = monthStatisticsDao.getStatisticsInfoByCond(pager, yearMonth, depIds, jobNumber);
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("data", list);
		returnMap.put("total", pager.getTotalCount());
		return returnMap;
	}
	
	/**
	 * @Description 获得当前用户的所有权限部门集合
	 * @Title: getAllDepIdStr 
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月30日 V 1.0
	 */
	@SuppressWarnings("rawtypes")
	public String getAllDepIdStrs(){
		String depIds = "";
  		String userId = toolUtil.getUserId();
  		List data = this.findBySql("SELECT B.ID FROM BRANCH B " 
  				+ "INNER JOIN QX_USER_SHOWAUTH UD ON UD.BRANCH_DEP=B.ID AND UD.FLG='0' AND UD.USERID = '"+ userId +"' " 
  				+ "WHERE B.IS_DELETE = 0 ");
  		if (data!=null&&data.size()>0){
  			for (int i=0;i<data.size();i++){
  				Object b = (Object)data.get(i);
//  				depIds += ("'"+ (b==null?"":b.toString()) + "', ");
  				//获取下级部门
  				List dataP = backendDepService.findBySql("SELECT D.PARENT,D.ID FROM QX_DEPARTMENT D " +
               		"INNER JOIN QX_USER_SHOWAUTH UD ON UD.BRANCH_DEP=D.ID AND UD.FLG='1' AND UD.USERID='"+userId+"' " +
               		"WHERE D.BARCH_ID='"+b+"' AND D.IS_DELETE=0 ");
  				for (int k=0; k<dataP.size(); k++){
  					Object[] dep=(Object[])dataP.get(k);
            	    depIds += ("'"+ (dep[1]==null?"":dep[1].toString()) + "', ");
  				}
  			}
  		}
  		if (!"".equals(depIds)){
  			depIds = depIds.substring(0,depIds.length()-2);
  		}
  		return depIds;
	}

	@Override
	public void updateStatisInfos(String formdata) throws Exception {
		Map<String,String> dataMap = JsonUtil.parseProperties(formdata);
		if (!dataMap.isEmpty()){
			if (!"".equals(dataMap.get("id"))){//修改
				CkMonthStatistics info = monthStatisticsDao.get(dataMap.get("id"));
				info.setDaytime(Double.valueOf(dataMap.get("daytime")));
				info.setNight(Double.valueOf(dataMap.get("night")));
				info.setOffDuty(Double.valueOf(dataMap.get("offDuty")));
				info.setCentre(Double.valueOf(dataMap.get("centre")));
				info.setOverseas(Double.valueOf(dataMap.get("overseas")));
				info.setRest(Double.valueOf(dataMap.get("rest")));
				info.setAlls(Double.valueOf(dataMap.get("alls")));
				info.setTake(Double.valueOf(dataMap.get("take")));
				info.setFlip(Double.valueOf(dataMap.get("flip")));
				info.setTakeWork(Double.valueOf(dataMap.get("takeWork")));
				info.setChanges(Double.valueOf(dataMap.get("changes")));
				info.setDisease(Double.valueOf(dataMap.get("disease")));
				info.setThing(Double.valueOf(dataMap.get("thing")));
				info.setMarry(Double.valueOf(dataMap.get("marry")));
				info.setInjury(Double.valueOf(dataMap.get("injury")));
				info.setLost(Double.valueOf(dataMap.get("lost")));
				info.setGive(Double.valueOf(dataMap.get("give")));
				info.setExplore(Double.valueOf(dataMap.get("explore")));
				info.setFree(Double.valueOf(dataMap.get("free")));
				info.setStay(Double.valueOf(dataMap.get("stay")));
				info.setStudy(Double.valueOf(dataMap.get("study")));
				info.setVery(Double.valueOf(dataMap.get("very")));
				info.setBusiness(Double.valueOf(dataMap.get("business")));
				info.setEvection(Double.valueOf(dataMap.get("evection")));
				info.setTeam(Double.valueOf(dataMap.get("team")));
				info.setBorrow(Double.valueOf(dataMap.get("borrow")));
				info.setRetreat(Double.valueOf(dataMap.get("retreat")));
				info.setDiction(Double.valueOf(dataMap.get("diction")));
				info.setLeave(Double.valueOf(dataMap.get("leave")));
				info.setPiece(Double.valueOf(dataMap.get("piece")));
				info.setYears(Double.valueOf(dataMap.get("years")));
				info.setOther(Double.valueOf(dataMap.get("other")));
				info.setOutWork(Double.valueOf(dataMap.get("outWork")));
				info.setBigClass(Double.valueOf(dataMap.get("bigClass")));
				info.setSmallClass(Double.valueOf(dataMap.get("smallClass")));
				info.setBigNight(Double.valueOf(dataMap.get("bigNight")));
				info.setSmallNight(Double.valueOf(dataMap.get("smallNight")));
				info.setTotals(Double.valueOf(dataMap.get("totals")));
				info.setAttendanceDays(Double.valueOf(dataMap.get("attendanceDays")));
				info.setNightDays(Double.valueOf(dataMap.get("nightDays")));
				info.setVacationModulus(Double.valueOf(dataMap.get("vacationModulus")));
				info.setOvertimeModulus(Double.valueOf(dataMap.get("overtimeModulus")));
				info.setDelayedModulus(Double.valueOf(dataMap.get("delayedModulus")));
				info.setLatency(Double.valueOf(dataMap.get("latency")));
				info.setRemark(dataMap.get("remark"));
				monthStatisticsDao.update(info);
			}
		}
	}

	@Override
	public HSSFWorkbook exportStatisticsInfo(String yearMonth,
			String jobNumber, String depIds) {
		String[] cellname={
				"序号", "职工号", "年月份", "姓名", "部门", "标准岗位", "具体岗位", "薪酬岗位", "人员类别", "班制", "工资形式", "白", "夜", "下", "中", "外", "休",
				"全", "带", "弹", "调", "换", "病", "事", "婚", "伤", "丧", "产", "探", "旷", "待", "学", "非", "公", 
				"差", "团", "借", "退", "辞", "离", "计件", "年休", "其他", "出工", "大班", "小班", "大夜", "小夜", "合计",
				"出勤天数", "夜班", "节加", "休加", "延时小时数", "缓休", "备注"};
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("月度考勤统计信息");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        HSSFCellStyle stycle = hwb.createCellStyle();
        stycle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < cellname.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(cellname[i]);
            cell.setCellStyle(stycle);
            sheet.setColumnWidth((short) i, cellname[i].getBytes().length * 600);
        }
        if (depIds == null || "".equals(depIds)){
			//获得当前用户的所有权限部门集合
			depIds = getAllDepIdStrs();
		} else {
			String[] depIdArrs = depIds.split(",");
			depIds = "";
			for (String dId:depIdArrs){
				if (dId != null && !"".equals(dId)){
					depIds += ("'"+dId+"', "); 
				}
			}
			depIds = depIds.substring(0, depIds.length()-2);
		}
        //查询班制名称信息
        List<Map<String, String>> list = monthStatisticsDao.getStatisInfoBySearchs(yearMonth, depIds, jobNumber);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	Map<String, String> info = list.get(i);
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i+1+"");
                if(info.get("jobNumber") != null && !"null".equals(info.get("jobNumber"))){ 
                	row.createCell(1).setCellValue(info.get("jobNumber")); 
                }
                if(info.get("yearMonth") != null && !"null".equals(info.get("yearMonth"))){ 
                	row.createCell(2).setCellValue(info.get("yearMonth")); 
                }
                if(info.get("name") != null && !"null".equals(info.get("name"))){ 
                	row.createCell(3).setCellValue(info.get("name"));
                }
                if(info.get("depName") != null && !"null".equals(info.get("depName"))){
                	row.createCell(4).setCellValue(info.get("depName"));
                }
                if(info.get("standardPostName") != null && !"null".equals(info.get("standardPostName"))){ 
                	row.createCell(5).setCellValue(info.get("standardPostName")); 
                }
                if(info.get("postName") != null && !"null".equals(info.get("postName"))){ 
                	row.createCell(6).setCellValue(info.get("postName")); 
                }
                if(info.get("salaryPostName") != null && !"null".equals(info.get("salaryPostName"))){ 
                	row.createCell(7).setCellValue(info.get("salaryPostName")); 
                }
                if(info.get("empType") != null && !"null".equals(info.get("empType"))){ 
                	row.createCell(8).setCellValue(info.get("empType")); 
                }
                if(info.get("instituteName") != null && !"null".equals(info.get("instituteName"))){ 
                	row.createCell(9).setCellValue(info.get("instituteName")); 
                }
                if(info.get("wageForm") != null && !"null".equals(info.get("wageForm"))){ 
                	row.createCell(10).setCellValue(info.get("wageForm")); 
                }
                if(info.get("daytime") != null && !"null".equals(info.get("daytime"))){ 
                	row.createCell(11).setCellValue(info.get("daytime")); 
                }
                if(info.get("night") != null && !"null".equals(info.get("night"))){ 
                	row.createCell(12).setCellValue(info.get("night")); 
                }
                if(info.get("offDuty") != null && !"null".equals(info.get("offDuty"))){ 
                	row.createCell(13).setCellValue(info.get("offDuty")); 
                }
                if(info.get("centre") != null && !"null".equals(info.get("centre"))){ 
                	row.createCell(14).setCellValue(info.get("centre")); 
                }
                if(info.get("overseas") != null && !"null".equals(info.get("overseas"))){ 
                	row.createCell(15).setCellValue(info.get("overseas")); 
                }
                if(info.get("rest") != null && !"null".equals(info.get("rest"))){ 
                	row.createCell(16).setCellValue(info.get("rest")); 
                }
                if(info.get("alls") != null && !"null".equals(info.get("alls"))){ 
                	row.createCell(17).setCellValue(info.get("alls")); 
                }
                if(info.get("take") != null && !"null".equals(info.get("take"))){ 
                	row.createCell(18).setCellValue(info.get("take")); 
                }
                if(info.get("flip") != null && !"null".equals(info.get("flip"))){ 
                	row.createCell(19).setCellValue(info.get("flip")); 
                }
                if(info.get("takeWork") != null && !"null".equals(info.get("takeWork"))){ 
                	row.createCell(20).setCellValue(info.get("takeWork")); 
                }
                if(info.get("changes") != null && !"null".equals(info.get("changes"))){ 
                	row.createCell(21).setCellValue(info.get("changes")); 
                }
                if(info.get("disease") != null && !"null".equals(info.get("disease"))){ 
                	row.createCell(22).setCellValue(info.get("disease")); 
                }
                if(info.get("thing") != null && !"null".equals(info.get("thing"))){ 
                	row.createCell(23).setCellValue(info.get("thing")); 
                }
                if(info.get("marry") != null && !"null".equals(info.get("marry"))){ 
                	row.createCell(24).setCellValue(info.get("marry")); 
                }
                if(info.get("injury") != null && !"null".equals(info.get("injury"))){ 
                	row.createCell(25).setCellValue(info.get("injury")); 
                }
                if(info.get("lost") != null && !"null".equals(info.get("lost"))){ 
                	row.createCell(26).setCellValue(info.get("lost")); 
                }
                if(info.get("give") != null && !"null".equals(info.get("give"))){ 
                	row.createCell(27).setCellValue(info.get("give")); 
                }
                if(info.get("explore") != null && !"null".equals(info.get("explore"))){ 
                	row.createCell(28).setCellValue(info.get("explore")); 
                }
                if(info.get("free") != null && !"null".equals(info.get("free"))){ 
                	row.createCell(29).setCellValue(info.get("free")); 
                }
                if(info.get("stay") != null && !"null".equals(info.get("stay"))){ 
                	row.createCell(30).setCellValue(info.get("stay")); 
                }
                if(info.get("study") != null && !"null".equals(info.get("study"))){ 
                	row.createCell(31).setCellValue(info.get("study")); 
                }
                if(info.get("very") != null && !"null".equals(info.get("very"))){ 
                	row.createCell(32).setCellValue(info.get("very")); 
                }
                if(info.get("business") != null && !"null".equals(info.get("business"))){ 
                	row.createCell(33).setCellValue(info.get("business")); 
                }
                if(info.get("evection") != null && !"null".equals(info.get("evection"))){ 
                	row.createCell(34).setCellValue(info.get("evection")); 
                }
                if(info.get("team") != null && !"null".equals(info.get("team"))){ 
                	row.createCell(35).setCellValue(info.get("team")); 
                }
                if(info.get("borrow") != null && !"null".equals(info.get("borrow"))){ 
                	row.createCell(36).setCellValue(info.get("borrow")); 
                }
                if(info.get("retreat") != null && !"null".equals(info.get("retreat"))){ 
                	row.createCell(37).setCellValue(info.get("retreat")); 
                }
                if(info.get("diction") != null && !"null".equals(info.get("diction"))){ 
                	row.createCell(38).setCellValue(info.get("diction")); 
                }
                if(info.get("leave") != null && !"null".equals(info.get("leave"))){ 
                	row.createCell(39).setCellValue(info.get("leave")); 
                }
                if(info.get("piece") != null && !"null".equals(info.get("piece"))){ 
                	row.createCell(40).setCellValue(info.get("piece")); 
                }
                if(info.get("years") != null && !"null".equals(info.get("years"))){ 
                	row.createCell(41).setCellValue(info.get("years")); 
                }
                if(info.get("other") != null && !"null".equals(info.get("other"))){ 
                	row.createCell(42).setCellValue(info.get("other")); 
                }
                if(info.get("outWork") != null && !"null".equals(info.get("outWork"))){ 
                	row.createCell(43).setCellValue(info.get("outWork")); 
                }
                if(info.get("bigClass") != null && !"null".equals(info.get("bigClass"))){ 
                	row.createCell(44).setCellValue(info.get("bigClass")); 
                }
                if(info.get("smallClass") != null && !"null".equals(info.get("smallClass"))){ 
                	row.createCell(45).setCellValue(info.get("smallClass")); 
                }
                if(info.get("bigNight") != null && !"null".equals(info.get("bigNight"))){ 
                	row.createCell(46).setCellValue(info.get("bigNight")); 
                }
                if(info.get("smallNight") != null && !"null".equals(info.get("smallNight"))){ 
                	row.createCell(47).setCellValue(info.get("smallNight")); 
                }
                if(info.get("totals") != null && !"null".equals(info.get("totals"))){ 
                	row.createCell(48).setCellValue(info.get("totals")); 
                }
                if(info.get("attendanceDays") != null && !"null".equals(info.get("attendanceDays"))){ 
                	row.createCell(49).setCellValue(info.get("attendanceDays")); 
                }
                if(info.get("nightDays") != null && !"null".equals(info.get("nightDays"))){ 
                	row.createCell(50).setCellValue(info.get("nightDays")); 
                }
                if(info.get("vacationModulus") != null && !"null".equals(info.get("vacationModulus"))){ 
                	row.createCell(51).setCellValue(info.get("vacationModulus")); 
                }
                if(info.get("overtimeModulus") != null && !"null".equals(info.get("overtimeModulus"))){ 
                	row.createCell(52).setCellValue(info.get("overtimeModulus")); 
                }
                if(info.get("delayedModulus") != null && !"null".equals(info.get("delayedModulus"))){ 
                	row.createCell(53).setCellValue(info.get("delayedModulus")); 
                }
                if(info.get("latency") != null && !"null".equals(info.get("latency"))){ 
                	row.createCell(54).setCellValue(info.get("latency")); 
                }
                if(info.get("remark") != null && !"null".equals(info.get("remark"))){ 
                	row.createCell(55).setCellValue(info.get("remark")); 
                }
            }
        }
        return hwb;
	}

	@Override
	public Map<String, Object> getReportDataByCond(Pager pager,
			String searchData) {
		String yearMonth = "";
		String jobNumber = "";
		String depId = "";
		String depIds = "";
		Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
		if (dataMap != null){
			yearMonth = dataMap.get("yearMonth");
			jobNumber = dataMap.get("jobNumber");
			depId = dataMap.get("depId");
		}
		if (depId == null || "".equals(depId)){
			//获得当前用户的所有权限部门集合
			depIds = getAllDepIdStrs();
		} else {
			String[] depIdArrs = depId.split(",");
			for (String dId:depIdArrs){
				if (dId != null && !"".equals(dId)){
					depIds += ("'"+dId+"',"); 
				    depIds = depIds.replace("'", ""); 
				}
			}
//			depIds = depIds.substring(0, depIds.length()-2);
//            depIds = depIds.replace(",", "");
//            depIds = depIds.replace(" ", ""); 
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (searchData != null){
			List<Object> infos = checkInfosDao.findIdByMonthCalendar(yearMonth);
			String searchTb = "";
			if (infos.size() > 0){//查询考勤信息表
				searchTb = "ck_emp_info";
			} else {//查询考勤历史信息表
				searchTb = "ck_emp_hisinfo";
			}
			list = monthStatisticsDao.getReportInfoByCond(pager, yearMonth, depIds, jobNumber, searchTb);
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("data", list);
		returnMap.put("total", pager.getTotalCount());
		return returnMap;
	}

	@Override
	public HSSFWorkbook exportMonthCheckInfo(String yearMonth,
			String jobNumber, String depIds) {
		String[] cellname={
				"序号", "职工号", "年月份", "姓名", "部门", "标准岗位", "具体岗位", "薪酬岗位", "人员类别", "班制", "工资形式", "1", "2", "3", "4", "5", 
				"6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", 
				"22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "白", "夜", "下", "中", "外", "休",
				"全", "带", "弹", "调", "换", "病", "事", "婚", "伤", "丧", "产", "探", "旷", "待", "学", "非", "公", 
				"差", "团", "借", "退", "辞", "离", "计件", "年休", "其他", "出工", "大班", "小班", "大夜", "小夜", "合计",
				"出勤天数", "夜班", "节加", "休加", "延时小时数", "缓休"};
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("员工月度考勤表信息");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        HSSFCellStyle stycle = hwb.createCellStyle();
        stycle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < cellname.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(cellname[i]);
            cell.setCellStyle(stycle);
            sheet.setColumnWidth((short) i, cellname[i].getBytes().length * 600);
        }
        if (depIds == null || "".equals(depIds)){
			//获得当前用户的所有权限部门集合
			depIds = getAllDepIdStrs();
		} else {
			String[] depIdArrs = depIds.split(",");
			depIds = "";
			for (String dId:depIdArrs){
				if (dId != null && !"".equals(dId)){
					depIds += ("'"+dId+"', "); 
				}
			}
			depIds = depIds.substring(0, depIds.length()-2);
		}
        //查询班制名称信息
        List<Object> infos = checkInfosDao.findIdByMonthCalendar(yearMonth);
		String searchTb = "";
		if (infos.size() > 0){//查询考勤信息表
			searchTb = "ck_emp_info";
		} else {//查询考勤历史信息表
			searchTb = "ck_emp_hisinfo";
		}
        List<Map<String, String>> list = monthStatisticsDao.getCheckInfoBySearchs(yearMonth, depIds, jobNumber, searchTb);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	Map<String, String> info = list.get(i);
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i+1+"");
                if(info.get("jobNumber") != null && !"null".equals(info.get("jobNumber"))){ 
                	row.createCell(1).setCellValue(info.get("jobNumber")); 
                }
                if(info.get("yearMonth") != null && !"null".equals(info.get("yearMonth"))){ 
                	row.createCell(2).setCellValue(info.get("yearMonth")); 
                }
                if(info.get("name") != null && !"null".equals(info.get("name"))){ 
                	row.createCell(3).setCellValue(info.get("name"));
                }
                if(info.get("depName") != null && !"null".equals(info.get("depName"))){
                	row.createCell(4).setCellValue(info.get("depName"));
                }
                if(info.get("standardPostName") != null && !"null".equals(info.get("postName"))){ 
                	row.createCell(5).setCellValue(info.get("postName")); 
                }
                if(info.get("postName") != null && !"null".equals(info.get("postName"))){ 
                	row.createCell(6).setCellValue(info.get("postName")); 
                }
                if(info.get("salaryPostName") != null && !"null".equals(info.get("postName"))){ 
                	row.createCell(7).setCellValue(info.get("postName")); 
                }
                if(info.get("empType") != null && !"null".equals(info.get("empType"))){ 
                	row.createCell(8).setCellValue(info.get("empType")); 
                }
                if(info.get("instituteName") != null && !"null".equals(info.get("instituteName"))){ 
                	row.createCell(9).setCellValue(info.get("instituteName")); 
                }
                if(info.get("wageForm") != null && !"null".equals(info.get("wageForm"))){ 
                	row.createCell(10).setCellValue(info.get("wageForm")); 
                }
                if(info.get("day1") != null && !"null".equals(info.get("day1"))){ 
                	row.createCell(11).setCellValue(info.get("day1")); 
                }
                if(info.get("day2") != null && !"null".equals(info.get("day2"))){ 
                	row.createCell(12).setCellValue(info.get("day2")); 
                }
                if(info.get("day3") != null && !"null".equals(info.get("day3"))){ 
                	row.createCell(13).setCellValue(info.get("day3")); 
                }
                if(info.get("day4") != null && !"null".equals(info.get("day4"))){ 
                	row.createCell(14).setCellValue(info.get("day4")); 
                }
                if(info.get("day5") != null && !"null".equals(info.get("day5"))){ 
                	row.createCell(15).setCellValue(info.get("day5")); 
                }
                if(info.get("day6") != null && !"null".equals(info.get("day6"))){ 
                	row.createCell(16).setCellValue(info.get("day6")); 
                }
                if(info.get("day7") != null && !"null".equals(info.get("day7"))){ 
                	row.createCell(17).setCellValue(info.get("day7")); 
                }
                if(info.get("day8") != null && !"null".equals(info.get("day8"))){ 
                	row.createCell(18).setCellValue(info.get("day8")); 
                }
                if(info.get("day9") != null && !"null".equals(info.get("day9"))){ 
                	row.createCell(19).setCellValue(info.get("day9")); 
                }
                if(info.get("day10") != null && !"null".equals(info.get("day10"))){ 
                	row.createCell(20).setCellValue(info.get("day10")); 
                }
                if(info.get("day11") != null && !"null".equals(info.get("day11"))){ 
                	row.createCell(21).setCellValue(info.get("day11")); 
                }
                if(info.get("day12") != null && !"null".equals(info.get("day12"))){ 
                	row.createCell(22).setCellValue(info.get("day12")); 
                }
                if(info.get("day13") != null && !"null".equals(info.get("day13"))){ 
                	row.createCell(23).setCellValue(info.get("day13")); 
                }
                if(info.get("day14") != null && !"null".equals(info.get("day14"))){ 
                	row.createCell(24).setCellValue(info.get("day14")); 
                }
                if(info.get("day15") != null && !"null".equals(info.get("day15"))){ 
                	row.createCell(25).setCellValue(info.get("day15")); 
                }
                if(info.get("day16") != null && !"null".equals(info.get("day16"))){ 
                	row.createCell(26).setCellValue(info.get("day16")); 
                }
                if(info.get("day17") != null && !"null".equals(info.get("day17"))){ 
                	row.createCell(27).setCellValue(info.get("day17")); 
                }
                if(info.get("day18") != null && !"null".equals(info.get("day18"))){ 
                	row.createCell(28).setCellValue(info.get("day18")); 
                }
                if(info.get("day19") != null && !"null".equals(info.get("day19"))){ 
                	row.createCell(29).setCellValue(info.get("day19")); 
                }
                if(info.get("day20") != null && !"null".equals(info.get("day20"))){ 
                	row.createCell(30).setCellValue(info.get("day20")); 
                }
                if(info.get("day21") != null && !"null".equals(info.get("day21"))){ 
                	row.createCell(31).setCellValue(info.get("day21")); 
                }
                if(info.get("day22") != null && !"null".equals(info.get("day22"))){ 
                	row.createCell(32).setCellValue(info.get("day22")); 
                }
                if(info.get("day23") != null && !"null".equals(info.get("day23"))){ 
                	row.createCell(33).setCellValue(info.get("day23")); 
                }
                if(info.get("day24") != null && !"null".equals(info.get("day24"))){ 
                	row.createCell(34).setCellValue(info.get("day24")); 
                }
                if(info.get("day25") != null && !"null".equals(info.get("day25"))){ 
                	row.createCell(35).setCellValue(info.get("day25")); 
                }
                if(info.get("day26") != null && !"null".equals(info.get("day26"))){ 
                	row.createCell(36).setCellValue(info.get("day26")); 
                }
                if(info.get("day27") != null && !"null".equals(info.get("day27"))){ 
                	row.createCell(37).setCellValue(info.get("day27")); 
                }
                if(info.get("day28") != null && !"null".equals(info.get("day28"))){ 
                	row.createCell(38).setCellValue(info.get("day28")); 
                }
                if(info.get("day29") != null && !"null".equals(info.get("day29"))){ 
                	row.createCell(39).setCellValue(info.get("day29")); 
                }
                if(info.get("day30") != null && !"null".equals(info.get("day30"))){ 
                	row.createCell(40).setCellValue(info.get("day30")); 
                }
                if(info.get("day31") != null && !"null".equals(info.get("day31"))){ 
                	row.createCell(41).setCellValue(info.get("day31")); 
                }
                if(info.get("daytime") != null && !"null".equals(info.get("daytime"))){ 
                	row.createCell(42).setCellValue(info.get("daytime")); 
                }
                if(info.get("night") != null && !"null".equals(info.get("night"))){ 
                	row.createCell(43).setCellValue(info.get("night")); 
                }
                if(info.get("offDuty") != null && !"null".equals(info.get("offDuty"))){ 
                	row.createCell(44).setCellValue(info.get("offDuty")); 
                }
                if(info.get("centre") != null && !"null".equals(info.get("centre"))){ 
                	row.createCell(45).setCellValue(info.get("centre")); 
                }
                if(info.get("overseas") != null && !"null".equals(info.get("overseas"))){ 
                	row.createCell(46).setCellValue(info.get("overseas")); 
                }
                if(info.get("rest") != null && !"null".equals(info.get("rest"))){ 
                	row.createCell(47).setCellValue(info.get("rest")); 
                }
                if(info.get("alls") != null && !"null".equals(info.get("alls"))){ 
                	row.createCell(48).setCellValue(info.get("alls")); 
                }
                if(info.get("take") != null && !"null".equals(info.get("take"))){ 
                	row.createCell(49).setCellValue(info.get("take")); 
                }
                if(info.get("flip") != null && !"null".equals(info.get("flip"))){ 
                	row.createCell(50).setCellValue(info.get("flip")); 
                }
                if(info.get("takeWork") != null && !"null".equals(info.get("takeWork"))){ 
                	row.createCell(51).setCellValue(info.get("takeWork")); 
                }
                if(info.get("changes") != null && !"null".equals(info.get("changes"))){ 
                	row.createCell(52).setCellValue(info.get("changes")); 
                }
                if(info.get("disease") != null && !"null".equals(info.get("disease"))){ 
                	row.createCell(53).setCellValue(info.get("disease")); 
                }
                if(info.get("thing") != null && !"null".equals(info.get("thing"))){ 
                	row.createCell(54).setCellValue(info.get("thing")); 
                }
                if(info.get("marry") != null && !"null".equals(info.get("marry"))){ 
                	row.createCell(55).setCellValue(info.get("marry")); 
                }
                if(info.get("injury") != null && !"null".equals(info.get("injury"))){ 
                	row.createCell(56).setCellValue(info.get("injury")); 
                }
                if(info.get("lost") != null && !"null".equals(info.get("lost"))){ 
                	row.createCell(57).setCellValue(info.get("lost")); 
                }
                if(info.get("give") != null && !"null".equals(info.get("give"))){ 
                	row.createCell(58).setCellValue(info.get("give")); 
                }
                if(info.get("explore") != null && !"null".equals(info.get("explore"))){ 
                	row.createCell(59).setCellValue(info.get("explore")); 
                }
                if(info.get("free") != null && !"null".equals(info.get("free"))){ 
                	row.createCell(60).setCellValue(info.get("free")); 
                }
                if(info.get("stay") != null && !"null".equals(info.get("stay"))){ 
                	row.createCell(61).setCellValue(info.get("stay")); 
                }
                if(info.get("study") != null && !"null".equals(info.get("study"))){ 
                	row.createCell(62).setCellValue(info.get("study")); 
                }
                if(info.get("very") != null && !"null".equals(info.get("very"))){ 
                	row.createCell(63).setCellValue(info.get("very")); 
                }
                if(info.get("business") != null && !"null".equals(info.get("business"))){ 
                	row.createCell(64).setCellValue(info.get("business")); 
                }
                if(info.get("evection") != null && !"null".equals(info.get("evection"))){ 
                	row.createCell(65).setCellValue(info.get("evection")); 
                }
                if(info.get("team") != null && !"null".equals(info.get("team"))){ 
                	row.createCell(66).setCellValue(info.get("team")); 
                }
                if(info.get("borrow") != null && !"null".equals(info.get("borrow"))){ 
                	row.createCell(67).setCellValue(info.get("borrow")); 
                }
                if(info.get("retreat") != null && !"null".equals(info.get("retreat"))){ 
                	row.createCell(68).setCellValue(info.get("retreat")); 
                }
                if(info.get("diction") != null && !"null".equals(info.get("diction"))){ 
                	row.createCell(69).setCellValue(info.get("diction")); 
                }
                if(info.get("leave") != null && !"null".equals(info.get("leave"))){ 
                	row.createCell(70).setCellValue(info.get("leave")); 
                }
                if(info.get("piece") != null && !"null".equals(info.get("piece"))){ 
                	row.createCell(71).setCellValue(info.get("piece")); 
                }
                if(info.get("years") != null && !"null".equals(info.get("years"))){ 
                	row.createCell(72).setCellValue(info.get("years")); 
                }
                if(info.get("other") != null && !"null".equals(info.get("other"))){ 
                	row.createCell(73).setCellValue(info.get("other")); 
                }
                if(info.get("outWork") != null && !"null".equals(info.get("outWork"))){ 
                	row.createCell(74).setCellValue(info.get("outWork")); 
                }
                if(info.get("bigClass") != null && !"null".equals(info.get("bigClass"))){ 
                	row.createCell(75).setCellValue(info.get("bigClass")); 
                }
                if(info.get("smallClass") != null && !"null".equals(info.get("smallClass"))){ 
                	row.createCell(76).setCellValue(info.get("smallClass")); 
                }
                if(info.get("bigNight") != null && !"null".equals(info.get("bigNight"))){ 
                	row.createCell(77).setCellValue(info.get("bigNight")); 
                }
                if(info.get("smallNight") != null && !"null".equals(info.get("smallNight"))){ 
                	row.createCell(78).setCellValue(info.get("smallNight")); 
                }
                if(info.get("totals") != null && !"null".equals(info.get("totals"))){ 
                	row.createCell(79).setCellValue(info.get("totals")); 
                }
                if(info.get("attendanceDays") != null && !"null".equals(info.get("attendanceDays"))){ 
                	row.createCell(80).setCellValue(info.get("attendanceDays")); 
                }
                if(info.get("nightDays") != null && !"null".equals(info.get("nightDays"))){ 
                	row.createCell(81).setCellValue(info.get("nightDays")); 
                }
                if(info.get("vacationModulus") != null && !"null".equals(info.get("vacationModulus"))){ 
                	row.createCell(82).setCellValue(info.get("vacationModulus")); 
                }
                if(info.get("overtimeModulus") != null && !"null".equals(info.get("overtimeModulus"))){ 
                	row.createCell(83).setCellValue(info.get("overtimeModulus")); 
                }
                if(info.get("delayedModulus") != null && !"null".equals(info.get("delayedModulus"))){ 
                	row.createCell(84).setCellValue(info.get("delayedModulus")); 
                }
                if(info.get("latency") != null && !"null".equals(info.get("latency"))){ 
                	row.createCell(85).setCellValue(info.get("latency")); 
                }
            }
        }
        return hwb;
	}

	@Override
	public Map<String, Object> getAbsenceReportByCond(Pager pager,
			String searchData) {
		String yearMonth = "";
		String isAbsence = "";
		String depId = "";
		String depIds = "";
		Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
		if (dataMap != null){
			yearMonth = dataMap.get("yearMonth");
			isAbsence = dataMap.get("isAbsence");
			depId = dataMap.get("depId");
		}
		if (depId == null || "".equals(depId)){
			//获得当前用户的所有权限部门集合
			depIds = getAllDepIdStrs();
		} else {
			String[] depIdArrs = depId.split(",");
			for (String dId:depIdArrs){
				if (dId != null && !"".equals(dId)){
					depIds += ("'"+dId+"', "); 
				}
			}
			depIds = depIds.substring(0, depIds.length()-2);
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (searchData != null){
			List<Object> infos = checkInfosDao.findIdByMonthCalendar(yearMonth);
			String searchTb = "";
			if (infos.size() > 0){//查询考勤信息表
				searchTb = "ck_emp_info";
			} else {//查询考勤历史信息表
				searchTb = "ck_emp_hisinfo";
			}
			list = monthStatisticsDao.getAbsenceReportByCond(pager, yearMonth, depIds, isAbsence, searchTb);
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("data", list);
		returnMap.put("total", pager.getTotalCount());
		return returnMap;
	}

    @Override
    public HSSFWorkbook exportAbsenceCheckInfo(String yearMonth,
            String isAbsence, String depIds) { 

        String[] cellname={
                "序号", "职工号", "年月份", "姓名", "部门", "班制", "标准岗位", "具体岗位", "薪酬岗位", 
                 "病假", "事假",  "工伤","探假",  "产假",  "旷工" };
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("缺勤月度考勤统计信息");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        HSSFCellStyle stycle = hwb.createCellStyle();
        stycle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < cellname.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(cellname[i]);
            cell.setCellStyle(stycle);
            sheet.setColumnWidth((short) i, cellname[i].getBytes().length * 600);
        }
        if (depIds == null || "".equals(depIds)){
            //获得当前用户的所有权限部门集合
            depIds = getAllDepIdStrs();
        } else {
            String[] depIdArrs = depIds.split(",");
            depIds = "";
            for (String dId:depIdArrs){
                if (dId != null && !"".equals(dId)){
                    depIds += ("'"+dId+"', "); 
                }
            }
            depIds = depIds.substring(0, depIds.length()-2);
        }
        //查询班制名称信息

        List<Object> infos = checkInfosDao.findIdByMonthCalendar(yearMonth);
        String searchTb = "";
        if (infos.size() > 0){//查询考勤信息表
            searchTb = "ck_emp_info";
        } else {//查询考勤历史信息表
            searchTb = "ck_emp_hisinfo";
        }
        Pager pager=new Pager();
        pager.setPageSize(100000);
        pager.setPageNumber(1);
        List<Map<String, String>> list = monthStatisticsDao.getAbsenceReportByCond(pager, yearMonth, depIds, isAbsence, searchTb) ;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> info = list.get(i);
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i+1+"");
                if(info.get("jobNumber") != null && !"null".equals(info.get("jobNumber"))){ 
                    row.createCell(1).setCellValue(info.get("jobNumber")); 
                }
                if(info.get("yearMonth") != null && !"null".equals(info.get("yearMonth"))){ 
                    row.createCell(2).setCellValue(info.get("yearMonth")); 
                }
                if(info.get("name") != null && !"null".equals(info.get("name"))){ 
                    row.createCell(3).setCellValue(info.get("name"));
                }
                if(info.get("depName") != null && !"null".equals(info.get("depName"))){
                    row.createCell(4).setCellValue(info.get("depName"));
                }
                if(info.get("standardPostName") != null && !"null".equals(info.get("standardPostName"))){ 
                    row.createCell(5).setCellValue(info.get("standardPostName")); 
                }
                if(info.get("instituteName") != null && !"null".equals(info.get("instituteName"))){ 
                    row.createCell(6).setCellValue(info.get("instituteName")); 
                }
                if(info.get("postName") != null && !"null".equals(info.get("postName"))){ 
                    row.createCell(7).setCellValue(info.get("postName")); 
                }
                if(info.get("salaryPostName") != null && !"null".equals(info.get("salaryPostName"))){ 
                    row.createCell(8).setCellValue(info.get("salaryPostName")); 
                }
                if(info.get("disease") != null && !"null".equals(info.get("disease"))){ 
                    row.createCell(9).setCellValue(info.get("disease")); 
                }
                if(info.get("thing") != null && !"null".equals(info.get("thing"))){ 
                    row.createCell(10).setCellValue(info.get("thing")); 
                } 
                if(info.get("injury") != null && !"null".equals(info.get("injury"))){ 
                    row.createCell(11).setCellValue(info.get("injury")); 
                }
                if(info.get("explore") != null && !"null".equals(info.get("explore"))){ 
                    row.createCell(12).setCellValue(info.get("explore")); 
                }
                if(info.get("give") != null && !"null".equals(info.get("give"))){ 
                    row.createCell(13).setCellValue(info.get("give")); 
                }
                if(info.get("free") != null && !"null".equals(info.get("free"))){ 
                    row.createCell(14).setCellValue(info.get("free")); 
                }
            }
        }
        return hwb;
    }
    

    @Override
    public Map<String, Object> getRateDataByCond(Pager pager, String searchData) {
        String yearMonth = "";
        String depId = "";
        String depIds = "";
        Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
        if (dataMap != null){
            yearMonth = dataMap.get("yearMonth");
            depId = dataMap.get("depId");
        }
        if (depId == null || "".equals(depId)){
            //获得当前用户的所有权限部门集合
//            depIds = getAllDepIdStrs();
        } else {
            depIds = "'"+StringUtils.join(depId.split(","), "', '")+"'";
        }
        List<Map<String, String>> depInfos = new ArrayList<Map<String, String>>();
        if (searchData != null){
            //获得各部门的考勤统计信息
            List<Map<String, String>> deps = monthStatisticsDao.getStatisInfoBySearchs(yearMonth, depIds, "");
            //获得各部门的人数及相关信息
            depInfos = monthStatisticsDao.getEmpNumsByDepId(pager, yearMonth, depIds);
            double outWork = 0.0;
            if (deps.size() > 0){
                for (Map<String, String> depMap:deps){
                    String currDepId = depMap.get("depId");
                    for (Map<String, String> infoMap:depInfos){
                        if (currDepId.equals(infoMap.get("depId"))){
                            infoMap.put("depName", depMap.get("depName"));
                            infoMap.put("disease", (Double.parseDouble(infoMap.get("disease"))
                                    +Double.parseDouble(depMap.get("disease")))+"");
                            infoMap.put("injury", (Double.parseDouble(infoMap.get("injury"))
                                    +Double.parseDouble(depMap.get("injury")))+"");
                            infoMap.put("thing", (Double.parseDouble(infoMap.get("thing"))
                                    +Double.parseDouble(depMap.get("thing")))+"");
                            infoMap.put("free", (Double.parseDouble(infoMap.get("free"))
                                    +Double.parseDouble(depMap.get("free")))+"");
                            infoMap.put("marry", (Double.parseDouble(infoMap.get("marry"))
                                    +Double.parseDouble(depMap.get("marry")))+"");
                            infoMap.put("lost", (Double.parseDouble(infoMap.get("lost"))
                                    +Double.parseDouble(depMap.get("lost")))+"");
                            infoMap.put("explore", (Double.parseDouble(infoMap.get("explore"))
                                    +Double.parseDouble(depMap.get("explore")))+"");
                            infoMap.put("give", (Double.parseDouble(infoMap.get("give"))
                                    +Double.parseDouble(depMap.get("give")))+"");
                            try {
                                infoMap.put("actualOutDays", (Double.parseDouble(infoMap.get("actualOutDays"))
                                        +Double.parseDouble(depMap.get("outDuty")))+"");
                            } catch (Exception e) {
                                infoMap.put("actualOutDays", "");
                            }
                            outWork += Double.parseDouble(depMap.get("outWork"));
                        }
                    }
                }
            }
            if (depInfos.size() > 0){
                String restDays = "0";//公休日天数
                String holidayDays = "0";//节假日天数
                int workDays = 0;
                CkStandard standard = standardDao.getStandardByYearCalendar(yearMonth);//获得年月份的考勤标准
                if (standard != null){
                    restDays = standard.getRestDays();
                    if (standard.getHolidayDays() != null){
                        holidayDays = standard.getHolidayDays();
                    }
                    workDays = Integer.parseInt(standard.getWorkDays());
                }
                for (Map<String, String> infoMap:depInfos){
                    infoMap.put("restDays", restDays);
                    infoMap.put("holidayDays", holidayDays);
                    infoMap.put("shouldOutDays", (Integer.parseInt(infoMap.get("depNum"))*workDays)+"");
                    infoMap.put("total", (Double.parseDouble(infoMap.get("disease"))
                            +Double.parseDouble(infoMap.get("injury"))+Double.parseDouble(infoMap.get("thing"))
                            +Double.parseDouble(infoMap.get("free"))+Double.parseDouble(infoMap.get("marry"))
                            +Double.parseDouble(infoMap.get("lost"))+Double.parseDouble(infoMap.get("explore"))
                            +Double.parseDouble(infoMap.get("give")))+"");
                    double attendanceRate = 0.0;
                    try { 
                        attendanceRate = Double.parseDouble(infoMap.get("actualOutDays"))/Double.parseDouble(infoMap.get("shouldOutDays"));
                    } catch (Exception e) {
                        attendanceRate = 0.0;
                    }
                    infoMap.put("attendanceRate", ((int)(attendanceRate*100))+"%");
                    double workRate = outWork/Double.parseDouble(infoMap.get("shouldOutDays"));
                    infoMap.put("workRate", ((int)(workRate*100))+"%");
                }
            }
        }
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("data", depInfos);
        returnMap.put("total", pager.getTotalCount());
        return returnMap;
    }

    @Override
    public HSSFWorkbook exportRateInfo(String yearMonth, String depIds) {
        // TODO Auto-generated method stub
        return null;
    }
}
