package com.lingnet.hcm.dao.impl.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.check.StandardDao;
import com.lingnet.hcm.entity.check.CkStandard;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: StandardDaoImpl 
 * @Description: 考勤标准Dao实现类 
 * @author wangqiang
 * @date 2017年3月22日 下午2:09:54 
 *
 */
@Repository("standardDao")
public class StandardDaoImpl extends BaseDaoImpl<CkStandard, String> 
	implements StandardDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getInfoByCond(Pager pager) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		String sql = "select ID, YEAR_CALENDAR, MONTH_DAYS, WORK_DAYS, REST_DAYS, HOLIDAY_DAYS, FESTIVAL1, "
				+ "FESTIVAL2, FESTIVAL3, FESTIVAL4 from CK_STANDARD "
				+ "where IS_DELETE = 0 ORDER BY CREATEDATE";
		Pager pageInfo = this.findPagerBySql(pager, sql);
		List<Object[]> list = (List<Object[]>) pageInfo.getResult();
		if (list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Map<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("id", list.get(i)[0]);
				hashmap.put("yearCalendar", list.get(i)[1]);
				hashmap.put("monthDays", list.get(i)[2]);
				hashmap.put("workDays", list.get(i)[3]);
				hashmap.put("restDays", list.get(i)[4]);
				if (list.get(i)[5] != null){
					hashmap.put("holidayDays", list.get(i)[5]);
				} else {
					hashmap.put("holidayDays", "");
				}
				if (list.get(i)[6] != null){
					hashmap.put("festival1", list.get(i)[6]);
				} else {
					hashmap.put("festival1", "");
				}
				if (list.get(i)[7] != null){
					hashmap.put("festival2", list.get(i)[7]);
				} else {
					hashmap.put("festival2", "");
				}
				if (list.get(i)[8] != null){
					hashmap.put("festival3", list.get(i)[8]);
				} else {
					hashmap.put("festival3", "");
				}
				if (list.get(i)[9] != null){
					hashmap.put("festival4", list.get(i)[9]);
				} else {
					hashmap.put("festival4", "");
				}
				mapList.add(hashmap);
			}
		}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllInfoList() {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		List<CkStandard> list = this.getSession().createCriteria(CkStandard.class)
				.add(Restrictions.eq("isDelete", 0)).addOrder(Order.asc("createDate")).list();
		if (list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Map<String, String> hashmap = new HashMap<String, String>();
				hashmap.put("yearCalendar", list.get(i).getYearCalendar()+"");
				hashmap.put("monthDays", list.get(i).getMonthDays()+"");
				hashmap.put("workDays", list.get(i).getWorkDays()+"");
				hashmap.put("restDays", list.get(i).getRestDays()+"");
				hashmap.put("holidayDays", list.get(i).getHolidayDays()+"");
				hashmap.put("festival1", list.get(i).getFestival1()+"");
				hashmap.put("festival2", list.get(i).getFestival2()+"");
				hashmap.put("festival3", list.get(i).getFestival3()+"");
				hashmap.put("festival4", list.get(i).getFestival4()+"");
				mapList.add(hashmap);
			}
		}
		return mapList;
	}

	@Override
	public CkStandard getStandardByYearCalendar(String yearMonth) {
		return (CkStandard) this.getSession().createCriteria(CkStandard.class)
				.add(Restrictions.eq("yearCalendar", yearMonth))
				.add(Restrictions.eq("isDelete", 0)).uniqueResult();
	}
	
	
}
