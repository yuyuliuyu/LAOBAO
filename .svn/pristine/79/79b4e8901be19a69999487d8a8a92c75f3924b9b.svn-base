package com.lingnet.hcm.dao.impl.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.check.ClassGroupDao;
import com.lingnet.hcm.entity.check.CkClass;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: ClassGroupDaoImpl 
 * @Description: 班组信息Dao实现类 
 * @author wangqiang
 * @date 2017年3月22日 下午2:09:54 
 *
 */
@Repository("classGroupDao")
public class ClassGroupDaoImpl extends BaseDaoImpl<CkClass, String> 
	implements ClassGroupDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getInfoByCond(Pager pager, String userId) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		String sql = "select cc.ID, cc.CLASS_NO, cc.CLASS_NAME, ci.INSTITUTE_NAME "
				+ "from CK_CLASS cc "
				+ "left join CK_INSTITUTION ci on cc.INSTITUTE_ID = ci.ID "
				+ "where cc.IS_DELETE = 0 and cc.USER_ID = '" + userId + "' ORDER BY cc.CREATEDATE";
		Pager pageInfo = this.findPagerBySql(pager, sql);
		List<Object[]> list = (List<Object[]>) pageInfo.getResult();
		if (list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Map<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("id", list.get(i)[0]);
				hashmap.put("classNo", list.get(i)[1]);
				hashmap.put("className", list.get(i)[2]);
				hashmap.put("instituteName", list.get(i)[3]);
				mapList.add(hashmap);
			}
		}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAllInfoList(String userId) {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		String sql = "select cc.CLASS_NO, cc.CLASS_NAME, ci.INSTITUTE_NAME "
				+ "from CK_CLASS cc "
				+ "left join CK_INSTITUTION ci on cc.INSTITUTE_ID = ci.ID "
				+ "where cc.IS_DELETE = 0 and cc.USER_ID = '" + userId + "' ORDER BY cc.CREATEDATE";
		List<Object[]> list = this.getSession().createSQLQuery(sql).list();
		if (list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Map<String, String> hashmap = new HashMap<String, String>();
				hashmap.put("classNo", list.get(i)[0]+"");
				hashmap.put("className", list.get(i)[1]+"");
				hashmap.put("instituteName", list.get(i)[2]+"");
				mapList.add(hashmap);
			}
		}
		return mapList;
	}

	@Override
	public CkClass getInfoByClassNo(String classNo) {
		return (CkClass) this.getSession().createCriteria(CkClass.class)
				.add(Restrictions.eq("classNo", classNo))
				.add(Restrictions.eq("isDelete", 0)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CkClass> getEntityListByUserId(String userId) {
		return this.getSession().createCriteria(CkClass.class)
				.add(Restrictions.eq("userId", userId)).add(Restrictions.eq("isDelete", 0)).list();
	}
	
	
}
