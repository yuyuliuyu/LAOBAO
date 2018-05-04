package com.lingnet.hcm.dao.impl.laobao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.laobao.YuangongDao;
import com.lingnet.hcm.entity.laobao.StaffInfo;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

@Repository("yuangongDao")
public class YuangongDaoImpl extends BaseDaoImpl<BasicInformation, String> implements YuangongDao{

	@Override
	public HashMap<String, Object> getPersonByDepId(Pager pager,String searchData) {
		
		StringBuilder sql=new StringBuilder()
		.append(" select t1.ID, DEPART_ID, t1.JOB_NUMBER,t1. NAME,t1.AGE,t1.SEX,t1.IS_MARRIED,t1.POST,t1.JOB_LEVEL,t1.EMP_TYPE,t1.ON_JOB,t1.ON_POST,t1.SPECIFIC_POST,t1.FILM_ID,   ")
		.append(" t1.FILM_NAME,t1.DEPART_NAME,shoe_number,clothes_number,CLASS_NAME ")
		.append("  from JC_BASIC_INFORMATION  t1 left outer join  staff_info t2 on t1.job_number=t2.job_number  ")
		.append(" where IS_DELETE = 0 ")
		;
		if (searchData != null){
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
				
				sql.append(" and t1.DEPART_ID in("+resql+")");
				
			}
			if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
				sql.append(" and t1.JOB_NUMBER LIKE'"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and t1.NAME like '%"+dataMap.get("name")+"%'");
			}
			if (dataMap.get("post") != null && !"".equals(dataMap.get("post"))){
				sql.append(" and POST ='"+dataMap.get("post")+"'");
			}
		}
		sql.append(" order by CREATEDATE DESC ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			map.put("age", obj[4]);
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			map.put("post", obj[7]);
			map.put("jobLevel", obj[8]);
			/*map.put("jobLevel", obj[8]);*/
			map.put("empType", obj[9]);
			map.put("onJob", obj[10]);
			map.put("onPost", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("shoe_number", obj[16]);
			map.put("clothes_number", obj[17]);
			
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
		
	}

	public StaffInfo getStaffByJobNumber(String jobNumber) {

		StringBuilder sql=new StringBuilder()
		.append("select job_number,shoe_number,clothes_number,class_name,class_id from STAFF_INFO where JOB_NUMBER='"+jobNumber+"'");
		StaffInfo staff=new StaffInfo();
		List list=findstaffBySql(sql.toString());
		
		try {
			System.out.println(list.get(0));
			System.out.println(((StaffInfo) list.get(0)).getClothes_number());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return (StaffInfo) list.get(0);
	}

    public List findstaffBySql(String sql) {
        SQLQuery query=this.getSession().createSQLQuery(sql);
        List a=new ArrayList();
        a=query.list();
        return query.list();
    }



}
