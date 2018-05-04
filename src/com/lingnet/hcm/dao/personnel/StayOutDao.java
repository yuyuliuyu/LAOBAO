package com.lingnet.hcm.dao.personnel;

import java.util.HashMap;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.hcm.entity.person.StayOut;
import com.lingnet.util.Pager;
/**
 *	工作履历Dao
 */
public interface StayOutDao extends BaseDao<StayOut, String>{
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds);
}
