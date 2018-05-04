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
import com.lingnet.hcm.dao.empdata.SkilledEmpDao;
import com.lingnet.hcm.entity.person.EmpSkill;
import com.lingnet.hcm.entity.person.SkilledEmp;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 技工信息Dao实现类
 */
@Repository("skilledEmpDao")
public class SkilledEmpDaoImpl extends BaseDaoImpl<SkilledEmp, String> implements SkilledEmpDao{

	@Override
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append(" SELECT BAS.JOB_NUMBER,BAS.NAME PERNAME,BR.FZX,QD.NAME,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  JSE.ID,JSE.SKILL_TYPE,JSE.SKILL_GRADE,JSE.JUDGE_OFFICE,JSE.PAPERS_NUM,JSE.OFFICE,JSE.AWARDDATE,JSE.PAPERS_OFFICE, ")
		.append("  JSE.GRADE,JSE.HIGHEST_LEVEL,JSE.APPELLATION,JSE.BEGINDATE,JSE.GET_FORM,JSE.ENDDATE,JSE.TIMES,JSE.REMARK   ")
		.append("  FROM JC_SKILLED_EMP JSE    ")
		.append("  LEFT JOIN JC_BASIC_INFORMATION BAS ON BAS.ID=JSE.PERSON_ID   ")
		.append(" LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append(" LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append(" LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append(" LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append("  WHERE 1=1 ")
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
		sql.append("  ORDER BY JSE.ENDDATE DESC    ");
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
			map.put("skillType", obj[7]);
			map.put("skillGrade", obj[8]);
			map.put("judgeOffice", obj[9]);
			map.put("papersNum", obj[10]);//人名字
			map.put("office", obj[11]);
			if(obj[12]!=null&&!"".equals(obj[12])){
				stamp=form.format(obj[12]);
				map.put("awarddate", stamp);
			}
			//map.put("awarddate", obj[12]);
			map.put("papersOffice", obj[13]);
			
			/*if(obj[12]!=null&&!"".equals(obj[12])){
				stamp=form.format(obj[12]);
				map.put("begindate", stamp);
			}
			if(obj[13]!=null&&!"".equals(obj[13])){
				stamp=form.format(obj[13]);
				map.put("enddate", stamp);
			}*/
			map.put("grade", obj[14]);
			map.put("highestLevel", obj[15]);
			map.put("appellation", obj[16]);
			if(obj[17]!=null&&!"".equals(obj[17])){
				stamp=form.format(obj[17]);
				map.put("begindate", stamp);
			}
			//map.put("begindate", obj[17]);
			map.put("getForm", obj[18]);
			if(obj[19]!=null&&!"".equals(obj[19])){
				stamp=form.format(obj[19]);
				map.put("enddate", stamp);
			}
			//map.put("enddate", obj[19]);
			map.put("times", obj[20]);
			map.put("remark", obj[21]);
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
		.append("  SELECT JSE.ID,JSE.SKILL_TYPE,JSE.SKILL_GRADE,JSE.JUDGE_OFFICE,JSE.PAPERS_NUM,JSE.OFFICE,JSE.AWARDDATE,JSE.PAPERS_OFFICE, ")
		.append("  JSE.GRADE,JSE.HIGHEST_LEVEL,JSE.APPELLATION,JSE.BEGINDATE,JSE.GET_FORM,JSE.ENDDATE,JSE.TIMES,JSE.REMARK   ")
		.append("  FROM JC_SKILLED_EMP JSE    ")
		.append("  WHERE JSE.PERSON_ID='"+thePersonId+"'")
		;
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		List<Object[]> list=query.list();
		return list;
	}
}
