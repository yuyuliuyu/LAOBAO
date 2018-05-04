package com.lingnet.hcm.dao.empdata;

import java.util.HashMap;
import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.person.EmpWelfare;
import com.lingnet.util.Pager;
/**
 *	员工福利Dao
 */
public interface EmpWelfareDao extends BaseDao<EmpWelfare, String>{
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds);
	
	public List<Object[]> getExceptData(String thePersonId);
}
