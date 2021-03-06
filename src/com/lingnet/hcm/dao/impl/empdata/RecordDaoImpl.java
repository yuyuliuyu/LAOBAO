package com.lingnet.hcm.dao.impl.empdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.empdata.RecordDao;
import com.lingnet.hcm.entity.person.Record;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ToolUtil;

/**
 * 档案  Dao实现类
 */
@Repository("recordDao")
public class RecordDaoImpl extends BaseDaoImpl<Record, String> implements RecordDao{

	@Override
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append(" SELECT BAS.JOB_NUMBER,BAS.NAME PERNAME,BR.FZX,QD.NAME,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append(" RE.ID,RE.RE_TYPE,RE.RE_NAME,RE.RE_FILE_NUM,RE.RE_CONTENT,RE.RE_YEAR,RE.RE_NUM,RE.EXTEND_NAME, ")
		.append(" RE.FILE_SIZE,RE.REMARK,RE.CREATE_NAME,RE.CREATEDATE,RE.CREATE_NUM,RE.PERSON_ID,RE.URL ")
		.append(" FROM JC_E_RECORD RE ")
		.append(" LEFT JOIN JC_BASIC_INFORMATION BAS ON BAS.ID=RE.PERSON_ID  ")
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
			if (dataMap.get("reName") != null && !"".equals(dataMap.get("reName"))){
				sql.append(" and RE.RE_NAME like '%"+dataMap.get("reName")+"%'");
			}
			if (dataMap.get("reYear") != null && !"".equals(dataMap.get("reYear"))){
				sql.append(" and RE.RE_YEAR like '%"+dataMap.get("reYear")+"%'");
			}
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" ORDER BY RE.CREATEDATE DESC ");
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
			map.put("reType", obj[7]);
			map.put("reName", obj[8]);
			map.put("reFileNum", obj[9]);
			map.put("reContent", obj[10]);
			map.put("reYear", obj[11]);
			map.put("reNum", obj[12]);
			map.put("extendName", obj[13]);
			map.put("fileSize", obj[14]);
			map.put("remark", obj[15]);
			map.put("createName", obj[16]);
			//map.put("createDate", obj[17]);
			if(obj[17]!=null&&!"".equals(obj[17])){
				stamp=form.format(obj[17]);
				map.put("createDate", stamp);
			}
			map.put("createNum", obj[18]);
			if(obj[20]!=null&&!"".equals(obj[20])){
				String pathbase = ToolUtil.getPropert("config.properties", "file_path");
                map.put("realUrl", pathbase+obj[20]);
			}
			
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
		.append("  SELECT RE.ID,RE.RE_TYPE,RE.RE_NAME,RE.RE_FILE_NUM,RE.RE_CONTENT,RE.RE_YEAR,RE.RE_NUM,RE.EXTEND_NAME,  ")
		.append(" RE.FILE_SIZE,RE.REMARK,RE.CREATE_NAME,RE.CREATEDATE,RE.CREATE_NUM,RE.PERSON_ID ")
		.append(" FROM JC_E_RECORD RE ")
		.append("  WHERE RE.PERSON_ID='"+thePersonId+"'")
		;
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		List<Object[]> list=query.list();
		return list;
	}
}
