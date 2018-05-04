package com.lingnet.hcm.dao.impl.laobao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.laobao.GangweiDao;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

@Repository("gangweiDao")
public class GangweiDaoImpl extends BaseDaoImpl<BasicInformation, String> implements GangweiDao{

/*    @SuppressWarnings({ "rawtypes" })
    public List getUsersByRoleId(Pager pager,String roleId){
        
    
        pager = findPager(pager, getSession().createCriteria(QxUsers.class).add(Restrictions.sqlRestriction("id in(select user_id from qx_user_role where role_id = ?)",roleId,Hibernate.STRING)));
        
        return pager.getResult();
        
    }*/
	public HashMap<String, Object> getPersonByDepId(Pager pager,String searchData) {
		
		StringBuilder sql=new StringBuilder()
		.append(" select bz.id,bz.name, bz.maqm,bz.daqm,bz.cxgz,bz.dxgz,bz.yrf,bz.yy,bz.yx,bz.maqx,bz.jyx,bz.jyst,bz.dhst,DP.POSITION_NAME  ")
		.append("  from fafang_biaozhun bz ")
		.append("  left join DEPT_POSITION DP ")
		.append("  ON BZ.NAME = DP.ID ")
		/*.append(" where 1 = 1 ")*/
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
				
				sql.append(" and DEPART_ID in("+resql+")");
				
			}
			if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
				sql.append(" and JOB_NUMNER ='"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BZ.POSITION_NAME like '%"+dataMap.get("name")+"%'");
			}
			if (dataMap.get("post") != null && !"".equals(dataMap.get("post"))){
				sql.append(" and POST ='"+dataMap.get("post")+"'");
			}
		}
		//sql.append(" order by CREATEDATE DESC ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("name", obj[1]);
			map.put("maqm", obj[2]);
			map.put("daqm", obj[3]);
			map.put("cxgz", obj[4]);
			map.put("dxgz", obj[5]);
			map.put("yrf", obj[6]);
			map.put("yy", obj[7]);
			map.put("yx", obj[8]);
			map.put("maqx", obj[9]);
			map.put("jyx", obj[10]);
			map.put("jyst", obj[11]);
			map.put("dhst", obj[12]);
			map.put("positionName", obj[13]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
		
	}

@Override
public List getUsersByRoleId(Pager pager, String roleId) {
	// TODO Auto-generated method stub
	return null;
}

}
