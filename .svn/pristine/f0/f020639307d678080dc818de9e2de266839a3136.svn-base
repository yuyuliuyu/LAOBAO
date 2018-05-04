package com.lingnet.hcm.dao.impl.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.check.InstituteDao;
import com.lingnet.hcm.entity.check.CkEmpInstitute;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: InstituteDaoImpl 
 * @Description: 考勤班次Dao实现类 
 * @author wangqiang
 * @date 2017年3月31日 上午10:59:40 
 *
 */
@Repository("instituteDao")
public class InstituteDaoImpl extends BaseDaoImpl<CkEmpInstitute, String> implements InstituteDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getInfoByCond(Pager pager, String typeFlag) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		String sql = "select cei.ID, ci.INSTITUTE_NAME, to_char(cei.STARTDATE,'yyyy-MM-dd'), to_char(cei.ENDDATE,'yyyy-MM-dd'), "
				+ "cei.CYCLE_TYPE, cei.DAYS, cei.DAY1, cei.DAY2, cei.DAY3, cei.DAY4, cei.DAY5, cei.DAY6, "
				+ "cei.DAY7, cei.DAY8, cei.DAY9, cei.DAY10, cei.DAY11, cei.DAY12, cei.DAY13, cei.DAY14, cei.DAY15, "
				+ "cei.DAY16, cei.DAY17, cei.DAY18, cei.DAY19, cei.DAY20, cei.DAY21, cei.DAY22, cei.DAY23, cei.DAY24, "
				+ "cei.DAY25, cei.DAY26, cei.DAY27, cei.DAY28, cei.DAY29, cei.DAY30, cei.DAY31, cei.DAY32, cei.DAY33, "
				+ "cei.DAY34, cei.DAY35, cei.DAY36, cei.DAY37, cei.DAY38, cei.DAY39, cei.DAY40, cei.WEEK1, cei.WEEK2, "
				+ "cei.WEEK3, cei.WEEK4, cei.WEEK5, cei.WEEK6, cei.WEEK7 "
				+ "from CK_EMP_INSTITUTE cei "
				+ "left join CK_INSTITUTION ci on cei.INSTITUTE_ID = ci.ID "
				+ "where cei.IS_DELETE = 0 ";
		if ("1".equals(typeFlag)){
			sql += "and ci.INSTITUTE_NAME <> '白班' ";
		} else if ("2".equals(typeFlag)){
			sql += "and ci.INSTITUTE_NAME = '白班' ";
		}
		sql += "ORDER BY cei.CREATEDATE";
		Pager pageInfo = this.findPagerBySql(pager, sql);
		List<Object[]> list = (List<Object[]>) pageInfo.getResult();
		if (list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Map<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("id", list.get(i)[0]);
				hashmap.put("instituteName", list.get(i)[1]);
				hashmap.put("startDate", list.get(i)[2]);
				hashmap.put("endDate", list.get(i)[3]);
				if ("1".equals(list.get(i)[4]+"")){
					hashmap.put("cycleType", "天");
				} else if ("2".equals(list.get(i)[4]+"")){
					hashmap.put("cycleType", "月");
				} else if ("3".equals(list.get(i)[4]+"")){
					hashmap.put("cycleType", "周");
				} else {
					hashmap.put("cycleType", "");
				}
				hashmap.put("days", list.get(i)[5]);
				hashmap.put("day1", list.get(i)[6]);
				hashmap.put("day2", list.get(i)[7]);
				hashmap.put("day3", list.get(i)[8]);
				hashmap.put("day4", list.get(i)[9]);
				hashmap.put("day5", list.get(i)[10]);
				hashmap.put("day6", list.get(i)[11]);
				hashmap.put("day7", list.get(i)[12]);
				hashmap.put("day8", list.get(i)[13]);
				hashmap.put("day9", list.get(i)[14]);
				hashmap.put("day10", list.get(i)[15]);
				hashmap.put("day11", list.get(i)[16]);
				hashmap.put("day12", list.get(i)[17]);
				hashmap.put("day13", list.get(i)[18]);
				hashmap.put("day14", list.get(i)[19]);
				hashmap.put("day15", list.get(i)[20]);
				hashmap.put("day16", list.get(i)[21]);
				hashmap.put("day17", list.get(i)[22]);
				hashmap.put("day18", list.get(i)[23]);
				hashmap.put("day19", list.get(i)[24]);
				hashmap.put("day20", list.get(i)[25]);
				hashmap.put("day21", list.get(i)[26]);
				hashmap.put("day22", list.get(i)[27]);
				hashmap.put("day23", list.get(i)[28]);
				hashmap.put("day24", list.get(i)[29]);
				hashmap.put("day25", list.get(i)[30]);
				hashmap.put("day26", list.get(i)[31]);
				hashmap.put("day27", list.get(i)[32]);
				hashmap.put("day28", list.get(i)[33]);
				hashmap.put("day29", list.get(i)[34]);
				hashmap.put("day30", list.get(i)[35]);
				hashmap.put("day31", list.get(i)[36]);
				hashmap.put("day32", list.get(i)[37]);
				hashmap.put("day33", list.get(i)[38]);
				hashmap.put("day34", list.get(i)[39]);
				hashmap.put("day35", list.get(i)[40]);
				hashmap.put("day36", list.get(i)[41]);
				hashmap.put("day37", list.get(i)[42]);
				hashmap.put("day38", list.get(i)[43]);
				hashmap.put("day39", list.get(i)[44]);
				hashmap.put("day40", list.get(i)[45]);
				hashmap.put("week1", list.get(i)[46]);
				hashmap.put("week2", list.get(i)[47]);
				hashmap.put("week3", list.get(i)[48]);
				hashmap.put("week4", list.get(i)[49]);
				hashmap.put("week5", list.get(i)[50]);
				hashmap.put("week6", list.get(i)[51]);
				hashmap.put("week7", list.get(i)[52]);
				mapList.add(hashmap);
			}
		}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllInfoList() {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		String sql = "select ci.INSTITUTE_NAME, to_char(cei.STARTDATE,'yyyy-MM-dd'), to_char(cei.ENDDATE,'yyyy-MM-dd'), "
				+ "cei.CYCLE_TYPE, cei.DAYS, cei.DAY1, cei.DAY2, cei.DAY3, cei.DAY4, cei.DAY5, cei.DAY6, "
				+ "cei.DAY7, cei.DAY8, cei.DAY9, cei.DAY10, cei.DAY11, cei.DAY12, cei.DAY13, cei.DAY14, cei.DAY15, "
				+ "cei.DAY16, cei.DAY17, cei.DAY18, cei.DAY19, cei.DAY20, cei.DAY21, cei.DAY22, cei.DAY23, cei.DAY24, "
				+ "cei.DAY25, cei.DAY26, cei.DAY27, cei.DAY28, cei.DAY29, cei.DAY30, cei.DAY31, cei.DAY32, cei.DAY33, "
				+ "cei.DAY34, cei.DAY35, cei.DAY36, cei.DAY37, cei.DAY38, cei.DAY39, cei.DAY40, cei.WEEK1, cei.WEEK2, "
				+ "cei.WEEK3, cei.WEEK4, cei.WEEK5, cei.WEEK6, cei.WEEK7 "
				+ "from CK_EMP_INSTITUTE cei "
				+ "left join CK_INSTITUTION ci on cei.INSTITUTE_ID = ci.ID "
				+ "where cei.IS_DELETE = 0 ORDER BY cei.CREATEDATE";
		List<Object[]> list = this.getSession().createSQLQuery(sql).list();
		if (list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Map<String, String> hashmap = new HashMap<String, String>();
				hashmap.put("instituteName", list.get(i)[0]+"");
				hashmap.put("startDate", list.get(i)[1]+"");
				hashmap.put("endDate", list.get(i)[2]+"");
				if ("1".equals(list.get(i)[3]+"")){
					hashmap.put("cycleType", "天");
				} else if ("2".equals(list.get(i)[3]+"")){
					hashmap.put("cycleType", "月");
				} else if ("3".equals(list.get(i)[3]+"")){
					hashmap.put("cycleType", "周");
				} else {
					hashmap.put("cycleType", "");
				}
				hashmap.put("days", list.get(i)[4]+"");
				hashmap.put("day1", list.get(i)[5]+"");
				hashmap.put("day2", list.get(i)[6]+"");
				hashmap.put("day3", list.get(i)[7]+"");
				hashmap.put("day4", list.get(i)[8]+"");
				hashmap.put("day5", list.get(i)[9]+"");
				hashmap.put("day6", list.get(i)[10]+"");
				hashmap.put("day7", list.get(i)[11]+"");
				hashmap.put("day8", list.get(i)[12]+"");
				hashmap.put("day9", list.get(i)[13]+"");
				hashmap.put("day10", list.get(i)[14]+"");
				hashmap.put("day11", list.get(i)[15]+"");
				hashmap.put("day12", list.get(i)[16]+"");
				hashmap.put("day13", list.get(i)[17]+"");
				hashmap.put("day14", list.get(i)[18]+"");
				hashmap.put("day15", list.get(i)[19]+"");
				hashmap.put("day16", list.get(i)[20]+"");
				hashmap.put("day17", list.get(i)[21]+"");
				hashmap.put("day18", list.get(i)[22]+"");
				hashmap.put("day19", list.get(i)[23]+"");
				hashmap.put("day20", list.get(i)[24]+"");
				hashmap.put("day21", list.get(i)[25]+"");
				hashmap.put("day22", list.get(i)[26]+"");
				hashmap.put("day23", list.get(i)[27]+"");
				hashmap.put("day24", list.get(i)[28]+"");
				hashmap.put("day25", list.get(i)[29]+"");
				hashmap.put("day26", list.get(i)[30]+"");
				hashmap.put("day27", list.get(i)[31]+"");
				hashmap.put("day28", list.get(i)[32]+"");
				hashmap.put("day29", list.get(i)[33]+"");
				hashmap.put("day30", list.get(i)[34]+"");
				hashmap.put("day31", list.get(i)[35]+"");
				hashmap.put("day32", list.get(i)[36]+"");
				hashmap.put("day33", list.get(i)[37]+"");
				hashmap.put("day34", list.get(i)[38]+"");
				hashmap.put("day35", list.get(i)[39]+"");
				hashmap.put("day36", list.get(i)[40]+"");
				hashmap.put("day37", list.get(i)[41]+"");
				hashmap.put("day38", list.get(i)[42]+"");
				hashmap.put("day39", list.get(i)[43]+"");
				hashmap.put("day40", list.get(i)[44]+"");
				hashmap.put("week1", list.get(i)[45]+"");
				hashmap.put("week2", list.get(i)[46]+"");
				hashmap.put("week3", list.get(i)[47]+"");
				hashmap.put("week4", list.get(i)[48]+"");
				hashmap.put("week5", list.get(i)[49]+"");
				hashmap.put("week6", list.get(i)[50]+"");
				hashmap.put("week7", list.get(i)[51]+"");
				mapList.add(hashmap);
			}
		}
		return mapList;
	}

	@Override
	public CkEmpInstitute getEmpInstituteByInstituteId(String instituteId) {
		return (CkEmpInstitute) this.getSession().createCriteria(CkEmpInstitute.class)
				.add(Restrictions.eq("instituteId", instituteId)).add(Restrictions.eq("isDelete", 0)).uniqueResult();
	}
	
}
