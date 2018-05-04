package com.lingnet.hcm.dao.impl.laobao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;
import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.laobao.WupingangweiDao;
import com.lingnet.hcm.dao.laobao.WupinhistoryDao;
import com.lingnet.hcm.entity.laobao.Gangweiwupin;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

@Repository("wupinhistoryDao")
public class WupinhistoryDaoImpl extends BaseDaoImpl<Gangweiwupin, String> implements WupinhistoryDao{
	public HashMap<String, Object> getPersonByDepId(Pager pager,String searchData,String ids) {

		StringBuilder sql=new StringBuilder()
		.append(" select  c.id,jbi.JOB_NUMBER,c.name,c.wpmc, c.count,c.ggxh,c.jldw,c.createdate, c.nextdate  ")
		.append("  from history c ")
		.append("  left join jc_basic_information jbi ")
		.append("  on c.staff_id = jbi.id ")
		.append(" where c.IS_SP = 1 ")
		//.append("and id in(1,2,3)")
		;
		   if (ids != null && !ids.equals("")) {
		 
		        String[] idArr = ids.split(",");
		        sql.append("   AND jbi.JOB_NUMBER IN ('"+StringUtils.join(idArr, "','")+"')");
		    }
		if (searchData != null){
			Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
			if (null != dataMap) {
			    
			}
		    // 判断是否选中

/*			 if (ids != null && !ids.equals("")) {
					 
			        String[] idArr = ids.split(",");
			        sql.append("   AND jbi.JOB_NUMBER IN ('"+StringUtils.join(idArr, "','")+"')");
			  }*/
		    List<?> list = findBySql(sql.toString());
		    if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
				sql.append(" and JBI.JOB_NUMBER LIKE'"+dataMap.get("jobNumber")+"'");
			}

			if (dataMap.get("createdate") != null && !"".equals(dataMap.get("createdate"))){
				sql.append(" and to_char(C.CREATEDATE,'yyyymm') = '"+dataMap.get("createdate")+"'");
			}
			if (dataMap.get("nextdate") != null && !"".equals(dataMap.get("nextdate"))){
				sql.append(" and to_char(C.NEXTDATE,'yyyymm') = '"+dataMap.get("nextdate")+"'");
			}			
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and c.NAME like '%"+dataMap.get("name")+"%'");
			}
			if (dataMap.get("wpmc") != null && !"".equals(dataMap.get("wpmc"))){
				sql.append(" and c.WPMC like '%"+dataMap.get("wpmc")+"%'");
			}

		}
		sql.append(" order by c.CREATEDATE DESC ");

		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();

		List dataList = new ArrayList();

		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", obj[0]);
			map.put("jobNumber", obj[1]);
			map.put("name", obj[2]);
			map.put("wpmc", obj[3]);
			map.put("count", obj[4]);
			map.put("ggxh", obj[5]);
			map.put("jldw", obj[6]);
			map.put("createdate", obj[7].toString());
			map.put("nextdate", obj[8].toString());
			

			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", dataList);
		map.put("total", pager.getTotalCount());

		return map;

	}

}
