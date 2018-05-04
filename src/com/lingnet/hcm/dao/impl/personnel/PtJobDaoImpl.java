package com.lingnet.hcm.dao.impl.personnel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.hcm.dao.personnel.PtJobDao;
import com.lingnet.hcm.entity.person.PtJob;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 人员岗位兼职Dao实现类
 */
@Repository("ptJobDao")
public class PtJobDaoImpl extends BaseDaoImplInit<PtJob, String> implements PtJobDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getInfoByCond(Pager pager, String searchData, String depIds) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		String sql = "select jbi.id, jbi.JOB_NUMBER, jbi.NAME, qd.NAME as UNIT_NAME, cc.CLASS_NAME, jpj.STANDARD_POST, "
				+ "jpj.SPE_POST, jbi.IS_MONITOR, jbi.IS_SPECIAL_HOUR, jbi.WAGE_FORM, jbi.CHECK_NO "
				+ "from jc_basic_information jbi "
				+ "left join jc_pt_job jpj on jpj.PERSON_ID = jbi.ID "
				+ "left join qx_department qd on qd.ID = jbi.CHECK_UNIT_ID "
				+ "left join CK_CLASS cc on cc.CLASS_NO = jbi.CLASS_NO "
				+ "where jbi.IS_DELETE = '0' and jpj.IS_HOST_POST = '1' ";
		if (searchData != null){
			Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
			if (dataMap.get("depId") != null && !"".equals(dataMap.get("depId"))){
				String[] depIdArrs = dataMap.get("depId").split(",");
				sql += "and jbi.CHECK_UNIT_ID in(";
				for (int i = 0; i < depIdArrs.length; i++){
					if (!"".equals(depIdArrs[i])){
						sql += ("'" + depIdArrs[i] + "', ");
					}
				}
				sql = sql.substring(0, sql.length()-2);
				sql += ") ";
			} 
			else {
				sql += "and jbi.CHECK_UNIT_ID IN("+ depIds+") ";
			}
			if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
				sql += "and jbi.JOB_NUMBER like '%" + dataMap.get("jobNumber") + "%' ";
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql += "and jbi.NAME like '%" + dataMap.get("name") + "%'";
			}
			if (dataMap.get("classNo") != null && !"".equals(dataMap.get("classNo"))){
				sql += "and jbi.CLASS_NO = '" + dataMap.get("classNo") + "' ";
			}
			if (dataMap.get("positionId") != null && !"".equals(dataMap.get("positionId"))){
			    sql += "and (jbi.POST_ID = '" + dataMap.get("positionId") + "' OR jpj.STANDARD_POST_ID = '" + dataMap.get("positionId") + "') ";
			}
		} else {
			sql += "and jbi.CHECK_UNIT_ID IN("+ depIds+") ";
		}
		Pager pageInfo = this.findPagerBySql(pager, sql);
		List<Object[]> list = (List<Object[]>) pageInfo.getResult();
		if (list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Map<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("id", list.get(i)[0]);
				hashmap.put("jobNumber", list.get(i)[1]);
				hashmap.put("name", list.get(i)[2]);
				hashmap.put("checkUnit", list.get(i)[3]);
				hashmap.put("className", list.get(i)[4]);
				hashmap.put("standardPost", list.get(i)[5]);
				hashmap.put("spePost", list.get(i)[6]);
				if ("0".equals(list.get(i)[7]+"")){
					hashmap.put("isMonitor", "否");
				} else if ("1".equals(list.get(i)[7]+"")){
					hashmap.put("isMonitor", "是");
				} else {
					hashmap.put("isMonitor", "");
				}
				if ("0".equals(list.get(i)[8]+"")){
					hashmap.put("isSpecialHour", "否");
				} else if ("1".equals(list.get(i)[8]+"")){
					hashmap.put("isSpecialHour", "是");
				} else {
					hashmap.put("isSpecialHour", "");
				}
				if ("0".equals(list.get(i)[9]+"")){
					hashmap.put("wageForm", "计时");
				} else if ("1".equals(list.get(i)[9]+"")){
					hashmap.put("wageForm", "计件");
				} else {
					hashmap.put("wageForm", "");
				}
				hashmap.put("checkNo", list.get(i)[10]);
				mapList.add(hashmap);
			}
		}
		return mapList;
	}

	@Override
	public Map<String, String> getPersonInfoById(String id) {
		String sql = "select jbi.id, jbi.JOB_NUMBER, jbi.NAME, jpj.STANDARD_POST, jpj.SPE_POST, jbi.CHECK_UNIT_ID, "
				+ "qd.NAME as UNIT_NAME, jbi.CLASS_NO, jbi.IS_MONITOR, jbi.IS_SPECIAL_HOUR, jbi.WAGE_FORM, jbi.CHECK_NO, "
				+ "xsr.SALARY_POST_NAME "
				+ "from jc_basic_information jbi "
				+ "left join jc_pt_job jpj on jpj.PERSON_ID = jbi.ID "
				+ "left join XC_SALARY_RECORD xsr on jbi.ID = xsr.STAFF_ID "
				+ "left join qx_department qd on qd.ID = jbi.CHECK_UNIT_ID "
				+ "left join CK_CLASS cc on cc.CLASS_NO = jbi.CLASS_NO "
				+ "where jpj.IS_HOST_POST = '1' and jbi.ID = '" + id + "'";
		Object[] infos = (Object[]) this.getSession().createSQLQuery(sql).uniqueResult();
		Map<String, String> map = new HashMap<String, String>();
		if (infos != null){
			map.put("id", infos[0]+"");
			map.put("jobNumber", infos[1]+"");
			map.put("name", infos[2]+"");
			map.put("standardPost", infos[3]+"");
			map.put("spePost", infos[4]+"");
			map.put("checkUnitId", infos[5]+"");
			map.put("checkUnit", infos[6]+"");
			map.put("classNo", infos[7]+"");
			map.put("isMonitor", infos[8]+"");
			map.put("isSpecialHour", infos[9]+"");
			map.put("wageForm", infos[10]+"");
			if (infos[11] == null){
				map.put("checkNo", "");
			} else {
				map.put("checkNo", infos[11]+"");
			}
			if (infos[12] == null){
				map.put("selaryPost", "");
			} else {
				map.put("selaryPost", infos[12]+"");
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getCheckInfosByDepIds(List<String> depIdList, String depIds) {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		String sql = "select jbi.JOB_NUMBER, jbi.NAME, qd.NAME as UNIT_NAME, cc.CLASS_NAME, jpj.STANDARD_POST, "
				+ "jpj.SPE_POST, jbi.IS_MONITOR, jbi.IS_SPECIAL_HOUR, jbi.WAGE_FORM, jbi.CHECK_NO, qd2.NAME as depName, br.FZX "
				+ "from jc_basic_information jbi "
				+ "left join jc_pt_job jpj on jpj.PERSON_ID = jbi.ID "
				+ "left join CK_CLASS cc on jbi.CLASS_NO = cc.CLASS_NO "
				+ "left join QX_DEPARTMENT qd on jbi.CHECK_UNIT_ID = qd.ID "
				+ "left join QX_DEPARTMENT qd2 on jbi.DEPART_ID = qd2.ID "
				+ "left join BRANCH br on qd2.BARCH_ID = br.ID "
				+ "where jbi.IS_DELETE = '0' and jpj.IS_HOST_POST = '1' ";
		if (depIdList.size() > 0){
			sql += "and jbi.CHECK_UNIT_ID in(";
			for (int i = 0; i < depIdList.size(); i++){
				if (!"".equals(depIdList.get(i))){
					sql += ("'" + depIdList.get(i) + "', ");
				}
			}
			sql = sql.substring(0, sql.length()-2);
			sql += ") ";
		} else {
			sql += "and jbi.CHECK_UNIT_ID IN("+ depIds+") ";
		}
		List<Object[]> list = this.getSession().createSQLQuery(sql).list();
		if (list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Map<String, String> hashmap = new HashMap<String, String>();
				hashmap.put("jobNumber", list.get(i)[0]+"");
				hashmap.put("name", list.get(i)[1]+"");
				hashmap.put("checkUnit", list.get(i)[2]+"");
				hashmap.put("className", list.get(i)[3]+"");
				hashmap.put("standardPost", list.get(i)[4]+"");
				hashmap.put("spePost", list.get(i)[5]+"");
				if ("0".equals(list.get(i)[6]+"")){
					hashmap.put("isMonitor", "否");
				} else if ("1".equals(list.get(i)[6]+"")){
					hashmap.put("isMonitor", "是");
				} else {
					hashmap.put("isMonitor", "");
				}
				if ("0".equals(list.get(i)[7]+"")){
					hashmap.put("isSpecialHour", "否");
				} else if ("1".equals(list.get(i)[7]+"")){
					hashmap.put("isSpecialHour", "是");
				} else {
					hashmap.put("isSpecialHour", "");
				}
				if ("0".equals(list.get(i)[8]+"")){
					hashmap.put("wageForm", "计时");
				} else if ("1".equals(list.get(i)[8]+"")){
					hashmap.put("wageForm", "计件");
				} else {
					hashmap.put("wageForm", "");
				}
				hashmap.put("checkNo", list.get(i)[9]+"");
				hashmap.put("depName", list.get(i)[10]+"");
				hashmap.put("branchName", list.get(i)[11]+"");
				mapList.add(hashmap);
			}
		}
		return mapList;
	}

	@Override
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.JOB_NUMBER,BAS.NAME PERNAME,BAS.FILM_NAME,BAS.DEPART_NAME,BAS.POST,BAS.SPECIFIC_POST,    ")
		.append("  PTJ.ID,PTJ.STANDARD_POST,PTJ.SPE_POST,PTJ.BEGIN_TIME,PTJ.END_TIME, ")
		.append("  PTJ.STATE,PTJ.BZ,PTJ.FIRM,PTJ.DEP,PTJ.END_CASE,PTJ.FIRM PTJFIRM,PTJ.DEP PTJDEP,PTJ.JOB_LEVER PTJLEVER  ")
		.append("  FROM JC_PT_JOB PTJ  ")
		.append("  LEFT JOIN JC_BASIC_INFORMATION BAS ON BAS.ID=PTJ.PERSON_ID     ")
		.append("  WHERE PTJ.IS_DELETE=0 AND  PTJ.IS_HOST_POST=0   ")
		;
		
		if(searchData!=null&&!"".equals(searchData)){
			Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
			if (dataMap.get("depId") != null && !"".equals(dataMap.get("depId"))){
				String[] depIdArrs = dataMap.get("depId").split(",");
				
				String  resql="";
				for (int i = 0; i < depIdArrs.length; i++){
					if (!"".equals(depIdArrs[i])){
						resql+="'" + depIdArrs[i] + "', ";
					}
				}
				resql = resql.substring(0, resql.length()-2);
				
				sql.append(" and BAS.DEPART_ID in("+resql+")");
				
			} else {
				sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
			}
			if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
				sql.append(" and BAS.JOB_NUMBER LIKE'%"+dataMap.get("jobNumber")+"%'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			if (dataMap.get("personId") != null && !"".equals(dataMap.get("personId"))){
				sql.append(" and BAS.ID = '"+dataMap.get("personId")+"'");
			}
			
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" order by PTJ.BEGIN_TIME DESC ");
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
        List dataList = new ArrayList();
        SimpleDateFormat form =new SimpleDateFormat("yyyy-MM-dd");
        String stamp = "";
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("jobNumber", obj[0]);//
			map.put("pername", obj[1]);
			map.put("filmName", obj[2]);//
			map.put("departName", obj[3]);
			map.put("post", obj[4]);
			map.put("specificPost", obj[5]);
			
			map.put("id", obj[6]);//
			map.put("standardPost", obj[7]);
			map.put("spePost", obj[8]);
			if(obj[9]!=null&&!"".equals(obj[9])){
				stamp=form.format(obj[9]);
				map.put("beginTime", stamp);
			}
			if(obj[10]!=null&&!"".equals(obj[10])){
				stamp=form.format(obj[10]);
				map.put("endTime", stamp);
			}
			
			map.put("state", obj[11]);
			map.put("bz", obj[12]);
			map.put("firm", obj[13]);
			map.put("dep", obj[14]);
			map.put("endCase", obj[15]);
			map.put("film", obj[16]);
			map.put("dep", obj[17]);
			map.put("jobLevel", obj[18]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}
	
	
	@Override
	public List<Object[]> getExceptData(String thePersonId) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT PTJ.ID,PTJ.STANDARD_POST,PTJ.SPE_POST,PTJ.BEGIN_TIME,PTJ.END_TIME,  ")
		.append("  PTJ.STATE,PTJ.BZ,PTJ.FIRM,PTJ.DEP,PTJ.END_CASE,PTJ.JOB_LEVER PTJLEVER  ")
		.append("  FROM JC_PT_JOB PTJ  ")
		.append("  WHERE PTJ.PERSON_ID='"+thePersonId+"'")
		;
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		List<Object[]> list=query.list();
		return list;
	}
	
}
