package com.lingnet.hcm.dao.impl.empdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.empdata.CareerDao;
import com.lingnet.hcm.dao.empdata.PunishDao;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.Punish;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 人员岗位兼职Dao实现类
 */
@Repository("punishDao")
public class PunishDaoImpl extends BaseDaoImpl<Punish, String> implements PunishDao{

	@Override
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append(" SELECT BAS.JOB_NUMBER,BAS.NAME PERNAME,BR.FZX,QD.NAME,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append(" JP.ID,JP.PUNISH_NAME,JP.PUNISHDATE,JP.APPROVE_NUM,JP.APPROVE_OFFICE,JP.PUNISH_CLASS,JP.PUNISH_TYPE, ")
		.append(" JP.PUNISH_LEVEL,JP.PUNISH_CAUSE,JP.PUNISH_STEP,JP.PUNISH_AMOUNT,JP.BEGINDATE,JP.ENDDATE,JP.IS_RECALL, ")
		.append(" JP.RECALL_OFFICE,JP.RECALL_NUM,JP.RECALLDATE,JP.FILE_NUM,JP.FILE_NAME,JP.REMARK,JP.URL ")
		.append(" FROM JC_PUNISH JP ")
		.append(" LEFT JOIN JC_BASIC_INFORMATION BAS ON BAS.ID=JP.PERSON_ID  ")
		.append(" LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append(" LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append(" LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append(" LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append(" WHERE 1=1 ")
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
			if (dataMap.get("punishName") != null && !"".equals(dataMap.get("punishName"))){
				sql.append(" and JP.PUNISH_NAME like '%"+dataMap.get("punishName")+"%'");
			}
			
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" ORDER BY JP.BEGINDATE DESC ");
		
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
			map.put("punishName", obj[7]);
			
			if(obj[8]!=null&&!"".equals(obj[8])){
				stamp=form.format(obj[8]);
				map.put("punishdate", stamp);
			}
			map.put("approveNum", obj[9]);
			map.put("approveOffice", obj[10]);//
			map.put("punishClass", obj[11]);
			map.put("punishType", obj[12]);
			map.put("punishLevel", obj[13]);
			map.put("punishCause", obj[14]);
			map.put("punishStep", obj[15]);
			map.put("punishAmount", obj[16]);
			if(obj[17]!=null&&!"".equals(obj[17])){
				stamp=form.format(obj[17]);
				map.put("begindate", stamp);
			}
			if(obj[18]!=null&&!"".equals(obj[18])){
				stamp=form.format(obj[18]);
				map.put("enddate", stamp);
			}
			map.put("isRecall", obj[19]);
			map.put("recallOffice", obj[20]);
			map.put("recallNum", obj[21]);
			if(obj[22]!=null&&!"".equals(obj[22])){
				stamp=form.format(obj[22]);
				map.put("recalldate", stamp);
			}
			map.put("fileNum", obj[23]);
			
			map.put("fileName", "<a href='#' onClick=\"filedownload('"+obj[6]+"')\">"+obj[24]+"</a>");
			map.put("url", obj[26]);
			map.put("remark", obj[25]);
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
		.append("  SELECT JP.ID,JP.PUNISH_NAME,JP.PUNISHDATE,JP.APPROVE_NUM,JP.APPROVE_OFFICE,JP.PUNISH_CLASS,JP.PUNISH_TYPE,  ")
		.append(" JP.PUNISH_LEVEL,JP.PUNISH_CAUSE,JP.PUNISH_STEP,JP.PUNISH_AMOUNT,JP.BEGINDATE,JP.ENDDATE,JP.IS_RECALL, ")
		.append(" JP.RECALL_OFFICE,JP.RECALL_NUM,JP.RECALLDATE,JP.FILE_NUM,JP.FILE_NAME,JP.REMARK,JP.URL ")
		.append(" FROM JC_PUNISH JP ")
		.append("  WHERE JP.PERSON_ID='"+thePersonId+"'")
		;
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		List<Object[]> list=query.list();
		return list;
	}
}
