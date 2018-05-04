package com.lingnet.hcm.dao.personnel;

import java.util.HashMap;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.person.Abroad;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.util.Pager;
/**
 *	工作履历Dao
 */
public interface AbroadDao extends BaseDao<Abroad, String>{
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds);
}
