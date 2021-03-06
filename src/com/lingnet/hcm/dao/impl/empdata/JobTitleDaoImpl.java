package com.lingnet.hcm.dao.impl.empdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.empdata.JobTitleDao;
import com.lingnet.hcm.dao.empdata.RelationDao;
import com.lingnet.hcm.entity.person.JobTitle;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 人员岗位兼职Dao实现类
 */
@Repository("jobTitleDao")
public class JobTitleDaoImpl extends BaseDaoImpl<JobTitle, String> implements JobTitleDao{

	@Override
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append(" SELECT BAS.JOB_NUMBER,BAS.NAME PERNAME,BR.FZX,QD.NAME,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("    JJT.ID,JJT.CLASS_TYPE,JJT.STATUS_CLASS,JJT.PROFESSION,JJT.JUGDE_OFFICE,JJT.CERTIFICATE_NUM, ")
		.append("    JJT.SEND_CERTIFICATE,TO_CHAR(JJT.AWARD_DATE,'YYYY-MM-DD'),JJT.CERTIFICATE_OFFICE,JJT.IS_HIGHEST,JJT.FOREIGN_TYPE,  ")
		.append("    JJT.FOREIGN_LEVEL,JJT.FOREIGN_CLASS,TO_CHAR(JJT.FOREIGN_DATE,'YYYY-MM-DD'),JJT.FOREIGN_REMARK,TO_CHAR(JJT.COMPUTER_DATE,'YYYY-MM-DD'),  ")
		.append("    JJT.COMPUTER_REMARK,TO_CHAR(JJT.PROFESSION_DATE,'YYYY-MM-DD'),JJT.PROFESSION_REMARK,JJT.DECLARE_LEVEL1,   ")
		.append("    TO_CHAR(JJT.DECLARE_DATE1,'YYYY-MM-DD'),JJT.DECLARE_LEVEL2,TO_CHAR(JJT.DECLARE_DATE2,'YYYY-MM-DD'),JJT.ENGAGE_TITLE,TO_CHAR(JJT.BEGIN_DATE,'YYYY-MM-DD'),  ")
		.append("    TO_CHAR(JJT.END_DATE,'YYYY-MM-DD'),JJT.ENGAGE_TIMES,JJT.REMARK    ")
		.append("    FROM JC_JOB_TITLE JJT   ")
		.append("    LEFT JOIN JC_BASIC_INFORMATION BAS ON BAS.ID=JJT.PERSON_ID   ")
		.append(" LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append(" LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append(" LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append(" LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append("    WHERE 1=1   ")
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
			if (dataMap.get("engageTitle") != null && !"".equals(dataMap.get("engageTitle"))){
				sql.append(" and JJT.ENGAGE_TITLE like '%"+dataMap.get("engageTitle")+"%'");
			}
			if (dataMap.get("statusClass") != null && !"".equals(dataMap.get("statusClass"))){
				sql.append(" and JJT.STATUS_CLASS = '"+dataMap.get("statusClass")+"'");
			}
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append("   ORDER BY JJT.BEGIN_DATE DESC ");
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
			map.put("classType", obj[7]);
			map.put("statusClass", obj[8]);
			map.put("profession", obj[9]);
			map.put("jugdeOffice", obj[10]);
			
			map.put("certificateNum", obj[11]);
			map.put("sendCertificate", obj[12]);
			map.put("awardDate", obj[13]);
			map.put("certificateOffice", obj[14]);
			map.put("isHighest", obj[15]);
			map.put("foreignType", obj[16]);
			map.put("foreignLevel", obj[17]);
			map.put("foreignClass", obj[18]);
			map.put("foreignDate", obj[19]);
			map.put("foreignRemark", obj[20]);
			map.put("computerDate", obj[21]);
			map.put("computerRemark", obj[22]);
			map.put("professionDate", obj[23]);
			map.put("professionRemark", obj[24]);
			map.put("declareLevel1", obj[25]);
			map.put("declareDate1", obj[26]);
			map.put("declareLevel2", obj[27]);
			map.put("declareDate2", obj[28]);
			map.put("engageTitle", obj[29]);
			map.put("beginDate", obj[30]);
			map.put("endDate", obj[31]);
			map.put("engageTimes", obj[32]);
			map.put("remark", obj[33]);
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
		.append("  SELECT JJT.ID,JJT.CLASS_TYPE,JJT.STATUS_CLASS,JJT.PROFESSION,JJT.JUGDE_OFFICE,JJT.CERTIFICATE_NUM,  ")
		.append("    JJT.SEND_CERTIFICATE,TO_CHAR(JJT.AWARD_DATE,'YYYY-MM-DD'),JJT.CERTIFICATE_OFFICE,JJT.IS_HIGHEST,JJT.FOREIGN_TYPE,  ")
		.append("    JJT.FOREIGN_LEVEL,JJT.FOREIGN_CLASS,TO_CHAR(JJT.FOREIGN_DATE,'YYYY-MM-DD'),JJT.FOREIGN_REMARK,TO_CHAR(JJT.COMPUTER_DATE,'YYYY-MM-DD'),  ")
		.append("    JJT.COMPUTER_REMARK,TO_CHAR(JJT.PROFESSION_DATE,'YYYY-MM-DD'),JJT.PROFESSION_REMARK,JJT.DECLARE_LEVEL1,   ")
		.append("    TO_CHAR(JJT.DECLARE_DATE1,'YYYY-MM-DD'),JJT.DECLARE_LEVEL2,TO_CHAR(JJT.DECLARE_DATE2,'YYYY-MM-DD'),JJT.ENGAGE_TITLE,TO_CHAR(JJT.BEGIN_DATE,'YYYY-MM-DD'),  ")
		.append("    TO_CHAR(JJT.END_DATE,'YYYY-MM-DD'),JJT.ENGAGE_TIMES,JJT.REMARK    ")
		.append("    FROM JC_JOB_TITLE JJT   ")
		.append("    WHERE JJT.PERSON_ID='"+thePersonId+"'")
		;
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		List<Object[]> list=query.list();
		return list;
	}
	
}
