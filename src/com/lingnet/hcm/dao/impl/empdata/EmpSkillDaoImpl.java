package com.lingnet.hcm.dao.impl.empdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.empdata.EmpSkillDao;
import com.lingnet.hcm.entity.person.EmpSkill;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 人员岗位兼职Dao实现类
 */
@Repository("empSkillDao")
public class EmpSkillDaoImpl extends BaseDaoImpl<EmpSkill, String> implements EmpSkillDao{

	@Override
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.JOB_NUMBER,BAS.NAME PERNAME,BR.FZX,QD.NAME,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,    ")
		.append("  JES.ID,JES.SKILL_DES,JES.PAPERS_CLASS,JES.PAPERS_TYPE,JES.PAPERS_NUM,JES.PAPERS_STATUS,JES.BEGINDATE, ")
		.append("  JES.ENDDATE,JES.VALIDITY_PERIOD,JES.DATES,JES.OFFICE,JES.REMARK  ")
		.append("  FROM JC_EMP_SKILL JES   ")
		.append("  LEFT JOIN JC_BASIC_INFORMATION BAS ON BAS.ID=JES.PERSON_ID   ")
		.append(" LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append(" LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append(" LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append(" LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append("  WHERE 1=1    ")
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
			if (dataMap.get("skillDes") != null && !"".equals(dataMap.get("skillDes"))){
				sql.append(" and JES.SKILL_DES like '%"+dataMap.get("skillDes")+"%'");
			}
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" ORDER BY JES.ENDDATE DESC ");
		
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
			
			map.put("id", obj[6]);
			map.put("skillDes", obj[7]);
			map.put("papersClass", obj[8]);
			map.put("papersType", obj[9]);
			map.put("papersNum", obj[10]);//人名字
			map.put("papersStatus", obj[11]);
			if(obj[12]!=null&&!"".equals(obj[12])){
				stamp=form.format(obj[12]);
				map.put("begindate", stamp);
			}
			if(obj[13]!=null&&!"".equals(obj[13])){
				stamp=form.format(obj[13]);
				map.put("enddate", stamp);
			}
			map.put("validityPeriod", obj[14]);
			map.put("dates", obj[15]);
			map.put("office", obj[16]);
			map.put("remark", obj[17]);
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
		.append("  SELECT JES.ID,JES.SKILL_DES,JES.PAPERS_CLASS,JES.PAPERS_TYPE,JES.PAPERS_NUM,JES.PAPERS_STATUS,JES.BEGINDATE, ")
		.append("  JES.ENDDATE,JES.VALIDITY_PERIOD,JES.DATES,JES.OFFICE,JES.REMARK  ")
		.append("  FROM JC_EMP_SKILL JES   ")
		.append("  WHERE JES.PERSON_ID='"+thePersonId+"'")
		;
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		List<Object[]> list=query.list();
		return list;
	}
}
