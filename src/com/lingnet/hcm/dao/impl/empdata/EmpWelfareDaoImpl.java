package com.lingnet.hcm.dao.impl.empdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.empdata.EmpWelfareDao;
import com.lingnet.hcm.entity.person.EmpWelfare;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 人员岗位兼职Dao实现类
 */
@Repository("empWelfareDao")
public class EmpWelfareDaoImpl extends BaseDaoImpl<EmpWelfare, String> implements EmpWelfareDao{

	@Override
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.JOB_NUMBER,BAS.NAME PERNAME,BR.FZX,QD.NAME,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,   ")
		.append(" JEW.ID,JEW.WEL_TYPE,JEW.WEL_NAME,JEW.WEL_CONTENT,JEW.BEGINDATE,JEW.ENDDATE,JEW.ISEND,JEW.COST, ")
		.append(" JEW.UNIT_APPROVED,JEW.APPROVER,JEW.REMARK ")
		.append("  FROM JC_EMP_WELFARE JEW   ")
		.append(" LEFT JOIN JC_BASIC_INFORMATION BAS ON BAS.ID=JEW.PERSON_ID     ")
		.append(" LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append(" LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append(" LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append(" LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append(" WHERE 1=1  ")
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
			if (dataMap.get("welName") != null && !"".equals(dataMap.get("welName"))){
				sql.append(" and JEW.WEL_NAME like '%"+dataMap.get("welName")+"%'");
			}
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" ORDER BY JEW.ENDDATE DESC ");
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
			map.put("welType", obj[7]);
			map.put("welName", obj[8]);
			map.put("welContent", obj[9]);
			
			if(obj[10]!=null&&!"".equals(obj[10])){
				stamp=form.format(obj[10]);
				map.put("begindate", stamp);
			}
			if(obj[11]!=null&&!"".equals(obj[11])){
				stamp=form.format(obj[11]);
				map.put("enddate", stamp);
			}
			
			//map.put("begindate", obj[10]);//人名字
			//map.put("enddate", obj[11]);
			map.put("isend", obj[12]);
			map.put("cost", obj[13]);
			map.put("unitApproved", obj[14]);
			map.put("approver", obj[15]);
			map.put("remark", obj[16]);
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
		.append("  SELECT JEW.ID,JEW.WEL_TYPE,JEW.WEL_NAME,JEW.WEL_CONTENT,JEW.BEGINDATE,JEW.ENDDATE,JEW.ISEND,JEW.COST,  ")
		.append(" JEW.UNIT_APPROVED,JEW.APPROVER,JEW.REMARK ")
		.append("  FROM JC_EMP_WELFARE JEW   ")
		.append("  WHERE JEW.PERSON_ID='"+thePersonId+"'")
		;
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		List<Object[]> list=query.list();
		return list;
	}
}
