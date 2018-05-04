package com.lingnet.hcm.service.impl.check;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.check.InstituteDao;
import com.lingnet.hcm.dao.check.InstitutionDao;
import com.lingnet.hcm.entity.check.CkEmpInstitute;
import com.lingnet.hcm.service.check.InstituteService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: InstituteServiceImpl 
 * @Description: 考勤班次service实现类 
 * @author wangqiang
 * @date 2017年3月31日 上午11:05:23 
 *
 */
@Service("instituteService")
public class InstituteServiceImpl extends BaseServiceImpl<CkEmpInstitute, String> 
	implements InstituteService{
	
	@Resource(name = "instituteDao")
	private InstituteDao instituteDao;
	
	@Resource(name = "institutionDao")
	private InstitutionDao institutionDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getDataByCond(Pager pager, String typeFlag) {
		//获得信息集合
		List<Map<String, Object>> datas = instituteDao.getInfoByCond(pager, typeFlag);
        HashMap result = new HashMap();
        result.put("data", datas);
        result.put("total", pager.getTotalCount());
		return result;
	}

	@Override
	public void saveOrUpdateInfo(String formdata) throws Exception {
		Map<String,String> dataMap = JsonUtil.parseProperties(formdata);
		if (!dataMap.isEmpty()){
			if (!"".equals(dataMap.get("id"))){//修改
				CkEmpInstitute institute = instituteDao.get(dataMap.get("id"));
				institute.setInstituteId(dataMap.get("instituteId"));
				institute = changeInstituteInfos(institute, dataMap);
				instituteDao.update(institute);
			} else {//保存
				CkEmpInstitute institute = new CkEmpInstitute();
				institute.setInstituteId(dataMap.get("instituteId"));
				institute = changeInstituteInfos(institute, dataMap);
				institute.setIsDelete(0);
				instituteDao.save(institute);
			}
		}
	}

	@Override
	public HSSFWorkbook exportInfos() {
		String[] cellname={
				"序号","班制名称","开始日期","结束日期","循环类型","循环周期",
                "1","2","3","4","5","6","7","8","9","10","11","12",
                "13","14","15","16","17","18","19","20","21","22",
                "23","24","25","26","27","28","29","30","31","32",
                "33","34","35","36","37","38","39","40","星期一",
                "星期二","星期三","星期四","星期五","星期六","星期七"
                };
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("班制名称信息");
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
        //查询班制名称信息
        List<Map<String, String>> list = instituteDao.getAllInfoList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	Map<String, String> info = list.get(i);
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i+1+"");
                row.createCell(1).setCellValue(info.get("instituteName")); 
                row.createCell(2).setCellValue(info.get("startDate")); 
                row.createCell(3).setCellValue(info.get("endDate")); 
                row.createCell(4).setCellValue(info.get("cycleType")); 
                row.createCell(5).setCellValue(info.get("days")); 
                if(info.get("day1") != null && !"null".equals(info.get("day1"))){ 
                	row.createCell(6).setCellValue(info.get("day1")); 
                }
                if(info.get("day2") != null && !"null".equals(info.get("day2"))){ 
                	row.createCell(7).setCellValue(info.get("day2")); 
                }
                if(info.get("day3") != null && !"null".equals(info.get("day3"))){ 
                	row.createCell(8).setCellValue(info.get("day3")); 
                }
                if(info.get("day4") != null && !"null".equals(info.get("day4"))){ 
                	row.createCell(9).setCellValue(info.get("day4")); 
                }
                if(info.get("day5") != null && !"null".equals(info.get("day5"))){ 
                	row.createCell(10).setCellValue(info.get("day5")); 
                }
                if(info.get("day6") != null && !"null".equals(info.get("day6"))){ 
                	row.createCell(11).setCellValue(info.get("day6")); 
                }
                if(info.get("day7") != null && !"null".equals(info.get("day7"))){ 
                	row.createCell(12).setCellValue(info.get("day7")); 
                }
                if(info.get("day8") != null && !"null".equals(info.get("day8"))){ 
                	row.createCell(13).setCellValue(info.get("day8")); 
                }
                if(info.get("day9") != null && !"null".equals(info.get("day9"))){ 
                	row.createCell(14).setCellValue(info.get("day9")); 
                }
                if(info.get("day10") != null && !"null".equals(info.get("day10"))){ 
                	row.createCell(15).setCellValue(info.get("day10")); 
                }
                if(info.get("day11") != null && !"null".equals(info.get("day11"))){ 
                	row.createCell(16).setCellValue(info.get("day11")); 
                }
                if(info.get("day12") != null && !"null".equals(info.get("day12"))){ 
                	row.createCell(17).setCellValue(info.get("day12")); 
                }
                if(info.get("day13") != null && !"null".equals(info.get("day13"))){ 
                	row.createCell(18).setCellValue(info.get("day13")); 
                }
                if(info.get("day14") != null && !"null".equals(info.get("day14"))){ 
                	row.createCell(19).setCellValue(info.get("day14")); 
                }
                if(info.get("day15") != null && !"null".equals(info.get("day15"))){ 
                	row.createCell(20).setCellValue(info.get("day15")); 
                }
                if(info.get("day16") != null && !"null".equals(info.get("day16"))){ 
                	row.createCell(21).setCellValue(info.get("day16")); 
                }
                if(info.get("day17") != null && !"null".equals(info.get("day17"))){ 
                	row.createCell(22).setCellValue(info.get("day17")); 
                }
                if(info.get("day18") != null && !"null".equals(info.get("day18"))){ 
                	row.createCell(23).setCellValue(info.get("day18")); 
                }
                if(info.get("day19") != null && !"null".equals(info.get("day19"))){ 
                	row.createCell(24).setCellValue(info.get("day19")); 
                }
                if(info.get("day20") != null && !"null".equals(info.get("day20"))){ 
                	row.createCell(25).setCellValue(info.get("day20")); 
                }
                if(info.get("day21") != null && !"null".equals(info.get("day21"))){ 
                	row.createCell(26).setCellValue(info.get("day21")); 
                }
                if(info.get("day22") != null && !"null".equals(info.get("day22"))){ 
                	row.createCell(27).setCellValue(info.get("day22")); 
                }
                if(info.get("day23") != null && !"null".equals(info.get("day23"))){ 
                	row.createCell(28).setCellValue(info.get("day23")); 
                }
                if(info.get("day24") != null && !"null".equals(info.get("day24"))){ 
                	row.createCell(29).setCellValue(info.get("day24")); 
                }
                if(info.get("day25") != null && !"null".equals(info.get("day25"))){ 
                	row.createCell(30).setCellValue(info.get("day25")); 
                }
                if(info.get("day26") != null && !"null".equals(info.get("day26"))){ 
                	row.createCell(31).setCellValue(info.get("day26")); 
                }
                if(info.get("day27") != null && !"null".equals(info.get("day27"))){ 
                	row.createCell(32).setCellValue(info.get("day27")); 
                }
                if(info.get("day28") != null && !"null".equals(info.get("day28"))){ 
                	row.createCell(33).setCellValue(info.get("day28")); 
                }
                if(info.get("day29") != null && !"null".equals(info.get("day29"))){ 
                	row.createCell(34).setCellValue(info.get("day29")); 
                }
                if(info.get("day30") != null && !"null".equals(info.get("day30"))){ 
                	row.createCell(35).setCellValue(info.get("day30")); 
                }
                if(info.get("day31") != null && !"null".equals(info.get("day31"))){ 
                	row.createCell(36).setCellValue(info.get("day31")); 
                }
                if(info.get("day32") != null && !"null".equals(info.get("day32"))){ 
                	row.createCell(37).setCellValue(info.get("day32")); 
                }
                if(info.get("day33") != null && !"null".equals(info.get("day33"))){ 
                	row.createCell(38).setCellValue(info.get("day33")); 
                }
                if(info.get("day34") != null && !"null".equals(info.get("day34"))){ 
                	row.createCell(39).setCellValue(info.get("day34")); 
                }
                if(info.get("day35") != null && !"null".equals(info.get("day35"))){ 
                	row.createCell(40).setCellValue(info.get("day35")); 
                }
                if(info.get("day36") != null && !"null".equals(info.get("day36"))){ 
                	row.createCell(41).setCellValue(info.get("day36")); 
                }
                if(info.get("day37") != null && !"null".equals(info.get("day37"))){ 
                	row.createCell(42).setCellValue(info.get("day37")); 
                }
                if(info.get("day38") != null && !"null".equals(info.get("day38"))){ 
                	row.createCell(43).setCellValue(info.get("day38")); 
                }
                if(info.get("day39") != null && !"null".equals(info.get("day39"))){ 
                	row.createCell(44).setCellValue(info.get("day39")); 
                }
                if(info.get("day40") != null && !"null".equals(info.get("day40"))){ 
                	row.createCell(45).setCellValue(info.get("day40")); 
                }
                if(info.get("week1") != null && !"null".equals(info.get("week1"))){ 
                	row.createCell(46).setCellValue(info.get("week1")); 
                }
                if(info.get("week2") != null && !"null".equals(info.get("week2"))){ 
                	row.createCell(47).setCellValue(info.get("week2")); 
                }
                if(info.get("week3") != null && !"null".equals(info.get("week3"))){ 
                	row.createCell(48).setCellValue(info.get("week3")); 
                }
                if(info.get("week4") != null && !"null".equals(info.get("week4"))){ 
                	row.createCell(49).setCellValue(info.get("week4")); 
                }
                if(info.get("week5") != null && !"null".equals(info.get("week5"))){ 
                	row.createCell(50).setCellValue(info.get("week5")); 
                }
                if(info.get("week6") != null && !"null".equals(info.get("week6"))){ 
                	row.createCell(51).setCellValue(info.get("week6")); 
                }
                if(info.get("week7") != null && !"null".equals(info.get("week7"))){ 
                	row.createCell(52).setCellValue(info.get("week7")); 
                }                
            }
        }
        return hwb;
	}
	
	/**
	 * 保存考勤班次信息
	 * @Title: changeInstituteInfos 
	 * @param institute
	 * @param dataMap
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月5日 V 1.0
	 */
	public CkEmpInstitute changeInstituteInfos(CkEmpInstitute institute, Map<String, String> dataMap) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		institute.setStartDate(sdf.parse(dataMap.get("startDate")+""));
		institute.setEndDate(sdf.parse(dataMap.get("endDate")+""));
		institute.setCycleType(Integer.parseInt(dataMap.get("cycleType")+""));
		if ("1".equals(dataMap.get("cycleType"))){
			institute.setDays(Integer.parseInt(dataMap.get("days")+""));
		} else if ("3".equals(dataMap.get("cycleType")+"")){
			institute.setDays(7);
		}
		institute.setDay1(dataMap.get("day1"));
		institute.setDay2(dataMap.get("day2"));
		institute.setDay3(dataMap.get("day3"));
		institute.setDay4(dataMap.get("day4"));
		institute.setDay5(dataMap.get("day5"));
		institute.setDay6(dataMap.get("day6"));
		institute.setDay7(dataMap.get("day7"));
		institute.setDay8(dataMap.get("day8"));
		institute.setDay9(dataMap.get("day9"));
		institute.setDay10(dataMap.get("day10"));
		institute.setDay11(dataMap.get("day11"));
		institute.setDay12(dataMap.get("day12"));
		institute.setDay13(dataMap.get("day13"));
		institute.setDay14(dataMap.get("day14"));
		institute.setDay15(dataMap.get("day15"));
		institute.setDay16(dataMap.get("day16"));
		institute.setDay17(dataMap.get("day17"));
		institute.setDay18(dataMap.get("day18"));
		institute.setDay19(dataMap.get("day19"));
		institute.setDay20(dataMap.get("day20"));
		institute.setDay21(dataMap.get("day21"));
		institute.setDay22(dataMap.get("day22"));
		institute.setDay23(dataMap.get("day23"));
		institute.setDay24(dataMap.get("day24"));
		institute.setDay25(dataMap.get("day25"));
		institute.setDay26(dataMap.get("day26"));
		institute.setDay27(dataMap.get("day27"));
		institute.setDay28(dataMap.get("day28"));
		institute.setDay29(dataMap.get("day29"));
		institute.setDay30(dataMap.get("day30"));
		institute.setDay31(dataMap.get("day31"));
		institute.setDay32(dataMap.get("day32"));
		institute.setDay33(dataMap.get("day33"));
		institute.setDay34(dataMap.get("day34"));
		institute.setDay35(dataMap.get("day35"));
		institute.setDay36(dataMap.get("day36"));
		institute.setDay37(dataMap.get("day37"));
		institute.setDay38(dataMap.get("day38"));
		institute.setDay39(dataMap.get("day39"));
		institute.setDay40(dataMap.get("day40"));
		institute.setWeek1(dataMap.get("week1"));
		institute.setWeek2(dataMap.get("week2"));
		institute.setWeek3(dataMap.get("week3"));
		institute.setWeek4(dataMap.get("week4"));
		institute.setWeek5(dataMap.get("week5"));
		institute.setWeek6(dataMap.get("week6"));
		institute.setWeek7(dataMap.get("week7"));
		return institute;
	}

	@Override
	public void saveInstituteInfo(Map<String, String> dataMap) throws Exception {
		if (!dataMap.isEmpty()){
			CkEmpInstitute institute = new CkEmpInstitute();
			institute.setInstituteId(dataMap.get("instituteId"));
			String yearCalendar = dataMap.get("yearCalendar");//年份
			String monthCalendar = dataMap.get("monthCalendar");//月份
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startDate = yearCalendar+"-"+(monthCalendar.length()==1?"0"+monthCalendar:monthCalendar)+"-01";
			String endDate = yearCalendar+"-"
					+(monthCalendar.length()==1?"0"+monthCalendar:monthCalendar)
					+"-"+getDaysByYearMonth(Integer.parseInt(yearCalendar), Integer.parseInt(monthCalendar));
			institute.setStartDate(sdf.parse(startDate));
			institute.setEndDate(sdf.parse(endDate));
			institute.setCycleType(2);//月
			institute.setDay1(dataMap.get("day1"));
			institute.setDay2(dataMap.get("day2"));
			institute.setDay3(dataMap.get("day3"));
			institute.setDay4(dataMap.get("day4"));
			institute.setDay5(dataMap.get("day5"));
			institute.setDay6(dataMap.get("day6"));
			institute.setDay7(dataMap.get("day7"));
			institute.setDay8(dataMap.get("day8"));
			institute.setDay9(dataMap.get("day9"));
			institute.setDay10(dataMap.get("day10"));
			institute.setDay11(dataMap.get("day11"));
			institute.setDay12(dataMap.get("day12"));
			institute.setDay13(dataMap.get("day13"));
			institute.setDay14(dataMap.get("day14"));
			institute.setDay15(dataMap.get("day15"));
			institute.setDay16(dataMap.get("day16"));
			institute.setDay17(dataMap.get("day17"));
			institute.setDay18(dataMap.get("day18"));
			institute.setDay19(dataMap.get("day19"));
			institute.setDay20(dataMap.get("day20"));
			institute.setDay21(dataMap.get("day21"));
			institute.setDay22(dataMap.get("day22"));
			institute.setDay23(dataMap.get("day23"));
			institute.setDay24(dataMap.get("day24"));
			institute.setDay25(dataMap.get("day25"));
			institute.setDay26(dataMap.get("day26"));
			institute.setDay27(dataMap.get("day27"));
			institute.setDay28(dataMap.get("day28"));
			if (dataMap.get("day29") != null && !"".equals(dataMap.get("day29"))){
				institute.setDay29(dataMap.get("day29"));
			}
			if (dataMap.get("day30") != null && !"".equals(dataMap.get("day30"))){
				institute.setDay30(dataMap.get("day30"));
			}
			if (dataMap.get("day31") != null && !"".equals(dataMap.get("day31"))){
				institute.setDay31(dataMap.get("day31"));
			}
            List<CkEmpInstitute> instlist=this.getList(Restrictions.eq("isDelete", 0));
            for (int i = 0; i < instlist.size(); i++) {
                CkEmpInstitute ist= instlist.get(i);
                ist.setIsDelete(1);
                instituteDao.update(ist);
            }
			institute.setIsDelete(0);
			instituteDao.save(institute);
		}
	}
	
	public static int getDaysByYearMonth(int year, int month) {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }

	@Override
	public void updateInstituteInfo(Map<String, String> dataMap,
			CkEmpInstitute institute) throws Exception {
		institute.setDay1(dataMap.get("day1"));
		institute.setDay2(dataMap.get("day2"));
		institute.setDay3(dataMap.get("day3"));
		institute.setDay4(dataMap.get("day4"));
		institute.setDay5(dataMap.get("day5"));
		institute.setDay6(dataMap.get("day6"));
		institute.setDay7(dataMap.get("day7"));
		institute.setDay8(dataMap.get("day8"));
		institute.setDay9(dataMap.get("day9"));
		institute.setDay10(dataMap.get("day10"));
		institute.setDay11(dataMap.get("day11"));
		institute.setDay12(dataMap.get("day12"));
		institute.setDay13(dataMap.get("day13"));
		institute.setDay14(dataMap.get("day14"));
		institute.setDay15(dataMap.get("day15"));
		institute.setDay16(dataMap.get("day16"));
		institute.setDay17(dataMap.get("day17"));
		institute.setDay18(dataMap.get("day18"));
		institute.setDay19(dataMap.get("day19"));
		institute.setDay20(dataMap.get("day20"));
		institute.setDay21(dataMap.get("day21"));
		institute.setDay22(dataMap.get("day22"));
		institute.setDay23(dataMap.get("day23"));
		institute.setDay24(dataMap.get("day24"));
		institute.setDay25(dataMap.get("day25"));
		institute.setDay26(dataMap.get("day26"));
		institute.setDay27(dataMap.get("day27"));
		institute.setDay28(dataMap.get("day28"));
		if (dataMap.get("day29") != null && !"".equals(dataMap.get("day29"))){
			institute.setDay29(dataMap.get("day29"));
		}
		if (dataMap.get("day30") != null && !"".equals(dataMap.get("day30"))){
			institute.setDay30(dataMap.get("day30"));
		}
		if (dataMap.get("day31") != null && !"".equals(dataMap.get("day31"))){
			institute.setDay31(dataMap.get("day31"));
		}
		instituteDao.update(institute);
	}

	@Override
	public CkEmpInstitute getValidateInstituteId(String instituteId) {
		return instituteDao.getEmpInstituteByInstituteId(instituteId);
	} 
}
