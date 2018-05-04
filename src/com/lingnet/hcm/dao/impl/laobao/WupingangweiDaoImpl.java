package com.lingnet.hcm.dao.impl.laobao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.laobao.WupingangweiDao;
import com.lingnet.hcm.entity.laobao.Gangweiwupin;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

@Repository("wupingangweiDao")
public class WupingangweiDaoImpl extends BaseDaoImpl<Gangweiwupin, String> implements WupingangweiDao{

	public HashMap<String, Object> getPersonByDepId(Pager pager,String searchData) {
		
		StringBuilder sql=new StringBuilder()
		.append(" select  c.id,c.wpmc, c.ggxh,c.jldw,c.gys,c.createdate, c.nextdate  ")
		.append("  from biaozhun a inner join branch2 b on a.issue_id=b.id inner join wupin c on c.id=a.wp_id  ")
		.append(" where 1 = 1 ")
		;
		if (searchData != null){
			Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
			if (dataMap != null) {
			    if (dataMap.get("depId") != null && !"".equals(dataMap.get("depId"))){
			        String[] depIdArrs = dataMap.get("depId").split(",");
			        
			        String  resql="";
			        for (int i = 0; i < depIdArrs.length; i++){
			            if (!"".equals(depIdArrs[i])){
			                resql+="'" + depIdArrs[i] + "', ";
			            }
			        }
			        resql = resql.substring(0, resql.length()-2);
			        
			        sql.append(" and DEPART_ID in("+resql+")");
			        
			    }
			    if (dataMap.get("wpmc") != null && !"".equals(dataMap.get("wpmc"))){
			        sql.append(" and WPMC like '%"+dataMap.get("wpmc")+"'");
			    }
			    if (dataMap.get("ggxh") != null && !"".equals(dataMap.get("ggxh"))){
			        sql.append(" and GGXH like '%"+dataMap.get("ggxh")+"%'");
			    }
			    if (dataMap.get("post") != null && !"".equals(dataMap.get("post"))){
			        sql.append(" and POST ='"+dataMap.get("post")+"'");
			    }
			    // 岗位ID
			    if (!StringUtils.isBlank(dataMap.get("postId"))) {
			        sql.append(" and C.POST_ID ='"+dataMap.get("postId")+"'");
			    }
			}
		}
		sql.append(" order by C.CREATEDATE DESC ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("wpmc", obj[1]);
			map.put("ggxh", obj[2]);
			map.put("jldw", obj[3]);
			map.put("kcsl", obj[4]);
			map.put("gys", obj[5]);
			map.put("createdate", obj[6]);
			map.put("nextdate", obj[7]);

			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
		
	}
}
