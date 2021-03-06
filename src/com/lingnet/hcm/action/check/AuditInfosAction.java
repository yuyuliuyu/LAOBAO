package com.lingnet.hcm.action.check;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.dao.check.ContentDao;
import com.lingnet.hcm.dao.check.TimerDao;
import com.lingnet.hcm.entity.check.ChangeApply;
import com.lingnet.hcm.entity.check.CkCheckInfo;
import com.lingnet.hcm.entity.check.CkContent;
import com.lingnet.hcm.entity.check.CkInfoCommit;
import com.lingnet.hcm.entity.check.CkTimer;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.service.check.AuditInfosService;
import com.lingnet.hcm.service.check.ChangeApplyService;
import com.lingnet.hcm.service.check.CheckInfosService;
import com.lingnet.hcm.service.check.MonthStatisticsService;
import com.lingnet.hcm.service.check.TimerService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
/**
 * 
 * @ClassName: ClassGroup 
 * @Description: 考勤数据审核控制层
 * @author wangqiang
 * @date 2017年3月7日 下午4:11:49 
 *
 */
public class AuditInfosAction extends BaseAction{

	private static final long serialVersionUID = 5623909238890670719L;
	
	private CkCheckInfo ckCheckInfo;
	private String name;//姓名
	private String workDate;//工作日期（如：2010-03-15）
	private Map<String, String> map;//签到时间map
	private String companyId;//单位ID
	private String depId;//部门ID
	private String yearMonth;//年月份
	private String opinion;//审核意见
	private String auditStatus;//审核状态
	private CkInfoCommit commitInfo;
	private String monthDays;//月份天数
	
	@Resource(name = "timerService")
	private TimerService timerService;
	
	@Resource(name = "auditInfosService")
	private AuditInfosService auditInfosService;
	
	@Resource(name = "personnelService")
	private PersonnelService personnelService;
	
	@Resource(name = "checkInfosService")
	private CheckInfosService checkInfosService;
	
	@Resource(name = "monthStatisticsService")
	private MonthStatisticsService monthStatisticsService;

    @Resource(name = "timerDao")
    private TimerDao timerDao;
    
    @Resource(name = "contentDao")
    private ContentDao contentDao;
    
    @Resource(name="changeapplyservice")
    private ChangeApplyService changeservice;
    
    private ChangeApply changeapp;
	
	/**
	 * 跳转到班制表信息维护页
	 */
	public String list(){
        return "list";
    }
	
	/**
	 * 根据条件获得审核状态
	 * @Title: getAuditStatusByCond 
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月21日 V 1.0
	 */
	public String getAuditStatusByCond(){
		Map<String, String> infoMap = new HashMap<String, String>();
		if (depId != null && !"".equals(depId)){
			infoMap = auditInfosService.getAuditStatusByCond(depId, yearMonth);			
		}
		//查询月份天数
		String year = "20"+yearMonth.substring(0, 2);
		String month = yearMonth.substring(2, 4);
		int days = getDaysByYearMonth(Integer.parseInt(year), Integer.parseInt(month));
		infoMap.put("days", days+"");
		String json = JsonUtil.Encode(infoMap);
        return ajax(Status.success, json);
	}
	
	/**
     * 获取指定月份的天数 
     * @Title: getDaysByYearMonth 
     * @param year
     * @param month
     * @return 
     * @author wangqiang
     * @since 2017年4月14日 V 1.0
     */
    public static int getDaysByYearMonth(int year, int month) {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    } 
	
	/**
	 * 获取考勤上报信息集合
	 * @throws ParseException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getData() {
		List<Map<String, String>> ckInfoList=null; 
            ckInfoList = auditInfosService.getDataByCond(searchData); 
		if(ckInfoList==null){
	        HashMap result = new HashMap();
	        result.put("data", "");
	        result.put("total", pager.getTotalCount());
	        String json = JsonUtil.Encode(result);
	        return ajax(Status.success, json);
		}
		List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
		if (ckInfoList.size() > 0){
			List<String> empIdLists = new ArrayList<String>();
			for (Map<String, String> ckInfo:ckInfoList){
				String empIds = ckInfo.get("personIds");
				if (empIds != null){
					List<String> subLists = new ArrayList<String>();
					String[] empIdArrs = empIds.split(",");
					Collections.addAll(subLists, empIdArrs);
					empIdLists.addAll(subLists);
				}
			}
			datas = checkInfosService.getDataByEmpIds(pager, empIdLists);
			if(datas!=null&&datas.size()>0){

	            //获得所有考勤内容时间集合 
//	          String userId = toolUtil.getUserId();
//	          String checkUnitId = personnelDao.getCheckUnitIdById(userId);
	            String checkUnitId = LingUtil.userinfo().getCid();
	            List<CkContent> contentList = contentDao.getContentListByDepId(checkUnitId, new ArrayList<String>());
	            Map<String, CkContent> contentMap = new HashMap<String, CkContent>();
	            if (contentList.size() > 0){
	                for (CkContent content:contentList){
	                    contentMap.put(content.getContent(), content);
	                }
	            }
	            //获取人员员工号集合考勤号
	            List<String> jobNumbers = new ArrayList<String>();
	            String monthCalendar = datas.get(0).get("monthCalendar")+"";
	            for(Map<String, String> map:datas){
	                jobNumbers.add(map.get("checkno")+"");
	            }
	            //获取人员考勤时间集合
	            List<CkTimer> timers = timerDao.getCheckTimesByJobNumbers(jobNumbers, monthCalendar);
	            Map<String, List<CkTimer>> timerMap = new HashMap<String, List<CkTimer>>();
	            String flag = "";
	            List<CkTimer> timerList = null;
	            if (timers.size() > 0){
	                timerList = new ArrayList<CkTimer>();
	                for (int i = 0; i <timers.size();i++){
	                    CkTimer timer = timers.get(i);
	                    if (i == 0){
	                        timerList.add(timer);
	                        flag = timer.getJobNumber();
	                    } else {
	                        if (flag.equals(timer.getJobNumber())){
	                            timerList.add(timer);
	                        } else {
	                            timerMap.put(flag, timerList);
	                            flag = timer.getJobNumber();
	                            timerList = new ArrayList<CkTimer>();
	                            timerList.add(timer);
	                        }
	                    }
	                }
	                if (!timerMap.containsKey(flag)){
	                    timerMap.put(flag, timerList);
	                }
	            }
	            //循环判断
	            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            SimpleDateFormat sdfday = new SimpleDateFormat("dd");
	            int flagDay = 0;
	            for (Map<String, String> map:datas){
	                String checkno=(String) map.get("checkno");
	                List<CkTimer> ckTimers = timerMap.get(checkno);//员工的考勤时间集合
//	              if(ckTimers==null){
//	                  
//	              }
	                boolean cz=true;
	                for(int i=1; i<=31; i++){
	                    changeapp=new ChangeApply();
	                    if(checkno==null||"null".equals(checkno)||"".equals(checkno.trim())){
	                        map.put("day"+i, map.get("day"+i)+"red");
	                        continue;
	                    }
	                    flagDay = i;
	                    String sql="select * from ck_change_apply where month='"+monthCalendar+"' and day='"+i+"' and state=1 and chackno="+checkno;
	                    List<ChangeApply> changeapplist= changeservice.findBySql(sql);
	                    if(changeapplist!=null&&changeapplist.size()>0){
	                        changeapp=new ChangeApply();
	                        changeapp.setId("2");
	                    }
	                    Date newdate;
                        try {
                            newdate = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                    +monthCalendar.substring(2, 4)+"-0"+i+" 00:00:01");
                        } catch (ParseException e2) { 
                            e2.printStackTrace();
                            newdate=new Date();
                        }
                          Date enddate;
                        try {
                            enddate = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                      +monthCalendar.substring(2, 4)+"-0"+i+" 24:59:59");
                        } catch (ParseException e2) { 
                            e2.printStackTrace();
                            enddate=new Date();
                        }
//	                  changeservice.get(ChangeApply.class,
//	                            Restrictions.eq("month", monthCalendar),
//	                            Restrictions.eq("day", i+""),
//	                            Restrictions.eq("chackno", flag),
//	                            Restrictions.eq("state", 1));
	                    if (contentMap.get(map.get("day"+i)) != null){
	                        CkContent content = contentMap.get(map.get("day"+i));
	                        String startTimeb1Str = sdf.format(content.getStartTimeb1());
	                        String startTimef1Str = sdf.format(content.getStartTimef1());
	                        Date startTimeb1 = null;
	                        Date startTimef1 = null;
 
	                        if("休".equals(content.getContent())){
	                            if(ckTimers != null && ckTimers.size() > 0){ 
	                                for (int j = 0; j < ckTimers.size(); j++) {
	                                    CkTimer ct =ckTimers.get(j); 
	                                    if(ct.getCheckTime().getTime()>newdate.getTime() && ct.getCheckTime().getTime()<enddate.getTime()){
	                                        if(changeapp!=null&&changeapp.getId()!=null){
	                                            map.put("day"+i, map.get("day"+i)+"blue");
	                                        }else{
	                                            map.put("day"+i, map.get("day"+i)+"red");
	                                        }
	                                    }
	                                }
	                            }
	                            continue;
	                        }
	                        if (!"00:00:00".equals(startTimeb1Str)){
	                            try {
                                    startTimeb1 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                            +monthCalendar.substring(2, 4)+"-0"+i+" "+startTimeb1Str);
                                } catch (ParseException e1) { 
                                    e1.printStackTrace();
                                }
	                            try {
                                    startTimef1 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                            +monthCalendar.substring(2, 4)+"-0"+i+" "+startTimef1Str);
                                } catch (ParseException e) { 
                                    e.printStackTrace();
                                }
	                            if (ckTimers != null && ckTimers.size() > 0){
	                                Iterator<CkTimer> timeIter = ckTimers.iterator();
	                                while (timeIter.hasNext()){
	                                    CkTimer ct = timeIter.next();
	                                    if (Integer.parseInt(sdfday.format(ct.getCheckTime())) > flagDay){
	                                        if(changeapp!=null&&changeapp.getId()!=null){
	                                            map.put("day"+i, map.get("day"+i)+"blue");
	                                        }else{
	                                            map.put("day"+i, map.get("day"+i)+"red");
	                                        }
	                                        cz=false;
	                                        break;
	                                    } else if(Integer.parseInt(sdfday.format(ct.getCheckTime())) == flagDay) {
	                                        if (ct.getCheckTime().getTime() > startTimeb1.getTime() 
	                                                && ct.getCheckTime().getTime() < startTimef1.getTime()){ 
	                                            map.put("day"+i, map.get("day"+i).toString().replace("red", ""));
	                                            cz=false;
	                                            timeIter.remove();
	                                            break;
	                                        } else {
	                                            if(changeapp!=null&&changeapp.getId()!=null){
	                                                map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                                map.put("day"+i, map.get("day"+i)+"blue");
	                                            }else{
	                                                map.put("day"+i, map.get("day"+i)+"red");
	                                            }
	                                            cz=false;
	                                            timeIter.remove();
	                                        }
	                                    } else {
	                                        continue;
	                                    }
	                                }
	                                if(cz){
	                                    if(changeapp!=null&&changeapp.getId()!=null){
	                                        map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                        map.put("day"+i, map.get("day"+i)+"blue");
	                                    }else{
	                                        map.put("day"+i, map.get("day"+i)+"red");
	                                    }
	                                }
	                            } else {
	                                if(changeapp!=null&&changeapp.getId()!=null){
	                                    map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                    map.put("day"+i, map.get("day"+i)+"blue");
	                                }else{
	                                    map.put("day"+i, map.get("day"+i)+"red");
	                                }
	                            }
	                        }
	                        String endTimeb1Str = sdf.format(content.getEndTimeb1());
	                        String endTimef1Str = sdf.format(content.getEndTimef1());
	                        Date endTimeb1 = null;
	                        Date endTimef1 = null;
	                        if (!"00:00:00".equals(endTimeb1Str)){
	                            //判断隔天
	                            try {
                                    if (sdf.parse(endTimeb1Str).getTime() < sdf.parse(startTimeb1Str).getTime()){
                                        flagDay = (i+1);
                                        endTimeb1 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+(i+1)+" "+endTimeb1Str);
                                        endTimef1 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+(i+1)+" "+endTimef1Str);
                                    } else {
                                        endTimeb1 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+i+" "+endTimeb1Str);
                                        endTimef1 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+i+" "+endTimef1Str);
                                    }
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
	                            if (ckTimers != null && ckTimers.size() > 0){
	                                Iterator<CkTimer> timeIter = ckTimers.iterator();
	                                cz=true;
	                                while (timeIter.hasNext()){
	                                    CkTimer ct = timeIter.next();
	                                    if (Integer.parseInt(sdfday.format(ct.getCheckTime())) > flagDay){
	                                        if(changeapp!=null&&changeapp.getId()!=null){
	                                            map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                            map.put("day"+i, map.get("day"+i)+"blue");
	                                        }else{
	                                            map.put("day"+i, map.get("day"+i)+"red");
	                                        }
	                                        cz=false;
	                                        break;
	                                    } else if(Integer.parseInt(sdfday.format(ct.getCheckTime())) == flagDay) {
	                                        if (ct.getCheckTime().getTime() > endTimeb1.getTime() 
	                                                && ct.getCheckTime().getTime() < endTimef1.getTime()){
	                                            map.put("day"+i, map.get("day"+i).toString().replace("red", ""));
	                                            timeIter.remove();cz=false;
	                                            break;
	                                        } else {
	                                            if(changeapp!=null&&changeapp.getId()!=null){
	                                                map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                                map.put("day"+i, map.get("day"+i)+"blue");
	                                            }else{
	                                                map.put("day"+i, map.get("day"+i)+"red");
	                                            }
	                                            timeIter.remove();cz=false;
	                                        }
	                                    } else {
	                                        continue;
	                                    }
	                                } 
	                                if(cz){
	                                    if(changeapp!=null&&changeapp.getId()!=null){
	                                        map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                        map.put("day"+i, map.get("day"+i)+"blue");
	                                    }else{
	                                        map.put("day"+i, map.get("day"+i)+"red");
	                                    }
	                                }
	                            } else {
	                                if(changeapp!=null&&changeapp.getId()!=null){
	                                    map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                    map.put("day"+i, map.get("day"+i)+"blue");
	                                }else{
	                                    map.put("day"+i, map.get("day"+i)+"red");
	                                }
	                            }
	                        }
	                        String startTimeb2Str = sdf.format(content.getStartTimeb2());
	                        String startTimef2Str = sdf.format(content.getStartTimef2());
	                        Date startTimeb2 = null;
	                        Date startTimef2 = null;
	                        if (!"00:00:00".equals(startTimeb2Str)){
	                            //判断隔天
	                            try {
                                    if (sdf.parse(startTimeb2Str).getTime() < sdf.parse(endTimeb1Str).getTime()){
                                        flagDay = (i+1);
                                        startTimeb2 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+(i+1)+" "+startTimeb2Str);
                                        startTimef2 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+(i+1)+" "+startTimef2Str);
                                    } else {
                                        startTimeb2 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+i+" "+startTimeb2Str);
                                        startTimef2 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+i+" "+startTimef2Str);
                                    }
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
	                            if (ckTimers != null && ckTimers.size() > 0){
	                                Iterator<CkTimer> timeIter = ckTimers.iterator();
	                                cz=true;
	                                while (timeIter.hasNext()){
	                                    CkTimer ct = timeIter.next();
	                                    if (Integer.parseInt(sdfday.format(ct.getCheckTime())) > flagDay){
	                                        if(changeapp!=null&&changeapp.getId()!=null){
	                                            map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                            map.put("day"+i, map.get("day"+i)+"blue");
	                                        }else{
	                                            map.put("day"+i, map.get("day"+i)+"red");
	                                        }
	                                        cz=false;
	                                        break;
	                                    } else if(Integer.parseInt(sdfday.format(ct.getCheckTime())) == flagDay) {
	                                        if (ct.getCheckTime().getTime() > startTimeb2.getTime() 
	                                                && ct.getCheckTime().getTime() < startTimef2.getTime()){
	                                            map.put("day"+i, map.get("day"+i).toString().replace("red", ""));
	                                            timeIter.remove();cz=false;
	                                            break; 
	                                        } else {
	                                            if(changeapp!=null&&changeapp.getId()!=null){
	                                                map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                                map.put("day"+i, map.get("day"+i)+"blue");
	                                            }else{
	                                                map.put("day"+i, map.get("day"+i)+"red");
	                                            }
	                                            timeIter.remove();cz=false;
	                                        }
	                                    } else {
	                                        continue;
	                                    }
	                                }
	                                if(cz){
	                                    if(changeapp!=null&&changeapp.getId()!=null){
	                                        map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                        map.put("day"+i, map.get("day"+i)+"blue");
	                                    }else{
	                                        map.put("day"+i, map.get("day"+i)+"red");
	                                    }
	                                }
	                            } else {
	                                if(changeapp!=null&&changeapp.getId()!=null){
	                                    map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                    map.put("day"+i, map.get("day"+i)+"blue");
	                                }else{
	                                    map.put("day"+i, map.get("day"+i)+"red");
	                                }
	                            }
	                        }
	                        String endTimeb2Str = sdf.format(content.getEndTimeb2());
	                        String endTimef2Str = sdf.format(content.getEndTimef2());
	                        Date endTimeb2 = null;
	                        Date endTimef2 = null;
	                        if (!"00:00:00".equals(endTimeb2Str)){
	                            //判断隔天
	                            try {
                                    if (sdf.parse(endTimeb2Str).getTime() < sdf.parse(startTimeb2Str).getTime()){
                                        flagDay = (i+1);
                                        endTimeb2 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+(i+1)+" "+endTimeb2Str);
                                        endTimef2 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+(i+1)+" "+endTimef2Str);
                                    } else {
                                        endTimeb2 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+i+" "+endTimeb2Str);
                                        endTimef2 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+i+" "+endTimef2Str);
                                    }
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
	                            if (ckTimers != null && ckTimers.size() > 0){
	                                Iterator<CkTimer> timeIter = ckTimers.iterator();
	                                cz=true;
	                                while (timeIter.hasNext()){
	                                    CkTimer ct = timeIter.next();
	                                    if (Integer.parseInt(sdfday.format(ct.getCheckTime())) > flagDay){
	                                        if(changeapp!=null&&changeapp.getId()!=null){
	                                            map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                            map.put("day"+i, map.get("day"+i)+"blue");
	                                        }else{
	                                            map.put("day"+i, map.get("day"+i)+"red");
	                                        }
	                                        cz=false;
	                                        break;
	                                    } else if(Integer.parseInt(sdfday.format(ct.getCheckTime())) == flagDay) {
	                                        if (ct.getCheckTime().getTime() > endTimeb2.getTime() 
	                                                && ct.getCheckTime().getTime() < endTimef2.getTime()){
	                                            map.put("day"+i, map.get("day"+i).toString().replace("red", ""));
	                                            timeIter.remove();cz=false;
	                                            break;
	                                        } else {
	                                            if(changeapp!=null&&changeapp.getId()!=null){
	                                                map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                                map.put("day"+i, map.get("day"+i)+"blue");
	                                            }else{
	                                                map.put("day"+i, map.get("day"+i)+"red");
	                                            }
	                                            timeIter.remove();cz=false;
	                                        }
	                                    } else {
	                                        continue;
	                                    }
	                                }
	                                if(cz){
	                                    if(changeapp!=null&&changeapp.getId()!=null){
	                                        map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                        map.put("day"+i, map.get("day"+i)+"blue");
	                                    }else{
	                                        map.put("day"+i, map.get("day"+i)+"red");
	                                    }
	                                }
	                            } else {
	                                if(changeapp!=null&&changeapp.getId()!=null){
	                                    map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                    map.put("day"+i, map.get("day"+i)+"blue");
	                                }else{
	                                    map.put("day"+i, map.get("day"+i)+"red");
	                                }
	                            }
	                        }
	                        String startTimeb3Str = sdf.format(content.getStartTimeb3());
	                        String startTimef3Str = sdf.format(content.getStartTimef3());
	                        Date startTimeb3 = null;
	                        Date startTimef3 = null;
	                        if (!"00:00:00".equals(startTimeb3Str)){
	                            //判断隔天
	                            try {
                                    if (sdf.parse(startTimeb3Str).getTime() < sdf.parse(endTimeb2Str).getTime()){
                                        flagDay = (i+1);
                                        startTimeb3 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+(i+1)+" "+startTimeb3Str);
                                        startTimef3 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+(i+1)+" "+startTimef3Str);
                                    } else {
                                        startTimeb3 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+i+" "+startTimeb3Str);
                                        startTimef3 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+i+" "+startTimef3Str);
                                    }
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
	                            if (ckTimers != null && ckTimers.size() > 0){
	                                Iterator<CkTimer> timeIter = ckTimers.iterator();cz=true;
	                                while (timeIter.hasNext()){
	                                    CkTimer ct = timeIter.next();
	                                    if (Integer.parseInt(sdfday.format(ct.getCheckTime())) > flagDay){
	                                        if(changeapp!=null&&changeapp.getId()!=null){
	                                            map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                            map.put("day"+i, map.get("day"+i)+"blue");
	                                        }else{
	                                            map.put("day"+i, map.get("day"+i)+"red");
	                                        }
	                                        cz=false;
	                                        break;
	                                    } else if(Integer.parseInt(sdfday.format(ct.getCheckTime())) == flagDay) {
	                                        if (ct.getCheckTime().getTime() > startTimeb3.getTime() 
	                                                && ct.getCheckTime().getTime() < startTimef3.getTime()){
	                                            map.put("day"+i, map.get("day"+i).toString().replace("red", ""));
	                                            timeIter.remove();cz=false;
	                                            break;
	                                        } else {
	                                            if(changeapp!=null&&changeapp.getId()!=null){
	                                                map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                                map.put("day"+i, map.get("day"+i)+"blue");
	                                            }else{
	                                                map.put("day"+i, map.get("day"+i)+"red");
	                                            }
	                                            timeIter.remove();cz=false;
	                                        }
	                                    } else {
	                                        continue;
	                                    }
	                                }
	                                if(cz){
	                                    if(changeapp!=null&&changeapp.getId()!=null){
	                                        map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                        map.put("day"+i, map.get("day"+i)+"blue");
	                                    }else{
	                                        map.put("day"+i, map.get("day"+i)+"red");
	                                    }
	                                }
	                            } else {
	                                if(changeapp!=null&&changeapp.getId()!=null){
	                                    map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                    map.put("day"+i, map.get("day"+i)+"blue");
	                                }else{
	                                    map.put("day"+i, map.get("day"+i)+"red");
	                                }
	                            }
	                        }
	                        String endTimeb3Str = sdf.format(content.getEndTimeb3());
	                        String endTimef3Str = sdf.format(content.getEndTimef3());
	                        Date endTimeb3 = null;
	                        Date endTimef3 = null;
	                        if (!"00:00:00".equals(endTimeb3Str)){
	                            //判断隔天
	                            try {
                                    if (sdf.parse(endTimeb3Str).getTime() < sdf.parse(startTimeb3Str).getTime()){
                                        flagDay = (i+1);
                                        endTimeb3 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+(i+1)+" "+endTimeb3Str);
                                        endTimef3 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+(i+1)+" "+endTimef3Str);
                                    } else {
                                        endTimeb3 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+i+" "+endTimeb3Str);
                                        endTimef3 = sdf2.parse("20"+monthCalendar.substring(0, 2)+"-"
                                                +monthCalendar.substring(2, 4)+"-0"+i+" "+endTimef3Str);
                                    }
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
	                            if (ckTimers != null && ckTimers.size() > 0){
	                                Iterator<CkTimer> timeIter = ckTimers.iterator();
	                                while (timeIter.hasNext()){
	                                    CkTimer ct = timeIter.next();
	                                    if (Integer.parseInt(sdfday.format(ct.getCheckTime())) > flagDay){ 
	                                        if(changeapp!=null&&changeapp.getId()!=null){
	                                            map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                            map.put("day"+i, map.get("day"+i)+"blue");
	                                        }else{
	                                            map.put("day"+i, map.get("day"+i)+"red");
	                                        }
	                                        break;
	                                    } else if(Integer.parseInt(sdfday.format(ct.getCheckTime())) == flagDay) {
	                                        if (ct.getCheckTime().getTime() > endTimeb3.getTime() 
	                                                && ct.getCheckTime().getTime() < endTimef3.getTime()){
	                                            map.put("day"+i, map.get("day"+i).toString().replace("red", ""));
	                                            timeIter.remove();
	                                            break;
	                                        } else {
	                                            if(changeapp!=null&&changeapp.getId()!=null){
	                                                map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                                map.put("day"+i, map.get("day"+i)+"blue");
	                                            }else{
	                                                map.put("day"+i, map.get("day"+i)+"red");
	                                            }
	                                            timeIter.remove();
	                                        }
	                                    } else {
	                                        continue;
	                                    }
	                                }
	                            } else {
	                                if(changeapp!=null&&changeapp.getId()!=null){
	                                    map.put("day"+i, map.get("day"+i).toString().replace("blue", ""));
	                                    map.put("day"+i, map.get("day"+i)+"blue");
	                                }else{
	                                    map.put("day"+i, map.get("day"+i)+"red");
	                                }
	                            }
	                        }
	                    }
	                }
	                
	            }
	        
			}
		}
        HashMap result = new HashMap();
        result.put("data", datas);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
	
	/**
	 * 进入查看考勤信息页面
	 */
	public String view(){
		ckCheckInfo = checkInfosService.get(id);
        yearMonth=ckCheckInfo.getMonthCalendar();
		System.out.println(monthDays);
    	if (ckCheckInfo != null){
    		BasicInformation basicInfo = personnelService.get(BasicInformation.class, Restrictions.eq("jobNumber", ckCheckInfo.getJobNumber()));
    		if (basicInfo != null){
    			name = basicInfo.getName();
    			if (basicInfo.getSetWorkDate() != null){
	    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    			workDate = sdf.format(basicInfo.getSetWorkDate());
    			}
    		}
    		//封装员工的签到、签退时间
    		map = timerService.getListByCond(ckCheckInfo.getJobNumber(), ckCheckInfo.getMonthCalendar());
    	}
		return "view";
	}
    
    /**
     * 跳转到审核页面
     * @Title: cycle 
     * @return 
     * @author wangqiang
     * @since 2017年3月9日 V 1.0
     */
    public String showApprove() {
    	commitInfo = this.getBeanByCriteria(CkInfoCommit.class, Restrictions.eq("monthCalendar", yearMonth), Restrictions.eq("depId", id)); 
    	if(commitInfo==null||commitInfo.getAuditStatus()==1){
    	    id="本月已审核通过不能再进行审核";
            return "error";
    	}
    	return "approve";
    }
    
    /**
     * 考勤上报审核
     * @Title: saveApprove 
     * @return 
     * @author wangqiang
     * @since 2017年4月21日 V 1.0
     */
    public String saveApprove(){
    	CkInfoCommit ckInfo = auditInfosService.get(id);
    	if (ckInfo != null){
    		if (ckInfo.getAuditStatus() == 1 || ckInfo.getAuditStatus() == 2){//已审核
    			return ajax(Status.error, "该部门已审核！");
    		}
    	    try {
                return ajax(Status.success, monthStatisticsService.saveMonthStatistics(ckInfo, companyId, Integer.parseInt(auditStatus), opinion));
            } catch (Exception e) {
                return ajax(Status.error, e.getMessage());
            }
    	} else
    	    return ajax(Status.success, "审核数据不存在");
    }

	public CkCheckInfo getCkCheckInfo() {
		return ckCheckInfo;
	}

	public void setCkCheckInfo(CkCheckInfo ckCheckInfo) {
		this.ckCheckInfo = ckCheckInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public CkInfoCommit getCommitInfo() {
		return commitInfo;
	}

	public void setCommitInfo(CkInfoCommit commitInfo) {
		this.commitInfo = commitInfo;
	}

	public String getMonthDays() {
		return monthDays;
	}

	public void setMonthDays(String monthDays) {
		this.monthDays = monthDays;
	}
    
}
