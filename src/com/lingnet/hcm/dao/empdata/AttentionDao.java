package com.lingnet.hcm.dao.empdata;

import java.util.HashMap;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.person.Attention;
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.util.Pager;
/**
 *	员工关注Dao
 */
public interface AttentionDao extends BaseDao<Attention, String>{
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds);
}
