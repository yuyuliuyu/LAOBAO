package com.lingnet.hcm.dao.empdata;

import java.util.HashMap;
import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.hcm.entity.person.SoldierBack;
import com.lingnet.util.Pager;
/**
 *	军人复转Dao
 */
public interface SoldierBackDao extends BaseDao<SoldierBack, String>{
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds);
	
	public List<Object[]> getExceptData(String thePersonId);
}
