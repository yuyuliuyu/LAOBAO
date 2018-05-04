package com.lingnet.hcm.service.impl.check;

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
import com.lingnet.hcm.dao.check.StandardDao;
import com.lingnet.hcm.entity.check.CkStandard;
import com.lingnet.hcm.service.check.StandardService;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: StandardServiceImpl 
 * @Description: 考勤标准service实现类 
 * @author wangqiang
 * @date 2017年3月22日 下午2:13:12 
 *
 */
@Service("standardService")
public class StandardServiceImpl extends BaseServiceImpl<CkStandard, String> implements StandardService{
	
	@Resource(name = "standardDao")
	private StandardDao standardDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getDataByCond(Pager pager) {
		List<Map<String, Object>> datas = standardDao.getInfoByCond(pager);
        HashMap result = new HashMap();
        result.put("data", datas);
        result.put("total", pager.getTotalCount());
		return result;
	}

	@Override
	public void saveOrUpdateInfo(Map<String,String> dataMap) throws Exception {
		if (!dataMap.isEmpty()){
			if (!"".equals(dataMap.get("id"))){//修改
				CkStandard standard = standardDao.get(dataMap.get("id"));
				standard.setYearCalendar(dataMap.get("yearCalendar"));
				standard.setMonthDays(dataMap.get("monthDays"));
				standard.setWorkDays(dataMap.get("workDays"));
				standard.setRestDays(dataMap.get("restDays"));
				standard.setHolidayDays(dataMap.get("holidayDays"));
				standard.setFestival1(dataMap.get("festival1"));
				standard.setFestival2(dataMap.get("festival2"));
				standard.setFestival3(dataMap.get("festival3"));
				standard.setFestival4(dataMap.get("festival4"));
				standardDao.update(standard);
			} else {//保存
				CkStandard standard = new CkStandard();
				standard.setYearCalendar(dataMap.get("yearCalendar"));
				standard.setMonthDays(dataMap.get("monthDays"));
				standard.setWorkDays(dataMap.get("workDays"));
				standard.setRestDays(dataMap.get("restDays"));
				standard.setHolidayDays(dataMap.get("holidayDays"));
				standard.setFestival1(dataMap.get("festival1"));
				standard.setFestival2(dataMap.get("festival2"));
				standard.setFestival3(dataMap.get("festival3"));
				standard.setFestival4(dataMap.get("festival4"));
				standard.setIsDelete(0);
				standardDao.save(standard);
			}
		}
	}

	@Override
	public HSSFWorkbook exportInfos() {
		String[] cellname={
				"序号","年月份","月历天数","工作日","公休日","节假日","节一","节二","节三","节四"
                };
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("考勤标准信息");
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
        List<Map<String, String>> list = standardDao.getAllInfoList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
            	Map<String, String> info = list.get(i);
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i+1+"");
                row.createCell(1).setCellValue(info.get("yearCalendar"));
                row.createCell(2).setCellValue(info.get("monthDays")); 
                row.createCell(3).setCellValue(info.get("workDays")); 
                row.createCell(4).setCellValue(info.get("restDays")); 
                if(info.get("holidayDays") != null && !"null".equals(info.get("holidayDays"))){ 
                	row.createCell(5).setCellValue(info.get("holidayDays")); 
                }
                if(info.get("festival1") != null && !"null".equals(info.get("festival1"))){ 
                	row.createCell(6).setCellValue(info.get("festival1")); 
                }
                if(info.get("festival2") != null && !"null".equals(info.get("festival2"))){ 
                	row.createCell(7).setCellValue(info.get("festival2")); 
                }
                if(info.get("festival3") != null && !"null".equals(info.get("festival3"))){ 
                	row.createCell(8).setCellValue(info.get("festival3")); 
                }
                if(info.get("festival4") != null && !"null".equals(info.get("festival4"))){ 
                	row.createCell(9).setCellValue(info.get("festival4")); 
                }
            }
        }
        return hwb;
	}
	
}
