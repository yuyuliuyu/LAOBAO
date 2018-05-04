package com.lingnet.hcm.service.impl.check;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.check.AnnualLeaveRecordDao;
import com.lingnet.hcm.dao.personnel.PersonnelDao;
import com.lingnet.hcm.entity.check.CkAnnualLeave;
import com.lingnet.hcm.service.check.AnnualLeaveRecordService;
import com.lingnet.qxgl.service.BackendDepService;
/**
 * 
 * @ClassName: AnnualLeaveRecordServiceImpl 
 * @Description: 员工年假休假记录ServiceImpl 
 * @author wangqiang
 * @date 2017年4月24日 下午5:27:01 
 *
 */
@Service("annualLeaveRecordService")
public class AnnualLeaveRecordServiceImpl extends BaseServiceImpl<CkAnnualLeave, String> implements AnnualLeaveRecordService{
	
	@Resource(name = "annualLeaveRecordDao")
	private AnnualLeaveRecordDao annualLeaveRecordDao;
	
	@Resource(name="personnelDao")
	private PersonnelDao personnelDao;
	
	@Resource(name="backendDepService")
    private BackendDepService backendDepService;

	@Override
	public HSSFWorkbook exportAnnualLeaveInfo(String year, String searchData) {
		String[] cellname={
				"序号", "职工号", "姓名", "班组", "全部", "应休", "一月", "二月", "三月", "四月", "五月", 
				"六月", "七月", "八月", "九月", "十月", "十一月", "十二月", "已休", "剩余"};
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("员工年假信息");
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
        //获得当前用户的所有权限部门集合
        String depIds = getAllDepIdStrs();
        //查询班制名称信息
        List<Map<String, String>> list = getAnnualRecordList(year, depIds, searchData);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	Map<String, String> info = list.get(i);
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i+1+"");
                if(info.get("jobNumber") != null && !"null".equals(info.get("jobNumber"))){ 
                	row.createCell(1).setCellValue(info.get("jobNumber")); 
                }
                if(info.get("name") != null && !"null".equals(info.get("name"))){ 
                	row.createCell(2).setCellValue(info.get("name"));
                }
                if(info.get("className") != null && !"null".equals(info.get("className"))){ 
                	row.createCell(3).setCellValue(info.get("className")); 
                }
                if(info.get("legalDay") != null && !"null".equals(info.get("legalDay"))){ 
                	row.createCell(4).setCellValue(info.get("legalDay")); 
                }
                if(info.get("shouldTake") != null && !"null".equals(info.get("shouldTake"))){ 
                	row.createCell(5).setCellValue(info.get("shouldTake")); 
                }
                if(info.get("month1") != null && !"null".equals(info.get("month1"))){ 
                	row.createCell(6).setCellValue(info.get("month1")); 
                }
                if(info.get("month2") != null && !"null".equals(info.get("month2"))){ 
                	row.createCell(7).setCellValue(info.get("month2")); 
                }
                if(info.get("month3") != null && !"null".equals(info.get("month3"))){ 
                	row.createCell(8).setCellValue(info.get("month3")); 
                }
                if(info.get("month4") != null && !"null".equals(info.get("month4"))){ 
                	row.createCell(9).setCellValue(info.get("month4")); 
                }
                if(info.get("month5") != null && !"null".equals(info.get("month5"))){ 
                	row.createCell(10).setCellValue(info.get("month5")); 
                }
                if(info.get("month6") != null && !"null".equals(info.get("month6"))){ 
                	row.createCell(11).setCellValue(info.get("month6")); 
                }
                if(info.get("month7") != null && !"null".equals(info.get("month7"))){ 
                	row.createCell(12).setCellValue(info.get("month7")); 
                }
                if(info.get("month8") != null && !"null".equals(info.get("month8"))){ 
                	row.createCell(13).setCellValue(info.get("month8")); 
                }
                if(info.get("month9") != null && !"null".equals(info.get("month9"))){ 
                	row.createCell(14).setCellValue(info.get("month9")); 
                }
                if(info.get("month10") != null && !"null".equals(info.get("month10"))){ 
                	row.createCell(15).setCellValue(info.get("month10")); 
                }
                if(info.get("month11") != null && !"null".equals(info.get("month11"))){ 
                	row.createCell(16).setCellValue(info.get("month11")); 
                }
                if(info.get("month12") != null && !"null".equals(info.get("month12"))){ 
                	row.createCell(17).setCellValue(info.get("month12")); 
                }
                if(info.get("haveTake") != null && !"null".equals(info.get("haveTake"))){ 
                	row.createCell(18).setCellValue(info.get("haveTake")); 
                }
                if(info.get("surplus") != null && !"null".equals(info.get("surplus"))){ 
                	row.createCell(19).setCellValue(info.get("surplus")); 
                }
            }
        }
        return hwb;
	}
	
	public List<Map<String, String>> getAnnualRecordList(String year, String depIds, String searchData){
		List<Map<String, String>> list = personnelDao.getAnnualDataList(searchData, depIds);
        if (year == null || "".equals(year)){
    		return list;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfAll = new SimpleDateFormat("yyyy-MM-dd");
        if (Integer.parseInt(year) <= Integer.parseInt(sdf.format(new Date()))){
        	String thisDate = "";
        	if (Integer.parseInt(year) < Integer.parseInt(sdf.format(new Date()))){//输入年度小于当前年份
        		thisDate = year + "-12-31";
            } else if (Integer.parseInt(year) == Integer.parseInt(sdf.format(new Date()))){
            	thisDate = sdfAll.format(new Date());
            }
    		if (list.size() > 0){
    			SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
    			List<String> jobNumbers = new ArrayList<String>();
    			for (Map<String, String> map :list){
    				jobNumbers.add(map.get("jobNumber"));
    				Double legalDay = 0.0;//全部休假天数
    				Double allDay = 0.0;//法定休假天数
    				String workDate = map.get("workDate");//开始工作日期
    				String portDate = map.get("portDate");//入港时间
    				int legalMonths = 0;//当前日期距离开始工作日期相差月份数
    				try {
    					legalMonths = countMonths(workDate, thisDate);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    				if (legalMonths <= 0){
    					allDay = 0.0;
    				} else if (legalMonths > 0 && legalMonths < 12){
    					allDay = 0.0;
    				} else if (legalMonths/12 >=1 && legalMonths/12 < 10){
    					allDay = 5.0;
    				} else if (legalMonths/12 >= 10 && legalMonths/12 < 20){
    					allDay = 10.0;
    				} else {
    					allDay = 15.0;
    				}
    				Double actualDay = 0.0;//应休休假天数
    				int actualMonths = 0;//搜索年份距离开始工作日期相差月份数
    				try {
    					actualMonths = countMonths(portDate, thisDate);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    				if (actualMonths < 0){
    					legalDay = 0.0;
    				} else {
    					if (Integer.parseInt(portDate.substring(0, 4)) == Integer.parseInt(thisDate.substring(0, 4))){
        					if (Integer.parseInt(portDate.substring(0, 4)) == Integer.parseInt(sdfMonth.format(new Date()))){
        						int thisMonth = Integer.parseInt(sdfMonth.format(new Date()))-Integer.parseInt(portDate.substring(5, 7))+1;
            					legalDay = thisMonth/12.0*allDay;
        					} else {
        						int thisMonth = 12-Integer.parseInt(portDate.substring(5, 7))+1;
            					legalDay = thisMonth/12.0*allDay;
        					}
        				} else {
        					legalDay = allDay;
        				}
    				}
    				map.put("legalDay", Math.ceil(legalDay)+"");//全部
    				if (actualMonths <= 0){
    					actualDay = 0.0;
    				} else {
    					if (Integer.parseInt(year) < Integer.parseInt(sdf.format(new Date()))){//输入年度小于当前年份
    						if (Integer.parseInt(thisDate.substring(0, 4)) == Integer.parseInt(portDate.substring(0, 4))){
    							int thisMonth = Integer.parseInt(thisDate.substring(5, 7))-Integer.parseInt(portDate.substring(5, 7))+1;
            					actualDay = thisMonth/12.0*allDay;
    						} else {
    							actualDay = allDay;
    						}
        	            } else if (Integer.parseInt(year) == Integer.parseInt(sdf.format(new Date()))){
        	            	if (Integer.parseInt(thisDate.substring(0, 4)) == Integer.parseInt(portDate.substring(0, 4))){
        	            		int thisMonth = Integer.parseInt(sdfMonth.format(new Date()))-Integer.parseInt(portDate.substring(5, 7))+1;
            					actualDay = thisMonth/12.0*allDay;
        	            	} else {
        	            		actualDay = allDay;
        	            	}
        	            }
    				}
    				map.put("shouldTake", Math.ceil(actualDay)+"");//应休天数
    			}
    			//查询员工年假休假记录
    			List<CkAnnualLeave> annualList = annualLeaveRecordDao.getRecordByJobNumbers(jobNumbers, year);
    			Map<String, List<CkAnnualLeave>> annualMap = new HashMap<String, List<CkAnnualLeave>>();
    			String flag = "";
    			if (annualList != null && annualList.size() > 0){
    				List<CkAnnualLeave> leaveList = null;
    				for (CkAnnualLeave leave :annualList){
    					if ("".equals(flag)){
    						flag = leave.getJobNumber();
    						leaveList = new ArrayList<CkAnnualLeave>();
    						leaveList.add(leave);
    					} else {
    						if (flag.equals(leave.getJobNumber())){
    							leaveList.add(leave);
    						} else {
    							annualMap.put(flag, leaveList);
    							flag = leave.getJobNumber();
    							leaveList = new ArrayList<CkAnnualLeave>();
    							leaveList.add(leave);
    						}
    					}
    				}
    				if (annualMap.get(flag) == null){
    					annualMap.put(flag, leaveList);
    				}
    			}
    			//循环封装
    			if (list.size() > 0){
    				for (Map<String, String> map :list){
    					if (!annualMap.isEmpty()){
    						List<CkAnnualLeave> recordList = annualMap.get(map.get("jobNumber"));
    						Double total = 0.0;
    						if (recordList != null){
    							for (CkAnnualLeave leave:recordList){
    								total += leave.getDays();
    								String days = map.get("month"+leave.getMonthCalendar());
    								if ("".equals(days)){
    									map.put("month"+leave.getMonthCalendar(), leave.getDays()+"");
    								} else {
    									map.put("month"+leave.getMonthCalendar(), Double.parseDouble(days)+(double)leave.getDays()+"");
    								}
    							}
    							map.put("haveTake", total+"");//已休
    							if (!"0".equals(map.get("shouldTake"))){
    								map.put("surplus", (Double.parseDouble(map.get("shouldTake"))
    										-Double.parseDouble(map.get("haveTake")))+"");
    							} else {
    								map.put("surplus", "0");//剩余
    							}
    						} else {
    							map.put("haveTake", "0");//已休
    							map.put("surplus", map.get("shouldTake"));//剩余
    						}
    					} else {
    						map.put("haveTake", "0");//已休
    						map.put("surplus", map.get("shouldTake"));//剩余
    					}
    				}
    			}
    		}
        }
		return list;
	}
	
	public static int countMonths(String date1,String date2) throws Exception{
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    Calendar c1 = Calendar.getInstance();
	    Calendar c2 = Calendar.getInstance();
	    c1.setTime(sdf.parse(date1));
	    c2.setTime(sdf.parse(date2));
	    int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
	    return year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
	}
	
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
  				depIds += ("'"+ (b==null?"":b.toString()) + "', ");
  				//获取下级部门
  				List dataP = backendDepService.findBySql("SELECT D.PARENT,D.ID FROM QX_DEPARTMENT D " +
               		"INNER JOIN QX_USER_SHOWAUTH UD ON UD.BRANCH_DEP=D.ID AND UD.FLG='1' AND UD.USERID='"+userId+"' " +
               		"WHERE D.BARCH_ID='"+b+"' AND D.IS_DELETE=0 ");
  				for (int k=0; k<dataP.size(); k++){
  					Object[] dep=(Object[])dataP.get(k);
            	    String parent=dep[0]==null?"":dep[0].toString();
            	    if ("ROOT".equals(parent)){
            	    	depIds += ("'"+ (dep[1]==null?"":dep[1].toString()) + "', ");
            	    }else{
            	    	depIds += ("'"+ (dep[1]==null?"":dep[1].toString()) + "', ");
            	    }
  				}
  			}
  		}
  		if (!"".equals(depIds)){
  			depIds = depIds.substring(0,depIds.length()-2);
  		}
  		return depIds;
	}
}
