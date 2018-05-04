package com.lingnet.hcm.action.check;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.check.CkEmpInstitute;
import com.lingnet.hcm.entity.check.CkInstitution;
import com.lingnet.hcm.entity.check.CkYearDay;
import com.lingnet.hcm.service.check.InstituteService;
import com.lingnet.hcm.service.check.InstitutionService;
import com.lingnet.hcm.service.check.YearDayService;
import com.lingnet.util.JsonUtil;
/**
 * @ClassName: ClassGroup 
 * @Description: 考勤班次控制层
 * @author wangqiang
 * @date 2017年3月7日 下午4:11:49
 */
public class InstituteAction extends BaseAction{

	private static final long serialVersionUID = 5623909238890670719L;
	
	private CkEmpInstitute ckEmpInstitute;
	private String daysFlag;//天数标记
	private CkYearDay ckYearDay;//全年白班班制
	private String instituteId;//白班班制ID
	private String existFlag;//白班班制是否存在标记
	private String yearVal;
	private String monthVal;
	private String typeFlag;//tab标记（1 循环排班；2 白班排班）
	
	@Resource(name = "instituteService")
	private InstituteService instituteService;
	
	@Resource(name = "yearDayService")
	private YearDayService yearDayService;
	
	@Resource(name = "institutionService")
	private InstitutionService institutionService;
	
	/**
	 * 跳转到循环班次维护页
	 */
	public String list(){
        return "list";
    }
	
	/**
	 * 获取考勤班次信息集合
	 */
	public String getData(){
		Map<String, Object> result = instituteService.getDataByCond(pager, typeFlag);
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
	
	/**
	 * 进入添加循环班次信息页面
	 */
	public String add(){
		return "add";
	}
	
	/**
     * 进入编辑循环班次页面
     */
    public String edit() {
    	ckEmpInstitute = instituteService.get(id);
    	if (ckEmpInstitute.getCycleType() == 2){
    		daysFlag = "2";
    	} else if (ckEmpInstitute.getCycleType() == 3){
    		daysFlag = "3";
    	} else {
    		daysFlag = "1";
    	}
    	return "add";
    }
    
    /**
     * 保存或修改循环班次信息
     */
    public String saveOrUpdate(){
    	Map<String,String> dataMap = JsonUtil.parseProperties(formdata);
		if ("".equals(dataMap.get("id"))){//保存
			CkEmpInstitute cei = instituteService.getValidateInstituteId(dataMap.get("instituteId"));
			if (cei != null){
				return ajax(Status.error, "班制名称已存在！"); 
			}
		}
		try {
			instituteService.saveOrUpdateInfo(formdata);
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.success, "操作失败"); 
		}
		return ajax(Status.success, "success");
	}
    
    /**
     * 删除循环班次信息
     * @Title: remove 
     * @return 
     * @author wangqiang
     * @since 2017年3月31日 V 1.0
     */
    public String remove() {
    	if (ids != null && !"".equals(ids)){
    		String[] idArrs = ids.split(",");
    		instituteService.delete(CkEmpInstitute.class, idArrs);
    	}
        return ajax(Status.success, "success");
    }
    
    /**
     * 导出循环班次信息集合
     * @Title: exportInstitutionInfo  
     * @author wangqiang
     * @since 2017年3月31日 V 1.0
     */
    public void exportInstituteInfo(){
    	HttpServletResponse resp = getResponse(); 
    	try {
            HSSFWorkbook hwb = instituteService.exportInfos();
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-Disposition", "attachment;filename=\""
            		+ new String("考勤班次信息".getBytes("gb2312"), "ISO8859-1")+ ".xls" + "\"");
            OutputStream out = resp.getOutputStream();
            hwb.write(out);
            out.flush();
            out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
    }
    
    /**
	 * 跳转到白班班次维护页
	 */
	public String dayList(){
        return "dayList";
    }
    
    /**
     * 生成全年白班
     * @Title: generateYear 
     * @return 
     * @author wangqiang
     * @since 2017年4月14日 V 1.0
     */
    public String generateYear(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date firstDate = null;
    	String thisYear = "";
    	String thisMonth = "";
		try {
			thisYear = sdf.format(new Date()).substring(0, 4);
			thisMonth = sdf.format(new Date()).substring(5, 7);
			firstDate = sdf.parse(thisYear+"-01-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//若是12月份，可生成下一年的排班
		if ("12".equals(thisMonth)){
			thisYear = Integer.parseInt(thisYear)+1+"";
			try {
				firstDate = sdf.parse(thisYear+"-01-01");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//判断当年的白班班制是否已生成
		List<CkYearDay> oldList = yearDayService.getList(CkYearDay.class, Restrictions.eq("yearCalendar", thisYear));
    	if (oldList.size() > 0){
    		return ajax(Status.error, "白班班制已生成，请勿重复操作");
    	}
		String firstWeek = getWeekOfDate(firstDate);//获取当年1月1日的星期
    	int flag = 0;//当前星期标记
    	List<CkYearDay> list = new ArrayList<CkYearDay>();
    	for (int i = 1; i <=12; i++){
    		CkYearDay yearDay = new CkYearDay();
    		yearDay.setYearCalendar(thisYear);
    		yearDay.setMonthCalendar(i+"");
    		yearDay.setIsDelete(0);
    		//获取指定月份的天数
    		int days = getDaysByYearMonth(Integer.parseInt(thisYear), Integer.parseInt(i+""));
    		yearDay.setTotalDays(days);
    		if (flag == 0){
    			flag = Integer.parseInt(firstWeek);
    		}
    		//遍历
    		for (int j = 1; j <=days; j++){
    			// 获取实体类的所有属性，返回Field数组  
    		    Field[] field = CkYearDay.class.getDeclaredFields();  
    		    // 遍历所有属性  
    		    for (int m = 0; m < field.length; m++) {  
		            // 获取属性的名字  
		            String name = field[m].getName(); 
		            if (name.equals("day"+i)){
		            	try {
		            		if (flag == 1 || flag == 2 || flag == 3 || flag == 4 || flag == 5){
		            			yearDay.getClass().getMethod("setDay"+j, String.class).invoke(yearDay, "白");
		            			flag ++;
		            		} else if (flag == 6){
		            			yearDay.getClass().getMethod("setDay"+j, String.class).invoke(yearDay, "休");
		            			flag ++;
		            		} else if (flag == 7){
		            			yearDay.getClass().getMethod("setDay"+j, String.class).invoke(yearDay, "休");
		            			flag = 1;
		            		}
		            	} catch (Exception e){
		            		e.printStackTrace();
		            	}
		            	break;
		            }
    		    }
    		}
    		list.add(yearDay);
    	}
    	//批量保存
		yearDayService.saveBatch(list);
    	return ajax(Status.success, "success");
    }
    
    /**
     * 根据日期获取星期
     * @Title: getWeekOfDate 
     * @param date
     * @return 
     * @author wangqiang
     * @since 2017年4月14日 V 1.0
     */
    public static String getWeekOfDate(Date date) {
    	String[] weekDaysCode = { "7", "1", "2", "3", "4", "5", "6" };
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
    	return weekDaysCode[intWeek];
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
     * 跳转到编辑白班排班页面
     * @Title: editDay 
     * @return 
     * @author wangqiang
     * @since 2017年4月14日 V 1.0
     */
    public String editDay(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String thisYear = "";//当前年份
    	String thisMonth = "";//当前月份
    	//根据白班班制名称查询班制ID
		CkInstitution institution = institutionService.get(CkInstitution.class, Restrictions.eq("instituteName", "白班"));
		if (institution != null){
			instituteId = institution.getId();
		}else{
		    id="未查询到名称为白班的班制信息，请检查是否添加了班制信息和对应的考勤统计信息";
		    return "audit_infos_error";
		}
		//查询班制表中是否已存在白班班制
		if (instituteId != null){//若白班班制ID不为空
			CkEmpInstitute empInstitute = instituteService.get(CkEmpInstitute.class, Restrictions.eq("instituteId", instituteId));
			if (empInstitute != null){
				String startDate = sdf.format(empInstitute.getStartDate());
				existFlag = startDate.substring(5, 7);
				if ("12".equals(existFlag)){
					thisYear = Integer.parseInt(startDate.substring(0, 4))+1+"";
					thisMonth = "1";
				} else {
					thisYear = startDate.substring(0, 4);
					thisMonth = Integer.parseInt(startDate.substring(5, 7))+1+"";
				}
			} else {
				existFlag = "";
				thisYear = sdf.format(new Date()).substring(0, 4);
				thisMonth = sdf.format(new Date()).substring(5, 7);
			}
		}
		if ("0".equals(thisMonth.substring(0,1))){
			thisMonth = thisMonth.substring(1,2);
		}
		ckYearDay = yearDayService.getDayInfoByCond(thisYear, thisMonth);
		return "editDay";
	}
    
    /**
     * 编辑白班排班
     * @Title: editDayClass 
     * @return 
     * @author wangqiang
     * @since 2017年12月15日 V 1.0
     */
    public String editDayClass(){
    	CkEmpInstitute institute = instituteService.get(id);
    	instituteId = institute.getInstituteId();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    	String date = sdf.format(institute.getStartDate());
    	String thisYear = date.substring(0, 4);//年份
    	String thisMonth = date.substring(5, 7);//月份
    	existFlag = thisMonth+"update";
    	if ("0".equals(thisMonth.substring(0,1))){
			thisMonth = thisMonth.substring(1,2);
		} 
    	ckYearDay = yearDayService.getDayInfoByCond(thisYear, thisMonth);
    	return "editDay";
    }
    
    /**
     * 获取月份下拉
     * @Title: getMonths 
     * @return 
     * @author wangqiang
     * @since 2017年4月15日 V 1.0
     */
    public String getMonths(){
    	List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    	if (existFlag != null && !"".equals(existFlag)){
    		Map<String, String> map = new HashMap<String, String>();
    		if (existFlag.length() == 2 && "0".equals(existFlag.substring(0,1))){
    			existFlag = existFlag.substring(1,2);
    		} else if (existFlag.length() > 2){
    			existFlag = existFlag.substring(1,existFlag.length());
    		}
    		if ("12".equals(existFlag)){
    			map.put("id", "1");
        		map.put("text", "1");
    		} else if (existFlag.indexOf("update") >= 0){
    			map.put("id", existFlag.substring(0, existFlag.indexOf("update")));
        		map.put("text", existFlag.substring(0, existFlag.indexOf("update")));
    		} else {
    			map.put("id", Integer.parseInt(existFlag)+1+"");
        		map.put("text", Integer.parseInt(existFlag)+1+"");
    		}
    		list.add(map);
    	} else {//白班班制不存在
    		Map<String, String> map = new HashMap<String, String>();
    		SimpleDateFormat sdf = new SimpleDateFormat("MM");
    		String month = sdf.format(new Date());
    		if ("0".equals(month.substring(0,1))){
    			month = month.substring(1, 2);
    		}
    		map.put("id", month);
    		map.put("text", month);
    		list.add(map);
    		Map<String, String> map2 = new HashMap<String, String>();
    		int nextMonth = Integer.parseInt(month);
    		if (nextMonth == 12){
    			map2.put("id", "1");
        		map2.put("text", "1");
    		} else {
    			map2.put("id", nextMonth+1+"");
        		map2.put("text", nextMonth+1+"");
    		}
    		list.add(map2);
    	}
        String json = JsonUtil.Encode(list);
        return ajax(Status.success, json);
    }
    
    /**
     * 获得白班班制信息
     * @Title: getChangeDays 
     * @return 
     * @author wangqiang
     * @since 2017年4月15日 V 1.0
     */
    public String getChangeDays(){
    	CkYearDay info = yearDayService.getDayInfoByCond(yearVal, monthVal);
        String json = JsonUtil.Encode(info);
        return ajax(Status.success, json);
    }
    
    /**
     * 保存白班预排班
     * @Title: saveOrUpdateDays 
     * @return 
     * @author wangqiang
     * @since 2017年4月14日 V 1.0
     */
    public String saveOrUpdateDays(){
    	Map<String,String> dataMap = JsonUtil.parseProperties(formdata);
    	//修改全年白班排班
    	yearDayService.updateInfo(dataMap);
        CkEmpInstitute empInstitute =new CkEmpInstitute();
    	String instituteId = dataMap.get("instituteId");
    	String  monthCalendar=dataMap.get("monthCalendar");
    	if(monthCalendar==null||"".equals(monthCalendar.trim())){ 
            return ajax(Status.success, "操作失败"); 
    	}else{
            empInstitute = instituteService.get(CkEmpInstitute.class, Restrictions.eq("instituteId", instituteId), Restrictions.eq("isDelete", 0));
    	} 
    	//保存/修改白班排班
		try {
			if (empInstitute == null){//保存
				instituteService.saveInstituteInfo(dataMap);
			} else {//修改 
		        boolean isypb=false; 
		        Date date=empInstitute.getStartDate();
		        int month=date.getMonth()+1;
		        int monthstr=Integer.parseInt(monthCalendar.trim());
		        if(month==monthstr){
	                instituteService.updateInstituteInfo(dataMap, empInstitute);
		        }else{
	                instituteService.saveInstituteInfo(dataMap);
		        }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.success, "操作失败"); 
		}
		return ajax(Status.success, "success");
	}
    
	public CkEmpInstitute getCkEmpInstitute() {
		return ckEmpInstitute;
	}

	public void setCkEmpInstitute(CkEmpInstitute ckEmpInstitute) {
		this.ckEmpInstitute = ckEmpInstitute;
	}

	public String getDaysFlag() {
		return daysFlag;
	}

	public void setDaysFlag(String daysFlag) {
		this.daysFlag = daysFlag;
	}

	public CkYearDay getCkYearDay() {
		return ckYearDay;
	}

	public void setCkYearDay(CkYearDay ckYearDay) {
		this.ckYearDay = ckYearDay;
	}

	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}

	public String getExistFlag() {
		return existFlag;
	}

	public void setExistFlag(String existFlag) {
		this.existFlag = existFlag;
	}

	public String getYearVal() {
		return yearVal;
	}

	public void setYearVal(String yearVal) {
		this.yearVal = yearVal;
	}

	public String getMonthVal() {
		return monthVal;
	}

	public void setMonthVal(String monthVal) {
		this.monthVal = monthVal;
	}

	public String getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}
    
}
