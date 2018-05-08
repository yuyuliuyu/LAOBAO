package com.lingnet.hcm.dao.impl.laobao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;
import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.laobao.WupininputDao;
import com.lingnet.hcm.entity.laobao.Phone;

import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

@Repository("wupininputDao")
public class WupininputDaoImpl extends BaseDaoImpl<Phone, String> implements WupininputDao{
	public HashMap<String, Object> getPersonByDepId(Pager pager,String searchData,String ids) {

		StringBuilder sql=new StringBuilder()
		.append(" select  p.id,p.department,p.postname,p.username,p.phonenumber, p.type")
		.append("  from phone p ")
/*		.append("  left join jc_basic_information jbi ")
		.append("  on c.staff_id = jbi.id ")
		.append(" where c.IS_SP = 1 ")*/
		//.append("and id in(1,2,3)")
		;

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
		    if (dataMap.get("department") != null && !"".equals(dataMap.get("department"))){
				sql.append(" and p.DEPARTMENT LIKE'"+dataMap.get("department")+"'");
			}

			if (dataMap.get("postname") != null && !"".equals(dataMap.get("postname"))){
				sql.append(" and p.POSTNAME LIKE'"+dataMap.get("postname")+"'");
			}
			if (dataMap.get("username") != null && !"".equals(dataMap.get("username"))){
				sql.append(" and p.USERNAME LIKE'"+dataMap.get("username")+"'");
			}	
			if (dataMap.get("phonenumber") != null && !"".equals(dataMap.get("phonenumber"))){
				sql.append(" and c.PHONENEMBER like '%"+dataMap.get("phonenumber")+"%'");
			}
			if (dataMap.get("type") != null && !"".equals(dataMap.get("type"))){
				sql.append(" and c.TYPE like '%"+dataMap.get("type")+"%'");
			}

		}
		sql.append(" order by p.TYPE DESC ");

		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();

		List dataList = new ArrayList();

		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", obj[0]);
			map.put("department", obj[1]);
			map.put("postname", obj[2]);
			map.put("username", obj[3]);
			map.put("phonenumber", obj[4]);
			map.put("type", obj[5]);


			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", dataList);
		map.put("total", pager.getTotalCount());

		return map;

	}

}
