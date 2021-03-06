package com.lingnet.hcm.dao.impl.empdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.empdata.AttentionDao;
import com.lingnet.hcm.dao.empdata.AwardDao;
import com.lingnet.hcm.dao.empdata.CareerDao;
import com.lingnet.hcm.entity.person.Attention;
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 员工关注Dao实现类
 */
@Repository("attentionDao")
public class AttentionDaoImpl extends BaseDaoImpl<Attention, String> implements AttentionDao{

	@Override
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append(" SELECT BAS.JOB_NUMBER,BAS.NAME PERNAME,BR.FZX,QD.NAME,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  JA.ID,JA.ATTENTION_TYPE,JA.ATTENTION_DES,JA.REMARK,JA.ATTENTION_STATUS ")
		.append("  FROM JC_ATTENTION JA ")
		.append("  LEFT JOIN JC_BASIC_INFORMATION BAS ON BAS.ID=JA.PERSON_ID   ")
		.append("  LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
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
			if (dataMap.get("personId") != null && !"".equals(dataMap.get("personId"))){
				sql.append(" and BAS.ID = '"+dataMap.get("personId")+"'");
			}
			
		}else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append("  ORDER BY JA.CREATEDATE DESC   ");
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
			map.put("attentionType", obj[7]);
			map.put("attentionDes", obj[8]);
			map.put("remark", obj[9]);//人名字
			map.put("attentionStatus", obj[10]);//人名字
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}
}
