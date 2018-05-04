package com.lingnet.hcm.dao.empdata;

import java.util.HashMap;
import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.License;
import com.lingnet.util.Pager;
/**
 *	证照Dao
 */
public interface LicenseDao extends BaseDao<License, String>{
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds);
	
	public List<Object[]> getExceptData(String thePersonId);
}
