package com.lingnet.hcm.dao.impl.personnel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.empdata.RelationDao;
import com.lingnet.hcm.dao.personnel.AbroadDao;
import com.lingnet.hcm.entity.person.Abroad;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 出国Dao实现类
 */
@Repository("abroadDao")
public class AbroadDaoImpl extends BaseDaoImpl<Abroad, String> implements AbroadDao{

	@Override
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.JOB_NUMBER,BAS.NAME PERNAME,BAS.FILM_NAME,BAS.DEPART_NAME,BAS.POST,BAS.SPECIFIC_POST,    ")
		.append("  JA.ID,JA.GOAL_PIACE,JA.ABROAD_GOAL,JA.ABROAD_CASE,TO_CHAR(JA.BEGINDATE,'YYYY-MM-DD'), ")
		.append("  TO_CHAR(JA.PLANENDDATE,'YYYY-MM-DD'),TO_CHAR(JA.ENDDATE,'YYYY-MM-DD'),JA.ID_TYPE,JA.ID_NUM,JA.STATUS  ")
		.append("  FROM JC_ABROAD JA   ")
		.append("  LEFT JOIN JC_BASIC_INFORMATION BAS ON BAS.JOB_NUMBER=JA.JOB_NUMBER    ")
		.append("  WHERE 1=1   ")
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
			if (dataMap.get("goalPiace") != null && !"".equals(dataMap.get("goalPiace"))){
				sql.append(" and JA.GOAL_PIACE like '%"+dataMap.get("goalPiace")+"%'");
			}
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" ORDER BY JA.BEGINDATE DESC ");
		pager=this.findPagerBySql(pager, sql.toString());
		
		List list = pager.getResult();
        List dataList = new ArrayList();
        
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
			map.put("goalPiace", obj[7]);
			map.put("abroadGoal", obj[8]);
			map.put("abroadCase", obj[9]);
			map.put("begindate", obj[10]);
			map.put("planenddate", obj[11]);
			map.put("enddate", obj[12]);
			map.put("idType", obj[13]);
			map.put("idNum", obj[14]);
			map.put("status", obj[15]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}
}
