package com.lingnet.hcm.dao.empdata;

import java.util.HashMap;
import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.person.JobTitle;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.util.Pager;
/**
 *	职称聘任Dao
 */
public interface JobTitleDao extends BaseDao<JobTitle, String>{
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds);
	
	public List<Object[]> getExceptData(String thePersonId);
}
