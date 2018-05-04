package com.lingnet.hcm.dao.impl.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.check.InstitutionDao;
import com.lingnet.hcm.entity.check.CkInstitution;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: InstitutionDaoImpl 
 * @Description: 班制名称Dao实现类 
 * @author wangqiang
 * @date 2017年3月31日 上午10:59:40 
 *
 */
@Repository("institutionDao")
public class InstitutionDaoImpl extends BaseDaoImpl<CkInstitution, String> implements InstitutionDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getInfoByCond(Pager pager) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		String sql = "select ID, INSTITUTE_NAME, OVERTIME_MODULUS, VACATION_MODULUS "
				+ "from CK_INSTITUTION where IS_DELETE = 0 ORDER BY CREATEDATE";
		Pager pageInfo = this.findPagerBySql(pager, sql);
		List<Object[]> list = (List<Object[]>) pageInfo.getResult();
		if (list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Map<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("id", list.get(i)[0]);
				hashmap.put("instituteName", list.get(i)[1]);
				hashmap.put("overtimeModulus", list.get(i)[2]);
				hashmap.put("vacationModulus", list.get(i)[3]);
				mapList.add(hashmap);
			}
		}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllInfoList() {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		List<CkInstitution> list = this.getSession().createCriteria(CkInstitution.class)
				.add(Restrictions.eq("isDelete", 0)).addOrder(Order.asc("createDate")).list();
		if (list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Map<String, String> hashmap = new HashMap<String, String>();
				hashmap.put("instituteName", list.get(i).getInstituteName()+"");
				hashmap.put("overtimeModulus", list.get(i).getOvertimeModulus()+"");
				hashmap.put("vacationModulus", list.get(i).getVacationModulus()+"");
				mapList.add(hashmap);
			}
		}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getInstitutionList(String dayFlag) {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Criteria c = this.getSession().createCriteria(CkInstitution.class);
		if ("N".equals(dayFlag)){
			c.add(Restrictions.ne("instituteName", "白班"));
		}
		List<CkInstitution> list = c.add(Restrictions.eq("isDelete", 0)).list();
		if (list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Map<String, String> hashmap = new HashMap<String, String>();
				hashmap.put("id", list.get(i).getId()+"");
				hashmap.put("text", list.get(i).getInstituteName()+"");
				mapList.add(hashmap);
			}
		}
		return mapList;
	}

	@Override
	public String getInstituteIdByName(String name) {
		String sql = "select ID from CK_INSTITUTION where INSTITUTE_NAME ='"+name+"'";
		Object id = this.getSession().createSQLQuery(sql).uniqueResult();
		if (id != null){
			return id+"";
		}
		return null;
	}

	@Override
	public CkInstitution getInfoByName(String institueName) {
		return (CkInstitution) this.getSession().createCriteria(CkInstitution.class)
				.add(Restrictions.eq("instituteName", institueName))
				.add(Restrictions.eq("isDelete", 0)).uniqueResult();
	}
	
}
