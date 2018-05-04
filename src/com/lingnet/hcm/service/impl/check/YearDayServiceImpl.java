package com.lingnet.hcm.service.impl.check;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.check.YearDayDao;
import com.lingnet.hcm.entity.check.CkYearDay;
import com.lingnet.hcm.service.check.YearDayService;
/**
 * 
 * @ClassName: YearDayServiceImpl 
 * @Description: 全年白班班制service实现类 
 * @author wangqiang
 * @date 2017年4月14日 下午1:57:43 
 *
 */
@Service("yearDayService")
public class YearDayServiceImpl extends BaseServiceImpl<CkYearDay, String> implements YearDayService{
	
	@Resource(name = "yearDayDao")
	private YearDayDao yearDayDao;
	
	@Override
	public CkYearDay getDayInfoByCond(String thisYear, String thisMonth) {
		return yearDayDao.getDayInfoByCond(thisYear, thisMonth);
	}

	@Override
	public void updateInfo(Map<String, String> dataMap) {
		if (!dataMap.isEmpty() && dataMap.get("id") != null && !"".equals(dataMap.get("id"))){
			CkYearDay yearDay = yearDayDao.get(dataMap.get("id"));
			yearDay.setDay1(dataMap.get("day1"));
			yearDay.setDay2(dataMap.get("day2"));
			yearDay.setDay3(dataMap.get("day3"));
			yearDay.setDay4(dataMap.get("day4"));
			yearDay.setDay5(dataMap.get("day5"));
			yearDay.setDay6(dataMap.get("day6"));
			yearDay.setDay7(dataMap.get("day7"));
			yearDay.setDay8(dataMap.get("day8"));
			yearDay.setDay9(dataMap.get("day9"));
			yearDay.setDay10(dataMap.get("day10"));
			yearDay.setDay11(dataMap.get("day11"));
			yearDay.setDay12(dataMap.get("day12"));
			yearDay.setDay13(dataMap.get("day13"));
			yearDay.setDay14(dataMap.get("day14"));
			yearDay.setDay15(dataMap.get("day15"));
			yearDay.setDay16(dataMap.get("day16"));
			yearDay.setDay17(dataMap.get("day17"));
			yearDay.setDay18(dataMap.get("day18"));
			yearDay.setDay19(dataMap.get("day19"));
			yearDay.setDay20(dataMap.get("day20"));
			yearDay.setDay21(dataMap.get("day21"));
			yearDay.setDay22(dataMap.get("day22"));
			yearDay.setDay23(dataMap.get("day23"));
			yearDay.setDay24(dataMap.get("day24"));
			yearDay.setDay25(dataMap.get("day25"));
			yearDay.setDay26(dataMap.get("day26"));
			yearDay.setDay27(dataMap.get("day27"));
			yearDay.setDay28(dataMap.get("day28"));
			if (dataMap.get("day29") != null){
				yearDay.setDay29(dataMap.get("day29"));
			}
			if (dataMap.get("day30") != null){
				yearDay.setDay30(dataMap.get("day30"));
			}
			if (dataMap.get("day31") != null){
				yearDay.setDay31(dataMap.get("day31"));
			}
			yearDayDao.update(yearDay);
		}
	}
	
	
}
