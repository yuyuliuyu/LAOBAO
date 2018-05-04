package com.lingnet.hcm.dao.empdata;

import java.util.HashMap;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.person.Education;
import com.lingnet.util.Pager;
/**
 *	工作履历Dao
 */
public interface EducationDao extends BaseDao<Education, String>{
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds);
}
